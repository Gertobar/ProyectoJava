package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.ahorros.bean.LicitudFondosBean;
import ec.renafipse.mks.control.componentes.ListasUAFEComponente;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.LicitudFonCptoTran;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormulario;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosModulo;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.ContratoDpfPK;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpf;
import ec.renafipse.mks.modelo.dpfs.DocumentoContratoDpf;
import ec.renafipse.mks.modelo.dpfs.ParametroGeneralDpfIfip;
import ec.renafipse.mks.modelo.dpfs.ParametroGeneralDpfIfipPK;
import ec.renafipse.mks.modelo.dpfs.RegistroAgenciaSocioDPF;
import ec.renafipse.mks.modelo.dpfs.TasaIntenresProductoDPFMontoDetalle;
import ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.seguridades.UsuarioCambiaTasaDpf;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaInstitucion;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFonCptoTranFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosFormularioFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosModuloFacade;
import ec.renafipse.mks.negocio.comunes.PeriodicidadFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.dpfs.ContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.DocumentoContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.ParametroGeneralDpfIfipFacade;
import ec.renafipse.mks.negocio.dpfs.RegistroAgenciaSocioDPFFacade;
import ec.renafipse.mks.negocio.dpfs.TasaIntenresProductoDPFMontoDetalleFacade;
import ec.renafipse.mks.negocio.dpfs.TasaInteresProDpfMonFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioCambiaTasaDpfFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaInstitucionFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "contratoDpfController")
@ViewScoped
public class ContratoDpfController extends AbstractController<ContratoDpf> implements Serializable {

    @EJB
    private ContratoDpfFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private PeriodicidadFacade ejbFacadePeriodicidad;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private PersonaInstitucionFacade ejbFacadePerIns;

    @EJB
    private ParametroGeneralDpfIfipFacade ejbFacadeParametroGeneralDpfIfip;

    @EJB
    private TasaInteresProDpfMonFacade ejbFacadeTasaInteresProDpfMon;

    @EJB
    private UsuarioCambiaTasaDpfFacade ejbFacadeUsuarioCambiaTasaDpf;

    @EJB
    private DocumentoContratoDpfFacade ejbFacadeDocumentoContratoDpf;

    @EJB
    private ComputadorFacade ejbFacadeComputador;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private PersonaNaturalFacade ejbFacadePersonaNatural;

    @EJB
    private PersonaConyugeFacade ejbFacadeConyugePersona;

    @EJB
    private GaranteCreditoFacade ejbFacadeGaranteCredito;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private LicitudFondosModuloFacade ejbFacadeLicitudFondosModulo;

    @EJB
    private LicitudFonCptoTranFacade ejbFacadeLicitudFonCptoTran;

    @EJB
    private LicitudFondosFormularioFacade ejbFacadeLicitudFondosFormulario;

    private LlamaSP llamaSP;

    //--------------------------------------------------------
    // PERSONALIZADOS
    @ManagedProperty("#{licitudFondosBean}")
    private LicitudFondosBean licitudFondosBean;

    private Long codigoSocio;
    private String nombreSocio;
    private String msg;
    private String mensajeCriterio;
    private String nombreSocioBusqueda;
    private Long codigoPersona;
    private String controladaPorOc;
    private String nombrePersonaBusqueda;
    private String criterio;

    private boolean esBusquedaSocioContrato;

    private String tipoCuentaSel;
    private RequestContext context;
    private String urlSinPermisos;

    // Variables del COntrato 
    private Long codigoContrato;
    private Long codigoPersonaContrato;
    private Long codigoSocioContrato;
    private Long plazo;
    private Long plazoDias;
    private Long plazoMeses;
    private long generaFormulario;
    private long codigoFormulario;
    private String nombreSocioContrato;
    private BigDecimal capital;
    private BigDecimal retencion;
    private BigDecimal total;
    private BigDecimal tasaInteres;
    private BigDecimal tasaInteresMaxima;
    private BigDecimal tasaInteresIncremento = new BigDecimal(0.1);
    private final BigDecimal INCREMENTO_TASA = new BigDecimal(0.50);
    private final BigDecimal TASA_MAXIMA = new BigDecimal(10.50);
    private BigDecimal tasaMinima;
    private BigDecimal interes;
    private BigDecimal porcentajeRetencion;
    private Date fechaVencimiento;
    private String acreditacionMensual;
    private String numeroDocumento;
    private Long codigoDocumento;
    private Long codigoTasa;
    private Timestamp fechaContrato;
    private GeneraReporte generaReporte;
    private String conInteres;
    private String renovacionAutomatica;
    private String esSocioBeneficiario;
    private String identificacionPersonaBeneficiario;

    private boolean deshabilitaConInteres;
    private boolean deshabilitaRenovacionAutomatica;
    private boolean deshabilitaControladaPorOc;
    private boolean deshabilitaTasa;
    private boolean deshabilidaCodigoPersonaBeneficiario;

    private Socio socioSel;
    private Cuenta cuentaCombo;
    private Cuenta cuenta;
    private Periodicidad periodicidad;
    private Moneda moneda;
    private Persona personaBeneficiario;
    private Persona personaBeneficiarioSel;
    private LicitudFondosModulo licitudFondosModulo;

    private List<Socio> itemsSocio;
    private List<ContratoDpf> itemsContratoDpf;
    private List<CuentaContratoDpf> itemsCuentaContraDpf;
    private List<Cuenta> itemsCuentaSocio;
    private List<TasaInteresProDpfMon> listaTasaInteresDpfIfip;
    private List<Persona> itemsPersona;
    private int tipoMensaje;
    private String modulo = "";
    private boolean desactivaBotonGuardar;

    // <editor-fold defaultstate="collapsed" desc="VARIABLES NUEVA TASA DE APROBACION">
    @EJB
    private TasaIntenresProductoDPFMontoDetalleFacade nuevaTasa;
    private boolean tasaAprobada = false;
    private BigDecimal valorNuevaTasa = BigDecimal.ZERO;
    private RegistroAgenciaSocioDPF registroAgenciaSocioDPF;
    @EJB
    private RegistroAgenciaSocioDPFFacade registroAgenciaSocioDPFFacade;

    // </editor-fold>
    /**
     * Initialize the concrete ContratoDpf controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     *
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        this.controladaPorOc = "N";
        try {
            this.obtientePorcentajeRetencion();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "init", CapturaError.getErrorException(ex)});
        }
        urlSinPermisos = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(urlSinPermisos);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"contratoDpfController", "init", CapturaError.getErrorException(ex)});
            }
        } else {
            List<Computador> listaComputador = this.ejbFacadeComputador.getItemsCodigoComputadorIfiAgenPerEli(ActivacionUsuario.getCodigoComputador(), ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
            if (listaComputador.isEmpty()) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoPerteneAgencia"));
                try {
                    Sesion.redireccionaPagina(urlSinPermisos);
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"contratoDpfController", "init", CapturaError.getErrorException(ex)});
                }
            }
        }
        // Colocando el modulo de liciytud de fondos
        licitudFondosModulo = this.ejbFacadeLicitudFondosModulo.find(3L);
        // Verificando que exista el modulo de licitud de fondos
        if (licitudFondosModulo == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModuloLicitudFondosNoExiste"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"contratoDpfController", "init", CapturaError.getErrorException(ex)});
            }
        }
        //---------------------------------------------------------
        //-- OBTIENE SI PUEDE MODIFICAR LA TASA DE INTERES
        setDeshabilitaTasa(true);
        // DESHABILITA EL BOTON GUARDAR HASTA QUE PASE LA VALIDACION DE LA UAF
        setDesactivaBotonGuardar(true);
        ParametroGeneralDpfIfip pgdi = this.ejbFacadeParametroGeneralDpfIfip.find(new ParametroGeneralDpfIfipPK((long) 3, ActivacionUsuario.getCodigoIfip()));
        if (pgdi != null) {
            //-- Consultando si la IFIP puede cambiar la tasa de Interes de los DPFS            
            if (pgdi.getValorTexto().trim().equals("S")) {
                // Buscamos si el usuario esta perimitido para hacer el cambio de la tasa
                UsuarioCambiaTasaDpf uctd = this.ejbFacadeUsuarioCambiaTasaDpf.find(ActivacionUsuario.getCodigoUsuario());
                if (uctd != null) {
                    // Verificando que el usuario pueda modificar la tasa
                    if (uctd.getEliminado() == 'N') {
                        this.setDeshabilitaTasa(false);
                    }
                }
            }
        }
    }
    
    /**
     * 
     */
    public ContratoDpfController() {
        // Inform the Abstract parent controller of the concrete PagoCredito?cap_first Entity
        super(ContratoDpf.class);
    }

    /**
     * Obtiene los DPFS de las Personas
     */
    public void obtieneDpfs() {
        if (this.codigoPersona != null) {
            this.itemsContratoDpf = this.ejbFacade.getItemsCodigoIfipCodigoPersona(this.getCodigoPersona(), ActivacionUsuario.getCodigoIfip());
        } else {
            this.itemsContratoDpf = null;
        }
    }

    /**
     * Obtiene los datos de las personas buscado por un nombre
     */
    public void obtienePersonas() {
        try {
            this.setPersonaBeneficiarioSel(null);
            this.setItemsPersona(null);
            if (Validaciones.cumpleRequerimientoCampo(this.nombrePersonaBusqueda, 10)) {
                this.setItemsPersona(this.ejbFacadePersona.getItemsPersona(this.nombrePersonaBusqueda.toUpperCase(), "N", 'N'));
            } else {
                this.mensajeCriterio = Validaciones.msg;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtienePersonas", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Selecciona la persona beneficiario
     */
    public void seleccionaPersona() {
        this.personaBeneficiario = personaBeneficiarioSel;
        this.identificacionPersonaBeneficiario = this.personaBeneficiario.getIdentificacion();
        if (validacionUAF(personaBeneficiario)) {
            setDesactivaBotonGuardar(true);
        } else {
            setDesactivaBotonGuardar(false);
        }
        //Valida fecha de caducidad de la identificacion del beneficiario
        /*if (Validaciones.validaFechaMenorHoy(this.getPersonaBeneficiarioSel().getFechaCaducidadIdentificacion())) {
            this.personaBeneficiario = null;
            this.identificacionPersonaBeneficiario = "";
            msg = (ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioCaducadaBeneficiario"));
            MuestraMensaje.addError(msg);
        }*/
    }

    public void validacionPersonaUAF() {
        if (getSocioSel() != null) {
            if (validacionUAF(getSocioSel().getCodigoPersona())) {
                setDesactivaBotonGuardar(true);
            } else {
                setDesactivaBotonGuardar(false);
            }
        }
    }

    /**
     * Permite validar por nombres o por identificacion
     *
     * @param personaEncontrada
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
        listasUAFEComponente.setEjbUsuarioFacade(ejbFacadeUsuario);
        listasUAFEComponente.setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloDPF"));
        setModulo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasModuloDPF"));
        try {
            if (personaEncontrada.getNombreCompleto() != null) {
                if (!personaEncontrada.getIdentificacion().isEmpty()) {
                    listasUAFEComponente.validaUAFPorIdentificacionONombres(personaEncontrada.getIdentificacion(), true);
                }
                if (!personaEncontrada.getNombreCompleto().isEmpty() && listasUAFEComponente.getTipoPersona() == 0) {
                    listasUAFEComponente.validaUAFPorIdentificacionONombres(personaEncontrada.getNombreCompleto(), true);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "validacionUAF", CapturaError.getErrorException(e)});
        }
        return listasUAFEComponente.getTipoPersona() == ListasUAFEComponente.SENTENCIADO;
    }

    /**
     * Buscando la persona que sera beneficiario
     */
    public void buscaPersonaBeneficiario() {
        if (this.getEsSocioBeneficiario().equals("N")) {
            this.setPersonaBeneficiario(this.ejbFacadePersona.getPersona(this.identificacionPersonaBeneficiario));
            this.identificacionPersonaBeneficiario = this.getPersonaBeneficiario().getIdentificacion();
        }
    }

    /**
     * Prepara la creación de un nuevo DPF
     *
     * @param actionEvent
     */
    public void preparaNuevoDpf(ActionEvent actionEvent) {
        this.cuentaCombo = null;
        this.setMoneda(null);
        this.controladaPorOc = "N";
        this.deshabilitaControladaPorOc = true;
        this.setPeriodicidad(null);
        this.socioSel = null;
        this.msg = null;
        this.codigoContrato = null;
        this.deshabilitaConInteres = false;
        this.deshabilitaRenovacionAutomatica = false;
        this.renovacionAutomatica = "S";
        this.conInteres = "N";
        this.acreditacionMensual = "N";
        this.setSelected(null);
        codigoPersonaContrato = null;
        codigoSocioContrato = null;
        nombreSocioContrato = null;
        plazo = null;
        plazoDias = null;
        capital = null;
        retencion = null;
        total = null;
        tasaInteres = null;
        interes = null;
        fechaVencimiento = null;
        acreditacionMensual = null;
        codigoContrato = 0L;
        this.porcentajeRetencion = null;
        this.numeroDocumento = null;
        this.tipoCuentaSel = null;
        this.esSocioBeneficiario = "S";
        this.deshabilidaCodigoPersonaBeneficiario = false;
        this.personaBeneficiario = null;
        this.identificacionPersonaBeneficiario = null;
        itemsCuentaContraDpf = new ArrayList<>();
        this.itemsCuentaSocio = this.ejbFacadeCuenta.getItemsProductoMonedaPuedeReactivar(13389L, ActivacionUsuario.getCodigoIfip(), 2L, 1L, 'S');
    }

    /**
     * Prepara la lista de valores para la busqueda
     *
     * @param actionEvent
     */
    public void preparaBusquedaPersonaLista(ActionEvent actionEvent) {
        this.setNombrePersonaBusqueda(null);
        this.personaBeneficiarioSel = null;
        this.itemsPersona = null;
        this.setCriterio(null);
    }

    /**
     * Valida antes de guarda el deposito de plazo fijo
     *
     * @return
     * @throws IOException
     */
    private boolean validaGuardar() throws IOException {
        if (this.codigoSocioContrato == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("Socio") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        } else if (this.personaBeneficiario == null) {
            return false;
        } else {
            this.obtieneNombreSocio(false);
            if (this.msg != null) {
                return false;
            }
        }
        if (this.getMoneda() == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("Moneda") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }
        if (this.getPeriodicidad() == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("Periodicidad") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addAdvertencia(msg);
            return false;
        }
        if (this.getPlazo() == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("Plazo") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addAdvertencia(msg);
            return false;
        } else {
            if (this.getPlazo() == 0) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PlazoMayorCero");
                MuestraMensaje.addAdvertencia(msg);
                return false;
            }
        }
        if (this.getCapital() == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("Capital") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addAdvertencia(msg);
            return false;
        } else {
            if (this.getCapital().compareTo(new BigDecimal("0.00")) == 0) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CapitalMayorCero");
                MuestraMensaje.addAdvertencia(msg);
                return false;
            }
        }
        if (this.getTasaInteres() == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("TasaInteresMayorCero") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addAdvertencia(msg);
            return false;
        } else {
            if (this.getTasaInteres().compareTo(new BigDecimal("0.00")) == 0) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CapitalMayorCero");
                MuestraMensaje.addAdvertencia(msg);
                return false;
            }
        }
        if (this.itemsCuentaContraDpf.isEmpty()) {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DpfNoTieneCuentas");
            MuestraMensaje.addAdvertencia(msg);
            return false;
        } else {
            if (this.itemsCuentaContraDpf.get(0).getCuentaDebito() == null
                    || this.itemsCuentaContraDpf.get(0).getCuentaCredito() == null) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DpfNoTieneCuentasCompietas");
                MuestraMensaje.addAdvertencia(msg);
                return false;
            }
        }
        this.obtieneNombreSocio(false);
        this.validaSaldoCuentaDebito();
        if (tasaInteres.doubleValue() > TASA_MAXIMA.doubleValue()) {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModificacionTasaDPFValidaTasaAcumulada");
            return false;
        }
        return this.msg == null;
    }

    /**
     * Guarda el Deposito de Plazo Fijo.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void guardar(ActionEvent actionEvent) throws IOException {
        context = RequestContext.getCurrentInstance();
        if (this.validaGuardar()) {
            try {
                // Cargando la conexion de BD
                llamaSP.cargaConexion();
                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);
                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);
                this.fechaContrato = new java.sql.Timestamp(new Date().getTime());
                // Guardando el contrato del DPF
                llamaSP.setNombreSP("mks_dpfs.pkm_contrato_dpf.p_guarda");
                llamaSP.setNumeroParametros(38);
                llamaSP.setListParametrosEntrada(new ArrayList<>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.getMoneda().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.getCodigoPersonaContrato()});
                //<editor-fold defaultstate="collapsed" desc="VERIFICACION DE LA AGENCIA DEL SOCIO">                
                //Por Willan Jara 30-11-2017
                //Se valida si es el socio esta registrando el DPF en la misma Agencia que fue creado o en otra agencia
                if (Objects.equals(this.getSocioSel().getCodigoIfipAgencia().getCodigo(), ActivacionUsuario.getCodigoIfipAgencia())) {
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
                } else {
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", getSocioSel().getCodigoIfipAgencia().getCodigo()});
                }
                // </editor-fold>
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tasa", this.getCodigoTasa()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodicidad", this.getPeriodicidad().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_documento", this.getCodigoDocumento()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_documento", this.getNumeroDocumento()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_realizado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_contrato", this.fechaContrato});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_vencimiento", new java.sql.Timestamp(this.getFechaVencimiento().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_plazo", this.getPlazo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_plazo_en_meses", this.getPlazoMeses()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_plazo_dias", this.getPlazoDias()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", this.getCapital()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_tasa_interes", this.tasaInteres});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes", this.getInteres()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_porcentaje_retencion", this.getPorcentajeRetencion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_retencion", this.getRetencion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", this.getTotal()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_credito", this.itemsCuentaContraDpf.get(0).getCuentaCredito().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_debito", this.itemsCuentaContraDpf.get(0).getCuentaDebito().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tip_pro_deb", this.itemsCuentaContraDpf.get(0).getCuentaCredito().getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_de_socio", "S"});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_renovacion", "N"});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "V"});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_acredita_mensual", this.getAcreditacionMensual()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_renovacion_automatica", this.renovacionAutomatica});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_renovacion_con_interes", this.getConInteres()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_socio_beneficiario", this.getEsSocioBeneficiario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona_beneficiario", this.personaBeneficiario.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", licitudFondosModulo.getCodigo()});
                llamaSP.setListParametrosSalida(new ArrayList<>());
                llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
                llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
                llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
                llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
                // Invocando al SP
                llamaSP.invocaSP();
                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Encerando el codigo de formulario de licitud de fondos.
                    ActivacionUsuario.setCodigoFormularioLicitudFondos(null);
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();
                    // Obtencion de parametros de salida
                    this.msg = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
                    this.codigoFormulario = (llamaSP.getListResultado().get(1) != null) ? Long.parseLong(llamaSP.getListResultado().get(1).toString()) : null;
                    this.generaFormulario = Long.parseLong(llamaSP.getListResultado().get(2).toString());
                    this.codigoContrato = Long.parseLong(llamaSP.getListResultado().get(3).toString());
                    // Asignando el codigo de formulario
                    ActivacionUsuario.setCodigoFormularioLicitudFondos(codigoFormulario);
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    // <editor-fold defaultstate="collapsed" desc="ACCIONES NUEVA TASA DE APROBACION">
                    guardarAprobacionDpf(codigoContrato);
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="REGISTRO DE OFICINAS">
                    //Por Willan Jara 30 de Noviembre de 2017
                    guardaAgenciaOrigenDestino();
                    // </editor-fold>
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContratoDpfGrabado");
                    MuestraMensaje.addInformacion(msg);
                    // Llamando al Proceso de Licitud de Fondos
                    impresionFormularioLicitudFondos();
                } else {
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                }
            } catch (IOException | NumberFormatException ex) {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"contratoDpfController", "guardar", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     * Llama al diaglo de la impresion del formulario en caso de que exista
     *
     * @throws IOException
     */
    public void impresionFormularioLicitudFondos() throws IOException {
        context = RequestContext.getCurrentInstance();
        if (generaFormulario == 1L) {
            context = RequestContext.getCurrentInstance();
            // Buscando la transaccion en la parametrizacion de licitud de fondos.
            LicitudFondosFormulario lff = ejbFacadeLicitudFondosFormulario.find(codigoFormulario);
            List<LicitudFonCptoTran> listaLicitudFonCptoTran = ejbFacadeLicitudFonCptoTran.getByTipo(lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoConcepto(),
                    lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoMoneda(),
                    lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoTipoProducto(),
                    lff.getCodigoIfip(),
                    'N');
            if (listaLicitudFonCptoTran.size() == 1) {
                List<String> listaComponentesActualizar = new ArrayList<>();
                listaComponentesActualizar.add("FormularioLicitudFondosForm");
                context.update(listaComponentesActualizar);
                listaComponentesActualizar = new ArrayList<>();
                listaComponentesActualizar.add("ContratoDpfListForm");
                context.update(listaComponentesActualizar);
                // Iniciand formulario de licitud de fondos
                getLicitudFondosBean().setCodigoFormulario(this.codigoFormulario);
                getLicitudFondosBean().preparaLicitudFondos();
                getLicitudFondosBean().setLlamadoOtrasVentanas(true);
                getLicitudFondosBean().cargarListaDestinos('N');
                getLicitudFondosBean().cargarListaOrigenes('S');
                switch (listaLicitudFonCptoTran.get(0).getTipo()) {
                    case 'A':
                        getLicitudFondosBean().setDeshabilitaComboDestino(false);
                        getLicitudFondosBean().setDeshabilitaComboOrigen(false);
                        break;
                    case 'O':
                        getLicitudFondosBean().setDeshabilitaComboOrigen(false);
                        break;
                    default:
                        getLicitudFondosBean().setDeshabilitaComboDestino(false);
                        break;
                }
                getLicitudFondosBean().setListaComponentesActualizar(listaComponentesActualizar);
                getLicitudFondosBean().setDialogoMostrar("ImprimeComprobanteDialogo");
                context.execute("FormularioLicitudFondosDialogo.show()");
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteTransaccionLicitudFondos"));
                context.execute("ImprimeComprobanteDialogo.show()");
            }
        } else {
            context.execute("ImprimeComprobanteDialogo.show()");
        }

        this.init();
    }

    /**
     * Obtiene el porcentaje de retencion.
     *
     * @throws IOException
     */
    private void obtientePorcentajeRetencion() throws IOException {
        urlSinPermisos = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        ParametroGeneralDpfIfip parametroGeneralDpf;
        if (this.controladaPorOc.equals("N")) {
            parametroGeneralDpf = this.ejbFacadeParametroGeneralDpfIfip.find(new ParametroGeneralDpfIfipPK((long) 1, ActivacionUsuario.getCodigoIfip()));
        } else {
            parametroGeneralDpf = this.ejbFacadeParametroGeneralDpfIfip.find(new ParametroGeneralDpfIfipPK((long) 2, ActivacionUsuario.getCodigoIfip()));
        }
        if (parametroGeneralDpf != null) {
            this.porcentajeRetencion = parametroGeneralDpf.getValorNumerico();
            if (porcentajeRetencion == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteDefinidoPorcentajeRetencion"));
                Sesion.redireccionaPagina(urlSinPermisos);
            }
        }
    }

    /**
     * Obtiene el numero del documento del DPF.
     */
    public void obtieneDocumentoPdf() {
        this.msg = null;
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            this.fechaContrato = new java.sql.Timestamp(new Date().getTime());
            llamaSP.setNombreSP("mks_dpfs.pkg_talonario_doc_dpf_det.p_obtiene_siguiente_documento");
            llamaSP.setNumeroParametros(6);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", (this.getSelected() == null) ? this.getMoneda().getCodigo() : this.getSelected().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                this.numeroDocumento = llamaSP.getListResultado().get(0).toString();
                this.codigoDocumento = Long.parseLong(llamaSP.getListResultado().get(1).toString());
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "obtieneDocumentosDpf", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Calcula los dias totales del plazo
     *
     * @throws java.io.IOException
     */
    public void calculaPlazoDpf() throws IOException {
        this.msg = null;
        if (this.getPlazo() != null) {
            if (this.getPeriodicidad() != null) {
                this.setPlazoMeses(this.getPlazo() * this.getPeriodicidad().getEquivalenciaMeses());
                this.setPlazoDias(this.getPlazo() * this.getPeriodicidad().getEquivalenciaDias());
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(new Date()); // Configuramos la fecha que se recibe            
                calendario.add(Calendar.DAY_OF_MONTH, this.getPlazoDias().intValue());
                this.setFechaVencimiento(calendario.getTime());
                this.calculaTotalesDpf();
            }
        }
    }

    public void calculaPlazoDiasDpf() throws IOException {
        if (this.getPlazoDias() != null) {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(new Date()); // Configuramos la fecha que se recibe
            calendario.add(Calendar.DAY_OF_MONTH, this.getPlazoDias().intValue());
            this.setFechaVencimiento(calendario.getTime());
            this.plazoMeses = this.plazoDias / 30;
            this.calculaTotalesDpf();
        }
    }

    /**
     * Coloca la Periodicidad
     *
     * @throws java.io.IOException
     */
    public void periodicidadDpf() throws IOException {
        try {
            this.setPeriodicidad(periodicidad);
            this.calculaPlazoDiasDpf();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "periodicidadDpf", CapturaError.getErrorException(e)});
        }
    }

    /**
     * Calcula los totales para el DPF; aqui se calculan los valores y la fecha
     * de Vencimiento.
     *
     * @throws java.io.IOException
     */
    public void calculaTotalesDpf() throws IOException {
        try {
            this.msg = null;
            this.setTasaInteres(null);
            this.setInteres(null);
            this.setPorcentajeRetencion(null);
            this.setRetencion(null);
            this.setTotal(null);
            this.setFechaVencimiento(null);
            if (this.getCapital() != null
                    && this.getPlazoDias() != null
                    && this.getMoneda() != null) {
                if (this.getCapital().compareTo(BigDecimal.ZERO) > 0 && this.getPlazo() > 0) {
                    // <editor-fold defaultstate="collapsed" desc="ACCIONES NUEVA TASA DE APROBACION">
                    try {
                        actualizarAprobacionMontoMinimo();
                        if (tasaAprobada) {
                            tasaInteres = valorNuevaTasa;
                            calculaContratoDpf();
                            return;
                        }
                    } catch (Exception e) {
                        return;
                    }
                    // </editor-fold>
                    listaTasaInteresDpfIfip = this.ejbFacadeTasaInteresProDpfMon.getItemsContratoDpf(ActivacionUsuario.getCodigoIfip(),
                            this.getPlazoDias(),
                            this.getMoneda().getCodigo(),
                            this.getCapital(),
                            'N');
                    if (!listaTasaInteresDpfIfip.isEmpty()) {
                        if (listaTasaInteresDpfIfip.size() == 1) {
                            // <editor-fold defaultstate="collapsed" desc="ACCIONES NUEVAS TASAS CUANDO SUPERA LOS 181 DIAS">
                            if(getPlazoDias()>=181){
                                tasaInteres = listaTasaInteresDpfIfip.get(0).getTasa().getValor().subtract(new BigDecimal(0.25));
                            }else{
                                tasaInteres = listaTasaInteresDpfIfip.get(0).getTasa().getValor();
                            }
                            // </editor-fold>
                            calculaContratoDpf();
                        }
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistaTasaParaDPf");
                        MuestraMensaje.addAdvertencia(msg);
                    }
                }
            }
            if(tasaInteres==null){
                tasaInteres=BigDecimal.ZERO;
            }
            tasaMinima = tasaInteres;
            if (!tasaAprobada) {
                if (tasaInteresMaxima != null && tasaInteres != null) {
                    tasaInteresMaxima = tasaInteres.add(INCREMENTO_TASA);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "calculaTotalesDpf", CapturaError.getErrorException(e)});
        }

    }

    /**
     * Calcula el valor del contrato del dpf
     */
    private void calculaContratoDpf() {
        try {
            this.verificarPersonaInst();
            this.obtientePorcentajeRetencion();
            this.codigoTasa = listaTasaInteresDpfIfip.get(0).getCodigo();
            interes = this.getCapital().multiply(tasaInteres);
            interes = interes.divide(new BigDecimal("36000"), 2, RoundingMode.HALF_UP);
            interes = interes.multiply(new BigDecimal(String.valueOf(this.getPlazoDias())));
            this.setInteres(interes);
            if (this.getPlazoDias() > 360) {
                this.setPorcentajeRetencion(new BigDecimal("0.00"));
                this.setRetencion(new BigDecimal("0.00"));
            } else {
                this.setRetencion(this.getInteres().multiply(this.porcentajeRetencion.divide(new BigDecimal("100"))));
            }
            this.setTotal(this.getInteres().add(this.getCapital()).subtract(this.getRetencion()));
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(new Date()); // Configuramos la fecha que se recibe
            calendario.add(Calendar.DAY_OF_MONTH, this.getPlazoDias().intValue());
            this.setFechaVencimiento(calendario.getTime());
            validaSaldoCuentaDebito();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "calculaContratoDpf", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaAcreditacionMensual() {
        this.conInteres = "N";
        this.renovacionAutomatica = "S";
        if (this.acreditacionMensual.equals("S")) {
            this.deshabilitaConInteres = true;
            this.deshabilitaRenovacionAutomatica = true;
        } else {
            this.deshabilitaRenovacionAutomatica = false;
            this.deshabilitaConInteres = false;
        }
    }

    /**
     * Permite deshabilitar los itereses cuando la renovacion es automatica
     *
     */
    public void cambiaAcreditacionMensualSinInteres() {
        if (this.renovacionAutomatica.equals("S")) {
            this.conInteres = "N";
            this.deshabilitaConInteres = true;
        } else {
            this.conInteres = "S";
            this.deshabilitaConInteres = false;
        }
    }

    /**
     * Cambia la tasa de interes
     */
    public void cambiaTasaInteres() {
        if (listaTasaInteresDpfIfip != null) {
            if (tasaInteres == null) {
                this.tasaInteres = listaTasaInteresDpfIfip.get(0).getTasa().getValor();
            }
            this.calculaContratoDpf();
        }
    }

    /**
     * CUANDO CAMBIA EL COMBO DE SI EL SOCIO ES BENEFICIARIO
     */
    public void cambiaSocioBeneficiario() {
        setDeshabilidaCodigoPersonaBeneficiario(true);
        if (this.getEsSocioBeneficiario().equals("N")) {
            this.setDeshabilidaCodigoPersonaBeneficiario(false);
            this.setPersonaBeneficiario(null);
            this.identificacionPersonaBeneficiario = null;
        } else {
            if (this.getSocioSel() != null) {
                try {
                    if (getSocioSel().getCodigoPersona() != null) {
                        setPersonaBeneficiario(getSocioSel().getCodigoPersona());
                        if (getPersonaBeneficiario() != null) {
                            if (!Objects.equals(getPersonaBeneficiario().getCodigo(), getSocioSel().getCodigoPersona().getCodigo())) {
                                validacionUAF(getSocioSel().getCodigoPersona());
                            } else {
                                this.setDesactivaBotonGuardar(false);
                            }
                        }
                        this.setPersonaBeneficiario(this.getSocioSel().getCodigoPersona());
                    }
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"contratoDpfController", "cambiaSocioBeneficiario", CapturaError.getErrorException(e)});
                }
                if (getPersonaBeneficiario() != null) {
                    this.identificacionPersonaBeneficiario = getPersonaBeneficiario().getIdentificacion();
                }
                this.setDeshabilidaCodigoPersonaBeneficiario(true);
            }
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

    /**
     * Imprime el Documento del Contrato DPF Seleccionado
     */
    public void imprime() {
        boolean imprime = true;
        context = RequestContext.getCurrentInstance();
        if (this.getSelected().getEstado() == 'V') {
            this.numeroDocumento = null;
            this.codigoDocumento = null;
            context = RequestContext.getCurrentInstance();
            List<DocumentoContratoDpf> listaDcumentoContratoDpfs = ejbFacadeDocumentoContratoDpf.getItemsCodigoContratoEstado(this.getSelected().getContratoDpfPK().getCodigoIfip(),
                    this.getSelected().getContratoDpfPK().getCodigo(),
                    'A');
            this.codigoContrato = this.getSelected().getContratoDpfPK().getCodigo();
            if (listaDcumentoContratoDpfs.isEmpty()) {
                this.obtieneDocumentoPdf();
                try {
                    llamaSP.cargaConexion();
                    llamaSP.setCerrarConexion(false);
                    llamaSP.autoCommit(false);
                    Timestamp fechaAsignacion = new java.sql.Timestamp(new Date().getTime());
                    llamaSP.setNombreSP("mks_dpfs.pkm_contrato_dpf.p_asigna_documento_dpf");
                    llamaSP.setNumeroParametros(5);
                    llamaSP.setListParametrosEntrada(new ArrayList<>());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.codigoContrato});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_documento", this.codigoDocumento});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_asignado_por", ActivacionUsuario.getCodigoUsuario()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_asignacion", fechaAsignacion});
                    llamaSP.setListParametrosSalida(new ArrayList<>());
                    llamaSP.invocaSP();
                    if (llamaSP.isEjecucionCorrecta()) {
                        llamaSP.commit();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                    } else {
                        llamaSP.rollback();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                        imprime = false;
                    }
                } catch (Exception ex) {
                    context.execute("procesandoDlg.hide()");
                    imprime = false;
                    MuestraMensaje.addErrorCapaControl();
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"contratoDpfController", "guardar", CapturaError.getErrorException(ex)});
                }
            } else if (listaDcumentoContratoDpfs.size() > 1) {
                imprime = false;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContratoTieneMasUnDocumento");
            } else {
                this.numeroDocumento = listaDcumentoContratoDpfs.get(0).getTalonarioDocumentoDpfDet().getNumero();
            }
        } else {
            imprime = false;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContratoDebeEstarVigente");
        }
        if (imprime) {
            context.execute("procesandoDlg.hide()");
            context.execute("ImprimeComprobanteDialogo.show()");
        } else {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    /**
     *
     * Metodo modificado por Willan Jara 22-11-2017 Se agrega una linea que
     * imprima varias paginas en un solo reporte
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimir(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap());
        getGeneraReporte().getParametros().put("codigoContrato", this.codigoContrato);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());
        //Declaramos los reportes los mismos deben estar compilados en el mismo directorio
        String nombreReporte1 = "contratoDpf";
        String nombreReporte2 = "contratoDpfCopia";
        getGeneraReporte().exportaVariasPagias("/financiero/dpfs/contrato/reporte/", Arrays.asList(nombreReporte1, nombreReporte2), nombreReporte1 + String.valueOf(this.numeroDocumento) + ".pdf",
                "PDF", externalContext, facesContext);
        getGeneraReporte().exportaVariasPagias("/financiero/dpfs/contrato/reporte/", Arrays.asList(new String[]{nombreReporte1, nombreReporte2}), nombreReporte1 + String.valueOf(this.numeroDocumento) + ".pdf", "PDF", externalContext, facesContext);
    }

    //-------------------------------------------------------------------------
    // BUSQUEDA Y SELECCION DEL SOCIO AL USAR LISTA DE VALORES
    /**
     * Obtiene el nombre del Socio
     */
    private void obtieneNombreSocio(boolean esBusqueda) {
        this.msg = null;
        if (esBusqueda) {
            this.setSocioSel(this.ejbFacadeSocio.find(new SocioPK(this.codigoSocio, ActivacionUsuario.getCodigoIfip())));
            if (getSocioSel() == null) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste");
                this.nombreSocio = null;
                MuestraMensaje.addAdvertencia(msg);
            } else {
                if (getSocioSel().getCodigoPersona() != null) {
                    if (!validacionUAF(getSocioSel().getCodigoPersona())) {
                        return;
                    }
                }
                if (getSocioSel().getCodigoPersona().getFechaCaducidadIdentificacion() == null) {
                    this.setNombreSocioContrato("");
                    msg = (ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioSinCaducidad"));
                    MuestraMensaje.addAdvertencia(msg);
                    return;
                }
                if (Validaciones.validaFechaMenorHoy(getSocioSel().getCodigoPersona().getFechaCaducidadIdentificacion())) {
                    this.setNombreSocioContrato("");
                    msg = (ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioCaducada"));
                    MuestraMensaje.addAdvertencia(msg);
                    return;
                }
                {
                    this.setNombreSocio(this.getSocioSel().getCodigoPersona().getNombreCompleto());
                    this.codigoPersona = this.socioSel.getCodigoPersona().getCodigo();
                }
            }
        } else {
            if (this.codigoSocioContrato != null) {
                this.setSocioSel(this.ejbFacadeSocio.find(new SocioPK(this.codigoSocioContrato, ActivacionUsuario.getCodigoIfip())));
                if (getSocioSel() == null) {
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste");
                    this.nombreSocioContrato = null;
                    MuestraMensaje.addAdvertencia(msg);
                } else {
                    if (getSocioSel().getCodigoPersona().getFechaCaducidadIdentificacion() == null) {
                        msg = (ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioSinCaducidad"));
                        MuestraMensaje.addAdvertencia(msg);
                        return;
                    }
                    if (Validaciones.validaFechaMenorHoy(getSocioSel().getCodigoPersona().getFechaCaducidadIdentificacion())) {
                        msg = (ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioCaducada"));
                        MuestraMensaje.addAdvertencia(msg);
                        return;
                    }
                    //Por Willan Jara 30 de noviembre de 2017
                    //Proceso de control para que un socio registrado en una agencia obtenga un servicio de DPF en la misma agencia
                    //Se modifica para que un socio registrado en cualquier agencia pueda obtener un DPF en otra       
                    {
                        this.codigoPersonaContrato = this.getSocioSel().getCodigoPersona().getCodigo();
                        this.nombreSocioContrato = this.getSocioSel().getCodigoPersona().getNombreCompleto();
                        this.buscarMoraSocio(this.getSocioSel().getSocioPK().getCodigo());
                        this.buscarMoraConyuge(this.getSocioSel().getSocioPK().getCodigo());
                        this.buscarMoraGarante(this.getSocioSel().getCodigoPersona().getCodigo());
                        this.verificarPersonaInst();
                    }
                }
            } else {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste");
            }
        }
    }

    /**
     *
     */
    public void obtieneNombreSocioBusqueda() {
        this.obtieneNombreSocio(true);
        this.obtieneDpfs();
    }

    /**
     * 
     */
    public void verificarPersonaInst() {
        this.controladaPorOc = "N";
        if (this.getSocioSel().getCodigoPersona().getCodigoTipoPersona().getEsParaInstitucion() == 'S') {
            PersonaInstitucion personaInstitucion = this.ejbFacadePerIns.find(this.getSocioSel().getCodigoPersona().getCodigo());
            if (personaInstitucion != null) {
                this.controladaPorOc = String.valueOf(personaInstitucion.getControladaOc());
            } else {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaInstitucionNoRegistrada");
                MuestraMensaje.addError(msg);
            }
        }
    }

    /**
     * Obtiene el nombre del socio que va a contratar el DPF.
     *
     * @throws java.io.IOException
     */
    public void obtieneNombreSocioContrato() throws IOException {
        try {
            this.obtieneNombreSocio(false);
            this.esSocioBeneficiario = "S";
            this.cambiaSocioBeneficiario();
            this.obtieneCuentasSocio();
            this.calculaTotalesDpf();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "obtieneNombreSocioContrato", CapturaError.getErrorException(e)});
        }
    }

    /**
     * 
     * @param codigoSocio 
     */
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
    
    /**
     * 
     * @param codigoSocio 
     */
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

    /**
     * 
     * @param valor
     * @return 
     */
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

    /**
     * 
     * @param codigoPrsona 
     */
    public void buscarMoraGarante(Long codigoPrsona) {
        ////VERIFICACIONES DE MORA
        List<Solicitud> solicitudes = ejbFacadeGaranteCredito.getItemPersonaIfipVigente(codigoPrsona, ActivacionUsuario.getCodigoIfip(), 'S');
        solicitudes.stream().map((sol) -> ejbFacadeSolicitudDetalle.getItemSolicitudCreditoNumeroIfipMora(sol.getSolicitudPK().getNumero(), ActivacionUsuario.getCodigoIfip(), new BigDecimal(0))).filter((moraSocio) -> (!moraSocio.isEmpty())).forEachOrdered((moraSocio) -> {
            String aux;
            for (SolicitudDetalle s : moraSocio) {
                List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                double deuda = 0;
                for (CalculoCuotaPagar c : pendientes) {
                    deuda = deuda + c.getTotalPago().doubleValue();
                }
                Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), 'P');
                aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioGaranteMora") + "\n";
                aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", s.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(s.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", s.getSolicitud().getSocio().getCodigoPersona().getNombreCompleto()).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));;
                MuestraMensaje.addInformacion(aux);
            }
        });
    }

    /**
     * Prepara la busqueda de los socios
     *
     *
     * @param actionEvent
     */
    public void preparaBusqueaSociosLista(ActionEvent actionEvent) {
        this.preparaBusquedaSocio();
        this.esBusquedaSocioContrato = false;
    }

    /**
     * Prepara la busqueda de los socios en el contrato
     *
     *
     * @param actionEvent
     */
    public void preparaBusquedaSociosContrato(ActionEvent actionEvent) {
        nombreSocioContrato = null;
        esBusquedaSocioContrato = true;
        nombreSocioBusqueda = null;
        itemsCuentaSocio = null;
        itemsSocio = null;
        mensajeCriterio = null;
        setDesactivaBotonGuardar(true);
    }

    /**
     * Encera todo lo que se refiere a la busque del socio.
     */
    private void preparaBusquedaSocio() {
        this.itemsContratoDpf = null;
        this.itemsSocio = null;
        this.codigoPersona = null;
        this.nombreSocioBusqueda = null;
        this.setMensajeCriterio(null);
        this.setSocioSel(null);
        this.setCodigoPersona(null);
        this.setSelected(null);
        this.codigoSocio = null;
        this.nombreSocio = null;
    }

    /**
     * Cuando Selecciona el Socio
     */
    public void seleccionaSocio() {
        if (socioSel != null) {
            if (getSocioSel().getCodigoPersona().getFechaCaducidadIdentificacion() == null) {
                msg = (ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioSinCaducidad"));
                MuestraMensaje.addAdvertencia(msg);
                seleccionSocio();
                return;
            }
            if (Validaciones.validaFechaMenorHoy(getSocioSel().getCodigoPersona().getFechaCaducidadIdentificacion())) {
                msg = (ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionSocioCaducada"));
                MuestraMensaje.addAdvertencia(msg);
                seleccionSocio();
                return;
            }
            if (!this.esBusquedaSocioContrato) {
                //Por Willan Jara 30 de noviembre de 2017
                //Proceso de control para que un socio registrado en una agencia obtenga un servicio de DPF en la misma agencia
                //Se modifica para que un socio registrado en cualquier agencia pueda obtener un DPF en otra       
                {
                    this.codigoPersona = this.socioSel.getCodigoPersona().getCodigo();
                    this.nombreSocio = this.socioSel.getCodigoPersona().getNombreCompleto();
                    this.codigoSocio = this.socioSel.getSocioPK().getCodigo();
                    this.obtieneDpfs();
                }
            } else {
                //Por Willan Jara 30 de noviembre de 2017
                //Proceso de control para que un socio registrado en una agencia obtenga un servicio de DPF en la misma agencia
                //Se modifica para que un socio registrado en cualquier agencia pueda obtener un DPF en otra       
                {
                    this.codigoSocioContrato = this.socioSel.getSocioPK().getCodigo();
                    this.codigoPersonaContrato = this.getSocioSel().getCodigoPersona().getCodigo();
                    this.nombreSocioContrato = this.getSocioSel().getCodigoPersona().getNombreCompleto();
                    this.buscarMoraSocio(this.getSocioSel().getSocioPK().getCodigo());
                    this.buscarMoraConyuge(this.getSocioSel().getSocioPK().getCodigo());
                    this.buscarMoraGarante(this.getSocioSel().getCodigoPersona().getCodigo());
                    // Actualizando componentes de la vista
                    context = RequestContext.getCurrentInstance();
                    List<String> listaComponentesActualizar = new ArrayList<>();
                    listaComponentesActualizar.add("ContratoDpfCreateForm:socio");
                    listaComponentesActualizar.add("ContratoDpfCreateForm:nombrePersona");
                    listaComponentesActualizar.add("ContratoDpfCreateForm:tipoItem");
                    listaComponentesActualizar.add("ContratoDpfCreateForm:cuenta");
                    listaComponentesActualizar.add("ContratoDpfCreateForm:cuentasTbl");
                    listaComponentesActualizar.add("ContratoDpfCreateForm");
                    this.esSocioBeneficiario = "S";
                    this.cambiaSocioBeneficiario();
                    context.update(listaComponentesActualizar);
                    this.obtieneCuentasSocio();
                }
            }
            seleccionSocio();
        }
    }

    /**
     *
     */
    public void seleccionSocio() {
        try {
            if (msg == null) {
                this.setNombreSocioContrato(socioSel.getCodigoPersona().getNombreCompleto());
                this.setCodigoSocioContrato(socioSel.getSocioPK().getCodigo());
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "seleccionSocio", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Obtiene los Socios para la Seleccion.
     */
    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(this.getNombreSocioBusqueda(), 6)) {
                this.itemsSocio = this.ejbFacadeSocio.getItemsNombresIndicaActivo(this.getNombreSocioBusqueda(), ActivacionUsuario.getCodigoIfip(), 'S');
            } else {
                this.setMensajeCriterio(Validaciones.msg);
                this.setItemsSocio(null);
                this.setSocioSel(null);
                this.setSelected(null);
                this.setCodigoPersona(null);
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    //---------------------------------------------------------------------------
    // CUENTAS SOCIO
    /**
     * Obtiene las cuentas del socio par asignarlas del dpf.
     */
    public void obtieneCuentasSocio() {
        if (this.getMoneda() != null) {
            this.itemsCuentaSocio = this.ejbFacadeCuenta.getItemsProductoMonedaPuedeReactivar(this.codigoSocioContrato, ActivacionUsuario.getCodigoIfip(), Long.parseLong("2"), this.getMoneda().getCodigo(), 'S');
            this.cuentaCombo = null;
            this.tipoCuentaSel = null;
            this.itemsCuentaContraDpf = new ArrayList<>();
            this.obtieneDocumentoPdf();
        } else {
            this.itemsCuentaSocio = null;

        }
    }

    /**
     * Verfica la selección de la cuenta
     */
    public void verificaSeleccionCuenta() {
        this.msg = null;
        if (cuentaCombo != null) {
            if (String.valueOf(this.cuentaCombo.getCodigoEstado().getIndicaActiva()).equals("N")) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaNoActiva");
                MuestraMensaje.addAdvertencia(msg);
            } else {
                if (this.tipoCuentaSel != null) {
                    if (tipoCuentaSel.equals("D")) {
                        if (this.cuentaCombo.getSaldoDisponible().compareTo(this.getCapital()) < 0) {
                            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
                            MuestraMensaje.addAdvertencia(msg);
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     */
    private void validaSaldoCuentaDebito() {
        this.msg = null;
        if (!this.itemsCuentaContraDpf.isEmpty()) {
            if (this.itemsCuentaContraDpf.get(0).getCuentaDebito() != null) {
                if (this.itemsCuentaContraDpf.get(0).getCuentaDebito().getSaldoDisponible().compareTo(this.getCapital()) < 0) {
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
                    MuestraMensaje.addAdvertencia(msg);
                }
            }
        }
    }

    /**
     * Agrega la cuenta del socio para el DPF.
     */
    public void agregaCuentaDpf() {
        this.verificaSeleccionCuenta();
        if (this.msg == null) {
            if (this.cuentaCombo != null && this.tipoCuentaSel != null) {
                if (this.itemsCuentaContraDpf.isEmpty()) {
                    this.itemsCuentaContraDpf.add(new CuentaContratoDpf());
                }
                if (tipoCuentaSel.equals("D")) {
                    itemsCuentaContraDpf.get(0).setCuentaDebito(cuentaCombo);
                }
                if (tipoCuentaSel.equals("C")) {
                    itemsCuentaContraDpf.get(0).setCuentaCredito(cuentaCombo);
                }
            }
        }
    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    public List<Periodicidad> getItemsPeriodicidad() {
        return this.ejbFacadePeriodicidad.getItemsEsParaDpfEliminado('S', 'N');
    }
    //FIN DE LISTAS QUE NO SON DE REFRESCAMIENTO
    //---------------------------------------------------------------------------

    public List<Socio> getItemsSocio() {
        return itemsSocio;
    }

    public void setItemsSocio(List<Socio> itemsSocio) {
        this.itemsSocio = itemsSocio;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getMensajeCriterio() {
        return mensajeCriterio;
    }

    public void setMensajeCriterio(String mensajeCriterio) {
        this.mensajeCriterio = mensajeCriterio;
    }

    public String getNombreSocioBusqueda() {
        return nombreSocioBusqueda;
    }

    public void setNombreSocioBusqueda(String nombreSocioBusqueda) {
        this.nombreSocioBusqueda = nombreSocioBusqueda;
    }

    public Socio getSocioSel() {
        return socioSel;
    }

    public void setSocioSel(Socio socioSel) {
        this.socioSel = socioSel;
    }

    public Long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public List<ContratoDpf> getItemsContratoDpf() {
        return itemsContratoDpf;
    }

    public void setItemsContratoDpf(List<ContratoDpf> itemsContratoDpf) {
        this.itemsContratoDpf = itemsContratoDpf;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres!=null?tasaInteres:(this.tasaInteres == null? BigDecimal.ONE:this.tasaInteres);
    }

    public List<CuentaContratoDpf> getItemsCuentaContraDpf() {
        return itemsCuentaContraDpf;
    }

    public void setItemsCuentaContraDpf(List<CuentaContratoDpf> itemsCuentaContraDpf) {
        this.itemsCuentaContraDpf = itemsCuentaContraDpf;
    }

    public Cuenta getCuentaCombo() {
        return cuentaCombo;
    }

    public void setCuentaCombo(Cuenta cuentaCombo) {
        this.cuentaCombo = cuentaCombo;
    }

    public List<Cuenta> getItemsCuentaSocio() {
        return itemsCuentaSocio;
    }

    public void setItemsCuentaSocio(List<Cuenta> itemsCuentaSocio) {
        this.itemsCuentaSocio = itemsCuentaSocio;
    }

    public String getTipoCuentaSel() {
        return tipoCuentaSel;
    }

    public void setTipoCuentaSel(String tipoCuentaSel) {
        this.tipoCuentaSel = tipoCuentaSel;
    }

    public boolean isEsBusquedaSocioContrato() {
        return esBusquedaSocioContrato;
    }

    public void setEsBusquedaSocioContrato(boolean esBusquedaSocioContrato) {
        this.esBusquedaSocioContrato = esBusquedaSocioContrato;
    }

    public Long getCodigoSocioContrato() {
        return codigoSocioContrato;
    }

    public void setCodigoSocioContrato(Long codigoSocioContrato) {
        this.codigoSocioContrato = codigoSocioContrato;
    }

    public Long getCodigoPersonaContrato() {
        return codigoPersonaContrato;
    }

    public void setCodigoPersonaContrato(Long codigoPersonaContrato) {
        this.codigoPersonaContrato = codigoPersonaContrato;
    }

    public BigDecimal getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    public void setPorcentajeRetencion(BigDecimal porcentajeRetencion) {
        this.porcentajeRetencion = porcentajeRetencion;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Long getPlazo() {
        return plazo;
    }

    public void setPlazo(Long plazo) {
        this.plazo = plazo;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public String getNombreSocioContrato() {
        return nombreSocioContrato;
    }

    public void setNombreSocioContrato(String nombreSocioContrato) {
        this.nombreSocioContrato = nombreSocioContrato;
    }

    public BigDecimal getRetencion() {
        return retencion;
    }

    public void setRetencion(BigDecimal retencion) {
        this.retencion = retencion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(Long plazoDias) {
        this.plazoDias = plazoDias!=null?plazoDias:(this.plazoDias==null?0:this.plazoDias);
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getAcreditacionMensual() {
        return acreditacionMensual;
    }

    public void setAcreditacionMensual(String acreditacionMensual) {
        this.acreditacionMensual = acreditacionMensual;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Long getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(Long codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public Long getCodigoTasa() {
        return codigoTasa;
    }

    public void setCodigoTasa(Long codigoTasa) {
        this.codigoTasa = codigoTasa;
    }

    public Long getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Long plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

    public String getConInteres() {
        return conInteres;
    }

    public void setConInteres(String conInteres) {
        this.conInteres = conInteres;
    }

    public String getRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    public void setRenovacionAutomatica(String renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    public boolean isDeshabilitaConInteres() {
        return deshabilitaConInteres;
    }

    public void setDeshabilitaConInteres(boolean deshabilitaConInteres) {
        this.deshabilitaConInteres = deshabilitaConInteres;
    }

    public boolean isDeshabilitaRenovacionAutomatica() {
        return deshabilitaRenovacionAutomatica;
    }

    public void setDeshabilitaRenovacionAutomatica(boolean deshabilitaRenovacionAutomatica) {
        this.deshabilitaRenovacionAutomatica = deshabilitaRenovacionAutomatica;
    }

    public String getControladaPorOc() {
        return controladaPorOc;
    }

    public void setControladaPorOc(String controladaPorOc) {
        this.controladaPorOc = controladaPorOc;
    }

    public boolean isDeshabilitaControladaPorOc() {
        return deshabilitaControladaPorOc;
    }

    public void setDeshabilitaControladaPorOc(boolean deshabilitaControladaPorOc) {
        this.deshabilitaControladaPorOc = deshabilitaControladaPorOc;
    }

    public boolean isDeshabilitaTasa() {
        return deshabilitaTasa;
    }

    public void setDeshabilitaTasa(boolean deshabilitaTasa) {
        this.deshabilitaTasa = deshabilitaTasa;
    }

    public String getEsSocioBeneficiario() {
        return esSocioBeneficiario;
    }

    public void setEsSocioBeneficiario(String esSocioBeneficiario) {
        this.esSocioBeneficiario = esSocioBeneficiario;
    }

    public String getIdentificacionPersonaBeneficiario() {
        return identificacionPersonaBeneficiario;
    }

    public void setIdentificacionPersonaBeneficiario(String identificacionPersonaBeneficiario) {
        this.identificacionPersonaBeneficiario = identificacionPersonaBeneficiario;
    }

    public List<Persona> getItemsPersona() {
        return itemsPersona;
    }

    public void setItemsPersona(List<Persona> itemsPersona) {
        this.itemsPersona = itemsPersona;
    }

    public boolean isDeshabilidaCodigoPersonaBeneficiario() {
        return deshabilidaCodigoPersonaBeneficiario;
    }

    public void setDeshabilidaCodigoPersonaBeneficiario(boolean deshabilidaCodigoPersonaBeneficiario) {
        this.deshabilidaCodigoPersonaBeneficiario = deshabilidaCodigoPersonaBeneficiario;
    }

    public Persona getPersonaBeneficiario() {
        return personaBeneficiario;
    }

    public void setPersonaBeneficiario(Persona personaBeneficiario) {
        this.personaBeneficiario = personaBeneficiario;
    }

    public Persona getPersonaBeneficiarioSel() {
        return personaBeneficiarioSel;
    }

    public void setPersonaBeneficiarioSel(Persona personaBeneficiarioSel) {
        this.personaBeneficiarioSel = personaBeneficiarioSel;
    }

    public String getNombrePersonaBusqueda() {
        return nombrePersonaBusqueda;
    }

    public void setNombrePersonaBusqueda(String nombrePersonaBusqueda) {
        this.nombrePersonaBusqueda = nombrePersonaBusqueda;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public LicitudFondosBean getLicitudFondosBean() {
        return licitudFondosBean;
    }

    public void setLicitudFondosBean(LicitudFondosBean licitudFondosBean) {
        this.licitudFondosBean = licitudFondosBean;
    }

    // <editor-fold defaultstate="collapsed" desc="NUEVA TASA DE APROBACION">
    public boolean isTasaAprobada() {
        return tasaAprobada;
    }

    public void setTasaAprobada(boolean tasaAprobada) {
        this.tasaAprobada = tasaAprobada;
    }

    public BigDecimal getValorNuevaTasa() {
        return valorNuevaTasa;
    }

    public void setValorNuevaTasa(BigDecimal valorNuevaTasa) {
        this.valorNuevaTasa = valorNuevaTasa;
    }

    // <editor-fold defaultstate="collapsed" desc="ACCIONES NUEVA TASA DE APROBACION">
    /**
     *
     * Metodo que permite actualizar los campos que tiene que ver con una nueva
     * tasa de aprobacion
     *
     */
    public void actualizarAprobacionMontoMinimo() {
        try {
            if (nuevaTasa == null || capital == null || plazoDias == null) {
                return;
            }
            TasaIntenresProductoDPFMontoDetalle tasaIntenresProductoDPFMontoDetalle = nuevaTasa.buscarPorPersonaMontoFecha(personaBeneficiario, capital, new java.util.Date(), plazoDias);
            if (tasaIntenresProductoDPFMontoDetalle != null) {
                tasaAprobada = tasaIntenresProductoDPFMontoDetalle.getEsAprobado() == 'S';
                if (tasaAprobada) {
                    tasaMinima = tasaInteresMaxima = tasaInteres = valorNuevaTasa = tasaIntenresProductoDPFMontoDetalle.getTasaAcumulada();
                    if (listaTasaInteresDpfIfip != null) {
                        if (listaTasaInteresDpfIfip.isEmpty()) {
                            listaTasaInteresDpfIfip.add(tasaIntenresProductoDPFMontoDetalle.getRasaInteresProDpfMon());
                        }
                    } else {
                        listaTasaInteresDpfIfip = new ArrayList<>();
                        listaTasaInteresDpfIfip.add(tasaIntenresProductoDPFMontoDetalle.getRasaInteresProDpfMon());
                    }
                    calculaContratoDpf();
                } else {
                    tasaMinima = valorNuevaTasa = tasaInteres;
                }
            } else {
                valorNuevaTasa = BigDecimal.ZERO;
                tasaAprobada = false;
                deshabilitaTasa = false;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "actualizarAprobacionMontoMinimo 1", CapturaError.getErrorException(e)});
        }
        try {
            cambiaTasaInteres();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"contratoDpfController", "actualizarAprobacionMontoMinimo 2", CapturaError.getErrorException(e)});
        }
    }

    /**
     * Metodo que permite guardar el registro de la entidad
     * tasaIntenresProductoDPFMontoDetalle con el objetivo de que en la proxima
     * busquda no salgan los resultados que no son nulos
     *
     * @param codigoContrato
     */
    public void guardarAprobacionDpf(Long codigoContrato) {
        TasaIntenresProductoDPFMontoDetalle tasaIntenresProductoDPFMontoDetalle = nuevaTasa.buscarPorPersonaMontoFecha(personaBeneficiario, capital, new java.util.Date(), plazoDias);
        if (tasaIntenresProductoDPFMontoDetalle != null) {
            tasaAprobada = tasaIntenresProductoDPFMontoDetalle.getEsAprobado() == 'S';
            if (nuevaTasa != null && tasaAprobada) {
                ContratoDpfPK contratoDpfPK = new ContratoDpfPK();
                contratoDpfPK.setCodigo(codigoContrato);
                contratoDpfPK.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                ContratoDpf contratoDpf = ejbFacade.buscarPorCampo(contratoDpfPK);
                tasaIntenresProductoDPFMontoDetalle.setContratoDpf(contratoDpf);
                nuevaTasa.guardaOActualiza(tasaIntenresProductoDPFMontoDetalle);
            }
        }
    }

    // </editor-fold>
    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="REGISTRO DE OFICINAS ORIGEN DESTINO SOCIO">
    //Por Willan Jara 30 de noviembre de 2017
    //Proceso de control para que un socio registrado en una agencia obtenga un servicio de DPF en otra agencia
    //Se modifica para que un socio registrado en cualquier agencia pueda obtener un DPF en otra                
    /**
     * Metodo para registro de los DPFS cuyas oficinas de origen son diferentes
     * al destino
     */
    public void guardaAgenciaOrigenDestino() {
        String descripcion = "Registro del socio: " + getSocioSel().getCodigoPersona().getNombreCompleto() + ", oficina origen " + getSocioSel().getCodigoIfipAgencia().getNombre() + " oficina destino " + registroAgenciaSocioDPFFacade.buscarPorAgencia(ActivacionUsuario.getCodigoIfipAgencia()).getNombre();
        ContratoDpfPK contratoDpfPK = new ContratoDpfPK();
        contratoDpfPK.setCodigo(codigoContrato);
        contratoDpfPK.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
        ContratoDpf contratoDpf = ejbFacade.buscarPorCampo(contratoDpfPK);
        registroAgenciaSocioDPF = registroAgenciaSocioDPFFacade.buscarPorDPF(contratoDpf);
        if (registroAgenciaSocioDPF == null) {
            registroAgenciaSocioDPF = new RegistroAgenciaSocioDPF();
        }
        registroAgenciaSocioDPF.setDescripcion(descripcion);
        registroAgenciaSocioDPF.setCodigoDPF(contratoDpf.getContratoDpfPK().getCodigo());
        registroAgenciaSocioDPF.setCodigoIFIP(ActivacionUsuario.getCodigoIfip());
        registroAgenciaSocioDPF.setFechaRegistro(new java.util.Date());
        registroAgenciaSocioDPF.setCodigoAgenciaOrigen(getSocioSel().getCodigoIfipAgencia().getCodigo());
        registroAgenciaSocioDPF.setCodigoAgenciaRegistro(ActivacionUsuario.getCodigoIfipAgencia());
        registroAgenciaSocioDPF.setCodigoUsuarioRegistra(ActivacionUsuario.getUsuario().getCodigo());
        registroAgenciaSocioDPF.setContratoDpf(contratoDpf);
        registroAgenciaSocioDPFFacade.guardaOActualiza(registroAgenciaSocioDPF);
    }
    // </editor-fold>

    public Long getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(Long codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public BigDecimal getTasaInteresMaxima() {
        return tasaInteresMaxima;
    }

    public void setTasaInteresMaxima(BigDecimal tasaInteresMaxima) {
        this.tasaInteresMaxima = tasaInteresMaxima;
    }

    public BigDecimal getTasaInteresIncremento() {
        return tasaInteresIncremento;
    }

    public void setTasaInteresIncremento(BigDecimal tasaInteresIncremento) {
        this.tasaInteresIncremento = tasaInteresIncremento;
    }

    public void actualizaTasaMaxima() {
        cambiaTasaInteres();
    }

    public BigDecimal getTasaMinima() {
        return tasaMinima;
    }

    public void setTasaMinima(BigDecimal tasaMinima) {
        this.tasaMinima = tasaMinima;
    }

    public void validar() {

    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public boolean isDesactivaBotonGuardar() {
        return desactivaBotonGuardar;
    }

    public void setDesactivaBotonGuardar(boolean desactivaBotonGuardar) {
        this.desactivaBotonGuardar = desactivaBotonGuardar;
    }
}
