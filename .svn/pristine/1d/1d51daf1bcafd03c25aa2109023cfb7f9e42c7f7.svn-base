package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.GaranteCredito;
import ec.renafipse.mks.modelo.creditos.GaranteNotificacion;
import ec.renafipse.mks.modelo.creditos.NotificacionCredito;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.creditos.SolicitudDetallePK;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK;
import ec.renafipse.mks.modelo.creditos.TipoNotificacionIfip;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.DiasEnvioNotificacionIfipFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.GaranteNotificacionFacade;
import ec.renafipse.mks.negocio.creditos.NotificacionCreditoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.creditos.TablaAmortizacionFacade;
import ec.renafipse.mks.negocio.creditos.TipoNotificacionIfipFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
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
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "notificacionCreditoController")
@ViewScoped
public class NotificacionCreditoController extends AbstractController<NotificacionCredito> implements Serializable {

    @EJB
    private NotificacionCreditoFacade ejbFacade;

    @EJB
    private TipoNotificacionIfipFacade ejbFacadeTipoNotificacionIfip;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private SolicitudFacade ejbFacadeSolicitud;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private TablaAmortizacionFacade ejbFacadeTablaAmortizacion;

    @EJB
    private GaranteCreditoFacade ejbFacadeGaranteCredito;

    @EJB
    private GaranteNotificacionFacade ejbFacadeGaranteNotificacion;

    @EJB
    private DiasEnvioNotificacionIfipFacade ejbFacadeDiasEnvioNotificacionIfip;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    private LlamaSP llamaSP;

    private TipoNotificacionIfip ubicaTipoNotificacionIfip;
    private Socio socio;
    private Socio socioSel;
    private Solicitud solicitudSel;
    private GaranteCredito garanteCreditoSel;
    private SolicitudDetalle solicitudDetalle;
    private SolicitudDetallePK solicitudDetallePk;
    private TablaAmortizacion tablaAmortizacion;
    private TablaAmortizacionPK tablaAmortizacionPK;
    private NotificacionCredito notificacionCreditoSel;
    private NotificacionCredito notificacionCredito;
    private GeneraReporte generaReporte;
    private TipoNotificacionIfip tipoNotificacionIfip;
    private CalculoCuotaPagar cuotaVencida;

    private long codigoNotificacion;
    private String codigoSocio;
    private long codigoAnulacion;

    private String ubicaEsParaGarante;
    private String msg;
    private String nombreSocio;
    private String nombreSocioBusqueda;
    private String mensajeCriterio;
    private String ubicaEtiquetaRecibir;
    private String ubicaEtiquetaAnular;
    private BigDecimal ubicaValorNotificacion;
    private Timestamp fechaNotificacion;
    private Timestamp fecha;

    private boolean visiblePanelGarantes;
    private boolean busquedaSocioLista;
    private boolean deshabilitaValorNotificacion;
    private boolean deshabilitaBotonNotificacionCredito;
    private boolean deshabilitaTipoNoti;
    private boolean deshabilitaEsParaGarante;
    private boolean visibleAnularPan;
    private boolean visibleRecibirPan;
    private boolean esRecibir;
    private boolean esAnular;

    private List<TipoNotificacionIfip> itemsTipoNotificacionIfip;
    private List<Socio> itemsSocio;
    private List<Solicitud> itemsSolicitudCredito;
    private List<SolicitudDetalle> itemsSolicitudDetalleCredito;
    private List<TablaAmortizacion> itemsCuotasPendientes;
    private List<CalculoCuotaPagar> itemsCuotasVencidas;
    private List<NotificacionCredito> itemsNotificacionCredito;
    private List<NotificacionCredito> listaNotificacionCreditoValida;
    private List<GaranteCredito> itemsGaranteCredito;
    private List<GaranteCredito> itemsGaranteCreditoSel;
    private List<GaranteNotificacion> itemsGaranteNotificacion;

    /*Nuevo Calculo del valor de notificacion segun tabla SEPS*/
    private int numeroCuotaCalculo;
    private BigDecimal capitalCuotaCalculo;
    private int diasMoraCalculo;
    private BigDecimal valorNotificacionActualCargado;
    private String observaciones;
    /*Nuevo Calculo del valor de notificacion segun tabla SEPS*/

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        this.setItemsTipoNotificacionIfip(new ArrayList<TipoNotificacionIfip>());
        this.setItemsGaranteCredito(new ArrayList<GaranteCredito>());
        this.setItemsGaranteCreditoSel(new ArrayList<GaranteCredito>());
        this.setItemsNotificacionCredito(new ArrayList<NotificacionCredito>());
        this.setItemsGaranteNotificacion(new ArrayList<GaranteNotificacion>());
        this.setItemsSocio(new ArrayList<Socio>());
        this.setItemsSolicitudCredito(new ArrayList<Solicitud>());
        this.setItemsSolicitudDetalleCredito(new ArrayList<SolicitudDetalle>());
        this.setUbicaTipoNotificacionIfip(null);
        this.fechaNotificacion = new java.sql.Timestamp(new Date().getTime());
        this.nombreSocio = null;
        this.notificacionCredito = new NotificacionCredito();
        this.visiblePanelGarantes = false;
        this.visibleAnularPan = false;
        this.visibleRecibirPan = false;
        this.nombreSocioBusqueda = null;
        this.mensajeCriterio = null;
        this.deshabilitaValorNotificacion = true;
        this.deshabilitaBotonNotificacionCredito = true;
        this.tipoNotificacionIfip = new TipoNotificacionIfip();
    }

    public NotificacionCreditoController() {
        // Inform the Abstract parent controller of the concrete NotificacionCredito?cap_first Entity
        super(NotificacionCredito.class);
    }

    //****************INICIO LOGICA*******************//
    public void obtieneNombreSocio() {
        this.setSocio(new Socio());
        this.setItemsSocio(this.ejbFacadeSocio.getItemsSocioCodigo(Long.parseLong(getCodigoSocio()), ActivacionUsuario.getCodigoIfip()));

        if (!itemsSocio.isEmpty()) {
            if (getItemsSocio().size() == 1) {

                // Validando que el socio pertenezca a la agencia del oficial de credito
                if (validaAgenciaSocio(this.getItemsSocio().get(0).getCodigoIfipAgencia().getCodigo())) {
                    this.setSocio(this.getItemsSocio().get(0));
                    obtieneSolicitudCreditoSocio();
                }
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
                    new Object[]{"pagoCreditoController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneSolicitudNombreSocio() {
        this.solicitudSel = new Solicitud();
        try {
            if (this.socioSel != null) {
                // Validando que el socio pertenezca a la agencia del oficial de credito
                if (validaAgenciaSocio(socioSel.getCodigoIfipAgencia().getCodigo())) {
                    this.itemsSolicitudCredito = this.ejbFacadeSolicitudDetalle.getItemsSolicitudCodSocioCodIfipDiasMora(this.getSocioSel().getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip(), 0);
                    if (!itemsSolicitudCredito.isEmpty()) {

                    } else {
                        this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoTieneSolicitudMora");
                        MuestraMensaje.addAdvertencia(msg);
                    }
                }
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneSolicitudNombreSocio", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Valida que la sucursal en la que esta el oficial sea la que pertenece el
     * socio.
     *
     * @param codigoIfipAgenciaSocio Codigo de Ifip de Agencia del Socio
     * @return true = correcto false = incorrecto
     */
    private boolean validaAgenciaSocio(Long codigoIfipAgenciaSocio) {
        if (codigoIfipAgenciaSocio != ActivacionUsuario.getCodigoIfipAgencia()) {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
            MuestraMensaje.addAdvertencia(msg);
            this.nombreSocio = null;
            return false;
        }

        return true;
    }

    public void obtieneSolicitudCreditoSocio() {
        this.setSolicitudSel(new Solicitud());
        try {
            this.itemsSolicitudCredito = this.ejbFacadeSolicitudDetalle.getItemsSolicitudCodSocioCodIfipDiasMora(Long.parseLong(getCodigoSocio()), ActivacionUsuario.getCodigoIfip(), 0);
            this.setSolicitudSel(null);

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneSolicitudCreditoSocio", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneDetalleCreditoParcial() throws IOException {
//        this.deshabilitaEtiCuotasOValor = true;
        //       limpiaDatos();
//        limpiaDatosSalida();
        this.solicitudDetallePk = new SolicitudDetallePK();
        this.solicitudDetalle = new SolicitudDetalle();
        this.setTablaAmortizacion(new TablaAmortizacion());
        this.tablaAmortizacionPK = new TablaAmortizacionPK();
        if (this.getSolicitudSel() != null) {
            this.solicitudDetallePk.setNumeroCredito(this.solicitudSel.getSolicitudPK().getNumero());
            this.solicitudDetallePk.setCodigoIfip(this.solicitudSel.getSolicitudPK().getCodigoIfip());
            this.solicitudDetalle = this.ejbFacadeSolicitudDetalle.find(this.solicitudDetallePk);
            this.setItemsCuotasPendientes(this.ejbFacadeTablaAmortizacion.getItemsCreditoCuotaPendiente(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'P'));
        }

    }

    public void validaEsParaGarante() {
        if (this.ubicaTipoNotificacionIfip != null) {
            if (this.ubicaEsParaGarante.equals("S")) {
                obtieneGaranteCredito();
                this.visiblePanelGarantes = true;
                this.deshabilitaBotonNotificacionCredito = false;
            } else if (this.ubicaEsParaGarante.equals("N")) {
                this.deshabilitaBotonNotificacionCredito = false;
                this.visiblePanelGarantes = false;
                this.itemsGaranteCredito.clear();
                this.itemsGaranteCreditoSel.clear();
            }
        } else {
            validaTipoNotificacion();
        }
    }

    public void validaTipoNotificacion() {
        if (this.ubicaTipoNotificacionIfip == null) {
            this.itemsGaranteCredito.clear();
            this.ubicaEsParaGarante = null;
            this.deshabilitaValorNotificacion = true;
            this.itemsGaranteCredito.clear();
            this.itemsGaranteCreditoSel.clear();
            this.deshabilitaEsParaGarante = true;
            this.deshabilitaBotonNotificacionCredito = true;
        } else {
            this.tipoNotificacionIfip = this.ejbFacadeTipoNotificacionIfip.getTipoNotificacionCodigo(this.getUbicaTipoNotificacionIfip().getTipoNotificacionIfipPK().getCodigoTipoNotificacion());
            if (this.tipoNotificacionIfip != null) {
                this.deshabilitaEsParaGarante = false;
            }
        }
    }

    public void TipoNotificacion() {

    }

    public void obtieneGaranteCredito() {
        try {
            this.itemsGaranteCredito = this.ejbFacadeGaranteCredito.getItemsGaranteCreditoIfipVigenteEliminado(this.solicitudSel.getSolicitudPK().getNumero(), ActivacionUsuario.getCodigoIfip(), 'S', 'N');
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"notificacionCreditoController", "obtieneGaranteCredito", CapturaError.getErrorException(ex)});
        }
    }

    public void habilitaBoton() {
        this.deshabilitaBotonNotificacionCredito = this.ubicaValorNotificacion.compareTo(new BigDecimal("-1")) == 0;
    }

    public void creaNotificacion() {
        try {
            this.setUbicaTipoNotificacionIfip(null);
            this.fechaNotificacion = new java.sql.Timestamp(new Date().getTime());
            this.nombreSocio = null;
            this.notificacionCredito = new NotificacionCredito();
            this.visiblePanelGarantes = false;
            this.visibleAnularPan = false;
            this.visibleRecibirPan = false;
            this.nombreSocioBusqueda = null;
            this.mensajeCriterio = null;
            this.deshabilitaValorNotificacion = true;
            this.deshabilitaBotonNotificacionCredito = true;
            this.tipoNotificacionIfip = new TipoNotificacionIfip();
            this.ubicaEsParaGarante = null;
            obtieneDetalleCreditoParcial();
            this.itemsTipoNotificacionIfip = null;
            //Cambio calculo notificaión crédito
            /*Obtien los valores necesarios de costo de notificaciòn y la cuota en la que se debe asignar*/
            obtineValorNotificacion();
            if (this.ubicaValorNotificacion.compareTo(new BigDecimal("-1")) == 0) {   
            } else {
                this.itemsCuotasVencidas = this.ejbFacadeCalculoCuotaPagar.getItemsCalculoCuotaNumeroIfip(solicitudDetalle.getSolicitudDetallePK().getNumeroCredito(), ActivacionUsuario.getCodigoIfip(), Long.valueOf(getNumeroCuotaCalculo()));
                this.cuotaVencida = this.itemsCuotasVencidas.get(0);
                this.itemsTipoNotificacionIfip = this.ejbFacadeDiasEnvioNotificacionIfip.getItemsDiasTipoNotificacionIfip(solicitudDetalle.getDiasMora(), ActivacionUsuario.getCodigoIfip(), 'N', solicitudDetalle.getSolicitudDetallePK().getNumeroCredito(), Long.valueOf(getNumeroCuotaCalculo()), 'A');
            }
            //Fin cambio calculo notificaión crédito

        } catch (IOException ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"notificacionCreditoController", "creaNotificacion", CapturaError.getErrorException(ex)});
            setUbicaValorNotificacion(new BigDecimal(-1));
        }
    }

    public void obtineValorNotificacion() {
        try {
            /*Validación solo se puede tener una notificación en estado emitida debe recibir para poder cargar otra*/
            listaNotificacionCreditoValida = this.ejbFacade.getItemsPagoCreditoSocio(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'E', 'N');
            if (listaNotificacionCreditoValida != null){
                if(listaNotificacionCreditoValida.size() >= 1){
                    MuestraMensaje.addError("El credito ya tiene emitida una notificación, favor recibir la notificación.");
                    this.ubicaValorNotificacion = new BigDecimal(-1);
                    return;
                }
            }    
            /*Validación solo se puede tener una notificación en estado emitida debe recibir para poder cargar otra*/

            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            // Formando parametros para el sp
            llamaSP.setNombreSP("mks_creditos.pkm_cobranza_extrajudicial.p_obtiene_valor_notificacion");
            llamaSP.setNumeroParametros(7);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_valor_not", Types.DECIMAL});
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_numero_cuota_not", Types.INTEGER});
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_capital_cuota", Types.DECIMAL});
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_dias_mora", Types.INTEGER});
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_valor_notificacion_ant", Types.DECIMAL});
            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                setUbicaValorNotificacion((BigDecimal) llamaSP.getListResultado().get(0));
                setNumeroCuotaCalculo((Integer) llamaSP.getListResultado().get(1));
                setCapitalCuotaCalculo((BigDecimal) llamaSP.getListResultado().get(2));
                setDiasMoraCalculo((Integer) llamaSP.getListResultado().get(3));
                setValorNotificacionActualCargado((BigDecimal) llamaSP.getListResultado().get(4));
            } else {
                this.ubicaValorNotificacion = new BigDecimal(-1);
            }
        } catch (Exception e) {
            MuestraMensaje.addError("Error al calcular el valor de la notificación.");
            Logger.getLogger(NotificacionCreditoController.class.getName()).log(Level.SEVERE, null, e);
            setUbicaValorNotificacion(new BigDecimal(-1));
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    public void garantesSeleccionados(SelectEvent event) {
        this.deshabilitaBotonNotificacionCredito = false;
    }

    public void garanteNoSeleccionado(UnselectEvent event) {
        this.deshabilitaBotonNotificacionCredito = false;
    }

    public void garanteSeleccionado(ToggleSelectEvent event) {
        this.deshabilitaBotonNotificacionCredito = false;
    }

    //NOTIFICACION CREDITO
    public boolean emiteNotificacionCredito() throws UnknownHostException {
        if (this.ubicaValorNotificacion.compareTo(BigDecimal.ZERO) == -1){
            MuestraMensaje.addError("Error al emitir notificacion, el valor calculado es erroneo");
            return false;
        }

        this.fechaNotificacion = new java.sql.Timestamp(new Date().getTime());
        llamaSP.setNombreSP("mks_creditos.pkm_notificacion_credito.p_guarda_notificacion_credito");
        llamaSP.setNumeroParametros(12);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        //Datos de Notificacion de Credito
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.getSolicitudSel().getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.getSolicitudSel().getSolicitudPK().getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota", this.cuotaVencida.getCalculoCuotaPagarPK().getCuota()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_notificacion", this.getUbicaTipoNotificacionIfip().getTipoNotificacionIfipPK().getCodigoTipoNotificacion()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_para_garante", String.valueOf(this.ubicaEsParaGarante)});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", String.valueOf(this.ubicaValorNotificacion)});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", String.valueOf(ActivacionUsuario.getCodigoUsuario())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", this.fechaNotificacion});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_saldo_capital_cuota", getCapitalCuotaCalculo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_dias_mora", getDiasMoraCalculo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observaciones", "EMITIDA"});
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_notificacion", Types.VARCHAR});
        try{
            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                this.setCodigoNotificacion(Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                //System.out.println("CodigoNotificacion: " + this.codigoNotificacion);
            }
            else{
                llamaSP.rollback();
                MuestraMensaje.addError("Error al emitir la notificación, resultado.");
            }
        } catch (Exception e) {
            llamaSP.rollback();
            MuestraMensaje.addError("Error al emitir la notificación.");
            Logger.getLogger(NotificacionCreditoController.class.getName()).log(Level.SEVERE, null, e);
        } 
        return llamaSP.isEjecucionCorrecta();
    }

    //GARANTE NOTIFICACION
    public boolean registraGaranteNotificacion() throws UnknownHostException {
        llamaSP.setNombreSP("mks_creditos.pkg_garante_notificacion.p_inserta_garante_notificacion");
        llamaSP.setNumeroParametros(4);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        for (GaranteCredito gc : this.getItemsGaranteCreditoSel()) {
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_notificacion", this.codigoNotificacion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_garante", gc.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", this.fechaNotificacion});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public void guardaNotificacionCredito(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            if (this.emiteNotificacionCredito()) {
                if (String.valueOf(this.ubicaEsParaGarante).equals("S")) {
                    registraGaranteNotificacion();
                } else if (String.valueOf(this.ubicaEsParaGarante).equals("N")) {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenGarantesNotificacion"));
                }
            }
            else
                return;
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NotificacionCreditoGrabado"));
                MuestraMensaje.addInformacion(getMsg());
                this.deshabilitaBotonNotificacionCredito = true;
                this.deshabilitaValorNotificacion = true;
                this.deshabilitaEsParaGarante = true;
                this.itemsGaranteCredito.clear();
                this.itemsGaranteCreditoSel.clear();
                this.itemsTipoNotificacionIfip.clear();
                this.ubicaTipoNotificacionIfip = null;
                context.execute("procesandoDlg.hide()");
                context.execute("NotificacionesCreditoDialog.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
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
                    new Object[]{"notificacionCreditoController", "guardaNotificacionCredito", CapturaError.getErrorException(ex)});
        }

    }

    public void recibeNotificaciones(ActionEvent event) {
        try {
            this.itemsNotificacionCredito = this.ejbFacade.getItemsPagoCreditoSocio(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'E', 'N');
        } catch (Exception ex) {
            MuestraMensaje.addError("Error al obtener notificaciones pago crédito.");
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"notificacionCreditoController", "recibeNotificaciones", CapturaError.getErrorException(ex)});
        }
    }

    public void recibeNotificacionesCre(ActionEvent event) {
        try {
            this.ubicaEtiquetaAnular = null;
            this.setUbicaEtiquetaRecibir(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RecibirNotificaciones"));
            this.itemsNotificacionCredito = this.ejbFacade.getItemsPagoCreditoSocio(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'E', 'N');
            this.visibleRecibirPan = true;
            this.visibleAnularPan = false;
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addError("Error al ubicar notificacion.");
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"notificacionCreditoController", "recibeNotificacionesCre", CapturaError.getErrorException(ex)});
        }
    }

    public void anulaNotificacionesCre(ActionEvent event) {
        try {
            this.setUbicaEtiquetaRecibir(null);
            this.itemsNotificacionCredito = this.ejbFacade.getItemsPagoCreditoSocio(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'R', 'N');
            this.setUbicaEtiquetaAnular(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AnularNotificaciones"));
            this.visibleAnularPan = true;
            this.visibleRecibirPan = false;
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addError("Error al ubicar anulación.");
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"notificacionCreditoController", "anulaNotificacionesCre", CapturaError.getErrorException(ex)});
        }
    }

    public void recibirNotificacion() {
        boolean actualizaEstado = false;
        try {
            /*Nuevo Calculo del valor de notificacion segun tabla SEPS*/
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            // Formando parametros para el sp
            llamaSP.setNombreSP("mks_creditos.pkm_notificacion_credito.p_actualiza_not_cal_cuo_det");
            llamaSP.setNumeroParametros(8);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", this.getNotificacionCreditoSel().getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_credito", this.getNotificacionCreditoSel().getNumeroCredito()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cuota", this.getNotificacionCreditoSel().getCuota()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_notificacion_causada", this.getNotificacionCreditoSel().getValor() });
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_notificacion", 1});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_notificacion", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_notificacion", this.getNotificacionCreditoSel().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {                
                try{
                    //Creo la persona       
                    this.getNotificacionCreditoSel().setEstado('R');
                    this.getNotificacionCreditoSel().setObservaciones(getObservaciones());
                    //En caso de que el valor sea 0 la notificacion se pone como cancelada por problemas en el fin de dia pago automatico
                    if (this.getNotificacionCreditoSel().getValor() == BigDecimal.ZERO)
                        this.getNotificacionCreditoSel().setCancelada('S');
                    
                    this.ejbFacade.edit(getNotificacionCreditoSel());
                    actualizaEstado = true;
                } catch (Exception ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"NotificacionCreditoController", "recibirNotificacionObtencionDatos", CapturaError.getErrorException(ex)});
                    MuestraMensaje.addError("Error al recibir notificación.");
                    actualizaEstado = false;
                }
                
                if (actualizaEstado) {
                    llamaSP.commit();              
                    this.itemsNotificacionCredito = this.ejbFacade.getItemsPagoCreditoSocio(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'E', 'N');
                    setObservaciones("");
                    MuestraMensaje.addSatisfactorio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NotificacionRecibidaCorrectamente"));
                }
                else
                    llamaSP.rollback();
                
            } else {
                llamaSP.rollback();
                MuestraMensaje.addError("Error en el proceso, al recibir notificación.");
            }
        } catch (Exception ex) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"NotificacionCreditoController", "recibirNotificacion", CapturaError.getErrorException(ex)});
            MuestraMensaje.addError("Error al recibir notificación.");
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
        /*Nuevo Calculo del valor de notificacion segun tabla SEPS*/
    }

    public boolean anularNotificacion() throws UnknownHostException {
        this.fecha = new java.sql.Timestamp(new Date().getTime());
        llamaSP.setNombreSP("mks_creditos.pkm_anulacion_credito.p_guarda_anulacion_credito");
        llamaSP.setNumeroParametros(11);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_notificacion", this.notificacionCreditoSel.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_anulacion", this.fecha});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fecha});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.notificacionCreditoSel.getNumeroCredito()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.notificacionCreditoSel.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", this.notificacionCreditoSel.getValor()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota", this.notificacionCreditoSel.getCuota()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observaciones", getObservaciones()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_notificacion", this.notificacionCreditoSel.getCodigo()});
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_anulacion", Types.VARCHAR});
        try {
            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                this.setCodigoAnulacion(Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                setObservaciones("");
            }
        } catch (Exception ex) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"NotificacionCreditoController", "anularNotificacion", CapturaError.getErrorException(ex)});
            MuestraMensaje.addError("Error en el proceso, al anular notificación.");
        }
        
        return llamaSP.isEjecucionCorrecta();
    }

    public void guardaAnulaNotificacion() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            if (this.anularNotificacion()) {

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NotificacionAnuladaCorrectamente"));
                MuestraMensaje.addInformacion(getMsg());
                context.execute("procesandoDlg.hide()");
                this.itemsNotificacionCredito = this.ejbFacade.getItemsPagoCreditoSocio(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'E', 'N');
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (UnknownHostException ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa

            MuestraMensaje.addError("Error al anular notificacion.");
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"notificacionCreditoController", "guardaAnulaNotificacion", CapturaError.getErrorException(ex)});
        }
    }

    public void imprimeNotificacionCredito(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.deshabilitaBotonNotificacionCredito = true;
        String nombreReporte = null;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoNotificacion", this.codigoNotificacion);

        if (String.valueOf(this.ubicaEsParaGarante).equals("S")) {
            nombreReporte = "notificacionCeditoGarante";
        } else if (String.valueOf(this.ubicaEsParaGarante).equals("N")) {
            nombreReporte = "notificacionCreditoSocio";
        }

        getGeneraReporte().exporta("/financiero/creditos/notificaciones/reporte/", nombreReporte,
                nombreReporte + String.valueOf(codigoNotificacion) + ".pdf",
                "PDF", externalContext, facesContext);
    }
    //****************FIN LOGICA*******************//
    /**
     * @return the ubicaEsParaGarante
     */
    public String getUbicaEsParaGarante() {
        return ubicaEsParaGarante;
    }

    /**
     * @param ubicaEsParaGarante the ubicaEsParaGarante to set
     */
    public void setUbicaEsParaGarante(String ubicaEsParaGarante) {
        this.ubicaEsParaGarante = ubicaEsParaGarante;
    }

    /**
     * @return the itemsTipoNotificacionIfip
     */
    public List<TipoNotificacionIfip> getItemsTipoNotificacionIfip() {
        return itemsTipoNotificacionIfip;
    }

    /**
     * @param itemsTipoNotificacionIfip the itemsTipoNotificacionIfip to set
     */
    public void setItemsTipoNotificacionIfip(List<TipoNotificacionIfip> itemsTipoNotificacionIfip) {
        this.itemsTipoNotificacionIfip = itemsTipoNotificacionIfip;
    }

    /**
     * @return the ubicaValorNotificacion
     */
    public BigDecimal getUbicaValorNotificacion() {
        return ubicaValorNotificacion;
    }

    /**
     * @param ubicaValorNotificacion the ubicaValorNotificacion to set
     */
    public void setUbicaValorNotificacion(BigDecimal ubicaValorNotificacion) {
        this.ubicaValorNotificacion = ubicaValorNotificacion;
    }

    /**
     * @return the deshabilitaValorNotificacion
     */
    public boolean isDeshabilitaValorNotificacion() {
        return deshabilitaValorNotificacion;
    }

    /**
     * @param deshabilitaValorNotificacion the deshabilitaValorNotificacion to
     * set
     */
    public void setDeshabilitaValorNotificacion(boolean deshabilitaValorNotificacion) {
        this.deshabilitaValorNotificacion = deshabilitaValorNotificacion;
    }

    /**
     * @return the deshabilitaBotonNotificacionCredito
     */
    public boolean isDeshabilitaBotonNotificacionCredito() {
        return deshabilitaBotonNotificacionCredito;
    }

    /**
     * @param deshabilitaBotonNotificacionCredito the
     * deshabilitaBotonNotificacionCredito to set
     */
    public void setDeshabilitaBotonNotificacionCredito(boolean deshabilitaBotonNotificacionCredito) {
        this.deshabilitaBotonNotificacionCredito = deshabilitaBotonNotificacionCredito;
    }

    /**
     * @return the codigoNotificacion
     */
    public long getCodigoNotificacion() {
        return codigoNotificacion;
    }

    /**
     * @param codigoNotificacion the codigoNotificacion to set
     */
    public void setCodigoNotificacion(long codigoNotificacion) {
        this.codigoNotificacion = codigoNotificacion;
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
     * @return the itemsCuotasPendientes
     */
    public List<TablaAmortizacion> getItemsCuotasPendientes() {
        return itemsCuotasPendientes;
    }

    /**
     * @param itemsCuotasPendientes the itemsCuotasPendientes to set
     */
    public void setItemsCuotasPendientes(List<TablaAmortizacion> itemsCuotasPendientes) {
        this.itemsCuotasPendientes = itemsCuotasPendientes;
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
     * @return the tablaAmortizacion
     */
    public TablaAmortizacion getTablaAmortizacion() {
        return tablaAmortizacion;
    }

    /**
     * @param tablaAmortizacion the tablaAmortizacion to set
     */
    public void setTablaAmortizacion(TablaAmortizacion tablaAmortizacion) {
        this.tablaAmortizacion = tablaAmortizacion;
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
     * @return the itemsNotificacionCredito
     */
    public List<NotificacionCredito> getItemsNotificacionCredito() {
        return itemsNotificacionCredito;
    }

    /**
     * @param itemsNotificacionCredito the itemsNotificacionCredito to set
     */
    public void setItemsNotificacionCredito(List<NotificacionCredito> itemsNotificacionCredito) {
        this.itemsNotificacionCredito = itemsNotificacionCredito;
    }

    /**
     * @return the itemsGaranteCredito
     */
    public List<GaranteCredito> getItemsGaranteCredito() {
        return itemsGaranteCredito;
    }

    /**
     * @param itemsGaranteCredito the itemsGaranteCredito to set
     */
    public void setItemsGaranteCredito(List<GaranteCredito> itemsGaranteCredito) {
        this.itemsGaranteCredito = itemsGaranteCredito;
    }

    /**
     * @return the garanteCreditoSel
     */
    public GaranteCredito getGaranteCreditoSel() {
        return garanteCreditoSel;
    }

    /**
     * @param garanteCreditoSel the garanteCreditoSel to set
     */
    public void setGaranteCreditoSel(GaranteCredito garanteCreditoSel) {
        this.garanteCreditoSel = garanteCreditoSel;
    }

    /**
     * @return the visiblePanelGarantes
     */
    public boolean isVisiblePanelGarantes() {
        return visiblePanelGarantes;
    }

    /**
     * @param visiblePanelGarantes the visiblePanelGarantes to set
     */
    public void setVisiblePanelGarantes(boolean visiblePanelGarantes) {
        this.visiblePanelGarantes = visiblePanelGarantes;
    }

    /**
     * @return the ubicaTipoNotificacionIfip
     */
    public TipoNotificacionIfip getUbicaTipoNotificacionIfip() {
        return ubicaTipoNotificacionIfip;
    }

    /**
     * @param ubicaTipoNotificacionIfip the ubicaTipoNotificacionIfip to set
     */
    public void setUbicaTipoNotificacionIfip(TipoNotificacionIfip ubicaTipoNotificacionIfip) {
        this.ubicaTipoNotificacionIfip = ubicaTipoNotificacionIfip;
    }

    /**
     * @return the itemsGaranteCreditoSel
     */
    public List<GaranteCredito> getItemsGaranteCreditoSel() {
        return itemsGaranteCreditoSel;
    }

    /**
     * @param itemsGaranteCreditoSel the itemsGaranteCreditoSel to set
     */
    public void setItemsGaranteCreditoSel(List<GaranteCredito> itemsGaranteCreditoSel) {
        this.itemsGaranteCreditoSel = itemsGaranteCreditoSel;
    }

    /**
     * @return the itemsGaranteNotificacion
     */
    public List<GaranteNotificacion> getItemsGaranteNotificacion() {
        return itemsGaranteNotificacion;
    }

    /**
     * @param itemsGaranteNotificacion the itemsGaranteNotificacion to set
     */
    public void setItemsGaranteNotificacion(List<GaranteNotificacion> itemsGaranteNotificacion) {
        this.itemsGaranteNotificacion = itemsGaranteNotificacion;
    }

    /**
     * @return the deshabilitaTipoNoti
     */
    public boolean isDeshabilitaTipoNoti() {
        return deshabilitaTipoNoti;
    }

    /**
     * @param deshabilitaTipoNoti the deshabilitaTipoNoti to set
     */
    public void setDeshabilitaTipoNoti(boolean deshabilitaTipoNoti) {
        this.deshabilitaTipoNoti = deshabilitaTipoNoti;
    }

    /**
     * @return the deshabilitaEsParaGarante
     */
    public boolean isDeshabilitaEsParaGarante() {
        return deshabilitaEsParaGarante;
    }

    /**
     * @param deshabilitaEsParaGarante the deshabilitaEsParaGarante to set
     */
    public void setDeshabilitaEsParaGarante(boolean deshabilitaEsParaGarante) {
        this.deshabilitaEsParaGarante = deshabilitaEsParaGarante;
    }

    /**
     * @return the notificacionCreditoSel
     */
    public NotificacionCredito getNotificacionCreditoSel() {
        return notificacionCreditoSel;
    }

    /**
     * @param notificacionCreditoSel the notificacionCreditoSel to set
     */
    public void setNotificacionCreditoSel(NotificacionCredito notificacionCreditoSel) {
        this.notificacionCreditoSel = notificacionCreditoSel;
    }

    /**
     * @return the visibleAnularPan
     */
    public boolean isVisibleAnularPan() {
        return visibleAnularPan;
    }

    /**
     * @param visibleAnularPan the visibleAnularPan to set
     */
    public void setVisibleAnularPan(boolean visibleAnularPan) {
        this.visibleAnularPan = visibleAnularPan;
    }

    /**
     * @return the visibleRecibirPan
     */
    public boolean isVisibleRecibirPan() {
        return visibleRecibirPan;
    }

    /**
     * @param visibleRecibirPan the visibleRecibirPan to set
     */
    public void setVisibleRecibirPan(boolean visibleRecibirPan) {
        this.visibleRecibirPan = visibleRecibirPan;
    }

    /**
     * @return the esRecibir
     */
    public boolean isEsRecibir() {
        return esRecibir;
    }

    /**
     * @param esRecibir the esRecibir to set
     */
    public void setEsRecibir(boolean esRecibir) {
        this.esRecibir = esRecibir;
    }

    /**
     * @return the esAnular
     */
    public boolean isEsAnular() {
        return esAnular;
    }

    /**
     * @param esAnular the esAnular to set
     */
    public void setEsAnular(boolean esAnular) {
        this.esAnular = esAnular;
    }

    /**
     * @return the ubicaEtiquetaRecibir
     */
    public String getUbicaEtiquetaRecibir() {
        return ubicaEtiquetaRecibir;
    }

    /**
     * @param ubicaEtiquetaRecibir the ubicaEtiquetaRecibir to set
     */
    public void setUbicaEtiquetaRecibir(String ubicaEtiquetaRecibir) {
        this.ubicaEtiquetaRecibir = ubicaEtiquetaRecibir;
    }

    /**
     * @return the ubicaEtiquetaAnular
     */
    public String getUbicaEtiquetaAnular() {
        return ubicaEtiquetaAnular;
    }

    /**
     * @param ubicaEtiquetaAnular the ubicaEtiquetaAnular to set
     */
    public void setUbicaEtiquetaAnular(String ubicaEtiquetaAnular) {
        this.ubicaEtiquetaAnular = ubicaEtiquetaAnular;
    }

    /**
     * @return the notificacionCredito
     */
    public NotificacionCredito getNotificacionCredito() {
        return notificacionCredito;
    }

    /**
     * @param notificacionCredito the notificacionCredito to set
     */
    public void setNotificacionCredito(NotificacionCredito notificacionCredito) {
        this.notificacionCredito = notificacionCredito;
    }

    /**
     * @return the codigoAnulacion
     */
    public long getCodigoAnulacion() {
        return codigoAnulacion;
    }

    /**
     * @param codigoAnulacion the codigoAnulacion to set
     */
    public void setCodigoAnulacion(long codigoAnulacion) {
        this.codigoAnulacion = codigoAnulacion;
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

    /**
     * @return the itemsCuotasVencidas
     */
    public List<CalculoCuotaPagar> getItemsCuotasVencidas() {
        return itemsCuotasVencidas;
    }

    /**
     * @param itemsCuotasVencidas the itemsCuotasVencidas to set
     */
    public void setItemsCuotasVencidas(List<CalculoCuotaPagar> itemsCuotasVencidas) {
        this.itemsCuotasVencidas = itemsCuotasVencidas;
    }

    /**
     * @return the cuotaVencida
     */
    public CalculoCuotaPagar getCuotaVencida() {
        return cuotaVencida;
    }

    /**
     * @param cuotaVencida the cuotaVencida to set
     */
    public void setCuotaVencida(CalculoCuotaPagar cuotaVencida) {
        this.cuotaVencida = cuotaVencida;
    }

    /**
     * @return the numeroCuotaCalculo
     */
    public int getNumeroCuotaCalculo() {
        return numeroCuotaCalculo;
    }

    /**
     * @param numeroCuotaCalculo the numeroCuotaCalculo to set
     */
    public void setNumeroCuotaCalculo(int numeroCuotaCalculo) {
        this.numeroCuotaCalculo = numeroCuotaCalculo;
    }

    /**
     * @return the capitalCuotaCalculo
     */
    public BigDecimal getCapitalCuotaCalculo() {
        return capitalCuotaCalculo;
    }

    /**
     * @param capitalCuotaCalculo the capitalCuotaCalculo to set
     */
    public void setCapitalCuotaCalculo(BigDecimal capitalCuotaCalculo) {
        this.capitalCuotaCalculo = capitalCuotaCalculo;
    }

    /**
     * @return the diasMoraCalculo
     */
    public int getDiasMoraCalculo() {
        return diasMoraCalculo;
    }

    /**
     * @param diasMoraCalculo the diasMoraCalculo to set
     */
    public void setDiasMoraCalculo(int diasMoraCalculo) {
        this.diasMoraCalculo = diasMoraCalculo;
    }

    /**
     * @return the valorNotificacionActualCargado
     */
    public BigDecimal getValorNotificacionActualCargado() {
        return valorNotificacionActualCargado;
    }

    /**
     * @param valorNotificacionActualCargado the valorNotificacionActualCargado
     * to set
     */
    public void setValorNotificacionActualCargado(BigDecimal valorNotificacionActualCargado) {
        this.valorNotificacionActualCargado = valorNotificacionActualCargado;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}