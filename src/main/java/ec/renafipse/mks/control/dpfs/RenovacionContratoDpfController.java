package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpf;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpfPK;
import ec.renafipse.mks.modelo.dpfs.ParametroGeneralDpfIfip;
import ec.renafipse.mks.modelo.dpfs.ParametroGeneralDpfIfipPK;
import ec.renafipse.mks.modelo.dpfs.RenovacionContratoDpf;
import ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.socios.PersonaInstitucion;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.negocio.dpfs.ContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.CuentaContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.ParametroGeneralDpfIfipFacade;
import ec.renafipse.mks.negocio.dpfs.RenovacionContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.TalonarioDocumentoDpfDetFacade;
import ec.renafipse.mks.negocio.dpfs.TasaInteresProDpfMonFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.socios.PersonaInstitucionFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
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

@ManagedBean(name = "renovacionContratoDpfController")
@ViewScoped
public class RenovacionContratoDpfController extends AbstractController<RenovacionContratoDpf> {

    @EJB
    private RenovacionContratoDpfFacade ejbFacade;

    @EJB
    private ContratoDpfFacade ejbFacadeContratoDpf;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private CuentaContratoDpfFacade ejbFacadeCuentaContratoDpf;

    @EJB
    private ParametroGeneralDpfIfipFacade ejbFacadeParametroGeneralDpfIfip;

    @EJB
    private TasaInteresProDpfMonFacade ejbFacadeTasaInteresProDpfMon;

    @EJB
    private TalonarioDocumentoDpfDetFacade ejbFacadeTalonarioDocumentoDpfDet;

    @EJB
    private ComputadorFacade ejbFacadeComputador;

    @EJB
    private PersonaInstitucionFacade ejbFacadePerIns;

    private LlamaSP llamaSP;

    private Long codigoSocio;
    private String nombreSocio;
    private String msg;
    private String mensajeCriterio;
    private String nombreSocioBusqueda;
    private Long codigoPersona;
    private char estado;

    private boolean esBusquedaSocioContrato;

    private RequestContext context;
    private String urlSinPermisos;

    // Variables del COntrato 
    private Long codigoPersonaContrato;
    private Long codigoSocioContrato;
    private Long plazo;
    private Long plazoDias;
    private Long plazoMeses;
    private String nombreSocioContrato;
    private BigDecimal capital;
    private BigDecimal retencion;
    private BigDecimal total;
    private BigDecimal tasaInteres;
    private BigDecimal interes;
    private BigDecimal porcentajeRetencion;
    private Date fechaVencimiento;
    private String acreditacionMensual;
    private String numeroDocumento;

    private Long codigoTasa;
    private Timestamp fechaContrato;
    private GeneraReporte generaReporte;
    private String conInteres;

    private boolean deshabilitaConInteres;

    private Socio socioSel;
    private Periodicidad periodicidad;
    private ContratoDpf contratoDpf;
    private ContratoDpf contratoDpfSel;

    private List<Socio> itemsSocio;
    private List<RenovacionContratoDpf> itemsRenovacionContratoDpf;
    private List<CuentaContratoDpf> itemsCuentaContraDpf;
    private List<ContratoDpf> itemsContratoDpf;
    private String controladaPorOc = "N";

    /**
     * Initialize the concrete ContratoDpf controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     *
     * @throws java.io.IOException
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        this.controladaPorOc = "N";
        ////System.out.println("ActivacionUsuario.getCodigoComputador() "+ActivacionUsuario.getCodigoComputador());
        urlSinPermisos = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(urlSinPermisos);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"renovacionContratoDpfController", "init", CapturaError.getErrorException(ex)});
            }
        } else {
            List<Computador> listaComputador = this.ejbFacadeComputador.getItemsCodigoComputadorIfiAgenPerEli(ActivacionUsuario.getCodigoComputador(), ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
            if (listaComputador.isEmpty()) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoPerteneAgencia"));
                try {
                    Sesion.redireccionaPagina(urlSinPermisos);
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"renovacionContratoDpfController", "init", CapturaError.getErrorException(ex)});
                }
            }
        }

    }

    public RenovacionContratoDpfController() {
        // Inform the Abstract parent controller of the concrete PagoCredito?cap_first Entity        
        super(RenovacionContratoDpf.class);
    }

    /**
     * Guarda la Renovacion del DPF
     *
     * @param actionEvent
     */
    public void guardar(ActionEvent actionEvent) {
        if (this.validaGuardar()) {
            try {
                context = RequestContext.getCurrentInstance();

                // Cargando la conexion de BD
                llamaSP.cargaConexion();

                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);

                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);

                this.fechaContrato = new java.sql.Timestamp(new Date().getTime());
                // Guardando el Fondeo de Caja
                llamaSP.setNombreSP("mks_dpfs.pkm_renovacion_contrato_dpf.p_guarda");
                llamaSP.setNumeroParametros(12);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.contratoDpf.getContratoDpfPK().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.contratoDpf.getContratoDpfPK().getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tasa", this.getCodigoTasa()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_renovado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_renovacion", this.fechaContrato});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_vencimiento", new java.sql.Timestamp(this.getFechaVencimiento().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_vencimiento_anterior", new java.sql.Timestamp(this.contratoDpf.getFechaVencimiento().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_tasa_interes", this.getTasaInteres().multiply(new BigDecimal("100"))});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_con_interes", this.conInteres});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RenovacionContratoDpfGrabado");
                    MuestraMensaje.addInformacion(msg);
                    context.execute("procesandoDlg.hide()");
                    context.execute("RenovacionContratoDpfCreateDialog.hide()");
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
                        new Object[]{"renovacionContratoDpfController", "guardar", CapturaError.getErrorException(ex)});
            }
        }
    }

    private boolean validaGuardar() {
        if (this.codigoSocioContrato == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("Socio") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        } else {
            this.obtieneNombreSocio(false);
            if (this.msg != null) {
                return false;
            }
        }

        if (this.contratoDpf == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetasEC").getString("Contrato") + "  "
                    + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        this.obtieneNombreSocio(false);

        return this.msg == null;
    }

    /**
     * Prepara la renovacion del DPF
     *
     * @param actionEvent
     */
    public void preparaNuevoRenovacionDpf(ActionEvent actionEvent) {
        //this.prepareCreate(actionEvent);        

        this.setContratoDpf(new ContratoDpf());
        this.setPeriodicidad(null);
        this.socioSel = null;
        this.msg = null;
        this.conInteres = "N";
        this.deshabilitaConInteres = true;

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
        this.porcentajeRetencion = null;

        itemsCuentaContraDpf = new ArrayList<CuentaContratoDpf>();
        this.itemsContratoDpf = null;
        //this.itemsContraDpf = ejbFacadeContratoDpf.getItemsRenovacionContratoDpf(this., codigoPersona, estado, estadoRenovacion)
    }

    /**
     * Obtiene los DPFS.
     */
    public void obtieneRenovaciones() {
        this.itemsRenovacionContratoDpf = this.ejbFacade.getItemsCodigoPersonaCodigoIfipEstado(this.codigoPersona, ActivacionUsuario.getCodigoIfip(), this.estado);

    }

    /**
     * Calcula los totales para el DPF; aqui se calculan los valores y la fecha
     * de Vencimiento.
     *
     * @throws java.io.IOException
     */
    public void calculaTotalesDpf() throws IOException {
        this.msg = null;
        if (this.contratoDpf != null) {

            List<TasaInteresProDpfMon> listaTasaInteresDpfIfip = this.ejbFacadeTasaInteresProDpfMon.getItemsContratoDpf(ActivacionUsuario.getCodigoIfip(),
                    this.contratoDpf.getPlazoDias(),
                    this.contratoDpf.getMoneda().getCodigo(),
                    this.contratoDpf.getCapital(),
                    'N');

            if (listaTasaInteresDpfIfip.size() == 1) {
                this.obtientePorcentajeRetencion();

                tasaInteres = listaTasaInteresDpfIfip.get(0).getTasa().getValor().divide(new BigDecimal("100"));
                this.codigoTasa = listaTasaInteresDpfIfip.get(0).getCodigo();
                interes = this.contratoDpf.getCapital().multiply(tasaInteres);
                interes = interes.divide(new BigDecimal("360"), 5);
                interes = interes.multiply(new BigDecimal(String.valueOf(this.contratoDpf.getPlazoDias())));
                this.setInteres(interes);
                if (this.contratoDpf.getPlazoDias() <= 360) {
                    this.setPorcentajeRetencion(new BigDecimal("0.00"));
                    this.setRetencion(new BigDecimal("0.00"));
                } else {
                    this.setRetencion(this.getInteres().multiply(this.porcentajeRetencion.divide(new BigDecimal("100"))));
                }

                this.setTotal(this.getInteres().add(this.contratoDpf.getCapital()).subtract(this.getRetencion()));

                Calendar calendario = Calendar.getInstance();
                calendario.setTime(this.contratoDpf.getFechaVencimiento()); // Configuramos la fecha que se recibe            
                calendario.add(Calendar.MONTH, Integer.parseInt(String.valueOf(this.contratoDpf.getPlazoEnMeses())));
                this.setFechaVencimiento(calendario.getTime());

            } else {

                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistaTasaParaDPf");
                MuestraMensaje.addAdvertencia(msg);

                this.setTasaInteres(null);
                this.setInteres(null);
                this.setPorcentajeRetencion(null);
                this.setRetencion(null);
                this.setTotal(null);
                this.setFechaVencimiento(null);
            }
        } else {
            this.setTasaInteres(null);
            this.setInteres(null);
            this.setPorcentajeRetencion(null);
            this.setRetencion(null);
            this.setTotal(null);
            this.setFechaVencimiento(null);
        }
    }

    /**
     * Obtiene el porcentaje de retencion.
     *
     * @throws IOException
     */
    private void obtientePorcentajeRetencion() throws IOException {

        urlSinPermisos = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        this.controladaPorOc = "N";
        PersonaInstitucion personaInstitucion = this.ejbFacadePerIns.find(this.getSocioSel().getCodigoPersona().getCodigo());
        if (personaInstitucion != null) {
            this.controladaPorOc = String.valueOf(personaInstitucion.getControladaOc());
        } else {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaInstitucionNoRegistrada");
            MuestraMensaje.addError(msg);
        }
        ParametroGeneralDpfIfip parametroGeneralDpf;
        if (this.controladaPorOc.equals("N")) {
            parametroGeneralDpf = this.ejbFacadeParametroGeneralDpfIfip.find(new ParametroGeneralDpfIfipPK((long) 1, ActivacionUsuario.getCodigoIfip()));
        } else {
            parametroGeneralDpf = this.ejbFacadeParametroGeneralDpfIfip.find(new ParametroGeneralDpfIfipPK((long) 2, ActivacionUsuario.getCodigoIfip()));
        }
        if (parametroGeneralDpf != null) {
            this.porcentajeRetencion = parametroGeneralDpf.getValorNumerico();
            //System.out.println("listaParametroGeneralDpf " + parametroGeneralDpf);
            if (porcentajeRetencion == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteDefinidoPorcentajeRetencion"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(urlSinPermisos);
            }
        }
    }


    /*
     * Obtiene las cuentas del Contrato del Dpf.
     */
    public void obtieneDatosContratoDpf() throws IOException {
        this.contratoDpf = this.contratoDpfSel;
        this.itemsCuentaContraDpf = new ArrayList<CuentaContratoDpf>();
        this.itemsCuentaContraDpf.add(this.ejbFacadeCuentaContratoDpf.find(new CuentaContratoDpfPK(this.contratoDpf.getContratoDpfPK().getCodigo(), this.contratoDpf.getContratoDpfPK().getCodigoIfip())));

        if (String.valueOf(this.contratoDpf.getAcreditacionMensual()).equals("N")) {
            this.deshabilitaConInteres = false;
        } else {
            this.deshabilitaConInteres = true;
            this.conInteres = "N";
        }

        calculaTotalesDpf();

    }

    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimir(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (this.getSelected().getContratoRenovado() != null) {
            String nombreReporte;
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            FacesContext facesContext = FacesContext.getCurrentInstance();

            setGeneraReporte(new GeneraReporte());
            getGeneraReporte().setParametros(new HashMap<String, Object>());

            getGeneraReporte().getParametros().put("codigoContrato", this.getSelected().getContratoRenovado().getContratoDpfPK().getCodigo());
            getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

            nombreReporte = "renovacionContratoDpf";

            getGeneraReporte().exporta("/financiero/dpfs/renovacion/reporte/", nombreReporte,
                    nombreReporte + this.numeroDocumento + ".pdf",
                    "PDF", externalContext, facesContext);
        } else {
            MuestraMensaje.addAdvertencia("Aún NO se ha generado el contrato para  la renovación del DPF ");
        }
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
                if (this.getSocioSel().getCodigoIfipAgencia().getCodigo() == ActivacionUsuario.getCodigoIfipAgencia()) {
                    this.setNombreSocio(this.getSocioSel().getCodigoPersona().getNombreCompleto());
                    this.codigoPersona = this.getSocioSel().getCodigoPersona().getCodigo();
                } else {
                    this.itemsContratoDpf = null;
                    this.itemsRenovacionContratoDpf = null;
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                    MuestraMensaje.addAdvertencia(msg);
                }
            }
        } else {
            if (this.getCodigoSocioContrato() != null) {
                this.setSocioSel(this.ejbFacadeSocio.find(new SocioPK(this.getCodigoSocioContrato(), ActivacionUsuario.getCodigoIfip())));
                if (getSocioSel() == null) {
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste");
                    this.setNombreSocioContrato(null);
                    MuestraMensaje.addAdvertencia(msg);
                } else {
                    if (this.getSocioSel().getCodigoIfipAgencia().getCodigo() == ActivacionUsuario.getCodigoIfipAgencia()) {
                        this.setCodigoPersonaContrato(this.getSocioSel().getCodigoPersona().getCodigo());
                        this.setNombreSocioContrato(this.getSocioSel().getCodigoPersona().getNombreCompleto());
                    } else {
                        this.itemsContratoDpf = null;
                        this.itemsRenovacionContratoDpf = null;
                        this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                        MuestraMensaje.addAdvertencia(msg);
                    }
                }
            } else {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste");
                MuestraMensaje.addAdvertencia(msg);
            }
        }
    }

    /**
     *
     */
    public void obtieneNombreSocioBusqueda() {
        this.obtieneNombreSocio(true);
    }

    /**
     * Obtiene el nombre del socio que va a contratar el DPF.
     */
    public void obtieneNombreSocioContrato() {
        this.obtieneNombreSocio(false);
        this.obtieneContratosDpf();
    }

    /**
     * Prepara la busqueda de los socios
     *
     *
     * @param actionEvent
     */
    public void preparaBusqueaSociosLista(ActionEvent actionEvent) {
        //System.out.println("prepara busqueda ");
        this.preparaBusquedaSocio();
        this.esBusquedaSocioContrato = false;
        //System.out.println("fin prepara busqueda ");
    }

    /**
     * Prepara la busqueda de los socios en el contrato
     *
     *
     * @param actionEvent
     */
    public void preparaBusquedaSociosContrato(ActionEvent actionEvent) {
        this.esBusquedaSocioContrato = true;
        this.nombreSocioBusqueda = null;
        this.setItemsSocio(null);
        this.mensajeCriterio = null;
        this.preparaNuevoRenovacionDpf(actionEvent);
    }

    /**
     * Encera todo lo que se refiere a la busque del socio.
     */
    private void preparaBusquedaSocio() {
        this.itemsRenovacionContratoDpf = null;
        this.setItemsSocio(null);
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
        if (this.getSocioSel() != null) {
            if (!this.esBusquedaSocioContrato) {
                if (this.getSocioSel().getCodigoIfipAgencia().getCodigo() == ActivacionUsuario.getCodigoIfipAgencia()) {
                    this.codigoPersona = this.getSocioSel().getCodigoPersona().getCodigo();
                    this.nombreSocio = this.getSocioSel().getCodigoPersona().getNombreCompleto();
                    this.codigoSocio = this.getSocioSel().getSocioPK().getCodigo();
                    this.obtieneRenovaciones();
                } else {
                    this.itemsContratoDpf = null;
                    this.itemsRenovacionContratoDpf = null;
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                    MuestraMensaje.addAdvertencia(msg);
                }
            } else {
                if (this.getSocioSel().getCodigoIfipAgencia().getCodigo() == ActivacionUsuario.getCodigoIfipAgencia()) {
                    this.setCodigoSocioContrato((Long) this.getSocioSel().getSocioPK().getCodigo());
                    this.setCodigoPersonaContrato(this.getSocioSel().getCodigoPersona().getCodigo());
                    this.setNombreSocioContrato(this.getSocioSel().getCodigoPersona().getNombreCompleto());

                    // Actualizando componentes de la vista
                    context = RequestContext.getCurrentInstance();
                    List<String> listaComponentesActualizar = new ArrayList<String>();
                    this.obtieneContratosDpf();
                    listaComponentesActualizar.add("RenovacionContratoDpfCreateForm:nombrePersona");
                    listaComponentesActualizar.add("RenovacionContratoDpfCreateForm:socio");
                    listaComponentesActualizar.add("RenovacionContratoDpfCreateForm:contrato");
                    context.update(listaComponentesActualizar);
                } else {
                    this.itemsContratoDpf = null;
                    this.itemsRenovacionContratoDpf = null;
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                    MuestraMensaje.addAdvertencia(msg);
                }
            }
        }
    }

    /**
     * Obtiene los Socios para la Seleccion.
     */
    public void obtieneSocios() {
        try {

            if (Validaciones.cumpleRequerimientoCampo(this.getNombreSocioBusqueda(), 6)) {
                this.setItemsSocio(this.ejbFacadeSocio.getItemsNombresIndicaActivo(this.getNombreSocioBusqueda(), ActivacionUsuario.getCodigoIfip(), 'S'));
            } else {
                this.setMensajeCriterio(Validaciones.msg);
                this.setItemsSocio(null);
                this.setSocioSel(null);
                this.setSelected(null);
                this.setCodigoPersona(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"renovacionContratoDpfController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Obtiene los contratos de la Persona que puede Renovar
     */
    private void obtieneContratosDpf() {
        this.itemsContratoDpf = this.ejbFacadeContratoDpf.getItemsRenovacionContratoDpf(ActivacionUsuario.getCodigoIfip(), this.codigoPersonaContrato, 'V', 'P', 'N');
        if (!this.itemsContratoDpf.isEmpty()) {
            this.contratoDpfSel = null;
        }
        //System.out.println("itemsContratoDpf DPF " + this.itemsContratoDpf);
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
     * @return the codigoPersona
     */
    public Long getCodigoPersona() {
        return codigoPersona;
    }

    /**
     * @param codigoPersona the codigoPersona to set
     */
    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    /**
     * @return the esBusquedaSocioContrato
     */
    public boolean isEsBusquedaSocioContrato() {
        return esBusquedaSocioContrato;
    }

    /**
     * @param esBusquedaSocioContrato the esBusquedaSocioContrato to set
     */
    public void setEsBusquedaSocioContrato(boolean esBusquedaSocioContrato) {
        this.esBusquedaSocioContrato = esBusquedaSocioContrato;
    }

    /**
     * @return the plazo
     */
    public Long getPlazo() {
        return plazo;
    }

    /**
     * @param plazo the plazo to set
     */
    public void setPlazo(Long plazo) {
        this.plazo = plazo;
    }

    /**
     * @return the plazoDias
     */
    public Long getPlazoDias() {
        return plazoDias;
    }

    /**
     * @param plazoDias the plazoDias to set
     */
    public void setPlazoDias(Long plazoDias) {
        this.plazoDias = plazoDias;
    }

    /**
     * @return the plazoMeses
     */
    public Long getPlazoMeses() {
        return plazoMeses;
    }

    /**
     * @param plazoMeses the plazoMeses to set
     */
    public void setPlazoMeses(Long plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    /**
     * @return the nombreSocioContrato
     */
    public String getNombreSocioContrato() {
        return nombreSocioContrato;
    }

    /**
     * @param nombreSocioContrato the nombreSocioContrato to set
     */
    public void setNombreSocioContrato(String nombreSocioContrato) {
        this.nombreSocioContrato = nombreSocioContrato;
    }

    /**
     * @return the capital
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * @return the retencion
     */
    public BigDecimal getRetencion() {
        return retencion;
    }

    /**
     * @param retencion the retencion to set
     */
    public void setRetencion(BigDecimal retencion) {
        this.retencion = retencion;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the tasaInteres
     */
    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    /**
     * @param tasaInteres the tasaInteres to set
     */
    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    /**
     * @return the interes
     */
    public BigDecimal getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the acreditacionMensual
     */
    public String getAcreditacionMensual() {
        return acreditacionMensual;
    }

    /**
     * @param acreditacionMensual the acreditacionMensual to set
     */
    public void setAcreditacionMensual(String acreditacionMensual) {
        this.acreditacionMensual = acreditacionMensual;
    }

    /**
     * @return the codigoTasa
     */
    public Long getCodigoTasa() {
        return codigoTasa;
    }

    /**
     * @param codigoTasa the codigoTasa to set
     */
    public void setCodigoTasa(Long codigoTasa) {
        this.codigoTasa = codigoTasa;
    }

    /**
     * @return the fechaContrato
     */
    public Timestamp getFechaContrato() {
        return fechaContrato;
    }

    /**
     * @param fechaContrato the fechaContrato to set
     */
    public void setFechaContrato(Timestamp fechaContrato) {
        this.fechaContrato = fechaContrato;
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
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
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
     * @return the itemsRenovacionContratoDpf
     */
    public List<RenovacionContratoDpf> getItemsRenovacionContratoDpf() {
        return itemsRenovacionContratoDpf;
    }

    /**
     * @param itemsRenovacionContratoDpf the itemsRenovacionContratoDpf to set
     */
    public void setItemsRenovacionContratoDpf(List<RenovacionContratoDpf> itemsRenovacionContratoDpf) {
        this.itemsRenovacionContratoDpf = itemsRenovacionContratoDpf;
    }

    /**
     * @return the porcentajeRetencion
     */
    public BigDecimal getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    /**
     * @param porcentajeRetencion the porcentajeRetencion to set
     */
    public void setPorcentajeRetencion(BigDecimal porcentajeRetencion) {
        this.porcentajeRetencion = porcentajeRetencion;
    }

    /**
     * @return the codigoPersonaContrato
     */
    public Long getCodigoPersonaContrato() {
        return codigoPersonaContrato;
    }

    /**
     * @param codigoPersonaContrato the codigoPersonaContrato to set
     */
    public void setCodigoPersonaContrato(Long codigoPersonaContrato) {
        this.codigoPersonaContrato = codigoPersonaContrato;
    }

    /**
     * @return the itemsCuentaContraDpf
     */
    public List<CuentaContratoDpf> getItemsCuentaContraDpf() {
        return itemsCuentaContraDpf;
    }

    /**
     * @param itemsCuentaContraDpf the itemsCuentaContraDpf to set
     */
    public void setItemsCuentaContraDpf(List<CuentaContratoDpf> itemsCuentaContraDpf) {
        this.itemsCuentaContraDpf = itemsCuentaContraDpf;
    }

    /**
     * @return the contratoDpf
     */
    public ContratoDpf getContratoDpf() {
        return contratoDpf;
    }

    /**
     * @param contratoDpf the contratoDpf to set
     */
    public void setContratoDpf(ContratoDpf contratoDpf) {
        this.contratoDpf = contratoDpf;
    }

    /**
     * @return the codigoSocioContrato
     */
    public Long getCodigoSocioContrato() {
        return codigoSocioContrato;
    }

    /**
     * @param codigoSocioContrato the codigoSocioContrato to set
     */
    public void setCodigoSocioContrato(Long codigoSocioContrato) {
        this.codigoSocioContrato = codigoSocioContrato;
    }

    /**
     * @return the contratoDpfSel
     */
    public ContratoDpf getContratoDpfSel() {
        return contratoDpfSel;
    }

    /**
     * @param contratoDpfSel the contratoDpfSel to set
     */
    public void setContratoDpfSel(ContratoDpf contratoDpfSel) {
        this.contratoDpfSel = contratoDpfSel;
    }

    /**
     * @return the itemsContratoDpf
     */
    public List<ContratoDpf> getItemsContratoDpf() {
        return itemsContratoDpf;
    }

    /**
     * @param itemsContratoDpf the itemsContratoDpf to set
     */
    public void setItemsContratoDpf(List<ContratoDpf> itemsContratoDpf) {
        this.itemsContratoDpf = itemsContratoDpf;
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
     * @return the conInteres
     */
    public String getConInteres() {
        return conInteres;
    }

    /**
     * @param conInteres the conInteres to set
     */
    public void setConInteres(String conInteres) {
        this.conInteres = conInteres;
    }

    /**
     * @return the deshabilitaConInteres
     */
    public boolean isDeshabilitaConInteres() {
        return deshabilitaConInteres;
    }

    /**
     * @param deshabilitaConInteres the deshabilitaConInteres to set
     */
    public void setDeshabilitaConInteres(boolean deshabilitaConInteres) {
        this.deshabilitaConInteres = deshabilitaConInteres;
    }

    /**
     * @return the estado
     */
    public char getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(char estado) {
        this.estado = estado;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
