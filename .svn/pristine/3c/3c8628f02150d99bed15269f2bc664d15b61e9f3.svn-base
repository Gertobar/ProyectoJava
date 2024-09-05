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
import ec.renafipse.mks.modelo.adquisiciones.Compra;
import ec.renafipse.mks.modelo.adquisiciones.CompraDetalle;
import ec.renafipse.mks.modelo.adquisiciones.DocumentosProveedor;
import ec.renafipse.mks.modelo.adquisiciones.GrupoArticulo;
import ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfip;
import ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfipPK;
import ec.renafipse.mks.modelo.adquisiciones.Proveedor;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfip;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfipPK;
import ec.renafipse.mks.modelo.adquisiciones.SustentoTributario;
import ec.renafipse.mks.modelo.adquisiciones.TipoComprobanteCompra;
import ec.renafipse.mks.modelo.contables.ComprobanteCompra;
import ec.renafipse.mks.modelo.contables.CuentaContableGruArtCom;
import ec.renafipse.mks.modelo.contables.DocumentoRetencion;
import ec.renafipse.mks.modelo.contables.ParametroContableIfip;
import ec.renafipse.mks.modelo.contables.ParametroContableIfipPK;
import ec.renafipse.mks.modelo.contables.Retencion;
import ec.renafipse.mks.modelo.contables.RetencionDetalle;
import ec.renafipse.mks.modelo.contables.RetencionDetallePK;
import ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetDet;
import ec.renafipse.mks.modelo.contables.TipoRetencion;

import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.negocio.adquisiciones.CompraDetalleFacade;
import ec.renafipse.mks.negocio.adquisiciones.CompraFacade;
import ec.renafipse.mks.negocio.adquisiciones.DocumentosProveedorFacade;
import ec.renafipse.mks.negocio.adquisiciones.GrupoArticuloFacade;
import ec.renafipse.mks.negocio.adquisiciones.ParametroCompraIfipFacade;
import ec.renafipse.mks.negocio.adquisiciones.ParametroContableIfipFacade;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorIfipFacade;
import ec.renafipse.mks.negocio.adquisiciones.SustentoTributarioFacade;
import ec.renafipse.mks.negocio.adquisiciones.TipoComprobanteCompraFacade;
import ec.renafipse.mks.negocio.contables.ComprobanteCompraFacade;
import ec.renafipse.mks.negocio.contables.CuentaContableGruArtComFacade;
import ec.renafipse.mks.negocio.contables.DocumentoRetencionFacade;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import ec.renafipse.mks.negocio.contables.RetencionDetalleFacade;
import ec.renafipse.mks.negocio.contables.RetencionFacade;
import ec.renafipse.mks.negocio.contables.TalonarioDocumentoRetDetFacade;
import ec.renafipse.mks.negocio.contables.TipoRetencionFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
//import oracle.sql.DATE;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vicastoc
 */
@ManagedBean(name = "facturaBean")
@ViewScoped
public class FacturaBean extends AbstractController<Compra> implements Serializable {

    @EJB
    private CompraFacade ejbFacadeCompras;

    @EJB
    private ProveedorIfipFacade ejbFacadeProveedorIfip;

    @EJB
    private SustentoTributarioFacade ejbFacadeSustentoTributario;

    @EJB
    private TipoComprobanteCompraFacade ejbFacadeTipoComprobanteCompra;

    @EJB
    private DocumentosProveedorFacade ejbFacadeDocumentosProveedorFacade;

    @EJB
    private GrupoArticuloFacade ejbFacadeGrupoArticulo;

    @EJB
    private CompraDetalleFacade ejbFacadeCompraDetalle;

    @EJB
    private TipoRetencionFacade ejbFacadeTipoRetencion;

    @EJB
    private DocumentoRetencionFacade ejbFacadeDocumentoRetencion;

    @EJB
    private PeriodoContableFacade ejbFacadePeriodoContable;

    @EJB
    private RetencionFacade ejbFacadeRetencion;

    @EJB
    private RetencionDetalleFacade ejbFacadeRetencionDetalle;

    @EJB
    private ComprobanteCompraFacade ejbFacadeComprobanteCompra;

    @EJB
    private ParametroCompraIfipFacade ejbFacadeParametroCompraIfip;

    @EJB
    private ParametroContableIfipFacade ejbFacadeParametroContableIfip;

    @EJB
    private TalonarioDocumentoRetDetFacade ejbFacadeTalonarioDocumentoRetDet;

    @EJB
    private CuentaContableGruArtComFacade ejbFacadeCuentaContableGruArtCom;

    private LlamaSP llamaSP;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS PANTALLA
    private String buscarPor;
    private String estadoEmitido;
    private Long criterio;
    private String nombreProevedorCompleto; /// para la pantalla principal
    private String nombreProveedorBusqueda; // para el dialogo
    private Date fechaInicio;
    private Date fechaFin;
    private String detalleArticulo;

    private Long codigoCompraCab;

    private boolean deshabilitaBuscarProveedor;
    private boolean busquedaProveedorLista;
    private String nombreProveedor;
    private Long codigoProveedor;

    private ProveedorIfip proveedorIfipSel;      //private Proveedor proveedor;
    private String mensajeCriterio;
    private String msg;
    private String mensajeDialogo;

    private int codigoCompra;
    private int opcionGeRet;
    private Long Factura;
    private Long codigoGrupoArticulo;
    private Long codigoComprasFact;
    private Long codigoCompraImpr;
    private Long codigoRetencion;
    private Long codigoComprobanteRetencion;
    private Long criterioRepresentante;
    private Long criterioRepresentanteAux;
    private Long codigoComprobanteContabilizacion;
    private Long codigoComprobanteConAux;
    private Long codigoTDRD;

    private String numeroTDRD;
    private String ciRucProveedor;
    private String comprasDireccionProveedor;
    private String numeroTelefonoProvedor;
    private String estadoRiseProveedor;
    private String estadoRetieneIvaProveedor;
    private String estadoCompra;
    private String numeroComprobanteContabilizacion;
    private String autorizacionElectronica;
    private String esFacturaElectronica;
    private Date fechaIngresoFacturAux;
    private Date fechaIngresoFactura;
    private Date fechaEmisionComprobante;
    private Date fechaCaducaComprobante;
    private Date fechaEstado;
    private Date fechaSistema;
    private Date fechaRetencion;
    private Date fechaRegistro;
    private Date fechaContabilizacion;
    private Date fechaMimina;
    private Date fechaMaxima;

    private Timestamp fechaIngresoFacturaTs;
    private Timestamp fechaEmisionComprobanteTs;
    private Timestamp fechaEstadTs;
    private Timestamp fechaSistemaTs;
    private Timestamp fechaRetencionTs;
    private Timestamp fechaRegistroTs;
    private Timestamp fechaContabilizacionTs;
    private String observaciones;
    private BigDecimal iva;
    private String serieParteUno;   //inicio de las var para el doc del proveedor
    private String serieParteDos;
    private String serieParteTres;
    private String serieParteUnoAux;
    private String serieParteDosAux;
    private String serieParteTresAux;
    private String serieFormada;
    private String serieRetencion;
    private String numeroRetencion;

    private RequestContext context;

    private boolean banderaComprobanteValido;
    private boolean banderaComprobanteValidoEd;
    private boolean banderaComboGrupo;
    private boolean banderaComboGrupoEd;
    private boolean banderaCalcula;
    private boolean banderaCalculaEd;
    private boolean banderaContRete;
    private boolean banderaAgenteRetencion;
    private boolean banderaReqRetencion;
    private boolean banderaValorSTRete;
    private boolean banderaReqRetencionDos;
    private boolean banderaDocRetExistente;
    private boolean deshabilitaBotonGuardarRetencion;
    private boolean contabilizaFechaFactura;

    private BigDecimal subtotalArticulo;
    private String gravaIvafact;
    private BigDecimal subtotalCompra;
    private BigDecimal subtotalconIvaCompra;
    private BigDecimal ivaCompra;
    private BigDecimal subtotalconIvaCeroaCompra;
    private BigDecimal descuentoCompra;
    private BigDecimal totalCompra;
    private BigDecimal totalAbono;
    private BigDecimal totalSaldo;
    private BigDecimal totalRetenido;
    private BigDecimal totalSumaRetenido;
    private BigDecimal totalCompraContabilizada;
    private BigDecimal baseImponible;
    private BigDecimal subtotalDetalleCompra;
    private BigDecimal ivaDetalleCompra;
    private BigDecimal totalDetalleCompra;
    private BigDecimal porcentajeRetencion;
    private BigDecimal porcantajeIva;

    private Compra compraSel;
    private ProveedorIfip provedorIfip;
    private TipoComprobanteCompra ubicaTipoComprobanteCompra;
    private SustentoTributario sustentoTributario;
    private GrupoArticulo grupoArticulo;
    private CompraDetalle compraDetalleFact;
    private CompraDetalle compraDetalleSel;
    private Compra compraFact;
    private Proveedor proveedor;
    private TipoRetencion tipoRetencion;
    private Retencion retencion;
    private RetencionDetalle retencionDetalle;
    private RetencionDetalle retencionDetalleSel;
    private GeneraReporte generaReporte;
    private CompraDetalle compraDetalle;

    //parametros para hacer manipulable la tbala
    private List<ProveedorIfip> itemsProveedores;
    private List<ProveedorIfip> itemsProveedoresDatos;
    private List<Solicitud> itemsSolicitud;
    private List<Compra> itemsCompras;     //para listar segun el filtrado de la busqueda
    private List<Compra> itemsComprasAll; // para listar todas las compras sin nigun tipo de filtrado
    private List<CompraDetalle> itemsCompraDetalleSel; // se guarda toda la infromacion de la compra detalle que se va a efectuar
    private List<TipoComprobanteCompra> itemsTipoComprobanteCompras;
    private List<SustentoTributario> itemsSustTributario;
    private List<DocumentosProveedor> itemsDocumentosProveedor;
    private List<GrupoArticulo> itemsGrupoArticulo;
    private List<CuentaContableGruArtCom> itemsCuentaGrupoArticulo;
    private List<TipoRetencion> itemsTipoRetencions;
    private List<RetencionDetalle> itemsRetencionDetalles;
    private DocumentosProveedor documentosProveedor;/////// ingreso de datos en la lista de los items de los detalles de la retencion

    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public FacturaBean() {
        super(Compra.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacadeCompras);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            this.buscarPor = "P";
            this.cambiaCriterio();
            contabilizaFechaFactura = false;

            this.fechaMaxima = new Date();
            ParametroCompraIfip pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(2, ActivacionUsuario.getCodigoIfip()));
            if (pci != null) {
                this.fechaMimina = this.agregaDias(new Date(), Long.parseLong(pci.getValor().toString()) * -1);
            } else {
                this.fechaMimina = new Date();
            }

            pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(1, ActivacionUsuario.getCodigoIfip()));
            if (pci != null) {
                this.contabilizaFechaFactura = (pci.getValor().equals("S"));
            }

            //Instancion el proceso de llama Store Procedure
            llamaSP = new LlamaSP();

            this.setEstadoCompra("INGRESADA");
            fechaIngresoFactura = new Date();
            fechaEmisionComprobante = new Date();
            fechaEstado = new Date();
            fechaRetencion = new Date();
            fechaRegistro = new Date();
            fechaSistema = new Date();
            fechaContabilizacion = new Date();

            banderaComprobanteValido = false;
            setBanderaValorSTRete(false);
            setBanderaContRete(false);
            setBanderaComboGrupo(false);
            setBanderaAgenteRetencion(false);
            setBanderaReqRetencion(false);
            setBanderaReqRetencionDos(false);
            setBanderaDocRetExistente(false);
            this.serieParteUno = null;
            this.serieParteDos = null;
            this.serieParteTres = null;
            this.setSubtotalArticulo(new BigDecimal("0"));

            this.setSubtotalCompra(new BigDecimal("0"));
            this.setSubtotalconIvaCeroaCompra(new BigDecimal("0"));
            this.setSubtotalconIvaCompra(new BigDecimal("0"));
            this.setIvaCompra(new BigDecimal("0"));
            this.setDescuentoCompra(new BigDecimal("0"));
            this.setTotalCompra(new BigDecimal("0"));

            this.setFechaMaxima(new Date());

            //this.setFechaMimina(this.fecha("01/01/" + ActivacionUsuario.getCodigoPeriodo()));
            this.setFechaMaxima(new Date());
//            if (Integer.parseInt(ActivacionUsuario.getCodigoPeriodo()) == new Date().getYear() + 1900) {
//                this.setFechaMaxima(new Date());
//            } else {
//                this.setFechaMaxima(this.fecha("31/12/" + ActivacionUsuario.getCodigoPeriodo()));
//            }

            this.setFechaMimina(this.getFechaMaxima());
            // Colocando Fecha Mimina

            ParametroContableIfip pti = this.ejbFacadeParametroContableIfip.find(new ParametroContableIfipPK(2, ActivacionUsuario.getCodigoIfip()));
            if (pti != null) {
                this.setFechaMimina(this.agregaDias(this.getFechaMaxima(), Long.parseLong(pti.getValor().toString()) * -1));
            }

            // Busncando el porcentaje del IVA
            pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(5, ActivacionUsuario.getCodigoIfip()));
            //System.out.println("Pasa x aki...");
            if (pci != null) {
                this.porcantajeIva = new BigDecimal(pci.getValor());
            } else {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoEstaDefinidoPorcentajeIva"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            //System.out.println("IVA..." + this.porcantajeIva);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FacturaBean", "init", CapturaError.getErrorException(ex)});
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlIniciarBean") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaSoloNumeros"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex1) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FacturaBean", "init", CapturaError.getErrorException(ex1)});
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FacturaBean", "init", CapturaError.getErrorException(ex)});
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorAlIniciarBean"));
            try {
                Sesion.redireccionaPagina(url);
            } catch (IOException ex1) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"FacturaBean", "init", CapturaError.getErrorException(ex1)});
            }
        }

    }

    // *******************************************************************************************
    // --------------------------------------------------------------------------
    // -- LOGICA
    /**
     * Al cambiar el criterio de Consulta Por Proveedor o fechas
     */
    public void cambiaCriterio() {
        this.setItemsSolicitud(null);
        this.setNombreProevedorCompleto(null);
        this.setCriterio(null);
        this.deshabilitaBuscarProveedor = true;

        if (this.buscarPor.equals("P")) {
            this.deshabilitaBuscarProveedor = false;

        }
    }

    public void obtieneProveedorDatos() {
        if (getCriterioRepresentante() != null) {
            try {
                if (getCriterioRepresentante() > 0) {
                    this.setItemsProveedoresDatos(ejbFacadeProveedorIfip.getItemsCodigoProveedorIfipEliminado(getCriterioRepresentante(), ActivacionUsuario.getCodigoIfip(), 'N'));
                    if (!this.itemsProveedoresDatos.isEmpty()) {
                        if (this.getItemsProveedoresDatos().size() == 1) {

                            proveedor = this.getItemsProveedoresDatos().get(0).getProveedor();
                            this.criterioRepresentante = this.getItemsProveedoresDatos().get(0).getProveedor().getCodigo();
                            this.nombreProevedorCompleto = proveedor.getPersona().getNombreCompleto();
                            this.ciRucProveedor = proveedor.getPersona().getIdentificacion();
                            this.comprasDireccionProveedor = proveedor.getPersona().getCorreoEletronico();
                            this.estadoRetieneIvaProveedor = "" + proveedor.getRetieneIva();
                            this.estadoRiseProveedor = "" + proveedor.getTieneRise();
                            //System.out.println("E L CODIGO Q LLEGA A INGRESARSE...... " + criterioRepresentante);
                        }
                        if (this.getItemsProveedoresDatos().size() > 1) {
                            //System.err.println("el proveedor no puede tener el mismo codigo");
                        }
                    } else {
                        ///MensajeProveedorInexistente
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente");
                        msg = msg + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip();
                        MuestraMensaje.addAdvertencia(msg);
                        limpioCampos();
                    }
                } else {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCampoCantidadZero");
                    MuestraMensaje.addAdvertencia(msg);
                    //System.out.println("cantidad mayo a cero");
                    limpioCampos();
                }

            } catch (NumberFormatException ex) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaSoloNumeros");
                MuestraMensaje.addAdvertencia(msg);
                limpioCampos();
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor");
            MuestraMensaje.addAdvertencia(msg);
            limpioCampos();
        }

    }

    public void limpioCampos() {

        this.criterioRepresentante = null;
        this.nombreProevedorCompleto = null;
        this.ciRucProveedor = null;
        this.comprasDireccionProveedor = null;
        this.estadoRetieneIvaProveedor = null;
        this.estadoRiseProveedor = null;

    }

    public void verificoDatos() {

        if (getCriterioRepresentante() != null) {
            if (getUbicaTipoComprobanteCompra() != null) {

            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccionComprobante");
                MuestraMensaje.addAdvertencia(msg);
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    public void verificoDatosComprobanteExitentes() {
         this.setItemsCuentaGrupoArticulo(ejbFacadeCuentaContableGruArtCom.getItemsGruArtElimiando('N'));
        banderaComprobanteValido = false;
        this.msg = null;
        try {
            Integer.parseInt(this.serieParteTres);
        } catch (NumberFormatException e) {
            this.serieParteTres = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
            MuestraMensaje.addError(msg);
            return;
        }
        if (getCriterioRepresentante() != null) {

            if (getUbicaTipoComprobanteCompra() != null) {
                if (this.ubicaTipoComprobanteCompra.getEsLiquidacion() == 'N' && this.esFacturaElectronica.equals("N")) {

                    if (((getSerieParteUno() != null) && (validaNumero(getSerieParteUno()))) && ((getSerieParteDos() != null) && (validaNumero(getSerieParteDos()))) && ((getSerieParteTres() != null) && (validaNumero(getSerieParteTres())))) {

                        this.setItemsDocumentosProveedor(ejbFacadeDocumentosProveedorFacade.getItemsDatosProveedorComprobante(this.criterioRepresentante, ubicaTipoComprobanteCompra.getCodigo(), this.serieParteUno.trim() + "-" + serieParteDos.trim(), Long.valueOf(serieParteTres)));
                        if (this.itemsDocumentosProveedor.size() == 1) {
                            //setFechaCaducaComprobante(itemsDocumentosProveedor.get(0).getFechaCaduca());
                            documentosProveedor = itemsDocumentosProveedor.get(0);

                            //serieFormada = serieParteTres;
                            serieFormada = serieParteUno + serieParteDos + ConvierteDato.llenaCerosIzquierda(itemsDocumentosProveedor.get(0).getDigitosComprobante(), Integer.parseInt(serieParteTres));

                            List<Compra> listaCompra = this.ejbFacadeCompras.getItemsComprobanteTipoIfipNumeroProve(this.ubicaTipoComprobanteCompra.getCodigo(), ActivacionUsuario.getCodigoIfip(), serieFormada, this.getCriterioRepresentante());

                            if (!listaCompra.isEmpty()) {
                                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaExistente");
                                MuestraMensaje.addAdvertencia(msg);
                            } else {
                                this.serieParteTres = ConvierteDato.llenaCerosIzquierda(itemsDocumentosProveedor.get(0).getDigitosComprobante(), Integer.parseInt(serieParteTres));
                                banderaComprobanteValido = true;
                                this.autorizacionElectronica = this.itemsDocumentosProveedor.get(0).getNumeroAutorizacion();
                            }

                        } else {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FacturaNoExisteEnDocumentosProveedor");
                            MuestraMensaje.addAdvertencia(msg);
                        }
                    } else {

                        if ((getSerieParteUno() == null) || (getSerieParteDos() == null) || (getSerieParteTres() == null)) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroSerieUnoVacio");
                            MuestraMensaje.addAdvertencia(msg);
                        }
                        //MensajeNumeroSerieExistente
                        if (!validaNumero(getSerieParteUno()) || !validaNumero(getSerieParteDos()) || !validaNumero(getSerieParteTres())) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroSerieExistente");
                            MuestraMensaje.addAdvertencia(msg);
                        }
                    }
                } else {

                    if (((getSerieParteUno() != null) && (validaNumero(getSerieParteUno()))) && ((getSerieParteDos() != null) && (validaNumero(getSerieParteDos()))) && ((getSerieParteTres() != null) && (validaNumero(getSerieParteTres())))) {
                        serieFormada = serieParteUno.trim() + serieParteDos.trim() + serieParteTres.trim();
                        List<Compra> listaCompra = this.ejbFacadeCompras.getItemsComprobanteTipoIfipNumeroProve(this.ubicaTipoComprobanteCompra.getCodigo(), ActivacionUsuario.getCodigoIfip(), serieFormada, this.getCriterioRepresentante());

                        if (!listaCompra.isEmpty()) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaExistente");
                            MuestraMensaje.addAdvertencia(msg);
                        } else {
                            banderaComprobanteValido = true;
                        }

                    } else {
                        if ((getSerieParteUno() == null) || (getSerieParteDos() == null) || (getSerieParteTres() == null)) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroSerieUnoVacio");
                            MuestraMensaje.addAdvertencia(msg);
                        }
                        //MensajeNumeroSerieExistente
                        if (!validaNumero(getSerieParteUno()) || !validaNumero(getSerieParteDos()) || !validaNumero(getSerieParteTres())) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroSerieExistente");
                            MuestraMensaje.addAdvertencia(msg);
                        }
                    }

                }

            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccionComprobante");
                MuestraMensaje.addAdvertencia(msg);
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    public void validaFechaFactura() {
        this.setItemsCuentaGrupoArticulo(ejbFacadeCuentaContableGruArtCom.getItemsGruArtElimiando('N'));
       
        //System.out.println("getFechaIngresoFactura " + getFechaIngresoFactura() + " " + itemsDocumentosProveedor.get(0).getFechaCaduca());
        this.verificoDatosComprobanteExitentes();
        if (banderaComprobanteValido && this.esFacturaElectronica.equals("N")) {
            if (!Validaciones.validaEntreFechasMenorIgual(this.getFechaIngresoFactura(), documentosProveedor.getFechaCaduca())) {
                banderaComprobanteValido = false;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturasCaducadas");
                MuestraMensaje.addAdvertencia(msg);
            } else {
                banderaComprobanteValido = true;
            }
        }

    }


    /*
     Busco compras
     */
    public void buscaCompras() {
        if (this.buscarPor.equals("P")) {
            if (this.getCriterio() != null) {

                ProveedorIfip pi = this.ejbFacadeProveedorIfip.find(new ProveedorIfipPK(this.getCriterio(), ActivacionUsuario.getCodigoIfip()));
                if (pi != null) {
                    if (pi.getEliminado() == 'N') {
                        this.nombreProevedorCompleto = pi.getProveedor().getPersona().getNombreCompleto();
                        this.itemsCompras = ejbFacadeCompras.getItemsComprasCodigoProveedorIngresadaRetenida(ActivacionUsuario.getCodigoIfip(), this.criterio, 'I', 'R');
                        this.compraSel = null;
                        if (itemsCompras.isEmpty()) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeComprasInexistentesFecha");
                            MuestraMensaje.addAdvertencia(msg);
                            this.itemsCompras = null;
                        }
                    } else {
                        this.compraSel = null;
                        this.itemsCompras = null;
                    }
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente");
                MuestraMensaje.addAdvertencia(msg);
                this.compraSel = null;
                this.itemsCompras = null;
            }

        } else if (this.buscarPor.equals("F")) {
            if (this.fechaInicio != null && this.fechaFin != null) {

                this.itemsCompras = ejbFacadeCompras.getItemsCompraFecha(ActivacionUsuario.getCodigoIfip(), this.fechaInicio, this.fechaFin, 'I', 'R');
                this.compraSel = null;
                if (itemsCompras.isEmpty()) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeComprasInexistentesFecha");
                    MuestraMensaje.addAdvertencia(msg);
                    this.itemsCompras = null;
                    this.compraSel = null;
                }

            } else {
                this.compraSel = null;
                this.itemsCompras = null;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneFecha");
                MuestraMensaje.addAdvertencia(msg);
            }

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
            this.setItemsProveedores(ejbFacadeProveedorIfip.getItemsNombresProveedorIfipEliminado(getNombreProveedorBusqueda(), ActivacionUsuario.getCodigoIfip(), 'N'));
        }
    }

    public void seleccionaProveedor(ActionEvent avc) {

        if (proveedorIfipSel != null) {
            this.criterio = proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();

            this.nombreProevedorCompleto = proveedorIfipSel.getProveedor().getPersona().getNombreCompleto();
            this.itemsCompras = this.ejbFacadeCompras.getItemsCompraCodigoProveedor(ActivacionUsuario.getCodigoIfip(), proveedorIfipSel.getProveedor().getCodigo());
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    public void seleccionaProveedorDetalle(ActionEvent avc) {

        if (proveedorIfipSel != null) {
            this.criterioRepresentante = proveedorIfipSel.getProveedorIfipPK().getCodigoProveedor();
            this.nombreProevedorCompleto = proveedorIfipSel.getProveedor().getPersona().getNombreCompleto();
            this.ciRucProveedor = proveedorIfipSel.getProveedor().getPersona().getIdentificacion();
            this.comprasDireccionProveedor = proveedorIfipSel.getProveedor().getPersona().getCorreoEletronico();
            this.estadoRiseProveedor = "" + proveedorIfipSel.getProveedor().getTieneRise();
            proveedor = proveedorIfipSel.getProveedor();
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneProveedor");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    /**
     ** PREPARA LA BUSQUEDA DE LOS DATOS Q PERMITA N REALIZAR LA EDICION DE LA
     * FACTURA
     *
     * @param actionEvent
     * @throws IOException
     */
    public void preparaEdicionFactura(ActionEvent actionEvent) throws IOException {
        this.msg = null;

        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");

        if (String.valueOf(compraSel.getEstado()).equals("R")) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaRetenida");
        } else if (String.valueOf(compraSel.getEstado()).equals("N")) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaContabilizada");
        } else if (String.valueOf(compraSel.getEstado()).equals("C")) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCancelada");
        } else if (ejbFacadeRetencion.getItemsRetencionesfindByEstadoCodigoCompra('E', compraSel.getCodigo()).size() > 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaProcesoRetencion");
        }

        if (this.msg != null) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            cargoDatosEdicion();
            context.execute("procesandoDlg.hide()");
            context.execute("FacturaEditaDialog.show()");
        }

    }

    public void cargoDatosEdicion() {
        this.banderaCalculaEd = true;
        this.banderaComboGrupoEd = true;
        this.banderaComprobanteValidoEd = true;
        this.criterioRepresentante = compraSel.getCodigoProveedor().getCodigo();
        this.nombreProevedorCompleto = compraSel.getCodigoProveedor().getPersona().getNombreCompleto();
        this.ciRucProveedor = compraSel.getCodigoProveedor().getPersona().getIdentificacion();
        this.comprasDireccionProveedor = compraSel.getCodigoProveedor().getPersona().getCorreoEletronico();
        this.estadoRiseProveedor = String.valueOf(compraSel.getCodigoProveedor().getTieneRise());
        this.estadoRetieneIvaProveedor = String.valueOf(compraSel.getCodigoProveedor().getRetieneIva());
        this.criterioRepresentanteAux = compraSel.getCodigoProveedor().getCodigo();
        this.ivaCompra = compraSel.getIva();
        this.autorizacionElectronica = this.compraSel.getAutorizacionElectronica();
        this.esFacturaElectronica = String.valueOf(this.compraSel.getEsFacruraElectronica());
        this.proveedor = compraSel.getCodigoProveedor();

        this.setItemsSustTributario(ejbFacadeSustentoTributario.getItemsSustentoTribEliminado('N'));
        this.sustentoTributario = compraSel.getCodigoSustento();

        this.setItemsGrupoArticulo(ejbFacadeGrupoArticulo.getItemsElimiando('N'));
        this.cambiaCodigoSustento();
        this.ubicaTipoComprobanteCompra = compraSel.getCodigoTipoComprobante();

        this.setCodigoCompra(compraSel.getCodigo().intValue());
        //this.serieParteUno
        String cadenaComprobante = "" + compraSel.getNumeroComprobante();
        this.fechaIngresoFactura = compraSel.getFechaIngreso();

        this.serieParteUno = cadenaComprobante.substring(0, 3) + "";
        this.serieParteDos = cadenaComprobante.substring(3, 6) + "";
        this.serieParteTres = cadenaComprobante.substring(6, cadenaComprobante.length()) + "";
        this.serieParteUnoAux = cadenaComprobante.substring(0, 3) + "";
        this.serieParteDosAux = cadenaComprobante.substring(3, 6) + "";
        this.serieParteTresAux = cadenaComprobante.substring(6, cadenaComprobante.length()) + "";

        this.subtotalCompra = compraSel.getSubtotal();
        this.subtotalconIvaCeroaCompra = compraSel.getSubtotalIvaCero();
        this.subtotalconIvaCompra = compraSel.getSubtotalConIva();
        this.descuentoCompra = compraSel.getDescuento();
        this.totalCompra = compraSel.getTotal();
        this.observaciones = compraSel.getObservaciones();
        this.compraDetalleSel = null;

        this.compraFact = (compraSel);

        this.setItemsCompraDetalleSel(ejbFacadeCompraDetalle.getItemsAllfindByCodigoCompra(compraSel.getCodigo()));
        banderaComprobanteValido = true;
    }

    /**
     ** PREPARA LA BUSQUEDA DE LOS SOCIOS PARA CONSULTAR LOS CREDITOS.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void preparaFactura(ActionEvent actionEvent) throws IOException {

        this.setItemsComprasAll(null);

        this.autorizacionElectronica = null;
        this.nombreProveedorBusqueda = null;
        this.criterioRepresentante = null;
        this.setItemsProveedores(null);
        this.setCriterio(null);
        this.busquedaProveedorLista = true;
        this.ciRucProveedor = null;
        this.comprasDireccionProveedor = null;
        this.numeroTelefonoProvedor = null;
        this.estadoRiseProveedor = null;
        this.estadoCompra = "INGRESADA";
        this.observaciones = null;
        this.banderaComprobanteValido = false;
        this.serieParteUno = null;
        this.serieParteDos = null;
        this.serieParteTres = null;
        this.banderaComboGrupo = false;
        this.banderaCalcula = false;
        this.subtotalArticulo = null;
        this.detalleArticulo = null;
        this.sustentoTributario = null;
        this.gravaIvafact = null;
        this.nombreProevedorCompleto = null;
        this.estadoRiseProveedor = null;
        this.estadoRetieneIvaProveedor = null;
        this.ubicaTipoComprobanteCompra = null;
        this.fechaIngresoFactura = null;
        this.sustentoTributario = null;
        this.subtotalCompra = null;
        this.subtotalconIvaCeroaCompra = null;
        this.subtotalconIvaCompra = null;
        this.descuentoCompra = null;
        this.ivaCompra = null;
        this.totalCompra = null;
        this.compraDetalleSel = null;
        //this.itemsTipoComprobanteCompras = ejbFacadeTipoComprobanteCompra.getItemsTipoComprobanteEliminado('N');
        this.itemsTipoComprobanteCompras = null;
        this.setItemsSustTributario(ejbFacadeSustentoTributario.getItemsSustentoTribEliminado('N'));
        this.setItemsCuentaGrupoArticulo(ejbFacadeCuentaContableGruArtCom.getItemsGruArtElimiando('N'));//this.setItemsGrupoArticulo(ejbFacadeGrupoArticulo.getItemsElimiando('N'));
        this.setItemsTipoRetencions(ejbFacadeTipoRetencion.getItemsEliminado('N'));
        //this.setCompraDetalleFact(new CompraDetalle());
        this.setCompraFact(new Compra());
        this.setGrupoArticulo(new GrupoArticulo());
        this.setItemsCompraDetalleSel(new ArrayList<CompraDetalle>());
        this.descuentoCompra = new BigDecimal("0.00");
        this.criterioRepresentante = null;
        this.sustentoTributario = null;
        this.esFacturaElectronica = null;
        this.ubicaTipoComprobanteCompra = null;
        this.serieParteUno = null;
        this.serieParteDos = null;
        this.serieParteTres = null;
        this.autorizacionElectronica = null;
        this.grupoArticulo = null;
        this.detalleArticulo = null;
        this.gravaIvafact = null;
        this.subtotalArticulo = null;
        this.esFacturaElectronica = null;

        this.fechaMaxima = new Date();
        this.contabilizaFechaFactura = false;

        ParametroCompraIfip pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(2, ActivacionUsuario.getCodigoIfip()));
        if (pci != null) {
            this.fechaMimina = this.agregaDias(new Date(), Long.parseLong(pci.getValor().toString()) * -1);
        } else {
            this.fechaMimina = new Date();
        }

        pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(1, ActivacionUsuario.getCodigoIfip()));
        if (pci != null) {
            this.contabilizaFechaFactura = (pci.getValor().equals("S"));
        }

    }

    public void retiene(ActionEvent actionEvent) throws IOException {
        codigoTDRD = null;
        numeroTDRD = null;
        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");
        this.setItemsTipoRetencions(ejbFacadeTipoRetencion.getItemsEliminado('N'));
        //System.out.println(" setItemsTipoRetencions " + itemsTipoRetencions);
        this.msg = null;
        if (compraSel.getCodigoProveedor().getTieneRise() == 'S') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProveedorNoEsAgenteDeRetencion");
        } else if (compraSel.getCodigoProveedor().getAgenteRetencion() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProveedorNoEsAgenteDeRetencion");
        } else if (String.valueOf(compraSel.getEstado()).equals("N")) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaContabilizada");
        } else if (String.valueOf(compraSel.getEstado()).equals("C")) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FacturaCancelada");
        }
        if (this.msg != null) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(msg);

        } else {

            this.cargoDatosRetencion();
            context.execute("procesandoDlg.hide()");
            context.execute("RetencionDialog.show()");

        }

        ////System.out.println(" this.banderaContRete " + this.banderaContRete);
    }

    public void cargoDatosRetencion() {
        this.msg = null;
        this.deshabilitaBotonGuardarRetencion = false;
        this.codigoRetencion = null;
        this.banderaValorSTRete = false;
        this.banderaCalculaEd = true;
        this.banderaComboGrupoEd = true;
        this.banderaComprobanteValidoEd = true;
        this.criterioRepresentante = compraSel.getCodigoProveedor().getCodigo();
        this.nombreProevedorCompleto = compraSel.getCodigoProveedor().getPersona().getNombreCompleto();
        this.ciRucProveedor = compraSel.getCodigoProveedor().getPersona().getIdentificacion();
        this.comprasDireccionProveedor = compraSel.getCodigoProveedor().getPersona().getCorreoEletronico();
        this.estadoRiseProveedor = String.valueOf(compraSel.getCodigoProveedor().getTieneRise());
        this.estadoRetieneIvaProveedor = String.valueOf(compraSel.getCodigoProveedor().getRetieneIva());
        this.criterioRepresentanteAux = compraSel.getCodigoProveedor().getCodigo();
        compraDetalle = null;

        this.setCodigoCompra(compraSel.getCodigo().intValue());
        this.ubicaTipoComprobanteCompra = compraSel.getCodigoTipoComprobante();
        this.sustentoTributario = compraSel.getCodigoSustento();

        //this.serieParteUno
        String cadenaComprobante = "" + compraSel.getNumeroComprobante();
        this.fechaIngresoFactura = compraSel.getFechaIngreso();

        this.serieParteUno = cadenaComprobante.substring(0, 3) + "";
        this.serieParteDos = cadenaComprobante.substring(3, 6) + "";
        this.serieParteTres = cadenaComprobante.substring(6, cadenaComprobante.length()) + "";
        this.serieParteUnoAux = cadenaComprobante.substring(0, 3) + "";
        this.serieParteDosAux = cadenaComprobante.substring(3, 6) + "";
        this.serieParteTresAux = cadenaComprobante.substring(6, cadenaComprobante.length()) + "";

        this.subtotalCompra = compraSel.getSubtotal();
        this.subtotalconIvaCeroaCompra = compraSel.getSubtotalIvaCero();
        this.subtotalconIvaCompra = compraSel.getSubtotalConIva();
        this.descuentoCompra = compraSel.getDescuento();
        this.totalCompra = compraSel.getTotal();
        this.observaciones = compraSel.getObservaciones();
        this.setItemsCompraDetalleSel(ejbFacadeCompraDetalle.getItemsAllfindByCodigoCompra(compraSel.getCodigo()));

        this.compraDetalleSel = null;
        this.compraFact = (compraSel);
        this.totalRetenido = new BigDecimal("0.00");
        this.ivaDetalleCompra = null;
        this.subtotalDetalleCompra = null;
        this.totalDetalleCompra = null;

        List<Retencion> listaRetencion = this.ejbFacadeRetencion.getItemsRetencionesfindByCodigoCompra(this.compraSel.getCodigo());
        if (listaRetencion.isEmpty()) {
            this.opcionGeRet = 1;
            this.retencion = new Retencion();
            this.totalSumaRetenido = new BigDecimal("0.00");
            this.setItemsRetencionDetalles(new ArrayList<RetencionDetalle>());
        } else {

            this.opcionGeRet = 2;
            this.setRetencion(listaRetencion.get(0));
            codigoRetencion = this.retencion.getCodigo();
            this.totalSumaRetenido = this.retencion.getTotalRetenido();
            this.itemsRetencionDetalles = this.ejbFacadeRetencionDetalle.getItemsRetencionesfindByCodigoRetencion(this.retencion.getCodigo());
            if (this.itemsRetencionDetalles.isEmpty()) {
                this.setItemsRetencionDetalles(new ArrayList<RetencionDetalle>());
            }

            if (this.retencion.getEstado() == 'I') {
                this.setDeshabilitaBotonGuardarRetencion(true);

                List<DocumentoRetencion> listaDocumentoRetencion = this.ejbFacadeDocumentoRetencion.getItemsDocumentoRetencionEstado(codigoRetencion, 'A');
                if (!listaDocumentoRetencion.isEmpty()) {
                    if (listaDocumentoRetencion.size() == 1) {
                        this.serieRetencion = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getTalonarioDocumentoRetencion().getSerie();
                        this.numeroRetencion = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getNumero();
                        this.codigoTDRD = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getCodigo();
                        this.numeroTDRD = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getNumero();
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnDocumentoRetencion");
                    }
                } else {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteDocumentoRetencion");
                }

            }
        }

        if (this.msg != null) {
            MuestraMensaje.addError(msg);
        }

        //siguienteDocumentoExistente();
    }

    public void cargoDatosRetencionEdicion() {

        this.banderaValorSTRete = false;
        this.banderaCalculaEd = true;
        this.banderaComboGrupoEd = true;
        this.banderaComprobanteValidoEd = true;
        this.criterioRepresentante = compraSel.getCodigoProveedor().getCodigo();
        this.nombreProevedorCompleto = compraSel.getCodigoProveedor().getPersona().getNombreCompleto();
        this.ciRucProveedor = compraSel.getCodigoProveedor().getPersona().getIdentificacion();
        this.comprasDireccionProveedor = compraSel.getCodigoProveedor().getPersona().getCorreoEletronico();
        this.estadoRiseProveedor = String.valueOf(compraSel.getCodigoProveedor().getTieneRise());
        this.estadoRetieneIvaProveedor = String.valueOf(compraSel.getCodigoProveedor().getRetieneIva());
        this.criterioRepresentanteAux = compraSel.getCodigoProveedor().getCodigo();

        this.setCodigoCompra(compraSel.getCodigo().intValue());
        this.ubicaTipoComprobanteCompra = compraSel.getCodigoTipoComprobante();
        this.sustentoTributario = compraSel.getCodigoSustento();

        //this.serieParteUno        
        String cadenaComprobante = "" + compraSel.getNumeroComprobante();
        this.fechaIngresoFactura = compraSel.getFechaIngreso();

        this.serieParteUno = cadenaComprobante.substring(0, 3) + "";
        this.serieParteDos = cadenaComprobante.substring(3, 6) + "";
        this.serieParteTres = cadenaComprobante.substring(6, cadenaComprobante.length()) + "";
        this.serieParteUnoAux = cadenaComprobante.substring(0, 3) + "";
        this.serieParteDosAux = cadenaComprobante.substring(3, 6) + "";
        this.serieParteTresAux = cadenaComprobante.substring(6, cadenaComprobante.length()) + "";

        this.subtotalCompra = compraSel.getSubtotal();
        this.subtotalconIvaCeroaCompra = compraSel.getSubtotalIvaCero();
        this.subtotalconIvaCompra = compraSel.getSubtotalConIva();
        this.descuentoCompra = compraSel.getDescuento();
        this.totalCompra = compraSel.getTotal();
        this.observaciones = compraSel.getObservaciones();
        this.compraDetalleSel = null;

        this.compraFact = (compraSel);
        this.setRetencion(new Retencion());
        this.setItemsCompraDetalleSel(ejbFacadeCompraDetalle.getItemsAllfindByCodigoCompra(compraSel.getCodigo()));

        this.retencion = ejbFacadeRetencion.getItemsRetencionesfindByCodigoCompra(compraSel.getCodigo()).get(0);
        this.totalRetenido = new BigDecimal("0.00");
        this.totalSumaRetenido = retencion.getTotalRetenido();
        this.setItemsRetencionDetalles(ejbFacadeRetencionDetalle.getItemsRetencionesfindByCodigoRetencion(retencion.getCodigo()));

    }

    public boolean validaNumero(String cadena) {
        Pattern pat = Pattern.compile("0+[1-9][0-9]*|[1-9]|^[1-9]([0-9]+$)");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public void cambioCriterioComboGrupo() {
        banderaComboGrupo = true;
    }

    public void contabilza(ActionEvent actionEvent) {

        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");
        this.msg = null;
        if (String.valueOf(compraSel.getEstado()).equals("N")) {
            List<ComprobanteCompra> listaComprobanteCompra = this.ejbFacadeComprobanteCompra.getItemsComprobantesComprafindByCodigoCompra(this.compraSel.getCodigo());
            if (!listaComprobanteCompra.isEmpty()) {
                this.codigoComprobanteContabilizacion = listaComprobanteCompra.get(0).getCodigoComprobante();
                this.numeroComprobanteContabilizacion = listaComprobanteCompra.get(0).getComprobante().getNumeroComprobante();
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaContabilizada");
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteContableDialogo.show()");
            } else {
                context.execute("procesandoDlg.hide()");
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaContabilizada") + "\n" + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteComprobante");
                MuestraMensaje.addError(msg);
            }

        } else if (String.valueOf(compraSel.getEstado()).equals("C")) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FacturaCancelada");
            MuestraMensaje.addError(msg);
            context.execute("procesandoDlg.hide()");
        } else if (compraSel.getEstado() == 'I' || compraSel.getEstado() == 'R') {
            guardoContabilizacion();
            context.execute("procesandoDlg.hide()");
            if (this.numeroComprobanteContabilizacion != null) {
                context.execute("procesandoDlg.hide()");
            }
        }
        buscaCompras();
    }

    /**
     * < *
     */
    public void agregaDetalleCompra() {

        if (!banderaComprobanteValido) {
            MuestraMensaje.addError(msg);
            return;
        }
        ParametroCompraIfip pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(5, ActivacionUsuario.getCodigoIfip()));
        //System.out.println("IVA..." + this.porcantajeIva);
        if (pci != null) {
            this.porcantajeIva = new BigDecimal(pci.getValor());
        }
        //MensajeFacturaCamposVaciosDetCab
        if (grupoArticulo != null && detalleArticulo != null && (getGravaIvafact().equals("S") || getGravaIvafact().equals("N")) && getSubtotalArticulo() != null) {

            try {
                if (getSubtotalArticulo().compareTo(BigDecimal.ZERO) > 0) {

                    if (banderaComprobanteValido) {
                        if (itemsCompraDetalleSel == null) {
                            itemsCompraDetalleSel = new ArrayList<CompraDetalle>();
                        }
                        DecimalFormat dFormat = new DecimalFormat("##.##");
                        dFormat.setRoundingMode(RoundingMode.HALF_EVEN);
                        compraDetalleFact = new CompraDetalle();
                        compraDetalleFact.setCodigo(Long.parseLong(String.valueOf(this.itemsCompraDetalleSel.size() + 1)));
                        compraDetalleFact.setCodigoArticulo(grupoArticulo);
                        compraDetalleFact.setDetalleArticulo(detalleArticulo);
                        compraDetalleFact.setSubtotal(getSubtotalArticulo());
                        compraDetalleFact.setGravaIva(getGravaIvafact().charAt(0));
                        compraDetalleFact.setPorcentajeIva(getGravaIvafact().equals("S") ? porcantajeIva : new BigDecimal("0.00"));
                        System.out.println("format: "+dFormat.format(compraDetalleFact.getSubtotal().multiply(compraDetalleFact.getPorcentajeIva()).divide(new BigDecimal("100"))));
                        compraDetalleFact.setIva(new BigDecimal(dFormat.format(compraDetalleFact.getSubtotal().multiply(compraDetalleFact.getPorcentajeIva()).divide(new BigDecimal("100")))));
                        compraDetalleFact.setTotal(compraDetalleFact.getSubtotal().add(compraDetalleFact.getIva()));
                        this.itemsCompraDetalleSel.add(compraDetalleFact);
                        realizaCalculos();
                        this.grupoArticulo = null;
                        this.detalleArticulo = null;
                        this.gravaIvafact = null;
                        this.subtotalArticulo = null;
                    } else {

                    }

                } else {
                    //MensajeFacturaCampoCantidadZero
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCampoCantidadZero");
                    MuestraMensaje.addAdvertencia(msg);
                }
            } catch (NumberFormatException ex) {

                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCampoCantidadSubtotal");
                MuestraMensaje.addAdvertencia(msg);
            }

        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCamposVaciosDetCab");
            MuestraMensaje.addAdvertencia(msg);
            //System.out.println("vacio");
        }
    }

    /**
     *
     */
    public void modificaDetalle() {
        realizaCalculos();
    }

    /**
     *
     */
    public void eliminaDetalleCompra() {

        if (this.itemsCompraDetalleSel.size() > 0) {
            this.itemsCompraDetalleSel.remove(this.compraDetalleSel);
            this.realizaCalculos();
        } else {
            banderaCalcula = false;
        }
    }

    public void guardoElementosCabecera() {

        compraFact.setCodigoProveedor(proveedor);
        compraFact.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
        compraFact.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
        compraFact.setCodigoTipoComprobante(ubicaTipoComprobanteCompra);
        compraFact.setCodigoSustento(sustentoTributario);
        compraFact.setNumeroComprobante(serieFormada);
        compraFact.setFechaIngreso(ConvierteDato.getFechaCortaDate(new Date(), "/"));
        compraFact.setEstado('I');                        /////////////  REVISAR
        compraFact.setFechaEstado(fechaEstado);   /////////////  REVISAR
        compraFact.setEstadoColocadoPor(ActivacionUsuario.getCodigoUsuario());
        compraFact.setFechaEmisionComprobante(ConvierteDato.getFechaCortaDate(this.fechaIngresoFactura, "/"));
        compraFact.setEmitidaRetencion('N');
        compraFact.setFechaSistema(fechaSistema);
        compraFact.setObservaciones(observaciones);

    }

    public void guardoElementosCabeceraEdit() {

        if (criterioRepresentante == criterioRepresentanteAux) {
            compraFact.setCodigoProveedor(compraSel.getCodigoProveedor());
        } else {
            compraFact.setCodigoProveedor(proveedor);
        }

        compraFact.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
        compraFact.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());

        if (compraSel.getCodigoTipoComprobante() == ubicaTipoComprobanteCompra) {
            compraFact.setCodigoTipoComprobante(compraSel.getCodigoTipoComprobante());
        } else {
            compraFact.setCodigoTipoComprobante(ubicaTipoComprobanteCompra);
        }

        if (compraSel.getCodigoSustento() == sustentoTributario) {
            compraFact.setCodigoSustento(compraSel.getCodigoSustento());
        } else {
            compraFact.setCodigoSustento(sustentoTributario);
        }

        compraFact.setFechaIngreso(compraSel.getFechaIngreso());
        compraFact.setEstado('I');
        compraFact.setFechaEstado(fechaEstado);
        compraFact.setEstadoColocadoPor(ActivacionUsuario.getCodigoUsuario());
        compraFact.setFechaEmisionComprobante(compraSel.getFechaEmisionComprobante());
        compraFact.setEmitidaRetencion('N');
        compraFact.setFechaSistema(fechaSistema);
        compraFact.setObservaciones(observaciones);

    }

    /**
     *
     */
    public void realizaCalculos() {

        compraFact.setSubtotal(new BigDecimal(0));
        compraFact.setSubtotalConIva(new BigDecimal(0));
        compraFact.setSubtotalIvaCero(new BigDecimal(0));
        compraFact.setDescuento(new BigDecimal(0));
        compraFact.setTotalRetencion(new BigDecimal("0.00"));
        compraFact.setTotal(new BigDecimal(0));
        compraFact.setAbono(new BigDecimal("0.00"));
        compraFact.setSaldo(new BigDecimal("0.00"));

        subtotalCompra = new BigDecimal("0.00");
        subtotalconIvaCompra = new BigDecimal("0.00");
        subtotalconIvaCeroaCompra = new BigDecimal("0.00");
        ivaCompra = new BigDecimal("0.00");
        totalCompra = new BigDecimal("0.00");
        ParametroCompraIfip pci = this.ejbFacadeParametroCompraIfip.find(new ParametroCompraIfipPK(5, ActivacionUsuario.getCodigoIfip()));
        //System.out.println("IVA..." + this.porcantajeIva);
        if (pci != null) {
            this.porcantajeIva = new BigDecimal(pci.getValor());
        }
        for (CompraDetalle cd : this.itemsCompraDetalleSel) {

            subtotalCompra = subtotalCompra.add(cd.getSubtotal());
            if (String.valueOf(cd.getGravaIva()).equals("S")) {
                subtotalconIvaCompra = subtotalconIvaCompra.add(cd.getSubtotal());
            } else {
                subtotalconIvaCeroaCompra = subtotalconIvaCeroaCompra.add(cd.getSubtotal());
            }
            DecimalFormat dFormat = new DecimalFormat("##.##");
            dFormat.setRoundingMode(RoundingMode.HALF_EVEN);
            cd.setPorcentajeIva(String.valueOf(cd.getGravaIva()).equals("S") ? porcantajeIva : new BigDecimal("0.00"));
            System.out.println("format: "+dFormat.format(cd.getSubtotal().multiply(cd.getPorcentajeIva()).divide(new BigDecimal("100"))));
            cd.setIva(new BigDecimal(dFormat.format(cd.getSubtotal().multiply(cd.getPorcentajeIva()).divide(new BigDecimal("100")))));
            cd.setTotal(cd.getSubtotal().add(cd.getIva()));
            this.ivaCompra = this.ivaCompra.add(cd.getIva());
        }

        totalCompra = totalCompra.add(this.subtotalCompra.add(ivaCompra).subtract(descuentoCompra));

        if (itemsCompraDetalleSel.size() > 0) {
            banderaCalcula = true;
        }
        ;

    }
    
    /**
     * Valida el monto que tiene la agencia para realizar una compra-factura
     * @return 
     */
    public boolean validaMontoFacturaAgencia(){
        boolean valido = false;
        try{
            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            // Formando parametros para el sp
            llamaSP.setNombreSP("mks_adquisiciones.pkm_compra_ifip_age_mon.p_valida_monto_ifip_age");
            llamaSP.setNumeroParametros(5);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip_age", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_monto_compra", getTotalCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_valido", Types.INTEGER});
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_validacion", Types.VARCHAR});
            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                int resultado = (Integer) llamaSP.getListResultado().get(0);
                if (resultado == 1)
                    valido = true;
                else
                    MuestraMensaje.addError(llamaSP.getListResultado().get(1).toString());
            }
        } catch (Exception e) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorValidaMontosAgencia"));
            Logger.getLogger(FacturaBean.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
        return valido;
    }
    
    public void guardaDatosCabeceraDetalle(ActionEvent event) {
        context = RequestContext.getCurrentInstance();
        if (!banderaComprobanteValido) {
            MuestraMensaje.addError(msg);
            context.execute("procesandoDlg.hide()");
            return;
        }
        //Valida el rango del monto que se permite segun la agencia ifip
        if (!validaMontoFacturaAgencia()){
            context.execute("procesandoDlg.hide()");
            return;
        }
        
        if (getTotalCompra().compareTo(BigDecimal.ZERO) > 0) {

            try {
                context.execute("procesandoDlg.show()");
                // Cargando la conexion de BD
                llamaSP.cargaConexion();

                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);

                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);

                guardoElementosCabecera();

                if (this.guardaCompraCabecera()) {
                    guardaCompraDetalles();

                    // obtieneProveedor();
                }
                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaGuardada"));
                    MuestraMensaje.addInformacion(getMsg());
                    context.execute("procesandoDlg.hide()");
                    context.execute("FacturaDialog.hide()");
                    itemsCompras = null;

                    //this.buscaCompras();
                    this.compraSel = null;
                    //context.execute("ImprimeComprobanteDialogo.show()");
                    //this.init();

                } else {
                    context.execute("procesandoDlg.hide()");
                    context.execute("FacturaDialog.hide()");
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                }

            } catch (Exception ex) {
                //System.out.println("Error ");
                ex.printStackTrace();
                context.execute("procesandoDlg.hide()");
                context.execute("FacturaDialog.hide()");
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"facturaBean", "guardaDatosCabeceraDetalle", CapturaError.getErrorException(ex)});
            }
        } else {
            //MensajeFacturaTotalCompraZero
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaTotalCompraZero");
            MuestraMensaje.addAdvertencia(msg);
        }
    }
    /*
     metodo q se usa para ingresar los datos de la cabecera
     */

    public boolean guardaCompraCabecera() throws UnknownHostException {

        //System.out.println("fecha " + compraFact.getFechaIngreso());
        //System.out.println("fecha " + this);
        this.setFechaIngresoFacturaTs(new java.sql.Timestamp(compraFact.getFechaIngreso().getTime()));
        this.setFechaEmisionComprobanteTs(new java.sql.Timestamp(compraFact.getFechaEmisionComprobante().getTime()));

        this.setFechaEstadTs(new java.sql.Timestamp(compraFact.getFechaEstado().getTime()));
        this.setFechaSistemaTs(new java.sql.Timestamp(compraFact.getFechaSistema().getTime()));

        if (this.itemsCompraDetalleSel != null) {
            llamaSP.setNombreSP("mks_adquisiciones.pkm_compra.p_inserta_compra");
            llamaSP.setNumeroParametros(26);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_proveedor", compraFact.getCodigoProveedor().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", compraFact.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", compraFact.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_comprobante", compraFact.getCodigoTipoComprobante().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_sustento", compraFact.getCodigoSustento().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_comprobante", compraFact.getNumeroComprobante()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso", new java.sql.Timestamp(ConvierteDato.getFechaCortaDate(compraFact.getFechaIngreso(), "/").getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf(compraFact.getEstado())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", compraFact.getEstadoColocadoPor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_emision_comprobante", new java.sql.Timestamp(ConvierteDato.getFechaCortaDate(compraFact.getFechaEmisionComprobante(), "/").getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_emitida_retencion", String.valueOf(compraFact.getEmitidaRetencion())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal", getSubtotalCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_con_iva", getSubtotalconIvaCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva_cero", getSubtotalconIvaCeroaCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_descuento", getDescuentoCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_retencion", compraFact.getTotalRetencion()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", getTotalCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_abono", compraFact.getAbono()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", getTotalCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", getObservaciones()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva", this.ivaCompra});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_autorzacion_electronica", this.autorizacionElectronica});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_factura_electronica", this.esFacturaElectronica});
            /////SALIDA
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_compra", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                this.codigoComprasFact = Long.parseLong(llamaSP.getListResultado().get(0).toString());
                //System.out.println("codigocompra: " + this.codigoComprasFact);
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public boolean guardaCompraDetalles() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_compra_detalle.p_inserta_compra_detalle");
        llamaSP.setNumeroParametros(8);

        if (this.codigoComprasFact != null) {

            for (CompraDetalle cd : this.getItemsCompraDetalleSel()) {
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", getCodigoComprasFact()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_grupo_articulo_en", cd.getCodigoArticulo().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_detalle_articulo_en", cd.getDetalleArticulo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_en", cd.getSubtotal()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_grava_iva_en", String.valueOf(cd.getGravaIva())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_porcentaje_iva_en", cd.getPorcentajeIva()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva_en", cd.getIva()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_en", cd.getTotal()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                //  
                if (!llamaSP.isEjecucionCorrecta()) {
                    //System.out.println("*****   GUARDO EL DETALLE DE LA COMPRA  *******************");
                    break;
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public void editoDatosCabeceraDetalle(ActionEvent event) {

        if (getTotalCompra().compareTo(BigDecimal.ZERO) > 0) {
            context = RequestContext.getCurrentInstance();
            try {
                context.execute("procesandoDlg.show()");
                // Cargando la conexion de BD
                llamaSP.cargaConexion();

                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);

                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);

                guardoElementosCabeceraEdit();

                if (editoCompraCabecera()) {
                    if (eliminoCompraDetalles()) {
                        guardaCompraDetalles();

                    }
                }
                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaGuardada"));
                    MuestraMensaje.addInformacion(getMsg());
                    context.execute("procesandoDlg.hide()");
                    this.buscaCompras();
                    this.compraSel = null;

                } else {
                    context.execute("procesandoDlg.hide()");
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                }

            } catch (Exception ex) {
                //System.out.println("Error ");
                ex.printStackTrace();
                context.execute("procesandoDlg.hide()");
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"facturaBean", "editoDatosCabeceraDetalle", CapturaError.getErrorException(ex)});
            }
        } else {
            //MensajeFacturaTotalCompraZero
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaTotalCompraZero");
            MuestraMensaje.addAdvertencia(msg);
        }
    }
    /*
     metodo q se usa para ingresar los datos de la cabecera
     */

    public boolean editoCompraCabecera() throws UnknownHostException {

        if (this.itemsCompraDetalleSel != null) {
            llamaSP.setNombreSP("mks_adquisiciones.pkm_compra.p_actualiza_compra");
            llamaSP.setNumeroParametros(26);  //PARAMETROS DE ENTRADA Y SALIDA QUE

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", compraFact.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_proveedor", compraFact.getCodigoProveedor().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", compraFact.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", compraFact.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_comprobante", compraFact.getCodigoTipoComprobante().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_sustento", compraFact.getCodigoSustento().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_comprobante", compraFact.getNumeroComprobante()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso", new java.sql.Timestamp(ConvierteDato.getFechaCortaDate(compraFact.getFechaIngreso(), "/").getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf(compraFact.getEstado())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", compraFact.getEstadoColocadoPor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_emision_comprobante", new java.sql.Timestamp(ConvierteDato.getFechaCortaDate(compraFact.getFechaEmisionComprobante(), "/").getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_emitida_retencion", String.valueOf(compraFact.getEmitidaRetencion())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal", getSubtotalCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_subtotal_con_iva", getSubtotalconIvaCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva_cero", getSubtotalconIvaCeroaCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_descuento", getDescuentoCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_retencion", compraFact.getTotalRetencion()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", getTotalCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_abono", compraFact.getAbono()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", getTotalCompra()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", getObservaciones()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", new java.sql.Timestamp(compraFact.getFechaSistema().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_iva", this.ivaCompra});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_autorzacion_electronica", this.autorizacionElectronica});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_factura_electronica", this.esFacturaElectronica});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            /////SALIDA
            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                this.codigoComprasFact = compraFact.getCodigo();
                //System.out.println("codigocompra: " + compraFact.getCodigo());
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public boolean eliminoCompraDetalles() {
        llamaSP.setNombreSP("mks_adquisiciones.pkm_compra_detalle.p_elimina_compra_detalle");
        llamaSP.setNumeroParametros(1);

        if (this.codigoComprasFact != null) {

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", this.codigoComprasFact});
            //System.out.println("p_codigo_compra: " + getCodigoComprasFact());
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (llamaSP.isEjecucionCorrecta()) {
                //System.out.println("SE ELIMIUNARON LOS REGISTROS CON EL CODIGO DE LA COMPRA: " + compraFact.getCodigo());
            }

        }
        return llamaSP.isEjecucionCorrecta();

    }

    // ------------------------------------------------------------------------------------------------------------------------------------
    //-- PROCESOS PARA GENERAR LA RETENCION
    public void agregoDetalleRetencion() {

        if (totalRetenido.doubleValue() <= 0) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoRetencionValorCero"));
            MuestraMensaje.addError(msg);
            return;
        }
        for (RetencionDetalle rd : this.itemsRetencionDetalles) {
            if (rd.getTipoRetencion().getCodigo() == this.tipoRetencion.getCodigo() && this.compraDetalleSel.getCodigo() == rd.getCompraDetalle().getCodigo()) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RetencionYaIngresada");
                MuestraMensaje.addError(msg);
                return;
            }
        }
        //MensajeFacturaCamposVaciosDetCab
        if (compraDetalleSel != null && tipoRetencion != null) {
            try {

                retencionDetalle = new RetencionDetalle();

                retencionDetalle.setRetencionDetallePK(new RetencionDetallePK((long) 1, this.tipoRetencion.getCodigo(), this.compraDetalleSel.getCodigo()));
                retencionDetalle.setTipoRetencion(tipoRetencion);
                retencionDetalle.setCompraDetalle(compraDetalleSel);
                retencionDetalle.setBaseImponible(this.baseImponible);
                retencionDetalle.setPorcentaje(tipoRetencion.getPorcentaje());
                retencionDetalle.setTotal(totalRetenido);

                try {

                    this.itemsRetencionDetalles.add(retencionDetalle);
                    this.tipoRetencion = null;
                    this.compraDetalleSel = null;
                    this.baseImponible = new BigDecimal("0.00");
                    this.porcentajeRetencion = new BigDecimal("0.00");
                    this.totalRetenido = new BigDecimal("0.00");
                    sumaRetenciones();
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("NO SE AGREGA");
                }

            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                //System.out.println(" ERRORl");
            }

        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCamposVaciosDetCab");
            MuestraMensaje.addAdvertencia(msg);
            //System.out.println("vacio");
        }
    }

    public void calculoTotalRetencion() {
        //System.out.println("calculoTotalRetencion ");
        try {

            if (compraDetalle != null && tipoRetencion != null) {
                this.porcentajeRetencion = tipoRetencion.getPorcentaje();
                if (this.tipoRetencion.getCalculaSobre() == 'I') {
                    this.baseImponible = this.compraDetalle.getIva();
                } else {
                    this.baseImponible = this.compraDetalle.getSubtotal();
                }

                this.obtieneRetencion();

            }
        } catch (Exception e) {
        }
    }

    public void calculoTotalRetencionBaseImponible() {

        //System.out.println("calculoTotalRetencionBaseImponible ");
        if (compraDetalle != null && tipoRetencion != null) {
            if (this.baseImponible.compareTo(new BigDecimal("0.00")) >= 0 && this.baseImponible.compareTo(this.compraDetalle.getSubtotal()) <= 0)// && )
            {
                this.porcentajeRetencion = tipoRetencion.getPorcentaje();
            }

            this.obtieneRetencion();
        } else {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoRetencionValorCero"));
            totalRetenido = new BigDecimal("0.00");
        }

    }

    private void obtieneRetencion() {

        //System.out.println(" obtieneRetencion  " + this.baseImponible);
        if (this.baseImponible.equals(new BigDecimal("0.00")) || this.baseImponible.equals(new BigDecimal("0"))) {
            //System.out.println("this.baseImponible " + this.baseImponible);
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoRetencionValorCero"));
            totalRetenido = new BigDecimal("0.00");
        } else {
            totalRetenido = baseImponible.multiply(porcentajeRetencion).divide(new BigDecimal("100"));
            //System.out.println("totalRetenido " + totalRetenido);
            totalRetenido = totalRetenido.setScale(2, BigDecimal.ROUND_HALF_UP);
            //System.out.println("totalRetenido REDONDEADO " + totalRetenido);
        }
    }

    public void sumaRetenciones() {
        // Obteniendo el total
        totalSumaRetenido = new BigDecimal("0.00");
        for (RetencionDetalle rd : itemsRetencionDetalles) {
            //System.out.println("Suma retencion " + rd.getTotal());
            this.totalSumaRetenido = this.totalSumaRetenido.add(rd.getTotal());
        }

        //System.out.println("Suma totalSumaRetenido " + totalSumaRetenido);
    }

    /**
     *
     */
    public void eliminaDetalleRetencion() {
        if (this.itemsRetencionDetalles.size() > 0) {
            this.itemsRetencionDetalles.remove(this.retencionDetalleSel);
            sumaRetenciones();
        }
    }

    public void guardoElementosCabeceraRetencion() {
        //System.out.println("codigo " + ejbFacadePeriodoContable.getItemPeriodoContable('S').get(0));

        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String fechaIngresAux = df1.format(fechaRetencion);

        try {
            fechaRetencion = formatter.parse(fechaIngresAux);

        } catch (ParseException ex) {
            Logger.getLogger(FacturaBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        retencion.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
        retencion.setCompra(compraSel);
        retencion.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
        retencion.setCodigoPeriodo(ejbFacadePeriodoContable.getItemPeriodoContable('S').get(0));
        retencion.setFechaRetencion(fechaRetencion);
        retencion.setTotalRetenido(totalSumaRetenido);
        retencion.setEstado('E');
        retencion.setEstadoColocadoPor(ActivacionUsuario.getCodigoUsuario());
        retencion.setFechaEstado(fechaEstado);   /////////////  REVISAR  
    }

    //////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////-------------------- REALIZAO LA RETENCION
    public void guadarRetencion(ActionEvent event) {

        List<Retencion> listaRetencion = this.ejbFacadeRetencion.getItemsRetencionesfindByCodigoCompra(this.compraSel.getCodigo());

        opcionGeRet = (listaRetencion.isEmpty()) ? 1 : 2;

        if (opcionGeRet == 1) {
            RequestContext context = RequestContext.getCurrentInstance();
            if (getTotalSumaRetenido().compareTo(BigDecimal.ZERO) > 0) {

                try {
                    context.execute("procesandoDlg.show()");
                    // Cargando la conexion de BD
                    llamaSP.cargaConexion();

                    // Indicando que no cierre la conexion de la base de datos
                    llamaSP.setCerrarConexion(false);

                    // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                    llamaSP.autoCommit(false);

                    guardoElementosCabeceraRetencion();

                    if (this.guardaRetencionCabecera()) {

                        if (guardaRetencionDetalle()) {
                            //System.out.println("GUARDO DETALLES");

                            try {
                                if (editoCompraCabeceraRetencion()) {
                                    //System.out.println("ok cambio");
                                }
                            } catch (Exception e) {
                            }
                        }
                        // Guardar el documento electronico
                        generaDocumentoElectronico(getCodigoRetencion());
                    }
                    // Verificando que la ejecucion del proceso ha sido correcta
                    if (llamaSP.isEjecucionCorrecta()) {
                        // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                        llamaSP.commit();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeRetencionRealizada"));
                        MuestraMensaje.addInformacion(getMsg());
                        context.execute("procesandoDlg.hide()");
                        //context.execute("RetencionDialog.hide()");
                        //this.obtieneProveedor();
                        //this.buscaCompras();
                        //this.compraSel = null;

                    } else {
                        context.execute("procesandoDlg.hide()");
                        llamaSP.rollback();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                    }

                } catch (UnknownHostException ex) {
                    context.execute("procesandoDlg.hide()");
                    // Muestra el Mensaje del Error en la Capa
                    MuestraMensaje.addErrorCapaControl();
                    // Registra el error en el log del servidor
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"facturaBean", "generoRetencion", CapturaError.getErrorException(ex)});
                }
            } else {
                //MensajeFacturaTotalCompraZero
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaTotalCompraZero");
                MuestraMensaje.addAdvertencia(msg);
                context.execute("procesandoDlg.hide()");
            }
        }
        if (opcionGeRet == 2) {
            RequestContext context = RequestContext.getCurrentInstance();
            this.setRetencion(listaRetencion.get(0));
            codigoRetencion = this.retencion.getCodigo();
            if (getTotalSumaRetenido().compareTo(BigDecimal.ZERO) > 0) {

                try {
                    context.execute("procesandoDlg.show()");
                    // Cargando la conexion de BD
                    llamaSP.cargaConexion();

                    // Indicando que no cierre la conexion de la base de datos
                    llamaSP.setCerrarConexion(false);

                    // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                    llamaSP.autoCommit(false);

                    guardoElementosCabeceraRetencion();
                    retencion.setEstado('E');
                    if (this.editoRetencionCabecera()) {
                        //System.out.println("EDITO CABERCERA");
                        if (this.eliminoRetencionDetalle()) {
                            //System.out.println("ELIMINO DETALLES ");
                            if (guardaRetencionDetalle()) {
                                //System.out.println("GUARDO DETALLES");

                                try {
                                    if (editoCompraCabeceraRetencion()) {
                                        //System.out.println("ok cambio");
                                    }
                                } catch (Exception e) {
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
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeRetencionRealizada"));
                        MuestraMensaje.addInformacion(getMsg());
                        context.execute("procesandoDlg.hide()");
                        //context.execute("RetencionDialog.hide()");
                        //context.execute("ImprimeComprobanteDialogo.show()");

                    } else {
                        context.execute("procesandoDlg.hide()");
                        llamaSP.rollback();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                    }

                } catch (Exception ex) {
                    //System.out.println("Error ");
                    ex.printStackTrace();
                    context.execute("procesandoDlg.hide()");
                    // Muestra el Mensaje del Error en la Capa
                    MuestraMensaje.addErrorCapaControl();
                    // Registra el error en el log del servidor
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"facturaBean", "editoRetencion", CapturaError.getErrorException(ex)});
                }
            } else {
                //MensajeFacturaTotalCompraZero
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaTotalCompraZero");
                MuestraMensaje.addAdvertencia(msg);
                context.execute("procesandoDlg.hide()");
            }
        }
    }

    public boolean generaDocumentoElectronico(Long codigoRetencion){
        System.out.println( "----------------- ********* "+codigoRetencion+" ********* -------------" );
        llamaSP.setNombreSP("sigma.pfac_retencion_genera_ele");
        llamaSP.setNumeroParametros(2);
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"in_codigo_retencion", codigoRetencion});
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"on_documento_id", Types.NUMERIC});
        llamaSP.invocaSP();
        if (llamaSP.isEjecucionCorrecta()) {
            this.setNumeroRetencion(llamaSP.getListResultado().get(0).toString());
        }
        return llamaSP.isEjecucionCorrecta();
    }
    
    // -------------------------------------------------------------------------
    // -- Imprimir Retencion
    public void imprimirRetencion(ActionEvent event) {
        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");
        if (this.compraSel == null) {
            context.execute("procesandoDlg.hide()");
            return;
        }

        if (codigoRetencion == null) {
            context.execute("procesandoDlg.hide()");
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RetencionDebeSerGuardada");
            MuestraMensaje.addError(msg);
            return;
        }

        List<DocumentoRetencion> listaDocumentoRetencion = this.ejbFacadeDocumentoRetencion.getItemsDocumentoRetencionCompra(this.compraSel.getCodigo(), this.compraSel.getCodigoIfip(), this.compraSel.getCodigoIfipAgencia(), 'A');
        if (listaDocumentoRetencion.isEmpty()) {

            obtieneDocumento();
            if (this.codigoTDRD == null) {
                context.execute("procesandoDlg.hide()");
                return;
            }
            this.guardoDocumento();
            listaDocumentoRetencion = this.ejbFacadeDocumentoRetencion.getItemsDocumentoRetencionCompra(this.compraSel.getCodigo(), this.compraSel.getCodigoIfip(), this.compraSel.getCodigoIfipAgencia(), 'A');
            this.codigoCompra = this.compraSel.getCodigo().intValue();
            this.numeroTDRD = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getNumero();
            this.codigoTDRD = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getCodigo();
            context.execute("procesandoDlg.hide()");
            context.execute("ImprimeComprobanteRetencionDialogo.show()");
            this.codigoCompra = this.compraSel.getCodigo().intValue();
            //context.execute("ImprimeComprobanteRetencionDialogo.show()");
            //this.buscaCompras();
            //this.compraSel = null;

        } else if (listaDocumentoRetencion.size() == 1) {
            this.codigoCompra = this.compraSel.getCodigo().intValue();
            this.numeroTDRD = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getNumero();
            this.codigoTDRD = listaDocumentoRetencion.get(0).getTalonarioDocumentoRetDet().getCodigo();
            context.execute("procesandoDlg.hide()");
            context.execute("ImprimeComprobanteRetencionDialogo.show()");

        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RetencionConMasDeUnDocumento");
            MuestraMensaje.addError(msg);
        }

    }

    /*
     metodo para obtener el siguiente documento
     */
    public void guardoDocumento() {

        try {
            this.codigoCompra = 0;
            this.codigoCompra = compraSel.getCodigo().intValue();
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            //if (recuperoSiguienteDocumento()) {
            if (guardoDocumentoRetencion()) {
                if (asignaDocumentoRetencion()) {

                }
            }

            //}
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                this.deshabilitaBotonGuardarRetencion = true;
                context.execute("procesandoDlg.hide()");

            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            //System.out.println("Error ");
            ex.printStackTrace();
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"facturaBean", "generoRetencion", CapturaError.getErrorException(ex)});
        }

    }

    /*
     metodo q se usa para ingresar los datos de la cabecera
     de la retencion 
     */
    public boolean guardaRetencionCabecera() throws UnknownHostException {

        this.setFechaRetencionTs(new java.sql.Timestamp(retencion.getFechaRetencion().getTime()));
        this.setFechaEstadTs(new java.sql.Timestamp(retencion.getFechaEstado().getTime()));
        //System.out.println("------------------- " + fechaRetencionTs + "--------------- " + fechaEstadTs);

        if (this.itemsRetencionDetalles != null) {
            //mks_contables.pkg_retencion.p_inserta_retencion
            llamaSP.setNombreSP("mks_contables.pkm_retencion.p_inserta_retencion");
            llamaSP.setNumeroParametros(10);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", retencion.getCodigoIfip()});
            //System.out.println("p_codigo_ifip: " + retencion.getCodigoIfip());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", retencion.getCompra().getCodigo()});
            //System.out.println("p_codigo_compra: " + retencion.getCompra().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", retencion.getCodigoIfipAgencia()});
            //System.out.println("p_codigo_ifip_agencia: " + retencion.getCodigoIfipAgencia());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", String.valueOf(new Date().getYear()+1900)});//Toma el año actual para evitar problemas por cierre de periodo
            //System.out.println("p_codigo_periodo: " + retencion.getCodigoPeriodo().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_retencion", this.fechaRetencionTs});
            //System.out.println("p_fecha_retencion: " + this.fechaRetencionTs);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_retenido", this.getTotalSumaRetenido()});
            //System.out.println("p_total_retenido: " + getTotalRetenido());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf(retencion.getEstado())});
            //System.out.println("p_estado: " + this.retencion.getEstado());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", retencion.getEstadoColocadoPor()});
            //System.out.println("p_estado_colocado_por: " + retencion.getEstadoColocadoPor());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.fechaEstadTs});
            //System.out.println("p_fecha_estado: " + this.fechaEstadTs);

            //System.out.println(" MON 1");
            /////SALIDA
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
            //System.out.println(" MON 3");
            // Invocando al SP
            llamaSP.invocaSP();
            //System.out.println(" MON 4");
            if (llamaSP.isEjecucionCorrecta()) {
                //System.out.println(" MON 5");
                this.codigoRetencion = Long.parseLong(llamaSP.getListResultado().get(0).toString());
                //System.out.println("codigo retencion: " + this.codigoRetencion);
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public boolean guardaRetencionDetalle() {
        llamaSP.setNombreSP("mks_contables.pkm_retencion_detalle.p_inserta_retencion_detalle");
        llamaSP.setNumeroParametros(6);

        if (this.codigoRetencion != null) {

            for (RetencionDetalle rd : this.getItemsRetencionDetalles()) {
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.codigoRetencion});
                //System.out.println("p_codigo: " + this.codigoRetencion);
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_retencion", rd.getTipoRetencion().getCodigo()});
                //System.out.println("p_codigo_tipo_retencion: " + rd.getTipoRetencion().getCodigo());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra_detalle", rd.getCompraDetalle().getCodigo()});
                //System.out.println("p_codigo_compra_detalle: " + rd.getCompraDetalle().getCodigo());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_base_imponible", rd.getBaseImponible()});
                //System.out.println("p_base_imponible: " + rd.getBaseImponible());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_porcentaje", rd.getPorcentaje()});
                //System.out.println("p_porcentaje: " + rd.getBaseImponible());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", rd.getTotal()});
                //System.out.println("p_total: " + rd.getTotal());

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

    /*
     metodo q se usa para ingresar los datos de la cabecera
     de la retencion 
     */
    public boolean editoRetencionCabecera() throws UnknownHostException {

        this.setFechaRetencionTs(new java.sql.Timestamp(retencion.getFechaRetencion().getTime()));
        this.setFechaEstadTs(new java.sql.Timestamp(retencion.getFechaEstado().getTime()));
        //System.out.println("------------------- " + fechaRetencionTs + "--------------- " + fechaEstadTs);

        if (this.itemsRetencionDetalles != null) {
            //mks_contables.pkg_retencion.p_inserta_retencion
            llamaSP.setNombreSP("mks_contables.pkm_retencion.p_actualiza_retencion");
            llamaSP.setNumeroParametros(10);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", retencion.getCodigo()});
            //System.out.println("p_codigo: " + retencion.getCodigoIfip());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", retencion.getCodigoIfip()});
            //System.out.println("p_codigo_ifip: " + retencion.getCodigoIfip());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", retencion.getCompra().getCodigo()});
            //System.out.println("p_codigo_compra: " + retencion.getCompra().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", retencion.getCodigoIfipAgencia()});
            //System.out.println("p_codigo_ifip_agencia: " + retencion.getCodigoIfipAgencia());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", String.valueOf(new Date().getYear()+1900)});//Toma el año actual para evitar problemas por cierre de periodo
            //System.out.println("p_codigo_periodo: " + retencion.getCodigoPeriodo().getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_retencion", this.fechaRetencionTs});
            //System.out.println("p_fecha_retencion: " + this.fechaRetencionTs);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_retenido", this.getTotalSumaRetenido()});
            //System.out.println("p_total_retenido: " + getTotalRetenido());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf(retencion.getEstado())});
            //System.out.println("p_estado: " + this.retencion.getEstado());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", retencion.getEstadoColocadoPor()});
            //System.out.println("p_estado_colocado_por: " + retencion.getEstadoColocadoPor());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.fechaEstadTs});
            //System.out.println("p_fecha_estado: " + this.fechaEstadTs);

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                this.codigoRetencion = retencion.getCodigo();
                //System.out.println(" ACTUALIZA codigo retencion: " + codigoRetencion);
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public boolean eliminoRetencionDetalle() {
        llamaSP.setNombreSP("mks_contables.pkm_retencion_detalle.p_elimina_retencion_detalle");
        llamaSP.setNumeroParametros(1);

        if (this.itemsRetencionDetalles != null) {

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_retencion", codigoRetencion});
            //System.out.println(" elimina p_codigo_retencion: " + getCodigoComprasFact());
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (llamaSP.isEjecucionCorrecta()) {
                //System.out.println("SE ELIMIUNARON LOS REGISTROS CON EL CODIGO DE LA COMPRA: " + compraFact.getCodigo());
            }

        }
        return llamaSP.isEjecucionCorrecta();

    }

    public boolean editoCompraCabeceraRetencion() throws UnknownHostException {

        if (this.itemsRetencionDetalles != null) {
            llamaSP.setNombreSP("mks_adquisiciones.pkm_compra.p_actualiza_estado_compra");
            llamaSP.setNumeroParametros(4);  //PARAMETROS DE ENTRADA Y SALIDA QUE

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", compraSel.getCodigo()});
            //System.out.println("p_codigo: " + compraSel.getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf('R')});
            //System.out.println("p_estado: " + String.valueOf('R'));
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_emitida_retencion", String.valueOf('S')});
            //System.out.println("p_emitida_retencion: " + String.valueOf('S'));
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_retencion", totalSumaRetenido});
            //System.out.println("p_total_retencion: " + totalSumaRetenido);

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            /////SALIDA
            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {

                //System.out.println("ACTUALIZACION DEL ESTADO DE LA COMPRA REALIZADA");
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    //----------------------------------------------------------------------------
    //-- PROCESOS DE DOCUMENTO DE RETENCION
    public void obtieneDocumento() {
        try {
            this.codigoTDRD = null;
            if (this.serieRetencion != null && this.numeroRetencion != null) {
                List<TalonarioDocumentoRetDet> listaTalonarioDocumentoRetDet = ejbFacadeTalonarioDocumentoRetDet.getItemsSerieTalonarioSerieDocumento(this.serieRetencion, Long.parseLong(this.numeroRetencion));
                //System.out.println("listaTalonarioDocumentoRetDet " + listaTalonarioDocumentoRetDet);
                if (!listaTalonarioDocumentoRetDet.isEmpty()) {
                    if (listaTalonarioDocumentoRetDet.size() == 1) {
                        if (listaTalonarioDocumentoRetDet.get(0).getEstado() == 'P') {
                            this.codigoTDRD = listaTalonarioDocumentoRetDet.get(0).getCodigo();
                            this.numeroTDRD = listaTalonarioDocumentoRetDet.get(0).getNumero();
                            this.numeroRetencion = numeroTDRD;
                        } else {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoDiferentePendiente");
                            MuestraMensaje.addError(msg);
                        }
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnaRetencionParaElNumero");
                        MuestraMensaje.addError(msg);
                    }

                } else {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoRetencionNoExiste");
                    MuestraMensaje.addError(msg);
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DocumentoRetencionNoExiste");
                MuestraMensaje.addError(msg);
            }
        } catch (NumberFormatException e) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SoloValoresNumericos");
            MuestraMensaje.addError(msg);
        }
    }
    /*
     metodo q se usa para recuperar el siguiente nuemero de docuemntos 
     */

    public boolean recuperoSiguienteDocumento() throws UnknownHostException {

        if (this.itemsRetencionDetalles != null) {
            llamaSP.setNombreSP("mks_contables.pkm_talonario_doc_ret_det.p_obtiene_siguiente_documento");
            llamaSP.setNumeroParametros(4);  //PARAMETROS DE ENTRADA Y SALIDA QUE

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
            /////SALIDA
            // Invocando al SP
            llamaSP.invocaSP();

            //System.out.println("ANTES DE RECUPERAR DATOS PARA DOCUMENTO ");
            if (llamaSP.isEjecucionCorrecta()) {

                numeroTDRD = llamaSP.getListResultado().get(0).toString();
                this.codigoTDRD = Long.parseLong(llamaSP.getListResultado().get(1).toString());
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }


    /*
     metodo q se usa para guardar guadar os datos en la tabla Documento retencion
     */
    public boolean guardoDocumentoRetencion() throws UnknownHostException {

        this.setFechaRegistroTs(new java.sql.Timestamp(fechaRegistro.getTime()));

        llamaSP.setNombreSP("mks_contables.pkm_documento_retencion.p_inserta_documento_retencion");
        llamaSP.setNumeroParametros(5);  //PARAMETROS DE ENTRADA Y SALIDA QUE

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_retencion", this.codigoRetencion});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_documento", this.codigoTDRD});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", this.fechaRegistroTs});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf('A')});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    /*
     metodo q se usa para guardar guadar os datos en la tabla TALONARIO Documento retencion
     */
    public boolean asignaDocumentoRetencion() throws UnknownHostException {

        if (this.itemsRetencionDetalles != null) {
            llamaSP.setNombreSP("mks_contables.pkm_talonario_doc_ret_det.p_actualiza_documento");
            llamaSP.setNumeroParametros(3);  //PARAMETROS DE ENTRADA Y SALIDA QUE

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_talonario", this.codigoTDRD});
            //System.out.println("p_codigo_talonario " + this.codigoTDRD);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf('A')});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_asignado_a", String.valueOf('A')});
            //System.out.println("p_estado: " + String.valueOf('A'));

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            /////SALIDA
            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {

                //System.out.println("CAMBIO DE ESTADO EN LA TABLA TALONARIO");
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public boolean actualizaRetencionCompra() throws UnknownHostException {

        //mks_contables.pkg_retencion.p_inserta_retencion
        llamaSP.setNombreSP("mks_adquisiciones.pkg_compra.p_actualiza_retencion");
        llamaSP.setNumeroParametros(2);  //PARAMETROS DE ENTRADA Y SALIDA

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.compraSel.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_retencion", this.totalSumaRetenido});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Imprime el comprobante de retencion
     *
     * @param actionEvent
     */
    public void imprimeComprobanteRetencionr(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        ////System.out.println("INGRESO A IMPRIMIR LA RETENCIO*******888**** N       " + compraSel.getCodigo());
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoCompra", (long) this.codigoCompra);

        nombreReporte = "comprobanteRetencion";
        // comprobanteRetencion
        getGeneraReporte().exporta("/adquisicion/compras/facturas/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.codigoCompra) + ".pdf",
                "PDF", externalContext, facesContext);

    }

    /**
     * IMPRIME COMPROBANTE CONTABLE
     *
     * @param codCom
     * @param numCom
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeComprobante(Long numCom) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigo", numCom);

        nombreReporte = "comprobante";

        getGeneraReporte().exporta("/contable/comprobantes/comprobante/reporte/", nombreReporte,
                nombreReporte + String.valueOf(numCom) + ".pdf",
                "PDF", externalContext, facesContext);

        ////System.out.println("ImprimiÃ³ Movimiento");
    }

    public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        context = RequestContext.getCurrentInstance();
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigo", this.codigoComprobanteContabilizacion);

        nombreReporte = "comprobanteCompra";

        getGeneraReporte().exporta("/adquisicion/compras/facturas/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.numeroComprobanteContabilizacion) + ".pdf",
                "PDF", externalContext, facesContext);

    }

    /**
     * CONTANILIZACION DE LA COMPRA
     */
    public void guardoContabilizacion() {

        context = RequestContext.getCurrentInstance();
        this.numeroComprobanteContabilizacion = null;
        this.codigoComprobanteContabilizacion = null;

        if (this.compraSel == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionCompra");
            MuestraMensaje.addAdvertencia(msg);
            return;
        }
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            if (contabilizaFechaFactura) {
                this.fechaContabilizacionTs = new java.sql.Timestamp(this.compraSel.getFechaEmisionComprobante().getTime());
            } else {
                this.fechaContabilizacionTs = new java.sql.Timestamp(new Date().getTime());
            }

            //mks_contables.pkg_retencion.p_inserta_retencion
            llamaSP.setNombreSP("mks_contables.pkm_comprobante.p_comprobante_compra");
            llamaSP.setNumeroParametros(9);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_compra", compraSel.getCodigo()});
            //System.out.println("p_codigo_compra: " + compraSel.getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", ActivacionUsuario.getCodigoPeriodo()});
            //System.out.println("p_codigo_periodo: " + ActivacionUsuario.getCodigoPeriodo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            //System.out.println("p_codigo_ifip: " + ActivacionUsuario.getCodigoIfip());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia_ori", ActivacionUsuario.getCodigoIfipAgencia()});
            //System.out.println("p_codigo_ifip_agencia_ori: " + ActivacionUsuario.getCodigoIfipAgencia());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", this.fechaContabilizacionTs});
            //System.out.println("p_fecha: " + this.fechaContabilizacionTs);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", compraSel.getTotal()});
            //System.out.println("p_total: " + compraSel.getTotal());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_contabilizado_por", ActivacionUsuario.getCodigoUsuario()});
            //System.out.println("p_contabilizado_por: " + ActivacionUsuario.getCodigoUsuario());

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_comprobante", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
            /////SALIDA
            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                this.numeroComprobanteContabilizacion = llamaSP.getListResultado().get(0).toString();
                this.codigoComprobanteContabilizacion = Long.parseLong(llamaSP.getListResultado().get(1).toString());
                //System.out.println("Codigo Contabilizacion " + codigoComprobanteContabilizacion);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CompraContablizadaCorrectamente");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteContableDialogo.show()");
            } else {

                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);

            }

        } catch (Exception ex) {

            ex.printStackTrace();
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"facturaBean", "procesoContabilizacion", CapturaError.getErrorException(ex)});
        }

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

    public Date fecha(String fecha) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaD;
        fechaD = formateador.parse(fecha);
        return fechaD;
    }

    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isDeshabilitaBuscarProveedor() {
        return deshabilitaBuscarProveedor;
    }

    public void setDeshabilitaBuscarProveedor(boolean deshabilitaBuscarSocioLista) {
        this.deshabilitaBuscarProveedor = deshabilitaBuscarSocioLista;
    }

    public boolean isBusquedaProveedorLista() {
        return busquedaProveedorLista;
    }

    public void setBusquedaProveedorLista(boolean busquedaProveedorLista) {
        this.busquedaProveedorLista = busquedaProveedorLista;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Long getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(Long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
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
     * @return the itemsSolicitud
     */
    public List<Solicitud> getItemsSolicitud() {
        return itemsSolicitud;
    }

    /**
     * @param itemsSolicitud the itemsSolicitud to set
     */
    public void setItemsSolicitud(List<Solicitud> itemsSolicitud) {
        this.itemsSolicitud = itemsSolicitud;
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
     * @return the estadoEmitido
     */
    public String getEstadoEmitido() {
        return estadoEmitido;
    }

    /**
     * @param estadoEmitido the estadoEmitido to set
     */
    public void setEstadoEmitido(String estadoEmitido) {
        this.estadoEmitido = estadoEmitido;
    }

    /**
     * @return the itemsTipoComprobanteCompras
     */
    public List<TipoComprobanteCompra> getItemsTipoComprobanteCompras() {
        return itemsTipoComprobanteCompras;
    }

    /**
     * @param itemsTipoComprobanteCompras the itemsTipoComprobanteCompras to set
     */
    public void setItemsTipoComprobanteCompras(List<TipoComprobanteCompra> itemsTipoComprobanteCompras) {
        this.itemsTipoComprobanteCompras = itemsTipoComprobanteCompras;
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
     * @return the itemsProveedoresDatos
     */
    public List<ProveedorIfip> getItemsProveedoresDatos() {
        return itemsProveedoresDatos;
    }

    /**
     * @param itemsProveedoresDatos the itemsProveedoresDatos to set
     */
    public void setItemsProveedoresDatos(List<ProveedorIfip> itemsProveedoresDatos) {
        this.itemsProveedoresDatos = itemsProveedoresDatos;
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
     * @return the numerTelefonoProvedor
     */
    public String getNumeroTelefonoProvedor() {
        return numeroTelefonoProvedor;
    }

    /**
     * @param numerTelefonoProvedor the numerTelefonoProvedor to set
     */
    public void setNumeroTelefonoProvedor(String numerTelefonoProvedor) {
        this.numeroTelefonoProvedor = numerTelefonoProvedor;
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
     * @return the fechaEmisionComprobante
     */
    public Date getFechaEmisionComprobante() {
        return fechaEmisionComprobante;
    }

    /**
     * @param fechaEmisionComprobante the fechaEmisionComprobante to set
     */
    public void setFechaEmisionComprobante(Date fechaEmisionComprobante) {
        this.fechaEmisionComprobante = fechaEmisionComprobante;
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
     * @return the itemsSustTributario
     */
    public List<SustentoTributario> getItemsSustTributario() {
        return itemsSustTributario;
    }

    /**
     * @param itemsSustTributario the itemsSustTributario to set
     */
    public void setItemsSustTributario(List<SustentoTributario> itemsSustTributario) {
        this.itemsSustTributario = itemsSustTributario;
    }

    /**
     * @return the Factura
     */
    public Long getFactura() {
        return Factura;
    }

    /**
     * @param Factura the Factura to set
     */
    public void setFactura(Long Factura) {
        this.Factura = Factura;
    }

    /**
     * @return the itemsComprasAll
     */
    public List<Compra> getItemsComprasAll() {
        return itemsComprasAll;
    }

    /**
     * @param itemsComprasAll the itemsComprasAll to set
     */
    public void setItemsComprasAll(List<Compra> itemsComprasAll) {
        this.itemsComprasAll = itemsComprasAll;
    }

    /**
     * @return the codigoCompra
     */
    public int getCodigoCompra() {
        return codigoCompra;
    }

    /**
     * @param codigoCompra the codigoCompra to set
     */
    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    /**
     * @return the estadoCompra
     */
    public String getEstadoCompra() {
        return estadoCompra;
    }

    /**
     * @param estadoCompra the estadoCompra to set
     */
    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
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
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
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
     * @return the itemsDocumentosProveedor
     */
    public List<DocumentosProveedor> getItemsDocumentosProveedor() {
        return itemsDocumentosProveedor;
    }

    /**
     * @param itemsDocumentosProveedor the itemsDocumentosProveedor to set
     */
    public void setItemsDocumentosProveedor(List<DocumentosProveedor> itemsDocumentosProveedor) {
        this.itemsDocumentosProveedor = itemsDocumentosProveedor;
    }

    /**
     * @return the banderaComprobanteValido
     */
    public boolean isBanderaComprobanteValido() {
        return banderaComprobanteValido;
    }

    /**
     * @param banderaComprobanteValido the banderaComprobanteValido to set
     */
    public void setBanderaComprobanteValido(boolean banderaComprobanteValido) {
        this.banderaComprobanteValido = banderaComprobanteValido;
    }

    /**
     * @return the itemsGrupoArticulo
     */
    public List<GrupoArticulo> getItemsGrupoArticulo() {
        return itemsGrupoArticulo;
    }

    /**
     * @param itemsGrupoArticulo the itemsGrupoArticulo to set
     */
    public void setItemsGrupoArticulo(List<GrupoArticulo> itemsGrupoArticulo) {
        this.itemsGrupoArticulo = itemsGrupoArticulo;
    }

    /**
     * @return the grupoArticulo
     */
    public GrupoArticulo getGrupoArticulo() {
        return grupoArticulo;
    }

    /**
     * @param grupoArticulo the grupoArticulo to set
     */
    public void setGrupoArticulo(GrupoArticulo grupoArticulo) {
        this.grupoArticulo = grupoArticulo;
    }

    /**
     * @return the itemsCompraDetalleSel
     */
    public List<CompraDetalle> getItemsCompraDetalleSel() {
        return itemsCompraDetalleSel;
    }

    /**
     * @param itemsCompraDetalleSel the itemsCompraDetalleSel to set
     */
    public void setItemsCompraDetalleSel(List<CompraDetalle> itemsCompraDetalleSel) {
        this.itemsCompraDetalleSel = itemsCompraDetalleSel;
    }

    /**
     * @return the compraFact
     */
    public Compra getCompraFact() {
        return compraFact;
    }

    /**
     * @param compraFact the compraFact to set
     */
    public void setCompraFact(Compra compraFact) {
        this.compraFact = compraFact;
    }

    /**
     * @return the codigoCompraCab
     */
    public Long getCodigoCompraCab() {
        return codigoCompraCab;
    }

    /**
     * @param codigoCompraCab the codigoCompraCab to set
     */
    public void setCodigoCompraCab(Long codigoCompraCab) {
        this.codigoCompraCab = codigoCompraCab;
    }

    /**
     * @return the detalleArticulo
     */
    public String getDetalleArticulo() {
        return detalleArticulo;
    }

    /**
     * @param detalleArticulo the detalleArticulo to set
     */
    public void setDetalleArticulo(String detalleArticulo) {
        this.detalleArticulo = detalleArticulo;
    }

    /**
     * @return the banderaComboGrupo
     */
    public boolean isBanderaComboGrupo() {
        return banderaComboGrupo;
    }

    /**
     * @param banderaComboGrupo the banderaComboGrupo to set
     */
    public void setBanderaComboGrupo(boolean banderaComboGrupo) {
        this.banderaComboGrupo = banderaComboGrupo;
    }

    /**
     * @return the compraDetalleFact
     */
    public CompraDetalle getCompraDetalleFact() {
        return compraDetalleFact;
    }

    /**
     * @param compraDetalleFact the compraDetalleFact to set
     */
    public void setCompraDetalleFact(CompraDetalle compraDetalleFact) {
        this.compraDetalleFact = compraDetalleFact;
    }

    /**
     * @return the codigoGrupoArticulo
     */
    public Long getCodigoGrupoArticulo() {
        return codigoGrupoArticulo;
    }

    /**
     * @param codigoGrupoArticulo the codigoGrupoArticulo to set
     */
    public void setCodigoGrupoArticulo(Long codigoGrupoArticulo) {
        this.codigoGrupoArticulo = codigoGrupoArticulo;
    }

    /**
     * @return the fechaEstado
     */
    public Date getFechaEstado() {
        return fechaEstado;
    }

    /**
     * @param fechaEstado the fechaEstado to set
     */
    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    /**
     * @return the fechaCaducaComprobante
     */
    public Date getFechaCaducaComprobante() {
        return fechaCaducaComprobante;
    }

    /**
     * @param fechaCaducaComprobante the fechaCaducaComprobante to set
     */
    public void setFechaCaducaComprobante(Date fechaCaducaComprobante) {
        this.fechaCaducaComprobante = fechaCaducaComprobante;
    }

    /**
     * @return the codigoComprasFact
     */
    public Long getCodigoComprasFact() {
        return codigoComprasFact;
    }

    /**
     * @param codigoComprasFact the codigoComprasFact to set
     */
    public void setCodigoComprasFact(Long codigoComprasFact) {
        this.codigoComprasFact = codigoComprasFact;
    }

    /**
     * @return the fechaIngresoFacturAux
     */
    public Date getFechaIngresoFacturAux() {
        return fechaIngresoFacturAux;
    }

    /**
     * @param fechaIngresoFacturAux the fechaIngresoFacturAux to set
     */
    public void setFechaIngresoFacturAux(Date fechaIngresoFacturAux) {
        this.fechaIngresoFacturAux = fechaIngresoFacturAux;
    }

    /**
     * @return the subtotalArticulo
     */
    public BigDecimal getSubtotalArticulo() {
        return subtotalArticulo;
    }

    /**
     * @param subtotalArticulo the subtotalArticulo to set
     */
    public void setSubtotalArticulo(BigDecimal subtotalArticulo) {
        this.subtotalArticulo = subtotalArticulo;
    }

    /**
     * @return the gravaIvafact
     */
    public String getGravaIvafact() {
        return gravaIvafact;
    }

    /**
     * @param gravaIvafact the gravaIvafact to set
     */
    public void setGravaIvafact(String gravaIvafact) {
        this.gravaIvafact = gravaIvafact;
    }

    /**
     * @return the subtotalCompra
     */
    public BigDecimal getSubtotalCompra() {
        return subtotalCompra;
    }

    /**
     * @param subtotalCompra the subtotalCompra to set
     */
    public void setSubtotalCompra(BigDecimal subtotalCompra) {
        this.subtotalCompra = subtotalCompra;
    }

    /**
     * @return the subtotalconIvaCompra
     */
    public BigDecimal getSubtotalconIvaCompra() {
        return subtotalconIvaCompra;
    }

    /**
     * @param subtotalconIvaCompra the subtotalconIvaCompra to set
     */
    public void setSubtotalconIvaCompra(BigDecimal subtotalconIvaCompra) {
        this.subtotalconIvaCompra = subtotalconIvaCompra;
    }

    /**
     * @return the subtotalconIvaCeroaCompra
     */
    public BigDecimal getSubtotalconIvaCeroaCompra() {
        return subtotalconIvaCeroaCompra;
    }

    /**
     * @param subtotalconIvaCeroaCompra the subtotalconIvaCeroaCompra to set
     */
    public void setSubtotalconIvaCeroaCompra(BigDecimal subtotalconIvaCeroaCompra) {
        this.subtotalconIvaCeroaCompra = subtotalconIvaCeroaCompra;
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
     * @return the ivaCompra
     */
    public BigDecimal getIvaCompra() {
        return ivaCompra;
    }

    /**
     * @param ivaCompra the ivaCompra to set
     */
    public void setIvaCompra(BigDecimal ivaCompra) {
        this.ivaCompra = ivaCompra;
    }

    /**
     * @return the fechaIngresoFacturaTs
     */
    public Timestamp getFechaIngresoFacturaTs() {
        return fechaIngresoFacturaTs;
    }

    /**
     * @param fechaIngresoFacturaTs the fechaIngresoFacturaTs to set
     */
    public void setFechaIngresoFacturaTs(Timestamp fechaIngresoFacturaTs) {
        this.fechaIngresoFacturaTs = fechaIngresoFacturaTs;
    }

    /**
     * @return the fechaEmisionComprobanteTs
     */
    public Timestamp getFechaEmisionComprobanteTs() {
        return fechaEmisionComprobanteTs;
    }

    /**
     * @param fechaEmisionComprobanteTs the fechaEmisionComprobanteTs to set
     */
    public void setFechaEmisionComprobanteTs(Timestamp fechaEmisionComprobanteTs) {
        this.fechaEmisionComprobanteTs = fechaEmisionComprobanteTs;
    }

    /**
     * @return the fechaEstadTs
     */
    public Timestamp getFechaEstadTs() {
        return fechaEstadTs;
    }

    /**
     * @param fechaEstadTs the fechaEstadTs to set
     */
    public void setFechaEstadTs(Timestamp fechaEstadTs) {
        this.fechaEstadTs = fechaEstadTs;
    }

    /**
     * @return the banderaCalcula
     */
    public boolean isBanderaCalcula() {
        return banderaCalcula;
    }

    /**
     * @param banderaCalcula the banderaCalcula to set
     */
    public void setBanderaCalcula(boolean banderaCalcula) {
        this.banderaCalcula = banderaCalcula;
    }

    /**
     * @return the compraDetalleSel
     */
    public CompraDetalle getCompraDetalleSel() {
        return compraDetalleSel;
    }

    /**
     * @param compraDetalleSel the compraDetalleSel to set
     */
    public void setCompraDetalleSel(CompraDetalle compraDetalleSel) {
        this.compraDetalleSel = compraDetalleSel;
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
     * @return the banderaComprobanteValidoEd
     */
    public boolean isBanderaComprobanteValidoEd() {
        return banderaComprobanteValidoEd;
    }

    /**
     * @param banderaComprobanteValidoEd the banderaComprobanteValidoEd to set
     */
    public void setBanderaComprobanteValidoEd(boolean banderaComprobanteValidoEd) {
        this.banderaComprobanteValidoEd = banderaComprobanteValidoEd;
    }

    /**
     * @return the banderaComboGrupoEd
     */
    public boolean isBanderaComboGrupoEd() {
        return banderaComboGrupoEd;
    }

    /**
     * @param banderaComboGrupoEd the banderaComboGrupoEd to set
     */
    public void setBanderaComboGrupoEd(boolean banderaComboGrupoEd) {
        this.banderaComboGrupoEd = banderaComboGrupoEd;
    }

    /**
     * @return the banderaCalculaEd
     */
    public boolean isBanderaCalculaEd() {
        return banderaCalculaEd;
    }

    /**
     * @param banderaCalculaEd the banderaCalculaEd to set
     */
    public void setBanderaCalculaEd(boolean banderaCalculaEd) {
        this.banderaCalculaEd = banderaCalculaEd;
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
     * @return the banderaContRete
     */
    public boolean isBanderaContRete() {
        return banderaContRete;
    }

    /**
     * @param banderaContRete the banderaContRete to set
     */
    public void setBanderaContRete(boolean banderaContRete) {
        this.banderaContRete = banderaContRete;
    }

    /**
     * @return the criterioRepresentanteAux
     */
    public Long getCriterioRepresentanteAux() {
        return criterioRepresentanteAux;
    }

    /**
     * @param criterioRepresentanteAux the criterioRepresentanteAux to set
     */
    public void setCriterioRepresentanteAux(Long criterioRepresentanteAux) {
        this.criterioRepresentanteAux = criterioRepresentanteAux;
    }

    /**
     * @return the banderaAgenteRetencion
     */
    public boolean isBanderaAgenteRetencion() {
        return banderaAgenteRetencion;
    }

    /**
     * @param banderaAgenteRetencion the banderaAgenteRetencion to set
     */
    public void setBanderaAgenteRetencion(boolean banderaAgenteRetencion) {
        this.banderaAgenteRetencion = banderaAgenteRetencion;
    }

    /**
     * @return the banderaReqRetencion
     */
    public boolean isBanderaReqRetencion() {

        banderaReqRetencion = false;
        //opcionGeRet = 0;
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (compraSel != null) {
                if (!banderaAgenteRetencion) {

                    //System.out.println("ES AGENTE DE RETENCION");
                    //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaAgenteRetencion");
                    //MuestraMensaje.addAdvertencia(msg);
                    //context.execute("procesandoDlg.hide()");
                    banderaReqRetencion = false;
                } else if (String.valueOf(compraSel.getEstado()).equals("N")) {

                    //System.out.println("LA FACTURA YA ESTA CONTABILIZA");
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaContabilizada");
                    MuestraMensaje.addAdvertencia(msg);
                    banderaReqRetencion = false;
                    context.execute("procesandoDlg.hide()");
                } else if (String.valueOf(compraSel.getEstado()).equals("R")) {

                    //System.out.println("LA FACTURA YA ESTA RETENIDA");
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaRetenida");
                    MuestraMensaje.addAdvertencia(msg);
                    banderaReqRetencion = false;
                    context.execute("procesandoDlg.hide()");

                } else if (ejbFacadeDocumentoRetencion.getItemsDocumentoRetencionCompra(compraSel.getCodigo(), compraSel.getCodigoIfip(), compraSel.getCodigoIfipAgencia(), 'A').size() > 0) {

                    //System.out.println("LA FACTURA YA ESTA ASIGNADO DE RETENCION");
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaAsignadaRetencion");
                    MuestraMensaje.addAdvertencia(msg);
                    banderaReqRetencion = false;

                    context.execute("procesandoDlg.hide()");
                } else if ((ejbFacadeRetencion.getItemsRetencionesfindByEstadoCodigoCompra('I', compraSel.getCodigo()).size() > 0) || (ejbFacadeRetencion.getItemsRetencionesfindByEstadoCodigoCompra('A', compraSel.getCodigo()).size() > 0)) {

                    //System.out.println("LA FACTURA YA ESTA ASIGNADO DE RETENCION");
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaAsignadaRetencion");
                    MuestraMensaje.addAdvertencia(msg);
                    banderaReqRetencion = false;

                } else if (ejbFacadeRetencion.getItemsRetencionesfindByEstadoCodigoCompra('E', compraSel.getCodigo()).size() > 0) {

                    banderaReqRetencion = true;
                    context.execute("procesandoDlg.hide()");
                    opcionGeRet = 2;
                    //System.out.println("LA FACTURA ESTA EN PROCESO DE RETENCION  " + opcionGeRet);
                } else {

                    //System.out.println("LA FACTURA RECIEN INICIA PROCESO DE RETENCION");
                    banderaReqRetencion = true;
                    opcionGeRet = 1;
                    context.execute("procesandoDlg.hide()");
                }

                context.execute("procesandoDlg.hide()");

            }
        } catch (Exception e) {
        }
        return banderaReqRetencion;
    }

    /**
     * @param banderaReqRetencion the banderaReqRetencion to set
     */
    public void setBanderaReqRetencion(boolean banderaReqRetencion) {
        this.banderaReqRetencion = banderaReqRetencion;
    }

    /**
     * @return the itemsTipoRetencions
     */
    public List<TipoRetencion> getItemsTipoRetencions() {
        return itemsTipoRetencions;
    }

    /**
     * @param itemsTipoRetencions the itemsTipoRetencions to set
     */
    public void setItemsTipoRetencions(List<TipoRetencion> itemsTipoRetencions) {
        this.itemsTipoRetencions = itemsTipoRetencions;
    }

    /**
     * @return the tipoRetencion
     */
    public TipoRetencion getTipoRetencion() {
        return tipoRetencion;
    }

    /**
     * @param tipoRetencion the tipoRetencion to set
     */
    public void setTipoRetencion(TipoRetencion tipoRetencion) {
        this.tipoRetencion = tipoRetencion;
    }

    /**
     * @return the totalRetenido
     */
    public BigDecimal getTotalRetenido() {
        return totalRetenido;
    }

    /**
     * @param totalRetenido the totalRetenido to set
     */
    public void setTotalRetenido(BigDecimal totalRetenido) {
        this.totalRetenido = totalRetenido;
    }

    /**
     * @return the retencion
     */
    public Retencion getRetencion() {
        return retencion;
    }

    /**
     * @param retencion the retencion to set
     */
    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }

    /**
     * @return the retencionDetalle
     */
    public RetencionDetalle getRetencionDetalle() {
        return retencionDetalle;
    }

    /**
     * @param retencionDetalle the retencionDetalle to set
     */
    public void setRetencionDetalle(RetencionDetalle retencionDetalle) {
        this.retencionDetalle = retencionDetalle;
    }

    /**
     * @return the itemsRetencionDetalles
     */
    public List<RetencionDetalle> getItemsRetencionDetalles() {
        return itemsRetencionDetalles;
    }

    /**
     * @param itemsRetencionDetalles the itemsRetencionDetalles to set
     */
    public void setItemsRetencionDetalles(List<RetencionDetalle> itemsRetencionDetalles) {
        this.itemsRetencionDetalles = itemsRetencionDetalles;
    }

    /**
     * @return the totalSumaRetenido
     */
    public BigDecimal getTotalSumaRetenido() {
        return totalSumaRetenido;
    }

    /**
     * @param totalSumaRetenido the totalSumaRetenido to set
     */
    public void setTotalSumaRetenido(BigDecimal totalSumaRetenido) {
        this.totalSumaRetenido = totalSumaRetenido;
    }

    /**
     * @return the fechaRetencion
     */
    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    /**
     * @param fechaRetencion the fechaRetencion to set
     */
    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    /**
     * @return the fechaRetencionTs
     */
    public Timestamp getFechaRetencionTs() {
        return fechaRetencionTs;
    }

    /**
     * @param fechaRetencionTs the fechaRetencionTs to set
     */
    public void setFechaRetencionTs(Timestamp fechaRetencionTs) {
        this.fechaRetencionTs = fechaRetencionTs;
    }

    /**
     * @return the codigoRetencion
     */
    public Long getCodigoRetencion() {
        return codigoRetencion;
    }

    /**
     * @param codigoRetencion the codigoRetencion to set
     */
    public void setCodigoRetencion(Long codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    /**
     * @return the opcionGeRet
     */
    public int getOpcionGeRet() {
        return opcionGeRet;
    }

    /**
     * @param opcionGeRet the opcionGeRet to set
     */
    public void setOpcionGeRet(int opcionGeRet) {
        this.opcionGeRet = opcionGeRet;
    }

    /**
     * @return the banderaValorSTRete
     */
    public boolean isBanderaValorSTRete() {

        BigDecimal stV = new BigDecimal("0.00");
        banderaValorSTRete = false;
        for (int i = 0; i < itemsRetencionDetalles.size(); i++) {
            stV = stV.add(itemsRetencionDetalles.get(i).getTotal());
        }
        if (stV.compareTo(BigDecimal.ZERO) > 0) {
            banderaValorSTRete = true;
        }
        return banderaValorSTRete;
    }

    /**
     * @param banderaValorSTRete the banderaValorSTRete to set
     */
    public void setBanderaValorSTRete(boolean banderaValorSTRete) {
        this.banderaValorSTRete = banderaValorSTRete;
    }

    /**
     * @return the retencionDetalleSel
     */
    public RetencionDetalle getRetencionDetalleSel() {
        return retencionDetalleSel;
    }

    /**
     * @param retencionDetalleSel the retencionDetalleSel to set
     */
    public void setRetencionDetalleSel(RetencionDetalle retencionDetalleSel) {
        this.retencionDetalleSel = retencionDetalleSel;
    }

    /**
     * @return the generaReportegg
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
     * @return the codigoCompraImpr
     */
    public Long getCodigoCompraImpr() {
        return codigoCompraImpr;
    }

    /**
     * @param codigoCompraImpr the codigoCompraImpr to set
     */
    public void setCodigoCompraImpr(Long codigoCompraImpr) {
        this.codigoCompraImpr = codigoCompraImpr;
    }

    /**
     * @return the codigoTDRD
     */
    public Long getCodigoTDRD() {
        return codigoTDRD;
    }

    /**
     * @param codigoTDRD the codigoTDRD to set
     */
    public void setCodigoTDRD(Long codigoTDRD) {
        this.codigoTDRD = codigoTDRD;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaRegistroTs
     */
    public Timestamp getFechaRegistroTs() {
        return fechaRegistroTs;
    }

    /**
     * @param fechaRegistroTs the fechaRegistroTs to set
     */
    public void setFechaRegistroTs(Timestamp fechaRegistroTs) {
        this.fechaRegistroTs = fechaRegistroTs;
    }

    /**
     * @return the fechaContabilizacion
     */
    public Date getFechaContabilizacion() {
        return fechaContabilizacion;
    }

    /**
     * @param fechaContabilizacion the fechaContabilizacion to set
     */
    public void setFechaContabilizacion(Date fechaContabilizacion) {
        this.fechaContabilizacion = fechaContabilizacion;
    }

    /**
     * @return the fechaContabilizacionTs
     */
    public Timestamp getFechaContabilizacionTs() {
        return fechaContabilizacionTs;
    }

    /**
     * @param fechaContabilizacionTs the fechaContabilizacionTs to set
     */
    public void setFechaContabilizacionTs(Timestamp fechaContabilizacionTs) {
        this.fechaContabilizacionTs = fechaContabilizacionTs;
    }

    /**
     * @return the totalCompraContabilizada
     */
    public BigDecimal getTotalCompraContabilizada() {
        return totalCompraContabilizada;
    }

    /**
     * @param totalCompraContabilizada the totalCompraContabilizada to set
     */
    public void setTotalCompraContabilizada(BigDecimal totalCompraContabilizada) {
        this.totalCompraContabilizada = totalCompraContabilizada;
    }

    /**
     * @return the codigoComprobanteContabilizacion
     */
    public Long getCodigoComprobanteContabilizacion() {
        return codigoComprobanteContabilizacion;
    }

    /**
     * @param codigoComprobanteContabilizacion the
     * codigoComprobanteContabilizacion to set
     */
    public void setCodigoComprobanteContabilizacion(Long codigoComprobanteContabilizacion) {
        this.codigoComprobanteContabilizacion = codigoComprobanteContabilizacion;
    }

    /**
     * @return the numeroComprobanteContabilizacion
     */
    public String getNumeroComprobanteContabilizacion() {
        return numeroComprobanteContabilizacion;
    }

    /**
     * @param numeroComprobanteContabilizacion the
     * numeroComprobanteContabilizacion to set
     */
    public void setNumeroComprobanteContabilizacion(String numeroComprobanteContabilizacion) {
        this.numeroComprobanteContabilizacion = numeroComprobanteContabilizacion;
    }

    /**
     * @return the codigoComprobanteRetencion
     */
    public Long getCodigoComprobanteRetencion() {
        return codigoComprobanteRetencion;
    }

    /**
     * @param codigoComprobanteRetencion the codigoComprobanteRetencion to set
     */
    public void setCodigoComprobanteRetencion(Long codigoComprobanteRetencion) {
        this.codigoComprobanteRetencion = codigoComprobanteRetencion;
    }

    /**
     * @return the banderaReqRetencionDos
     */
    public boolean isBanderaReqRetencionDos() {

        banderaReqRetencionDos = false;
        //opcionGeRet = 0;
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (compraSel != null) {
                if (String.valueOf(compraSel.getEstado()).equals("R")) {
                    //System.out.println("LA FACTURA YA ESTA RETENIDA Y RECIEN INICIA PROCESO DE CONTABILIZACION");
                    banderaReqRetencionDos = true;
                    context.execute("procesandoDlg.hide()");

                } else if (String.valueOf(compraSel.getEstado()).equals("N")) {
                    //System.out.println("LA FACTURA YA ESTA con CONTABILIZACION");
                    banderaReqRetencionDos = true;
                    context.execute("procesandoDlg.hide()");
                } else if (String.valueOf(compraSel.getEstado()).equals("I")) {
                    //System.out.println("LA FACTURAA NO PUEDE CONTABILIZARSE ");
                    banderaReqRetencionDos = false;
                    context.execute("procesandoDlg.hide()");
                    //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaNoRetenida");
                    // MuestraMensaje.addAdvertencia(msg);
                }

                context.execute("procesandoDlg.hide()");

            }
        } catch (Exception e) {
        }
        return banderaReqRetencionDos;
    }

    //----------------------------------------------------------------------------------------
    // -- ACCIONES DE CAMBIO DE COMBO
    public void cambiaArticulo() {
        if (compraDetalleSel != null) {
            this.subtotalDetalleCompra = compraDetalleSel.getSubtotal();
            this.ivaDetalleCompra = compraDetalleSel.getIva();
            this.totalDetalleCompra = compraDetalleSel.getTotal();
            this.tipoRetencion = null;
            this.baseImponible = new BigDecimal("0.00");
            this.porcentajeRetencion = new BigDecimal("0.00");
            this.totalRetenido = new BigDecimal("0.00");
            this.compraDetalle = compraDetalleSel;
        }
    }

    public void cambiaCodigoSustento() {
        /*if (this.sustentoTributario.getEsParaLiquidacion() == 'S') {
         this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsLiquidacionEliminado('S', 'N');
         } else {
         this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsDocumentosProveedorEliminado(proveedor.getCodigo(), 'N');
         }*/
        this.setItemsCuentaGrupoArticulo(ejbFacadeCuentaContableGruArtCom.getItemsGruArtElimiando('N'));
        esFacturaElectronica = (this.esFacturaElectronica == null) ? "N" : esFacturaElectronica;

        if (this.sustentoTributario.getEsParaLiquidacion() == 'S' && this.esFacturaElectronica.charAt(0) == 'N') {
            //System.out.println("Entro 1 cambio sustento");
            this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsLiquidacionEliminado('S', 'N');
        } else if (this.esFacturaElectronica.equals("N")) {
            //System.out.println("Entro 2 cambio sustento");
            this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsDocumentosProveedorEliminado(proveedor.getCodigo(), 'N');
        } else if (this.esFacturaElectronica.equals("S")) {
            //System.out.println("Entro 3 cambio sustento");
            this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsTipoComprobanteEliminado('N');
        }
        //System.out.println("Salio cambio sustento");

    }

    public void cambiaEsFacturaElentronica() {
         this.setItemsCuentaGrupoArticulo(ejbFacadeCuentaContableGruArtCom.getItemsGruArtElimiando('N'));
        if (this.sustentoTributario.getEsParaLiquidacion() == 'S' && this.esFacturaElectronica.equals("N")) {
            //System.out.println("Entro 1 cambio factura");
            this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsLiquidacionEliminado('S', 'N');
        } else if (this.esFacturaElectronica.charAt(0) == 'N') {
            //System.out.println("Entro 2 cambio factura");
            this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsDocumentosProveedorEliminado(proveedor.getCodigo(), 'N');
        } else if (this.esFacturaElectronica.charAt(0) == 'S') {
            //System.out.println("Entro 3 cambio factura");
            this.itemsTipoComprobanteCompras = this.ejbFacadeTipoComprobanteCompra.getItemsTipoComprobanteEliminado('N');
        }
        //System.out.println("Salio cambio factura");
    }

    /**
     * @param banderaReqRetencionDos the banderaReqRetencionDos to set
     */
    public void setBanderaReqRetencionDos(boolean banderaReqRetencionDos) {
        this.banderaReqRetencionDos = banderaReqRetencionDos;
    }

    /**
     * @return the numeroTDRD
     */
    public String getNumeroTDRD() {
        return numeroTDRD;
    }

    /**
     * @param numeroTDRD the numeroTDRD to set
     */
    public void setNumeroTDRD(String numeroTDRD) {
        this.numeroTDRD = numeroTDRD;
    }

    /**
     * @return the codigoComprobanteConAux
     */
    public Long getCodigoComprobanteConAux() {
        return codigoComprobanteConAux;
    }

    /**
     * @param codigoComprobanteConAux the codigoComprobanteConAux to set
     */
    public void setCodigoComprobanteConAux(Long codigoComprobanteConAux) {
        this.codigoComprobanteConAux = codigoComprobanteConAux;
    }

    /**
     * @return the banderaDocRetExistente
     */
    public boolean isBanderaDocRetExistente() {
        return banderaDocRetExistente;
    }

    /**
     * @param banderaDocRetExistente the banderaDocRetExistente to set
     */
    public void setBanderaDocRetExistente(boolean banderaDocRetExistente) {
        this.banderaDocRetExistente = banderaDocRetExistente;
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
     * @return the baseImponible
     */
    public BigDecimal getBaseImponible() {
        return baseImponible;
    }

    /**
     * @param baseImponible the baseImponible to set
     */
    public void setBaseImponible(BigDecimal baseImponible) {
        this.baseImponible = baseImponible;
    }

    /**
     * @return the subtotalDetalleCompra
     */
    public BigDecimal getSubtotalDetalleCompra() {
        return subtotalDetalleCompra;
    }

    /**
     * @param subtotalDetalleCompra the subtotalDetalleCompra to set
     */
    public void setSubtotalDetalleCompra(BigDecimal subtotalDetalleCompra) {
        this.subtotalDetalleCompra = subtotalDetalleCompra;
    }

    /**
     * @return the ivaDetalleCompra
     */
    public BigDecimal getIvaDetalleCompra() {
        return ivaDetalleCompra;
    }

    /**
     * @param ivaDetalleCompra the ivaDetalleCompra to set
     */
    public void setIvaDetalleCompra(BigDecimal ivaDetalleCompra) {
        this.ivaDetalleCompra = ivaDetalleCompra;
    }

    /**
     * @return the totalDetalleCompra
     */
    public BigDecimal getTotalDetalleCompra() {
        return totalDetalleCompra;
    }

    /**
     * @param totalDetalleCompra the totalDetalleCompra to set
     */
    public void setTotalDetalleCompra(BigDecimal totalDetalleCompra) {
        this.totalDetalleCompra = totalDetalleCompra;
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
     * @return the deshabilitaBotonGuardarRetencion
     */
    public boolean isDeshabilitaBotonGuardarRetencion() {
        return deshabilitaBotonGuardarRetencion;
    }

    /**
     * @param deshabilitaBotonGuardarRetencion the
     * deshabilitaBotonGuardarRetencion to set
     */
    public void setDeshabilitaBotonGuardarRetencion(boolean deshabilitaBotonGuardarRetencion) {
        this.deshabilitaBotonGuardarRetencion = deshabilitaBotonGuardarRetencion;
    }

    /**
     * @return the autorizacionElectronica
     */
    public String getAutorizacionElectronica() {
        return autorizacionElectronica;
    }

    /**
     * @param autorizacionElectronica the autorizacionElectronica to set
     */
    public void setAutorizacionElectronica(String autorizacionElectronica) {
        this.autorizacionElectronica = autorizacionElectronica;
    }

    /**
     * @return the esFacturaElectronica
     */
    public String getEsFacturaElectronica() {
        return esFacturaElectronica;
    }

    /**
     * @param esFacturaElectronica the esFacturaElectronica to set
     */
    public void setEsFacturaElectronica(String esFacturaElectronica) {
        this.esFacturaElectronica = esFacturaElectronica;
    }

    /**
     * @return the serieRetencion
     */
    public String getSerieRetencion() {
        return serieRetencion;
    }

    /**
     * @param serieRetencion the serieRetencion to set
     */
    public void setSerieRetencion(String serieRetencion) {
        this.serieRetencion = serieRetencion;
    }

    /**
     * @return the numeroRetencion
     */
    public String getNumeroRetencion() {
        return numeroRetencion;
    }

    /**
     * @param numeroRetencion the numeroRetencion to set
     */
    public void setNumeroRetencion(String numeroRetencion) {
        this.numeroRetencion = numeroRetencion;
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
     * @return the itemsCuentaGrupoArticulo
     */
    public List<CuentaContableGruArtCom> getItemsCuentaGrupoArticulo() {
        return itemsCuentaGrupoArticulo;
    }

    /**
     * @param itemsCuentaGrupoArticulo the itemsCuentaGrupoArticulo to set
     */
    public void setItemsCuentaGrupoArticulo(List<CuentaContableGruArtCom> itemsCuentaGrupoArticulo) {
        this.itemsCuentaGrupoArticulo = itemsCuentaGrupoArticulo;
    }

}
