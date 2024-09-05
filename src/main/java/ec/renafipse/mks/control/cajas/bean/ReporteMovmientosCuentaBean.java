/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.cajas.bean;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.AperturaCajaMovimientoCta;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.negocio.cajas.AperturaCajaMovimientoCtaFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author vastudillo
 */
@ManagedBean(name = "reporteMovmientosCuentaBean")
@ViewScoped
public class ReporteMovmientosCuentaBean extends AbstractController<AperturaCajaMovimientoCta> implements Serializable {

    @EJB
    private AperturaCajaMovimientoCtaFacade ejbFacade;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private IfipFacade ejbFacadeIfip;

    private Date fechaApertura;
    private Date fechaActual;
    private Apertura apertura;
    private GeneraReporte generaReporte;

    private List<Apertura> itemsApertura;

    public ReporteMovmientosCuentaBean() {
        super(AperturaCajaMovimientoCta.class);

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.fechaActual = new Date();

    }

    /**
     * Genera el reporte PDF de la impresion de la Solicitud
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        if (apertura == null) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneApertura"));
            return;
        }
        String nombreReporte = "movimientosCuenta";
        Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        String nombreIfip = ifip.getCodigoTipoIfip().getNombre() + " " + ifip.getNombre();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("nombreIfip", nombreIfip);
        getGeneraReporte().getParametros().put("codigoApertura", this.apertura.getCodigo());
        getGeneraReporte().getParametros().put("fechaApertura", this.apertura.getFechaSistema());
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        getGeneraReporte().exporta("/financiero/reportes/movimientosCuenta/reporte/", nombreReporte,
                "movimientosCuenta" + String.valueOf(this.apertura.getCodigo()) + ".txt",
                "TXT", externalContext, facesContext);
        ////System.out.println("impresion dada");

    }
    
    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        if (apertura == null) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneApertura"));
            return;
        }
        String nombreReporte = "transaccionesCaja";
        Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        String nombreIfip = ifip.getCodigoTipoIfip().getNombre() + " " + ifip.getNombre();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());
        getGeneraReporte().getParametros().put("codigoApertura", this.apertura.getCodigo());

        getGeneraReporte().exporta("/financiero/reportes/transaccionesCaja/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.apertura.getCodigo()) + ".pdf",
                "PDF", externalContext, facesContext);
        ////System.out.println("impresion dada");

    }
    
    
    /**
     * IMPRIME EL CIERRE DE CAJA
     *
     * @param actionEvent
     */
    public void imprimeCierre(ActionEvent actionEvent) {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoApertura", this.apertura.getCodigo());

        nombreReporte = "cierreCaja";
        getGeneraReporte().exporta("/financiero/cajas/cierre/reporte/", nombreReporte,
                nombreReporte + this.apertura.getCodigo() + ".pdf",
                "PDF", externalContext, facesContext);
        //System.out.println("Cierre imprimio");
    }
    
    /**
     * Exporta a Excel el Estado de Cuentas
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void exportaExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {


       if (apertura == null) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneApertura"));
            return;
        }
        String nombreReporte = "movimientosCuenta";
        Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        String nombreIfip = ifip.getCodigoTipoIfip().getNombre() + " " + ifip.getNombre();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("nombreIfip", nombreIfip);
        getGeneraReporte().getParametros().put("codigoApertura", this.apertura.getCodigo());
        getGeneraReporte().getParametros().put("fechaApertura", this.apertura.getFechaSistema());
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        getGeneraReporte().exporta("/financiero/reportes/movimientosCuenta/reporte/", nombreReporte,
                "movimientosCuenta" + String.valueOf(this.apertura.getCodigo()) + ".xls",
                "XLS", externalContext, facesContext);

    }
    // -- FIN DE SOCIO
    // ----------------------------------------------------------------------------------

    /**
     * Obtiene las aperturas de la caja
     */
    public void obtieneAperturas() {
        this.itemsApertura = this.ejbFacadeApertura.getItemsUsuarioIfipAgenciaFechaApertura(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), fechaApertura);
        this.apertura = null;
    }

    /**
     * @return the itemsApertura
     */
    public List<Apertura> getItemsApertura() {
        return itemsApertura;
    }

    /**
     * @param itemsApertura the itemsApertura to set
     */
    public void setItemsApertura(List<Apertura> itemsApertura) {
        this.itemsApertura = itemsApertura;
    }

    /**
     * @return the fechaApertura
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
     * @param fechaApertura the fechaApertura to set
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * @return the apertura
     */
    public Apertura getApertura() {
        return apertura;
    }

    /**
     * @param apertura the apertura to set
     */
    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
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
