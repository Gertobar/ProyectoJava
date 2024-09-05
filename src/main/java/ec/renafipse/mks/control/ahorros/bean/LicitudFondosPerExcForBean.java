/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosAprobarExc;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosPerExcFor;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosTipoMotivoExc;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosAprobarExcFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosPerExcForFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosTipoMotivoExcFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author v.astudillo
 */
@ManagedBean(name = "licitudFondosPerExcForBean")
@ViewScoped
public class LicitudFondosPerExcForBean extends AbstractController<LicitudFondosPerExcFor> implements Serializable {

    @EJB
    private LicitudFondosPerExcForFacade ejbFacade;

    @EJB
    private LicitudFondosTipoMotivoExcFacade ejbFacadeLicitudFondosTipoMotivoExc;

    @EJB
    private LicitudFondosAprobarExcFacade ejbFacadeLicitudFondosAprobarExc;

    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaActual;

    private String msg;
    private String estado;
    private String observacionesEstado;

    private boolean deshabilitaBotonAprobar;
    private boolean deshabilitaBotonAnular;

    private RequestContext context;

    private LlamaSP llamaSP;
    private GeneraReporte generaReporte;

    private LicitudFondosPerExcFor licitudFondosPerExcFor;
    private LicitudFondosAprobarExc licitudFondosAprobarExc;
    private LicitudFondosTipoMotivoExc licitudFondosTipoMotivoExc;

    private List<LicitudFondosPerExcFor> itemsLicitudFondosPerExcFor;
    private List<LicitudFondosTipoMotivoExc> itemsLicitudFondosTipoMotivoExc;

    public LicitudFondosPerExcForBean() {
        super(LicitudFondosPerExcFor.class);
    }

    @PostConstruct
    public void init() {
        // //System.out.println("INicia datos");   
        super.setFacade(this.ejbFacade);
        this.setLicitudFondosAprobarExc(null);
        this.setLicitudFondosPerExcFor(null);
        this.setItemsLicitudFondosPerExcFor(null);
        this.observacionesEstado = null;
        this.fechaActual = new Date();
        this.deshabilitaBotonAnular = true;
        this.deshabilitaBotonAprobar = true;
        this.estado = "P";

    }

    /**
     * Prepara la Aprobación de la Excepcion
     *
     * @param actionEvent
     */
    public void preparaAprobacion(ActionEvent actionEvent) {
        this.cargarListaTipoMotivos();
        this.observacionesEstado = null;
        this.setLicitudFondosAprobarExc(new LicitudFondosAprobarExc());
        this.getLicitudFondosAprobarExc().setLicitudFondosPerExcFor(licitudFondosPerExcFor);
        this.getLicitudFondosAprobarExc().setCodigoExcepcion(this.licitudFondosPerExcFor.getCodigo());
    }

    /**
     * Prepara la Anulación de la Excepcion
     *
     * @param actionEvent
     */
    public void preparaAnulacion(ActionEvent actionEvent) {
        this.observacionesEstado = null;
    }

    /**
     * Prepara la Anulación de la Excepcion
     *
     * @param actionEvent
     */
    public void preparaVerAprobacion(ActionEvent actionEvent) {
        this.setLicitudFondosAprobarExc(this.ejbFacadeLicitudFondosAprobarExc.find(this.licitudFondosPerExcFor.getCodigo()));
        this.observacionesEstado = this.licitudFondosPerExcFor.getObservacionesEstado();
    }

    /**
     * Obtiene las licitudes de Fondo de la Persona
     */
    public void obtienePersonasExcentas() {
        try {

            this.setItemsLicitudFondosPerExcFor(null);
            this.setLicitudFondosAprobarExc(null);
            this.licitudFondosPerExcFor = null;
            this.observacionesEstado = null;
            this.deshabilitaBotonAnular = true;
            this.deshabilitaBotonAprobar = true;
            
            if (this.fechaInicio != null && this.fechaFin != null && getEstado() != null) {                
                this.setItemsLicitudFondosPerExcFor(this.ejbFacade.getItemsCodigoIfipRangoFechaRegistroEstado(ActivacionUsuario.getCodigoIfip(), this.fechaInicio, this.getFechaFin(), getEstado().charAt(0)));
            } else if (this.fechaInicio != null && this.fechaFin != null && getEstado() == null) {
                this.setItemsLicitudFondosPerExcFor(this.ejbFacade.getItemsCodigoIfipRangoFechaRegistro(ActivacionUsuario.getCodigoIfip(), this.fechaInicio, this.getFechaFin()));
            } else if (this.fechaInicio == null && this.fechaFin == null && getEstado() != null) {
                this.setItemsLicitudFondosPerExcFor(this.ejbFacade.getItemsCodigoIfipEstado(ActivacionUsuario.getCodigoIfip(), getEstado().charAt(0)));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"licitudFondosPerExcForBean", "obtienePersonasExcentas", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Anula la persona excenta
     */
    public void anular() {
        // Actualizando el estado
        this.ejbFacade.actualizaEstado(this.licitudFondosPerExcFor.getCodigo(), 'N', new Date(), ActivacionUsuario.getCodigoUsuario(), getObservacionesEstado());
        //Mensaje de Satisfactorio
        MuestraMensaje.addSatisfactorioPersistencia(1);
    }

    /**
     * Aprueba la excepcion
     */
    public void aprobar() {
        // Creando la aprobacion        
        this.getLicitudFondosAprobarExc().setFechaAprobacion(new Date());
        this.getLicitudFondosAprobarExc().setAprobadoPor(ActivacionUsuario.getCodigoUsuario());
        this.getLicitudFondosAprobarExc().setEliminado('N');
        this.getLicitudFondosAprobarExc().setObservaciones(getLicitudFondosAprobarExc().getObservaciones().toUpperCase());
        this.getLicitudFondosAprobarExc().setCodigoTipoMotivo(getLicitudFondosAprobarExc().getTipoMotivo().getCodigo());
        this.ejbFacadeLicitudFondosAprobarExc.create(getLicitudFondosAprobarExc());
        // Actualizando el estado
        this.ejbFacade.actualizaEstado(this.licitudFondosPerExcFor.getCodigo(), 'A', new Date(), ActivacionUsuario.getCodigoUsuario(), getLicitudFondosAprobarExc().getObservaciones().toUpperCase());
        // Obteniendo las personas excentas
        this.obtienePersonasExcentas();
        //Mensaje de Satisfactorio
        MuestraMensaje.addSatisfactorioPersistencia(1);
    }

    /**
     * Cuando seleccione el formulario de licitud
     */
    public void seleccionaPersonaExcenta() {

        if (this.licitudFondosPerExcFor.getEstado() == 'A') {
            this.deshabilitaBotonAprobar = true;
            this.deshabilitaBotonAnular = false;
        } else if (this.licitudFondosPerExcFor.getEstado() == 'N') {
            this.deshabilitaBotonAprobar = true;
            this.deshabilitaBotonAnular = true;
        } else {
            this.deshabilitaBotonAprobar = false;
            this.deshabilitaBotonAnular = false;
        }

    }

    /**
     * Carga el listado de tipos de motivos.
     */
    public void cargarListaTipoMotivos() {
        this.setItemsLicitudFondosTipoMotivoExc(this.ejbFacadeLicitudFondosTipoMotivoExc.getItemsEliminado('N'));
    }

    /**
     * @return the llamaSP
     */
    public LlamaSP getLlamaSP() {
        return llamaSP;
    }

    /**
     * @param llamaSP the llamaSP to set
     */
    public void setLlamaSP(LlamaSP llamaSP) {
        this.llamaSP = llamaSP;
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
     * @return the deshabilitaBotonAprobar
     */
    public boolean isDeshabilitaBotonAprobar() {
        return deshabilitaBotonAprobar;
    }

    /**
     * @param deshabilitaBotonAprobar the deshabilitaBotonAprobar to set
     */
    public void setDeshabilitaBotonAprobar(boolean deshabilitaBotonAprobar) {
        this.deshabilitaBotonAprobar = deshabilitaBotonAprobar;
    }

    /**
     * @return the licitudFondosPerExcFor
     */
    public LicitudFondosPerExcFor getLicitudFondosPerExcFor() {
        return licitudFondosPerExcFor;
    }

    /**
     * @param licitudFondosPerExcFor the licitudFondosPerExcFor to set
     */
    public void setLicitudFondosPerExcFor(LicitudFondosPerExcFor licitudFondosPerExcFor) {
        this.licitudFondosPerExcFor = licitudFondosPerExcFor;
    }

    /**
     * @return the licitudFondosAprobarExc
     */
    public LicitudFondosAprobarExc getLicitudFondosAprobarExc() {
        return licitudFondosAprobarExc;
    }

    /**
     * @param licitudFondosAprobarExc the licitudFondosAprobarExc to set
     */
    public void setLicitudFondosAprobarExc(LicitudFondosAprobarExc licitudFondosAprobarExc) {
        this.licitudFondosAprobarExc = licitudFondosAprobarExc;
    }

    /**
     * @return the itemsLicitudFondosPerExcFor
     */
    public List<LicitudFondosPerExcFor> getItemsLicitudFondosPerExcFor() {
        return itemsLicitudFondosPerExcFor;
    }

    /**
     * @param itemsLicitudFondosPerExcFor the itemsLicitudFondosPerExcFor to set
     */
    public void setItemsLicitudFondosPerExcFor(List<LicitudFondosPerExcFor> itemsLicitudFondosPerExcFor) {
        this.itemsLicitudFondosPerExcFor = itemsLicitudFondosPerExcFor;
    }

    /**
     * @return the observacionesEstado
     */
    public String getObservacionesEstado() {
        return observacionesEstado;
    }

    /**
     * @param observacionesEstado the observacionesEstado to set
     */
    public void setObservacionesEstado(String observacionesEstado) {
        this.observacionesEstado = observacionesEstado;
    }

    /**
     * @return the itemsLicitudFondosTipoMotivoExc
     */
    public List<LicitudFondosTipoMotivoExc> getItemsLicitudFondosTipoMotivoExc() {
        return itemsLicitudFondosTipoMotivoExc;
    }

    /**
     * @param itemsLicitudFondosTipoMotivoExc the
     * itemsLicitudFondosTipoMotivoExc to set
     */
    public void setItemsLicitudFondosTipoMotivoExc(List<LicitudFondosTipoMotivoExc> itemsLicitudFondosTipoMotivoExc) {
        this.itemsLicitudFondosTipoMotivoExc = itemsLicitudFondosTipoMotivoExc;
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
     * @return the deshabilitaBotonAnular
     */
    public boolean isDeshabilitaBotonAnular() {
        return deshabilitaBotonAnular;
    }

    /**
     * @param deshabilitaBotonAnular the deshabilitaBotonAnular to set
     */
    public void setDeshabilitaBotonAnular(boolean deshabilitaBotonAnular) {
        this.deshabilitaBotonAnular = deshabilitaBotonAnular;
    }

    /**
     * @return the licitudFondosTipoMotivoExc
     */
    public LicitudFondosTipoMotivoExc getLicitudFondosTipoMotivoExc() {
        return licitudFondosTipoMotivoExc;
    }

    /**
     * @param licitudFondosTipoMotivoExc the licitudFondosTipoMotivoExc to set
     */
    public void setLicitudFondosTipoMotivoExc(LicitudFondosTipoMotivoExc licitudFondosTipoMotivoExc) {
        this.licitudFondosTipoMotivoExc = licitudFondosTipoMotivoExc;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
