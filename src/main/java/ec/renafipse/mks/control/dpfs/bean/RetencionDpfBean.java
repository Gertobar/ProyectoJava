
package ec.renafipse.mks.control.dpfs.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.ParametroContableIfip;
import ec.renafipse.mks.modelo.contables.ParametroContableIfipPK;
import ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetDet;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.PagoDpf;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.negocio.adquisiciones.ParametroContableIfipFacade;
import ec.renafipse.mks.negocio.contables.DocumentoRetencionDpfFacade;
import ec.renafipse.mks.negocio.contables.TalonarioDocumentoRetDetFacade;
import ec.renafipse.mks.negocio.dpfs.ContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.CuentaContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.MotivoPrecancelacionFacade;
import ec.renafipse.mks.negocio.dpfs.PagoDpfFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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



@ManagedBean(name = "retencionDpfBean")
@ViewScoped
public class RetencionDpfBean extends AbstractController<ContratoDpf> {

    @EJB
    private ContratoDpfFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private CuentaContratoDpfFacade ejbFacadeCuentaContratoDpf;

    @EJB
    private MotivoPrecancelacionFacade ejbFacadeMotivoPrecancelacion;

    @EJB
    private ComputadorFacade ejbFacadeComputador;

    @EJB
    private DocumentoRetencionDpfFacade ejbFacadeDocumentoRetencionDpf;

    @EJB
    private TalonarioDocumentoRetDetFacade ejbFacadeTalonarioDocumentoRetDet;

    @EJB
    private ParametroContableIfipFacade ejbFacadeParametroContableIfip;

    @EJB
    private PagoDpfFacade ejbFacadePagoDpf;

    private LlamaSP llamaSP;

    private Long codigoSocio;
    private String nombreSocio;
    private String msg;
    private String mensajeCriterio;
    private String nombreSocioBusqueda;
    private Long codigoPersona;
    private char estado;
    private char formaPago;

    private boolean esBusquedaSocioContrato;

    private RequestContext context;
    private String urlSinPermisos;

    // Variables del COntrato 
    private Long codigoPersonaContrato;
    private Long codigoSocioContrato;
    private Long codigoContrato;
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
    private BigDecimal interesGenerado;
    private BigDecimal interesPagado;
    private BigDecimal interesAcreditar;
    private Date fechaVencimiento;
    private String acreditacionMensual;
    private String numeroDocumento;
    private Long codigoDocumento;
    private Long codigoTasa;
    private Timestamp fechaContrato;
    private GeneraReporte generaReporte;
    private String conInteres;
    private Long codigoPrecancelacion;
    private String serieRetencion;
    private String numeroRetencion;
    private Date fechaRetencion;
    private Date fechaMimina;
    private Date fechaMaxima;
    private PagoDpf pagoDpfSel;

    private boolean deshabilitaImpresionRetencion;
    private boolean esReasignacion;

    private Socio socioSel;
    private ContratoDpf contratoDpfSel;
    private ContratoDpf contratoDpf;

    private List<Socio> itemsSocio;
    private List<ContratoDpf> itemsContratoDpf;
    private List<PagoDpf> itemsPagosDpf;
    private char retencionImpresa;
    private PagoDpf pagoDpf;

    /**
     * Initialize the concrete ContratoDpf controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();

        ////System.out.println("ActivacionUsuario.getCodigoComputador() "+ActivacionUsuario.getCodigoComputador());
        urlSinPermisos = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(urlSinPermisos);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"RetencionDpfBean", "init", CapturaError.getErrorException(ex)});
            }
        } else {
            List<Computador> listaComputador = this.ejbFacadeComputador.getItemsCodigoComputadorIfiAgenPerEli(ActivacionUsuario.getCodigoComputador(), ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
            if (listaComputador.isEmpty()) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoPerteneAgencia"));
                try {
                    Sesion.redireccionaPagina(urlSinPermisos);
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"RetencionDpfBean", "init", CapturaError.getErrorException(ex)});
                }
            }
        }
        this.setFechaMaxima(new Date());
//        if (Integer.parseInt(ActivacionUsuario.getCodigoPeriodo()) == new Date().getYear() + 1900) {
//            this.setFechaMaxima(new Date());
//        } else {
//            this.setFechaMaxima(this.fecha("31/12/" + ActivacionUsuario.getCodigoPeriodo()));
//        }

        this.setFechaMimina(this.getFechaMaxima());
        // Colocando Fecha Mimina
        try {
            ParametroContableIfip pti = this.ejbFacadeParametroContableIfip.find(new ParametroContableIfipPK(2, ActivacionUsuario.getCodigoIfip()));
            if (pti != null) {
                this.setFechaMimina(this.agregaDias(this.getFechaMaxima(), Long.parseLong(pti.getValor().toString()) * -1));
            }
        } catch (NumberFormatException ex) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaSoloNumeros");
            MuestraMensaje.addAdvertencia(msg);
        }

    }

    public RetencionDpfBean() {
        // Inform the Abstract parent controller of the concrete PagoCredito?cap_first Entity        
        super(ContratoDpf.class);
    }

    private void preparaRetencion() {

        //this.pagoDpfSel = null;
        //this.itemsPagosDpf = null;
        esReasignacion = false;
        this.codigoDocumento = null;
        this.numeroDocumento = null;
        this.serieRetencion = null;
        this.numeroRetencion = null;
        this.deshabilitaImpresionRetencion = false;
        /*context = RequestContext.getCurrentInstance();
         context.execute("procesandoDlg.show()");*/

        this.msg = null;

    }

    /**
     * Cuando selecciona el pago se almacena en una variable auxiliar
     */
    public void seleccionaPagoDpf() {
        pagoDpf = pagoDpfSel;
    }

    public void retiene(ActionEvent actionEvent) throws IOException {

        context = RequestContext.getCurrentInstance();

        if (this.contratoDpfSel == null) {
            context.execute("procesandoDlg.hide()");
            return;
        }

        this.preparaRetencion();
        retencionImpresa = 'N';

        if (this.contratoDpfSel.getRetencionImpresa() == 'S' && this.contratoDpfSel.getAcreditacionMensual() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContratoDpfConRetencion");
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(msg);

        } else {
            if (this.contratoDpfSel.getAcreditacionMensual() == 'N') {
                if (obtieneDatosRetencion()) {
                    codigoContrato = this.contratoDpfSel.getContratoDpfPK().getCodigo();
                    context.execute("procesandoDlg.hide()");
                    context.execute("CreaRetencionDilago.show()");
                }
            } else {

                this.obtienePagosDpfs();
                context.execute("procesandoDlg.hide()");
                context.execute("listaPagosDpfDialog.show()");
            }

        }

        ////System.out.println(" this.banderaContRete " + this.banderaContRete);
    }

    public void reasginaRetencion(ActionEvent actionEvent) throws IOException {

        context = RequestContext.getCurrentInstance();
        if (this.contratoDpfSel == null) {
            context.execute("procesandoDlg.hide()");
            return;
        }

        if (this.contratoDpfSel.getRetencionImpresa() == 'N' && this.contratoDpfSel.getAcreditacionMensual() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GenereLaRetencion");
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(msg);
            return;
        }

        this.preparaRetencion();
        this.esReasignacion = true;

        if (this.contratoDpfSel.getAcreditacionMensual() == 'N') {
            if (obtieneDatosRetencion()) {
                codigoContrato = this.contratoDpfSel.getContratoDpfPK().getCodigo();
                context.execute("procesandoDlg.hide()");
                context.execute("CreaRetencionDilago.show()");
            }
        } else {
            retencionImpresa = 'S';
            this.obtienePagosDpfs();
            context.execute("procesandoDlg.hide()");
            context.execute("listaPagosDpfDialog.show()");
        }
    }

    /**
     * Guarda la Renovacion del DPF
     *
     * @param actionEvent
     */
    public void guardar(ActionEvent actionEvent) {

        try {

            context = RequestContext.getCurrentInstance();
            context.execute("procesandoDlg.show()");

            if (this.contratoDpfSel == null) {
                context.execute("procesandoDlg.hide()");
                return;
            }

            this.obtieneDocumento();
            if (this.codigoDocumento == null) {
                context.execute("procesandoDlg.hide()");
                return;
            }

            if (pagoDpfSel == null) {
                obtienePagosDpfs();
                this.pagoDpfSel = (itemsPagosDpf != null && !itemsPagosDpf.isEmpty()) ? itemsPagosDpf.get(0) : null;
            }

            if (pagoDpfSel == null) {
                context.execute("procesandoDlg.hide()");
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistePagoDpf");
                context.execute("procesandoDlg.hide()");
                MuestraMensaje.addError(msg);
                return;
            }
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            this.fechaContrato = new java.sql.Timestamp(new Date().getTime());

            if (!this.esReasignacion) {
                // Generando la retencion
                llamaSP.setNombreSP("mks_contables.pkm_retencion_dpf.p_guarda");
                llamaSP.setNumeroParametros(15);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.contratoDpfSel.getContratoDpfPK().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", String.valueOf(new Date().getYear()+1900)});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_retencion", new java.sql.Timestamp(this.fechaRetencion.getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_retenido", this.retencion});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", new java.sql.Timestamp(new Date().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_base_imponible", this.interesPagado});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_porcentaje", this.porcentajeRetencion});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", this.retencion});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_documento", this.codigoDocumento});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_forma_pago_interes", (contratoDpfSel.getAcreditacionMensual() == 'S') ? "A" : "V"});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_pago", (pagoDpfSel != null) ? pagoDpfSel.getPagoDpfPK().getNumeroPago() : null});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            } else {
                llamaSP.setNombreSP("mks_contables.pkm_talonario_doc_ret_det.p_reasigna_documento_dpf");
                llamaSP.setNumeroParametros(8);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", null});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.contratoDpfSel.getContratoDpfPK().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_pago", (pagoDpfSel != null) ? pagoDpfSel.getPagoDpfPK().getNumeroPago() : null});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_talonario", this.codigoDocumento});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", new java.sql.Timestamp(new Date().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            }
            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RetencionRealizadaConExito");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("Retencion.hide()");
                context.execute("ImprimeComprobanteRetencionDialogo.show()");
                this.deshabilitaImpresionRetencion = true;
                this.contratoDpfSel.setRetencionImpresa('S');
                this.obtieneContratoDpf();

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
                    new Object[]{"retencionDpfBean", "guardar", CapturaError.getErrorException(ex)});
        }

    }

    private boolean obtieneDatosRetencion() {
        boolean correcto = false;

        try {

            if (this.contratoDpfSel.getAcreditacionMensual() == 'N') {
                // Cargando la conexion de BD
                llamaSP.cargaConexion();

                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);

                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);

                // Guardando el Fondeo de Caja
                llamaSP.setNombreSP("mks_dpfs.pkm_contrato_dpf.p_obtiene_datos_vencimiento");
                llamaSP.setNumeroParametros(5);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.contratoDpfSel.getContratoDpfPK().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_acreditado", Types.NUMERIC});
                llamaSP.getListParametrosSalida().add(new Object[]{"p_impuesto_retenido", Types.NUMERIC});
                llamaSP.getListParametrosSalida().add(new Object[]{"p_porcentaje_retencion", Types.NUMERIC});

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();

                    this.interesPagado = new BigDecimal(llamaSP.getListResultado().get(0).toString());
                    this.retencion = new BigDecimal(llamaSP.getListResultado().get(1).toString());
                    this.porcentajeRetencion = new BigDecimal(llamaSP.getListResultado().get(2).toString());

                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    context.execute("procesandoDlg.hide()");

                    correcto = true;

                } else {
                    context.execute("procesandoDlg.hide()");
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                }
            } else {

                correcto = true;
                this.interesPagado = pagoDpfSel.getInteresPagado();
                this.retencion = pagoDpfSel.getRetencion();
                this.porcentajeRetencion = this.contratoDpfSel.getPorcentajeRetencion();
            }
        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"retencionDpfBean", "guardar", CapturaError.getErrorException(ex)});

        }

        return correcto;
    }

    //----------------------------------------------------------------------------
    //-- PROCESOS DE DOCUMENTO DE RETENCION
    /**
     * Obtiene el Documento y lo valida que exista
     */
    public void obtieneDocumento() {
        try {
            this.codigoDocumento = null;
            //System.out.println("this.getSerieRetencion() " + this.getSerieRetencion()+" this.getNumeroRetencion() "+this.getNumeroRetencion());
            if (this.getSerieRetencion() != null && this.getNumeroRetencion() != null) {
                List<TalonarioDocumentoRetDet> listaTalonarioDocumentoRetDet = ejbFacadeTalonarioDocumentoRetDet.getItemsSerieTalonarioSerieDocumento(this.getSerieRetencion(), Long.parseLong(this.getNumeroRetencion()));
                //System.out.println("listaTalonarioDocumentoRetDet " + listaTalonarioDocumentoRetDet);

                if (!listaTalonarioDocumentoRetDet.isEmpty()) {
                    if (listaTalonarioDocumentoRetDet.size() == 1) {
                        if (listaTalonarioDocumentoRetDet.get(0).getEstado() == 'P') {
                            this.codigoDocumento = listaTalonarioDocumentoRetDet.get(0).getCodigo();
                            this.numeroDocumento = listaTalonarioDocumentoRetDet.get(0).getNumero();
                            this.numeroRetencion = this.numeroDocumento;
                        } else {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoDiferentePendiente");
                            MuestraMensaje.addError(msg);
                        }
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnaRetencionParaElNumero");
                        MuestraMensaje.addError(msg);
                    }

                } else {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoRetencionNoExiste");
                    MuestraMensaje.addError(msg);
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoRetencionNoExiste");
                MuestraMensaje.addError(msg);
            }
        } catch (NumberFormatException e) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SoloValoresNumericos");
            MuestraMensaje.addError(msg);
        }
    }

    /**
     * Obtiene los DPFS.
     */
    public void obtieneContratoDpf() {

        this.itemsContratoDpf = null;
        this.itemsPagosDpf = null;
        this.itemsContratoDpf = this.ejbFacade.getItemsCodigoIfipCodigoPersonaConRetencion(this.codigoPersona, ActivacionUsuario.getCodigoIfip(), BigDecimal.ZERO);
        this.codigoDocumento = null;
        this.numeroDocumento = null;

    }

    /**
     * Obtiene los pagos de lso DPFS
     */
    private void obtienePagosDpfs() {
        this.pagoDpf = null;
        this.pagoDpfSel = null;
        itemsPagosDpf = this.ejbFacadePagoDpf.getItemsPagoDpfContratoIfip(this.contratoDpfSel.getContratoDpfPK().getCodigo(), this.contratoDpfSel.getContratoDpfPK().getCodigoIfip(), this.retencionImpresa);
    }

    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimir(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        context = RequestContext.getCurrentInstance();
        if (this.contratoDpfSel.getRetencionImpresa() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContratoDpfSinRetencion");
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(msg);

        }

        codigoContrato = (contratoDpfSel != null) ? contratoDpfSel.getContratoDpfPK().getCodigo() : codigoContrato;

        if (codigoContrato == null) {
            return;
        }
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoPlazo", codigoContrato);
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());
        getGeneraReporte().getParametros().put("numeroPago", this.pagoDpfSel.getPagoDpfPK().getNumeroPago());
        nombreReporte = "comprobanteRetencion";

        getGeneraReporte().exporta("/financiero/dpfs/retencionDpf/reporte/", nombreReporte,
                nombreReporte + "Dpf" + String.valueOf(this.codigoContrato) + ".pdf",
                "PDF", externalContext, facesContext);

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
                    obtieneContratoDpf();

                } else {
                    this.itemsContratoDpf = null;
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                    MuestraMensaje.addAdvertencia(msg);
                    this.setItemsContratoDpf(null);
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
                        this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                        MuestraMensaje.addAdvertencia(msg);
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

        this.preparaBusquedaSocio();
        this.esBusquedaSocioContrato = false;
        this.itemsContratoDpf = null;

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
    }

    /**
     * Encera todo lo que se refiere a la busque del socio.
     */
    private void preparaBusquedaSocio() {
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
                } else {
                    this.itemsContratoDpf = null;
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                }
                this.obtieneContratoDpf();
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
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
                }
            }
        }
    }

    /**
     * Selecciona el Pago del DPF
     */
    public void seleccionaPago() {
        System.out.println("pagoDpfSel " + pagoDpfSel);
        System.out.println("pagoDpf " + pagoDpf);
        if (pagoDpf != null) {
            this.pagoDpfSel = pagoDpf;
            System.out.println("pagoDpfSel " + pagoDpfSel);
            if (this.obtieneDatosRetencion()) {
                context = RequestContext.getCurrentInstance();
                codigoContrato = this.contratoDpfSel.getContratoDpfPK().getCodigo();
                context.execute("listaPagosDpfDialog.hide()");
                context.execute("CreaRetencionDilago.show()");
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
//        this.itemsContratoDpf = this.ejbFacadeContratoDpf.getItemsRenovacionContratoDpf(ActivacionUsuario.getCodigoIfip(), this.codigoPersonaContrato, 'V', 'P');
        if (!this.itemsContratoDpf.isEmpty()) {
            this.contratoDpfSel = null;
        }
        //System.out.println("itemsContratoDpf DPF " + this.itemsContratoDpf);
    }

    /**
     * Agrega días a una fecha
     *
     * @param fecha FEcha
     * @param dias Numero de dias a agregar
     * @return FEcha agregada días
     */
    private Date agregaDias(Date fecha, long dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, (int) dias);
        return calendar.getTime();
    }

    public Date fecha(String fecha) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaD;
        fechaD = formateador.parse(fecha);
        return fechaD;
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

    /**
     * @return the codigoDocumento
     */
    public Long getCodigoDocumento() {
        return codigoDocumento;
    }

    /**
     * @param codigoDocumento the codigoDocumento to set
     */
    public void setCodigoDocumento(Long codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
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
     * @return the codigoPrecancelacion
     */
    public Long getCodigoPrecancelacion() {
        return codigoPrecancelacion;
    }

    /**
     * @param codigoPrecancelacion the codigoPrecancelacion to set
     */
    public void setCodigoPrecancelacion(Long codigoPrecancelacion) {
        this.codigoPrecancelacion = codigoPrecancelacion;
    }

    /**
     * @return the interesGenerado
     */
    public BigDecimal getInteresGenerado() {
        return interesGenerado;
    }

    /**
     * @param interesGenerado the interesGenerado to set
     */
    public void setInteresGenerado(BigDecimal interesGenerado) {
        this.interesGenerado = interesGenerado;
    }

    /**
     * @return the interesPagado
     */
    public BigDecimal getInteresPagado() {
        return interesPagado;
    }

    /**
     * @param interesPagado the interesPagado to set
     */
    public void setInteresPagado(BigDecimal interesPagado) {
        this.interesPagado = interesPagado;
    }

    /**
     * @return the interesAcreditar
     */
    public BigDecimal getInteresAcreditar() {
        return interesAcreditar;
    }

    /**
     * @param interesAcreditar the interesAcreditar to set
     */
    public void setInteresAcreditar(BigDecimal interesAcreditar) {
        this.interesAcreditar = interesAcreditar;
    }

    /**
     * @return the serieRetencion
     */
    public String getSerieRetencion() {
        return serieRetencion;
    }

    /**
     * @param serieRetencion the serieRetencion to set
     */
    public void setSerieRetencion(String serieRetencion) {
        this.serieRetencion = serieRetencion;
    }

    /**
     * @return the numeroRetencion
     */
    public String getNumeroRetencion() {
        return numeroRetencion;
    }

    /**
     * @param numeroRetencion the numeroRetencion to set
     */
    public void setNumeroRetencion(String numeroRetencion) {
        this.numeroRetencion = numeroRetencion;
    }

    /**
     * @return the fechaRetencion
     */
    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    /**
     * @param fechaRetencion the fechaRetencion to set
     */
    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    /**
     * @return the fechaMimina
     */
    public Date getFechaMimina() {
        return fechaMimina;
    }

    /**
     * @param fechaMimina the fechaMimina to set
     */
    public void setFechaMimina(Date fechaMimina) {
        this.fechaMimina = fechaMimina;
    }

    /**
     * @return the fechaMaxima
     */
    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    /**
     * @param fechaMaxima the fechaMaxima to set
     */
    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    /**
     * @return the deshabilitaImpresionRetencion
     */
    public boolean isDeshabilitaImpresionRetencion() {
        return deshabilitaImpresionRetencion;
    }

    /**
     * @param deshabilitaImpresionRetencion the deshabilitaImpresionRetencion to
     * set
     */
    public void setDeshabilitaImpresionRetencion(boolean deshabilitaImpresionRetencion) {
        this.deshabilitaImpresionRetencion = deshabilitaImpresionRetencion;
    }

    /**
     * @return the codigoContrato
     */
    public Long getCodigoContrato() {
        return codigoContrato;
    }

    /**
     * @param codigoContrato the codigoContrato to set
     */
    public void setCodigoContrato(Long codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    /**
     * @return the formaPago
     */
    public char getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(char formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the pagoDpfSel
     */
    public PagoDpf getPagoDpfSel() {
        return pagoDpfSel;
    }

    /**
     * @param pagoDpfSel the pagoDpfSel to set
     */
    public void setPagoDpfSel(PagoDpf pagoDpfSel) {
        this.pagoDpfSel = pagoDpfSel;
    }

    /**
     * @return the itemsPagosDpf
     */
    public List<PagoDpf> getItemsPagosDpf() {
        return itemsPagosDpf;
    }

    /**
     * @param itemsPagosDpf the itemsPagosDpf to set
     */
    public void setItemsPagosDpf(List<PagoDpf> itemsPagosDpf) {
        this.itemsPagosDpf = itemsPagosDpf;
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
}
