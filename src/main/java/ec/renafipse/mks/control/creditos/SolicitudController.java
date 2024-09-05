package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.componentes.ListasUAFEComponente;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.DestinoEspecifico;
import ec.renafipse.mks.modelo.creditos.DestinoFinanciero;
import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import ec.renafipse.mks.modelo.creditos.GaranteCredito;
import ec.renafipse.mks.modelo.creditos.OrigenRecursos;
import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfip;
import ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfi;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMoneda;
import ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfipPK;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacionPK;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.creditos.SolicitudDetallePK;
import ec.renafipse.mks.modelo.creditos.SolicitudPagare;
import ec.renafipse.mks.modelo.creditos.SolicitudPagarePK;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK;
import ec.renafipse.mks.modelo.creditos.TasaInteresProCreMonIfi;
import ec.renafipse.mks.modelo.creditos.TipoCartera;
import ec.renafipse.mks.modelo.creditos.TipoGarantiaProCreMonIfi;
import ec.renafipse.mks.modelo.creditos.TipoRubroCredito;
import ec.renafipse.mks.modelo.creditos.TipoRubroProCreMonIfi;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfip;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfipPK;
import ec.renafipse.mks.modelo.seguros.ValorSeguro;
import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaActividadEconomica;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaConyugePK;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.modelo.socios.PersonaRelacionIfip;
import ec.renafipse.mks.modelo.socios.PersonaVinculado;
import ec.renafipse.mks.modelo.socios.SectorActividadEconomica;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioFlujoCaja;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
import ec.renafipse.mks.modelo.socios.SubsectorActividadEconomica;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.comunes.UbicacionGeograficaFacade;
import ec.renafipse.mks.negocio.creditos.ActividadEcoProMonIifipFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.DestinoEspecificoFacade;
import ec.renafipse.mks.negocio.creditos.DestinoFinProCreMonIfipFacade;
import ec.renafipse.mks.negocio.creditos.DestinoFinancieroFacade;
import ec.renafipse.mks.negocio.creditos.EstadoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.FabricaUsuarioPerfilFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.OrigenRecursosFacade;
import ec.renafipse.mks.negocio.creditos.ParametroGeneralCreditoIfipFacade;
import ec.renafipse.mks.negocio.creditos.PeriodicidadProMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaIfipFacade;
import ec.renafipse.mks.negocio.creditos.SolcitudPagareFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.creditos.TablaAmortizacionFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresProCreMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.TipoCarteraFacade;
import ec.renafipse.mks.negocio.creditos.TipoGarantiaProCreMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.TipoRubroCreditoFacade;
import ec.renafipse.mks.negocio.creditos.TipoRubroProCreMonIfiFacade;
import ec.renafipse.mks.negocio.ifips.ParametroServidorIfipFacade;
import ec.renafipse.mks.negocio.seguros.ValorSeguroFacade;
import ec.renafipse.mks.negocio.socios.ActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.PersonaActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoTipoPersonaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.PersonaRelacionIfipFacade;
import ec.renafipse.mks.negocio.socios.PersonaVinculadoFacade;
import ec.renafipse.mks.negocio.socios.SectorActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
import ec.renafipse.mks.negocio.socios.SubsectorActividadEconomicaFacade;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "solicitudController")
@ViewScoped
public class SolicitudController extends AbstractController<Solicitud> implements Serializable {

    @EJB
    private SolicitudFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private EstadoCreditoFacade ejbFacadeEstadoCredito;

    @EJB
    private PersonaNaturalFacade ejbFacadePersonaNatural;

    @EJB
    private PersonaConyugeFacade ejbFacadeConyugePersona;

    @EJB
    private SocioFlujoCajaFacade ejbFacadeSocioFlujoCaja;

    @EJB
    private SocioSituacionPatrimonialFacade ejbFacadeSocioSituacionPatrimonial;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private ProductoCreditoMonedaIfipFacade ejbFacadeProductoCreditoMonedaIfip;

    @EJB
    private PersonaActividadEconomicaFacade ejbFacadePersonaActividadEconomica;

    @EJB
    private PeriodicidadProMonIfiFacade ejbFacadePeriodicidadProMonIfi;

    @EJB
    private TasaInteresProCreMonIfiFacade ejbFacadeTasaInteresProCreMonIfi;

    @EJB
    private TipoRubroProCreMonIfiFacade ejbFacadeTipoRubroProCreMonIfi;

    @EJB
    private TipoGarantiaProCreMonIfiFacade ejbFacadeTipoGarantiaProCreMonIfi;

    @EJB
    private SectorActividadEconomicaFacade ejbFacadeSectorActividadEconomica;

    @EJB
    private SubsectorActividadEconomicaFacade ejbFacadeSubsectorActividadEconomica;

    @EJB
    private ActividadEconomicaFacade ejbFacadeActvidadEconomica;

    @EJB
    private ParametroGeneralCreditoIfipFacade ejbFacadeParametroGeneralCreditoIfip;

    @EJB
    private TipoCarteraFacade ejbFacadeTipoCartera;

    @EJB
    private TablaAmortizacionFacade ejbFacadeTablaAmortizacion;

    @EJB
    private ParametroGeneralCreditoIfipFacade ejbFacadeParametroGenCredIfip;

    @EJB
    private GaranteCreditoFacade ejbFacadeGaranteCredito;

    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeo;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private OrigenRecursosFacade ejbFacadeOrigenRecursos;

    @EJB
    private DestinoFinancieroFacade ejbFacadeDestinoFinanciero;

    @EJB
    private SolcitudPagareFacade ejbFacadeSolicitudPagare;

    @EJB
    private TipoRubroCreditoFacade ejbFacadeTipoRubroCredito;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    @EJB
    private ParametroServidorIfipFacade ejbFacadeParametroServidorIfip;

    @EJB
    private ValorSeguroFacade ejbFacadeValorSeguro;

    @EJB
    private MonedaFacade ejbFabaceMoneda;

    @EJB
    private ActividadEcoProMonIifipFacade ejbFacadeActividadEcoProMonIifip;

    @EJB
    private DestinoFinProCreMonIfipFacade ejbFacadeDestinoFinProCreMonIfipFacade;

    @EJB
    private ProductoCreditoTipoPersonaFacade ejbProductoCreditoTipoPersonaFacade;

    @EJB
    private PersonaFacade ejbPersonaFacade;

    @EJB
    private UsuarioFacade ejbUsuarioFacade;

    @EJB
    private FabricaUsuarioPerfilFacade ejbFacadeFabricaUsuarioPerfil;

    @EJB
    private PersonaVinculadoFacade ejbPersonaVinculadoFacade;

    @EJB
    private PersonaRelacionIfipFacade ejbPersonaRelacionIfipFacade;

    private LlamaSP llamaSP;

    private int tipoMensaje;
    private String modulo = "";

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String buscarPor;
    private String criterio;
    private String nombreSocio;
    private String nombreSocioBusqueda;
    private String nombreGaranteBusqueda;
    private String nombreConyugeBusqueda;
    private String mensajeCriterio;
    private String msg;
    private String identificacionConyuge;
    private RequestContext context;
    private String mensajeSolicitud;
    private Long codigoSocio;
    private String numeroCuenta;
    private String tipoCuenta;
    private String identificacionGarante;
    private String identificacionConyugeGar;

    private boolean busquedaSocioLista;
    private boolean deshabilitaBuscarSocioLista;
//    private boolean deshabilitaEditarSocio;
    private boolean deshabilitaSituacionEconomica;
    private boolean deshabilitaBuscarConyuge;
    private boolean deshabilitaCrearConyuge;
    private boolean deshabilitaEditarConyuge;
    private boolean deshabilitaEliminarConyuge;
    private boolean deshabilitaTextoIdentificacionConyuge;
    private boolean deshabilitaBotonDetalleRubrosCredito;
    private boolean conyugeCorrecto;

    private boolean deshabilitarBuscarConGar;
    private boolean deshabilitarGuardarConGar;
    private boolean deshabilitarEliminarConGar;
    private boolean deshabilitaTextoIdentificacionConGar;
    boolean esEdicion;
    private boolean margenVinculadoDisponible;
    private String tipoCuentaSel;
    private String totalRubroCuotaCadena;
    private Cuenta cuentaCombo;
    private Cuenta cuentaDebito;
    private Cuenta cuentaCredito;
    private Socio socioSel;
    private PersonaNatural garanteSel;
    private EstadoCredito estadoCreditoBusqueda;
    private Solicitud solicitud;
    private SolicitudDetalle solicitudDetalle;
    private Persona personaConyugeSocio;
    private PersonaNatural personaConyugeSel;
    private SocioSituacionPatrimonial socioSituacionPatrimonial;
    private SocioFlujoCaja socioFlujoCaja;
    private GaranteCredito garanteCredito;
    private Persona personaGarante;
    private Persona personaConyugeGarante;
    private TablaAmortizacion cuotaSeleccionada;
    private BigDecimal totalCapital;
    private BigDecimal totalInteres;
    private BigDecimal totalRubros;
    private BigDecimal totalCredito;
    private String totalCapitalCadena;
    private String totalInteresCadena;
    private String totalRubrosCadena;
    private String totalCreditoCadena;
    private TipoCartera tipoCartera;
    private GeneraReporte generaReporte;
    private UbicacionGeografica ubiGeoPai;
    private UbicacionGeografica ubiGeoPro;
    private UbicacionGeografica ubiGeoCiu;
    private UbicacionGeografica ubiGeoPar;
    private RubroTablaAmortizacion rubroTablaAmortizacion;

    private List<Socio> itemsSocio;
    private List<PersonaNatural> itemsGarantes;
    private List<Solicitud> itemsSolicitud;
    private List<EstadoCredito> itemsEstadoCreditoLista;
    private List<Periodicidad> itemsPeriodicidad;
    private List<ProductoCredito> itemsProductoCredito;
    private List<SubsectorActividadEconomica> itemsSubsectorActividadEconomica;
    private List<SectorActividadEconomica> itemsSectorActividadEconomica;
    private List<ActividadEconomica> itemsActividadEconomicaN1;
    private List<ActividadEconomica> itemsActividadEconomicaN2;
    private List<ActividadEconomica> itemsActividadEconomicaN3;
    private List<ActividadEconomica> itemsActividadEconomicaN4;
    private List<ActividadEconomica> itemsActividadEconomicaN5;
    private List<ActividadEconomica> itemsActividadEconomicaN6;
    private List<ActividadEconomica> itemsActividadEconomica;
    private List<TablaAmortizacion> itemsTablaAmortizacion;
    private List<SolicitudDetalle> itemsSolicitudDetalle;
    private List<GaranteCredito> itemsGaranteCredito;
    private List<PersonaConyuge> itemsPersonaConyuge;
    private List<PersonaNatural> itemsPersonaNatural;
    private List<Cuenta> itemsCuentas;
    private List<Cuenta> itemsCuentasAhorrosVista;
    private List<UbicacionGeografica> itemsUbiGeoPai;
    private List<UbicacionGeografica> itemsUbiGeoPro;
    private List<UbicacionGeografica> itemsUbiGeoCiu;
    private List<UbicacionGeografica> itemsUbiGeoPar;
    private List<OrigenRecursos> itemsOrigenRecursos;
    private List<DestinoFinanciero> itemsDestinoFinanciero;
    private List<TasaInteresProCreMonIfi> listaTasaCredito;
    private List<TipoRubroProCreMonIfi> listaRubros;
    private List<TipoRubroCredito> itemsTipoRubroCredito;
    private List<RubroTablaAmortizacion> itemsRubroTablaAmortizacion;
    private List<RubroTablaAmortizacion> itemsRubroTablaAmortizacionVer;
    Map<TipoRubroProCreMonIfi, Double> mapaTotalRubros;
    private List<String[]> itemsDetalleRubros;
    private List<TipoCartera> itemsTipoCartera;
    List<TipoGarantiaProCreMonIfi> itemsGarantiaCredito;
    private List<Persona> itemsPersonaVinculado;

    private Long codigoMonedaCertificado;
    private Long cuotasMaximas;
    private SectorActividadEconomica sectorActividadEconomica;
    private SubsectorActividadEconomica subsectorActividadEconomica;
    private ValorSeguro valorSeguro;
    private boolean esQuery = false;
    private double totalRubrosDetalle;
    private double coberturaGarante;
    private int garantesRequeridos;
    private char garantiasObligatorias;
    char tieneGarantias;
    int mesesMaxUltimaActualizacion;
    int garantiasMaximasPorPersona;
    double cuotaMaxima;
    private BigDecimal totalRubrosCredito;
    private BigDecimal totalRubrosCreditoConcecion;
    private BigDecimal montoMaximoVinculado;
    private ActividadEconomica actividadEconomicaReceptoraN1;
    private ActividadEconomica actividadEconomicaReceptoraN2;
    private ActividadEconomica actividadEconomicaReceptoraN3;
    private ActividadEconomica actividadEconomicaReceptoraN4;
    private ActividadEconomica actividadEconomicaReceptoraN5;
    private ActividadEconomica actividadEconomicaReceptoraN6;
    private ActividadEconomica actividadEconomicaReceptora;
    //Solicitud externa persona externa
    private boolean solicitudExterna;
    @EJB
    private PersonaFacade ejbFacadePersona;
    //Solicitud externa persona externa
    private String informacionActualizada;

    private Date fechaVencimiento;
    public int i;

    private boolean sigue;

    // Para el calculo de indicadores
    private BigDecimal cuotaMaximaCredito;

    private List<DestinoEspecifico> listaDestinoEspecifico;
    @EJB
    private DestinoEspecificoFacade ejbFacadeDestinoEspecifico;

    @PostConstruct
    public void init() {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            //Verificar el acceso a la opcion por perfil en Fabrica de Credito
            if (!ejbFacadeFabricaUsuarioPerfil.validaAccesoProcesoCredito(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), Long.parseLong("1"))) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                Sesion.redireccionaPagina(url);
                return;
            }
            informacionActualizada = "";
            llamaSP = new LlamaSP();
            this.buscarPor = "S";
            this.cambiaCriterio();
            this.setItemsSectorActividadEconomica(this.ejbFacadeSectorActividadEconomica.getItemsSectorEliminado('N'));
            //this.setItemsActividadEconomicaN1(ejbFacadeActvidadEconomica.getItemsActividadEconomicaEliminado(1, 'C', 'N'));
            conyugeCorrecto = true;
            margenVinculadoDisponible = true;
            // Obteniendo los dias que un socio puede tener desactualizada su informacion
            ParametroGeneralCreditoIfip parametroGeneralCreditoIfip = ejbFacadeParametroGeneralCreditoIfip.find(new ParametroGeneralCreditoIfipPK((long) 2, ActivacionUsuario.getCodigoIfip()));
            if (parametroGeneralCreditoIfip == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaMesesUltimaActualizacion"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);

            }

            if (parametroGeneralCreditoIfip.getValorNumerico() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaMesesUltimaActualizacion"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);

            }

            mesesMaxUltimaActualizacion = parametroGeneralCreditoIfip.getValorNumerico().intValue();

            // Obteniendo los numeros de garantias que puede hacer una persona
            parametroGeneralCreditoIfip = ejbFacadeParametroGeneralCreditoIfip.find(new ParametroGeneralCreditoIfipPK((long) 1, ActivacionUsuario.getCodigoIfip()));
            if (parametroGeneralCreditoIfip == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaGarantiasMaximasPorPersona"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            if (parametroGeneralCreditoIfip.getValorNumerico() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaGarantiasMaximasPorPersona"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);

            }

            this.garantiasMaximasPorPersona = parametroGeneralCreditoIfip.getValorNumerico().intValue();
            obtieneMargenValorVinculado();

        } catch (IOException e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"SolicitudController", "init", CapturaError.getErrorException(e)});
            }

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SolicitudController", "init", CapturaError.getErrorException(e)});
        }
        this.itemsTipoCartera = this.ejbFacadeTipoCartera.getItemsTipoCarteraEliminado('N');
        this.setItemsUbiGeoPai(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        setValorSeguro(getEjbFacadeValorSeguro().find(new Long(1)));
        this.setListaDestinoEspecifico(this.ejbFacadeDestinoEspecifico.getListaEliminado("N"));
    }

    //-----------------------------------------------------------------------------
    // -- LOGICA DE LA SOLICITUD
    public String sigueProcesoSocio(FlowEvent event) {

        String nuevo = event.getNewStep();
        String viejo = event.getOldStep();
        boolean correcto = true;

        if (event.getOldStep().equals("contratoTab") && event.getNewStep().equals("negociacionTab")) {
            if (solicitud.getSocio() != null) {
                ListasUAFEComponente listasUAFEComponente = new ListasUAFEComponente();
                listasUAFEComponente.setEjbFacadePersona(ejbFacadePersona);
                boolean estadoUAF = listasUAFEComponente.validaUAFPorIdentificacionONombres(solicitud.getSocio().getCodigoPersona().getNombreCompleto(), true);
                listasUAFEComponente.setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloCredito"));
                setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloCredito"));
                if (!estadoUAF) {
                    return "contratoTab";
                }
            }
        }
        if (event.getOldStep().equals("contratoTab") && event.getNewStep().equals("negociacionTab")) {
            validaCuentasSolicitud();
        }
        if (event.getOldStep().equals("contratoTab") && event.getNewStep().equals("negociacionTab")) {
            if (!margenVinculadoDisponible) {
                MuestraMensaje.addAdvertencia("Cupo maximo para creditos vinculados excedido.");
                return "contratoTab";
            }
        }

        if (this.msg != null) {
            MuestraMensaje.addAdvertencia(msg);
            if (event.getNewStep().equals("negociacionTab")) {
                return "contratoTab";
            }

        }

        //Validaciones contrato
        if (nuevo.equals("negociacionTab") && viejo.equals("contratoTab")) {
            if (solicitud.getSocio().getSocioPK().getCodigo() > 0) {
                if (!isSocioValido(solicitud.getSocio())) {
                    correcto = false;
                }
                if (this.msg != null) {
                    MuestraMensaje.addAdvertencia(msg);
                }
                buscaConyugeSocio();
                if (getMsg() != null) {
                    MuestraMensaje.addAdvertencia(msg);
                    correcto = false;
                }
                if (this.socioFlujoCaja == null) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoTieneFlujoCaja"));
                    correcto = false;
                }
                /*if (cuentaCredito == null) {
                 MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaCredito"));
                 correcto = false;
                 }
                 if (cuentaDebito == null) {
                 MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuentaDebito"));
                 correcto = false;
                 }*/
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseDatosParaContinuar"));
                correcto = false;
            }
        } else if (nuevo.equals("garantesTab") && viejo.equals("negociacionTab")) {
            // VALIDAR MONTOS POR EDAD //
            if (getSocioSel().getCodigoPersona().getCodigoTipoPersona().getCodigo() == 1) {
                if (!this.validaMonto(this.getSocioSel().getCodigoPersona().getCodigoTipoPersona().getCodigo(),
                        this.getFechaStringMediun(this.getSocioSel().getCodigoPersona().getPersonaNatural().getFechaNacimiento(), "/"),
                        this.getSolicitud().getMontoCredito().longValue())) {
                    correcto = false;
                    this.setMsg("No es posible crear una solicitud por el monto especificado para la persona seleccionada.");
                    MuestraMensaje.addError(this.getMsg());
                    return event.getOldStep();
                }
            }
            // VALIDAR CREDITOS DE GARANTIAS DPF
            if (!this.validaCreditoGarantiaDpf()) {
                correcto = false;
                MuestraMensaje.addError(this.getMsg());
                this.setMsg(null);
                return event.getOldStep();
            }
            // OBTENER EL MAXIMO NIVEL A INGRESAR POR PRODUCTO 
            int maximoNivel = this.validaActividadEconomicaSolicitud(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip());
            switch (maximoNivel) {
                case -1:
                    correcto = false;
                    this.setMsg("Error al verificar el nivel de la actividad economica.");
                    MuestraMensaje.addError(this.getMsg());
                    return event.getOldStep();
                case 1:
                    if (this.actividadEconomicaReceptoraN1 == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 2:
                    if (this.actividadEconomicaReceptoraN2 == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 3:
                    if (this.actividadEconomicaReceptoraN3 == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 4:
                    if (this.actividadEconomicaReceptoraN4 == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 5:
                    if (this.actividadEconomicaReceptoraN5 == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 6:
                    if (this.actividadEconomicaReceptoraN6 == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActividadEconomica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
            }
            int maximaJerarquia = this.validaUbicacionGeograficaSolicitud(this.getUbiGeoPai().getCodigo());
            switch (maximaJerarquia) {
                case -1:
                    correcto = false;
                    this.setMsg("Error al verificar el nivel de la Ubicacion Geografica.");
                    MuestraMensaje.addError(this.getMsg());
                case 1:
                    if (this.ubiGeoPai == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 2:
                    if (this.ubiGeoPro == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 3:
                    if (this.ubiGeoCiu == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
                case 4:
                    if (this.ubiGeoPar == null) {
                        correcto = false;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaUbicacionGeografica"));
                        MuestraMensaje.addError(this.getMsg());
                        return event.getOldStep();
                    } else {
                        correcto = true;
                        this.setMsg(null);
                    }
                    break;
            }
            if (itemsTablaAmortizacion != null) {
                if (itemsTablaAmortizacion.isEmpty()) {
                    correcto = false;
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseDatosParaContinuar"));
                }
                if (this.cuotaMaxima > this.socioFlujoCaja.getCobertura().doubleValue()) {
                    correcto = false;
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CoberturaNoPermiteCredito"));
                }
                if (this.getUbiGeoPai() == null) {
                    correcto = false;
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseUbicacionGeoCredito"));
                }
                if (this.ubicaActividadEconomica() == null) {
                    correcto = false;
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseActividadEconomicaCredito"));
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseDatosParaContinuar"));
                correcto = false;
            }
            if (tieneGarantias == 'S' && this.getGarantesRequeridos() == 0) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoRequiereGarantias"));
                correcto = false;
            }
            if (tieneGarantias == 'N' && correcto) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoNoNecesitaGarantes"));
                return "confirmacionTab";
            }
            //Validar indicadores para el proceso de solicitud de credito
            String mensajeIndicador = validaIndicadoresCredito();
            if (mensajeIndicador != null) {
                MuestraMensaje.addError(mensajeIndicador);
                correcto = false;
            }
        } else if (nuevo.equals("garantesTab") && viejo.equals("confirmacionTab")) {
            if (tieneGarantias == 'N') {
                return "negociacionTab";
            }
        } else if (nuevo.equals("confirmacionTab") && viejo.equals("garantesTab")) {
            if (itemsGaranteCredito.size() < this.getMinimoGarantes()) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseTodosLosGarantes"));
                correcto = false;
            } else if (itemsGaranteCredito.size() > 0) {
                double cobertura = 0;
                for (int i = 0; i < itemsGaranteCredito.size(); i++) {

                    if (itemsGaranteCredito.get(i).getCoberturaDada().doubleValue() <= 0) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CoberturaMayorDeCero"));
                        correcto = false;
                        break;
                    }

                    cobertura += itemsGaranteCredito.get(i).getCoberturaDada().doubleValue();
                }
                if (cobertura > this.solicitud.getMontoCredito().doubleValue()) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CoberturaExcedeMonto"));
                    correcto = false;
                }
            }
        } else {
            ////System.out.println(event.getOldStep() + "-->" + nuevo);
            return nuevo;
        }
        if (correcto) {
            return nuevo;
        } else {
            return event.getOldStep();
        }

    }

    /**
     * *
     * Metodo para validar los indicadores para proseguir con la solicitud de
     * credito
     *
     * @return
     */
    public String validaIndicadoresCredito() {
        String mensaje = ejbFacade.validaIndicadorSolicitud(getSocioSel().getCodigoPersona().getCodigo(), solicitud.getMontoCredito(), solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo(), solicitud.getMontoCredito(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo());
        return mensaje;
    }

    /**
     * *
     * VALIDA LOS CREDITOS QUE VAN A TENER GARANTIA DPF
     */
    public boolean validaCreditoGarantiaDpf() {
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_valida_garantia_dpf");
            llamaSP.setNumeroParametros(8);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_socio", this.getCodigoSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", this.solicitud.getSolicitudPK().getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_monto_credito", this.solicitud.getMontoCredito()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_periodicidad", this.solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_cuotas", this.solicitud.getNumeroCuotas()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_producto", this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_vencimiento", new java.sql.Timestamp(fechaVencimiento.getTime())});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje", Types.VARCHAR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getError() == null) {
                    String mensaje = null;
                    if (llamaSP.getListResultado().get(0) != null) {
                        mensaje = llamaSP.getListResultado().get(0).toString();
                    }
                    if (mensaje != null) {
                        this.setMsg(mensaje);
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    MuestraMensaje.addError(llamaSP.getError());
                    return false;
                }
            } else {
                MuestraMensaje.addError(llamaSP.getError());
                return false;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"SolicitudController", "validaCreditoGarantiaDpf", llamaSP.getErroSql()});
            return false;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     * OBTIENE LAS SOLICITUDES DE CREDITO DE UN SOCIO O POR EL NUMERO DE
     * CREDITO, DE ACUERDO A LA BUSQUEDA.
     */
    public void obtieneSolicitudes() {
        try {
            //Solicitud externa persona externa
            if (this.criterio == null) {
                return;
            }
            if (this.criterio.length() <= 0) {
                return;
            }
            //Solicitud externa persona externa
            this.itemsSolicitud = null;
            this.nombreSocio = null;
            if (this.buscarPor.equals("S")) {
                this.buscaSocio(true);
            } else {
                if (this.buscarPor.equals("N")) {//Solicitud externa persona externa
                    this.itemsSolicitud = this.ejbFacade.getItemsNumeroCodigoIfip(Long.valueOf(this.criterio), ActivacionUsuario.getCodigoIfip());
                    if (!this.itemsSolicitud.isEmpty()) {
                        this.nombreSocio = this.itemsSolicitud.get(0).getSocio().getCodigoPersona().getNombreCompleto();
                        // Validando el Socio

                        this.codigoSocio = itemsSolicitud.get(0).getSocio().getSocioPK().getCodigo();
                        ////System.out.println("SOCIO VALIDO" + this.isSocioValido(this.itemsSolicitud.get(0).getSocio()));

                        if (!this.isSocioValido(this.itemsSolicitud.get(0).getSocio())) {
                            this.itemsSolicitud = null;

                        } else {
                            this.solicitud = this.itemsSolicitud.get(0);

                        }
                    } else {
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoNoExiste"));
                        MuestraMensaje.addAdvertencia(msg);
                    }
                } else {//Solicitud externa persona externa
                    if (this.buscarPor.equals("E")) {
                        this.itemsSolicitud = this.ejbFacade.getItemsIdentificacionPersonaExterna(this.criterio.toString(), ActivacionUsuario.getCodigoIfip());
                        if (!this.itemsSolicitud.isEmpty()) {
                            preparaSimulacionSolicitudExternaPersonaExterna(this.itemsSolicitud.get(0));
                        } else {
                            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoNoExiste"));
                            MuestraMensaje.addAdvertencia(msg);
                        }
                    }
                }
                //Solicitud externa persona externa
            }
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SolicitudController", "obtieneSolicitudes", CapturaError.getErrorException(e)});
        }
    }

    /**
     * @param solicitudSeleccionada
     *
     */
    public void preparaSimulacionSolicitudExternaPersonaExterna(Solicitud solicitudSeleccionada) {
        try {
            if (solicitudExterna) {
                this.solicitud = solicitudSeleccionada;
                Persona persona = ejbFacadePersona.getPersona(this.criterio.toString());
                this.nombreSocio = persona.getNombreCompleto();
                this.setTipoCartera(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigoTipoCartera());
                this.setItemsProductoCredito(this.ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditobyIfipMoneda(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), tipoCartera.getCodigo(), 'N'));
                this.listaTasaCredito = this.ejbFacadeTasaInteresProCreMonIfi.getListaTasaInteresProCreMonIfi(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N');
                this.setItemsPeriodicidad(this.ejbFacadePeriodicidadProMonIfi.getPeriodicidadProMonIfiElim(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N', 'S'));
                this.setActividadEconomicaReceptoraN1(new ActividadEconomica());
                autoGenerarTabla();
            }
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SolicitudController", "preparaSimulacionSolicitudExternaPersonaExterna", CapturaError.getErrorException(e)});
        }
    }

    public void imprimirSolicitud(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
        if (this.solicitud.getCodigoEstado() != 1) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeImprimirSolicitud"));
            return;
        }
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        String directoriSubRepInfFin = "/financiero/creditos/solicitud/reporte/";
        ////System.out.println("Imprime la Solicitud");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("SUBREPORT_DIR", externalContext.getRealPath(directoriSubRepInfFin + "informacionSocio.jasper"));
        String path = obtienePathReporte("tablaAmortizacion");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        } else {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, path, "tablaAmortizacion"));
        }
        path = obtienePathReporte("informacionSocio");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, "/financiero/creditos/solicitud/reporte/", "informacionSocio"));
        } else {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, path, "informacionSocio"));
        }
        if (this.solicitud.getSocio().getCodigoPersona().getCodigoTipoPersona().getCodigo() == 1) {
            nombreReporte = "solicitudCredito";
        } else {
            nombreReporte = "solicitudCreditoJuridica";
        }
        String pathReporte = obtienePathReporte(nombreReporte);
        if (pathReporte == null) {
            pathReporte = "/financiero/creditos/solicitud/reporte/";
        }
        getGeneraReporte().exporta(pathReporte, nombreReporte,
                nombreReporte + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
    }
    /***
     * Metodo para reimprimir la solicitud
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void reImprimirSolicitud(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        try{
            if (solicitud != null){
                String nombreReporte;
                if ( solicitud.getCodigoEstado() == 5 || solicitud.getCodigoEstado() == 7 || solicitud.getCodigoEstado() == 8 ) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoReImprime"));            
                }else{
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
                    String directoriSubRepInfFin = "/financiero/creditos/solicitud/reporte/";
                    ////System.out.println("Imprime la Solicitud");
                    setGeneraReporte(new GeneraReporte());
                    getGeneraReporte().setParametros(new HashMap<String, Object>());
                    getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
                    getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
                    getGeneraReporte().getParametros().put("SUBREPORT_DIR", externalContext.getRealPath(directoriSubRepInfFin + "informacionSocio.jasper"));
                    String path = obtienePathReporte("tablaAmortizacion");
                    if (path == null) {
                        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
                    } else {
                        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, path, "tablaAmortizacion"));
                    }
                    path = obtienePathReporte("informacionSocio");
                    if (path == null) {
                        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, "/financiero/creditos/solicitud/reporte/", "informacionSocio"));
                    } else {
                        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, path, "informacionSocio"));
                    }
                    if (this.solicitud.getSocio().getCodigoPersona().getCodigoTipoPersona().getCodigo() == 1) {
                        nombreReporte = "solicitudCredito";
                    } else {
                        nombreReporte = "solicitudCreditoJuridica";
                    }
                    String pathReporte = obtienePathReporte(nombreReporte);
                    if (pathReporte == null) {
                        pathReporte = "/financiero/creditos/solicitud/reporte/";
                    }
                    getGeneraReporte().exporta(pathReporte, nombreReporte,
                            nombreReporte + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                            "PDF", externalContext, facesContext);
                }
            }
        }catch(Exception e){
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SolicitudController", "reImprimirSolicitud", CapturaError.getErrorException(e)});
        }
    }

    /**
     * LIMPIA LOS DATOS DE LA SOLICITUD.
     */
    public void limpiaDatosSocioSolicitud() {
        try {
            this.identificacionConyuge = null;

            this.solicitud.setSocio(new Socio());
            this.solicitud.getSocio().setSocioPK(new SocioPK());

            this.setPersonaConyugeSocio(new Persona());
//            this.deshabilitaEditarSocio = true;
            this.deshabilitaSituacionEconomica = true;
            this.deshabilitaCrearConyuge = true;
            this.deshabilitaEditarConyuge = true;
            this.deshabilitaBuscarConyuge = true;
            this.deshabilitaEliminarConyuge = true;
            this.deshabilitaTextoIdentificacionConyuge = true;
            this.itemsSolicitudDetalle = new ArrayList<SolicitudDetalle>();

            this.deshabilitarEliminarConGar = true;
            this.deshabilitarBuscarConGar = true;
            this.deshabilitarGuardarConGar = true;

            this.socioFlujoCaja = null;
            this.socioSituacionPatrimonial = null;

            this.itemsGaranteCredito = new ArrayList<GaranteCredito>();
            this.itemsCuentasAhorrosVista = null;
            this.cuentaCombo = null;
            this.tipoCuentaSel = null;

            this.itemsCuentas = new ArrayList<Cuenta>();
            this.cuentaCredito = null;
            this.cuentaDebito = null;

        } catch (Exception e) {
        }
    }

    /**
     * INICIALIZA LAS VARIABLES PARA EDITAR LA SOLICITUD.
     */
    public void preparaEdicionSolicitud() {

        context = RequestContext.getCurrentInstance();
        if (this.solicitud.getCodigoEstado() == 1) {

            esEdicion = true;
            this.setItemsOrigenRecursos(this.ejbFacadeOrigenRecursos.getItemsEliminado('N'));
            this.setItemsDestinoFinanciero(this.ejbFacadeDestinoFinanciero.getItemsEliminado('N'));
            Solicitud aux = ejbFacade.find(solicitud.getSolicitudPK());
            solicitud.setTipoTabla(' ');
            this.codigoSocio = solicitud.getSocio().getSocioPK().getCodigo();
            this.limpiaDatosSocioSolicitud();
            this.buscaSocio(false);
            this.itemsSolicitudDetalle = ejbFacadeSolicitudDetalle.getItemSolicitudCreditoNumeroIfip(solicitud.getSolicitudPK().getNumero(), solicitud.getSolicitudPK().getCodigoIfip());
//            this.subsectorActividadEconomica = this.solicitud.getActividadEconomica().getCodigoSubsector();
//            this.sectorActividadEconomica = this.subsectorActividadEconomica.getCodigoSector();
            //            this.sectorActividadEconomica = aux.getActividadEconomica().getCodigoSubsector().getCodigoSector();
//            this.cambiaSector();
//            this.subsectorActividadEconomica = aux.getActividadEconomica().getCodigoSubsector();
//            this.cambiaSubSector();
//            solicitud.setActividadEconomica(aux.getActividadEconomica());
            ///Muestra Actividad Economica
            cargaActividadEconomicaSolicitud();
            this.tipoCartera = this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigoTipoCartera();
            this.cambiaSeleccionMoneda();
            ///Muestra Actividad Economica            
            cargaActividadEconomicaSolicitud();
            this.solicitud.setProductoCreditoMonedaIfip(aux.getProductoCreditoMonedaIfip());
            cambiaSeleccionProdCred();
            //this.cambiaSeleccionProdCred();            
            this.solicitud.setMontoCredito(aux.getMontoCredito());
            this.solicitud.setTasa(aux.getTasa());
            this.solicitud.setPeriodicidadProMonIfi(aux.getPeriodicidadProMonIfi());
            this.seleccionaTasaPorMonto();
            this.solicitud.setTasa(aux.getTasa());
            this.solicitud.setCodigoTasa(aux.getCodigoTasa());
            this.solicitud.setMontoCredito(aux.getMontoCredito());
            this.seleccionaCuotasMaximas();
            this.solicitud.setNumeroCuotas(aux.getNumeroCuotas());
            solicitud.setTipoTabla(aux.getTipoTabla());

            deshabilitaBotonDetalleRubrosCredito = true;

            // GEnerando la Tabla de Amortizacion
            this.autoGenerarTabla();

            double totCapital = 0, totInteres = 0, totRubros = 0, totCredito = 0;

            for (TablaAmortizacion item : itemsTablaAmortizacion) {
                totCapital += item.getCapital().doubleValue();
                totInteres += item.getInteres().doubleValue();
                totRubros += item.getRubros().doubleValue();
                totCredito += item.getTotal().doubleValue();
            }
            this.totalCapitalCadena = formatoValor(new BigDecimal(totCapital)).toString();
            this.totalRubrosCadena = formatoValor(new BigDecimal(totRubros)).toString();
            this.totalCreditoCadena = formatoValor(new BigDecimal(totCredito)).toString();
            this.totalInteresCadena = formatoValor(new BigDecimal(totInteres)).toString();
            itemsGaranteCredito = ejbFacadeGaranteCredito.getItemsGaranteCreditoIfipVigenteEliminado(solicitud.getSolicitudPK().getNumero(), solicitud.getSolicitudPK().getCodigoIfip(), 'S', 'N');
            UbicacionGeografica dest = ejbFacadeUbiGeo.find(this.solicitud.getCodigoUbiGeo());
            Long n = dest.getCodigoJerarquia().getCodigo();
            UbicacionGeografica pais = null, prov = null, ciu = null, par = null;
            for (int i = 0; i < n; i++) {
                if (dest.getCodigoJerarquia().getCodigo() == 1) {
                    pais = dest;
                }
                if (dest.getCodigoJerarquia().getCodigo() == 2) {
                    prov = dest;
                }
                if (dest.getCodigoJerarquia().getCodigo() == 3) {
                    ciu = dest;
                }
                if (dest.getCodigoJerarquia().getCodigo() == 4) {
                    par = dest;
                }
                dest = dest.getCodigoUbiGeoPadre();
            }
            if (pais != null) {
                this.ubiGeoPai = pais;
                this.cambiaUbiGeoPai();
            }
            if (prov != null) {
                this.ubiGeoPro = prov;
                this.cambiaUbiGeoPro();
            }
            if (ciu != null) {
                this.ubiGeoCiu = ciu;
                this.cambiaUbiGeoCiu();
            }
            if (par != null) {
                this.ubiGeoPar = par;
            }

            //this.itemsTipoRubroCredito = this.ejbFacadeTipoRubroCredito.getItemsNumeroCreditoCodigoIfip(solicitud.getSolicitudPK().getNumero(), solicitud.getSolicitudPK().getCodigoIfip());
            validaInformacionActualizada();
            cambiaSeleccionTipoCartera();
            context.execute("procesandoDlg.hide()");
            context.execute("SolicitudNuevaDialog.show()");
        } else {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeEditarSolicitudDiferenteSolicitada"));
            context.execute("procesandoDlg.hide()");
            return;
        }

    }

    /**
     *
     */
    public void validaInformacionActualizada() {
        if (!isSocioValido(this.solicitud.getSocio())) {
            setInformacionActualizada(this.getMsg().toUpperCase());
        } else {
            setInformacionActualizada("");
        }
    }

    /*
     Muestra la actividad Economica de la solicitud para la edision, incluida las actividades de nivel superios
     */
    private void cargaActividadEconomicaSolicitud() {
        this.actividadEconomicaReceptora = solicitud.getActividadEconomica();
        if (actividadEconomicaReceptora.getCodigoNivel().getCodigo().longValue() == 6) {
            actividadEconomicaReceptoraN6 = actividadEconomicaReceptora;
            actividadEconomicaReceptora = actividadEconomicaReceptora.getCodigoActEcoPad();
            this.setItemsActividadEconomicaN6(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(6, actividadEconomicaReceptora.getCodigo(), 'C', 'N'));
        }
        if (actividadEconomicaReceptora.getCodigoNivel().getCodigo().longValue() == 5) {
            actividadEconomicaReceptoraN5 = actividadEconomicaReceptora;
            actividadEconomicaReceptora = actividadEconomicaReceptora.getCodigoActEcoPad();
            this.setItemsActividadEconomicaN5(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(5, actividadEconomicaReceptora.getCodigo(), 'C', 'N'));
        }
        if (actividadEconomicaReceptora.getCodigoNivel().getCodigo().longValue() == 4) {
            actividadEconomicaReceptoraN4 = actividadEconomicaReceptora;
            actividadEconomicaReceptora = actividadEconomicaReceptora.getCodigoActEcoPad();
            this.setItemsActividadEconomicaN4(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(4, actividadEconomicaReceptora.getCodigo(), 'C', 'N'));
        }
        if (actividadEconomicaReceptora.getCodigoNivel().getCodigo().longValue() == 3) {
            actividadEconomicaReceptoraN3 = actividadEconomicaReceptora;
            actividadEconomicaReceptora = actividadEconomicaReceptora.getCodigoActEcoPad();
            this.setItemsActividadEconomicaN3(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(3, actividadEconomicaReceptora.getCodigo(), 'C', 'N'));
        }
        if (actividadEconomicaReceptora.getCodigoNivel().getCodigo().longValue() == 2) {
            actividadEconomicaReceptoraN2 = actividadEconomicaReceptora;
            actividadEconomicaReceptora = actividadEconomicaReceptora.getCodigoActEcoPad();
            this.setItemsActividadEconomicaN2(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(2, actividadEconomicaReceptora.getCodigo(), 'C', 'N'));
        }
        if (actividadEconomicaReceptora.getCodigoNivel().getCodigo().longValue() == 1) {
            actividadEconomicaReceptoraN1 = actividadEconomicaReceptora;
        }
    }

    /**
     * INICIALIZA LAS VARIABLES PARA CREAR LA SOLICITUD.
     */
    public void preparaSolicitud() {

        this.setItemsOrigenRecursos(this.ejbFacadeOrigenRecursos.getItemsEliminado('N'));
        //this.setItemsDestinoFinanciero(this.ejbFacadeDestinoFinanciero.getItemsEliminado('N'));
        this.solicitud = new Solicitud(Long.parseLong("0"), ActivacionUsuario.getCodigoIfip());
        this.solicitudDetalle = new SolicitudDetalle(new SolicitudDetallePK(Long.parseLong("0"), ActivacionUsuario.getCodigoIfip()));

        this.solicitud.setProductoCreditoMonedaIfip(new ProductoCreditoMonedaIfip());
        this.solicitud.getProductoCreditoMonedaIfip().setProductoCreditoMoneda(new ProductoCreditoMoneda());
        this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().setMoneda(new Moneda());
        this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().setProductoCredito(new ProductoCredito());

        this.solicitud.setPeriodicidadProMonIfi(new PeriodicidadProMonIfi());
        this.solicitud.getPeriodicidadProMonIfi().setPeriodicidad(null);

        itemsSolicitudDetalle = new ArrayList<>();
        this.itemsSolicitudDetalle.add(solicitudDetalle);
        this.itemsCuentas = null;
        this.cuentaCombo = null;
        this.itemsCuentasAhorrosVista = null;
        this.setTipoCuentaSel(null);

        this.limpiaDatosSocioSolicitud();

        this.codigoSocio = null;
        this.tipoCartera = null;
        this.sectorActividadEconomica = null;
        this.subsectorActividadEconomica = null;
        this.ubiGeoCiu = null;
        this.ubiGeoPai = null;
        this.ubiGeoPar = null;
        this.ubiGeoPro = null;
        this.sectorActividadEconomica = null;
        this.subsectorActividadEconomica = null;
        this.setMsg(null);
        this.itemsRubroTablaAmortizacion = null;
        this.itemsRubroTablaAmortizacionVer = null;
        this.totalRubrosCredito = BigDecimal.ZERO;
        deshabilitaBotonDetalleRubrosCredito = true;
        itemsPersonaVinculado = new ArrayList<Persona>();
        this.setUbiGeoPai(this.ejbFacadeUbiGeo.getUbicacionGeografica(new Long(1)));
        cambiaUbiGeoPai();
    }

    /**
     * PREPARA LA CREACION DE UNA SOLICITUD DE CREDITO.
     *
     * @param event
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void creaSolicitud(ActionEvent event) throws InstantiationException, IllegalAccessException {
        //Solicitud externa persona externa
        if (!this.solicitudExterna) {
            this.preparaSolicitud();
            esEdicion = false;
            this.itemsTablaAmortizacion = new ArrayList<TablaAmortizacion>();
            this.totalCapitalCadena = "0.00";
            this.totalInteresCadena = "0.00";
            this.totalRubrosCadena = "0.00";
            this.totalCreditoCadena = "0.00";
            this.itemsTipoRubroCredito = null;
        }
        informacionActualizada = "";
    }

    public void asignaActividadProvicional() {
        this.setActividadEconomicaReceptoraN1(new ActividadEconomica());
    }

    public void cierraCreacionSolicitud() {
        this.preparaSolicitud();
        ////System.out.println("Cerrando Dialogo");
        context = RequestContext.getCurrentInstance();
        List<String> listaComponentesActualizar = new ArrayList<String>();
        listaComponentesActualizar.add("SolicitudNuevaFor");
        context.update(listaComponentesActualizar);
    }

    //---------------------------------------------------------------------------
    //-- CONYUGE DEL SOCIO
    public void buscaConyugeSocio() {
        this.msg = null;
        Persona personaSocio = this.solicitud.getSocio().getCodigoPersona();
        Long codigoPersona = personaSocio.getCodigo();
        if (String.valueOf(personaSocio.getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
            PersonaNatural personaNatural = this.ejbFacadePersonaNatural.find(codigoPersona);
            ////System.out.println("personaNatural " + personaNatural + " estado civil " + personaNatural.getCodigoEstadoCivil());
            // Verificando si el estado civil es  casado para recuperar el conyue
            if (personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {
                // Asignando los conyuges registrados para el socio
                this.setItemsPersonaConyuge(ejbFacadeConyugePersona.getItemsCodigoPersona(codigoPersona));

                // Buscando el conyuge actual                
                List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(codigoPersona, 'N');

                // Si existe un conyuge definido para el socio                
                if (listaPersonaConyuge.size() == 1) {
                    // Asignando la persona del Conyuge
                    this.personaConyugeSocio = listaPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona();
                    this.identificacionConyuge = this.personaConyugeSocio.getIdentificacion();

                    this.deshabilitaEliminarConyuge = false;

                    // si no existe ningun conyuge asignado
                } else if (listaPersonaConyuge.isEmpty()) {
                    List<PersonaConyuge> conyuges = this.ejbFacadeConyugePersona.getItemsCodigoPersonaConyugeElminado(this.solicitud.getSocio().getCodigoPersona().getPersonaNatural().getCodigoPersona(), 'N');
                    if (conyuges.size() > 0) {
                        PersonaConyuge conyuge = conyuges.get(0);
                        PersonaConyuge nuevo = new PersonaConyuge();
                        nuevo.setPersonaConyugePK(new PersonaConyugePK(conyuge.getPersonaConyugePK().getCodigoPersonaConyuge(), conyuge.getPersonaConyugePK().getCodigoPersona()));
                        nuevo.setFirma('S');
                        nuevo.setEliminado('N');
                        nuevo.setFechaRegistro(new Date());
                        nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                        this.ejbFacadeConyugePersona.edit(nuevo);
                        this.personaConyugeSocio = conyuge.getPersonaNatural().getPersona();
                        this.identificacionConyuge = this.personaConyugeSocio.getIdentificacion();
                        this.deshabilitaEliminarConyuge = false;
                    } else {
                        this.personaConyugeSocio = null;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoTieneConyuge"));
                        this.deshabilitaCrearConyuge = false;
                        this.deshabilitaBuscarConyuge = false;
                        this.deshabilitaTextoIdentificacionConyuge = false;
                    }

                    // Si el socio tiene mas de un conyuge vigente asignados
                } else {
                    this.personaConyugeSocio = null;
                    this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioTieneMasDeUnConyuge"));
                }

            }
        }

    }

    //--------------------------------------------------------------------------------
    //-- BUSQUEDA DE SOCIOS
    /**
     * VALIDA SI EL SOCIO PUEDE REALIZAR ACCIONES SOBRE LA SOLICITUD DE CREDITO.
     *
     * @param socio
     * @return True = Socio Valido FALSE= Socio Invalido
     */
    public boolean isSocioValido(Socio socio) {

        this.socioFlujoCaja = ejbFacadeSocioFlujoCaja.getSocioFlujoCajaCodigoSocioIfip(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip());

        if (socioFlujoCaja != null) {
            this.socioFlujoCaja.setCobertura(formatoValor(this.socioFlujoCaja.getCobertura()));
        }
        this.socioSituacionPatrimonial = ejbFacadeSocioSituacionPatrimonial.getSocioSocioSituacionPatrimonialCodigoSocioIfip(socio.getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip());
        if (socioSituacionPatrimonial != null) {
            this.socioSituacionPatrimonial.setTotalPatrimonio(formatoValor(this.socioSituacionPatrimonial.getTotalPatrimonio()));
        }
        this.msg = null;
        if (!this.datosActualizados(socio)) {
            return false;
        }
        if (!this.tieneCertAportaciones()) {
            return false;
        }
        if (socio.getCodigoPersona().getFechaCaducidadIdentificacion() == null) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioSinCaducidad"));
            return false;
        }
        if (Validaciones.validaFechaMenorHoy(socio.getCodigoPersona().getFechaCaducidadIdentificacion())) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioCaducada"));
            return false;
        }
        if (socio.getCodigoIfipAgencia().getCodigo().compareTo(ActivacionUsuario.getCodigoIfipAgencia()) == 0) {
            if (String.valueOf(socio.getCodigoEstadoSocio().getIndicaActivo()).equals("S")) {
                informacionActualizada = "";
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

    public boolean datosActualizados(Socio socio) {
        boolean datosActualizados = true;
        int rangoEnDias = this.mesesMaxUltimaActualizacion * 30;
        if (this.socioSituacionPatrimonial == null) {
            datosActualizados = false;
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionPatrimonilaSocioDesactualizada"));
        } else {
            long diferenciaEn_ms = new Date().getTime() - this.socioSituacionPatrimonial.getFechaActualizacion().getTime();
            long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
            if (dias > rangoEnDias) {
                datosActualizados = false;
                this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionPatrimonilaSocioDesactualizada"));
            }
        }

        long diferenciaEn_ms = new Date().getTime() - socio.getFechaActualizacion().getTime();
        long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        if (dias > rangoEnDias) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionSocioDesactualizada"));
            datosActualizados = false;
        }
        return datosActualizados;
    }

    public boolean tieneCertAportaciones() {
        List<Cuenta> certificadosAport = this.ejbFacadeCuenta.getItemsCertAprPuedeReactivar(codigoSocio, ActivacionUsuario.getCodigoIfip(), 'S', Long.parseLong("1"), new BigDecimal("0"));
        ////System.out.println("socio " + codigoSocio);
        ////System.out.println("ifip " + ActivacionUsuario.getCodigoIfip());
        if (certificadosAport.isEmpty()) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoTieneCertificadoAportaciones"));
            return false;
        } else {
            codigoMonedaCertificado = certificadosAport.get(0).getCodigoMoneda();
            return true;
        }
    }

    private void validaCuentasSolicitud() {
        this.msg = null;
        ////System.out.println("Valida Cuentas de la Solicitud");
        if (this.itemsSolicitudDetalle.isEmpty()) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
            return;
        }

        //if ((this.itemsSolicitudDetalle.get(0).getCuentaAcreditada() == null) ||  (this.itemsSolicitudDetalle.get(0).getCuentaDebito())) {
        if (this.itemsSolicitudDetalle.get(0).getCuentaAcreditada() == null) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
            return;
        }

        if (this.itemsSolicitudDetalle.get(0).getCuentaDebito() == null) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AgregueCuentasSolicitudCredito"));
            return;
        }
    }

    public void datosSocioActualizados(Socio socio) {
    }

    /**
     ** PREPARA LA BUSQUEDA DE LOS SOCIOS PARA CONSULTAR LOS CREDITOS.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void preparaBusquedaSocioLista(ActionEvent actionEvent) throws IOException {
        this.nombreSocioBusqueda = null;
        this.itemsSocio = null;
        this.nombreSocio = null;
        this.criterio = null;
        this.busquedaSocioLista = true;
    }

    /**
     * PREPARA LA BUSQUEDA DE LOS SOCIOS CUANDO SE CREA UNA SOLICITUD.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void preparaBusquedaSocioSolicitud(ActionEvent actionEvent) throws IOException {
        //////System.out.println("Busca Socios Solicitud");
        this.codigoSocio = null;
        this.itemsSocio = null;
        this.solicitud.setSocio(null);
        this.nombreSocioBusqueda = null;
        this.itemsSocio = null;
        this.busquedaSocioLista = false;
        this.limpiaDatosSocioSolicitud();
        //////System.out.println("Busca Socios Solicitud Fin");

    }

    public void prepareBusquedaGarante() {
        this.garanteSel = null;
        this.itemsGarantes = null;
        this.nombreGaranteBusqueda = null;
    }

    public void preparaBusquedaConyuge(ActionEvent actionEvent) {
        this.itemsPersonaNatural = null;
        this.personaConyugeSel = null;
        this.nombreConyugeBusqueda = null;

    }

    public void preparaBusquedaConyugeGar(ActionEvent actionEvent) {
        this.itemsPersonaNatural = null;
        this.personaConyugeGarante = new Persona();
        this.nombreConyugeBusqueda = null;

    }

    public void guardaConyuge(ActionEvent actionEvent) {
        ////System.out.println("Guarda Conyuge " + personaConyugeSel);
        this.revisaEstadoCivil(this.personaConyugeSel, this.getSolicitud().getSocio().getCodigoPersona().getPersonaNatural());
        ////System.out.println("valida Guarda Conyuge " + personaConyugeSel + "\nvalido" + conyugeCorrecto);
        if (conyugeCorrecto) {
            PersonaConyuge nuevo = new PersonaConyuge();
            nuevo.setPersonaConyugePK(new PersonaConyugePK(this.getSolicitud().getSocio().getCodigoPersona().getPersonaNatural().getCodigoPersona(), this.personaConyugeSel.getCodigoPersona()));
            nuevo.setEliminado('N');
            nuevo.setFechaRegistro(new Date());
            nuevo.setFirma('S');
            nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.ejbFacadeConyugePersona.edit(nuevo);
            nuevo = new PersonaConyuge();
            nuevo.setPersonaConyugePK(new PersonaConyugePK(this.personaConyugeSel.getCodigoPersona(), this.getSolicitud().getSocio().getCodigoPersona().getPersonaNatural().getCodigoPersona()));
            nuevo.setEliminado('N');
            nuevo.setFechaRegistro(new Date());
            nuevo.setFirma('S');
            nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.ejbFacadeConyugePersona.edit(nuevo);
            this.deshabilitaBuscarConyuge = true;
            this.deshabilitaEliminarConyuge = false;
            this.deshabilitaEditarConyuge = true;
            this.deshabilitaTextoIdentificacionConyuge = true;
        } else {
            this.identificacionConyuge = "";
            this.personaConyugeSel = null;
            this.personaConyugeSocio = null;
        }
    }

    public void guardaConyugeGar(ActionEvent actionEvent) {
        ////System.out.println("Guarda Conyuge " + personaConyugeGarante);
        this.revisaEstadoCivil(this.personaConyugeGarante.getPersonaNatural(), this.getPersonaGarante().getPersonaNatural());
        ////System.out.println("valida Guarda Conyuge " + personaConyugeGarante + "\nvalido" + conyugeCorrecto);
        if (conyugeCorrecto) {
            PersonaConyuge nuevo = new PersonaConyuge();
            nuevo.setPersonaConyugePK(new PersonaConyugePK(this.getPersonaGarante().getPersonaNatural().getCodigoPersona(), this.getPersonaConyugeGarante().getPersonaNatural().getCodigoPersona()));
            nuevo.setEliminado('N');
            nuevo.setFechaRegistro(new Date());
            nuevo.setFirma('S');
            nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.ejbFacadeConyugePersona.edit(nuevo);
            nuevo = new PersonaConyuge();
            nuevo.setPersonaConyugePK(new PersonaConyugePK(this.getPersonaConyugeGarante().getPersonaNatural().getCodigoPersona(), this.getPersonaGarante().getPersonaNatural().getCodigoPersona()));
            nuevo.setEliminado('N');
            nuevo.setFechaRegistro(new Date());
            nuevo.setFirma('S');
            nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.ejbFacadeConyugePersona.edit(nuevo);
            this.deshabilitarBuscarConGar = true;
            this.deshabilitarEliminarConGar = false;
            this.deshabilitarGuardarConGar = true;
            this.deshabilitaTextoIdentificacionConGar = true;
        } else {
            this.identificacionConyuge = "";
            this.personaConyugeGarante = null;
        }

        //////System.out.println("Busca Socios Solicitud Fin");
    }

    public void quitarGarante() {
        itemsGaranteCredito.remove(garanteCredito);
    }

    public void agregaGarante() {
        if (this.identificacionConyuge != null) {
            if (this.identificacionConyuge.trim().equals(personaGarante.getIdentificacion())) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConyugeNoPuedeSerGarante"));
                return;
            }
        }
        Socio socio = this.ejbFacadeSocio.find(new SocioPK(this.codigoSocio, ActivacionUsuario.getCodigoIfip()));
        if (socio != null) {
            if (socio.getCodigoPersona().getIdentificacion().trim().equals(personaGarante.getIdentificacion())) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoPuedeSerGaranteMismoSocio"));
                return;
            }
        } else {
            MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
            return;

        }
        if (itemsGaranteCredito.size() >= garantesRequeridos) {
            MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GarantesCompletos"));
            return;
        }

        if (personaGarante != null && coberturaGarante > 0) {
            this.setMsg(null);
            if (personaGarante.getPersonaNatural().getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaGarante.getPersonaNatural().getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {
                // Buscando el conyuge actual                
                List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(personaGarante.getCodigo(), 'N');

                if (listaPersonaConyuge.isEmpty()) {
                    this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteNoTieneConyuge"));

                } else if (listaPersonaConyuge.size() > 1) {
                    this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteTieneMasDeUnConyuge"));
                }
            }
            if (this.getMsg() == null) {
                if (personaGarante.getCodigo() == solicitud.getSocio().getCodigoPersona().getCodigo()) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPuedeSerGarante"));
                    return;
                }
                if (personaConyugeSel != null) {
                    if (personaGarante.getCodigo() == personaConyugeSel.getCodigoPersona()) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ConyugeNoPuedeSerGarante"));
                        return;
                    }
                }
                int cantidadGarantias = ejbFacadeGaranteCredito.getNumeroGarantiasPersona(personaGarante.getCodigo(), Long.parseLong("6"), 'S');

                if (cantidadGarantias < garantiasMaximasPorPersona) {
                    //Verifica que el garante no este en la lista
                    boolean existe = false;
                    for (int i = 0; i < itemsGaranteCredito.size(); i++) {
                        if (itemsGaranteCredito.get(i).getGarante().getCodigoPersona().longValue() == personaGarante.getCodigo().longValue()) {
                            existe = true;
                            break;
                        }
                    }
                    if (existe) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteEstaEnLista"));
                    } else {
                        GaranteCredito garante = new GaranteCredito();
                        garante.setGarante(personaGarante.getPersonaNatural());
                        garante.setCoberturaDada(new BigDecimal(coberturaGarante));
                        this.itemsGaranteCredito.add(garante);
                        if (itemsGaranteCredito.size() >= garantesRequeridos) {
                            MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GarantesCompletos"));
                        }
                    }
                    personaGarante = null;
                    personaConyugeGarante = null;
                    this.identificacionConyugeGar = null;
                    this.identificacionGarante = null;
                    coberturaGarante = 0;

                } else {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExedeCantidadDeGarantias"));
                }

            } else {
                MuestraMensaje.addError(msg);
            }
        } else {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneGaranteCobertura"));
        }
    }

    public void eliminarConyuge(ActionEvent actionEvent) {
        List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(this.solicitud.getSocio().getCodigoPersona().getCodigo(), 'N');
        PersonaConyuge conyuge;
        if (listaPersonaConyuge.size() == 1) {
            conyuge = listaPersonaConyuge.get(0);
            conyuge.setEliminado('S');
            this.ejbFacadeConyugePersona.edit(conyuge);
            PersonaConyuge nuevo = new PersonaConyuge();
            nuevo.setPersonaConyugePK(new PersonaConyugePK(conyuge.getPersonaConyugePK().getCodigoPersonaConyuge(), conyuge.getPersonaConyugePK().getCodigoPersona()));
            nuevo.setEliminado('S');
            nuevo.setFechaRegistro(new Date());
            nuevo.setFirma('S');
            nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.ejbFacadeConyugePersona.edit(nuevo);
            this.identificacionConyuge = "";
            this.personaConyugeSel = null;
            this.personaConyugeSocio = null;
            this.deshabilitaBuscarConyuge = false;
            this.deshabilitaEliminarConyuge = true;
            this.deshabilitaEditarConyuge = true;
            this.deshabilitaTextoIdentificacionConyuge = false;
        }
    }

    public void eliminarConyugeGar(ActionEvent actionEvent) {
        List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(this.personaGarante.getPersonaNatural().getCodigoPersona(), 'N');
        ////System.out.println("Eliminar " + this.personaGarante.getPersonaNatural().getCodigoPersona());
        PersonaConyuge conyuge;
        ////System.out.println("N conyuges " + listaPersonaConyuge.size());
        if (listaPersonaConyuge.size() == 1) {
            conyuge = listaPersonaConyuge.get(0);
            conyuge.setEliminado('S');
            this.ejbFacadeConyugePersona.edit(conyuge);
            PersonaConyuge nuevo = new PersonaConyuge();
            nuevo.setPersonaConyugePK(new PersonaConyugePK(conyuge.getPersonaConyugePK().getCodigoPersonaConyuge(), conyuge.getPersonaConyugePK().getCodigoPersona()));
            nuevo.setEliminado('S');
            nuevo.setFechaRegistro(new Date());
            nuevo.setFirma('S');
            nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.ejbFacadeConyugePersona.edit(nuevo);
            this.identificacionConyugeGar = "";
            this.personaConyugeGarante = null;
            this.deshabilitarBuscarConGar = false;
            this.deshabilitarEliminarConGar = true;
            this.deshabilitarGuardarConGar = true;
            this.deshabilitaTextoIdentificacionConGar = false;
        }
    }

    /**
     *
     * @param actionEvent
     */
    public void nuevoCon(ActionEvent actionEvent) {
        ////System.out.println("entrooo nuevo");
    }

    public void revisaEstadoCivilCon() {
        this.revisaEstadoCivil(this.personaConyugeSel, this.getSolicitud().getSocio().getCodigoPersona().getPersonaNatural());
    }

    public void revisaEstadoCivilConGar() {
        this.personaConyugeGarante = this.personaConyugeGarante.getPersonaNatural().getPersona();
        this.revisaEstadoCivil(this.personaConyugeGarante.getPersonaNatural(), this.personaGarante.getPersonaNatural());
    }

    public void revisaEstadoCivil(PersonaNatural persona, PersonaNatural conyuge) {
        conyugeCorrecto = true;
        if (persona == null || conyuge == null) {
            conyugeCorrecto = false;
            return;
        }

        ////System.out.println("personaConyugeSel " + persona);
        if (!(persona.getCodigoEstadoCivil().getCodigo() == 2 || persona.getCodigoEstadoCivil().getCodigo() == 6)) {//Estado civil
            conyugeCorrecto = false;
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EstadoCivilNoValido"));
            MuestraMensaje.addAdvertencia(msg);
        } else if (conyuge.getCodigoEstadoCivil().getCodigo() != persona.getCodigoEstadoCivil().getCodigo()) {//Diferentes estados civil
            conyugeCorrecto = false;
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EstadoCivilNoCoincide"));
            MuestraMensaje.addAdvertencia(msg);
        } else if (persona.getCodigoEstadoCivil().getCodigo() == 2 && (conyuge.getSexo() == persona.getSexo())) {//Sexos de las personas
            conyugeCorrecto = false;
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SexoConyugeNoValido"));
            MuestraMensaje.addAdvertencia(msg);
        } else if (this.ejbFacadeConyugePersona.getItemsCodigoPersonaConyugeElminado(persona.getPersona().getPersonaNatural().getCodigoPersona(), 'N') != null) {
            if (this.ejbFacadeConyugePersona.getItemsCodigoPersonaConyugeElminado(persona.getPersona().getPersonaNatural().getCodigoPersona(), 'N').size() > 0) {
                conyugeCorrecto = false;
                this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaEsConyuge"));
                MuestraMensaje.addAdvertencia(msg);
            }

        }
    }

    /**
     * OBTIENE LOS SOCIOS PARA EL LISTADO DE BUSQUEDA.
     */
    public void obtieneSocios() {

        try {
            if (Validaciones.cumpleRequerimientoCampo(this.nombreSocioBusqueda, 6)) {
                if (this.busquedaSocioLista) {
                    this.setSocioSel(null);
                    this.setSelected(null);
                }
                this.setItemsSocio(this.ejbFacadeSocio.getItemsNombresIndicaActivo(this.nombreSocioBusqueda, ActivacionUsuario.getCodigoIfip(), 'S'));

            } else {
                this.mensajeCriterio = Validaciones.msg;
                this.setItemsSocio(null);
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
                    new Object[]{"solicitudController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * OBTIENE LOS GARANTES PARA EL LISTADO DE BUSQUEDA.
     */
    public void obtieneGarantes() {

        try {
            if (Validaciones.cumpleRequerimientoCampo(this.nombreGaranteBusqueda, 6)) {
                this.setGaranteSel(null);
                this.setItemsGarantes(this.ejbFacadePersonaNatural.getItemsLikeNombre(this.nombreGaranteBusqueda));

            } else {
                this.mensajeCriterio = Validaciones.msg;
                this.setGaranteSel(null);
                this.setItemsGarantes(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"solicitudController", "obtieneGarantes", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * OBTIENE LOS CONYUGES PARA EL LISTADO DE BUSQUEDA.
     */
    public void obtieneConyuge() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(this.nombreConyugeBusqueda, 6)) {
                this.setPersonaConyugeSel(null);
                this.setItemsPersonaNatural(this.ejbFacadePersonaNatural.getItemsLikeNombre(this.nombreConyugeBusqueda));

            } else {
                this.mensajeCriterio = Validaciones.msg;
                this.setItemsPersonaConyuge(null);
                this.setPersonaConyugeSel(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"solicitudController", "obtieneConyuges", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * CUANDO SELECCIONA EL SOCIO CUANDO SE LO BUSCA.
     *
     * @param actionEvent
     */
    public void seleccionaSocio(ActionEvent actionEvent) {
        if (socioSel != null) {
            try {
                validacionUAF(socioSel.getCodigoPersona());
            } catch (Exception e) {
            }

            String mensaje = this.validaSocioCrearSolicitud(socioSel.getSocioPK().getCodigo(), "T");
            if (mensaje != null) {
                MuestraMensaje.addError(mensaje);
                socioSel = null;
                return;
            }
            if (this.busquedaSocioLista) {
                this.codigoSocio = null;
                this.criterio = String.valueOf(this.socioSel.getSocioPK().getCodigo());
                this.nombreSocio = this.socioSel.getCodigoPersona().getNombreCompleto();
                // Busca el Socio
                this.buscaSocio(busquedaSocioLista);
            } else {

                this.solicitud.setSocio(new Socio());
                this.solicitud.setSocio(this.socioSel);
                this.codigoSocio = this.socioSel.getSocioPK().getCodigo();

                // Busca el Socio
                this.buscaSocio(busquedaSocioLista);
                if (this.getSolicitud().getSocio() != null) {
                    this.buscarMoraSocio(this.getSolicitud().getSocio().getSocioPK().getCodigo());
                    this.buscarMoraConyuge(this.getSolicitud().getSocio().getSocioPK().getCodigo());
                    this.buscarMoraGarante(this.getSolicitud().getSocio().getCodigoPersona().getCodigo());
                    this.buscarPersonasVinculadas(this.getSolicitud().getSocio().getCodigoPersona().getCodigo());
                }

                // Actualizando componentes de la vista
                context = RequestContext.getCurrentInstance();
                List<String> listaComponentesActualizar = new ArrayList<String>();
                listaComponentesActualizar.add("SolicitudNuevaFor:codigoSocio");
                listaComponentesActualizar.add("SolicitudNuevaFor");
                listaComponentesActualizar.add("SolicitudNuevaFor:nombreSocio");
                listaComponentesActualizar.add("SolicitudNuevaFor:editarSocioBot");
                listaComponentesActualizar.add("SolicitudNuevaFor:situacionEconomicaSocioBot");
                listaComponentesActualizar.add("SolicitudNuevaFor:identificacionConyuge");
                listaComponentesActualizar.add("SolicitudNuevaFor:nombreConyuge");
                listaComponentesActualizar.add("SolicitudNuevaFor:buscarConyugeSolicitud");
                listaComponentesActualizar.add("SolicitudNuevaFor:crearConyugeBot");
                listaComponentesActualizar.add("SolicitudNuevaFor:editarConyugeBot");
                listaComponentesActualizar.add("SolicitudNuevaFor:eliminarConyugeBot");
                listaComponentesActualizar.add("SolicitudNuevaFor:coberturaSocio");
                listaComponentesActualizar.add("SolicitudNuevaFor:patrimonioSocio");
                listaComponentesActualizar.add("SolicitudNuevaFor:cuentasTbl");
                listaComponentesActualizar.add("SolicitudNuevaFor:vinculadaDatalist");
                listaComponentesActualizar.add("SolicitudNuevaFor:cuentaDebitoTbl");
                listaComponentesActualizar.add("SolicitudNuevaFor:cuentaCreditoTbl");
                listaComponentesActualizar.add("SolicitudNuevaFor:actualizacionDatosSocio");
                listaComponentesActualizar.add("SolicitudNuevaFor:actualizacionSituacionPatrimonial");
                context.update(listaComponentesActualizar);
            }

        }
    }

    public BigDecimal formatoValor(BigDecimal valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", simbolos);
        try {
            return new BigDecimal(df.format(valor.doubleValue()));
        } catch (NumberFormatException e) {
            simbolos.setDecimalSeparator(',');
            df = new DecimalFormat("#.00", simbolos);
            return new BigDecimal(df.format(valor.doubleValue()));
        }

    }

    public void seleccionaGarante() {
        this.setMsg(null);
        if (garanteSel != null) {
            this.setIdentificacionGarante(garanteSel.getPersona().getIdentificacion());
            this.setPersonaGarante(garanteSel.getPersona());
            Socio socioGarante = this.ejbFacadeSocio.getItemsSociofindByIdIfip(this.getPersonaGarante().getIdentificacion(), ActivacionUsuario.getCodigoIfip()).get(0);
            String mensaje = this.validaSocioCrearSolicitud(socioGarante.getSocioPK().getCodigo(), "G");
            if (mensaje != null) {
                this.personaConyugeGarante = null;
                this.setMsg(mensaje);
                MuestraMensaje.addError(msg);
                return;
            }
            if (personaGarante.getPersonaNatural().getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaGarante.getPersonaNatural().getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {
                // Buscando el conyuge actual 
                List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(personaGarante.getCodigo(), 'N');

                // Si existe un conyuge definido para el socio                
                if (listaPersonaConyuge.size() == 1) {
                    // Asignando la persona del Conyuge
                    this.personaConyugeGarante = listaPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona();
                    this.identificacionConyugeGar = this.personaConyugeGarante.getIdentificacion();

                    this.deshabilitarEliminarConGar = false;
                    this.deshabilitarBuscarConGar = true;
                    this.deshabilitarGuardarConGar = true;
                    this.deshabilitaTextoIdentificacionConGar = true;

                    // si no existe ningun conyuge asignado
                } else if (listaPersonaConyuge.isEmpty()) {
                    List<PersonaConyuge> conyuges = this.ejbFacadeConyugePersona.getItemsCodigoPersonaConyugeElminado(this.personaGarante.getCodigo(), 'N');
                    if (conyuges.size() > 0) {
                        PersonaConyuge conyuge = conyuges.get(0);
                        PersonaConyuge nuevo = new PersonaConyuge();
                        nuevo.setPersonaConyugePK(new PersonaConyugePK(conyuge.getPersonaConyugePK().getCodigoPersonaConyuge(), conyuge.getPersonaConyugePK().getCodigoPersona()));
                        nuevo.setFirma('S');
                        nuevo.setEliminado('N');
                        nuevo.setFechaRegistro(new Date());
                        nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                        this.ejbFacadeConyugePersona.edit(nuevo);
                        this.personaConyugeGarante = conyuge.getPersonaNatural().getPersona();
                        this.identificacionConyugeGar = this.personaConyugeGarante.getIdentificacion();
                        this.deshabilitarEliminarConGar = false;
                        this.deshabilitarBuscarConGar = true;
                        this.deshabilitarGuardarConGar = true;
                        this.deshabilitaTextoIdentificacionConGar = true;
                    } else {
                        this.personaConyugeGarante = null;
                        this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteNoTieneConyuge"));
                        this.deshabilitarBuscarConGar = false;
                        this.deshabilitarEliminarConGar = true;
                        this.deshabilitarGuardarConGar = true;
                        this.deshabilitaTextoIdentificacionConGar = false;
                    }

                    // Si el socio tiene mas de un conyuge vigente asignados
                } else {
                    this.personaConyugeGarante = null;
                    this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteTieneMasDeUnConyuge"));
                }
            }
            if (this.getMsg() != null) {
                MuestraMensaje.addError(msg);
            }
            context = RequestContext.getCurrentInstance();
            List<String> listaComponentesActualizar = new ArrayList<String>();
            listaComponentesActualizar.add("SolicitudNuevaFor:mensajeWizard");
            listaComponentesActualizar.add("SolicitudNuevaFor:nombreGarante");
            listaComponentesActualizar.add("SolicitudNuevaFor:identificacionConyugeGar");
            listaComponentesActualizar.add("SolicitudNuevaFor:eliminarConyugeGarBot");
            listaComponentesActualizar.add("SolicitudNuevaFor:guardarConyugeGarBot");
            listaComponentesActualizar.add("SolicitudNuevaFor:nombreConyugeGar");
            listaComponentesActualizar.add("SolicitudNuevaFor:buscarConyugeGarBot");
            listaComponentesActualizar.add("SolicitudNuevaFor:codigoGarante");
            context.update(listaComponentesActualizar);
        }
    }

    public void seleccionaConyuge() {
        revisaEstadoCivil(this.personaConyugeSel, this.getSolicitud().getSocio().getCodigoPersona().getPersonaNatural());
        if (conyugeCorrecto) {
            identificacionConyuge = personaConyugeSel.getPersona().getIdentificacion();
            this.personaConyugeSocio = personaConyugeSel.getPersona();
            context = RequestContext.getCurrentInstance();
            this.deshabilitaBuscarConyuge = false;
            this.deshabilitaEliminarConyuge = true;
            this.deshabilitaEditarConyuge = false;
            this.deshabilitaTextoIdentificacionConyuge = false;
            List<String> listaComponentesActualizar = new ArrayList<String>();
            listaComponentesActualizar.add("SolicitudNuevaFor:identificacionConyuge");
            listaComponentesActualizar.add("SolicitudNuevaFor:nombreConyuge");
            listaComponentesActualizar.add("SolicitudNuevaFor:buscarConyugeSolicitud");
            listaComponentesActualizar.add("SolicitudNuevaFor:editarConyugeBot");
            listaComponentesActualizar.add("SolicitudNuevaFor:eliminarConyugeBot");
            context.update(listaComponentesActualizar);
            context.execute("ListaConyugesDialog.hide();");
            ////System.out.println("Conyuge " + personaConyugeSel);
        }
    }

    public void seleccionaConyugeGar() {
        revisaEstadoCivil(this.personaConyugeGarante.getPersonaNatural(), this.getPersonaGarante().getPersonaNatural());
        if (conyugeCorrecto) {
            identificacionConyugeGar = this.personaConyugeGarante.getIdentificacion();
            context = RequestContext.getCurrentInstance();
            this.deshabilitarBuscarConGar = false;
            this.deshabilitarEliminarConGar = true;
            this.deshabilitarGuardarConGar = false;
            this.deshabilitaTextoIdentificacionConGar = false;
            List<String> listaComponentesActualizar = new ArrayList<String>();
            listaComponentesActualizar.add("SolicitudNuevaFor:identificacionConyugeGar");
            listaComponentesActualizar.add("SolicitudNuevaFor:nombreConyugeGar");
            listaComponentesActualizar.add("SolicitudNuevaFor:buscarConyugeGarBot");
            listaComponentesActualizar.add("SolicitudNuevaFor:guardarConyugeGarBot");
            listaComponentesActualizar.add("SolicitudNuevaFor:eliminarConyugeGarBot");
            context.update(listaComponentesActualizar);
            context.execute("ListaConyugesGarDialog.hide();");
            ////System.out.println("Conyuge " + personaConyugeGarante.getPersonaNatural());
        } else {
            personaConyugeGarante = null;
        }
    }

    private void cargarCuentasSocio() {
        ////System.out.println("socio " + this.solicitud.getSocio().getSocioPK().getCodigo() + " ifip " + this.solicitud.getSocio().getSocioPK().getCodigoIfip());
        this.setItemsCuentas(this.ejbFacadeCuenta.getItemsCodigoSocioCodigoIfipCodigoEstado(this.solicitud.getSocio().getSocioPK().getCodigo(), this.solicitud.getSocio().getSocioPK().getCodigoIfip(), (long) 1));
        this.setItemsCuentasAhorrosVista(this.ejbFacadeCuenta.getItemsPuedeReactivarAhorros(this.solicitud.getSocio().getSocioPK().getCodigo(), this.solicitud.getSocio().getSocioPK().getCodigoIfip(), 'S', Long.parseLong("2")));
        ////System.out.println("items " + itemsCuentas.size());
    }

    /**
     * BUSCA EL SOCIO DE ACUERDO A LA CODIGO
     *
     * @param lista True cuando se va a buscar el socio para listar los creditos
     * y False cuando se busca el socio para crear la solicitud.
     */
    private void buscaSocio(boolean lista) {

        Long codigoSocioBusqueda = (lista) ? Long.valueOf(this.criterio) : this.codigoSocio;
        this.msg = null;

        Socio socioBusqueda = this.ejbFacadeSocio.find(new SocioPK(codigoSocioBusqueda, ActivacionUsuario.getCodigoIfip()));

        if (socioBusqueda != null) {
            // Validando al Socio
            if (lista) {

                this.itemsSolicitud = null;
                this.nombreSocio = socioBusqueda.getCodigoPersona().getNombreCompleto();
                // Buscando las solicitudes del socios
                this.itemsSolicitud = this.ejbFacade.getItemsCodigoSocioCodigoIfip(codigoSocioBusqueda, ActivacionUsuario.getCodigoIfip());

                // Si se busca al socio para crear la solicitud de credito
            } else {
                String mensaje = this.validaSocioCrearSolicitud(socioBusqueda.getSocioPK().getCodigo(), "T");
                if (mensaje != null) {
                    MuestraMensaje.addError(mensaje);
                    sigue = false;
                    return;
                } else {
                    sigue = true;
                }
                this.deshabilitaSituacionEconomica = false;
                this.socioFlujoCaja = ejbFacadeSocioFlujoCaja.getSocioFlujoCajaCodigoSocioIfip(socioBusqueda.getSocioPK().getCodigo(), socioBusqueda.getSocioPK().getCodigoIfip());
                this.socioSituacionPatrimonial = ejbFacadeSocioSituacionPatrimonial.getSocioSocioSituacionPatrimonialCodigoSocioIfip(socioBusqueda.getSocioPK().getCodigo(), socioBusqueda.getSocioPK().getCodigoIfip());

                if (socioFlujoCaja != null) {
                    ////System.out.println("patrimonio: " + socioFlujoCaja.getCobertura());

                    //   this.socioFlujoCaja.setCobertura(formatoValor(this.socioFlujoCaja.getCobertura()));
                }
                if (socioSituacionPatrimonial != null) {
                    ////System.out.println("patrimonio: " + socioSituacionPatrimonial.getTotalPatrimonio());
                    // this.socioSituacionPatrimonial.setTotalPatrimonio(formatoValor(this.socioSituacionPatrimonial.getTotalPatrimonio()));
                }

                if (this.isSocioValido(socioBusqueda)) {

                    // this.limpiaDatosSocioSolicitud();
                    this.solicitud.setSocio(socioBusqueda);
                    this.codigoSocio = socioBusqueda.getSocioPK().getCodigo();

                    // Activando Botones del Socio
//                    this.deshabilitaEditarSocio = false;
                    // Buscamos el conyuge del socio
                    buscaConyugeSocio();
                    cargarCuentasSocio();
                }

            }
            if (lista) {
                this.nombreSocio = socioBusqueda.getCodigoPersona().getNombreCompleto();
            } else {
                this.solicitud.setSocio(socioBusqueda);
            }

        } else {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
        }

        if (getMsg() != null) {
            MuestraMensaje.addAdvertencia(getMsg());
        }
    }

    /**
     * BUSCA EL SOCIO EN EL CRITERIO DE CONSULTA PARA OBTENER EL LISTADO DE
     * CREDITOS.
     */
    public void buscaSocioLista() {
        this.buscaSocio(true);
    }

    /**
     * BUSCA EL SOCIO PARA CREAR UNA SOLICITUD.
     */
    public void buscaSocioSolicitud() {
        String mensaje;
        if (this.codigoSocio == null) {
            return;
        }
        if (this.solicitud.getSocio().getSocioPK().getCodigo() != this.codigoSocio) {
            this.limpiaDatosSocioSolicitud();
            this.buscaSocio(false);
            if (sigue == false) {
                return;
            }
            if (this.getSolicitud().getSocio() != null) {
                mensaje = this.validaSocioCrearSolicitud(this.getSolicitud().getSocio().getSocioPK().getCodigo(), "T");
                if (mensaje == null) {
                    this.buscarMoraSocio(this.getSolicitud().getSocio().getSocioPK().getCodigo());
                    this.buscarMoraConyuge(this.getSolicitud().getSocio().getSocioPK().getCodigo());
                    this.buscarMoraGarante(this.getSolicitud().getSocio().getCodigoPersona().getCodigo());
                    this.buscarPersonasVinculadas(this.getSolicitud().getSocio().getCodigoPersona().getCodigo());
                } else {
                    this.solicitud = null;
                    MuestraMensaje.addError(mensaje);
                }
            }
        }
    }

    /**
     * BUSCA LAS PERSONAS QUE ESTAN VINCULADAS AL SOCIO Y PERTENENCEN A LA IFIP
     *
     * @param codigoPersona valor usado para buscar
     */
    public void buscarPersonasVinculadas(Long codigoPersona) {
        BigDecimal exists = ejbPersonaRelacionIfipFacade.getItemByCodigoPersonaEliminado(codigoPersona, 'N');
        margenVinculadoDisponible = true;
        if (exists.compareTo(BigDecimal.ZERO) > 0) {
            MuestraMensaje.addAdvertencia("El socio para el credito a solicitar se encuentra  dentro de la nomina de vinculados, revisar disponibilidad de cupo");
            obtieneMargenValorVinculado();
            if (montoMaximoVinculado.compareTo(BigDecimal.ZERO) < 1) {
                margenVinculadoDisponible = false;
            }
            List<PersonaVinculado> vinculaciones = ejbPersonaVinculadoFacade.getItemsfindByVinculadoNoEliminado(codigoPersona);
            itemsPersonaVinculado = new ArrayList<>();
            for (PersonaVinculado vin : vinculaciones) {
                long code = (vin.getPersona().getCodigo().compareTo(codigoPersona) == 0) ? vin.getPersona1().getCodigo() : vin.getPersona().getCodigo();
                Persona pri = ejbPersonaRelacionIfipFacade.getItemByCodigoPersonaP(code);
                if (pri != null) {
                    itemsPersonaVinculado.add(pri);
                }
            }
        }
    }

    /**
     * METODO PARA VALIDAR AL SOCIO PARA CREAR UNA SOLICITUD DE CREDITO
     *
     * @param codigoSocio
     */
    public String validaSocioCrearSolicitud(Long codigoSocio, String tipo) {
        String mensaje = null;
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_valida_socio_solicitud");
            llamaSP.setNumeroParametros(4);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_socio", codigoSocio});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pv_tipo", tipo});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje", Types.VARCHAR});
            // Invocando al SP
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                mensaje = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
                return mensaje;
            } else {
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                        new Object[]{"SolicitudController", "validaSocioCrearSolicitud", llamaSP.getErroSql()});
                return llamaSP.getError();
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"SolicitudController", "validaSocioCrearSolicitud", llamaSP.getErroSql()});
            return llamaSP.getError();
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
            return mensaje;
        }
    }

    /**
     * Metodo para valida que se haya ingresado hasta el último nivel de
     * Actividades Economicas
     */
    public int validaActividadEconomicaSolicitud(Long codigoProducto, Long codigoMoneda, Long codigoIfip) {
        try {
            int maximoNivel = 0;
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_creditos.pkm_act_eco_pro_cre_mon_ifip.p_obtiene_maximo_nivel");
            llamaSP.setNumeroParametros(4);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_producto", codigoProducto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_moneda", codigoMoneda});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", codigoIfip});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_maximo_nivel", Types.NUMERIC});
            // Invocando al SP
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getListResultado() != null) {
                    maximoNivel = Integer.parseInt(llamaSP.getListResultado().get(0).toString());
                    return maximoNivel;
                } else {
                    return -1;
                }
            } else {
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                        new Object[]{"SolicitudController", "validaActividadEconomicaSolicitud", llamaSP.getErroSql()});
                return -1;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"SolicitudController", "validaActividadEconomicaSolicitud", llamaSP.getErroSql()});
            return -1;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     * Metodo para valida que se haya ingresado hasta el último nivel de
     * Actividades Economicas
     */
    public int validaUbicacionGeograficaSolicitud(Long codigoPais) {
        try {
            int maximaJerarquia = 0;
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_comunes.pkg_ubicacion_geografica.p_obtiene_maxima_jerarquia");
            llamaSP.setNumeroParametros(2);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo", codigoPais});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_jerarquia", Types.NUMERIC});
            // Invocando al SP
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getListResultado() != null) {
                    maximaJerarquia = Integer.parseInt(llamaSP.getListResultado().get(0).toString());
                    return maximaJerarquia;
                } else {
                    return -1;
                }
            } else {
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                        new Object[]{"SolicitudController", "validaUbicacionGeograficaSolicitud", llamaSP.getErroSql()});
                return -1;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"SolicitudController", "validaUbicacionGeograficaSolicitud", llamaSP.getErroSql()});
            return -1;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    public void buscarMoraConyuge(Long codigoSocio) {
        Socio socio = ejbFacadeSocio.find(new SocioPK(codigoSocio, ActivacionUsuario.getCodigoIfip()));
        if (socio == null) {
            return;
        }
        Persona personaSocio = socio.getCodigoPersona();
        Long codigoPersona = personaSocio.getCodigo();
        if (String.valueOf(personaSocio.getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
            PersonaNatural personaNatural = this.ejbFacadePersonaNatural.find(codigoPersona);
            // Verificando si el estado civil es  casado para recuperar el conyue
            if (personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {

                // Buscando el conyuge actual                
                List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(codigoPersona, 'N');

                // Si existe un conyuge definido para el socio                
                if (listaPersonaConyuge.size() == 1) {
                    List<Socio> socios = ejbFacadeSocio.getItemsSociofindByIdentificacion(listaPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona().getIdentificacion());
                    if (socios.isEmpty()) {
                        return;
                    }
                    Long idSocio = socios.get(0).getSocioPK().getCodigo();
                    List<SolicitudDetalle> moraSocio = ejbFacadeSolicitudDetalle.getItemsDetalleCodigoSocioCodigoIfipMora(idSocio, ActivacionUsuario.getCodigoIfip(), new BigDecimal(0));
                    if (!moraSocio.isEmpty()) {
                        String aux;
                        for (SolicitudDetalle sol : moraSocio) {
                            List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                            double deuda = 0;
                            for (CalculoCuotaPagar c : pendientes) {
                                deuda = deuda + c.getTotalPago().doubleValue();
                            }
                            Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), 'P');
                            aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioConyugeMora") + "\n";
                            aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", sol.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(sol.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", socios.get(0).getCodigoPersona().getNombreCompleto().replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda)))));
                            MuestraMensaje.addInformacion(aux);
                        }

                    }
                }

            }
        }

    }

    public void buscarMoraSocio(Long codigoSocio) {
        ////VERIFICACIONES DE MORA
        //System.out.println("Entro Busca Mora Socio");
        List<SolicitudDetalle> moraSocio = ejbFacadeSolicitudDetalle.getItemsDetalleCodigoSocioCodigoIfipMora(codigoSocio, ActivacionUsuario.getCodigoIfip(), new BigDecimal(0));
        if (!moraSocio.isEmpty()) {
            String aux;
            for (SolicitudDetalle sol : moraSocio) {
                Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), 'P');
                List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                double deuda = 0;
                for (CalculoCuotaPagar c : pendientes) {
                    deuda = deuda + c.getTotalPago().doubleValue();
                }
                aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioTieneMora") + "\n";
                aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", sol.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(sol.getSolicitudDetallePK().getNumeroCredito())).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                MuestraMensaje.addInformacion(aux);
            }

        }
    }

    public void buscarMoraGarante(Long codigoPrsona) {
        ////VERIFICACIONES DE MORA
        List<Solicitud> solicitudes = ejbFacadeGaranteCredito.getItemPersonaIfipVigente(codigoPrsona, ActivacionUsuario.getCodigoIfip(), 'S');
        for (Solicitud sol : solicitudes) {
            List<SolicitudDetalle> moraSocio = ejbFacadeSolicitudDetalle.getItemSolicitudCreditoNumeroIfipMora(sol.getSolicitudPK().getNumero(), ActivacionUsuario.getCodigoIfip(), new BigDecimal(0));
            if (!moraSocio.isEmpty()) {
                String aux;
                for (SolicitudDetalle s : moraSocio) {
                    List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                    double deuda = 0;
                    for (CalculoCuotaPagar c : pendientes) {
                        deuda = deuda + c.getTotalPago().doubleValue();
                    }
                    Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), 'P');
                    aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioGaranteMora") + "\n";
                    aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", s.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(s.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", s.getSolicitud().getSocio().getCodigoPersona().getNombreCompleto()).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                    MuestraMensaje.addInformacion(aux);
                }
            }
        }
    }

    public void buscaGaranteSolicitud() {
        try {
            this.setMsg(null);
            boolean buscar = true;
            if (personaGarante != null) {
                if (personaGarante.getIdentificacion().equals(identificacionGarante)) {
                    buscar = false;
                }
            }
            if (buscar) {
                List<PersonaNatural> resultado = this.ejbFacadePersonaNatural.getItemsIdentificacion(identificacionGarante);
                if (resultado != null) {
                    if (resultado.size() > 1) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionAsignadoMasDeUnaPersona"));
                    } else if (resultado.size() == 1) {
                        personaGarante = resultado.get(0).getPersona();
                        if (personaGarante != null) {
                            List<Socio> listaSocio = this.ejbFacadeSocio.getItemsSociofindByIdIfip(personaGarante.getIdentificacion(), ActivacionUsuario.getCodigoIfip());
                            if (listaSocio != null) {
                                if (!listaSocio.isEmpty()) {
                                    Socio socioGarante = listaSocio.get(0);
                                    String mensaje = this.validaSocioCrearSolicitud(socioGarante.getSocioPK().getCodigo(), "G");
                                    if (mensaje != null) {
                                        MuestraMensaje.addError(mensaje);
                                        personaGarante = null;
                                        return;
                                    }
                                }
                            }

                        }
                        if (personaGarante.getPersonaNatural().getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaGarante.getPersonaNatural().getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {
                            // Buscando el conyuge actual 
                            List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(personaGarante.getCodigo(), 'N');

                            // Si existe un conyuge definido para el socio                
                            if (listaPersonaConyuge.size() == 1) {
                                // Asignando la persona del Conyuge
                                this.personaConyugeGarante = listaPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona();
                                this.identificacionConyugeGar = this.personaConyugeGarante.getIdentificacion();

                                this.deshabilitarEliminarConGar = false;
                                this.deshabilitarBuscarConGar = true;
                                this.deshabilitarGuardarConGar = true;
                                this.deshabilitaTextoIdentificacionConGar = true;

                                // si no existe ningun conyuge asignado
                            } else if (listaPersonaConyuge.isEmpty()) {
                                List<PersonaConyuge> conyuges = this.ejbFacadeConyugePersona.getItemsCodigoPersonaConyugeElminado(this.personaGarante.getCodigo(), 'N');
                                if (conyuges.size() > 0) {
                                    PersonaConyuge conyuge = conyuges.get(0);
                                    PersonaConyuge nuevo = new PersonaConyuge();
                                    nuevo.setPersonaConyugePK(new PersonaConyugePK(conyuge.getPersonaConyugePK().getCodigoPersonaConyuge(), conyuge.getPersonaConyugePK().getCodigoPersona()));
                                    nuevo.setFirma('S');
                                    nuevo.setEliminado('N');
                                    nuevo.setFechaRegistro(new Date());
                                    nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                                    this.ejbFacadeConyugePersona.edit(nuevo);
                                    this.personaConyugeGarante = conyuge.getPersonaNatural().getPersona();
                                    this.identificacionConyugeGar = this.personaConyugeGarante.getIdentificacion();
                                    this.deshabilitarEliminarConGar = false;
                                    this.deshabilitarBuscarConGar = true;
                                    this.deshabilitarGuardarConGar = true;
                                    this.deshabilitaTextoIdentificacionConGar = true;
                                } else {
                                    this.personaConyugeGarante = null;
                                    this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteNoTieneConyuge"));
                                    this.deshabilitarBuscarConGar = false;
                                    this.deshabilitarEliminarConGar = true;
                                    this.deshabilitarGuardarConGar = true;
                                    this.deshabilitaTextoIdentificacionConGar = false;
                                }

                                // Si el socio tiene mas de un conyuge vigente asignados
                            } else {
                                this.personaConyugeGarante = null;
                                this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GaranteTieneMasDeUnConyuge"));
                            }
                        }
                    } else {
                        this.personaGarante = null;
                        this.personaConyugeGarante = null;
                        this.identificacionConyugeGar = null;
                    }
                } else {
                    this.personaGarante = null;
                }
            }
            if (this.getMsg() != null) {
                MuestraMensaje.addError(msg);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SolicitudController", "buscaGaranteSolicitud", CapturaError.getErrorException(ex)});
        }
    }

    public void buscaConyuge() {
        List<PersonaNatural> resultado = this.ejbFacadePersonaNatural.getItemsIdentificacion(identificacionConyuge);
        if (resultado != null) {
            if (resultado.size() > 1) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionAsignadoMasDeUnaPersona"));
            } else if (resultado.size() == 1) {
                this.personaConyugeSel = resultado.get(0);
                this.revisaEstadoCivil(this.personaConyugeSel, this.getSolicitud().getSocio().getCodigoPersona().getPersonaNatural());
                if (this.conyugeCorrecto) {
                    this.personaConyugeSocio = this.personaConyugeSel.getPersona();
                    this.deshabilitaBuscarConyuge = false;
                    this.deshabilitaEliminarConyuge = true;
                    this.deshabilitaEditarConyuge = false;
                    this.deshabilitaTextoIdentificacionConyuge = false;
                } else {
                    this.identificacionConyuge = "";
                    this.personaConyugeSel = null;
                    this.personaConyugeSocio = null;
                }
            }
        }

    }

    public void buscaConyugeGar() {
        this.setMsg(null);
        List<PersonaNatural> resultado = this.ejbFacadePersonaNatural.getItemsIdentificacion(identificacionConyugeGar);
        if (resultado != null) {
            if (resultado.size() > 1) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionAsignadoMasDeUnaPersona"));
            } else if (resultado.size() == 1) {
                this.personaConyugeGarante = resultado.get(0).getPersona();
                this.revisaEstadoCivil(this.personaConyugeGarante.getPersonaNatural(), this.personaGarante.getPersonaNatural());
                if (this.conyugeCorrecto) {
                    this.deshabilitarBuscarConGar = false;
                    this.deshabilitarEliminarConGar = true;
                    this.deshabilitarGuardarConGar = false;
                    this.deshabilitaTextoIdentificacionConGar = false;
                } else {
                    this.identificacionConyugeGar = "";
                    this.personaConyugeGarante = null;
                }
            }
        }

        if (this.getMsg() != null) {
            MuestraMensaje.addError(msg);
        }
    }

    public void verificaSeleccionCuenta() {
        if (cuentaCombo != null) {
            if (this.cuentaCombo.getCodigoEstado().getCodigo() != 1) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaNoActiva"));
                cuentaCombo = null;
            }
        }
    }

    public void agregaCuentaDetalle() {
        if (this.cuentaCombo != null) {
            if (itemsSolicitudDetalle.isEmpty()) {
                itemsSolicitudDetalle.add(new SolicitudDetalle());
            }
            if (getTipoCuentaSel().equals("D")) {
                itemsSolicitudDetalle.get(0).setCuentaDebito(cuentaCombo);
            }
            if (getTipoCuentaSel().equals("C")) {
                itemsSolicitudDetalle.get(0).setCuentaAcreditada(cuentaCombo);
            }
        }
    }

    public void cambiaActEcoRecptoraN1() {
        if (actividadEconomicaReceptoraN1 != null) {
            //this.setItemsActividadEconomicaN2(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(2, actividadEconomicaReceptoraN1.getCodigo().longValue(), 'C', 'N'));
            this.setItemsActividadEconomicaN2(ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 2, actividadEconomicaReceptoraN1.getCodigo().longValue(), 'C', 'N'));
        }
        this.setItemsActividadEconomicaN3(null);
        this.setItemsActividadEconomicaN4(null);
        this.setItemsActividadEconomicaN5(null);
        this.setItemsActividadEconomicaN6(null);
        this.setActividadEconomicaReceptoraN2(null);
        this.setActividadEconomicaReceptoraN3(null);
        this.setActividadEconomicaReceptoraN4(null);
        this.setActividadEconomicaReceptoraN5(null);
        this.setActividadEconomicaReceptoraN6(null);
    }

    public void cambiaActEcoRecptoraN2() {
        if (actividadEconomicaReceptoraN2 != null) {
            //this.setItemsActividadEconomicaN3(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(3, actividadEconomicaReceptoraN2.getCodigo().longValue(), 'C', 'N'));
            this.setItemsActividadEconomicaN3(ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 3, actividadEconomicaReceptoraN2.getCodigo().longValue(), 'C', 'N'));
        }
        this.setItemsActividadEconomicaN4(null);
        this.setItemsActividadEconomicaN5(null);
        this.setItemsActividadEconomicaN6(null);
        this.setActividadEconomicaReceptoraN3(null);
        this.setActividadEconomicaReceptoraN4(null);
        this.setActividadEconomicaReceptoraN5(null);
        this.setActividadEconomicaReceptoraN6(null);
    }

    public void cambiaActEcoRecptoraN3() {
        if (actividadEconomicaReceptoraN3 != null) {
            //this.setItemsActividadEconomicaN4(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(4, actividadEconomicaReceptoraN3.getCodigo().longValue(), 'C', 'N'));
            this.setItemsActividadEconomicaN4(ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 4, actividadEconomicaReceptoraN3.getCodigo().longValue(), 'C', 'N'));
        }
        this.setItemsActividadEconomicaN5(null);
        this.setItemsActividadEconomicaN6(null);
        this.setActividadEconomicaReceptoraN4(null);
        this.setActividadEconomicaReceptoraN5(null);
        this.setActividadEconomicaReceptoraN6(null);
    }

    public void cambiaActEcoRecptoraN4() {
        if (actividadEconomicaReceptoraN4 != null) {
            //this.setItemsActividadEconomicaN5(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(5, actividadEconomicaReceptoraN4.getCodigo().longValue(), 'C', 'N'));
            this.setItemsActividadEconomicaN5(ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 5, actividadEconomicaReceptoraN4.getCodigo().longValue(), 'C', 'N'));
        }
        this.setItemsActividadEconomicaN6(null);
        this.setActividadEconomicaReceptoraN5(null);
        this.setActividadEconomicaReceptoraN6(null);
    }

    public void cambiaActEcoRecptoraN5() {
        if (actividadEconomicaReceptoraN5 != null) {
            //this.setItemsActividadEconomicaN6(ejbFacadeActvidadEconomica.getItemsActEcoNivelPadreEliminado(6, actividadEconomicaReceptoraN5.getCodigo().longValue(), 'C', 'N'));
            this.setItemsActividadEconomicaN6(ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelPadreEliminado(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 6, actividadEconomicaReceptoraN5.getCodigo().longValue(), 'C', 'N'));
        }
        this.setActividadEconomicaReceptoraN6(null);
    }

    private ActividadEconomica ubicaActividadEconomica() {
        if (actividadEconomicaReceptoraN6 != null) {
            return actividadEconomicaReceptoraN6;
        } else if (actividadEconomicaReceptoraN5 != null) {
            return actividadEconomicaReceptoraN5;
        }
        if (actividadEconomicaReceptoraN4 != null) {
            return actividadEconomicaReceptoraN4;
        }
        if (actividadEconomicaReceptoraN3 != null) {
            return actividadEconomicaReceptoraN3;
        }
        if (actividadEconomicaReceptoraN2 != null) {
            return actividadEconomicaReceptoraN2;
        }
        if (actividadEconomicaReceptoraN1 != null) {
            return actividadEconomicaReceptoraN1;
        } else {
            return null;
        }
    }

    public void cambiaSector() {
        if (this.getSectorActividadEconomica() != null) {
            this.setItemsSubsectorActividadEconomica(this.getEjbFacadeSubsectorActividadEconomica().getItemsCodigoSectorElminado(this.getSectorActividadEconomica().getCodigo(), 'N'));
            this.setSubsectorActividadEconomica(null);
            this.solicitud.setActividadEconomica(null);
        } else {
            this.setItemsSubsectorActividadEconomica(null);
            this.solicitud.setActividadEconomica(null);
        }
    }

//    public void cambiaSubSector() {
//        if (this.getSubsectorActividadEconomica() != null) {
//            this.setItemsActividadEconomica(this.ejbFacadeActvidadEconomica.getItemsSectorSubsectorNivelEliminado(this.getSectorActividadEconomica().getCodigo(), this.getSubsectorActividadEconomica().getCodigo(), 3,'C', 'N'));
//            this.solicitud.setActividadEconomica(null);
//        } else {
//            this.setItemsActividadEconomica(null);
//            this.solicitud.setActividadEconomica(null);
//        }
//    }
    public void cambiaSeleccionMoneda() {
        if (solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda() != null) {
            this.itemsTipoCartera = ejbFacadeTipoCartera.getItemsTipoCarteraProductoCredito(ActivacionUsuario.getCodigoIfip(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo());
            this.setItemsProductoCredito(null);
            this.setItemsActividadEconomica(null);
            this.setItemsActividadEconomicaN1(null);
            this.setItemsActividadEconomicaN2(null);
            this.setItemsActividadEconomicaN3(null);
            this.setItemsActividadEconomicaN4(null);
            this.setItemsActividadEconomicaN5(null);
            this.setItemsActividadEconomicaN6(null);
            this.setItemsDestinoFinanciero(null);
            this.setActividadEconomicaReceptoraN1(null);
            this.setActividadEconomicaReceptoraN2(null);
            this.setActividadEconomicaReceptoraN3(null);
            this.setActividadEconomicaReceptoraN4(null);
            this.setActividadEconomicaReceptoraN5(null);
            this.setActividadEconomicaReceptoraN6(null);
        }
        this.solicitud.setMontoCredito(null);
        this.solicitud.setTasa(null);
        this.setItemsPeriodicidad(null);
        this.listaTasaCredito = null;
        this.setGarantesRequeridos(0);
        solicitud.getPeriodicidadProMonIfi().setPeriodicidad(null);
        solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().setProductoCredito(null);
        this.autoGenerarTabla();
    }

    public void cambiaSeleccionMonedaSimulacion() {
        if (solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda() != null) {
            this.itemsTipoCartera = ejbFacadeTipoCartera.getItemsTipoCarteraProductoCredito(ActivacionUsuario.getCodigoIfip(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo());
            this.setItemsProductoCredito(null);
            this.setItemsActividadEconomica(null);
            this.setItemsActividadEconomicaN1(null);
            this.setItemsActividadEconomicaN2(null);
            this.setItemsActividadEconomicaN3(null);
            this.setItemsActividadEconomicaN4(null);
            this.setItemsActividadEconomicaN5(null);
            this.setItemsActividadEconomicaN6(null);
            this.setItemsDestinoFinanciero(null);
            this.setActividadEconomicaReceptoraN1(null);
            this.setActividadEconomicaReceptoraN2(null);
            this.setActividadEconomicaReceptoraN3(null);
            this.setActividadEconomicaReceptoraN4(null);
            this.setActividadEconomicaReceptoraN5(null);
            this.setActividadEconomicaReceptoraN6(null);
        }
        this.solicitud.setMontoCredito(null);
        this.solicitud.setTasa(null);
        this.setItemsPeriodicidad(null);
        this.listaTasaCredito = null;
        this.setGarantesRequeridos(0);
        solicitud.getPeriodicidadProMonIfi().setPeriodicidad(null);
        solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().setProductoCredito(null);
        this.autoGenerarTabla();
    }

    public void cambiaSeleccionTipoCartera() {
        try {
            if (solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda() != null && tipoCartera != null) {
                if (this.socioSel == null) {
                    this.socioSel = this.solicitud.getSocio();
                }
                this.setItemsProductoCredito(this.getEjbProductoCreditoTipoPersonaFacade().getItemsProductoCreditoMonedaIfip(this.tipoCartera.getCodigo(), this.socioSel.getCodigoPersona().getCodigoTipoPersona().getCodigo()));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"SolicitudController", "cambiaSeleccionTipoCartera", llamaSP.getErroSql()});
        }

    }

    public void cambiaSeleccionTipoCarteraSimulacion() {
        if (solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda() != null && tipoCartera != null) {
            this.setItemsProductoCredito(this.ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditobyCodigoIfipMonedaTipoCartera(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N', tipoCartera.getCodigo()));
        }
    }

    public void cambiaSeleccionProdCred() {
        if (solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito() != null) {
            this.setItemsPeriodicidad(this.ejbFacadePeriodicidadProMonIfi.getPeriodicidadProMonIfiElim(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N', 'S'));
            this.listaTasaCredito = this.ejbFacadeTasaInteresProCreMonIfi.getListaTasaInteresProCreMonIfi(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N');
            this.setItemsActividadEconomicaN1(this.ejbFacadeActividadEcoProMonIifip.getItemsActEcoNivelEliminado(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 1, 'C', 'N'));
            this.setItemsDestinoFinanciero(this.ejbFacadeDestinoFinProCreMonIfipFacade.getItemsDestinoFinancieroPorProducto(this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip()));
        } else {
            this.listaTasaCredito = null;
            this.setItemsPeriodicidad(null);
            this.setItemsActividadEconomica(null);
            this.setItemsActividadEconomicaN1(null);
            this.setItemsActividadEconomicaN2(null);
            this.setItemsActividadEconomicaN3(null);
            this.setItemsActividadEconomicaN4(null);
            this.setItemsActividadEconomicaN5(null);
            this.setItemsActividadEconomicaN6(null);
            this.setActividadEconomicaReceptoraN1(null);
            this.setActividadEconomicaReceptoraN2(null);
            this.setActividadEconomicaReceptoraN3(null);
            this.setActividadEconomicaReceptoraN4(null);
            this.setActividadEconomicaReceptoraN5(null);
            this.setActividadEconomicaReceptoraN6(null);
            this.setItemsDestinoFinanciero(null);
        }
        this.solicitud.setMontoCredito(null);
        this.solicitud.setTasa(null);
        this.setGarantesRequeridos(0);
        solicitud.getPeriodicidadProMonIfi().setPeriodicidad(null);
        this.autoGenerarTabla();
    }

    public void seleccionaCuotasMaximas() {
        if (solicitud.getPeriodicidadProMonIfi().getPeriodicidad() != null) {
            PeriodicidadProMonIfi per = this.ejbFacadePeriodicidadProMonIfi.getPeriodicidadProMonIfiCuoMax(solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N', solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo());
            cuotasMaximas = per.getCuotasMaximas();
        } else {
            cuotasMaximas = Long.parseLong("0");
        }
        this.solicitud.setNumeroCuotas(0);
        autoGenerarTabla();
    }

    public void seleccionaTasaPorMonto() {
        boolean valido = false;
        if (listaTasaCredito != null) {
            for (int i = 0; i < listaTasaCredito.size(); i++) {
                if (solicitud.getMontoCredito().doubleValue() >= listaTasaCredito.get(i).getMontoInicial().doubleValue() && solicitud.getMontoCredito().doubleValue() <= listaTasaCredito.get(i).getMontoFinal().doubleValue()) {
                    if (this.getSocioSel() != null) {
                        if (this.getSocioSel().getCodigoPersona().getCodigoTipoPersona().getCodigo() == 1) {
                            if (!this.validaMonto(this.getSocioSel().getCodigoPersona().getCodigoTipoPersona().getCodigo(),
                                    this.getFechaStringMediun(this.getSocioSel().getCodigoPersona().getPersonaNatural().getFechaNacimiento(), "/"),
                                    this.getSolicitud().getMontoCredito().longValue())) {
                                this.setMsg("No es posible crear una solicitud por el monto de " + this.getSolicitud().getMontoCredito() + " por la edad del socio.");
                                this.getSolicitud().setMontoCredito(null);
                                MuestraMensaje.addError(msg);
                                return;
                            }
                        }
                    }
                    solicitud.setCodigoTasa(listaTasaCredito.get(i));
                    solicitud.setTasa(formatoValor(listaTasaCredito.get(i).getCodigoTasa().getValor()));
                    tieneGarantias = listaTasaCredito.get(i).getTieneGarantias();
                    valido = true;
                    break;
                }
            }
        }
        if (valido) {
            solicitud.setTasa(this.formatoValor(solicitud.getTasa()));
            TipoGarantiaProCreMonIfi garantia = this.getEjbFacadeTipoGarantiaProCreMonIfi().getTipoGarantiaProCreMonIfiElim(solicitud.getCodigoTasa().getCodigo(), Long.parseLong("1"), 'N');
            itemsGarantiaCredito = this.getEjbFacadeTipoGarantiaProCreMonIfi().getTipoGarantiaProCreMonIfiTasaElim(solicitud.getCodigoTasa().getCodigo(), 'N');
            //System.out.println("Garantia " + garantia);
            if (garantia == null) {
                setGarantesRequeridos(0);
                this.setGarantiasObligatorias('N');
            } else if (garantia.getObligatorio() == 'N') {
                setGarantesRequeridos(garantia.getNumero());
                this.setGarantiasObligatorias('N');
            } else {
                setGarantesRequeridos(garantia.getNumero());
                this.setGarantiasObligatorias('S');
            }
        } else {
            solicitud.setTasa(this.formatoValor(new BigDecimal("0")));
            solicitud.setMontoCredito(BigDecimal.ZERO);
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MontoCreditoFueraDeRango"));
        }
        //Validar la base de ahorro
        String mensajeValidacion = ejbFacade.validaBaseAhorro(getCodigoSocio(),solicitud.getSolicitudPK().getCodigoIfip(), getSolicitud().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(), solicitud.getMontoCredito());
        if( mensajeValidacion != null ){
            solicitud.setTasa(this.formatoValor(new BigDecimal("0")));
            solicitud.setMontoCredito(BigDecimal.ZERO);
            MuestraMensaje.addError(mensajeValidacion);
            return;
        }
        autoGenerarTabla();
        validaVinculado();
    }

    /**
     * *
     * METODO PARA VALIDAR LOS MONTOS POR EDAD Y TIPO DE PERSONA
     */
    public boolean validaMonto(Long codigoTipoPersona, String fechaNacimiento, Long monto) {
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_creditos.pkg_parametro_monto_edad.p_valida");
            llamaSP.setNumeroParametros(4);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_persona", codigoTipoPersona});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pv_fecha_nacimiento", fechaNacimiento});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_monto", monto});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_es_valido", Types.VARCHAR});
            // Invocando al SP
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                String esValidoString = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
                if (esValidoString.equals("S")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                //MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                        new Object[]{"SolicitudController", "validaSocioCrearSolicitud", llamaSP.getErroSql()});
                return false;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"SolicitudController", "validaSocioCrearSolicitud", llamaSP.getErroSql()});
            return false;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    public boolean validaVinculado() {
        if (itemsPersonaVinculado != null && !itemsPersonaVinculado.isEmpty()) {
            if (solicitud.getMontoCredito().compareTo(montoMaximoVinculado) == 1) {
                MuestraMensaje.addAdvertencia("Monto del credito vinculado es mayor al maximo permitido");
                return false;
            }
        }
        return true;
    }

    public void validaNumeroCuotas() {
        try {
            if (!(this.solicitud.getNumeroCuotas() >= 1 && this.solicitud.getNumeroCuotas() <= cuotasMaximas)) {
                this.solicitud.setNumeroCuotas(0);
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuotasInvalido") + cuotasMaximas);
            }
        } catch (NumberFormatException e) {
            this.solicitud.setNumeroCuotas(0);
        }
        autoGenerarTabla();

    }

    //-----------------------------------------------------------------
    // IMPRESION DE DOCUMENTOS DE CONCECION
    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirPagare(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        //this.solicitudPagare = this.ejbFacadeSolicitudPagare.find(new SolicitudPagarePK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip()));
        ////System.out.println("numeroSolicitud: " + this.solicitud.getSolicitudPK().getNumero());
        if (this.getSolicitud().getEstadoCredito().getCodigo() != 6) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoDebeEstarConcedido") + cuotasMaximas);
            return;
        }
        SolicitudPagare solicitudPagare = this.ejbFacadeSolicitudPagare.find(new SolicitudPagarePK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip()));

        if (solicitudPagare == null) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PagareNoGenerado") + cuotasMaximas);
            return;
        }
        String nombreReporte;
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        //getGeneraReporte().getParametros().put("SUBREPORT_DIR", directoriReporte);
        getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantes"));

        nombreReporte = "pagare";

        getGeneraReporte().exporta(directoriReporte, nombreReporte,
                nombreReporte + solicitudPagare.getNumeroPagare() + "Solicutd" + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
        // Registrar el servicio financiero
        this.guardarServicioFinanciero(this.solicitud.getSolicitudPK().getNumero(), "imprimirPagare");
    }

    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirContrato(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (this.getSolicitud().getEstadoCredito().getCodigo() != 6) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoDebeEstarConcedido") + cuotasMaximas);
            return;
        }

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        ////System.out.println("Imprime el Contrato");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        getGeneraReporte().getParametros().put("subReporteListadoGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantesListado"));
        getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantes"));
        getGeneraReporte().getParametros().put("subReporteAceptacionRubro", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "aceptacionRubro"));

        nombreReporte = "contrato";

        getGeneraReporte().exporta("/financiero/creditos/ConcederCredito/reporte/", nombreReporte,
                nombreReporte + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
        // Registrar el servicio financiero
        this.guardarServicioFinanciero(this.solicitud.getSolicitudPK().getNumero(), "imprimirContrato");
    }

    /**
     * Imprime la concecion del credito
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirConcecion(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        if (this.getSolicitud().getEstadoCredito().getCodigo() != 6) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoDebeEstarConcedido") + cuotasMaximas);
            return;
        }
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        ////System.out.println("Imprime Concecion");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitud.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitud.getSolicitudPK().getCodigoIfip());
        String path = obtienePathReporte("tablaAmortizacion");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        } else {
            getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, path, "tablaAmortizacion"));
        }
        path = obtienePathReporte("aceptacionRubro");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteAceptacionRubro", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "aceptacionRubro"));
        } else {
            getGeneraReporte().getParametros().put("subReporteAceptacionRubro", getGeneraReporte().getSubReporte(externalContext, path, "aceptacionRubro"));
        }
        path = obtienePathReporte("garantes");
        if (path == null) {
            getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantes"));
        } else {
            getGeneraReporte().getParametros().put("subReporteGarantes", getGeneraReporte().getSubReporte(externalContext, path, "garantes"));
        }
        nombreReporte = "concecion";
        String pathReporte = obtienePathReporte(nombreReporte);
        if (pathReporte == null) {
            pathReporte = "/financiero/creditos/ConcederCredito/reporte/";
        }
        getGeneraReporte().exporta(pathReporte, nombreReporte,
                nombreReporte + this.solicitud.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
        // Registrar el servicio financiero
        this.guardarServicioFinanciero(this.solicitud.getSolicitudPK().getNumero(), "imprimirConcecion");
    }

    private String obtienePathReporte(String nombreReporte) {
        ParametroServidorIfip parametro = ejbFacadeParametroServidorIfip.find(new ParametroServidorIfipPK(9, ActivacionUsuario.getCodigoIfip()));
        if (parametro == null) {
            System.out.println("No existe directorio de reportes externos");
            return null;
        } else {
            String ruta = parametro.getValor();
            File archivo = new File(ruta + nombreReporte + ".jasper");
            if (archivo.exists()) {
                return ruta;
            } else {
                System.out.println("No existe el reporte " + nombreReporte + ".jasper" + " en el directorio configurado");
                return null;
            }
        }
    }

    /**
     * Imprime el informe tecnico del credito
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirInformeTecnico(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (this.getSolicitud().getEstadoCredito().getCodigo() != 6) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoDebeEstarConcedido") + cuotasMaximas);
            return;
        }
        String nombreReporte = null;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.getSolicitud().getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.getSolicitud().getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));
        getGeneraReporte().getParametros().put("subReporteListadoGarantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "garantesListado"));

        nombreReporte = "informeTecnico";

        getGeneraReporte().exporta("/financiero/creditos/informeTecnico/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.getSolicitud().getSolicitudPK().getNumero()) + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void imprimirSimulacion(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
//        if (this.getSolicitud().getEstadoCredito().getCodigo() != 6) {
//            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreditoDebeEstarConcedido") + cuotasMaximas);
//            return;
//        }
        String nombreReporte = null;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/solicitud/reporte/";

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());
        getGeneraReporte().getParametros().put("imagen_ifi", ActivacionUsuario.getUsuario().getCodigoIfip().getPathImagen().toString());
        //System.out.println("imagen : "+ActivacionUsuario.getUsuario().getCodigoIfip().getPathImagen().toString());
        //getGeneraReporte().getParametros().put("imagen_ifi",this.getClass().getResourceAsStream(ActivacionUsuario.getUsuario().getCodigoIfip().getPathImagen().toString()));
        getGeneraReporte().getParametros().put("ifip", ActivacionUsuario.getUsuario().getCodigoIfip().getNombre().toString());
        getGeneraReporte().getParametros().put("moneda", this.getSolicitud().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getNombre().toString());
        getGeneraReporte().getParametros().put("monto_credito", this.solicitud.getMontoCredito().toString());
        getGeneraReporte().getParametros().put("tasa", this.solicitud.getTasa().toString());
        getGeneraReporte().getParametros().put("producto", this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getNombre().toString());
        getGeneraReporte().getParametros().put("tipo_cartera", this.tipoCartera.getNombre().toString());
        getGeneraReporte().getParametros().put("periodicidad", this.solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getNombre().toString());
        getGeneraReporte().getParametros().put("cuotas", String.valueOf(this.solicitud.getNumeroCuotas()));
        getGeneraReporte().getParametros().put("recursos", this.solicitud.getOrigenRecursos().getNombre().toString().toUpperCase());
        getGeneraReporte().getParametros().put("tipo_tabla", String.valueOf(this.solicitud.getTipoTabla()).equals("C") ? "CAPITAL FIJO" : "CUOTA FIJA");
        getGeneraReporte().getParametros().put("dia_fijo", String.valueOf(this.solicitud.getDiaFijo()).equals("S") ? "SI" : "NO");
        getGeneraReporte().getParametros().put("observaciones", this.solicitud.getObservaciones().toString());
        getGeneraReporte().getParametros().put("subReportAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));

        ArrayList<HashMap> tablaAmortizacion = new ArrayList<HashMap>();
        for (i = 0; i < this.solicitud.getNumeroCuotas(); i++) {

            tablaAmortizacion.add(
                    new HashMap<String, String>() {
                {
                    put("cuota", String.valueOf(itemsTablaAmortizacion.get(i).getTablaAmortizacionPK().getCuota()));
                    put("fecha_inicio", new SimpleDateFormat("MM/dd/yyyy").format((Date) itemsTablaAmortizacion.get(i).getFechaInicio()));
                    put("fecha_pago", new SimpleDateFormat("MM/dd/yyyy").format((Date) itemsTablaAmortizacion.get(i).getFechaPago()));
                    put("saldo_capital", new DecimalFormat("#.##").format(itemsTablaAmortizacion.get(i).getSaldoCapital()).toString());
                    put("capital", new DecimalFormat("#.##").format(itemsTablaAmortizacion.get(i).getCapital()).toString());
                    put("interes", new DecimalFormat("#.##").format(itemsTablaAmortizacion.get(i).getInteres()).toString());
                    put("rubros", new DecimalFormat("#.##").format(itemsTablaAmortizacion.get(i).getRubros()).toString());
                    put("total", new DecimalFormat("#.##").format(itemsTablaAmortizacion.get(i).getTotal()).toString());
                }
            }
            );
        }

        //System.out.println(tablaAmortizacion.get(0));
        getGeneraReporte().getParametros().put("totalcapital", this.totalCapitalCadena);
        getGeneraReporte().getParametros().put("totalintereses", this.totalInteresCadena);
        getGeneraReporte().getParametros().put("totalrubros", this.totalRubrosCadena);
        getGeneraReporte().getParametros().put("totalcredito", this.totalCreditoCadena);
        getGeneraReporte().getParametros().put("tablaAmortizacion", tablaAmortizacion);
        try {
            nombreReporte = "solicitudCreditoSimulacion";
            //System.out.println("nombre del reporte : "+directoriReporte+""+nombreReporte);
            getGeneraReporte().exporta("/financiero/creditos/solicitud/reporte/", nombreReporte,
                    nombreReporte + ".pdf",
                    "PDF", externalContext, facesContext);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // ---------------------------------------------------------------------------
    // -- REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * Al cambiar el criterio de Consulta
     */
    public void cambiaCriterio() {
        this.itemsSolicitud = null;
        this.nombreSocio = null;
        this.criterio = null;
        this.deshabilitaBuscarSocioLista = true;
        if (this.buscarPor.equals("S")) {
            this.deshabilitaBuscarSocioLista = false;
        }
        //Solicitud externa persona externa
        this.solicitudExterna = false;
        if (this.buscarPor.equals("E")) {
            this.solicitudExterna = true;
        }
        //Solicitud externa persona externa
    }

    public static String getFechaStringMediun(Date fecha, String separador) {

        String fechaCadena = "";

        try {

            int dia = fecha.getDate();
            if (dia < 10) {
                fechaCadena = '0' + String.valueOf(dia);
            } else {
                fechaCadena = String.valueOf(dia);
            }

            fechaCadena = fechaCadena + separador;
            //System.out.println("fechaCadena 1 "+fechaCadena);

            int mes = fecha.getMonth() + 1;
            if (mes < 10) {
                fechaCadena += '0' + String.valueOf(mes);
            } else {
                fechaCadena += String.valueOf(mes);
            }

            //System.out.println("fechaCadena 2 "+fechaCadena);
            fechaCadena += separador;

            //System.out.println("fechaCadena 3 "+fechaCadena);
            fechaCadena += String.valueOf(fecha.getYear() + 1900);

        } catch (Exception ex) {

        }

        // System.out.println("fechaCadena "+fechaCadena);
        return fechaCadena;

    }

    public void autoGenerarTabla() {
        this.itemsTablaAmortizacion = new ArrayList<TablaAmortizacion>();
        this.totalCapitalCadena = "";
        this.totalInteresCadena = "";
        this.totalRubrosCadena = "";
        this.totalCreditoCadena = "";

        if (/*this.getActividadEconomicaReceptoraN1() != null &&*/solicitud.getMontoCredito() != null && solicitud.getTasa() != null && solicitud.getPeriodicidadProMonIfi().getPeriodicidad() != null && (solicitud.getTipoTabla() == 'C' || solicitud.getTipoTabla() == 'P') && (solicitud.getDiaFijo() == 'S' || solicitud.getDiaFijo() == 'N')) {
            if (solicitud.getMontoCredito().doubleValue() > 0 && solicitud.getTasa().doubleValue() > 0 && solicitud.getNumeroCuotas() > 0) {
                this.generarTablaAmort();
            }
        }
    }

    /**
     * Genera la Tabla de Amortizacion
     */
    @SuppressWarnings("empty-statement")
    public void generarTablaAmort() {
        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");
        fechaVencimiento = null;
        deshabilitaBotonDetalleRubrosCredito = true;
        cuotaMaximaCredito = new BigDecimal(0);

        this.cargarRubros();

        totalRubrosCredito = BigDecimal.ZERO;
        totalRubrosCreditoConcecion = BigDecimal.ZERO;

        // Calcula los Rubros que se deben cobrar al conceder el credito
        // mediante una nota de debito
        calculaRubrosConcecion();

        if (totalRubrosCredito.compareTo(BigDecimal.ZERO) > 0) {
            deshabilitaBotonDetalleRubrosCredito = false;
        }

        DecimalFormat formatoValores = new DecimalFormat("########0.00");

        this.itemsRubroTablaAmortizacion = new ArrayList<RubroTablaAmortizacion>();
        itemsTablaAmortizacion = new ArrayList<TablaAmortizacion>();

//        DecimalFormat formatoValores = new DecimalFormat("#.00");
        double monto = this.formatoValor(new BigDecimal(solicitud.getMontoCredito().doubleValue())).doubleValue();
        double saldoCapital = monto;
        double tasa = solicitud.getTasa().doubleValue();
        double interes = 0;
        double pago = 0;
        double capital = 0;
        double interesPorcentaje = 0;
        double totCapital = 0;
        double totInteres = 0;
        double totRubros = 0;
        double totCredito = 0;
        double rubros;
        this.cuotaMaxima = 0;
        Date fechaInicio = new Date();
        int numeroCuota = 0;
        int numCuotas = solicitud.getNumeroCuotas();
        boolean porMeses;
        int diasInteres;
        this.totalCapital = new BigDecimal("0.00");
        this.totalInteres = new BigDecimal("0.00");
        this.totalRubros = new BigDecimal("0.00");
        this.totalCredito = new BigDecimal("0.00");

        formatoValores = new DecimalFormat("###,###,##0.00");
        setTotalCapitalCadena(formatoValores.format(totalCapital.doubleValue()));
        setTotalInteresCadena(formatoValores.format(totalInteres.doubleValue()));
        setTotalRubrosCadena(formatoValores.format(totalRubros.doubleValue()));
        setTotalCreditoCadena(formatoValores.format(totalCredito.doubleValue()));

        formatoValores = new DecimalFormat("########0.00");
        Periodicidad period = solicitud.getPeriodicidadProMonIfi().getPeriodicidad();

        TablaAmortizacion item;

        ////System.out.println("Genera Tabla");
        if (monto > 0 && numCuotas > 0) {

            //this.cargarRubros();
            if (period.getEquivalenciaMeses() == 0) {
                //interesPorcentaje = period.getEquivalenciaDias() * tasa / 36000.0;//en dias
                porMeses = false;
            } else {
                //interesPorcentaje = period.getEquivalenciaMeses() * tasa / 1200.0;//en meses
                porMeses = true;
            }

            ////System.out.println("Periodicidad " + period.getEquivalenciaDias() + " " + period.getEquivalenciaMeses());
            // Obteniendo tasa 
            interesPorcentaje = tasa / 100 / 360;//en dias 
            Date fechaInicialTabla = new Date();

            if (solicitud.getTipoTabla() == 'C') {
                capital = this.formatoValor(new BigDecimal(monto / numCuotas)).doubleValue();
                ///Calculo de cuotas
                for (int i = 0; i < numCuotas; i++) {
                    if (i == numCuotas - 1) {
                        capital = saldoCapital;
                    }

                    item = new TablaAmortizacion();
                    numeroCuota++;

                    // ta = this.ejbFacadeTablaAmortizacion.find(new TablaAmortizacionPK(this.solicitud.getSolicitudPK().getNumero(), this.solicitud.getSolicitudPK().getCodigoIfip(), Long.parseLong(String.valueOf(numeroCuota))));
                    item.setTablaAmortizacionPK(new TablaAmortizacionPK());
                    item.getTablaAmortizacionPK().setCuota(numeroCuota);

                    if (porMeses) {

                        /*    item.setFechaInicio((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicio, period.getEquivalenciaMeses() * i) : agregaDias(fechaInicio, period.getEquivalenciaDias() * i));                        
                         item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicio, period.getEquivalenciaMeses() * i + 1) : agregaDias(fechaInicio, period.getEquivalenciaDias() * i + 1));*/
                        item.setFechaInicio(fechaInicio);
                        item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicialTabla, period.getEquivalenciaMeses() * (i + 1)) : agregaDias(fechaInicio, period.getEquivalenciaDias()));


                        /* item.setFechaInicio((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicio, period.getEquivalenciaMeses() * i) : agregaDias(fechaInicio, period.getEquivalenciaDias() * i));
                         item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicio, period.getEquivalenciaMeses() * (i + 1)) : agregaDias(fechaInicio, period.getEquivalenciaDias() * (i + 1)));*/
                        diasInteres = this.diferenciaEnDias2(item.getFechaInicio(), item.getFechaPago());//(this.getSolicitud().getDiaFijo() == 'S') ? this.diferenciaEnDias2(item.getFechaInicio(), item.getFechaPago()) : Integer.parseInt(String.valueOf(period.getEquivalenciaDias()));
                    } else {

                        item.setFechaInicio(fechaInicio);
                        item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias()));
                        /*item.setFechaInicio(agregaDias(fechaInicio, period.getEquivalenciaDias() * i));
                         item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias() * i + 1));*/


 /*item.setFechaInicio(agregaDias(fechaInicio, period.getEquivalenciaDias() * i));
                         item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias() * (i + 1)));*/
                        diasInteres = Integer.parseInt(String.valueOf(period.getEquivalenciaDias()));

                    }

                    // Colocando el Sando Capital
                    item.setSaldoCapital(formatoValor(new BigDecimal(saldoCapital)));

                    // Colocando el Capital
                    item.setCapital(formatoValor(new BigDecimal(capital)));

                    // Calculando el Interes
                    //////System.out.println("diasInteres "+diasInteres);
                    interes = this.formatoValor(new BigDecimal(saldoCapital * interesPorcentaje * diasInteres)).doubleValue();//(diasInteres + ((numeroCuota == 1) ? getDiasPago() : 0));
                    item.setInteres(new BigDecimal(interes));

                    //Colocando los Rubros ya generados en al tabla            
                    rubros = this.formatoValor(new BigDecimal(calculaRubrosCuota(monto, saldoCapital, numeroCuota, (item.getFechaPago().getTime() - item.getFechaInicio().getTime()) / (24 * 60 * 60 * 1000)))).doubleValue();
                    item.setRubros(new BigDecimal(rubros));

                    // Calculando el Total
                    pago = capital + interes;
                    item.setTotal(new BigDecimal(pago).add(item.getRubros()));
                    //Registra la cuota maxima a pagar
                    if (item.getTotal().doubleValue() > this.cuotaMaxima) {
                        this.cuotaMaxima = item.getTotal().doubleValue();
                    }

                    // Agrendo el Item en la Lista
                    this.itemsTablaAmortizacion.add(item);

                    // Colocando Totales
                    totCapital += item.getCapital().doubleValue();
                    totInteres += item.getInteres().doubleValue();
                    totRubros += item.getRubros().doubleValue();
                    totCredito += item.getTotal().doubleValue();

                    // Asignando el nuevo capital
                    saldoCapital -= capital;

                    // Asignando la proxima fecha de inicio
                    fechaInicio = item.getFechaPago();
                    fechaVencimiento = item.getFechaPago();

                    //Asignar la cuota maxima
                    if (item.getTotal().compareTo(cuotaMaximaCredito) > 0) {
                        cuotaMaximaCredito = item.getTotal();
                    }
                }
            } else if (solicitud.getTipoTabla() == 'P') {
                ////System.out.println("Genera Tabla Pago Fijo");
                pago = this.formatoValor(new BigDecimal(this.getPagoCuotaFija())).doubleValue();
                ////System.out.println("pago " + pago);
                for (int i = 0; i < numCuotas; i++) {

                    item = new TablaAmortizacion();

                    numeroCuota++;

                    item.setTablaAmortizacionPK(new TablaAmortizacionPK());
                    item.getTablaAmortizacionPK().setCuota(numeroCuota);

                    if (porMeses) {

                        /*item.setFechaInicio((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicio, period.getEquivalenciaMeses() * i) : agregaDias(fechaInicio, period.getEquivalenciaDias() * i));
                         item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicio, period.getEquivalenciaMeses() * i + 1) : agregaDias(fechaInicio, period.getEquivalenciaDias() * i + 1));*/
                        item.setFechaInicio(fechaInicio);
                        item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicialTabla, period.getEquivalenciaMeses() * (i + 1)) : agregaDias(fechaInicio, period.getEquivalenciaDias()));

                        //item.setFechaPago(agregaDias(item.getFechaPago(), (numeroCuota == 1) ? ((getDiasPago() != null) ? getDiasPago() : 0) : 0));
                        /*item.setFechaInicio(fechaInicio);
                         item.setFechaPago((this.getSolicitud().getDiaFijo() == 'S') ? agregaMes(fechaInicio, period.getEquivalenciaMeses() ) : agregaDias(fechaInicio, period.getEquivalenciaDias()));
                         */
                        diasInteres = this.diferenciaEnDias2(item.getFechaInicio(), item.getFechaPago());//(this.getSolicitud().getDiaFijo() == 'S') ? this.diferenciaEnDias2(item.getFechaInicio(), item.getFechaPago()) : Integer.parseInt(String.valueOf(period.getEquivalenciaDias()));
                    } else {

                        /*item.setFechaInicio(agregaDias(fechaInicio, period.getEquivalenciaDias() * i));
                         item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias() * i + 1));*/
                        item.setFechaInicio(fechaInicio);
                        item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias()));


                        /*item.setFechaInicio(agregaDias(fechaInicio, period.getEquivalenciaDias() * i));
                         item.setFechaPago(agregaDias(fechaInicio, period.getEquivalenciaDias() * (i + 1)));*/
                        diasInteres = Integer.parseInt(String.valueOf(period.getEquivalenciaDias()));
                        //item.setFechaPago(agregaDias(item.getFechaPago(), (numeroCuota == 1) ?  getDiasPago()  : 0));
                    }

                    // Colocando el Sando Capital
                    item.setSaldoCapital(new BigDecimal(saldoCapital));

                    // Calculando el Interes
                    //////System.out.println("diasInteres "+diasInteres);
                    interes = this.formatoValor(new BigDecimal(saldoCapital * interesPorcentaje * diasInteres)).doubleValue();//(diasInteres + ((numeroCuota == 1) ? getDiasPago() : 0));
                    item.setInteres(new BigDecimal(interes));

                    // Obteniendo el Capital
                    if (numCuotas == numeroCuota) {
                        capital = saldoCapital;
                    } else {
                        capital = pago - this.formatoValor(new BigDecimal(interes)).doubleValue();
                    }

                    item.setCapital(new BigDecimal((numCuotas == numeroCuota) ? saldoCapital : capital));

                    //Colocando los Rubros ya generados en al tabla               
                    rubros = calculaRubrosCuota(monto, saldoCapital, numeroCuota, (item.getFechaPago().getTime() - item.getFechaInicio().getTime()) / (24 * 60 * 60 * 1000));
                    item.setRubros(new BigDecimal(rubros));

                    //Sumando el total                    
                    item.setTotal(item.getCapital().add(item.getInteres()).add(item.getRubros()));
                    //Registra la cuota maxima a pagar
                    if (item.getTotal().doubleValue() > this.cuotaMaxima) {
                        this.cuotaMaxima = item.getTotal().doubleValue();
                    }

                    // Agregando a la Lista
                    this.itemsTablaAmortizacion.add(item);

                    // Colocando Totales
                    totCapital += item.getCapital().doubleValue();
                    totInteres += item.getInteres().doubleValue();
                    totRubros += item.getRubros().doubleValue();
                    totCredito += item.getTotal().doubleValue();

                    //Obteniendo el nuevo saldo capital
                    saldoCapital -= capital;

                    // Colocando la Fecha proxima de inicio de pago
                    fechaInicio = item.getFechaPago();
                    fechaVencimiento = item.getFechaPago();

                    //Asignar la cuota maxima
                    if (item.getTotal().compareTo(cuotaMaximaCredito) > 0) {
                        cuotaMaximaCredito = item.getTotal();
                    }
                }
            }

            totalCapital = this.formatoValor(new BigDecimal(totCapital));
            totalInteres = this.formatoValor(new BigDecimal(totInteres));
            totalRubros = this.formatoValor(new BigDecimal(totRubros));
            totalCredito = totalCapital.add(totalInteres).add(totalRubros);

            // Formateando valores para mostrar en la ventana los totales
//            formatoValores = new DecimalFormat("###,###,##0.00");
            setTotalCapitalCadena(this.formatoValor(new BigDecimal(totalCapital.doubleValue())).toString());
            setTotalInteresCadena(this.formatoValor(new BigDecimal(totalInteres.doubleValue())).toString());
            setTotalRubrosCadena(this.formatoValor(new BigDecimal(totalRubros.doubleValue())).toString());
            setTotalCreditoCadena(this.formatoValor(new BigDecimal(totalCredito.doubleValue())).toString());
        }

        context.execute("procesandoDlg.hide()");
    }

    public void obtieneMargenValorVinculado() {
        this.montoMaximoVinculado = BigDecimal.ZERO;
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_creditos.pkg_credito_vinculado.p_valor_margen");

            llamaSP.setNumeroParametros(1);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_valor", Types.DOUBLE});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                montoMaximoVinculado = new BigDecimal(llamaSP.getListResultado().get(0).toString());

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"concedeBean", "getPagoCuotaFija", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Obtiene el pago de la cuota fija, esto sirve para calculos de tabla de
     * Amortizacion de Pago Fijo.
     *
     * @return Pago
     */
    public double getPagoCuotaFija() {

        context = RequestContext.getCurrentInstance();

        double pagoCuota = 0;
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_obtiene_pago_cuota_fija");

            llamaSP.setNumeroParametros(5);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_tasa", this.getSolicitud().getTasa()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_dias_plazo", solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_cuotas", solicitud.getNumeroCuotas()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_monto_credito", solicitud.getMontoCredito()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_exponente", Types.DOUBLE});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                pagoCuota = Double.parseDouble(llamaSP.getListResultado().get(0).toString());

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"concedeBean", "getPagoCuotaFija", CapturaError.getErrorException(ex)});
        }

        return pagoCuota;
    }

    /**
     * Calcula los Rubros por cada Cuota
     *
     * @param monto MOnto del Credito
     * @param saldo Saldo del Capital
     * @param cuota NUmero de Cuota
     * @return Valor de rubro cargado a la cuota
     */
    private double calculaRubrosCuota(double monto, double saldo, long cuota, long dias) {
        double totalRubro = 0, itemRubro;
        RubroTablaAmortizacion rta;

        //DecimalFormat formatoValores = new DecimalFormat("########0.00");
        ////System.out.println("listaRubros " + this.listaRubros);
        for (TipoRubroCredito trc : this.itemsTipoRubroCredito) {
            rta = new RubroTablaAmortizacion();
            itemRubro = 0;
            if (trc.getCobradoEn() == 'D') {
                if (trc.getTipo() == 'V') {
                    //////Seguro en periodicidades mensuales y quincenales
                    if (solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 5) {
                        if (trc.getCobradoEn() == 'D') {
                            itemRubro = trc.getValor().doubleValue() / 30 * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias();
                        } else {
                            itemRubro = trc.getValor().doubleValue();
                        }
                    }
                } else if (trc.getTipo() == 'P') {
                    double porcentaje = trc.getValor().doubleValue() / 100;
                    if (trc.getCalculadoSobre() == 'M') {
                        itemRubro = monto * porcentaje / 360 * dias;
                    } else {
                        itemRubro = saldo * porcentaje / 360 * dias;
                    }
                }
                itemRubro = this.formatoValor(new BigDecimal(itemRubro)).doubleValue();
                totalRubro = totalRubro + itemRubro;

                //-- Colocando el detalle de la tabla de amortizacion
                rta.setRubroTablaAmortizacionPK(new RubroTablaAmortizacionPK(solicitud.getSolicitudPK().getNumero(), solicitud.getSolicitudPK().getCodigoIfip(), cuota, trc.getTipoRubro().getCodio()));
                for (TipoRubroProCreMonIfi trpcmi : listaRubros) {
                    if (trpcmi.getCodigoTipoRubro() == trc.getTipoRubro()) {
                        rta.setTipoRubroProCreMonIfi(trpcmi);
                    }
                    if (rta.getTipoRubroProCreMonIfi() != null) {
                        break;
                    }
                }
                rta.setValor(new BigDecimal(itemRubro));
                rta.setAbono(BigDecimal.ZERO);
                rta.setSaldo(rta.getValor());
                this.itemsRubroTablaAmortizacion.add(rta);

                //--- Colocando en totales del tipo del rubro del credito
                trc.setTotalRubroGrabado(trc.getTotalRubroGrabado().add(new BigDecimal(itemRubro)));

            }

        }

        this.totalRubrosCredito = totalRubrosCredito.add(new BigDecimal(totalRubro));
        totalRubrosCredito = this.formatoValor(totalRubrosCredito);
        return (totalRubro);
    }

    /**
     * Caluclo los trubos que tenga al credito al conceder al credito
     */
    private void calculaRubrosConcecion() {
        double rubro = 0;

        for (TipoRubroCredito trc : this.itemsTipoRubroCredito) {

            if (trc.getCobradoEn() != 'D') {
                if (trc.getTipo() == 'V') {
                    //////Seguro en periodicidades mensuales y quincenales
                    if (solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 5) {
                        if (trc.getCobradoEn() == 'D') {
                            rubro = trc.getValor().doubleValue() / 30 * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias();
                        } else {
                            rubro = trc.getValor().doubleValue();
                        }
                    }
                } else if (trc.getTipo() == 'P') {
                    double porcentaje = trc.getValor().doubleValue() / 100;
                    // Si es mnor a 12 meses se anuliza el rubro
                    if (this.solicitud.getNumeroCuotas() * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaMeses() < 12L) {

                        //rubro = ((solicitud.getMontoCredito().doubleValue() * porcentaje ) / 12) * this.solicitud.getNumeroCuotas();
                        rubro = solicitud.getMontoCredito().doubleValue() * porcentaje / 360 * this.solicitud.getNumeroCuotas() * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias();

                    } else {
                        rubro = solicitud.getMontoCredito().doubleValue() * porcentaje;
                    }
                }

                //--- Colocando en totales del tipo del rubro del credito
                trc.setTotalRubroGrabado(trc.getTotalRubroGrabado().add(new BigDecimal(rubro)));

                this.totalRubrosCredito = totalRubrosCredito.add(new BigDecimal(rubro));
                totalRubrosCreditoConcecion = totalRubrosCreditoConcecion.add(new BigDecimal(rubro));
            }
        }

        totalRubrosCredito = this.formatoValor(totalRubrosCredito);
        totalRubrosCreditoConcecion = this.formatoValor(totalRubrosCreditoConcecion);
    }

    /**
     * Cuando pulsa mostrar ver detalle rubro
     */
    public void calculaDetalleRubro() {
        this.itemsRubroTablaAmortizacionVer = new ArrayList<RubroTablaAmortizacion>();
        for (RubroTablaAmortizacion rta : itemsRubroTablaAmortizacion) {
            if (rta.getRubroTablaAmortizacionPK().getCuota() == this.cuotaSeleccionada.getTablaAmortizacionPK().getCuota()) {
                itemsRubroTablaAmortizacionVer.add(rta);

            }
        }

        this.totalRubroCuotaCadena = this.formatoValor(this.cuotaSeleccionada.getRubros()).toString();

    }

    public void calculaTotalRubroCredito() {
        this.cargarRubros();
        mapaTotalRubros = new HashMap<TipoRubroProCreMonIfi, Double>();
        for (TipoRubroProCreMonIfi item : listaRubros) {
            mapaTotalRubros.put(item, 0.0);
        }
        double monto = solicitud.getMontoCredito().doubleValue();
        for (TablaAmortizacion item : itemsTablaAmortizacion) {
            double saldo = item.getSaldoCapital().doubleValue();
            DecimalFormat formatoValores = new DecimalFormat("########0.00");
            TipoRubroProCreMonIfi rubro;
            double itemRubro;
            for (int i = 0; i < listaRubros.size(); i++) {
                rubro = listaRubros.get(i);
                itemRubro = 0;
                if (rubro.getTipo() == 'V') {
                    //////Seguro en periodicidades mensuales y quincenales
                    if (solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo().longValue() == 5) {
                        if (rubro.getCobradoEn() == 'D') {
                            itemRubro = rubro.getValor().doubleValue() / 30 * solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getEquivalenciaDias();
                        } else {
                            itemRubro = rubro.getValor().doubleValue();
                        }
                    }
                } else if (rubro.getTipo() == 'P') {
                    double porcentaje = rubro.getValor().doubleValue() / 100 / 360;
                    if (rubro.getCalculaSobre() == 'M') {
                        ////System.out.println(saldo + "*" + porcentaje);
                        itemRubro = monto * porcentaje;
                    } else if (rubro.getCalculaSobre() == 'S') {
                        ////System.out.println(saldo + "*" + porcentaje);
                        itemRubro = saldo * porcentaje;
                    }
                }
                itemRubro = Double.parseDouble(formatoValores.format(itemRubro));
                mapaTotalRubros.put(rubro, mapaTotalRubros.get(rubro) + itemRubro);
            }
        }

    }

    public BigDecimal getTotalCapital() {
        double total = 0;
        if (itemsTablaAmortizacion != null) {
            for (int i = 0; i < itemsTablaAmortizacion.size(); i++) {
                total = total + itemsTablaAmortizacion.get(i).getCapital().doubleValue();
            }
        }
        return this.formatoValor(new BigDecimal(total));
    }

    public BigDecimal getTotalInteres() {
        double total = 0;
        if (itemsTablaAmortizacion != null) {
            for (int i = 0; i < itemsTablaAmortizacion.size(); i++) {
                total = total + itemsTablaAmortizacion.get(i).getInteres().doubleValue();
            }
        }
        return this.formatoValor(new BigDecimal(total));
    }

    public BigDecimal getTotalRubros() {
        double total = 0;
        if (itemsTablaAmortizacion != null) {
            for (int i = 0; i < itemsTablaAmortizacion.size(); i++) {
                total = total + itemsTablaAmortizacion.get(i).getRubros().doubleValue();
            }
        }
        return this.formatoValor(new BigDecimal(total));
    }

    public BigDecimal getTotal() {
        double total = 0;
        if (itemsTablaAmortizacion != null) {
            for (int i = 0; i < itemsTablaAmortizacion.size(); i++) {
                total = total + itemsTablaAmortizacion.get(i).getTotal().doubleValue();
            }
        }
        return this.formatoValor(new BigDecimal(total));
    }

    private Date agregaMes(Date fecha, long meses) {
        ////System.out.println("MNeses "+meses);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, (int) meses);
        return calendar.getTime();
    }

    private Date agregaDias(Date fecha, long dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, (int) dias);
        return calendar.getTime();
    }

    private void cargarRubros() {
        this.setListaRubros(this.ejbFacadeTipoRubroProCreMonIfi.getItemsProCreMonIfi(
                solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo(),
                solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo(),
                ActivacionUsuario.getCodigoIfip(),
                'N'));

        /**
         * Colocando los tipos de rubros del credito
         */
        this.itemsTipoRubroCredito = new ArrayList<TipoRubroCredito>();
        TipoRubroCredito tipoRubroCredito;
        for (TipoRubroProCreMonIfi ifi : listaRubros) {
            tipoRubroCredito = new TipoRubroCredito();
            tipoRubroCredito.setCalculadoSobre(ifi.getCalculaSobre());
            tipoRubroCredito.setCobradoEn(ifi.getCobradoEn());
            tipoRubroCredito.setCodigoMovimiento(null);
            tipoRubroCredito.setSolicitud(solicitud);
            tipoRubroCredito.setTipo(ifi.getTipo());
            tipoRubroCredito.setTipoRubro(ifi.getCodigoTipoRubro());
            tipoRubroCredito.setTotalRubroGrabado(new BigDecimal("0.00"));
            tipoRubroCredito.setValor(ifi.getValor());
            itemsTipoRubroCredito.add(tipoRubroCredito);
        }

    }

    /**
     * Obtiene los dias de entre dos fechas
     *
     * @param fechaMayor Fecha Mayor
     * @param fechaMenor Fecha Menor (a restar)
     * @return Numero de Dias entre las dos fechas
     */
    public int diferenciaEnDias2(Date fechaMayor, Date fechaMenor) {
        Long diferenciaEn_ms = (fechaMayor.getTime() - fechaMenor.getTime());
        Long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        return (dias.intValue() < 0) ? -1 * dias.intValue() : dias.intValue();
    }

    public void muestraCuentas() {
        ////System.out.println("BEDITO " + this.cuentaDebito);
        ////System.out.println("CREDITO " + this.cuentaCredito);
    }

    public void cambiaUbiGeoPai() {

        this.setItemsUbiGeoPro(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPai()));
        this.setUbiGeoPro(null);
        this.setItemsUbiGeoCiu(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPro()));
        this.setUbiGeoCiu(null);
        this.setItemsUbiGeoPar(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiu()));
        this.setUbiGeoPar(null);
    }

    public void cambiaUbiGeoPro() {
        this.setItemsUbiGeoCiu(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPro()));
        this.setUbiGeoCiu(null);
        this.setItemsUbiGeoPar(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiu()));
        this.setUbiGeoPar(null);

    }

    public void cambiaUbiGeoCiu() {
        this.setItemsUbiGeoPar(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiu()));
        this.setUbiGeoPar(null);
    }

    public void guardaSolicitud(ActionEvent actionEvent) {
        if (esEdicion) {
            this.editaSolicitud(actionEvent);
            return;
        }
        this.solicitud.setCodigoEstado(1);
        RequestContext contexto = RequestContext.getCurrentInstance();

        try {
            contexto.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            ////System.out.println("INICIO GRADADO");
            if (this.guardaDatosSolicitud()) {
                ////System.out.println("GUARDADO 1");
                if (this.guardaTablaAmortizacion()) {
                    ////System.out.println("GUARDADO 3");
                    if (this.guardaRubrosTablaAmort()) {
                        ////System.out.println("GUARDADO 4");
                        if (this.guardaTipoGarantiaCred()) {
                            ////System.out.println("GUARDADO 5");
                            if (this.guardaGarantesCredito()) {
                                this.guardaTipoRubroCredito();
                            }
                        }
                    }
                }

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                this.obtieneSolicitudes();
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SolicitudGrabado");
                MuestraMensaje.addInformacion(msg);
                contexto.execute("procesandoDlg.hide()");
                contexto.execute("SolicitudNuevaDialog.hide()");
                contexto.execute("imprimeSolicitudDialog.show()");
                this.init();
            } else {
                contexto.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            contexto.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"solicitudController", "guardarSolicitud", CapturaError.getErrorException(ex)});
        }

    }

    public void editaSolicitud(ActionEvent actionEvent) {
        RequestContext contexto = RequestContext.getCurrentInstance();

        try {
            contexto.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            ////System.out.println("INICIO ACTUALIZACION");
            if (this.actualizaDatosSolicitud()) {
                ////System.out.println("BORRa 1");
                if (this.eliminaDatosSolicitud()) {
                    ////System.out.println("GUARDADO 3");
                    if (this.guardaTablaAmortizacion()) {
                        if (this.guardaTipoGarantiaCred()) {
                            ////System.out.println("GUARDADO 4");
                            if (this.guardaRubrosTablaAmort()) {
                                //System.out.println("GUARDADO 5");
                                if (this.guardaGarantesCredito()) {
                                    this.guardaTipoRubroCredito();
                                }
                            }
                        }
                    }
                }
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                this.obtieneSolicitudes();
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SolicitudGrabado");
                MuestraMensaje.addInformacion(msg);
                contexto.execute("procesandoDlg.hide()");
                contexto.execute("SolicitudNuevaDialog.hide()");
                contexto.execute("imprimeSolicitudDialog.show()");
                this.init();
            } else {
                contexto.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            contexto.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"solicitudController", "guardarSolicitud", CapturaError.getErrorException(ex)});
        }

    }

    private boolean guardaDatosSolicitud() {
        Date fecha = new java.sql.Timestamp(new Date().getTime());
        String tuvoRubros;
        if (this.getListaRubros().isEmpty()) {
            tuvoRubros = "N";
        } else {
            tuvoRubros = "S";
        }
        Long codUbiGeo;
        if (ubiGeoPro == null) {
            codUbiGeo = ubiGeoPai.getCodigo();
        } else if (ubiGeoCiu == null) {
            codUbiGeo = ubiGeoPro.getCodigo();
        } else if (ubiGeoPar == null) {
            codUbiGeo = ubiGeoCiu.getCodigo();
        } else {
            codUbiGeo = ubiGeoPar.getCodigo();
        }

        llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_registra_solicitud_credito");
        llamaSP.setNumeroParametros(26);

        llamaSP.setListParametrosEntrada(new ArrayList<>());

        llamaSP.setListParametrosEntrada(new ArrayList<>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.solicitud.getSocio().getSocioPK().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_producto", this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodicidad", this.solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_act_eco", ubicaActividadEconomica().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_solicitud", fecha});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tasa", this.solicitud.getCodigoTasa().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tasa", this.solicitud.getTasa()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_monto_credito", this.solicitud.getMontoCredito()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_dia_fijo", String.valueOf(this.solicitud.getDiaFijo())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo_tabla", String.valueOf(this.solicitud.getTipoTabla())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuotas", this.solicitud.getNumeroCuotas()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", Long.parseLong("1")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getUsuario().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ubi_geo", codUbiGeo});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fecha});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.solicitud.getObservaciones()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_acred", this.getItemsSolicitudDetalle().get(0).getCuentaAcreditada().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_deb", this.getItemsSolicitudDetalle().get(0).getCuentaDebito().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tuvo_rubros", tuvoRubros});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_origen_recursos", this.solicitud.getOrigenRecursos().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_destino_financiero", this.solicitud.getDestinoFinanciero().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_destino_especifico", this.solicitud.getDestinoEspecifico().getCodigo()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_numero", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.solicitud.getSolicitudPK().setNumero(Long.parseLong(llamaSP.getListResultado().get(0).toString()));
            this.solicitud.getSolicitudPK().setCodigoIfip(ActivacionUsuario.getCodigoIfip());
        }
        return llamaSP.isEjecucionCorrecta();
    }

    private boolean actualizaDatosSolicitud() {
        Date fecha = new java.sql.Timestamp(new Date().getTime());
        String tuvoRubros;
        if (this.getListaRubros().isEmpty()) {
            tuvoRubros = "N";
        } else {
            tuvoRubros = "S";
        }
        Long codUbiGeo;
        if (ubiGeoPro == null) {
            codUbiGeo = ubiGeoPai.getCodigo();
        } else if (ubiGeoCiu == null) {
            codUbiGeo = ubiGeoPro.getCodigo();
        } else if (ubiGeoPar == null) {
            codUbiGeo = ubiGeoCiu.getCodigo();
        } else {
            codUbiGeo = ubiGeoPar.getCodigo();
        }

        llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_actualiza_solicitud_credito");
        llamaSP.setNumeroParametros(24);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.solicitud.getSocio().getSocioPK().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_producto", this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.solicitud.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodicidad", this.solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_act_eco", ubicaActividadEconomica().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", fecha});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tasa", this.solicitud.getCodigoTasa().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tasa", this.solicitud.getTasa()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_monto_credito", this.solicitud.getMontoCredito()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_dia_fijo", String.valueOf(this.solicitud.getDiaFijo())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo_tabla", String.valueOf(this.solicitud.getTipoTabla())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuotas", this.solicitud.getNumeroCuotas()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ubi_geo", codUbiGeo});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.solicitud.getObservaciones()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_acred", this.getItemsSolicitudDetalle().get(0).getCuentaAcreditada().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_deb", this.getItemsSolicitudDetalle().get(0).getCuentaDebito().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tuvo_rubros", tuvoRubros});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_origen_recursos", this.solicitud.getOrigenRecursos().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_destino_financiero", this.solicitud.getDestinoFinanciero().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_destino_especifico", this.solicitud.getDestinoEspecifico().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero", this.solicitud.getSolicitudPK().getNumero()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    private boolean eliminaDatosSolicitud() {

        llamaSP.setNombreSP("mks_creditos.pkm_solicitud.p_elimina_datos_solicitud");
        llamaSP.setNumeroParametros(2);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero", this.solicitud.getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    private boolean guardaTablaAmortizacion() {

        llamaSP.setNombreSP("mks_creditos.pkm_tabla_amortizacion.p_inserta_item_tabla");
        llamaSP.setNumeroParametros(10);
        for (TablaAmortizacion item : this.getItemsTablaAmortizacion()) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota", item.getTablaAmortizacionPK().getCuota()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_inicio", new java.sql.Timestamp(item.getFechaInicio().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_pago", new java.sql.Timestamp(item.getFechaPago().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_capital", item.getSaldoCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", item.getCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes", item.getInteres()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_rubros", item.getRubros()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", item.getTotal()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Guarda los Rubrols de la Tabla de Amotizacion.
     *
     * @return
     */
    private boolean guardaRubrosTablaAmort() {

        llamaSP.setNombreSP("mks_creditos.pkm_rubro_tabla_amort.p_inserta_rubro_tab_amor");
        llamaSP.setNumeroParametros(5);
        for (RubroTablaAmortizacion rta : itemsRubroTablaAmortizacion) {
            ///Guarda el rubro Seguro Contigo Solo para mensuales y bimensuales
            if (!((rta.getTipoRubroProCreMonIfi().getCodigoTipoRubro().getCodio() == 6 || rta.getTipoRubroProCreMonIfi().getCodigoTipoRubro().getCodio() == 7)
                    && !(solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 5))) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuota", rta.getRubroTablaAmortizacionPK().getCuota()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_rubro", rta.getTipoRubroProCreMonIfi().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", rta.getValor()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el registro fue correcto
                if (!llamaSP.isEjecucionCorrecta()) {
                    return llamaSP.isEjecucionCorrecta();
                }
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    private boolean guardaTipoGarantiaCred() {
        if (this.getItemsGaranteCredito().isEmpty()) {
            return true;
        }
        Date fecha = new java.sql.Timestamp(new Date().getTime());

        llamaSP.setNombreSP("mks_creditos.pkm_tipo_garantia_credito.p_inserta_tipo_gar_cre");
        llamaSP.setNumeroParametros(6);
        for (TipoGarantiaProCreMonIfi item : this.itemsGarantiaCredito) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_garantia", item.getCodigoTipoGarantia().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", fecha});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", "N"});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                return llamaSP.isEjecucionCorrecta();
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Gaurda el Tipo de Rubro Grabado en el Credito
     *
     * @return
     */
    private boolean guardaTipoRubroCredito() {
        llamaSP.setNombreSP("mks_creditos.pkm_tipo_rubro_credito.p_inserta_rubro");
        llamaSP.setNumeroParametros(8);
        for (TipoRubroCredito trc : this.itemsTipoRubroCredito) {
            ///Guarda el rubro Seguro Contigo Solo para mensuales y bimensuales
            if (!((trc.getTipoRubro().getCodio() == 6 || trc.getTipoRubro().getCodio() == 7)
                    && !(solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 4 || solicitud.getPeriodicidadProMonIfi().getPeriodicidad().getCodigo() == 5))) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_rubro", trc.getTipoRubro().getCodio()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitud.getSolicitudPK().getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", String.valueOf(trc.getTipo())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_calculado_sobre", String.valueOf(trc.getCalculadoSobre())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobrado_en", String.valueOf(trc.getCobradoEn())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", trc.getValor()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_rubro_grabado", trc.getTotalRubroGrabado()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el registro fue correcto
                if (!llamaSP.isEjecucionCorrecta()) {
                    return llamaSP.isEjecucionCorrecta();
                }
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    private boolean guardaGarantesCredito() {
        Date fecha = new java.sql.Timestamp(new Date().getTime());
        llamaSP.setNombreSP("mks_creditos.pkm_garante_credito.p_inserta_garante");
        llamaSP.setNumeroParametros(10);
        for (GaranteCredito item : this.getItemsGaranteCredito()) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitud.getSolicitudPK().getNumero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"P_codigo_tipo_garantia", Long.parseLong("1")});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", item.getGarante().getCodigoPersona()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobertura_dada", item.getCoberturaDada()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", fecha});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_vigente", "S"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", "N"});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    private void guardarServicioFinanciero(long numeroCredito, String proceso) {
        // Registrar el servicio financiero para la F01 
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ifips.pkm_servicio_financiero.p_registra_servicio_financiero");
            llamaSP.setNumeroParametros(6);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_servicio_fin_tip_can", 8}); // EMISION DE TABLAS DE AMORTIZACION
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificador", numeroCredito});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cantidad", 1});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observacion", "Impresion desde " + proceso});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
            } else {
                System.out.println("Error la registrar el servicio financiero desde Consulta de Cuentas.");
                System.out.println(llamaSP.getError());
                System.out.println(llamaSP.getErroSql());
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"SolicitudController", "guardarServicioFinanciero", CapturaError.getErrorException(ex)});
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public int getMinimoGarantes() {
        if (this.garantiasObligatorias == 'S') {
            return this.garantesRequeridos;
        } else {
            return 0;
        }
    }

    public String getGarantesObligatorios() {
        if (this.garantiasObligatorias == 'S') {
            return "SI";
        } else {
            return "NO";
        }
    }

    public List<Moneda> getItemsMoneda() {
        return this.ejbFabaceMoneda.getItemsMonedas('N');
    }

    public List<Moneda> getAllItemsMoneda() {
        //return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
        return this.ejbFacadeProductoCreditoMonedaIfip.getItemsIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    public List<PersonaActividadEconomica> getItemsPerActEco() {
        return this.ejbFacadePersonaActividadEconomica.getItemsPerActEconMaxNivel(solicitud.getSocio().getCodigoPersona().getCodigo(), 'N');
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
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
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
     * @return the itemsPeriodicidad
     */
    public List<Periodicidad> getItemsPeriodicidad() {
        return itemsPeriodicidad;
    }

    /**
     * @param itemsPeriodicidad the itemsPeriodicidad to set
     */
    public void setItemsPeriodicidad(List<Periodicidad> itemsPeriodicidad) {
        this.itemsPeriodicidad = itemsPeriodicidad;
    }

    /**
     * @return the itemsProductoCredito
     */
    public List<ProductoCredito> getItemsProductoCredito() {
        return itemsProductoCredito;
    }

    /**
     * @param itemsProductoCredito the itemsProductoCredito to set
     */
    public void setItemsProductoCredito(List<ProductoCredito> itemsProductoCredito) {
        this.itemsProductoCredito = itemsProductoCredito;
    }

    /**
     * @return the itemsActividadEconomica
     */
    public List<ActividadEconomica> getItemsActividadEconomica() {
        return itemsActividadEconomica;
    }

    /**
     * @param itemsActividadEconomica the itemsActividadEconomica to set
     */
    public void setItemsActividadEconomica(List<ActividadEconomica> itemsActividadEconomica) {
        this.itemsActividadEconomica = itemsActividadEconomica;
    }

    /**
     * @return the itemsTablaAmortizacion
     */
    public List<TablaAmortizacion> getItemsTablaAmortizacion() {
        return itemsTablaAmortizacion;
    }

    /**
     * @param itemsTablaAmortizacion the itemsTablaAmortizacion to set
     */
    public void setItemsTablaAmortizacion(List<TablaAmortizacion> itemsTablaAmortizacion) {
        this.itemsTablaAmortizacion = itemsTablaAmortizacion;
    }

    /**
     * @return the personaConyugeSocio
     */
    public Persona getPersonaConyugeSocio() {
        return personaConyugeSocio;
    }

    /**
     * @param personaConyugeSocio the personaConyugeSocio to set
     */
    public void setPersonaConyugeSocio(Persona personaConyugeSocio) {
        this.personaConyugeSocio = personaConyugeSocio;
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

//    /**
//     * @return the deshabilitaEditarSocio
//     */
//    public boolean isDeshabilitaEditarSocio() {
//        return deshabilitaEditarSocio;
//    }
//
//    /**
//     * @param deshabilitaEditarSocio the deshabilitaEditarSocio to set
//     */
//    public void setDeshabilitaEditarSocio(boolean deshabilitaEditarSocio) {
//        this.deshabilitaEditarSocio = deshabilitaEditarSocio;
//    }
    /**
     * @return the deshabilitaSituacionEconomica
     */
    public boolean isDeshabilitaSituacionEconomica() {
        return deshabilitaSituacionEconomica;
    }

    /**
     * @param deshabilitaSituacionEconomica the deshabilitaSituacionEconomica to
     * set
     */
    public void setDeshabilitaSituacionEconomica(boolean deshabilitaSituacionEconomica) {
        this.deshabilitaSituacionEconomica = deshabilitaSituacionEconomica;
    }

    /**
     * @return the deshabilitaBuscarConyuge
     */
    public boolean isDeshabilitaBuscarConyuge() {
        return deshabilitaBuscarConyuge;
    }

    /**
     * @param deshabilitaBuscarConyuge the deshabilitaBuscarConyuge to set
     */
    public void setDeshabilitaBuscarConyuge(boolean deshabilitaBuscarConyuge) {
        this.deshabilitaBuscarConyuge = deshabilitaBuscarConyuge;
    }

    /**
     * @return the deshabilitaCrearConyuge
     */
    public boolean isDeshabilitaCrearConyuge() {
        return deshabilitaCrearConyuge;
    }

    /**
     * @param deshabilitaCrearConyuge the deshabilitaCrearConyuge to set
     */
    public void setDeshabilitaCrearConyuge(boolean deshabilitaCrearConyuge) {
        this.deshabilitaCrearConyuge = deshabilitaCrearConyuge;
    }

    /**
     * @return the deshabilitaEditarConyuge
     */
    public boolean isDeshabilitaEditarConyuge() {
        return deshabilitaEditarConyuge;
    }

    /**
     * @param deshabilitaEditarConyuge the deshabilitaEditarConyuge to set
     */
    public void setDeshabilitaEditarConyuge(boolean deshabilitaEditarConyuge) {
        this.deshabilitaEditarConyuge = deshabilitaEditarConyuge;
    }

    /**
     * @return the deshabilitaEliminarConyuge
     */
    public boolean isDeshabilitaEliminarConyuge() {
        return deshabilitaEliminarConyuge;
    }

    /**
     * @param deshabilitaEliminarConyuge the deshabilitaEliminarConyuge to set
     */
    public void setDeshabilitaEliminarConyuge(boolean deshabilitaEliminarConyuge) {
        this.deshabilitaEliminarConyuge = deshabilitaEliminarConyuge;
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
     * @return the identificacionConyuge
     */
    public String getIdentificacionConyuge() {
        return identificacionConyuge;
    }

    /**
     * @param identificacionConyuge the identificacionConyuge to set
     */
    public void setIdentificacionConyuge(String identificacionConyuge) {
        this.identificacionConyuge = identificacionConyuge;
    }

    /**
     * @return the deshabilitaTextoIdentificacionConyuge
     */
    public boolean isDeshabilitaTextoIdentificacionConyuge() {
        return deshabilitaTextoIdentificacionConyuge;
    }

    /**
     * @param deshabilitaTextoIdentificacionConyuge the
     * deshabilitaTextoIdentificacionConyuge to set
     */
    public void setDeshabilitaTextoIdentificacionConyuge(boolean deshabilitaTextoIdentificacionConyuge) {
        this.deshabilitaTextoIdentificacionConyuge = deshabilitaTextoIdentificacionConyuge;
    }

    /**
     * @return the mensajeSolicitud
     */
    public String getMensajeSolicitud() {
        return mensajeSolicitud;
    }

    /**
     * @param mensajeSolicitud the mensajeSolicitud to set
     */
    public void setMensajeSolicitud(String mensajeSolicitud) {
        this.mensajeSolicitud = mensajeSolicitud;
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
     * @return the itemsSolicitudDetalle
     */
    public List<SolicitudDetalle> getItemsSolicitudDetalle() {
        return itemsSolicitudDetalle;
    }

    /**
     * @param itemsSolicitudDetalle the itemsSolicitudDetalle to set
     */
    public void setItemsSolicitudDetalle(List<SolicitudDetalle> itemsSolicitudDetalle) {
        this.itemsSolicitudDetalle = itemsSolicitudDetalle;
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
     * @return the garanteCredito
     */
    public GaranteCredito getGaranteCredito() {
        return garanteCredito;
    }

    /**
     * @param garanteCredito the garanteCredito to set
     */
    public void setGaranteCredito(GaranteCredito garanteCredito) {
        this.garanteCredito = garanteCredito;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the identificacionGarante
     */
    public String getIdentificacionGarante() {
        return identificacionGarante;
    }

    /**
     * @param identificacionGarante the identificacionGarante to set
     */
    public void setIdentificacionGarante(String identificacionGarante) {
        this.identificacionGarante = identificacionGarante;
    }

    /**
     * @return the personaGarante
     */
    public Persona getPersonaGarante() {
        return personaGarante;
    }

    /**
     * @param personaGarante the personaGarante to set
     */
    public void setPersonaGarante(Persona personaGarante) {
        this.personaGarante = personaGarante;
    }

    /**
     * @return the personaConyugeGarante
     */
    public Persona getPersonaConyugeGarante() {
        return personaConyugeGarante;
    }

    /**
     * @param personaConyugeGarante the personaConyugeGarante to set
     */
    public void setPersonaConyugeGarante(Persona personaConyugeGarante) {
        this.personaConyugeGarante = personaConyugeGarante;
    }

    /**
     * @return the itemsPersonaConyuge
     */
    public List<PersonaConyuge> getItemsPersonaConyuge() {
        return itemsPersonaConyuge;
    }

    /**
     * @param itemsPersonaConyuge the itemsPersonaConyuge to set
     */
    public void setItemsPersonaConyuge(List<PersonaConyuge> itemsPersonaConyuge) {
        this.itemsPersonaConyuge = itemsPersonaConyuge;
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
     * @return the nombreConyugeBusqueda
     */
    public String getNombreConyugeBusqueda() {
        return nombreConyugeBusqueda;
    }

    /**
     * @param nombreConyugeBusqueda the nombreConyugeBusqueda to set
     */
    public void setNombreConyugeBusqueda(String nombreConyugeBusqueda) {
        this.nombreConyugeBusqueda = nombreConyugeBusqueda;
    }

    /**
     * @return the personaConyugeSel
     */
    public PersonaNatural getPersonaConyugeSel() {
        return personaConyugeSel;
    }

    /**
     * @param personaConyugeSel the personaConyugeSel to set
     */
    public void setPersonaConyugeSel(PersonaNatural personaConyugeSel) {
        this.personaConyugeSel = personaConyugeSel;
    }

    /**
     * @return the itemsPersonaNatural
     */
    public List<PersonaNatural> getItemsPersonaNatural() {
        return itemsPersonaNatural;
    }

    /**
     * @param itemsPersonaNatural the itemsPersonaNatural to set
     */
    public void setItemsPersonaNatural(List<PersonaNatural> itemsPersonaNatural) {
        this.itemsPersonaNatural = itemsPersonaNatural;
    }

    /**
     * @return the conyugeCorrecto
     */
    public boolean isConyugeCorrecto() {
        return conyugeCorrecto;
    }

    /**
     * @param conyugeCorrecto the conyugeCorrecto to set
     */
    public void setConyugeCorrecto(boolean conyugeCorrecto) {
        this.conyugeCorrecto = conyugeCorrecto;
    }

    /**
     * @return the itemsCuentas
     */
    public List<Cuenta> getItemsCuentas() {
        return itemsCuentas;
    }

    /**
     * @param itemsCuentas the itemsCuentas to set
     */
    public void setItemsCuentas(List<Cuenta> itemsCuentas) {
        this.itemsCuentas = itemsCuentas;
    }

    /**
     * @return the cuantaCombo
     */
    public Cuenta getCuentaCombo() {
        return cuentaCombo;
    }

    /**
     * @param cuentaCombo the cuantaCombo to set
     */
    public void setCuentaCombo(Cuenta cuentaCombo) {
        this.cuentaCombo = cuentaCombo;
    }

    /**
     * @return the ejbFacadeTasaInteresProCreMonIfi
     */
    public TasaInteresProCreMonIfiFacade getEjbFacadeTasaInteresProCreMonIfi() {
        return ejbFacadeTasaInteresProCreMonIfi;
    }

    /**
     * @param ejbFacadeTasaInteresProCreMonIfi the
     * ejbFacadeTasaInteresProCreMonIfi to set
     */
    public void setEjbFacadeTasaInteresProCreMonIfi(TasaInteresProCreMonIfiFacade ejbFacadeTasaInteresProCreMonIfi) {
        this.ejbFacadeTasaInteresProCreMonIfi = ejbFacadeTasaInteresProCreMonIfi;
    }

    /**
     * @return the ejbFacadeTipoRubroProCreMonIfi
     */
    public TipoRubroProCreMonIfiFacade getEjbFacadeTipoRubroProCreMonIfi() {
        return ejbFacadeTipoRubroProCreMonIfi;
    }

    /**
     * @param ejbFacadeTipoRubroProCreMonIfi the ejbFacadeTipoRubroProCreMonIfi
     * to set
     */
    public void setEjbFacadeTipoRubroProCreMonIfi(TipoRubroProCreMonIfiFacade ejbFacadeTipoRubroProCreMonIfi) {
        this.ejbFacadeTipoRubroProCreMonIfi = ejbFacadeTipoRubroProCreMonIfi;
    }

    /**
     * @return the listaRubros
     */
    public List<TipoRubroProCreMonIfi> getListaRubros() {
        return listaRubros;
    }

    /**
     * @param listaRubros the listaRubros to set
     */
    public void setListaRubros(List<TipoRubroProCreMonIfi> listaRubros) {
        this.listaRubros = listaRubros;
    }

    /**
     * @return the cuentaDebito
     */
    public Cuenta getCuentaDebito() {
        return cuentaDebito;
    }

    /**
     * @param cuentaDebito the cuentaDebito to set
     */
    public void setCuentaDebito(Cuenta cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }

    /**
     * @return the cuentaCredito
     */
    public Cuenta getCuentaCredito() {
        return cuentaCredito;
    }

    /**
     * @param cuentaCredito the cuentaCredito to set
     */
    public void setCuentaCredito(Cuenta cuentaCredito) {
        this.cuentaCredito = cuentaCredito;
    }

    /**
     * @return the itemsDetalleRubros
     */
    public List<String[]> getItemsDetalleRubros() {
        return itemsDetalleRubros;
    }

    /**
     * @param itemsDetalleRubros the itemsDetalleRubros to set
     */
    public void setItemsDetalleRubros(List<String[]> itemsDetalleRubros) {
        this.itemsDetalleRubros = itemsDetalleRubros;
    }

    /**
     * @return the cuotaSeleccionada
     */
    public TablaAmortizacion getCuotaSeleccionada() {
        return cuotaSeleccionada;
    }

    /**
     * @param cuotaSeleccionada the cuotaSeleccionada to set
     */
    public void setCuotaSeleccionada(TablaAmortizacion cuotaSeleccionada) {
        this.cuotaSeleccionada = cuotaSeleccionada;
    }

    /**
     * @return the identificacionConyugeGar
     */
    public String getIdentificacionConyugeGar() {
        return identificacionConyugeGar;
    }

    /**
     * @param identificacionConyugeGar the identificacionConyugeGar to set
     */
    public void setIdentificacionConyugeGar(String identificacionConyugeGar) {
        this.identificacionConyugeGar = identificacionConyugeGar;
    }

    /**
     * @return the deshabilitarBuscarConGar
     */
    public boolean isDeshabilitarBuscarConGar() {
        return deshabilitarBuscarConGar;
    }

    /**
     * @param deshabilitarBuscarConGar the deshabilitarBuscarConGar to set
     */
    public void setDeshabilitarBuscarConGar(boolean deshabilitarBuscarConGar) {
        this.deshabilitarBuscarConGar = deshabilitarBuscarConGar;
    }

    /**
     * @return the deshabilitarGuardarConGar
     */
    public boolean isDeshabilitarGuardarConGar() {
        return deshabilitarGuardarConGar;
    }

    /**
     * @param deshabilitarGuardarConGar the deshabilitarGuardarConGar to set
     */
    public void setDeshabilitarGuardarConGar(boolean deshabilitarGuardarConGar) {
        this.deshabilitarGuardarConGar = deshabilitarGuardarConGar;
    }

    /**
     * @return the deshabilitarEliminarConGar
     */
    public boolean isDeshabilitarEliminarConGar() {
        return deshabilitarEliminarConGar;
    }

    /**
     * @param deshabilitarEliminarConGar the deshabilitarEliminarConGar to set
     */
    public void setDeshabilitarEliminarConGar(boolean deshabilitarEliminarConGar) {
        this.deshabilitarEliminarConGar = deshabilitarEliminarConGar;
    }

    /**
     * @return the deshabilitaTextoIdentificacionConGar
     */
    public boolean isDeshabilitaTextoIdentificacionConGar() {
        return deshabilitaTextoIdentificacionConGar;
    }

    /**
     * @param deshabilitaTextoIdentificacionConGar the
     * deshabilitaTextoIdentificacionConGar to set
     */
    public void setDeshabilitaTextoIdentificacionConGar(boolean deshabilitaTextoIdentificacionConGar) {
        this.deshabilitaTextoIdentificacionConGar = deshabilitaTextoIdentificacionConGar;
    }

    /**
     * @return the totalCapitalCadena
     */
    public String getTotalCapitalCadena() {
        return totalCapitalCadena;
    }

    /**
     * @param totalCapitalCadena the totalCapitalCadena to set
     */
    public void setTotalCapitalCadena(String totalCapitalCadena) {
        this.totalCapitalCadena = totalCapitalCadena;
    }

    /**
     * @return the totalInteresCadena
     */
    public String getTotalInteresCadena() {
        return totalInteresCadena;
    }

    /**
     * @param totalInteresCadena the totalInteresCadena to set
     */
    public void setTotalInteresCadena(String totalInteresCadena) {
        this.totalInteresCadena = totalInteresCadena;
    }

    /**
     * @return the totalRubrosCadena
     */
    public String getTotalRubrosCadena() {
        return totalRubrosCadena;
    }

    /**
     * @param totalRubrosCadena the totalRubrosCadena to set
     */
    public void setTotalRubrosCadena(String totalRubrosCadena) {
        this.totalRubrosCadena = totalRubrosCadena;
    }

    /**
     * @return the totalCreditoCadena
     */
    public String getTotalCreditoCadena() {
        return totalCreditoCadena;
    }

    /**
     * @param totalCreditoCadena the totalCreditoCadena to set
     */
    public void setTotalCreditoCadena(String totalCreditoCadena) {
        this.totalCreditoCadena = totalCreditoCadena;
    }

    /**
     * @return the itemsSubsectorActividadEconomica
     */
    public List<SubsectorActividadEconomica> getItemsSubsectorActividadEconomica() {
        return itemsSubsectorActividadEconomica;
    }

    /**
     * @param itemsSubsectorActividadEconomica the
     * itemsSubsectorActividadEconomica to set
     */
    public void setItemsSubsectorActividadEconomica(List<SubsectorActividadEconomica> itemsSubsectorActividadEconomica) {
        this.itemsSubsectorActividadEconomica = itemsSubsectorActividadEconomica;
    }

    /**
     * @return the itemsSectorActividadEconomica
     */
    public List<SectorActividadEconomica> getItemsSectorActividadEconomica() {
        return itemsSectorActividadEconomica;
    }

    /**
     * @param itemsSectorActividadEconomica the itemsSectorActividadEconomica to
     * set
     */
    public void setItemsSectorActividadEconomica(List<SectorActividadEconomica> itemsSectorActividadEconomica) {
        this.itemsSectorActividadEconomica = itemsSectorActividadEconomica;
    }

    /**
     * @return the garantesRequeridos
     */
    public int getGarantesRequeridos() {
        return garantesRequeridos;
    }

    /**
     * @param garantesRequeridos the garantesRequeridos to set
     */
    public void setGarantesRequeridos(int garantesRequeridos) {
        this.garantesRequeridos = garantesRequeridos;
    }

    /**
     * @return the ejbFacadeSubsectorActividadEconomica
     */
    public SubsectorActividadEconomicaFacade getEjbFacadeSubsectorActividadEconomica() {
        return ejbFacadeSubsectorActividadEconomica;
    }

    /**
     * @param ejbFacadeSubsectorActividadEconomica the
     * ejbFacadeSubsectorActividadEconomica to set
     */
    public void setEjbFacadeSubsectorActividadEconomica(SubsectorActividadEconomicaFacade ejbFacadeSubsectorActividadEconomica) {
        this.ejbFacadeSubsectorActividadEconomica = ejbFacadeSubsectorActividadEconomica;
    }

    /**
     * @return the ejbFacadeTipoGarantiaProCreMonIfi
     */
    public TipoGarantiaProCreMonIfiFacade getEjbFacadeTipoGarantiaProCreMonIfi() {
        return ejbFacadeTipoGarantiaProCreMonIfi;
    }

    /**
     * @param ejbFacadeTipoGarantiaProCreMonIfi the
     * ejbFacadeTipoGarantiaProCreMonIfi to set
     */
    public void setEjbFacadeTipoGarantiaProCreMonIfi(TipoGarantiaProCreMonIfiFacade ejbFacadeTipoGarantiaProCreMonIfi) {
        this.ejbFacadeTipoGarantiaProCreMonIfi = ejbFacadeTipoGarantiaProCreMonIfi;
    }

    /**
     * @return the sectorActividadEconomica
     */
    public SectorActividadEconomica getSectorActividadEconomica() {
        return sectorActividadEconomica;
    }

    /**
     * @param sectorActividadEconomica the sectorActividadEconomica to set
     */
    public void setSectorActividadEconomica(SectorActividadEconomica sectorActividadEconomica) {
        this.sectorActividadEconomica = sectorActividadEconomica;
    }

    /**
     * @return the subsectorActividadEconomica
     */
    public SubsectorActividadEconomica getSubsectorActividadEconomica() {
        return subsectorActividadEconomica;
    }

    /**
     * @param subsectorActividadEconomica the subsectorActividadEconomica to set
     */
    public void setSubsectorActividadEconomica(SubsectorActividadEconomica subsectorActividadEconomica) {
        this.subsectorActividadEconomica = subsectorActividadEconomica;
    }

    /**
     * @return the ejbFacadeParametroGeneralCreditoIfip
     */
    public ParametroGeneralCreditoIfipFacade getEjbFacadeParametroGeneralCreditoIfip() {
        return ejbFacadeParametroGeneralCreditoIfip;
    }

    /**
     * @param ejbFacadeParametroGeneralCreditoIfip the
     * ejbFacadeParametroGeneralCreditoIfip to set
     */
    public void setEjbFacadeParametroGeneralCreditoIfip(ParametroGeneralCreditoIfipFacade ejbFacadeParametroGeneralCreditoIfip) {
        this.ejbFacadeParametroGeneralCreditoIfip = ejbFacadeParametroGeneralCreditoIfip;
    }

    /**
     * @return the tipoCartera
     */
    public TipoCartera getTipoCartera() {
        return tipoCartera;
    }

    /**
     * @param tipoCartera the tipoCartera to set
     */
    public void setTipoCartera(TipoCartera tipoCartera) {
        this.tipoCartera = tipoCartera;
    }

    /**
     * @return the itemsTipoCartera
     */
    public List<TipoCartera> getItemsTipoCartera() {
        return itemsTipoCartera;
    }

    /**
     * @param itemsTipoCartera the itemsTipoCartera to set
     */
    public void setItemsTipoCartera(List<TipoCartera> itemsTipoCartera) {
        this.itemsTipoCartera = itemsTipoCartera;
    }

    /**
     * @return the totalRubrosDetalle
     */
    public double getTotalRubrosDetalle() {
        return totalRubrosDetalle;
    }

    /**
     * @param totalRubrosDetalle the totalRubrosDetalle to set
     */
    public void setTotalRubrosDetalle(double totalRubrosDetalle) {
        this.totalRubrosDetalle = totalRubrosDetalle;
    }

    /**
     * @return the garantiasObligatorias
     */
    public char getGarantiasObligatorias() {
        return garantiasObligatorias;
    }

    /**
     * @param garantiasObligatorias the garantiasObligatorias to set
     */
    public void setGarantiasObligatorias(char garantiasObligatorias) {
        this.garantiasObligatorias = garantiasObligatorias;
    }

    /**
     * @return the nombreGaranteBusqueda
     */
    public String getNombreGaranteBusqueda() {
        return nombreGaranteBusqueda;
    }

    /**
     * @param nombreGaranteBusqueda the nombreGaranteBusqueda to set
     */
    public void setNombreGaranteBusqueda(String nombreGaranteBusqueda) {
        this.nombreGaranteBusqueda = nombreGaranteBusqueda;
    }

    /**
     * @return the garanteSel
     */
    public PersonaNatural getGaranteSel() {
        return garanteSel;
    }

    /**
     * @param garanteSel the garanteSel to set
     */
    public void setGaranteSel(PersonaNatural garanteSel) {
        this.garanteSel = garanteSel;
    }

    /**
     * @return the itemsGarantes
     */
    public List<PersonaNatural> getItemsGarantes() {
        return itemsGarantes;
    }

    /**
     * @param itemsGarantes the itemsGarantes to set
     */
    public void setItemsGarantes(List<PersonaNatural> itemsGarantes) {
        this.itemsGarantes = itemsGarantes;
    }

    /**
     * @return the itemsCuentasAhorrosVista
     */
    public List<Cuenta> getItemsCuentasAhorrosVista() {
        return itemsCuentasAhorrosVista;
    }

    /**
     * @param itemsCuentasAhorrosVista the itemsCuentasAhorrosVista to set
     */
    public void setItemsCuentasAhorrosVista(List<Cuenta> itemsCuentasAhorrosVista) {
        this.itemsCuentasAhorrosVista = itemsCuentasAhorrosVista;
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
     * @return the ubiGeoPai
     */
    public UbicacionGeografica getUbiGeoPai() {
        return ubiGeoPai;
    }

    /**
     * @param ubiGeoPai the ubiGeoPai to set
     */
    public void setUbiGeoPai(UbicacionGeografica ubiGeoPai) {
        this.ubiGeoPai = ubiGeoPai;
    }

    /**
     * @return the ubiGeoPro
     */
    public UbicacionGeografica getUbiGeoPro() {
        return ubiGeoPro;
    }

    /**
     * @param ubiGeoPro the ubiGeoPro to set
     */
    public void setUbiGeoPro(UbicacionGeografica ubiGeoPro) {
        this.ubiGeoPro = ubiGeoPro;
    }

    /**
     * @return the ubiGeoCiu
     */
    public UbicacionGeografica getUbiGeoCiu() {
        return ubiGeoCiu;
    }

    /**
     * @param ubiGeoCiu the ubiGeoCiu to set
     */
    public void setUbiGeoCiu(UbicacionGeografica ubiGeoCiu) {
        this.ubiGeoCiu = ubiGeoCiu;
    }

    /**
     * @return the ubiGeoPar
     */
    public UbicacionGeografica getUbiGeoPar() {
        return ubiGeoPar;
    }

    /**
     * @param ubiGeoPar the ubiGeoPar to set
     */
    public void setUbiGeoPar(UbicacionGeografica ubiGeoPar) {
        this.ubiGeoPar = ubiGeoPar;
    }

    /**
     * @return the itemsUbiGeoPai
     */
    public List<UbicacionGeografica> getItemsUbiGeoPai() {
        return itemsUbiGeoPai;
    }

    /**
     * @param itemsUbiGeoPai the itemsUbiGeoPai to set
     */
    public void setItemsUbiGeoPai(List<UbicacionGeografica> itemsUbiGeoPai) {
        this.itemsUbiGeoPai = itemsUbiGeoPai;
    }

    /**
     * @return the itemsUbiGeoPro
     */
    public List<UbicacionGeografica> getItemsUbiGeoPro() {
        return itemsUbiGeoPro;
    }

    /**
     * @param itemsUbiGeoPro the itemsUbiGeoPro to set
     */
    public void setItemsUbiGeoPro(List<UbicacionGeografica> itemsUbiGeoPro) {
        this.itemsUbiGeoPro = itemsUbiGeoPro;
    }

    /**
     * @return the itemsUbiGeoCiu
     */
    public List<UbicacionGeografica> getItemsUbiGeoCiu() {
        return itemsUbiGeoCiu;
    }

    /**
     * @param itemsUbiGeoCiu the itemsUbiGeoCiu to set
     */
    public void setItemsUbiGeoCiu(List<UbicacionGeografica> itemsUbiGeoCiu) {
        this.itemsUbiGeoCiu = itemsUbiGeoCiu;
    }

    /**
     * @return the itemsUbiGeoPar
     */
    public List<UbicacionGeografica> getItemsUbiGeoPar() {
        return itemsUbiGeoPar;
    }

    /**
     * @param itemsUbiGeoPar the itemsUbiGeoPar to set
     */
    public void setItemsUbiGeoPar(List<UbicacionGeografica> itemsUbiGeoPar) {
        this.itemsUbiGeoPar = itemsUbiGeoPar;
    }

    /**
     * @return the ejbFacadeUbiGeo
     */
    public UbicacionGeograficaFacade getEjbFacadeUbiGeo() {
        return ejbFacadeUbiGeo;
    }

    /**
     * @param ejbFacadeUbiGeo the ejbFacadeUbiGeo to set
     */
    public void setEjbFacadeUbiGeo(UbicacionGeograficaFacade ejbFacadeUbiGeo) {
        this.ejbFacadeUbiGeo = ejbFacadeUbiGeo;
    }

    /**
     * @return the coberturaGarante
     */
    public double getCoberturaGarante() {
        return coberturaGarante;
    }

    /**
     * @param coberturaGarante the coberturaGarante to set
     */
    public void setCoberturaGarante(double coberturaGarante) {
        this.coberturaGarante = coberturaGarante;
    }

    /**
     * @return the tipoCuentaSel
     */
    public String getTipoCuentaSel() {
        return tipoCuentaSel;
    }

    /**
     * @param tipoCuentaSel the tipoCuentaSel to set
     */
    public void setTipoCuentaSel(String tipoCuentaSel) {
        this.tipoCuentaSel = tipoCuentaSel;
    }

    /**
     * @return the itemsOrigenRecursos
     */
    public List<OrigenRecursos> getItemsOrigenRecursos() {
        return itemsOrigenRecursos;
    }

    /**
     * @param itemsOrigenRecursos the itemsOrigenRecursos to set
     */
    public void setItemsOrigenRecursos(List<OrigenRecursos> itemsOrigenRecursos) {
        this.itemsOrigenRecursos = itemsOrigenRecursos;
    }

    /**
     * @return the itemsDestinoFinanciero
     */
    public List<DestinoFinanciero> getItemsDestinoFinanciero() {
        return itemsDestinoFinanciero;
    }

    /**
     * @param itemsDestinoFinanciero the itemsDestinoFinanciero to set
     */
    public void setItemsDestinoFinanciero(List<DestinoFinanciero> itemsDestinoFinanciero) {
        this.itemsDestinoFinanciero = itemsDestinoFinanciero;
    }

    /**
     * @return the itemsTipoRubroCredito
     */
    public List<TipoRubroCredito> getItemsTipoRubroCredito() {
        return itemsTipoRubroCredito;
    }

    /**
     * @param itemsTipoRubroCredito the itemsTipoRubroCredito to set
     */
    public void setItemsTipoRubroCredito(List<TipoRubroCredito> itemsTipoRubroCredito) {
        this.itemsTipoRubroCredito = itemsTipoRubroCredito;
    }

    /**
     * @return the itemsRubroTablaAmortizacion
     */
    public List<RubroTablaAmortizacion> getItemsRubroTablaAmortizacion() {
        return itemsRubroTablaAmortizacion;
    }

    /**
     * @param itemsRubroTablaAmortizacion the itemsRubroTablaAmortizacion to set
     */
    public void setItemsRubroTablaAmortizacion(List<RubroTablaAmortizacion> itemsRubroTablaAmortizacion) {
        this.itemsRubroTablaAmortizacion = itemsRubroTablaAmortizacion;
    }

    /**
     * @return the rubroTablaAmortizacion
     */
    public RubroTablaAmortizacion getRubroTablaAmortizacion() {
        return rubroTablaAmortizacion;
    }

    /**
     * @param rubroTablaAmortizacion the rubroTablaAmortizacion to set
     */
    public void setRubroTablaAmortizacion(RubroTablaAmortizacion rubroTablaAmortizacion) {
        this.rubroTablaAmortizacion = rubroTablaAmortizacion;
    }

    /**
     * @return the itemsRubroTablaAmortizacionVer
     */
    public List<RubroTablaAmortizacion> getItemsRubroTablaAmortizacionVer() {
        return itemsRubroTablaAmortizacionVer;
    }

    /**
     * @param itemsRubroTablaAmortizacionVer the itemsRubroTablaAmortizacionVer
     * to set
     */
    public void setItemsRubroTablaAmortizacionVer(List<RubroTablaAmortizacion> itemsRubroTablaAmortizacionVer) {
        this.itemsRubroTablaAmortizacionVer = itemsRubroTablaAmortizacionVer;
    }

    /**
     * @return the totalRubroCuotaCadena
     */
    public String getTotalRubroCuotaCadena() {
        return totalRubroCuotaCadena;
    }

    /**
     * @param totalRubroCuotaCadena the totalRubroCuotaCadena to set
     */
    public void setTotalRubroCuotaCadena(String totalRubroCuotaCadena) {
        this.totalRubroCuotaCadena = totalRubroCuotaCadena;
    }

    /**
     * @return the totalRubrosCredito
     */
    public BigDecimal getTotalRubrosCredito() {
        return totalRubrosCredito;
    }

    /**
     * @param totalRubrosCredito the totalRubrosCredito to set
     */
    public void setTotalRubrosCredito(BigDecimal totalRubrosCredito) {
        this.totalRubrosCredito = totalRubrosCredito;
    }

    /**
     * @return the totalRubrosCreditoConcecion
     */
    public BigDecimal getTotalRubrosCreditoConcecion() {
        return totalRubrosCreditoConcecion;
    }

    /**
     * @param totalRubrosCreditoConcecion the totalRubrosCreditoConcecion to set
     */
    public void setTotalRubrosCreditoConcecion(BigDecimal totalRubrosCreditoConcecion) {
        this.totalRubrosCreditoConcecion = totalRubrosCreditoConcecion;
    }

    /**
     * @return the deshabilitaBotonDetalleRubrosCredito
     */
    public boolean isDeshabilitaBotonDetalleRubrosCredito() {
        return deshabilitaBotonDetalleRubrosCredito;
    }

    /**
     * @param deshabilitaBotonDetalleRubrosCredito the
     * deshabilitaBotonDetalleRubrosCredito to set
     */
    public void setDeshabilitaBotonDetalleRubrosCredito(boolean deshabilitaBotonDetalleRubrosCredito) {
        this.deshabilitaBotonDetalleRubrosCredito = deshabilitaBotonDetalleRubrosCredito;
    }

    /**
     * @return the itemsActividadEconomicaN1
     */
    public List<ActividadEconomica> getItemsActividadEconomicaN1() {
        return itemsActividadEconomicaN1;
    }

    /**
     * @param itemsActividadEconomicaN1 the itemsActividadEconomicaN1 to set
     */
    public void setItemsActividadEconomicaN1(List<ActividadEconomica> itemsActividadEconomicaN1) {
        this.itemsActividadEconomicaN1 = itemsActividadEconomicaN1;
    }

    /**
     * @return the itemsActividadEconomicaN2
     */
    public List<ActividadEconomica> getItemsActividadEconomicaN2() {
        return itemsActividadEconomicaN2;
    }

    /**
     * @param itemsActividadEconomicaN2 the itemsActividadEconomicaN2 to set
     */
    public void setItemsActividadEconomicaN2(List<ActividadEconomica> itemsActividadEconomicaN2) {
        this.itemsActividadEconomicaN2 = itemsActividadEconomicaN2;
    }

    /**
     * @return the itemsActividadEconomicaN3
     */
    public List<ActividadEconomica> getItemsActividadEconomicaN3() {
        return itemsActividadEconomicaN3;
    }

    /**
     * @param itemsActividadEconomicaN3 the itemsActividadEconomicaN3 to set
     */
    public void setItemsActividadEconomicaN3(List<ActividadEconomica> itemsActividadEconomicaN3) {
        this.itemsActividadEconomicaN3 = itemsActividadEconomicaN3;
    }

    /**
     * @return the itemsActividadEconomicaN4
     */
    public List<ActividadEconomica> getItemsActividadEconomicaN4() {
        return itemsActividadEconomicaN4;
    }

    /**
     * @param itemsActividadEconomicaN4 the itemsActividadEconomicaN4 to set
     */
    public void setItemsActividadEconomicaN4(List<ActividadEconomica> itemsActividadEconomicaN4) {
        this.itemsActividadEconomicaN4 = itemsActividadEconomicaN4;
    }

    /**
     * @return the itemsActividadEconomicaN5
     */
    public List<ActividadEconomica> getItemsActividadEconomicaN5() {
        return itemsActividadEconomicaN5;
    }

    /**
     * @param itemsActividadEconomicaN5 the itemsActividadEconomicaN5 to set
     */
    public void setItemsActividadEconomicaN5(List<ActividadEconomica> itemsActividadEconomicaN5) {
        this.itemsActividadEconomicaN5 = itemsActividadEconomicaN5;
    }

    /**
     * @return the itemsActividadEconomicaN6
     */
    public List<ActividadEconomica> getItemsActividadEconomicaN6() {
        return itemsActividadEconomicaN6;
    }

    /**
     * @param itemsActividadEconomicaN6 the itemsActividadEconomicaN6 to set
     */
    public void setItemsActividadEconomicaN6(List<ActividadEconomica> itemsActividadEconomicaN6) {
        this.itemsActividadEconomicaN6 = itemsActividadEconomicaN6;
    }

    /**
     * @return the ActividadEconomicaReceptora
     */
    public ActividadEconomica getActividadEconomicaReceptoraN1() {
        return actividadEconomicaReceptoraN1;
    }

    /**
     * @param ActividadEconomicaReceptoraN1 the ActividadEconomicaReceptora to
     * set
     */
    public void setActividadEconomicaReceptoraN1(ActividadEconomica ActividadEconomicaReceptoraN1) {
        this.actividadEconomicaReceptoraN1 = ActividadEconomicaReceptoraN1;
    }

    /**
     * @return the ActividadEconomicaReceptoraN2
     */
    public ActividadEconomica getActividadEconomicaReceptoraN2() {
        return actividadEconomicaReceptoraN2;
    }

    /**
     * @param ActividadEconomicaReceptoraN2 the ActividadEconomicaReceptoraN2 to
     * set
     */
    public void setActividadEconomicaReceptoraN2(ActividadEconomica ActividadEconomicaReceptoraN2) {
        this.actividadEconomicaReceptoraN2 = ActividadEconomicaReceptoraN2;
    }

    /**
     * @return the ActividadEconomicaReceptoraN3
     */
    public ActividadEconomica getActividadEconomicaReceptoraN3() {
        return actividadEconomicaReceptoraN3;
    }

    /**
     * @param ActividadEconomicaReceptoraN3 the ActividadEconomicaReceptoraN3 to
     * set
     */
    public void setActividadEconomicaReceptoraN3(ActividadEconomica ActividadEconomicaReceptoraN3) {
        this.actividadEconomicaReceptoraN3 = ActividadEconomicaReceptoraN3;
    }

    /**
     * @return the ActividadEconomicaReceptoraN4
     */
    public ActividadEconomica getActividadEconomicaReceptoraN4() {
        return actividadEconomicaReceptoraN4;
    }

    /**
     * @param ActividadEconomicaReceptoraN4 the ActividadEconomicaReceptoraN4 to
     * set
     */
    public void setActividadEconomicaReceptoraN4(ActividadEconomica ActividadEconomicaReceptoraN4) {
        this.actividadEconomicaReceptoraN4 = ActividadEconomicaReceptoraN4;
    }

    /**
     * @return the ActividadEconomicaReceptoraN5
     */
    public ActividadEconomica getActividadEconomicaReceptoraN5() {
        return actividadEconomicaReceptoraN5;
    }

    /**
     * @param ActividadEconomicaReceptoraN5 the ActividadEconomicaReceptoraN5 to
     * set
     */
    public void setActividadEconomicaReceptoraN5(ActividadEconomica ActividadEconomicaReceptoraN5) {
        this.actividadEconomicaReceptoraN5 = ActividadEconomicaReceptoraN5;
    }

    /**
     * @return the ActividadEconomicaReceptoraN6
     */
    public ActividadEconomica getActividadEconomicaReceptoraN6() {
        return actividadEconomicaReceptoraN6;
    }

    /**
     * @param ActividadEconomicaReceptoraN6 the ActividadEconomicaReceptoraN6 to
     * set
     */
    public void setActividadEconomicaReceptoraN6(ActividadEconomica ActividadEconomicaReceptoraN6) {
        this.actividadEconomicaReceptoraN6 = ActividadEconomicaReceptoraN6;
    }

    /**
     * @return the ejbFacadeValorSeguro
     */
    public ValorSeguroFacade getEjbFacadeValorSeguro() {
        return ejbFacadeValorSeguro;
    }

    /**
     * @param ejbFacadeValorSeguro the ejbFacadeValorSeguro to set
     */
    public void setEjbFacadeValorSeguro(ValorSeguroFacade ejbFacadeValorSeguro) {
        this.ejbFacadeValorSeguro = ejbFacadeValorSeguro;
    }

    /**
     * @return the valorSeguro
     */
    public ValorSeguro getValorSeguro() {
        return valorSeguro;
    }

    /**
     * @param valorSeguro the valorSeguro to set
     */
    public void setValorSeguro(ValorSeguro valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    /**
     * Permite validar por nombres o por identificacion
     *
     * @return devuelve falso si es una persona sentenciada y verdadero homonimo
     * o pps
     */
    public boolean validacionUAF(Persona personaEncontrada) {
        boolean estadoValidacionNombres = true;
        if (personaEncontrada == null) {
            return estadoValidacionNombres;
        }
        ListasUAFEComponente listasUAFEComponente = new ListasUAFEComponente();
        listasUAFEComponente.setEjbFacadePersona(ejbFacadePersona);
        listasUAFEComponente.setEjbUsuarioFacade(ejbUsuarioFacade);
        listasUAFEComponente.setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloCredito"));
        setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloCredito"));

        try {
            if (personaEncontrada.getNombreCompleto() != null) {
                if (!personaEncontrada.getNombreCompleto().isEmpty()) {
                    listasUAFEComponente.validaUAFPorIdentificacionONombres(personaEncontrada.getNombreCompleto(), true);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "+++++++++++++++++++++++++++++++++++++++ 4 Error");
        }
        return listasUAFEComponente.getTipoPersona() == ListasUAFEComponente.SENTENCIADO;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    /**
     * @return the solicitudExterna
     */
    public boolean isSolicitudExterna() {
        return solicitudExterna;
    }

    /**
     * @param solicitudExterna the solicitudExterna to set
     */
    public void setSolicitudExterna(boolean solicitudExterna) {
        this.solicitudExterna = solicitudExterna;
    }

    /**
     * @return the informacionActualizada
     */
    public String getInformacionActualizada() {
        return informacionActualizada;
    }

    /**
     * @param informacionActualizada the informacionActualizada to set
     */
    public void setInformacionActualizada(String informacionActualizada) {
        this.informacionActualizada = informacionActualizada;
    }

    /**
     * @return the ejbProductoCreditoTipoPersonaFacade
     */
    public ProductoCreditoTipoPersonaFacade getEjbProductoCreditoTipoPersonaFacade() {
        return ejbProductoCreditoTipoPersonaFacade;
    }

    /**
     * @param ejbProductoCreditoTipoPersonaFacade the
     * ejbProductoCreditoTipoPersonaFacade to set
     */
    public void setEjbProductoCreditoTipoPersonaFacade(ProductoCreditoTipoPersonaFacade ejbProductoCreditoTipoPersonaFacade) {
        this.ejbProductoCreditoTipoPersonaFacade = ejbProductoCreditoTipoPersonaFacade;
    }

    /**
     * @return the ejbPersonaFacade
     */
    public PersonaFacade getEjbPersonaFacade() {
        return ejbPersonaFacade;
    }

    /**
     * @param ejbPersonaFacade the ejbPersonaFacade to set
     */
    public void setEjbPersonaFacade(PersonaFacade ejbPersonaFacade) {
        this.ejbPersonaFacade = ejbPersonaFacade;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the listaDestinoEspecifico
     */
    public List<DestinoEspecifico> getListaDestinoEspecifico() {
        return listaDestinoEspecifico;
    }

    /**
     * @param listaDestinoEspecifico the listaDestinoEspecifico to set
     */
    public void setListaDestinoEspecifico(List<DestinoEspecifico> listaDestinoEspecifico) {
        this.listaDestinoEspecifico = listaDestinoEspecifico;
    }

    /**
     * @return the itemsPersonaVinculado
     */
    public List<Persona> getItemsPersonaVinculado() {
        return itemsPersonaVinculado;
    }

    /**
     * @param itemsPersonaVinculado the itemsPersonaVinculado to set
     */
    public void setItemsPersonaVinculado(List<Persona> itemsPersonaVinculado) {
        this.itemsPersonaVinculado = itemsPersonaVinculado;
    }

    /**
     * @return the montoMaximoVinculado
     */
    public BigDecimal getMontoMaximoVinculado() {
        return montoMaximoVinculado;
    }

    /**
     * @param montoMaximoVinculado the montoMaximoVinculado to set
     */
    public void setMontoMaximoVinculado(BigDecimal montoMaximoVinculado) {
        this.montoMaximoVinculado = montoMaximoVinculado;
    }

    public boolean isMargenVinculadoDisponible() {
        return margenVinculadoDisponible;
    }

    public void setMargenVinculadoDisponible(boolean margenVinculadoDisponible) {
        this.margenVinculadoDisponible = margenVinculadoDisponible;
    }
}
