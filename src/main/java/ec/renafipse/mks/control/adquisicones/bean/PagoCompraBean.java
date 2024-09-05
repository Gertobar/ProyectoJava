/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.adquisicones.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.Compra;
import ec.renafipse.mks.modelo.adquisiciones.FormaPago;
import ec.renafipse.mks.modelo.adquisiciones.PagoCompra;
import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalle;
import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCheque;
import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCueAh;
import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleTransf;
import ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfip;
import ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfipPK;
import ec.renafipse.mks.modelo.adquisiciones.Proveedor;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfip;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfipPK;
import ec.renafipse.mks.modelo.adquisiciones.SustentoTributario;
import ec.renafipse.mks.modelo.adquisiciones.TipoComprobanteCompra;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.TalonarioChequeDetalle;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.contables.CuentaContableEntFin;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.adquisiciones.CompraFacade;
import ec.renafipse.mks.negocio.adquisiciones.FormaPagoFacade;
import ec.renafipse.mks.negocio.adquisiciones.PagoCompraDetalleChequeFacade;
import ec.renafipse.mks.negocio.adquisiciones.PagoCompraDetalleCueAhFacade;
import ec.renafipse.mks.negocio.adquisiciones.PagoCompraDetalleFacade;
import ec.renafipse.mks.negocio.adquisiciones.PagoCompraDetalleTransfFacade;
import ec.renafipse.mks.negocio.adquisiciones.PagoCompraFacade;
import ec.renafipse.mks.negocio.adquisiciones.ParametroCompraIfipFacade;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorCuentaEntFinFacade;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorIfipFacade;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioChequeDetalleFacade;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import ec.renafipse.mks.negocio.ifips.IfipCuentaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author renafipse1
 */
@ManagedBean(name = "pagoCompraBean")
@ViewScoped

public class PagoCompraBean extends AbstractController<PagoCompra> implements Serializable {

    @EJB
    private CompraFacade ejbFacadeCompras;

    @EJB
    private ProveedorIfipFacade ejbFacadeProveedorIfip;

    @EJB
    private FormaPagoFacade ejbFacadeFormaPago;

    @EJB
    private IfipCuentaEntidadFinancieraFacade ejbFacadeIfipCuentaEntidadFinanciera;

    @EJB
    private PagoCompraFacade ejbFacadePagoCompra;

    @EJB
    private PagoCompraDetalleFacade ejbFacadePagoCompraDetalle;

    @EJB
    private PagoCompraDetalleChequeFacade ejbFacadePagoCompraDetalleCheque;

    @EJB
    private PagoCompraDetalleCueAhFacade ejbFacadePagoCompraDetalleCueAh;

    @EJB
    private PagoCompraDetalleTransfFacade ejbFacadePagoCompraDetalleTrans;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private SocioFacade ejbFacadeSocio;
    @EJB
    private TalonarioChequeDetalleFacade ejbFacadeTalonarioChequeDetalle;

    @EJB
    private ProveedorCuentaEntFinFacade ejbFacadeProveedorCuentaEntFin;

    @EJB
    private PeriodoContableFacade ejbFacadePeriodoContable;

    @EJB
    private ParametroCompraIfipFacade ejbFacadeParametroCompraIfip;

    private LlamaSP llamaSP;

    //// VARIABLES 
    private int numColumna;
    private int opcionIngEdiPagoPro;
    private int numFilasActualCh;
    private int numFilasActualAcah;
    private int numFilasActualTr;
    private Long codigoPagoCompra;
    private Long numeroCheque;
    private Long codForPagDetEfec;
    private Long codForPagDetChe;
    private Long codForPagDetCAh;
    private Long codForPagDetTra;
    private Long codigoEvaluar;
    private Long criterio;
    private Long criterioRepresentante;
    private Long codigoComprobantePago;

    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaPagoProveedores;
    private Date fechaSistema;
    private Date fechaIngresoFactura;

    private String msg;
    private String mensajeDialogo;
    private String buscarPor;
    private String mensajeCriterio;
    private String numeroCuentaDesTra;
    private String nombreBeneficiario;
    private String nombreSocio;
    private String ciRucProveedor;
    private String comprasDireccionProveedor;
    private String numeroCuentaCre;
    private String serieParteUno;   //inicio de las var para el doc del proveedor
    private String serieParteDos;
    private String serieParteTres;
    private String serieParteUnoAux;
    private String serieParteDosAux;
    private String serieParteTresAux;
    private String serieFormada;
    private String estadoRiseProveedor;
    private String estadoRetieneIvaProveedor;
    private String nombreProevedorCompleto; /// para la pantalla principal
    private String nombreProveedorBusqueda; // para el dialogo
    private String numeroComprobantePago;  ///para obtnere el numero de comprobante
    private String totalPagoFacturaCadena;

    private Timestamp fechaPagoProveedoresTs;
    private Timestamp fechaSistemaTs;

    private boolean deshabilitaBuscarProveedor;
    private boolean banderaEstadoCheque;
    private boolean banderaEstadoTransfer;
    private boolean banderaEstadoEfectivo;
    private boolean banderaEstadoCredito;
    private boolean banderaCompraDetCheq;
    private boolean banderaFctCancelada;
    private boolean banderaEstPagoCompraN;
    private boolean banderaEstPagoCompraI;

    /////    
    private BigDecimal valorAbonoDetalle;
    private BigDecimal valorAbonoDetalleChe;
    private BigDecimal valorAbonoDetalleEfectivo;
    private BigDecimal valorAbonoDetalleCredito;
    private BigDecimal valorAbonoDetalleTrans;
    private BigDecimal descuentoCompra;
    private BigDecimal totalCompra;
    private BigDecimal totalAbono;
    private BigDecimal totalAbonadoDia;
    private BigDecimal totalSaldo;
    private BigDecimal totalPago;

    private Compra compraSel;
    private ProveedorIfip proveedorIfipSel;
    private SustentoTributario sustentoTributario;
    private ProveedorIfip provedorIfip;
    private TipoComprobanteCompra ubicaTipoComprobanteCompra;
    private FormaPago formaPagoSel;
    private PagoCompra pagoCompra;
    private PagoCompraDetalle pagoCompraDetalle;
    private PagoCompraDetalle pagoCompraDetalleSel;
    private CuentaContableEntFin cueConEntFinSel;
    private EntidadFinanciera entidadFinacieraSel;
    private EntidadFinanciera entidadFinacieraSelTraOrigen;
    private EntidadFinanciera entidadFinacieraSelTraDestino;
    private PagoCompraDetalleCueAh pagoCompraDetalleCueAh;
    private PagoCompraDetalleCheque pagoCompraDetalleCheque;
    private PagoCompraDetalleTransf pagoCompraDetalleTransf;
    private Cuenta cuenta;
    private IfipCuentaEntidadFinanciera numeroCuenta;
    private IfipCuentaEntidadFinanciera numeroCuentaOriTra;
    private RequestContext context;
    private GeneraReporte generaReporte;
    private Date fechaMaxima;
    private Date fechaMinima;
    private boolean contabilizaFechaPagoFactura;

    private List<Compra> itemsCompras;     //para listar segun el filtrado de la busqueda
    private List<PagoCompraDetalle> itemsPagoCompraDetalles;/////LAS VARAIBLES ON PARA EL PAGO A`PROVEEDORES
    private List<ProveedorIfip> itemsProveedores;
    private List<FormaPago> itemsFormaPagos;
    private List<EntidadFinanciera> itemsEntidadFinanciera;
    private List<EntidadFinanciera> itemsEntidadFinancieraTraOri;
    private List<EntidadFinanciera> itemsEntidadFinancieraTraDes;
    private List<CuentaContableEntFin> itemsCuentaContableEntFin;
    private List<PagoCompraDetalleCueAh> itemsPagoCompraDetalleCueAhs;
    private List<PagoCompraDetalleCheque> itemsPagoCompraDetalleCheques;
    private List<PagoCompraDetalleTransf> itemsPagoCompraDetalleTrans;
    private List<IfipCuentaEntidadFinanciera> itemsCuentasEntidadFinanciera;
    private List<IfipCuentaEntidadFinanciera> itemsCuentasEntidadFinancieraTraOrigen;
    private List<String> itemsCuentasEntidadFinancieraTraDestino;
    private List<Long> itemsPagoCDtCueAhsCod;
    private List<Long> itemsPagoCDtChequesCod;
    private List<Long> itemsPagoCDtTraCod;

    /**
     * Creates a new instance of PagoCompraBean
     */
    public PagoCompraBean() {
        super(PagoCompra.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacadePagoCompra);
        this.setBuscarPor("P");

        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();

        setFechaIngresoFactura(new Date());
        setFechaSistema(new Date());
        setBanderaFctCancelada(false);
        setBanderaEstPagoCompraN(false);
        setBanderaEstPagoCompraI(false);
        this.setSerieParteUno(null);
        this.setSerieParteDos(null);
        this.setSerieParteTres(null);
        this.setDescuentoCompra(new BigDecimal("0"));
        this.setTotalCompra(new BigDecimal("0"));
        this.setNumeroComprobantePago("");
        this.setCodigoComprobantePago(0L);
        fechaMinima = new Date();
        fechaMaxima = new Date();
        
        ParametroCompraIfip pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(4, ActivacionUsuario.getCodigoIfip()));        
        if (pci != null) {
            if (pci.getValor().equals("S")) {
                pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(2, ActivacionUsuario.getCodigoIfip()));
                if (pci != null) {
                   ////System.out.println("Dias Ingreso Factura " + pci.getValor());
                    this.fechaMinima = this.agregaDias(new Date(), Long.parseLong(pci.getValor().toString()) * -1);
                }
            }
        }

        setContabilizaFechaPagoFactura(false);
        pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(3, ActivacionUsuario.getCodigoIfip()));
        if (pci != null) {
           ////System.out.println("Contabiliza Fecha Factura " + pci.getValor());
            this.setContabilizaFechaPagoFactura(pci.getValor().equals("S"));
        }
    }

    /**
     * Al cambiar el criterio de Consulta Por Proveedor o fechas
     */
    public void cambiaCriterio() {

        this.setNombreProevedorCompleto(null);
        this.setCriterio(null);
        this.setDeshabilitaBuscarProveedor(true);

        if (this.buscarPor.equals("P")) {
            this.setDeshabilitaBuscarProveedor(false);

        }
    }

    public void leoCodigoRecibido() {
        this.mensajeCriterio = "" + proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
        this.criterio = proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
    }

    //   **********  PAGO   A PROVEEDORES
    public void obtieneComprasContabilizadas() {
        if (this.getBuscarPor().equals("P")) {
            if (this.getCriterio() != null) {                
                provedorIfip = this.ejbFacadeProveedorIfip.find(new ProveedorIfipPK((long) this.criterio, ActivacionUsuario.getCodigoIfip()));
                //this.setItemsProveedores(ejbFacadeProveedorIfip.getItemsNombresProveedorIfipEliminado(, ActivacionUsuario.getCodigoIfip(), 'N'));
                if (this.provedorIfip != null) {
                    /*if (this.getItemsProveedores().size() == 1) {
                        
                    ////System.out.println("itemsCompras " + itemsCompras);
                     }
                     if (this.getItemsProveedores().size() > 1) {
                     //System.err.println("el proveedor no puede tener el mismo codigo");
                     }*/
                    Proveedor proveedor = provedorIfip.getProveedor();
                    this.setNombreProevedorCompleto(proveedor.getPersona().getNombreCompleto());
                    obtienteComprasPorProveedorContabilizadas();
                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente"));
                    MuestraMensaje.addAdvertencia(getMsg());
                    this.setItemsCompras(null);
                }
            }

        } else {

            obtieneComprasContaPorFechas();
            if (getItemsCompras().isEmpty()) {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeComprasInexistentesFecha"));
                setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
                MuestraMensaje.addAdvertencia(getMsg());
                this.setItemsCompras(null);
            }
        }
     
    }

    public void seleccionaProveedorDetalle(ActionEvent avc) {
       ////System.out.println("proveedorIfipSel " + proveedorIfipSel);
        if (proveedorIfipSel != null) {
            this.criterio = proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
            this.criterioRepresentante = proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
            this.nombreProevedorCompleto = proveedorIfipSel.getProveedor().getPersona().getNombreCompleto();
            this.ciRucProveedor = proveedorIfipSel.getProveedor().getPersona().getIdentificacion();
            this.comprasDireccionProveedor = proveedorIfipSel.getProveedor().getPersona().getCorreoEletronico();
            this.estadoRiseProveedor = "" + proveedorIfipSel.getProveedor().getTieneRise();
            this.obtienteComprasPorProveedorContabilizadas();
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    public void buscaProveedores() {
        if (getNombreProveedorBusqueda().trim().isEmpty()) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsProveedores(null);
        } else if (this.getNombreProveedorBusqueda().trim().length() <= 6) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsProveedores(null);
        } else {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta"));
            this.setMensajeDialogo(null);
            this.setItemsProveedores(ejbFacadeProveedorIfip.getItemsNombresProveedorIfipEliminado(getNombreProveedorBusqueda().toUpperCase(), ActivacionUsuario.getCodigoIfip(), 'N'));
        }
    }

    public void obtienteComprasPorProveedorContabilizadas() {
        this.itemsCompras = ejbFacadeCompras.getItemsfindByComprasCodProvEstado(ActivacionUsuario.getCodigoIfip(), this.getCriterio(), 'N');
    }

    public void obtieneComprasContaPorFechas() {
        this.itemsCompras = ejbFacadeCompras.getItemsCompraByFechaIfipEsta(ActivacionUsuario.getCodigoIfip(), this.fechaInicio, this.getFechaFin(), 'N');
    }

    public void preparaPagoProveedores() {
        try {
        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.hide()");
        if (this.compraSel.getEstado() == 'N') {
            context.execute("formaPagosDialog.show()");
            this.totalPagoFacturaCadena = "0.00";
            //formaPagosDialog.show();
            this.setNombreProevedorCompleto(getCompraSel().getCodigoProveedor().getPersona().getNombreCompleto());
            this.setCiRucProveedor(getCompraSel().getCodigoProveedor().getPersona().getIdentificacion());
            this.setComprasDireccionProveedor(getCompraSel().getCodigoProveedor().getPersona().getCorreoEletronico());
            this.setEstadoRiseProveedor(String.valueOf(getCompraSel().getCodigoProveedor().getTieneRise()));
            this.setEstadoRetieneIvaProveedor(String.valueOf(getCompraSel().getCodigoProveedor().getRetieneIva()));

            this.setUbicaTipoComprobanteCompra(getCompraSel().getCodigoTipoComprobante());
            this.setSustentoTributario(getCompraSel().getCodigoSustento());

            String cadenaComprobante = "" + getCompraSel().getNumeroComprobante();

            this.setSerieParteUno(cadenaComprobante.substring(0, 3) + "");
            this.setSerieParteDos(cadenaComprobante.substring(3, 6) + "");
            this.setSerieParteTres(cadenaComprobante.substring(6, cadenaComprobante.length()) + "");

            this.setTotalCompra(getCompraSel().getTotal());
            this.setTotalAbono(getCompraSel().getAbono());
            this.setTotalSaldo(getCompraSel().getSaldo());
            this.setTotalAbonadoDia(new BigDecimal("0.00"));

            this.setValorAbonoDetalleChe(new BigDecimal("0.00"));
            this.setValorAbonoDetalleEfectivo(new BigDecimal("0.00"));
            this.setValorAbonoDetalleCredito(new BigDecimal("0.00"));
            this.setValorAbonoDetalleTrans(new BigDecimal("0.00"));
            
            this.setCodigoEvaluar(0L);           

            setItemsPagoCompraDetalles(new ArrayList<PagoCompraDetalle>());
            setItemsPagoCompraDetalleCueAhs(new ArrayList<PagoCompraDetalleCueAh>());
            setItemsPagoCompraDetalleCheques(new ArrayList<PagoCompraDetalleCheque>());
            setItemsPagoCompraDetalleTrans(new ArrayList<PagoCompraDetalleTransf>());
            setItemsFormaPagos(ejbFacadeFormaPago.findAll());
            setItemsEntidadFinanciera(ejbFacadeIfipCuentaEntidadFinanciera.getItemsIfipEntidadAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
            setItemsEntidadFinancieraTraOri(ejbFacadeIfipCuentaEntidadFinanciera.getItemsIfipEntidadAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
            setItemsEntidadFinancieraTraDes(ejbFacadeProveedorCuentaEntFin.getItemsCuentaEntfinbyCodigoProveedorEstado(getCompraSel().getCodigoProveedor().getCodigo(), 'N'));
            this.setValorAbonoDetalle(new BigDecimal("0.00"));
            this.setBanderaEstadoCheque(false);
            this.setBanderaEstadoCredito(false);
            this.setBanderaEstadoEfectivo(false);
            this.setBanderaCompraDetCheq(false);
            this.setEntidadFinacieraSel(new EntidadFinanciera());
            this.setFormaPagoSel(new FormaPago());
            this.setOpcionIngEdiPagoPro(1);
            this.setFechaPagoProveedores(new Date());
            this.setItemsPagoCDtTraCod(new ArrayList<Long>());
            this.setItemsPagoCDtChequesCod(new ArrayList<Long>());
            this.setItemsPagoCDtCueAhsCod(new ArrayList<Long>());
            this.setItemsPagoCDtTraCod(new ArrayList<Long>());
            cueConEntFinSel = null;
            entidadFinacieraSel = null;
            entidadFinacieraSelTraOrigen = null;
            entidadFinacieraSelTraDestino = null;
            pagoCompraDetalleCueAh = null;
            pagoCompraDetalleCheque = null;
            pagoCompraDetalleTransf = null;
            this.numeroCuentaDesTra = null;
            this.numeroCuentaOriTra = null;
            this.numeroCuenta = null;
            this.numeroCheque = null;
        }
        }catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    public void mensajeNoMuestraPantallaPago() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.hide()");
        context.execute("formaPagosDialog.hide()");
        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaIniciadaCancelada"));
        MuestraMensaje.addAdvertencia(getMsg());
    }

    public void preparaEdicionPagoProveedores() {

        if (banderaEstPagoCompraI) {
            this.setNombreProevedorCompleto(getCompraSel().getCodigoProveedor().getPersona().getNombreCompleto());
            this.setCiRucProveedor(getCompraSel().getCodigoProveedor().getPersona().getIdentificacion());
            this.setComprasDireccionProveedor(getCompraSel().getCodigoProveedor().getPersona().getCorreoEletronico());
            this.setEstadoRiseProveedor(String.valueOf(getCompraSel().getCodigoProveedor().getTieneRise()));
            this.setEstadoRetieneIvaProveedor(String.valueOf(getCompraSel().getCodigoProveedor().getRetieneIva()));

            this.setUbicaTipoComprobanteCompra(getCompraSel().getCodigoTipoComprobante());
            this.setSustentoTributario(getCompraSel().getCodigoSustento());

            String cadenaComprobante = "" + getCompraSel().getNumeroComprobante();

            this.setSerieParteUno(cadenaComprobante.substring(0, 3) + "");
            this.setSerieParteDos(cadenaComprobante.substring(3, 6) + "");
            this.setSerieParteTres(cadenaComprobante.substring(6, cadenaComprobante.length()) + "");

            this.setTotalCompra(getCompraSel().getTotal());
            this.setTotalAbono(new BigDecimal("0.00"));
            this.setTotalSaldo(new BigDecimal("0.00"));
            this.setTotalAbono(getCompraSel().getAbono());
            this.setTotalSaldo(getCompraSel().getSaldo());

            this.setValorAbonoDetalleChe(new BigDecimal("0.00"));
            this.setValorAbonoDetalleEfectivo(new BigDecimal("0.00"));
            this.setValorAbonoDetalleCredito(new BigDecimal("0.00"));
            this.setValorAbonoDetalleTrans(new BigDecimal("0.00"));
            this.setTotalAbonadoDia(new BigDecimal("0.00"));

            setPagoCompra(ejbFacadePagoCompra.getItemsfindByCodigoCompra(getCompraSel().getCodigo()).get(0));
            setItemsPagoCompraDetalles(ejbFacadePagoCompraDetalle.getItemsfindByCodigoPago(getPagoCompra().getCodigo()));

            for (int i = 0; i < getItemsPagoCompraDetalles().size(); i++) {
                Long codDetalle = getItemsPagoCompraDetalles().get(i).getCodigo();

               ////System.out.println("el codigo del detalle a buscar es ....." + codDetalle);
                if (getItemsPagoCompraDetalles().get(i).getFormaPago().getCodigo() == 1L) {
                    setItemsPagoCompraDetalleCheques(ejbFacadePagoCompraDetalleCheque.findByCodigoPagoCompraDetalle(codDetalle));
                   ////System.out.println("EXISTEN PAGOS HECHOS CON CHEQUE");
                }
                if (getItemsPagoCompraDetalles().get(i).getFormaPago().getCodigo() == 3L) {
                    setItemsPagoCompraDetalleCueAhs(ejbFacadePagoCompraDetalleCueAh.findByCodigoPagoCompraDetalle(codDetalle));
                   ////System.out.println("EXISTEN PAGOS HECHOS ACREDITACION A CUENTA DE AHORRO");
                }
                if (getItemsPagoCompraDetalles().get(i).getFormaPago().getCodigo() == 4L) {
                    setItemsPagoCompraDetalleTrans(ejbFacadePagoCompraDetalleTrans.findByCodigoPagoCompraDetalle(codDetalle));
                   ////System.out.println("EXISTEN PAGOS HECHOS EN TRANSFERENCIA");
                }
            }

            setItemsFormaPagos(ejbFacadeFormaPago.findAll());
            setItemsEntidadFinanciera(ejbFacadeIfipCuentaEntidadFinanciera.getItemsIfipEntidadAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
            setItemsEntidadFinancieraTraOri(ejbFacadeIfipCuentaEntidadFinanciera.getItemsIfipEntidadAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
            setItemsEntidadFinancieraTraDes(ejbFacadeProveedorCuentaEntFin.getItemsCuentaEntfinbyCodigoProveedorEstado(getCompraSel().getCodigoProveedor().getCodigo(), 'N'));
            this.setValorAbonoDetalle(new BigDecimal("0.00"));
            this.setBanderaEstadoCheque(false);
            this.setBanderaEstadoCredito(false);
            this.setBanderaEstadoEfectivo(false);
            this.setBanderaCompraDetCheq(false);
            this.setEntidadFinacieraSel(new EntidadFinanciera());
            this.setFormaPagoSel(new FormaPago());
            this.setOpcionIngEdiPagoPro(2);
            this.setFechaPagoProveedores(new Date());
            this.setCodigoEvaluar(ejbFacadePagoCompraDetalle.getMaximoPagoCompraDetalles().get(0) + 1L);
            ////
            this.setItemsPagoCompraDetalleCheques(new ArrayList<PagoCompraDetalleCheque>());
            this.setItemsPagoCompraDetalleCueAhs(new ArrayList<PagoCompraDetalleCueAh>());
            this.setItemsPagoCompraDetalleTrans(new ArrayList<PagoCompraDetalleTransf>());

            this.setItemsPagoCDtTraCod(new ArrayList<Long>());
            this.setItemsPagoCDtChequesCod(new ArrayList<Long>());
            this.setItemsPagoCDtCueAhsCod(new ArrayList<Long>());
            this.setItemsPagoCDtTraCod(new ArrayList<Long>());

            if (!itemsPagoCompraDetalleCheques.isEmpty()) {
                for (int i = 0; i < itemsPagoCompraDetalleCheques.size(); i++) {
                    this.itemsPagoCDtChequesCod.add(itemsPagoCompraDetalleCheques.get(i).getCodigoPagoCompraDetalle());
                }
            }

            if (!itemsPagoCompraDetalleCueAhs.isEmpty()) {
                for (int i = 0; i < itemsPagoCompraDetalleCueAhs.size(); i++) {
                    this.itemsPagoCDtCueAhsCod.add(itemsPagoCompraDetalleCueAhs.get(i).getCodigoPagoCompraDetalle());
                }
            }

            if (!itemsPagoCompraDetalleTrans.isEmpty()) {
                for (int i = 0; i < itemsPagoCompraDetalleTrans.size(); i++) {
                    this.itemsPagoCDtTraCod.add(itemsPagoCompraDetalleTrans.get(i).getCodigoPagoCompraDetalle());
                }
            }

            this.numFilasActualAcah = itemsPagoCompraDetalleCueAhs.size();
            this.numFilasActualCh = itemsPagoCompraDetalleCheques.size();
            this.numFilasActualTr = itemsPagoCompraDetalleTrans.size();

        } else {
            mensajeNoMuestraPantallaEdiPago();
        }

    }

    public void mensajeNoMuestraPantallaEdiPago() {

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.hide()");
        context.execute("formaPagosDialog.hide()");
        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaInicioCancelada"));
        MuestraMensaje.addAdvertencia(getMsg());
    }

    public void cambioCriterioBusqFormPago() {

        setNumeroCheque(null);
        setNumeroCuenta(null);

        try {
            if (getFormaPagoSel().getCodigo() > 0) {
                if (getFormaPagoSel().getCodigo() == 1) {

                    this.setBanderaEstadoCheque(true);
                    this.setBanderaEstadoEfectivo(false);
                    this.setBanderaEstadoTransfer(false);
                    this.setBanderaEstadoCredito(false);
                    this.setNombreBeneficiario(getCompraSel().getCodigoProveedor().getPersona().getNombreCompleto());
                   ////System.out.println("ingreso a la pantalla estado de cheque");
                } else if (getFormaPagoSel().getCodigo() == 2) {

                    this.setBanderaEstadoCheque(false);
                    this.setBanderaEstadoTransfer(false);
                    this.setBanderaEstadoEfectivo(true);
                    this.setBanderaEstadoCredito(false);

                   ////System.out.println("ingreso a la pantalla estado de efectivo ");

                } else if (getFormaPagoSel().getCodigo() == 3) {

                    this.setBanderaEstadoCheque(false);
                    this.setBanderaEstadoTransfer(false);
                    this.setBanderaEstadoEfectivo(false);
                    this.setBanderaEstadoCredito(true);
                    this.setNombreSocio(getCompraSel().getCodigoProveedor().getPersona().getNombreCompleto());
                   ////System.out.println("ingreso a la pantalla estado de credito");
                } else if (getFormaPagoSel().getCodigo() == 4) {

                    this.setBanderaEstadoCheque(false);
                    this.setBanderaEstadoTransfer(true);
                    this.setBanderaEstadoEfectivo(false);
                    this.setBanderaEstadoCredito(false);

                   ////System.out.println("ingreso a la pantalla estado transferencia");
                }
            }
        } catch (Exception e) {
        }
    }

    public void cargoCuentasExitentes() {
        if (getEntidadFinacieraSel() != null) {
            setItemsCuentasEntidadFinanciera(ejbFacadeIfipCuentaEntidadFinanciera.getItemsIfipEntidadAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N', getEntidadFinacieraSel().getCodigo()));
        } else {
            setItemsCuentasEntidadFinanciera(new ArrayList<IfipCuentaEntidadFinanciera>());
        }
    }

    public void cargoCuentasExitentesTrOrigen() {
        if (getEntidadFinacieraSelTraOrigen() != null) {
            setItemsCuentasEntidadFinancieraTraOrigen(ejbFacadeIfipCuentaEntidadFinanciera.getItemsIfipEntidadAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'N', getEntidadFinacieraSelTraOrigen().getCodigo()));
        } else {
            setItemsCuentasEntidadFinancieraTraOrigen(new ArrayList<IfipCuentaEntidadFinanciera>());
        }
    }

    public void cargoCuentasExitentesTrDestino() {
        if (getEntidadFinacieraSelTraDestino() != null) {
            setItemsCuentasEntidadFinancieraTraDestino(ejbFacadeProveedorCuentaEntFin.getItemsCuentaEntfinfindByCodProvEliminaCodEntida(getCompraSel().getCodigoProveedor().getCodigo(), getEntidadFinacieraSelTraDestino().getCodigo(), 'N'));
        } else {
            setItemsCuentasEntidadFinancieraTraDestino(new ArrayList<String>());
        }
    }

    public void busquedaObtengoDatosSocio() {

        String identificacionCI = getCompraSel().getCodigoProveedor().getPersona().getIdentificacion();
        if (!ejbFacadeSocio.getItemsSociofindByIdentificacion(identificacionCI).isEmpty()) {
            if (!ejbFacadeSocio.getItemsSociofindByIdIfip(identificacionCI, ActivacionUsuario.getCodigoIfip()).isEmpty()) {

                if (!ejbFacadeSocio.getItemsSociofindByIdIfipAgencia(identificacionCI, ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia()).isEmpty()) {

                    if (!ejbFacadeSocio.getItemsSociofindByIdIfipAgenciaIndicaActivo(identificacionCI, ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'S').isEmpty()) {

                        Socio socio = ejbFacadeSocio.getItemsSociofindByIdIfipAgenciaIndicaActivo(identificacionCI, ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'S').get(0);
                        // :codigoSocio AND  :codigoTipoProducto AND  :codigoEstado AND  :codigoMoneda AND  :numero AND  :codigoIfip AND   :esSocioBeneficiario")
                       ////System.out.println("El codigo del soscio es ....." + socio.getSocioPK().getCodigo());
                        if (!ejbFacadeCuenta.getItemsfindByNumCuentaIfipSocioBenef(socio.getSocioPK().getCodigo(), 2L, 1L, 1L, this.numeroCuentaCre, ActivacionUsuario.getCodigoIfip(), 'S').isEmpty()) {

                            setCuenta(ejbFacadeCuenta.getItemsfindByNumCuentaIfipSocioBenef(socio.getSocioPK().getCodigo(), 2L, 1L, 1L, this.getNumeroCuentaCre(), ActivacionUsuario.getCodigoIfip(), 'S').get(0));

                        } else {
                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSocioNumeroCuentaImp"));
                            MuestraMensaje.addAdvertencia(getMsg());
                        }

                    } else {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSocioEstadoInactivo"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    }

                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSocioNoExisteIfipAgencia"));
                    MuestraMensaje.addAdvertencia(getMsg());
                }

            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSocioNoExisteIfip"));
                MuestraMensaje.addAdvertencia(getMsg());
            }

        } else {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
            MuestraMensaje.addAdvertencia(getMsg());
        }
    }

    public boolean validaNumeroCheque() {
        this.setMsg(null);
        setBanderaCompraDetCheq(false);
       ////System.out.println("validaNumeroCheque  1");
        if (getEntidadFinacieraSel() != null) {

           ////System.out.println("validaNumeroCheque  3  CODIGO ENTIDAD FINANCIERA " + this.entidadFinacieraSel.getCodigo() + "  NUMERO CHEQ  " + numeroCheque);
            if (!ejbFacadeTalonarioChequeDetalle.itemsFindByNumChequEntFin(this.entidadFinacieraSel.getCodigo(), numeroCheque).isEmpty()) {

               ////System.out.println("validaNumeroCheque  4");

                TalonarioChequeDetalle talonarioChequeDetalle = this.ejbFacadeTalonarioChequeDetalle.itemsFindByNumChequEntFinaIfipAgencia(this.getEntidadFinacieraSel().getCodigo(), getNumeroCheque(), ActivacionUsuario.getCodigoIfipAgencia()).get(0);

               ////System.out.println("validaNumeroCheque  5");
                if (talonarioChequeDetalle.getEstado() == 'P') {
                    setBanderaCompraDetCheq(true);

                } else {

                   ////System.out.println("validaNumeroCheque  5.1");
                    PagoCompraDetalleCheque pagDetChe = ejbFacadePagoCompraDetalleCheque.findByCodEnTtFinNumCheNumCue(getEntidadFinacieraSel().getCodigo(), getNumeroCheque()).get(0);

                    if (talonarioChequeDetalle.getEstado() == 'G') {

                       ////System.out.println("validaNumeroCheque  5.1.1");
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroChequeGirado") + " CON LA CUENTA " + pagDetChe.getCodigoCuenta());

                        MuestraMensaje.addAdvertencia(getMsg());
                        setBanderaCompraDetCheq(false);

                    } else if (talonarioChequeDetalle.getEstado() == 'A') {
                       ////System.out.println("validaNumeroCheque  5.1.2");
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroAnulado") + " CON LA CUENTA " + pagDetChe.getCodigoCuenta());
                        MuestraMensaje.addAdvertencia(getMsg());
                        setBanderaCompraDetCheq(false);
                    }
                }

            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroChequeNoExiste"));
                MuestraMensaje.addAdvertencia(getMsg());
                setBanderaCompraDetCheq(false);
            }

        } else {

            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneEntidadChe"));
            MuestraMensaje.addAdvertencia(getMsg());
            setBanderaCompraDetCheq(false);
           ////System.out.println("Seleccion  la Entidad Financiera  ");

        }

        return isBanderaCompraDetCheq();
    }

    public void agregoDatosCabeceraFormaPago() {

        //DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        //String fechaIngresAux = df1.format(getFechaPagoProveedores());

        /*try {
            setFechaPagoProveedores(formatter.parse(fechaIngresAux));

        } catch (ParseException ex) {
            Logger.getLogger(FacturaBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }*/

        setPagoCompra(new PagoCompra());
        getPagoCompra().setCompra(getCompraSel());
        /*getPagoCompra().setFechaPago(getFechaPagoProveedores());
         getPagoCompra().setFechaSistema(getFechaSistema());
         getPagoCompra().setPagadoPor(ActivacionUsuario.getCodigoUsuario());
         getPagoCompra().setAbono(getTotalAbono());
         getPagoCompra().setSaldo(getTotalSaldo());
         if (getPagoCompra().getSaldo().compareTo(BigDecimal.ZERO) == 0) {
         
             
         getPagoCompra().setCompraCancelada('S');
         this.compraSel.setEstado('C');
         } else {
         getPagoCompra().setCompraCancelada('N');
         }*/
    }

    public void agregoDatosEdicionCabeceraFormaPago() {

        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String fechaIngresAux = df1.format(getFechaPagoProveedores());

        try {
            setFechaPagoProveedores(formatter.parse(fechaIngresAux));

        } catch (ParseException ex) {
            Logger.getLogger(FacturaBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        getPagoCompra().setFechaPago(getFechaPagoProveedores());
        getPagoCompra().setFechaSistema(getFechaSistema());
        getPagoCompra().setPagadoPor(ActivacionUsuario.getCodigoUsuario());
        getPagoCompra().setAbono(getTotalAbono());
        getPagoCompra().setSaldo(getTotalSaldo());
        if (getPagoCompra().getSaldo().compareTo(BigDecimal.ZERO) == 0) {
            getPagoCompra().setCompraCancelada('S');
            this.compraSel.setEstado('C');
        } else {
            getPagoCompra().setCompraCancelada('N');
        }
    }

    public void agregoDetalleFormaPago() {

        this.msg = null;
       ////System.out.println("EL  VALOR A INGRESAR  " + getValorAbonoDetalle());

        try {
            if (getFormaPagoSel().getCodigo() > 0) {
                if (getFormaPagoSel().getCodigo() == 1) {
                    setValorAbonoDetalle(getValorAbonoDetalleChe());
                   ////System.out.println("ingreso a la pantalla estado de cheque");
                } else if (getFormaPagoSel().getCodigo() == 2) {

                    setValorAbonoDetalle(getValorAbonoDetalleEfectivo());
                   ////System.out.println("ingreso a la pantalla estado de efectivo ");

                } else if (getFormaPagoSel().getCodigo() == 3) {

                    setValorAbonoDetalle(getValorAbonoDetalleCredito());
                   ////System.out.println("ingreso a la pantalla estado de credito");
                } else if (getFormaPagoSel().getCodigo() == 4) {

                    setValorAbonoDetalle(getValorAbonoDetalleTrans());
                   ////System.out.println("ingreso a la pantalla estado de pago de transferencia");
                }
            }

       /*//System.out.println("getValorAbonoDetalleChe() " + getValorAbonoDetalleChe()
                    + " getValorAbonoDetalleEfectivo " + getValorAbonoDetalleEfectivo()
                    + " getValorAbonoDetalleCredito () " + getValorAbonoDetalleCredito()
                    + " getValorAbonoDetalleTrans " + getValorAbonoDetalleTrans()
                    + " getValorAbonoDetalle " + getValorAbonoDetalle()
                    + " totalAbono " + this.totalAbono);*/

            if (!validaFormaPagoDetalle()) {
                //MensajeFacturaCampoCantidadZero
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FormaPagoYaIngresada"));
                MuestraMensaje.addAdvertencia(getMsg());
               ////System.out.println("cantidad mayo a cero");
                return;
            }

            if (this.valorAbonoDetalle.doubleValue() <= 0) {
                //MensajeFacturaCampoCantidadZero
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCampoCantidadZero"));
                MuestraMensaje.addAdvertencia(getMsg());
               ////System.out.println("cantidad mayo a cero");
                return;
            }

            if (this.totalAbono.add(this.valorAbonoDetalle).doubleValue() > this.totalCompra.doubleValue()) {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeValorAbonoIngresar"));
                MuestraMensaje.addAdvertencia(getMsg());
                return;
            }

           ////System.out.println("PASO PRIMER FILTRO");
            if (getFormaPagoSel().getCodigo() > 0L) {

                if (isBanderaEstadoEfectivo()) {
                   ////System.out.println("caso deefectvo 1");

                    setPagoCompraDetalle(new PagoCompraDetalle());
                    getPagoCompraDetalle().setCodigo(getCodigoEvaluar());
                    getPagoCompraDetalle().setFormaPago(getFormaPagoSel());
                    getPagoCompraDetalle().setValor(getValorAbonoDetalle());
                   ////System.out.println("caso de efectvo 1");

                    this.getItemsPagoCompraDetalles().add(getPagoCompraDetalle());
                    this.setCodigoEvaluar((Long) this.getCodigoEvaluar() + 1L);
                   ////System.out.println("OK Ingreso nuevos detalles a la tabla pagocompradetalle" + " valor actual del abono " + getTotalAbono());
                    this.setValorAbonoDetalle(null);

                }

                if (isBanderaEstadoCredito()) {

                    if (getNumeroCuentaCre() != null && getNumeroCuentaCre().length() > 0
                            && getNombreSocio() != null
                            && getNombreSocio().length() > 0) {

                        ///Ingreso en la tabla forma pago
                       ////System.out.println("caso de creditp 1");

                        setPagoCompraDetalle(new PagoCompraDetalle());
                        getPagoCompraDetalle().setCodigo(getCodigoEvaluar());
                        getPagoCompraDetalle().setFormaPago(getFormaPagoSel());
                        getPagoCompraDetalle().setValor(getValorAbonoDetalle());

                        setPagoCompraDetalleCueAh(new PagoCompraDetalleCueAh());
                        pagoCompraDetalleCueAh.setCodigoPagoCompraDetalle(getCodigoEvaluar());
                        getPagoCompraDetalleCueAh().setCodigoCuenta(getCuenta().getCodigo());
                        getPagoCompraDetalleCueAh().setValor(getValorAbonoDetalle());
                       ////System.out.println("caso de creditp");

                        this.getItemsPagoCompraDetalles().add(getPagoCompraDetalle());
                        this.getItemsPagoCompraDetalleCueAhs().add(getPagoCompraDetalleCueAh());
                        this.setCodigoEvaluar((Long) this.getCodigoEvaluar() + 1L);
                       ////System.out.println("OK  valor actual del abono " + getTotalAbono());
                        this.setValorAbonoDetalle(null);

                    } else {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCamposVaciosDetCab"));
                        //  MuestraMensaje.addAdvertencia(getMsg());
                       ////System.out.println("campos vacios ");
                    }

                }

                if (isBanderaEstadoCheque()) {

                    if (getEntidadFinacieraSel() != null) {

                        if (getEntidadFinacieraSel().getCodigo() > 0L
                                && getNumeroCuenta() != null && getNumeroCuenta() != null
                                && getNombreBeneficiario() != null
                                && getNombreBeneficiario().length() > 0) {

                            if (isBanderaCompraDetCheq()) {
                                if (!validaNumeroChequePorIngresar(numeroCheque)) {

                                   ////System.out.println("caso de cheque");

                                    setPagoCompraDetalle(new PagoCompraDetalle());
                                    getPagoCompraDetalle().setCodigo(getCodigoEvaluar());
                                    getPagoCompraDetalle().setFormaPago(getFormaPagoSel());
                                    getPagoCompraDetalle().setValor(getValorAbonoDetalle());
                                    //CODIGO_ENTIDAD_FINANCIERA, CODIGO_TIPO_CUENTA, NUMERO_CUENTA
                                    Long codifipCueEntFin = getNumeroCuenta().getCodigo();
                                    setPagoCompraDetalleCheque(new PagoCompraDetalleCheque());
                                    pagoCompraDetalleCheque.setCodigoPagoCompraDetalle(getCodigoEvaluar());
                                    getPagoCompraDetalleCheque().setCodigoCuenta(codifipCueEntFin);
                                    getPagoCompraDetalleCheque().setValor(getValorAbonoDetalle());
                                    getPagoCompraDetalleCheque().setBeneficiario(this.getNombreBeneficiario());
                                    getPagoCompraDetalleCheque().setNumeroCheque(this.getNumeroCheque());

                                    this.getItemsPagoCompraDetalles().add(getPagoCompraDetalle());
                                    this.getItemsPagoCompraDetalleCheques().add(getPagoCompraDetalleCheque());
                                    this.setCodigoEvaluar((Long) this.getCodigoEvaluar() + 1L);

                                   ////System.out.println("codifipCueEntFin CODIGO DE IFIP CUENTA ENTIDAD FUANANCIERA·" + codifipCueEntFin);
                                   ////System.out.println("OK Ingreso nuevos " + " valor actual del abono " + getTotalAbono());
                                    this.setValorAbonoDetalle(null);

                                } else {

                                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroChequeItemsE"));
                                    //  MuestraMensaje.addAdvertencia(getMsg());
                                }
                            } else {
                                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajePagoConChequeYaExiste"));
                                //MuestraMensaje.addAdvertencia(getMsg());
                            }

                        } else {
                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCamposVaciosDetCab"));
                            //MuestraMensaje.addAdvertencia(getMsg());
                           ////System.out.println("campos vacios ");
                        }
                    } else {
                        ///SeleccionEntidadFinanciera
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionEntidadFinanciera"));
                        //MuestraMensaje.addAdvertencia(getMsg());
                       ////System.out.println("Seleccion Entidad Financiera");

                    }
                }

                if (isBanderaEstadoTransfer()) {
                    if (getEntidadFinacieraSelTraOrigen() != null && getEntidadFinacieraSelTraDestino() != null && getNumeroCuentaOriTra() != null && getNumeroCuentaDesTra().length() > 0) {

                       ////System.out.println("caso de transferencia");

                        setPagoCompraDetalle(new PagoCompraDetalle());
                        getPagoCompraDetalle().setCodigo(getCodigoEvaluar());
                        getPagoCompraDetalle().setFormaPago(getFormaPagoSel());
                        getPagoCompraDetalle().setValor(getValorAbonoDetalle());

                        Long codifipCueEntFin = getNumeroCuentaOriTra().getCodigo();
                        setPagoCompraDetalleTransf(new PagoCompraDetalleTransf());
                        pagoCompraDetalleTransf.setCodigoPagoCompraDetalle(getCodigoEvaluar());
                        getPagoCompraDetalleTransf().setCodigoCuentaEntFin(codifipCueEntFin);//numeroCuentaDesTra
                        getPagoCompraDetalleTransf().setValor(getValorAbonoDetalle());
                        getPagoCompraDetalleTransf().setNumeroCuenta(numeroCuentaDesTra);
                        getPagoCompraDetalleTransf().setCodigoEntidadFinanciera(entidadFinacieraSelTraDestino.getCodigo());

                        this.getItemsPagoCompraDetalles().add(getPagoCompraDetalle());
                        this.getItemsPagoCompraDetalleTrans().add(getPagoCompraDetalleTransf());
                        this.setCodigoEvaluar((Long) this.getCodigoEvaluar() + 1L);
                       ////System.out.println("OK Ingreso nuevos detalles a la tabla de la retencion" + " valor actual del abono " + getTotalAbono());
                        this.setValorAbonoDetalle(null);

                    } else if (getEntidadFinacieraSelTraOrigen() == null) {
                        ///SeleccionEntidadFinanciera
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneEntidadOrigen"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    } else if (getEntidadFinacieraSelTraDestino() == null) {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneEntidadDestino"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    } else if (getNumeroCuentaOriTra() != null) {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneEntidadCuentaOrigen"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    } else if (getNumeroCuentaDesTra().length() > 0) {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneEntidadCuentaDestino"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    }
                }

                // Calculaando Totales de la Factura
                calculaTotalPagoFactura();

                if (this.msg != null) {
                    MuestraMensaje.addError(msg);
                }

            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCamposVaciosDetCab"));
                MuestraMensaje.addAdvertencia(getMsg());
               ////System.out.println("vacio");
            }
        } catch (Exception e) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            e.printStackTrace();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCompraBean", "agregaDetallePago", CapturaError.getErrorException(e)});
        }
        //} else {
    }

    public boolean validaFormaPagoDetalle() {
        for (PagoCompraDetalle pcd : itemsPagoCompraDetalles) {
            if (pcd.getFormaPago().getCodigo() == this.formaPagoSel.getCodigo()) {
                return false;
            }
        }

        return true;
    }

    public boolean validaNumeroChequePorIngresar(Long numeroChequeI) {

        boolean respuesta = false;
        if (this.itemsPagoCompraDetalleCheques.isEmpty()) {
            respuesta = false;
        } else {
            for (int i = 0; i < this.itemsPagoCompraDetalleCheques.size(); i++) {
                respuesta = this.itemsPagoCompraDetalleCheques.get(i).getNumeroCheque() == Long.parseLong("" + numeroChequeI);
            }
        }

        return respuesta;
    }

    public void calculaTotalPagoFactura() {

        DecimalFormat formatoValores = new DecimalFormat("###,###,##0.00");
        totalPago = new BigDecimal("0.00");

        for (PagoCompraDetalle pcd : this.itemsPagoCompraDetalles) {
            totalPago = totalPago.add(pcd.getValor());
        }

        this.totalPagoFacturaCadena = formatoValores.format(totalPago);

        this.totalAbono = compraSel.getAbono().add(totalPago);
        this.totalSaldo = this.compraSel.getTotal().subtract(totalAbono);
    }

    public void obtengoPagoCompra(SelectEvent event) {

        if (getPagoCompraDetalleSel() != null) {

            setCodigoEvaluar(getPagoCompraDetalleSel().getCodigo());
           ////System.out.println("codigo  reccuperado " + getCodigoEvaluar());
            ////System.out.println("fila   reccuperada " + numeroFilaActual);
            DataTable objDataTable = (DataTable) event.getSource();
           ////System.out.println("Selected checkbox row index: " + objDataTable.getRowIndex());
        }
    }

    public void eliminaDetalleFormaPago() {

        if (this.getItemsPagoCompraDetalles().size() > 0) {
            if (getOpcionIngEdiPagoPro() == 1) {

                if (itemsPagoCompraDetalleCheques.size() > 0) {
                    for (int i = 0; i < itemsPagoCompraDetalleCheques.size(); i++) {
                        if (itemsPagoCompraDetalleCheques.get(i).getCodigoPagoCompraDetalle() == pagoCompraDetalleSel.getCodigo()) {
                            PagoCompraDetalleCheque pcdChe = itemsPagoCompraDetalleCheques.get(i);
                            this.itemsPagoCompraDetalleCheques.remove(pcdChe);

                        }
                    }
                }

                if (itemsPagoCompraDetalleCueAhs.size() > 0) {
                    for (int i = 0; i < itemsPagoCompraDetalleCueAhs.size(); i++) {
                        if (itemsPagoCompraDetalleCueAhs.get(i).getCodigoPagoCompraDetalle() == pagoCompraDetalleSel.getCodigo()) {
                            PagoCompraDetalleCueAh pcdCuAh = itemsPagoCompraDetalleCueAhs.get(i);
                            this.itemsPagoCompraDetalleCueAhs.remove(pcdCuAh);

                        }
                    }
                }

                if (itemsPagoCompraDetalleTrans.size() > 0) {
                    for (int i = 0; i < itemsPagoCompraDetalleTrans.size(); i++) {
                        if (itemsPagoCompraDetalleTrans.get(i).getCodigoPagoCompraDetalle() == pagoCompraDetalleSel.getCodigo()) {
                            PagoCompraDetalleTransf pcdTra = itemsPagoCompraDetalleTrans.get(i);
                            this.itemsPagoCompraDetalleTrans.remove(pcdTra);

                        }
                    }
                }
                this.getItemsPagoCompraDetalles().remove(this.getPagoCompraDetalleSel());

                // Calculando Total de Pago y Saldos de lA FActura
                calculaTotalPagoFactura();

            } else if (getOpcionIngEdiPagoPro() == 2) {

               ////System.out.println("codigo  " + getPagoCompraDetalleSel().getCodigo());

                if (ejbFacadePagoCompraDetalle.getItemsfindByCodigo(getPagoCompraDetalleSel().getCodigo()).isEmpty()) {

                    if (itemsPagoCompraDetalleCheques.size() > 0) {
                        for (int i = 0; i < itemsPagoCompraDetalleCheques.size(); i++) {
                            if (itemsPagoCompraDetalleCheques.get(i).getCodigoPagoCompraDetalle() == pagoCompraDetalleSel.getCodigo()) {
                                PagoCompraDetalleCheque pcdChe = itemsPagoCompraDetalleCheques.get(i);
                                this.itemsPagoCompraDetalleCheques.remove(pcdChe);

                            }
                        }
                    }

                    if (itemsPagoCompraDetalleCueAhs.size() > 0) {
                        for (int i = 0; i < itemsPagoCompraDetalleCueAhs.size(); i++) {
                            if (itemsPagoCompraDetalleCueAhs.get(i).getCodigoPagoCompraDetalle() == pagoCompraDetalleSel.getCodigo()) {
                                PagoCompraDetalleCueAh pcdCuAh = itemsPagoCompraDetalleCueAhs.get(i);
                                this.itemsPagoCompraDetalleCueAhs.remove(pcdCuAh);
                            }
                        }
                    }

                    if (itemsPagoCompraDetalleTrans.size() > 0) {
                        for (int i = 0; i < itemsPagoCompraDetalleTrans.size(); i++) {
                            if (itemsPagoCompraDetalleTrans.get(i).getCodigoPagoCompraDetalle() == pagoCompraDetalleSel.getCodigo()) {
                                PagoCompraDetalleTransf pcdTra = itemsPagoCompraDetalleTrans.get(i);
                                this.itemsPagoCompraDetalleTrans.remove(pcdTra);

                            }
                        }
                    }

                    this.getItemsPagoCompraDetalles().remove(this.getPagoCompraDetalleSel());

                   ////System.out.println("eliminado");

                } else {
                    String mensaje = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNoEliminarDetalle");
                    MuestraMensaje.addAdvertencia(mensaje);
                   ////System.out.println(" NO SE PUEDE ELIMINAR ESTE REGISTRO");
                   ////System.out.println("codigo  reccuperado " + getCodigoEvaluar());

                }

            }
        }
    }

    public void guardoFormaPago(ActionEvent event) {
        try {
            context = RequestContext.getCurrentInstance();

            if (this.totalPago.doubleValue() > 0) {

                if (getOpcionIngEdiPagoPro() == 1) {

                    try {
                        context.execute("procesandoDlg.show()");
                        // Cargando la conexion de BD
                        llamaSP.cargaConexion();

                        // Indicando que no cierre la conexion de la base de datos
                        llamaSP.setCerrarConexion(false);

                        // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                        llamaSP.autoCommit(false);

                        agregoDatosCabeceraFormaPago();
                       ////System.out.println("Guardo Cabecera");
                        if (this.guardoPagoCompraCabecera()) {
                           ////System.out.println("Guardo Detalle");
                            if (this.guardaPagoCompraDetalle()) {
                               ////System.out.println("Estado tablas");
                                if (this.guardaEstadoTablaCompras()) {
                                   ////System.out.println("Imtes");
                                    if (getItemsPagoCompraDetalleCheques().size() > 0) {
                                        if (guardaPagoCompraCheque()) {

                                        }
                                    }
                                    if (getItemsPagoCompraDetalleCueAhs().size() > 0) {
                                        if (guardaPagoCompraAcreCueAhorro()) {
                                        }
                                    }

                                    if (getItemsPagoCompraDetalleTrans().size() > 0) {
                                        if (guardaPagoCompraTransferencia()) {
                                        }
                                    }
                                    if (llamoProcedimientoComprobantePagoCompra()) {
                                    }
                                }
                            }
                        }
                        // Verificando que la ejecucion del proceso ha sido correcta
                        if (llamaSP.isEjecucionCorrecta()) {
                            // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                            llamaSP.commit();
                            llamaSP.cerrarConexion();
                            llamaSP.setConexionBD(null);
                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajePagoRealizado"));
                            MuestraMensaje.addInformacion(getMsg());
                            context.execute("procesandoDlg.hide()");
                            obtieneComprasContabilizadas();
                            this.compraSel = null;

                        } else {
                            context.execute("procesandoDlg.hide()");
                            llamaSP.rollback();
                            llamaSP.cerrarConexion();
                            llamaSP.setConexionBD(null);
                        }

                    } catch (Exception ex) {
                       ////System.out.println("Error ");
                        ex.printStackTrace();
                        context.execute("procesandoDlg.hide()");
                        // Muestra el Mensaje del Error en la Capa
                        MuestraMensaje.addErrorCapaControl();
                        // Registra el error en el log del servidor
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                new Object[]{"facturaBean", "guardaDatosPagoCompra", CapturaError.getErrorException(ex)});
                    }

                } else if (getOpcionIngEdiPagoPro() == 2) {
                    RequestContext context = RequestContext.getCurrentInstance();
                    try {
                        context.execute("procesandoDlg.show()");
                        // Cargando la conexion de BD
                        llamaSP.cargaConexion();

                        // Indicando que no cierre la conexion de la base de datos
                        llamaSP.setCerrarConexion(false);

                        // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                        llamaSP.autoCommit(false);

                        agregoDatosEdicionCabeceraFormaPago();

                        if (this.edicionPagoCompraCabecera()) {

                            //////////// GUARDO LOS DETALLES DEL PAGO DE LAS COMPRAS
                            //////////////////
                            if (this.guardaEdicionPagoCompraDetalle()) {

                                if (this.guardaEstadoTablaCompras()) {

                                    if (getItemsPagoCompraDetalleCheques().size() > 0) {
                                        if (guardaEdicionPagoCompraCheque()) {
                                           ////System.out.println("DETALLES GUARDADOS EN LO Q ES CHEQUES");
                                        }
                                    }
                                    if (getItemsPagoCompraDetalleCueAhs().size() > 0) {
                                        if (guardaEdicionPagoCompraAcreCueAhorro()) {
                                           ////System.out.println("DETALLES GUARDADOS EN LO Q ES ACREDITACION");
                                        }
                                    }

                                    if (getItemsPagoCompraDetalleTrans().size() > 0) {
                                        if (guardaEdicionPagoCompraTransferencia()) {
                                           ////System.out.println("DETALLES GUARDADOS EN LO Q ES TRANSFERENCIA");
                                        }
                                    }
                                    ///  LLAMO AÑ P        
                                    if (llamoProcedimientoComprobantePagoCompra()) {
                                       ////System.out.println("SE HA REALIZADO LA CONTABILIZACION");
                                    }
                                }
                            }
                        }
                        // Verificando que la ejecucion del proceso ha sido correcta
                        if (llamaSP.isEjecucionCorrecta()) {
                            // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                            llamaSP.commit();
                            llamaSP.cerrarConexion();
                            llamaSP.setConexionBD(null);
                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajePagoRealizado"));
                            MuestraMensaje.addInformacion(getMsg());
                            context.execute("procesandoDlg.hide()");

                            setItemsCompras(null);

                            this.init();

                        } else {
                            context.execute("procesandoDlg.hide()");
                            llamaSP.rollback();
                            llamaSP.cerrarConexion();
                            llamaSP.setConexionBD(null);
                        }

                    } catch (Exception ex) {
                       ////System.out.println("Error ");
                        ex.printStackTrace();
                        context.execute("procesandoDlg.hide()");
                        // Muestra el Mensaje del Error en la Capa
                        MuestraMensaje.addErrorCapaControl();
                        // Registra el error en el log del servidor
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                new Object[]{"facturaBean", "guardaDatosPagoCompra", CapturaError.getErrorException(ex)});
                    }
                }
            } else {
                //MensajeFacturaTotalCompraZero
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeValorAbonoZero"));
                MuestraMensaje.addAdvertencia(getMsg());
            }
        } catch (Exception e) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            e.printStackTrace();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCompraBean", "guardoFormaPago", CapturaError.getErrorException(e)});
        }
    }

    /**
     * 
     */
    public void cambiaFecha(){
        try{
            String todayAsString = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES")).format(fechaPagoProveedores);
            System.out.println("PAGO: " + todayAsString);
            fechaPagoProveedores = new Date(new SimpleDateFormat("MM/dd/yyyy", new Locale("es", "ES")).format(fechaPagoProveedores));
        }catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"pagoCompraBean", "cambiaFecha", CapturaError.getErrorException(e)});
        }
    }
    
    /*
     metodo q se usa para ingresar los datos de la cabecera
     de pagoCompra
     */
    public boolean guardoPagoCompraCabecera() throws UnknownHostException {
        System.out.println("Paso guardar p_fecha_pago ORIGINAL: " + fechaPagoProveedores);
        String todayAsString = new SimpleDateFormat("MMddyyyy", new Locale("es", "ES")).format(fechaPagoProveedores);
        System.out.println("Paso 1 guardar p_fecha_pago transformada: " + todayAsString);
        
        if (this.getItemsPagoCompraDetalles() != null) {
            //
            llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra.p_inserta_pago_compra");
            llamaSP.setNumeroParametros(8);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", getPagoCompra().getCompra().getCodigo()});
           ////System.out.println("p_codigo_compra: " + getPagoCompra().getCompra().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_pago", todayAsString});
            System.out.println("Paso 2 guardar p_fecha_pago transformada enviada: " + todayAsString);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", new java.sql.Timestamp(new Date().getTime())});
           ////System.out.println("guardoPagoCompraCabecera p_fecha_sistema: " + fechaPago);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_pagado_por", ActivacionUsuario.getCodigoUsuario()});
           ////System.out.println("p_pagado_por: " + getPagoCompra().getPagadoPor());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_abono", this.totalPago});
           ////System.out.println("p_abono: " + totalPago);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", this.totalSaldo});
           ////System.out.println("p_saldo: " + totalSaldo);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_compra_cancelada", (this.totalSaldo.doubleValue() > 0) ? "N" : "S"});
           ////System.out.println("p_compra_cancelada: " + ((this.totalSaldo.doubleValue() > 0) ? "N" : "S"));

            /////SALIDA
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {

                this.setCodigoPagoCompra((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
               ////System.out.println("codigo codigoPagoCompra: " + this.getCodigoPagoCompra());
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    //
    public boolean edicionPagoCompraCabecera() throws UnknownHostException {

        //this.setFechaPagoProveedoresTs(new java.sql.Timestamp(getPagoCompra().getFechaPago().getTime()));
        //this.setFechaSistemaTs(new java.sql.Timestamp(getPagoCompra().getFechaSistema().getTime()));
        if (this.getItemsPagoCompraDetalles() != null) {
            //
            llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra.p_actualiza_pago_compra");
            llamaSP.setNumeroParametros(8);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", getPagoCompra().getCodigo()});
           ////System.out.println("p_codigo: " + getPagoCompra().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", getPagoCompra().getCompra().getCodigo()});
           ////System.out.println("p_codigo_compra: " + getPagoCompra().getCompra().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_pago", this.getFechaPagoProveedoresTs()});
           ////System.out.println("p_fecha_pago: " + this.getFechaPagoProveedoresTs());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.getFechaSistemaTs()});
           ////System.out.println("p_fecha_sistema: " + this.getFechaSistemaTs());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_pagado_por", getPagoCompra().getPagadoPor()});
           ////System.out.println("p_pagado_por: " + getPagoCompra().getPagadoPor());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_abono", getPagoCompra().getAbono()});
           ////System.out.println("p_abono: " + getPagoCompra().getAbono());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", getPagoCompra().getSaldo()});
           ////System.out.println("p_saldo: " + getPagoCompra().getSaldo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_compra_cancelada", String.valueOf(getPagoCompra().getCompraCancelada())});
           ////System.out.println("p_compra_cancelada: " + String.valueOf(getPagoCompra().getCompraCancelada()));

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {

               ////System.out.println("ACTUALIZACION DEL PAGO DE LA COMPRA REALIZADA");
                setCodigoPagoCompra(getPagoCompra().getCodigo());
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public boolean guardaPagoCompraDetalle() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_detalle.p_inserta_pago_compra_detalle");
        llamaSP.setNumeroParametros(4);
        int pos = 0;

        if (this.getCodigoPagoCompra() != null) {

            for (PagoCompraDetalle pcd : this.getItemsPagoCompraDetalles()) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago", this.getCodigoPagoCompra()});
               ////System.out.println("p_codigo_pago: " + this.getCodigoPagoCompra());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_forma_pago", pcd.getFormaPago().getCodigo()});
               ////System.out.println("p_codigo_forma_pago: " + pcd.getFormaPago().getCodigo());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", pcd.getValor()});
               ////System.out.println("p_valor: " + pcd.getValor());

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
                // Invocando al SP
                llamaSP.invocaSP();

                totalAbonadoDia = totalAbonadoDia.add(pcd.getValor());
                if (pcd.getFormaPago().getCodigo() == 1L) {
                    setCodForPagDetChe((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                    getItemsPagoCDtChequesCod().add(getCodForPagDetChe());
                   ////System.out.println("codigo pago cheque " + getCodForPagDetChe());
                } else if (pcd.getFormaPago().getCodigo() == 2L) {
                    setCodForPagDetEfec((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                   ////System.out.println("codigo pago efectivo " + getCodForPagDetEfec());

                } else if (pcd.getFormaPago().getCodigo() == 3L) {
                    setCodForPagDetCAh((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                    getItemsPagoCDtCueAhsCod().add(getCodForPagDetCAh());
                   ////System.out.println("codigo pago acreditacion a cuenta de ahorros " + getCodForPagDetCAh());
                } else if (pcd.getFormaPago().getCodigo() == 4L) {
                    setCodForPagDetTra((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                    getItemsPagoCDtTraCod().add(getCodForPagDetTra());
                   ////System.out.println("codigo pago por transferencia " + getCodForPagDetTra());
                }

                if (!llamaSP.isEjecucionCorrecta()) {
                   ////System.out.println("PASO LOS CODIGOS ");

                    break;
                }

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaPagoCompraAcreCueAhorro() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_detalle_cue_ah.p_inserta_pago_com_det_ah");
        llamaSP.setNumeroParametros(3);

        if (this.getItemsPagoCompraDetalleCueAhs().size() > 0) {

            for (int i = 0; i < getItemsPagoCDtCueAhsCod().size(); i++) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago_compra_detalle", this.getItemsPagoCDtCueAhsCod().get(i)});
               ////System.out.println("p_codigo_pago_compra_detalle: " + this.getItemsPagoCDtCueAhsCod().get(i));
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", getItemsPagoCompraDetalleCueAhs().get(i).getCodigoCuenta()});
               ////System.out.println("p_codigo_cuenta: " + getItemsPagoCompraDetalleCueAhs().get(i).getCodigoCuenta());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", getItemsPagoCompraDetalleCueAhs().get(i).getValor()});
               ////System.out.println("p_valor: " + getItemsPagoCompraDetalleCueAhs().get(i).getValor());

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                   ////System.out.println("GUARADA POR ACREDITACON AHORRO");

                }

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaPagoCompraCheque() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_detalle_cheque.p_inserta_pago_com_det_che");
        llamaSP.setNumeroParametros(5);

        if (this.getItemsPagoCompraDetalleCheques().size() > 0) {
            for (int i = 0; i < getItemsPagoCDtChequesCod().size(); i++) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago_compra_detalle", this.getItemsPagoCDtChequesCod().get(i)});
               ////System.out.println("p_codigo_pago_compra_detalle: " + this.getItemsPagoCDtChequesCod().get(i));
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", getItemsPagoCompraDetalleCheques().get(i).getCodigoCuenta()});
               ////System.out.println("p_codigo_cuenta: " + getItemsPagoCompraDetalleCheques().get(i).getCodigoCuenta());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", getItemsPagoCompraDetalleCheques().get(i).getValor()});
               ////System.out.println("p_valor: " + getItemsPagoCompraDetalleCheques().get(i).getValor());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_beneficiario", getItemsPagoCompraDetalleCheques().get(i).getBeneficiario()});
               ////System.out.println("p_beneficiario: " + getItemsPagoCompraDetalleCheques().get(i).getBeneficiario());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque", getItemsPagoCompraDetalleCheques().get(i).getNumeroCheque()});
               ////System.out.println("p_numero_cheque: " + getItemsPagoCompraDetalleCheques().get(i).getNumeroCheque());

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                   ////System.out.println("GUARADA ");

                }

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaPagoCompraTransferencia() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_det_transf.p_inserta_pago_compra_det_tr");
        llamaSP.setNumeroParametros(5);

        if (this.getItemsPagoCompraDetalleTrans().size() > 0) {
            for (int i = 0; i < getItemsPagoCDtTraCod().size(); i++) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago_compra_detalle", this.getItemsPagoCDtTraCod().get(i)});
               ////System.out.println("p_codigo_pago_compra_detalle: " + this.getItemsPagoCDtTraCod().get(i));
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", getItemsPagoCompraDetalleTrans().get(i).getCodigoCuentaEntFin()});
               ////System.out.println("p_codigo_cuenta_ent_fin: " + getItemsPagoCompraDetalleTrans().get(i).getCodigoCuentaEntFin());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", getItemsPagoCompraDetalleTrans().get(i).getValor()});
               ////System.out.println("p_valor: " + getItemsPagoCompraDetalleTrans().get(i).getValor());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuenta", getItemsPagoCompraDetalleTrans().get(i).getNumeroCuenta()});
               ////System.out.println("p_numero_cuenta: " + getItemsPagoCompraDetalleTrans().get(i).getNumeroCuenta());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_entidad_financiera", getItemsPagoCompraDetalleTrans().get(i).getCodigoEntidadFinanciera()});
               ////System.out.println("p_codigo_entidad_financiera: " + getItemsPagoCompraDetalleTrans().get(i).getCodigoEntidadFinanciera());

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                   ////System.out.println("GUARADA ");

                }

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaEdicionPagoCompraDetalle() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_detalle.p_inserta_pago_compra_detalle");
        llamaSP.setNumeroParametros(4);
        int pos = 0;
        if (this.getCodigoPagoCompra() != null) {

            for (PagoCompraDetalle pcd : this.getItemsPagoCompraDetalles()) {

                if (ejbFacadePagoCompraDetalle.getItemsfindByCodigo(pcd.getCodigo()).isEmpty()) {

                    llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago", this.getCodigoPagoCompra()});
                   ////System.out.println("p_codigo_pago: " + this.getCodigoPagoCompra());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_forma_pago", pcd.getFormaPago().getCodigo()});
                   ////System.out.println("p_codigo_forma_pago: " + pcd.getFormaPago().getCodigo());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", pcd.getValor()});
                   ////System.out.println("p_valor: " + pcd.getValor());

                    llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                    llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
                    // Invocando al SP
                    llamaSP.invocaSP();

                    totalAbonadoDia = totalAbonadoDia.add(pcd.getValor());

                    if (pcd.getFormaPago().getCodigo() == 1L) {
                        setCodForPagDetChe((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                        getItemsPagoCDtChequesCod().add(getCodForPagDetChe());
                       ////System.out.println("codigo pago cheque " + getCodForPagDetChe());
                    } else if (pcd.getFormaPago().getCodigo() == 2L) {
                        setCodForPagDetEfec((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                       ////System.out.println("codigo pago efectivo " + getCodForPagDetEfec());

                    } else if (pcd.getFormaPago().getCodigo() == 3L) {
                        setCodForPagDetCAh((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                        getItemsPagoCDtCueAhsCod().add(getCodForPagDetCAh());
                       ////System.out.println("codigo pago acreditacion a cuenta de ahorros " + getCodForPagDetCAh());
                    } else if (pcd.getFormaPago().getCodigo() == 4L) {
                        setCodForPagDetTra((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                        getItemsPagoCDtTraCod().add(getCodForPagDetTra());
                       ////System.out.println("codigo pago por transferencia " + getCodForPagDetTra());
                    }
                    if (!llamaSP.isEjecucionCorrecta()) {
                       ////System.out.println("PASO CODIGOS EDICION");
                        break;
                    }
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaEdicionPagoCompraAcreCueAhorro() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_detalle_cue_ah.p_inserta_pago_com_det_ah");
        llamaSP.setNumeroParametros(3);

        if (this.getItemsPagoCompraDetalleCueAhs().size() > 0) {

            for (int i = numFilasActualAcah; i < getItemsPagoCDtCueAhsCod().size(); i++) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago_compra_detalle", this.getItemsPagoCDtCueAhsCod().get(i)});
               ////System.out.println("p_codigo_pago_compra_detalle: " + this.getItemsPagoCDtCueAhsCod().get(i));
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", getItemsPagoCompraDetalleCueAhs().get(i).getCodigoCuenta()});
               ////System.out.println("p_codigo_cuenta: " + getItemsPagoCompraDetalleCueAhs().get(i).getCodigoCuenta());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", getItemsPagoCompraDetalleCueAhs().get(i).getValor()});
               ////System.out.println("p_valor: " + getItemsPagoCompraDetalleCueAhs().get(i).getValor());

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                   ////System.out.println("GUARADA POR ACREDITACON AHORRO");

                }

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaEdicionPagoCompraCheque() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_detalle_cheque.p_inserta_pago_com_det_che");
        llamaSP.setNumeroParametros(5);

        if (this.getItemsPagoCompraDetalleCheques().size() > 0) {
            for (int i = numFilasActualCh; i < getItemsPagoCDtChequesCod().size(); i++) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago_compra_detalle", this.getItemsPagoCDtChequesCod().get(i)});
               ////System.out.println("p_codigo_pago_compra_detalle: " + this.getItemsPagoCDtChequesCod().get(i));
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", getItemsPagoCompraDetalleCheques().get(i).getCodigoCuenta()});
               ////System.out.println("p_codigo_cuenta: " + getItemsPagoCompraDetalleCheques().get(i).getCodigoCuenta());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", getItemsPagoCompraDetalleCheques().get(i).getValor()});
               ////System.out.println("p_valor: " + getItemsPagoCompraDetalleCheques().get(i).getValor());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_beneficiario", getItemsPagoCompraDetalleCheques().get(i).getBeneficiario()});
               ////System.out.println("p_beneficiario: " + getItemsPagoCompraDetalleCheques().get(i).getBeneficiario());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque", getItemsPagoCompraDetalleCheques().get(i).getNumeroCheque()});
               ////System.out.println("p_numero_cheque: " + getItemsPagoCompraDetalleCheques().get(i).getNumeroCheque());

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                   ////System.out.println("GUARADA ");

                }

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaEdicionPagoCompraTransferencia() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_pago_compra_det_transf.p_inserta_pago_compra_det_tr");
        llamaSP.setNumeroParametros(5);

        if (this.getItemsPagoCompraDetalleTrans().size() > 0) {
            for (int i = numFilasActualTr; i < getItemsPagoCDtTraCod().size(); i++) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago_compra_detalle", this.getItemsPagoCDtTraCod().get(i)});
               ////System.out.println("p_codigo_pago_compra_detalle: " + this.getItemsPagoCDtTraCod().get(i));
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", getItemsPagoCompraDetalleTrans().get(i).getCodigoCuentaEntFin()});
               ////System.out.println("p_codigo_cuenta_ent_fin: " + getItemsPagoCompraDetalleTrans().get(i).getCodigoCuentaEntFin());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", getItemsPagoCompraDetalleTrans().get(i).getValor()});
               ////System.out.println("p_valor: " + getItemsPagoCompraDetalleTrans().get(i).getValor());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cuenta", getItemsPagoCompraDetalleTrans().get(i).getNumeroCuenta()});
               ////System.out.println("p_numero_cuenta: " + getItemsPagoCompraDetalleTrans().get(i).getNumeroCuenta());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_entidad_financiera", getItemsPagoCompraDetalleTrans().get(i).getCodigoEntidadFinanciera()});
               ////System.out.println("p_codigo_entidad_financiera: " + getItemsPagoCompraDetalleTrans().get(i).getCodigoEntidadFinanciera());

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                   ////System.out.println("GUARADA ");

                }

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean guardaEstadoTablaCompras() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_compra.p_actualiza_compra_pago_pro");
        llamaSP.setNumeroParametros(4);
        if (this.getItemsPagoCompraDetalles() != null) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.compraSel.getCodigo()});
           ////System.out.println("p_codigo: " + this.compraSel.getCodigo());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", (this.totalSaldo.doubleValue() == 0) ? "C" : "N"});
           ////System.out.println("p_estado: " + String.valueOf(this.compraSel.getEstado()));
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_abono", this.totalAbono});
           ////System.out.println("p_abono: " + this.totalAbono);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", this.totalSaldo});
           ////System.out.println("p_saldo: " + this.totalSaldo);

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (!llamaSP.isEjecucionCorrecta()) {
               ////System.out.println("GUARADA ");

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean llamoProcedimientoComprobantePagoCompra() {
        if (isContabilizaFechaPagoFactura()) {
            this.fechaSistemaTs =   new java.sql.Timestamp(fechaPagoProveedores.getTime());
        } else {
            this.fechaSistemaTs = new java.sql.Timestamp(new Date().getTime());
        }
        //this.setFechaSistemaTs(new java.sql.Timestamp(getPagoCompra().getFechaSistema().getTime()));
        llamaSP.setNombreSP("mks_contables.pkm_comprobante.p_comprobante_pago_compra");
        llamaSP.setNumeroParametros(10);
        if (this.getItemsPagoCompraDetalles() != null) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pago_compra", getCodigoPagoCompra()});           
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", this.compraSel.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", ejbFacadePeriodoContable.getItemPeriodoContable('S').get(0).getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia_ori", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", this.fechaSistemaTs});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", this.totalAbonadoDia});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_contabilizado_por", ActivacionUsuario.getCodigoUsuario()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_comprobante", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (llamaSP.isEjecucionCorrecta()) {
                this.setNumeroComprobantePago(llamaSP.getListResultado().get(0).toString());
                this.setCodigoComprobantePago((Long) Long.parseLong(llamaSP.getListResultado().get(1).toString()));
               ////System.out.println("GUARADA ");

            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    /**
     * Imprime el comprobante de retencion
     *
     * @param actionEvent
     */
    public void imprimirComprobante(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        ////System.out.println("INGRESO A IMPRIMIR LA RETENCIO*******888**** N       " + compraSel.getCodigo());
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoPago", (long) this.codigoPagoCompra);

        nombreReporte = "pagoFactura";
        // comprobanteRetencion
        getGeneraReporte().exporta("/adquisicion/compras/pagoFacturas/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.codigoPagoCompra) + ".pdf",
                "PDF", externalContext, facesContext);

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

    /**
     * @return the opcionIngEdiPagoPro
     */
    public int getOpcionIngEdiPagoPro() {
        return opcionIngEdiPagoPro;
    }

    /**
     * @param opcionIngEdiPagoPro the opcionIngEdiPagoPro to set
     */
    public void setOpcionIngEdiPagoPro(int opcionIngEdiPagoPro) {
        this.opcionIngEdiPagoPro = opcionIngEdiPagoPro;
    }

    /**
     * @return the codigoPagoCompra
     */
    public Long getCodigoPagoCompra() {
        return codigoPagoCompra;
    }

    /**
     * @param codigoPagoCompra the codigoPagoCompra to set
     */
    public void setCodigoPagoCompra(Long codigoPagoCompra) {
        this.codigoPagoCompra = codigoPagoCompra;
    }

    /**
     * @return the numeroCheque
     */
    public Long getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(Long numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * @return the codForPagDetEfec
     */
    public Long getCodForPagDetEfec() {
        return codForPagDetEfec;
    }

    /**
     * @param codForPagDetEfec the codForPagDetEfec to set
     */
    public void setCodForPagDetEfec(Long codForPagDetEfec) {
        this.codForPagDetEfec = codForPagDetEfec;
    }

    /**
     * @return the codForPagDetChe
     */
    public Long getCodForPagDetChe() {
        return codForPagDetChe;
    }

    /**
     * @param codForPagDetChe the codForPagDetChe to set
     */
    public void setCodForPagDetChe(Long codForPagDetChe) {
        this.codForPagDetChe = codForPagDetChe;
    }

    /**
     * @return the codForPagDetCAh
     */
    public Long getCodForPagDetCAh() {
        return codForPagDetCAh;
    }

    /**
     * @param codForPagDetCAh the codForPagDetCAh to set
     */
    public void setCodForPagDetCAh(Long codForPagDetCAh) {
        this.codForPagDetCAh = codForPagDetCAh;
    }

    /**
     * @return the codForPagDetTra
     */
    public Long getCodForPagDetTra() {
        return codForPagDetTra;
    }

    /**
     * @param codForPagDetTra the codForPagDetTra to set
     */
    public void setCodForPagDetTra(Long codForPagDetTra) {
        this.codForPagDetTra = codForPagDetTra;
    }

    /**
     * @return the codigoEvaluar
     */
    public Long getCodigoEvaluar() {
        return codigoEvaluar;
    }

    /**
     * @param codigoEvaluar the codigoEvaluar to set
     */
    public void setCodigoEvaluar(Long codigoEvaluar) {
        this.codigoEvaluar = codigoEvaluar;
    }

    /**
     * @return the fechaPagoProveedores
     */
    public Date getFechaPagoProveedores() {
        return fechaPagoProveedores;
    }

    /**
     * @param fechaPagoProveedores the fechaPagoProveedores to set
     */
    public void setFechaPagoProveedores(Date fechaPagoProveedores) {
        this.fechaPagoProveedores = fechaPagoProveedores;
    }

    /**
     * @return the numeroCuentaDesTra
     */
    public String getNumeroCuentaDesTra() {
        return numeroCuentaDesTra;
    }

    /**
     * @param numeroCuentaDesTra the numeroCuentaDesTra to set
     */
    public void setNumeroCuentaDesTra(String numeroCuentaDesTra) {
        this.numeroCuentaDesTra = numeroCuentaDesTra;
    }

    /**
     * @return the nombreBeneficiario
     */
    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    /**
     * @param nombreBeneficiario the nombreBeneficiario to set
     */
    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
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
     * @return the numeroCuentaCre
     */
    public String getNumeroCuentaCre() {
        return numeroCuentaCre;
    }

    /**
     * @param numeroCuentaCre the numeroCuentaCre to set
     */
    public void setNumeroCuentaCre(String numeroCuentaCre) {
        this.numeroCuentaCre = numeroCuentaCre;
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
     * @return the serieParteUnoAux
     */
    public String getSerieParteUnoAux() {
        return serieParteUnoAux;
    }

    /**
     * @param serieParteUnoAux the serieParteUnoAux to set
     */
    public void setSerieParteUnoAux(String serieParteUnoAux) {
        this.serieParteUnoAux = serieParteUnoAux;
    }

    /**
     * @return the serieParteDosAux
     */
    public String getSerieParteDosAux() {
        return serieParteDosAux;
    }

    /**
     * @param serieParteDosAux the serieParteDosAux to set
     */
    public void setSerieParteDosAux(String serieParteDosAux) {
        this.serieParteDosAux = serieParteDosAux;
    }

    /**
     * @return the serieParteTresAux
     */
    public String getSerieParteTresAux() {
        return serieParteTresAux;
    }

    /**
     * @param serieParteTresAux the serieParteTresAux to set
     */
    public void setSerieParteTresAux(String serieParteTresAux) {
        this.serieParteTresAux = serieParteTresAux;
    }

    /**
     * @return the serieFormada
     */
    public String getSerieFormada() {
        return serieFormada;
    }

    /**
     * @param serieFormada the serieFormada to set
     */
    public void setSerieFormada(String serieFormada) {
        this.serieFormada = serieFormada;
    }

    /**
     * @return the fechaPagoProveedoresTs
     */
    public Timestamp getFechaPagoProveedoresTs() {
        return fechaPagoProveedoresTs;
    }

    /**
     * @param fechaPagoProveedoresTs the fechaPagoProveedoresTs to set
     */
    public void setFechaPagoProveedoresTs(Timestamp fechaPagoProveedoresTs) {
        this.fechaPagoProveedoresTs = fechaPagoProveedoresTs;
    }

    /**
     * @return the banderaEstadoCheque
     */
    public boolean isBanderaEstadoCheque() {
        return banderaEstadoCheque;
    }

    /**
     * @param banderaEstadoCheque the banderaEstadoCheque to set
     */
    public void setBanderaEstadoCheque(boolean banderaEstadoCheque) {
        this.banderaEstadoCheque = banderaEstadoCheque;
    }

    /**
     * @return the banderaEstadoTransfer
     */
    public boolean isBanderaEstadoTransfer() {
        return banderaEstadoTransfer;
    }

    /**
     * @param banderaEstadoTransfer the banderaEstadoTransfer to set
     */
    public void setBanderaEstadoTransfer(boolean banderaEstadoTransfer) {
        this.banderaEstadoTransfer = banderaEstadoTransfer;
    }

    /**
     * @return the banderaEstadoEfectivo
     */
    public boolean isBanderaEstadoEfectivo() {
        return banderaEstadoEfectivo;
    }

    /**
     * @param banderaEstadoEfectivo the banderaEstadoEfectivo to set
     */
    public void setBanderaEstadoEfectivo(boolean banderaEstadoEfectivo) {
        this.banderaEstadoEfectivo = banderaEstadoEfectivo;
    }

    /**
     * @return the banderaEstadoCredito
     */
    public boolean isBanderaEstadoCredito() {
        return banderaEstadoCredito;
    }

    /**
     * @param banderaEstadoCredito the banderaEstadoCredito to set
     */
    public void setBanderaEstadoCredito(boolean banderaEstadoCredito) {
        this.banderaEstadoCredito = banderaEstadoCredito;
    }

    /**
     * @return the banderaCompraDetCheq
     */
    public boolean isBanderaCompraDetCheq() {
        return banderaCompraDetCheq;
    }

    /**
     * @param banderaCompraDetCheq the banderaCompraDetCheq to set
     */
    public void setBanderaCompraDetCheq(boolean banderaCompraDetCheq) {
        this.banderaCompraDetCheq = banderaCompraDetCheq;
    }

    /**
     * @return the banderaFctCancelada
     */
    public boolean isBanderaFctCancelada() {
        banderaFctCancelada = false;
        //opcionGeRet = 0;
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (getCompraSel() != null) {
                if (String.valueOf(getCompraSel().getEstado()).equals("C")) {
                   ////System.out.println("LA FACTURA YA ESTA CANCELADA");
                    banderaFctCancelada = false;
                    context.execute("procesandoDlg.hide()");
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCancelada"));
                    MuestraMensaje.addAdvertencia(getMsg());

                } else {
                   ////System.out.println("LA FACTURA AUN FALTA POR PAGAR");
                    banderaFctCancelada = true;
                    context.execute("procesandoDlg.hide()");
                }
                context.execute("procesandoDlg.hide()");

            }
        } catch (Exception e) {
        }

        return banderaFctCancelada;
    }

    /**
     * @param banderaFctCancelada the banderaFctCancelada to set
     */
    public void setBanderaFctCancelada(boolean banderaFctCancelada) {
        this.banderaFctCancelada = banderaFctCancelada;
    }

    /**
     * @return the numeroCuenta
     */
    public IfipCuentaEntidadFinanciera getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(IfipCuentaEntidadFinanciera numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the numeroCuentaOriTra
     */
    public IfipCuentaEntidadFinanciera getNumeroCuentaOriTra() {
        return numeroCuentaOriTra;
    }

    /**
     * @param numeroCuentaOriTra the numeroCuentaOriTra to set
     */
    public void setNumeroCuentaOriTra(IfipCuentaEntidadFinanciera numeroCuentaOriTra) {
        this.numeroCuentaOriTra = numeroCuentaOriTra;
    }

    /**
     * @return the valorAbonoDetalle
     */
    public BigDecimal getValorAbonoDetalle() {
        return valorAbonoDetalle;
    }

    /**
     * @param valorAbonoDetalle the valorAbonoDetalle to set
     */
    public void setValorAbonoDetalle(BigDecimal valorAbonoDetalle) {
        this.valorAbonoDetalle = valorAbonoDetalle;
    }

    /**
     * @return the valorAbonoDetalleChe
     */
    public BigDecimal getValorAbonoDetalleChe() {
        return valorAbonoDetalleChe;
    }

    /**
     * @param valorAbonoDetalleChe the valorAbonoDetalleChe to set
     */
    public void setValorAbonoDetalleChe(BigDecimal valorAbonoDetalleChe) {
        this.valorAbonoDetalleChe = valorAbonoDetalleChe;
    }

    /**
     * @return the valorAbonoDetalleEfectivo
     */
    public BigDecimal getValorAbonoDetalleEfectivo() {
        return valorAbonoDetalleEfectivo;
    }

    /**
     * @param valorAbonoDetalleEfectivo the valorAbonoDetalleEfectivo to set
     */
    public void setValorAbonoDetalleEfectivo(BigDecimal valorAbonoDetalleEfectivo) {
        this.valorAbonoDetalleEfectivo = valorAbonoDetalleEfectivo;
    }

    /**
     * @return the valorAbonoDetalleCredito
     */
    public BigDecimal getValorAbonoDetalleCredito() {
        return valorAbonoDetalleCredito;
    }

    /**
     * @param valorAbonoDetalleCredito the valorAbonoDetalleCredito to set
     */
    public void setValorAbonoDetalleCredito(BigDecimal valorAbonoDetalleCredito) {
        this.valorAbonoDetalleCredito = valorAbonoDetalleCredito;
    }

    /**
     * @return the valorAbonoDetalleTrans
     */
    public BigDecimal getValorAbonoDetalleTrans() {
        return valorAbonoDetalleTrans;
    }

    /**
     * @param valorAbonoDetalleTrans the valorAbonoDetalleTrans to set
     */
    public void setValorAbonoDetalleTrans(BigDecimal valorAbonoDetalleTrans) {
        this.valorAbonoDetalleTrans = valorAbonoDetalleTrans;
    }

    /**
     * @return the formaPagoSel
     */
    public FormaPago getFormaPagoSel() {
        return formaPagoSel;
    }

    /**
     * @param formaPagoSel the formaPagoSel to set
     */
    public void setFormaPagoSel(FormaPago formaPagoSel) {
        this.formaPagoSel = formaPagoSel;
    }

    /**
     * @return the pagoCompra
     */
    public PagoCompra getPagoCompra() {
        return pagoCompra;
    }

    /**
     * @param pagoCompra the pagoCompra to set
     */
    public void setPagoCompra(PagoCompra pagoCompra) {
        this.pagoCompra = pagoCompra;
    }

    /**
     * @return the pagoCompraDetalle
     */
    public PagoCompraDetalle getPagoCompraDetalle() {
        return pagoCompraDetalle;
    }

    /**
     * @param pagoCompraDetalle the pagoCompraDetalle to set
     */
    public void setPagoCompraDetalle(PagoCompraDetalle pagoCompraDetalle) {
        this.pagoCompraDetalle = pagoCompraDetalle;
    }

    /**
     * @return the pagoCompraDetalleSel
     */
    public PagoCompraDetalle getPagoCompraDetalleSel() {
        return pagoCompraDetalleSel;
    }

    /**
     * @param pagoCompraDetalleSel the pagoCompraDetalleSel to set
     */
    public void setPagoCompraDetalleSel(PagoCompraDetalle pagoCompraDetalleSel) {
        this.pagoCompraDetalleSel = pagoCompraDetalleSel;
    }

    /**
     * @return the cueConEntFinSel
     */
    public CuentaContableEntFin getCueConEntFinSel() {
        return cueConEntFinSel;
    }

    /**
     * @param cueConEntFinSel the cueConEntFinSel to set
     */
    public void setCueConEntFinSel(CuentaContableEntFin cueConEntFinSel) {
        this.cueConEntFinSel = cueConEntFinSel;
    }

    /**
     * @return the entidadFinacieraSel
     */
    public EntidadFinanciera getEntidadFinacieraSel() {
        return entidadFinacieraSel;
    }

    /**
     * @param entidadFinacieraSel the entidadFinacieraSel to set
     */
    public void setEntidadFinacieraSel(EntidadFinanciera entidadFinacieraSel) {
        this.entidadFinacieraSel = entidadFinacieraSel;
    }

    /**
     * @return the entidadFinacieraSelTraOrigen
     */
    public EntidadFinanciera getEntidadFinacieraSelTraOrigen() {
        return entidadFinacieraSelTraOrigen;
    }

    /**
     * @param entidadFinacieraSelTraOrigen the entidadFinacieraSelTraOrigen to
     * set
     */
    public void setEntidadFinacieraSelTraOrigen(EntidadFinanciera entidadFinacieraSelTraOrigen) {
        this.entidadFinacieraSelTraOrigen = entidadFinacieraSelTraOrigen;
    }

    /**
     * @return the entidadFinacieraSelTraDestino
     */
    public EntidadFinanciera getEntidadFinacieraSelTraDestino() {
        return entidadFinacieraSelTraDestino;
    }

    /**
     * @param entidadFinacieraSelTraDestino the entidadFinacieraSelTraDestino to
     * set
     */
    public void setEntidadFinacieraSelTraDestino(EntidadFinanciera entidadFinacieraSelTraDestino) {
        this.entidadFinacieraSelTraDestino = entidadFinacieraSelTraDestino;
    }

    /**
     * @return the pagoCompraDetalleCueAh
     */
    public PagoCompraDetalleCueAh getPagoCompraDetalleCueAh() {
        return pagoCompraDetalleCueAh;
    }

    /**
     * @param pagoCompraDetalleCueAh the pagoCompraDetalleCueAh to set
     */
    public void setPagoCompraDetalleCueAh(PagoCompraDetalleCueAh pagoCompraDetalleCueAh) {
        this.pagoCompraDetalleCueAh = pagoCompraDetalleCueAh;
    }

    /**
     * @return the pagoCompraDetalleCheque
     */
    public PagoCompraDetalleCheque getPagoCompraDetalleCheque() {
        return pagoCompraDetalleCheque;
    }

    /**
     * @param pagoCompraDetalleCheque the pagoCompraDetalleCheque to set
     */
    public void setPagoCompraDetalleCheque(PagoCompraDetalleCheque pagoCompraDetalleCheque) {
        this.pagoCompraDetalleCheque = pagoCompraDetalleCheque;
    }

    /**
     * @return the pagoCompraDetalleTransf
     */
    public PagoCompraDetalleTransf getPagoCompraDetalleTransf() {
        return pagoCompraDetalleTransf;
    }

    /**
     * @param pagoCompraDetalleTransf the pagoCompraDetalleTransf to set
     */
    public void setPagoCompraDetalleTransf(PagoCompraDetalleTransf pagoCompraDetalleTransf) {
        this.pagoCompraDetalleTransf = pagoCompraDetalleTransf;
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
     * @return the itemsPagoCompraDetalles
     */
    public List<PagoCompraDetalle> getItemsPagoCompraDetalles() {
        return itemsPagoCompraDetalles;
    }

    /**
     * @param itemsPagoCompraDetalles the itemsPagoCompraDetalles to set
     */
    public void setItemsPagoCompraDetalles(List<PagoCompraDetalle> itemsPagoCompraDetalles) {
        this.itemsPagoCompraDetalles = itemsPagoCompraDetalles;
    }

    /**
     * @return the itemsFormaPagos
     */
    public List<FormaPago> getItemsFormaPagos() {
        return itemsFormaPagos;
    }

    /**
     * @param itemsFormaPagos the itemsFormaPagos to set
     */
    public void setItemsFormaPagos(List<FormaPago> itemsFormaPagos) {
        this.itemsFormaPagos = itemsFormaPagos;
    }

    /**
     * @return the itemsEntidadFinanciera
     */
    public List<EntidadFinanciera> getItemsEntidadFinanciera() {
        return itemsEntidadFinanciera;
    }

    /**
     * @param the itemsEntidadFinanciera to set
     */
    public void setItemsEntidadFinanciera(List<EntidadFinanciera> itemsEntidadFinanciera) {
        this.itemsEntidadFinanciera = itemsEntidadFinanciera;
    }

    /**
     * @return the itemsEntidadFinancieraTraOri
     */
    public List<EntidadFinanciera> getItemsEntidadFinancieraTraOri() {
        return itemsEntidadFinancieraTraOri;
    }

    /**
     * @param itemsEntidadFinancieraTraOri the itemsEntidadFinancieraTraOri to
     * set
     */
    public void setItemsEntidadFinancieraTraOri(List<EntidadFinanciera> itemsEntidadFinancieraTraOri) {
        this.itemsEntidadFinancieraTraOri = itemsEntidadFinancieraTraOri;
    }

    /**
     * @return the itemsEntidadFinancieraTraDes
     */
    public List<EntidadFinanciera> getItemsEntidadFinancieraTraDes() {
        return itemsEntidadFinancieraTraDes;
    }

    /**
     * @param itemsEntidadFinancieraTraDes the itemsEntidadFinancieraTraDes to
     * set
     */
    public void setItemsEntidadFinancieraTraDes(List<EntidadFinanciera> itemsEntidadFinancieraTraDes) {
        this.itemsEntidadFinancieraTraDes = itemsEntidadFinancieraTraDes;
    }

    /**
     * @return the itemsCuentaContableEntFin
     */
    public List<CuentaContableEntFin> getItemsCuentaContableEntFin() {
        return itemsCuentaContableEntFin;
    }

    /**
     * @param itemsCuentaContableEntFin the itemsCuentaContableEntFin to set
     */
    public void setItemsCuentaContableEntFin(List<CuentaContableEntFin> itemsCuentaContableEntFin) {
        this.itemsCuentaContableEntFin = itemsCuentaContableEntFin;
    }

    /**
     * @return the itemsPagoCompraDetalleCueAhs
     */
    public List<PagoCompraDetalleCueAh> getItemsPagoCompraDetalleCueAhs() {
        return itemsPagoCompraDetalleCueAhs;
    }

    /**
     * @param itemsPagoCompraDetalleCueAhs the itemsPagoCompraDetalleCueAhs to
     * set
     */
    public void setItemsPagoCompraDetalleCueAhs(List<PagoCompraDetalleCueAh> itemsPagoCompraDetalleCueAhs) {
        this.itemsPagoCompraDetalleCueAhs = itemsPagoCompraDetalleCueAhs;
    }

    /**
     * @return the itemsPagoCompraDetalleCheques
     */
    public List<PagoCompraDetalleCheque> getItemsPagoCompraDetalleCheques() {
        return itemsPagoCompraDetalleCheques;
    }

    /**
     * @param itemsPagoCompraDetalleCheques the itemsPagoCompraDetalleCheques to
     * set
     */
    public void setItemsPagoCompraDetalleCheques(List<PagoCompraDetalleCheque> itemsPagoCompraDetalleCheques) {
        this.itemsPagoCompraDetalleCheques = itemsPagoCompraDetalleCheques;
    }

    /**
     * @return the itemsPagoCompraDetalleTrans
     */
    public List<PagoCompraDetalleTransf> getItemsPagoCompraDetalleTrans() {
        return itemsPagoCompraDetalleTrans;
    }

    /**
     * @param itemsPagoCompraDetalleTrans the itemsPagoCompraDetalleTrans to set
     */
    public void setItemsPagoCompraDetalleTrans(List<PagoCompraDetalleTransf> itemsPagoCompraDetalleTrans) {
        this.itemsPagoCompraDetalleTrans = itemsPagoCompraDetalleTrans;
    }

    /**
     * @return the itemsCuentasEntidadFinanciera
     */
    public List<IfipCuentaEntidadFinanciera> getItemsCuentasEntidadFinanciera() {
        return itemsCuentasEntidadFinanciera;
    }

    /**
     * @param itemsCuentasEntidadFinanciera the itemsCuentasEntidadFinanciera to
     * set
     */
    public void setItemsCuentasEntidadFinanciera(List<IfipCuentaEntidadFinanciera> itemsCuentasEntidadFinanciera) {
        this.itemsCuentasEntidadFinanciera = itemsCuentasEntidadFinanciera;
    }

    /**
     * @return the itemsCuentasEntidadFinancieraTraOrigen
     */
    public List<IfipCuentaEntidadFinanciera> getItemsCuentasEntidadFinancieraTraOrigen() {
        return itemsCuentasEntidadFinancieraTraOrigen;
    }

    /**
     * @param itemsCuentasEntidadFinancieraTraOrigen the
     * itemsCuentasEntidadFinancieraTraOrigen to set
     */
    public void setItemsCuentasEntidadFinancieraTraOrigen(List<IfipCuentaEntidadFinanciera> itemsCuentasEntidadFinancieraTraOrigen) {
        this.itemsCuentasEntidadFinancieraTraOrigen = itemsCuentasEntidadFinancieraTraOrigen;
    }

    /**
     * @return the itemsCuentasEntidadFinancieraTraDestino
     */
    public List<String> getItemsCuentasEntidadFinancieraTraDestino() {
        return itemsCuentasEntidadFinancieraTraDestino;
    }

    /**
     * @param itemsCuentasEntidadFinancieraTraDestino the
     * itemsCuentasEntidadFinancieraTraDestino to set
     */
    public void setItemsCuentasEntidadFinancieraTraDestino(List<String> itemsCuentasEntidadFinancieraTraDestino) {
        this.itemsCuentasEntidadFinancieraTraDestino = itemsCuentasEntidadFinancieraTraDestino;
    }

    /**
     * @return the itemsPagoCDtCueAhsCod
     */
    public List<Long> getItemsPagoCDtCueAhsCod() {
        return itemsPagoCDtCueAhsCod;
    }

    /**
     * @param itemsPagoCDtCueAhsCod the itemsPagoCDtCueAhsCod to set
     */
    public void setItemsPagoCDtCueAhsCod(List<Long> itemsPagoCDtCueAhsCod) {
        this.itemsPagoCDtCueAhsCod = itemsPagoCDtCueAhsCod;
    }

    /**
     * @return the itemsPagoCDtChequesCod
     */
    public List<Long> getItemsPagoCDtChequesCod() {
        return itemsPagoCDtChequesCod;
    }

    /**
     * @param itemsPagoCDtChequesCod the itemsPagoCDtChequesCod to set
     */
    public void setItemsPagoCDtChequesCod(List<Long> itemsPagoCDtChequesCod) {
        this.itemsPagoCDtChequesCod = itemsPagoCDtChequesCod;
    }

    /**
     * @return the itemsPagoCDtTraCod
     */
    public List<Long> getItemsPagoCDtTraCod() {
        return itemsPagoCDtTraCod;
    }

    /**
     * @param itemsPagoCDtTraCod the itemsPagoCDtTraCod to set
     */
    public void setItemsPagoCDtTraCod(List<Long> itemsPagoCDtTraCod) {
        this.itemsPagoCDtTraCod = itemsPagoCDtTraCod;
    }

    /**
     * @return the compraSel
     */
    public Compra getCompraSel() {
        return compraSel;
    }

    /**
     * @param compraSel the compraSel to set
     */
    public void setCompraSel(Compra compraSel) {
        this.compraSel = compraSel;
    }

    /**
     * @return the descuentoCompra
     */
    public BigDecimal getDescuentoCompra() {
        return descuentoCompra;
    }

    /**
     * @param descuentoCompra the descuentoCompra to set
     */
    public void setDescuentoCompra(BigDecimal descuentoCompra) {
        this.descuentoCompra = descuentoCompra;
    }

    /**
     * @return the totalCompra
     */
    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    /**
     * @param totalCompra the totalCompra to set
     */
    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

    /**
     * @return the totalAbono
     */
    public BigDecimal getTotalAbono() {
        return totalAbono;
    }

    /**
     * @param totalAbono the totalAbono to set
     */
    public void setTotalAbono(BigDecimal totalAbono) {
        this.totalAbono = totalAbono;
    }

    /**
     * @return the totalSaldo
     */
    public BigDecimal getTotalSaldo() {
        return totalSaldo;
    }

    /**
     * @param totalSaldo the totalSaldo to set
     */
    public void setTotalSaldo(BigDecimal totalSaldo) {
        this.totalSaldo = totalSaldo;
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
     * @return the fechaSistema
     */
    public Date getFechaSistema() {
        return fechaSistema;
    }

    /**
     * @param fechaSistema the fechaSistema to set
     */
    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    /**
     * @return the fechaSistemaTs
     */
    public Timestamp getFechaSistemaTs() {
        return fechaSistemaTs;
    }

    /**
     * @param fechaSistemaTs the fechaSistemaTs to set
     */
    public void setFechaSistemaTs(Timestamp fechaSistemaTs) {
        this.fechaSistemaTs = fechaSistemaTs;
    }

    /**
     * @return the itemsCompras
     */
    public List<Compra> getItemsCompras() {
        return itemsCompras;
    }

    /**
     * @param itemsCompras the itemsCompras to set
     */
    public void setItemsCompras(List<Compra> itemsCompras) {
        this.itemsCompras = itemsCompras;
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
     * @return the provedorIfip
     */
    public ProveedorIfip getProvedorIfip() {
        return provedorIfip;
    }

    /**
     * @param provedorIfip the provedorIfip to set
     */
    public void setProvedorIfip(ProveedorIfip provedorIfip) {
        this.provedorIfip = provedorIfip;
    }

    /**
     * @return the ubicaTipoComprobanteCompra
     */
    public TipoComprobanteCompra getUbicaTipoComprobanteCompra() {
        return ubicaTipoComprobanteCompra;
    }

    /**
     * @param ubicaTipoComprobanteCompra the ubicaTipoComprobanteCompra to set
     */
    public void setUbicaTipoComprobanteCompra(TipoComprobanteCompra ubicaTipoComprobanteCompra) {
        this.ubicaTipoComprobanteCompra = ubicaTipoComprobanteCompra;
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
     * @return the estadoRiseProveedor
     */
    public String getEstadoRiseProveedor() {
        return estadoRiseProveedor;
    }

    /**
     * @param estadoRiseProveedor the estadoRiseProveedor to set
     */
    public void setEstadoRiseProveedor(String estadoRiseProveedor) {
        this.estadoRiseProveedor = estadoRiseProveedor;
    }

    /**
     * @return the estadoRetieneIvaProveedor
     */
    public String getEstadoRetieneIvaProveedor() {
        return estadoRetieneIvaProveedor;
    }

    /**
     * @param estadoRetieneIvaProveedor the estadoRetieneIvaProveedor to set
     */
    public void setEstadoRetieneIvaProveedor(String estadoRetieneIvaProveedor) {
        this.estadoRetieneIvaProveedor = estadoRetieneIvaProveedor;
    }

    /**
     * @return the itemsProveedores
     */
    public List<ProveedorIfip> getItemsProveedores() {
        return itemsProveedores;
    }

    /**
     * @param itemsProveedores the itemsProveedores to set
     */
    public void setItemsProveedores(List<ProveedorIfip> itemsProveedores) {
        this.itemsProveedores = itemsProveedores;
    }

    /**
     * @return the criterio
     */
    public Long getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(Long criterio) {
        this.criterio = criterio;
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
     * @return the fechaIngresoFactura
     */
    public Date getFechaIngresoFactura() {
        return fechaIngresoFactura;
    }

    /**
     * @param fechaIngresoFactura the fechaIngresoFactura to set
     */
    public void setFechaIngresoFactura(Date fechaIngresoFactura) {
        this.fechaIngresoFactura = fechaIngresoFactura;
    }

    /**
     * @return the nombreProevedorCompleto
     */
    public String getNombreProevedorCompleto() {
        return nombreProevedorCompleto;
    }

    /**
     * @param nombreProevedorCompleto the nombreProevedorCompleto to set
     */
    public void setNombreProevedorCompleto(String nombreProevedorCompleto) {
        this.nombreProevedorCompleto = nombreProevedorCompleto;
    }

    /**
     * @return the nombreProveedorBusqueda
     */
    public String getNombreProveedorBusqueda() {
        return nombreProveedorBusqueda;
    }

    /**
     * @param nombreProveedorBusqueda the nombreProveedorBusqueda to set
     */
    public void setNombreProveedorBusqueda(String nombreProveedorBusqueda) {
        this.nombreProveedorBusqueda = nombreProveedorBusqueda;
    }

    /**
     * @return the ciRucProveedor
     */
    public String getCiRucProveedor() {
        return ciRucProveedor;
    }

    /**
     * @param ciRucProveedor the ciRucProveedor to set
     */
    public void setCiRucProveedor(String ciRucProveedor) {
        this.ciRucProveedor = ciRucProveedor;
    }

    /**
     * @return the comprasDireccionProveedor
     */
    public String getComprasDireccionProveedor() {
        return comprasDireccionProveedor;
    }

    /**
     * @param comprasDireccionProveedor the comprasDireccionProveedor to set
     */
    public void setComprasDireccionProveedor(String comprasDireccionProveedor) {
        this.comprasDireccionProveedor = comprasDireccionProveedor;
    }

    /**
     * @return the proveedorIfipSel
     */
    public ProveedorIfip getProveedorIfipSel() {
        return proveedorIfipSel;
    }

    /**
     * @param proveedorIfipSel the proveedorIfipSel to set
     */
    public void setProveedorIfipSel(ProveedorIfip proveedorIfipSel) {
        this.proveedorIfipSel = proveedorIfipSel;
    }

    /**
     * @return the deshabilitaBuscarProveedor
     */
    public boolean isDeshabilitaBuscarProveedor() {
        return deshabilitaBuscarProveedor;
    }

    /**
     * @param deshabilitaBuscarProveedor the deshabilitaBuscarProveedor to set
     */
    public void setDeshabilitaBuscarProveedor(boolean deshabilitaBuscarProveedor) {
        this.deshabilitaBuscarProveedor = deshabilitaBuscarProveedor;
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
     * @return the criterioRepresentante
     */
    public Long getCriterioRepresentante() {
        return criterioRepresentante;
    }

    /**
     * @param criterioRepresentante the criterioRepresentante to set
     */
    public void setCriterioRepresentante(Long criterioRepresentante) {
        this.criterioRepresentante = criterioRepresentante;
    }

    /**
     * @return the numColumna
     */
    public int getNumColumna() {
        return numColumna;
    }

    /**
     * @param numColumna the numColumna to set
     */
    public void setNumColumna(int numColumna) {
        this.numColumna = numColumna;
    }

    /**
     * @return the banderaEstPagoCompraN
     */
    public boolean isBanderaEstPagoCompraN() {

        banderaEstPagoCompraN = false;
        try {
            if (ejbFacadePagoCompra.getItemsfindByCodigoCompra(compraSel.getCodigo()).isEmpty()) {

                banderaEstPagoCompraN = true;
            } else {

                banderaEstPagoCompraN = false;
            }
        } catch (Exception e) {
        }
        return banderaEstPagoCompraN;
    }

    /**
     * @param banderaEstPagoCompraN the banderaEstPagoCompraN to set
     */
    public void setBanderaEstPagoCompraN(boolean banderaEstPagoCompraN) {
        this.banderaEstPagoCompraN = banderaEstPagoCompraN;
    }

    /**
     * @return the banderaEstPagoCompraI
     */
    public boolean isBanderaEstPagoCompraI() {

        try {
            if (!ejbFacadePagoCompra.getItemsfindByCodigoCompra(compraSel.getCodigo()).isEmpty()) {

                banderaEstPagoCompraI = true;
            } else {

                banderaEstPagoCompraI = false;
            }
        } catch (Exception e) {
        }
        return banderaEstPagoCompraI;
    }

    /**
     * @param banderaEstPagoCompraI the banderaEstPagoCompraI to set
     */
    public void setBanderaEstPagoCompraI(boolean banderaEstPagoCompraI) {
        this.banderaEstPagoCompraI = banderaEstPagoCompraI;
    }

    /**
     * @return the numFilasActualCh
     */
    public int getNumFilasActualCh() {
        return numFilasActualCh;
    }

    /**
     * @param numFilasActualCh the numFilasActualCh to set
     */
    public void setNumFilasActualCh(int numFilasActualCh) {
        this.numFilasActualCh = numFilasActualCh;
    }

    /**
     * @return the numFilasActualAcah
     */
    public int getNumFilasActualAcah() {
        return numFilasActualAcah;
    }

    /**
     * @param numFilasActualAcah the numFilasActualAcah to set
     */
    public void setNumFilasActualAcah(int numFilasActualAcah) {
        this.numFilasActualAcah = numFilasActualAcah;
    }

    /**
     * @return the numFilasActualTr
     */
    public int getNumFilasActualTr() {
        return numFilasActualTr;
    }

    /**
     * @param numFilasActualTr the numFilasActualTr to set
     */
    public void setNumFilasActualTr(int numFilasActualTr) {
        this.numFilasActualTr = numFilasActualTr;
    }

    /**
     * @return the totalAbonadoDia
     */
    public BigDecimal getTotalAbonadoDia() {
        return totalAbonadoDia;
    }

    /**
     * @param totalAbonadoDia the totalAbonadoDia to set
     */
    public void setTotalAbonadoDia(BigDecimal totalAbonadoDia) {
        this.totalAbonadoDia = totalAbonadoDia;
    }

    /**
     * @return the numeroComprobantePago
     */
    public String getNumeroComprobantePago() {
        return numeroComprobantePago;
    }

    /**
     * @param numeroComprobantePago the numeroComprobantePago to set
     */
    public void setNumeroComprobantePago(String numeroComprobantePago) {
        this.numeroComprobantePago = numeroComprobantePago;
    }

    /**
     * @return the codigoComprobantePago
     */
    public Long getCodigoComprobantePago() {
        return codigoComprobantePago;
    }

    /**
     * @param codigoComprobantePago the codigoComprobantePago to set
     */
    public void setCodigoComprobantePago(Long codigoComprobantePago) {
        this.codigoComprobantePago = codigoComprobantePago;
    }

    /**
     * @return the totalPagoFacturaCadena
     */
    public String getTotalPagoFacturaCadena() {
        return totalPagoFacturaCadena;
    }

    /**
     * @param totalPagoFacturaCadena the totalPagoFacturaCadena to set
     */
    public void setTotalPagoFacturaCadena(String totalPagoFacturaCadena) {
        this.totalPagoFacturaCadena = totalPagoFacturaCadena;
    }

    /**
     * @return the totalPago
     */
    public BigDecimal getTotalPago() {
        return totalPago;
    }

    /**
     * @param totalPago the totalPago to set
     */
    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
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
     * @return the fechaMinima
     */
    public Date getFechaMinima() {
        return fechaMinima;
    }

    /**
     * @return the contabilizaFechaPagoFactura
     */
    public boolean isContabilizaFechaPagoFactura() {
        return contabilizaFechaPagoFactura;
    }

    /**
     * @param contabilizaFechaPagoFactura the contabilizaFechaPagoFactura to set
     */
    public void setContabilizaFechaPagoFactura(boolean contabilizaFechaPagoFactura) {
        this.contabilizaFechaPagoFactura = contabilizaFechaPagoFactura;
    }

}
