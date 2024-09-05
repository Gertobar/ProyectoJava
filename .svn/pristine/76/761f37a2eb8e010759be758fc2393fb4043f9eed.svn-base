/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.cajas.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.impresiones.ImpresionComprobantes;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaAdicionalFacade;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PrinterName;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vastudillo
 */
@ManagedBean(name = "estadoCuentaBean")
@ViewScoped
public class EstadoCuentaBean implements Serializable {

    @EJB
    private MovimientoCuentaFacade ejbFacade;

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
    private IfipFacade ejbFacadeIfip;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    //---------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    private String msg;
    private String mensajeDialogo;
    private Date fechaInicio;
    private Date fechaFin;
    private String numeroCuenta;
    private String nombresSocio;
    private BigDecimal saldoInicial;

    private GeneraReporte generaReporte;

    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Cuenta cuenta;
    private MovimientoCuenta movimientoCuentaSel;
    private MovimientoCuentaAdicional movimientoCuentaAdicional;
    private Cuenta cuentaSel;
    private RequestContext context;
    private Date fechaActual;
    private LlamaSP llamaSP;

    // Variables de impresion
    private FileOutputStream os;
    private PrintStream pw;
    boolean empezoImpresion;
    boolean esReverso;
    private DecimalFormat formatoValores;
    private SimpleDateFormat formatoFechas;
    private boolean imprimeEnArchivo;
    private String nombreArchivo;
    private long codigoMovimientoUltimoImpreso;
    private String nombreArchivoImprimir;
    private int lineasImpresas;
    private String impresion;
    private AttributeSet attributeSet;
    private DocFlavor flavor;
    private PrintService[] printService;
    private DocPrintJob docPrintJob;
    private Doc doc;

    private List<ProductoIfip> itemsProductoIfip;
    private List<MovimientoCuenta> itemsMovimientoCuenta;
    private List<Cuenta> itemsCuenta;

    @PostConstruct
    public void init() {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            this.moneda = this.ejbFacadeMoneda.find(Long.parseLong("1"));
            this.cambiaMoneda();
            this.setCuenta(null);
            this.setProductoIfip(null);
            this.setFechaInicio(new Date());
            this.setFechaFin(new Date());
            this.setFechaActual(new Date());
            this.saldoInicial = new BigDecimal("0.00");
            this.llamaSP = new LlamaSP();
        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"estadoCuentaBean", "init", CapturaError.getErrorException(ex)});
            }
        }

    }

    /**
     * BUSCA LOS MOVIMIENTOS DE LAS CUENTA DEL SOCIO.
     */
    public void buscaEstadoCuentas() {
        this.saldoInicial = new BigDecimal("0.00");
        if (!this.validaCuenta()) {
            MuestraMensaje.addError(msg);
            return;
        }
        if (this.fechaFin == null && this.fechaInicio == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCriterioConsulta");
            MuestraMensaje.addAdvertencia(msg);
            return;
        }
        if ((this.fechaFin == null && this.fechaInicio != null) || (this.fechaFin != null && this.fechaInicio == null)) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseLasDosFechas");
            MuestraMensaje.addAdvertencia(msg);
            return;
        }
        this.setItemsMovimientoCuenta(null);
        this.setItemsMovimientoCuenta(this.ejbFacade.getItemsEstadoDeCuenta(this.cuenta.getCodigo(), fechaInicio, fechaFin));
        if (!this.itemsMovimientoCuenta.isEmpty()) {
            Long codigoMovimientoAnterior = this.ejbFacade.getCodigoAnteriorDelPrimeroEstadoCuentas(this.itemsMovimientoCuenta.get(0).getCodigo(), this.cuenta.getCodigo());
            if (codigoMovimientoAnterior != null) {
                this.saldoInicial = this.ejbFacade.find(codigoMovimientoAnterior).getSaldoCuenta();
            }
        }
        // Registrar el servicio financiero para la F01 
        try {
            if( !this.getItemsMovimientoCuenta().isEmpty() && this.getItemsMovimientoCuenta().size() > 0 ){
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
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_servicio_fin_tip_can", 4}); // CODIGO 4 PARA CONSULTA DE CUENTAS EN OFICINAS
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha",new java.sql.Timestamp(new Date().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificador", cuenta.getNumero()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cantidad", 1});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observacion","Consulta para la fecha "+new java.sql.Timestamp(this.getFechaInicio().getTime())+" "+new java.sql.Timestamp(this.getFechaFin().getTime())});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                // Invocando al SP
                llamaSP.invocaSP();
                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();
                }else{
                    System.out.println("Error la registrar el servicio financiero desde Consulta de Cuentas.");
                    System.out.println(llamaSP.getError());
                    System.out.println(llamaSP.getErroSql());
                }
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"EstadoCuentaBean", "buscaEstadosCuentas", CapturaError.getErrorException(ex)});
        }
        finally{
             if( llamaSP.getConexionBD()!= null ) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
             }
        }
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

    /*public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

     String nombreReporte;
     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
     FacesContext facesContext = FacesContext.getCurrentInstance();

     setGeneraReporte(new GeneraReporte());
     getGeneraReporte().setParametros(new HashMap<String, Object>());

     getGeneraReporte().getParametros().put("codigoMovimiento", this.movimientoCuentaSel.getCodigo());

     nombreReporte = "movimientoCuenta";

     getGeneraReporte().exporta("/financiero/cajas/movimientosCuentas/reporte/", nombreReporte,
     nombreReporte + String.valueOf(this.movimientoCuentaSel.getCodigo()) + ".pdf",
     "PDF", externalContext, facesContext);

     }*/
    //--------------------------------------------------------------------------
    //-- IMPRESION DE COMPROBANTE
    public void imprimirComprobante(ActionEvent actionEvent) throws IOException {
        if (ActivacionUsuario.getCodigoComputador() == null) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            return;

        }
        /*    this.setImprimeEnArchivo(true);
        
         context = RequestContext.getCurrentInstance();

         context.execute("procesandoDlg.show()");

         iniciaImpresoraArchivo();
         impresion = "";

         try {
         // Estableciendo Formatos de Impresion
         formatoValores = new DecimalFormat("###,###,##0.00");
         formatoFechas = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");

         Ifip ifip = this.ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
         IfipAgencia ifipAgencia = this.ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia());
         Usuario usuario = this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario());

         ////System.out.println("Imprimir comprobante");
         imprimeLinea(this.LPad(ifip.getCodigoTipoIfip().getSiglas() + " " + ifip.getNombre(), 5));
         imprimeLinea(null);
         imprimeLinea(this.LPad("MOVIMIENTO DE CUENTA", 15));
         imprimeLinea(null);
         imprimeLinea("AGENCIA           :" + ifipAgencia.getNombre());
         imprimeLinea("CODIGO MOVIMIENTO :" + this.movimientoCuentaSel.getCodigo().toString());
         imprimeLinea("FECHA MOVIMIENTO  :" + formatoFechas.format(movimientoCuentaSel.getFechaMovimiento()));
         imprimeLinea("FECHA IMPRESION   :" + formatoFechas.format(new java.sql.Timestamp(new Date().getTime())));
         imprimeLinea("SOCIO             :" + this.cuenta.getSocio().getCodigoPersona().getNombreCompleto());
         imprimeLinea("MONEDA            :" + this.getMoneda().getNombre());
         imprimeLinea("PRODUCTO          :" + this.getProductoIfip().getProducto().getTipoProducto().getNombre());
         imprimeLinea("CUENTA            :" + this.cuenta.getNumero());
         imprimeLinea("TRANSACCION       :" + this.movimientoCuentaSel.getConceptoTransaccionPro().getConceptoTransaccion().getNombre());
         imprimeLinea("EFECTIVO          :" + formatoValores.format(this.movimientoCuentaSel.getTotalEfectivo().doubleValue()));
         imprimeLinea("CHEQUES           :" + formatoValores.format(this.movimientoCuentaSel.getTotalCheques().doubleValue()));
         imprimeLinea("TOTAL             :" + formatoValores.format(this.movimientoCuentaSel.getTotalMovimiento().doubleValue()));

         imprimeLinea(null);
         imprimeLinea(null);
         imprimeLinea(null);
         imprimeLinea(null);
         imprimeLinea(this.LPad("          .................................", 10));
         imprimeLinea(this.LPad("             " + usuario.getCodigoPersona().getNombreCompleto(), 15));
         imprimeLinea(this.LPad("                     " + ActivacionUsuario.getCodigoRol(), 25));

         if (this.isImprimeEnArchivo()) {
         //Cerramos el archivo
         pw.close();
         pw.flush();

         } else {
         //Enviamos a la Impresora                
         this.enviaImpresora();
         }

         } catch (InterruptedException ex) {
         MuestraMensaje.addErrorCapaControl();
         context.execute("procesandoDlg.hide()");
         // Registra el error en el log del servidor
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
         new Object[]{"movimientoCuentaBean", "imprimirComprobante", CapturaError.getErrorException(ex)});
         }
         */
        ImpresionComprobantes impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora());
        impresionComprobantes.setMensajeAdicional(cargarMensaje(1l, this.ejbFacadeMovimientoCuentaAdicional.find(movimientoCuentaSel.getCodigo()).getCodigoMovimiento()));
        impresionComprobantes.movimientoCuenta(this.ejbFacadeMovimientoCuentaAdicional.find(movimientoCuentaSel.getCodigo()));

    }
    
      /**
     *
     * @param codigoServicioBoleto
     * @param codigoServicio
     * @return
     */
    public String cargarMensaje(java.lang.Long codigoServicioBoleto, java.lang.Long codigoServicio) {
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_sorteos.pkm_sorteo.p_obtiene_boleto");
            llamaSP.setNumeroParametros(5);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_servicio_boleto", codigoServicioBoleto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_servicio", codigoServicio});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia() });
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario() });
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje", Types.VARCHAR});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                llamaSP.commit();
                Object valorObject = llamaSP.getListResultado().get(0);
                if (valorObject == null){
                    return null;
                }else{
                    String valor = llamaSP.getListResultado().get(0).toString();
                    if (valor != null) {
                        if (valor.length() > 0) {
                            return valor;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
            } else {
                llamaSP.rollback();
                return null;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BoletoComponenteController", "cargarMensaje", CapturaError.getErrorException(e)});
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            return null;
        } finally {
            System.out.println("CIERRA CONECCION");
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     * Realiza la impresiona de la linea en la libreta
     *
     * @param linea
     * @throws InterruptedException
     */
    private void imprimeLinea(String linea) throws InterruptedException {

        try {
            if (!this.isImprimeEnArchivo()) {
                impresion += ((linea != null) ? linea : "") + "\n";
                if (linea != null) {
                    this.lineasImpresas++;
                }
            } else {
                if (linea == null) {
                    pw.println();
                } else {
                    pw.println(linea);
                }
            }

        } catch (Exception pwe) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionCuentaController", "imprimirReverso", CapturaError.getErrorException(pwe)});
        }

    }

    /**
     * Ejecutando comando para que el servidor envie directo a la impresora
     */
    private boolean enviaImpresora() {
        try {
            context = RequestContext.getCurrentInstance();
            /**
             * Add extra tag in modules/sun/jdk/main/module.xml of jbossAs as
             * mentioned: path name="sun/print"
             *
             * Open up resources.jar from your JRE, and extract
             * META-INF/services/javax.print.PrintServiceLookup and copy to
             * location
             * modules/sun/jdk/main/service-loader-resources/META-INF/services
             * of JbossAS.
             *
             * Restart JBoss and run the application.
             */

            //Ubicando la Impresora
            String impresora = ActivacionUsuario.getRutaImpresora();

            attributeSet = new HashAttributeSet();

            attributeSet.add(new PrinterName(impresora, null));

            flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            //System.out.println("impresora: " + impresora);

            //Encontrando la impresora que debe estar conectada o instalada en el servidor
            printService = PrintServiceLookup.lookupPrintServices(flavor, attributeSet);

            //System.out.println("Imprimiendo en : " + printService[0]);

            docPrintJob = printService[0].createPrintJob();

            doc = new SimpleDoc(impresion.getBytes(), flavor, null);

            // Enviado a Imprimir
            docPrintJob.print(doc, new HashPrintRequestAttributeSet());

        } catch (PrintException pwe) {

            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEnviarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionCuentaController", "enviaImpresora", CapturaError.getErrorException(pwe)});

            context.execute("procesandoDlg.hide()");

            return false;
        }

        context.execute("procesandoDlg.hide()");

        return true;

    }

    /**
     * Inicia la impresora o el envio al archivo
     *
     * @return True = Correcto False=Error
     */
    private boolean iniciaImpresoraArchivo() {
        context = RequestContext.getCurrentInstance();

        this.setImprimeEnArchivo(false);

        if (this.isImprimeEnArchivo()) {
            ////System.out.println("//192.168.1.3/lx300");
            this.nombreArchivoImprimir = ActivacionUsuario.getRutaImpresionArchivos() + "libreta" + this.getCuenta().getNumero() + ".txt";
        } else {
            this.nombreArchivoImprimir = ActivacionUsuario.getRutaImpresora();
        }
        if (isImprimeEnArchivo()) {
            try {
                os = new FileOutputStream(this.nombreArchivoImprimir);
            } catch (FileNotFoundException ex) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEnviarImpresora"));
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"impresionCuentaController", "enviaImpresora", CapturaError.getErrorException(ex)});

                context.execute("procesandoDlg.hide()");
                return false;
            }
            pw = new PrintStream(os);
        }
        return true;
    }

    public String LPad(String str, Integer length) {
        return str + String.format("%" + (length - str.length()) + "s", "");
    }

    public String RPad(String str, Integer length) {
        return String.format("%" + (length - str.length()) + "s", "") + str;
    }

    //--- FIN DE IMPRIMIR COMPROBANTE
    //--------------------------------------------------------------------------
    /**
     * Exporta a PDF el Estadod de Cuentas
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void exportaPDF(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("fechaInicio", this.fechaInicio);
        getGeneraReporte().getParametros().put("fechaFin", this.fechaFin);
        getGeneraReporte().getParametros().put("codigoCuenta", this.cuenta.getCodigo());

        nombreReporte = "estadoCuenta";

        getGeneraReporte().exporta("/financiero/cajas/estadosCuentas/reporte/", nombreReporte,
                nombreReporte + "-" + this.cuenta.getNumero() + ".pdf",
                "PDF", externalContext, facesContext);

    }

    /**
     * Exporta a Excel el Estado de Cuentas
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void exportaExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("fechaInicio", this.fechaInicio);
        getGeneraReporte().getParametros().put("fechaFin", this.fechaFin);
        getGeneraReporte().getParametros().put("codigoCuenta", this.cuenta.getCodigo());

        nombreReporte = "estadoCuenta";

        getGeneraReporte().exporta("/financiero/cajas/estadosCuentas/reporte/", nombreReporte,
                nombreReporte + "-" + this.cuenta.getNumero() + ".xls",
                "XLS", externalContext, facesContext);

    }

    /**
     * VALIDA LA CUENTA DEL SOCIO.
     *
     * @return
     */
    private boolean validaCuenta() {
        msg = null;
        ////System.out.println("Valida Cuenta " + this.numeroCuenta + " " + this.productoIfip);
        List<Cuenta> listaCuenta = ejbFacadeCuenta.getItemsCuentas(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                this.numeroCuenta.toUpperCase());
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

    //---------------------------------------------------------------------------
    // -- METODOS DE LA LISTA DE BUSQUEDA DE CUENTAS
    public void preparaBusquedaCuentas(ActionEvent actionEvent) {
        this.setNombresSocio(null);
        this.setItemsCuenta(null);
        this.setItemsMovimientoCuenta(null);
        this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
    }

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
        this.setItemsMovimientoCuenta(null);
        this.buscaEstadoCuentas();
    }

    //---------------------------------------------------------------------------
    // -- METODOS DE EVENTOS DE AJAX DE CUADROS DE TEXTO
    public void buscaCuenta() {
        ////System.out.println("Numero Cuenta " + this.numeroCuenta);
        this.setItemsMovimientoCuenta(null);
        this.setCuenta(null);
        if (!validaCuenta()) {
            MuestraMensaje.addError(msg);
            return;
        }
        buscaEstadoCuentas();
    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    // ------------------ REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
        this.setProductoIfip(null);
        this.setCuenta(null);

    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
     * @return the itemsMovimientoCuenta
     */
    public List<MovimientoCuenta> getItemsMovimientoCuenta() {
        return itemsMovimientoCuenta;
    }

    /**
     * @param itemsMovimientoCuenta the itemsMovimientoCuenta to set
     */
    public void setItemsMovimientoCuenta(List<MovimientoCuenta> itemsMovimientoCuenta) {
        this.itemsMovimientoCuenta = itemsMovimientoCuenta;
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
}
