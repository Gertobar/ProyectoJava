/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.socios.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.comunes.Empresa;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.OperadoraTelefono;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.Cargo;
import ec.renafipse.mks.modelo.socios.ClasePersona;
import ec.renafipse.mks.modelo.socios.ConocimientoIfip;
import ec.renafipse.mks.modelo.socios.DesvinculaConyuge;
import ec.renafipse.mks.modelo.socios.EstadoCivil;
import ec.renafipse.mks.modelo.socios.EstadoSocio;
import ec.renafipse.mks.modelo.socios.FuenteIngresos;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDet;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDetPK;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgencia;
import ec.renafipse.mks.modelo.socios.Instruccion;
import ec.renafipse.mks.modelo.socios.Nivel;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaActEcoPri;
import ec.renafipse.mks.modelo.socios.PersonaActividadEconomica;
import ec.renafipse.mks.modelo.socios.PersonaActividadEconomicaPK;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaConyugePK;
import ec.renafipse.mks.modelo.socios.PersonaInstitucion;
import ec.renafipse.mks.modelo.socios.PersonaInstitucionRep;
import ec.renafipse.mks.modelo.socios.PersonaInstitucionRepPK;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.modelo.socios.PersonaResidencia;
import ec.renafipse.mks.modelo.socios.PersonaTelefono;
import ec.renafipse.mks.modelo.socios.Profesion;
import ec.renafipse.mks.modelo.socios.ReferenciaEntidadFinanciera;
import ec.renafipse.mks.modelo.socios.ReferenciaPersonal;
import ec.renafipse.mks.modelo.socios.RelacionPersona;
import ec.renafipse.mks.modelo.socios.Sector;
import ec.renafipse.mks.modelo.socios.SectorActividadEconomica;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.modelo.socios.SubsectorActividadEconomica;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoInstitucion;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import ec.renafipse.mks.modelo.socios.TipoVivienda;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.EstadoCuentaFacade;
import ec.renafipse.mks.negocio.comunes.EmpresaFacade;
import ec.renafipse.mks.negocio.comunes.EntidadFinancieraFacade;
import ec.renafipse.mks.negocio.comunes.OperadoraTelefonoFacade;
import ec.renafipse.mks.negocio.comunes.PeriodicidadFacade;
import ec.renafipse.mks.negocio.comunes.TipoCuentaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.comunes.TipoTelefonoFacade;
import ec.renafipse.mks.negocio.comunes.UbicacionGeograficaFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.ActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.CargoFacade;
import ec.renafipse.mks.negocio.socios.ClasePersonaFacade;
import ec.renafipse.mks.negocio.socios.ConocimientoIfipFacade;
import ec.renafipse.mks.negocio.socios.EstadoCivilFacade;
import ec.renafipse.mks.negocio.socios.EstadoSocioFacade;
import ec.renafipse.mks.negocio.socios.FuenteIngresosFacade;
import ec.renafipse.mks.negocio.socios.GrupoInstitucionAgeDetFacade;
import ec.renafipse.mks.negocio.socios.GrupoInstitucionIfipAgenciaFacade;
import ec.renafipse.mks.negocio.socios.InstruccionFacade;
import ec.renafipse.mks.negocio.socios.NivelFacade;
import ec.renafipse.mks.negocio.socios.PersonaActEcoPriFacade;
import ec.renafipse.mks.negocio.socios.PersonaActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaInstitucionFacade;
import ec.renafipse.mks.negocio.socios.PersonaInstitucionRepFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.socios.PersonaResidenciaFacade;
import ec.renafipse.mks.negocio.socios.PersonaTelefonoFacade;
import ec.renafipse.mks.negocio.socios.PersonaTrabajoActEcoFacade;
import ec.renafipse.mks.negocio.socios.ProfesionFacade;
import ec.renafipse.mks.negocio.socios.ReferenciaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.socios.ReferenciaPersonalFacade;
import ec.renafipse.mks.negocio.socios.RelacionPersonaFacade;
import ec.renafipse.mks.negocio.socios.SectorActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.SectorFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SubsectorActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.TipoIdentificacionFacade;
import ec.renafipse.mks.negocio.socios.TipoInstitucionFacade;
import ec.renafipse.mks.negocio.socios.TipoPerTipoIdeFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import ec.renafipse.mks.negocio.socios.TipoRelacionFacade;
import ec.renafipse.mks.negocio.socios.TipoViviendaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
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
import javax.faces.validator.ValidatorException;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author vicastoc
 */
@ManagedBean(name = "socioBean")
@ViewScoped
public class SocioBean extends AbstractController<Socio> implements Serializable {

    @EJB
    private SocioFacade ejbFacade;

    @EJB
    private ClasePersonaFacade ejbFacadeClasePersona;

    @EJB
    private RelacionPersonaFacade ejbFacadeRelacionPersona;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private EstadoSocioFacade ejbFacadeEstadoSocio;

    @EJB
    private ConocimientoIfipFacade ejbFacadeConocimientoIfip;

    @EJB
    private FuenteIngresosFacade ejbFacadeFuenteIngresos;

    @EJB
    private TipoIdentificacionFacade ejbFacadeTipIde;

    @EJB
    private TipoPerTipoIdeFacade ejbFacadeTipPerTipoIde;

    @EJB
    private TipoPersonaFacade ejbFacadeTipPer;

    @EJB
    private ProfesionFacade ejbFacadeProfesion;

    @EJB
    private InstruccionFacade ejbFacadeInstruccion;

    @EJB
    private EstadoCivilFacade ejbFacadeEstadoCivil;

    @EJB
    private TipoViviendaFacade ejbFacadeTipoVivienda;

    @EJB
    private PeriodicidadFacade ejbFacadePeriocidad;

    @EJB
    private SectorFacade ejbFacadeSector;

    @EJB
    private TipoInstitucionFacade ejbFacadeTipoInstitucion;

    @EJB
    private NivelFacade ejbFacadeNivel;

    @EJB
    private CargoFacade ejbFacadeCargo;

    @EJB
    private TipoTelefonoFacade ejbFacadeTipoTelefono;

    @EJB
    private OperadoraTelefonoFacade ejbFacadeOperadoraTelefono;

    @EJB
    private EntidadFinancieraFacade ejbFacadeEntidadFinanciera;

    @EJB
    private TipoCuentaEntidadFinancieraFacade ejbFacadeTipoCuentaEntFin;

    @EJB
    private ActividadEconomicaFacade ejbFacadeActvidadEconomica;

    @EJB
    private UbicacionGeograficaFacade ejbFacadeUbiGeo;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private PersonaNaturalFacade ejbFacadePerNat;

    @EJB
    private PersonaInstitucionFacade ejbFacadePerIns;

    @EJB
    private PersonaResidenciaFacade ejbFacadePerRes;

    @EJB
    private PersonaInstitucionRepFacade ejbFacadePerInsRep;

    @EJB
    private PersonaTelefonoFacade ejbFacadePerTel;

    @EJB
    private ReferenciaPersonalFacade ejbFacadeRefPer;

    @EJB
    private ReferenciaEntidadFinancieraFacade ejbFacadeRefEntFin;

    @EJB
    private PersonaActividadEconomicaFacade ejbFacadePerActEco;

    @EJB
    private PersonaActEcoPriFacade ejbFacadePerActEcoPri;

    @EJB
    private SectorActividadEconomicaFacade ejbFacadeSectorActividadEconomica;

    @EJB
    private SubsectorActividadEconomicaFacade ejbFacadeSubsectorActividadEconomica;

    @EJB
    private GrupoInstitucionIfipAgenciaFacade ejbFacadeGrupoInstitucionIfipAgencia;

    @EJB
    private GrupoInstitucionAgeDetFacade ejbFacadeGrupoInstitucionAgeDet;

    @EJB
    private TipoRelacionFacade ejbFacadeTipoRelacion;

    @EJB
    private EmpresaFacade ejbFacadeEmpresa;

    @EJB
    private PersonaTrabajoActEcoFacade ejbFacadePersonaTrabajoActEco;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private EstadoCuentaFacade ejbFacadeEstadoCuenta;

    @EJB
    private PersonaConyugeFacade ejbPersonaConyuge;

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
    private String tituloPersonaTab;
    private String nombreSocioBusqueda;
    private String mensajeCriterio;
    private String identificacionConyuge;

    private String identificacion;
    private String siglasIdentificacion;
    private String nombresCompletos;
    private String nombreConyugue;

    private String razonSocial;
    private String identificacionRepresentante;
    private String nombreRepresentante;
    private String numeroTelefono;

    private String nombresRefPer;
    private String direccionRefPer;
    private String telefonoRefPer;

    private String cuentaRefEntFin;
    private String observacionesRefEntFin;

    private int tiempoActEco;
    private Date fechaIngresoActEco;
    private StreamedContent reportePDF;
    private GeneraReporte generaReporte;
    private Date fechaActual;
    private BigDecimal valorAporte;
    private String numeroTelefonoTrabajo;
    private Date fechaIngresoTrabajo;
    private Date fechaSalidaTrabajo;
    private long tiempoTrabajo;
    private String barrioTrabajo;
    private String direccionTrabajo;
    private String referenciaTrabajo;
    private RequestContext context;

    private boolean personaNueva;
    private boolean socioNuevo;
    private boolean editaSocio;
    private boolean personaNaturalNueva;
    private boolean personaInstitucionNueva;
    private boolean personaResidenciaNueva;
    private boolean saltar;
    private boolean verPanelNatural;
    private boolean verPanelInstitucion;
    private boolean verPanelDatosBasicosSocio;
    private boolean deshabilitaAgregarRepresentante;
    private boolean deshabilitaEstadoSocio;
    private boolean deshabilitaRegistrarRepresentante;
    private boolean deshabilidaNombreRepresentante;
    private boolean deshabilitaAgregarTelefono;
    private boolean deshabilitaAgregarRefPer;
    private boolean deshabilitaAgregarRefEntFin;
    private boolean deshabilitaIdentificacion;
    private boolean deshabilitaAsignacionGrupo;
    private boolean sigueProceso;
    private boolean buscoPersona;
    private boolean esParaInstitucion;
    private boolean asignaGrupo;
    private boolean tuvoGrupo;
    private boolean deshabilitaTrabajo;
    private boolean deshabilitaFechaCaduca;
    private boolean esReingreso;

    private Socio socio;
    private Socio socioSel;
    private UbicacionGeografica ubiGeoPai;

    private UbicacionGeografica ubiGeoPaiNaci;
    private ClasePersona clasePersona;
    private RelacionPersona relacionPersona;
    private long codigoRelacionPersona = 0l;

    private UbicacionGeografica ubiGeoPro;
    private UbicacionGeografica ubiGeoCiu;
    private UbicacionGeografica ubiGeoPar;
    private UbicacionGeografica ubiGeoPaiRes;
    private UbicacionGeografica ubiGeoProRes;
    private UbicacionGeografica ubiGeoCiuRes;
    private UbicacionGeografica ubiGeoParRes;
    private UbicacionGeografica ubiGeoPaiTra;
    private UbicacionGeografica ubiGeoProTra;
    private UbicacionGeografica ubiGeoCiuTra;
    private UbicacionGeografica ubiGeoParTra;
    private Persona persona;
    private Persona personaSel;
    private PersonaNatural personaNatural;
    private PersonaNatural personaNaturalConyugeSel;
    private PersonaInstitucion personaInstitucion;
    private Persona personaRepresentanteIns;
    private PersonaConyuge personaConyuge;
    private PersonaInstitucionRep personaInstitucionRep;
    private PersonaInstitucionRep personaInstitucionRepSel;
    private PersonaResidencia personaResidencia;
    private PersonaTelefono personaTelefono;
    private PersonaTelefono personaTelefonoSel;
    private ReferenciaPersonal referenciaPersonal;
    private ReferenciaPersonal referenciaPersonalSel;
    private ReferenciaEntidadFinanciera referenciaEntFin;
    private ReferenciaEntidadFinanciera referenciaEntFinSel;
    private PersonaActividadEconomica personaActividadEconomica;
    private PersonaActividadEconomica personaActividadEconomicaSel;
    private PersonaActEcoPri personaActEcoPrin;
    private SectorActividadEconomica sectorActividadEconomica;
    private SubsectorActividadEconomica subsectorActividadEconomica;
    private GrupoInstitucionAgeDet grupoInstitucionAgeDet;
    private GrupoInstitucionIfipAgencia grupoInstitucionIfipAgencia;
    private LlamaSP llamaSP;

    private TipoRelacion tipoRelacion;
    private List<UbicacionGeografica> itemsUbiGeoPaiNaci;
    private List<ClasePersona> clasePersonaLista;

//1244
    private List<Socio> itemsSocios;
    private List<Persona> itemsPersona;
    private List<PersonaNatural> itemsConyuguePersonaNatural;
    private List<TipoIdentificacion> itemsTipoIdentificacion;
    private List<PersonaInstitucionRep> itemsPersonaInstitucionRep;
    private List<PersonaInstitucionRep> itemsPersonaInstitucionRepReg;
    private List<UbicacionGeografica> itemsUbiGeoPai;
    private List<UbicacionGeografica> itemsUbiGeoPro;
    private List<UbicacionGeografica> itemsUbiGeoCiu;
    private List<UbicacionGeografica> itemsUbiGeoPar;
    private List<UbicacionGeografica> itemsUbiGeoPaiRes;
    private List<UbicacionGeografica> itemsUbiGeoProRes;
    private List<UbicacionGeografica> itemsUbiGeoCiuRes;
    private List<UbicacionGeografica> itemsUbiGeoParRes;
    private List<PersonaTelefono> itemsPersonaTelefono;
    private List<PersonaTelefono> itemsPersonaTelefonoReg;
    private List<PersonaActividadEconomica> itemsPersonaActdEco;
    private List<ReferenciaPersonal> itemsReferenciaPersonal;
    private List<ReferenciaPersonal> itemsReferenciaPersonalReg;
    private List<ReferenciaEntidadFinanciera> itemsReferenciaEntFin;
    private List<ReferenciaEntidadFinanciera> itemsReferenciaEntFinReg;
    private List<PersonaActividadEconomica> itemsPersonaActEco;
    private List<PersonaActividadEconomica> itemsPersonaActEcoTra;
    private List<PersonaActividadEconomica> itemsPersonaActEcoReg;
    private List<SubsectorActividadEconomica> itemsSubsectorActividadEconomica;
    private List<SectorActividadEconomica> itemsSectorActividadEconomica;
    private List<ActividadEconomica> itemsActividadEconomica;
    private List<GrupoInstitucionIfipAgencia> itemsGrupoInstitucionIfipAgencia;
    private List<Cuenta> listaCuentasSocio;
    private List<TipoRelacion> itemsTipoRelacionParent;
    private ExternalContext externalContext;
    private Map<String, Object> sessionMap;

    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public SocioBean() {
        super(Socio.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        this.setFechaActual(new Date());
    }

    // *******************************************************************************************
    // --------------------------------------------------------------------------
    // -- LOGICA
    /**
     * Obtiene los datos del socio en base del criterio de la consulta
     */
    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(criterio, 6)) {
                this.setItemsSocios(this.getEjbFacade().getItemsSocio(this.getCriterio(), this.getBuscarPor(), ActivacionUsuario.getCodigoIfip()));
                if (this.getItemsSocios().size() == 1) {
                    this.buscarMoraSocio(getItemsSocios().get(0).getSocioPK().getCodigo());
                    this.buscarMoraConyuge(getItemsSocios().get(0).getSocioPK().getCodigo());
                    this.buscarMoraGarante(getItemsSocios().get(0).getCodigoPersona().getCodigo());
                    try {
                        RelacionPersona relacionPersonaEncontrada = ejbFacadeRelacionPersona.buscarRelacionPersona(getItemsSocios().get(0).getCodigoPersona());
                        if (relacionPersonaEncontrada != null) {
                            setClasePersona(relacionPersonaEncontrada.getCodigoClasePersona());
                            setPersonaRelacion(relacionPersonaEncontrada.getCodigoPersonaRelacion());
                            setNombreCompletoPersona(relacionPersonaEncontrada.getCodigoPersonaRelacion().getNombreCompleto());
                        }
                    } catch (Exception e) {
                    }
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsSocios(null);
                this.setSocioSel(null);
                this.preparaSocio();
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"socioBean", "obtieneSocios", CapturaError.getErrorException(ex)});
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
        Socio socio = ejbFacade.find(new SocioPK(codigoSocio, ActivacionUsuario.getCodigoIfip()));
        //System.out.println("Busqueda Socio Mora: " + socio);
        if (socio == null) {
            return;
        }
        Persona personaSocio = socio.getCodigoPersona();
        Long codigoPersona = personaSocio.getCodigo();
        if (String.valueOf(personaSocio.getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
            PersonaNatural personaNatural = this.ejbFacadePersonaNatural.find(codigoPersona);
            //System.out.println("personaNatural " + personaNatural + " estado civil " + personaNatural.getCodigoEstadoCivil());
            // Verificando si el estado civil es  casado para recuperar el conyue
            if (personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("2")) == 0 || personaNatural.getCodigoEstadoCivil().getCodigo().compareTo(Long.parseLong("6")) == 0) {

                // Buscando el conyuge actual                
                List<PersonaConyuge> listaPersonaConyuge = ejbFacadeConyugePersona.getItemsCodigoPersonaElminado(codigoPersona, 'N');

                // Si existe un conyuge definido para el socio                
                if (listaPersonaConyuge.size() == 1) {
                    List<Socio> socios = ejbFacade.getItemsSociofindByIdentificacion(listaPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona().getIdentificacion());
                    //System.out.println("Busqueda Socios Mora: " + socios);
                    if (socios.isEmpty()) {
                        return;
                    }
                    Long idSocio = socios.get(0).getSocioPK().getCodigo();
                    //System.out.println("Busqueda idSocio Mora: " + idSocio);
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
                //En caso de dias de mora > 0 sale la advertencia
                if( diasMora > 0 ){
                    aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioTieneMora") + "\n";
                    aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", sol.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(sol.getSolicitudDetallePK().getNumeroCredito())).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                    MuestraMensaje.addInformacion(aux);
                }
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
     * OBTIENE LOS CONYUGUES PARA EL LISTADO DE BUSQUEDA.
     */
    public void obtienePersonaNaturalConyugue() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(nombreConyugue, 6)) {
                this.setItemsConyuguePersonaNatural(ejbFacadePerNat.getItemsLikeNombre(nombreConyugue));
            } else {
                this.setMensajeCriterio(Validaciones.msg);
                this.setItemsConyuguePersonaNatural(null);
                this.setPersonaNaturalConyugeSel(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"socioBean", "obtienePersonaNaturalConyugue", CapturaError.getErrorException(ex)});
        }
    }

    public void seleccionaSocio(SelectEvent event) {
        if (getPersonaNaturalConyugeSel() != null && getPersonaNaturalConyugeSel().getPersona() != null) {
            setIdentificacionConyuge(getPersonaNaturalConyugeSel().getPersona().getIdentificacion());
            buscaPersonaConyugue();
        }
        // Actualizando componentes de la vista
        context = RequestContext.getCurrentInstance();
        List<String> listaComponentesActualizar = new ArrayList<String>();
        //listaComponentesActualizar.add(":SocioNuevoFor:etiquetaConyugue");
        //listaComponentesActualizar.add(":SocioNuevoFor:codigoConyugue");
        //listaComponentesActualizar.add(":SocioNuevoFor:personaNaturalPan");
        RequestContext.getCurrentInstance().update(":SocioNuevoFor:etiquetaConyugue");
        RequestContext.getCurrentInstance().update(":SocioNuevoFor:codigoConyugue");
        //context.update(listaComponentesActualizar);
    }

    public void buscarConyuge() {

        setIdentificacionConyuge("");
        if (this.getPersonaNatural().getCodigoEstadoCivil() != null && this.getPersonaNatural().getCodigoEstadoCivil().getCodigo() == 2) {
            PersonaConyuge pc = ejbPersonaConyuge.getPersonaCodigoFechaRegistro(this.getPersona().getCodigo(), 'N');
            this.setPersonaConyuge((pc != null) ? pc : nuevoPersonaConyuge());
            if (this.personaConyuge.getPersonaNaturalConyuge() != null) {
                this.setPersonaNaturalConyugeSel(this.personaConyuge.getPersonaNaturalConyuge());
                this.getPersonaNaturalConyugeSel().setPersona((this.getPersonaNaturalConyugeSel() != null) ? ejbFacadePersona.getPersonaByCodigo(this.getPersonaNaturalConyugeSel().getCodigoPersona()) : new Persona());

                if (this.getPersonaNaturalConyugeSel() != null && this.getPersonaNaturalConyugeSel().getPersona() != null && this.getPersonaNaturalConyugeSel().getPersona().getIdentificacion() != null) {
                    setIdentificacionConyuge(this.getPersonaNaturalConyugeSel().getPersona().getIdentificacion());
                }
            }
        } else {
            this.setPersonaConyuge(null);
            setPersonaNaturalConyugeSel(new PersonaNatural());
            getPersonaNaturalConyugeSel().setPersona(new Persona());
        }
    }

    public void buscaPersonaConyugue1() {

    }

    public void buscaPersonaConyugue() {
        if (this.getPersonaNatural().getCodigoEstadoCivil() != null && this.getPersonaNatural().getCodigoEstadoCivil().getCodigo() == 2) {
            this.setPersonaConyuge(nuevoPersonaConyuge());
            // this.setPersonaNaturalConyuge(this.personaConyuge.getPersonaConyugeSel());
            this.getPersonaNaturalConyugeSel().setPersona((getPersonaNaturalConyugeSel() != null) ? ejbFacadePersona.getPersonaByCodigo(this.getPersonaNaturalConyugeSel().getCodigoPersona()) : new Persona());

            if (this.getPersonaNaturalConyugeSel() != null && this.getPersonaNaturalConyugeSel().getPersona() != null && this.getPersonaNaturalConyugeSel().getPersona().getIdentificacion() != null) {
                setIdentificacionConyuge(this.getPersonaNaturalConyugeSel().getPersona().getIdentificacion());
            }
        } else {
            this.setPersonaConyuge(null);
            setPersonaNaturalConyugeSel(new PersonaNatural());
            getPersonaNaturalConyugeSel().setPersona(new Persona());
        }
        String validaConyuge = validaConyuge(getPersona().getCodigo(), getPersonaNaturalConyugeSel().getCodigoPersona());
        if (validaConyuge != null) {
            MuestraMensaje.addError(validaConyuge);
            setIdentificacionConyuge(null);
            setPersonaNaturalConyugeSel(null);
        }
    }

    /**
     * Validar la inforamcion de conyuges
     *
     * @param codigoPersona
     * @param codigoPersonaConyuge
     * @return
     */
    public String validaConyuge(Long codigoPersona, Long codigoPersonaConyuge) {
        return ejbPersonaConyuge.validaConyuge(codigoPersona, codigoPersonaConyuge);
    }

    public PersonaConyuge nuevoPersonaConyuge() {
        if (!getIdentificacionConyuge().isEmpty()) {
            this.setPersonaNaturalConyugeSel(ejbFacadePerNat.getIdentificacion(getIdentificacionConyuge()));
            if (getPersonaNaturalConyugeSel() == null) {// No se encuentra registrado el usuario.                
                setPersonaNaturalConyugeSel(new PersonaNatural());
                getPersonaNaturalConyugeSel().setPersona(new Persona());
                return new PersonaConyuge();
            }
            PersonaConyuge conyuge = new PersonaConyuge(new PersonaConyugePK());
            conyuge.getPersonaConyugePK().setCodigoPersonaConyuge(getPersonaNaturalConyugeSel().getCodigoPersona());
            conyuge.setPersonaNaturalConyuge(getPersonaNaturalConyugeSel());
            conyuge.setPersonaNatural(getPersonaNatural());
            conyuge.setEliminado('N');
            conyuge.setFirma('S');
            conyuge.setFechaRegistro(getFechaActual());
            conyuge.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            return conyuge;
        } else {///Identificador vacio            
            setPersonaNaturalConyugeSel(new PersonaNatural());
            getPersonaNaturalConyugeSel().setPersona(new Persona());
            return new PersonaConyuge();
        }
    }

    public void preparaBusquedaSocioLista(ActionEvent actionEvent) throws IOException {
        nombreConyugue = "";
    }

    public boolean guardaPersonaConyuge() {
        Long codigoEstadoCivil = getPersonaNatural().getCodigoEstadoCivil().getCodigo();
        if (codigoEstadoCivil == 3 || codigoEstadoCivil == 1) {
            return ejbPersonaConyuge.desvinculaConyuge(getPersona().getCodigo(), ActivacionUsuario.getCodigoUsuario());
        }
        if (getPersonaConyuge() == null) {
            return true;
        }
        if (getPersonaConyuge().getPersonaConyugePK() == null) {
            return true;
        }
        boolean bandera1 = ejbPersonaConyuge.guardaConyuge(getPersona().getCodigo(), personaConyuge.getPersonaConyugePK().getCodigoPersonaConyuge());
        boolean bandera2 = ejbPersonaConyuge.guardaConyuge(personaConyuge.getPersonaConyugePK().getCodigoPersonaConyuge(), getPersona().getCodigo());
        return bandera1 && bandera2;
    }

    /**
     * Metodo para dar segumiento al wizard del socio
     *
     * @param event
     * @return Evento
     */
    public String sigueProcesoSocio(FlowEvent event) {
        try {
            externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            if (isSaltar()) {
                setSaltar(false);
                return "confirmacionTab";
            } else {

                if (event.getOldStep().equals("datosBasicosTab") && event.getNewStep().equals("detallePersonaNaturalTab")) {
                    this.validaSocioIngresado();
                    if (!this.isSigueProceso()) {

                        return "datosBasicosTab";
                    } else {

                        if (this.getSiglasIdentificacion().equals("C") && this.persona.getFechaCaducidadIdentificacion() == null) {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseFechaCaducidad"));
                            return "datosBasicosTab";
                        }
                    }

                    if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                        return "detallePersonaNaturalTab";
                    } else {
                        return "detallePersonaInstitucionTab";
                    }
                }

                /*if (event.getOldStep().equals("detallePersonaNaturalTab") && event.getNewStep().equals("datosBasicosTab")) {

                 if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                 return "datosBasicosTab";
                 }

                 }*/
                //event.getOldStep() detallePersonaNaturalTab event.getNewStep() detallePersonaInstitucionTab
                if (event.getOldStep().equals("detallePersonaNaturalTab") && event.getNewStep().equals("detallePersonaInstitucionTab")) {
                    this.validaPersona();
                    if (this.sigueProceso) {
                        this.validaPersonaNatural();
                        //System.out.println("Sigue proceso persona natural " + this.isSigueProceso());
                        if (!this.isSigueProceso()) {

                            return "detallePersonaNaturalTab";
                        }
                    } else {
                        return "datosBasicosTab";
                    }

                    //System.out.println("Entro en personta tab");
                    if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                        return "residenciaTab";
                    }

                }

                if (event.getOldStep().equals("detallePersonaInstitucionTab") && event.getNewStep().equals("residenciaTab")) {
                    if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("S")) {
                        this.validaPersona();
                        if (this.sigueProceso) {
                            this.validaIngresoRepresentante();
                            if (this.isSigueProceso()) {
                                this.validaRazonSocialIngresada();
                            }
                        }

                        if (!this.isSigueProceso()) {
                            return "detallePersonaInstitucionTab";
                        }
                        return "residenciaTab";
                    }

                }

                if (event.getOldStep().equals("detallePersonaInstitucionTab") && event.getNewStep().equals("detallePersonaNaturalTab")) {
                    if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("S")) {
                        return "datosBasicosTab";
                    }

                }

                if (event.getOldStep().equals("detallePersonaNaturalTab") && event.getNewStep().equals("detallePersonaInstitucionTab")) {
                    if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("S")) {
                        return "residenciaTab";
                    }

                }

                if (event.getOldStep().equals("residenciaTab") && event.getNewStep().equals("trabajoActividadEconomicaTab")) {
                    if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                        return "detallePersonaNaturalTab";
                    }
                }

                if (event.getOldStep().equals("actividadEconomicaTab") && event.getNewStep().equals("actividadEconomicaTab")) {
                    return "trabajoActividadEconomicaTab";
                }

                if (event.getOldStep().equals("residenciaTab") && event.getNewStep().equals("detallePersonaInstitucionTab")) {
                    if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                        return "detallePersonaNaturalTab";
                    }
                }

                if (event.getOldStep().equals("actividadEconomicaTab") && event.getNewStep().equals("trabajoActividadEconomicaTab")) {
                    if ((Socio) socioSel != null) {
                        this.guardaActividadEconomica();
                        sessionMap.put("socio", this.socioSel);
                    } else {
                        this.guardaSocio(null);
                        sessionMap.put("socio", this.socio);
                    }
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('SituacionEconomicaDialog').show()");
                    this.validaPrincipalActEco();
                    if (!this.isSigueProceso()) {
                        return "actividadEconomicaTab";
                    }

                }
                if (event.getOldStep().equals("confirmacionTab") && event.getNewStep().equals("trabajoActividadEconomicaTab")) {
                    //this.validaPrincipalActEco(); 
                    this.validaPrincipalActEco();
                    if ((Socio) socioSel != null) {
                        sessionMap.put("socio", this.socioSel);
                    } else {
                        sessionMap.put("socio", this.socio);
                    }
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('SituacionEconomicaDialog').show()");
                    if (!this.isSigueProceso()) {
                        return "trabajoActividadEconomicaTab";
                    }
                }
                //

                /*
                
                 if (event.getOldStep().equals("grupoTab") && event.getNewStep().equals("confirmacionTab")) {
                 this.validaGrupo();
                 if (!this.isSigueProceso()) {
                 return "grupoTab";
                 }*/
            }

            return event.getNewStep();

        } catch (Exception ex) {
            return "datosBasicosTab";
        }
    }

    // -------------------------------------------------------------------------------------------------
    // -- SOCIO
    public void preparaSocio() {

        //pais de nacionalidad
        this.setItemsUbiGeoPaiNaci(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.setUbiGeoPaiNaci(null);
        this.setItemsUbiGeoPai(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.setUbiGeoPai(null);
        this.cambiaUbiGeoPai();

        this.setItemsUbiGeoPaiRes(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.setUbiGeoPaiRes(null);
        this.cambiaUbiGeoPaiRes();

        this.deshabilitaAgregarRepresentante = true;
        this.deshabilitaAgregarTelefono = true;
        this.deshabilitaAgregarRefPer = true;
        this.deshabilitaAgregarRefEntFin = true;
        this.deshabilitaRegistrarRepresentante = true;
        this.deshabilidaNombreRepresentante = true;
        this.deshabilitaEstadoSocio = true;
        this.deshabilitaIdentificacion = false;
        this.sigueProceso = true;
        this.buscoPersona = false;
        this.personaNueva = true;
        this.personaInstitucionNueva = true;
        this.personaNaturalNueva = true;
        this.personaResidenciaNueva = true;
        this.fechaActual = new Date();
        this.deshabilitaAsignacionGrupo = true;
        this.asignaGrupo = false;
        this.tuvoGrupo = false;

        this.identificacion = null;
        this.siglasIdentificacion = null;
        this.nombresCompletos = null;
        this.razonSocial = null;
        this.identificacionRepresentante = null;
        this.nombreRepresentante = null;
        this.numeroTelefono = null;
        this.nombresRefPer = null;
        this.direccionRefPer = null;
        this.telefonoRefPer = null;
        this.cuentaRefEntFin = null;
        this.observacionesRefEntFin = null;
        this.tiempoActEco = 1;
        this.tiempoTrabajo = 1;
        this.fechaIngresoActEco = null;
        this.grupoInstitucionIfipAgencia = null;
        this.valorAporte = new BigDecimal("0.00");
        this.deshabilitaFechaCaduca = true;

        this.setPersona(new Persona());
        this.setPersonaNatural(new PersonaNatural());
        this.setPersonaInstitucion(new PersonaInstitucion());
        this.setPersonaResidencia(new PersonaResidencia());
        this.setPersonaTelefono(new PersonaTelefono());
        this.setPersonaInstitucionRep(new PersonaInstitucionRep());
        this.setPersonaRepresentanteIns(new Persona());
        this.setReferenciaPersonal(new ReferenciaPersonal());
        this.setReferenciaEntFin(new ReferenciaEntidadFinanciera());
        this.setPersonaActividadEconomica(new PersonaActividadEconomica());

        this.setItemsPersonaInstitucionRep(new ArrayList<PersonaInstitucionRep>());
        this.setItemsPersonaTelefono(new ArrayList<PersonaTelefono>());
        this.setItemsReferenciaPersonal(new ArrayList<ReferenciaPersonal>());
        this.setItemsReferenciaEntFin(new ArrayList<ReferenciaEntidadFinanciera>());
        this.setItemsPersonaActEco(new ArrayList<PersonaActividadEconomica>());
        this.setItemsSectorActividadEconomica(new ArrayList<SectorActividadEconomica>());
        this.setItemsSubsectorActividadEconomica(new ArrayList<SubsectorActividadEconomica>());

        //this.setItemsSectorActividadEconomica(this.ejbFacadeSectorActividadEconomica.getItemsSectorEliminado('N'));
        this.setItemsActividadEconomica(ejbFacadeActvidadEconomica.getItemsActividadEconomicaEliminado(7, 'S', 'N'));
        this.sectorActividadEconomica = null;
        this.subsectorActividadEconomica = null;

        this.esReingreso = false;

        //this.cambiaSector();
        //this.cambiaSubSector();
    }

    /**
     * Prepara la creacion de un Nuevo Socio
     *
     * @param event
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Socio creaSocio(ActionEvent event) throws InstantiationException, IllegalAccessException {
        try {
            this.socioSel = null;
            this.preparaSocio();
            this.editaSocio = false;
            this.buscoPersona = false;
            this.setSocioNuevo(true);
            this.setSocio(new Socio());
            this.getSocio().setSocioPK(new SocioPK());
            this.getSocio().setCodigoEstadoSocio(this.ejbFacadeEstadoSocio.find(Long.parseLong("1")));
            this.getSocio().setCodigoIfipAgencia(this.ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia()));
            this.getSocio().setCodigoUsuarioCreacion(this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario()));
            this.setItemsGrupoInstitucionIfipAgencia(this.ejbFacadeGrupoInstitucionIfipAgencia.getItemsGrupoIfipAgenciaEliminado(ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
            this.grupoInstitucionAgeDet = new GrupoInstitucionAgeDet();
            this.getSocio().getSocioPK().setCodigo(Long.parseLong("0"));
            this.buscarConyuge();
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"socioBean", "creaSocio", CapturaError.getErrorException(ex)});

            return null;
        }
        return this.getSocio();

    }

    public Socio preparaReingresoSocio(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        Socio soc = editaSocio(event);
        //System.out.println("Codigo Estado " + socio.getCodigoEstadoSocio().getCodigo());
        if (socio.getCodigoEstadoSocio().getCodigo() == 2) {
            socio.setCodigoEstadoSocio(ejbFacadeEstadoSocio.find(new Long(1)));
            context.execute("SocioNuevoDialog.show();");
            esReingreso = true;
            return soc;
        } else {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioYaEsActivo"));
            return null;
        }

    }

    /**
     * Prepara la Edicion del Socio Seleccionado
     *
     * @param event
     * @return
     */
    public Socio editaSocio(ActionEvent event) {
        try {
            this.preparaSocio();
            this.setSocio(this.getSocioSel());
            this.setPersona(this.getSocio().getCodigoPersona());
            this.setSocioNuevo(false);
            this.sigueProceso = true;
            this.buscoPersona = false;
            this.editaSocio = true;
            if (this.persona.getCodigoTipoIdentificacion().getSiglas() == 'C') {
                this.deshabilitaFechaCaduca = false;
            }

            this.buscoPersona = false;
            this.setItemsTipoIdentificacion(this.ejbFacadeTipIde.findAll());
            this.buscaPersona();
            this.getSocio().setCodigoIfipAgencia(this.ejbFacadeIfipAgencia.find(this.getSocio().getCodigoIfipAgencia().getCodigo()));

            // Buscando el grupo del Socio, se coloco el primero encontrado como no eliminado
            this.setItemsGrupoInstitucionIfipAgencia(this.ejbFacadeGrupoInstitucionIfipAgencia.getItemsGrupoIfipAgenciaEliminado(this.getSocio().getCodigoIfipAgencia().getCodigo(), 'N'));
            this.grupoInstitucionAgeDet = null;
            this.buscarConyuge();
            List<GrupoInstitucionAgeDet> listaGrupoInstitucionAgeDet = this.ejbFacadeGrupoInstitucionAgeDet.getItemsCodigoSocioCodigoIfip(this.getSocio().getSocioPK().getCodigo(), this.getSocio().getSocioPK().getCodigoIfip());
            if (!listaGrupoInstitucionAgeDet.isEmpty()) {
                this.tuvoGrupo = true;
                for (GrupoInstitucionAgeDet giad : listaGrupoInstitucionAgeDet) {
                    if (giad.getEliminado() == 'N') {
                        this.asignaGrupo = true;
                        this.grupoInstitucionAgeDet = giad;
                    }

                    if (grupoInstitucionAgeDet != null) {
                        break;
                    }
                }
                if (grupoInstitucionAgeDet == null) {
                    this.asignaGrupo = false;
                    this.deshabilitaAsignacionGrupo = true;
                    grupoInstitucionIfipAgencia = null;
                    grupoInstitucionAgeDet = new GrupoInstitucionAgeDet();
                } else {
                    this.asignaGrupo = true;
                    this.deshabilitaAsignacionGrupo = false;
                    this.grupoInstitucionIfipAgencia = grupoInstitucionAgeDet.getGrupoInstitucionIfipAgencia();
                    this.valorAporte = this.grupoInstitucionAgeDet.getValorAporte();
                }
            } else {
                this.asignaGrupo = false;
                this.tuvoGrupo = false;
                this.deshabilitaAsignacionGrupo = true;
                grupoInstitucionIfipAgencia = null;
                this.valorAporte = new BigDecimal("0.00");
                grupoInstitucionAgeDet = new GrupoInstitucionAgeDet();
            }
            /*this.buscaPersonaNatural();
             this.buscaPersonaInsitucion();
             this.buscaPersonaResidencia();*/
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            ex.printStackTrace();
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"socioBean", "editaSocio", CapturaError.getErrorException(ex)});

            return null;
        }

        //this.setSiglasIdentificacion(String.valueOf(this.getPersona().getCodigoTipoIdentificacion().getSiglas()));
        return this.getSocio();
    }

    public void oncloseDiag() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
        RequestContext context = RequestContext.getCurrentInstance();

        if ((Socio) sessionMap.get("socio") != null) {
            if (sessionMap.get("tab").equals("confirmacionTab")) {
                context.execute("PF('personaWizer').next()");
                this.setSocioNuevo(false);
//                this.setPersonaNueva(false);
                this.editaSocio = true;
                this.buscoPersona = true;
                sessionMap.remove("tab");
            } else {
                context.execute("PF('personaWizer').back()");
            }
        }
        sessionMap.remove("socio");
    }

    /**
     * Metodo que guarda el socio
     *
     * @param actionEvent
     */
    public void guardaSocio(ActionEvent actionEvent) {
        try {

            this.guardaPersona();
            if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                this.guardaPersonaNatural();
            } else {
                this.guardaPersonaInstitucion();
            }
            if(String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")){
                if (!this.guardaPersonaConyuge()) {
                    return;
                }
            }

            this.guardaResidencia();

            this.guardaTelefono();

            this.guardaReferenciaPersonal();

            this.guardaReferenciaEntFin();

            this.guardaActividadEconomica();

            this.socio.setCodigoPersona(this.getPersona());
            this.getSocio().setFechaActualizacion(new Date());
            if (this.isSocioNuevo()) {
                this.getSocio().setFechaCreacion(new Date());
                if (this.obtieneSecuenciasSocio()) {
                    this.socio.getSocioPK().setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                    this.ejbFacade.create(socio);
                    if (getClasePersona() != null) {
                        relacionPersona = new RelacionPersona();
                        relacionPersona.setCodigoPersona(socio.getCodigoPersona());
                        relacionPersona.setCodigoClasePersona(getClasePersona());
                        relacionPersona.setCodigoPersonaRelacion(getPersonaRelacion());
                        relacionPersona.setDescripcion("DESCRIPCION");
                        ejbFacadeRelacionPersona.create(relacionPersona);
                    }
                }
            } else {
                this.ejbFacade.actualiza(socio.getCodigoPersona(), this.socio.getCodigoMotivoSocio(), this.socio.getCodigoConocimientoIfip(), this.socio.getCodigoEstadoSocio(), this.socio.getEntregoRequesitios(), this.socio.getObservaciones(), new Date(), this.socio.getSocioPK().getCodigo(), this.socio.getSocioPK().getCodigoIfip());
                //this.ejbFacade.edit(socio);                
                if (getClasePersona() != null) {
                    ejbFacadeRelacionPersona.actualizar(socio.getCodigoPersona(), getClasePersona(), getPersonaRelacion(), "descripcion");
                }
            }

            // Guardando el grupo seleccionado            
            boolean crearGrupo = true;
            if (this.asignaGrupo) {

                List<GrupoInstitucionAgeDet> lista = this.ejbFacadeGrupoInstitucionAgeDet.getItemsCodigoSocioCodigoIfip(this.socio.getSocioPK().getCodigo(), this.socio.getSocioPK().getCodigoIfip());
                for (GrupoInstitucionAgeDet item : lista) {
                    this.ejbFacadeGrupoInstitucionAgeDet.actualizaElimiado('S', item.getGrupoInstitucionAgeDetPK().getCodigoGrupo(), item.getGrupoInstitucionAgeDetPK().getCodigoIfip(), item.getGrupoInstitucionAgeDetPK().getCodigoSocio());
                }
                grupoInstitucionAgeDet = this.ejbFacadeGrupoInstitucionAgeDet.find(new GrupoInstitucionAgeDetPK(this.getGrupoInstitucionIfipAgencia().getGrupoInstitucion().getCodigo(), this.socio.getSocioPK().getCodigo(), this.socio.getSocioPK().getCodigoIfip()));
                if (grupoInstitucionAgeDet != null) {
                    crearGrupo = false;
                }

                if (crearGrupo) {
                    grupoInstitucionAgeDet = new GrupoInstitucionAgeDet();
                    this.grupoInstitucionAgeDet.setGrupoInstitucionAgeDetPK(new GrupoInstitucionAgeDetPK());
                    this.grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().setCodigoSocio(this.socio.getSocioPK().getCodigo());
                    this.grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().setCodigoIfip(this.socio.getSocioPK().getCodigoIfip());
                    this.grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().setCodigoGrupo(this.grupoInstitucionIfipAgencia.getGrupoInstitucion().getCodigo());
                    this.grupoInstitucionAgeDet.setCodigoIfipAgencia(this.grupoInstitucionIfipAgencia.getGrupoInstitucionIfipAgenciaPK().getCodigoIfipAgencia());
                    this.grupoInstitucionAgeDet.setEliminado('N');
                    this.grupoInstitucionAgeDet.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                    this.grupoInstitucionAgeDet.setFechaRegistro(new Date());
                    this.grupoInstitucionAgeDet.setValorAporte(valorAporte);

                    this.ejbFacadeGrupoInstitucionAgeDet.create(grupoInstitucionAgeDet);
                } else {
                    grupoInstitucionAgeDet.setEliminado('N');
                    grupoInstitucionAgeDet.setValorAporte(valorAporte);
                    this.ejbFacadeGrupoInstitucionAgeDet.actualiza(grupoInstitucionAgeDet.getCodigoIfipAgencia(), 'N', grupoInstitucionAgeDet.getValorAporte(), grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().getCodigoGrupo(), grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().getCodigoIfip(), grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().getCodigoSocio());
                    // this.ejbFacadeGrupoInstitucionAgeDet.edit(grupoInstitucionAgeDet);
                }
            } else {
                if (this.tuvoGrupo) {
                    if (grupoInstitucionAgeDet != null) {
                        //System.out.println("grupoInstitucionAgeDet " + grupoInstitucionAgeDet);
                        this.grupoInstitucionAgeDet.setEliminado('S');
                        //this.ejbFacadeGrupoInstitucionAgeDet.edit(grupoInstitucionAgeDet);
                        this.ejbFacadeGrupoInstitucionAgeDet.actualizaElimiado('S', grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().getCodigoGrupo(), grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().getCodigoIfip(), grupoInstitucionAgeDet.getGrupoInstitucionAgeDetPK().getCodigoSocio());
                    }
                }
            }

            //System.out.println("Guarda Socio Conyuge");
            this.socioSel = this.socio;

            MuestraMensaje.addSatisfactorioPersistencia(1);

            if (this.esReingreso) {
                this.listaCuentasSocio = ejbFacadeCuenta.getItemsPuedeReactivar(socio.getSocioPK().getCodigo(), socio.getSocioPK().getCodigoIfip(), 'S');
                for (Cuenta cuenta : listaCuentasSocio) {
                    //cuenta.setCodigoEstado(ejbFacadeEstadoCuenta.find(new Long(3)));
                    //System.out.println("Cuenta "+cuenta);                    
                    ejbFacadeCuenta.actualizaEstado(ejbFacadeEstadoCuenta.find(3L), cuenta.getCodigo());
                }
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            ex.printStackTrace();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"socioBean", "guardaSocio", CapturaError.getErrorException(ex)});

        }
    }

    /**
     * Valida si existe un socio Ingresado con la Identificacion Digitada
     */
    public void validaSocioIngresado() {
        ////System.out.println("Persona de socio " + this.getPersona());
        boolean existeSocio = (this.getEjbFacade().getItemsSocio(this.getPersona().getIdentificacion(),
                "I",
                ActivacionUsuario.getCodigoIfip()).size() > 0);
        ////System.out.println("Pasa la busqueda del socio " + this.getPersona());
        String msg = null;
        if (this.isSocioNuevo()) {
            if (!existeSocio) {
                this.buscoPersona = false;
                this.buscaPersona();
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioExistente");
                this.sigueProceso = false;
            }
        } else {
            if (!this.getIdentificacion().equals(this.persona.getIdentificacion())) {
                if (this.ejbFacadePersona.getPersona(this.persona.getIdentificacion()) != null) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionExistente");
                    this.sigueProceso = false;
                } else {
                    //this.preparaSocio();                    
                    //this.setSocioNuevo(true);
                    //System.out.println("BUsco Persona");
                    this.buscaPersona();
                    this.buscoPersona = false;
                }
            }
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
    }

    /**
     * Imprime la solicitud del Socio Seleccionado
     *
     * @param actionEvent
     */
    public void imprime(ActionEvent actionEvent) {
        try {

            this.imprimeSolicitud(this.getSocioSel().getSocioPK().getCodigo(),
                    this.getSocioSel().getSocioPK().getCodigoIfip(), this.getSocioSel().getCodigoPersona().getIdentificacion(),
                    this.getSocioSel().getCodigoPersona().getCodigoTipoPersona().getEsParaInstitucion());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Genera el reporte PDF de la impresion de la Solicitud
     *
     * @param codigoSocio Codigo del Socio
     * @param codigoIfip codigo de la Ifip
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeSolicitud(Long codigoSocio, Long codigoIfip, String socioIdentidad, char esParaInstitucion) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/socios/socio/reporte/";
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoSocio", codigoSocio);
        getGeneraReporte().getParametros().put("codigoIfip", codigoIfip);
        getGeneraReporte().getParametros().put("socioIdentidad", socioIdentidad);
        getGeneraReporte().getParametros().put("SubReportInformacionLaboral", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "InformacionLaboral"));
        getGeneraReporte().getParametros().put("subReportSocioTelefonos", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioTelefonos"));
        getGeneraReporte().getParametros().put("SubReportSocioPasivos", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioPasivos"));
        getGeneraReporte().getParametros().put("SubReportSocioActivos", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioActivos"));
        getGeneraReporte().getParametros().put("subReportSocioEgresos", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioEgresos"));
        getGeneraReporte().getParametros().put("subReportSocioIngresos", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioIngresos"));
        getGeneraReporte().getParametros().put("subReportPatrimonio", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioPatrimonio"));
        getGeneraReporte().getParametros().put("subReportDisponible", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioDisponible"));
        getGeneraReporte().getParametros().put("subReportSocioRefenciasPersonales", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "SocioRefenciasPersonales"));
        getGeneraReporte().getParametros().put("subReportSocioReferenciasEntidades", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioRefernciasEntidades"));
        getGeneraReporte().getParametros().put("SubReportRepresentantes", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioIntitucionRepresentantes"));
        getGeneraReporte().getParametros().put("subReportSocioUAF", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "socioUAF"));

        if (String.valueOf(esParaInstitucion).equals("N")) {
            nombreReporte = "solicitudSocios";
        } else {
            nombreReporte = "solicitudSociosIns";
        }
        getGeneraReporte().exporta("/financiero/socios/socio/reporte/", nombreReporte,
                "solicitudSocio" + String.valueOf(codigoSocio) + ".pdf",
                "PDF", externalContext, facesContext);
        ////System.out.println("impresion dada");

    }
    // -- FIN DE SOCIO
    // ----------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------
    // -- PERSONA
    public void guardaPersona() {
        if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
            this.getPersona().setNombreCompleto(this.getPersonaNatural().getPrimerApellido() + " "
                    + this.getPersonaNatural().getSegundoApellido() + " "
                    + this.getPersonaNatural().getNombres());
        } else {
            this.getPersona().setNombreCompleto(this.getPersonaInstitucion().getRazonSocial());
        }

        if (this.isPersonaNueva()) {
            this.getPersona().setFechaIngreso(new Date());
            this.getPersona().setFechaActualizacion(this.getPersona().getFechaIngreso());
            ejbFacadePersona.create(persona);
        } else {
            this.getPersona().setFechaActualizacion(new Date());
            //ejbFacadePersona.edit(persona);            
            ejbFacadePersona.actualiza(this.getPersona().getCodigoTipoIdentificacion(), this.getPersona().getCodigoTipoPersona(), this.getPersona().getIdentificacion(), this.getPersona().getNombreCompleto(), new Date(), this.getPersona().getCorreoEletronico(), this.getPersona().getFechaCaducidadIdentificacion(), this.getPersona().getCodigo());
        }
    }

    /**
     * Busca los datos basicos de la persona
     */
    public void buscaPersona() {

        Persona personaExistente;
        ////System.out.println("Busca Persona: " + this.getPersona() + " Ide " + this.getPersona().getIdentificacion());

        if (this.isSocioNuevo() || (!this.isSocioNuevo()) && !this.buscoPersona) {
            personaExistente = this.ejbFacadePersona.getPersona(this.getPersona().getIdentificacion());
            this.identificacion = this.persona.getIdentificacion();
            this.buscoPersona = true;
            if (personaExistente != null) {

                Date fechaCaducidad = null;
                if (this.persona.getFechaCaducidadIdentificacion() != null) {
                    //System.out.println("Fecha Caducicidad en Persona Existente ");
                    fechaCaducidad = this.persona.getFechaCaducidadIdentificacion();
                    //System.out.println("Fecha Caducicidad en Persona Existente " + fechaCaducidad);
                }
                this.setPersona(personaExistente);
                if (fechaCaducidad != null) {
                    this.persona.setFechaCaducidadIdentificacion(fechaCaducidad);
                }
                this.setPersonaNueva(false);
                this.setSiglasIdentificacion(String.valueOf(this.getPersona().getCodigoTipoIdentificacion().getSiglas()));

                if (this.isSocioNuevo()) {
                    String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaExistente");
                    MuestraMensaje.addInformacion(msg);
                }

                this.setItemsPersonaTelefono(this.ejbFacadePerTel.getItemsPorPersona(persona));
                this.setItemsPersonaTelefonoReg(this.ejbFacadePerTel.getItemsPorPersona(persona));
                if (this.getItemsPersonaTelefono() == null) {
                    this.setItemsPersonaTelefono(new ArrayList<PersonaTelefono>());
                }

                this.setItemsReferenciaPersonal(this.ejbFacadeRefPer.getItemsRefPerPer(persona));
                this.setItemsReferenciaPersonalReg(this.ejbFacadeRefPer.getItemsRefPerPer(persona));
                if (this.getItemsReferenciaPersonal() == null) {
                    this.setItemsReferenciaPersonal(new ArrayList<ReferenciaPersonal>());
                }

                this.setItemsReferenciaEntFin(this.ejbFacadeRefEntFin.getItemsRefEntFinPer(persona));
                this.setItemsReferenciaEntFinReg(this.ejbFacadeRefEntFin.getItemsRefEntFinPer(persona));
                if (this.getItemsReferenciaEntFin() == null) {
                    this.setItemsReferenciaEntFin(new ArrayList<ReferenciaEntidadFinanciera>());
                }

                this.setItemsPersonaActEco(this.ejbFacadePerActEco.getItemsPersonaActividadEconomoica(persona.getCodigo()));
                if (this.getItemsPersonaActEco() == null) {
                    this.setItemsPersonaActEco(new ArrayList<PersonaActividadEconomica>());
                    this.setItemsPersonaActEcoTra(new ArrayList<PersonaActividadEconomica>());
                } else {
                    cargaActividadEconomicaTrabajo();
                }

                this.buscaPersonaNatural();
                this.buscaPersonaInsitucion();
                this.buscaPersonaResidencia();
            }
        }

    }

    /**
     * Valida datos basicos de la persona
     */
    public void validaPersona() {
        this.sigueProceso = true;
        String msg = null;
        // Valida Identificacion
        if (this.persona.getCodigoTipoIdentificacion().getSiglas() == 'C') {
            if(!esNumerico(this.getPersona().getIdentificacion().trim())){
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
                this.sigueProceso = false;
            }            
            //System.out.println("Valida Persona 1.1");
            if (!Validaciones.validaCedula(this.getPersona().getIdentificacion())) {
                this.sigueProceso = false;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");

            }
        }

        if (this.persona.getCodigoTipoIdentificacion().getSiglas() == 'R') {
            if (this.getPersona().getIdentificacion().length() != 13) {
                try {
                    Integer.parseInt(this.getPersona().getIdentificacion());
                    this.sigueProceso = false;
                } catch (NumberFormatException e) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
                }
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            }
        }

        if (msg != null) {
            this.sigueProceso = false;
            throw new ValidatorException(MuestraMensaje.getFaceError(msg));
        }
        /*
         // Valida Correo Electronico
         if (this.persona.getCorreoEletronico() != null) {

         if (!Validaciones.validaCorreoElectronico(this.persona.getCorreoEletronico())) {
         msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CorreoElectronicoInvalido");
         }
         this.sigueProceso = false;
         // throw new ValidatorException(MuestraMensaje.getFaceError(msg));
         }*/
    }

    /**
     * Metodo para comprobar si una cadena es numerica
     */
    public boolean esNumerico(String valor) {
        boolean ret = false;
        if (!valor.isEmpty()) {
            ret = valor.matches("^[0-9]+$");
        }
        return ret;
    }

    // -------------------------------------------------------------------------------------
    // --- PERSONA NATURAL 
    /**
     * Busca los datos de la Persona Natural
     */
    public void buscaPersonaNatural() {
        if (this.getPersona() != null) {
            PersonaNatural personaNaturalExistente = this.ejbFacadePerNat.find(this.getPersona().getCodigo());
            if (personaNaturalExistente != null) {
                this.setPersonaNaturalNueva(false);
                this.setPersonaNatural(personaNaturalExistente);
                this.esParaInstitucion = false;

                if (this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoJerarquia().getSiglas().equals("PA")) {
                    this.ubiGeoPai = this.getPersonaNatural().getCodigoUbiGeoNac();
                    this.cambiaUbiGeoPai();
                    this.cambiaUbiGeoPro();
                    this.cambiaUbiGeoCiu();
                }

                if (this.getPersonaNatural().getCodigoUbiGeoNaci() != null) {
                    if (this.getPersonaNatural().getCodigoUbiGeoNaci().getCodigoJerarquia().getSiglas().equals("PA")) {
                        this.ubiGeoPaiNaci = this.getPersonaNatural().getCodigoUbiGeoNaci();
                    }
                    this.ubiGeoPai = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getPersonaNatural().getCodigoUbiGeoNac();
                    this.cambiaUbiGeoPro();
                    this.cambiaUbiGeoCiu();
                }

                if (this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoJerarquia().getSiglas().equals("PR")) {
                    this.ubiGeoPai = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getPersonaNatural().getCodigoUbiGeoNac();
                    this.cambiaUbiGeoPro();
                    this.cambiaUbiGeoCiu();

                }

                if (this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoJerarquia().getSiglas().equals("CI")) {
                    this.ubiGeoPai = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPro();
                    this.ubiGeoCiu = this.getPersonaNatural().getCodigoUbiGeoNac();

                    this.cambiaUbiGeoCiu();
                }

                if (this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoJerarquia().getSiglas().equals("PQ")) {
                    this.ubiGeoPai = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPro();
                    this.ubiGeoCiu = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoCiu();
                    this.ubiGeoPar = this.getPersonaNatural().getCodigoUbiGeoNac();

                }
            }
        }
    }

    /**
     * Guarda la Información digitada en la Base de Datos de la Persona Natural
     */
    public void guardaPersonaNatural() {

        this.getPersonaNatural().setCodigoUbiGeoNac(ubiGeoPai);
        //pais denacionalidad
        this.getPersonaNatural().setCodigoUbiGeoNaci(ubiGeoPaiNaci);
        if (this.ubiGeoPar != null) {
            this.getPersonaNatural().setCodigoUbiGeoNac(ubiGeoPar);
        } else {
            if (this.ubiGeoCiu != null) {
                this.getPersonaNatural().setCodigoUbiGeoNac(ubiGeoCiu);
            } else {
                if (this.ubiGeoPro != null) {
                    this.getPersonaNatural().setCodigoUbiGeoNac(ubiGeoPro);
                }
            }
        }

        //System.out.println("Ubi geo " + this.getPersonaNatural().getCodigoUbiGeoNac());
        if (this.isPersonaNaturalNueva()) {
            this.getPersonaNatural().setFechaIngreso(new Date());
            this.getPersonaNatural().setFechaActualizacion(this.getPersonaNatural().getFechaIngreso());
            this.getPersonaNatural().setCodigoPersona(this.getPersona().getCodigo());
            ejbFacadePerNat.create(this.personaNatural);
        } else {
            this.getPersonaNatural().setFechaActualizacion(new Date());
            //ejbFacadePerNat.edit(personaNatural);            
            ejbFacadePerNat.actualiza(this.getPersonaNatural().getCodigoUbiGeoNac(), this.getPersonaNatural().getCodigoUbiGeoNaci(), this.getPersonaNatural().getCodigoProfesion(), this.getPersonaNatural().getCodigoInstruccion(), this.getPersonaNatural().getCodigoFuenteIngresos(), this.getPersonaNatural().getCodigoEstadoCivil(), this.getPersonaNatural().getNombres(), this.getPersonaNatural().getPrimerApellido(), this.getPersonaNatural().getSegundoApellido(), this.getPersonaNatural().getIngresos(), this.getPersonaNatural().getSexo(), this.getPersonaNatural().getFechaNacimiento(), this.getPersonaNatural().getExoneradoSri(), this.getPersonaNatural().getCargasFamiliares(), new Date(), this.getPersonaNatural().getPersona());
        }

    }

    /**
     * Valida la Persona Natural
     */
    public void validaPersonaNatural() {
        this.sigueProceso = true;

        // Valida si señalo la asginacion al grupo
        if (this.asignaGrupo) {
            if (this.grupoInstitucionIfipAgencia == null) {
                String msg = null;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneGrupoInstitucion");
                this.sigueProceso = false;
                //System.out.println("Error seleccione grupo " + msg);
                MuestraMensaje.addError(msg);
            }
        }

    }

    // --- FIN PERSONA NATURAL
    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------
    // --- PERSONA INSTITUCION 
    /**
     * Obtiene los datos de la Institucion
     */
    public void buscaPersonaInsitucion() {

        if (this.getPersona() != null) {

            PersonaInstitucion personaInstitucionExistente = this.ejbFacadePerIns.find(this.getPersona().getCodigo());
            if (personaInstitucionExistente != null) {
                this.esParaInstitucion = true;
                this.setPersonaInstitucionNueva(false);
                this.setPersonaInstitucion(personaInstitucionExistente);
                this.setRazonSocial(this.getPersonaInstitucion().getRazonSocial());
                this.setItemsPersonaInstitucionRep((List<PersonaInstitucionRep>) personaInstitucionExistente.getPersonaInstitucionRepCollection());
                if (this.getItemsPersonaInstitucionRep() == null) {
                    this.setItemsPersonaInstitucionRep(new ArrayList<PersonaInstitucionRep>());
                }
            }
        }

    }

    /**
     * Guarda los datos de la Institucion digitados en la Pantalla en la Base de
     * Datos
     */
    public void guardaPersonaInstitucion() {

        if (this.isPersonaInstitucionNueva()) {
            this.getPersonaInstitucion().setCodigoPersona(this.getPersona().getCodigo());
            this.getPersonaInstitucion().setFechaIngreso(new Date());
            this.getPersonaInstitucion().setFechaActualizacion(this.getPersonaInstitucion().getFechaIngreso());
            this.ejbFacadePerIns.create(this.personaInstitucion);

        } else {
            this.getPersonaInstitucion().setFechaActualizacion(new Date());
            //this.ejbFacadePerIns.edit(personaInstitucion);            
            this.ejbFacadePerIns.actualiza(getPersonaInstitucion().getCodigoTipoInstitucion(), getPersonaInstitucion().getRazonSocial(), getPersonaInstitucion().getNombreComercial(), getPersonaInstitucion().getObjetoSocial(), getPersonaInstitucion().getFechaConstitucion(), getPersonaInstitucion().getEsSujetoSri(), getPersonaInstitucion().getObservaciones(), new Date(), getPersonaInstitucion().getControladaOc(), getPersonaInstitucion().getCodigoPersona());
        }

        for (PersonaInstitucionRep representante : this.getItemsPersonaInstitucionRep()) {
            if (ejbFacadePerInsRep.find(representante.getPersonaInstitucionRepPK()) == null) {
                representante.setPersonaInstitucion(personaInstitucion);
                representante.getPersonaInstitucionRepPK().setCodigoPersona(this.getPersonaInstitucion().getCodigoPersona());
                ejbFacadePerInsRep.create(representante);
            } else {
                ejbFacadePerInsRep.actualiza(representante.getCodigoNivel(), representante.getCodigoCargo(), representante.getEliminado(), representante.getPersonaInstitucionRepPK().getCodigoPersona(), representante.getPersonaInstitucionRepPK().getCodigoPersonaRep());
            }

        }
    }

    /**
     * Busca y Valida si el representante de la institucion ya esta en la grilla
     */
    public void buscaRepresentanteInstitucion() {
        String msg = null;
        this.deshabilitaAgregarRepresentante = true;
        this.deshabilitaRegistrarRepresentante = true;
        this.deshabilidaNombreRepresentante = true;
        if (this.getIdentificacionRepresentante().length() > 0) {
            if (Validaciones.validaCedula(this.getIdentificacionRepresentante())) {
                this.personaRepresentanteIns = null;

                this.setPersonaRepresentanteIns(this.ejbFacadePersona.getPersona(this.getIdentificacionRepresentante()));
                if (personaRepresentanteIns != null) {
                    if (String.valueOf(this.personaRepresentanteIns.getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                        if (this.getItemsPersonaInstitucionRep() != null) {
                            for (PersonaInstitucionRep p : this.itemsPersonaInstitucionRep) {
                                if (p.getPersonaInstitucionRepPK().getCodigoPersonaRep() == personaRepresentanteIns.getCodigo()) {
                                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaYaAsiganadaRepresentante");
                                    break;
                                }
                            }
                        }
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaDebeSerNatural");
                    }
                } else {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoRegistrada");
                    this.deshabilitaRegistrarRepresentante = false;
                    this.deshabilidaNombreRepresentante = false;
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            }

            if (msg != null) {
                MuestraMensaje.addAdvertencia(msg);
                this.personaRepresentanteIns = null;
                this.deshabilitaAgregarRepresentante = true;
                this.nombreRepresentante = null;
                this.identificacionRepresentante = null;
            } else {
                this.deshabilitaAgregarRepresentante = false;
                this.nombreRepresentante = this.personaRepresentanteIns.getNombreCompleto();
            }
        }
    }

    /**
     * Agrega un Representante de la Institucion en la Grilla al Presionar el
     * Boton Agregar
     */
    public void agregaRepresentante() {

        if (this.personaRepresentanteIns != null) {

            personaInstitucionRep.setPersonaInstitucionRepPK(new PersonaInstitucionRepPK());

            if (!this.isPersonaInstitucionNueva() && !this.isPersonaNueva()) {
                personaInstitucionRep.getPersonaInstitucionRepPK().setCodigoPersona(this.getPersonaInstitucion().getCodigoPersona());
                personaInstitucionRep.setPersonaInstitucion(personaInstitucion);
            }

            personaInstitucionRep.getPersonaInstitucionRepPK().setCodigoPersonaRep(this.personaRepresentanteIns.getCodigo());
            personaInstitucionRep.setPersona(this.personaRepresentanteIns);
            personaInstitucionRep.setEliminado('N');
            this.itemsPersonaInstitucionRep.add(personaInstitucionRep);

            this.setPersonaInstitucionRep(new PersonaInstitucionRep());
            this.personaRepresentanteIns = null;
            this.deshabilitaAgregarRepresentante = true;
            this.identificacionRepresentante = null;
            this.nombreRepresentante = null;

        }
    }

    /**
     * Cuando el Representante no esta registrado en el sistema (base de datos)
     * se activa los casilleros y boton REGISTRA, este metodo guarda el
     * representante al presiona el boton indicado
     */
    public void registraRepresentante() {
        String msg;
        if (Validaciones.validaCedula(this.getIdentificacionRepresentante())) {
            if (this.getNombreRepresentante() != null) {
                try {
                    Persona per = new Persona();
                    per.setCodigoTipoIdentificacion(this.ejbFacadeTipIde.find(Long.parseLong("1")));
                    per.setCodigoTipoPersona(this.ejbFacadeTipPer.find(Long.parseLong("1")));
                    per.setFechaActualizacion(new Date());
                    per.setFechaIngreso(new Date());
                    per.setNombreCompleto(this.getNombreRepresentante());
                    per.setIdentificacion(this.getIdentificacionRepresentante());
                    this.ejbFacadePersona.create(per);
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RepresentanteRegistrado");
                    MuestraMensaje.addInformacion(msg);
                    buscaRepresentanteInstitucion();
                } catch (Exception e) {
                    msg = CapturaError.getErrorPersistencia(e);
                    MuestraMensaje.addErrorPersistencia(msg);

                }

            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    /**
     * Quita el Represalentante Selecciona de la Grilla. Validando Primero sin
     * no esta ingresado en la Base de Datos
     */
    public void quitaRepresentante() {
        boolean registrado = false;
        if (this.getPersonaInstitucionRepSel().getPersona().getCodigo() != null) {
            if (this.ejbFacadePerInsRep.find(this.getPersonaInstitucionRepSel().getPersonaInstitucionRepPK()) != null) {
                registrado = true;
            }
        }

        if (registrado) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getItemsPersonaInstitucionRep().remove(this.getPersonaInstitucionRepSel());
        }

    }

    /**
     * Valida si la Razon Social de la Institucion esta Siendo Usada por Otra.
     */
    public void validaRazonSocialIngresada() {
        if ((this.getEjbFacade().getItemsSocio(this.getPersonaInstitucion().getRazonSocial(),
                "N",
                ActivacionUsuario.getCodigoIfip()).size()) > 0) {
            if (!this.isPersonaInstitucionNueva() && this.getRazonSocial().equals(this.personaInstitucion.getRazonSocial())) {
                this.sigueProceso = true;
            } else {
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RazonSocialExistente");
                MuestraMensaje.addAdvertencia(msg);
                this.sigueProceso = false;
            }

        } else {
            this.sigueProceso = true;
        }
    }

    /**
     * Valida si la Institucion tiene Representantes
     */
    public void validaIngresoRepresentante() {
        this.sigueProceso = true;
        if (this.itemsPersonaInstitucionRep.isEmpty()) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseRepresentanteInstitucion");
            MuestraMensaje.addAdvertencia(msg);
            this.sigueProceso = false;
        }
    }
    // --- FIN PERSONA INSTITUCION 
    // -------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------
    // --- PERSONA RESIDENCIA
    /**
     * Busca la Residencia de la Persona
     */
    public void buscaPersonaResidencia() {
        if (this.getPersona() != null) {

            PersonaResidencia personaResidenciaExistente = this.ejbFacadePerRes.find(this.getPersona().getCodigo());
            if (personaResidenciaExistente != null) {

                this.setPersonaResidenciaNueva(false);
                this.setPersonaResidencia(personaResidenciaExistente);

                if (this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoJerarquia().getSiglas().equals("PA")) {
                    this.ubiGeoPaiRes = this.getPersonaResidencia().getCodigoUbiGeoRes();
                    this.cambiaUbiGeoPaiRes();
                    this.cambiaUbiGeoProRes();
                    this.cambiaUbiGeoCiuRes();
                }

                if (this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoJerarquia().getSiglas().equals("PR")) {
                    this.ubiGeoPaiRes = this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPaiRes();
                    this.ubiGeoProRes = this.getPersonaResidencia().getCodigoUbiGeoRes();
                    this.cambiaUbiGeoProRes();
                    this.cambiaUbiGeoCiuRes();
                }

                if (this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoJerarquia().getSiglas().equals("CI")) {
                    this.ubiGeoPaiRes = this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPaiRes();
                    this.ubiGeoProRes = this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoProRes();
                    this.ubiGeoCiuRes = this.getPersonaResidencia().getCodigoUbiGeoRes();
                    this.cambiaUbiGeoCiuRes();
                }

                if (this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoJerarquia().getSiglas().equals("PQ")) {
                    this.ubiGeoPaiRes = this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPaiRes();
                    this.ubiGeoProRes = this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoUbiGeoPadre().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoProRes();
                    this.ubiGeoCiuRes = this.getPersonaResidencia().getCodigoUbiGeoRes().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoCiuRes();
                    this.ubiGeoParRes = this.getPersonaResidencia().getCodigoUbiGeoRes();
                }
            }
        }

    }

    /**
     * Guarda la Residencia en la Base de Datos
     */
    public void guardaResidencia() {

        this.getPersonaResidencia().setCodigoUbiGeoRes(ubiGeoPaiRes);
        if (this.ubiGeoParRes != null) {
            this.getPersonaResidencia().setCodigoUbiGeoRes(ubiGeoParRes);
        } else {
            if (this.ubiGeoCiuRes != null) {
                this.getPersonaResidencia().setCodigoUbiGeoRes(ubiGeoCiuRes);
            } else {
                if (this.ubiGeoProRes != null) {
                    this.getPersonaResidencia().setCodigoUbiGeoRes(ubiGeoProRes);
                }
            }
        }

        if (this.isPersonaResidenciaNueva()) {
            this.getPersonaResidencia().setCodigoPersona(this.getPersona().getCodigo());
            ejbFacadePerRes.create(this.personaResidencia);
        } else {
            //ejbFacadePerRes.edit(personaResidencia);            
            ejbFacadePerRes.actualiza(personaResidencia.getCodigoUbiGeoRes(), personaResidencia.getCodigoTipoVivienda(), personaResidencia.getCodigoPeriodicidad(), personaResidencia.getCodigoSector(), personaResidencia.getBarrio(), personaResidencia.getDireccion(), personaResidencia.getReferencia(), personaResidencia.getTiempo(), personaResidencia.getCodigoPersona());
        }

    }

    // --- FIN PERSONA RESIDENCIA 
    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------
    // --- PERSONA TELEFONO 
    /**
     * BUsca y Valida si el telefono ya está en la Grilla
     */
    public void buscaTelefono() {
        boolean telefonoCorrecto = true;
        String msg = null;
        if (this.getItemsPersonaTelefono() != null) {

            if (telefonoCorrecto) {
                for (PersonaTelefono pt : this.getItemsPersonaTelefono()) {
                    if (pt.getNumero().trim().equals(this.getNumeroTelefono().trim())) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TelefonoIngresado");
                    }
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TelefonoIncorrecto");
            }
        }
        if (msg != null) {
            this.deshabilitaAgregarTelefono = true;
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.deshabilitaAgregarTelefono = false;
        }
    }

    /**
     * Guarda los Telefonos en la Base de Datos
     */
    public void guardaTelefono() {
        if (this.getItemsPersonaTelefono() != null) {
            for (PersonaTelefono pt : this.getItemsPersonaTelefono()) {
                if (this.isPersonaNueva()) {
                    pt.setCodigoPersona(persona);
                }

                if (pt.getCodigo() == null) {
                    this.ejbFacadePerTel.create(pt);
                } else {
                    this.ejbFacadePerTel.actualiza(persona, pt.getCodigoTipoTelefono(), pt.getCodigoOperadoraTelefono(), pt.getNumero(), pt.getEliminado(), pt.getRecibeNotificacion(), pt.getCodigo());
                }

            }
        }
    }

    /**
     * Agrega Telefono la Grilla al Presionar el Boton Agregar
     */
    public void agregaTelefono() {
        String msg = null;
        msg = (msg == null) ? (Validaciones.cumpleRequerimientoCampo(this.numeroTelefono.toString(), 6) ? null : ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Telefono") + " " + Validaciones.msg) : msg;
        boolean telefonoCorrecto = true;
        if (msg == null) {
            if (this.personaTelefono.getCodigoTipoTelefono().getCodigo().toString().equals("2")) {
                telefonoCorrecto = Validaciones.validaTelefonoCelular(this.getNumeroTelefono());
            }

            if (this.personaTelefono.getCodigoTipoTelefono().getCodigo().toString().equals("1")) {
                telefonoCorrecto = Validaciones.validaTelefonoConvencional(this.getNumeroTelefono());
            }

            if (!telefonoCorrecto) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TelefonoIncorrecto");
            }
        }

        if (msg == null) {
            if (!this.isPersonaNueva()) {
                this.personaTelefono.setCodigoPersona(persona);
            }
            this.personaTelefono.setNumero(this.getNumeroTelefono());
            this.personaTelefono.setEliminado('N');
            this.getItemsPersonaTelefono().add(personaTelefono);

            this.setNumeroTelefono(null);
            this.setPersonaTelefono(new PersonaTelefono());
            this.deshabilitaAgregarTelefono = true;
            this.personaTelefono.setRecibeNotificacion("");
        } else {
            MuestraMensaje.addError(msg);
            this.setNumeroTelefono(null);
            this.setPersonaTelefono(new PersonaTelefono());
            this.deshabilitaAgregarTelefono = true;
        }
    }

    /**
     * Quita el telefono de la Grilla siempre y Cuando no este registrado en la
     * base de datos
     */
    public void quitaTelefono() {
        boolean registrado = false;

        if (this.getItemsPersonaTelefonoReg() != null) {
            for (PersonaTelefono pt : this.getItemsPersonaTelefonoReg()) {
                if (pt.getNumero().equals(this.getPersonaTelefonoSel().getNumero())) {
                    registrado = true;
                    break;
                }
            }
        }
        if (registrado) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getItemsPersonaTelefono().remove(this.getPersonaTelefonoSel());
        }
    }

    // --- FIN PERSONA TELEFONO 
    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------
    // --- REFERENCIA PERSONAL
    /**
     * Busca y Valida si la Referencia Personal esta Ingresada en la Grilla
     */
    public void buscaReferenciaPersonal() {
        String msg = null;
        if (this.getItemsPersonaTelefono() != null) {
            for (ReferenciaPersonal rP : this.getItemsReferenciaPersonal()) {
                if (rP.getNombres().equals(this.getNombresRefPer())) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ReferenciaPersonalIngresada");
                }
            }
        }
        if (msg != null) {
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.deshabilitaAgregarRefPer = false;
        }
    }

    /**
     * Guarda las referencias personales
     */
    public void guardaReferenciaPersonal() {

        if (this.getItemsReferenciaPersonal() != null) {
            for (ReferenciaPersonal rP : this.getItemsReferenciaPersonal()) {
                if (this.isPersonaNueva()) {
                    rP.setCodigoPersona(persona);
                }

                if (rP.getCodigo() == null) {
                    this.ejbFacadeRefPer.create(rP);
                } else {
                    this.ejbFacadeRefPer.actualiza(rP.getDireccion(), rP.getEliminado(), rP.getNombres(), rP.getTelefono(), rP.getCodigo());
                };
            }
        }

    }

    /**
     * Agrega una Referencia Personal en la Grilla al Presionar el Boton Agregar
     */
    public void agregaReferenciaPersonal() {
        String msg = null;

        msg = (msg == null) ? (Validaciones.cumpleRequerimientoCampo(this.getNombresRefPer(), 4) ? null : ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Nombres") + " " + Validaciones.msg) : msg;
        msg = (msg == null) ? (Validaciones.cumpleRequerimientoCampo(this.getTelefonoRefPer(), 4) ? null : ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Telefono") + " " + Validaciones.msg) : msg;
        msg = (msg == null) ? (Validaciones.cumpleRequerimientoCampo(this.getDireccionRefPer(), 4) ? null : ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Direccion") + " " + Validaciones.msg) : msg;
        msg = (msg == null) ? ((Validaciones.validaTelefonoConvencional(this.getTelefonoRefPer()) || Validaciones.validaTelefonoCelular(this.getTelefonoRefPer())) ? null : ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TelefonoIncorrecto")) : msg;

        if (msg == null) {
            if (!this.isPersonaNueva()) {
                this.referenciaPersonal.setCodigoPersona(persona);
            }

            this.referenciaPersonal.setNombres(this.getNombresRefPer());
            this.referenciaPersonal.setTelefono(this.getTelefonoRefPer());
            this.referenciaPersonal.setDireccion(this.getDireccionRefPer());
            this.referenciaPersonal.setEliminado('N');
            this.getItemsReferenciaPersonal().add(referenciaPersonal);

            this.nombresRefPer = null;
            this.telefonoRefPer = null;
            this.direccionRefPer = null;
            this.setReferenciaPersonal(new ReferenciaPersonal());
            this.deshabilitaAgregarRefPer = true;
        } else {
            MuestraMensaje.addAdvertencia(msg);
        }

    }

    /**
     * Quita una Referencia Personal
     */
    public void quitaReferenciaPersonal() {
        boolean registrado = false;

        if (this.getItemsReferenciaPersonalReg() != null) {
            for (ReferenciaPersonal rP : this.getItemsReferenciaPersonalReg()) {
                if (rP.getNombres().equals(this.referenciaPersonalSel.getNombres())) {
                    registrado = true;
                    break;
                }
            }
        }

        if (registrado) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getItemsReferenciaPersonal().remove(this.referenciaPersonalSel);
        }
    }
    // --- REFERENCIA PERSONAL
    // -------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------
    // --- REFERENCIA ENTIDIDAD FINANCIERA
    /**
     * Guarda las Referencias Financieras en la Base de Datos
     */
    public void guardaReferenciaEntFin() {

        if (this.getItemsReferenciaEntFin() != null) {
            for (ReferenciaEntidadFinanciera rEF : this.getItemsReferenciaEntFin()) {
                if (this.isPersonaNueva()) {
                    rEF.setCodigoPersona(persona);
                }
                if (rEF.getCodigo() == null) {
                    this.ejbFacadeRefEntFin.create(rEF);
                } else {
                    this.ejbFacadeRefEntFin.actualiza(rEF.getCodigoEntidadFinanciera(), rEF.getCodigoTipoCuenta(), rEF.getEliminado(), rEF.getNumeroCuenta(), rEF.getObservaciones(), rEF.getCodigo());
                }

            }
        }
    }

    /**
     * Agrega una Referencia Financiera en la Grilla al Presionar el Boton
     * Agregar
     */
    public void agregaReferenciaEntFin() {
        String msg = null;

        msg = (msg == null) ? (Validaciones.cumpleRequerimientoCampo(this.getCuentaRefEntFin(), 4) ? null : ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cuenta") + " " + Validaciones.msg) : msg;
        msg = (msg == null) ? (Validaciones.cumpleRequerimientoCampo(this.getObservacionesRefEntFin(), 4) ? null : ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Obseraciones") + " " + Validaciones.msg) : msg;

        if (this.getItemsReferenciaEntFin() != null) {
            for (ReferenciaEntidadFinanciera rEF : this.getItemsReferenciaEntFin()) {
                if ((rEF.getCodigoEntidadFinanciera().getCodigo() == this.referenciaEntFin.getCodigoEntidadFinanciera().getCodigo())
                        && (rEF.getCodigoTipoCuenta().getCodigo() == this.referenciaEntFin.getCodigoTipoCuenta().getCodigo())
                        && (rEF.getNumeroCuenta().equals(this.referenciaEntFin.getNumeroCuenta()))) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EntidadFinancieraIngresada");
                    break;
                }
            }
        }

        if (msg == null) {
            if (!this.isPersonaNueva()) {
                this.referenciaEntFin.setCodigoPersona(persona);
            }

            this.referenciaEntFin.setNumeroCuenta(this.getCuentaRefEntFin());
            this.referenciaEntFin.setObservaciones(this.getObservacionesRefEntFin());
            this.referenciaEntFin.setEliminado('N');
            this.getItemsReferenciaEntFin().add(referenciaEntFin);

            this.cuentaRefEntFin = null;
            this.observacionesRefEntFin = null;
            this.setReferenciaEntFin(new ReferenciaEntidadFinanciera());
            this.deshabilitaAgregarRefEntFin = true;
        } else {
            MuestraMensaje.addAdvertencia(msg);
        }

    }

    /**
     * Quita la Referencia Financiera de la Grilla
     */
    public void quitaReferenciaEntFin() {

        boolean registrado = false;

        if (this.getItemsReferenciaEntFinReg() != null) {
            for (ReferenciaEntidadFinanciera rEF : this.getItemsReferenciaEntFinReg()) {
                if ((rEF.getCodigoEntidadFinanciera().getCodigo() == this.referenciaEntFinSel.getCodigoEntidadFinanciera().getCodigo())
                        && (rEF.getCodigoTipoCuenta().getCodigo() == this.referenciaEntFinSel.getCodigoTipoCuenta().getCodigo())
                        && (rEF.getNumeroCuenta().equals(this.referenciaEntFinSel.getNumeroCuenta()))) {
                    registrado = true;
                    break;
                }
            }
        }

        if (registrado) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getItemsReferenciaEntFin().remove(this.referenciaEntFinSel);
        }

    }
    // --- FIN REFERENCIA ENTIDIDAD FINANCIERA
    // -------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------
    // --- ACTIVIDAD ECONOMICA
    /**
     * Guarda la Actividad Economica en la Base de Datos.
     */
    public void guardaActividadEconomica() {

        if (this.getItemsPersonaActEco() != null) {
            for (PersonaActividadEconomica pAE : this.getItemsPersonaActEco()) {
                if (this.isPersonaNueva()) {
                    pAE.getPersonaActividadEconomicaPK().setCodigoPersona(persona.getCodigo());
                    pAE.setFechaIngreso(new Date());
                    pAE.setPersona(persona);
                }

                pAE.setFechaActualizacion(new Date());
                pAE.setCodigoPeriodicidad(pAE.getPeriodicidad().getCodigo());

                if (this.ejbFacadePerActEco.find(pAE.getPersonaActividadEconomicaPK()) == null) {
                    this.ejbFacadePerActEco.create(pAE);
                } else {
                    //this.ejbFacadePerActEco.edit(pAE);                    
                    ejbFacadePerActEco.actualiza(pAE.getPersonaActividadEconomicaPK().getCodigoActividadEconomica(), pAE.getCodigoPeriodicidad(), pAE.getTiempo(), new Date(), pAE.getEliminado(), pAE.getEsPrincipal(), persona.getCodigo());
                }

                if (String.valueOf(pAE.getEsPrincipal()).equals("S")) {

                    personaActEcoPrin = this.ejbFacadePerActEcoPri.find(persona.getCodigo());

                    if (personaActEcoPrin == null) {
                        personaActEcoPrin = new PersonaActEcoPri();
                        this.personaActEcoPrin.setFechaIngreso(new Date());
                        this.personaActEcoPrin.setCodigoPersona(persona.getCodigo());
                        this.personaActEcoPrin.setCodigoPersona(this.persona.getCodigo());
                        this.personaActEcoPrin.setFechaActualizacion(new Date());
                        this.personaActEcoPrin.setPersonaActividadEconomica(pAE);
                        this.personaActEcoPrin.setCodigoActividadEconomica(pAE.getActividadEconomica().getCodigo());
                        this.ejbFacadePerActEcoPri.create(personaActEcoPrin);
                    } else {
                        //this.ejbFacadePerActEcoPri.edit(personaActEcoPrin);                        
                        this.ejbFacadePerActEcoPri.actualiza(pAE.getActividadEconomica().getCodigo(), new Date(), persona.getCodigo());
                    }

                }

            }
        }

    }

    /**
     * Valida la Actividad Eonomica Principal
     */
    public void validaPrincipalActEco() {
        int principales = 0;
        String msg = null;
        this.setSigueProceso(true);
        if (this.getItemsPersonaActEco() != null) {
            for (PersonaActividadEconomica pAE : this.getItemsPersonaActEco()) {
                if (String.valueOf(pAE.getEsPrincipal()).equals("S")) {
                    principales++;
                }
            }
        }

        if (principales == 0) {
            this.setSigueProceso(false);
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FaltaActEcoPri");
        }

        if (principales > 1) {
            this.setSigueProceso(false);
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnaActEcoPri");
        }

        if (msg != null) {
            MuestraMensaje.addAdvertencia(msg);
        }

    }

    /**
     * Agrega una Actividad Economica al Presionar el Boton de Agregar.
     */
    public void agregaActividadEconomica() {
        String msg = null;

        try {

            msg = (msg == null) ? (!Validaciones.cumpleRequerimientoCampo(this.getFechaIngresoActEco()) ? ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("FechaIngreso") + " " + Validaciones.msg : null) : null;
            msg = (msg == null) ? (!Validaciones.cumpleRequerimientoCampo(this.getTiempoActEco(), 1) ? ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Tiempo") + " " + Validaciones.msg : null) : Validaciones.msg;

            if (this.personaActividadEconomica.getActividadEconomica() == null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("ActividadEconomica") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            }
            if (this.getItemsPersonaActEco() != null && msg == null) {
                for (PersonaActividadEconomica pAC : this.getItemsPersonaActEco()) {
                    if (pAC.getActividadEconomica().getCodigo() == this.personaActividadEconomica.getActividadEconomica().getCodigo()) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ActividadEonomicaExistente");
                        break;
                    }
                }
            }

            if (String.valueOf(this.personaActividadEconomica.getEsPrincipal()).equals("S")) {
                if (this.getItemsPersonaActEco() != null && msg == null) {
                    for (PersonaActividadEconomica pAC : this.getItemsPersonaActEco()) {
                        if (String.valueOf(pAC.getEsPrincipal()).equals("S")) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnaActEcoPri");
                            break;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCamposRequeridos");
        }

        /*if (msg == null) {
         ////System.out.println("Fecha Act Eco "+fechaIngresoActEco.toString()+" "+Validaciones.validaFechaMenorHoy(fechaIngresoActEco));
         if (!Validaciones.validaFechaMenorHoy(fechaIngresoActEco)) {
         msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechaActEcoMenHoy");
         }
         }*/
        if (msg == null) {
            PersonaActividadEconomicaPK perActEcoPK = new PersonaActividadEconomicaPK();
            perActEcoPK.setCodigoActividadEconomica(personaActividadEconomica.getActividadEconomica().getCodigo());
            if (!this.isPersonaNueva()) {
                this.personaActividadEconomica.setPersona(persona);
                perActEcoPK.setCodigoPersona(persona.getCodigo());
            }

            this.personaActividadEconomica.setPersonaActividadEconomicaPK(perActEcoPK);
            this.personaActividadEconomica.setFechaIngreso(this.getFechaIngresoActEco());
            this.personaActividadEconomica.setTiempo(tiempoActEco);
            this.personaActividadEconomica.setEliminado('N');
            this.getItemsPersonaActEco().add(personaActividadEconomica);

            this.cuentaRefEntFin = null;
            this.observacionesRefEntFin = null;
            this.setPersonaActividadEconomica(new PersonaActividadEconomica());

            // this.personaActividadEconomica = null;
            this.tiempoActEco = 1;
            this.fechaIngresoActEco = null;
            this.sectorActividadEconomica = null;
            this.subsectorActividadEconomica = null;

            //Se cargan las actividades economicas del trabajo
            cargaActividadEconomicaTrabajo();

        } else {
            MuestraMensaje.addAdvertencia(msg);
        }

    }

    /**
     * Quita la Actividad Ecnomica de la Grilla
     */
    public void quitaActividadEconomica() {

        boolean registrado = false;

        if (this.ejbFacadePerActEco.find(this.getPersonaActividadEconomicaSel().getPersonaActividadEconomicaPK()) != null) {
            registrado = true;
        }

        if (registrado) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getItemsPersonaActEco().remove(this.personaActividadEconomicaSel);
            //Se cargan las actividades economicas del trabajo
            cargaActividadEconomicaTrabajo();
        }

    }

    /**
     * Carga las Actividades Economicas para el Trabajo
     */
    //TODO: WILLAN JARA
    public void cargaActividadEconomicaTrabajo() {
        //System.out.println("Carga Actividad Economica");
        this.setItemsPersonaActEcoTra(new ArrayList<PersonaActividadEconomica>());
        for (PersonaActividadEconomica pae : itemsPersonaActEco) {
            //System.out.println("pae.getEliminado() " + pae.getEliminado() + " pae " + pae.getPersonaActividadEconomicaPK());
            //if (pae.getEliminado() == 'N') {
            this.itemsPersonaActEcoTra.add(pae);
            //}
        }
    }

    public void cambiaActividadEconomica() {
        this.setItemsPersonaActEcoTra(itemsPersonaActEco);
    }

    // --- FIN ACTIVIDAD ECONOMICA
    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------
    // -- REFRESCAMIENTO DE COMBOS/LISTAS 
    /**
     * Al cambiar el tipo de persona
     */
    public void cambiaTipoPersona() {

        this.setItemsTipoIdentificacion(this.ejbFacadeTipPerTipoIde.getItemsTipoIdentificacionVigente(this.getPersona().getCodigoTipoPersona().getCodigo()));
        this.persona.setIdentificacion(null);
        this.deshabilitaIdentificacion = false;
        this.esParaInstitucion = !String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N");

    }

    /**
     * Al cambiar el tipo de Identificacion
     */
    public void cambiaTipoIdentificacion() {
        this.persona.setIdentificacion(null);
        this.deshabilitaIdentificacion = false;
        this.deshabilitaFechaCaduca = true;

        this.setSiglasIdentificacion(String.valueOf(this.getPersona().getCodigoTipoIdentificacion().getSiglas()));
        if (this.getSiglasIdentificacion().equals("S")) {
//            this.persona.setIdentificacion("SEQ");
//            this.deshabilitaIdentificacion = true;
        } else if (this.getSiglasIdentificacion().equals("C")) {
            this.persona.setFechaCaducidadIdentificacion(null);
            this.deshabilitaFechaCaduca = false;
        }

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

    public void cambiaUbiGeoPaiRes() {

        this.setItemsUbiGeoProRes(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoPaiRes()));
        this.setUbiGeoProRes(null);
        this.setItemsUbiGeoCiuRes(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoProRes()));
        this.setUbiGeoCiuRes(null);
        this.setItemsUbiGeoParRes(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiuRes()));
        this.setUbiGeoParRes(null);
    }

    public void cambiaUbiGeoProRes() {
        this.setItemsUbiGeoCiuRes(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoProRes()));
        this.setUbiGeoCiuRes(null);
        this.setItemsUbiGeoParRes(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiuRes()));
        this.setUbiGeoParRes(null);
    }

    public void cambiaUbiGeoCiuRes() {
        this.setItemsUbiGeoParRes(this.getEjbFacadeUbiGeo().getItemsUbicacionGeograficaHijosVigentes(this.getUbiGeoCiuRes()));
        this.setUbiGeoParRes(null);
    }

    public void cambiaSector() {
        if (this.sectorActividadEconomica != null) {
            this.setItemsSubsectorActividadEconomica(this.ejbFacadeSubsectorActividadEconomica.getItemsCodigoSectorElminado(this.getSectorActividadEconomica().getCodigo(), 'N'));
            this.subsectorActividadEconomica = null;
            this.personaActividadEconomica.setActividadEconomica(null);
        } else {
            this.setItemsSubsectorActividadEconomica(null);
            this.subsectorActividadEconomica = null;
            this.personaActividadEconomica.setActividadEconomica(null);
        }
    }

    public void cambiaSubSector() {
        //System.out.println("subsectorActividadEconomica " + subsectorActividadEconomica);
        if (this.subsectorActividadEconomica != null) {
//            this.setItemsActividadEconomica(this.ejbFacadeActvidadEconomica.getItemsSectorSubsectorNivelEliminado(this.getSectorActividadEconomica().getCodigo(), this.getSubsectorActividadEconomica().getCodigo(), (long) 3, 'N'));
            this.personaActividadEconomica.setActividadEconomica(null);
        } else {
            this.setItemsActividadEconomica(null);
            this.personaActividadEconomica.setActividadEconomica(null);
        }

        //System.out.println("itemsActividadEconomica " + itemsActividadEconomica);
    }

    public void cambiaAsignacionGrupo() {
        //this.grupoInstitucionAgeDet = new GrupoInstitucionAgeDet();
        //this.grupoInstitucionAgeDet.setValorAporte(new BigDecimal("0.00"));
        if (this.isAsignaGrupo()) {
            this.deshabilitaAsignacionGrupo = false;
        } else {
            this.deshabilitaAsignacionGrupo = true;
        }

    }
    // ---------------FIN DE REFRESCAMIENTO DE BOTONES --------------------------

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<TipoPersona> getItemsTipoPersona() {
        return this.ejbFacadeTipPer.getItemsTipoPersonaVigentes();
    }

    public List<EstadoSocio> getItemsEstadoSocio() {
        return this.ejbFacadeEstadoSocio.getItemsEstadoSocioEliminado('N');
    }

    public List<ConocimientoIfip> getItemsConocimientoIfip() {
        return this.ejbFacadeConocimientoIfip.getItemsConocimientoIfipEliminado('N');
    }

    public List<Profesion> getItemsProfesion() {
        return this.ejbFacadeProfesion.getItemsProfesionEliminado('N');
    }

    public List<Instruccion> getItemsInstruccion() {
        return this.ejbFacadeInstruccion.getItemsInstruccionEliminado('N');
    }

    public List<FuenteIngresos> getItemsFuenteIngresos() {
        return this.ejbFacadeFuenteIngresos.getItemsFuenteIngresosEliminado('N');
    }

    public List<EstadoCivil> getItemsEstadoCivil() {
        return this.ejbFacadeEstadoCivil.getItemsEstadoCivilEliminado('N');
    }

    public List<TipoVivienda> getItemsTipoVivienda() {
        return this.ejbFacadeTipoVivienda.getItemsTipoViviendaEliminado('N');
    }

    public List<Periodicidad> getItemsPeriodicidad() {
        return this.ejbFacadePeriocidad.getItemsEsParaSolicitudSocioEliminado('S', 'N');
    }

    public List<Sector> getItemsSector() {
        return this.ejbFacadeSector.getItemsSectorEliminado('N');
    }

    public List<TipoInstitucion> getItemsTipoInstitucion() {
        return this.ejbFacadeTipoInstitucion.getItemsTipoInstitucionEliminado('N');
    }

    public List<Nivel> getItemsNivel() {
        return this.ejbFacadeNivel.getItemsNivelEliminado('N');
    }

    public List<Cargo> getItemsCargo() {
        return this.ejbFacadeCargo.getItemsCargoEliminado('N');
    }

    public List<OperadoraTelefono> getItemsOperadoraTelefono() {
        return this.ejbFacadeOperadoraTelefono.getItemsOperadoraTelefonoEliminado('N');
    }

    public List<TipoTelefono> getItemsTipoTelefono() {
        return this.ejbFacadeTipoTelefono.getItemsTipoTelefonoEliminado('N');
    }

    public List<EntidadFinanciera> getItemsEntidadFinanciera() {
        return this.ejbFacadeEntidadFinanciera.getItemsEntidadFinancieraEliminado('N');
    }

    public List<TipoCuentaEntidadFinanciera> getItemsTipoCuentaEntFin() {
        return this.ejbFacadeTipoCuentaEntFin.getItemsTipoCuentaEntFinEliminado('N');
    }

    public List<Empresa> getItemsEmpresa() {
        return this.ejbFacadeEmpresa.getItemsEmpresaEliminado('N');
    }

    public List<TipoRelacion> getItemsTipoRelacion() {
        return this.ejbFacadeTipoRelacion.getItemsEsParaSolicitudSocioEliminado('S', 'N');
    }

    public List<TipoRelacion> getItemsTipoRelacionParent() {
        return this.ejbFacadeTipoRelacion.getItemsEsParaFirmantesEliminado('S', 'N');
    }

//    public void setItemsTipoRelacionParent(List<TipoRelacion> itemsTipoRelacionParent) {
//        this.itemsTipoRelacionParent = itemsTipoRelacionParent;
//    }
    /*public List<ActividadEconomica> getItemsActEcoMaxiNiv() {
     return this.ejbFacadeActvidadEconomica.getItemsActEcoMaxiNivEli('N');
     }*/
    // -- FIN LISTAS DE COMBOS 
    // ---------------------------------------------------------------------------
    // -- FIN DE LOGICA DE NEGOCIO
    // --------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    // LLAMADA A PROCEDIMIENTOS DE LA BASE
    private boolean obtieneSecuenciasSocio() {
        try {

            //System.out.println("Obteniendo las secuencias del socio.");
            llamaSP = new LlamaSP();
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_socios.pkg_socio.p_obtiene_secuencias");
            llamaSP.setNumeroParametros(4);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_archivo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            if (this.llamaSP.isEjecucionCorrecta()) {

                // colocando las secuencias del socio
                this.socio.getSocioPK().setCodigo(Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                this.socio.setCodigoArchivo(Long.parseLong(llamaSP.getListResultado().get(1).toString()));

                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio                
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);

            } else {
                //
                llamaSP.rollback();
                context.execute("procesandoDlg.hide()");
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

            return llamaSP.isEjecucionCorrecta();

        } catch (Exception ex) {

            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"socioController", "obtieneSecuenciasSocio", CapturaError.getErrorException(ex)});

            return false;
        }
    }

    // *******************************************************************************************
    // --------------------------------------------------------------------------    
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    /**
     * @return the ejbFacade
     */
    public SocioFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(SocioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
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
     * @return the nombresCompletos
     */
    public String getNombresCompletos() {
        return nombresCompletos;
    }

    /**
     * @param nombresCompletos the nombresCompletos to set
     */
    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the tituloPersonaTab
     */
    public String getTituloPersonaTab() {
        return tituloPersonaTab;
    }

    /**
     * @param tituloPersonaTab the tituloPersonaTab to set
     */
    public void setTituloPersonaTab(String tituloPersonaTab) {
        this.tituloPersonaTab = tituloPersonaTab;
    }

    /**
     * @return the personaResidencia
     */
    public PersonaResidencia getPersonaResidencia() {
        return personaResidencia;
    }

    /**
     * @param personaResidencia the personaResidencia to set
     */
    public void setPersonaResidencia(PersonaResidencia personaResidencia) {
        this.personaResidencia = personaResidencia;
    }

    /**
     * @return the personaTelefono
     */
    public PersonaTelefono getPersonaTelefono() {
        return personaTelefono;
    }

    /**
     * @param personaTelefono the personaTelefono to set
     */
    public void setPersonaTelefono(PersonaTelefono personaTelefono) {
        this.personaTelefono = personaTelefono;
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
     * @return the personaActEcoPrin
     */
    public PersonaActEcoPri getPersonaActEcoPrin() {
        return personaActEcoPrin;
    }

    /**
     * @param personaActEcoPrin the personaActEcoPrin to set
     */
    public void setPersonaActEcoPrin(PersonaActEcoPri personaActEcoPrin) {
        this.personaActEcoPrin = personaActEcoPrin;
    }

    /**
     * @return the referenciaPersonal
     */
    public ReferenciaPersonal getReferenciaPersonal() {
        return referenciaPersonal;
    }

    /**
     * @param referenciaPersonal the referenciaPersonal to set
     */
    public void setReferenciaPersonal(ReferenciaPersonal referenciaPersonal) {
        this.referenciaPersonal = referenciaPersonal;
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
     * @return the verPanelNatural
     */
    public boolean isVerPanelNatural() {
        return verPanelNatural;
    }

    /**
     * @param verPanelNatural the verPanelNatural to set
     */
    public void setVerPanelNatural(boolean verPanelNatural) {
        this.verPanelNatural = verPanelNatural;
    }

    /**
     * @return the verPanelInstitucion
     */
    public boolean isVerPanelInstitucion() {
        return verPanelInstitucion;
    }

    /**
     * @param verPanelInstitucion the verPanelInstitucion to set
     */
    public void setVerPanelInstitucion(boolean verPanelInstitucion) {
        this.verPanelInstitucion = verPanelInstitucion;
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
     * @return the itemsTipoIdentificacion
     */
    public List<TipoIdentificacion> getItemsTipoIdentificacion() {
        return itemsTipoIdentificacion;
    }

    /**
     * @param itemsTipoIdentificacion the itemsTipoIdentificacion to set
     */
    public void setItemsTipoIdentificacion(List<TipoIdentificacion> itemsTipoIdentificacion) {
        this.itemsTipoIdentificacion = itemsTipoIdentificacion;
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
     * @return the ubiGeoPaiRes
     */
    public UbicacionGeografica getUbiGeoPaiRes() {
        return ubiGeoPaiRes;
    }

    /**
     * @param ubiGeoPaiRes the ubiGeoPaiRes to set
     */
    public void setUbiGeoPaiRes(UbicacionGeografica ubiGeoPaiRes) {
        this.ubiGeoPaiRes = ubiGeoPaiRes;
    }

    /**
     * @return the ubiGeoProRes
     */
    public UbicacionGeografica getUbiGeoProRes() {
        return ubiGeoProRes;
    }

    /**
     * @param ubiGeoProRes the ubiGeoProRes to set
     */
    public void setUbiGeoProRes(UbicacionGeografica ubiGeoProRes) {
        this.ubiGeoProRes = ubiGeoProRes;
    }

    /**
     * @return the ubiGeoCiuRes
     */
    public UbicacionGeografica getUbiGeoCiuRes() {
        return ubiGeoCiuRes;
    }

    /**
     * @param ubiGeoCiuRes the ubiGeoCiuRes to set
     */
    public void setUbiGeoCiuRes(UbicacionGeografica ubiGeoCiuRes) {
        this.ubiGeoCiuRes = ubiGeoCiuRes;
    }

    /**
     * @return the ubiGeoParRes
     */
    public UbicacionGeografica getUbiGeoParRes() {
        return ubiGeoParRes;
    }

    /**
     * @param ubiGeoParRes the ubiGeoParRes to set
     */
    public void setUbiGeoParRes(UbicacionGeografica ubiGeoParRes) {
        this.ubiGeoParRes = ubiGeoParRes;
    }

    /**
     * @return the itemsUbiGeoPaiRes
     */
    public List<UbicacionGeografica> getItemsUbiGeoPaiRes() {
        return itemsUbiGeoPaiRes;
    }

    /**
     * @param itemsUbiGeoPaiRes the itemsUbiGeoPaiRes to set
     */
    public void setItemsUbiGeoPaiRes(List<UbicacionGeografica> itemsUbiGeoPaiRes) {
        this.itemsUbiGeoPaiRes = itemsUbiGeoPaiRes;
    }

    /**
     * @return the itemsUbiGeoProRes
     */
    public List<UbicacionGeografica> getItemsUbiGeoProRes() {
        return itemsUbiGeoProRes;
    }

    /**
     * @param itemsUbiGeoProRes the itemsUbiGeoProRes to set
     */
    public void setItemsUbiGeoProRes(List<UbicacionGeografica> itemsUbiGeoProRes) {
        this.itemsUbiGeoProRes = itemsUbiGeoProRes;
    }

    /**
     * @return the itemsUbiGeoCiuRes
     */
    public List<UbicacionGeografica> getItemsUbiGeoCiuRes() {
        return itemsUbiGeoCiuRes;
    }

    /**
     * @param itemsUbiGeoCiuRes the itemsUbiGeoCiuRes to set
     */
    public void setItemsUbiGeoCiuRes(List<UbicacionGeografica> itemsUbiGeoCiuRes) {
        this.itemsUbiGeoCiuRes = itemsUbiGeoCiuRes;
    }

    /**
     * @return the itemsUbiGeoParRes
     */
    public List<UbicacionGeografica> getItemsUbiGeoParRes() {
        return itemsUbiGeoParRes;
    }

    /**
     * @param itemsUbiGeoParRes the itemsUbiGeoParRes to set
     */
    public void setItemsUbiGeoParRes(List<UbicacionGeografica> itemsUbiGeoParRes) {
        this.itemsUbiGeoParRes = itemsUbiGeoParRes;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the personaNatural
     */
    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    /**
     * @param personaNatural the personaNatural to set
     */
    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    /**
     * @return the personaInstitucion
     */
    public PersonaInstitucion getPersonaInstitucion() {
        return personaInstitucion;
    }

    /**
     * @param personaInstitucion the personaInstitucion to set
     */
    public void setPersonaInstitucion(PersonaInstitucion personaInstitucion) {
        this.personaInstitucion = personaInstitucion;
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
     * @return the socioNuevo
     */
    public boolean isSocioNuevo() {
        return socioNuevo;
    }

    /**
     * @param socioNuevo the socioNuevo to set
     */
    public void setSocioNuevo(boolean socioNuevo) {
        this.socioNuevo = socioNuevo;
    }

    /**
     * @return the personaNaturalNueva
     */
    public boolean isPersonaNaturalNueva() {
        return personaNaturalNueva;
    }

    /**
     * @param personaNaturalNueva the personaNaturalNueva to set
     */
    public void setPersonaNaturalNueva(boolean personaNaturalNueva) {
        this.personaNaturalNueva = personaNaturalNueva;
    }

    /**
     * @return the personaInstitucionNueva
     */
    public boolean isPersonaInstitucionNueva() {
        return personaInstitucionNueva;
    }

    /**
     * @param personaInstitucionNueva the personaInstitucionNueva to set
     */
    public void setPersonaInstitucionNueva(boolean personaInstitucionNueva) {
        this.personaInstitucionNueva = personaInstitucionNueva;
    }

    /**
     * @return the personaResidenciaNueva
     */
    public boolean isPersonaResidenciaNueva() {
        return personaResidenciaNueva;
    }

    /**
     * @param personaResidenciaNueva the personaResidenciaNueva to set
     */
    public void setPersonaResidenciaNueva(boolean personaResidenciaNueva) {
        this.personaResidenciaNueva = personaResidenciaNueva;
    }

    /**
     * @return the personaNueva
     */
    public boolean isPersonaNueva() {
        return personaNueva;
    }

    /**
     * @param personaNueva the personaNueva to set
     */
    public void setPersonaNueva(boolean personaNueva) {
        this.personaNueva = personaNueva;
    }

    /**
     * @return the siglasIdentificacion
     */
    public String getSiglasIdentificacion() {
        return siglasIdentificacion;
    }

    /**
     * @param siglasIdentificacion the siglasIdentificacion to set
     */
    public void setSiglasIdentificacion(String siglasIdentificacion) {
        this.siglasIdentificacion = siglasIdentificacion;
    }

    /**
     * @return the verPanelDatosBasicosSocio
     */
    public boolean isVerPanelDatosBasicosSocio() {
        return verPanelDatosBasicosSocio;
    }

    /**
     * @param verPanelDatosBasicosSocio the verPanelDatosBasicosSocio to set
     */
    public void setVerPanelDatosBasicosSocio(boolean verPanelDatosBasicosSocio) {
        this.verPanelDatosBasicosSocio = verPanelDatosBasicosSocio;
    }

    /**
     * @return the personaRepresentanteIns
     */
    public Persona getPersonaRepresentanteIns() {
        return personaRepresentanteIns;
    }

    /**
     * @param personaRepresentanteIns the personaRepresentanteIns to set
     */
    public void setPersonaRepresentanteIns(Persona personaRepresentanteIns) {
        this.personaRepresentanteIns = personaRepresentanteIns;
    }

    /**
     * @return the itemsPersonaInstitucionRep
     */
    public List<PersonaInstitucionRep> getItemsPersonaInstitucionRep() {
        return itemsPersonaInstitucionRep;
    }

    /**
     * @param itemsPersonaInstitucionRep the itemsPersonaInstitucionRep to set
     */
    public void setItemsPersonaInstitucionRep(List<PersonaInstitucionRep> itemsPersonaInstitucionRep) {
        this.itemsPersonaInstitucionRep = itemsPersonaInstitucionRep;
    }

    /**
     * @return the itemsPersonaInstitucionRepReg
     */
    public List<PersonaInstitucionRep> getItemsPersonaInstitucionRepReg() {
        return itemsPersonaInstitucionRepReg;
    }

    /**
     * @param itemsPersonaInstitucionRepReg the itemsPersonaInstitucionRepReg to
     * set
     */
    public void setItemsPersonaInstitucionRepReg(List<PersonaInstitucionRep> itemsPersonaInstitucionRepReg) {
        this.itemsPersonaInstitucionRepReg = itemsPersonaInstitucionRepReg;
    }

    /**
     * @return the itemsPersonaTelefono
     */
    public List<PersonaTelefono> getItemsPersonaTelefono() {
        return itemsPersonaTelefono;
    }

    /**
     * @param itemsPersonaTelefono the itemsPersonaTelefono to set
     */
    public void setItemsPersonaTelefono(List<PersonaTelefono> itemsPersonaTelefono) {
        this.itemsPersonaTelefono = itemsPersonaTelefono;
    }

    /**
     * @return the itemsPersonaActdEco
     */
    public List<PersonaActividadEconomica> getItemsPersonaActdEco() {
        return itemsPersonaActdEco;
    }

    /**
     * @param itemsPersonaActdEco the itemsPersonaActdEco to set
     */
    public void setItemsPersonaActdEco(List<PersonaActividadEconomica> itemsPersonaActdEco) {
        this.itemsPersonaActdEco = itemsPersonaActdEco;
    }

    /**
     * @return the itemsReferenciaPersonal
     */
    public List<ReferenciaPersonal> getItemsReferenciaPersonal() {
        return itemsReferenciaPersonal;
    }

    /**
     * @param itemsReferenciaPersonal the itemsReferenciaPersonal to set
     */
    public void setItemsReferenciaPersonal(List<ReferenciaPersonal> itemsReferenciaPersonal) {
        this.itemsReferenciaPersonal = itemsReferenciaPersonal;
    }

    /**
     * @return the itemsReferenciaEntFin
     */
    public List<ReferenciaEntidadFinanciera> getItemsReferenciaEntFin() {
        return itemsReferenciaEntFin;
    }

    /**
     * @param itemsReferenciaEntFin the itemsReferenciaEntFin to set
     */
    public void setItemsReferenciaEntFin(List<ReferenciaEntidadFinanciera> itemsReferenciaEntFin) {
        this.itemsReferenciaEntFin = itemsReferenciaEntFin;
    }

    /**
     * @return the personaTelefonoSel
     */
    public PersonaTelefono getPersonaTelefonoSel() {
        return personaTelefonoSel;
    }

    /**
     * @param personaTelefonoSel the personaTelefonoSel to set
     */
    public void setPersonaTelefonoSel(PersonaTelefono personaTelefonoSel) {
        this.personaTelefonoSel = personaTelefonoSel;
    }

    /**
     * @return the itemsPersonaTelefonoReg
     */
    public List<PersonaTelefono> getItemsPersonaTelefonoReg() {
        return itemsPersonaTelefonoReg;
    }

    /**
     * @param itemsPersonaTelefonoReg the itemsPersonaTelefonoReg to set
     */
    public void setItemsPersonaTelefonoReg(List<PersonaTelefono> itemsPersonaTelefonoReg) {
        this.itemsPersonaTelefonoReg = itemsPersonaTelefonoReg;
    }

    /**
     * @return the numeroTelefono
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * @param numeroTelefono the numeroTelefono to set
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
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
     * @return the itemsReferenciaPersonalReg
     */
    public List<ReferenciaPersonal> getItemsReferenciaPersonalReg() {
        return itemsReferenciaPersonalReg;
    }

    /**
     * @param itemsReferenciaPersonalReg the itemsReferenciaPersonalReg to set
     */
    public void setItemsReferenciaPersonalReg(List<ReferenciaPersonal> itemsReferenciaPersonalReg) {
        this.itemsReferenciaPersonalReg = itemsReferenciaPersonalReg;
    }

    /**
     * @return the buscoPersona
     */
    public boolean isBuscoPersona() {
        return buscoPersona;
    }

    /**
     * @param buscoPersona the buscoPersona to set
     */
    public void setBuscoPersona(boolean buscoPersona) {
        this.buscoPersona = buscoPersona;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the identificacionRepresentante
     */
    public String getIdentificacionRepresentante() {
        return identificacionRepresentante;
    }

    /**
     * @param identificacionRepresentante the identificacionRepresentante to set
     */
    public void setIdentificacionRepresentante(String identificacionRepresentante) {
        this.identificacionRepresentante = identificacionRepresentante;
    }

    /**
     * @return the nombresRefPer
     */
    public String getNombresRefPer() {
        return nombresRefPer;
    }

    /**
     * @param nombresRefPer the nombresRefPer to set
     */
    public void setNombresRefPer(String nombresRefPer) {
        this.nombresRefPer = nombresRefPer;
    }

    /**
     * @return the direccionRefPer
     */
    public String getDireccionRefPer() {
        return direccionRefPer;
    }

    /**
     * @param direccionRefPer the direccionRefPer to set
     */
    public void setDireccionRefPer(String direccionRefPer) {
        this.direccionRefPer = direccionRefPer;
    }

    /**
     * @return the telefonoRefPer
     */
    public String getTelefonoRefPer() {
        return telefonoRefPer;
    }

    /**
     * @param telefonoRefPer the telefonoRefPer to set
     */
    public void setTelefonoRefPer(String telefonoRefPer) {
        this.telefonoRefPer = telefonoRefPer;
    }

    /**
     * @return the nombreRepresentante
     */
    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    /**
     * @param nombreRepresentante the nombreRepresentante to set
     */
    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    /**
     * @return the personaInstitucionRep
     */
    public PersonaInstitucionRep getPersonaInstitucionRep() {
        return personaInstitucionRep;
    }

    /**
     * @param personaInstitucionRep the personaInstitucionRep to set
     */
    public void setPersonaInstitucionRep(PersonaInstitucionRep personaInstitucionRep) {
        this.personaInstitucionRep = personaInstitucionRep;
    }

    /**
     * @return the personaInstitucionRepSel
     */
    public PersonaInstitucionRep getPersonaInstitucionRepSel() {
        return personaInstitucionRepSel;
    }

    /**
     * @param personaInstitucionRepSel the personaInstitucionRepSel to set
     */
    public void setPersonaInstitucionRepSel(PersonaInstitucionRep personaInstitucionRepSel) {
        this.personaInstitucionRepSel = personaInstitucionRepSel;
    }

    /**
     * @return the referenciaPersonalSel
     */
    public ReferenciaPersonal getReferenciaPersonalSel() {
        return referenciaPersonalSel;
    }

    /**
     * @param referenciaPersonalSel the referenciaPersonalSel to set
     */
    public void setReferenciaPersonalSel(ReferenciaPersonal referenciaPersonalSel) {
        this.referenciaPersonalSel = referenciaPersonalSel;
    }

    /**
     * @return the cuentaRefEntFin
     */
    public String getCuentaRefEntFin() {
        return cuentaRefEntFin;
    }

    /**
     * @param cuentaRefEntFin the cuentaRefEntFin to set
     */
    public void setCuentaRefEntFin(String cuentaRefEntFin) {
        this.cuentaRefEntFin = cuentaRefEntFin;
    }

    /**
     * @return the observacionesRefEntFin
     */
    public String getObservacionesRefEntFin() {
        return observacionesRefEntFin;
    }

    /**
     * @param observacionesRefEntFin the observacionesRefEntFin to set
     */
    public void setObservacionesRefEntFin(String observacionesRefEntFin) {
        this.observacionesRefEntFin = observacionesRefEntFin;
    }

    /**
     * @return the referenciaEntFin
     */
    public ReferenciaEntidadFinanciera getReferenciaEntFin() {
        return referenciaEntFin;
    }

    /**
     * @param referenciaEntFin the referenciaEntFin to set
     */
    public void setReferenciaEntFin(ReferenciaEntidadFinanciera referenciaEntFin) {
        this.referenciaEntFin = referenciaEntFin;
    }

    /**
     * @return the referenciaEntFinSel
     */
    public ReferenciaEntidadFinanciera getReferenciaEntFinSel() {
        return referenciaEntFinSel;
    }

    /**
     * @param referenciaEntFinSel the referenciaEntFinSel to set
     */
    public void setReferenciaEntFinSel(ReferenciaEntidadFinanciera referenciaEntFinSel) {
        this.referenciaEntFinSel = referenciaEntFinSel;
    }

    /**
     * @return the deshabilitaAgregarRepresentante
     */
    public boolean isDeshabilitaAgregarRepresentante() {
        return deshabilitaAgregarRepresentante;
    }

    /**
     * @param deshabilitaAgregarRepresentante the
     * deshabilitaAgregarRepresentante to set
     */
    public void setDeshabilitaAgregarRepresentante(boolean deshabilitaAgregarRepresentante) {
        this.deshabilitaAgregarRepresentante = deshabilitaAgregarRepresentante;
    }

    /**
     * @return the deshabilitaRegistrarRepresentante
     */
    public boolean isDeshabilitaRegistrarRepresentante() {
        return deshabilitaRegistrarRepresentante;
    }

    /**
     * @param deshabilitaRegistrarRepresentante the
     * deshabilitaRegistrarRepresentante to set
     */
    public void setDeshabilitaRegistrarRepresentante(boolean deshabilitaRegistrarRepresentante) {
        this.deshabilitaRegistrarRepresentante = deshabilitaRegistrarRepresentante;
    }

    /**
     * @return the deshabilidaNombreRepresentante
     */
    public boolean isDeshabilidaNombreRepresentante() {
        return deshabilidaNombreRepresentante;
    }

    /**
     * @param deshabilidaNombreRepresentante the deshabilidaNombreRepresentante
     * to set
     */
    public void setDeshabilidaNombreRepresentante(boolean deshabilidaNombreRepresentante) {
        this.deshabilidaNombreRepresentante = deshabilidaNombreRepresentante;
    }

    /**
     * @return the deshabilitaAgregarTelefono
     */
    public boolean isDeshabilitaAgregarTelefono() {
        return deshabilitaAgregarTelefono;
    }

    /**
     * @param deshabilitaAgregarTelefono the deshabilitaAgregarTelefono to set
     */
    public void setDeshabilitaAgregarTelefono(boolean deshabilitaAgregarTelefono) {
        this.deshabilitaAgregarTelefono = deshabilitaAgregarTelefono;
    }

    /**
     * @return the deshabilitaAgregarRefPer
     */
    public boolean isDeshabilitaAgregarRefPer() {
        return deshabilitaAgregarRefPer;
    }

    /**
     * @param deshabilitaAgregarRefPer the deshabilitaAgregarRefPer to set
     */
    public void setDeshabilitaAgregarRefPer(boolean deshabilitaAgregarRefPer) {
        this.deshabilitaAgregarRefPer = deshabilitaAgregarRefPer;
    }

    /**
     * @return the deshabilitaAgregarRefEntFin
     */
    public boolean isDeshabilitaAgregarRefEntFin() {
        return deshabilitaAgregarRefEntFin;
    }

    /**
     * @param deshabilitaAgregarRefEntFin the deshabilitaAgregarRefEntFin to set
     */
    public void setDeshabilitaAgregarRefEntFin(boolean deshabilitaAgregarRefEntFin) {
        this.deshabilitaAgregarRefEntFin = deshabilitaAgregarRefEntFin;
    }

    /**
     * @return the deshabilitaEstadoSocio
     */
    public boolean isDeshabilitaEstadoSocio() {
        return deshabilitaEstadoSocio;
    }

    /**
     * @param deshabilitaEstadoSocio the deshabilitaEstadoSocio to set
     */
    public void setDeshabilitaEstadoSocio(boolean deshabilitaEstadoSocio) {
        this.deshabilitaEstadoSocio = deshabilitaEstadoSocio;
    }

    /**
     * @return the deshabilitaIdentificacion
     */
    public boolean isDeshabilitaIdentificacion() {
        return deshabilitaIdentificacion;
    }

    /**
     * @param deshabilitaIdentificacion the deshabilitaIdentificacion to set
     */
    public void setDeshabilitaIdentificacion(boolean deshabilitaIdentificacion) {
        this.deshabilitaIdentificacion = deshabilitaIdentificacion;
    }

    /**
     * @return the itemsReferenciaEntFinReg
     */
    public List<ReferenciaEntidadFinanciera> getItemsReferenciaEntFinReg() {
        return itemsReferenciaEntFinReg;
    }

    /**
     * @param itemsReferenciaEntFinReg the itemsReferenciaEntFinReg to set
     */
    public void setItemsReferenciaEntFinReg(List<ReferenciaEntidadFinanciera> itemsReferenciaEntFinReg) {
        this.itemsReferenciaEntFinReg = itemsReferenciaEntFinReg;
    }

    /**
     * @return the tiempoActEco
     */
    public int getTiempoActEco() {
        return tiempoActEco;
    }

    /**
     * @param tiempoActEco the tiempoActEco to set
     */
    public void setTiempoActEco(int tiempoActEco) {
        this.tiempoActEco = tiempoActEco;
    }

    /**
     * @return the fechaIngresoActEco
     */
    public Date getFechaIngresoActEco() {
        return fechaIngresoActEco;
    }

    /**
     * @param fechaIngresoActEco the fechaIngresoActEco to set
     */
    public void setFechaIngresoActEco(Date fechaIngresoActEco) {
        this.fechaIngresoActEco = fechaIngresoActEco;
    }

    /**
     * @return the personaActividadEconomicaSel
     */
    public PersonaActividadEconomica getPersonaActividadEconomicaSel() {
        return personaActividadEconomicaSel;
    }

    /**
     * @param personaActividadEconomicaSel the personaActividadEconomicaSel to
     * set
     */
    public void setPersonaActividadEconomicaSel(PersonaActividadEconomica personaActividadEconomicaSel) {
        this.personaActividadEconomicaSel = personaActividadEconomicaSel;
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
     * @return the itemsPersonaActEcoReg
     */
    public List<PersonaActividadEconomica> getItemsPersonaActEcoReg() {
        return itemsPersonaActEcoReg;
    }

    /**
     * @param itemsPersonaActEcoReg the itemsPersonaActEcoReg to set
     */
    public void setItemsPersonaActEcoReg(List<PersonaActividadEconomica> itemsPersonaActEcoReg) {
        this.itemsPersonaActEcoReg = itemsPersonaActEcoReg;
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
     * @return the editaSocio
     */
    public boolean isEditaSocio() {
        return editaSocio;
    }

    /**
     * @param editaSocio the editaSocio to set
     */
    public void setEditaSocio(boolean editaSocio) {
        this.editaSocio = editaSocio;
    }

    /**
     * @return the esParaInstitucion
     */
    public boolean isEsParaInstitucion() {
        return esParaInstitucion;
    }

    /**
     * @param esParaInstitucion the esParaInstitucion to set
     */
    public void setEsParaInstitucion(boolean esParaInstitucion) {
        this.esParaInstitucion = esParaInstitucion;
    }

    /**
     * @return the reportePDF
     */
    public StreamedContent getReportePDF() {
        return reportePDF;
    }

    /**
     * @param reportePDF the reportePDF to set
     */
    public void setReportePDF(StreamedContent reportePDF) {
        this.reportePDF = reportePDF;
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
     * @return the personaSel
     */
    public Persona getPersonaSel() {
        return personaSel;
    }

    /**
     * @param personaSel the personaSel to set
     */
    public void setPersonaSel(Persona personaSel) {
        this.personaSel = personaSel;
    }

    /**
     * @return the itemsPersona
     */
    public List<Persona> getItemsPersona() {
        return itemsPersona;
    }

    /**
     * @param itemsPersona the itemsPersona to set
     */
    public void setItemsPersona(List<Persona> itemsPersona) {
        this.itemsPersona = itemsPersona;
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
     * @return the grupoInstitucionAgeDet
     */
    public GrupoInstitucionAgeDet getGrupoInstitucionAgeDet() {
        return grupoInstitucionAgeDet;
    }

    /**
     * @param grupoInstitucionAgeDet the grupoInstitucionAgeDet to set
     */
    public void setGrupoInstitucionAgeDet(GrupoInstitucionAgeDet grupoInstitucionAgeDet) {
        this.grupoInstitucionAgeDet = grupoInstitucionAgeDet;
    }

    /**
     * @return the grupoInstitucionIfipAgencia
     */
    public GrupoInstitucionIfipAgencia getGrupoInstitucionIfipAgencia() {
        return grupoInstitucionIfipAgencia;
    }

    /**
     * @param grupoInstitucionIfipAgencia the grupoInstitucionIfipAgencia to set
     */
    public void setGrupoInstitucionIfipAgencia(GrupoInstitucionIfipAgencia grupoInstitucionIfipAgencia) {
        this.grupoInstitucionIfipAgencia = grupoInstitucionIfipAgencia;
    }

    /**
     * @return the itemsGrupoInstitucionIfipAgencia
     */
    public List<GrupoInstitucionIfipAgencia> getItemsGrupoInstitucionIfipAgencia() {
        return itemsGrupoInstitucionIfipAgencia;
    }

    /**
     * @param itemsGrupoInstitucionIfipAgencia the
     * itemsGrupoInstitucionIfipAgencia to set
     */
    public void setItemsGrupoInstitucionIfipAgencia(List<GrupoInstitucionIfipAgencia> itemsGrupoInstitucionIfipAgencia) {
        this.itemsGrupoInstitucionIfipAgencia = itemsGrupoInstitucionIfipAgencia;
    }

    /**
     * @return the asignaGrupo
     */
    public boolean isAsignaGrupo() {
        return asignaGrupo;
    }

    /**
     * @param asignaGrupo the asignaGrupo to set
     */
    public void setAsignaGrupo(boolean asignaGrupo) {
        this.asignaGrupo = asignaGrupo;
    }

    /**
     * @return the deshabilitaAsignacionGrupo
     */
    public boolean isDeshabilitaAsignacionGrupo() {
        return deshabilitaAsignacionGrupo;
    }

    /**
     * @param deshabilitaAsignacionGrupo the deshabilitaAsignacionGrupo to set
     */
    public void setDeshabilitaAsignacionGrupo(boolean deshabilitaAsignacionGrupo) {
        this.deshabilitaAsignacionGrupo = deshabilitaAsignacionGrupo;
    }

    /**
     * @return the valorAporte
     */
    public BigDecimal getValorAporte() {
        return valorAporte;
    }

    /**
     * @param valorAporte the valorAporte to set
     */
    public void setValorAporte(BigDecimal valorAporte) {
        this.valorAporte = valorAporte;
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
     * @return the itemsPersonaActEcoTra
     */
    public List<PersonaActividadEconomica> getItemsPersonaActEcoTra() {
        return itemsPersonaActEcoTra;
    }

    /**
     * @param itemsPersonaActEcoTra the itemsPersonaActEcoTra to set
     */
    public void setItemsPersonaActEcoTra(List<PersonaActividadEconomica> itemsPersonaActEcoTra) {
        this.itemsPersonaActEcoTra = itemsPersonaActEcoTra;
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
     * @return the deshabilitaFechaCaduca
     */
    public boolean isDeshabilitaFechaCaduca() {
        return deshabilitaFechaCaduca;
    }

    /**
     * @param deshabilitaFechaCaduca the deshabilitaFechaCaduca to set
     */
    public void setDeshabilitaFechaCaduca(boolean deshabilitaFechaCaduca) {
        this.deshabilitaFechaCaduca = deshabilitaFechaCaduca;
    }

    /**
     * @return the itemsConyuguePersonaNatural
     */
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
     * @return the itemsConyuguePersonaNatural
     */
    public List<PersonaNatural> getItemsConyuguePersonaNatural() {
        return itemsConyuguePersonaNatural;
    }

    /**
     * @param itemsConyuguePersonaNatural the itemsConyuguePersonaNatural to set
     */
    public void setItemsConyuguePersonaNatural(List<PersonaNatural> itemsConyuguePersonaNatural) {
        this.itemsConyuguePersonaNatural = itemsConyuguePersonaNatural;
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
     * @return the nombreConyugue
     */
    public String getNombreConyugue() {
        return nombreConyugue;
    }

    /**
     * @param nombreConyugue the nombreConyugue to set
     */
    public void setNombreConyugue(String nombreConyugue) {
        this.nombreConyugue = nombreConyugue;
    }

    /**
     * @return the personaNaturalConyugeSel
     */
    public PersonaNatural getPersonaNaturalConyugeSel() {
        return personaNaturalConyugeSel;
    }

    /**
     * @param personaNaturalConyugeSel the personaNaturalConyugeSel to set
     */
    public void setPersonaNaturalConyugeSel(PersonaNatural personaNaturalConyugeSel) {
        this.personaNaturalConyugeSel = personaNaturalConyugeSel;
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
     * @return the personaConyuge
     */
    public PersonaConyuge getPersonaConyuge() {
        return personaConyuge;
    }

    /**
     * @param personaConyuge the personaConyuge to set
     */
    public void setPersonaConyuge(PersonaConyuge personaConyuge) {
        this.personaConyuge = personaConyuge;
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

    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public List<UbicacionGeografica> getItemsUbiGeoPaiNaci() {
        return itemsUbiGeoPaiNaci;
    }

    public void setItemsUbiGeoPaiNaci(List<UbicacionGeografica> itemsUbiGeoPaiNaci) {
        this.itemsUbiGeoPaiNaci = itemsUbiGeoPaiNaci;
    }

    public UbicacionGeografica getUbiGeoPaiNaci() {
        return ubiGeoPaiNaci;
    }

    public void setUbiGeoPaiNaci(UbicacionGeografica ubiGeoPaiNaci) {
        this.ubiGeoPaiNaci = ubiGeoPaiNaci;
    }

    public List<ClasePersona> getClasePersonaLista() {
        return this.ejbFacadeClasePersona.getItemsEstadoEliminado('N');
    }

    public void setClasePersonaLista(List<ClasePersona> clasePersonaLista) {
        this.clasePersonaLista = clasePersonaLista;
    }
    /**
     * Permite actualizar los datos de la persona seleccionada con el componente
     * de busqueda
     */

    private Long codigoPersona;
    private String nombreCompletoPersona = "";
    private Persona personaRelacion;

    public void seleccionaPersona() {
        try {
            nombreCompletoPersona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteNombreCompleto");
            if (nombreCompletoPersona == null || nombreCompletoPersona.isEmpty()) {
                nombreCompletoPersona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteNombreCompleto");
                codigoPersona = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona"));
            } else {
                codigoPersona = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona"));
            }
            setPersonaRelacion(ejbFacadePersona.getItemsByCodigo(codigoPersona).get(0));
            if (personaRelacion != null) {
                codigoRelacionPersona = personaRelacion.getCodigo();
            }
        } catch (NumberFormatException e) {
        }
    }

    public Persona getPersonaRelacion() {
        return personaRelacion;
    }

    public void setPersonaRelacion(Persona personaRelacion) {
        this.personaRelacion = personaRelacion;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getNombreCompletoPersona() {
        return nombreCompletoPersona;
    }

    public void setNombreCompletoPersona(String nombreCompletoPersona) {
        this.nombreCompletoPersona = nombreCompletoPersona;
    }

    public ClasePersona getClasePersona() {
        return clasePersona;
    }

    public void setClasePersona(ClasePersona clasePersona) {
        this.clasePersona = clasePersona;
    }

    public long getCodigoRelacionPersona() {
        return codigoRelacionPersona;
    }

    public void setCodigoRelacionPersona(long codigoRelacionPersona) {
        this.codigoRelacionPersona = codigoRelacionPersona;
    }

}
