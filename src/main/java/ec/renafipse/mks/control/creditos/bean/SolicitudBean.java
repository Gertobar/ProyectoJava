package ec.renafipse.mks.control.creditos.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import ec.renafipse.mks.modelo.creditos.SeguimientoEstadoSolicitud;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.seguridades.UsuarioEstadoCredito;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.creditos.EstadoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.FabricaUsuarioPerfilFacade;
import ec.renafipse.mks.negocio.creditos.SeguimientoEstadoSolicitudFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "solicitudBean")
@ViewScoped
public class SolicitudBean extends AbstractController<Solicitud> implements Serializable {

    @EJB
    private SolicitudFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private EstadoCreditoFacade ejbFacadeEstadoCredito;

    @EJB
    private SocioFlujoCajaFacade ejbFacadeSocioFlujoCaja;

    @EJB
    private SocioSituacionPatrimonialFacade ejbFacadeSocioSituacionPatrimonial;

    @EJB
    private UsuarioFacade ejbFacadeUsuarioEstadoCredito;

    @EJB
    private SeguimientoEstadoSolicitudFacade ejbFacadeSeguimientoEstadoSolicitud;
    
    @EJB
    private FabricaUsuarioPerfilFacade ejbFacadeFabricaUsuarioPerfil;

    private LlamaSP llamaSP;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String msg;

    private boolean deshabilitaEnvioBot;

    private Socio socioSel;
    private EstadoCredito estadoCreditoBusqueda;
    private UsuarioEstadoCredito usuarioEstadoCredito;
    private Solicitud solicitud;
    private SeguimientoEstadoSolicitud seguimientoEstadoSolicitud;
    private Timestamp fecha;

    private List<Socio> itemsSocio;
    private List<Solicitud> itemsSolicitud;
    private List<EstadoCredito> itemsEstadoCreditoLista;
    private List<Solicitud> itemsSolicitudSel;
    private List<UsuarioEstadoCredito> listaUsuarioEstadoCredito;

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        try {
            //Verificar el acceso a la opcion por perfil en Fabrica de Credito
            if(ejbFacadeFabricaUsuarioPerfil.validaAccesoProcesoCredito(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), Long.parseLong("3"))){
                this.seguimientoEstadoSolicitud = new SeguimientoEstadoSolicitud();
                this.deshabilitaEnvioBot = true;
                this.itemsSolicitud = this.ejbFacade.getItemsSolicitudCodigoIfipFabrica(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), Long.parseLong("11"), ActivacionUsuario.getCodigoUsuario());
            } else {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                Sesion.redireccionaPagina(url);
            }
        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"solicitudBean", "init", CapturaError.getErrorException(ex)});
            }
        }
    }

    public void solicitudSeleccionada(SelectEvent event) {
        this.deshabilitaEnvioBot = false;
    }

    public void solicitudNoSeleccionada(UnselectEvent event) {
        this.deshabilitaEnvioBot = false;
    }

    public void solicitudesSeleccionadas(ToggleSelectEvent event) {
        this.deshabilitaEnvioBot = false;
    }

    public void emiteInformeTecnico(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            if (this.guardaInformeTecnico()) {
                this.deshabilitaEnvioBot = true;

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SolicitudEnviadaAEmiionCorrectamente");
                MuestraMensaje.addSatisfactorio(msg);
                context.execute("procesandoDlg.hide()");
                this.init();
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
                    new Object[]{"solicitudBean", "emiteInformeTecnico", CapturaError.getErrorException(ex)});
        }
    }

    public boolean guardaInformeTecnico() {
        llamaSP.setNombreSP("mks_creditos.pkm_envio_a_comision.p_guarda_envio_a_comision");
        this.fecha = new java.sql.Timestamp(new Date().getTime());
        llamaSP.setNumeroParametros(5);

        if (this.itemsSolicitudSel != null) {
            EstadoCredito estado = this.ejbFacadeEstadoCredito.find(Long.parseLong("3"));
            for (Solicitud so : this.itemsSolicitudSel) {
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_solicitud", so.getSolicitudPK().getNumero()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", so.getSolicitudPK().getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", estado.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fecha});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                    break;
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    // ---------------------------------------------------------------------------
    /**
     * @return the itemsSolicitud
     */
    public List<Solicitud> getItemsSolicitud() {
        return itemsSolicitud;
    }

    /**
     * @param itemsSolicitud the itemsSolicitud to set
     */
    public void setItemsSolicitud(List<Solicitud> itemsSolicitud) {
        this.itemsSolicitud = itemsSolicitud;
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
     * @return the estadoCreditoBusqueda
     */
    public EstadoCredito getEstadoCreditoBusqueda() {
        return estadoCreditoBusqueda;
    }

    /**
     * @param estadoCreditoBusqueda the estadoCreditoBusqueda to set
     */
    public void setEstadoCreditoBusqueda(EstadoCredito estadoCreditoBusqueda) {
        this.estadoCreditoBusqueda = estadoCreditoBusqueda;
    }

    /**
     * @return the itemsEstadoCreditoLista
     */
    public List<EstadoCredito> getItemsEstadoCreditoLista() {
        this.itemsEstadoCreditoLista = this.ejbFacadeEstadoCredito.findAll();
        return itemsEstadoCreditoLista;
    }

    /**
     * @param itemsEstadoCreditoLista the itemsEstadoCreditoLista to set
     */
    public void setItemsEstadoCreditoLista(List<EstadoCredito> itemsEstadoCreditoLista) {
        this.itemsEstadoCreditoLista = itemsEstadoCreditoLista;
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     * @param solicitud the solicitud to set
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
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
     * @return the ejbFacadeSocioFlujoCaja
     */
    public SocioFlujoCajaFacade getEjbFacadeSocioFlujoCaja() {
        return ejbFacadeSocioFlujoCaja;
    }

    /**
     * @param ejbFacadeSocioFlujoCaja the ejbFacadeSocioFlujoCaja to set
     */
    public void setEjbFacadeSocioFlujoCaja(SocioFlujoCajaFacade ejbFacadeSocioFlujoCaja) {
        this.ejbFacadeSocioFlujoCaja = ejbFacadeSocioFlujoCaja;
    }

    /**
     * @return the ejbFacadeSocioSituacionPatrimonial
     */
    public SocioSituacionPatrimonialFacade getEjbFacadeSocioSituacionPatrimonial() {
        return ejbFacadeSocioSituacionPatrimonial;
    }

    /**
     * @param ejbFacadeSocioSituacionPatrimonial the
     * ejbFacadeSocioSituacionPatrimonial to set
     */
    public void setEjbFacadeSocioSituacionPatrimonial(SocioSituacionPatrimonialFacade ejbFacadeSocioSituacionPatrimonial) {
        this.ejbFacadeSocioSituacionPatrimonial = ejbFacadeSocioSituacionPatrimonial;
    }

    /**
     * @return the itemsSolicitudSel
     */
    public List<Solicitud> getItemsSolicitudSel() {
        return itemsSolicitudSel;
    }

    /**
     * @param itemsSolicitudSel the itemsSolicitudSel to set
     */
    public void setItemsSolicitudSel(List<Solicitud> itemsSolicitudSel) {
        this.itemsSolicitudSel = itemsSolicitudSel;
    }

    /**
     * @return the deshabilitaEnvioBot
     */
    public boolean isDeshabilitaEnvioBot() {
        return deshabilitaEnvioBot;
    }

    /**
     * @param deshabilitaEnvioBot the deshabilitaEnvioBot to set
     */
    public void setDeshabilitaEnvioBot(boolean deshabilitaEnvioBot) {
        this.deshabilitaEnvioBot = deshabilitaEnvioBot;
    }

    /**
     * @return the usuarioEstadoCredito
     */
    public UsuarioEstadoCredito getUsuarioEstadoCredito() {
        return usuarioEstadoCredito;
    }

    /**
     * @param usuarioEstadoCredito the usuarioEstadoCredito to set
     */
    public void setUsuarioEstadoCredito(UsuarioEstadoCredito usuarioEstadoCredito) {
        this.usuarioEstadoCredito = usuarioEstadoCredito;
    }

    /**
     * @return the fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

}
