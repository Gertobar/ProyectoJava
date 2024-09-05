package ec.renafipse.mks.control.cajas.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.impresiones.ImpresionComprobantes;
import ec.renafipse.mks.capas.impresiones.ImpresionLibreta;
import ec.renafipse.mks.capas.utilitario.TransformaNumeroALetras;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.ahorros.bean.LicitudFondosBean;
import ec.renafipse.mks.modelo.ahorros.BloqueoCuenta;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.modelo.ahorros.LicitudFonCptoTran;
import ec.renafipse.mks.modelo.ahorros.LicitudFonOrgDest;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormularioAdi;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormulario;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosModulo;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.modelo.ahorros.ParametroGeneralAhorroIfip;
import ec.renafipse.mks.modelo.ahorros.ParametroGeneralAhorroIfipPK;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.cajas.DesgloceBilletes;
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
import ec.renafipse.mks.negocio.ahorros.BloqueoCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.FirmanteCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ImpresionLibretaCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFonCptoTranFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFonOrgDestFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosFormularioAdiFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosFormularioFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosModuloFacade;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaAdicionalFacade;
import ec.renafipse.mks.negocio.ahorros.ParametroGeneralAhorroIfipFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.cajas.ChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.DesgloceBilletesFacade;
import ec.renafipse.mks.negocio.cajas.PeriodoEfeCheEntFinFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import ec.renafipse.mks.negocio.creditos.SolicitudDetalleFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.RolConceptoTransaccionIfipFacade;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
//import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

@ManagedBean(name = "movimientoCuentaBean")
@ViewScoped
public class MovimientoCuentaBean implements Serializable {

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
    private MovimientoCuentaAdicionalFacade ejbFacadeMovimientoCuentaAdicional;

    @EJB
    private ChequeDepositadoFacade ejbFacadeChequeDepositado;

    @EJB
    private FirmanteCuentaFacade ejbFacadeFirmanteCuenta;

    @EJB
    private PeriodoEfeCheEntFinFacade ejbFacadePerEfeCheEntFin;

    @EJB
    private ParametroGeneralAhorroIfipFacade ejbFacadeParametroGeneralAhorroIfip;

    @EJB
    private LicitudFondosFormularioFacade ejbFacadeLicitudFondosFormulario;

    @EJB
    private LicitudFonOrgDestFacade ejbFacadeLicitudFonOrgDest;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private LicitudFondosFormularioAdiFacade ejbFacadeLicitudFondosFormularioAdi;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private PersonaNaturalFacade ejbFacadePersonaNatural;

    @EJB
    private PersonaConyugeFacade ejbFacadeConyugePersona;

    @EJB
    private SolicitudDetalleFacade ejbFacadeSolicitudDetalle;

    @EJB
    private GaranteCreditoFacade ejbFacadeGaranteCredito;

    @EJB
    private CalculoCuotaPagarFacade ejbFacadeCalculoCuotaPagar;

    @EJB
    private ImpresionLibretaCuentaFacade ejbFacadeImpresionLibretaCuenta;

    @EJB
    private LicitudFondosModuloFacade ejbFacadeLicitudFondosModulo;

    @EJB
    private LicitudFonCptoTranFacade ejbFacadeLicitudFonCptoTran;

    private LlamaSP llamaSP;

    //---------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    @ManagedProperty("#{licitudFondosBean}")
    private LicitudFondosBean licitudFondosBean;

    private String msg;
    private String nombresSocio;
    private String numeroCuenta;
    private String mensajeDialogo;
    private String comprobante;
    private String observaciones;
    private BigDecimal totalEfectivo;
    private BigDecimal totalCheques;
    private BigDecimal totalMovimiento;
    private String numeroCuentaCheque;
    private String numeroCheque;
    private BigDecimal valorCheque;
    private int numeroCheques;
    private Long codigoMovimiento;
    private Timestamp fechaMovimiento;
    private Long codigoApertura;
    private String mensajeFirmantes;
    private long generaFormulario;
    private long codigoFormulario;
    private int tamanoCaractresCriterio;

    private boolean deshabilitaBotonGuardar;
    private boolean deshabilitaBotonBuscarCuenta;
    private boolean deshabilitaBotonDesgloceCheques;
    private boolean verPanelFirmantes;
    private boolean deshabilitaIdentificacionPersonaFirma;
    private boolean deshabilitaNombrePersonaFirma;

    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Transaccion transaccion;
    private ConceptoTransaccion conceptoTransaccion;
    private Cuenta cuenta;
    private Cuenta cuentaSel;
    private MovimientoCuenta movimientoCuenta;
    private MovimientoCuentaAdicional movimientoCuentaAdicional;
    private EntidadFinanciera entidadFinanciera;
    private ChequeDepositado chequeDepositadoSel;
    private ChequeDepositado chequeDepositado;
    private GeneraReporte generaReporte;
    private RequestContext context;
    private LicitudFonOrgDest licitudFonOrgDest;
    private LicitudFondosFormulario licitudFondosFormulario;
    private LicitudFondosFormularioAdi licitudFondosFormaluarioAdi;
    private LicitudFondosModulo licitudFondosModulo;
    private Persona personaFirmaLicitud;
    private Persona persona;

    // Variables de impresion
    boolean empezoImpresion;
    boolean esReverso;
    private int numeroLineasAnverso;
    private int numeroLineasReverso;
    private boolean imprimeEnArchivo;
    private boolean deshabilitaBotonGuardarLicitud;
    private int formatoLibreta;
    private ImpresionLibreta libreta;

    private List<ProductoIfip> itemsProductoIfip;
    private List<Transaccion> itemsTransaccion;
    private List<ConceptoTransaccion> itemsConceptoTransaccion;
    private List<EntidadFinanciera> itemsEntidadFinanciera;
    private List<FirmanteCuenta> itemsFirmanteCuenta;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalImpresas;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalNoImpresas;
    private List<LicitudFonOrgDest> itemsLicitudFonOrgDest;
    private List<Cuenta> itemsCuenta;
    private List<ChequeDepositado> itemsChequeDepositado;

    //Variables para el control de la persona quien esta realizando la transaccion
    private String visualizaPersonaTransacciona;
    private String visualizaComponetePersonaTransacciona;
    private boolean socioTransacciona;
    private String identificacionPersonaTransacciona;
    private String nombrePersonaTransacciona;
    private Long codigoPersonaTransacciona;
    private boolean vaciarPersonaTransacciona;

    private boolean dobleImpresion;

    private List<BloqueoCuenta> listaBloqueoCuenta;
    @EJB
    private BloqueoCuentaFacade bloqueoCuentaFacade;

    @EJB
    private DesgloceBilletesFacade ejbDesgloceBilletesFacade;
    private List<DesgloceBilletes> listaDesgloceBilletes = new ArrayList<>();
    //validacion para mostrar el dialogo de billetes de alta deniminicacion        
    private boolean mostraBotonGuardar;
    private boolean mostraDialogoDesgloceBilletesGuardar;

    @PostConstruct
    public void init() {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {

            listaBloqueoCuenta = null;
            ParametroGeneralAhorroIfip pga;

            tamanoCaractresCriterio = 15;

            // Colocando el modulo de liciytud de fondos
            licitudFondosModulo = this.ejbFacadeLicitudFondosModulo.find(1L);

            // Verificando que exista el modulo de licitud de fondos
            if (licitudFondosModulo == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ModuloLicitudFondosNoExiste"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            //Instancion el proceso de llama Store Procedure
            llamaSP = new LlamaSP();

            this.setDeshabilitaBotonGuardar(true);
            this.verPanelFirmantes = false;

            ////System.out.println("Direccion IP " + ObtieneInformacionCliente.obtenerDireccionIP() + " Codigo Computador " + ActivacionUsuario.getCodigoComputador());
            /*if (this.ejbFacadeRolOpcionOperacion.getItemsOperacionPermisosMenu(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoOpcion(), ActivacionUsuario.getCodigoIfip(), 'N').isEmpty()) {
             ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
             //Accediendo a la ventana de no permisos            
             Sesion.redireccionaPagina(url);
             }*/
            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            ////System.out.println("PAsa computador");
            List<Apertura> listaApertura = this.ejbFacadeApertura.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
            // Verifico que si tiene aperturado la caja
            if (!listaApertura.isEmpty()) {
                if (listaApertura.size() == 1) {
                    // Validamos si la Apertura de la Caja pertenece a la Fecha de Hoy
                    if (Validaciones.validaFechaIgualHoy(listaApertura.get(0).getFechaApertura())) {

                        // Obteniendo el numero de lineas que tiene la libreta al anverso y reverso
                        pga = this.ejbFacadeParametroGeneralAhorroIfip.find(new ParametroGeneralAhorroIfipPK(Long.parseLong("1"), ActivacionUsuario.getCodigoIfip()));
                        if (pga != null) {
                            this.numeroLineasAnverso = pga.getValorNumerico().intValue();
                            pga = this.ejbFacadeParametroGeneralAhorroIfip.find(new ParametroGeneralAhorroIfipPK(Long.parseLong("2"), ActivacionUsuario.getCodigoIfip()));

                            if (pga != null) {
                                this.numeroLineasReverso = pga.getValorNumerico().intValue();
                            } else {
                                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoNumeroLineasReverso"));
                                //Accediendo a la ventana de no permisos            
                                Sesion.redireccionaPagina(url);
                            }
                        } else {
                            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoNumeroLineasAnverso"));
                            //Accediendo a la ventana de no permisos            
                            Sesion.redireccionaPagina(url);
                        }

                        this.codigoApertura = listaApertura.get(0).getCodigo();
                        this.setMovimientoCuenta(new MovimientoCuenta());
                        this.setMovimientoCuentaAdicional(new MovimientoCuentaAdicional());
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
                        this.setItemsChequeDepositado(new ArrayList<ChequeDepositado>());
                        numeroCheques = 0;
                        //this.codigoMovimiento = Long.parseLong("0");
                        this.comprobante = null;
                        this.observaciones = null;
                        this.numeroCuenta = null;
                        this.numeroCuentaCheque = null;
                        this.numeroCheque = null;
                        this.setItemsLicitudFonOrgDest(this.ejbFacadeLicitudFonOrgDest.getItemsEsOrigenEliminado('S', 'N'));
                        //Obtiene parametro 5 para Doble Impresion
                        pga = this.ejbFacadeParametroGeneralAhorroIfip.find(new ParametroGeneralAhorroIfipPK(Long.parseLong("5"), ActivacionUsuario.getCodigoIfip()));
                        this.dobleImpresion = (pga != null) ? pga.getValorTexto().equals("S") : false;

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
                listaApertura = this.ejbFacadeApertura.getItemsUsuarioEstado(ActivacionUsuario.getCodigoUsuario(), 'A');
                if (!listaApertura.isEmpty()) {
                    String nombreAgencia = null;
                    if (listaApertura.size() == 1) {
                        nombreAgencia = listaApertura.get(0).getIfipAgencia().getNombre();
                    }
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCajaOtraAgencia") + " " + nombreAgencia);
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                } else {

                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioSinAperturaCaja"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }
            }

            pga = this.ejbFacadeParametroGeneralAhorroIfip.find(new ParametroGeneralAhorroIfipPK(Long.parseLong("3"), ActivacionUsuario.getCodigoIfip()));
            if (pga == null) {
                this.setFormatoLibreta(1);
            } else {
                this.setFormatoLibreta(pga.getValorNumerico().intValue());
            }

            //Control de la persona quien esta transaccionando
            visualizaPersonaTransacciona = "display: none";
            visualizaComponetePersonaTransacciona = "display: none";
            socioTransacciona = false;
            identificacionPersonaTransacciona = "";
            nombrePersonaTransacciona = "";
            codigoPersonaTransacciona = Long.valueOf("0");
            vaciarPersonaTransacciona = true;
            //Control de la persona quien esta transaccionando

        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"movimientoCuentaBean", "init", CapturaError.getErrorException(ex)});
            }

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoCuentaBean", "init", CapturaError.getErrorException(e)});
        }

    }

    public void habilitaBotonGuardar() {
        ////System.out.println("Habilita boton guardar");
        this.deshabilitaBotonGuardar = !this.validaciones(false);
    }

    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR EL MOVIMIENTO
    public void guardaMovimiento(ActionEvent actionEvent) throws IOException {
        //context = RequestContext.getCurrentInstance();
        try {
            if (!validaPersonaTransacciona()) {
                msg = "Debe seleccionar la persona quien realiza la transaccion";
                MuestraMensaje.addError(msg);
                return;
            }

            ///System.out.println("cuenta en guardar movimiento " + cuenta);
            this.licitudFondosFormaluarioAdi = new LicitudFondosFormularioAdi();
            personaFirmaLicitud = new Persona();
            deshabilitaBotonGuardarLicitud = false;
            this.msg = null;

            //context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaMovimientoCuenta()) {

                ////System.out.println("Guarda Cheques");
                if (this.guardaDesgloceCheque()) {
                    //this.guardaMovimientoCaja();
                    if (!this.guardaMovimientoCuentaPersonaTransacciona()) {
                        //context.execute("procesandoDlg.hide()");
                        llamaSP.rollback();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                        return;
                    }
                }

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                guardaDesgloceBilletesAltaDenominacion();
                //Guardamos los billetes de alta denominacion
                
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                this.deshabilitaBotonGuardar = true;
                RequestContext.getCurrentInstance().update("display,MovimientoCuentaFor");

                // Iniciando la impresion de la libreta
                libreta = new ImpresionLibreta(ActivacionUsuario.getRutaImpresora());

                // //System.out.println("MOvimiento guardado ");
                MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado"));

                // Si existen un mensaje de la licitud de fondos se la coloca
                if (this.msg != null) {
                    MuestraMensaje.addAdvertencia(msg);
                }
                //context.execute("procesandoDlg.hide()");

                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);

                // Llamando al formulario de licitud de fondos
                this.impresionFormularioLicitudFondos();

                //}
                //context.execute("ImprimeComprobanteDialogo.show()");
                //this.init();
                //context.execute("MovimientoBovedaFor.update()");
            } else {
                //context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (UnknownHostException ex) {
            llamaSP.rollback();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            //context.execute("procesandoDlg.hide()");

            //ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoBovedaController", "guardarMovimiento", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * GUARDA EL MOVIMIENTO DE LA CUENTA, AFECTANDO A LA CUENTA DEL SOCIO Y SUS
     * MOVIMIENTOS
     *
     * @return
     * @throws UnknownHostException
     */
    private boolean guardaMovimientoCuenta() throws UnknownHostException {

        this.fechaMovimiento = new java.sql.Timestamp(new Date().getTime());
        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_ahorros.pkm_movimiento_cuenta.p_guarda_movimiento_cuenta");
        llamaSP.setNumeroParametros(22);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.cuenta.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.productoIfip.getProductoIfipPK().getCodigoTipoProducto()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.productoIfip.getProductoIfipPK().getCodigoMoneda()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_concepto", this.conceptoTransaccion.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_movimiento", this.getFechaMovimiento()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_efectivo", this.totalEfectivo});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_cheques", this.totalCheques});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_movimiento", this.totalMovimiento});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_comprobante", this.comprobante});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.observaciones});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.codigoApertura});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.cuenta.getSocio().getCodigoPersona().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", licitudFondosModulo.getCodigo()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_movimiento", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.msg = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
            this.codigoFormulario = (llamaSP.getListResultado().get(1) != null) ? Long.parseLong(llamaSP.getListResultado().get(1).toString()) : null;
            this.generaFormulario = Long.parseLong(llamaSP.getListResultado().get(2).toString());
            this.codigoMovimiento = Long.parseLong(llamaSP.getListResultado().get(3).toString());
            //ActivacionUsuario.setCodigoFormularioLicitudFondos(codigoFormulario);
        }
        //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "localException consulta****codigoApertura*********************** " + this.codigoApertura);            
        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * GUARDA EL DESGLOCE DE BILLETES DE ALTA DENOMINACION     
     *
     * @return verdadero si la ejecucion fue correcta 
     * @throws UnknownHostException
     */
    private boolean guardaDesgloceBilletesAltaDenominacion() throws UnknownHostException {
        if (listaDesgloceBilletes.isEmpty()) {
            return false;
        }
        if (listaDesgloceBilletes.size() > 0) {
            for (DesgloceBilletes desgloceBilletes : listaDesgloceBilletes) {
                try {
                    llamaSP.setNombreSP("MKS_CAJAS.pgk_desgloce_billetes_al_den.p_inserta_des_bil_alt_den");
                    llamaSP.setNumeroParametros(4);
                    llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movimiento", codigoMovimiento});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_monto", desgloceBilletes.getMonto()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_serie", desgloceBilletes.getSerie()});
                    llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                    llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});                    
                    llamaSP.invocaSP();
                    if (llamaSP.isEjecucionCorrecta()) {
                        this.msg = (llamaSP.getListResultado().get(0) != null) ? llamaSP.getListResultado().get(0).toString() : null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * GUARDA EL DESGLOCE DE LOS CHEQUES REGISTRADOS EN EL MOVIMIENTO.
     *
     * @return
     */
    private boolean guardaDesgloceCheque() {

        if (this.totalCheques.compareTo(new BigDecimal("0.00")) > 0
                && !this.itemsChequeDepositado.isEmpty()
                && String.valueOf(this.transaccion.getTipo()).equals("C")) {
            llamaSP.setNombreSP("mks_cajas.pkm_cheque_depositado.p_guarda_cheque_depositado");
            llamaSP.setNumeroParametros(7);
            // Insertando degloce del movimiento 
            for (ChequeDepositado cd : this.itemsChequeDepositado) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movimiento", this.getCodigoMovimiento()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_movimiento", this.getFechaMovimiento()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_entidad_financiera", cd.getEntidadFinanciera().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuenta", cd.getNumeroCuenta()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque", cd.getNumeroCheque()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", cd.getValor()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                    break;
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Boton que cierra el formulario
     */
    public void cierraFormulario() {
        context = RequestContext.getCurrentInstance();
        context.execute("FormularioLicitudFondosDialogo.hide()");
        if (this.conceptoTransaccion.getImprimeEnLibreta() == 'S') {
            context.execute("ImprimirLibretaDialogo.show()");
        } else {
            context.execute("ImprimirComprobanteDialogo.show()");
        }
    }

    //-----------------------------------------------------------------------------------------
    //-- IMPRTIME LIBRETA
    @SuppressWarnings("empty-statement")
    public void imprimirAnverso(ActionEvent actionEvent) throws IOException {
        context = RequestContext.getCurrentInstance();
        this.itemsMovimientoCuentaAdicionalImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta(), 'S');
        int lineasImpresas = ejbFacadeImpresionLibretaCuenta.getUltimaLineaImpresaLibreta(this.cuenta.getNumeroLibreta()) + 1;
        //System.out.println("Numero Lineas Impresas: " + lineasImpresas);
        libreta.setLineaLibreta(lineasImpresas);
        this.itemsMovimientoCuentaAdicionalNoImpresas = this.ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');
        libreta.setMsg(null);
        libreta.setNumeroLineasAnverso(numeroLineasAnverso);
        libreta.setNumeroLineasReverso(numeroLineasReverso);
        libreta.setItemsMovimientoCuentaAdicionalImpresas(itemsMovimientoCuentaAdicionalImpresas);
        libreta.setItemsMovimientoCuentaAdicionalNoImpresas(itemsMovimientoCuentaAdicionalNoImpresas);
        if (itemsFirmanteCuenta == null) {
            itemsFirmanteCuenta = new ArrayList<FirmanteCuenta>();
        }
        libreta.setItemsFirmanteCuenta(itemsFirmanteCuenta);
        libreta.setFormatoLibreta(formatoLibreta);
        libreta.setCuenta(cuenta);
        libreta.imprimirAnverso();
        context.execute("procesandoDlg.hide()");
        if (libreta.isImpresionCorrecta()) {
            if (libreta.isMuestraDialogoNuevaCartola()) {
                context.execute("ImprimeNuevaLibretaDialogo.show()");
            } else {
                if (libreta.isMuestraDialogoReverso()) {
                    //  MostramosMensaje de dar vuelta a la libreta
                    context.execute("ImprimeReversoDialogo.show()");
                } else {
                    context.execute("ImprimirComprobanteDialogo.show()");
                }
            }
        }

    }

    /**
     * Imprime el rerverso
     *
     * @param actionEvent
     */
    @SuppressWarnings("empty-statement")
    public void imprimirReverso(ActionEvent actionEvent) {

        context = RequestContext.getCurrentInstance();

        libreta.setMsg(null);
        libreta.setNumeroLineasAnverso(numeroLineasAnverso);
        libreta.setNumeroLineasReverso(numeroLineasReverso);
        if (itemsFirmanteCuenta == null) {
            itemsFirmanteCuenta = new ArrayList<FirmanteCuenta>();
        }
        libreta.setItemsFirmanteCuenta(itemsFirmanteCuenta);
        // Obteniendo los movimientos impresos
        this.itemsMovimientoCuentaAdicionalImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta(), 'S');
        // Obteniendo los movimientos NO Impresoso
        this.itemsMovimientoCuentaAdicionalNoImpresas = this.ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');

        libreta.setItemsMovimientoCuentaAdicionalImpresas(itemsMovimientoCuentaAdicionalImpresas);
        libreta.setItemsMovimientoCuentaAdicionalNoImpresas(itemsMovimientoCuentaAdicionalNoImpresas);
        //libreta.setItemsFirmanteCuenta(listaFirmanteCuenta);
        libreta.setFormatoLibreta(formatoLibreta);
        libreta.setCuenta(cuenta);

        // Enviando a imprimir el anverso de la cara de la Cartola
        libreta.imprimirReverso();

        context.execute("procesandoDlg.hide()");

        if (libreta.isImpresionCorrecta()) {

            // Si hay que imprimir el reverso
            if (libreta.isMuestraDialogoNuevaCartola()) {
                //  MostramosMensaje de dar vuelta a la libreta
                context.execute("ImprimeNuevaLibretaDialogo.show()");
            } else {
                context.execute("ImprimirComprobanteDialogo.show()");
            }
        }

    }

    public void aceptarAsignacionNuevaLibreta() {
        context.execute("ImprimirComprobanteDialogo.show()");
    }

    public String LPad(String str, Integer length) {
        //return String.format("%1$-" + ((length-str.length()) > 0 ? length-str.length() : 1) + "s", "")+str;

        return String.format("%1$" + length + "s", str);
    }

    public String RPad(String str, Integer length) {
        return String.format("%1$-" + length + "s", str);
        //return String.format("%" + (length - str.length()) + "s", "") + str;
    }

    //-- FIN DE IMPRESION DE LIBRETA
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //-- IMPRESION DE COMPROBANTE
    public void imprimirComprobante(ActionEvent actionEvent) throws IOException {
        this.imprimeEnArchivo = true;
        context = RequestContext.getCurrentInstance();

        context.execute("ImprimirComprobanteDialogo.hide()");

        context.execute("procesandoDlg.show()");

        ImpresionComprobantes impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora(), nombrePersonaTransacciona);
        //impresionComprobantes.setMensajeAdicional(cargarMensaje(1l,codigoMovimiento));
        impresionComprobantes.movimientoCuenta(this.ejbFacadeMovimientoCuentaAdicional.find(codigoMovimiento));

        // Incia Datos
        context.execute("procesandoDlg.hide()");

        if (this.conceptoTransaccion.getImprimeEnLibreta() == 'S') {
            this.init();
        } else if (this.dobleImpresion) {
            context.execute("ReimprimirComprobanteDialogo.show()");
        }

    }

    /**
     *
     * @param actionEvent
     */
    public void actualizaFomulario(ActionEvent actionEvent) {
        RequestContext.getCurrentInstance().update("display,MovimientoCuentaFor");
    }

    public void reimprimirComprobante(ActionEvent actionEvent) throws IOException {
        this.imprimeEnArchivo = true;
        context = RequestContext.getCurrentInstance();

        context.execute("ReimprimirComprobanteDialogo.hide()");

        context.execute("procesandoDlg.show()");

        ImpresionComprobantes impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora(), nombrePersonaTransacciona);
        impresionComprobantes.movimientoCuenta(this.ejbFacadeMovimientoCuentaAdicional.find(codigoMovimiento));

        // Incia Datos
        context.execute("procesandoDlg.hide()");
        this.init();

        //this.impresionFormularioLicitudFondos();
    }

    public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoMovimiento", this.codigoMovimiento);

        nombreReporte = "movimientoCuenta";

        getGeneraReporte().exporta("/financiero/cajas/movimientosCuentas/reporte/", nombreReporte,
                nombreReporte + String.valueOf(codigoMovimiento) + ".pdf",
                "PDF", externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }

    /**
     * Llama al diaglo de la impresion del formulario en caso de que exista
     *
     * @throws IOException
     */
    public void impresionFormularioLicitudFondos() throws IOException {
        context = RequestContext.getCurrentInstance();
        // Si se genero el formulario y existe el codigo se procede al llamado del formulario de licitud de fondos
        if (generaFormulario == 1L) {

            LicitudFondosFormulario lff = ejbFacadeLicitudFondosFormulario.find(codigoFormulario);
            List<LicitudFonCptoTran> listaLicitudFonCptoTran = ejbFacadeLicitudFonCptoTran.getByTipo(lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoConcepto(),
                    lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoMoneda(),
                    lff.getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoTipoProducto(),
                    lff.getCodigoIfip(),
                    'N');

            if (listaLicitudFonCptoTran.size() == 1) {

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
                //getLicitudFondosBean().setListaComponentesActualizar(listaComponentesActualizar);
                if (this.conceptoTransaccion.getImprimeEnLibreta() == 'S') {
                    getLicitudFondosBean().setDialogoMostrar("ImprimirLibretaDialogo");
                } else {
                    getLicitudFondosBean().setDialogoMostrar("ImprimirComprobanteDialogo");
                }

                context.execute("FormularioLicitudFondosDialogo.show()");

            } else {
                this.init();

                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteTransaccionLicitudFondos"));
                RequestContext.getCurrentInstance().update("display,MovimientoCuentaFor");

            }
        } else if (this.conceptoTransaccion.getImprimeEnLibreta() == 'S') {
            context.execute("ImprimirLibretaDialogo.show()");
            context.execute("PF('boletosDialog').show();");
        } else {
            context.execute("ImprimirComprobanteDialogo.show()");
            context.execute("PF('boletosDialog').show();");
        }
    }
    // -- FIN DE GUARDAR EL MOVIMIENTO
    //---------------------------------------------------------------------------

    //---------------------------------------------------------------------------
    // -- METODOS DE VALIDACIONES
    public boolean validaciones(boolean muestraMensaje) {
        ////System.out.println("Validaciones");
        //Valida los campos del moviento si estan vacios o cumplen requerimientos minimos
        if (this.validaCampos()) {
            ////System.out.println("Valida Cuentas");
            //Validando que la cuenta Existe
            if (this.validaCuenta()) {
                ////System.out.println("Valida Comprobante");
                //Valida si el comprobante no ha sido usado
                if (true) {//if (this.validaComprobante()) {
                    ////System.out.println("Valida Valida Saldo Cuenta");
                    //Valida si tiene saldo en la cuenta en caso de ser debito de la cuenta
                    if (this.validaSaldoCuenta()) {
                        return true;
                    }
                }
            }
        }

        ////System.out.println("Culmina Validaciones");
        try {
            if (muestraMensaje) {
                MuestraMensaje.addError(msg);
            }
        } catch (Exception er) {
            return false;
        }
        return false;
    }

    @SuppressWarnings("empty-statement")
    public boolean validaCampos() {
        try {
            if (this.moneda == null) {
                return false;
            }
            ////System.out.println("1.1");
            if (this.productoIfip == null) {
                return false;
            }
            ////System.out.println("1.2");
            if (this.transaccion == null) {
                return false;
            }

            ////System.out.println("Concepto Transaccion " + conceptoTransaccion);
            if (this.conceptoTransaccion == null) {
                return false;
            }

            ////System.out.println("1.4 " + this.numeroCuenta);
            if ((this.numeroCuenta != null) ? this.numeroCuenta.trim().isEmpty() : null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaRequerida");

                return false;
            }
            ////System.out.println("1.5");
            if ((this.totalEfectivo != null) ? this.totalEfectivo.toString().trim().isEmpty() : null) {
                this.setTotalEfectivo(new BigDecimal("0.00"));
            }

            this.setTotalMovimiento(totalEfectivo.add(totalCheques));
            if (this.totalMovimiento.compareTo(new BigDecimal("0.00")) <= 0) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovmientoNoPuedeSerCero");
                return false;
            }

            if (this.observaciones != null) {
                if (this.observaciones.trim().length() == 0) {
                    msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                    return false;
                } else {
                    if (this.observaciones.trim().length() > 0 && this.observaciones.trim().length() <= 4) {
                        msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                        return false;
                    }
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                return false;
            }
            ////System.out.println("1.11");
        } catch (Exception er) {
            return false;
        }

        return true;
    }

    public boolean validaSaldoCuenta() {
        msg = null;
        //  Long codigoCuenta = cuenta.getCodigo();
        //cuenta = this.ejbFacadeCuenta.find(codigoCuenta);
        this.setTotalMovimiento(totalEfectivo.add(totalCheques));
        if (String.valueOf(this.transaccion.getTipo()).equals("D")) {
            if (this.cuenta.getSaldoDisponible().subtract(totalMovimiento).compareTo(totalCheques) < 0) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
                return false;
            }
        }

        return true;
    }

    public boolean validaComprobante() {
        msg = null;
        if (!ejbFacadeMovimientoCuentaAdicional.getItemsNumeroComprobanteIfip(ActivacionUsuario.getCodigoIfip(), comprobante).isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComprobanteUsado");
        }
        return (msg == null);
    }

    public boolean validaCuenta() {
        msg = null;
        List<Cuenta> listaCuenta = ejbFacadeCuenta.getItemsNumero(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                this.numeroCuenta.toUpperCase(),
                1L);

        if (listaCuenta.isEmpty()) {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuentaNoExiste");
        } else if (listaCuenta.size() == 1) {

            this.cuenta = listaCuenta.get(0);
            this.numeroCuenta = this.cuenta.getNumero();
            if (this.cuenta.getCodigoEstado().getCodigo().toString().equals("3")) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaCerrada");
            } else if (String.valueOf(this.cuenta.getCodigoEstado().getIndicaActiva()).equals("N")) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cuenta") + this.cuenta.getCodigoEstado().getNombre();
            }
        } else {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroMasUnaCuenta");
        }
        return (msg == null);
    }

    //---------------------------------------------------------------------------
    // -- METODOS DE EVENTOS DE AJAX DE CUADROS DE TEXTO
    public void buscaComprobante() {
        msg = null;
        if (msg == null) {
            if (this.comprobante != null && !validaComprobante()) {
                MuestraMensaje.addError(msg);
            }
        }
        //Verificando si se habilita el boton guardar
        habilitaBotonGuardar();
    }

    public void buscaCuenta() {

        if (!validaCuenta()) {
            listaBloqueoCuenta = null;
            MuestraMensaje.addError(msg);
        } else {
            this.buscaFirmantes();
            this.buscarMoraSocio(cuenta.getCodigoSocio());
            this.buscarMoraConyuge(cuenta.getCodigoSocio());
            this.buscarMoraGarante(cuenta.getSocio().getCodigoPersona().getCodigo());
            this.obtieneBloqueoCuenta();
        }
        //Verificando si se habilita el boton guardar
        habilitaBotonGuardar();

    }

    private void buscaFirmantes() {

        this.verPanelFirmantes = false;
        this.itemsFirmanteCuenta = null;
        this.mensajeFirmantes = null;
        ////System.out.println("Cuenta en Busca Firmantes "+this.cuenta);
        if (String.valueOf(this.cuenta.getCodigoTipoFirma().getTieneFirmas()).equals("S")) {
            this.itemsFirmanteCuenta = this.ejbFacadeFirmanteCuenta.getItemsCodigoCuentaEliminado(this.cuenta.getCodigo(), 'N');
            this.verPanelFirmantes = true;
            String quienFirma = String.valueOf(this.cuenta.getCodigoTipoFirma().getQuienFirma());
            if (quienFirma.equals("T")) {
                this.mensajeFirmantes = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FirmanTodosTransaccion");
            } else if (quienFirma.equals("U")) {
                this.mensajeFirmantes = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FirmanUnoTransaccion");
            }

        }
        RequestContext.getCurrentInstance().update("firmatesPan");
    }

    public void calculaTotal() {
        this.totalCheques = new BigDecimal("0.00");
        if (!this.itemsChequeDepositado.isEmpty()) {
            for (ChequeDepositado cd : this.itemsChequeDepositado) {
                this.totalCheques = this.totalCheques.add(cd.getValor());
            }
        }
        if (this.totalEfectivo.compareTo(new BigDecimal("0.00")) > 0) {
            this.setTotalMovimiento(totalEfectivo.add(totalCheques));
        }
        if (!this.validaSaldoCuenta()) {
            MuestraMensaje.addError(msg);
        }

        this.habilitaBotonGuardar();
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

    //---------------------------------------------------------------------------
    // -- METODOS DE DESGLOE DE CHEQUES
    public void preparaDegloceCheques(ActionEvent actionEvent) {
        this.numeroCheque = null;
        this.numeroCuentaCheque = null;
        this.valorCheque = null;
        this.entidadFinanciera = null;
        this.setItemsEntidadFinanciera(this.ejbFacadePerEfeCheEntFin.getItemsEntidadFinancieraIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N'));
        if (this.itemsChequeDepositado == null) {
            this.setItemsChequeDepositado(new ArrayList<ChequeDepositado>());
        }
    }

    public void agregaCheque() {
        msg = null;
        this.numeroCheques++;
        if (this.entidadFinanciera == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("EntidadFinanciera") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else if ((this.numeroCuentaCheque != null) ? this.numeroCuentaCheque.trim().isEmpty() : null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cuenta") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else if ((this.numeroCheque != null) ? this.numeroCheque.trim().isEmpty() : null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("NumeroCheque") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else if ((this.valorCheque != null) ? this.valorCheque.toString().trim().isEmpty() : null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Valor") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else if (!this.ejbFacadeChequeDepositado.getItemsEntFinumeroChequeNumeroCuenta(this.entidadFinanciera.getCodigo(), numeroCheque, this.numeroCuentaCheque).isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeDepositado");
        } else if (this.valorCheque.compareTo(new BigDecimal("0.00")) <= 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorChequeMayorACero");
        } else if (!this.itemsChequeDepositado.isEmpty()) {

            for (ChequeDepositado cd : this.itemsChequeDepositado) {

                if (cd.getNumeroCheque().trim().equals(this.numeroCheque.trim())
                        && cd.getNumeroCuenta().trim().equals(this.numeroCuentaCheque.trim())
                        && cd.getEntidadFinanciera().getCodigo() == this.entidadFinanciera.getCodigo()) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeEnDesgloce");
                    break;
                }
                if (msg != null) {
                    break;
                }
            }
        }

        if (msg == null) {
            this.setChequeDepositado(new ChequeDepositado());
            this.chequeDepositado.setCodigoEntidadFinanciera(this.getEntidadFinanciera().getCodigo());
            this.chequeDepositado.setEntidadFinanciera(entidadFinanciera);
            this.chequeDepositado.setNumeroCheque(numeroCheque);
            this.chequeDepositado.setNumeroCuenta(numeroCuentaCheque);
            this.chequeDepositado.setValor(valorCheque);
            this.chequeDepositado.setCodigo(Long.parseLong(String.valueOf(this.numeroCheques)));
            this.itemsChequeDepositado.add(chequeDepositado);
            this.totalCheques = this.totalCheques.add(valorCheque);
            this.calculaTotal();
            this.numeroCuentaCheque = null;
            this.numeroCheque = null;
            this.valorCheque = null;
            this.entidadFinanciera = null;
        } else {
            this.mensajeDialogo = msg;
        }
    }

    public void quitaCheque() {
        this.itemsChequeDepositado.remove(this.chequeDepositadoSel);
        this.totalCheques = this.totalCheques.subtract(this.chequeDepositadoSel.getValor());
        this.calculaTotal();
    }

    //---------------------------------------------------------------------------
    // -- METODOS DE LA LISTA DE BUSQUEDA DE CUENTAS
    public void preparaBusquedaCuentas(ActionEvent actionEvent) {
        this.nombresSocio = null;
        this.setItemsCuenta(null);
        this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
    }

    public void buscaCuentas() {

        if (nombresSocio.trim().isEmpty()) {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
            this.setItemsCuenta(null);
        } else if (this.nombresSocio.trim().length() <= tamanoCaractresCriterio) {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CaracteresMinimoCriterio") + " " + TransformaNumeroALetras.convertirNumeroEnteroALetras(tamanoCaractresCriterio);
            this.setItemsCuenta(null);
        } else {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta");
            this.mensajeDialogo = null;
            this.setItemsCuenta(ejbFacadeCuenta.getItemsMovimientoCuenta(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                    this.nombresSocio.toUpperCase(), 'S'));
        }
    }

    public void seleccionaCuenta(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        this.cuenta = this.cuentaSel;
        this.numeroCuenta = this.cuenta.getNumero();
        this.buscaFirmantes();
        this.habilitaBotonGuardar();
        this.buscarMoraSocio(cuenta.getCodigoSocio());
        this.buscarMoraConyuge(cuenta.getCodigoSocio());
        this.buscarMoraGarante(cuenta.getSocio().getCodigoPersona().getCodigo());
        this.obtieneBloqueoCuenta();
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
                    //System.out.println("Busqueda Socios Mora: " + socios);
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
                    //System.out.println(pendientes);
                    for (CalculoCuotaPagar c : pendientes) {
                        deuda = deuda + c.getTotalPago().doubleValue();
                    }
                    Long diasMora = this.ejbFacadeCalculoCuotaPagar.getDiasMoraNumeroIfip(s.getSolicitudDetallePK().getNumeroCredito(), s.getSolicitudDetallePK().getCodigoIfip(), 'P');
                    aux = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioGaranteMora") + "\n";
                    aux = aux.replace("$DIAS", String.valueOf(diasMora)).replace("$SUMA", s.getTotalMora().toPlainString()).replace("$NUMERO", String.valueOf(s.getSolicitudDetallePK().getNumeroCredito())).replace("$SOCIO", s.getSolicitud().getSocio().getCodigoPersona().getNombreCompleto()).replace("$DEUDA", String.valueOf(formatoValor(new BigDecimal(deuda))));;
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
        this.numeroCuenta = null;
        this.cuenta = null;
        this.deshabilitaBotonDesgloceCheques = true;
        //Verifica si se muestra en pantalla el componente persona transacciona
        this.visualizaComponentePersonaTransacciona();
        this.habilitaBotonGuardar();
    }

    /**
     * CUANDO CAMBIA EL PRODUCTO.
     */
    public void cambiaProductoIfip() {
        this.setItemsTransaccion(
                this.ejbFacadeConceptoTransaccionIfip.getItemsTransaccionesMovimentoCaja(this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto(),
                        this.getProductoIfip().getProductoIfipPK().getCodigoMoneda(),
                        this.getProductoIfip().getProductoIfipPK().getCodigoIfip(),
                        ActivacionUsuario.getCodigoRol(),
                        'N',
                        'S'));

        this.transaccion = null;
        this.cuenta = null;
        this.conceptoTransaccion = null;
        this.numeroCuenta = null;
        this.cuenta = null;
        this.deshabilitaBotonDesgloceCheques = true;
        this.setTotalEfectivo(new BigDecimal("0.00"));
        this.setTotalMovimiento(new BigDecimal("0.00"));
        this.setTotalCheques(new BigDecimal("0.00"));
        //Verifica si se muestra en pantalla el componente persona transacciona
        this.visualizaComponentePersonaTransacciona();
        this.habilitaBotonGuardar();
    }

    /**
     * CUANDO CAMBIA LA TRANSACCION.
     */
    public void cambiaTransaccion() {
        this.setItemsConceptoTransaccion(this.ejbFacadeConceptoTransaccionIfip.getItemsConceptosMovimentoCaja(this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto(),
                this.getProductoIfip().getProductoIfipPK().getCodigoMoneda(),
                this.getProductoIfip().getProductoIfipPK().getCodigoIfip(),
                this.transaccion.getCodigo(),
                ActivacionUsuario.getCodigoRol(),
                'N',
                'S'));
        this.conceptoTransaccion = null;
        if (String.valueOf(this.transaccion.getTipo()).equals("D")) {
            this.deshabilitaBotonDesgloceCheques = true;
            this.setTotalCheques(new BigDecimal("0.00"));
            this.setTotalMovimiento(totalEfectivo.add(totalCheques));
            this.setItemsChequeDepositado(new ArrayList<ChequeDepositado>());
            numeroCheques = 0;
        } else {
            this.deshabilitaBotonDesgloceCheques = false;
            this.setTotalCheques(new BigDecimal("0.00"));
            this.setTotalMovimiento(totalEfectivo.add(totalCheques));
        }
        this.setTotalEfectivo(new BigDecimal("0.00"));
        this.setTotalMovimiento(new BigDecimal("0.00"));
        //Verifica si se muestra en pantalla el componente persona transacciona
        this.visualizaComponentePersonaTransacciona();
        this.habilitaBotonGuardar();

    }

    //****** Control de persona quien realiza la transaccion ******//
    /**
     *
     */
    public void cambiaConceptoTransaccion() {
        //Verifica si se muestra en pantalla el componente persona transacciona
        this.visualizaComponentePersonaTransacciona();
        this.habilitaBotonGuardar();
    }

    /**
     *
     */
    public void cambiaPersonaTransacciona() {
        try {
            if (socioTransacciona) {
                visualizaComponetePersonaTransacciona = "display: none";
            } else {
                visualizaComponetePersonaTransacciona = "display: inline";
            }
        } catch (Exception e) {
            identificacionPersonaTransacciona = "";
            nombrePersonaTransacciona = "";
            codigoPersonaTransacciona = Long.valueOf("0");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"MovimientoCuentaBean", "cambiaPersonaTransacciona", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error al asignar persona que realiza la transaccion");
        }
    }

    /**
     *
     */
    public void visualizaComponentePersonaTransacciona() {
        try {

            if (moneda == null) {
                visualizaPersonaTransacciona = "display: none";
                socioTransacciona = false;
                vaciarPersonaTransacciona = true;
                visualizaComponetePersonaTransacciona = "display: none";
                return;
            }

            if (productoIfip == null) {
                visualizaPersonaTransacciona = "display: none";
                socioTransacciona = false;
                vaciarPersonaTransacciona = true;
                visualizaComponetePersonaTransacciona = "display: none";
                return;
            }

            if (transaccion == null) {
                visualizaPersonaTransacciona = "display: none";
                socioTransacciona = false;
                vaciarPersonaTransacciona = true;
                visualizaComponetePersonaTransacciona = "display: none";
                return;
            }

            if (conceptoTransaccion == null) {
                visualizaPersonaTransacciona = "display: none";
                socioTransacciona = false;
                vaciarPersonaTransacciona = true;
                visualizaComponetePersonaTransacciona = "display: none";
                return;
            }
            //Verifica nuevamente en solo en caso que el control este visible
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_ahorros.pkm_parametro_persona_tra_ifi.p_visualiza_control");
            llamaSP.setNumeroParametros(5);
            llamaSP.setListParametrosEntrada(new ArrayList());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_producto", productoIfip.getProducto().getTipoProducto().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_moneda", moneda.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_concepto", conceptoTransaccion.getCodigo()});
            llamaSP.setListParametrosSalida(new ArrayList());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_visualiza", Types.VARCHAR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                int visualizaComponente = Integer.valueOf((String) llamaSP.getListResultado().get(0));
                if (visualizaComponente == 0) {
                    visualizaPersonaTransacciona = "display: none";
                    visualizaComponetePersonaTransacciona = "display: none";
                } else {
                    visualizaPersonaTransacciona = "display: inline";
                    socioTransacciona = false;
                    vaciarPersonaTransacciona = true;
                    visualizaComponetePersonaTransacciona = "display: inline";
                }
            } else {
                MuestraMensaje.addError("Error al consulta componente persona que realiza transaccion");
                visualizaPersonaTransacciona = "display: none";
                visualizaComponetePersonaTransacciona = "display: none";
            }

        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"MovimientoCuentaBean", "visualizaComponenteDepositante", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error al cargar componente persona que realiza transaccion");
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean validaPersonaTransacciona() {
        try {
            if (visualizaPersonaTransacciona.equals("display: inline")) {
                if (socioTransacciona) {
                    if (cuenta != null) {
                        if (cuenta.getSocio() != null) {
                            if (cuenta.getSocio().getCodigoPersona() != null) {
                                if (cuenta.getSocio().getCodigoPersona().getIdentificacion() != null) {
                                    identificacionPersonaTransacciona = cuenta.getSocio().getCodigoPersona().getIdentificacion();
                                    nombrePersonaTransacciona = cuenta.getSocio().getCodigoPersona().getNombreCompleto();
                                    codigoPersonaTransacciona = cuenta.getSocio().getCodigoPersona().getCodigo();
                                    if (identificacionPersonaTransacciona != null || nombrePersonaTransacciona != null || codigoPersonaTransacciona != null) {
                                        if (identificacionPersonaTransacciona.length() > 0 && nombrePersonaTransacciona.length() > 0 && (codigoPersonaTransacciona.compareTo(0L) > 0)) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    try {
                        identificacionPersonaTransacciona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("MovimientoCuentaFor:personaTransaccionaComponente:identificacionPersonaComponente");
                        nombrePersonaTransacciona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("MovimientoCuentaFor:personaTransaccionaComponente:nombrePersonaComponente");
                        codigoPersonaTransacciona = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("MovimientoCuentaFor:personaTransaccionaComponente:codigoPersonaComponente"));
                        if (identificacionPersonaTransacciona != null || nombrePersonaTransacciona != null || codigoPersonaTransacciona != null) {
                            if (identificacionPersonaTransacciona.length() > 0 && nombrePersonaTransacciona.length() > 0 && (codigoPersonaTransacciona.compareTo(0L) > 0)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"MovimientoCuentaBean", "validaPersonaTransacciona", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error al validar persona depositante");
            return false;
        }
    }

    /**
     * Almacena la persona que que ejecuta el deposito de la transaccion
     *
     * @return
     */
    public boolean guardaMovimientoCuentaPersonaTransacciona() {

        try {
            if (visualizaPersonaTransacciona.equals("display: inline")) {
                if (validaPersonaTransacciona()) {
                    if (identificacionPersonaTransacciona != null || nombrePersonaTransacciona != null || codigoPersonaTransacciona != null) {
                        if (identificacionPersonaTransacciona.length() > 0 && nombrePersonaTransacciona.length() > 0 && (codigoPersonaTransacciona.compareTo(0L) > 0)) {
                            llamaSP.setNombreSP("mks_ahorros.pkg_movimiento_cuenta_per_tran.p_inserta");
                            llamaSP.setNumeroParametros(3);
                            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_movimiento", codigoMovimiento});
                            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", codigoPersonaTransacciona});
                            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo", Types.VARCHAR});
                            // Invocando al SP
                            llamaSP.invocaSP();

                            if (!llamaSP.isEjecucionCorrecta()) {
                                MuestraMensaje.addError("Error al guardar movimiento persona quien realiza la transaccion en base");
                            }
                        } else {
                            MuestraMensaje.addError("Debe seleccionar persona quien realiza la transaccion");
                            return false;
                        }
                    } else {
                        MuestraMensaje.addError("Debe seleccionar persona quien realiza la transaccion");
                        return false;
                    }
                } else {
                    MuestraMensaje.addError("Debe seleccionar persona quien realiza la transaccion");
                    return false;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"MovimientoCuentaBean", "guardaMovimientoCuentaPersonaTransacciona", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error al guardar movimiento persona quien realiza la transaccion");
            return false;
        }

        return llamaSP.isEjecucionCorrecta();
    }
    //****** Control de persona quien realiza la transaccion ******//

    /**
     *
     */
    public void obtieneBloqueoCuenta() {
        try {
            listaBloqueoCuenta = null;
            listaBloqueoCuenta = bloqueoCuentaFacade.getItemsCuentaVigenteMayorAUnValor(cuenta, 'S', BigDecimal.ZERO);
        } catch (Exception e) {
            MuestraMensaje.addAdvertencia("No se puede obtener los bloqueos de la cuenta");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"MovimientoCuentaBean", "obtieneBloqueoCuenta", CapturaError.getErrorException(e)});
            listaBloqueoCuenta = null;
        }
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
     * @return the itemsChequeDepositado
     */
    public List<ChequeDepositado> getItemsChequeDepositado() {
        return itemsChequeDepositado;
    }

    /**
     * @param itemsChequeDepositado the itemsChequeDepositado to set
     */
    public void setItemsChequeDepositado(List<ChequeDepositado> itemsChequeDepositado) {
        this.itemsChequeDepositado = itemsChequeDepositado;
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
     * @return the imprimeEnArchivo
     */
    public boolean isImprimeEnArchivo() {
        return imprimeEnArchivo;
    }

    /**
     * @param imprimeEnArchivo the imprimeEnArchivo to set
     */
    public void setImprimeEnArchivo(boolean imprimeEnArchivo) {
        this.imprimeEnArchivo = imprimeEnArchivo;
    }

    /**
     * @return the itemsLicitudFonOrgDest
     */
    public List<LicitudFonOrgDest> getItemsLicitudFonOrgDest() {
        return itemsLicitudFonOrgDest;
    }

    /**
     * @param itemsLicitudFonOrgDest the itemsLicitudFonOrgDest to set
     */
    public void setItemsLicitudFonOrgDest(List<LicitudFonOrgDest> itemsLicitudFonOrgDest) {
        this.itemsLicitudFonOrgDest = itemsLicitudFonOrgDest;
    }

    /**
     * @return the licitudFonOrgDest
     */
    public LicitudFonOrgDest getLicitudFonOrgDest() {
        return licitudFonOrgDest;
    }

    /**
     * @param licitudFonOrgDest the licitudFonOrgDest to set
     */
    public void setLicitudFonOrgDest(LicitudFonOrgDest licitudFonOrgDest) {
        this.licitudFonOrgDest = licitudFonOrgDest;
    }

    /**
     * @return the licitudFondosFormulario
     */
    public LicitudFondosFormulario getLicitudFondosFormulario() {
        return licitudFondosFormulario;
    }

    /**
     * @param licitudFondosFormulario the licitudFondosFormulario to set
     */
    public void setLicitudFondosFormulario(LicitudFondosFormulario licitudFondosFormulario) {
        this.licitudFondosFormulario = licitudFondosFormulario;
    }

    /**
     * @return the licitudFondosFormaluarioAdi
     */
    public LicitudFondosFormularioAdi getLicitudFondosFormaluarioAdi() {
        return licitudFondosFormaluarioAdi;
    }

    /**
     * @param licitudFondosFormaluarioAdi the licitudFondosFormaluarioAdi to set
     */
    public void setLicitudFondosFormaluarioAdi(LicitudFondosFormularioAdi licitudFondosFormaluarioAdi) {
        this.licitudFondosFormaluarioAdi = licitudFondosFormaluarioAdi;
    }

    /**
     * @return the personaFirmaLicitud
     */
    public Persona getPersonaFirmaLicitud() {
        return personaFirmaLicitud;
    }

    /**
     * @param personaFirmaLicitud the personaFirmaLicitud to set
     */
    public void setPersonaFirmaLicitud(Persona personaFirmaLicitud) {
        this.personaFirmaLicitud = personaFirmaLicitud;
    }

    /**
     * @return the deshabilitaBotonGuardarLicitud
     */
    public boolean isDeshabilitaBotonGuardarLicitud() {
        return deshabilitaBotonGuardarLicitud;
    }

    /**
     * @param deshabilitaBotonGuardarLicitud the deshabilitaBotonGuardarLicitud
     * to set
     */
    public void setDeshabilitaBotonGuardarLicitud(boolean deshabilitaBotonGuardarLicitud) {
        this.deshabilitaBotonGuardarLicitud = deshabilitaBotonGuardarLicitud;
    }

    /**
     * @return the deshabilitaIdentificacionPersonaFirma
     */
    public boolean isDeshabilitaIdentificacionPersonaFirma() {
        return deshabilitaIdentificacionPersonaFirma;
    }

    /**
     * @param deshabilitaIdentificacionPersonaFirma the
     * deshabilitaIdentificacionPersonaFirma to set
     */
    public void setDeshabilitaIdentificacionPersonaFirma(boolean deshabilitaIdentificacionPersonaFirma) {
        this.deshabilitaIdentificacionPersonaFirma = deshabilitaIdentificacionPersonaFirma;
    }

    /**
     * @return the deshabilitaNombrePersonaFirma
     */
    public boolean isDeshabilitaNombrePersonaFirma() {
        return deshabilitaNombrePersonaFirma;
    }

    /**
     * @param deshabilitaNombrePersonaFirma the deshabilitaNombrePersonaFirma to
     * set
     */
    public void setDeshabilitaNombrePersonaFirma(boolean deshabilitaNombrePersonaFirma) {
        this.deshabilitaNombrePersonaFirma = deshabilitaNombrePersonaFirma;
    }

    /**
     * @return the formatoLibreta
     */
    public int getFormatoLibreta() {
        return formatoLibreta;
    }

    /**
     * @param formatoLibreta the formatoLibreta to set
     */
    public void setFormatoLibreta(int formatoLibreta) {
        this.formatoLibreta = formatoLibreta;
    }

    /**
     * @return the ejbFacadeImpresionLibretaCuenta
     */
    public ImpresionLibretaCuentaFacade getEjbFacadeImpresionLibretaCuenta() {
        return ejbFacadeImpresionLibretaCuenta;
    }

    /**
     * @param ejbFacadeImpresionLibretaCuenta the
     * ejbFacadeImpresionLibretaCuenta to set
     */
    public void setEjbFacadeImpresionLibretaCuenta(ImpresionLibretaCuentaFacade ejbFacadeImpresionLibretaCuenta) {
        this.ejbFacadeImpresionLibretaCuenta = ejbFacadeImpresionLibretaCuenta;
    }

    /**
     * @return the licitudFondosBean
     */
    public LicitudFondosBean getLicitudFondosBean() {
        return licitudFondosBean;
    }

    /**
     * @param licitudFondosBean the licitudFondosBean to set
     */
    public void setLicitudFondosBean(LicitudFondosBean licitudFondosBean) {
        this.licitudFondosBean = licitudFondosBean;
    }

    /**
     * @return the visualizaPersonaTransacciona
     */
    public String getVisualizaPersonaTransacciona() {
        return visualizaPersonaTransacciona;
    }

    /**
     * @param visualizaPersonaTransacciona the visualizaPersonaTransacciona to
     * set
     */
    public void setVisualizaPersonaTransacciona(String visualizaPersonaTransacciona) {
        this.visualizaPersonaTransacciona = visualizaPersonaTransacciona;
    }

    /**
     * @return the socioTransacciona
     */
    public boolean isSocioTransacciona() {
        return socioTransacciona;
    }

    /**
     * @param socioTransacciona the socioTransacciona to set
     */
    public void setSocioTransacciona(boolean socioTransacciona) {
        this.socioTransacciona = socioTransacciona;
    }

    /**
     * @return the identificacionPersonaTransacciona
     */
    public String getIdentificacionPersonaTransacciona() {
        return identificacionPersonaTransacciona;
    }

    /**
     * @param identificacionPersonaTransacciona the
     * identificacionPersonaTransacciona to set
     */
    public void setIdentificacionPersonaTransacciona(String identificacionPersonaTransacciona) {
        this.identificacionPersonaTransacciona = identificacionPersonaTransacciona;
    }

    /**
     * @return the vaciarPersonaTransacciona
     */
    public boolean isVaciarPersonaTransacciona() {
        return vaciarPersonaTransacciona;
    }

    /**
     * @param vaciarPersonaTransacciona the vaciarPersonaTransacciona to set
     */
    public void setVaciarPersonaTransacciona(boolean vaciarPersonaTransacciona) {
        this.vaciarPersonaTransacciona = vaciarPersonaTransacciona;
    }

    /**
     * @return the nombrePersonaTransacciona
     */
    public String getNombrePersonaTransacciona() {
        return nombrePersonaTransacciona;
    }

    /**
     * @param nombrePersonaTransacciona the nombrePersonaTransacciona to set
     */
    public void setNombrePersonaTransacciona(String nombrePersonaTransacciona) {
        this.nombrePersonaTransacciona = nombrePersonaTransacciona;
    }

    /**
     * @return the codigoPersonaTransacciona
     */
    public Long getCodigoPersonaTransacciona() {
        return codigoPersonaTransacciona;
    }

    /**
     * @param codigoPersonaTransacciona the codigoPersonaTransacciona to set
     */
    public void setCodigoPersonaTransacciona(Long codigoPersonaTransacciona) {
        this.codigoPersonaTransacciona = codigoPersonaTransacciona;
    }

    /**
     * @return the visualizaComponetePersonaTransacciona
     */
    public String getVisualizaComponetePersonaTransacciona() {
        return visualizaComponetePersonaTransacciona;
    }

    /**
     * @param visualizaComponetePersonaTransacciona the
     * visualizaComponetePersonaTransacciona to set
     */
    public void setVisualizaComponetePersonaTransacciona(String visualizaComponetePersonaTransacciona) {
        this.visualizaComponetePersonaTransacciona = visualizaComponetePersonaTransacciona;
    }

    /**
     * @return the dobleImpresion
     */
    public boolean isDobleImpresion() {
        return dobleImpresion;
    }

    /**
     * @param dobleImpresion the dobleImpresion to set
     */
    public void setDobleImpresion(boolean dobleImpresion) {
        this.dobleImpresion = dobleImpresion;
    }

    public List<BloqueoCuenta> getListaBloqueoCuenta() {
        return listaBloqueoCuenta;
    }

    public void setListaBloqueoCuenta(List<BloqueoCuenta> listaBloqueoCuenta) {
        this.listaBloqueoCuenta = listaBloqueoCuenta;
    }

    public List<DesgloceBilletes> getListaDesgloceBilletes() {
        return listaDesgloceBilletes;
    }

    public void setListaDesgloceBilletes(List<DesgloceBilletes> listaDesgloceBilletes) {
        this.listaDesgloceBilletes = listaDesgloceBilletes;
    }

//    public void onRowEdit(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Car Edited", String.valueOf(((DesgloceBilletes) event.getObject()).getCodigo()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((DesgloceBilletes) event.getObject()).getCodigo()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("controlBilletesAltaDenomiacionRegistroCambiado"), 
                    ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("controlBilletesAltaDenomiacionAnterior") +": "+ oldValue + 
                            ",: "+ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("controlBilletesAltaDenomiacionNuevo")+ 
                            newValue
            );
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onAddNew() {
        DesgloceBilletes desgloceBilletes = new DesgloceBilletes();
        desgloceBilletes.setCodigo(0l);
        desgloceBilletes.setCodigoMovimiento(movimientoCuenta);
        desgloceBilletes.setSerie("EDITE-SERIE");
        desgloceBilletes.setMonto(BigDecimal.TEN.multiply(BigDecimal.TEN));
        listaDesgloceBilletes.add(desgloceBilletes);
    }

    public void eliminarDetalle(long codigo, int indice) {
        try {
            if (codigo == 0l) {
                listaDesgloceBilletes.remove(indice);
            }
        } catch (Exception e) {
        }
    }

    public boolean isMostraBotonGuardar() {
        return listaDesgloceBilletes.size() <= 0;
    }

    public void setMostraBotonGuardar(boolean mostraBotonGuardar) {
        this.mostraBotonGuardar = mostraBotonGuardar;
    }

    public boolean isMostraDialogoDesgloceBilletesGuardar() {
        return totalEfectivo.doubleValue() < 100;
    }

    public void setMostraDialogoDesgloceBilletesGuardar(boolean mostraDialogoDesgloceBilletesGuardar) {
        this.mostraDialogoDesgloceBilletesGuardar = mostraDialogoDesgloceBilletesGuardar;
    }

}
