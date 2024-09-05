/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.adquisicones.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.Compra;
import ec.renafipse.mks.modelo.adquisiciones.PagoCompra;
import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalle;
import ec.renafipse.mks.modelo.adquisiciones.Proveedor;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfip;
import ec.renafipse.mks.modelo.adquisiciones.SustentoTributario;
import ec.renafipse.mks.modelo.adquisiciones.TipoComprobanteCompra;
import ec.renafipse.mks.negocio.adquisiciones.CompraFacade;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorIfipFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author renafipse1
 */
@ManagedBean(name = "listadoComprasBean")
@ViewScoped
public class ListadoComprasBean extends AbstractController<Compra> implements Serializable {

    @EJB
    private CompraFacade ejbFacadeCompras;

    @EJB
    private ProveedorIfipFacade ejbFacadeProveedorIfip;

    @Inject
    private LlamaSP llamaSP;

    // DECLARACION DE LAS VARIABLES
    private Long criterio;

    private String msg;
    private String buscarPor;
    private String mensajeCriterio;
    private String ciRucProveedor;
    private String comprasDireccionProveedor;
    private String numeroCuentaCre;
    private String nombreProevedorCompleto; /// para la pantalla principal
    private String nombreProveedorBusqueda; // para el dialogo
    private String mensajeDialogo;
    private String estadoRiseProveedor;
    private boolean deshabilitaBuscarProveedor;

    private Date fechaInicio;
    private Date fechaFin;

    private Compra compraSel;
    private ProveedorIfip proveedorIfipSel;
    private SustentoTributario sustentoTributario;
    private ProveedorIfip provedorIfip;
    private TipoComprobanteCompra ubicaTipoComprobanteCompra;
    private GeneraReporte generaReporte;

    private List<Compra> itemsCompras;     //para listar segun el filtrado de la busqueda
    private List<PagoCompraDetalle> itemsPagoCompraDetalles;/////LAS VARAIBLES ON PARA EL PAGO A`PROVEEDORES
    private List<ProveedorIfip> itemsProveedores;

    /**
     * Creates a new instance of ListadoCompras
     */
    public ListadoComprasBean() {
        super(Compra.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacadeCompras);
        this.setBuscarPor("P");

        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();

    }

    /**
     * Al cambiar el criterio de Consulta Por Proveedor o fechas
     */
    public void cambiaCriterio() {

        this.setNombreProevedorCompleto(null);
        this.setCriterio(null);
        this.setDeshabilitaBuscarProveedor(true);

        if (this.buscarPor.equals("P")) {
            this.setDeshabilitaBuscarProveedor(false);

        }
    }

    public void buscaProveedores() {
        if (getNombreProveedorBusqueda().trim().isEmpty()) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsProveedores(null);
        } else if (this.getNombreProveedorBusqueda().trim().length() <= 6) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsProveedores(null);
        } else {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta"));
            this.setMensajeDialogo(null);
            this.setItemsProveedores(ejbFacadeProveedorIfip.getItemsNombresProveedorIfipEliminado(getNombreProveedorBusqueda(), ActivacionUsuario.getCodigoIfip(), 'N'));
        }
    }

    public void obtieneComprasContabilizadas() {
        if (this.getBuscarPor().equals("P")) {

            if (getCriterio() != null) {
                this.setItemsProveedores(ejbFacadeProveedorIfip.getItemsCodigoProveedorIfipEliminado(getCriterio(), ActivacionUsuario.getCodigoIfip(), 'N'));
                if (!this.itemsProveedores.isEmpty()) {
                    if (this.getItemsProveedores().size() == 1) {
                        Proveedor proveedor = this.getItemsProveedores().get(0).getProveedor();
                        this.setNombreProevedorCompleto(proveedor.getPersona().getNombreCompleto());
                        obtienteComprasPorProveedor();
                    }
                    if (this.getItemsProveedores().size() > 1) {
                        //System.err.println("el proveedor no puede tener el mismo codigo");
                    }
                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente"));
                    setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
                    MuestraMensaje.addAdvertencia(getMsg());
                    this.setItemsCompras(null);
                }
            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor"));
                MuestraMensaje.addAdvertencia(getMsg());
            }
        } else if (this.getBuscarPor().equals("F")) {
            if (this.getFechaInicio() != null && this.getFechaFin() != null) {

                obtieneComprasPorFechas();
                if (getItemsCompras().isEmpty()) {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeComprasInexistentesFecha"));
                    setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
                    MuestraMensaje.addAdvertencia(getMsg());
                    this.setItemsCompras(null);
                }

            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneFecha"));
                MuestraMensaje.addAdvertencia(getMsg());
            }
        }
    }

    public void leoCodigoRecibido() {
        this.mensajeCriterio = "" + proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
        this.criterio = proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
    }

    public void seleccionaProveedorDetalle(ActionEvent avc) {

        if (proveedorIfipSel != null) {
            this.mensajeCriterio = "" + proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
            this.criterio = proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
            this.setNombreProevedorCompleto(proveedorIfipSel.getProveedor().getPersona().getNombreCompleto());
            this.setCiRucProveedor(proveedorIfipSel.getProveedor().getPersona().getIdentificacion());
            this.setComprasDireccionProveedor(proveedorIfipSel.getProveedor().getPersona().getCorreoEletronico());
            this.setEstadoRiseProveedor("" + proveedorIfipSel.getProveedor().getTieneRise());
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    public void imprimeListaCompras(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (this.buscarPor.equals("F")) {
            if (fechaInicio != null && fechaFin != null) {
                if (ejbFacadeCompras.getItemsCompraFecha(ActivacionUsuario.getCodigoIfip(), this.getFechaInicio(), this.getFechaFin()).isEmpty()) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeListadoRetencionesFechas");
                    MuestraMensaje.addAdvertencia(msg);
                } else {
                    imprimeComprobantePorFechas();
                }

            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccionesFechas");
                MuestraMensaje.addAdvertencia(msg);
                //System.out.println("vacio");
            }
        }
        if (this.buscarPor.equals("P")) {
            if (this.criterio > 0L) {
                if (ejbFacadeCompras.getItemsCompraCodigoProveedor(ActivacionUsuario.getCodigoIfip(), this.getCriterio()).isEmpty()) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeListadoRetencionesProveedor");
                    MuestraMensaje.addAdvertencia(msg);
                } else {
                    imprimeComprobantePorProveedor();
                }

            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneProveedorEtiqueta");
                MuestraMensaje.addAdvertencia(msg);
                //System.out.println("vacio");
            }
        }
    }

    /**
     * IMPRIME LISTA DE COMPRAS
     *
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeComprobantePorFechas() throws JRException, IOException, ClassNotFoundException {

        Date nuevoDia = new Date();
        String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        //System.out.println("Today is " + sdf.format(nuevoDia));

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("fechaInicio", fechaInicio);
        getGeneraReporte().getParametros().put("fechaFin", fechaFin);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());
        getGeneraReporte().getParametros().put("codigoIfipAgencia", ActivacionUsuario.getCodigoIfipAgencia());
        getGeneraReporte().getParametros().put("codigoResponsable", ActivacionUsuario.getCodigoUsuario());

        nombreReporte = "listadoComprasFecha";

        getGeneraReporte().exporta("/adquisicion/reportes/listadoCompras/reporte/", nombreReporte,
                nombreReporte + String.valueOf(sdf.format(nuevoDia)) + ".pdf",
                "PDF", externalContext, facesContext);
    }

    /**
     *
     *
     *
     * IMPRIME LISTA DE COMPRAS
     *
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeComprobantePorProveedor() throws JRException, IOException, ClassNotFoundException {

        Date nuevoDia = new Date();
        String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        //System.out.println("Today is " + sdf.format(nuevoDia));

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoProveedor", this.criterio);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());
        getGeneraReporte().getParametros().put("codigoIfipAgencia", ActivacionUsuario.getCodigoIfipAgencia());
        getGeneraReporte().getParametros().put("codigoResponsable", ActivacionUsuario.getCodigoUsuario());

        nombreReporte = "listadoComprasProveedor";

        getGeneraReporte().exporta("/adquisicion/reportes/listadoCompras/reporte/", nombreReporte,
                nombreReporte + String.valueOf(criterio) + "_" + sdf.format(nuevoDia) + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void obtienteComprasPorProveedor() {
        this.itemsCompras = ejbFacadeCompras.getItemsCompraCodigoProveedor(ActivacionUsuario.getCodigoIfip(), this.getCriterio());
    }

    public void obtieneComprasPorFechas() {
        this.itemsCompras = ejbFacadeCompras.getItemsCompraFecha(ActivacionUsuario.getCodigoIfip(), this.getFechaInicio(), this.getFechaFin());
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    /**
     * @return the mensajeCriterio
     */
    public String getMensajeCriterio() {
        return mensajeCriterio;
    }

    /**
     * @param mensajeCriterio the mensajeCriterio to set
     */
    public void setMensajeCriterio(String mensajeCriterio) {
        this.mensajeCriterio = mensajeCriterio;
    }

    /**
     * @return the deshabilitaBuscarProveedor
     */
    public boolean isDeshabilitaBuscarProveedor() {
        return deshabilitaBuscarProveedor;
    }

    /**
     * @param deshabilitaBuscarProveedor the deshabilitaBuscarProveedor to set
     */
    public void setDeshabilitaBuscarProveedor(boolean deshabilitaBuscarProveedor) {
        this.deshabilitaBuscarProveedor = deshabilitaBuscarProveedor;
    }

    /**
     * @return the compraSel
     */
    public Compra getCompraSel() {
        return compraSel;
    }

    /**
     * @param compraSel the compraSel to set
     */
    public void setCompraSel(Compra compraSel) {
        this.compraSel = compraSel;
    }

    /**
     * @return the proveedorIfipSel
     */
    public ProveedorIfip getProveedorIfipSel() {
        return proveedorIfipSel;
    }

    /**
     * @param proveedorIfipSel the proveedorIfipSel to set
     */
    public void setProveedorIfipSel(ProveedorIfip proveedorIfipSel) {
        this.proveedorIfipSel = proveedorIfipSel;
    }

    /**
     * @return the sustentoTributario
     */
    public SustentoTributario getSustentoTributario() {
        return sustentoTributario;
    }

    /**
     * @param sustentoTributario the sustentoTributario to set
     */
    public void setSustentoTributario(SustentoTributario sustentoTributario) {
        this.sustentoTributario = sustentoTributario;
    }

    /**
     * @return the provedorIfip
     */
    public ProveedorIfip getProvedorIfip() {
        return provedorIfip;
    }

    /**
     * @param provedorIfip the provedorIfip to set
     */
    public void setProvedorIfip(ProveedorIfip provedorIfip) {
        this.provedorIfip = provedorIfip;
    }

    /**
     * @return the ubicaTipoComprobanteCompra
     */
    public TipoComprobanteCompra getUbicaTipoComprobanteCompra() {
        return ubicaTipoComprobanteCompra;
    }

    /**
     * @param ubicaTipoComprobanteCompra the ubicaTipoComprobanteCompra to set
     */
    public void setUbicaTipoComprobanteCompra(TipoComprobanteCompra ubicaTipoComprobanteCompra) {
        this.ubicaTipoComprobanteCompra = ubicaTipoComprobanteCompra;
    }

    /**
     * @return the itemsCompras
     */
    public List<Compra> getItemsCompras() {
        return itemsCompras;
    }

    /**
     * @param itemsCompras the itemsCompras to set
     */
    public void setItemsCompras(List<Compra> itemsCompras) {
        this.itemsCompras = itemsCompras;
    }

    /**
     * @return the itemsPagoCompraDetalles
     */
    public List<PagoCompraDetalle> getItemsPagoCompraDetalles() {
        return itemsPagoCompraDetalles;
    }

    /**
     * @param itemsPagoCompraDetalles the itemsPagoCompraDetalles to set
     */
    public void setItemsPagoCompraDetalles(List<PagoCompraDetalle> itemsPagoCompraDetalles) {
        this.itemsPagoCompraDetalles = itemsPagoCompraDetalles;
    }

    /**
     * @return the itemsProveedores
     */
    public List<ProveedorIfip> getItemsProveedores() {
        return itemsProveedores;
    }

    /**
     * @param itemsProveedores the itemsProveedores to set
     */
    public void setItemsProveedores(List<ProveedorIfip> itemsProveedores) {
        this.itemsProveedores = itemsProveedores;
    }

    /**
     * @return the ciRucProveedor
     */
    public String getCiRucProveedor() {
        return ciRucProveedor;
    }

    /**
     * @param ciRucProveedor the ciRucProveedor to set
     */
    public void setCiRucProveedor(String ciRucProveedor) {
        this.ciRucProveedor = ciRucProveedor;
    }

    /**
     * @return the comprasDireccionProveedor
     */
    public String getComprasDireccionProveedor() {
        return comprasDireccionProveedor;
    }

    /**
     * @param comprasDireccionProveedor the comprasDireccionProveedor to set
     */
    public void setComprasDireccionProveedor(String comprasDireccionProveedor) {
        this.comprasDireccionProveedor = comprasDireccionProveedor;
    }

    /**
     * @return the numeroCuentaCre
     */
    public String getNumeroCuentaCre() {
        return numeroCuentaCre;
    }

    /**
     * @param numeroCuentaCre the numeroCuentaCre to set
     */
    public void setNumeroCuentaCre(String numeroCuentaCre) {
        this.numeroCuentaCre = numeroCuentaCre;
    }

    /**
     * @return the nombreProevedorCompleto
     */
    public String getNombreProevedorCompleto() {
        return nombreProevedorCompleto;
    }

    /**
     * @param nombreProevedorCompleto the nombreProevedorCompleto to set
     */
    public void setNombreProevedorCompleto(String nombreProevedorCompleto) {
        this.nombreProevedorCompleto = nombreProevedorCompleto;
    }

    /**
     * @return the nombreProveedorBusqueda
     */
    public String getNombreProveedorBusqueda() {
        return nombreProveedorBusqueda;
    }

    /**
     * @param nombreProveedorBusqueda the nombreProveedorBusqueda to set
     */
    public void setNombreProveedorBusqueda(String nombreProveedorBusqueda) {
        this.nombreProveedorBusqueda = nombreProveedorBusqueda;
    }

    /**
     * @return the mensajeDialogo
     */
    public String getMensajeDialogo() {
        return mensajeDialogo;
    }

    /**
     * @param mensajeDialogo the mensajeDialogo to set
     */
    public void setMensajeDialogo(String mensajeDialogo) {
        this.mensajeDialogo = mensajeDialogo;
    }

    /**
     * @return the criterio
     */
    public Long getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(Long criterio) {
        this.criterio = criterio;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the estadoRiseProveedor
     */
    public String getEstadoRiseProveedor() {
        return estadoRiseProveedor;
    }

    /**
     * @param estadoRiseProveedor the estadoRiseProveedor to set
     */
    public void setEstadoRiseProveedor(String estadoRiseProveedor) {
        this.estadoRiseProveedor = estadoRiseProveedor;
    }

    /**
     * @return the generaReporte
     */
    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    /**
     * @param generaReporte the generaReporte to set
     */
    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

}
