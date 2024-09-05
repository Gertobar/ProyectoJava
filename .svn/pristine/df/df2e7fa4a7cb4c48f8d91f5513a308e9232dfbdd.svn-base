/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.adquisicones.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.Cliente;
import ec.renafipse.mks.modelo.adquisiciones.ClienteIfip;
import ec.renafipse.mks.modelo.adquisiciones.ClienteIfipPK;
import ec.renafipse.mks.modelo.adquisiciones.ItemVenta;
import ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfip;
import ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfipPK;
import ec.renafipse.mks.modelo.adquisiciones.SustentoTributario;
import ec.renafipse.mks.modelo.adquisiciones.TipoComprobanteCompra;
import ec.renafipse.mks.modelo.adquisiciones.Venta;
import ec.renafipse.mks.modelo.adquisiciones.VentaDetalle;
import ec.renafipse.mks.negocio.adquisiciones.ClienteIfipFacade;
import ec.renafipse.mks.negocio.adquisiciones.ParametroCompraIfipFacade;
import ec.renafipse.mks.negocio.adquisiciones.SustentoTributarioFacade;
import ec.renafipse.mks.negocio.adquisiciones.TipoComprobanteCompraFacade;
import ec.renafipse.mks.negocio.adquisiciones.VentaDetalleFacade;
import ec.renafipse.mks.negocio.adquisiciones.VentaFacade;
import ec.renafipse.mks.negocio.contables.CuentaContableItemVentaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author v.astudillo
 */
@ManagedBean(name = "ventaBean")
@ViewScoped
public class VentaBean extends AbstractController<Venta> implements Serializable {

    @EJB
    private VentaFacade ejbFacade;

    @EJB
    private VentaDetalleFacade ejbFacadeVentaDetalle;

    @EJB
    private ClienteIfipFacade ejbFacadeClienteIfip;

    @EJB
    private SustentoTributarioFacade ejbFacadeSustentoTributario;

    @EJB
    private TipoComprobanteCompraFacade ejbFacadeTipoComprobanteCompra;

    @EJB
    private ParametroCompraIfipFacade ejbFacadeParametroCompraIfip;

    @EJB
    private CuentaContableItemVentaFacade ejbFacadeCuentaContableItemVenta;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS PANTALLA
    private String buscarPor;
    private Long codigoCliente;
    private Long codigoClienteVenta;
    private String nombreCliente; /// para la pantalla principal
    private String nombreClienteLOV; // para el dialogo
    private Date fechaInicio;
    private Date fechaFin;
    private String mensajeCriterio;
    private String descripcionItemVenta;
    private String gravaIva;
    private String msg;
    private String facturaElectronica;
    private BigDecimal subTotal;
    private String serieParteUno;   //inicio de las var para el doc del proveedor
    private String serieParteDos;
    private String serieParteTres;
    private Date fechaMimina;
    private Date fechaMaxima;
    private BigDecimal porcantajeIva;
    private String numeroFactura;
    private String numeroFacturaEdicion;
    private RequestContext context;
    private Long codigoVenta;
    private Timestamp fechaRegistro;
    private Long codigoComprobante;
    private String numeroComprobante;
    private GeneraReporte generaReporte;

    private boolean visiblePanelFecha;
    private boolean esListaValoresClienteBusqueda;
    private boolean deshabilitaBotonAgregarDetalle;
    private boolean deshabilitaBotonCliente;
    private boolean contabilizaFechaFactura;
    private boolean esEdicion;

    private Venta venta;
    private ClienteIfip clienteIfip;
    private ItemVenta itemVenta;
    private VentaDetalle ventaDetalle;
    private VentaDetalle ventaDetalleSel;
    private SustentoTributario sustentoTributario;
    private TipoComprobanteCompra tipoComprobante;
    private LlamaSP llamaSP;

    private List<Venta> itemsVenta;
    private List<ItemVenta> itemsItemVenta;
    private List<VentaDetalle> itemsVentaDetalle;
    private List<ClienteIfip> itemsClienteIfip;
    private List<SustentoTributario> itemsSustentoTributario;
    private List<TipoComprobanteCompra> itemsTipoComprobanteCompra;

    /**
     * Initialize the concrete ItemVenta controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     *
     * @throws java.io.IOException
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {

            this.setVisiblePanelFecha(false);
            this.setFechaMaxima(new Date());
            this.setContabilizaFechaFactura(false);
            ParametroCompraIfip pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(7, ActivacionUsuario.getCodigoIfip()));
            if (pci != null) {
                this.setFechaMimina(ConvierteDato.agregaDias(new Date(), Long.parseLong(pci.getValor().toString()) * -1));
            } else {
                this.setFechaMimina(new Date());
            }

            pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(6, ActivacionUsuario.getCodigoIfip()));
            if (pci != null) {
                this.setContabilizaFechaFactura(pci.getValor().equals("S"));
            }

            // Busncando el porcentaje del IVA
            pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(5L, ActivacionUsuario.getCodigoIfip()));
            
            if (pci != null) {
                this.porcantajeIva = new BigDecimal(pci.getValor());
            } else {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoEstaDefinidoPorcentajeIva"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            setLlamaSP(new LlamaSP());

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ventaBean", "init", CapturaError.getErrorException(ex)});
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlIniciarBean"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex1) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ventaBean", "init", CapturaError.getErrorException(ex1)});
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ventaBean", "init", CapturaError.getErrorException(ex)});
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlIniciarBean"));
            try {
                Sesion.redireccionaPagina(url);
            } catch (IOException ex1) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ventaBean", "init", CapturaError.getErrorException(ex1)});
            }
        }

    }

    public VentaBean() {
        // Inform the Abstract parent controller of the concrete ItemVenta?cap_first Entity
        super(Venta.class);
    }

    //-------------------------------------------------------------------------
    // PROCESO PARA GUARDAR LA VENTA YA SEA UNA CREACION O UNA EDICION
    public void guardar() {
        context = RequestContext.getCurrentInstance();
        if (this.msg != null) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(msg);
            return;
        }

        this.calculaTotales();
        if (this.venta.getTotal().equals(BigDecimal.ZERO)) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(msg);
            return;
        }
        try {

            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Consulta si NO es una edicion  pra insertar la cabecera
            if (!this.esEdicion) {
                // inserto la venta
                System.out.println("Inicia Venta");
                this.insertaVenta();
                System.out.println("Fin Venta");
            } else {
                // Si no es actualizo la venta y elimino el detalle
                if (this.actualizaVenta()) {
                    this.eliminaDetalleVenta();
                }
            }

            if (this.llamaSP.isEjecucionCorrecta()) {
                System.out.println("Inicia Detalle");
                this.insertaDetalleVenta();
                System.out.println("Fin Detalle");
            }

            if (this.llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                context.execute("procesandoDlg.hide()");
                context.execute("VentaDialog.hide()");
                MuestraMensaje.addSatisfactorioPersistencia(1);
                this.buscaVentas();
                this.venta = null;

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                context.execute("procesandoDlg.hide()");
            }

        } catch (Exception ex) {
            llamaSP.rollback();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            context.execute("procesandoDlg.hide()");

            //ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ventaBean", "guardar", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Inserta la Venta
     *
     *
     * @return true o false
     */
    private boolean insertaVenta() {
        this.fechaRegistro = new java.sql.Timestamp(new Date().getTime());
        // Colocando el nombre del proceso
        llamaSP.setNombreSP("mks_adquisiciones.pkg_venta.p_inserta");
        llamaSP.setNumeroParametros(24);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cliente", this.venta.getClienteIfip().getClienteIfipPK().getCodigoCliente()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_comprobante", this.venta.getCodigoDocumentoIfpAgencia().getCodigoTipoComprobante().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_comprobante", this.numeroFactura});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", this.fechaRegistro});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_emision_comprobante", new java.sql.Timestamp(this.venta.getFechaEmisionComprobante().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "I"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.fechaRegistro});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal", venta.getSubtotal()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_con_iva", venta.getSubtotalConIva()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_iva_cero", venta.getSubtotalIvaCero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_descuento", venta.getDescuento()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_retencion_iva", this.venta.getRetencionIva()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_retencion_renta", this.venta.getRetencionRenta()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva", this.venta.getIva()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"P_total", this.venta.getTotal()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.venta.getObservaciones()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaRegistro});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_autorizacion", this.venta.getNumeroAutorizacion()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_factura_electronica", Character.toString(venta.getCodigoDocumentoIfpAgencia().getEsFacturaElectronica())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_sustento", this.venta.getCodigoSustento().getCodigo()});

        
//        for (int i=0;i<llamaSP.getListParametrosEntrada().size();i++){
//            System.out.println(llamaSP.getListParametrosEntrada().get(i)[0]+"--"+(String)llamaSP.getListParametrosEntrada().get(i)[1]);
//        }
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.codigoVenta = Long.parseLong(llamaSP.getListResultado().get(0).toString());
            this.venta.setCodigo(codigoVenta);
        }
        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Actualiza la venta
     *
     * @return true o false
     */
    private boolean actualizaVenta() {

        this.fechaRegistro = new java.sql.Timestamp(new Date().getTime());

        // Colocando el nombre del proceso
        llamaSP.setNombreSP("mks_adquisiciones.pkg_venta.p_actualiza");
        llamaSP.setNumeroParametros(24);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.venta.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cliente", this.venta.getClienteIfip().getClienteIfipPK().getCodigoCliente()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_comprobante", this.venta.getCodigoDocumentoIfpAgencia().getCodigoTipoComprobante().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_comprobante", this.numeroFactura});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", new java.sql.Timestamp(this.venta.getFechaRegistro().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_emision_comprobante", new java.sql.Timestamp(this.venta.getFechaEmisionComprobante().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "I"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.fechaRegistro});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal", venta.getSubtotal()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_con_iva", venta.getSubtotalConIva()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_iva_cero", venta.getSubtotalIvaCero()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_descuento", venta.getDescuento()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_retencion_iva", this.venta.getRetencionIva()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_retencion_renta", this.venta.getRetencionRenta()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva", this.venta.getIva()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"P_total", this.venta.getTotal()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.venta.getObservaciones()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", new java.sql.Timestamp(this.venta.getFechaSistema().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_autorizacion", this.venta.getNumeroAutorizacion()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_factura_electronica", String.valueOf(venta.getCodigoDocumentoIfpAgencia().getEsFacturaElectronica())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_sustento", this.venta.getCodigoSustento().getCodigo()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Elimina el detalle esto se hace cuando es una edicion
     *
     * @return true o false
     */
    private boolean eliminaDetalleVenta() {

        // Colocando el nombre del proceso
        llamaSP.setNombreSP("mks_adquisiciones.pkg_venta_detalle.p_elimina_detalle");
        llamaSP.setNumeroParametros(1);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_venta", this.venta.getCodigo()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();

    }

    /**
     * Insertando el detalle de la venta de acuerdo a la lista
     *
     * @return true o false
     */
    private boolean insertaDetalleVenta() {

        // Colocando el nombre del proceso
        llamaSP.setNombreSP("mks_adquisiciones.pkg_venta_detalle.p_inserta");
        llamaSP.setNumeroParametros(11);

        for (VentaDetalle vd : itemsVentaDetalle) {

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_venta", this.venta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_item", vd.getCodigoItem().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal", vd.getSubtotal()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_con_iva", vd.getSubtotalConIva()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_iva_cero", vd.getSubtotalIvaCero()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_porcentaje_iva", vd.getPorcentajeIva()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva", vd.getIva()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_descuento", vd.getDescuento()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", vd.getTotal()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_descripcion", vd.getDescripcion()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_grava_iva", String.valueOf(vd.getGravaIva())});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            if (!llamaSP.isEjecucionCorrecta()) {
                return llamaSP.isEjecucionCorrecta();
            }

        }

        return llamaSP.isEjecucionCorrecta();
    }
    // FIN DE CREAR O EDITAR LA VENTA
    //----------------------------------------------------------------------------

    //----------------------------------------------------------------------------
    // -- CONTABILIZA LA VENTA
    public void contabilizar() {

        context = RequestContext.getCurrentInstance();

        if (this.venta.getCodigoEstado().getCodigo() == 2) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("VentaYaContabilizada"));
            return;
        }
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Colocando el nombre del proceso
            llamaSP.setNombreSP("mks_contables.pkm_comprobante.p_comprobante_venta");
            llamaSP.setNumeroParametros(9);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_venta", this.venta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", ActivacionUsuario.getCodigoPeriodo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia_ori", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", venta.getTotal()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_contabilizado_por", ActivacionUsuario.getCodigoUsuario()});

            //Parametros de Salida
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_comprobante", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            if (this.llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                context.execute("procesandoDlg.hide()");
                this.numeroComprobante = llamaSP.getListResultado().get(0).toString();
                this.codigoComprobante = Long.parseLong(llamaSP.getListResultado().get(1).toString());
                this.buscaVentas();
                this.venta = null;
                MuestraMensaje.addSatisfactorio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("VentaCompraContablizadaCorrectamente"));
                context.execute("ImprimeComprobanteContableDialogo.show()");

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                context.execute("procesandoDlg.hide()");
            }

        } catch (NumberFormatException ex) {
            llamaSP.rollback();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            context.execute("procesandoDlg.hide()");

            //ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ventaBean", "contabilizar", CapturaError.getErrorException(ex)});
        }
    }
    // FIN DE CONTABILIZACION
    //----------------------------------------------------------------------------------

    /**
     * Cerrar la Ventana
     */
    public void cancelar() {       
        this.buscaVentas();
        this.venta = null;
        context = RequestContext.getCurrentInstance();
        List<String> listaComponentesActualizar = new ArrayList<String>();
        listaComponentesActualizar.add("barraForm:contabilizarBot");
        listaComponentesActualizar.add("barraForm:editaBot");
        listaComponentesActualizar.add("ListaClientesForm:datalist");
        context.update(listaComponentesActualizar);

    }

    /**
     * Busca las ventas del Cliente
     */
    public void buscaVentas() {
        this.itemsVenta = null;
        this.venta = null;
        if (codigoCliente != null) {
            ClienteIfip clienteIfipCriterio = this.ejbFacadeClienteIfip.find(new ClienteIfipPK(codigoCliente, ActivacionUsuario.getCodigoIfip()));
            if (clienteIfipCriterio == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ClienteNoExiste"));
                return;
            }
            this.nombreCliente = clienteIfipCriterio.getCliente().getPersona().getNombreCompleto();
            this.itemsVenta = this.ejbFacade.getItemsClienteIfip(codigoCliente, ActivacionUsuario.getCodigoIfip());
        }

    }

    public void calculaTotales() {

        this.msg = null;

        this.venta.setSubtotal(BigDecimal.ZERO);
        this.venta.setSubtotalIvaCero(BigDecimal.ZERO);
        this.venta.setSubtotalConIva(BigDecimal.ZERO);
        this.venta.setIva(BigDecimal.ZERO);
        this.venta.setTotal(BigDecimal.ZERO);
        for (VentaDetalle vd : itemsVentaDetalle) {
            this.venta.setSubtotal(venta.getSubtotal().add(vd.getSubtotal()));
            this.venta.setSubtotalIvaCero(venta.getSubtotalIvaCero().add(vd.getSubtotalIvaCero()));
            this.venta.setSubtotalConIva(venta.getSubtotalConIva().add(vd.getSubtotalConIva()));
            this.venta.setIva(venta.getIva().add(vd.getIva()));
            this.venta.setTotal(this.venta.getTotal().add(vd.getTotal()));
        }

        venta.setTotal(this.venta.getTotal().subtract(this.venta.getDescuento()).subtract(this.venta.getRetencionIva()).subtract(this.venta.getRetencionRenta()));

        if (venta.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TotalMayorCero"));
            MuestraMensaje.addError(msg);
        }

    }

    //---------------------------------------------------------------
    // METODOS DE MANIPULACION DEL DETALLE
    public void agregaDetalle() {

        if (!validaDetalle()) {
            return;
        }

        if (this.itemsVentaDetalle == null) {
            this.itemsVentaDetalle = new ArrayList<VentaDetalle>();
        } else if (this.itemsVentaDetalle.isEmpty()) {
            this.itemsVentaDetalle = new ArrayList<VentaDetalle>();
        }

        this.ventaDetalle = new VentaDetalle();
        this.ventaDetalle.setCodigoItem(itemVenta);
        this.ventaDetalle.setCodigoVenta(venta);
        this.ventaDetalle.setCodigo(this.itemsVentaDetalle.size() + 1L);
        this.ventaDetalle.setGravaIva(this.gravaIva.charAt(0));
        this.ventaDetalle.setPorcentajeIva(getGravaIva().equals("S") ? porcantajeIva : new BigDecimal("0.00"));
        this.ventaDetalle.setIva(subTotal.multiply(this.ventaDetalle.getPorcentajeIva()).divide(new BigDecimal("100")));
        this.ventaDetalle.setDescuento(new BigDecimal("0.00"));
        this.ventaDetalle.setSubtotalConIva(getGravaIva().equals("S") ? subTotal : new BigDecimal("0.00"));
        this.ventaDetalle.setSubtotalIvaCero(getGravaIva().equals("N") ? subTotal : new BigDecimal("0.00"));
        this.ventaDetalle.setSubtotal(subTotal);
        this.ventaDetalle.setDescripcion(this.descripcionItemVenta);
        this.ventaDetalle.setTotal(subTotal.add(this.ventaDetalle.getIva()).subtract(this.ventaDetalle.getDescuento()));
        this.itemsVentaDetalle.add(ventaDetalle);
        this.calculaTotales();
        this.itemVenta = null;
        this.descripcionItemVenta = null;
        this.gravaIva = null;
        this.subTotal = null;
        /*} else {
         MuestraMensaje.addError(msg);
         }*/

    }

    /**
     * Cuando se modifica el detalle
     */
    public void modificaDetalle() {
        this.ventaDetalleSel.setPorcentajeIva(ventaDetalleSel.getGravaIva() == 'S' ? porcantajeIva : new BigDecimal("0.00"));
        this.ventaDetalleSel.setIva(ventaDetalleSel.getSubtotal().multiply(this.ventaDetalleSel.getPorcentajeIva()).divide(new BigDecimal("100")));
        this.ventaDetalleSel.setDescuento(new BigDecimal("0.00"));
        this.ventaDetalleSel.setSubtotalConIva(ventaDetalleSel.getGravaIva() == 'S' ? ventaDetalleSel.getSubtotal() : new BigDecimal("0.00"));
        this.ventaDetalleSel.setSubtotalIvaCero(ventaDetalleSel.getGravaIva() == 'N' ? ventaDetalleSel.getSubtotal() : new BigDecimal("0.00"));
        this.ventaDetalleSel.setTotal(ventaDetalleSel.getSubtotal().add(this.ventaDetalleSel.getIva()).subtract(this.ventaDetalleSel.getDescuento()));
        this.calculaTotales();
    }

    /**
     * Cuando elimina el detalle
     */
    public void eliminaDetalle() {
        this.itemsVentaDetalle.remove(ventaDetalleSel);
        this.calculaTotales();
    }

    /**
     * Valida el detalle a ingresar para habilitar el boton agregar
     *
     * @return Si el detalle cumple requerimientos.
     */
    public boolean validaDetalle() {
        boolean esValido = true;
        this.deshabilitaBotonAgregarDetalle = true;
        if (this.itemVenta != null && this.descripcionItemVenta != null && this.gravaIva != null && this.subTotal != null) {
            if (this.subTotal.compareTo(new BigDecimal("0.00")) <= 0 || this.descripcionItemVenta.trim().length() <= 6) {
                esValido = false;
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RequerimientosDetalleVentaIncumplidos"));
            } else {
                if (this.itemsVentaDetalle != null) {
                    if (!this.itemsVentaDetalle.isEmpty()) {
                        for (VentaDetalle vd : itemsVentaDetalle) {
                            if (vd.getCodigoItem().getCodigo() == itemVenta.getCodigo()) {
                                esValido = false;
                                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ItemYaAgregado"));
                                return esValido;
                            }
                        }
                    }
                }
            }
        } else {
            esValido = false;
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseValoresCamposDetalle"));
        }
        return esValido;

    }

    /**
     * Valida que el numeor de factura no este ingresada ya en las ventas
     */
    public void validaNumeroFactura() {
        this.msg = null;
        boolean validaNumeroFac = true;
        if (this.serieParteUno != null
                && this.serieParteDos != null
                && this.serieParteTres != null
                && this.venta.getCodigoDocumentoIfpAgencia().getCodigoTipoComprobante() != null
                && clienteIfip != null) {

            this.numeroFactura = this.serieParteUno.trim() + this.serieParteDos.trim() + this.serieParteTres.trim();

            if (this.esEdicion && numeroFacturaEdicion != null) {
                if (this.numeroFactura.equals(this.numeroFacturaEdicion)) {
                    validaNumeroFac = false;
                }
            }

            if (validaNumeroFac) {
                List<Venta> listaVenta = this.ejbFacade.getItemsNumeroComprobanteClienteIfip(numeroFactura, this.clienteIfip.getClienteIfipPK().getCodigoCliente(), this.getVenta().getCodigoDocumentoIfpAgencia().getCodigoTipoComprobante().getCodigo());
                if (listaVenta != null) {
                    if (!listaVenta.isEmpty()) {
                        this.serieParteUno = null;
                        this.serieParteDos = null;
                        this.serieParteTres = null;
                        numeroFactura = null;
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroFacturaIngresada"));
                        MuestraMensaje.addError(msg);
                    }
                }
            }
        }
    }

    /**
     * Busca el cliente para la venta
     */
    public void buscaCliente() {
        this.msg = null;
        if (codigoClienteVenta != null) {
            clienteIfip = this.ejbFacadeClienteIfip.find(new ClienteIfipPK(codigoClienteVenta, ActivacionUsuario.getCodigoIfip()));
            if (clienteIfip != null) {
                this.venta.setClienteIfip(clienteIfip);
            } else {
                this.codigoClienteVenta = null;
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ClienteNoExiste"));
                MuestraMensaje.addError(msg);
            }
        }
    }

    /**
     * Busca los clientes para lista de valores
     */
    public void buscaClientes() {

        if (Validaciones.cumpleRequerimientoCampo(this.nombreClienteLOV, 6)) {
            this.mensajeCriterio = null;
            this.itemsClienteIfip = this.ejbFacadeClienteIfip.getItemsNombrePersonaCodigoIfip(ActivacionUsuario.getCodigoIfip(), this.nombreClienteLOV);
        } else {
            this.mensajeCriterio = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
        }

    }

    /**
     * Metodo de Seleccion del cliente
     */
    public void seleccionaCliente() {
        if (this.isEsListaValoresClienteBusqueda()) {
            this.codigoCliente = clienteIfip.getClienteIfipPK().getCodigoCliente();
            this.nombreCliente = clienteIfip.getCliente().getPersona().getNombreCompleto();
            this.buscaVentas();
            this.venta = null;
            context = RequestContext.getCurrentInstance();
            List<String> listaComponentesActualizar = new ArrayList<String>();
            listaComponentesActualizar.add("barraForm:criterioTex");
            listaComponentesActualizar.add("barraForm:nombreCliente");
            context.update(listaComponentesActualizar);
        } else {
            this.codigoCliente = null;
            this.nombreCliente = null;
            this.venta.setClienteIfip(clienteIfip);
            this.codigoClienteVenta = clienteIfip.getClienteIfipPK().getCodigoCliente();
            // Actualizando componentes de la vista
            context = RequestContext.getCurrentInstance();
            List<String> listaComponentesActualizar = new ArrayList<String>();
            listaComponentesActualizar.add("VentaForm:codigoCliente");
            listaComponentesActualizar.add("VentaForm:identificacionCliente");
            listaComponentesActualizar.add("VentaForm:nombreCliente");
            listaComponentesActualizar.add("VentaForm:direccionCliente");
            listaComponentesActualizar.add("VentaForm:telefonoCliente");
            context.update(listaComponentesActualizar);
        }
    }

    /**
     * Cuando cambia el criterio se visibiliza los paneles de busqueda de
     * cliente o fecha
     */
    public void cambiaCriterio() {
        this.setItemsVenta(null);
        this.setItemsClienteIfip(null);
        this.codigoCliente = null;
        this.nombreCliente = null;
        this.nombreClienteLOV = null;
        this.fechaFin = null;
        this.fechaInicio = null;
        this.visiblePanelFecha = false;

        if (this.buscarPor.equals("F")) {
            this.visiblePanelFecha = true;

        }
    }

    //-------------------------------------------------------------------------
    // PREPARACIONES DE COMPONENTES MEDIANTE ACTION EVENT
    /**
     * Prepara la Creación de la Venta
     *
     * @param actionEvent
     */
    public void preparaCreacion(ActionEvent actionEvent) {

        if (this.venta != null) {
            this.venta.setClienteIfip(null);
            this.venta = null;
        }

        this.setVenta(new Venta());
        this.clienteIfip = null;
        this.setItemsSustentoTributario(ejbFacadeSustentoTributario.getItemsSustentoTribEliminado('N'));
        this.setItemsItemVenta(ejbFacadeCuentaContableItemVenta.getItemsItemVentaIfip(ActivacionUsuario.getCodigoIfip(), 'N'));
        this.itemsVentaDetalle = null;
        this.itemVenta = null;
        this.getVenta().setSubtotal(new BigDecimal("0.00"));
        this.getVenta().setSubtotalConIva(new BigDecimal("0.00"));
        this.getVenta().setSubtotalIvaCero(new BigDecimal("0.00"));
        this.getVenta().setIva(new BigDecimal("0.00"));
        this.getVenta().setRetencionIva(new BigDecimal("0.00"));
        this.getVenta().setRetencionRenta(new BigDecimal("0.00"));
        this.getVenta().setDescuento(new BigDecimal("0.00"));
        this.getVenta().setTotal(new BigDecimal("0.00"));
        this.descripcionItemVenta = null;
        this.subTotal = null;
        this.deshabilitaBotonAgregarDetalle = true;
        this.gravaIva = null;
        this.codigoClienteVenta = null;
        this.deshabilitaBotonCliente = false;
        this.facturaElectronica = null;
        this.codigoClienteVenta = null;
        this.sustentoTributario = null;
        this.setTipoComprobante(null);
        this.serieParteUno = null;
        this.serieParteDos = null;
        this.serieParteTres = null;
        this.descripcionItemVenta = null;
        this.setEsEdicion(false);
        numeroFacturaEdicion = null;
        this.numeroFactura = null;

    }

    /**
     * Prepara la Edicion de la Venta
     *
     * @param actionEvent
     */
    public void preparaEdicion(ActionEvent actionEvent) {
        this.setItemsSustentoTributario(ejbFacadeSustentoTributario.getItemsSustentoTribEliminado('N'));
        this.codigoClienteVenta = this.venta.getClienteIfip().getClienteIfipPK().getCodigoCliente();
        this.buscaCliente();
        this.setItemsItemVenta(ejbFacadeCuentaContableItemVenta.getItemsItemVentaIfip(ActivacionUsuario.getCodigoIfip(), 'N'));
        this.deshabilitaBotonCliente = true;
        this.descripcionItemVenta = null;
        this.subTotal = null;
        this.deshabilitaBotonAgregarDetalle = true;
        this.gravaIva = null;
        this.deshabilitaBotonCliente = true;
        numeroFacturaEdicion = venta.getNumeroComprobante();
        this.numeroFactura = venta.getNumeroComprobante();
        this.setEsEdicion(true);
        this.serieParteUno = this.venta.getNumeroComprobante().substring(0, 3);
        this.serieParteDos = this.venta.getNumeroComprobante().substring(3, 6);
        this.serieParteTres = this.venta.getNumeroComprobante().substring(6, this.venta.getNumeroComprobante().length());
        this.itemsVentaDetalle = null;
        this.itemsVentaDetalle = ejbFacadeVentaDetalle.getItemsCodigoVenta(venta.getCodigo());

        if (this.venta.getCodigoSustento().getEsParaLiquidacion() == 'S') {
            this.itemsTipoComprobanteCompra = this.ejbFacadeTipoComprobanteCompra.getItemsLiquidacionEliminado('S', 'N');
        } else {
            this.itemsTipoComprobanteCompra = this.ejbFacadeTipoComprobanteCompra.getItemsTipoComprobanteEliminado('N');
        }

        context = RequestContext.getCurrentInstance();

        if (this.venta.getCodigoEstado().getCodigo() == 2) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("VentaYaContabilizada"));
            return;
        }

        context.execute("procesandoDlg.hide()");
        context.execute("VentaDialog.show()");

    }

    /**
     * Prepara para la lista de valores de clientes para el registro de la
     * venta.
     *
     * @param actionEvent
     */
    public void preparaListaValoresClienteVenta(ActionEvent actionEvent) {
        this.itemsClienteIfip = null;
        this.nombreClienteLOV = null;
        this.codigoClienteVenta = null;
        clienteIfip = null;
        this.esListaValoresClienteBusqueda = false;
        this.venta.setClienteIfip(null);

    }

    /**
     * Prepara para la lista de valores de clientes para la busqueda de ventas.
     *
     * @param actionEvent
     */
    public void preparListaValoresClienteBusqueda(ActionEvent actionEvent) {
        this.itemsClienteIfip = null;
        this.nombreClienteLOV = null;
        this.nombreCliente = null;
        this.codigoCliente = null;
        clienteIfip = null;
        this.esListaValoresClienteBusqueda = true;

    }

    //--------------------------------------------------------------------------------
    // IMPRESIONES
    /**
     * Imprimie el comprobante contable
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeComprobanteContable(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        context = RequestContext.getCurrentInstance();
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigo", this.codigoComprobante);

        nombreReporte = "comprobante";

        getGeneraReporte().exporta("/contable/comprobantes/comprobante/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.numeroComprobante) + ".pdf",
                "PDF", externalContext, facesContext);

    }

    //--------------------------------------------------------------------------------
    // CAMBIO DE COMBOS
    public void cambiaCodigoSustento() {

        setFacturaElectronica((this.getFacturaElectronica() == null) ? "N" : getFacturaElectronica());

        if (this.venta.getCodigoSustento() != null) {
            if (this.venta.getCodigoSustento().getEsParaLiquidacion() == 'S') {
                //System.out.println("Entro 1 cambio sustento");
                this.itemsTipoComprobanteCompra = this.ejbFacadeTipoComprobanteCompra.getItemsLiquidacionEliminado('S', 'N');
            } else {
                //System.out.println("Entro 3 cambio sustento");
                this.itemsTipoComprobanteCompra = this.ejbFacadeTipoComprobanteCompra.getItemsTipoComprobanteEliminado('N');
            }
            this.venta.getCodigoDocumentoIfpAgencia().setCodigoTipoComprobante(null);
        }

    }

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @return the nombreClienteLOV
     */
    public String getNombreClienteLOV() {
        return nombreClienteLOV;
    }

    /**
     * @param nombreClienteLOV the nombreClienteLOV to set
     */
    public void setNombreClienteLOV(String nombreClienteLOV) {
        this.nombreClienteLOV = nombreClienteLOV;
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
     * @return the venta
     */
    public Venta getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * @return the itemsVenta
     */
    public List<Venta> getItemsVenta() {
        return itemsVenta;
    }

    /**
     * @param itemsVenta the itemsVenta to set
     */
    public void setItemsVenta(List<Venta> itemsVenta) {
        this.itemsVenta = itemsVenta;
    }

    /**
     * @return the itemsClienteIfip
     */
    public List<ClienteIfip> getItemsClienteIfip() {
        return itemsClienteIfip;
    }

    /**
     * @param itemsClienteIfip the itemsClienteIfip to set
     */
    public void setItemsClienteIfip(List<ClienteIfip> itemsClienteIfip) {
        this.itemsClienteIfip = itemsClienteIfip;
    }

    /**
     * @return the visiblePanelFecha
     */
    public boolean isVisiblePanelFecha() {
        return visiblePanelFecha;
    }

    /**
     * @param visiblePanelFecha the visiblePanelFecha to set
     */
    public void setVisiblePanelFecha(boolean visiblePanelFecha) {
        this.visiblePanelFecha = visiblePanelFecha;
    }

    /**
     * @return the clienteIfip
     */
    public ClienteIfip getClienteIfip() {
        return clienteIfip;
    }

    /**
     * @param clienteIfip the clienteIfip to set
     */
    public void setClienteIfip(ClienteIfip clienteIfip) {
        this.clienteIfip = clienteIfip;
    }

    /**
     * @return the codigoCliente
     */
    public Long getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * @return the itemsVentaDetalle
     */
    public List<VentaDetalle> getItemsVentaDetalle() {
        return itemsVentaDetalle;
    }

    /**
     * @param itemsVentaDetalle the itemsVentaDetalle to set
     */
    public void setItemsVentaDetalle(List<VentaDetalle> itemsVentaDetalle) {
        this.itemsVentaDetalle = itemsVentaDetalle;
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
     * @return the esListaValoresClienteBusqueda
     */
    public boolean isEsListaValoresClienteBusqueda() {
        return esListaValoresClienteBusqueda;
    }

    /**
     * @param esListaValoresClienteBusqueda the esListaValoresClienteBusqueda to
     * set
     */
    public void setEsListaValoresClienteBusqueda(boolean esListaValoresClienteBusqueda) {
        this.esListaValoresClienteBusqueda = esListaValoresClienteBusqueda;
    }

    /**
     * @return the itemsItemVenta
     */
    public List<ItemVenta> getItemsItemVenta() {
        return itemsItemVenta;
    }

    /**
     * @param itemsItemVenta the itemsItemVenta to set
     */
    public void setItemsItemVenta(List<ItemVenta> itemsItemVenta) {
        this.itemsItemVenta = itemsItemVenta;
    }

    /**
     * @return the itemVenta
     */
    public ItemVenta getItemVenta() {
        return itemVenta;
    }

    /**
     * @param itemVenta the itemVenta to set
     */
    public void setItemVenta(ItemVenta itemVenta) {
        this.itemVenta = itemVenta;
    }

    /**
     * @return the descripcionItemVenta
     */
    public String getDescripcionItemVenta() {
        return descripcionItemVenta;
    }

    /**
     * @param descripcionItemVenta the descripcionItemVenta to set
     */
    public void setDescripcionItemVenta(String descripcionItemVenta) {
        this.descripcionItemVenta = descripcionItemVenta;
    }

    /**
     * @return the gravaIva
     */
    public String getGravaIva() {
        return gravaIva;
    }

    /**
     * @param gravaIva the gravaIva to set
     */
    public void setGravaIva(String gravaIva) {
        this.gravaIva = gravaIva;
    }

    /**
     * @return the subTotal
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the deshabilitaBotonAgregarDetalle
     */
    public boolean isDeshabilitaBotonAgregarDetalle() {
        return deshabilitaBotonAgregarDetalle;
    }

    /**
     * @param deshabilitaBotonAgregarDetalle the deshabilitaBotonAgregarDetalle
     * to set
     */
    public void setDeshabilitaBotonAgregarDetalle(boolean deshabilitaBotonAgregarDetalle) {
        this.deshabilitaBotonAgregarDetalle = deshabilitaBotonAgregarDetalle;
    }

    /**
     * @return the ventaDetalle
     */
    public VentaDetalle getVentaDetalle() {
        return ventaDetalle;
    }

    /**
     * @param ventaDetalle the ventaDetalle to set
     */
    public void setVentaDetalle(VentaDetalle ventaDetalle) {
        this.ventaDetalle = ventaDetalle;
    }

    /**
     * @return the codigoClienteVenta
     */
    public Long getCodigoClienteVenta() {
        return codigoClienteVenta;
    }

    /**
     * @param codigoClienteVenta the codigoClienteVenta to set
     */
    public void setCodigoClienteVenta(Long codigoClienteVenta) {
        this.codigoClienteVenta = codigoClienteVenta;
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
     * @return the deshabilitaBotonCliente
     */
    public boolean isDeshabilitaBotonCliente() {
        return deshabilitaBotonCliente;
    }

    /**
     * @param deshabilitaBotonCliente the deshabilitaBotonCliente to set
     */
    public void setDeshabilitaBotonCliente(boolean deshabilitaBotonCliente) {
        this.deshabilitaBotonCliente = deshabilitaBotonCliente;
    }

    /**
     * @return the sustentoTributario
     */
    public SustentoTributario getSustentoTributario() {
        return sustentoTributario;
    }

    /**
     * @param sustentoTributario the sustentoTributario to set
     */
    public void setSustentoTributario(SustentoTributario sustentoTributario) {
        this.sustentoTributario = sustentoTributario;
    }

    /**
     * @return the itemsSustentoTributario
     */
    public List<SustentoTributario> getItemsSustentoTributario() {
        return itemsSustentoTributario;
    }

    /**
     * @param itemsSustentoTributario the itemsSustentoTributario to set
     */
    public void setItemsSustentoTributario(List<SustentoTributario> itemsSustentoTributario) {
        this.itemsSustentoTributario = itemsSustentoTributario;
    }

    /**
     * @return the itemsTipoComprobanteCompra
     */
    public List<TipoComprobanteCompra> getItemsTipoComprobanteCompra() {
        return itemsTipoComprobanteCompra;
    }

    /**
     * @param itemsTipoComprobanteCompra the itemsTipoComprobanteCompra to set
     */
    public void setItemsTipoComprobanteCompra(List<TipoComprobanteCompra> itemsTipoComprobanteCompra) {
        this.itemsTipoComprobanteCompra = itemsTipoComprobanteCompra;
    }

    /**
     * @return the facturaElectronica
     */
    public String getFacturaElectronica() {
        return facturaElectronica;
    }

    /**
     * @param facturaElectronica the facturaElectronica to set
     */
    public void setFacturaElectronica(String facturaElectronica) {
        this.facturaElectronica = facturaElectronica;
    }

    /**
     * @return the serieParteUno
     */
    public String getSerieParteUno() {
        return serieParteUno;
    }

    /**
     * @param serieParteUno the serieParteUno to set
     */
    public void setSerieParteUno(String serieParteUno) {
        this.serieParteUno = serieParteUno;
    }

    /**
     * @return the serieParteDos
     */
    public String getSerieParteDos() {
        return serieParteDos;
    }

    /**
     * @param serieParteDos the serieParteDos to set
     */
    public void setSerieParteDos(String serieParteDos) {
        this.serieParteDos = serieParteDos;
    }

    /**
     * @return the serieParteTres
     */
    public String getSerieParteTres() {
        return serieParteTres;
    }

    /**
     * @param serieParteTres the serieParteTres to set
     */
    public void setSerieParteTres(String serieParteTres) {
        this.serieParteTres = serieParteTres;
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
     * @return the contabilizaFechaFactura
     */
    public boolean isContabilizaFechaFactura() {
        return contabilizaFechaFactura;
    }

    /**
     * @param contabilizaFechaFactura the contabilizaFechaFactura to set
     */
    public void setContabilizaFechaFactura(boolean contabilizaFechaFactura) {
        this.contabilizaFechaFactura = contabilizaFechaFactura;
    }

    /**
     * @return the ventaDetalleSel
     */
    public VentaDetalle getVentaDetalleSel() {
        return ventaDetalleSel;
    }

    /**
     * @param ventaDetalleSel the ventaDetalleSel to set
     */
    public void setVentaDetalleSel(VentaDetalle ventaDetalleSel) {
        this.ventaDetalleSel = ventaDetalleSel;
    }

    /**
     * @return the esEdicion
     */
    public boolean isEsEdicion() {
        return esEdicion;
    }

    /**
     * @param esEdicion the esEdicion to set
     */
    public void setEsEdicion(boolean esEdicion) {
        this.esEdicion = esEdicion;
    }

    /**
     * @return the tipoComprobante
     */
    public TipoComprobanteCompra getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * @param tipoComprobante the tipoComprobante to set
     */
    public void setTipoComprobante(TipoComprobanteCompra tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    /**
     * @return the llamaSP
     */
    public LlamaSP getLlamaSP() {
        return llamaSP;
    }

    /**
     * @param llamaSP the llamaSP to set
     */
    public void setLlamaSP(LlamaSP llamaSP) {
        this.llamaSP = llamaSP;
    }

    /**
     * @return the codigoVenta
     */
    public Long getCodigoVenta() {
        return codigoVenta;
    }

    /**
     * @param codigoVenta the codigoVenta to set
     */
    public void setCodigoVenta(Long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    /**
     * @return the numeroFacturaEdicion
     */
    public String getNumeroFacturaEdicion() {
        return numeroFacturaEdicion;
    }

    /**
     * @param numeroFacturaEdicion the numeroFacturaEdicion to set
     */
    public void setNumeroFacturaEdicion(String numeroFacturaEdicion) {
        this.numeroFacturaEdicion = numeroFacturaEdicion;
    }

    /**
     * @return the fechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the codigoComprobante
     */
    public Long getCodigoComprobante() {
        return codigoComprobante;
    }

    /**
     * @param codigoComprobante the codigoComprobante to set
     */
    public void setCodigoComprobante(Long codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    /**
     * @return the numeroComprobante
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * @param numeroComprobante the numeroComprobante to set
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
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

}
