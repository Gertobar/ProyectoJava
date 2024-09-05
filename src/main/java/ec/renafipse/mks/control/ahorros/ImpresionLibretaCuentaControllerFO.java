package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "impresionLibretaCuentaControllerFO")
@ViewScoped
public class ImpresionLibretaCuentaControllerFO extends AbstractController<ImpresionLibretaCuenta> implements Serializable {

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

    //---------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
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
    private int lineaLibreta;
    private String transaccion;
    private String valor;
    private String saldo;
    private String signo;
    private DecimalFormat formatoValores;
    private SimpleDateFormat formatoFechas;
    private int numeroLineasAnverso;
    private int numeroLineasReverso;
    private boolean imprimeEnArchivo;
    private boolean servidorEsLinux;
    private String nombreArchivo;
    private long codigoMovimientoUltimoImpreso;
    private int lineasImpresas;

    private String[] textoLineas;
    private String texto;
    private Graphics graficar;
    private GeneraReporte generaReporte;
    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Cuenta cuenta;
    private MovimientoCuenta movimientoCuentaSel;
    private MovimientoCuentaAdicional movimientoCuentaAdicional;
    private Cuenta cuentaSel;
    private RequestContext context;
    private ImpresionLibretaCuenta impresionLibretaCuenta;

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

    private LlamaSP llamaSP;

    public ImpresionLibretaCuentaControllerFO() {
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
            this.servidorEsLinux = ActivacionUsuario.getTipoSOServidorAppl().equals("L");
            this.nombreArchivo = ActivacionUsuario.getRutaImpresionArchivos() + "lib" + ActivacionUsuario.getCodigoUsuario() + ".txt";
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

        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaControllerFO", "init", CapturaError.getErrorException(ex)});
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
        this.itemsMovimientoCuentaAdicionalImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta(), 'S');
        this.itemsMovimientoCuentaAdicionalNoImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');

        // Colocando en una solo lista para mostrar en la ventana
        this.itemsMovimientoCuentaAdicional = new ArrayList<MovimientoCuentaAdicional>();
        this.itemsMovimientoCuentaAdicional.addAll(itemsMovimientoCuentaAdicionalImpresas);
        this.itemsMovimientoCuentaAdicional.addAll(itemsMovimientoCuentaAdicionalNoImpresas);
        //System.out.println("itemsMovimientoCuentaAdicionalNoImpresas " + itemsMovimientoCuentaAdicionalNoImpresas.size());

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

    }

    // -- METODOS DE LA LISTA DE BUSQUEDA DE CUENTAS
    public void preparaBusquedaCuentas(ActionEvent actionEvent) {
        this.setNombresSocio(null);
        this.setItemsCuenta(null);
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
        try {
            this.lineasImpresas = 0;
            // ActivacionUsuario.getRutaImpresora();
            this.setImprimeEnArchivo(false);
            if (this.isImprimeEnArchivo()) {
                ////System.out.println("//192.168.1.3/lx300");
                this.nombreArchivoImprimir = ActivacionUsuario.getRutaImpresionArchivos() + "libreta" + this.getCuenta().getNumero() + ".txt";
            } else {
                this.nombreArchivoImprimir = ActivacionUsuario.getRutaImpresora();
            }

            //System.out.println("nombreArchivoImprimir " + nombreArchivoImprimir + "  ActivacionUsuario.getRutaImpresora() " + ActivacionUsuario.getRutaImpresora());

            //os = new FileOutputStream(this.nombreArchivoImprimir);
            os = new FileOutputStream(this.nombreArchivoImprimir);
            pw = new PrintStream(os);

            if (!this.isImprimeEnArchivo()) {
                pw.close();
                pw.flush();
            }

            //System.out.println("this.nombreArchivo Anverso " + this.nombreArchivo);

        } catch (FileNotFoundException pwe) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));

            context.execute("procesandoDlg.hide()");

            pwe.printStackTrace();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "imprimirAnverso", CapturaError.getErrorException(pwe)});
            return;

        }
        //  opcion = 1;
        context = RequestContext.getCurrentInstance();
        //this.listaImpresionLibretaCuenta = new ArrayList<ImpresionLibretaCuenta>();
        //impresionLibretaCuenta = new ImpresionLibretaCuenta();
        try {

            empezoImpresion = false;
            esReverso = false;

            //numero de posicion en la cartola en la que se va a imprimir
            lineaLibreta = itemsMovimientoCuentaAdicionalImpresas.size() + 1;

            // Estableciendo Formatos de Impresion
            formatoValores = new DecimalFormat("###,###,##0.00");
            formatoFechas = new SimpleDateFormat("dd/MM/yyyy");

            //System.out.println("Impresion lista " + itemsMovimientoCuentaAdicionalNoImpresas.size());

            //Verificando que tenga lineas que impirmi
            if (itemsMovimientoCuentaAdicionalNoImpresas.isEmpty()) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenMovimientos");
                if (this.isImprimeEnArchivo()) {
                    pw.close();
                    pw.flush();
                }
                MuestraMensaje.addAdvertencia(msg);
                context.execute("procesandoDlg.hide()");
                return;
            }

            // Verificando si tiene que irse a la Impresion reversa
            if (lineaLibreta > this.numeroLineasAnverso) {
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeReversoDialogo.show()");
                //  MostramosMensaje de dar vuelta a la libreta
                //System.out.println("Impresion Reverso");
                if (this.isImprimeEnArchivo()) {
                    pw.close();
                    pw.flush();
                }
                return;
            }

            if (lineaLibreta == 1) {
                imprimeLinea("Codigo   : " + this.cuenta.getSocio().getSocioPK().getCodigo());
                imprimeLinea("Socio    : " + this.cuenta.getSocio().getCodigoPersona().getNombreCompleto());
                imprimeLinea("# Cuenta : " + this.cuenta.getNumero());
                imprimeLinea("Firmantes: " + this.cuenta.getCodigoTipoFirma().getNombre());

                // Si tiene Firmantes se imprime en la cabecera
                int numeroFirmante = 0;
                if (!this.listaFirmanteCuenta.isEmpty()) {
                    for (FirmanteCuenta fc : listaFirmanteCuenta) {
                        numeroFirmante++;
                        imprimeLinea("      Firmante " + numeroFirmante + ": " + fc.getPersona().getNombreCompleto());
                    }

                }

                if (numeroFirmante > 0) {
                    for (int i = numeroFirmante; i < 3; i++) {
                        imprimeLinea(null);
                    }
                } else {
                    imprimeLinea(null);
                    imprimeLinea(null);
                    imprimeLinea(null);
                }

                imprimeLinea(null);

                this.empezoImpresion = true;
            }

            for (MovimientoCuentaAdicional mca : itemsMovimientoCuentaAdicionalNoImpresas) {

                // Verificando si es la primera linea para imprimir la cabecera
                if (lineaLibreta <= this.numeroLineasAnverso) {
                    if (!empezoImpresion) {
                        // Recorremos el carro hasta que llegue a la linea correcta
                        for (int c = 0; c <= lineaLibreta + 6; c++) {
                            imprimeLinea(null);;
                        }
                    }
                } else {
                    //System.out.println("Es REverso");
                    esReverso = true;
                    this.itemsMovimientoCuentaAdicionalNoImpresas = this.ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');

                }

                // Si toca el reverso
                if (!esReverso) {
                    transaccion = mca.getMovimientoCuenta().getConceptoTransaccionPro().getConceptoTransaccion().getSiglas();
                    signo = (String.valueOf(mca.getMovimientoCuenta().getTipo()).equals("D")) ? "-" : " ";
                    valor = formatoValores.format(mca.getMovimientoCuenta().getTotalMovimiento().doubleValue());
                    saldo = formatoValores.format(mca.getMovimientoCuenta().getSaldoCuenta().doubleValue());

                    // Imprimiendo la Libreta
                    imprimeLinea(LPad(String.valueOf(lineaLibreta), 5) + LPad(formatoFechas.format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad(transaccion, 12) + RPad(signo + valor.toString(), 12) + RPad(saldo.toString(), 12));

                    //System.out.println(" Anverso " + lineaLibreta);

                    // Verificando si ya empezo a imprimir
                    if (!empezoImpresion) {
                        empezoImpresion = true;
                    }

                    this.codigoMovimientoUltimoImpreso = mca.getCodigoMovimiento();

                    this.lineaLibreta++;
                } else {
                    break;
                }

            }

            // Cerramos la Impresion  
            if (this.imprimeEnArchivo) {
                pw.close();
                pw.flush();
            }

            Thread.sleep(this.lineasImpresas * 1300);

            //Ejecucion de Comando para enviar archivo a Impresora
            //this.ejecutaComando();
            context.execute("procesandoDlg.hide()");

            // Registrando en la base de datos la impresion de la libreta
            this.guardarImpresionLibretaCuenta();

            // Consultando Nuevamemte los Movimientos
            this.buscaEstadoCuentas();

            if (esReverso) {
                //  MostramosMensaje de dar vuelta a la libreta
                context.execute("ImprimeReversoDialogo.show()");
            } 
            this.imprimeRetornoCarro();
            

        } catch (InterruptedException e) {
            context.execute("procesandoDlg.hide()");
            // Error en capa de control
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "imprimirAnverso", CapturaError.getErrorException(e)});
        }
    }

    /**
     * Imprime el rerverso
     *
     * @param actionEvent
     */
    public void imprimirReverso(ActionEvent actionEvent) throws FileNotFoundException {
        //  opcion = 1;

        context = RequestContext.getCurrentInstance();
        this.setImprimeEnArchivo(false);
        boolean terminoLibreta = false;
        this.lineasImpresas = 0;
        try {
            // ActivacionUsuario.getRutaImpresora();
            this.setImprimeEnArchivo(false);
            if (this.isImprimeEnArchivo()) {
                ////System.out.println("//192.168.1.3/lx300");
                this.nombreArchivoImprimir = ActivacionUsuario.getRutaImpresionArchivos() + "libreta" + this.getCuenta().getNumero() + ".txt";
            } else {
                this.nombreArchivoImprimir = ActivacionUsuario.getRutaImpresora();
            }

            //System.out.println("nombreArchivoImprimir " + nombreArchivoImprimir + "  ActivacionUsuario.getRutaImpresora() " + ActivacionUsuario.getRutaImpresora());

            //os = new FileOutputStream(this.nombreArchivoImprimir);
            os = new FileOutputStream(this.nombreArchivoImprimir);
            pw = new PrintStream(os);

            if (!this.isImprimeEnArchivo()) {
                pw.close();
                pw.flush();
            }

            //System.out.println("this.nombreArchivo Anverso " + this.nombreArchivo);

        } catch (FileNotFoundException pwe) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));

            context.execute("procesandoDlg.hide()");

            pwe.printStackTrace();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "imprimirReverso", CapturaError.getErrorException(pwe)});
            return;

        }

        try {

            // Obteniendo los movimientos impresos
            this.itemsMovimientoCuentaAdicionalImpresas = ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalImpreso(this.cuenta.getCodigo(), this.cuenta.getNumeroLibreta(), 'S');
            // Obteniendo los movimientos NO Impresoso
            this.itemsMovimientoCuentaAdicionalNoImpresas = this.ejbFacadeMovimientoCuentaAdicional.getItemsMovimientoAdicionalNoImpreso(this.cuenta.getCodigo(), 'N');

            //System.out.println(" Reverso ");
            if (itemsMovimientoCuentaAdicionalNoImpresas.size() > 0) {

                //System.out.println(" empezoImpresion " + empezoImpresion);
                imprimeLinea(null);
                imprimeLinea(null);

                //System.out.println(" empezoImpresionads " + empezoImpresion + " lineaLibreta " + lineaLibreta);
                // Verificando si se esta imprimiendo ´primera vez el Reverso
                if (!empezoImpresion) {
                    for (int c = 0; c <= this.itemsMovimientoCuentaAdicionalImpresas.size() - this.numeroLineasAnverso; c++) {
                        imprimeLinea(null);
                    }
                }

                for (MovimientoCuentaAdicional mca : itemsMovimientoCuentaAdicionalNoImpresas) {

                    if (lineaLibreta <= this.numeroLineasAnverso + this.numeroLineasReverso) {
                        transaccion = mca.getMovimientoCuenta().getConceptoTransaccionPro().getConceptoTransaccion().getSiglas();
                        signo = (String.valueOf(mca.getMovimientoCuenta().getTipo()).equals("D")) ? "-" : " ";
                        valor = formatoValores.format(mca.getMovimientoCuenta().getTotalMovimiento().doubleValue());
                        saldo = formatoValores.format(mca.getMovimientoCuenta().getSaldoCuenta().doubleValue());

                        // Imprimiendo la Libreta
                        //pw.println(LPad(String.valueOf(lineaLibreta), 5) + LPad(formatoFechas.format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad(transaccion, 12) + RPad(signo + valor.toString(), 12) + RPad(saldo.toString(), 12));
                        imprimeLinea(LPad(String.valueOf(lineaLibreta), 5) + LPad(formatoFechas.format(mca.getMovimientoCuenta().getFechaMovimiento()), 15) + LPad(transaccion, 12) + RPad(signo + valor.toString(), 12) + RPad(saldo.toString(), 12));

                        // Verificando si ya empezo a imprimir
                        if (!empezoImpresion) {
                            empezoImpresion = true;
                        }
                        this.codigoMovimientoUltimoImpreso = mca.getCodigoMovimiento();
                        this.lineaLibreta++;
                        //System.out.println(" REverso Impreso " + lineaLibreta);
                    } else {

                        terminoLibreta = true;
                        break;
                    }

                }

                if (this.isImprimeEnArchivo() && pw != null) {
                    pw.close();
                    pw.flush();
                }

                // Ejecucion de comando para enviar archivo a impresora
                //this.ejecutaComando();
                Thread.sleep(this.lineasImpresas * 1300);

                context.execute("procesandoDlg.hide()");

                //  MostramosMensaje de Asignar Nueva Libreta
                if (terminoLibreta) {
                    context.execute("ImprimeNuevaLibretaDialogo.show()");
                }

                // Registrando en la base de datos la impresion de la libreta
                this.guardarImpresionLibretaCuenta();

                this.buscaEstadoCuentas();

                this.imprimeRetornoCarro();
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenMovimientos");
                context.execute("procesandoDlg.hide()");
                MuestraMensaje.addAdvertencia(msg);
            }

        } catch (InterruptedException e) {
            context.execute("procesandoDlg.hide()");
            // Error en capa de control
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "imprimirReverso", CapturaError.getErrorException(e)});
        }
    }

    /**
     * Ejecutando comando para que el servidor envie directo a la impresora
     */
    private void ejecutaComando() {
        try {
            String comando;
            if (servidorEsLinux) {

                comando = "/bin/cat " + this.nombreArchivoImprimir + " |/usr/bin/smbspool smb:"
                        + ActivacionUsuario.getRutaImpresora() + " jobid1 guest prueba 1 -";                
                String[] command = {"/bin/sh", "-c", comando};
                final Process process = Runtime.getRuntime().exec(command);
                //System.out.println("TERMINO DE EJECUTAR EL COMANDO ");
            }
        } catch (Exception er) {
            // Error en capa de control
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "ejecutaComando", CapturaError.getErrorException(er)});
        }
    }

    private void imprimeLinea(String linea) throws InterruptedException {

        try {
            if (!this.isImprimeEnArchivo()) {
                os = new FileOutputStream(ActivacionUsuario.getRutaImpresora());
                pw = new PrintStream(os);
            }
            if (linea == null) {
                pw.println();
            } else {
                pw.println(linea);
                //Thread.sleep(1500);
                this.lineasImpresas++;
            }

            if (!this.isImprimeEnArchivo()) {
                pw.close();
            }
        } catch (Exception pwe) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "imprimirReverso", CapturaError.getErrorException(pwe)});
        }

    }

    private void imprimeRetornoCarro() throws InterruptedException {

        try {
            if (!this.isImprimeEnArchivo()) {
                os = new FileOutputStream(ActivacionUsuario.getRutaImpresora());
                pw = new PrintStream(os);
            }
            pw.println("\r");

            if (!this.isImprimeEnArchivo()) {
                pw.close();
            }
        } catch (Exception pwe) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "imprimirReverso", CapturaError.getErrorException(pwe)});
        }

    }

    /**
     * Guarda la Impresion de la Libreta
     */
    private void guardarImpresionLibretaCuenta() {

        //System.out.println("this.codigoMovimientoUltimoImpreso " + this.codigoMovimientoUltimoImpreso);

        context = RequestContext.getCurrentInstance();
        try {
         ////System.out.println("Guarda Movimiento");

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_impresion_libreta_cuenta.p_guarda");
            llamaSP.setNumeroParametros(3);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.cuenta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_libreta", this.cuenta.getNumeroLibreta()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movmiento", this.codigoMovimientoUltimoImpreso});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                //System.out.println("Impresion Guardada");
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                //MuestraMensaje.addInformacion(msg);

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
                    new Object[]{"ImpresionLibretaCuentaController", "guardarImpresionLibretaCuenta", CapturaError.getErrorException(ex)});
        }
    }

    public String LPad(String str, Integer length) {
        return str + String.format("%" + (length - str.length()) + "s", "");
    }

    public String RPad(String str, Integer length) {
        return String.format("%" + (length - str.length()) + "s", "") + str;
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
     * @return the signo
     */
    public String getSigno() {
        return signo;
    }

    /**
     * @param signo the signo to set
     */
    public void setSigno(String signo) {
        this.signo = signo;
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
}
