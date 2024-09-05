package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import ec.renafipse.mks.modelo.creditos.InformeTecnico;
import ec.renafipse.mks.modelo.creditos.InformeTecnicoPK;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.seguridades.UsuarioEstadoCredito;
import ec.renafipse.mks.modelo.socios.ItemFlujoCaja;
import ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonial;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioFlujoCaja;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgreso;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgresoPK;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngreso;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngresoPK;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatAct;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatActPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatPas;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatPasPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
import ec.renafipse.mks.negocio.creditos.EstadoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.FabricaUsuarioPerfilFacade;
import ec.renafipse.mks.negocio.creditos.InformeTecnicoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.ItemFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.ItemSituacionPatrimonialFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaEgresoFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaIngresoFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatActFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatPasFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "informeTecnicoController")
@ViewScoped
public class InformeTecnicoController extends AbstractController<InformeTecnico> implements Serializable {

    @EJB
    private InformeTecnicoFacade ejbFacade;

    @EJB
    private SolicitudFacade ejbFacadeSolicitud;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private ItemFlujoCajaFacade ejbFacadeItemFlujoCaja;

    @EJB
    private SocioFlujoCajaFacade ejbFacadeSocioFluCaj;

    @EJB
    private SocioFlujoCajaIngresoFacade ejbFacadeSocioFluCajIng;

    @EJB
    private SocioFlujoCajaEgresoFacade ejbFacadeSocioFluCajEng;

    @EJB
    private ItemSituacionPatrimonialFacade ejbFacadeItemSitPat;

    @EJB
    private SocioSituacionPatrimonialFacade ejbFacadeSocioSitPat;

    @EJB
    private SocioSituacionPatActFacade ejbFacadeSocioSitPatAct;

    @EJB
    private SocioSituacionPatPasFacade ejbFacadeSocioSitPatPas;

    @EJB
    private EstadoCreditoFacade ejbFacadeEstadoCredito;

    @EJB
    private UsuarioFacade ejbFacadeUsuarioEstadoCredito;
    
    @EJB
    private FabricaUsuarioPerfilFacade ejbFacadeFabricaUsuarioPerfil;

    private LlamaSP llamaSP;

    //Variables
    private String buscarPor;
    private String nombreSocio;
    private String mensajeCriterio;
    private String msg;
    private String nombreSocioBusqueda;
    private String ubicaDetalle;

    private char ubicaFactible;

    private Timestamp fecha;

    private Long codigoInformeTecnico;
    private Long criterio;
    private Long codigoSocio;

    private boolean busquedaSocioLista;
    private boolean deshabilitaBuscarSocioLista;
    private boolean socioFlujoCajaNuevo;
    private boolean socioSituacionPatrimonialNuevo;
    private boolean sigueProceso;
    private boolean saltar;
    private boolean deshabilitaDetalle;
    private boolean deshabilitaFactible;
    private boolean deshabilitaGuardaBot;

    private Socio socioSel;
    private Solicitud solicitud;
    private Solicitud solicitudSel;
    private SolicitudDetalle solicitudDetalle;
    private ItemFlujoCaja itemFlujoCaja;
    private SocioFlujoCaja socioFlujoCaja;
    private ItemSituacionPatrimonial itemSituacionPatrimonial;
    private SocioSituacionPatrimonial socioSituacionPatrimonial;
    private GeneraReporte generaReporte;

    private List<SocioFlujoCajaIngreso> itemsSocioFlujoCajIng;
    private List<SocioFlujoCajaEgreso> itemsSocioFlujoCajEgr;
    private List<SocioSituacionPatAct> itemsSocioSituacionPatAct;
    private List<SocioSituacionPatPas> itemsSocioSituacionPatPas;
    private List<Socio> itemsSocios;
    private List<EstadoCredito> itemsEstadoCreditoLista;
    private List<Solicitud> itemsSolicitud;
    private List<UsuarioEstadoCredito> listaUsuarioEstadoCredito;

    @PostConstruct
    public void init() {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        try {
            //Verificar el acceso a la opcion por perfil en Fabrica de Credito
            if(ejbFacadeFabricaUsuarioPerfil.validaAccesoProcesoCredito(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), Long.parseLong("2"))){
                super.setFacade(ejbFacade);
                this.buscarPor = "S";
                this.cambiaCriterio();
                this.deshabilitaDetalle = false;
                this.deshabilitaFactible = false;
                this.ubicaDetalle = null;
                this.deshabilitaGuardaBot = true;
                this.setUbicaFactible('V');
                this.busquedaSocioLista = false;
                this.deshabilitaBuscarSocioLista = false;
                this.socioFlujoCajaNuevo = false;
                this.socioSituacionPatrimonialNuevo = false;
                this.sigueProceso = false;
                this.saltar = false;
                this.nombreSocio = null;
                this.mensajeCriterio = null;
                this.msg = null;
                this.nombreSocioBusqueda = null;
                this.criterio = null;
                this.codigoSocio = null;
                this.setItemsSocios(null);
                this.setSocioSel(null);
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
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"informeTecnicoController", "init", CapturaError.getErrorException(ex)});
            }
        }
    }

    public InformeTecnicoController() {
        // Inform the Abstract parent controller of the concrete InformeTecnico?cap_first Entity
        super(InformeTecnico.class);
    }

    //**********INICIO LOGICA
    /**
     * Al cambiar el criterio de Consulta
     */
    public void cambiaCriterio() {
        this.itemsSolicitud = null;
        this.nombreSocio = null;
        this.setCriterio(null);
        this.setDeshabilitaBuscarSocioLista(true);
        if (this.buscarPor.equals("S")) {
            this.setDeshabilitaBuscarSocioLista(false);
        }
    }

    /**
     * OBTIENE LAS SOLICITUDES DE CREDITO DE UN SOCIO O POR EL NUMERO DE
     * CREDITO, DE ACUERDO A LA BUSQUEDA.
     */
    public void obtieneSolicitudes() {
        this.itemsSolicitud = null;
        this.nombreSocio = null;
        if (this.buscarPor.equals("S")) {
            this.buscaSocio(true);
        } else {
            this.itemsSolicitud = this.ejbFacadeSolicitud.getItemsNumeroCodigoIfipEstadoFabrica(this.getCriterio(), ActivacionUsuario.getCodigoIfip(), Long.parseLong("1"),ActivacionUsuario.getCodigoUsuario());
            if (!this.itemsSolicitud.isEmpty()) {
                this.nombreSocio = this.itemsSolicitud.get(0).getSocio().getCodigoPersona().getNombreCompleto();
                // Validando el Socio
                if (!this.isSocioValido(this.itemsSolicitud.get(0).getSocio())) {
                    this.itemsSolicitud = null;
                }
            } else {
                this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoNoExiste"));
            }
        }
    }

    /**
     ** PREPARA LA BUSQUEDA DE LOS SOCIOS PARA CONSULTAR LOS CREDITOS.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void preparaBusquedaSocioLista(ActionEvent actionEvent) throws IOException {
        this.setNombreSocioBusqueda(null);
        this.itemsSocios = null;
        this.nombreSocio = null;
        this.setCriterio(null);
        this.setBusquedaSocioLista(true);
    }

    /**
     * BUSCA EL SOCIO DE ACUERDO AL CODIGO
     *
     * @param lista True cuando se va a buscar el socio para listar los creditos
     * y False cuando se busca el socio para crear la solicitud.
     */
    private void buscaSocio(boolean lista) {
        this.msg = null;
        Long codigoSocioBusqueda = (lista) ? this.criterio : this.getCodigoSocio();
        Socio socioBusqueda = this.ejbFacadeSocio.find(new SocioPK(codigoSocioBusqueda, ActivacionUsuario.getCodigoIfip()));

        if (socioBusqueda != null) {
            // Validando al Socio
            if (this.isSocioValido(socioBusqueda)) {
                // Si se busca el socio por el criterio de la consulta
                if (lista) {

                    this.itemsSolicitud = null;
                    this.nombreSocio = socioBusqueda.getCodigoPersona().getNombreCompleto();
                    // Buscando las solicitudes del socios
                    this.itemsSolicitud = this.ejbFacadeSolicitud.getItemsCodigoSocioCodigoIfipEstado(codigoSocioBusqueda, ActivacionUsuario.getCodigoIfip(), Long.parseLong("1"));

                    // Si se busca al socio para crear la solicitud de credito
                } else {
                    this.getSolicitud().setSocio(socioBusqueda);
                    this.setCodigoSocio((Long) socioBusqueda.getSocioPK().getCodigo());

                }
            } else {
                if (lista) {
                    this.nombreSocio = socioBusqueda.getCodigoPersona().getNombreCompleto();
                } else {
                    this.getSolicitud().setSocio(socioBusqueda);
                }
            }
        } else {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
        }

        if (getMsg() != null) {
            MuestraMensaje.addAdvertencia(getMsg());
        }
    }

    public void edicionCeldaIngresos(CellEditEvent event) {

        BigDecimal totalSocioIngreso = new BigDecimal("0.00");
        BigDecimal totalConyugeIngreso = new BigDecimal("0.00");

        for (SocioFlujoCajaIngreso sfci : this.itemsSocioFlujoCajIng) {
            totalSocioIngreso = totalSocioIngreso.add(sfci.getTotalSocio());
            totalConyugeIngreso = totalConyugeIngreso.add(sfci.getTotalConyuge());
        }

        this.getSocioFlujoCaja().setTotalIngresosSocio(totalSocioIngreso);
        this.getSocioFlujoCaja().setTotalIngresosConyuge(totalConyugeIngreso);

        this.getSocioFlujoCaja().setSaldo((getSocioFlujoCaja().getTotalIngresosSocio().add(getSocioFlujoCaja().getTotalIngresosConyuge())).subtract(this.getSocioFlujoCaja().getTotalGastosSocio()));
        this.getSocioFlujoCaja().setCobertura(this.getSocioFlujoCaja().getSaldo());

    }

    public void edicionCeldaEgresos(CellEditEvent event) {

        BigDecimal totalSocioGastos = new BigDecimal("0.00");

        for (SocioFlujoCajaEgreso sfce : this.itemsSocioFlujoCajEgr) {
            totalSocioGastos = totalSocioGastos.add(sfce.getTotalSocio());
        }

        this.getSocioFlujoCaja().setTotalGastosSocio(totalSocioGastos);

        this.getSocioFlujoCaja().setSaldo((getSocioFlujoCaja().getTotalIngresosSocio().add(getSocioFlujoCaja().getTotalIngresosConyuge())).subtract(this.getSocioFlujoCaja().getTotalGastosSocio()));
        this.getSocioFlujoCaja().setCobertura(this.getSocioFlujoCaja().getSaldo());

    }

    
       /**
     *
     * @param event
     */
    public void edicionCeldaActivos(CellEditEvent event) {

        BigDecimal totalActivosSocio = new BigDecimal("0.00");

        for (SocioSituacionPatAct sspa : this.itemsSocioSituacionPatAct) {
            totalActivosSocio = totalActivosSocio.add(sspa.getTotal());
        }

        this.getSocioSituacionPatrimonial().setTotalActivos(totalActivosSocio);
        this.getSocioSituacionPatrimonial().setTotalPatrimonio(this.getSocioSituacionPatrimonial().getTotalActivos().subtract(this.getSocioSituacionPatrimonial().getTotalPasivos()));

    }
    
      /**
     *
     * @param event
     */
    public void edicionCeldaPasivos(CellEditEvent event) {

        BigDecimal totalPasivosSocio = new BigDecimal("0.00");

        for (SocioSituacionPatPas sspp : this.itemsSocioSituacionPatPas) {
            totalPasivosSocio = totalPasivosSocio.add(sspp.getTotal());
        }

        this.getSocioSituacionPatrimonial().setTotalPasivos(totalPasivosSocio);
        this.getSocioSituacionPatrimonial().setTotalPatrimonio(this.getSocioSituacionPatrimonial().getTotalActivos().subtract(this.getSocioSituacionPatrimonial().getTotalPasivos()));

    }

    
    /**
     * VALIDA SI EL SOCIO PUEDE REALIZAR ACCIONES SOBRE LA SOLICITUD DE CREDITO.
     *
     * @param socio
     * @return True = Socio Valido FALSE= Socio Invalido
     */
    public boolean isSocioValido(Socio socio) {
        this.msg = null;

        if (socio.getCodigoIfipAgencia().getCodigo().compareTo(ActivacionUsuario.getCodigoIfipAgencia()) == 0) {
            if (String.valueOf(socio.getCodigoEstadoSocio().getIndicaActivo()).equals("S")) {
                return true;
            } else {
                this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioInactivo")
                        + ": {" + socio.getCodigoPersona().getNombreCompleto() + "," + socio.getCodigoEstadoSocio().getNombre() + " }");
            }
        } else {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia") + " "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgenciaSocio") + ": " + socio.getCodigoIfipAgencia().getNombre());
        }
        if (this.msg != null) {
            MuestraMensaje.addAdvertencia(msg);
        }
        return false;
    }

    public void seleccionaSocio(ActionEvent actionEvent) {

        if (this.busquedaSocioLista) {
            this.codigoSocio = null;
            this.criterio = this.socioSel.getSocioPK().getCodigo();
            this.nombreSocio = this.socioSel.getCodigoPersona().getNombreCompleto();
            // Busca el Socio
            this.buscaSocio(busquedaSocioLista);
        } else {
            this.solicitud.setSocio(new Socio());
            this.solicitud.setSocio(this.socioSel);
            this.codigoSocio = this.socioSel.getSocioPK().getCodigo();

            // Busca el Socio
            this.buscaSocio(busquedaSocioLista);
        }
    }

    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(this.nombreSocioBusqueda, 5)) {
                if (this.busquedaSocioLista) {
                    this.setSocioSel(null);
                    this.setSelected(null);
                }
                this.setItemsSocios(this.ejbFacadeSocio.getItemsNombresIndicaActivo(this.nombreSocioBusqueda, ActivacionUsuario.getCodigoIfip(), 'S'));

            } else {
                this.mensajeCriterio = Validaciones.msg;
                this.setItemsSocios(null);
                if (this.busquedaSocioLista) {
                    this.setSocioSel(null);
                    this.setSelected(null);
                } else {
                    this.limpiaDatosSocioSolicitud();
                }
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"informeTecnicoController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * LIMPIA LOS DATOS DE LA SOLICITUD.
     */
    public void limpiaDatosSocioSolicitud() {
        try {
            this.solicitud.setSocio(new Socio());
            this.solicitud.getSocio().setSocioPK(new SocioPK());
        } catch (Exception e) {
        }
    }

    /**
     * Metodo para dar segumiento al wizard del socio
     *
     * @param event
     * @return Evento
     */
    public String sigueProceso(FlowEvent event) {

        if (isSaltar()) {
            setSaltar(false);
            return "confirmacionTab";
        }

        if (event.getOldStep().equals("flujoCajaTab") && event.getNewStep().equals("situacionPatrimonialTab")) {
            this.validaFlujoCaja();
            if (!this.sigueProceso) {
                return "flujoCajaTab";
            }
        }

        if (event.getOldStep().equals("situacionPatrimonialTab") && event.getNewStep().equals("informeTecnicoTab")) {
            this.validaSituacionPatrimonial();
            if (!this.sigueProceso) {
                return "situacionPatrimonialTab";
            }
        }

        if (event.getOldStep().equals("informeTecnicoTab") && event.getNewStep().equals("confirmacionTab")) {
            this.validaInforme();

            if (!this.sigueProceso) {
                return "informeTecnicoTab";
            } else {
                if (this.ubicaDetalle != null && this.ubicaFactible != ' ') {
                    this.deshabilitaGuardaBot = false;
                }
            }
        }

        return event.getNewStep();
    }

    private void validaFlujoCaja() {
        this.sigueProceso = true;
        if (this.getSocioFlujoCaja().getTotalIngresosSocio().compareTo(BigDecimal.ZERO) == 0
                || this.getSocioFlujoCaja().getTotalGastosSocio().compareTo(BigDecimal.ZERO) == 0) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseFlujoCaja");
            MuestraMensaje.addAdvertencia(msg);
            this.sigueProceso = false;
        }
    }

    private void validaSituacionPatrimonial() {
        this.sigueProceso = true;
        if (this.getSocioSituacionPatrimonial().getTotalPatrimonio().compareTo(BigDecimal.ZERO) == 0) {
            this.sigueProceso = false;
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseSituacionPatrimonial");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    public void validaInforme() {
        this.sigueProceso = true;
        this.msg = null;
        if (String.valueOf(this.getUbicaFactible()).equals("V") && this.ubicaDetalle == null) {
            this.sigueProceso = false;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseInformeTecnico");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    /**
     *
     * @param actionEvent
     */
    public void editaSituacionEconomica(ActionEvent actionEvent) {

        this.setSocioFlujoCajaNuevo(false);
        this.setSocioSituacionPatrimonialNuevo(false);
        // Obteniendo el flujo de caja del socio
        this.setSocioFlujoCaja(this.ejbFacadeSocioFluCaj.getSocioFlujoCajaCodigoSocioIfip(this.getSolicitudSel().getSocio().getSocioPK().getCodigo(), 
                                                                                          this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip())
                                                                                         );

        // Consultando si existe el flujo de caja, caso contrario instanciamos un nuevo
        if (this.getSocioFlujoCaja() == null) {
            this.setSocioFlujoCajaNuevo(true);
            this.setSocioFlujoCaja(new SocioFlujoCaja(this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo()));
            this.getSocioFlujoCaja().setCodigoSocio(this.getSolicitudSel().getSocio().getSocioPK().getCodigo());
            this.getSocioFlujoCaja().setCodigoIfip(this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip());
            this.getSocioFlujoCaja().setTotalGastosSocio(new BigDecimal("0.00"));
            this.getSocioFlujoCaja().setTotalIngresosConyuge(new BigDecimal("0.00"));
            this.getSocioFlujoCaja().setTotalIngresosSocio(new BigDecimal("0.00"));
            this.getSocioFlujoCaja().setSaldo(new BigDecimal("0.00"));
            this.getSocioFlujoCaja().setCobertura(new BigDecimal("0.00"));

        }

        // Obteniendo la Situacion Patrimonial del Socio
         this.setSocioSituacionPatrimonial(this.ejbFacadeSocioSitPat.getSocioSocioSituacionPatrimonialCodigoSocioIfip(
                this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip()
        ));
        // Consultando si existe la Situacion Patrimonial, caso contrario instanciamos un nuevo
        if (this.getSocioSituacionPatrimonial() == null) {
            this.setSocioSituacionPatrimonialNuevo(true);
            this.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo()));
            this.getSocioSituacionPatrimonial().setCodigoSocio(this.getSolicitudSel().getSocio().getSocioPK().getCodigo());
            this.getSocioSituacionPatrimonial().setCodigoIfip(this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip());
            this.getSocioSituacionPatrimonial().setTotalActivos(new BigDecimal("0.00"));
            this.getSocioSituacionPatrimonial().setTotalPasivos(new BigDecimal("0.00"));
            this.getSocioSituacionPatrimonial().setTotalPatrimonio(new BigDecimal("0.00"));

        }
        this.armaFlujoCaja();
        this.armaSituacionPatrimonial();
    }

    /**
     * ARMA LOS CASILLEROS DE LOS FLUJOS DE CAJA, TANTO DE INGRESOS COMO DE
     * EGRESOS.
     */
    public void armaFlujoCaja() {

        boolean existe;

        //----------------------------------------------
        // Armando los Ingresos del Socio
        List<ItemFlujoCaja> listItemsFlujoCajaIngreso = this.ejbFacadeItemFlujoCaja.getItemsFlujoCaja('I', 'N');
        SocioFlujoCajaIngreso socioFlujoCajaIngreso;
        SocioFlujoCajaIngresoPK sfcipk;

        // Obteniendo el flujo de ingreso del socio
        this.itemsSocioFlujoCajIng = this.ejbFacadeSocioFluCajIng.getItemsFlujoCajaIngresoSocio(this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip());

        // Comparo si tiene el flujo de ingresos, caso contrario creo un nuevo arreglo
        if (this.itemsSocioFlujoCajIng == null) {
            this.itemsSocioFlujoCajIng = new ArrayList<SocioFlujoCajaIngreso>();
        }

        // Bucle para armar la lista de acuerdo los Items de Ingreso
        for (ItemFlujoCaja i : listItemsFlujoCajaIngreso) {

            existe = false;

            // Si el socio tiene flujo de caja de ingresos, validamos si el item se encuentra en ese listado
            if (this.itemsSocioFlujoCajIng.size() > 0) {
                for (SocioFlujoCajaIngreso sfci : this.itemsSocioFlujoCajIng) {
                    if (sfci.getItemFlujoCaja().getCodigo() == i.getCodigo()) {
                        existe = true;
                    }

                    if (existe) {
                        break;
                    }
                }
            }

            // Si el item no se encuentra, lo agregamos como un nuevo
            if (!existe) {
                socioFlujoCajaIngreso = new SocioFlujoCajaIngreso();

                sfcipk = new SocioFlujoCajaIngresoPK(
                        i.getCodigo(),
                        this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo()
                        );

                socioFlujoCajaIngreso.setSocioFlujoCajaIngresoPK(sfcipk);
                socioFlujoCajaIngreso.setSocioFlujoCaja(new SocioFlujoCaja(this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo(),
                                                                           this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                                                                           this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip()
                                                                           )
                                                        ); 
                socioFlujoCajaIngreso.setItemFlujoCaja(i);
                socioFlujoCajaIngreso.setTotalSocio(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                socioFlujoCajaIngreso.setTotalConyuge(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));

                this.itemsSocioFlujoCajIng.add(socioFlujoCajaIngreso);
            }

        }

        //----------------------------------------------
        // Armando los Egresos del Socio
        List<ItemFlujoCaja> listItemsFlujoCajaEgreso = this.ejbFacadeItemFlujoCaja.getItemsFlujoCaja('E', 'N');
        SocioFlujoCajaEgreso socioFlujoCajaEgreso;
        SocioFlujoCajaEgresoPK sfcepk;

        // Obteniendo el flujo de ingreso del socio
        this.itemsSocioFlujoCajEgr = this.ejbFacadeSocioFluCajEng.getItemsFlujoCajaEgresoSocio(this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip());

        // Comparo si tiene el flujo de ingresos, caso contrario creo un nuevo arreglo
        if (this.itemsSocioFlujoCajEgr == null) {
            this.itemsSocioFlujoCajEgr = new ArrayList<SocioFlujoCajaEgreso>();
        }

        // Bucle para armar la lista de acuerdo los Items de Egreso
        for (ItemFlujoCaja i : listItemsFlujoCajaEgreso) {

            existe = false;

            // Si el socio tiene flujo de caja de egresos, validamos si el item se encuentra en ese listado
            if (this.itemsSocioFlujoCajEgr.size() > 0) {
                for (SocioFlujoCajaEgreso sfce : this.itemsSocioFlujoCajEgr) {
                    if (sfce.getItemFlujoCaja().getCodigo() == i.getCodigo()) {
                        existe = true;
                    }

                    if (existe) {
                        break;
                    }
                }
            }

            // Si el item no se encuentra, lo agregamos como un nuevo
            if (!existe) {
                socioFlujoCajaEgreso = new SocioFlujoCajaEgreso();

                sfcepk = new SocioFlujoCajaEgresoPK(
                            i.getCodigo(),
                            this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo()
                        );

                socioFlujoCajaEgreso.setSocioFlujoCajaEgresoPK(sfcepk);
                socioFlujoCajaEgreso.setSocioFlujoCaja(new SocioFlujoCaja(this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo(),
                                                                          this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                                                                          this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip()
                                                                          )
                                                       ); 
                socioFlujoCajaEgreso.setItemFlujoCaja(i);
                socioFlujoCajaEgreso.setTotalSocio(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                this.itemsSocioFlujoCajEgr.add(socioFlujoCajaEgreso);
            }

        }

    }

    /**
     * ARMA LOS ITEMS DE LA SITUACION PATRIMONIAL DEL SOCIO.
     */
    public void armaSituacionPatrimonial() {
        boolean existe;

        //----------------------------------------------
        // Armando los Activos del Socio
        List<ItemSituacionPatrimonial> listItemsSituacionPatAct = this.ejbFacadeItemSitPat.getItemsSituacionPatrimonial('A', 'N');
        SocioSituacionPatAct socioSituacionPatAct;
        SocioSituacionPatActPK sspapk;

        // Obteniendo el flujo de ingreso del socio
        this.itemsSocioSituacionPatAct = this.ejbFacadeSocioSitPatAct.getItemsSocioSituacionPatAct(this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip());

        // Comparo si tiene el flujo de ingresos, caso contrario creo un nuevo arreglo
        if (this.itemsSocioSituacionPatAct == null) {
            this.itemsSocioSituacionPatAct = new ArrayList<SocioSituacionPatAct>();
        }

        // Bucle para armar la lista de acuerdo los Items de Ingreso
        for (ItemSituacionPatrimonial i : listItemsSituacionPatAct) {

            existe = false;

            // Si el socio tiene flujo de caja de ingresos, validamos si el item se encuentra en ese listado
            if (this.itemsSocioSituacionPatAct.size() > 0) {
                for (SocioSituacionPatAct sfci : this.itemsSocioSituacionPatAct) {
                    if (sfci.getItemSituacionPatrimonial().getCodigo() == i.getCodigo()) {
                        existe = true;
                    }

                    if (existe) {
                        break;
                    }
                }
            }

            // Si el item no se encuentra, lo agregamos como un nuevo
            if (!existe) {
                socioSituacionPatAct = new SocioSituacionPatAct();

                 sspapk = new SocioSituacionPatActPK(
                        i.getCodigo(),
                        this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo());

                socioSituacionPatAct.setSocioSituacionPatActPK(sspapk);
                socioSituacionPatAct.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                                                         this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo(),
                                                                         this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                                                                         this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip()
                                                                         )
                                                      );
                socioSituacionPatAct.setItemSituacionPatrimonial(i);
                socioSituacionPatAct.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));

                this.itemsSocioSituacionPatAct.add(socioSituacionPatAct);
            }
        }

        //----------------------------------------------
        // Armando los Pasivos del Socio
        List<ItemSituacionPatrimonial> listItemsSituacionPatPas = this.ejbFacadeItemSitPat.getItemsSituacionPatrimonial('P', 'N');
        SocioSituacionPatPas socioSituacionPatPas;
        SocioSituacionPatPasPK sspppk;

        // Obteniendo el flujo de ingreso del socio
        this.itemsSocioSituacionPatPas = this.ejbFacadeSocioSitPatPas.getItemsSocioSituacionPatPas(this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip());

        // Comparo si tiene el flujo de ingresos, caso contrario creo un nuevo arreglo
        if (this.itemsSocioSituacionPatPas == null) {
            this.itemsSocioSituacionPatPas = new ArrayList<SocioSituacionPatPas>();
        }

        // Bucle para armar la lista de acuerdo los Items de Ingreso
        for (ItemSituacionPatrimonial i : listItemsSituacionPatPas) {

            existe = false;

            // Si el socio tiene flujo de caja de ingresos, validamos si el item se encuentra en ese listado
            if (this.itemsSocioSituacionPatPas.size() > 0) {
                for (SocioSituacionPatPas sspp : this.itemsSocioSituacionPatPas) {
                    if (sspp.getItemSituacionPatrimonial().getCodigo() == i.getCodigo()) {
                        existe = true;
                    }

                    if (existe) {
                        break;
                    }
                }
            }

            // Si el item no se encuentra, lo agregamos como un nuevo
            if (!existe) {
                socioSituacionPatPas = new SocioSituacionPatPas();

                sspppk = new SocioSituacionPatPasPK(i.getCodigo(),
                        this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo());

                socioSituacionPatPas.setSocioSituacionPatPasPK(sspppk);
                socioSituacionPatPas.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                                          this.getSolicitudSel().getSocio().getCodigoPersona().getCodigo(),
                                                          this.getSolicitudSel().getSocio().getSocioPK().getCodigo(),
                                                          this.getSolicitudSel().getSocio().getSocioPK().getCodigoIfip()
                                                          ));
                socioSituacionPatPas.setItemSituacionPatrimonial(i);
                socioSituacionPatPas.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));

                this.itemsSocioSituacionPatPas.add(socioSituacionPatPas);
            }
        }
    }

    
      /**
     *
     * @param actionEvent
     */
    public void guardaSituacionEconomica() {
        try {
            // ----------------------------------------------------
            // Guardando la cabecera de la Situacion economica
            // Flujo de Caja.
            this.socioFlujoCaja.setFechaActualizacion(new Date());
            this.socioFlujoCaja.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());
            if (this.isSocioFlujoCajaNuevo()) {

                this.socioFlujoCaja.setCodigoUsuarioIngreso(ActivacionUsuario.getCodigoUsuario());
                this.socioFlujoCaja.setFechaIngreso(new Date());
                this.socioFlujoCaja.setFechaActualizacion(this.socioFlujoCaja.getFechaIngreso());
                this.ejbFacadeSocioFluCaj.create(socioFlujoCaja);
            } else {
                this.ejbFacadeSocioFluCaj.edit(socioFlujoCaja);
            }

            // Situacion Patrimonial
            this.socioSituacionPatrimonial.setFechaActualizacion(new Date());
            this.socioSituacionPatrimonial.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());
            if (this.isSocioSituacionPatrimonialNuevo()) {
                this.socioSituacionPatrimonial.setCodigoUsuarioIngreso(ActivacionUsuario.getCodigoUsuario());
                this.socioSituacionPatrimonial.setFechaIngreso(new Date());
                this.socioSituacionPatrimonial.setFechaIngreso(this.socioSituacionPatrimonial.getFechaIngreso());
                this.ejbFacadeSocioSitPat.create(socioSituacionPatrimonial);
            } else {
                this.ejbFacadeSocioSitPat.edit(socioSituacionPatrimonial);
            }

            // ----------------------------------------------------
            // Guardando el detalle de la Situacion economica
            // Flujo de Caja - Ingresos        
            for (SocioFlujoCajaIngreso sfci : this.itemsSocioFlujoCajIng) {
                this.ejbFacadeSocioFluCajIng.edit(sfci);
            }

            // Flujo de Caja - Egresos        
            for (SocioFlujoCajaEgreso sfce : this.itemsSocioFlujoCajEgr) {
                this.ejbFacadeSocioFluCajEng.edit(sfce);
            }

            // Situacion Patrimonial - Activos        
            for (SocioSituacionPatAct sspa : this.itemsSocioSituacionPatAct) {
                this.ejbFacadeSocioSitPatAct.edit(sspa);
            }

            // Situacion Patrimonial - Pasivos        
            for (SocioSituacionPatPas sspp : this.itemsSocioSituacionPatPas) {
                this.ejbFacadeSocioSitPatPas.edit(sspp);
            }

            MuestraMensaje.addSatisfactorioPersistencia(1);
            

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"informeTecnicoController", "guardaSituacionEconomica", CapturaError.getErrorException(ex)});
        }

    }

    
    public void realizaInformeTecnico(ActionEvent event) {
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
             //   guardaSituacionEconomica();
                this.deshabilitaGuardaBot = true;
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformeTecnicoRealizadoCorrectamente");
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
                    new Object[]{"informeTecnicoController", "realizaInformeTecnico", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    public boolean guardaInformeTecnico() throws UnknownHostException {
        this.setFecha(new java.sql.Timestamp(new Date().getTime()));
        EstadoCredito estado = this.ejbFacadeEstadoCredito.find(Long.parseLong("2"));
        llamaSP.setNombreSP("mks_creditos.pkm_informe_tecnico.p_guarda_informe_tecnico");
        llamaSP.setNumeroParametros(16);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_solicitud", this.solicitudSel.getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", fecha});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_factible", String.valueOf(this.getUbicaFactible())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_detalle", this.ubicaDetalle});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_ingresos", this.socioFlujoCaja.getTotalIngresosSocio()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_egresos", this.socioFlujoCaja.getTotalGastosSocio()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_flujo_caja", this.socioFlujoCaja.getSaldo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobertura", this.socioFlujoCaja.getCobertura()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_activos", this.socioSituacionPatrimonial.getTotalActivos()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_pasivos", this.socioSituacionPatrimonial.getTotalPasivos()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_patimonio ", this.socioSituacionPatrimonial.getTotalPatrimonio()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", estado.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fecha});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_soli", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.codigoInformeTecnico = Long.parseLong(llamaSP.getListResultado().get(0).toString());

        }
        return llamaSP.isEjecucionCorrecta();
    }

    public void imprimeInformeTecnico(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte = null;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitudSel.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitudSel.getSolicitudPK().getCodigoIfip());        
        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        getGeneraReporte().getParametros().put("subReporteListadoGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantesListado"));
        
        nombreReporte = "informeTecnico";

        getGeneraReporte().exporta("/financiero/creditos/informeTecnico/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.codigoInformeTecnico) + ".pdf",
                "PDF", externalContext, facesContext);
    }

    //********FIN LOGICA
    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getInformeTecnicoPK().setNumeroSolicitud(this.getSelected().getSolicitud().getSolicitudPK().getNumero());
        this.getSelected().getInformeTecnicoPK().setCodigoIfip(this.getSelected().getSolicitud().getSolicitudPK().getCodigoIfip());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setInformeTecnicoPK(new InformeTecnicoPK());
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
     * @return the itemsSocios
     */
    public List<Socio> getItemsSocios() {
        return itemsSocios;
    }

    /**
     * @param itemsSocios the itemsSocios to set
     */
    public void setItemsSocios(List<Socio> itemsSocios) {
        this.itemsSocios = itemsSocios;
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
     * @return the deshabilitaBuscarSocioLista
     */
    public boolean isDeshabilitaBuscarSocioLista() {
        return deshabilitaBuscarSocioLista;
    }

    /**
     * @param deshabilitaBuscarSocioLista the deshabilitaBuscarSocioLista to set
     */
    public void setDeshabilitaBuscarSocioLista(boolean deshabilitaBuscarSocioLista) {
        this.deshabilitaBuscarSocioLista = deshabilitaBuscarSocioLista;
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
     * @return the codigoSocio
     */
    public Long getCodigoSocio() {
        return codigoSocio;
    }

    /**
     * @param codigoSocio the codigoSocio to set
     */
    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
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
     * @return the socioFlujoCajaNuevo
     */
    public boolean isSocioFlujoCajaNuevo() {
        return socioFlujoCajaNuevo;
    }

    /**
     * @param socioFlujoCajaNuevo the socioFlujoCajaNuevo to set
     */
    public void setSocioFlujoCajaNuevo(boolean socioFlujoCajaNuevo) {
        this.socioFlujoCajaNuevo = socioFlujoCajaNuevo;
    }

    /**
     * @return the socioSituacionPatrimonialNuevo
     */
    public boolean isSocioSituacionPatrimonialNuevo() {
        return socioSituacionPatrimonialNuevo;
    }

    /**
     * @param socioSituacionPatrimonialNuevo the socioSituacionPatrimonialNuevo
     * to set
     */
    public void setSocioSituacionPatrimonialNuevo(boolean socioSituacionPatrimonialNuevo) {
        this.socioSituacionPatrimonialNuevo = socioSituacionPatrimonialNuevo;
    }

    /**
     * @return the sigueProceso
     */
    public boolean isSigueProceso() {
        return sigueProceso;
    }

    /**
     * @param sigueProceso the sigueProceso to set
     */
    public void setSigueProceso(boolean sigueProceso) {
        this.sigueProceso = sigueProceso;
    }

    /**
     * @return the saltar
     */
    public boolean isSaltar() {
        return saltar;
    }

    /**
     * @param saltar the saltar to set
     */
    public void setSaltar(boolean saltar) {
        this.saltar = saltar;
    }

    /**
     * @return the itemFlujoCaja
     */
    public ItemFlujoCaja getItemFlujoCaja() {
        return itemFlujoCaja;
    }

    /**
     * @param itemFlujoCaja the itemFlujoCaja to set
     */
    public void setItemFlujoCaja(ItemFlujoCaja itemFlujoCaja) {
        this.itemFlujoCaja = itemFlujoCaja;
    }

    /**
     * @return the socioFlujoCaja
     */
    public SocioFlujoCaja getSocioFlujoCaja() {
        return socioFlujoCaja;
    }

    /**
     * @param socioFlujoCaja the socioFlujoCaja to set
     */
    public void setSocioFlujoCaja(SocioFlujoCaja socioFlujoCaja) {
        this.socioFlujoCaja = socioFlujoCaja;
    }

    /**
     * @return the itemSituacionPatrimonial
     */
    public ItemSituacionPatrimonial getItemSituacionPatrimonial() {
        return itemSituacionPatrimonial;
    }

    /**
     * @param itemSituacionPatrimonial the itemSituacionPatrimonial to set
     */
    public void setItemSituacionPatrimonial(ItemSituacionPatrimonial itemSituacionPatrimonial) {
        this.itemSituacionPatrimonial = itemSituacionPatrimonial;
    }

    /**
     * @return the socioSituacionPatrimonial
     */
    public SocioSituacionPatrimonial getSocioSituacionPatrimonial() {
        return socioSituacionPatrimonial;
    }

    /**
     * @param socioSituacionPatrimonial the socioSituacionPatrimonial to set
     */
    public void setSocioSituacionPatrimonial(SocioSituacionPatrimonial socioSituacionPatrimonial) {
        this.socioSituacionPatrimonial = socioSituacionPatrimonial;
    }

    /**
     * @return the itemsSocioFlujoCajIng
     */
    public List<SocioFlujoCajaIngreso> getItemsSocioFlujoCajIng() {
        return itemsSocioFlujoCajIng;
    }

    /**
     * @param itemsSocioFlujoCajIng the itemsSocioFlujoCajIng to set
     */
    public void setItemsSocioFlujoCajIng(List<SocioFlujoCajaIngreso> itemsSocioFlujoCajIng) {
        this.itemsSocioFlujoCajIng = itemsSocioFlujoCajIng;
    }

    /**
     * @return the itemsSocioFlujoCajEgr
     */
    public List<SocioFlujoCajaEgreso> getItemsSocioFlujoCajEgr() {
        return itemsSocioFlujoCajEgr;
    }

    /**
     * @param itemsSocioFlujoCajEgr the itemsSocioFlujoCajEgr to set
     */
    public void setItemsSocioFlujoCajEgr(List<SocioFlujoCajaEgreso> itemsSocioFlujoCajEgr) {
        this.itemsSocioFlujoCajEgr = itemsSocioFlujoCajEgr;
    }

    /**
     * @return the itemsSocioSituacionPatAct
     */
    public List<SocioSituacionPatAct> getItemsSocioSituacionPatAct() {
        return itemsSocioSituacionPatAct;
    }

    /**
     * @param itemsSocioSituacionPatAct the itemsSocioSituacionPatAct to set
     */
    public void setItemsSocioSituacionPatAct(List<SocioSituacionPatAct> itemsSocioSituacionPatAct) {
        this.itemsSocioSituacionPatAct = itemsSocioSituacionPatAct;
    }

    /**
     * @return the itemsSocioSituacionPatPas
     */
    public List<SocioSituacionPatPas> getItemsSocioSituacionPatPas() {
        return itemsSocioSituacionPatPas;
    }

    /**
     * @param itemsSocioSituacionPatPas the itemsSocioSituacionPatPas to set
     */
    public void setItemsSocioSituacionPatPas(List<SocioSituacionPatPas> itemsSocioSituacionPatPas) {
        this.itemsSocioSituacionPatPas = itemsSocioSituacionPatPas;
    }

    /**
     * @return the ubicaDetalle
     */
    public String getUbicaDetalle() {
        return ubicaDetalle;
    }

    /**
     * @param ubicaDetalle the ubicaDetalle to set
     */
    public void setUbicaDetalle(String ubicaDetalle) {
        this.ubicaDetalle = ubicaDetalle;
    }

    /**
     * @return the deshabilitaDetalle
     */
    public boolean isDeshabilitaDetalle() {
        return deshabilitaDetalle;
    }

    /**
     * @param deshabilitaDetalle the deshabilitaDetalle to set
     */
    public void setDeshabilitaDetalle(boolean deshabilitaDetalle) {
        this.deshabilitaDetalle = deshabilitaDetalle;
    }

    /**
     * @return the deshabilitaFactible
     */
    public boolean isDeshabilitaFactible() {
        return deshabilitaFactible;
    }

    /**
     * @param deshabilitaFactible the deshabilitaFactible to set
     */
    public void setDeshabilitaFactible(boolean deshabilitaFactible) {
        this.deshabilitaFactible = deshabilitaFactible;
    }

    /**
     * @return the ubicaFactible
     */
    public char getUbicaFactible() {
        return ubicaFactible;
    }

    /**
     * @param ubicaFactible the ubicaFactible to set
     */
    public void setUbicaFactible(char ubicaFactible) {
        this.ubicaFactible = ubicaFactible;
    }

    /**
     * @return the codigoInformeTecnico
     */
    public Long getCodigoInformeTecnico() {
        return codigoInformeTecnico;
    }

    /**
     * @param codigoInformeTecnico the codigoInformeTecnico to set
     */
    public void setCodigoInformeTecnico(Long codigoInformeTecnico) {
        this.codigoInformeTecnico = codigoInformeTecnico;
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
     * @return the listaUsuarioEstadoCredito
     */
    public List<UsuarioEstadoCredito> getListaUsuarioEstadoCredito() {
        return listaUsuarioEstadoCredito;
    }

    /**
     * @param listaUsuarioEstadoCredito the listaUsuarioEstadoCredito to set
     */
    public void setListaUsuarioEstadoCredito(List<UsuarioEstadoCredito> listaUsuarioEstadoCredito) {
        this.listaUsuarioEstadoCredito = listaUsuarioEstadoCredito;
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

    /**
     * @return the deshabilitaGuardaBot
     */
    public boolean isDeshabilitaGuardaBot() {
        return deshabilitaGuardaBot;
    }

    /**
     * @param deshabilitaGuardaBot the deshabilitaGuardaBot to set
     */
    public void setDeshabilitaGuardaBot(boolean deshabilitaGuardaBot) {
        this.deshabilitaGuardaBot = deshabilitaGuardaBot;
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

}
