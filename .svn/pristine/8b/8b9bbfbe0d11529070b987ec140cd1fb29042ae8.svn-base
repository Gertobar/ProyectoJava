/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.socios.bean;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.componentes.ListasUAFEComponente;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.OperadoraTelefono;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.Cargo;
import ec.renafipse.mks.modelo.socios.ConocimientoIfip;
import ec.renafipse.mks.modelo.socios.EstadoCivil;
import ec.renafipse.mks.modelo.socios.EstadoSocio;
import ec.renafipse.mks.modelo.socios.FuenteIngresos;
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
import ec.renafipse.mks.modelo.socios.Sector;
import ec.renafipse.mks.modelo.socios.SectorActividadEconomica;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SubsectorActividadEconomica;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoInstitucion;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.modelo.socios.TipoVivienda;
import ec.renafipse.mks.negocio.comunes.EntidadFinancieraFacade;
import ec.renafipse.mks.negocio.comunes.OperadoraTelefonoFacade;
import ec.renafipse.mks.negocio.comunes.PeriodicidadFacade;
import ec.renafipse.mks.negocio.comunes.TipoCuentaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.comunes.TipoTelefonoFacade;
import ec.renafipse.mks.negocio.comunes.UbicacionGeograficaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.ActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.CargoFacade;
import ec.renafipse.mks.negocio.socios.ConocimientoIfipFacade;
import ec.renafipse.mks.negocio.socios.EstadoCivilFacade;
import ec.renafipse.mks.negocio.socios.EstadoSocioFacade;
import ec.renafipse.mks.negocio.socios.FuenteIngresosFacade;
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
import ec.renafipse.mks.negocio.socios.ProfesionFacade;
import ec.renafipse.mks.negocio.socios.ReferenciaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.socios.ReferenciaPersonalFacade;
import ec.renafipse.mks.negocio.socios.SectorActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.SectorFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.SubsectorActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.TipoIdentificacionFacade;
import ec.renafipse.mks.negocio.socios.TipoInstitucionFacade;
import ec.renafipse.mks.negocio.socios.TipoPerTipoIdeFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import ec.renafipse.mks.negocio.socios.TipoViviendaFacade;
import java.io.IOException;
import java.io.Serializable;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author vicastoc
 */
@ManagedBean(name = "personaGeneralBean")
@ViewScoped
public class PersonaGeneralBean extends AbstractController<Persona> implements Serializable {

    @EJB
    private SocioFacade ejbFacade;

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
    private PersonaConyugeFacade ejbPersonaConyuge;

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

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String buscarPor;
    private String criterio;
    private String tituloPersonaTab;
    private String msg;

    private String identificacion;
    private String identificacionConyuge;
    private String mensajeCriterio;
    private String siglasIdentificacion;
    private String nombresCompletos;
    private String nombreSocioBusqueda;
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
    private RequestContext context;

    private boolean personaNueva;
    private boolean socioNuevo;
    private boolean editaPersona;
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
    private boolean sigueProceso;
    private boolean buscoPersona;
    private boolean esParaInstitucion;

    private Socio socio;
    private Socio socioSel;
    private UbicacionGeografica ubiGeoPai;
    private UbicacionGeografica ubiGeoPaiNaci;
    private UbicacionGeografica ubiGeoPro;
    private UbicacionGeografica ubiGeoCiu;
    private UbicacionGeografica ubiGeoPar;
    private UbicacionGeografica ubiGeoPaiRes;
    private UbicacionGeografica ubiGeoProRes;
    private UbicacionGeografica ubiGeoCiuRes;
    private UbicacionGeografica ubiGeoParRes;
    private Persona persona;
    private Persona personaSel;
    private PersonaNatural personaNatural;
    private PersonaNatural perNatConySelect;
    private PersonaNatural personaNaturalConyuge;
    private PersonaConyuge personaConyuge;
    private PersonaInstitucion personaInstitucion;
    private Persona personaRepresentanteIns;
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

    private List<Socio> itemsSocios;
    private List<Persona> itemsPersona;
    private List<PersonaNatural> itemsPersonaNatural;
    private List<TipoIdentificacion> itemsTipoIdentificacion;
    private List<PersonaInstitucionRep> itemsPersonaInstitucionRep;
    private List<PersonaInstitucionRep> itemsPersonaInstitucionRepReg;
    private List<UbicacionGeografica> itemsUbiGeoPai;
    private List<UbicacionGeografica> itemsUbiGeoPaiNaci;
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
    private List<PersonaActividadEconomica> itemsPersonaActEcoReg;
    private List<TipoPersona> itemsTipoPersona;
    private List<SubsectorActividadEconomica> itemsSubsectorActividadEconomica;
    private List<SectorActividadEconomica> itemsSectorActividadEconomica;
    private List<ActividadEconomica> itemsActividadEconomica;

    private String personaListasNegras;
    private String tituloMensaje;
    private String mensajePanel = "";
    private int estiloMensaje;
    private int tipoMensaje;
    String modulo = "";

    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public PersonaGeneralBean() {
        super(Persona.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacadePersona);
    }

    // *******************************************************************************************
    // --------------------------------------------------------------------------
    // -- LOGICA
    /**
     * Obtiene los datos del socio en base del criterio de la consulta
     */
    public void obtienePersonasNaturales() {

        try {
            if (Validaciones.cumpleRequerimientoCampo(criterio, 6)) {
                this.setItemsPersona(this.ejbFacadePersona.getItemsPersona(this.getCriterio(), this.getBuscarPor(), 'N'));
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsPersona(null);
                this.setPersonaSel(null);

            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaGeneralBean", "obtienePersonasNaturales", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Metodo para dar segumiento al wizard del socio
     *
     * Se adiciona el control de cambiar el color del panel
     *
     * @param event
     * @return Evento
     */
    public String sigueFlujo(FlowEvent event) {
        try {

            boolean estadoValidacionNombres = true;
            if (persona == null) {
                return "naturalTab";
            }
            ListasUAFEComponente listasUAFEComponente = new ListasUAFEComponente();
            listasUAFEComponente.setEjbFacadePersona(ejbFacadePersona);
            listasUAFEComponente.setEjbUsuarioFacade(ejbFacadeUsuario);
            listasUAFEComponente.setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloPersonaNatural"));
            setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloPersonaNatural"));
            try {
                if (this.persona.getNombreCompleto() != null) {
                    if (!this.persona.getNombreCompleto().isEmpty()) {
                        if (event.getNewStep().equals("naturalTab")||event.getOldStep().equals("naturalTab")) {
                            estadoValidacionNombres = listasUAFEComponente.validaUAFPorIdentificacionONombres(this.persona.getNombreCompleto(), true);                            
                        }                                                
                    }
                }
            } catch (Exception e) {
            }
            if (!estadoValidacionNombres) {
                
                return "naturalTab";
            }

            if (this.msg != null) {
                MuestraMensaje.addError(this.msg);
                return event.getOldStep();
            }
            if (isSaltar()) {
                setSaltar(false);
                return "confirmacionPerNatTab";
            }
            if ((event.getOldStep().equals("actividadEconomicaTab")) && (event.getNewStep().equals("confirmacionTab"))) {
                validaPrincipalActEco();
                if (!isSigueProceso()) {
                    return "actividadEconomicaTab";
                }
            }
        } catch (Exception ex) {
            return "datosBasicosPerNatTab";
        }
        return event.getNewStep();
    }

    // -------------------------------------------------------------------------------------------------
    // -- PERSONA
    /**
     *
     */
    public void preparaPersonaNatural() {

        this.deshabilitaAgregarTelefono = true;

        this.deshabilitaIdentificacion = false;
        this.sigueProceso = true;
        this.buscoPersona = false;
        this.personaNueva = true;
        this.personaNaturalNueva = true;
        this.personaResidenciaNueva = true;
        this.esParaInstitucion = false;

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
        this.tiempoActEco = 0;
        this.fechaIngresoActEco = null;
        this.setFechaActual(new Date());

        this.setPersona(new Persona());
        this.setPersonaNatural(new PersonaNatural());
        this.setPersonaResidencia(new PersonaResidencia());
        this.setPersonaTelefono(new PersonaTelefono());
        this.setPersonaActividadEconomica(new PersonaActividadEconomica());

        this.setItemsPersonaTelefono(new ArrayList<PersonaTelefono>());
        this.setItemsPersonaActEco(new ArrayList<PersonaActividadEconomica>());

        this.setItemsUbiGeoPaiNaci(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.setUbiGeoPaiNaci(null);
        this.setItemsUbiGeoPai(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.setUbiGeoPai(null);
        this.cambiaUbiGeoPai();
        this.setItemsUbiGeoPaiRes(this.ejbFacadeUbiGeo.getItemsUbicacionGeograficaJerarquiaVigentes(Long.parseLong("1")));
        this.setUbiGeoPaiRes(null);
        this.cambiaUbiGeoPaiRes();

        this.setItemsTipoPersona(ejbFacadeTipPer.getItemsEsParaInstitucionEliminado(this.esParaInstitucion ? 'S' : 'N', 'N'));

        this.setItemsSectorActividadEconomica(new ArrayList<SectorActividadEconomica>());
        this.setItemsSubsectorActividadEconomica(new ArrayList<SubsectorActividadEconomica>());
        this.setItemsSectorActividadEconomica(this.ejbFacadeSectorActividadEconomica.getItemsSectorEliminado('N'));
        this.setItemsActividadEconomica(new ArrayList<ActividadEconomica>());

        this.setItemsSectorActividadEconomica(this.ejbFacadeSectorActividadEconomica.getItemsSectorEliminado('N'));
        this.setItemsActividadEconomica(ejbFacadeActvidadEconomica.getItemsActividadEconomicaEliminado(7, 'S', 'N'));
        //System.out.println("Intems " + getItemsActividadEconomica().size());
        this.sectorActividadEconomica = null;
        this.subsectorActividadEconomica = null;
    }

    public void creaPersonaNatural(ActionEvent actionEvent) {
        this.preparaPersonaNatural();
    }

    /**
     * Prepara la Edicion del Socio Seleccionado
     *
     * @param event
     * @return
     */
    public Socio editaPersonaNatural(ActionEvent event) {
        try {
            this.preparaPersonaNatural();
            this.setItemsTipoPersona(ejbFacadeTipPer.getItemsEsParaInstitucionEliminado(this.esParaInstitucion ? 'S' : 'N', 'N'));
            this.setPersona(this.personaSel);
            this.personaNueva = false;
            this.sigueProceso = true;
            this.buscoPersona = false;
            this.editaPersona = true;
            ////System.out.println("Edita de socio " + this.getPersona());
            this.buscoPersona = false;
            this.setItemsTipoIdentificacion(this.ejbFacadeTipIde.findAll());
            this.buscaPersona();
            this.buscarConyuge();
            /*this.buscaPersonaNatural();
             this.buscaPersonaInsitucion();
             this.buscaPersonaResidencia();*/
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaGeneralBean", "editaPersonaNatural", CapturaError.getErrorException(ex)});

            return null;
        }

        //this.setSiglasIdentificacion(String.valueOf(this.getPersona().getCodigoTipoIdentificacion().getSiglas()));
        return this.getSocio();
    }

    /**
     * Metodo que guarda el socio
     *
     * @param actionEvent
     */
    public void guardaPersonaGeneral(ActionEvent actionEvent) {
        try {
            this.guardaPersona();
            if (String.valueOf(this.getPersona().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                this.guardaPersonaNatural();
            } else {
                this.guardaPersonaInstitucion();
            }
            this.guardaResidencia();
            this.guardaTelefono();
            this.guardaActividadEconomica();
            MuestraMensaje.addSatisfactorioPersistencia(1);

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaGeneralBean", "guardaPersonaGeneral", CapturaError.getErrorException(ex)});

        }
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
            ejbFacadePersona.actualiza(persona.getCodigoTipoIdentificacion(), persona.getCodigoTipoPersona(), persona.getIdentificacion(), persona.getNombreCompleto(), persona.getFechaActualizacion(), persona.getCorreoEletronico(), persona.getFechaCaducidadIdentificacion(), persona.getCodigo());
            //ejbFacadePersona.edit(persona);
        }
    }

    /**
     * Busca los datos basicos de la persona
     */
    public void buscaPersona() {

        Persona personaExistente;
        ////System.out.println("Busca Persona: " + this.getPersona() + " Ide " + this.getPersona().getIdentificacion());
        this.msg = null;
        if ((this.isPersonaNueva() && !this.buscoPersona) || (this.editaPersona && !this.buscoPersona)) {
            personaExistente = this.ejbFacadePersona.getPersona(this.getPersona().getIdentificacion());
            this.identificacion = this.persona.getIdentificacion();
            this.buscoPersona = true;
            //System.out.println("Busco Persona MSG" + msg);
            if (personaExistente != null) {
                this.setPersona(personaExistente);
                this.setPersonaNueva(false);
                this.setSiglasIdentificacion(String.valueOf(this.getPersona().getCodigoTipoIdentificacion().getSiglas()));

                if (this.isPersonaNueva()) {
                    MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaExistente"));
                }

                this.setItemsPersonaTelefono(this.ejbFacadePerTel.getItemsPorPersona(persona));
                this.setItemsPersonaTelefonoReg(this.ejbFacadePerTel.getItemsPorPersona(persona));
                if (this.getItemsPersonaTelefono() == null) {
                    this.setItemsPersonaTelefono(new ArrayList<PersonaTelefono>());
                }

                this.setItemsPersonaActEco(this.ejbFacadePerActEco.getItemsPersonaActividadEconomoica(persona.getCodigo()));
                if (this.getItemsPersonaActEco() == null) {
                    this.setItemsPersonaActEco(new ArrayList<PersonaActividadEconomica>());
                }

                this.buscaPersonaNatural();
                this.buscaPersonaResidencia();
            }

            //System.out.println("Busco Persona FIN " + msg);
        }

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

                if (this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoJerarquia().getSiglas().equals("PR")) {
                    this.ubiGeoPai = this.getPersonaNatural().getCodigoUbiGeoNac().getCodigoUbiGeoPadre();
                    this.cambiaUbiGeoPai();
                    this.ubiGeoPro = this.getPersonaNatural().getCodigoUbiGeoNac();

                    this.cambiaUbiGeoPai();
                    this.cambiaUbiGeoPro();

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

                if (this.getPersonaNatural() != null) {
                    if (this.getPersonaNatural().getCodigoUbiGeoNaci() != null) {
                        if (this.getPersonaNatural().getCodigoUbiGeoNaci().getCodigoJerarquia() != null) {
                            if (this.getPersonaNatural().getCodigoUbiGeoNaci().getCodigoJerarquia().getSiglas().equals("PA")) {
                                this.ubiGeoPaiNaci = this.getPersonaNatural().getCodigoUbiGeoNaci();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Guarda la Información digitada en la Base de Datos de la Persona Natural
     */
    public void guardaPersonaNatural() {

        this.getPersonaNatural().setCodigoUbiGeoNaci(ubiGeoPaiNaci);
        this.getPersonaNatural().setCodigoUbiGeoNac(ubiGeoPai);
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
        if (this.isPersonaNaturalNueva()) {
            this.getPersonaNatural().setFechaIngreso(new Date());
            this.getPersonaNatural().setFechaActualizacion(this.getPersonaNatural().getFechaIngreso());
            this.getPersonaNatural().setCodigoPersona(this.getPersona().getCodigo());
            ejbFacadePerNat.create(this.personaNatural);
        } else {
            this.getPersonaNatural().setFechaActualizacion(new Date());
            ejbFacadePerNat.actualiza(personaNatural.getCodigoUbiGeoNac(), personaNatural.getCodigoUbiGeoNaci(), personaNatural.getCodigoProfesion(), personaNatural.getCodigoInstruccion(), personaNatural.getCodigoFuenteIngresos(), personaNatural.getCodigoEstadoCivil(), personaNatural.getNombres(), personaNatural.getPrimerApellido(), personaNatural.getSegundoApellido(), personaNatural.getIngresos(), personaNatural.getSexo(), personaNatural.getFechaNacimiento(), personaNatural.getExoneradoSri(), personaNatural.getCargasFamiliares(), personaNatural.getFechaActualizacion(), personaNatural.getPersona());
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
            ejbFacadePerIns.actualiza(personaInstitucion.getCodigoTipoInstitucion(), personaInstitucion.getRazonSocial(), personaInstitucion.getNombreComercial(), personaInstitucion.getObjetoSocial(), personaInstitucion.getFechaConstitucion(), personaInstitucion.getEsSujetoSri(), personaInstitucion.getObservaciones(), personaInstitucion.getFechaActualizacion(), personaInstitucion.getControladaOc(), personaInstitucion.getCodigoPersona());
            //this.ejbFacadePerIns.edit(personaInstitucion);
        }

        for (PersonaInstitucionRep representante : this.getItemsPersonaInstitucionRep()) {
            if (representante.getPersonaInstitucion() == null) {
                representante.setPersonaInstitucion(personaInstitucion);
                representante.getPersonaInstitucionRepPK().setCodigoPersona(this.getPersonaInstitucion().getCodigoPersona());
            }
            ejbFacadePerInsRep.actualiza(representante.getCodigoNivel(), representante.getCodigoCargo(), representante.getEliminado(), representante.getPersonaInstitucionRepPK().getCodigoPersona(), representante.getPersonaInstitucionRepPK().getCodigoPersonaRep());
            //ejbFacadePerInsRep.edit(representante);
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

    public void validaPersonaNaturalIngresada() {
        try {
            List<Persona> personas = this.ejbFacadePersona.getItemsPersonaIdentificacion(this.persona.getIdentificacion());
            if (!personas.isEmpty()) {
                this.persona.setIdentificacion(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionRegistrada");
                MuestraMensaje.addAdvertencia(msg);
            } else {
                msg = null;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Paso");
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
            ejbFacadePerRes.actualiza(personaResidencia.getCodigoUbiGeoRes(), personaResidencia.getCodigoTipoVivienda(), personaResidencia.getCodigoPeriodicidad(), personaResidencia.getCodigoSector(), personaResidencia.getBarrio(), personaResidencia.getDireccion(), personaResidencia.getReferencia(), personaResidencia.getTiempo(), personaResidencia.getCodigoPersona());
            //ejbFacadePerRes.edit(personaResidencia);
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
                    pt.setRecibeNotificacion(pt.getRecibeNotificacion());
                    this.ejbFacadePerTel.create(pt);
                } else {
                    this.ejbFacadePerTel.actualiza(pt.getCodigoPersona(), pt.getCodigoTipoTelefono(), pt.getCodigoOperadoraTelefono(), pt.getNumero(), pt.getEliminado(), pt.getRecibeNotificacion(), pt.getCodigo());
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
                ejbFacadeRefPer.actualiza(rP.getDireccion(), rP.getEliminado(), rP.getNombres(), rP.getTelefono(), rP.getCodigo());
                //this.ejbFacadeRefPer.edit(rP);
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
        msg = (msg == null) ? ((Validaciones.validaTelefonoConvencional(this.getTelefonoRefPer())) ? null : ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TelefonoIncorrecto")) : msg;

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
                this.ejbFacadeRefEntFin.actualiza(rEF.getCodigoEntidadFinanciera(), rEF.getCodigoTipoCuenta(), rEF.getEliminado(), rEF.getNumeroCuenta(), rEF.getObservaciones(), rEF.getCodigo());
                //this.ejbFacadeRefEntFin.edit(rEF);
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
                    this.ejbFacadePerActEco.actualiza(pAE.getActividadEconomica().getCodigo(), pAE.getCodigoPeriodicidad(), pAE.getTiempo(), pAE.getFechaActualizacion(), pAE.getEliminado(), pAE.getEsPrincipal(), pAE.getPersona().getCodigo());
                    //this.ejbFacadePerActEco.edit(pAE);
                }

                if (String.valueOf(pAE.getEsPrincipal()).equals("S")) {

                    personaActEcoPrin = this.ejbFacadePerActEcoPri.find(persona.getCodigo());
                    if (personaActEcoPrin == null) {
                        personaActEcoPrin = new PersonaActEcoPri();
                        this.personaActEcoPrin.setFechaIngreso(new Date());
                        this.personaActEcoPrin.setCodigoPersona(persona.getCodigo());
                        this.personaActEcoPrin.setCodigoPersona(this.persona.getCodigo());
                    }

                    this.personaActEcoPrin.setFechaActualizacion(new Date());
                    this.personaActEcoPrin.setPersonaActividadEconomica(pAE);
                    this.personaActEcoPrin.setCodigoActividadEconomica(pAE.getActividadEconomica().getCodigo());

                    this.ejbFacadePerActEcoPri.actualiza(personaActEcoPrin.getCodigoActividadEconomica(), personaActEcoPrin.getFechaActualizacion(), personaActEcoPrin.getCodigoPersona());

                    //this.ejbFacadePerActEcoPri.edit(personaActEcoPrin);
                }

            }
        }

    }

    public void guardaPersonaConyuge() {
        List<PersonaConyuge> conyuges = ejbPersonaConyuge.getItemsCodigoPersonaYcodigoConyuge(getPersonaConyuge().getPersonaConyugePK().getCodigoPersona(), getPersonaConyuge().getPersonaConyugePK().getCodigoPersonaConyuge());
        if (conyuges != null && !conyuges.isEmpty()) {
            for (PersonaConyuge pc : conyuges) {
                pc.setEliminado('S');
                ejbPersonaConyuge.actualiza(pc.getPersonaNaturalConyuge(), pc.getFirma(), pc.getEliminado(), pc.getPersonaNatural());
                //ejbPersonaConyuge.edit(pc);
            }
        }
        if (this.getPersonaNatural().getCodigoEstadoCivil() != null && this.getPersonaNatural().getCodigoEstadoCivil().getCodigo() == 2
                && this.getPersonaConyuge() != null && this.getPersonaConyuge().getPersonaConyugePK() != null) {
            //Actualizar/eliminar
            if (ejbPersonaConyuge.getPersonaYconyuge(getPersonaConyuge().getPersonaConyugePK().getCodigoPersona(), getPersonaConyuge().getPersonaConyugePK().getCodigoPersonaConyuge()) != null) {
                ejbPersonaConyuge.actualiza(personaConyuge.getPersonaNaturalConyuge(), personaConyuge.getFirma(), personaConyuge.getFirma(), personaConyuge.getPersonaNatural());
                //ejbPersonaConyuge.edit(personaConyuge);
            } //Ingresar
            else {
                ejbPersonaConyuge.create(personaConyuge);
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
        msg = null;

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
                            //System.out.println("MasDeUnaActEcoPri " + msg);
                            break;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCamposRequeridos");
        }

        //System.out.println("msg antes " + msg);
        /*
         if (msg == null) {
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
            this.getItemsPersonaInstitucionRep().remove(this.getPersonaInstitucionRepSel());
        }

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
        this.setSiglasIdentificacion(String.valueOf(this.getPersona().getCodigoTipoIdentificacion().getSiglas()));
        if (this.getSiglasIdentificacion().equals("S")) {
//            this.persona.setIdentificacion("SEQ");
//            this.deshabilitaIdentificacion = true;
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

//    public void cambiaSubSector() {
//        if (this.subsectorActividadEconomica != null) {
////            this.setItemsActividadEconomica(this.ejbFacadeActvidadEconomica.getItemsSectorSubsectorNivelEliminado(this.getSectorActividadEconomica().getCodigo(), this.getSubsectorActividadEconomica().getCodigo(), (long) 3, 'N'));
//            this.personaActividadEconomica.setActividadEconomica(null);
//        } else {
//            this.setItemsActividadEconomica(null);
//            this.personaActividadEconomica.setActividadEconomica(null);
//        }
//    }
    public void buscarConyuge() {
        setIdentificacionConyuge("");
        if (this.getPersonaNatural().getCodigoEstadoCivil() != null && this.getPersonaNatural().getCodigoEstadoCivil().getCodigo() == 2) {
            PersonaConyuge pc = ejbPersonaConyuge.getPersonaCodigoFechaRegistro(this.getPersona().getCodigo(), 'N');
            this.setPersonaConyuge((pc != null) ? pc : nuevoPersonaConyuge());
            this.setPersonaNaturalConyuge(this.personaConyuge.getPersonaNaturalConyuge() != null ? this.personaConyuge.getPersonaNaturalConyuge() : new PersonaNatural());
            this.getPersonaNaturalConyuge().setPersona((this.personaNaturalConyuge != null) ? ejbFacadePersona.getPersonaByCodigo(this.personaNaturalConyuge.getCodigoPersona()) : new Persona());

            if (this.getPersonaNaturalConyuge() != null && this.getPersonaNaturalConyuge().getPersona() != null && this.getPersonaNaturalConyuge().getPersona().getIdentificacion() != null) {
                setIdentificacionConyuge(this.getPersonaNaturalConyuge().getPersona().getIdentificacion());
                System.out.println(": " + this.getPersonaNaturalConyuge().getPersona().getIdentificacion());
                System.out.println(": " + this.getPersonaNaturalConyuge().getPersona().getNombreCompleto());
            }
        } else {
            this.setPersonaConyuge(null);
            setPersonaNaturalConyuge(new PersonaNatural());
            getPersonaNaturalConyuge().setPersona(new Persona());
        }
    }

    public void buscaPersonaConyugue() {
        //setIdentificacionConyuge("");
        System.out.println("buscaPersonaConyugue(): " + getIdentificacionConyuge());
        if (this.getPersonaNatural().getCodigoEstadoCivil() != null && this.getPersonaNatural().getCodigoEstadoCivil().getCodigo() == 2) {
            this.setPersonaConyuge(nuevoPersonaConyuge());
            // this.setPersonaNaturalConyuge(this.personaConyuge.getPersonaNaturalConyuge());
            this.getPersonaNaturalConyuge().setPersona((this.personaNaturalConyuge != null) ? ejbFacadePersona.getPersonaByCodigo(this.personaNaturalConyuge.getCodigoPersona()) : new Persona());

            if (this.getPersonaNaturalConyuge() != null && this.getPersonaNaturalConyuge().getPersona() != null && this.getPersonaNaturalConyuge().getPersona().getIdentificacion() != null) {
                setIdentificacionConyuge(this.getPersonaNaturalConyuge().getPersona().getIdentificacion());
                System.out.println(": " + this.getPersonaNaturalConyuge().getPersona().getIdentificacion());
                System.out.println(": " + this.getPersonaNaturalConyuge().getPersona().getNombreCompleto());
            }
        } else {
            this.setPersonaConyuge(null);
            setPersonaNaturalConyuge(new PersonaNatural());
            getPersonaNaturalConyuge().setPersona(new Persona());
        }
    }

    public PersonaConyuge nuevoPersonaConyuge() {
        if (!getIdentificacionConyuge().isEmpty()) {
            System.out.println("nuevoPersonaConyuge(): " + getIdentificacionConyuge());
            this.setPersonaNaturalConyuge(ejbFacadePerNat.getIdentificacion(getIdentificacionConyuge()));
            if (getPersonaNaturalConyuge() == null) {// No se encuentra registrado el usuario.
                System.out.println("No se encontro a la persona para conyuge");
                setPersonaNaturalConyuge(new PersonaNatural());
                getPersonaNaturalConyuge().setPersona(new Persona());
                return new PersonaConyuge();
            }
            PersonaConyuge conyuge = new PersonaConyuge(new PersonaConyugePK(getPersona().getCodigo(), getPersonaNaturalConyuge().getCodigoPersona()));
            conyuge.setPersonaNaturalConyuge(getPersonaNaturalConyuge());
            conyuge.setPersonaNatural(getPersonaNatural());
            conyuge.setEliminado('N');
            conyuge.setFirma('S');
            conyuge.setFechaRegistro(getFechaActual());
            conyuge.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            return conyuge;
        } else {///Identificador vacio
            System.out.println("Vacio el campo de ideintificaicon");
            setPersonaNaturalConyuge(new PersonaNatural());
            getPersonaNaturalConyuge().setPersona(new Persona());
            return new PersonaConyuge();
        }
    }
//1 decimal // 2 entero // 3 fecha // 4 string 

    public void seleccionaSocio(ActionEvent actionEvent) {
        System.out.println("Ingresa al boton..");
        if (perNatConySelect != null
                && perNatConySelect.getPersona() != null
                && perNatConySelect.getPersona().getIdentificacion() != null
                && !perNatConySelect.getPersona().getIdentificacion().isEmpty()) {
            identificacionConyuge = perNatConySelect.getPersona().getIdentificacion();
            System.out.println(identificacionConyuge);
            buscaPersonaConyugue();
        }
        // Actualizando componentes de la vista
        System.out.println("Actualizando componentes de la vista");
        context = RequestContext.getCurrentInstance();
        List<String> listaComponentesActualizar = new ArrayList<String>();
        listaComponentesActualizar.add("personaNatPan");
        listaComponentesActualizar.add("personaNatPan:codigoConyugue");
        context.update(listaComponentesActualizar);
    }
    // ---------------FIN DE REFRESCAMIENTO DE BOTONES --------------------------

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public void obtieneSocios() {
        if (Validaciones.cumpleRequerimientoCampo(this.nombreSocioBusqueda, 6)) {
            this.setItemsPersonaNatural(ejbFacadePerNat.getItemsLikeNombre(nombreSocioBusqueda));
        } else {
            this.setItemsPersonaNatural(null);
        }
    }

    public void preparaBusquedaSocioLista(ActionEvent actionEvent) throws IOException {
        nombreSocioBusqueda = "";
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
        return this.ejbFacadePeriocidad.getItemsPeriodicidadEliminado('N');
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

    // -- FIN LISTAS DE COMBOS 
    // ---------------------------------------------------------------------------
    // -- FIN DE LOGICA DE NEGOCIO
    // --------------------------------------------------------------------------
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
     * @return the itemsTipoPersona
     */
    public List<TipoPersona> getItemsTipoPersona() {
        return itemsTipoPersona;
    }

    /**
     * @param itemsTipoPersona the itemsTipoPersona to set
     */
    public void setItemsTipoPersona(List<TipoPersona> itemsTipoPersona) {
        this.itemsTipoPersona = itemsTipoPersona;
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
     * @return the personaNaturalConyuge
     */
    public PersonaNatural getPersonaNaturalConyuge() {
        return personaNaturalConyuge;
    }

    /**
     * @param personaNaturalConyuge the personaNaturalConyuge to set
     */
    public void setPersonaNaturalConyuge(PersonaNatural personaNaturalConyuge) {
        this.personaNaturalConyuge = personaNaturalConyuge;
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
     * @return the perNatConySelect
     */
    public PersonaNatural getPerNatConySelect() {
        return perNatConySelect;
    }

    /**
     * @param perNatConySelect the perNatConySelect to set
     */
    public void setPerNatConySelect(PersonaNatural perNatConySelect) {
        this.perNatConySelect = perNatConySelect;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTituloMensaje() {
        return tituloMensaje;
    }

    public void setTituloMensaje(String tituloMensaje) {
        this.tituloMensaje = tituloMensaje;
    }

    public String getMensajePanel() {
        return mensajePanel;
    }

    public void setMensajePanel(String mensajePanel) {
        this.mensajePanel = mensajePanel;
    }

    public int getEstiloMensaje() {
        return estiloMensaje;
    }

    public void setEstiloMensaje(int estiloMensaje) {
        this.estiloMensaje = estiloMensaje;
    }

    /**
     * Permite validar por nombres o por identificacion
     *
     * @return devuelve falso si es una persona sentenciada y verdadero homonimo
     * o pps
     */
    public boolean validacionUAF() {
        boolean estadoValidacionNombres = true;
        if (persona == null) {
            return estadoValidacionNombres;
        }
        ListasUAFEComponente listasUAFEComponente = new ListasUAFEComponente();
        listasUAFEComponente.setEjbFacadePersona(ejbFacadePersona);
        listasUAFEComponente.setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloPersonaNatural"));
        setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloPersonaNatural"));
        try {
            if (this.persona.getIdentificacion() != null) {
                if (!this.persona.getIdentificacion().isEmpty()) {
                    estadoValidacionNombres = listasUAFEComponente.validaUAFPorIdentificacionONombres(this.persona.getIdentificacion(), true);
                }
            }
        } catch (Exception e) {
        }
        try {
            if (!listasUAFEComponente.isMostraMensaje()) {
                if (personaNatural != null) {
                    if (personaNatural.getNombres() != null) {
                        if (!personaNatural.getNombres().isEmpty()) {
                            listasUAFEComponente.validaUAFPorIdentificacionONombres(this.personaNatural.getPrimerApellido() + "%" + this.personaNatural.getSegundoApellido() + "%" + this.personaNatural.getNombres(), true);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return listasUAFEComponente.getTipoPersona() == ListasUAFEComponente.SENTENCIADO;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
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
}
