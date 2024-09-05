package ec.renafipse.mks.control.cajas.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.impresiones.ImpresionComprobantes;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionTransf;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.modelo.ahorros.TransferenciaEntreCuentas;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDetalle;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionTransfFacade;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TransferenciaEntreCuentasFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.RolConceptoTransaccionIfipFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
//import java.sql.Date;
import java.sql.Timestamp;
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
import org.primefaces.context.RequestContext;

@ManagedBean(name = "trasferenciaEntreCuentasBean")
@ViewScoped
public class TrasferenciaEntreCuentasBean implements Serializable {

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private RolConceptoTransaccionIfipFacade ejbFacadeConceptoTransaccionIfip;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private ConceptoTransaccionTransfFacade ejbFacadeConceptoTransaccionTransf;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private PersonaNaturalFacade ejbFacadePersonaNatural;

    @EJB
    private PersonaConyugeFacade ejbFacadeConyugePersona;

    @EJB
    private GaranteCreditoFacade ejbFacadeGaranteCredito;

    @EJB
    private TransferenciaEntreCuentasFacade ejbFacaderansferenciaEntreCuentas;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    private LlamaSP llamaSP;

    //---------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String msg;
    private String nombresSocio;
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    private String mensajeDialogo;
    private String comprobante;
    private String observaciones;
    private BigDecimal totalEfectivo;
    private BigDecimal totalCheques;
    private BigDecimal totalMovimiento;
    private String numeroCuentaCheque;
    private String numeroCheque;
    private BigDecimal valorCheque;
    private Long codigoMovimiento;
    private Timestamp fechaMovimiento;
    private String mensajeFirmantes;

    private boolean deshabilitaBotonGuardar;
    private boolean deshabilitaBotonBuscarCuenta;
    private boolean deshabilitaBotonDesgloceCheques;
    private boolean verPanelFirmantes;
    private boolean esCuentaOrigen;

    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Transaccion transaccion;
    private ConceptoTransaccion conceptoTransaccion;
    private Cuenta cuenta;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaSel;
    private MovimientoCuenta movimientoCuenta;
    private MovimientoCuentaAdicional movimientoCuentaAdicional;
    private EntidadFinanciera entidadFinanciera;
    private ChequeDepositado chequeDepositadoSel;
    private ChequeDepositado chequeDepositado;
    private GeneraReporte generaReporte;
    private RequestContext context;
    private ConceptoTransaccionTransf conceptoTransaccionTransf;
    private TransferenciaEntreCuentas tec;
    private Cuenta cuentaDestino;
    private Cuenta cuentaDestinoSel;
    private Long codigoTransferencia;
    private ImpresionComprobantes impresionComprobantes;

    private List<ProductoIfip> itemsProductoIfip;
    private List<Transaccion> itemsTransaccion;
    private List<ConceptoTransaccion> itemsConceptoTransaccion;
    private List<EntidadFinanciera> itemsEntidadFinanciera;
    private List<FirmanteCuenta> itemsFirmanteCuenta;
    private List<ConceptoTransaccionTransf> itemsConceptoTransaccionTransf;

    private List<Cuenta> itemsCuenta;
    private List<Cuenta> itemsCuentaDestino;

    @PostConstruct
    public void init() {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {

            //Instancion el proceso de llama Store Procedure
            llamaSP = new LlamaSP();

            this.setDeshabilitaBotonGuardar(true);
            this.verPanelFirmantes = false;

            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            this.moneda = this.ejbFacadeMoneda.find(Long.parseLong("1"));
            this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
            this.setProductoIfip(null);
            this.setCuenta(new Cuenta());
            this.productoIfip = null;
            this.transaccion = null;
            this.conceptoTransaccion = null;
            this.cuenta = null;
            this.itemsFirmanteCuenta = null;
            this.setTotalCheques(new BigDecimal("0.00"));
            this.setTotalEfectivo(new BigDecimal("0.00"));
            this.setTotalMovimiento(new BigDecimal("0.00"));
            this.conceptoTransaccionTransf = null;

            //this.codigoMovimiento = Long.parseLong("0");
            this.observaciones = null;
            this.numeroCuentaOrigen = null;

            this.cuentaDestinoSel = null;
            this.cuentaDestino = null;
            this.cuentaOrigen = null;

        } catch (IOException e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"trasferenciaEntreCuentasBean", "init", CapturaError.getErrorException(ex)});
            }

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"trasferenciaEntreCuentasBean", "init", CapturaError.getErrorException(e)});
        }

    }

    public void habilitaBotonGuardar() {
        ////System.out.println("Habilita boton guardar");
        this.deshabilitaBotonGuardar = !this.validaciones(false);
    }

    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR LA TRANSFERENCIA
    public void guardaTransferencia() {
        // context = RequestContext.getCurrentInstance();
        //System.out.println("Realiza Transferencia");
        context = RequestContext.getCurrentInstance();
        try {
            ////System.out.println("Guarda Movimiento");

            if (!validaciones(true)) {
                return;
            }

            context.execute("procesandoDlg.show()");

            // context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            this.fechaMovimiento = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_transferencia_enre_cuentas.p_guarda");
            llamaSP.setNumeroParametros(29);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.cuenta.getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_origen", this.cuentaOrigen.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto_origen", conceptoTransaccionTransf.getCodigoProductoOrigen()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_concepto_origen", conceptoTransaccionTransf.getCodigoConceptoOrigen()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_destino", this.cuentaDestino.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto_destino", conceptoTransaccionTransf.getCodigoProductoDestino()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_concepto_destino", conceptoTransaccionTransf.getCodigoConceptoDestino()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_concepto_transf", conceptoTransaccionTransf.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_canal", 1L});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_transferido", this.totalEfectivo});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_transferencia", fechaMovimiento});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.observaciones});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_mismo_socio", (cuentaOrigen.getCodigoSocio() == cuentaDestino.getCodigoSocio()) ? "S" : "N"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona_debito", cuentaOrigen.getSocio().getCodigoPersona().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo_debito", 1L});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona_credito", cuentaDestino.getSocio().getCodigoPersona().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo_credito", 1L});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario_debito", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario_debito", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario_debito", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario_credito", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario_credito", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario_credito", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TransferenciaRealizada");
                MuestraMensaje.addInformacion(msg);
                this.codigoTransferencia = Long.parseLong(llamaSP.getListResultado().get(6).toString());
                //context.execute("procesandoDlg.hide()");                               

                context.execute("procesandoDlg.hide()");

                if (ActivacionUsuario.getRutaImpresora() != null) {
                    context.execute("ImprimeComprobanteDialogo.show()");
                }

                this.init();
            } else {
                //context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                context.execute("procesandoDlg.hide()");
            }

        } catch (IOException ex) {
            //context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            context.execute("procesandoDlg.hide()");
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"trasferenciaEntreCuentasBean", "guardarMovimiento", CapturaError.getErrorException(ex)});
        } catch (NumberFormatException ex) {
            //context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            llamaSP.rollback();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            context.execute("procesandoDlg.hide()");

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"trasferenciaEntreCuentasBean", "guardarMovimiento", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Imprime el comprobante de la transferencia
     *
     * @param actionEvent
     */
    public void imprime(ActionEvent actionEvent) {
        // Imprimiento el comprobante                
        tec = this.ejbFacaderansferenciaEntreCuentas.find(codigoTransferencia);
        if (tec != null) {

            impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora());
            impresionComprobantes.transferenciaEntreCuenta(tec, ActivacionUsuario.getUsuario().getCodigoPersona().getNombreCompleto());

        }
    }
    // -- FIN DE TRANSFERENCIA 
    //---------------------------------------------------------------------------

    //---------------------------------------------------------------------------
    // -- METODOS DE VALIDACIONES
    public boolean validaciones(boolean muestraMensaje) {
        ////System.out.println("Validaciones");
        //Valida los campos del moviento si estan vacios o cumplen requerimientos minimos
        if (this.validaCampos()) {
            ////System.out.println("Valida Cuentas");
            //Validando que la cuenta Existe
            if (this.validaCuenta(this.numeroCuentaOrigen, true)) {
                if (this.validaCuenta(this.numeroCuentaDestino, false)) {
                    //Valida si tiene saldo en la cuenta en caso de ser debito de la cuenta
                    if (this.validaSaldoDisponibleCuenta()) {
                        return true;
                    }
                }
            }
        }

        if (muestraMensaje) {
            MuestraMensaje.addError(msg);
        }

        return false;
    }

    @SuppressWarnings("empty-statement")
    public boolean validaCampos() {
        try {
            if (this.moneda == null) {
                return false;
            }
            //System.out.println("1.1");
            if (this.productoIfip == null) {
                return false;
            }

            //System.out.println("1.2");
            if (this.conceptoTransaccionTransf == null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Transferencia") + "  " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                return false;
            }

            //System.out.println("1.3 " + cuentaDestinoSel);
            if (this.cuentaDestino == null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("CuentaDestino") + "  " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                return false;
            }

            if (this.cuentaOrigen == null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("CuentaOrigen") + "  " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                return false;
            }

            //System.out.println("1.4");
            if ((this.numeroCuentaOrigen != null) ? this.numeroCuentaOrigen.trim().isEmpty() : null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("CuentaOrigen") + "  " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                return false;
            }

            //System.out.println("1.4");
            if ((this.numeroCuentaDestino != null) ? this.numeroCuentaDestino.trim().isEmpty() : null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("CuentaDestino") + "  " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                return false;
            }

            //System.out.println("1.5");
            if ((this.totalEfectivo != null) ? this.totalEfectivo.toString().trim().isEmpty() : null) {
                this.setTotalEfectivo(new BigDecimal("0.00"));
            }

            //System.out.println("1.6");
            if (this.totalEfectivo.compareTo(new BigDecimal("0.00")) <= 0) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovmientoNoPuedeSerCero");
                return false;
            }

            //System.out.println("1.7");
            if (this.observaciones != null) {
                if (this.observaciones.trim().length() > 0 && this.observaciones.trim().length() <= 4) {
                    msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                    return false;
                }

            }

        } catch (Exception er) {
            return false;
        }

        return true;
    }

    private boolean validaSaldoDisponibleCuenta() {
        if (this.cuentaOrigen.getSaldoDisponible().subtract(totalEfectivo).compareTo(new BigDecimal("0.00")) < 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
            MuestraMensaje.addAdvertencia(msg);
            return false;
        }
        return true;
    }

    public void validaSaldoDisponible() {
        msg = null;
        //Long codigoCuenta = cuenta.getCodigo();
        //cuenta = this.ejbFacadeCuenta.find(codigoCuenta);

        if (this.cuentaOrigen.getSaldoDisponible().subtract(totalEfectivo).compareTo(new BigDecimal("0.00")) < 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
            MuestraMensaje.addAdvertencia(msg);
        }

        this.habilitaBotonGuardar();

    }

    /**
     * Valida el numero de Cuenta
     *
     * @param numeroCuenta Numero de Cuenta a validar
     * @param esCuentaOrigen Si se va a validar la cuenta Origen o Destino
     * @return TRUE correcta y FALSE incorrecta
     */
    public boolean validaCuenta(String numeroCuenta, boolean esCuentaOrigen) {
        msg = null;

        String mensajeValdaCuenta = (esCuentaOrigen) ? ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("CuentaOrigen") + " " : ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("CuentaDestino") + " ";
        List<Cuenta> listaCuenta;
        if (esCuentaOrigen) {
            System.out.println("Valida Cuenta Origen " + esCuentaOrigen + " " + numeroCuenta + " " + productoIfip);
            listaCuenta = ejbFacadeCuenta.getItemsNumero(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(),
                    this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                    numeroCuenta.trim(),
                    new Long(1));
        } else {
            System.out.println("Valida Cuenta Destino " + esCuentaOrigen + " " + numeroCuenta + " " + conceptoTransaccionTransf);
            listaCuenta = ejbFacadeCuenta.getItemsNumero(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(),
                    this.conceptoTransaccionTransf.getCodigoProductoDestino(),
                    numeroCuenta.trim(),
                    new Long(1));
        }
        System.out.println("Cuenta " + listaCuenta);
        if (listaCuenta.isEmpty()) {
            this.cuenta = null;
            if (esCuentaOrigen) {
                this.cuentaOrigen = null;
            } else {
                this.cuentaDestino = null;
            }
            msg = mensajeValdaCuenta + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuentaNoExiste");
        } else if (listaCuenta.size() == 1) {

            if (esCuentaOrigen) {
                this.cuentaOrigen = listaCuenta.get(0);
                this.numeroCuentaOrigen = this.cuentaOrigen.getNumero();
            } else {
                this.cuentaDestino = listaCuenta.get(0);
                this.numeroCuentaDestino = this.cuentaDestino.getNumero();

            }
            cuenta = (esCuentaOrigen) ? cuentaOrigen : cuentaDestino;

            if (this.cuenta.getCodigoEstado().getCodigo().toString().equals("3")) {
                msg = mensajeValdaCuenta + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaCerrada");
            } else if (String.valueOf(this.cuenta.getCodigoEstado().getIndicaActiva()).equals("N")) {
                msg = mensajeValdaCuenta + ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cuenta") + this.cuenta.getCodigoEstado().getNombre();
            }
        } else {
            this.cuenta = null;
            msg = mensajeValdaCuenta + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroMasUnaCuenta");
        }

        return (msg == null);
    }

    public void compruebaObservaciones() {
        msg = null;
        if (this.observaciones != null) {
            if (this.observaciones.trim().length() > 0 && this.observaciones.trim().length() <= 4) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                MuestraMensaje.addError(msg);
            }
            this.habilitaBotonGuardar();
        }

    }
    // FIN DE METODOS DE VALIDACIONES
    // ------------------------------------------------------------------------------

    //---------------------------------------------------------------------------
    // -- METODOS DE EVENTOS DE AJAX DE CUADROS DE TEXTO
    public void buscaCuentaOrigen() {
        this.msg = null;
        if (!validaCuenta(this.numeroCuentaOrigen, true)) {
            MuestraMensaje.addError(msg);
            this.numeroCuentaOrigen = null;
            this.cuentaOrigen = null;
        } else {
            this.buscarMoraSocio(cuentaOrigen.getCodigoSocio());
            this.buscarMoraConyuge(cuentaOrigen.getCodigoSocio());
            this.buscarMoraGarante(cuentaOrigen.getSocio().getCodigoPersona().getCodigo());
        }
        //Verificando si se habilita el boton guardar
        habilitaBotonGuardar();
    }

    public void buscaCuentaDestino() {
        this.msg = null;
        if (!validaCuenta(this.numeroCuentaDestino, false)) {
            MuestraMensaje.addError(msg);
            this.numeroCuentaDestino = null;
            this.cuentaDestino = null;
        }
        //Verificando si se habilita el boton guardar
        habilitaBotonGuardar();
    }

    /**
     * Cuando cambia el Tipo de Transferencia.
     */
    public void cambiaTransferencia() {

        this.cuentaDestino = null;
        this.numeroCuentaDestino = null;
        //Verificando si se habilita el boton guardar
        habilitaBotonGuardar();

    }

    public void cambiaCuentaDestino() {
        if (this.cuentaDestino != null) {
            this.cuentaDestinoSel = this.cuentaDestino;
        } else {
            this.cuentaDestinoSel = null;
        }

    }

    //---------------------------------------------------------------------------
    // -- METODOS DE LA LISTA DE BUSQUEDA DE CUENTAS
    public void preparaBusquedaCuentasOrigen(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        if (productoIfip == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Producto") + "  " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return;
        }
        this.nombresSocio = null;
        this.setItemsCuenta(null);
        this.esCuentaOrigen = true;
        this.cuentaOrigen = null;
        this.cuentaSel = null;
        context.execute("cuentaDialog.show()");
        this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
    }

    public void preparaBusquedaCuentasDestino(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        if (conceptoTransaccionTransf == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Transferencia") + "  " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            MuestraMensaje.addError(msg);
            return;
        }
        this.nombresSocio = null;
        this.setItemsCuenta(null);
        this.esCuentaOrigen = false;
        this.cuentaDestino = null;
        this.cuentaSel = null;
        context.execute("cuentaDialog.show()");
        this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
    }

    public void buscaCuentas() {
        if (nombresSocio.trim().isEmpty()) {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
            this.setItemsCuenta(null);
        } else if (this.nombresSocio.trim().length() <= 6) {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
            this.setItemsCuenta(null);
        } else {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta");
            this.mensajeDialogo = null;

            this.setItemsCuenta(ejbFacadeCuenta.getItemsMovimientoCuenta(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(),
                    (this.esCuentaOrigen) ? this.productoIfip.getProductoIfipPK().getCodigoTipoProducto() : this.conceptoTransaccionTransf.getCodigoProductoDestino(),
                    this.nombresSocio.toUpperCase(), 'S'));
        }
    }

    /**
     * Seleccciona la Cuenta
     *
     * @param actionEvent
     */
    public void seleccionaCuenta(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        if (this.esCuentaOrigen) {
            this.cuentaOrigen = this.cuentaSel;
            this.numeroCuentaOrigen = this.cuentaOrigen.getNumero();

            if (cuentaOrigen != null) {
                this.buscarMoraSocio(cuentaOrigen.getCodigoSocio());
                this.buscarMoraConyuge(cuentaOrigen.getCodigoSocio());
                this.buscarMoraGarante(cuentaOrigen.getSocio().getCodigoPersona().getCodigo());
            }
        } else {
            this.cuentaDestino = this.cuentaSel;
            this.numeroCuentaDestino = this.cuentaDestino.getNumero();

        }

        this.habilitaBotonGuardar();
        //context.execute("totalEfectivoTex.focus();");

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
                    List<Socio> socios = ejbFacadeSocio.getItemsSociofindByIdentificacion(listaPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona().getIdentificacion());
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
                            System.out.println(pendientes);
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
                System.out.println(pendientes);
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
                    Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), 'P');
                    List<CalculoCuotaPagar> pendientes = this.ejbFacadeCalculoCuotaPagar.getItemsCuotasVencidasCreditoIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), new Date(), 'P');
                    double deuda = 0;
                    System.out.println(pendientes);
                    for (CalculoCuotaPagar c : pendientes) {
                        deuda = deuda + c.getTotalPago().doubleValue();
                    }
                    aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioGaranteMora") + "\n";
                    aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", s.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(s.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", s.getSolicitud().getSocio().getCodigoPersona().getNombreCompleto()).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));
                    MuestraMensaje.addInformacion(aux);
                }
            }
        }
    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    // ------------------ REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * CUANDO CAMBIA EL PRODUCTO. BUSCA SI EL SOCIO TIENE UNA CUENTA DE
     * CERTIFICADOS ASIGNADA Y ESTE ACTIVA.
     */
    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
        this.setProductoIfip(null);
        this.transaccion = null;
        this.cuenta = null;
        this.conceptoTransaccion = null;
        this.numeroCuentaOrigen = null;
        this.cuenta = null;
        this.deshabilitaBotonDesgloceCheques = true;
        this.habilitaBotonGuardar();

    }

    /**
     * CUANDO CAMBIA EL PRODUCTO.
     */
    public void cambiaProductoIfip() {

        this.setItemsConceptoTransaccionTransf(this.ejbFacadeConceptoTransaccionTransf.getItemsDescripcionTransferencia(this.getProductoIfip().getProductoIfipPK().getCodigoMoneda(),
                this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto(),
                this.getProductoIfip().getProductoIfipPK().getCodigoIfip(), 'N'));

        //System.out.println("getItemsConceptoTransaccionTransf " + this.getItemsConceptoTransaccionTransf());
        //System.out.println("transferencias " + ejbFacadeConceptoTransaccionTransf.findAll());
        this.cuenta = null;
        this.conceptoTransaccionTransf = null;
        this.numeroCuentaOrigen = null;
        this.cuentaDestino = null;
        this.cuenta = null;
        this.setTotalEfectivo(new BigDecimal("0.00"));
        this.habilitaBotonGuardar();

    }

    /**
     * @return the movimientoCuenta
     */
    public MovimientoCuenta getMovimientoCuenta() {
        return movimientoCuenta;
    }

    /**
     * @param movimientoCuenta the movimientoCuenta to set
     */
    public void setMovimientoCuenta(MovimientoCuenta movimientoCuenta) {
        this.movimientoCuenta = movimientoCuenta;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the transaccion
     */
    public Transaccion getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * @return the conceptoTransaccion
     */
    public ConceptoTransaccion getConceptoTransaccion() {
        return conceptoTransaccion;
    }

    /**
     * @param conceptoTransaccion the conceptoTransaccion to set
     */
    public void setConceptoTransaccion(ConceptoTransaccion conceptoTransaccion) {
        this.conceptoTransaccion = conceptoTransaccion;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the movimientoCuentaAdicional
     */
    public MovimientoCuentaAdicional getMovimientoCuentaAdicional() {
        return movimientoCuentaAdicional;
    }

    /**
     * @param movimientoCuentaAdicional the movimientoCuentaAdicional to set
     */
    public void setMovimientoCuentaAdicional(MovimientoCuentaAdicional movimientoCuentaAdicional) {
        this.movimientoCuentaAdicional = movimientoCuentaAdicional;
    }

    /**
     * @return the itemsTransaccion
     */
    public List<Transaccion> getItemsTransaccion() {
        return itemsTransaccion;
    }

    /**
     * @param itemsTransaccion the itemsTransaccion to set
     */
    public void setItemsTransaccion(List<Transaccion> itemsTransaccion) {
        this.itemsTransaccion = itemsTransaccion;
    }

    /**
     * @return the itemsConceptoTransaccion
     */
    public List<ConceptoTransaccion> getItemsConceptoTransaccion() {
        return itemsConceptoTransaccion;
    }

    /**
     * @param itemsConceptoTransaccion the itemsConceptoTransaccion to set
     */
    public void setItemsConceptoTransaccion(List<ConceptoTransaccion> itemsConceptoTransaccion) {
        this.itemsConceptoTransaccion = itemsConceptoTransaccion;
    }

    /**
     * @return the itemsCuenta
     */
    public List<Cuenta> getItemsCuenta() {
        return itemsCuenta;
    }

    /**
     * @param itemsCuenta the itemsCuenta to set
     */
    public void setItemsCuenta(List<Cuenta> itemsCuenta) {
        this.itemsCuenta = itemsCuenta;
    }

    /**
     * @return the deshabilitaBotonGuardar
     */
    public boolean isDeshabilitaBotonGuardar() {
        return deshabilitaBotonGuardar;
    }

    /**
     * @param deshabilitaBotonGuardar the deshabilitaBotonGuardar to set
     */
    public void setDeshabilitaBotonGuardar(boolean deshabilitaBotonGuardar) {
        this.deshabilitaBotonGuardar = deshabilitaBotonGuardar;
    }

    /**
     * @return the itemsProductoIfip
     */
    public List<ProductoIfip> getItemsProductoIfip() {
        return itemsProductoIfip;
    }

    /**
     * @param itemsProductoIfip the itemsProductoIfip to set
     */
    public void setItemsProductoIfip(List<ProductoIfip> itemsProductoIfip) {
        this.itemsProductoIfip = itemsProductoIfip;
    }

    /**
     * @return the productoIfip
     */
    public ProductoIfip getProductoIfip() {
        return productoIfip;
    }

    /**
     * @param productoIfip the productoIfip to set
     */
    public void setProductoIfip(ProductoIfip productoIfip) {
        this.productoIfip = productoIfip;
    }

    /**
     * @return the nombresSocio
     */
    public String getNombresSocio() {
        return nombresSocio;
    }

    /**
     * @param nombresSocio the nombresSocio to set
     */
    public void setNombresSocio(String nombresSocio) {
        this.nombresSocio = nombresSocio;
    }

    /**
     * @return the deshabilitaBotonBuscarCuenta
     */
    public boolean isDeshabilitaBotonBuscarCuenta() {
        return deshabilitaBotonBuscarCuenta;
    }

    /**
     * @param deshabilitaBotonBuscarCuenta the deshabilitaBotonBuscarCuenta to
     * set
     */
    public void setDeshabilitaBotonBuscarCuenta(boolean deshabilitaBotonBuscarCuenta) {
        this.deshabilitaBotonBuscarCuenta = deshabilitaBotonBuscarCuenta;
    }

    /**
     * @return the deshabilitaBotonDesgloceCheques
     */
    public boolean isDeshabilitaBotonDesgloceCheques() {
        return deshabilitaBotonDesgloceCheques;
    }

    /**
     * @param deshabilitaBotonDesgloceCheques the
     * deshabilitaBotonDesgloceCheques to set
     */
    public void setDeshabilitaBotonDesgloceCheques(boolean deshabilitaBotonDesgloceCheques) {
        this.deshabilitaBotonDesgloceCheques = deshabilitaBotonDesgloceCheques;
    }

    /**
     * @return the mensajeDialogo
     */
    public String getMensajeDialogo() {
        return mensajeDialogo;
    }

    /**
     * @param mensajeDialogo the mensajeDialogo to set
     */
    public void setMensajeDialogo(String mensajeDialogo) {
        this.mensajeDialogo = mensajeDialogo;
    }

    /**
     * @return the cuentaSel
     */
    public Cuenta getCuentaSel() {
        return cuentaSel;
    }

    /**
     * @param cuentaSel the cuentaSel to set
     */
    public void setCuentaSel(Cuenta cuentaSel) {
        this.cuentaSel = cuentaSel;
    }

    /**
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
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

    /**
     * @return the totalEfectivo
     */
    public BigDecimal getTotalEfectivo() {
        return totalEfectivo;
    }

    /**
     * @param totalEfectivo the totalEfectivo to set
     */
    public void setTotalEfectivo(BigDecimal totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    /**
     * @return the totalCheques
     */
    public BigDecimal getTotalCheques() {
        return totalCheques;
    }

    /**
     * @param totalCheques the totalCheques to set
     */
    public void setTotalCheques(BigDecimal totalCheques) {
        this.totalCheques = totalCheques;
    }

    /**
     * @return the totalMovimiento
     */
    public BigDecimal getTotalMovimiento() {
        return totalMovimiento;
    }

    /**
     * @param totalMovimiento the totalMovimiento to set
     */
    public void setTotalMovimiento(BigDecimal totalMovimiento) {
        this.totalMovimiento = totalMovimiento;
    }

    /**
     * @return the itemsEntidadFinanciera
     */
    public List<EntidadFinanciera> getItemsEntidadFinanciera() {
        return itemsEntidadFinanciera;
    }

    /**
     * @param itemsEntidadFinanciera the itemsEntidadFinanciera to set
     */
    public void setItemsEntidadFinanciera(List<EntidadFinanciera> itemsEntidadFinanciera) {
        this.itemsEntidadFinanciera = itemsEntidadFinanciera;
    }

    /**
     * @return the numeroCuentaCheque
     */
    public String getNumeroCuentaCheque() {
        return numeroCuentaCheque;
    }

    /**
     * @param numeroCuentaCheque the numeroCuentaCheque to set
     */
    public void setNumeroCuentaCheque(String numeroCuentaCheque) {
        this.numeroCuentaCheque = numeroCuentaCheque;
    }

    /**
     * @return the numeroCheque
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * @return the valorCheque
     */
    public BigDecimal getValorCheque() {
        return valorCheque;
    }

    /**
     * @param valorCheque the valorCheque to set
     */
    public void setValorCheque(BigDecimal valorCheque) {
        this.valorCheque = valorCheque;
    }

    /**
     * @return the entidadFinanciera
     */
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    /**
     * @param entidadFinanciera the entidadFinanciera to set
     */
    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    /**
     * @return the chequeDepositadoSel
     */
    public ChequeDepositado getChequeDepositadoSel() {
        return chequeDepositadoSel;
    }

    /**
     * @param chequeDepositadoSel the chequeDepositadoSel to set
     */
    public void setChequeDepositadoSel(ChequeDepositado chequeDepositadoSel) {
        this.chequeDepositadoSel = chequeDepositadoSel;
    }

    /**
     * @return the chequeDepositado
     */
    public ChequeDepositado getChequeDepositado() {
        return chequeDepositado;
    }

    /**
     * @param chequeDepositado the chequeDepositado to set
     */
    public void setChequeDepositado(ChequeDepositado chequeDepositado) {
        this.chequeDepositado = chequeDepositado;
    }

    /**
     * @return the codigoMovimiento
     */
    public Long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    /**
     * @param codigoMovimiento the codigoMovimiento to set
     */
    public void setCodigoMovimiento(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    /**
     * @return the fechaMovimiento
     */
    public Timestamp getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * @param fechaMovimiento the fechaMovimiento to set
     */
    public void setFechaMovimiento(Timestamp fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
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
     * @return the verPanelFirmantes
     */
    public boolean isVerPanelFirmantes() {
        return verPanelFirmantes;
    }

    /**
     * @param verPanelFirmantes the verPanelFirmantes to set
     */
    public void setVerPanelFirmantes(boolean verPanelFirmantes) {
        this.verPanelFirmantes = verPanelFirmantes;
    }

    /**
     * @return the itemsFirmanteCuenta
     */
    public List<FirmanteCuenta> getItemsFirmanteCuenta() {
        return itemsFirmanteCuenta;
    }

    /**
     * @param itemsFirmanteCuenta the itemsFirmanteCuenta to set
     */
    public void setItemsFirmanteCuenta(List<FirmanteCuenta> itemsFirmanteCuenta) {
        this.itemsFirmanteCuenta = itemsFirmanteCuenta;
    }

    /**
     * @return the mensajeFirmantes
     */
    public String getMensajeFirmantes() {
        return mensajeFirmantes;
    }

    /**
     * @param mensajeFirmantes the mensajeFirmantes to set
     */
    public void setMensajeFirmantes(String mensajeFirmantes) {
        this.mensajeFirmantes = mensajeFirmantes;
    }

    /**
     * @return the conceptoTransaccionTransf
     */
    public ConceptoTransaccionTransf getConceptoTransaccionTransf() {
        return conceptoTransaccionTransf;
    }

    /**
     * @param conceptoTransaccionTransf the conceptoTransaccionTransf to set
     */
    public void setConceptoTransaccionTransf(ConceptoTransaccionTransf conceptoTransaccionTransf) {
        this.conceptoTransaccionTransf = conceptoTransaccionTransf;
    }

    /**
     * @return the itemsConceptoTransaccionTransf
     */
    public List<ConceptoTransaccionTransf> getItemsConceptoTransaccionTransf() {
        return itemsConceptoTransaccionTransf;
    }

    /**
     * @param itemsConceptoTransaccionTransf the itemsConceptoTransaccionTransf
     * to set
     */
    public void setItemsConceptoTransaccionTransf(List<ConceptoTransaccionTransf> itemsConceptoTransaccionTransf) {
        this.itemsConceptoTransaccionTransf = itemsConceptoTransaccionTransf;
    }

    /**
     * @return the cuentaDestino
     */
    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    /**
     * @param cuentaDestino the cuentaDestino to set
     */
    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    /**
     * @return the itemsCuentaDestino
     */
    public List<Cuenta> getItemsCuentaDestino() {
        return itemsCuentaDestino;
    }

    /**
     * @param itemsCuentaDestino the itemsCuentaDestino to set
     */
    public void setItemsCuentaDestino(List<Cuenta> itemsCuentaDestino) {
        this.itemsCuentaDestino = itemsCuentaDestino;
    }

    /**
     * @return the codigoTransferencia
     */
    public Long getCodigoTransferencia() {
        return codigoTransferencia;
    }

    /**
     * @param codigoTransferencia the codigoTransferencia to set
     */
    public void setCodigoTransferencia(Long codigoTransferencia) {
        this.codigoTransferencia = codigoTransferencia;
    }

    /**
     * @return the cuentaOrigen
     */
    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    /**
     * @param cuentaOrigen the cuentaOrigen to set
     */
    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    /**
     * @return the esCuentaOrigen
     */
    public boolean isEsCuentaOrigen() {
        return esCuentaOrigen;
    }

    /**
     * @param esCuentaOrigen the esCuentaOrigen to set
     */
    public void setEsCuentaOrigen(boolean esCuentaOrigen) {
        this.esCuentaOrigen = esCuentaOrigen;
    }

    /**
     * @return the numeroCuentaOrigen
     */
    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    /**
     * @param numeroCuentaOrigen the numeroCuentaOrigen to set
     */
    public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    /**
     * @return the numeroCuentaDestino
     */
    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    /**
     * @param numeroCuentaDestino the numeroCuentaDestino to set
     */
    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

}
