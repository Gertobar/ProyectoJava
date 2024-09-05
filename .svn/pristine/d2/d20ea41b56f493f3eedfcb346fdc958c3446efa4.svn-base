package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.SeguimientoEstadoSolicitud;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.creditos.SolicitudDetallePK;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.creditos.SeguimientoEstadoSolicitudFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "seguimientoEstadoSolicitudController")
@ViewScoped
public class SeguimientoEstadoSolicitudController extends AbstractController<SeguimientoEstadoSolicitud> implements Serializable {

    @EJB
    private SeguimientoEstadoSolicitudFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private SolicitudFacade ejbFacadeSolicitud;

    private SolicitudController solicitud;

    private Socio socio;
    private Socio socioSel;
    private Solicitud solicitudSel;
    private SolicitudDetalle solicitudDetalle;
    private SolicitudDetallePK solicitudDetallePk;

    private String codigoSocio;

    private String msg;
    private String nombreSocio;
    private String nombreSocioBusqueda;
    private String mensajeCriterio;

    private boolean busquedaSocioLista;

    private List<Socio> itemsSocio;
    private List<Solicitud> itemsSolicitudCredito;
    private List<SolicitudDetalle> itemsSolicitudDetalleCredito;
    private List<SeguimientoEstadoSolicitud> itemsSeguimientoEstadoSolicitud;

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);    
        this.codigoSocio=null;
        this.setItemsSeguimientoEstadoSolicitud(new ArrayList<SeguimientoEstadoSolicitud>());
        this.setItemsSocio(new ArrayList<Socio>());
        this.setItemsSolicitudCredito(new ArrayList<Solicitud>());
        this.setItemsSolicitudDetalleCredito(new ArrayList<SolicitudDetalle>());
    }

    public SeguimientoEstadoSolicitudController() {
        // Inform the Abstract parent controller of the concrete SeguimientoEstadoSolicitud?cap_first Entity
        super(SeguimientoEstadoSolicitud.class);
    }

    //****************INICIO LOGICA*******************//
    public void obtieneNombreSocio() {
        this.setSocio(new Socio());
        this.setItemsSocio(this.ejbFacadeSocio.getItemsSocioCodigo(Long.parseLong(getCodigoSocio()), ActivacionUsuario.getCodigoIfip()));
        if (!itemsSocio.isEmpty()) {
            if (getItemsSocio().size() == 1) {
                this.setSocio(this.getItemsSocio().get(0));
                obtieneSolicitudCreditoSocio();
            }
            if (getItemsSocio().size() > 1) {
                this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnSocioConMismoCodigo"));
                MuestraMensaje.addAdvertencia(getMsg());
            }

        } else {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
            MuestraMensaje.addAdvertencia(getMsg());
        }
    }

    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(this.getNombreSocioBusqueda(), 6)) {
                if (this.isBusquedaSocioLista()) {
                    this.setSocioSel(null);
                    this.setSelected(null);
                }
                this.setItemsSocio(this.ejbFacadeSocio.getItemsNombresIndicaActivo(this.getNombreSocioBusqueda(), ActivacionUsuario.getCodigoIfip(), 'S'));
            } else {
                this.setMensajeCriterio(Validaciones.msg);
                this.setItemsSocio(null);
                if (this.isBusquedaSocioLista()) {
                    this.setSocioSel(null);
                    this.setSelected(null);
                }
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"seguimientoEstadoSolicitudController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneSolicitudNombreSocio() {
        this.setSolicitudSel(new Solicitud());
        try {
            if (this.getSocioSel() != null) {
                this.setItemsSolicitudCredito(this.ejbFacadeSolicitud.getItemsSolicitudNombreSocioCodigoIfip(this.getSocioSel().getCodigoPersona().getNombreCompleto(), ActivacionUsuario.getCodigoIfip()));
                if (!itemsSolicitudCredito.isEmpty()) {

                } else {
                    this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoTieneSolicitud"));
                    MuestraMensaje.addAdvertencia(getMsg());
                }
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"seguimientoEstadoSolicitudController", "obtieneSolicitudNombreSocio", CapturaError.getErrorException(ex)});
        }

    }

    public void obtieneSolicitudCreditoSocio() {
        this.setSolicitudSel(new Solicitud());
        try {
            if (Validaciones.cumpleRequerimientoCampo(getCodigoSocio(), 1)) {
                this.setItemsSolicitudCredito(this.ejbFacadeSolicitud.getItemsCodigoSocioCodigoIfip(Long.parseLong(getCodigoSocio()), ActivacionUsuario.getCodigoIfip()));
                if (!itemsSolicitudCredito.isEmpty()) {

                } else {
                    this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoTieneSolicitud"));
                    MuestraMensaje.addAdvertencia(getMsg());
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsSolicitudCredito(null);
                this.setSolicitudSel(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"seguimientoEstadoSolicitudController", "obtieneSolicitudCreditoSocio", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneSeguimientoEstadoSolicitud(ActionEvent event) {
        try {
            this.itemsSeguimientoEstadoSolicitud = this.ejbFacade.getItemsSeguimientoSolicitudIfipEstado(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'N');

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"seguimientoEstadoSolicitudController", "obtieneSeguimientoEstadoSolicitud", CapturaError.getErrorException(ex)});
        }
    }

    //****************FIN LOGICA*******************//
    /**
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * @return the socioSel
     */
    public Socio getSocioSel() {
        return socioSel;
    }

    /**
     * @param socioSel the socioSel to set
     */
    public void setSocioSel(Socio socioSel) {
        this.socioSel = socioSel;
    }

    /**
     * @return the solicitudSel
     */
    public Solicitud getSolicitudSel() {
        return solicitudSel;
    }

    /**
     * @param solicitudSel the solicitudSel to set
     */
    public void setSolicitudSel(Solicitud solicitudSel) {
        this.solicitudSel = solicitudSel;
    }

    /**
     * @return the solicitudDetalle
     */
    public SolicitudDetalle getSolicitudDetalle() {
        return solicitudDetalle;
    }

    /**
     * @param solicitudDetalle the solicitudDetalle to set
     */
    public void setSolicitudDetalle(SolicitudDetalle solicitudDetalle) {
        this.solicitudDetalle = solicitudDetalle;
    }

    /**
     * @return the solicitudDetallePk
     */
    public SolicitudDetallePK getSolicitudDetallePk() {
        return solicitudDetallePk;
    }

    /**
     * @param solicitudDetallePk the solicitudDetallePk to set
     */
    public void setSolicitudDetallePk(SolicitudDetallePK solicitudDetallePk) {
        this.solicitudDetallePk = solicitudDetallePk;
    }

    /**
     * @return the itemsSocio
     */
    public List<Socio> getItemsSocio() {
        return itemsSocio;
    }

    /**
     * @param itemsSocio the itemsSocio to set
     */
    public void setItemsSocio(List<Socio> itemsSocio) {
        this.itemsSocio = itemsSocio;
    }

    /**
     * @return the itemsSolicitudCredito
     */
    public List<Solicitud> getItemsSolicitudCredito() {
        return itemsSolicitudCredito;
    }

    /**
     * @param itemsSolicitudCredito the itemsSolicitudCredito to set
     */
    public void setItemsSolicitudCredito(List<Solicitud> itemsSolicitudCredito) {
        this.itemsSolicitudCredito = itemsSolicitudCredito;
    }

    /**
     * @return the itemsSolicitudDetalleCredito
     */
    public List<SolicitudDetalle> getItemsSolicitudDetalleCredito() {
        return itemsSolicitudDetalleCredito;
    }

    /**
     * @param itemsSolicitudDetalleCredito the itemsSolicitudDetalleCredito to
     * set
     */
    public void setItemsSolicitudDetalleCredito(List<SolicitudDetalle> itemsSolicitudDetalleCredito) {
        this.itemsSolicitudDetalleCredito = itemsSolicitudDetalleCredito;
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
     * @return the nombreSocio
     */
    public String getNombreSocio() {
        return nombreSocio;
    }

    /**
     * @param nombreSocio the nombreSocio to set
     */
    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    /**
     * @return the nombreSocioBusqueda
     */
    public String getNombreSocioBusqueda() {
        return nombreSocioBusqueda;
    }

    /**
     * @param nombreSocioBusqueda the nombreSocioBusqueda to set
     */
    public void setNombreSocioBusqueda(String nombreSocioBusqueda) {
        this.nombreSocioBusqueda = nombreSocioBusqueda;
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
     * @return the busquedaSocioLista
     */
    public boolean isBusquedaSocioLista() {
        return busquedaSocioLista;
    }

    /**
     * @param busquedaSocioLista the busquedaSocioLista to set
     */
    public void setBusquedaSocioLista(boolean busquedaSocioLista) {
        this.busquedaSocioLista = busquedaSocioLista;
    }



    /**
     * @return the itemsSeguimientoEstadoSolicitud
     */
    public List<SeguimientoEstadoSolicitud> getItemsSeguimientoEstadoSolicitud() {
        return itemsSeguimientoEstadoSolicitud;
    }

    /**
     * @param itemsSeguimientoEstadoSolicitud the
     * itemsSeguimientoEstadoSolicitud to set
     */
    public void setItemsSeguimientoEstadoSolicitud(List<SeguimientoEstadoSolicitud> itemsSeguimientoEstadoSolicitud) {
        this.itemsSeguimientoEstadoSolicitud = itemsSeguimientoEstadoSolicitud;
    }

    /**
     * @return the codigoSocio
     */
    public String getCodigoSocio() {
        return codigoSocio;
    }

    /**
     * @param codigoSocio the codigoSocio to set
     */
    public void setCodigoSocio(String codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

}
