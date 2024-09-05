package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.impresiones.ImpresionComprobantes;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.PagoCredito;
import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleCuota;
import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleNot;
import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubro;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.creditos.SolicitudDetallePK;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK;
import ec.renafipse.mks.modelo.creditos.TipoMotivoPagoCredito;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.PagoCreditoDetalleCuotaFacade;
import ec.renafipse.mks.negocio.creditos.PagoCreditoDetalleNotFacade;
import ec.renafipse.mks.negocio.creditos.PagoCreditoDetalleRubroFacade;
import ec.renafipse.mks.negocio.creditos.PagoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.creditos.TablaAmortizacionFacade;
import ec.renafipse.mks.negocio.creditos.TipoMotivoPagoCreditoFacade;
import ec.renafipse.mks.negocio.seguridades.RolOpcionOperacionFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioIfipAgenciaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

/*Cambio para generar factura por notificacion de credito*/
import ec.renafipse.mks.capas.utilitario.GeneraFacturaVenta;
import ec.renafipse.mks.negocio.adquisiciones.ItemVentaFacade;
import ec.renafipse.mks.negocio.adquisiciones.ParametroCompraIfipFacade;
/*Cambio para generar factura por notificacion de credito*/

@ManagedBean(name = "pagoCreditoController")
@ViewScoped
public class PagoCreditoController extends AbstractController<PagoCredito> implements Serializable {

    @EJB
    private PagoCreditoFacade ejbFacade;

    @EJB
    private SolicitudFacade ejbFacadeSolicitud;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private TablaAmortizacionFacade ejbFacadeTablaAmortizacion;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private UsuarioIfipAgenciaFacade ejbFacadeUsuarioIfipAgencia;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    @EJB
    private RolOpcionOperacionFacade ejbFacadeRolOpcionOperacion;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private PagoCreditoDetalleCuotaFacade ejbFacadePagoCreditoDetalleCuota;

    @EJB
    private PagoCreditoDetalleNotFacade ejbFacadePagoCreditoDetalleNot;

    @EJB
    private PagoCreditoDetalleRubroFacade ejbFacadePagoCreditoDetalleRubro;

    @EJB
    private TipoMotivoPagoCreditoFacade ejbFacadeTipoMotivoPagoCredito;

    private LlamaSP llamaSP;

    private GeneraReporte generaReporte;
    private char ubicaPago;
    private char ubicaTipo;
    private char pagoTotal;
    private long ubicaCuotas;
    private long calcularDato;
    private String codigoSocio;
    private long cuotaPendi;
    private long periodoPagoCredito;
    private long codigoIngEgre;
    private long codigoPagoCredito;
    private long codigoMovimiento;
    private long codigoNotificacion;
    private long codigoRubro;
    private long codigo;
    private Long generaFormulario;
    private Long codigoFormulario;
    private BigDecimal porValorOCuota;
    private BigDecimal ubicaValor;
    private BigDecimal ubicaCostoJudicial;
    private BigDecimal ubicaSaldoCapital;
    private long ubicaDiasInteres;
    private long ubicaDiasMora;
    private long ubicaNumNotificaciones;
    private long cuenta;
    private BigDecimal ubicaCapital;
    private BigDecimal ubicaInteres;
    private BigDecimal ubicaMora;
    private BigDecimal ubicaRubro;
    private BigDecimal ubicaNotificacion;
    private BigDecimal ubicaTotal;
    private BigDecimal valorAPagar;
    private boolean visiblePanelValor;
    private boolean visiblePanelCuotas;
    private boolean visiblePanelCuotasPendientesCredito;
    private boolean visiblePanelSaldoCuentaSocio;
    private boolean pagoPor;
    private boolean saltar;
    private boolean sigueProceso;
    private boolean sigueProcesoPagoCredito;
    private Apertura apertura;
    private Socio socio;
    private Socio socioSel;
    private Solicitud solicitudSel;
    private PagoCredito pagoCreditoSel;
    private PagoCreditoDetalleCuota pagoCreditoDetalleCuotaSel;
    private Solicitud solicitud;
    private SolicitudDetalle solicitudDetalle;
    private SolicitudDetallePK solicitudDetallePk;
    private PagoCredito pagoCredito;
    private TablaAmortizacion tablaAmortizacion;
    private TablaAmortizacionPK tablaAmortizacionPK;
    private boolean deshabilitaBotonPagoParcial;
    private boolean deshabilitaBotonPagoTotal;
    private boolean deshabilitaEtiCuotasOValor;
    private boolean deshabilitarBotDesgloce;
    private boolean deshabilitaComPagoPor;
    private boolean deshabilitaFormaPago;
    private boolean deshabilitaMuestraDetalles;
    private boolean deshabilitaDetalleCuotas;
    private boolean es_parcial;
    private boolean busquedaSocioLista;
    private boolean deshabilitaMotivoPago;
    private String ubicaEtiquetaValor;
    private String ubicaEtiquetaCuota;
    private TipoMotivoPagoCredito ubicaTipoMotivo;
    private String msgCajaNoAperturada;
    private String msg;
    private String cuotasAPagar;
    private String msgSaldoCuentaSocio;
    private String ubicaNumCuotas;
    private String nombreSocio;
    private String nombreSocioBusqueda;
    private String mensajeCriterio;
    private String formaPagoID;
    private Timestamp fechaProximaPago;
    private Timestamp fechaPagoCredito;
    private Timestamp fecha;
    private char pagoTotalCredito;
    private boolean verCancelados;
    private BigDecimal totalPagoCuotaPendiente;
    private BigDecimal totalPagoCredito;
    private String totalCapital;
    private String totalCostosJudiciales;
    private String totalNotificaciones;
    private String totalRubros;
    private String totalInteres;
    private String totalMora;
    private String totalPago;
    private HashMap<String, String> resumenCredito;

    public HashMap<String, String> getResumenCredito() {
        return resumenCredito;
    }

    public void setResumenCredito(HashMap<String, String> resumenCredito) {
        this.resumenCredito = resumenCredito;
    }

    private List<Solicitud> itemsSolicitudCredito;
    private List<SolicitudDetalle> itemsSolicitudDetalleCredito;
    private List<TablaAmortizacion> itemsCuotasPendientes;
    private List<String> numeroCuotas;
    private List<Socio> itemsSocio;
    private List<PagoCredito> itemsPagoCredito;
    private List<PagoCreditoDetalleCuota> itemsPagoCreditoCuota;
    private List<PagoCreditoDetalleNot> itemsPagoCreditoNot;
    private List<PagoCreditoDetalleRubro> itemsPagoCreditoRubro;
    private List<TipoMotivoPagoCredito> itemsTipoMotivoPagoCredito;
    private List<CalculoCuotaPagar> itemsTablaCalculoCuotaPagar;
    private List<CalculoCuotaPagar> itemsTablaCalculoCuotaPagarPendiente;
    private List<TablaAmortizacion> itemsTablaAmortizacion;

    /*Cambio para generar factura por notificacion de credito*/
    @EJB
    private GeneraFacturaVenta ejbGeneraFacturaVenta;
    @EJB
    private ParametroCompraIfipFacade parametroCompraIfipFacade;
    @EJB
    private ItemVentaFacade ejbItemVentaFacade;
    /*Cambio para generar factura por notificacion de credito*/

    @PostConstruct
    public void init() {
        resumenCredito = new HashMap<String, String>();
        super.setFacade(ejbFacade);
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
//        this.codigoSocio = 0;
        this.calcularDato = 0;
        this.codigoIngEgre = 0;
        this.codigoSocio = null;
        this.codigoPagoCredito = 0;
        this.ubicaCapital = new BigDecimal("0.00");
        this.valorAPagar = new BigDecimal("0.00");
        this.ubicaValor = new BigDecimal("0.00");
        this.setPorValorOCuota(new BigDecimal("0.00"));
        this.setUbicaInteres(new BigDecimal("0.00"));
        this.setUbicaMora(new BigDecimal("0.00"));
        this.setUbicaRubro(new BigDecimal("0.00"));
        this.ubicaCostoJudicial = new BigDecimal("0.00");
        this.setUbicaNotificacion(new BigDecimal("0.00"));
        this.setUbicaTotal(new BigDecimal("0.00"));
        this.ubicaDiasInteres = 0;
        this.ubicaDiasMora = 0;
        this.ubicaNumNotificaciones = 0;
        this.deshabilitaBotonPagoParcial = true;
        this.deshabilitaBotonPagoTotal = true;
        this.deshabilitaEtiCuotasOValor = false;
        this.deshabilitarBotDesgloce = false;
        this.deshabilitaComPagoPor = true;
        this.deshabilitaFormaPago = true;
        this.deshabilitaEtiCuotasOValor = true;
        this.deshabilitaMuestraDetalles = true;
        this.deshabilitaDetalleCuotas = true;
        this.deshabilitaMotivoPago = true;
        this.es_parcial = false;
        this.ubicaEtiquetaCuota = null;
        this.ubicaEtiquetaValor = null;
        this.visiblePanelSaldoCuentaSocio = false;
        this.msgCajaNoAperturada = null;
        this.msgSaldoCuentaSocio = null;
        this.msg = null;
        this.setUbicaTipoMotivo(null);
        this.nombreSocio = null;
        this.cuotaPendi = 0;
        this.nombreSocioBusqueda = null;
        this.verCancelados = false;
        this.fechaPagoCredito = new java.sql.Timestamp(new Date().getTime());
        this.fechaProximaPago = new java.sql.Timestamp(new Date().getTime());
        this.fecha = new java.sql.Timestamp(new Date().getTime());
        this.setItemsTipoMotivoPagoCredito(this.ejbFacadeTipoMotivoPagoCredito.getItemsTipoMotivoPagoCreditoTotal('N'));

    }

    public void cargarResumenCredito() {
        //this.ubicaTipo = 'C';
        //this.ubicaPago = 'I';
        //this.porValorOCuota = new BigDecimal("1");
        //obtieneDatosPagoCreditoParcial();
        //this.obtieneDatosCreditoTotal(false);

    }

    public PagoCreditoController() throws IOException {
        // Inform the Abstract parent controller of the concrete PagoCredito?cap_first Entity
        super(PagoCredito.class);

        if (ActivacionUsuario.getCodigoComputador() == null) {
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");

            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            //Accediendo a la ventana de no permisos            
            Sesion.redireccionaPagina(url);
        }
    }

    ////********************************LOGICA****************************//
    public void conexionDatosCreditoParcial() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.obtienePagoCreditoParcialDatos()) {
                //System.out.println("Paquete Ejecutado Correctamente");
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //System.out.println("se ejecuto correctamente");
                this.deshabilitaBotonPagoParcial = false;
                context.execute("procesandoDlg.hide()");
                // context.execute("ImprimeComprobanteDialogo.show()");            
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "conexionDatosCreditoParcial", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneDatosPagoCreditoParcial() {
        if (this.ubicaTipo != 'S' && this.ubicaPago != 'S' || this.ubicaTipo == 'S' && this.ubicaPago != 'S' || this.ubicaTipo != 'S' && this.ubicaPago == 'S') {
            if (String.valueOf(this.ubicaTipo).equals("C")) {
                if ((Long.parseLong(this.porValorOCuota.toString())) > 0 && (Long.parseLong(this.porValorOCuota.toString()) <= this.solicitudSel.getNumeroCuotas())) {
                    if (String.valueOf(this.ubicaPago).equals("I")) {
                        if (this.porValorOCuota.compareTo(new BigDecimal("0.00")) > 0) {
                            conexionDatosCreditoParcial();
                        } else {
                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorAPagarMayorACero"));
                            MuestraMensaje.addAdvertencia(getMsg());
                        }
                    } else if (String.valueOf(this.ubicaPago).equals("D")) {
                        if (this.porValorOCuota.compareTo(new BigDecimal("0.00")) > 0) {
                            /**
                             * && Long.parseLong(this.porValorOCuota.toString())
                             * <=
                             * Long.parseLong(this.solicitudDetalle.getCuentaDebito().getSaldoDisponible().toString())
                             */
                            conexionDatosCreditoParcial();
                        } else {
                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorMayorACeroyMenorSaldoDisponible"));
                            MuestraMensaje.addAdvertencia(getMsg());
                        }
                    }
                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuotasMayorACero"));
                    MuestraMensaje.addAdvertencia(getMsg());
                }

            } else if (String.valueOf(this.ubicaTipo).equals("V")) {
                if (String.valueOf(this.ubicaPago).equals("I")) {
                    if (this.porValorOCuota.compareTo(new BigDecimal("0.00")) > 0) {
                        conexionDatosCreditoParcial();
                    } else {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorAPagarMayorACero"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    }
                } else if (String.valueOf(this.ubicaPago).equals("D")) {
                    if (this.porValorOCuota.compareTo(new BigDecimal("0.00")) > 0 && this.porValorOCuota.compareTo(this.solicitudDetalle.getCuentaDebito().getSaldoDisponible()) <= 0) {
                        conexionDatosCreditoParcial();
                    } else {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorMayorACeroyMenorSaldoDisponible"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    }
                }
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("TipoFormaDePago")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    //Pago Credito Parcial
    public void conexionGuardaCreditoParcial() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            if (this.realizaPagoCreditoParcial()) {
                //System.out.println("Pago realizado correctamente");
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {

                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PagoCreditoGrabado"));
                MuestraMensaje.addInformacion(getMsg());
                this.deshabilitaBotonPagoParcial = true;
                this.deshabilitaComPagoPor = true;
                this.deshabilitaEtiCuotasOValor = true;
                this.deshabilitaFormaPago = true;
                context.execute("procesandoDlg.hide()");
                context.execute("PagoCreditoParcialDialog.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
                context.execute("PF('boletosDialog').show()");
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
            context.execute("procesandoDlg.hide()");

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa

            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "conexionGuardaCreditoParcial", CapturaError.getErrorException(ex)});
        }
    }

    public void guardaPagoCreditoParcial(ActionEvent event) {
        if (String.valueOf(this.ubicaPago).equals("I")) {
            conexionGuardaCreditoParcial();
        } else if (String.valueOf(this.ubicaPago).equals("D")) {
            if (solicitudDetalle.getCuentaDebito().getSaldoDisponible().compareTo(this.ubicaTotal) >= 0) {
                conexionGuardaCreditoParcial();
            } else {
                this.deshabilitaBotonPagoTotal = true;
                this.deshabilitaFormaPago = true;
                this.deshabilitaMotivoPago = true;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SaldoCuentaSocioInsuficiente");
                MuestraMensaje.addAdvertencia(getMsg());
            }

        }
    }

    //Datos Credito Total
    public void obtieneDatosCreditoTotal(boolean seleccionaPago) {
        if (seleccionaPago) {
            if (this.ubicaTipoMotivo == null && this.ubicaPago == 'S') {

                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("TipoMotivoYFormaPago")
                        + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                MuestraMensaje.addAdvertencia(msg);

                return;

            }
        }
        RequestContext context = RequestContext.getCurrentInstance();

        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.obtienePagoCreditoTotalDatos()) {
                this.deshabilitaBotonPagoTotal = false;
            } else {
                this.deshabilitaBotonPagoTotal = true;
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //System.out.println("Se obtuvo los datos del pago credito total correctamente");
                context.execute("procesandoDlg.hide()");
                //context.execute("ImprimeComprobanteDialogo.show()");
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneDatosCreditoTotal", CapturaError.getErrorException(ex)});
        }

    }

    //Pago Credito Total
    public void conexionGuardaCreditoTotal() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.realizaPagoCreditoTotal()) {
                this.deshabilitaBotonPagoTotal = true;
                this.deshabilitaFormaPago = true;
                this.deshabilitaMotivoPago = true;
		context.execute("PF('boletosDialog').show()");
            } else {
                this.deshabilitaBotonPagoTotal = false;
                this.deshabilitaFormaPago = true;
                this.deshabilitaMotivoPago = false;
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PagoCreditoGrabado"));
                MuestraMensaje.addInformacion(getMsg());
                context.execute("procesandoDlg.hide()");
                context.execute("PagoCreditoTotalDialog.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
		context.execute("PF('boletosDialog').show()");
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
		context.execute("procesandoDlg.hide()");
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "conexionGuardaCreditoTotal", CapturaError.getErrorException(ex)});
        }
    }

    public void guardaPagoCreditoTotal(ActionEvent event) {
        if (String.valueOf(this.ubicaPago).equals("I")) {
            conexionGuardaCreditoTotal();
        } else if (String.valueOf(this.ubicaPago).equals("D")) {
            if (solicitudDetalle.getCuentaDebito().getSaldoDisponible().compareTo(this.ubicaTotal) >= 0) {
                conexionGuardaCreditoTotal();
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SaldoCuentaSocioInsuficiente");
                MuestraMensaje.addAdvertencia(getMsg());
            }
        }
    }

    public void obtieneNombreSocio() {
        //this.socio = new Socio();
        //System.out.println("this.getCodigoSocio " + getCodigoSocio());
        this.verCancelados = false;
        this.itemsSocio = this.ejbFacadeSocio.getItemsSocioCodigo(Long.parseLong(getCodigoSocio()), ActivacionUsuario.getCodigoIfip());
        this.solicitudSel = null;
        if (!itemsSocio.isEmpty()) {
            if (itemsSocio.size() == 1) {
                this.socio = this.itemsSocio.get(0);
                obtieneSolicitudCreditoSocio();
            }
            if (itemsSocio.size() > 1) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnSocioConMismoCodigo");
                MuestraMensaje.addAdvertencia(msg);
            }

        } else {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    /**
     * Metodo donde se obtiene los datos del credito del socio en base del
     * criterio de la consulta
     */
    public void obtieneSolicitudCreditoSocio() {
        if (this.getSocio() == null) {
            return;
        }
        long estadoCredito = (this.verCancelados) ? 7 : 6;
        this.solicitudSel = null;
        try {
            this.itemsSolicitudCredito = this.ejbFacadeSolicitud.getItemsSolicitudCodigoSocioCodigoIfip(Long.parseLong(getCodigoSocio()), ActivacionUsuario.getCodigoIfip(), estadoCredito);
            if (itemsSolicitudCredito == null) {
                MuestraMensaje.addAdvertencia(this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoTieneSolicitud"));
                this.itemsSolicitudCredito = null;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneSolicitudCreditoSocio", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneSolicitudNombreSocio() {
        this.verCancelados = false;
        this.deshabilitaBotonPagoParcial = true;
        this.deshabilitaBotonPagoTotal = true;
        this.deshabilitaMuestraDetalles = true;

        long estadoCredito = (this.verCancelados) ? 7 : 6;
        this.solicitudSel = null;
        try {
            if (this.socioSel != null) {
                this.codigoSocio = String.valueOf(this.getSocioSel().getSocioPK().getCodigo());
                this.setSocio(this.getSocioSel());

                this.itemsSolicitudCredito = this.ejbFacadeSolicitud.getItemsSolicitudCodigoSocioCodigoIfip(this.getSocioSel().getSocioPK().getCodigo(), ActivacionUsuario.getCodigoIfip(), estadoCredito);

                if (!itemsSolicitudCredito.isEmpty()) {
                } else {
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoTieneSolicitud");
                    MuestraMensaje.addAdvertencia(msg);
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

    public void limpiaDatos() {
        this.ubicaTipo = ' ';
        this.ubicaPago = ' ';
        this.setUbicaTipoMotivo(null);
        this.porValorOCuota = null;
    }

    public void obtieneDetalleCreditoParcial() throws IOException {
        try {
            this.pagoCreditoSel = null;
            RequestContext context = RequestContext.getCurrentInstance();

            if (this.verCancelados) {
                return;
            }
            this.deshabilitaEtiCuotasOValor = true;
            limpiaDatos();
            limpiaDatosSalida();
            this.solicitudDetallePk = new SolicitudDetallePK();
            this.solicitudDetalle = new SolicitudDetalle();
            this.tablaAmortizacion = new TablaAmortizacion();
            this.tablaAmortizacionPK = new TablaAmortizacionPK();
            if (this.getSolicitudSel() != null) {
                this.solicitudDetallePk.setNumeroCredito(this.solicitudSel.getSolicitudPK().getNumero());
                this.solicitudDetallePk.setCodigoIfip(this.solicitudSel.getSolicitudPK().getCodigoIfip());
                this.solicitudDetalle = this.ejbFacadeSolicitudDetalle.find(this.solicitudDetallePk);
                this.itemsCuotasPendientes = this.ejbFacadeTablaAmortizacion.getItemsCreditoCuotaPendiente(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'P');

                this.ubicaTipo = 'C';
                this.ubicaPago = 'D';
                this.porValorOCuota = new BigDecimal("1");
                this.obtieneDatosPagoCreditoParcial();
                this.setUbicaEtiquetaCuota(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("Cuotas"));
                this.deshabilitaFormaPago = false;
                this.deshabilitaEtiCuotasOValor = false;

            }

            context.execute("PagoCreditoParcialDialog.show()");

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneDetalleCreditoParcial", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneResumenCredito() throws IOException {
        try {
            this.pagoCreditoSel = null;
            RequestContext context = RequestContext.getCurrentInstance();

            if (this.verCancelados) {
                return;
            }
            this.deshabilitaEtiCuotasOValor = true;
            limpiaDatos();
            limpiaDatosSalida();
            this.solicitudDetallePk = new SolicitudDetallePK();
            this.solicitudDetalle = new SolicitudDetalle();
            this.tablaAmortizacion = new TablaAmortizacion();
            this.tablaAmortizacionPK = new TablaAmortizacionPK();
            if (this.getSolicitudSel() != null) {
                this.solicitudDetallePk.setNumeroCredito(this.solicitudSel.getSolicitudPK().getNumero());
                this.solicitudDetallePk.setCodigoIfip(this.solicitudSel.getSolicitudPK().getCodigoIfip());
                this.solicitudDetalle = this.ejbFacadeSolicitudDetalle.find(this.solicitudDetallePk);
                this.itemsCuotasPendientes = this.ejbFacadeTablaAmortizacion.getItemsCreditoCuotaPendiente(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'P');

                this.ubicaTipo = 'C';
                this.ubicaPago = 'D';
                this.obtieneCuotasVencidas();
                this.obtieneDatosPagoCreditoParcial();
                this.obtieneDatosResumenCredito();
                this.deshabilitaFormaPago = false;
                this.deshabilitaEtiCuotasOValor = false;

            }

            context.execute("ResumenCreditoDialog.show()");

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneDetalleCreditoParcial", CapturaError.getErrorException(ex)});
        }
    }

    /*
     * Obtiene el Valor a pagar de la cuota pendiente y total del
     * credito.
     */
    /* private void obtieneValorPagarCuotaPendienteTotalCredito() {
     RequestContext context = RequestContext.getCurrentInstance();
     try {
     context.execute("procesandoDlg.show()");
     // Cargando la conexion de BD
     llamaSP.cargaConexion();

     // Indicando que no cierre la conexion de la base de datos
     llamaSP.setCerrarConexion(false);

     // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
     llamaSP.autoCommit(false);

     // Guardando el Pago del Credito        
     llamaSP.setNombreSP("mks_creditos.pkg_calculo_cuota_pagar.p_obtiene_pago_cuo_pen_tot_cre");
     llamaSP.setNumeroParametros(4);

     llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
     llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
     llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});

     llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
     llamaSP.getListParametrosSalida().add(new Object[]{"p_total_cuota_pendiente", Types.NUMERIC});
     llamaSP.getListParametrosSalida().add(new Object[]{"p_total_credito", Types.NUMERIC});

     // Invocando al SP
     llamaSP.invocaSP();

     // Verificando que la ejecucion del proceso ha sido correcta
     if (llamaSP.isEjecucionCorrecta()) {
     // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
     llamaSP.commit();
     this.totalPagoCuotaPendiente = new BigDecimal(llamaSP.getListResultado().get(0).toString());
     this.totalPagoCredito = new BigDecimal(llamaSP.getListResultado().get(1).toString());
     llamaSP.cerrarConexion();
     llamaSP.setConexionBD(null);                
     } else {
                
     llamaSP.rollback();
     llamaSP.cerrarConexion();
     llamaSP.setConexionBD(null);
     }

     } catch (Exception ex) {
     context.execute("procesandoDlg.hide()");
     ex.printStackTrace();
     // Muestra el Mensaje del Error en la Capa
     MuestraMensaje.addErrorCapaControl();
     // Registra el error en el log del servidor
     Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
     new Object[]{"pagoCreditoController", "conexionGuardaCreditoTotal", CapturaError.getErrorException(ex)});
     }
     }
     */
    public void cuentasPendientesCredito() {
        this.visiblePanelCuotasPendientesCredito = true;
        if (this.getSolicitudSel() != null) {
            if (this.getSolicitudDetalle() != null) {
                this.itemsTablaCalculoCuotaPagarPendiente = this.ejbFacadeCalculoCuotaPagar.getItemsCalculoCuotaPendientes(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'P');
                if (!itemsCuotasPendientes.isEmpty()) {

                } else {
                    //System.out.println("No existen cuentas pendientes");
                }
            } else {

            }
        }
    }

    public void obtieneTablaAmortizacion(ActionEvent actionEvent) {

        if (this.getSolicitudSel() != null) {
            this.itemsTablaAmortizacion = this.ejbFacadeTablaAmortizacion.getItemsNumeroCreditoCodigoIfip(this.getSolicitudSel().getSolicitudPK().getNumero(), this.getSolicitudSel().getSolicitudPK().getCodigoIfip());
        }
    }

    public void obtieneTablaCalculada(ActionEvent actionEvent) {
        if (this.getSolicitudSel() != null) {
            this.itemsTablaCalculoCuotaPagar = this.ejbFacadeCalculoCuotaPagar.getItemsCalculoNumeroIfip(this.getSolicitudSel().getSolicitudPK().getNumero(), this.getSolicitudSel().getSolicitudPK().getCodigoIfip());
        }
    }

    public void validaPagoPor() throws IOException {
        if (String.valueOf(this.ubicaPago).equals("I")) {
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
            try {
                //////System.out.println("Codigo OPcion " + ActivacionUsuario.getCodigoOpcion());
                /*if (this.ejbFacadeRolOpcionOperacion.getItemsOperacionPermisosMenu(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoOpcion(), ActivacionUsuario.getCodigoIfip(), 'N').isEmpty()) {
                 ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                 //Accediendo a la ventana de no permisos            
                 Sesion.redireccionaPagina(url);
                 }*/

                List<Apertura> listaApertura = this.ejbFacadeApertura.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
                // Verifico que si tiene aperturado la caja
                if (!listaApertura.isEmpty()) {
                    if (listaApertura.size() == 1) {
                        // Validamos si la Apertura de la Caja pertenece a la Fecha de Hoy
                        if (Validaciones.validaFechaIgualHoy(listaApertura.get(0).getFechaApertura())) {
                            this.setApertura(listaApertura.get(0));
                            this.deshabilitaEtiCuotasOValor = false;
                            this.ubicaCapital = null;
                            this.ubicaInteres = null;
                            this.ubicaMora = null;
                            this.ubicaDiasInteres = 0;
                            this.ubicaDiasMora = 0;
                            this.ubicaTotal = null;
                            this.ubicaRubro = null;
                            this.ubicaNotificacion = null;
                            this.ubicaNumNotificaciones = 0;
                            this.ubicaCostoJudicial = null;
                            this.ubicaNumCuotas = null;
                            this.porValorOCuota = null;
                            this.deshabilitaEtiCuotasOValor = false;
                            if (String.valueOf(this.ubicaTipo).equals("C")) {
                                this.setPorValorOCuota(new BigDecimal("0"));
                            } else if (String.valueOf(this.ubicaTipo).equals("V")) {
                                this.setPorValorOCuota(new BigDecimal("0.00"));
                            }
                        } else {
                            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AperturaNoCorrespondeDiaActual") + " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(listaApertura.get(0).getFechaApertura()));
                            //Accediendo a la ventana de no permisos            
                            Sesion.redireccionaPagina(url);
                        }
                    } else {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioMasUnaApertura"));
                        //Accediendo a la ventana de no permisos            
                        Sesion.redireccionaPagina(url);
                    }

                } else {
                    setMsgCajaNoAperturada(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTieneAperturaCaja"));
                    MuestraMensaje.addAdvertencia(getMsgCajaNoAperturada());
                    this.deshabilitaEtiCuotasOValor = true;
                    this.deshabilitaBotonPagoParcial = true;
                    this.deshabilitarBotDesgloce = true;
                    listaApertura = this.ejbFacadeApertura.getItemsUsuarioEstado(ActivacionUsuario.getCodigoUsuario(), 'A');
                    if (!listaApertura.isEmpty()) {
                        String nombreAgencia = null;
                        if (listaApertura.size() == 1) {
                            nombreAgencia = listaApertura.get(0).getIfipAgencia().getNombre();
                        }
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCajaOtraAgencia") + " " + nombreAgencia);
                        //Accediendo a la ventana de no permisos            
                        Sesion.redireccionaPagina(url);
                    }
                }
            } catch (Exception e) {
                try {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                    //Accediendo a la ventana de no permisos
                    Sesion.redireccionaPagina(url);

                } catch (IOException ex) {
                    Logger.getLogger(PagoCreditoController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (String.valueOf(this.ubicaPago).equals("D")) {
            //Valido por Cuota o por Valor                 
            BigDecimal saldo = this.solicitudDetalle.getCuentaDebito().getSaldoDisponible();
            String saldoSocio = String.valueOf(saldo);
            setMsgSaldoCuentaSocio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SaldoDispobleCuentaSocio"));
            MuestraMensaje.addInformacion(msgSaldoCuentaSocio + saldoSocio);
            this.ubicaCapital = null;
            this.ubicaInteres = null;
            this.ubicaMora = null;
            this.ubicaDiasInteres = 0;
            this.ubicaDiasMora = 0;
            this.ubicaTotal = null;
            this.ubicaRubro = null;
            this.ubicaNotificacion = null;
            this.ubicaNumNotificaciones = 0;
            this.ubicaCostoJudicial = null;
            this.ubicaNumCuotas = null;
            this.porValorOCuota = null;
            this.deshabilitaEtiCuotasOValor = false;
            if (String.valueOf(this.ubicaTipo).equals("C")) {
                this.setPorValorOCuota(new BigDecimal("0"));
            } else if (String.valueOf(this.ubicaTipo).equals("V")) {
                this.setPorValorOCuota(new BigDecimal("0.00"));

            }
//            this.apertura=new Apertura();
//            this.apertura.setCodigo(Long.parseLong("361"));
        } else if (String.valueOf(this.ubicaPago).equals("S")) {
            this.ubicaPago = ' ';
            this.ubicaCapital = null;
            this.ubicaInteres = null;
            this.ubicaMora = null;
            this.ubicaDiasInteres = 0;
            this.ubicaDiasMora = 0;
            this.ubicaTotal = null;
            this.ubicaRubro = null;
            this.ubicaNotificacion = null;
            this.ubicaNumNotificaciones = 0;
            this.ubicaCostoJudicial = null;
            this.ubicaNumCuotas = null;
            this.porValorOCuota = null;
            this.deshabilitaEtiCuotasOValor = true;
        }

    }

    public void obtieneDetalleCreditoTotal() throws IOException {

        RequestContext context = RequestContext.getCurrentInstance();

        if (this.verCancelados) {
            return;
        }
        this.pagoCreditoSel = null;
        this.deshabilitaFormaPago = true;
        this.deshabilitaMotivoPago = false;
        limpiaDatos();
        limpiaDatosSalida();
        this.solicitudDetallePk = new SolicitudDetallePK();
        this.solicitudDetalle = new SolicitudDetalle();
        this.tablaAmortizacion = new TablaAmortizacion();
        this.tablaAmortizacionPK = new TablaAmortizacionPK();
        if (this.getSolicitudSel() != null) {
            this.solicitudDetallePk.setNumeroCredito(this.solicitudSel.getSolicitudPK().getNumero());
            this.solicitudDetallePk.setCodigoIfip(this.solicitudSel.getSolicitudPK().getCodigoIfip());
            this.solicitudDetalle = this.ejbFacadeSolicitudDetalle.find(this.solicitudDetallePk);
            this.itemsCuotasPendientes = this.ejbFacadeTablaAmortizacion.getItemsCreditoCuotaPendiente(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSolicitudPK().getCodigoIfip(), 'P');
        }

        limpiaDatosSalida();
        obtieneDatosCreditoTotal(false);
        context.execute("PagoCreditoTotalDialog.show()");

    }

    public void validaFomaPagoPor() {
        if (String.valueOf(this.ubicaPago).equals("I")) {
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
            try {
                //////System.out.println("Codigo OPcion " + ActivacionUsuario.getCodigoOpcion());
                if (this.ejbFacadeRolOpcionOperacion.getItemsOperacionPermisosMenu(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoOpcion(), ActivacionUsuario.getCodigoIfip(), 'N').isEmpty()) {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }

                if (ActivacionUsuario.getCodigoComputador() == null) {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }

                List<Apertura> listaApertura = this.ejbFacadeApertura.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
                // Verifico que si tiene aperturado la caja
                if (!listaApertura.isEmpty()) {
                    if (listaApertura.size() == 1) {
                        // Validamos si la Apertura de la Caja pertenece a la Fecha de Hoy
                        if (Validaciones.validaFechaIgualHoy(listaApertura.get(0).getFechaApertura())) {
                            this.setApertura(listaApertura.get(0));
                            limpiaDatosSalida();
                            obtieneDatosCreditoTotal(true);

                        } else {
                            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AperturaNoCorrespondeDiaActual") + " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(listaApertura.get(0).getFechaApertura()));
                            //Accediendo a la ventana de no permisos            
                            Sesion.redireccionaPagina(url);
                        }
                    } else {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioMasUnaApertura"));
                        //Accediendo a la ventana de no permisos            
                        Sesion.redireccionaPagina(url);
                    }

                } else {
                    setMsgCajaNoAperturada(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTieneAperturaCaja"));
                    MuestraMensaje.addAdvertencia(getMsgCajaNoAperturada());
                    this.deshabilitaBotonPagoTotal = true;
                    this.deshabilitarBotDesgloce = true;
                    listaApertura = this.ejbFacadeApertura.getItemsUsuarioEstado(ActivacionUsuario.getCodigoUsuario(), 'A');
                    if (!listaApertura.isEmpty()) {
                        String nombreAgencia = null;
                        if (listaApertura.size() == 1) {
                            nombreAgencia = listaApertura.get(0).getIfipAgencia().getNombre();
                        }
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCajaOtraAgencia") + " " + nombreAgencia);
                        //Accediendo a la ventana de no permisos            
                        Sesion.redireccionaPagina(url);
                    }
                }
            } catch (IOException e) {
                try {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                    //Accediendo a la ventana de no permisos
                    Sesion.redireccionaPagina(url);

                } catch (IOException ex) {
                    Logger.getLogger(PagoCreditoController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (String.valueOf(this.ubicaPago).equals("D")) {
            //Valido por Cuota o por Valor
            BigDecimal saldo = this.solicitudDetalle.getCuentaDebito().getSaldoDisponible();
            String saldoSocio = String.valueOf(saldo);
            setMsgSaldoCuentaSocio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SaldoDispobleCuentaSocio"));
            MuestraMensaje.addInformacion(msgSaldoCuentaSocio + saldoSocio);
            this.ubicaCapital = null;
            this.ubicaInteres = null;
            this.ubicaMora = null;
            this.ubicaDiasInteres = 0;
            this.ubicaDiasMora = 0;
            this.ubicaTotal = null;
            this.ubicaRubro = null;
            this.ubicaNotificacion = null;
            this.ubicaNumNotificaciones = 0;
            this.ubicaCostoJudicial = null;
            this.ubicaNumCuotas = null;
            obtieneDatosCreditoTotal(true);
        } else if (String.valueOf(this.ubicaPago).equals("S")) {
            this.ubicaPago = ' ';
            this.ubicaCapital = null;
            this.ubicaInteres = null;
            this.ubicaMora = null;
            this.ubicaDiasInteres = 0;
            this.ubicaDiasMora = 0;
            this.ubicaTotal = null;
            this.ubicaRubro = null;
            this.ubicaNotificacion = null;
            this.ubicaNumNotificaciones = 0;
            this.ubicaCostoJudicial = null;
            this.ubicaNumCuotas = null;
        }

    }

    public void validaCuotas() throws UnknownHostException {
        this.msg = null;
        if (Long.parseLong(this.porValorOCuota.toString()) != 0 || Long.parseLong(this.porValorOCuota.toString()) <= this.itemsCuotasPendientes.size()) {
            calcularDato = Long.parseLong(this.porValorOCuota.toString());
            obtienePagoCreditoParcialDatos();
        } else {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseNumeroCuotas"));
            MuestraMensaje.addInformacion(getMsg());

        }
    }

    public void validaPagoTotal() {
        if (this.solicitudDetalle.getCuotaPendiente() == this.solicitudSel.getNumeroCuotas()) {
            this.setPagoTotal('S');
        } else {
            this.setPagoTotal('N');
        }
    }

    //PAGO CREDITO PARCIAL
    public boolean obtienePagoCreditoParcialDatos() throws UnknownHostException {
        // Guardando el Pago del Credito        
        llamaSP.setNombreSP("mks_creditos.pkm_obtiene_datos_cre_parcial.p_obtiene_datos_pago_credito");
        llamaSP.setNumeroParametros(18);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        if (String.valueOf(this.ubicaTipo).equals("C")) {
            calcularDato = Math.round(this.porValorOCuota.doubleValue());
            //Datos de Pago Credito y Detalle Pago Credito
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
            //System.out.println("p_numero_credito: " + this.solicitudSel.getSolicitudPK().getNumero());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});
            //System.out.println("p_codigo_ifip: " + this.solicitudSel.getSolicitudPK().getCodigoIfip());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_forma_pago", String.valueOf(this.ubicaPago)});
            //System.out.println("p_forma_pago: " + String.valueOf(this.ubicaPago));
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuotas", calcularDato});
            //System.out.println("p_cuotas: " + calcularDato);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", null});
            //System.out.println("p_valor: " + null);
            llamaSP.getListParametrosEntrada().add(new Object[]{"pv_tipo", String.valueOf(this.ubicaTipo)});
            //System.out.println("pv_tipo: " + String.valueOf(this.ubicaTipo));
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            //Datos de Pago Credito y Detalle Pago Credito
            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_cuotas", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_capital_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_rubros_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_mora_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_notificacion_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_costo_judicial_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_interes_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_mora_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_num_notifica_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_total_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_saldo_capital", Types.NUMERIC});

        } else if (String.valueOf(this.ubicaTipo).equals("V")) {
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_forma_pago", String.valueOf(this.ubicaPago)});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuotas", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", this.porValorOCuota});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pv_tipo", String.valueOf(this.ubicaTipo)});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            //Datos de Pago Credito y Detalle Pago Credito
            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_cuotas", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_capital_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_rubros_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_mora_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_notificacion_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_costo_judicial_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_interes_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_mora_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_num_notifica_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_total_sal", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_saldo_capital", Types.NUMERIC});
        }

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.setUbicaNumCuotas((llamaSP.getListResultado().get(0).toString()).trim());
            this.setUbicaCapital(new BigDecimal(llamaSP.getListResultado().get(1).toString()));
            this.setUbicaRubro(new BigDecimal(llamaSP.getListResultado().get(2).toString()));
            this.setUbicaInteres(new BigDecimal(llamaSP.getListResultado().get(3).toString()));
            this.setUbicaMora(new BigDecimal(llamaSP.getListResultado().get(4).toString()));
            this.setUbicaNotificacion(new BigDecimal(llamaSP.getListResultado().get(5).toString()));
            this.setUbicaCostoJudicial(new BigDecimal(llamaSP.getListResultado().get(6).toString()));
            this.setUbicaDiasInteres(Long.parseLong(llamaSP.getListResultado().get(7).toString()));
            this.setUbicaDiasMora(Long.parseLong(llamaSP.getListResultado().get(8).toString()));
            this.setUbicaNumNotificaciones(Long.parseLong(llamaSP.getListResultado().get(9).toString()));
            this.setUbicaTotal(new BigDecimal(llamaSP.getListResultado().get(10).toString()));
            this.setUbicaSaldoCapital(new BigDecimal(llamaSP.getListResultado().get(11).toString()));

            this.resumenCredito.put("UbicaNumCuotas1", this.ubicaNumCuotas);
            this.resumenCredito.put("UbicaCapital1", this.ubicaCapital.toString());
            this.resumenCredito.put("UbicaRubro1", this.ubicaRubro.toString());
            this.resumenCredito.put("UbicaInteres1", this.ubicaInteres.toString());
            this.resumenCredito.put("UbicaMora1", this.ubicaMora.toString());
            this.resumenCredito.put("UbicaNotificacion1", this.ubicaNotificacion.toString());
            this.resumenCredito.put("UbicaCostoJudicial1", this.ubicaCostoJudicial.toString());
            this.resumenCredito.put("UbicaDiasInteres1", this.ubicaDiasInteres + "");
            this.resumenCredito.put("UbicaDiasMora1", this.ubicaDiasMora + "");
            this.resumenCredito.put("UbicaNumNotificaciones1", this.ubicaNumNotificaciones + "");
            this.resumenCredito.put("UbicaTotal1", this.ubicaTotal.toString());
            this.resumenCredito.put("UbicaSaldoCapital1", this.ubicaSaldoCapital.toString());
            //System.out.println("SaldoCapital: " + this.ubicaSaldoCapital);
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public long obtengoCuotas() {
        long parcial = 0;
        String cuotas = this.getUbicaNumCuotas();
        String[] numeroCuotasArray = cuotas.split(" ");
        for (int i = 0; i < numeroCuotasArray.length; i++) {
            es_parcial = numeroCuotasArray[i].endsWith("P");
        }
        if (es_parcial) {
            parcial = 1;
        } else {
            parcial = 0;
        }
        return parcial;
    }

    public boolean realizaPagoCreditoParcial() throws UnknownHostException {
        validaPagoTotal();
        long parcial_es = obtengoCuotas();
        this.cuotasAPagar = this.getUbicaNumCuotas().trim();
        this.fechaPagoCredito = new java.sql.Timestamp(new Date().getTime());
        this.calcularDato = Math.round(this.porValorOCuota.doubleValue());

        // Guardando el Pago del Credito
        llamaSP.setNombreSP("mks_creditos.pkm_pago_credito_parcial.p_guarda_pago_credito_parcial");
        llamaSP.setNumeroParametros(38);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        //Datos de Pago Credito y Detalle Pago Credito
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_cobro", this.fechaPagoCredito});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaPagoCredito});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_forma_pago", String.valueOf(this.ubicaPago)});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuotas", this.ubicaNumCuotas});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobrado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_pago_total_credito", String.valueOf(this.getPagoTotal())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo_pago", String.valueOf(this.ubicaTipo)});

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_costos_judiciales", this.ubicaCostoJudicial});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", this.ubicaCapital});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes", this.ubicaInteres});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_mora", this.ubicaMora});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_rubros", this.ubicaRubro});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_notificaciones", this.ubicaNotificacion});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_dias_interes", this.ubicaDiasInteres});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_dias_mora", this.ubicaDiasMora});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", this.ubicaTotal});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_notificaciones", this.ubicaNumNotificaciones});
        //System.out.println("p_total: " + ubicaTotal);
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_capital", this.ubicaSaldoCapital});
        //System.out.println("p_saldo_capital: " + ubicaSaldoCapital);
        if (String.valueOf(this.ubicaPago).equals("I")) {

            //Datos de Ingreso/Egreso a Caja
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.solicitudSel.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo()});
            //System.out.println("p_codigo_moneda: " + this.solicitudSel.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.apertura.getCodigo()});
            //System.out.println("p_codigo_apertura: " + this.apertura.getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "POR PAGO DE CREDITO"});
            //System.out.println("p_observaciones: " + "POR PAGO DE CREDITO");

            //Datos Movimiento Cuenta
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", null});

        } else if (String.valueOf(this.ubicaPago).equals("D")) {
            //Datos de Ingreso/Egreso a Caja
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.solicitudSel.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo()});
            //System.out.println("p_codigo_moneda: " + this.solicitudSel.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "POR PAGO DE CREDITO"});

            //Datos Movimiento Cuenta
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.solicitudDetalle.getCuentaDebito().getCodigo()});
            //System.out.println("p_codigo_cuenta: " + this.solicitudDetalle.getCuentaDebito().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.solicitudDetalle.getCuentaDebito().getTipoProducto().getCodigo()});
            //System.out.println("p_codigo_tipo_producto: " + this.solicitudDetalle.getCuentaDebito().getTipoProducto().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            //System.out.println("p_codigo_usuario: " + ActivacionUsuario.getCodigoUsuario());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            //System.out.println("p_codigo_computador: " + ActivacionUsuario.getCodigoComputador());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
            //System.out.println("p_direccion_ip: " + ObtieneInformacionCliente.obtenerDireccionIP());
        }

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_parcial", parcial_es});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.solicitudSel.getSocio().getCodigoPersona().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", 1L});

        //System.out.println("p_es_parcial: " + parcial_es);
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_movimiento", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_ing_egre", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_pago_credito", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();
        if (llamaSP.isEjecucionCorrecta()) {

            // Obtencion de parametros de salida
            this.msg = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
            this.codigoFormulario = (llamaSP.getListResultado().get(1) != null) ? Long.parseLong(llamaSP.getListResultado().get(1).toString()) : null;
            this.setGeneraFormulario((llamaSP.getListResultado().get(2) != null) ? Long.parseLong(llamaSP.getListResultado().get(2).toString()) : null);

            this.setCodigoMovimiento(Long.parseLong(llamaSP.getListResultado().get(3).toString()));
            //System.out.println("CodigoMovimiento: " + codigoMovimiento);
            this.setCodigoIngEgre(Long.parseLong(llamaSP.getListResultado().get(4).toString()));
            //System.out.println("CodigoIngEgre: " + codigoIngEgre);
            this.setCodigoPagoCredito(Long.parseLong(llamaSP.getListResultado().get(5).toString()));
            //System.out.println("CodigoPagoCredito: " + codigoPagoCredito);
        }
        return llamaSP.isEjecucionCorrecta();
    }

    //PAGO CREDITO TOTAL
    public boolean obtienePagoCreditoTotalDatos() throws UnknownHostException {
        // Guardando el Pago del Credito
        llamaSP.setNombreSP("mks_creditos.pkm_obtiene_datos_cre_total.p_obtiene_datos_credito_total");
        llamaSP.setNumeroParametros(15);
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        //Datos de Pago Credito y Detalle Pago Credito
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
        //System.out.println("p_numero_credito: " + this.solicitudSel.getSolicitudPK().getNumero());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});
        //System.out.println("p_codigo_ifip: " + this.solicitudSel.getSolicitudPK().getCodigoIfip());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_forma_pago", String.valueOf(this.ubicaPago)});
        //System.out.println("p_forma_pago: " + String.valueOf(this.ubicaPago));

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        //Datos de Pago Credito y Detalle Pago Credito        
        llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_cuotas", Types.VARCHAR});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_capital_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_rubros_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_mora_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_notificacion_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_costo_judicial_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_interes_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_mora_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_num_notifica_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_total_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_saldo_capital", Types.NUMERIC});

        //Datos de Ingreso/Egreso a Caja
//            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_ing_egre", Types.NUMERIC});
        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.setUbicaNumCuotas(llamaSP.getListResultado().get(0).toString());
            this.setUbicaCapital(new BigDecimal(llamaSP.getListResultado().get(1).toString()));
            this.setUbicaRubro(new BigDecimal(llamaSP.getListResultado().get(2).toString()));
            this.setUbicaInteres(new BigDecimal(llamaSP.getListResultado().get(3).toString()));
            this.setUbicaMora(new BigDecimal(llamaSP.getListResultado().get(4).toString()));
            this.setUbicaNotificacion(new BigDecimal(llamaSP.getListResultado().get(5).toString()));
            this.setUbicaCostoJudicial(new BigDecimal(llamaSP.getListResultado().get(6).toString()));
            this.setUbicaDiasInteres(Long.parseLong(llamaSP.getListResultado().get(7).toString()));
            this.setUbicaDiasMora(Long.parseLong(llamaSP.getListResultado().get(8).toString()));
            this.setUbicaNumNotificaciones(Long.parseLong(llamaSP.getListResultado().get(9).toString()));
            this.setUbicaTotal(new BigDecimal(llamaSP.getListResultado().get(10).toString()));
            this.setUbicaSaldoCapital(new BigDecimal(llamaSP.getListResultado().get(11).toString()));
        }
        return llamaSP.isEjecucionCorrecta();
    }

    //OBTIENE RESUMEN CREDITO
    public boolean obtieneDatosResumenCredito() throws UnknownHostException {
        // Cargando la conexion de BD
        llamaSP.cargaConexion();

        // Indicando que no cierre la conexion de la base de datos
        llamaSP.setCerrarConexion(false);

        // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
        llamaSP.autoCommit(false);
        // Guardando el Pago del Credito
        llamaSP.setNombreSP("mks_creditos.pkm_obtiene_datos_cre_total.p_obtiene_resumen_credito");
        llamaSP.setNumeroParametros(15);
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        //Datos de Pago Credito y Detalle Pago Credito
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
        //System.out.println("p_numero_credito: " + this.solicitudSel.getSolicitudPK().getNumero());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});
        //System.out.println("p_codigo_ifip: " + this.solicitudSel.getSolicitudPK().getCodigoIfip());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_forma_pago", String.valueOf(this.ubicaPago)});
        //System.out.println("p_forma_pago: " + String.valueOf(this.ubicaPago));

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        //Datos de Pago Credito y Detalle Pago Credito        
        llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_cuotas", Types.VARCHAR});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_capital_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_rubros_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_interes_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_mora_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_notificacion_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_costo_judicial_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_interes_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_dias_mora_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_num_notifica_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_total_sal", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_saldo_capital", Types.NUMERIC});

        //Datos de Ingreso/Egreso a Caja
//            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_ing_egre", Types.NUMERIC});
        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.setUbicaNumCuotas(llamaSP.getListResultado().get(0).toString());
            this.setUbicaCapital(new BigDecimal(llamaSP.getListResultado().get(1).toString()));
            this.setUbicaRubro(new BigDecimal(llamaSP.getListResultado().get(2).toString()));
            this.setUbicaInteres(new BigDecimal(llamaSP.getListResultado().get(3).toString()));
            this.setUbicaMora(new BigDecimal(llamaSP.getListResultado().get(4).toString()));
            this.setUbicaNotificacion(new BigDecimal(llamaSP.getListResultado().get(5).toString()));
            this.setUbicaCostoJudicial(new BigDecimal(llamaSP.getListResultado().get(6).toString()));
            this.setUbicaDiasInteres(Long.parseLong(llamaSP.getListResultado().get(7).toString()));
            this.setUbicaDiasMora(Long.parseLong(llamaSP.getListResultado().get(8).toString()));
            this.setUbicaNumNotificaciones(Long.parseLong(llamaSP.getListResultado().get(9).toString()));
            this.setUbicaTotal(new BigDecimal(llamaSP.getListResultado().get(10).toString()));
            this.setUbicaSaldoCapital(new BigDecimal(llamaSP.getListResultado().get(11).toString()));

            this.resumenCredito.put("UbicaNumCuotas2", this.ubicaNumCuotas);
            this.resumenCredito.put("UbicaCapital2", this.ubicaCapital.toString());
            this.resumenCredito.put("UbicaRubro2", this.ubicaRubro.toString());
            this.resumenCredito.put("UbicaInteres2", this.ubicaInteres.toString());
            this.resumenCredito.put("UbicaMora2", this.ubicaMora.toString());
            this.resumenCredito.put("UbicaNotificacion2", this.ubicaNotificacion.toString());
            this.resumenCredito.put("UbicaCostoJudicial2", this.ubicaCostoJudicial.toString());
            this.resumenCredito.put("UbicaDiasInteres2", this.ubicaDiasInteres + "");
            this.resumenCredito.put("UbicaDiasMora2", this.ubicaDiasMora + "");
            this.resumenCredito.put("UbicaNumNotificaciones2", this.ubicaNumNotificaciones + "");
            this.resumenCredito.put("UbicaTotal2", this.ubicaTotal.toString());
            this.resumenCredito.put("UbicaSaldoCapital2", this.ubicaSaldoCapital.toString());
        }
        return llamaSP.isEjecucionCorrecta();
    }

    //CUOTAS VENCIDAS
    public boolean obtieneCuotasVencidas() throws UnknownHostException {
        // Cargando la conexion de BD
        llamaSP.cargaConexion();

        // Indicando que no cierre la conexion de la base de datos
        llamaSP.setCerrarConexion(false);

        // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
        llamaSP.autoCommit(false);

        llamaSP.setNombreSP("mks_creditos.pkm_obtiene_cuotas.p_obtiene_cuotas_vencidas");
        llamaSP.setNumeroParametros(2);
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        //Datos de Cuotas Vencidas
        llamaSP.getListParametrosEntrada().add(new Object[]{"pv_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        llamaSP.getListParametrosSalida().add(new Object[]{"p_cuotas_vencidas", Types.VARCHAR});

        //Datos de Ingreso/Egreso a Caja
//            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_ing_egre", Types.NUMERIC});
        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.setPorValorOCuota(new BigDecimal(llamaSP.getListResultado().get(0).toString().equals("0") ? "1" : llamaSP.getListResultado().get(0).toString()));
            llamaSP.commit();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            
        } else {
            llamaSP.rollback();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
        }

        return llamaSP.isEjecucionCorrecta();
    }

    public boolean realizaPagoCreditoTotal() throws UnknownHostException {
        String cuotasAPagar = this.getUbicaNumCuotas().trim();
        this.fechaPagoCredito = new java.sql.Timestamp(new Date().getTime());
        // Guardando el Pago del Credito Total
        llamaSP.setNombreSP("mks_creditos.pkm_pago_credito_total.p_guarda_pago_credito_total");
        llamaSP.setNumeroParametros(39);
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        //Datos de Pago Credito y Detalle Pago Credito
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", this.solicitudSel.getSolicitudPK().getNumero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.solicitudSel.getSolicitudPK().getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_cobro", this.fechaPagoCredito});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaPagoCredito});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_forma_pago", String.valueOf(this.ubicaPago)});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuotas", cuotasAPagar});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cobrado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_pago_total_credito", String.valueOf("S")});

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_costos_judiciales", this.ubicaCostoJudicial});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_capital", this.ubicaCapital});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes", this.ubicaInteres});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_mora", this.ubicaMora});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_rubros", this.ubicaRubro});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_notificaciones", this.ubicaNotificacion});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_dias_interes", this.ubicaDiasInteres});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_dias_mora", this.ubicaDiasMora});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_notificaciones", this.ubicaNumNotificaciones});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", this.ubicaTotal});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_capital", this.ubicaSaldoCapital});

        if (String.valueOf(this.ubicaPago).equals("I")) {

            //Datos de Ingreso/Egreso a Caja
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.solicitudSel.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.apertura.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "POR PAGO DE CREDITO"});

            //Datos Movimiento Cuenta
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", null});

            //Datos Motivo Pago Credito 
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_motivo", this.ubicaTipoMotivo.getCodigo()});
            //System.out.println("codigoMotivo: " + this.ubicaTipoMotivo);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones_mot", "POR PAGO CREDITO TOTAL"});

        } else if (String.valueOf(this.ubicaPago).equals("D")) {

            //Datos de Ingreso/Egreso a Caja
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.solicitudSel.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "POR PAGO DE CREDITO"});

            //Datos Movimiento Cuenta
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.solicitudDetalle.getCuentaDebito().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.solicitudDetalle.getCuentaDebito().getTipoProducto().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});

            //Datos Motivo Pago Credito 
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_motivo", this.ubicaTipoMotivo.getCodigo()});
            //System.out.println("codigoMotivo: " + this.ubicaTipoMotivo);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones_mot", "POR PAGO CREDITO TOTAL"});

        }
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_parcial", Long.parseLong("0")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.solicitudSel.getSocio().getCodigoPersona().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", 1L});

        //System.out.println("p_es_parcial: " + parcial_es);
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_movimiento", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_ing_egre", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_pago_credito", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();
        if (llamaSP.isEjecucionCorrecta()) {

            // Obtencion de parametros de salida
            this.msg = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
            this.codigoFormulario = (llamaSP.getListResultado().get(1) != null) ? Long.parseLong(llamaSP.getListResultado().get(1).toString()) : null;
            this.setGeneraFormulario(Long.parseLong(llamaSP.getListResultado().get(2).toString()));

            this.setCodigoMovimiento(Long.parseLong(llamaSP.getListResultado().get(3).toString()));
            //System.out.println("CodigoMovimiento: " + codigoMovimiento);
            this.setCodigoIngEgre(Long.parseLong(llamaSP.getListResultado().get(4).toString()));
            //System.out.println("CodigoIngEgre: " + codigoIngEgre);
            this.setCodigoPagoCredito(Long.parseLong(llamaSP.getListResultado().get(5).toString()));
            //System.out.println("CodigoPagoCredito: " + codigoPagoCredito);
        }
        return llamaSP.isEjecucionCorrecta();

    }

    /**
     * Limpia todos los campos
     */
    public void limpiaDatosSalida() {
        this.ubicaPago = ' ';
        this.setUbicaTipoMotivo(null);
        this.ubicaCapital = null;
        this.ubicaInteres = null;
        this.ubicaMora = null;
        this.ubicaDiasInteres = 0;
        this.ubicaDiasMora = 0;
        this.ubicaTotal = null;
        this.ubicaRubro = null;
        this.ubicaNotificacion = null;
        this.ubicaNumNotificaciones = 0;
        this.ubicaCostoJudicial = null;
        this.ubicaNumCuotas = null;
        this.porValorOCuota = null;
    }

    /**
     * Valida el tipo de pago de credito ya sea por cuotas o por valor
     */
    public void validaTipoPago() {
        if (String.valueOf(this.ubicaTipo).equals("C")) {
            this.setUbicaEtiquetaCuota(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("Cuotas"));
            limpiaDatosSalida();
            this.deshabilitaFormaPago = false;
            this.deshabilitaEtiCuotasOValor = true;
            this.setPorValorOCuota(new BigDecimal("0"));
        } else if (String.valueOf(this.ubicaTipo).equals("V")) {
            this.setUbicaEtiquetaCuota(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("Valor"));
            limpiaDatosSalida();
            this.deshabilitaFormaPago = false;
            this.deshabilitaEtiCuotasOValor = true;
            this.setPorValorOCuota(new BigDecimal("0.00"));
        } else if (String.valueOf(this.ubicaTipo).equals("S")) {
            limpiaDatosSalida();
            this.ubicaPago = ' ';
            this.porValorOCuota = null;
            this.deshabilitaFormaPago = true;
            this.deshabilitaEtiCuotasOValor = true;
        }
    }

    /**
     * Obtiene todos los pagos realizados de la solicitud de credito
     *
     * @param event
     */
    public void muestraPagosRealizados(ActionEvent event) {
        try {
            this.itemsPagoCredito = this.ejbFacade.getItemsPagoCreditoSocioRealizados(this.solicitudSel.getSolicitudPK().getNumero(), this.solicitudSel.getSocio().getSocioPK().getCodigo(), this.solicitudSel.getSocio().getSocioPK().getCodigoIfip());
            if (!this.itemsPagoCredito.isEmpty()) {
                this.deshabilitaDetalleCuotas = false;
                this.obtieneTotales();
                this.obtieneCuotasVencidas();
            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistePagosCredito"));
                MuestraMensaje.addInformacion(getMsg());
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "muestraPagosRealizados", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Obtiene un listado de todos los detalles de las cuotas pagadas del
     * credito
     */
    public void obtieneDetalleCuotas() {
        try {
            this.itemsPagoCreditoCuota = this.ejbFacadePagoCreditoDetalleCuota.getItemsPagoCreditoDetalleCuota(this.pagoCreditoSel.getCodigo());
            this.itemsPagoCreditoRubro.clear();
            this.itemsPagoCreditoNot.clear();
            this.itemsPagoCreditoRubro = null;
            this.itemsPagoCreditoNot = null;
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneDetalleCuotas", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Obtiene los socios para el listado de busqueda
     */
    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(this.getNombreSocioBusqueda(), 6)) {
                if (this.isBusquedaSocioLista()) {
                    this.setSocioSel(null);
                    this.setSelected(null);
                    this.deshabilitaMuestraDetalles = true;
                }
                this.setItemsSocio(this.ejbFacadeSocio.getItemsNombresIndicaActivo(this.getNombreSocioBusqueda(), ActivacionUsuario.getCodigoIfip(), 'S'));
                this.deshabilitaMuestraDetalles = true;
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

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Detalle Rubros")) {
            try {
                this.itemsPagoCreditoRubro = this.ejbFacadePagoCreditoDetalleRubro.getItemsPagoCreditoDetalleRubro(this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCodigoPagoCredito(), this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCuota());

            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"pagoCreditoController", "obtieneDetalleRubrosNotificaciones", CapturaError.getErrorException(ex)});
            }
        } else if (event.getTab().getTitle().equals("Detalle Notificaciones")) {
            try {
                this.itemsPagoCreditoNot = this.ejbFacadePagoCreditoDetalleNot.getItemsPagoCreditoDetalleNot(this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCodigoPagoCredito(), this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCuota());
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"pagoCreditoController", "obtieneDetalleRubrosNotificaciones", CapturaError.getErrorException(ex)});
            }
        }
    }

    public void onTabClose(TabCloseEvent event) {

    }

    /**
     * Obtengo los datos de rubros y notificaciones
     */
    public void obtieneDetalleRubrosNotificaciones() {
        //System.out.println("llegooooooooo");
        try {
            this.itemsPagoCreditoRubro = this.ejbFacadePagoCreditoDetalleRubro.getItemsPagoCreditoDetalleRubro(this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCodigoPagoCredito(), this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCuota());
            this.itemsPagoCreditoNot = this.ejbFacadePagoCreditoDetalleNot.getItemsPagoCreditoDetalleNot(this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCodigoPagoCredito(), this.pagoCreditoDetalleCuotaSel.getPagoCreditoDetalleCuotaPK().getCuota());
            //System.out.println("pasoooo");
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCreditoController", "obtieneDetalleRubrosNotificaciones", CapturaError.getErrorException(ex)});
        }
    }

    public void habilitaCombo() {
        if (this.getUbicaTipoMotivo() == null) {
            this.ubicaPago = 'S';
            this.ubicaCapital = null;
            this.ubicaInteres = null;
            this.ubicaMora = null;
            this.ubicaDiasInteres = 0;
            this.ubicaDiasMora = 0;
            this.ubicaTotal = null;
            this.ubicaRubro = null;
            this.ubicaNotificacion = null;
            this.ubicaNumNotificaciones = 0;
            this.ubicaCostoJudicial = null;
            this.ubicaNumCuotas = null;
            this.deshabilitaFormaPago = true;
        } else {
            this.deshabilitaFormaPago = false;
        }

    }

    /*public void imprimePagoCredito(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
     //this.deshabilitaBotonEfectifizar = true;
     String nombreReporte;
     String extension;
     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
     FacesContext facesContext = FacesContext.getCurrentInstance();

     setGeneraReporte(new GeneraReporte());
     getGeneraReporte().setParametros(new HashMap<String, Object>());

     getGeneraReporte().getParametros().put("codigoPagoCredito", this.pagoCreditoSel.getCodigo());

     nombreReporte = "pagoCredito";
     extension = ".pdf";

     getGeneraReporte().exporta("/financiero/creditos/pagosCredito/reporte/", nombreReporte,
     nombreReporte + String.valueOf(this.pagoCreditoSel.getCodigo()) + extension,
     "PDF", externalContext, facesContext);
     }*/
    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirTabla(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/ConcederCredito/reporte/";
        //System.out.println("Imprime Concecion");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitudSel.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitudSel.getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("subReporteTablaAmortizacion", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "tablaAmortizacion"));

        nombreReporte = "tablaAmortizacionOriginal";

        getGeneraReporte().exporta("/financiero/creditos/pagosCredito/reporte/", nombreReporte,
                nombreReporte + this.solicitudSel.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
        this.guardarServicioFinanciero(this.solicitudSel.getSolicitudPK().getNumero(), "imprimirTabla");
    }

    public void imprimePagoCredito(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        //this.deshabilitaBotonEfectifizar = true;
        if (this.pagoCreditoSel != null) {
            this.codigoPagoCredito = this.pagoCreditoSel.getCodigo();
        }

        this.pagoCredito = this.ejbFacade.find(this.codigoPagoCredito);
        TablaAmortizacion ta = null;
        if (pagoCredito.getSolicitud().getCodigoEstado() == 6) {
            ta = this.ejbFacadeTablaAmortizacion.find(new TablaAmortizacionPK(pagoCredito.getSolicitud().getSolicitudPK().getNumero(), pagoCredito.getSolicitud().getSolicitudPK().getCodigoIfip(), pagoCredito.getCuotaAPagar()));
        }
        /* String nombreReporte;
         String extension;
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
         FacesContext facesContext = FacesContext.getCurrentInstance();

         setGeneraReporte(new GeneraReporte());
         getGeneraReporte().setParametros(new HashMap<String, Object>());

         getGeneraReporte().getParametros().put("codigoPagoCredito", codigoPagoCredito);

         nombreReporte = "pagoCredito";
         extension = ".pdf";

         getGeneraReporte().exporta("/financiero/creditos/pagosCredito/reporte/", nombreReporte,
         nombreReporte + String.valueOf(codigoPagoCredito) + extension,
         "PDF", externalContext, facesContext);*/

        ImpresionComprobantes impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora());
        impresionComprobantes.pagoCredito(pagoCredito, ta, pagoCredito.getCobradoPor().getCodigoPersona().getNombreCompleto());
    }

    /**
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirListadoPagosCreditos(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String directoriReporte = "/financiero/creditos/pagosCredito/reporte/";
        //System.out.println("Imprime Concecion");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("numeroCredito", this.solicitudSel.getSolicitudPK().getNumero());
        getGeneraReporte().getParametros().put("codigoIfip", this.solicitudSel.getSolicitudPK().getCodigoIfip());
        getGeneraReporte().getParametros().put("subReportelPagosCredito", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "pagosCredito"));

        nombreReporte = "listadoPagosCredito";

        getGeneraReporte().exporta("/financiero/creditos/pagosCredito/reporte/", nombreReporte,
                nombreReporte + this.solicitudSel.getSolicitudPK().getNumero() + ".pdf",
                "PDF", externalContext, facesContext);
    }

    public void limpiaTablas() {
        this.itemsPagoCreditoNot.clear();
        this.itemsPagoCreditoRubro.clear();

    }

    public void obtieneTotales() {
        double total = 0;
        for (PagoCredito pc : itemsPagoCredito) {
            total += pc.getCapital().doubleValue();
        }
        setTotalCapital(new DecimalFormat("###,###,###.##").format(total));

        total = 0;
        for (PagoCredito pc : itemsPagoCredito) {
            total += pc.getCostosJudiciales().doubleValue();
        }
        setTotalCostosJudiciales(new DecimalFormat("###,###,###.##").format(total));

        total = 0;
        for (PagoCredito pc : itemsPagoCredito) {
            total += pc.getRubros().doubleValue();
        }
        setTotalRubros(new DecimalFormat("###,###,###.##").format(total));

        total = 0;
        for (PagoCredito pc : itemsPagoCredito) {
            total += pc.getNotificaciones().doubleValue();
        }
        setTotalNotificaciones(new DecimalFormat("###,###,###.##").format(total));

        total = 0;
        for (PagoCredito pc : itemsPagoCredito) {
            total += pc.getInteres().doubleValue();
        }
        setTotalInteres(new DecimalFormat("###,###,###.##").format(total));

        total = 0;
        for (PagoCredito pc : itemsPagoCredito) {
            total += pc.getMora().doubleValue();
        }
        setTotalMora(new DecimalFormat("###,###,###.##").format(total));

        total = 0;
        for (PagoCredito pc : itemsPagoCredito) {
            total += pc.getTotal().doubleValue();
        }
        totalPago = new DecimalFormat("###,###,###.##").format(total);

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

    //FIN DE LA LOGICA 
    /**
     * @return the ubicaPago
     */
    public char getUbicaPago() {
        return ubicaPago;
    }

    /**
     * @param ubicaPago the ubicaPago to set
     */
    public void setUbicaPago(char ubicaPago) {
        this.ubicaPago = ubicaPago;
    }

    /**
     * @return the ubicaTipo
     */
    public char getUbicaTipo() {
        return ubicaTipo;
    }

    /**
     * @param ubicaTipo the ubicaTipo to set
     */
    public void setUbicaTipo(char ubicaTipo) {
        this.ubicaTipo = ubicaTipo;
    }

    /**
     * @return the visiblePanelValor
     */
    public boolean isVisiblePanelValor() {
        return visiblePanelValor;
    }

    /**
     * @param visiblePanelValor the visiblePanelValor to set
     */
    public void setVisiblePanelValor(boolean visiblePanelValor) {
        this.visiblePanelValor = visiblePanelValor;
    }

    /**
     * @return the pagoCredito
     */
    public PagoCredito getPagoCredito() {
        return pagoCredito;
    }

    /**
     * @param pagoCredito the pagoCredito to set
     */
    public void setPagoCredito(PagoCredito pagoCredito) {
        this.pagoCredito = pagoCredito;
    }

    /**
     * @return the deshabilitaBotonPagoParcial
     */
    public boolean isDeshabilitaBotonPagoParcial() {
        return deshabilitaBotonPagoParcial;
    }

    /**
     * @param deshabilitaBotonPagoParcial the deshabilitaBotonPagoParcial to set
     */
    public void setDeshabilitaBotonPagoParcial(boolean deshabilitaBotonPagoParcial) {
        this.deshabilitaBotonPagoParcial = deshabilitaBotonPagoParcial;
    }

    /**
     * @return the deshabilitaBotonPagoTotal
     */
    public boolean isDeshabilitaBotonPagoTotal() {
        return deshabilitaBotonPagoTotal;
    }

    /**
     * @param deshabilitaBotonPagoTotal the deshabilitaBotonPagoTotal to set
     */
    public void setDeshabilitaBotonPagoTotal(boolean deshabilitaBotonPagoTotal) {
        this.deshabilitaBotonPagoTotal = deshabilitaBotonPagoTotal;
    }

    /**
     * @return the numeroCuotas
     */
    public List<String> getNumeroCuotas() {
        return numeroCuotas;
    }

    /**
     * @param numeroCuotas the numeroCuotas to set
     */
    public void setNumeroCuotas(List<String> numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    /**
     * @return the visiblePanelCuotas
     */
    public boolean isVisiblePanelCuotas() {
        return visiblePanelCuotas;
    }

    /**
     * @param visiblePanelCuotas the visiblePanelCuotas to set
     */
    public void setVisiblePanelCuotas(boolean visiblePanelCuotas) {
        this.visiblePanelCuotas = visiblePanelCuotas;
    }

    /**
     * @return the pagoPor
     */
    public boolean isPagoPor() {
        return pagoPor;
    }

    /**
     * @param pagoPor the pagoPor to set
     */
    public void setPagoPor(boolean pagoPor) {
        this.pagoPor = pagoPor;
    }

    /**
     * @return the ubicaValor
     */
    public BigDecimal getUbicaValor() {
        return ubicaValor;
    }

    /**
     * @param ubicaValor the ubicaValor to set
     */
    public void setUbicaValor(BigDecimal ubicaValor) {
        this.ubicaValor = ubicaValor;
    }

    /**
     * @return the valorAPagar
     */
    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    /**
     * @param valorAPagar the valorAPagar to set
     */
    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    /**
     * @return the visiblePanelCuotasPendientesCredito
     */
    public boolean isVisiblePanelCuotasPendientesCredito() {
        return visiblePanelCuotasPendientesCredito;
    }

    /**
     * @param visiblePanelCuotasPendientesCredito the
     * visiblePanelCuotasPendientesCredito to set
     */
    public void setVisiblePanelCuotasPendientesCredito(boolean visiblePanelCuotasPendientesCredito) {
        this.visiblePanelCuotasPendientesCredito = visiblePanelCuotasPendientesCredito;
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
     * @return the cuenta
     */
    public long getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(long cuenta) {
        this.cuenta = cuenta;
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
     * @return the ubicaCostoJudicial
     */
    public BigDecimal getUbicaCostoJudicial() {
        return ubicaCostoJudicial;
    }

    /**
     * @param ubicaCostoJudicial the ubicaCostoJudicial to set
     */
    public void setUbicaCostoJudicial(BigDecimal ubicaCostoJudicial) {
        this.ubicaCostoJudicial = ubicaCostoJudicial;
    }

    /**
     * @return the ubicaInteres
     */
    public BigDecimal getUbicaInteres() {
        return ubicaInteres;
    }

    /**
     * @param ubicaInteres the ubicaInteres to set
     */
    public void setUbicaInteres(BigDecimal ubicaInteres) {
        this.ubicaInteres = ubicaInteres;
    }

    /**
     * @return the ubicaMora
     */
    public BigDecimal getUbicaMora() {
        return ubicaMora;
    }

    /**
     * @param ubicaMora the ubicaMora to set
     */
    public void setUbicaMora(BigDecimal ubicaMora) {
        this.ubicaMora = ubicaMora;
    }

    /**
     * @return the ubicaRubro
     */
    public BigDecimal getUbicaRubro() {
        return ubicaRubro;
    }

    /**
     * @param ubicaRubro the ubicaRubro to set
     */
    public void setUbicaRubro(BigDecimal ubicaRubro) {
        this.ubicaRubro = ubicaRubro;
    }

    /**
     * @return the ubicaNotificacion
     */
    public BigDecimal getUbicaNotificacion() {
        return ubicaNotificacion;
    }

    /**
     * @param ubicaNotificacion the ubicaNotificacion to set
     */
    public void setUbicaNotificacion(BigDecimal ubicaNotificacion) {
        this.ubicaNotificacion = ubicaNotificacion;
    }

    /**
     * @return the ubicaTotal
     */
    public BigDecimal getUbicaTotal() {
        return ubicaTotal;
    }

    /**
     * @param ubicaTotal the ubicaTotal to set
     */
    public void setUbicaTotal(BigDecimal ubicaTotal) {
        this.ubicaTotal = ubicaTotal;
    }

    /**
     * @return the ubicaDiasInteres
     */
    public long getUbicaDiasInteres() {
        return ubicaDiasInteres;
    }

    /**
     * @param ubicaDiasInteres the ubicaDiasInteres to set
     */
    public void setUbicaDiasInteres(long ubicaDiasInteres) {
        this.ubicaDiasInteres = ubicaDiasInteres;
    }

    /**
     * @return the ubicaDiasMora
     */
    public long getUbicaDiasMora() {
        return ubicaDiasMora;
    }

    /**
     * @param ubicaDiasMora the ubicaDiasMora to set
     */
    public void setUbicaDiasMora(long ubicaDiasMora) {
        this.ubicaDiasMora = ubicaDiasMora;
    }

    /**
     * @return the ubicaNumNotificaciones
     */
    public long getUbicaNumNotificaciones() {
        return ubicaNumNotificaciones;
    }

    /**
     * @param ubicaNumNotificaciones the ubicaNumNotificaciones to set
     */
    public void setUbicaNumNotificaciones(long ubicaNumNotificaciones) {
        this.ubicaNumNotificaciones = ubicaNumNotificaciones;
    }

    /**
     * @return the ubicaCapital
     */
    public BigDecimal getUbicaCapital() {
        return ubicaCapital;
    }

    /**
     * @param ubicaCapital the ubicaCapital to set
     */
    public void setUbicaCapital(BigDecimal ubicaCapital) {
        this.ubicaCapital = ubicaCapital;
    }

    /**
     * @return the ubicaEtiquetaCuota
     */
    public String getUbicaEtiquetaCuota() {
        return ubicaEtiquetaCuota;
    }

    /**
     * @param ubicaEtiquetaCuota the ubicaEtiquetaCuota to set
     */
    public void setUbicaEtiquetaCuota(String ubicaEtiquetaCuota) {
        this.ubicaEtiquetaCuota = ubicaEtiquetaCuota;
    }

    /**
     * @return the ubicaEtiquetaValor
     */
    public String getUbicaEtiquetaValor() {
        return ubicaEtiquetaValor;
    }

    /**
     * @param ubicaEtiquetaValor the ubicaEtiquetaValor to set
     */
    public void setUbicaEtiquetaValor(String ubicaEtiquetaValor) {
        this.ubicaEtiquetaValor = ubicaEtiquetaValor;
    }

    /**
     * @return the ubicaCuotas
     */
    public long getUbicaCuotas() {
        return ubicaCuotas;
    }

    /**
     * @param ubicaCuotas the ubicaCuotas to set
     */
    public void setUbicaCuotas(long ubicaCuotas) {
        this.ubicaCuotas = ubicaCuotas;
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
     * @return the calcularDato
     */
    public long getCalcularDato() {
        return calcularDato;
    }

    /**
     * @param calcularDato the calcularDato to set
     */
    public void setCalcularDato(long calcularDato) {
        this.calcularDato = calcularDato;
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
     * @return the cuotaPendi
     */
    public long getCuotaPendi() {
        return cuotaPendi;
    }

    /**
     * @param cuotaPendi the cuotaPendi to set
     */
    public void setCuotaPendi(long cuotaPendi) {
        this.cuotaPendi = cuotaPendi;
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
     * @return the apertura
     */
    public Apertura getApertura() {
        return apertura;
    }

    /**
     * @param apertura the apertura to set
     */
    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }

    /**
     * @return the fechaProximaPago
     */
    public Timestamp getFechaProximaPago() {
        return fechaProximaPago;
    }

    /**
     * @param fechaProximaPago the fechaProximaPago to set
     */
    public void setFechaProximaPago(Timestamp fechaProximaPago) {
        this.fechaProximaPago = fechaProximaPago;
    }

    /**
     * @return the periodoPagoCredito
     */
    public long getPeriodoPagoCredito() {
        return periodoPagoCredito;
    }

    /**
     * @param periodoPagoCredito the periodoPagoCredito to set
     */
    public void setPeriodoPagoCredito(long periodoPagoCredito) {
        this.periodoPagoCredito = periodoPagoCredito;
    }

    /**
     * @return the fechaPagoCredito
     */
    public Timestamp getFechaPagoCredito() {
        return fechaPagoCredito;
    }

    /**
     * @param fechaPagoCredito the fechaPagoCredito to set
     */
    public void setFechaPagoCredito(Timestamp fechaPagoCredito) {
        this.fechaPagoCredito = fechaPagoCredito;
    }

    /**
     * @return the codigoPagoCredito
     */
    public long getCodigoPagoCredito() {
        return codigoPagoCredito;
    }

    /**
     * @param codigoPagoCredito the codigoPagoCredito to set
     */
    public void setCodigoPagoCredito(long codigoPagoCredito) {
        this.codigoPagoCredito = codigoPagoCredito;
    }

    /**
     * @return the pagoTotalCredito
     */
    public char getPagoTotalCredito() {
        return pagoTotalCredito;
    }

    /**
     * @param pagoTotalCredito the pagoTotalCredito to set
     */
    public void setPagoTotalCredito(char pagoTotalCredito) {
        this.pagoTotalCredito = pagoTotalCredito;
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
     * @return the codigoIngEgre
     */
    public long getCodigoIngEgre() {
        return codigoIngEgre;
    }

    /**
     * @param codigoIngEgre the codigoIngEgre to set
     */
    public void setCodigoIngEgre(long codigoIngEgre) {
        this.codigoIngEgre = codigoIngEgre;
    }

    /**
     * @return the visiblePanelSaldoCuentaSocio
     */
    public boolean isVisiblePanelSaldoCuentaSocio() {
        return visiblePanelSaldoCuentaSocio;
    }

    /**
     * @param visiblePanelSaldoCuentaSocio the visiblePanelSaldoCuentaSocio to
     * set
     */
    public void setVisiblePanelSaldoCuentaSocio(boolean visiblePanelSaldoCuentaSocio) {
        this.visiblePanelSaldoCuentaSocio = visiblePanelSaldoCuentaSocio;
    }

    /**
     * @return the msgCajaNoAperturada
     */
    public String getMsgCajaNoAperturada() {
        return msgCajaNoAperturada;
    }

    /**
     * @param msgCajaNoAperturada the msgCajaNoAperturada to set
     */
    public void setMsgCajaNoAperturada(String msgCajaNoAperturada) {
        this.msgCajaNoAperturada = msgCajaNoAperturada;
    }

    /**
     * @return the msgSaldoCuentaSocio
     */
    public String getMsgSaldoCuentaSocio() {
        return msgSaldoCuentaSocio;
    }

    /**
     * @param msgSaldoCuentaSocio the msgSaldoCuentaSocio to set
     */
    public void setMsgSaldoCuentaSocio(String msgSaldoCuentaSocio) {
        this.msgSaldoCuentaSocio = msgSaldoCuentaSocio;
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
     * @return the porValorOCuota
     */
    public BigDecimal getPorValorOCuota() {
        return porValorOCuota;
    }

    /**
     * @param porValorOCuota the porValorOCuota to set
     */
    public void setPorValorOCuota(BigDecimal porValorOCuota) {
        this.porValorOCuota = porValorOCuota;
    }

    /**
     * @return the ubicaSaldoCapital
     */
    public BigDecimal getUbicaSaldoCapital() {
        return ubicaSaldoCapital;
    }

    /**
     * @param ubicaSaldoCapital the ubicaSaldoCapital to set
     */
    public void setUbicaSaldoCapital(BigDecimal ubicaSaldoCapital) {
        this.ubicaSaldoCapital = ubicaSaldoCapital;
    }

    /**
     * @return the ubicaNumCuotas
     */
    public String getUbicaNumCuotas() {
        return ubicaNumCuotas;
    }

    /**
     * @param ubicaNumCuotas the ubicaNumCuotas to set
     */
    public void setUbicaNumCuotas(String ubicaNumCuotas) {
        this.ubicaNumCuotas = ubicaNumCuotas;
    }

    /**
     * @return the codigo
     */
    public long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the cuotasAPagar
     */
    public String getCuotasAPagar() {
        return cuotasAPagar;
    }

    /**
     * @param cuotasAPagar the cuotasAPagar to set
     */
    public void setCuotasAPagar(String cuotasAPagar) {
        this.cuotasAPagar = cuotasAPagar;
    }

    /**
     * @return the deshabilitaEtiCuotasOValor
     */
    public boolean isDeshabilitaEtiCuotasOValor() {
        return deshabilitaEtiCuotasOValor;
    }

    /**
     * @param deshabilitaEtiCuotasOValor the deshabilitaEtiCuotasOValor to set
     */
    public void setDeshabilitaEtiCuotasOValor(boolean deshabilitaEtiCuotasOValor) {
        this.deshabilitaEtiCuotasOValor = deshabilitaEtiCuotasOValor;
    }

    /**
     * @return the deshabilitarBotDesgloce
     */
    public boolean isDeshabilitarBotDesgloce() {
        return deshabilitarBotDesgloce;
    }

    /**
     * @param deshabilitarBotDesgloce the deshabilitarBotDesgloce to set
     */
    public void setDeshabilitarBotDesgloce(boolean deshabilitarBotDesgloce) {
        this.deshabilitarBotDesgloce = deshabilitarBotDesgloce;
    }

    /**
     * @return the pagoTotal
     */
    public char getPagoTotal() {
        return pagoTotal;
    }

    /**
     * @param pagoTotal the pagoTotal to set
     */
    public void setPagoTotal(char pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    /**
     * @return the deshabilitaComPagoPor
     */
    public boolean isDeshabilitaComPagoPor() {
        return deshabilitaComPagoPor;
    }

    /**
     * @param deshabilitaComPagoPor the deshabilitaComPagoPor to set
     */
    public void setDeshabilitaComPagoPor(boolean deshabilitaComPagoPor) {
        this.deshabilitaComPagoPor = deshabilitaComPagoPor;
    }

    /**
     * @return the es_parcial
     */
    public boolean isEs_parcial() {
        return es_parcial;
    }

    /**
     * @param es_parcial the es_parcial to set
     */
    public void setEs_parcial(boolean es_parcial) {
        this.es_parcial = es_parcial;
    }

    /**
     * @return the codigoMovimiento
     */
    public long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    /**
     * @param codigoMovimiento the codigoMovimiento to set
     */
    public void setCodigoMovimiento(long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
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
     * @return the codigoRubro
     */
    public long getCodigoRubro() {
        return codigoRubro;
    }

    /**
     * @param codigoRubro the codigoRubro to set
     */
    public void setCodigoRubro(long codigoRubro) {
        this.codigoRubro = codigoRubro;
    }

    /**
     * @return the deshabilitaFormaPago
     */
    public boolean isDeshabilitaFormaPago() {
        return deshabilitaFormaPago;
    }

    /**
     * @param deshabilitaFormaPago the deshabilitaFormaPago to set
     */
    public void setDeshabilitaFormaPago(boolean deshabilitaFormaPago) {
        this.deshabilitaFormaPago = deshabilitaFormaPago;
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
     * @return the sigueProcesoPagoCredito
     */
    public boolean isSigueProcesoPagoCredito() {
        return sigueProcesoPagoCredito;
    }

    /**
     * @param sigueProcesoPagoCredito the sigueProcesoPagoCredito to set
     */
    public void setSigueProcesoPagoCredito(boolean sigueProcesoPagoCredito) {
        this.sigueProcesoPagoCredito = sigueProcesoPagoCredito;
    }

    /**
     * @return the itemsPagoCredito
     */
    public List<PagoCredito> getItemsPagoCredito() {
        return itemsPagoCredito;
    }

    /**
     * @param itemsPagoCredito the itemsPagoCredito to set
     */
    public void setItemsPagoCredito(List<PagoCredito> itemsPagoCredito) {
        this.itemsPagoCredito = itemsPagoCredito;
    }

    /**
     * @return the pagoCreditoSel
     */
    public PagoCredito getPagoCreditoSel() {
        return pagoCreditoSel;
    }

    /**
     * @param pagoCreditoSel the pagoCreditoSel to set
     */
    public void setPagoCreditoSel(PagoCredito pagoCreditoSel) {
        this.pagoCreditoSel = pagoCreditoSel;
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
     * @return the deshabilitaMuestraDetalles
     */
    public boolean isDeshabilitaMuestraDetalles() {
        return deshabilitaMuestraDetalles;
    }

    /**
     * @param deshabilitaMuestraDetalles the deshabilitaMuestraDetalles to set
     */
    public void setDeshabilitaMuestraDetalles(boolean deshabilitaMuestraDetalles) {
        this.deshabilitaMuestraDetalles = deshabilitaMuestraDetalles;
    }

    /**
     * @return the formaPagoID
     */
    public String getFormaPagoID() {
        return formaPagoID;
    }

    /**
     * @param formaPagoID the formaPagoID to set
     */
    public void setFormaPagoID(String formaPagoID) {
        this.formaPagoID = formaPagoID;
    }

    /**
     * @return the deshabilitaDetalleCuotas
     */
    public boolean isDeshabilitaDetalleCuotas() {
        return deshabilitaDetalleCuotas;
    }

    /**
     * @param deshabilitaDetalleCuotas the deshabilitaDetalleCuotas to set
     */
    public void setDeshabilitaDetalleCuotas(boolean deshabilitaDetalleCuotas) {
        this.deshabilitaDetalleCuotas = deshabilitaDetalleCuotas;
    }

    /**
     * @return the itemsPagoCreditoCuota
     */
    public List<PagoCreditoDetalleCuota> getItemsPagoCreditoCuota() {
        return itemsPagoCreditoCuota;
    }

    /**
     * @param itemsPagoCreditoCuota the itemsPagoCreditoCuota to set
     */
    public void setItemsPagoCreditoCuota(List<PagoCreditoDetalleCuota> itemsPagoCreditoCuota) {
        this.itemsPagoCreditoCuota = itemsPagoCreditoCuota;
    }

    /**
     * @return the itemsPagoCreditoNot
     */
    public List<PagoCreditoDetalleNot> getItemsPagoCreditoNot() {
        return itemsPagoCreditoNot;
    }

    /**
     * @param itemsPagoCreditoNot the itemsPagoCreditoNot to set
     */
    public void setItemsPagoCreditoNot(List<PagoCreditoDetalleNot> itemsPagoCreditoNot) {
        this.itemsPagoCreditoNot = itemsPagoCreditoNot;
    }

    /**
     * @return the pagoCreditoDetalleCuotaSel
     */
    public PagoCreditoDetalleCuota getPagoCreditoDetalleCuotaSel() {
        return pagoCreditoDetalleCuotaSel;
    }

    /**
     * @param pagoCreditoDetalleCuotaSel the pagoCreditoDetalleCuotaSel to set
     */
    public void setPagoCreditoDetalleCuotaSel(PagoCreditoDetalleCuota pagoCreditoDetalleCuotaSel) {
        this.pagoCreditoDetalleCuotaSel = pagoCreditoDetalleCuotaSel;
    }

    /**
     * @return the itemsPagoCreditoRubro
     */
    public List<PagoCreditoDetalleRubro> getItemsPagoCreditoRubro() {
        return itemsPagoCreditoRubro;
    }

    /**
     * @param itemsPagoCreditoRubro the itemsPagoCreditoRubro to set
     */
    public void setItemsPagoCreditoRubro(List<PagoCreditoDetalleRubro> itemsPagoCreditoRubro) {
        this.itemsPagoCreditoRubro = itemsPagoCreditoRubro;
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
     * @return the deshabilitaMotivoPago
     */
    public boolean isDeshabilitaMotivoPago() {
        return deshabilitaMotivoPago;
    }

    /**
     * @param deshabilitaMotivoPago the deshabilitaMotivoPago to set
     */
    public void setDeshabilitaMotivoPago(boolean deshabilitaMotivoPago) {
        this.deshabilitaMotivoPago = deshabilitaMotivoPago;
    }

    /**
     * @return the itemsTipoMotivoPagoCredito
     */
    public List<TipoMotivoPagoCredito> getItemsTipoMotivoPagoCredito() {
        return itemsTipoMotivoPagoCredito;
    }

    /**
     * @param itemsTipoMotivoPagoCredito the itemsTipoMotivoPagoCredito to set
     */
    public void setItemsTipoMotivoPagoCredito(List<TipoMotivoPagoCredito> itemsTipoMotivoPagoCredito) {
        this.itemsTipoMotivoPagoCredito = itemsTipoMotivoPagoCredito;
    }

    /**
     * @return the ubicaTipoMotivo
     */
    public TipoMotivoPagoCredito getUbicaTipoMotivo() {
        return ubicaTipoMotivo;
    }

    /**
     * @param ubicaTipoMotivo the ubicaTipoMotivo to set
     */
    public void setUbicaTipoMotivo(TipoMotivoPagoCredito ubicaTipoMotivo) {
        this.ubicaTipoMotivo = ubicaTipoMotivo;
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
     * @return the verCancelados
     */
    public boolean isVerCancelados() {
        return verCancelados;
    }

    /**
     * @param verCancelados the verCancelados to set
     */
    public void setVerCancelados(boolean verCancelados) {
        this.verCancelados = verCancelados;
    }

    /**
     * @return the itemsTablaCalculoCuotaPagar
     */
    public List<CalculoCuotaPagar> getItemsTablaCalculoCuotaPagar() {
        return itemsTablaCalculoCuotaPagar;
    }

    /**
     * @param itemsTablaCalculoCuotaPagar the itemsTablaCalculoCuotaPagar to set
     */
    public void setItemsTablaCalculoCuotaPagar(List<CalculoCuotaPagar> itemsTablaCalculoCuotaPagar) {
        this.itemsTablaCalculoCuotaPagar = itemsTablaCalculoCuotaPagar;
    }

    /**
     * @return the itemsTablaCalculoCuotaPagarPendiente
     */
    public List<CalculoCuotaPagar> getItemsTablaCalculoCuotaPagarPendiente() {
        return itemsTablaCalculoCuotaPagarPendiente;
    }

    /**
     * @param itemsTablaCalculoCuotaPagarPendiente the
     * itemsTablaCalculoCuotaPagarPendiente to set
     */
    public void setItemsTablaCalculoCuotaPagarPendiente(List<CalculoCuotaPagar> itemsTablaCalculoCuotaPagarPendiente) {
        this.itemsTablaCalculoCuotaPagarPendiente = itemsTablaCalculoCuotaPagarPendiente;
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
     * @param totalCapital the totalCapital to set
     */
    public void setTotalCapital(String totalCapital) {
        this.totalCapital = totalCapital;
    }

    /**
     * @param totalCostosJudiciales the totalCostosJudiciales to set
     */
    public void setTotalCostosJudiciales(String totalCostosJudiciales) {
        this.totalCostosJudiciales = totalCostosJudiciales;
    }

    /**
     * @param totalNotificaciones the totalNotificaciones to set
     */
    public void setTotalNotificaciones(String totalNotificaciones) {
        this.totalNotificaciones = totalNotificaciones;
    }

    /**
     * @param totalRubros the totalRubros to set
     */
    public void setTotalRubros(String totalRubros) {
        this.totalRubros = totalRubros;
    }

    /**
     * @param totalInteres the totalInteres to set
     */
    public void setTotalInteres(String totalInteres) {
        this.totalInteres = totalInteres;
    }

    /**
     * @param totalMora the totalMora to set
     */
    public void setTotalMora(String totalMora) {
        this.totalMora = totalMora;
    }

    /**
     * @return the totalPago
     */
    public String getTotalPago() {
        return totalPago;
    }

    /**
     * @param totalPago the totalPago to set
     */
    public void setTotalPago(String totalPago) {
        this.totalPago = totalPago;
    }

    /**
     * @return the totalCapital
     */
    public String getTotalCapital() {
        return totalCapital;
    }

    /**
     * @return the totalCostosJudiciales
     */
    public String getTotalCostosJudiciales() {
        return totalCostosJudiciales;
    }

    /**
     * @return the totalNotificaciones
     */
    public String getTotalNotificaciones() {
        return totalNotificaciones;
    }

    /**
     * @return the totalRubros
     */
    public String getTotalRubros() {
        return totalRubros;
    }

    /**
     * @return the totalInteres
     */
    public String getTotalInteres() {
        return totalInteres;
    }

    /**
     * @return the totalMora
     */
    public String getTotalMora() {
        return totalMora;
    }

    /**
     * @return the generaFormulario
     */
    public long getGeneraFormulario() {
        return generaFormulario;
    }

    /**
     * @param generaFormulario the generaFormulario to set
     */
    public void setGeneraFormulario(Long generaFormulario) {
        this.generaFormulario = generaFormulario;
    }

    /**
     * @return the codigoFormulario
     */
    public Long getCodigoFormulario() {
        return codigoFormulario;
    }

    /**
     * @param codigoFormulario the codigoFormulario to set
     */
    public void setCodigoFormulario(long codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }
}
