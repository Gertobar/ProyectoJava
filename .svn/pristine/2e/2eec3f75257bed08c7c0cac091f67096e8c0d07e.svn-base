package ec.renafipse.mks.control.reporteria;

import ec.renafipse.mks.capas.basedatos.EjecutaConsultas;
import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.contables.PeriodoContable;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.creditos.TipoCartera;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.reportes.ParametroEntradaReporte;
import ec.renafipse.mks.modelo.reportes.ReporteDetalle;
import ec.renafipse.mks.modelo.reportes.ReporteGrupo;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionProFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaIfipFacade;
import ec.renafipse.mks.negocio.creditos.TipoCarteraFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.reportes.ParametroEntradaReporteFacade;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import ec.renafipse.mks.negocio.seguridades.RolReporteDetalleFacade;
import java.io.Serializable;
import java.text.ParseException;
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
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

@ManagedBean(name = "reporteadorBean")
@ViewScoped
public class ReporteadorBean extends AbstractController<ReporteGrupo> implements Serializable {

    @EJB
    private ParametroEntradaReporteFacade ejbFacadeParametroEntradaReporte;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private PeriodoContableFacade ejbFacadePeriodoContable;

    @EJB
    private ProductoCreditoMonedaIfipFacade ejbFacadeProductoCreditoMonedaIfip;

    @EJB
    private TipoCarteraFacade ejbFacadeTipoCartera;

    @EJB
    private ConceptoTransaccionProFacade ejbFacadeConceptoTransaccionPro;

    @EJB
    private PlanDeCuentaIfipFacade ejbFacadePlanDeCuentaIfip;

    @EJB
    private RolReporteDetalleFacade ejbFacadeReporteDetalle;

    @EJB
    private ProductoIfipFacade ejbFacadeProductoIfip;

    @EJB
    private RolFacade ejbFacadeRol;

    private String msg;

    //Variable para le ejecucion de Consultas
    private EjecutaConsultas ejecutaConsultas;

    //Reportes
    private List<ReporteGrupo> itemsReporteGrupo;
    private List<ReporteDetalle> itemsReporteDetalle;
    private ReporteGrupo reporteGrupoSel;
    private ReporteDetalle reporteDetalleSel;
    private LlamaSP llamaSP;

    private boolean deshabilitaBotonPdf;
    private boolean deshabilitaBotonExcel;
    private boolean deshabilitaBotonConsultar;

    // Variables de Campos de Fitro para los reportes    
    private PeriodoContable periodoContable;
    private ProductoIfip productoAhorro;
    private Moneda moneda;

    private ConceptoTransaccionPro conceptoTransaccionPro;
    private TipoCartera tipoCartera;
    private IfipAgencia ifipAgencia;
    private ProductoCreditoMonedaIfip productoCredito;
    private Long codigoSocio;
    private String codigo_Socio;

    private String cuentaIncio;
    private String cuentaFin;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaMaxima;
    private String cuentaContableInicio;
    private String cuentaContableFin;

    private List<PeriodoContable> itemsPeriodoContable;
    private List<ProductoIfip> itemsProductosAhorro;
    private List<Moneda> itemsMoneda;
    private List<ConceptoTransaccionPro> itemsConceptoTransaccionPro;
    private List<IfipAgencia> itemsIfipAgencia;
    private List<TipoCartera> itemsTipoCartera;
    private List<ProductoCreditoMonedaIfip> itemsProductosCredito;

    //Variables Tabla Dinamica
    private List<Object[]> itemsDatos;
    private List<Long> itemsDatosFiltro;
    private List<ColumnModel> itemsColumnas;
    private String tamanoScrollWitdh;

    //Variables de deshabilita los filtos
    private boolean deshabilitaCuentaInicio;
    private boolean deshabilitaCuentaFin;
    private boolean deshabilitaFechaInicio;
    private boolean deshabilitaFechaFin;
    private boolean deshabilitaCuentaContableInicio;
    private boolean deshabilitaCuentaContableFin;
    private boolean deshabilitaPeriodoContable;
    private boolean deshabilitaProductoAhorro;
    private boolean deshabilitaProductoCredito;
    private boolean deshabilitaMoneda;
    private boolean deshabilitaConceptoTrnsaccionPro;
    private boolean deshabilitaIfipAgencia;
    private boolean deshabilitaCodigoSocio;
    private boolean deshabilidaCodigo_Socio;
    List<ParametroEntradaReporte> listaParametroEntradaReporte;
    // private StreamedContent exportarXLS;
    // private String excel_name_ruta = " ";
    private String resultadoReporte;

    @PostConstruct
    public void init() {
        ejecutaConsultas = new EjecutaConsultas();
        this.fechaMaxima = new Date();
        this.obtieneReportes();
        this.limpiaFiltros();
        this.deshabilitaFiltros();
        this.generarReporte();

    }

    public void cambiaAgencia() {
        //System.out.println(ifipAgencia.getNombre());
    }

    public void cambiaMoneda() {
        if (!this.deshabilitaProductoAhorro) {
            if (this.moneda != null) {
                this.itemsProductosAhorro = this.ejbFacadeProductoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.moneda.getCodigo(), 'N');
                this.productoAhorro = null;
                this.conceptoTransaccionPro = null;
                this.itemsConceptoTransaccionPro = null;
            } else {
                this.itemsProductosAhorro = null;
                this.productoAhorro = null;
                this.conceptoTransaccionPro = null;
                this.itemsConceptoTransaccionPro = null;
            }
        }

        if (!this.deshabilitaProductoCredito) {
            if (this.moneda != null) {
                this.itemsProductosCredito = this.ejbFacadeProductoCreditoMonedaIfip.getItemsIfipMoneda(ActivacionUsuario.getCodigoIfip(), this.moneda.getCodigo(), 'N');
                this.productoCredito = null;
            } else {
                this.itemsProductosCredito = null;
                this.productoCredito = null;
            }
        }
    }

    public void cambiaProductoAhorro() {
        if (!this.deshabilitaConceptoTrnsaccionPro) {
            if (this.moneda != null && this.productoAhorro != null) {
                this.itemsConceptoTransaccionPro = this.ejbFacadeConceptoTransaccionPro.getItemsCodigoTipoProductoMoneda(this.productoAhorro.getProductoIfipPK().getCodigoTipoProducto(), this.moneda.getCodigo());
                this.conceptoTransaccionPro = null;
            } else {
                this.itemsConceptoTransaccionPro = null;
                this.conceptoTransaccionPro = null;
            }
        }
    }

    /**
     * Deshabilita los cuadros de control de los filtros.
     */
    private void deshabilitaFiltros() {
        deshabilitaCuentaInicio = true;
        deshabilitaCuentaFin = true;
        deshabilitaFechaInicio = true;
        deshabilitaFechaFin = true;
        deshabilitaCuentaContableInicio = true;
        deshabilitaCuentaContableFin = true;
        deshabilitaPeriodoContable = true;
        deshabilitaProductoAhorro = true;
        deshabilitaProductoCredito = true;
        deshabilitaMoneda = true;
        deshabilitaConceptoTrnsaccionPro = true;
        deshabilitaIfipAgencia = true;
        deshabilidaCodigo_Socio = true;

    }

    private void limpiaFiltros() {
        this.conceptoTransaccionPro = null;
        this.cuentaContableFin = null;
        this.cuentaContableInicio = null;
        this.cuentaFin = null;
        this.cuentaIncio = null;
        periodoContable = null;
        productoAhorro = null;
        moneda = null;
        tipoCartera = null;
        ifipAgencia = null;
        productoCredito = null;
        fechaInicio = null;
        fechaFin = null;
        codigoSocio = (long) 0;
    }

    /**
     * Obtiene los Reportes del Rol
     */
    private void obtieneReportes() {
        this.deshabilitaBotonConsultar = true;
        this.deshabilitaBotonPdf = true;
        this.deshabilitaBotonExcel = true;
        this.itemsReporteGrupo = this.ejbFacadeReporteDetalle.getItemsReporteRolGrupoEliminado(ActivacionUsuario.getCodigoRol(), 'N');
        this.reporteDetalleSel = null;
        ////System.out.println("itemsReporteGrupo " + itemsReporteGrupo + " ActivacionUsuario.getCodigoRol() " + ActivacionUsuario.getCodigoRol());
        /*        if (!itemsReporteGrupo.isEmpty()) {
         this.itemsReporteDetalle = this.ejbFacadeReporteDetalle.getItemsReporteDetalleRolGrupoEliminado(ActivacionUsuario.getCodigoRol(), itemsReporteGrupo.get(0).getCodigo(), 'N');
         }*/
    }

    /**
     * Obtiene los Reportes del Grupo y de acuerdo al Rol.
     */
    public void obtieneReportesDetalle() {
        this.deshabilitaFiltros();
        if (this.reporteGrupoSel != null) {
            this.itemsReporteDetalle = this.ejbFacadeReporteDetalle.getItemsReporteDetalleRolGrupoEliminado(ActivacionUsuario.getCodigoRol(), reporteGrupoSel.getCodigo(), 'N');
            this.reporteDetalleSel = null;
        }
    }

    /**
     * Metodo que refresca los filtros de consulta y botones que pueden se
     * accionados Es usado cuando el usuario selecciona el Reporte a Ejecutar.
     */
    public void refrescaHabilitaListaCampos() {

        this.deshabilitaFiltros();
        this.limpiaFiltros();
        this.deshabilitaBotonConsultar = false;
        this.deshabilitaBotonPdf = true;
        this.deshabilitaBotonExcel = true;
        this.itemsDatos = null;
        this.itemsColumnas = null;
        this.setItemsDatosFiltro(null);

        if (this.reporteDetalleSel != null) {

            this.deshabilitaBotonExcel = (this.reporteDetalleSel.getExcel() == 'N');
            this.deshabilitaBotonPdf = (this.reporteDetalleSel.getPdf() == 'N');

            listaParametroEntradaReporte = this.ejbFacadeParametroEntradaReporte.getItemsCodigoReporte(this.reporteDetalleSel.getCodigo());
            for (ParametroEntradaReporte per : listaParametroEntradaReporte) {
                if (per.getCodigoParametro().getCodigo().equals("PERIODO")) {
                    this.deshabilitaPeriodoContable = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("PRODUCTOAHO")) {
                    this.deshabilitaProductoAhorro = false;
                    this.deshabilitaMoneda = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("MONEDA")) {
                    this.deshabilitaMoneda = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("FECHAINICIO")) {
                    this.deshabilitaFechaInicio = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("FECHAFIN")) {
                    this.deshabilitaFechaFin = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("CUENTACONTABLEINI")) {
                    this.deshabilitaCuentaContableInicio = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("CUENTACONTABLEFIN")) {
                    this.deshabilitaCuentaContableFin = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("CONCEPTO")) {
                    this.deshabilitaProductoAhorro = false;
                    this.deshabilitaMoneda = false;
                    this.deshabilitaConceptoTrnsaccionPro = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("AGENCIA")) {
                    this.deshabilitaIfipAgencia = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("CUENTAINICIO")) {
                    this.deshabilitaCuentaInicio = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("CUENTAFIN")) {
                    this.deshabilitaCuentaFin = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("PRODUCTOCRE")) {
                    this.deshabilitaProductoCredito = false;
                    this.deshabilitaMoneda = false;
                }

                if (per.getCodigoParametro().getCodigo().equals("CODIGOSOC")) {
                    this.deshabilidaCodigo_Socio = false;
                }

            }
        }

    }

    /**
     * Genera los Reportes.
     */
    public void generarReporte() {
        setResultadoReporte(null);
        if (reporteDetalleSel != null) {

            if (validaCamposConsulta()) {

                try {
                    // Veririficando si tiene que llamar a un metodo antes de sacar el reporte
                    if (reporteDetalleSel.getNombreMetodoLlamar() != null) {
                        if (reporteDetalleSel.getNombreMetodoLlamar().equals("genera_balance_sucursal")) {
                            this.genera_balance_sucursal();
                        }
                    }

                    ejecutaConsultas = new EjecutaConsultas();
                    // Cargando la conexion de BD
                    getEjecutaConsultas().cargaConexion();

                    // Indicando que no cierre la conexion de la base de datos
                    getEjecutaConsultas().setCerrarConexion(false);

                    // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                    getEjecutaConsultas().autoCommit(false);

                    getEjecutaConsultas().setQuery(this.reporteDetalleSel.getConsulta());
                    getEjecutaConsultas().setListParametrosEntrada(new ArrayList<Object[]>());

                    getEjecutaConsultas().setFormatoDecimales((this.reporteDetalleSel.getFormatoDecimales() == 'S'));
                    getEjecutaConsultas().setFormatoFechaHora((this.reporteDetalleSel.getFormatoFechaHora() == 'S'));
                    // Colocando Parametros           
                    this.colocaParametros();

                    // Ejecutando la Consulta
                    getEjecutaConsultas().ejecutaQuery();

                    getEjecutaConsultas().cerrarConexion();
                    getEjecutaConsultas().setConexionBD(null);

                    //Creando la Tabla.
                    this.itemsDatos = this.getEjecutaConsultas().getListFilas();
                    this.itemsColumnas = new ArrayList<ColumnModel>();
                    this.itemsDatosFiltro = new ArrayList<Long>();

                    ////System.out.println("this.getEjecutaConsultas().getListTIpoDato() "+this.getEjecutaConsultas().getListTIpoDato());
                    for (int i = 0; i < this.getEjecutaConsultas().getListColumnas().size(); i++) {
                        itemsColumnas.add(new ColumnModel(this.getEjecutaConsultas().getListColumnas().get(i).toUpperCase(), i, this.getEjecutaConsultas().getListTipoDato().get(i)));
                        itemsDatosFiltro.add(((long) i));
                    }

                    if (this.getEjecutaConsultas().getListColumnas().size() > 4) {
                        this.tamanoScrollWitdh = String.valueOf(Math.round((4 * 100) / this.getEjecutaConsultas().getListColumnas().size())) + "%";
                        this.tamanoScrollWitdh = "4";
                    } else {
                        this.tamanoScrollWitdh = String.valueOf(this.getEjecutaConsultas().getListColumnas().size());
                    }
                    setResultadoReporte("RESULTADO DEL REPORTE: "+getEjecutaConsultas().getListFilas().size()+" REGISTRO(S)");
                    // generarXLS();
                } catch (Exception ex) {
                    //System.out.println("Error " + ex.toString());
                    ex.printStackTrace();

                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorGenerarConsulta");
                    MuestraMensaje.addError(msg);
                }
            }
        }
    }

    /**
     * Colocando los Parametros para la Consulta.
     */
    private void colocaParametros() {

        for (ParametroEntradaReporte per : this.listaParametroEntradaReporte) {
            if (per.getCodigoParametro().getCodigo().equals("PERIODO")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"PERIODO", this.periodoContable.getCodigo()});
            }

            if (per.getCodigoParametro().getCodigo().equals("PRODUCTOAHO")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"PRODUCTOAHO", this.productoAhorro.getProductoIfipPK().getCodigoTipoProducto()});
            }

            if (per.getCodigoParametro().getCodigo().equals("MONEDA")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"MONEDA", this.moneda.getCodigo()});
            }

            if (per.getCodigoParametro().getCodigo().equals("FECHAINICIO")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"FECHAINICIO", new java.sql.Timestamp(this.fechaInicio.getTime())});
            }

            if (per.getCodigoParametro().getCodigo().equals("FECHAFIN")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"FECHAFIN", new java.sql.Timestamp(this.fechaFin.getTime())});
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTACONTABLEINI")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"CUENTACONTABLEINI", this.cuentaContableInicio});
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTACONTABLEFIN")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"CUENTACONTABLEFIN", this.cuentaContableFin});
            }

            if (per.getCodigoParametro().getCodigo().equals("CONCEPTO")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"CONCEPTO", this.conceptoTransaccionPro.getConceptoTransaccion().getCodigo()});
            }

            if (per.getCodigoParametro().getCodigo().equals("AGENCIA")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"CONCEPTO", this.ifipAgencia.getCodigo()});
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTAINICIO")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"CUENTAINICIO", this.cuentaIncio});
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTAFIN")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"CUENTAFIN", this.cuentaFin});
            }

            if (per.getCodigoParametro().getCodigo().equals("PRODUCTOCRE")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"PRODUCTOCRE", this.productoCredito.getProductoCreditoMonedaIfipPK().getCodigoProducto()});
            }

            if (per.getCodigoParametro().getCodigo().equals("IFIP")) {
                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"PRODUCTOCRE", ActivacionUsuario.getCodigoIfip()});
            }
            if (per.getCodigoParametro().getCodigo().equals("CODIGOSOC")) {

                getEjecutaConsultas().getListParametrosEntrada().add(new Object[]{"CODIGOSOC", this.codigo_Socio});
            }

        }
    }

    private boolean validaCamposConsulta() {
        boolean correcto = true;
        this.msg = null;

        for (ParametroEntradaReporte per : this.listaParametroEntradaReporte) {

            if (per.getCodigoParametro().getCodigo().equals("PERIODO")) {
                correcto = !(periodoContable == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("PRODUCTOAHO")) {
                correcto = !(productoAhorro == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("MONEDA")) {
                correcto = !(moneda == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("FECHAINICIO")) {
                correcto = !(fechaInicio == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("FECHAFIN")) {
                correcto = !(fechaFin == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTACONTABLEINI")) {
                correcto = !(cuentaContableInicio == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTACONTABLEFIN")) {
                correcto = !(cuentaContableFin == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("CONCEPTO")) {
                correcto = !(conceptoTransaccionPro == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("AGENCIA")) {
                correcto = !(ifipAgencia == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTAINICIO")) {
                correcto = !(cuentaIncio == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("CUENTAFIN")) {
                correcto = !(cuentaFin == null);
            }

            if (per.getCodigoParametro().getCodigo().equals("PRODUCTOCRE")) {
                correcto = !(productoCredito == null);
            }
            if (per.getCodigoParametro().getCodigo().equals("CODIGOSOC")) {
                correcto = !(codigo_Socio == null);
            }

            if (!correcto) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneTodosLosParametros");
                MuestraMensaje.addError(msg);
                return correcto;
            }
        }

        //System.out.println("Correcto " + correcto);
        return correcto;
    }

    public void postProcessXLS(Object document) {

        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet hoja = wb.getSheetAt(0);

        HSSFRow fila = hoja.getRow(0);

        HSSFCellStyle celdaStyle = wb.createCellStyle();
        celdaStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        celdaStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFCell celda;

        for (int i = 0; i < fila.getPhysicalNumberOfCells(); i++) {
            celda = fila.getCell(i);
            celda.setCellStyle(celdaStyle);
        }

        HSSFDataFormat format;
        celdaStyle = wb.createCellStyle();

        int ultimaFila = hoja.getLastRowNum();
        SimpleDateFormat formatoFechas;

        for (int c = 1; c <= ultimaFila; c++) {
            fila = hoja.getRow(c);
            for (int i = 0; i < fila.getPhysicalNumberOfCells(); i++) {
                celda = fila.getCell(i);
                if (this.getEjecutaConsultas().getListTipoDato().get(i) == 3) {
                    try {
                        if (this.reporteDetalleSel.getFormatoFechaHora() == 'S') {
                            format = wb.createDataFormat();
                            celdaStyle.setDataFormat(format.getFormat("mm/DD/yyyy hh:MM:ss"));
                            formatoFechas = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
                        } else {
                            format = wb.createDataFormat();
                            celdaStyle.setDataFormat(format.getFormat("mm/DD/yyyy"));
                            formatoFechas = new SimpleDateFormat("dd/MM/yyyy");
                        }
                        if (!(celda.getStringCellValue() == null || celda.getStringCellValue().equals(""))) {
                            celda.setCellValue(formatoFechas.parse(celda.getStringCellValue().toString()));
                        }
                        celda.setCellStyle(celdaStyle);
                    } catch (ParseException ex) {
                        Logger.getLogger(ReporteadorBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (this.getEjecutaConsultas().getListTipoDato().get(i) == 1 || this.getEjecutaConsultas().getListTipoDato().get(i) == 2) {
                    if (celda != null) {
                        if (!celda.equals("")) {
                            celda.setCellValue(new Double(celda.getStringCellValue().toString().replaceAll(",", "").trim()));
                        }
                    }
                    //celda.setCellType((this.getEjecutaConsultas().getListTipoDato().get(i) == 1 || this.getEjecutaConsultas().getListTipoDato().get(i) == 2) ? Cell.CELL_TYPE_NUMERIC : Cell.CELL_TYPE_STRING);
                }/*else if (this.getEjecutaConsultas().getListTipoDato().get(i) == 2 )
                 {
                 celda.setCellValue(Long.parseLong(celda.getStringCellValue().toString()));
                 }*/

            }

        }

        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Generando el balance por sucursal
     */
    private void genera_balance_sucursal() {
        //Instancion el proceso de llama Store Procedure
        setLlamaSP(new LlamaSP());
        // //System.out.println("Guarda Formulario");
        try {

            getLlamaSP().cargaConexion();
            getLlamaSP().setCerrarConexion(false);
            getLlamaSP().autoCommit(false);
            getLlamaSP().setNombreSP("mks_contables.p_crea_indicadores_financieros");
            getLlamaSP().setNumeroParametros(3);
            getLlamaSP().setListParametrosEntrada(new ArrayList<Object[]>());
            getLlamaSP().getListParametrosEntrada().add(new Object[]{"p_fecha_corte", new java.sql.Timestamp(this.fechaInicio.getTime())});
            getLlamaSP().getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            getLlamaSP().getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", this.ifipAgencia.getCodigo()});
            getLlamaSP().setListParametrosSalida(new ArrayList<Object[]>());
            getLlamaSP().invocaSP();
            if (getLlamaSP().isEjecucionCorrecta()) {
                getLlamaSP().commit();
                getLlamaSP().cerrarConexion();
                getLlamaSP().setConexionBD(null);
            } else {

                getLlamaSP().rollback();
                getLlamaSP().cerrarConexion();
                getLlamaSP().setConexionBD(null);

            }

        } catch (Exception ex) {

            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"licitudFondosBean", "guardaLicitud", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the itemsColumnas
     */
    public List<ColumnModel> getItemsColumnas() {
        return itemsColumnas;
    }

    /**
     * @param itemsColumnas the itemsColumnas to set
     */
    public void setItemsColumnas(List<ColumnModel> itemsColumnas) {
        this.itemsColumnas = itemsColumnas;
    }

    /**
     * @return the tamanoScrollWitdh
     */
    public String getTamanoScrollWitdh() {
        return tamanoScrollWitdh;
    }

    /**
     * @param tamanoScrollWitdh the tamanoScrollWitdh to set
     */
    public void setTamanoScrollWitdh(String tamanoScrollWitdh) {
        this.tamanoScrollWitdh = tamanoScrollWitdh;
    }

    /**
     * @return the itemsDatosFiltro
     */
    public List<Long> getItemsDatosFiltro() {
        return itemsDatosFiltro;
    }

    /**
     * @param itemsDatosFiltro the itemsDatosFiltro to set
     */
    public void setItemsDatosFiltro(List<Long> itemsDatosFiltro) {
        this.itemsDatosFiltro = itemsDatosFiltro;
    }

    /**
     * @return the deshabilitaCodigoSocio
     */
    public boolean isDeshabilitaCodigoSocio() {
        return deshabilitaCodigoSocio;
    }

    /**
     * @param deshabilitaCodigoSocio the deshabilitaCodigoSocio to set
     */
    public void setDeshabilitaCodigoSocio(boolean deshabilitaCodigoSocio) {
        this.deshabilitaCodigoSocio = deshabilitaCodigoSocio;
    }

    /**
     * @return the codigoSocio
     */
    public long getCodigoSocio() {
        return codigoSocio;
    }

    /**
     * @param codigoSocio the codigoSocio to set
     */
    public void setCodigoSocio(long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    /**
     * @return the codigo_Socio
     */
    public String getCodigo_Socio() {
        return codigo_Socio;
    }

    /**
     * @param codigo_Socio the codigo_Socio to set
     */
    public void setCodigo_Socio(String codigo_Socio) {
        this.codigo_Socio = codigo_Socio;
    }

    /**
     * @return the deshabilidaCodigo_Socio
     */
    public boolean isDeshabilidaCodigo_Socio() {
        return deshabilidaCodigo_Socio;
    }

    /**
     * @param deshabilidaCodigo_Socio the deshabilidaCodigo_Socio to set
     */
    public void setDeshabilidaCodigo_Socio(boolean deshabilidaCodigo_Socio) {
        this.deshabilidaCodigo_Socio = deshabilidaCodigo_Socio;
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

    static public class ColumnModel implements Serializable {

        private String fila;
        private int property;
        private int tipoDato;

        public ColumnModel(String fila, int property, int tipodato) {
            this.fila = fila;
            this.property = property;
            this.tipoDato = tipodato;
            ////System.out.println(" tipodato " + tipodato);
        }

        public String getHeader() {
            return fila;
        }

        public int getProperty() {
            return property;
        }

        /**
         * @return the tipoDato
         */
        public int getTipoDato() {
            return tipoDato;
        }

        /**
         * @param tipoDato the tipoDato to set
         */
        public void setTipoDato(int tipoDato) {
            this.tipoDato = tipoDato;
        }
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
     * @return the itemsPeriodoContable
     */
    public List<PeriodoContable> getItemsPeriodoContable() {
        this.itemsPeriodoContable = this.ejbFacadePeriodoContable.findAll();
        return itemsPeriodoContable;
    }

    /**
     * @param itemsPeriodoContable the itemsPeriodoContable to set
     */
    public void setItemsPeriodoContable(List<PeriodoContable> itemsPeriodoContable) {
        this.itemsPeriodoContable = itemsPeriodoContable;
    }

    /**
     * @return the itemsMoneda
     */
    public List<Moneda> getItemsMoneda() {
        this.itemsMoneda = this.ejbFacadeMoneda.findAll();
        return itemsMoneda;
    }

    /**
     * @param itemsMoneda the itemsMoneda to set
     */
    public void setItemsMoneda(List<Moneda> itemsMoneda) {
        this.itemsMoneda = itemsMoneda;
    }

    /**
     * @return the itemsConceptoTransaccionPro
     */
    public List<ConceptoTransaccionPro> getItemsConceptoTransaccionPro() {
        return itemsConceptoTransaccionPro;
    }

    /**
     * @param itemsConceptoTransaccionPro the itemsConceptoTransaccionPro to set
     */
    public void setItemsConceptoTransaccionPro(List<ConceptoTransaccionPro> itemsConceptoTransaccionPro) {
        this.itemsConceptoTransaccionPro = itemsConceptoTransaccionPro;
    }

    /**
     * @return the itemsIfipAgencia
     */
    public List<IfipAgencia> getItemsIfipAgencia() {
        ////System.out.println("ActivacionUsuario.getCodigoIfip() " + ActivacionUsuario.getCodigoIfip());
        // Verificando si tiene permiso par ver los reportes de todas las agencias
        if (ejbFacadeRol.find(ActivacionUsuario.getCodigoRol()).getPuedeReporteTodasAgencias() == 'S') {
            this.itemsIfipAgencia = this.ejbFacadeIfipAgencia.getItemsIfipAgenciaPorIfip(ActivacionUsuario.getCodigoIfip());
        } else {
            List listaIfipAgencia = new ArrayList<IfipAgencia>();
            listaIfipAgencia.add(this.ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia()));
            this.itemsIfipAgencia = listaIfipAgencia;
        }

        return itemsIfipAgencia;
    }

    /**
     * @param itemsIfipAgencia the itemsIfipAgencia to set
     */
    public void setItemsIfipAgencia(List<IfipAgencia> itemsIfipAgencia) {
        this.itemsIfipAgencia = itemsIfipAgencia;
    }

    /**
     * @return the itemsTipoCartera
     */
    public List<TipoCartera> getItemsTipoCartera() {
        return itemsTipoCartera;
    }

    /**
     * @param itemsTipoCartera the itemsTipoCartera to set
     */
    public void setItemsTipoCartera(List<TipoCartera> itemsTipoCartera) {
        this.itemsTipoCartera = itemsTipoCartera;
    }

    /**
     * @return the itemsProductosCredito
     */
    public List<ProductoCreditoMonedaIfip> getItemsProductosCredito() {
        return itemsProductosCredito;
    }

    /**
     * @param itemsProductosCredito the itemsProductosCredito to set
     */
    public void setItemsProductosCredito(List<ProductoCreditoMonedaIfip> itemsProductosCredito) {
        this.itemsProductosCredito = itemsProductosCredito;
    }

    /**
     * @return the periodoContable
     */
    public PeriodoContable getPeriodoContable() {
        return periodoContable;
    }

    /**
     * @param periodoContable the periodoContable to set
     */
    public void setPeriodoContable(PeriodoContable periodoContable) {
        this.periodoContable = periodoContable;
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
     * @return the cuentaContableInicio
     */
    public String getCuentaContableInicio() {
        return cuentaContableInicio;
    }

    /**
     * @param cuentaContableInicio the cuentaContableInicio to set
     */
    public void setCuentaContableInicio(String cuentaContableInicio) {
        this.cuentaContableInicio = cuentaContableInicio;
    }

    /**
     * @return the cuentaContableFin
     */
    public String getCuentaContableFin() {
        return cuentaContableFin;
    }

    /**
     * @param cuentaContableFin the cuentaContableFin to set
     */
    public void setCuentaContableFin(String cuentaContableFin) {
        this.cuentaContableFin = cuentaContableFin;
    }

    /**
     * @return the conceptoTransaccionPro
     */
    public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    /**
     * @param conceptoTransaccionPro the conceptoTransaccionPro to set
     */
    public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
    }

    /**
     * @return the tipoCartera
     */
    public TipoCartera getTipoCartera() {
        return tipoCartera;
    }

    /**
     * @param tipoCartera the tipoCartera to set
     */
    public void setTipoCartera(TipoCartera tipoCartera) {
        this.tipoCartera = tipoCartera;
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    /**
     * @return the productoCredito
     */
    public ProductoCreditoMonedaIfip getProductoCredito() {
        return productoCredito;
    }

    /**
     * @param productoCredito the productoCredito to set
     */
    public void setProductoCredito(ProductoCreditoMonedaIfip productoCredito) {
        this.productoCredito = productoCredito;
    }

    /**
     * @return the ejecutaConsultas
     */
    public EjecutaConsultas getEjecutaConsultas() {
        return ejecutaConsultas;
    }

    /**
     * @param ejecutaConsultas the ejecutaConsultas to set
     */
    public void setEjecutaConsultas(EjecutaConsultas ejecutaConsultas) {
        this.ejecutaConsultas = ejecutaConsultas;
    }

    /**
     * @return the productoAhorro
     */
    public ProductoIfip getProductoAhorro() {
        return productoAhorro;
    }

    /**
     * @param productoAhorro the productoAhorro to set
     */
    public void setProductoAhorro(ProductoIfip productoAhorro) {
        this.productoAhorro = productoAhorro;
    }

    /**
     * @return the itemsProductosAhorro
     */
    public List<ProductoIfip> getItemsProductosAhorro() {
        return itemsProductosAhorro;
    }

    /**
     * @param itemsProductosAhorro the itemsProductosAhorro to set
     */
    public void setItemsProductosAhorro(List<ProductoIfip> itemsProductosAhorro) {
        this.itemsProductosAhorro = itemsProductosAhorro;
    }

    /**
     * @return the itemsReporteGrupo
     */
    public List<ReporteGrupo> getItemsReporteGrupo() {
        return itemsReporteGrupo;
    }

    /**
     * @param itemsReporteGrupo the itemsReporteGrupo to set
     */
    public void setItemsReporteGrupo(List<ReporteGrupo> itemsReporteGrupo) {
        this.itemsReporteGrupo = itemsReporteGrupo;
    }

    /**
     * @return the itemsReporteDetalle
     */
    public List<ReporteDetalle> getItemsReporteDetalle() {
        return itemsReporteDetalle;
    }

    /**
     * @param itemsReporteDetalle the itemsReporteDetalle to set
     */
    public void setItemsReporteDetalle(List<ReporteDetalle> itemsReporteDetalle) {
        this.itemsReporteDetalle = itemsReporteDetalle;
    }

    /**
     * @return the reporteGrupoSel
     */
    public ReporteGrupo getReporteGrupoSel() {
        return reporteGrupoSel;
    }

    /**
     * @param reporteGrupoSel the reporteGrupoSel to set
     */
    public void setReporteGrupoSel(ReporteGrupo reporteGrupoSel) {
        this.reporteGrupoSel = reporteGrupoSel;
    }

    /**
     * @return the reporteDetalleSel
     */
    public ReporteDetalle getReporteDetalleSel() {
        return reporteDetalleSel;
    }

    /**
     * @param reporteDetalleSel the reporteDetalleSel to set
     */
    public void setReporteDetalleSel(ReporteDetalle reporteDetalleSel) {
        this.reporteDetalleSel = reporteDetalleSel;
    }

    /**
     * @return the deshabilitaBotonPdf
     */
    public boolean isDeshabilitaBotonPdf() {
        return deshabilitaBotonPdf;
    }

    /**
     * @param deshabilitaBotonPdf the deshabilitaBotonPdf to set
     */
    public void setDeshabilitaBotonPdf(boolean deshabilitaBotonPdf) {
        this.deshabilitaBotonPdf = deshabilitaBotonPdf;
    }

    /**
     * @return the deshabilitaBotonExcel
     */
    public boolean isDeshabilitaBotonExcel() {
        return deshabilitaBotonExcel;
    }

    /**
     * @param deshabilitaBotonExcel the deshabilitaBotonExcel to set
     */
    public void setDeshabilitaBotonExcel(boolean deshabilitaBotonExcel) {
        this.deshabilitaBotonExcel = deshabilitaBotonExcel;
    }

    /**
     * @return the deshabilitaBotonConsultar
     */
    public boolean isDeshabilitaBotonConsultar() {
        return deshabilitaBotonConsultar;
    }

    /**
     * @param deshabilitaBotonConsultar the deshabilitaBotonConsultar to set
     */
    public void setDeshabilitaBotonConsultar(boolean deshabilitaBotonConsultar) {
        this.deshabilitaBotonConsultar = deshabilitaBotonConsultar;
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
     * @return the cuentaIncio
     */
    public String getCuentaIncio() {
        return cuentaIncio;
    }

    /**
     * @param cuentaIncio the cuentaIncio to set
     */
    public void setCuentaIncio(String cuentaIncio) {
        this.cuentaIncio = cuentaIncio;
    }

    /**
     * @return the cuentaFin
     */
    public String getCuentaFin() {
        return cuentaFin;
    }

    /**
     * @param cuentaFin the cuentaFin to set
     */
    public void setCuentaFin(String cuentaFin) {
        this.cuentaFin = cuentaFin;
    }

    /**
     * @return the deshabilitaCuentaInicio
     */
    public boolean isDeshabilitaCuentaInicio() {
        return deshabilitaCuentaInicio;
    }

    /**
     * @param deshabilitaCuentaInicio the deshabilitaCuentaInicio to set
     */
    public void setDeshabilitaCuentaInicio(boolean deshabilitaCuentaInicio) {
        this.deshabilitaCuentaInicio = deshabilitaCuentaInicio;
    }

    /**
     * @return the deshabilitaCuentaFin
     */
    public boolean isDeshabilitaCuentaFin() {
        return deshabilitaCuentaFin;
    }

    /**
     * @param deshabilitaCuentaFin the deshabilitaCuentaFin to set
     */
    public void setDeshabilitaCuentaFin(boolean deshabilitaCuentaFin) {
        this.deshabilitaCuentaFin = deshabilitaCuentaFin;
    }

    /**
     * @return the deshabilitaFechaInicio
     */
    public boolean isDeshabilitaFechaInicio() {
        return deshabilitaFechaInicio;
    }

    /**
     * @param deshabilitaFechaInicio the deshabilitaFechaInicio to set
     */
    public void setDeshabilitaFechaInicio(boolean deshabilitaFechaInicio) {
        this.deshabilitaFechaInicio = deshabilitaFechaInicio;
    }

    /**
     * @return the deshabilitaFechaFin
     */
    public boolean isDeshabilitaFechaFin() {
        return deshabilitaFechaFin;
    }

    /**
     * @param deshabilitaFechaFin the deshabilitaFechaFin to set
     */
    public void setDeshabilitaFechaFin(boolean deshabilitaFechaFin) {
        this.deshabilitaFechaFin = deshabilitaFechaFin;
    }

    /**
     * @return the deshabilitaCuentaContableInicio
     */
    public boolean isDeshabilitaCuentaContableInicio() {
        return deshabilitaCuentaContableInicio;
    }

    /**
     * @param deshabilitaCuentaContableInicio the
     * deshabilitaCuentaContableInicio to set
     */
    public void setDeshabilitaCuentaContableInicio(boolean deshabilitaCuentaContableInicio) {
        this.deshabilitaCuentaContableInicio = deshabilitaCuentaContableInicio;
    }

    /**
     * @return the deshabilitaCuentaContableFin
     */
    public boolean isDeshabilitaCuentaContableFin() {
        return deshabilitaCuentaContableFin;
    }

    /**
     * @param deshabilitaCuentaContableFin the deshabilitaCuentaContableFin to
     * set
     */
    public void setDeshabilitaCuentaContableFin(boolean deshabilitaCuentaContableFin) {
        this.deshabilitaCuentaContableFin = deshabilitaCuentaContableFin;
    }

    /**
     * @return the deshabilitaPeriodoContable
     */
    public boolean isDeshabilitaPeriodoContable() {
        return deshabilitaPeriodoContable;
    }

    /**
     * @param deshabilitaPeriodoContable the deshabilitaPeriodoContable to set
     */
    public void setDeshabilitaPeriodoContable(boolean deshabilitaPeriodoContable) {
        this.deshabilitaPeriodoContable = deshabilitaPeriodoContable;
    }

    /**
     * @return the deshabilitaProductoAhorro
     */
    public boolean isDeshabilitaProductoAhorro() {
        return deshabilitaProductoAhorro;
    }

    /**
     * @param deshabilitaProductoAhorro the deshabilitaProductoAhorro to set
     */
    public void setDeshabilitaProductoAhorro(boolean deshabilitaProductoAhorro) {
        this.deshabilitaProductoAhorro = deshabilitaProductoAhorro;
    }

    /**
     * @return the deshabilitaProductoCredito
     */
    public boolean isDeshabilitaProductoCredito() {
        return deshabilitaProductoCredito;
    }

    /**
     * @param deshabilitaProductoCredito the deshabilitaProductoCredito to set
     */
    public void setDeshabilitaProductoCredito(boolean deshabilitaProductoCredito) {
        this.deshabilitaProductoCredito = deshabilitaProductoCredito;
    }

    /**
     * @return the deshabilitaMoneda
     */
    public boolean isDeshabilitaMoneda() {
        return deshabilitaMoneda;
    }

    /**
     * @param deshabilitaMoneda the deshabilitaMoneda to set
     */
    public void setDeshabilitaMoneda(boolean deshabilitaMoneda) {
        this.deshabilitaMoneda = deshabilitaMoneda;
    }

    /**
     * @return the deshabilitaConceptoTrnsaccionPro
     */
    public boolean isDeshabilitaConceptoTrnsaccionPro() {
        return deshabilitaConceptoTrnsaccionPro;
    }

    /**
     * @param deshabilitaConceptoTrnsaccionPro the
     * deshabilitaConceptoTrnsaccionPro to set
     */
    public void setDeshabilitaConceptoTrnsaccionPro(boolean deshabilitaConceptoTrnsaccionPro) {
        this.deshabilitaConceptoTrnsaccionPro = deshabilitaConceptoTrnsaccionPro;
    }

    /**
     * @return the deshabilitaIfipAgencia
     */
    public boolean isDeshabilitaIfipAgencia() {
        return deshabilitaIfipAgencia;
    }

    /**
     * @param deshabilitaIfipAgencia the deshabilitaIfipAgencia to set
     */
    public void setDeshabilitaIfipAgencia(boolean deshabilitaIfipAgencia) {
        this.deshabilitaIfipAgencia = deshabilitaIfipAgencia;
    }

    /**
     * @return the itemsDatos
     */
    public List<Object[]> getItemsDatos() {
        return itemsDatos;
    }

    /**
     * @param itemsDatos the itemsDatos to set
     */
    public void setItemsDatos(List<Object[]> itemsDatos) {
        this.itemsDatos = itemsDatos;
    }

    /**
     * @return the resultadoReporte
     */
    public String getResultadoReporte() {
        return resultadoReporte;
    }

    /**
     * @param resultadoReporte the resultadoReporte to set
     */
    public void setResultadoReporte(String resultadoReporte) {
        this.resultadoReporte = resultadoReporte;
    }

}
