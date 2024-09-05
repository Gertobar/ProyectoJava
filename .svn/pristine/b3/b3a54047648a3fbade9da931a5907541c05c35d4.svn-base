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
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpf;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpfPK;
import ec.renafipse.mks.modelo.dpfs.MotivoPrecancelacion;
import ec.renafipse.mks.modelo.dpfs.PagoDpf;
import ec.renafipse.mks.modelo.dpfs.RenovacionContratoDpf;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.negocio.dpfs.ContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.CuentaContratoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.MotivoPrecancelacionFacade;
import ec.renafipse.mks.negocio.dpfs.PagoDpfFacade;
import ec.renafipse.mks.negocio.dpfs.ParametroGeneralDpfFacade;
import ec.renafipse.mks.negocio.dpfs.TalonarioDocumentoDpfDetFacade;
import ec.renafipse.mks.negocio.dpfs.TasaInteresProDpfMonFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "precancelacionContratoDpfController")
@ViewScoped
public class PrecancelacionContratoDpfController extends AbstractController<ContratoDpf> {

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
    private PagoDpfFacade ejbFacadePagoDpf;

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
    private Double retencionPorPagar;

    private boolean deshabilitaConInteres;

    private Socio socioSel;
    private Periodicidad periodicidad;
    private ContratoDpf contratoDpfSel;
    private MotivoPrecancelacion motivoPrecancelacion;

    private List<Socio> itemsSocio;
    private List<RenovacionContratoDpf> itemsRenovacionContratoDpf;
    private List<CuentaContratoDpf> itemsCuentaContraDpf;
    private List<ContratoDpf> itemsContratoDpf;
    private List<MotivoPrecancelacion> itemsMotivoPrecancelacion;

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
        ////System.out.println("ActivacionUsuario.getCodigoComputador() "+ActivacionUsuario.getCodigoComputador());
        urlSinPermisos = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(urlSinPermisos);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"precancelacionContratoDpfController", "init", CapturaError.getErrorException(ex)});
            }
        } else {
            List<Computador> listaComputador = this.ejbFacadeComputador.getItemsCodigoComputadorIfiAgenPerEli(ActivacionUsuario.getCodigoComputador(), ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
            if (listaComputador.isEmpty()) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoPerteneAgencia"));
                try {
                    Sesion.redireccionaPagina(urlSinPermisos);
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"precancelacionContratoDpfController", "init", CapturaError.getErrorException(ex)});
                }
            }
        }

    }

    public PrecancelacionContratoDpfController() {
        // Inform the Abstract parent controller of the concrete PagoCredito?cap_first Entity        
        super(ContratoDpf.class);
    }

    /**
     * Guarda la Renovacion del DPF
     *
     * @param actionEvent
     */
    public void guardar(ActionEvent actionEvent) {

        try {

            context = RequestContext.getCurrentInstance();

            if (this.motivoPrecancelacion == null) {
                context.execute("procesandoDlg.hide()");
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionMotivo");
                MuestraMensaje.addAdvertencia(msg);
                return;
            }

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            this.fechaContrato = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_dpfs.pkm_contrato_dpf.p_precancelacion");
            llamaSP.setNumeroParametros(22);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.contratoDpfSel.getContratoDpfPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_motivo", this.motivoPrecancelacion.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_credito", this.itemsCuentaContraDpf.get(0).getCodigoCuentaCredito()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tip_pro_cre", this.itemsCuentaContraDpf.get(0).getCuentaCredito().getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.contratoDpfSel.getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_plazo_dias", (this.contratoDpfSel.getFechaVencimiento().getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000)});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_precancelado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_precancelacion", this.fechaContrato});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaContrato});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", this.contratoDpfSel.getCapital()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes_acreditado", this.interesAcreditar});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_impuesto_retenido", this.retencionPorPagar});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_porcentaje_retencion", this.contratoDpfSel.getPorcentajeRetencion()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.contratoDpfSel.getCodigoPersona()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", 1L});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PrecancelacionContratoDpfGrabado");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("PrecancelacionContratoDpfCreateDialog.hide()");
                this.init();

                //context.execute("ImprimeComprobanteDialogo.show()");
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
                    new Object[]{"precancelacionContratoDpfController", "guardar", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Prepara la renovacion del DPF
     *
     * @param actionEvent
     */
    public void preparaNuevoPrecancelacionDpf(ActionEvent actionEvent) {
        //this.prepareCreate(actionEvent);        

        this.obtieneIntereses();
        this.setItemsMotivoPrecancelacion(this.ejbFacadeMotivoPrecancelacion.getItemsEliminado('N'));
        this.itemsCuentaContraDpf = new ArrayList<CuentaContratoDpf>();
        //System.out.println("contrato "+contratoDpfSel);
        System.out.println("Codigo Contrato " + this.contratoDpfSel.getContratoDpfPK().getCodigo() + " Codigo Ifip " + this.contratoDpfSel.getContratoDpfPK().getCodigoIfip());
        //CuentaContratoDpf ccd = this.ejbFacadeCuentaContratoDpf.find(new CuentaContratoDpfPK(this.contratoDpfSel.getContratoDpfPK().getCodigo(), this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()));
        //System.out.println("Cuenca Contrato DPFS "+ccd);
        this.itemsCuentaContraDpf = this.ejbFacadeCuentaContratoDpf.itemsCodigoContratoCodigoIfip(this.contratoDpfSel.getContratoDpfPK().getCodigo(), this.contratoDpfSel.getContratoDpfPK().getCodigoIfip());
        System.out.println("itemsCuentaContraDpf " + itemsCuentaContraDpf);
        double retencionPagada = 0;
        List<PagoDpf> pagos = ejbFacadePagoDpf.getItemsPagoDpfContratoIfip(contratoDpfSel.getContratoDpfPK().getCodigo(), contratoDpfSel.getContratoDpfPK().getCodigoIfip());
        for (PagoDpf p : pagos) {
            retencionPagada = retencionPagada + p.getRetencion().doubleValue();
        }//this.setRetencion(contratoDpfSel.getPorcentajeRetencion().multiply(contratoDpfSel.getInteres()).subtract(new BigDecimal(retencionPagada)));
        retencionPorPagar = contratoDpfSel.getInteres().doubleValue() * contratoDpfSel.getPorcentajeRetencion().doubleValue() / 100 / contratoDpfSel.getPlazoDias() * ((new Date().getTime() - contratoDpfSel.getFechaContrato().getTime()) / (24 * 60 * 60 * 1000)) - retencionPagada;
        System.out.println("pagos " + pagos);
        System.out.println("contratoDpfSel.getInteres().doubleValue() " + contratoDpfSel.getInteres().doubleValue());
        System.out.println("contratoDpfSel.getPorcentajeRetencion().doubleValue() " + contratoDpfSel.getPorcentajeRetencion().doubleValue());
        System.out.println("contratoDpfSel.getPlazoDias() " + contratoDpfSel.getPlazoDias());
        System.out.println("((new Date().getTime()-contratoDpfSel.getFechaContrato().getTime())/(24*60*60*1000)) " + ((new Date().getTime() - contratoDpfSel.getFechaContrato().getTime()) / (24 * 60 * 60 * 1000)));
        System.out.println("retencionPagada " + retencionPagada);
        retencionPorPagar = formatoValor(new BigDecimal(retencionPorPagar)).doubleValue();

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

    private void obtieneIntereses() {
        try {
            this.total = new BigDecimal("0.00");
            context = RequestContext.getCurrentInstance();

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            this.fechaContrato = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_dpfs.pkm_contrato_dpf.p_obtiene_interes_precancelar");
            llamaSP.setNumeroParametros(5);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_contrato", this.contratoDpfSel.getContratoDpfPK().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.contratoDpfSel.getContratoDpfPK().getCodigoIfip()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_acreditar", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_generado", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_pagado", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {

                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                this.interesAcreditar = new BigDecimal(this.llamaSP.getListResultado().get(0).toString());
                this.interesGenerado = new BigDecimal(this.llamaSP.getListResultado().get(1).toString());
                this.interesPagado = new BigDecimal(this.llamaSP.getListResultado().get(2).toString());

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
                    new Object[]{"precancelacionContratoDpfController", "obtieneIntereses", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Obtiene los DPFS.
     */
    public void obtieneContratoDpf() {
        this.setItemsContratoDpf(this.ejbFacade.getItemsIfipPersonaEstado(ActivacionUsuario.getCodigoIfip(), this.codigoPersona, 'V'));

    }

    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimir(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        // getGeneraReporte().getParametros().put("codigoContrato", this.getSelected().getContratoRenovado().getContratoDpfPK().getCodigo());
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());

        nombreReporte = "renovacionContratoDpf";

        getGeneraReporte().exporta("/financiero/dpfs/renovacion/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.numeroDocumento) + ".pdf",
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
                } else {
                    this.itemsContratoDpf = null;
                    this.itemsRenovacionContratoDpf = null;
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
                    this.itemsRenovacionContratoDpf = null;
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoPerteneceAgencia");
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
//        this.itemsContratoDpf = this.ejbFacadeContratoDpf.getItemsRenovacionContratoDpf(ActivacionUsuario.getCodigoIfip(), this.codigoPersonaContrato, 'V', 'P');
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
     * @return the motivoPrecancelacion
     */
    public MotivoPrecancelacion getMotivoPrecancelacion() {
        return motivoPrecancelacion;
    }

    /**
     * @param motivoPrecancelacion the motivoPrecancelacion to set
     */
    public void setMotivoPrecancelacion(MotivoPrecancelacion motivoPrecancelacion) {
        this.motivoPrecancelacion = motivoPrecancelacion;
    }

    /**
     * @return the itemsMotivoPrecancelacion
     */
    public List<MotivoPrecancelacion> getItemsMotivoPrecancelacion() {
        return itemsMotivoPrecancelacion;
    }

    /**
     * @param itemsMotivoPrecancelacion the itemsMotivoPrecancelacion to set
     */
    public void setItemsMotivoPrecancelacion(List<MotivoPrecancelacion> itemsMotivoPrecancelacion) {
        this.itemsMotivoPrecancelacion = itemsMotivoPrecancelacion;
    }

    /**
     * @return the retencionPorPagar
     */
    public Double getRetencionPorPagar() {
        return retencionPorPagar;
    }

    /**
     * @param retencionPorPagar the retencionPorPagar to set
     */
    public void setRetencionPorPagar(Double retencionPorPagar) {
        this.retencionPorPagar = retencionPorPagar;
    }
}
