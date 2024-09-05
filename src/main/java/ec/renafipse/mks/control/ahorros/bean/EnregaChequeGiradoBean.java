package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.RetiroCheque;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionFacade;
import ec.renafipse.mks.negocio.ahorros.RetiroChequeFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.primefaces.context.RequestContext;

@ManagedBean(name = "entregaChequeGiradoBean")
@ViewScoped
public class EnregaChequeGiradoBean extends AbstractController<RetiroCheque> implements Serializable {

    @EJB
    private RetiroChequeFacade ejbFacade;

    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private ConceptoTransaccionFacade ejbFacadeConceptoTransaccion;

    //--------------------------------------------------------------------------
    // --PARAMETROS
    private RetiroCheque retiroCheque;
    private RetiroCheque retiroChequeGuardado;
    private LlamaSP llamaSP;
    private RequestContext context;
    private String msg;

    // Variables de impresion    
    boolean empezoImpresion;
    boolean esReverso;
    private Long codigoMovimiento;
    private DecimalFormat formatoValores;
    private SimpleDateFormat formatoFechas;
    private String impresionLibreta;
    private AttributeSet attributeSet;
    private DocFlavor flavor;
    private PrintService[] printService;
    private DocPrintJob docPrintJob;
    private Doc doc;
    private Timestamp fechaEntrega;

    private List<RetiroCheque> itemsRetiroCheques;

    public EnregaChequeGiradoBean() {
        super(RetiroCheque.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"entregaChequeGiradoBean", "init", CapturaError.getErrorException(ex)});
            }
        }
        this.retiroCheque = null;
        llamaSP = new LlamaSP();
        this.buscaRetirosChequesGirados();
    }

    /**
     * Busca los Cheques Girados.
     */
    public void buscaRetirosChequesGirados() {

        this.setItemsRetiroCheques(ejbFacade.getItemsCodigiIfipCodigoAgenciaEstado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'G'));
        this.setRetiroCheque(null);
         //System.out.println("Busca Cheques "+this.itemsRetiroCheques);

    }

    //------------------------------------------------------------------------
    // GUARDA EL RETIRO DEL CHEQUE
    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR EL MOVIMIENTO
    public void guardaEntrega(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();

        if (this.getRetiroCheque() == null) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCheque"));
            context.execute("procesandoDlg.hide()");
            return;
        }
        try {

            retiroChequeGuardado = null;

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            fechaEntrega = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_retiro_cheque.p_entrega");
            llamaSP.setNumeroParametros(21);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", this.retiroCheque.getTalonarioChequeDetalle().getTalonarioCheque().getIfipCuentaEntidadFinanciera().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_retiro", this.retiroCheque.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque", this.retiroCheque.getTalonarioChequeDetalle().getTalonarioChequeDetallePK().getNumeroCheque()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.retiroCheque.getCodigoCuenta().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_beneficiario", this.retiroCheque.getBeneficiario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", this.retiroCheque.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fechaEntrega});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "E"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.retiroCheque.getCodigoCuenta().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.retiroCheque.getCodigoCuenta().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.retiroCheque.getCodigoCuenta().getSocio().getCodigoPersona().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", 1L});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_movimiento", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeEntregado");
                MuestraMensaje.addInformacion(msg);
                retiroChequeGuardado = this.retiroCheque;
                this.codigoMovimiento = Long.parseLong(llamaSP.getListResultado().get(3).toString());

                this.init();
                context.execute("ImprimeComprobanteDialogo.show()");

                //context.execute("MovimientoBovedaFor.update()");
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
                    new Object[]{"entregaChequeGiradoBean", "guardaRetiro", CapturaError.getErrorException(ex)});
        }

    }

    //--------------------------------------------------------------------------
    //-- IMPRESION DE COMPROBANTE
    public void imprimirComprobante(ActionEvent actionEvent) throws IOException {

        context = RequestContext.getCurrentInstance();

        try {

            iniciaImpresoraArchivo();
            impresionLibreta = "";

            // Estableciendo Formatos de Impresion
            formatoValores = new DecimalFormat("###,###,##0.00");
            formatoFechas = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");

            Ifip ifip = this.ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
            IfipAgencia ifipAgencia = this.ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia());
            Usuario usuario = this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario());
            ConceptoTransaccion conceptoTransaccion = this.ejbFacadeConceptoTransaccion.find((long) 11);

            ////System.out.println("Imprimir comprobante");
            imprimeLinea(this.LPad(ifip.getCodigoTipoIfip().getSiglas() + " " + ifip.getNombre(), 5));
            imprimeLinea(null);
            imprimeLinea(this.LPad("MOVIMIENTO DE CUENTA", 15));
            imprimeLinea(null);
            imprimeLinea("AGENCIA           :" + ifipAgencia.getNombre());
            imprimeLinea("CODIGO MOVIMIENTO :" + this.codigoMovimiento.toString());
            imprimeLinea("FECHA MOVIMIENTO  :" + formatoFechas.format(this.fechaEntrega));
            imprimeLinea("FECHA IMPRESION   :" + formatoFechas.format(new java.sql.Timestamp(new Date().getTime())));
            imprimeLinea("SOCIO             :" + this.retiroChequeGuardado.getCodigoCuenta().getSocio().getCodigoPersona().getNombreCompleto());
            imprimeLinea("MONEDA            :" + this.retiroChequeGuardado.getCodigoCuenta().getMoneda().getNombre());
            imprimeLinea("PRODUCTO          :" + this.retiroChequeGuardado.getCodigoCuenta().getProductoIfip().getProducto().getTipoProducto().getNombre());
            imprimeLinea("CUENTA            :" + this.retiroChequeGuardado.getCodigoCuenta().getNumero());
            imprimeLinea("TRANSACCION       :" + conceptoTransaccion.getNombre());
            imprimeLinea("EFECTIVO          :" + formatoValores.format(new Double(0)));
            imprimeLinea("CHEQUES           :" + formatoValores.format(this.retiroChequeGuardado.getValor().doubleValue()));
            imprimeLinea("TOTAL             :" + formatoValores.format(this.retiroChequeGuardado.getValor().doubleValue()));
            //System.out.println("Imprimir comprobante 2");
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(this.LPad("          .................................", 10));
            imprimeLinea(this.LPad("             " + usuario.getCodigoPersona().getNombreCompleto(), 15));
            imprimeLinea(this.LPad("                     " + ActivacionUsuario.getCodigoRol(), 25));
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(null);
            imprimeLinea(this.LPad("          .................................", 10));
            imprimeLinea(this.LPad("             " + this.retiroChequeGuardado.getCodigoCuenta().getSocio().getCodigoPersona().getNombreCompleto(), 15));
            imprimeLinea(this.LPad("                     " + "SOCIO", 25));

            //Enviamos a la Impresora
            //System.out.println("Comprobante: \n" + this.impresionLibreta);
            this.enviaImpresora();

        } catch (InterruptedException ex) {
            MuestraMensaje.addErrorCapaControl();
            context.execute("ImprimirComprobanteDialogo.hide()");
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoCuentaBean", "imprimirComprobante", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Inicia la impresora o el envio al archivo
     *
     * @return True = Correcto False=Error
     */
    private boolean iniciaImpresoraArchivo() {
        context = RequestContext.getCurrentInstance();
        //this.nombreArchivoImprimir = ActivacionUsuario.getRutaImpresora();
        return true;
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

            doc = new SimpleDoc(impresionLibreta.getBytes(), flavor, null);

            // Enviado a Imprimir
            docPrintJob.print(doc, new HashPrintRequestAttributeSet());

        } catch (PrintException pwe) {

            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEnviarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"entregaChequeGiradoBean", "enviaImpresora", CapturaError.getErrorException(pwe)});

            context.execute("procesandoDlg.hide()");

            return false;
        }

        return true;

    }

    /**
     * Realiza la impresiona de la linea en la libreta
     *
     * @param linea
     * @throws InterruptedException
     */
    private void imprimeLinea(String linea) throws InterruptedException {

        try {
            impresionLibreta += ((linea != null) ? linea : "") + "\n";

        } catch (Exception pwe) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"impresionLibretaCuentaController", "imprimirReverso", CapturaError.getErrorException(pwe)});
        }

    }

    public String LPad(String str, Integer length) {
        return str + String.format("%" + (length - str.length()) + "s", "");
    }

    public String RPad(String str, Integer length) {
        return String.format("%" + (length - str.length()) + "s", "") + str;
    }

    /**
     * @return the itemsRetiroCheques
     */
    public List<RetiroCheque> getItemsRetiroCheques() {
        return itemsRetiroCheques;
    }

    /**
     * @param itemsRetiroCheques the itemsRetiroCheques to set
     */
    public void setItemsRetiroCheques(List<RetiroCheque> itemsRetiroCheques) {
        this.itemsRetiroCheques = itemsRetiroCheques;
    }

    /**
     * @return the retiroCheque
     */
    public RetiroCheque getRetiroCheque() {
        return retiroCheque;
    }

    /**
     * @param retiroCheque the retiroCheque to set
     */
    public void setRetiroCheque(RetiroCheque retiroCheque) {
        this.retiroCheque = retiroCheque;
    }

}
