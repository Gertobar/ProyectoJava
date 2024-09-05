package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.impresiones.ImpresionLibreta;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.modelo.ahorros.ParametroGeneralAhorroIfip;
import ec.renafipse.mks.modelo.ahorros.ParametroGeneralAhorroIfipPK;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroDet;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.FirmanteCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ImpresionLibretaCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaAdicionalFacade;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ParametroGeneralAhorroIfipFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioLibretaAhorroDetFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.Graphics;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.attribute.AttributeSet;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "impresionLibretaCuentaController")
@ViewScoped
public class ImpresionLibretaCuentaController extends AbstractController<ImpresionLibretaCuenta> implements Serializable {

    @EJB
    private ImpresionLibretaCuentaFacade ejbFacade;

    @EJB
    private MovimientoCuentaFacade ejbFacadeMovimientoCuenta;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private MovimientoCuentaAdicionalFacade ejbFacadeMovimientoCuentaAdicional;

    @EJB
    private FirmanteCuentaFacade ejbFacadeFirmanteCuenta;

    @EJB
    private ParametroGeneralAhorroIfipFacade ejbFacadeParametroGeneralAhorroIfip;

    @EJB
    private ImpresionLibretaCuentaFacade ejbFacadeImpresionLibretaCuenta;

    @EJB
    private TalonarioLibretaAhorroDetFacade ejbFacadeTalonarioLibretaAhorroDet;

    //---------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String msg;
    private String mensajeDialogo;
    private String numeroCuenta;
    private long codigoCuenta;
    private long sistemaOperativo;
    private String nombreReporte;
    private String nombresSocio;
    private BigDecimal saldoInicial;
    private String numeroLibretaSocio;
    private String mensajeTransaccion;
    private BufferedReader buffer;
    private StringTokenizer lineasdetexto;
    private int totalLineas;
    private int numeroLineasArchivo;
    private FileReader fileReader;
    private int[] paginas;
    private String nombreArchivoImprimir;

    // Variables de impresion
    private FileOutputStream os;
    private PrintStream pw;
    boolean empezoImpresion;
    boolean esReverso;
    private int numeroLineasAnverso;
    private int numeroLineasReverso;
    private boolean imprimeEnArchivo;
    private boolean servidorEsLinux;
    private boolean deshabilitaBotonReimprimir;
    private boolean esReimpresion;
    private String nombreArchivo;
    private String numeroLibretaNueva;

    // variables de Impresion
    private String[] textoLineas;
    private String texto;
    private Graphics graficar;
    private GeneraReporte generaReporte;
    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Cuenta cuenta;
    private TalonarioLibretaAhorroDet talonarioLibretaAhorroDet;
    private MovimientoCuenta movimientoCuentaSel;
    private MovimientoCuentaAdicional movimientoCuentaAdicional;
    private Cuenta cuentaSel;
    private RequestContext context;
    private int formatoLibreta;

    private ArrayList<String> contenido;
    private ArrayList<String> lineasPagina;
    private List<ProductoIfip> itemsProductoIfip;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicional;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalImpresas;
    private List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalNoImpresas;
    // private List<ImpresionLibretaCuenta> listaImpresionLibretaCuenta;
    private List<Cuenta> itemsCuenta;
    private List<TalonarioLibretaAhorroDet> listaTalonarioLibretaAhorroDet;
    private List<FirmanteCuenta> listaFirmanteCuenta;
    private List<ImpresionLibretaCuenta> listaImpresionLibretaCuenta;

    private ImpresionLibreta libreta;
    private LlamaSP llamaSP;

    public ImpresionLibretaCuentaController() {
        super(ImpresionLibretaCuenta.class);
    }

    @PostConstruct
    public void init() {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {

            // Verificando que el equipo este registrado en el sistema 
            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            //Instancion el proceso de llama Store Procedure
            llamaSP = new LlamaSP();

            this.setMoneda(this.ejbFacadeMoneda.find(Long.parseLong("1")));
            this.cambiaMoneda();
            this.nombreReporte = null;
            this.setCuenta(null);
            this.setTexto(null);
            this.msg = null;
            this.sistemaOperativo = 0;
            this.setNumeroLineasArchivo(0);
            this.setProductoIfip(null);
            this.setSaldoInicial(new BigDecimal("0.00"));
            this.pw = null;
            this.fileReader = null;
            this.buffer = null;
            this.lineasdetexto = null;
            this.totalLineas = 0;
            this.fileReader = null;
            this.paginas = null;
            this.contenido = new ArrayList();
            this.lineasPagina = new ArrayList();
            this.textoLineas = null;
            this.texto = null;
            this.listaImpresionLibretaCuenta = null;
            this.setImprimeEnArchivo(false);
            this.nombreArchivoImprimir = null;
            this.setServidorEsLinux(ActivacionUsuario.getTipoSOServidorAppl().equals("L"));
            this.setNombreArchivo(ActivacionUsuario.getRutaImpresionArchivos() + "lib" + ActivacionUsuario.getCodigoUsuario() + ".txt");
            esReimpresion = false;
            this.libreta = new ImpresionLibreta(ActivacionUsuario.getRutaImpresora());
            ParametroGeneralAhorroIfip pga = this.ejbFacadeParametroGeneralAhorroIfip.find(new ParametroGeneralAhorroIfipPK(Long.parseLong("1"), ActivacionUsuario.getCodigoIfip()));
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

            pga = this.ejbFacadeParametroGeneralAhorroIfip.find(new ParametroGeneralAhorroIfipPK(Long.parseLong("3"), ActivacionUsuario.getCodigoIfip()));
            if (pga == null) {
                this.setFormatoLibreta(1);
            } else {
                this.setFormatoLibreta(pga.getValorNumerico().intValue());
            }

        } catch (IOException e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"transaccionLoteBean", "init", CapturaError.getErrorException(ex)});
                        
            }
        } catch (NumberFormatException e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"transaccionLoteBean", "init", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     * BUSCA LOS MOVIMIENTOS DE LAS CUENTA DEL SOCIO.
     */
    public void buscaEstadoCuentas() {
        this.saldoInicial = new BigDecimal("0.00");
        if (!this.validaCuenta()) {
            return;
        }

        // Obteniendo las lineas impresas y no impresas respectivamente
        this.obtieneLineasImpresasNoImpresas();
        /*this.itemsMovimientoCuentaAdicionalImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta(), 'S');
         this.itemsMovimientoCuentaAdicionalNoImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');*/

        // Colocando en una solo lista para mostrar en la ventana
        this.itemsMovimientoCuentaAdicional = new ArrayList<MovimientoCuentaAdicional>();
        this.itemsMovimientoCuentaAdicional.addAll(itemsMovimientoCuentaAdicionalImpresas);
        this.itemsMovimientoCuentaAdicional.addAll(itemsMovimientoCuentaAdicionalNoImpresas);

        // Obteniendo los firmantes
        this.listaFirmanteCuenta = this.ejbFacadeFirmanteCuenta.getItemsCodigoCuentaEliminado(this.cuenta.getCodigo(), 'N');

        //this.itemsMovimientoCuentaAdicional.ad
        //this.setItemsMovimientoCuenta(this.ejbFacadeMovimientoCuenta.getItemsMovimientosLibretaImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta()));
        if (!this.itemsMovimientoCuentaAdicional.isEmpty()) {
            Long codigoMovimientoAnterior = this.ejbFacadeMovimientoCuenta.getCodigoAnteriorDelPrimeroEstadoCuentas(this.itemsMovimientoCuentaAdicional.get(0).getMovimientoCuenta().getCodigo(), this.cuenta.getCodigo());
            if (codigoMovimientoAnterior != null) {
                this.saldoInicial = this.ejbFacadeMovimientoCuenta.find(codigoMovimientoAnterior).getSaldoCuenta();
            }
        }
        this.libreta = new ImpresionLibreta(ActivacionUsuario.getRutaImpresora());

    }

    /**
     * Obtiene las lineas impresas y no impresas de la libreta
     */
    private void obtieneLineasImpresasNoImpresas() {
        // Obteniendo las lineas impresas y no impresas respectivamente
        this.itemsMovimientoCuentaAdicionalImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta(), 'S');
        this.itemsMovimientoCuentaAdicionalNoImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');

    }

    // -- METODOS DE LA LISTA DE BUSQUEDA DE CUENTAS
    public void preparaBusquedaCuentas(ActionEvent actionEvent) {
        this.setNombresSocio(null);
        this.setItemsCuenta(null);
        this.setCuenta(null);
        esReimpresion = false;
        this.setItemsMovimientoCuentaAdicional(null);
        this.setItemsMovimientoCuentaAdicionalImpresas(null);
        this.setItemsMovimientoCuentaAdicionalNoImpresas(null);
        this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
    }

    /**
     * Busca las Cuentas de los Socios
     */
    public void buscaCuentas() {
        if (getNombresSocio().trim().isEmpty()) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsCuenta(null);
        } else if (this.getNombresSocio().trim().length() <= 6) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsCuenta(null);
        } else {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta"));
            this.setMensajeDialogo(null);
            this.setItemsCuenta(ejbFacadeCuenta.getItemsEstadoCuenta(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                    this.getNombresSocio().toUpperCase()));
        }
    }

    public void seleccionaCuenta(ActionEvent actionEvent) {
        this.cuenta = this.getCuentaSel();
        this.numeroCuenta = this.cuenta.getNumero();
        this.setItemsMovimientoCuentaAdicional(null);
        this.setItemsMovimientoCuentaAdicionalImpresas(null);
        this.setItemsMovimientoCuentaAdicionalNoImpresas(null);
        this.libreta.setEsReimpresion(false);
        this.buscaEstadoCuentas();
    }

    public void buscaCuenta() {
        ////System.out.println("Numero Cuenta " + this.numeroCuenta);
        this.setItemsMovimientoCuentaAdicional(null);
        this.setItemsMovimientoCuentaAdicionalImpresas(null);
        this.setItemsMovimientoCuentaAdicionalNoImpresas(null);
        this.setCuenta(null);
        if (!validaCuenta()) {
            MuestraMensaje.addError(msg);
        } else {
            this.buscaEstadoCuentas();
            esReimpresion = false;
            this.libreta = new ImpresionLibreta(ActivacionUsuario.getRutaImpresora());
            libreta.setEsReimpresion(false);
        }
    }

    /**
     * VALIDA LA CUENTA DEL SOCIO.
     *
     * @return
     */
    private boolean validaCuenta() {
        msg = null;
        ////System.out.println("Valida Cuenta " + this.numeroCuenta + " " + this.productoIfip);
        List<Cuenta> listaCuenta = ejbFacadeCuenta.getItemsNumero(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                this.numeroCuenta.toUpperCase(),
                new Long(1));
        ////System.out.println("Cuenta " + listaCuenta);
        if (listaCuenta.isEmpty()) {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuentaNoExiste");
        } else if (listaCuenta.size() == 1) {
            this.cuenta = listaCuenta.get(0);
            this.numeroCuenta = this.cuenta.getNumero();
            if (this.cuenta.getCodigoEstado().getCodigo().toString().equals("3")) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaCerrada"));
            } else if (String.valueOf(this.cuenta.getCodigoEstado().getIndicaActiva()).equals("N")) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cuenta") + this.cuenta.getCodigoEstado().getNombre());
            }
        } else {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroMasUnaCuenta");
        }
        return (msg == null);
    }

    /**
     * MUESTRA EL DETALLE DEL MOVIMIENTO DE LA CUENTA.
     *
     * @param actionEvent
     */
    public void muestraDetalleMovimiento(ActionEvent actionEvent) {
        this.movimientoCuentaAdicional = null;
        ////System.out.println("movimientoCuentaSel " + movimientoCuentaSel);
        this.movimientoCuentaAdicional = ejbFacadeMovimientoCuentaAdicional.find(this.movimientoCuentaSel.getCodigo());
    }

    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
        this.setProductoIfip(null);
        this.setCuenta(null);

    }

    /**
     *
     * @param actionEvent
     * @throws java.io.IOException
     */
    @SuppressWarnings("empty-statement")
    public void imprimirAnverso(ActionEvent actionEvent) throws IOException {
        context = RequestContext.getCurrentInstance();
        if (cuenta.getNumeroLibreta() == null) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AsigneNumeroLibreta"));
            context.execute("procesandoDlg.hide()");
        } else {
            System.out.println("Libreta " + getCuenta().getNumeroLibreta());
            int lineasImpresas = ejbFacadeImpresionLibretaCuenta.getUltimaLineaImpresaLibreta(getCuenta().getNumeroLibreta()) + 1;
            System.out.println("Numero Lineas Impresas: " + lineasImpresas);
            libreta.setLineaLibreta(lineasImpresas);
            libreta.setMsg(null);
            libreta.setNumeroLineasAnverso(numeroLineasAnverso);
            libreta.setNumeroLineasReverso(numeroLineasReverso);
            libreta.setItemsMovimientoCuentaAdicionalImpresas(itemsMovimientoCuentaAdicionalImpresas);
            libreta.setItemsMovimientoCuentaAdicionalNoImpresas(itemsMovimientoCuentaAdicionalNoImpresas);
            libreta.setItemsFirmanteCuenta(listaFirmanteCuenta);
            libreta.setFormatoLibreta(formatoLibreta);
            libreta.setCuenta(cuenta);

            libreta.setMsg(null);
            System.out.println("imprime averso");
            if (this.cuenta == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCuenta");
                MuestraMensaje.addError(msg);
                return;
            }

            if (this.cuenta.getNumeroLibreta() == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AsigneUnaLibreta");
                MuestraMensaje.addError(msg);
                return;
            }

            libreta.setNumeroLineasAnverso(numeroLineasAnverso);
            libreta.setNumeroLineasReverso(numeroLineasReverso);
            libreta.setItemsMovimientoCuentaAdicionalImpresas(itemsMovimientoCuentaAdicionalImpresas);
            libreta.setItemsMovimientoCuentaAdicionalNoImpresas(itemsMovimientoCuentaAdicionalNoImpresas);
            libreta.setItemsFirmanteCuenta(listaFirmanteCuenta);
            libreta.setFormatoLibreta(formatoLibreta);
            libreta.setCuenta(cuenta);

            // Enviando a imprimir el anverso de la cara de la Cartola
            libreta.imprimirAnverso();

            context.execute("procesandoDlg.hide()");

            if (libreta.isImpresionCorrecta()) {

                if (libreta.isImpresionCorrecta()) {

                    if (libreta.isMuestraDialogoNuevaCartola()) {
                        context.execute("ImprimeNuevaLibretaDialogo.show()");
                    } else {
                        if (libreta.isMuestraDialogoReverso()) {

                            if (libreta.isMuestraDialogoNuevaCartola()) {
                                context.execute("ImprimeNuevaLibretaDialogo.show()");
                            } else {
                                if (libreta.isMuestraDialogoReverso()) {
                                    //  MostramosMensaje de dar vuelta a la libreta
                                    //System.err.println("Muestra Reverso en Impresion Libreta");
                                    context.execute("ImprimeReversoDialogo.show()");
                                }
                                // Buscando el Estado de Cuentas
                                this.buscaEstadoCuentas();
                            }

                        }
                    }
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
        libreta.setItemsFirmanteCuenta(listaFirmanteCuenta);
        // Obteniendo los movimientos impresos
        this.itemsMovimientoCuentaAdicionalImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta(), 'S');
        // Obteniendo los movimientos NO Impresoso
        this.itemsMovimientoCuentaAdicionalNoImpresas = this.ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');

        libreta.setItemsMovimientoCuentaAdicionalImpresas(itemsMovimientoCuentaAdicionalImpresas);
        libreta.setItemsMovimientoCuentaAdicionalNoImpresas(itemsMovimientoCuentaAdicionalNoImpresas);
        //libreta.setItemsFirmanteCuenta(listaFirmanteCuenta);
        libreta.setFormatoLibreta(formatoLibreta);
        int lineasImpresas = ejbFacadeImpresionLibretaCuenta.getUltimaLineaImpresaLibreta(this.cuenta.getNumeroLibreta()) + 1;
        System.out.println("Numero Lineas Impresas: " + lineasImpresas);
        libreta.setLineaLibreta(lineasImpresas);
        libreta.setCuenta(cuenta);

        // Enviando a imprimir el anverso de la cara de la Cartola
        libreta.imprimirReverso();

        context.execute("procesandoDlg.hide()");

        if (libreta.isImpresionCorrecta()) {
            // Buscando el Estado de Cuentas
            this.buscaEstadoCuentas();
            // Si hay que imprimir el reverso
            if (libreta.isMuestraDialogoNuevaCartola()) {
                //  MostramosMensaje de dar vuelta a la libreta
                context.execute("ImprimeNuevaLibretaDialogo.show()");
            }
        }
    }
   
   //----------------------------------------------------------------------
    // METODOS DE REIMPRESION DE LIBRETA
    public void preparaReimpresionLibreta(ActionEvent actionEvent) {

        context = RequestContext.getCurrentInstance();

        context.execute("procesandoDlg.hide()");

        if (this.cuenta == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCuenta");
            MuestraMensaje.addError(msg);
            return;
        }

        if (this.cuenta.getNumeroLibreta() == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AsigneUnaLibreta");
            MuestraMensaje.addError(msg);
            return;
        }

        if (itemsMovimientoCuentaAdicionalImpresas.isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoEixistenMovimientosImpresos");
            MuestraMensaje.addError(msg);
            return;
        }

        this.numeroLibretaNueva = null;
        this.talonarioLibretaAhorroDet = null;
        this.deshabilitaBotonReimprimir = true;

        context.execute("reimpresionLibretaDialogo.show()");
    }

    /**
     * BUSCA EL NUMERO DE LIBRETA PARA VALIDAR SU ASIGNACION.
     */
    public void buscaNumeroLibreta() {
        msg = null;
        talonarioLibretaAhorroDet = null;

        this.deshabilitaBotonReimprimir = false;
        //this.numeroLibretaNueva = null;
        // Verificando que el nuevo ingresado sea nuemerico
        if (this.getNumeroLibretaNueva() == null) {
            deshabilitaBotonReimprimir = true;
            return;
        }

        if (this.getNumeroLibretaNueva().trim().equals("")) {
            return;
        }
        try {
            Long.parseLong(this.getNumeroLibretaNueva());
        } catch (NumberFormatException er) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
            this.talonarioLibretaAhorroDet = null;
            MuestraMensaje.addError(msg);
            deshabilitaBotonReimprimir = true;
            return;
        }

        this.talonarioLibretaAhorroDet = this.ejbFacadeTalonarioLibretaAhorroDet.getLibretaIfipProductoMonedaSerie(this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                this.productoIfip.getProductoIfipPK().getCodigoMoneda(),
                this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                Long.parseLong(this.getNumeroLibretaNueva()));

        if (this.talonarioLibretaAhorroDet == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroLibretaNoExistente");
        } else {
            if (!String.valueOf(talonarioLibretaAhorroDet.getEstado()).equals("P")) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroLibretaYaAsignada");
                this.talonarioLibretaAhorroDet = null;
            } else {

                Formatter fmt = new Formatter();
                this.setNumeroLibretaNueva(fmt.format("%0" + talonarioLibretaAhorroDet.getCodigoTalLibAhoCab().getDigitosLibreta() + "d", Integer.parseInt(this.getNumeroLibretaNueva())).toString());

            }
        }
         
        if (msg != null) {
            //this.deshabilitaBotonGuardar = true;
            MuestraMensaje.addError(msg);
            this.talonarioLibretaAhorroDet = null;
            deshabilitaBotonReimprimir = true;
            this.numeroLibretaNueva = null;
          //Encontrando la impresora que debe estar conectada o instalada en el servidor
         
            //System.out.println("Impresion OK : ");
        }
    }

    /**
     * Reimprime la libreta
     *
     * @param actionEvent
     */
    public void reimprimirLibreta(ActionEvent actionEvent) {
        try {
            context = RequestContext.getCurrentInstance();
            this.msg = null;
            if (this.numeroLibretaNueva == null || talonarioLibretaAhorroDet == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseNumeroLibreta");
                MuestraMensaje.addError(msg);
                return;
            }

            if (itemsMovimientoCuentaAdicionalImpresas.isEmpty()) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoEixistenMovimientosImpresos");
                MuestraMensaje.addError(msg);
                return;
            }

            esReimpresion = true;
            libreta.setEsReimpresion(true);
            libreta.setCuenta(cuenta);

            // Colocando las lineas que son reimpresas
            libreta.setCodigoMovimientoPrimeroReimpreso(itemsMovimientoCuentaAdicionalImpresas.get(0).getCodigoMovimiento());
            libreta.setCodigoMovimientoUltimoReimpreso(itemsMovimientoCuentaAdicionalImpresas.get(itemsMovimientoCuentaAdicionalImpresas.size() - 1).getCodigoMovimiento());
            // Colocando en la impresion libreta como nueva la ingresada esto para es necesario para prerapar la reimpresion de la libreta
            libreta.setNumeroLibretaNueva(talonarioLibretaAhorroDet.getNumeroLibreta());
            // Colocando como libreta anterior la que esta actualmente en la libreta y sera reemplazada
            libreta.setNumeroLibretaAnterior(cuenta.getNumeroLibreta());

            // Preparando la reimpresion de la libreta
            libreta.preparaReimpresionLibreta();

            if (libreta.isImpresionCorrecta()) {

                //this.cuenta.setNumeroLibreta(talonarioLibretaAhorroDet.getNumeroLibreta());
                this.cuenta = this.ejbFacadeCuenta.find(cuenta.getCodigo());

                // Enviando nuevamente la cuenta ya con el numero de libreta asignada
                libreta.setCuenta(cuenta);
                libreta.setNumeroLibretaNueva(cuenta.getNumeroLibreta());

                // Obteniendo nuevamente las lineas impresas y/o no impresas, en este caso estaran todas como no impresas
                this.obtieneLineasImpresasNoImpresas();

                // Cerrando el dialogo de reimpresion
                context.execute("reimpresionLibretaDialogo.hide()");

                // Enviando a imprimir
                this.imprimirAnverso(actionEvent);
            } else {
                this.buscaEstadoCuentas();
            }
            esReimpresion = false;
            libreta.setEsReimpresion(false);

        } catch (IOException ex) {
            Logger.getLogger(ImpresionLibretaCuentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // FIN DE METODOS DE REIMPRESION DE LIBRETA
    //----------------------------------------------------------------------------------
    public String LPad(String str, Integer length) {
        //return String.format("%1$-" + ((length-str.length()) > 0 ? length-str.length() : 1) + "s", "")+str;

        return String.format("%1$" + length + "s", str);
    }

    public String RPad(String str, Integer length) {
        return String.format("%1$-" + length + "s", str);
        //return String.format("%" + (length - str.length()) + "s", "") + str;
    }

    //*************************FIN LOGICA*****************************//
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
     * @return the movimientoCuentaSel
     */
    public MovimientoCuenta getMovimientoCuentaSel() {
        return movimientoCuentaSel;
    }

    /**
     * @param movimientoCuentaSel the movimientoCuentaSel to set
     */
    public void setMovimientoCuentaSel(MovimientoCuenta movimientoCuentaSel) {
        this.movimientoCuentaSel = movimientoCuentaSel;
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
     * @return the saldoInicial
     */
    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    /**
     * @param saldoInicial the saldoInicial to set
     */
    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
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
     * @return the numeroLibretaSocio
     */
    public String getNumeroLibretaSocio() {
        return numeroLibretaSocio;
    }

    /**
     * @param numeroLibretaSocio the numeroLibretaSocio to set
     */
    public void setNumeroLibretaSocio(String numeroLibretaSocio) {
        this.numeroLibretaSocio = numeroLibretaSocio;
    }

    /**
     * @return the codigoCuenta
     */
    public long getCodigoCuenta() {
        return codigoCuenta;
    }

    /**
     * @param codigoCuenta the codigoCuenta to set
     */
    public void setCodigoCuenta(long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    /**
     * @return the nombreReporte
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * @param nombreReporte the nombreReporte to set
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    /**
     * @return the listaTalonarioLibretaAhorroDet
     */
    public List<TalonarioLibretaAhorroDet> getListaTalonarioLibretaAhorroDet() {
        return listaTalonarioLibretaAhorroDet;
    }

    /**
     * @param listaTalonarioLibretaAhorroDet the listaTalonarioLibretaAhorroDet
     * to set
     */
    public void setListaTalonarioLibretaAhorroDet(List<TalonarioLibretaAhorroDet> listaTalonarioLibretaAhorroDet) {
        this.listaTalonarioLibretaAhorroDet = listaTalonarioLibretaAhorroDet;
    }

    /**
     * @return the mensajeTransaccion
     */
    public String getMensajeTransaccion() {
        return mensajeTransaccion;
    }

    /**
     * @param mensajeTransaccion the mensajeTransaccion to set
     */
    public void setMensajeTransaccion(String mensajeTransaccion) {
        this.mensajeTransaccion = mensajeTransaccion;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the numeroLineasArchivo
     */
    public int getNumeroLineasArchivo() {
        return numeroLineasArchivo;
    }

    /**
     * @param numeroLineasArchivo the numeroLineasArchivo to set
     */
    public void setNumeroLineasArchivo(int numeroLineasArchivo) {
        this.numeroLineasArchivo = numeroLineasArchivo;
    }

    /**
     * @return the sistemaOperativo
     */
    public long getSistemaOperativo() {
        return sistemaOperativo;
    }

    /**
     * @param sistemaOperativo the sistemaOperativo to set
     */
    public void setSistemaOperativo(long sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    /**
     * @return the buffer
     */
    public BufferedReader getBuffer() {
        return buffer;
    }

    /**
     * @param buffer the buffer to set
     */
    public void setBuffer(BufferedReader buffer) {
        this.buffer = buffer;
    }

    /**
     * @return the lineasdetexto
     */
    public StringTokenizer getLineasdetexto() {
        return lineasdetexto;
    }

    /**
     * @param lineasdetexto the lineasdetexto to set
     */
    public void setLineasdetexto(StringTokenizer lineasdetexto) {
        this.lineasdetexto = lineasdetexto;
    }

    /**
     * @return the totalLineas
     */
    public int getTotalLineas() {
        return totalLineas;
    }

    /**
     * @param totalLineas the totalLineas to set
     */
    public void setTotalLineas(int totalLineas) {
        this.totalLineas = totalLineas;
    }

    /**
     * @return the fileReader
     */
    public FileReader getFileReader() {
        return fileReader;
    }

    /**
     * @param fileReader the fileReader to set
     */
    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    /**
     * @return the contenido
     */
    public ArrayList<String> getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(ArrayList<String> contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the lineasPagina
     */
    public ArrayList<String> getLineasPagina() {
        return lineasPagina;
    }

    /**
     * @param lineasPagina the lineasPagina to set
     */
    public void setLineasPagina(ArrayList<String> lineasPagina) {
        this.lineasPagina = lineasPagina;
    }

    /**
     * @return the textoLineas
     */
    public String[] getTextoLineas() {
        return textoLineas;
    }

    /**
     * @param textoLineas the textoLineas to set
     */
    public void setTextoLineas(String[] textoLineas) {
        this.textoLineas = textoLineas;
    }

    /**
     * @return the paginas
     */
    public int[] getPaginas() {
        return paginas;
    }

    /**
     * @param paginas the paginas to set
     */
    public void setPaginas(int[] paginas) {
        this.paginas = paginas;
    }

    /**
     * @return the graficar
     */
    public Graphics getGraficar() {
        return graficar;
    }

    /**
     * @param graficar the graficar to set
     */
    public void setGraficar(Graphics graficar) {
        this.graficar = graficar;
    }

    /**
     * @return the listaImpresionLibretaCuenta
     */
    public List<ImpresionLibretaCuenta> getListaImpresionLibretaCuenta() {
        return listaImpresionLibretaCuenta;
    }

    /**
     * @param listaImpresionLibretaCuenta the listaImpresionLibretaCuenta to set
     */
    public void setListaImpresionLibretaCuenta(List<ImpresionLibretaCuenta> listaImpresionLibretaCuenta) {
        this.listaImpresionLibretaCuenta = listaImpresionLibretaCuenta;
    }

    /**
     * @return the itemsMovimientoCuentaAdicional
     */
    public List<MovimientoCuentaAdicional> getItemsMovimientoCuentaAdicional() {
        return itemsMovimientoCuentaAdicional;
    }

    /**
     * @param itemsMovimientoCuentaAdicional the itemsMovimientoCuentaAdicional
     * to set
     */
    public void setItemsMovimientoCuentaAdicional(List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicional) {
        this.itemsMovimientoCuentaAdicional = itemsMovimientoCuentaAdicional;
    }

    /**
     * @return the itemsMovimientoCuentaAdicionalImpresas
     */
    public List<MovimientoCuentaAdicional> getItemsMovimientoCuentaAdicionalImpresas() {
        return itemsMovimientoCuentaAdicionalImpresas;
    }

    /**
     * @param itemsMovimientoCuentaAdicionalImpresas the
     * itemsMovimientoCuentaAdicionalImpresas to set
     */
    public void setItemsMovimientoCuentaAdicionalImpresas(List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalImpresas) {
        this.itemsMovimientoCuentaAdicionalImpresas = itemsMovimientoCuentaAdicionalImpresas;
    }

    /**
     * @return the itemsMovimientoCuentaAdicionalNoImpresas
     */
    public List<MovimientoCuentaAdicional> getItemsMovimientoCuentaAdicionalNoImpresas() {
        return itemsMovimientoCuentaAdicionalNoImpresas;
    }

    /**
     * @param itemsMovimientoCuentaAdicionalNoImpresas the
     * itemsMovimientoCuentaAdicionalNoImpresas to set
     */
    public void setItemsMovimientoCuentaAdicionalNoImpresas(List<MovimientoCuentaAdicional> itemsMovimientoCuentaAdicionalNoImpresas) {
        this.itemsMovimientoCuentaAdicionalNoImpresas = itemsMovimientoCuentaAdicionalNoImpresas;
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
     * @return the servidorEsLinux
     */
    public boolean isServidorEsLinux() {
        return servidorEsLinux;
    }

    /**
     * @param servidorEsLinux the servidorEsLinux to set
     */
    public void setServidorEsLinux(boolean servidorEsLinux) {
        this.servidorEsLinux = servidorEsLinux;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the numeroLibretaNueva
     */
    public String getNumeroLibretaNueva() {
        return numeroLibretaNueva;
    }

    /**
     * @param numeroLibretaNueva the numeroLibretaNueva to set
     */
    public void setNumeroLibretaNueva(String numeroLibretaNueva) {
        this.numeroLibretaNueva = numeroLibretaNueva;
    }

    /**
     * @return the talonarioLibretaAhorroDet
     */
    public TalonarioLibretaAhorroDet getTalonarioLibretaAhorroDet() {
        return talonarioLibretaAhorroDet;
    }

    /**
     * @param talonarioLibretaAhorroDet the talonarioLibretaAhorroDet to set
     */
    public void setTalonarioLibretaAhorroDet(TalonarioLibretaAhorroDet talonarioLibretaAhorroDet) {
        this.talonarioLibretaAhorroDet = talonarioLibretaAhorroDet;
    }

    /**
     * @return the deshabilitaBotonReimprimir
     */
    public boolean isDeshabilitaBotonReimprimir() {
        return deshabilitaBotonReimprimir;
    }

    /**
     * @param deshabilitaBotonReimprimir the deshabilitaBotonReimprimir to set
     */
    public void setDeshabilitaBotonReimprimir(boolean deshabilitaBotonReimprimir) {
        this.deshabilitaBotonReimprimir = deshabilitaBotonReimprimir;
    }
}
