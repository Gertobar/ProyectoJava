/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.socios.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormularioAdi;
import ec.renafipse.mks.modelo.comunes.Empresa;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Cargo;
import ec.renafipse.mks.modelo.socios.ConocimientoIfip;
import ec.renafipse.mks.modelo.socios.EstadoSocio;
import ec.renafipse.mks.modelo.socios.ItemFlujoCaja;
import ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonial;
import ec.renafipse.mks.modelo.socios.MotivoSocio;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaActividadEconomica;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.modelo.socios.PersonaTrabajoActEco;
import ec.renafipse.mks.modelo.socios.Sector;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioFlujoCaja;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgreso;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgresoPK;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngreso;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngresoPK;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaPK;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatAct;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatActPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatPas;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatPasPK;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonialPK;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import ec.renafipse.mks.negocio.comunes.EmpresaFacade;
import ec.renafipse.mks.negocio.comunes.PeriodicidadFacade;
import ec.renafipse.mks.negocio.comunes.TipoTelefonoFacade;
import ec.renafipse.mks.negocio.comunes.UbicacionGeograficaFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.socios.CargoFacade;
import ec.renafipse.mks.negocio.socios.ConocimientoIfipFacade;
import ec.renafipse.mks.negocio.socios.EstadoSocioFacade;
import ec.renafipse.mks.negocio.socios.ItemFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.ItemSituacionPatrimonialFacade;
import ec.renafipse.mks.negocio.socios.MotivoSocioFacade;
import ec.renafipse.mks.negocio.socios.PersonaActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.socios.PersonaTrabajoActEcoFacade;
import ec.renafipse.mks.negocio.socios.SectorFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaEgresoFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaFacade;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaIngresoFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatActFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatPasFacade;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
import ec.renafipse.mks.negocio.socios.TipoRelacionFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import oracle.jdbc.OracleTypes;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import ec.renafipse.mks.control.socios.bean.SocioBean;
import java.util.Iterator;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;

/**
 *
 * @author vicastoc
 */
@ManagedBean(name = "situacionEconomicaBean")
@ViewScoped
public class SituacionEconomicaBean implements Serializable {

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
    private PeriodicidadFacade ejbFacadePeriocidad;

    @EJB
    private SectorFacade ejbFacadeSector;

    @EJB
    private CargoFacade ejbFacadeCargo;

    @EJB
    private TipoTelefonoFacade ejbFacadeTipoTelefono;

    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeo;

    @EJB
    private PersonaActividadEconomicaFacade ejbFacadePerActEco;

    @EJB
    private TipoRelacionFacade ejbFacadeTipoRelacion;

    @EJB
    private EmpresaFacade ejbFacadeEmpresa;

    @EJB
    private PersonaTrabajoActEcoFacade ejbFacadePersonaTrabajoActEco;

    @EJB
    private GaranteCreditoFacade ejbFacadeGaranteCredito;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    @EJB
    private PersonaNaturalFacade ejbFacadePersonaNatural;

    @EJB
    private PersonaConyugeFacade ejbFacadeConyugePersona;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String buscarPor;
    private String criterio;
    private String msg;

    private boolean socioFlujoCajaNuevo;
    private boolean socioSituacionPatrimonialNuevo;
    private boolean sigueProceso;
    private boolean saltar;

    private Socio socioSel;
    private ItemFlujoCaja itemFlujoCaja;
    private SocioFlujoCaja socioFlujoCaja;
    private ItemSituacionPatrimonial itemSituacionPatrimonial;
    private SocioSituacionPatrimonial socioSituacionPatrimonial;
    private PersonaActividadEconomica personaActividadEconomica;
    private List<PersonaTrabajoActEco> personaTrabajoActEcoAux;
    private PersonaTrabajoActEco personaTrabajoActEcoSel;
    private PersonaTrabajoActEco personaTrabajoActEcoSelEdi;
    private Cargo cargoTrabajo;
    private Empresa empresaTrabajo;
    private TipoRelacion tipoRelacionTrabajo;
    private Periodicidad periodicidadTrabajo;
    private Sector sectorTrabajo;
    private TipoTelefono tipoTelefonoTrabajo;
    private UbicacionGeografica ubiGeoPaiTra;
    private UbicacionGeografica ubiGeoProTra;
    private UbicacionGeografica ubiGeoCiuTra;
    private UbicacionGeografica ubiGeoParTra;

    private List<Socio> itemsSocios;
    private List<SocioFlujoCajaIngreso> itemsSocioFlujoCajIng;
    private List<SocioFlujoCajaEgreso> itemsSocioFlujoCajEgr;
    private List<SocioSituacionPatAct> itemsSocioSituacionPatAct;
    private List<SocioSituacionPatPas> itemsSocioSituacionPatPas;
    private List<PersonaTrabajoActEco> itemsPersonaTrabajoActEco;
    private List<UbicacionGeografica> itemsUbiGeoPaiTra;
    private List<UbicacionGeografica> itemsUbiGeoProTra;
    private List<UbicacionGeografica> itemsUbiGeoCiuTra;
    private List<UbicacionGeografica> itemsUbiGeoParTra;
    private List<PersonaActividadEconomica> itemsPersonaActEco;
    private List<ItemFlujoCaja> listItemsFlujoCajaIngreso;
    private List<ItemFlujoCaja> listItemsFlujoCajaEgreso;
    private List<ItemSituacionPatrimonial> listItemsSituacionPatAct;
    private List<ItemSituacionPatrimonial> listItemsSituacionPatPas;

    private String numeroTelefonoTrabajo;
    private Date fechaIngresoTrabajo;
    private Date fechaSalidaTrabajo;
    private long tiempoTrabajo;
    private String barrioTrabajo;
    private String direccionTrabajo;
    private String referenciaTrabajo;
    private long codigoAuxilarTrabajo;
    private boolean deshabilitaTrabajo;
    private Date fechaActual;
    private RequestContext context;
    private LlamaSP llamaSP;

    /*Cambio para ingresar personas*/
    private ExternalContext externalContext;
    private Map<String, Object> sessionMap;
    private ResultSet resultSet;
    private List<Socio> listaSociosPersonas;
    @EJB
    private PersonaFacade ejbFacadePersona;
    @EJB
    private MotivoSocioFacade ejbMotivoSocioFacade;
    @EJB
    private EstadoSocioFacade ejbEstadoSocioFacade;
    @EJB
    private ConocimientoIfipFacade ejbConocimientoIfipFacade;

    /*Cambio para ingresar personas*/
    public SituacionEconomicaBean() {

    }

    @PostConstruct
    public void init() {
        this.setItemsSocios(null);
        this.setSocioSel(null);
        this.fechaActual = new Date();
        llamaSP = new LlamaSP();
    }

    // *******************************************************************************************
    // --------------------------------------------------------------------------
    // -- LOGICA
    /**
     * Metodo para dar segumiento al wizard del socio
     *
     * @param event
     * @return Evento
     */
    public String sigueProceso(FlowEvent event) {
        try{
            externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            sessionMap.put("tab", event.getNewStep());
            if (isSaltar()) {
                setSaltar(false);
                return "confirmacionTab";
            }
            if (event.getNewStep().equals("trabajoActividadEconomicaTab")) {
                if ((Socio) sessionMap.get("socio") != null) {
                    this.socioSel = (Socio) sessionMap.get("socio");
                    this.editaSituacionEconomica(null);
                }
            }
            if (event.getOldStep() != null){
                if (event.getOldStep().equals("flujoCajaTab") && event.getNewStep().equals("situacionPatrimonialTab")) {
                    this.validaFlujoCaja();
                    if (!this.sigueProceso) {
                        return "flujoCajaTab";
                    }
                }
            }
            if (event.getOldStep() != null){
                if (event.getOldStep().equals("situacionPatrimonialTab") && event.getNewStep().equals("confirmacionTab")) {
                    this.validaSituacionPatrimonial();
                    if (!this.sigueProceso) {
                        return "situacionPatrimonialTab";
                    }
                }
            }
        }catch(Exception e){
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"situacionEconomicaBean", "sigueProceso", CapturaError.getErrorException(e)});
            return event.getNewStep();
        }
        return event.getNewStep();
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
     * Obtiene los datos del socio en base del criterio de la consulta
     */
    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(getCriterio(), 6)) {
                this.setItemsSocios(this.ejbFacadeSocio.getItemsSocio(this.getCriterio(), this.getBuscarPor(), ActivacionUsuario.getCodigoIfip()));
                if (this.getItemsSocios().size() == 1) {
                    this.buscarMoraSocio(getItemsSocios().get(0).getSocioPK().getCodigo());
                    this.buscarMoraConyuge(getItemsSocios().get(0).getSocioPK().getCodigo());
                    this.buscarMoraGarante(getItemsSocios().get(0).getCodigoPersona().getCodigo());
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsSocios(null);
                this.setSocioSel(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"situacionEconomicaBean", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void obtienePersonasYSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(getCriterio(), 6)) {
                // Cargando la conexion de BD
                llamaSP.cargaConexion();
                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);
                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);
                // Formando parametros para el sp
                llamaSP.setNombreSP("mks_socios.pkm_persona_socio.p_buscar_socio_y_persona");
                llamaSP.setNumeroParametros(4);
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pv_criterio", this.getCriterio()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pv_buscar_por", this.getBuscarPor()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"pc_persona_socio", OracleTypes.CURSOR});
                // Invocando al SP
                llamaSP.invocaSP();
                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    resultSet = (ResultSet) llamaSP.getListResultado().get(0);
                    if (resultSet != null) {
                        listaSociosPersonas = new ArrayList<Socio>();
                        while (resultSet.next()) {
                            Socio socio = new Socio();
                            Persona persona = this.ejbFacadePersona.getPersonaByCodigo(Long.valueOf(resultSet.getString("codigo_persona")));
                            if (resultSet.getString("es_socio").equals("S")) {
                                MotivoSocio motivoSocio = this.ejbMotivoSocioFacade.find(Long.valueOf(resultSet.getString("codigo_motivo_socio")));
                                EstadoSocio estadoSocio = this.ejbEstadoSocioFacade.find(Long.valueOf(resultSet.getString("codigo_estado_socio")));
                                ConocimientoIfip conocimientoIfip = this.ejbConocimientoIfipFacade.find(Long.valueOf(resultSet.getString("codigo_conocimiento_ifip")));
                                socio.setSocioPK(new SocioPK(Long.valueOf(resultSet.getString("codigo")), ActivacionUsuario.getCodigoIfip()));
                                socio.setCodigoIfipAgencia(new IfipAgencia(Long.valueOf(resultSet.getString("codigo_ifip_agencia"))));
                                socio.setCodigoUsuarioCreacion(new Usuario(Long.valueOf(resultSet.getString("codigo_usuario_creacion"))));
                                socio.setCodigoArchivo(Long.valueOf(resultSet.getString("codigo_archivo")));
                                socio.setEntregoRequesitios(resultSet.getString("entrego_requesitios").charAt(0));
                                socio.setObservaciones(resultSet.getString("observaciones"));
                                socio.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                                socio.setFechaActualizacion(resultSet.getDate("fecha_actualizacion"));
                                socio.setCodigoPersona(persona);
                                socio.setCodigoMotivoSocio(motivoSocio);
                                socio.setCodigoEstadoSocio(estadoSocio);
                                socio.setCodigoConocimientoIfip(conocimientoIfip);
                                listaSociosPersonas.add(socio);
                            } else {
                                socio.setSocioPK(new SocioPK(Long.valueOf("0"), ActivacionUsuario.getCodigoIfip()));
                                socio.setCodigoIfipAgencia(new IfipAgencia());
                                socio.setCodigoUsuarioCreacion(new Usuario());
                                socio.setCodigoArchivo(Long.valueOf(resultSet.getString("codigo_archivo")));
                                socio.setEntregoRequesitios(resultSet.getString("entrego_requesitios").charAt(0));
                                socio.setObservaciones(resultSet.getString("observaciones"));
                                socio.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                                socio.setFechaActualizacion(resultSet.getDate("fecha_actualizacion"));
                                socio.setCodigoPersona(persona);
                                socio.setCodigoMotivoSocio(new MotivoSocio());
                                socio.setCodigoEstadoSocio(new EstadoSocio());
                                socio.setCodigoConocimientoIfip(new ConocimientoIfip());
                                listaSociosPersonas.add(socio);
                            }
                        }
                        this.setItemsSocios(listaSociosPersonas);
                        if (this.getItemsSocios().size() == 1) {
                            if (!(Long.valueOf(getItemsSocios().get(0).getSocioPK().getCodigo()).compareTo(Long.valueOf("0")) == 0)) {
                                this.buscarMoraSocio(getItemsSocios().get(0).getSocioPK().getCodigo());
                                this.buscarMoraConyuge(getItemsSocios().get(0).getSocioPK().getCodigo());
                            }
                            this.buscarMoraGarante(getItemsSocios().get(0).getCodigoPersona().getCodigo());
                        }
                    }
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsSocios(null);
                this.setSocioSel(null);
            }
        } catch (Exception e) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCargaSocioYPersona"));
            Logger.getLogger(SituacionEconomicaBean.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    public void buscaMora() {
        if (this.getSocioSel() != null) {
            this.buscarMoraSocio(this.getSocioSel().getSocioPK().getCodigo());
            this.buscarMoraConyuge(this.getSocioSel().getSocioPK().getCodigo());
            this.buscarMoraGarante(this.getSocioSel().getCodigoPersona().getCodigo());
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
                            aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", sol.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(sol.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", socios.get(0).getCodigoPersona().getNombreCompleto()).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                            MuestraMensaje.addInformacion(aux);
                        }

                    }
                }

            }
        }

    }

    public void buscarMoraSocio(Long codigoSocio) {
        ////VERIFICACIONES DE MORA
        List<SolicitudDetalle> moraSocio = ejbFacadeSolicitudDetalle.getItemsDetalleCodigoSocioCodigoIfipMora(codigoSocio, ActivacionUsuario.getCodigoIfip(), new BigDecimal(0));
        if (!moraSocio.isEmpty()) {
            String aux;
            for (SolicitudDetalle sol : moraSocio) {
                List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                double deuda = 0;
                for (CalculoCuotaPagar c : pendientes) {
                    deuda = deuda + c.getTotalPago().doubleValue();
                }
                Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(sol.getSolicitudDetallePK().getNumeroCredito(), sol.getSolicitudDetallePK().getCodigoIfip(), 'P');
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

    /**
     *
     * @param actionEvent
     */
    public void editaSituacionEconomica(ActionEvent actionEvent) {
        this.socioFlujoCajaNuevo = false;
        this.socioSituacionPatrimonialNuevo = false;
        // Obteniendo el flujo de caja del socio
        this.socioFlujoCaja = this.ejbFacadeSocioFluCaj.getSocioFlujoCajaCodigoPersonaIfip(this.getSocioSel().getCodigoPersona().getCodigo(), ActivacionUsuario.getCodigoIfip());
        // Consultando si existe el flujo de caja, caso contrario instanciamos un nuevo
        if (this.socioFlujoCaja == null) {
            this.socioFlujoCajaNuevo = true;
            //Se cambia por el codigo de socio
            this.socioFlujoCaja = new SocioFlujoCaja(this.getSocioSel().getSocioPK().getCodigo());
            this.socioFlujoCaja.setCodigoIfip(this.getSocioSel().getSocioPK().getCodigoIfip());
            this.socioFlujoCaja.setTotalGastosSocio(new BigDecimal("0.00"));
            this.socioFlujoCaja.setTotalIngresosConyuge(new BigDecimal("0.00"));
            this.socioFlujoCaja.setTotalIngresosSocio(new BigDecimal("0.00"));
            this.socioFlujoCaja.setSaldo(new BigDecimal("0.00"));
            this.socioFlujoCaja.setCobertura(new BigDecimal("0.00"));
        }
        // Obteniendo la Situacion Patrimonial del Socio
        this.socioSituacionPatrimonial = this.ejbFacadeSocioSitPat.find(this.getSocioSel().getCodigoPersona().getCodigo());
        // Consultando si existe la Situacion Patrimonial, caso contrario instanciamos un nuevo
        if (this.socioSituacionPatrimonial == null) {
            this.socioSituacionPatrimonialNuevo = true;
            this.socioSituacionPatrimonial = new SocioSituacionPatrimonial(Long.valueOf("0"));
            this.socioSituacionPatrimonial.setTotalActivos(new BigDecimal("0.00"));
            this.socioSituacionPatrimonial.setTotalPasivos(new BigDecimal("0.00"));
            this.socioSituacionPatrimonial.setTotalPatrimonio(new BigDecimal("0.00"));
        }
        this.armaFlujoCaja();
        this.armaSituacionPatrimonial();
        //Obteniendo datos de las activides economicas
        this.setItemsPersonaActEco(this.ejbFacadePerActEco.getItemsCodigoPersonaEliminado(this.socioSel.getCodigoPersona().getCodigo(), 'N'));
        //Obteniendo trabajos de la persona donde no esten eliminados tanto el trabajo como su actividad economica
        this.setItemsPersonaTrabajoActEco(this.ejbFacadePersonaTrabajoActEco.getItemsCodigoPersonaActividadEconomicaEliminado(this.socioSel.getCodigoPersona().getCodigo(), 'N'));
        this.setItemsUbiGeoPaiTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.ubiGeoPaiTra = null;
        this.cambiaUbiGeoPaiTra();
        //Variables de Trabajo
        this.personaActividadEconomica = null;
        this.empresaTrabajo = null;
        this.cargoTrabajo = null;
        this.tipoRelacionTrabajo = null;
        //this.tipoRelacionParentesco = null;
        this.tiempoTrabajo = 1;
        this.fechaIngresoTrabajo = null;
        this.tipoTelefonoTrabajo = null;
        this.numeroTelefonoTrabajo = null;
        this.ubiGeoPaiTra = null;
        this.ubiGeoProTra = null;
        this.ubiGeoCiuTra = null;
        this.ubiGeoParTra = null;
        this.sectorTrabajo = null;
        this.barrioTrabajo = null;
        this.direccionTrabajo = null;
        this.referenciaTrabajo = null;
        this.periodicidadTrabajo = null;
        this.codigoAuxilarTrabajo = 999999900;
    }

    /**
     *
     * @param actionEvent
     */
    public void guardaSituacionEconomica(ActionEvent actionEvent) {
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
            //Guarda los trabajos de la Persona/Socio
            this.guardaTrabajos();
            MuestraMensaje.addSatisfactorioPersistencia(1);
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"situacionEconomicaBean", "guardaSocio", CapturaError.getErrorException(ex)});
        }

    }

    //------------------------------------------------------------------------------
    //- GUARDA LA SITUACION ECONOMICA
    public void guardaSituacionEconomicaSocio(ActionEvent actionEvent) {
        //System.out.println("******* GUARDA LA SITUACION ECONOMICA *********");
        context = RequestContext.getCurrentInstance();
        try {

            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaFlujoCaja()) {
                if (this.guardaSituacionPatrimonial()) {
                    if (this.guardaIngresosFlujoCaja()) {
                        if (this.guardaEgresosFlujoCaja()) {
                            if (this.guardaActivosSituacionPatrimonial()) {
                                if (this.guardaPasivosPatrimonial()) {
                                    if (this.guardaTrabajosSocio()) {

                                        context.execute("procesandoDlg.hide()");

                                        // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                                        llamaSP.commit();

                                        llamaSP.cerrarConexion();
                                        llamaSP.setConexionBD(null);
                                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                                        MuestraMensaje.addInformacion(msg);
                                        context.execute("procesandoDlg.hide()");
                                        context.execute("SituacionEconomicaDialog.hide()");

                                    } else {
                                        //
                                        llamaSP.rollback();
                                        context.execute("procesandoDlg.hide()");
                                        llamaSP.cerrarConexion();
                                        llamaSP.setConexionBD(null);
                                    }
                                } else {
                                    llamaSP.rollback();
                                    llamaSP.cerrarConexion();
                                    llamaSP.setConexionBD(null);
                                    context.execute("procesandoDlg.hide()");
                                    MuestraMensaje.addErrorCapaControl();
                                }
                            } else {
                                llamaSP.rollback();
                                llamaSP.cerrarConexion();
                                llamaSP.setConexionBD(null);
                                context.execute("procesandoDlg.hide()");
                                MuestraMensaje.addErrorCapaControl();
                            }
                        } else {
                            llamaSP.rollback();
                            llamaSP.cerrarConexion();
                            llamaSP.setConexionBD(null);
                            context.execute("procesandoDlg.hide()");
                            MuestraMensaje.addErrorCapaControl();
                        }
                    } else {
                        llamaSP.rollback();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                        context.execute("procesandoDlg.hide()");
                        MuestraMensaje.addErrorCapaControl();
                    }
                } else {
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    context.execute("procesandoDlg.hide()");
                    MuestraMensaje.addErrorCapaControl();
                }
            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                context.execute("procesandoDlg.hide()");
                MuestraMensaje.addErrorCapaControl();
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            //ex.printStackTrace();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"situacionEconomicaBean", "guardaSituacionEconomicaSocio", CapturaError.getErrorException(ex)});
        }

    }

    private boolean guardaSituacionPatrimonial() {
        // Situacion Patrimonial
        this.socioSituacionPatrimonial.setFechaActualizacion(new Date());
        this.socioSituacionPatrimonial.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());
        if (this.isSocioSituacionPatrimonialNuevo()) {
            this.socioSituacionPatrimonial.setCodigoUsuarioIngreso(ActivacionUsuario.getCodigoUsuario());
            this.socioSituacionPatrimonial.setFechaIngreso(new Date());
            this.socioSituacionPatrimonial.setFechaIngreso(this.socioSituacionPatrimonial.getFechaIngreso());

            llamaSP.setNombreSP("mks_socios.pkg_socio_situacion_pat.p_inserta");
            llamaSP.setNumeroParametros(10);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_patrimonio", this.socioSituacionPatrimonial.getTotalPatrimonio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_actualizo", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_activos", this.socioSituacionPatrimonial.getTotalActivos()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_ingreso", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_pasivos", this.socioSituacionPatrimonial.getTotalPasivos()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

        } else {

            llamaSP.setNombreSP("mks_socios.pkg_socio_situacion_pat.p_actualiza");
            llamaSP.setNumeroParametros(8);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_patrimonio", this.socioSituacionPatrimonial.getTotalPatrimonio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_actualizo", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_activos", this.socioSituacionPatrimonial.getTotalActivos()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_pasivos", this.socioSituacionPatrimonial.getTotalPasivos()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();
        }

        return llamaSP.isEjecucionCorrecta();

    }

    private boolean guardaFlujoCaja() {
        this.socioFlujoCaja.setFechaActualizacion(new Date());
        this.socioFlujoCaja.setCodigoUsuarioActualizo(ActivacionUsuario.getCodigoUsuario());

        if (this.isSocioFlujoCajaNuevo()) {

            this.socioFlujoCaja.setCodigoUsuarioIngreso(ActivacionUsuario.getCodigoUsuario());
            this.socioFlujoCaja.setFechaIngreso(new Date());
            this.socioFlujoCaja.setFechaActualizacion(this.socioFlujoCaja.getFechaIngreso());

            llamaSP.setNombreSP("mks_socios.pkg_socio_flujo_caja.p_inserta");
            llamaSP.setNumeroParametros(12);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_ingresos_socio", this.socioFlujoCaja.getTotalIngresosSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_ingresos_conyuge", this.socioFlujoCaja.getTotalIngresosConyuge()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_gastos_socio", this.socioFlujoCaja.getTotalGastosSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", this.socioFlujoCaja.getSaldo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobertura", this.socioFlujoCaja.getCobertura()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_ingreso", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_actualizo", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

        } else {                        
            llamaSP.setNombreSP("mks_socios.pkg_socio_flujo_caja.p_actualiza");
            llamaSP.setNumeroParametros(10);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_ingresos_socio", this.socioFlujoCaja.getTotalIngresosSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_ingresos_conyuge", this.socioFlujoCaja.getTotalIngresosConyuge()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_gastos_socio", this.socioFlujoCaja.getTotalGastosSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", this.socioFlujoCaja.getSaldo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobertura", this.socioFlujoCaja.getCobertura()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_actualizo", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            // Invocando al SP
            llamaSP.invocaSP();
        }

        return llamaSP.isEjecucionCorrecta();
    }

    private boolean guardaIngresosFlujoCaja() {

        // ----------------------------------------------------
        // Guardando el detalle de la Situacion economica
        // Flujo de Caja - Ingresos        
        for (SocioFlujoCajaIngreso sfci : this.itemsSocioFlujoCajIng) {
            //this.ejbFacadeSocioFluCajIng.edit(sfci);

            llamaSP.setNombreSP("mks_socios.pkm_socio_flujo_caja_ingreso.p_guarda");

            llamaSP.setNumeroParametros(6);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_item_flu_caj", sfci.getItemFlujoCaja().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_socio", sfci.getTotalSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_conyuge", sfci.getTotalConyuge()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            if (!llamaSP.isEjecucionCorrecta()) {
                return false;
            }

        }

        return llamaSP.isEjecucionCorrecta();

    }

    private boolean guardaEgresosFlujoCaja() {
        // ----------------------------------------------------
        // Guardando el detalle de la Situacion economica
        // Flujo de Caja - Egresos        
        for (SocioFlujoCajaEgreso sfce : this.itemsSocioFlujoCajEgr) {

            llamaSP.setNombreSP("mks_socios.pkm_socio_flujo_caja_egreso.p_guarda");

            llamaSP.setNumeroParametros(5);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_item_flu_caj", sfce.getItemFlujoCaja().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_socio", sfce.getTotalSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            if (!llamaSP.isEjecucionCorrecta()) {
                return false;
            }

        }

        return llamaSP.isEjecucionCorrecta();

    }

    private boolean guardaActivosSituacionPatrimonial() {
        // Situacion Patrimonial - Activos        
        // ----------------------------------------------------
        // Guardando el detalle de la Situacion economica
        // Situacion Patrimonial - Activos
        for (SocioSituacionPatAct sspa : this.itemsSocioSituacionPatAct) {
            //this.ejbFacadeSocioFluCajIng.edit(sfci);

            llamaSP.setNombreSP("mks_socios.pkm_socio_situacion_pat_act.p_guarda");

            llamaSP.setNumeroParametros(5);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_item_sit_pat", sspa.getItemSituacionPatrimonial().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", sspa.getTotal()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            if (!llamaSP.isEjecucionCorrecta()) {
                return false;
            }

        }

        return llamaSP.isEjecucionCorrecta();

    }

    private boolean guardaPasivosPatrimonial() {
        // Situacion Patrimonial - Activos        
        // ----------------------------------------------------
        // Guardando el detalle de la Situacion economica
        // Situacion Patrimonial - Activos
        for (SocioSituacionPatPas sspp : this.itemsSocioSituacionPatPas) {
            //this.ejbFacadeSocioFluCajIng.edit(sfci);

            llamaSP.setNombreSP("mks_socios.pkm_socio_situacion_pat_pas.p_guarda");

            llamaSP.setNumeroParametros(5);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_item_sit_pat", sspp.getItemSituacionPatrimonial().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", sspp.getTotal()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", this.socioSel.getCodigoPersona().getCodigo()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            if (!llamaSP.isEjecucionCorrecta()) {
                return false;
            }

        }

        return llamaSP.isEjecucionCorrecta();

    }

    private boolean guardaTrabajosSocio() {
        //try {
        for (PersonaTrabajoActEco ptae : getItemsPersonaTrabajoActEco()) {

            if (ptae.getFechaRegistro() == null) {
                ptae.setFechaRegistro(new Date());
            }

            ptae.setCodigoPersona(this.socioSel.getCodigoPersona().getCodigo());

            ptae.setFechaActualizacion(new Date());
            if (ptae.getCodigo() == codigoAuxilarTrabajo) {

                llamaSP.setNombreSP("mks_socios.pkg_persona_trabajo_act_eco.p_inserta");

                llamaSP.setNumeroParametros(20);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", ptae.getCodigoPersona()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_actividad_economica", ptae.getCodigoActividadEconomica()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ubi_geo_tra", ptae.getCodigoUbiGeoTra()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_empresa", ptae.getCodigoEmpresa()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cargo", ptae.getCodigoCargo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_relacion", ptae.getCodigoTipoRelacion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_sector", ptae.getCodigoSector()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodicidad ", ptae.getCodigoPeriodicidad()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_barrio", ptae.getBarrio()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion", ptae.getDireccion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_referencia", ptae.getReferencia()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_tiempo", ptae.getTiempo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso", new java.sql.Timestamp(ptae.getFechaIngreso().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", new java.sql.Timestamp(new Date().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_telefono", ptae.getCodigoTipoTelefono()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_telefonico", ptae.getNumeroTelefonico()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", String.valueOf(ptae.getEliminado())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_salida", ((ptae.getFechaSalida() != null) ? new java.sql.Timestamp(ptae.getFechaSalida().getTime()) : null)});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", new java.sql.Timestamp(new Date().getTime())});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

                // Invocando al SP
                llamaSP.invocaSP();

                if (!llamaSP.isEjecucionCorrecta()) {
                    return false;
                }
            } else {
                llamaSP.setNombreSP("mks_socios.pkg_persona_trabajo_act_eco.p_actualiza");

                llamaSP.setNumeroParametros(20);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", ptae.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", ptae.getCodigoPersona()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_actividad_economica", ptae.getCodigoActividadEconomica()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ubi_geo_tra", ptae.getCodigoUbiGeoTra()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_empresa", ptae.getCodigoEmpresa()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cargo", ptae.getCodigoCargo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_relacion", ptae.getCodigoTipoRelacion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_sector", ptae.getCodigoSector()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodicidad ", ptae.getCodigoPeriodicidad()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_barrio", ptae.getBarrio()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion", ptae.getDireccion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_referencia", ptae.getReferencia()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_tiempo", ptae.getTiempo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso", new java.sql.Timestamp(ptae.getFechaIngreso().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_actualizacion", new java.sql.Timestamp(new Date().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_telefono", ptae.getCodigoTipoTelefono()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_telefonico", ptae.getNumeroTelefonico()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", String.valueOf(ptae.getEliminado())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_salida", ((ptae.getFechaSalida() != null) ? new java.sql.Timestamp(ptae.getFechaSalida().getTime()) : null)});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", new java.sql.Timestamp(new Date().getTime())});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                if (!llamaSP.isEjecucionCorrecta()) {
                    return false;
                }
            }
            //this.ejbFacadePersonaTrabajoActEco.edit(ptae);
        }
        /* } catch (Exception e) {
         e.printStackTrace();
         }*/

        return llamaSP.isEjecucionCorrecta();
    }

    //--------------------------------------------------------------
    /**
     * ARMA LOS CASILLEROS DE LOS FLUJOS DE CAJA, TANTO DE INGRESOS COMO DE
     * EGRESOS.
     */
    public void armaFlujoCaja() {

        boolean existe;

        //----------------------------------------------
        // Armando los Ingresos del Socio
        listItemsFlujoCajaIngreso = this.ejbFacadeItemFlujoCaja.getItemsFlujoCaja('I', 'N');
        SocioFlujoCajaIngreso socioFlujoCajaIngreso;
        SocioFlujoCajaIngresoPK sfcipk;

        // Obteniendo el flujo de ingreso del socio
        if (this.getSocioSel().getSocioPK().getCodigo() == Long.valueOf("0")) {
            this.itemsSocioFlujoCajIng = this.ejbFacadeSocioFluCajIng.getItemsFlujoCajaIngresoPersona(this.getSocioSel().getCodigoPersona().getCodigo());

        } else {
            this.itemsSocioFlujoCajIng = this.ejbFacadeSocioFluCajIng.getItemsFlujoCajaIngresoSocio(this.getSocioSel().getSocioPK().getCodigo(),
                    this.getSocioSel().getSocioPK().getCodigoIfip());
        }

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
                        this.getSocioSel().getCodigoPersona().getCodigo()
                );

                socioFlujoCajaIngreso.setSocioFlujoCajaIngresoPK(sfcipk);
                socioFlujoCajaIngreso.setSocioFlujoCaja(new SocioFlujoCaja(this.getSocioSel().getCodigoPersona().getCodigo(),
                        this.getSocioSel().getSocioPK().getCodigo(),
                        this.getSocioSel().getSocioPK().getCodigoIfip()
                )
                );

                socioFlujoCajaIngreso.setSocioFlujoCajaIngresoPK(sfcipk);
                socioFlujoCajaIngreso.setItemFlujoCaja(i);
                socioFlujoCajaIngreso.setTotalSocio(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));
                socioFlujoCajaIngreso.setTotalConyuge(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));

                this.itemsSocioFlujoCajIng.add(socioFlujoCajaIngreso);
            }

        }

        //----------------------------------------------
        // Armando los Egresos del Socio
        listItemsFlujoCajaEgreso = this.ejbFacadeItemFlujoCaja.getItemsFlujoCaja('E', 'N');
        SocioFlujoCajaEgreso socioFlujoCajaEgreso;
        SocioFlujoCajaEgresoPK sfcepk;

        // Obteniendo el flujo de ingreso del socio
        if (this.getSocioSel().getSocioPK().getCodigo() == Long.valueOf("0")) {
            this.itemsSocioFlujoCajEgr = this.ejbFacadeSocioFluCajEng.getItemsFlujoCajaEgresoPersona(this.getSocioSel().getCodigoPersona().getCodigo());
        } else {
            this.itemsSocioFlujoCajEgr = this.ejbFacadeSocioFluCajEng.getItemsFlujoCajaEgresoSocio(this.getSocioSel().getSocioPK().getCodigo(),
                    this.getSocioSel().getSocioPK().getCodigoIfip());
        }

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
                            this.getSocioSel().getCodigoPersona().getCodigo()
                );

                socioFlujoCajaEgreso.setSocioFlujoCajaEgresoPK(sfcepk);
                socioFlujoCajaEgreso.setSocioFlujoCaja(new SocioFlujoCaja(this.getSocioSel().getCodigoPersona().getCodigo(),
                                                                          this.getSocioSel().getSocioPK().getCodigo(),
                                                                          this.getSocioSel().getSocioPK().getCodigoIfip()
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
        listItemsSituacionPatAct = this.ejbFacadeItemSitPat.getItemsSituacionPatrimonial('A', 'N');
        SocioSituacionPatAct socioSituacionPatAct;
        SocioSituacionPatActPK sspapk;

        // Obteniendo el flujo de ingreso del socio
        if (this.getSocioSel().getSocioPK().getCodigo() == Long.valueOf("0")) {
            this.itemsSocioSituacionPatAct = this.ejbFacadeSocioSitPatAct.getItemsPersonaSituacionPatAct(this.getSocioSel().getCodigoPersona().getCodigo());
        }
        else{
        this.itemsSocioSituacionPatAct = this.ejbFacadeSocioSitPatAct.getItemsSocioSituacionPatAct(this.getSocioSel().getSocioPK().getCodigo(),
                this.getSocioSel().getSocioPK().getCodigoIfip());
        }

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

                sspapk = new SocioSituacionPatActPK(i.getCodigo(),
                                                    this.getSocioSel().getCodigoPersona().getCodigo());

                socioSituacionPatAct.setSocioSituacionPatActPK(sspapk);
                socioSituacionPatAct.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                                                          this.getSocioSel().getCodigoPersona().getCodigo(),
                                                                          this.getSocioSel().getSocioPK().getCodigo(),
                                                                          this.getSocioSel().getSocioPK().getCodigoIfip()
                                                                          )
                                                       ); 
                socioSituacionPatAct.setItemSituacionPatrimonial(i);
                socioSituacionPatAct.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));

                this.itemsSocioSituacionPatAct.add(socioSituacionPatAct);
            }
        }

        //----------------------------------------------
        // Armando los Pasivos del Socio
        listItemsSituacionPatPas = this.ejbFacadeItemSitPat.getItemsSituacionPatrimonial('P', 'N');
        SocioSituacionPatPas socioSituacionPatPas;
        SocioSituacionPatPasPK sspppk;

        // Obteniendo el flujo de ingreso del socio
        if (this.getSocioSel().getSocioPK().getCodigo() == Long.valueOf("0")) {
            this.itemsSocioSituacionPatPas = this.ejbFacadeSocioSitPatPas.getItemsPersonaSituacionPatPas(this.getSocioSel().getCodigoPersona().getCodigo());
        }
        else{
        this.itemsSocioSituacionPatPas = this.ejbFacadeSocioSitPatPas.getItemsSocioSituacionPatPas(this.getSocioSel().getSocioPK().getCodigo(),
                this.getSocioSel().getSocioPK().getCodigoIfip());
        }

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
                        this.getSocioSel().getCodigoPersona().getCodigo());

                socioSituacionPatPas.setSocioSituacionPatPasPK(sspppk);
                socioSituacionPatPas.setSocioSituacionPatrimonial(new SocioSituacionPatrimonial(
                                                                          this.getSocioSel().getCodigoPersona().getCodigo(),
                                                                          this.getSocioSel().getSocioPK().getCodigo(),
                                                                          this.getSocioSel().getSocioPK().getCodigoIfip()
                                                                          ));
                socioSituacionPatPas.setItemSituacionPatrimonial(i);
                socioSituacionPatPas.setTotal(new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN));

                this.itemsSocioSituacionPatPas.add(socioSituacionPatPas);
            }

        }

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

    //----------------------------------------------------------------------------------
    //--TRABAJOS
    public boolean validaTrabajo() {
        String msg = null;
        boolean telefonoCorrecto = false;

        if (this.personaActividadEconomica == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("ActividadEconomica") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getEmpresaTrabajo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Empresa") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getCargoTrabajo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cargo") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getTipoRelacionTrabajo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("TipoRelacion") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (String.valueOf(this.getTiempoTrabajo()).trim().equals("")) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Tiempo") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getTiempoTrabajo() <= 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TiempoTrabajoMayorCero");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getFechaIngresoTrabajo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("FechaIngreso") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getTipoTelefonoTrabajo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("TipoTelefono") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getNumeroTelefonoTrabajo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Numero") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getTipoTelefonoTrabajo().getCodigo().toString().equals("2")) {
            telefonoCorrecto = Validaciones.validaTelefonoCelular(getNumeroTelefonoTrabajo());
        }

        if (this.getTipoTelefonoTrabajo().getCodigo().toString().equals("1")) {
            telefonoCorrecto = Validaciones.validaTelefonoConvencional(getNumeroTelefonoTrabajo());
        }

        if (!telefonoCorrecto) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TelefonoIncorrecto");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getUbiGeoPaiTra() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("PaisTrabajo") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getSectorTrabajo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Sector") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getBarrioTrabajo().length() <= 5) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("BarrioMiinimoCincoCaracteres");
            MuestraMensaje.addError(msg);
            return false;

        }

        if (this.getDireccionTrabajo().length() <= 5) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DireccionMiinimoCincoCaracteres");
            MuestraMensaje.addError(msg);
            return false;

        }

        if (this.getReferenciaTrabajo().length() <= 5) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ReferenciaMiinimoCincoCaracteres");
            MuestraMensaje.addError(msg);
            return false;

        }

        //Verifica que la actividad economica no este eliminada
        if (this.personaActividadEconomica.getEliminado() == 'S') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ActividadEconomicaEliminada");
            MuestraMensaje.addError(msg);
            return false;
        }
        // Buscamos si el trabajo ya esta ingresado
        if (getItemsPersonaTrabajoActEco() != null) {
            for (PersonaTrabajoActEco ptae : getItemsPersonaTrabajoActEco()) {
                if (ptae.getCodigoEmpresa() == this.getEmpresaTrabajo().getCodigo() && ptae.getPersonaActividadEconomica().getActividadEconomica().getCodigo() == this.personaActividadEconomica.getActividadEconomica().getCodigo()) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TrabajoIngresado");
                    MuestraMensaje.addError(msg);
                    return false;
                }
            }
        }

        if (!this.ejbFacadePersonaTrabajoActEco.getItemsCodigoPersonaCodigoActividadCodigoEmpresa(this.socioSel.getCodigoPersona().getCodigo(), this.personaActividadEconomica.getPersonaActividadEconomicaPK().getCodigoActividadEconomica(), this.empresaTrabajo.getCodigo()).isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TrabajoIngresadoEmpresaActividadEconomica");
            MuestraMensaje.addError(msg);
            return false;
        }
        return true;

    }

    public boolean validaTrabajoEdicion() {
        String msg = null;
        boolean telefonoCorrecto = false;

        if (this.getPersonaTrabajoActEcoSel().getCargo() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cargo") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            this.personaTrabajoActEcoSel.setCargo(personaTrabajoActEcoSelEdi.getCargo());
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.getPersonaTrabajoActEcoSel().getTipoRelacion() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("TipoRelacion") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setTipoRelacion(personaTrabajoActEcoSelEdi.getTipoRelacion());
            return false;
        }

        if (String.valueOf(this.personaTrabajoActEcoSel.getTiempo()).trim().equals("")) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Tiempo") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setTiempo(personaTrabajoActEcoSelEdi.getTiempo());
            return false;
        }

        if (this.personaTrabajoActEcoSel.getTiempo() <= 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TiempoTrabajoMayorCero");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setTiempo(personaTrabajoActEcoSelEdi.getTiempo());
            return false;
        }

        if (this.personaTrabajoActEcoSel.getFechaIngreso() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("FechaIngreso") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setFechaIngreso(personaTrabajoActEcoSelEdi.getFechaIngreso());
            return false;
        }

        if (this.personaTrabajoActEcoSel.getTipoTelefono() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("TipoTelefono") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setTipoTelefono(personaTrabajoActEcoSelEdi.getTipoTelefono());
            return false;
        }

        if (this.personaTrabajoActEcoSel.getNumeroTelefonico() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Numero") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setNumeroTelefonico(personaTrabajoActEcoSelEdi.getNumeroTelefonico());
            return false;
        }

        if (this.personaTrabajoActEcoSel.getTipoTelefono().getCodigo() == 2) {
            telefonoCorrecto = Validaciones.validaTelefonoCelular(personaTrabajoActEcoSelEdi.getNumeroTelefonico());

        }

        if (this.personaTrabajoActEcoSel.getTipoTelefono().getCodigo() == 1) {
            telefonoCorrecto = Validaciones.validaTelefonoConvencional(personaTrabajoActEcoSelEdi.getNumeroTelefonico());
        }

        if (!telefonoCorrecto) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TelefonoIncorrecto");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setNumeroTelefonico(personaTrabajoActEcoSelEdi.getNumeroTelefonico());
            return false;
        }

        if (this.getUbiGeoPaiTra() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("PaisTrabajo") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.personaTrabajoActEcoSel.getSector() == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Sector") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setSector(personaTrabajoActEcoSelEdi.getSector());
            return false;
        }

        if (this.personaTrabajoActEcoSel.getBarrio().length() <= 5) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("BarrioMiinimoCincoCaracteres");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setBarrio(personaTrabajoActEcoSelEdi.getBarrio());
            return false;

        }

        if (this.personaTrabajoActEcoSel.getDireccion().length() <= 5) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DireccionMiinimoCincoCaracteres");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setDireccion(personaTrabajoActEcoSelEdi.getDireccion());
            return false;

        }

        if (this.personaTrabajoActEcoSel.getReferencia().length() <= 5) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ReferenciaMiinimoCincoCaracteres");
            MuestraMensaje.addError(msg);
            this.personaTrabajoActEcoSel.setReferencia(personaTrabajoActEcoSelEdi.getReferencia());
            return false;

        }

        //Verifica que la actividad economica no este eliminada
        /* if (this.personaActividadEconomica.getEliminado() == 'S') {
         msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ActividadEconomicaEliminada");
         MuestraMensaje.addError(msg);
         return false;
         }*/
        // Buscamos si el trabajo ya esta ingresado
        /* if (getItemsPersonaTrabajoActEco() != null) {
         for (PersonaTrabajoActEco ptae : getItemsPersonaTrabajoActEco()) {
         if (ptae.getCodigoEmpresa() == this.getEmpresaTrabajo().getCodigo() && ptae.getPersonaActividadEconomica().getActividadEconomica().getCodigo() == this.getPersonaTrabajoActEcoSel().getPersonaActividadEconomica().. getCodigo()) {
         msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TrabajoIngresado");
         MuestraMensaje.addError(msg);
         return false;
         }
         }
         }

         if (!this.ejbFacadePersonaTrabajoActEco.getItemsCodigoPersonaCodigoActividadCodigoEmpresa(this.socioSel.getCodigoPersona().getCodigo(), this.personaActividadEconomica.getPersonaActividadEconomicaPK().getCodigoActividadEconomica(), this.empresaTrabajo.getCodigo()).isEmpty()) {
         msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TrabajoIngresadoEmpresaActividadEconomica");
         MuestraMensaje.addError(msg);
         return false;
         }*/
        return true;

    }

    /**
     * Agrega el trabajo del socio en la actividad economica.
     */
    public void agregaTrabajo() {

        if (this.validaTrabajo()) {
            PersonaTrabajoActEco ptae = new PersonaTrabajoActEco();
            if (this.getUbiGeoParTra() != null) {
                ptae.setCodigoUbiGeoTra(getUbiGeoParTra().getCodigo());
                ptae.setUbicacionGeograficaTrabajo(getUbiGeoParTra());
            } else if (this.getUbiGeoCiuTra() != null) {
                ptae.setCodigoUbiGeoTra(getUbiGeoCiuTra().getCodigo());
                ptae.setUbicacionGeograficaTrabajo(getUbiGeoCiuTra());
            } else if (this.getUbiGeoProTra() != null) {
                ptae.setCodigoUbiGeoTra(getUbiGeoProTra().getCodigo());
                ptae.setUbicacionGeograficaTrabajo(getUbiGeoProTra());
            } else {
                ptae.setCodigoUbiGeoTra(getUbiGeoPaiTra().getCodigo());
                ptae.setUbicacionGeograficaTrabajo(getUbiGeoPaiTra());
            }

            ptae.setCodigoEmpresa(this.getEmpresaTrabajo().getCodigo());
            ptae.setEmpresa(getEmpresaTrabajo());

            ptae.setCodigoCargo(this.getCargoTrabajo().getCodigo());
            ptae.setCargo(getCargoTrabajo());

            ptae.setCodigoPeriodicidad(this.getPeriodicidadTrabajo().getCodigo());
            ptae.setPeriodicidad(getPeriodicidadTrabajo());

            ptae.setPersonaActividadEconomica(this.personaActividadEconomica);
            ptae.setCodigoActividadEconomica(personaActividadEconomica.getActividadEconomica().getCodigo());

            ptae.setBarrio(this.getBarrioTrabajo());
            ptae.setDireccion(this.getDireccionTrabajo());
            ptae.setReferencia(this.getReferenciaTrabajo());
            ptae.setFechaIngreso(this.getFechaIngresoTrabajo());
            ptae.setFechaSalida(null);

            ptae.setCodigoTipoTelefono(this.getTipoTelefonoTrabajo().getCodigo());
            ptae.setTipoTelefono(getTipoTelefonoTrabajo());

            ptae.setCodigoTipoRelacion(this.getTipoRelacionTrabajo().getCodigo());
            ptae.setTipoRelacion(getTipoRelacionTrabajo());

            ptae.setCodigoSector(this.getSectorTrabajo().getCodigo());
            ptae.setSector(getSectorTrabajo());

            ptae.setNumeroTelefonico(this.getNumeroTelefonoTrabajo());
            ptae.setTiempo((int) this.getTiempoTrabajo());
            ptae.setEliminado('N');

            this.personaActividadEconomica = null;
            this.setEmpresaTrabajo(null);
            this.setCargoTrabajo(null);
            this.setTipoRelacionTrabajo(null);
            this.setTiempoTrabajo(1);
            this.setFechaIngresoTrabajo(null);
            this.setTipoTelefonoTrabajo(null);
            this.setNumeroTelefonoTrabajo(null);
            this.setUbiGeoPaiTra(null);
            this.setUbiGeoProTra(null);
            this.setUbiGeoCiuTra(null);
            this.setUbiGeoParTra(null);
            this.setSectorTrabajo(null);
            this.setBarrioTrabajo(null);
            this.setDireccionTrabajo(null);
            this.setReferenciaTrabajo(null);
            this.setPeriodicidadTrabajo(null);

            if (this.getItemsPersonaTrabajoActEco() == null) {
                this.setItemsPersonaTrabajoActEco(new ArrayList<PersonaTrabajoActEco>());
            } else if (this.getItemsPersonaTrabajoActEco().isEmpty()) {
                this.setItemsPersonaTrabajoActEco(new ArrayList<PersonaTrabajoActEco>());
            }

            this.codigoAuxilarTrabajo++;
            ptae.setCodigo(this.codigoAuxilarTrabajo);
            this.getItemsPersonaTrabajoActEco().add(ptae);

        }
    }

    /**
     * Quitando trabajo
     */
    public void quitaTrabajo() {
        if (this.getPersonaTrabajoActEcoSel() != null) {
            if (this.ejbFacadePersonaTrabajoActEco.find(this.getPersonaTrabajoActEcoSel().getCodigo()) == null) {
                this.getItemsPersonaTrabajoActEco().remove(getPersonaTrabajoActEcoSel());
            } else {
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
                MuestraMensaje.addAdvertencia(msg);
            }
        }
    }

    /**
     * Prepara la
     *
     * @param actionEvent
     */
    public void preparaEdicionTrabajo(ActionEvent actionEvent) {
        if (this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoJerarquia().getSiglas().equals("PA")) {
            this.setUbiGeoPaiTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo());
            this.cambiaUbiGeoPaiTra();
            this.cambiaUbiGeoProTra();
            this.cambiaUbiGeoCiuTra();
        }

        if (this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoJerarquia().getSiglas().equals("PR")) {
            this.setUbiGeoPaiTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoUbiGeoPadre());
            this.cambiaUbiGeoPaiTra();
            this.setUbiGeoProTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo());
            this.cambiaUbiGeoProTra();
            this.cambiaUbiGeoCiuTra();

        }

        if (this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoJerarquia().getSiglas().equals("CI")) {
            this.setUbiGeoPaiTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre());
            this.cambiaUbiGeoPaiTra();
            this.setUbiGeoProTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoUbiGeoPadre());
            this.cambiaUbiGeoProTra();
            this.ubiGeoCiuTra = this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo();
            this.cambiaUbiGeoCiuTra();
        }

        if (this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoJerarquia().getSiglas().equals("PQ")) {
            this.setUbiGeoPaiTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre());
            this.cambiaUbiGeoPaiTra();
            this.setUbiGeoProTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre());
            this.cambiaUbiGeoProTra();
            this.setUbiGeoCiuTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo().getCodigoUbiGeoPadre());
            this.cambiaUbiGeoCiuTra();
            this.setUbiGeoParTra(this.getPersonaTrabajoActEcoSel().getUbicacionGeograficaTrabajo());

        }
        this.personaTrabajoActEcoSelEdi = this.getPersonaTrabajoActEcoSel();
        this.setDeshabilitaTrabajo(true);
    }

    public void preparaNuevoTrabajo(ActionEvent actionEvent) {
        this.setItemsUbiGeoPaiTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.setUbiGeoPaiTra(null);
        this.cambiaUbiGeoPaiTra();
        this.setPersonaTrabajoActEcoSel(new PersonaTrabajoActEco());
        this.setDeshabilitaTrabajo(false);

    }

    /**
     * Cancela el Trabajo
     */
    public void cancelaTrabajo() {

        if (this.isDeshabilitaTrabajo()) {
            if (this.codigoAuxilarTrabajo != this.personaTrabajoActEcoSel.getCodigo()) {
                this.setPersonaTrabajoActEcoSel(this.ejbFacadePersonaTrabajoActEco.find(this.personaTrabajoActEcoSelEdi.getCodigo()));
            }
        }
    }

    public void editaTrabajo() {
        context = RequestContext.getCurrentInstance();
        //if (ptae.getCodigoEmpresa() == this.getEmpresaTrabajo().getCodigo() && ptae.getPersonaActividadEconomica().getActividadEconomica().getCodigo() == this.personaActividadEconomica.getActividadEconomica().getCodigo()) {
        if (validaTrabajoEdicion()) {
            if (this.getUbiGeoParTra() != null) {
                getPersonaTrabajoActEcoSel().setCodigoUbiGeoTra(getUbiGeoParTra().getCodigo());
                getPersonaTrabajoActEcoSel().setUbicacionGeograficaTrabajo(getUbiGeoParTra());
            } else if (this.getUbiGeoCiuTra() != null) {
                getPersonaTrabajoActEcoSel().setCodigoUbiGeoTra(getUbiGeoCiuTra().getCodigo());
                getPersonaTrabajoActEcoSel().setUbicacionGeograficaTrabajo(getUbiGeoCiuTra());
            } else if (this.getUbiGeoProTra() != null) {
                getPersonaTrabajoActEcoSel().setCodigoUbiGeoTra(getUbiGeoProTra().getCodigo());
                getPersonaTrabajoActEcoSel().setUbicacionGeograficaTrabajo(getUbiGeoProTra());
            } else {
                getPersonaTrabajoActEcoSel().setCodigoUbiGeoTra(getUbiGeoPaiTra().getCodigo());
                getPersonaTrabajoActEcoSel().setUbicacionGeograficaTrabajo(getUbiGeoPaiTra());
            }
            context.execute("EdicionTrabajoDialog.hide()");
        }

    }

    public void guardaTrabajos() {

        for (PersonaTrabajoActEco ptae : getItemsPersonaTrabajoActEco()) {
            if (ptae.getFechaRegistro() == null) {
                ptae.setFechaRegistro(new Date());
            }

            ptae.setCodigoPersona(this.socioSel.getCodigoPersona().getCodigo());
            ptae.setFechaActualizacion(new Date());
            this.ejbFacadePersonaTrabajoActEco.edit(ptae);
        }
    }
    //-----------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------
    // -- REFRESCAMIENTO DE COMBOS/LISTAS 
    public void cambiaUbiGeoPaiTra() {

        this.setItemsUbiGeoProTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPaiTra()));
        this.setUbiGeoProTra(null);
        this.setItemsUbiGeoCiuTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoProTra()));
        this.setUbiGeoCiuTra(null);
        this.setItemsUbiGeoParTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiuTra()));
        this.setUbiGeoParTra(null);
    }

    public void cambiaUbiGeoProTra() {
        this.setItemsUbiGeoCiuTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoProTra()));
        this.setUbiGeoCiuTra(null);
        this.setItemsUbiGeoParTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiuTra()));
        this.setUbiGeoParTra(null);
    }

    public void cambiaUbiGeoCiuTra() {
        this.setItemsUbiGeoParTra(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiuTra()));
        this.setUbiGeoParTra(null);
    }

    // -- FIN REFRESCAMIENTO DE COMBOS/LISTAS 
    // -------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Periodicidad> getItemsPeriodicidad() {
        return this.ejbFacadePeriocidad.getItemsEsParaSolicitudSocioEliminado('S', 'N');
    }

    public List<Sector> getItemsSector() {
        return this.ejbFacadeSector.getItemsSectorEliminado('N');
    }

    public List<Cargo> getItemsCargo() {
        return this.ejbFacadeCargo.getItemsCargoEliminado('N');
    }

    public List<TipoTelefono> getItemsTipoTelefono() {
        return this.ejbFacadeTipoTelefono.getItemsTipoTelefonoEliminado('N');
    }

    public List<Empresa> getItemsEmpresa() {
        return this.ejbFacadeEmpresa.getItemsEmpresaEliminado('N');
    }

    public List<TipoRelacion> getItemsTipoRelacion() {
        return this.ejbFacadeTipoRelacion.getItemsEsParaSolicitudSocioEliminado('S', 'N');
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
     * @return the personaTrabajoActEcoSel
     */
    public PersonaTrabajoActEco getPersonaTrabajoActEcoSel() {
        return personaTrabajoActEcoSel;
    }

    /**
     * @param personaTrabajoActEcoSel the personaTrabajoActEcoSel to set
     */
    public void setPersonaTrabajoActEcoSel(PersonaTrabajoActEco personaTrabajoActEcoSel) {
        this.personaTrabajoActEcoSel = personaTrabajoActEcoSel;
    }

    /**
     * @return the cargoTrabajo
     */
    public Cargo getCargoTrabajo() {
        return cargoTrabajo;
    }

    /**
     * @param cargoTrabajo the cargoTrabajo to set
     */
    public void setCargoTrabajo(Cargo cargoTrabajo) {
        this.cargoTrabajo = cargoTrabajo;
    }

    /**
     * @return the empresaTrabajo
     */
    public Empresa getEmpresaTrabajo() {
        return empresaTrabajo;
    }

    /**
     * @param empresaTrabajo the empresaTrabajo to set
     */
    public void setEmpresaTrabajo(Empresa empresaTrabajo) {
        this.empresaTrabajo = empresaTrabajo;
    }

    /**
     * @return the tipoRelacionTrabajo
     */
    public TipoRelacion getTipoRelacionTrabajo() {
        return tipoRelacionTrabajo;
    }

    /**
     * @param tipoRelacionTrabajo the tipoRelacionTrabajo to set
     */
    public void setTipoRelacionTrabajo(TipoRelacion tipoRelacionTrabajo) {
        this.tipoRelacionTrabajo = tipoRelacionTrabajo;
    }

    /**
     * @return the periodicidadTrabajo
     */
    public Periodicidad getPeriodicidadTrabajo() {
        return periodicidadTrabajo;
    }

    /**
     * @param periodicidadTrabajo the periodicidadTrabajo to set
     */
    public void setPeriodicidadTrabajo(Periodicidad periodicidadTrabajo) {
        this.periodicidadTrabajo = periodicidadTrabajo;
    }

    /**
     * @return the sectorTrabajo
     */
    public Sector getSectorTrabajo() {
        return sectorTrabajo;
    }

    /**
     * @param sectorTrabajo the sectorTrabajo to set
     */
    public void setSectorTrabajo(Sector sectorTrabajo) {
        this.sectorTrabajo = sectorTrabajo;
    }

    /**
     * @return the tipoTelefonoTrabajo
     */
    public TipoTelefono getTipoTelefonoTrabajo() {
        return tipoTelefonoTrabajo;
    }

    /**
     * @param tipoTelefonoTrabajo the tipoTelefonoTrabajo to set
     */
    public void setTipoTelefonoTrabajo(TipoTelefono tipoTelefonoTrabajo) {
        this.tipoTelefonoTrabajo = tipoTelefonoTrabajo;
    }

    /**
     * @return the ubiGeoPaiTra
     */
    public UbicacionGeografica getUbiGeoPaiTra() {
        return ubiGeoPaiTra;
    }

    /**
     * @param ubiGeoPaiTra the ubiGeoPaiTra to set
     */
    public void setUbiGeoPaiTra(UbicacionGeografica ubiGeoPaiTra) {
        this.ubiGeoPaiTra = ubiGeoPaiTra;
    }

    /**
     * @return the ubiGeoProTra
     */
    public UbicacionGeografica getUbiGeoProTra() {
        return ubiGeoProTra;
    }

    /**
     * @param ubiGeoProTra the ubiGeoProTra to set
     */
    public void setUbiGeoProTra(UbicacionGeografica ubiGeoProTra) {
        this.ubiGeoProTra = ubiGeoProTra;
    }

    /**
     * @return the ubiGeoCiuTra
     */
    public UbicacionGeografica getUbiGeoCiuTra() {
        return ubiGeoCiuTra;
    }

    /**
     * @param ubiGeoCiuTra the ubiGeoCiuTra to set
     */
    public void setUbiGeoCiuTra(UbicacionGeografica ubiGeoCiuTra) {
        this.ubiGeoCiuTra = ubiGeoCiuTra;
    }

    /**
     * @return the ubiGeoParTra
     */
    public UbicacionGeografica getUbiGeoParTra() {
        return ubiGeoParTra;
    }

    /**
     * @param ubiGeoParTra the ubiGeoParTra to set
     */
    public void setUbiGeoParTra(UbicacionGeografica ubiGeoParTra) {
        this.ubiGeoParTra = ubiGeoParTra;
    }

    /**
     * @return the itemsPersonaTrabajoActEco
     */
    public List<PersonaTrabajoActEco> getItemsPersonaTrabajoActEco() {
        return itemsPersonaTrabajoActEco;
    }

    /**
     * @param itemsPersonaTrabajoActEco the itemsPersonaTrabajoActEco to set
     */
    public void setItemsPersonaTrabajoActEco(List<PersonaTrabajoActEco> itemsPersonaTrabajoActEco) {
        this.itemsPersonaTrabajoActEco = itemsPersonaTrabajoActEco;
    }

    /**
     * @return the numeroTelefonoTrabajo
     */
    public String getNumeroTelefonoTrabajo() {
        return numeroTelefonoTrabajo;
    }

    /**
     * @param numeroTelefonoTrabajo the numeroTelefonoTrabajo to set
     */
    public void setNumeroTelefonoTrabajo(String numeroTelefonoTrabajo) {
        this.numeroTelefonoTrabajo = numeroTelefonoTrabajo;
    }

    /**
     * @return the fechaIngresoTrabajo
     */
    public Date getFechaIngresoTrabajo() {
        return fechaIngresoTrabajo;
    }

    /**
     * @param fechaIngresoTrabajo the fechaIngresoTrabajo to set
     */
    public void setFechaIngresoTrabajo(Date fechaIngresoTrabajo) {
        this.fechaIngresoTrabajo = fechaIngresoTrabajo;
    }

    /**
     * @return the fechaSalidaTrabajo
     */
    public Date getFechaSalidaTrabajo() {
        return fechaSalidaTrabajo;
    }

    /**
     * @param fechaSalidaTrabajo the fechaSalidaTrabajo to set
     */
    public void setFechaSalidaTrabajo(Date fechaSalidaTrabajo) {
        this.fechaSalidaTrabajo = fechaSalidaTrabajo;
    }

    /**
     * @return the tiempoTrabajo
     */
    public long getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    /**
     * @param tiempoTrabajo the tiempoTrabajo to set
     */
    public void setTiempoTrabajo(long tiempoTrabajo) {
        this.tiempoTrabajo = tiempoTrabajo;
    }

    /**
     * @return the barrioTrabajo
     */
    public String getBarrioTrabajo() {
        return barrioTrabajo;
    }

    /**
     * @param barrioTrabajo the barrioTrabajo to set
     */
    public void setBarrioTrabajo(String barrioTrabajo) {
        this.barrioTrabajo = barrioTrabajo;
    }

    /**
     * @return the direccionTrabajo
     */
    public String getDireccionTrabajo() {
        return direccionTrabajo;
    }

    /**
     * @param direccionTrabajo the direccionTrabajo to set
     */
    public void setDireccionTrabajo(String direccionTrabajo) {
        this.direccionTrabajo = direccionTrabajo;
    }

    /**
     * @return the referenciaTrabajo
     */
    public String getReferenciaTrabajo() {
        return referenciaTrabajo;
    }

    /**
     * @param referenciaTrabajo the referenciaTrabajo to set
     */
    public void setReferenciaTrabajo(String referenciaTrabajo) {
        this.referenciaTrabajo = referenciaTrabajo;
    }

    /**
     * @return the itemsUbiGeoPaiTra
     */
    public List<UbicacionGeografica> getItemsUbiGeoPaiTra() {
        return itemsUbiGeoPaiTra;
    }

    /**
     * @param itemsUbiGeoPaiTra the itemsUbiGeoPaiTra to set
     */
    public void setItemsUbiGeoPaiTra(List<UbicacionGeografica> itemsUbiGeoPaiTra) {
        this.itemsUbiGeoPaiTra = itemsUbiGeoPaiTra;
    }

    /**
     * @return the itemsUbiGeoProTra
     */
    public List<UbicacionGeografica> getItemsUbiGeoProTra() {
        return itemsUbiGeoProTra;
    }

    /**
     * @param itemsUbiGeoProTra the itemsUbiGeoProTra to set
     */
    public void setItemsUbiGeoProTra(List<UbicacionGeografica> itemsUbiGeoProTra) {
        this.itemsUbiGeoProTra = itemsUbiGeoProTra;
    }

    /**
     * @return the itemsUbiGeoCiuTra
     */
    public List<UbicacionGeografica> getItemsUbiGeoCiuTra() {
        return itemsUbiGeoCiuTra;
    }

    /**
     * @param itemsUbiGeoCiuTra the itemsUbiGeoCiuTra to set
     */
    public void setItemsUbiGeoCiuTra(List<UbicacionGeografica> itemsUbiGeoCiuTra) {
        this.itemsUbiGeoCiuTra = itemsUbiGeoCiuTra;
    }

    /**
     * @return the itemsUbiGeoParTra
     */
    public List<UbicacionGeografica> getItemsUbiGeoParTra() {
        return itemsUbiGeoParTra;
    }

    /**
     * @param itemsUbiGeoParTra the itemsUbiGeoParTra to set
     */
    public void setItemsUbiGeoParTra(List<UbicacionGeografica> itemsUbiGeoParTra) {
        this.itemsUbiGeoParTra = itemsUbiGeoParTra;
    }

    /**
     * @return the personaActividadEconomica
     */
    public PersonaActividadEconomica getPersonaActividadEconomica() {
        return personaActividadEconomica;
    }

    /**
     * @param personaActividadEconomica the personaActividadEconomica to set
     */
    public void setPersonaActividadEconomica(PersonaActividadEconomica personaActividadEconomica) {
        this.personaActividadEconomica = personaActividadEconomica;
    }

    /**
     * @return the deshabilitaTrabajo
     */
    public boolean isDeshabilitaTrabajo() {
        return deshabilitaTrabajo;
    }

    /**
     * @param deshabilitaTrabajo the deshabilitaTrabajo to set
     */
    public void setDeshabilitaTrabajo(boolean deshabilitaTrabajo) {
        this.deshabilitaTrabajo = deshabilitaTrabajo;
    }

    /**
     * @return the itemsPersonaActEco
     */
    public List<PersonaActividadEconomica> getItemsPersonaActEco() {
        return itemsPersonaActEco;
    }

    /**
     * @param itemsPersonaActEco the itemsPersonaActEco to set
     */
    public void setItemsPersonaActEco(List<PersonaActividadEconomica> itemsPersonaActEco) {
        this.itemsPersonaActEco = itemsPersonaActEco;
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

    public List<PersonaTrabajoActEco> getPersonaTrabajoActEcoAux() {
        return personaTrabajoActEcoAux;
    }

    public void setPersonaTrabajoActEcoAux(List<PersonaTrabajoActEco> personaTrabajoActEcoAux) {
        this.personaTrabajoActEcoAux = personaTrabajoActEcoAux;
    }
}
