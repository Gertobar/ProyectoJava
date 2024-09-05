/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.adquisicones.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.Proveedor;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfip;
import ec.renafipse.mks.modelo.adquisiciones.SustentoTributario;
import ec.renafipse.mks.modelo.contables.Retencion;
import ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetDet;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorIfipFacade;
import ec.renafipse.mks.negocio.contables.RetencionFacade;
import ec.renafipse.mks.negocio.contables.TalonarioDocumentoRetDetFacade;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author renafipse1 CLASE VALIDA PARA LAS RETENCIONES
 */
@ManagedBean(name = "listadoRetencionBean")
@ViewScoped
public class ListadoRetencionesBean extends AbstractController<Retencion> implements Serializable {

    @EJB
    private RetencionFacade ejbFacadeRetencion;

    @EJB
    private ProveedorIfipFacade ejbFacadeProveedorIfip;

    @EJB
    private TalonarioDocumentoRetDetFacade ejbFacadeTalonarioDocumentoRetDet;

    private LlamaSP llamaSP;

    // DECLARACION DE LAS VARIABLES
    private Long criterio;
    private Long codigoCompraImpr;
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

    private Retencion retencionSel;
    private ProveedorIfip proveedorIfipSel;
    private SustentoTributario sustentoTributario;
    private ProveedorIfip provedorIfip;
    private GeneraReporte generaReporte;

    private List<Retencion> itemRetenciones;     //para listar segun el filtrado de la busqueda    
    private List<ProveedorIfip> itemsProveedores;
    private Long codigoDocumento;
    private String numeroDocumento;

    private String serieRetencion;
    private String numeroRetencion;
    private boolean deshabilitaImpresionRetencion;

    private RequestContext context;

    /**
     * Creates a new instance of ListadoCompras
     */
    public ListadoRetencionesBean() {
        super(Retencion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacadeRetencion);
        this.setBuscarPor("P");

        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();

    }

    //----------------------------------------------------------------------------
    //-- PROCESOS DE DOCUMENTO DE RETENCION
    /**
     * Obtiene el Documento y lo valida que exista
     */
    public void obtieneDocumento() {
        try {
            this.setCodigoDocumento(null);
            if (this.getSerieRetencion() != null && this.getNumeroRetencion() != null) {
                List<TalonarioDocumentoRetDet> listaTalonarioDocumentoRetDet = ejbFacadeTalonarioDocumentoRetDet.getItemsSerieTalonarioSerieDocumento(this.getSerieRetencion(), Long.parseLong(this.getNumeroRetencion()));
                //System.out.println("listaTalonarioDocumentoRetDet " + listaTalonarioDocumentoRetDet);
                if (!listaTalonarioDocumentoRetDet.isEmpty()) {
                    if (listaTalonarioDocumentoRetDet.size() == 1) {
                        if (listaTalonarioDocumentoRetDet.get(0).getEstado() == 'P') {
                            this.setCodigoDocumento(listaTalonarioDocumentoRetDet.get(0).getCodigo());
                            this.setNumeroDocumento(listaTalonarioDocumentoRetDet.get(0).getNumero());
                            this.numeroRetencion = this.getNumeroDocumento();
                        } else {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoDiferentePendiente");
                            MuestraMensaje.addError(msg);
                        }
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnaRetencionParaElNumero");
                        MuestraMensaje.addError(msg);
                    }

                } else {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoRetencionNoExiste");
                    MuestraMensaje.addError(msg);
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoRetencionNoExiste");
                MuestraMensaje.addError(msg);
            }
        } catch (NumberFormatException e) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SoloValoresNumericos");
            MuestraMensaje.addError(msg);
        }
    }


    /*  * Guarda la Renovacion del DPF
     *
     * @param actionEvent
     */
    public void guardar(ActionEvent actionEvent) {

        try {

            context = RequestContext.getCurrentInstance();
            context.execute("procesandoDlg.show()");

            if (this.retencionSel == null) {
                context.execute("procesandoDlg.hide()");
                return;
            }

            this.obtieneDocumento();
            if (this.codigoDocumento == null) {
                context.execute("procesandoDlg.hide()");
                return;
            }
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_contables.pkm_talonario_doc_ret_det.p_reasigna_documento_dpf");
            llamaSP.setNumeroParametros(8);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", this.retencionSel.getCodigoCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.retencionSel.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_pago", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_talonario", this.codigoDocumento});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RetencionRealizadaConExito");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("Retencion.hide()");
                context.execute("ImprimeComprobanteRetencionDialogo.show()");
                this.deshabilitaImpresionRetencion = true;

            } else {
                context.execute("procesandoDlg.hide()");

                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"listadoRetencionBean", "guardar", CapturaError.getErrorException(ex)});
        }

    }

    public void preparaReasignacion(ActionEvent actionEvent) throws IOException {
        if (this.retencionSel == null) {
            return;
        }

        this.codigoDocumento = null;
        this.numeroDocumento = null;
        this.serieRetencion = null;
        this.numeroRetencion = null;
        this.deshabilitaImpresionRetencion = false;
        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");
        this.msg = null;
        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.hide()");
        context.execute("CreaRetencionDilago.show()");

        ////System.out.println(" this.banderaContRete " + this.banderaContRete);
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
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente"));
                    }
                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente"));
                    MuestraMensaje.addAdvertencia(getMsg());
                    this.setItemsProveedores(null);
                }
            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor"));
                MuestraMensaje.addAdvertencia(getMsg());
            }
        } else if (this.getBuscarPor().equals("F")) {
            if (this.getFechaInicio() != null && this.getFechaFin() != null) {

                obtieneComprasPorFechas();
                /*if (this.itemsProveedores.isEmpty()) {
                 setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeComprasInexistentesFecha"));
                 setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
                 MuestraMensaje.addAdvertencia(getMsg());
                 this.setItemsProveedores(null);
                 }*/

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

    public void leoCodigoRecibidoCompra() {

        this.codigoCompraImpr = getRetencionSel().getCodigoCompra();
        //System.out.println("CODIGO DE LA COMPRA " + codigoCompraImpr);
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

    public void preparaRetencionFactura(ActionEvent actionEvent) throws IOException {
        llamoVentanaImpresion();
    }

    public boolean llamoVentanaImpresion() {
        boolean bandera = false;
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.hide()");
            context.execute("ImprimeComprobanteRetencionDialogo.show()");
            bandera = true;

        } catch (Exception e) {
            bandera = false;
        }
        return bandera;
    }

    public void imprimeListaCompras(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (this.buscarPor.equals("F")) {
            if (fechaInicio != null && fechaFin != null) {

                if (ejbFacadeRetencion.getItemsRetencionesfindByIfipIAgenciaFechaIngreso(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), this.getFechaInicio(), this.getFechaFin()).isEmpty()) {

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
                if (ejbFacadeRetencion.getItemsRetencionesfindByIfipIAgenciaPro(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), this.getCriterio()).isEmpty()) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeListadoRetencionesProveedor");
                    MuestraMensaje.addAdvertencia(msg);
                    //System.out.println("vacio");
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

        nombreReporte = "listadoRetencionesEstadoFechas";
        /*           listadoRetencionesEstadoFechas
         adquisicion\reportes\listadoRetenciones\reporte\listadoRetencionesEstadoFechas.jrxml
         */
        getGeneraReporte().exporta("/adquisicion/reportes/listadoRetenciones/reporte/", nombreReporte,
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

        nombreReporte = "listadoRetencionesEstadoProveedor";

        getGeneraReporte().exporta("/adquisicion/reportes/listadoRetenciones/reporte/", nombreReporte,
                nombreReporte + String.valueOf(criterio) + "_" + sdf.format(nuevoDia) + ".pdf",
                "PDF", externalContext, facesContext);
    }

    //METODO Q ME LISTA TODOS LAS COMPRAS EN ESTADO DE RETENCION
    public void obtienteComprasPorProveedor() {
        this.itemRetenciones = ejbFacadeRetencion.getItemsRetencionesfindByIfipIAgenciaPro(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), this.getCriterio());
    }

    // METODO Q ME LISTA TODAS LAS COMPRAS SEGUN LA FECHA
    public void obtieneComprasPorFechas() {
        this.itemRetenciones = ejbFacadeRetencion.getItemsRetencionesfindByIfipIAgenciaFechaIngreso(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), this.getFechaInicio(), this.getFechaFin());
    }

    /**
     * Imprime el comprobante de retencion
     *
     * @param actionEvent
     */
    public void imprimeComprobanteRetencionr(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoCompra", this.retencionSel.getCodigoCompra());

        nombreReporte = "comprobanteRetencion";
        // comprobanteRetencion
        getGeneraReporte().exporta("/adquisicion/compras/facturas/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.getCodigoCompraImpr()) + ".pdf",
                "PDF", externalContext, facesContext);

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

    /**
     * @return the codigoCompraImpr
     */
    public Long getCodigoCompraImpr() {
        return codigoCompraImpr;
    }

    /**
     * @param codigoCompraImpr the codigoCompraImpr to set
     */
    public void setCodigoCompraImpr(Long codigoCompraImpr) {
        this.codigoCompraImpr = codigoCompraImpr;
    }

    /**
     * @return the itemRetenciones
     */
    public List<Retencion> getItemRetenciones() {
        return itemRetenciones;
    }

    /**
     * @param itemRetenciones the itemRetenciones to set
     */
    public void setItemRetenciones(List<Retencion> itemRetenciones) {
        this.itemRetenciones = itemRetenciones;
    }

    /**
     * @return the retencionSel
     */
    public Retencion getRetencionSel() {
        return retencionSel;
    }

    /**
     * @param retencionSel the retencionSel to set
     */
    public void setRetencionSel(Retencion retencionSel) {
        this.retencionSel = retencionSel;
    }

    /**
     * @return the serieRetencion
     */
    public String getSerieRetencion() {
        return serieRetencion;
    }

    /**
     * @param serieRetencion the serieRetencion to set
     */
    public void setSerieRetencion(String serieRetencion) {
        this.serieRetencion = serieRetencion;
    }

    /**
     * @return the numeroRetencion
     */
    public String getNumeroRetencion() {
        return numeroRetencion;
    }

    /**
     * @param numeroRetencion the numeroRetencion to set
     */
    public void setNumeroRetencion(String numeroRetencion) {
        this.numeroRetencion = numeroRetencion;
    }

    /**
     * @return the deshabilitaImpresionRetencion
     */
    public boolean isDeshabilitaImpresionRetencion() {
        return deshabilitaImpresionRetencion;
    }

    /**
     * @param deshabilitaImpresionRetencion the deshabilitaImpresionRetencion to
     * set
     */
    public void setDeshabilitaImpresionRetencion(boolean deshabilitaImpresionRetencion) {
        this.deshabilitaImpresionRetencion = deshabilitaImpresionRetencion;
    }

    /**
     * @return the codigoDocumento
     */
    public Long getCodigoDocumento() {
        return codigoDocumento;
    }

    /**
     * @param codigoDocumento the codigoDocumento to set
     */
    public void setCodigoDocumento(Long codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

}
