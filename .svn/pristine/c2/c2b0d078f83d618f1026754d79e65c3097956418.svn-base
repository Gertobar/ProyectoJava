package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.EjecucionTransaccionLotDet;
import ec.renafipse.mks.modelo.ahorros.EjecucionTransaccionLotErr;
import ec.renafipse.mks.modelo.ahorros.EjecucionTransaccionLote;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.TmpEjecucionTraLotDet;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionLoteFacade;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.EjecucionTransaccionLoteFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "transaccionLoteBean")
@ViewScoped
public class TransaccionLoteBean extends AbstractController<EjecucionTransaccionLote> implements Serializable {

    @EJB
    private EjecucionTransaccionLoteFacade ejbFacade;

    @EJB
    private ConceptoTransaccionLoteFacade ejbFacadeConceptoTransaccionLote;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private LlamaSP llamaSP;

    // //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//---
    // //-- PARAMETROS PERSONALIZADOS
    private RequestContext context;

    private boolean deshabilitaBotonGuardar;
    private boolean deshabilitaVerTransaccionesError;
    private String observaciones;
    private String destinoArchivo;
    private String nombreArchivo;
    private String msg;
    private File archivo;
    private String error;
    private BigDecimal totalTransaccionError;
    private Long codigoEjecucion;
    private Timestamp fechaEjecucion;
    private BigDecimal totalEjecutado;
    private Long numeroErroresEnEjecucion;
    private String totalTransaccionErrorCadena;
    private String totalTransaccionDetalleCadena;

    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Transaccion transaccion;
    private ConceptoTransaccion conceptoTransaccion;
    private EjecucionTransaccionLote ejecucionTransaccionLote;
    private Cuenta cuenta;
    private EjecucionTransaccionLotDet ejecucionTransaccionLotDet;
    private EjecucionTransaccionLotErr ejecucionTransaccionLotErr;

    private List<ProductoIfip> itemsProductoIfip;
    private List<Transaccion> itemsTransaccion;
    private List<ConceptoTransaccion> itemsConceptoTransaccion;
    private List<EjecucionTransaccionLotDet> itemsTransaccionLotDet;
    private List<EjecucionTransaccionLotErr> itemsTransaccionLotErr;
    DecimalFormatSymbols simbolos;
    DecimalFormat decimalFormato;

    public TransaccionLoteBean() {
        super(EjecucionTransaccionLote.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {

            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            this.destinoArchivo = ActivacionUsuario.getRutaSubidaArchivos();

            this.setMoneda(this.ejbFacadeMoneda.find(Long.parseLong("1")));
            this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
            this.setProductoIfip(null);
            this.conceptoTransaccion = null;
            this.setItemsConceptoTransaccion(null);
            //this.cambiaTransaccion();
            this.deshabilitaBotonGuardar = true;
            this.deshabilitaVerTransaccionesError = true;
            this.setEjecucionTransaccionLote(new EjecucionTransaccionLote());
            this.getEjecucionTransaccionLote().setCodigoIfip(ActivacionUsuario.getCodigoIfip());
            this.getEjecucionTransaccionLote().setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
            this.getEjecucionTransaccionLote().setNumeroTransacciones(0);
            this.getEjecucionTransaccionLote().setRealizadoPor(ActivacionUsuario.getCodigoUsuario());
            this.getEjecucionTransaccionLote().setTotalTransaccionado(new BigDecimal("0.00"));
            this.setItemsTransaccionLotDet(null);
            totalTransaccionError = new BigDecimal("0.00");
            this.transaccion = null;
            this.conceptoTransaccion = null;
            this.observaciones = null;
            this.archivo = null;
            simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            decimalFormato = new DecimalFormat("#################.00", simbolos);

        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"transaccionLoteBean", "init", CapturaError.getErrorException(ex)});
            }
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "init", CapturaError.getErrorException(e)});
        }
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--
    //-- PROCESO PARA GUARDAR EL MOVIMIENTO
    public void guardaEjecucion(ActionEvent actionEvent) {
        validaCampos(true);
        if (this.msg != null) {
            MuestraMensaje.addError(msg);
            return;
        }
        try {
            this.totalEjecutado = new BigDecimal("0.00");
            this.numeroErroresEnEjecucion = (long) 0;
            if (this.guardaEjecucionTransaccionLote()) {
                this.guardaEjecucionTransaccionLoteDetalle();
            } 
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "guardaEjecucion", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     *
     * @return
     */
    public boolean guardaEjecucionTransaccionLote() {
        try {
            EjecucionTransaccionLote ejecucion = new EjecucionTransaccionLote();
            List<TmpEjecucionTraLotDet> listaTmpEjecucionTraLotDet = new ArrayList<>();
            this.fechaEjecucion = new java.sql.Timestamp(new Date().getTime());
            Long conceptoTransaccionLote = getConceptoTransaccionLote();
            if (conceptoTransaccionLote.compareTo(Long.valueOf("0")) == 0) {
                MuestraMensaje.addError("Error al obtener concepto transaccion");
                return false;
            }
            if (!this.itemsTransaccionLotDet.isEmpty()) {
                ejecucion.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                ejecucion.setCodigoTransaccionLote(ejbFacadeConceptoTransaccionLote.find(conceptoTransaccionLote));
                ejecucion.setFecha(fechaEjecucion);
                ejecucion.setFechaSistema(fechaEjecucion);
                ejecucion.setNumeroTransacciones(ejecucionTransaccionLote.getNumeroTransacciones());
                ejecucion.setTotalTransaccionado(ejecucionTransaccionLote.getTotalTransaccionado());
                ejecucion.setObservaciones(observaciones);
                ejecucion.setRealizadoPor(ActivacionUsuario.getCodigoUsuario());
                ejecucion.setCodigoIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia());
                this.itemsTransaccionLotDet.stream().map((etld) -> {
                    TmpEjecucionTraLotDet tmpEjecucionTraLotDet = new TmpEjecucionTraLotDet();
                    tmpEjecucionTraLotDet.setEjecucionTransaccionLote(ejecucion);
                    tmpEjecucionTraLotDet.setCodigoCuenta(etld.getCodigoCuenta().getCodigo());
                    tmpEjecucionTraLotDet.setCodigoPersona(etld.getCodigoCuenta().getSocio().getCodigoPersona().getCodigo());
                    tmpEjecucionTraLotDet.setValor(etld.getValorTransaccion());
                    return tmpEjecucionTraLotDet;
                }).forEachOrdered(listaTmpEjecucionTraLotDet::add);
                ejecucion.setTmpEjecucionTraLotDetLista(listaTmpEjecucionTraLotDet);
                ejbFacade.create(ejecucion);
                this.codigoEjecucion = ejecucion.getCodigo();
                return true;
            } else {
                MuestraMensaje.addError("No existen transacciones en lote detalle");
                return false;
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "guardaEjecucionTransaccionLote", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error al guardar Ejecucion transacciones en lote con tmp");
            return false;
        }
    }

    public Long getConceptoTransaccionLote() {
        Long concepto_transaccion_lote = Long.valueOf("0");
        try {
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_ahorros.pkg_concepto_transaccion_lote.p_obtiene_codigo");
            llamaSP.setNumeroParametros(6);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.productoIfip.getProductoIfipPK().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.productoIfip.getProductoIfipPK().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_concepto", this.conceptoTransaccion.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_eliminado", String.valueOf("N")});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                concepto_transaccion_lote = Long.parseLong(llamaSP.getListResultado().get(0).toString());
            }
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            return concepto_transaccion_lote;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "getConceptoTransaccionLote", CapturaError.getErrorException(e)});
            MuestraMensaje.addErrorCapaControl();
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
            return concepto_transaccion_lote;
        }
    }

    /**
     * REALIZA EL MOVIMIENTO EN LA CUENTA DEL SOCIO Y GUARDA LA TRANSACCION EN
     * EL DETALLE
     *
     * @return true correcto y false incorrecto
     */
    private boolean guardaEjecucionTransaccionLoteDetalle() {
        try{
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_ahorros.pkm_ejecucion_tra_lot_det.p_guarda");
            llamaSP.setNumeroParametros(15);
            llamaSP.setListParametrosEntrada(new ArrayList<>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ejecucion", this.codigoEjecucion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.productoIfip.getProductoIfipPK().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.productoIfip.getProductoIfipPK().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_concepto", this.conceptoTransaccion.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_movimiento", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.observaciones});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", 1L});
            llamaSP.setListParametrosSalida(new ArrayList<>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
            llamaSP.invocaSP();
            if (!llamaSP.isEjecucionCorrecta()) {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                MuestraMensaje.addError("Error al guardar detalle transacciones en lote");
                deshabilitaBotonGuardar = true;
                return false;
            } else {
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                MuestraMensaje.addSatisfactorioPersistencia(1);
                deshabilitaBotonGuardar = true;
                return true;
            }
        }catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "guardaEjecucionTransaccionLoteDetalle", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Erro al guardar detalle transacciones en lote");
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
            deshabilitaBotonGuardar = true;
            return false;
        }
    }
    //-- FIN DE GUARDAR EJECUCION
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--
    //--PROCESOS DE CARGA DE ARCHIVO
    /**
     * Carga el Archivo
     *
     * @param event
     */
    public void cargaArchivo(FileUploadEvent event) {
        context = RequestContext.getCurrentInstance();
        context.execute("procesandoDlg.show()");
        this.validaCampos(true);
        if (this.msg != null) {
            MuestraMensaje.addError(msg);
            context.execute("procesandoDlg.hide()");
            return;
        }
        try {
            nombreArchivo = event.getFile().getFileName();
            archivo = new File(this.destinoArchivo + nombreArchivo);
            if (archivo.exists()) {
                archivo.delete();
            }

            if (copiarArchivo(event.getFile().getInputstream())) {

                DecimalFormat formatoValores = new DecimalFormat("###,###,##0.00");
                //Leyendo archio en formato 2007 o superior
                if (!leeArchivoXSSF()) {
                    //Leyendo archivo en formato 2003 o inferior
                    leeArchivoHSSF();

                }

                this.totalTransaccionDetalleCadena = formatoValores.format(this.ejecucionTransaccionLote.getTotalTransaccionado().doubleValue());
                if (this.ejecucionTransaccionLote.getTotalTransaccionado().compareTo(new BigDecimal("0.00")) <= 0) {
                    msg = msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenTransacciones");
                    MuestraMensaje.addError(msg);
                } else {
                    // //System.out.println(formatoValores.format(this.ejecucionTransaccionLote.getTotalTransaccionado().doubleValue()));
                    this.totalTransaccionDetalleCadena = formatoValores.format(this.ejecucionTransaccionLote.getTotalTransaccionado().doubleValue());
                    this.deshabilitaBotonGuardar = false;
                }

                this.setTotalTransaccionErrorCadena(formatoValores.format(this.totalTransaccionError.doubleValue()));
                if (!this.itemsTransaccionLotErr.isEmpty()) {

                    this.deshabilitaVerTransaccionesError = false;
                }

                context.execute("procesandoDlg.hide()");
            } else {
                context.execute("procesandoDlg.hide()");
            }

        } catch (Exception e) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "cargaArchivo", CapturaError.getErrorException(e)});
        }
    }

    private void validaCampos(boolean carga) {
        this.msg = null;
        if (this.moneda == null && msg == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Moneda") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        }

        if (this.productoIfip == null && msg == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Producto") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        }

        if (this.transaccion == null && msg == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Transaccion") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        }

        if (!carga) {
            // //System.out.println("ConcetoTrasanccion " + this.conceptoTransaccion);
            if (this.conceptoTransaccion == null && msg == null) {
                this.msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Concepto") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            }
            if (this.observaciones == null && msg == null) {
                this.msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            }
        }

        if (this.observaciones == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        }
    }

    /**
     * Copia el Archivoe en el Servidor
     *
     * @param inputStream Archivo Fisico
     * @return true copio achivo false error
     */
    public boolean copiarArchivo(InputStream inputStream) {
        try {
            context = RequestContext.getCurrentInstance();
            OutputStream out = new FileOutputStream(archivo);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            //Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "cargaArchivo", CapturaError.getErrorException(e)});
            return false;
        }

        return true;
    }

    /**
     * Lee archivo cuyo formato sea 2003 e inferior
     *
     * @return true leyo el archivo y cargo en la lista, falso error al leer el
     * archivo
     */
    private boolean leeArchivoHSSF() {
        List cellDataList = new ArrayList();
        try {

            FileInputStream fileInputStream = new FileInputStream(this.destinoArchivo + nombreArchivo);
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (Exception e) {
            MuestraMensaje.addErrorCapaControl();
            //Registra el error en el log del servidor
            MuestraMensaje.addError("Verifique formato de archivo y/o los datos del archivo.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "leeArchivoHSSF", CapturaError.getErrorException(e)});
            return false;
        }

        leeCeldasArchivoHSSF(cellDataList);

        return true;
    }

    private void leeCeldasArchivoHSSF(List cellDataList) {
        String numeroCuenta = null;
        BigDecimal valorTransaccion = null;
        this.itemsTransaccionLotErr = new ArrayList<EjecucionTransaccionLotErr>();
        this.itemsTransaccionLotDet = new ArrayList<EjecucionTransaccionLotDet>();
        this.ejecucionTransaccionLote.setNumeroTransacciones(0);
        this.ejecucionTransaccionLote.setTotalTransaccionado(new BigDecimal("0.00"));
        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            for (int j = 0; j < cellTempList.size(); j++) {
                HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
                String stringCellValue = hssfCell.toString();
                if (stringCellValue.toString() == null) {
                    return;
                }
                ////System.out.print(stringCellValue + "\t");

                if (i > 0 && j == 0) {
                    numeroCuenta = stringCellValue.toString();
                    // //System.out.println(j + " numeroCuenta " + numeroCuenta);
                }

                if (i > 0 && j == 1) {
                    valorTransaccion = new BigDecimal(decimalFormato.format(Double.parseDouble(stringCellValue.toString())));
                    ////System.out.println(j + "  valorTransaccion " + valorTransaccion);
                }
            }

            if (i > 0) {
                obtieneCuenta(numeroCuenta, valorTransaccion);
            }
            // //System.out.println();
        }
    }

    /**
     * Lee archivo cuyo formato sea 2007 o superior
     *
     * @return true leyo el archivo y cargo en la lista, falso error al leer el
     * archivo
     */
    private boolean leeArchivoXSSF() {
        List cellDataList = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(this.archivo);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }

        } catch (Exception e) {
            //Muestra el Mensaje del Error en la Capa            
            MuestraMensaje.addError("Verifique formato de archivo y/o los datos del archivo.");
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TransaccionLoteBean", "leeArchivoXSSF", CapturaError.getErrorException(e)});

            return false;
        }
        LeerCeldasArchivoXSSF(cellDataList);

        return true;
    }

    private void LeerCeldasArchivoXSSF(List cellDataList) {
        String numeroCuenta = null;
        BigDecimal valorTransaccion = null;
        this.itemsTransaccionLotErr = new ArrayList<EjecucionTransaccionLotErr>();
        this.itemsTransaccionLotDet = new ArrayList<EjecucionTransaccionLotDet>();
        this.ejecucionTransaccionLote.setNumeroTransacciones(0);
        this.ejecucionTransaccionLote.setTotalTransaccionado(new BigDecimal("0.00"));
        this.deshabilitaBotonGuardar = true;

        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            // //System.out.println(" i " + i);
            for (int j = 0; j < cellTempList.size(); j++) {
                XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                String stringCellValue = hssfCell.toString();
                // //System.out.println(j + " " + stringCellValue);
                if (stringCellValue.toString() == null) {
                    return;
                }
                if (i > 0 && j == 0) {
                    numeroCuenta = stringCellValue.toString();
                    //// //System.out.println(j + " numeroCuenta " + numeroCuenta);
                }

                if (i > 0 && j == 1) {
                    valorTransaccion = new BigDecimal(decimalFormato.format(Double.parseDouble(stringCellValue.toString())));
                    ////System.out.println(j + "  valorTransaccion " + valorTransaccion);
                }
            }

            if (i > 0) {
                obtieneCuenta(numeroCuenta, valorTransaccion);
            }

        }

        //// //System.out.println("itemsTransaccionLotDet " + itemsTransaccionLotDet);
        ////System.out.println("itemsTransaccionLotErr " + itemsTransaccionLotErr);
    }

    private void obtieneCuenta(String numeroCuenta, BigDecimal valorTransaccion) {
        // //System.out.println("numeroCuenta " + numeroCuenta + " valorTransaccion " + valorTransaccion);

        List<Cuenta> listaCuenta = this.ejbFacadeCuenta.getItemsNumero(ActivacionUsuario.getCodigoIfip(), this.getProductoIfip().getProductoIfipPK().getCodigoMoneda(), this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto(), numeroCuenta, new Long(1));
        if (!listaCuenta.isEmpty()) {
            if (listaCuenta.size() == 1) {
                cuenta = listaCuenta.get(0);

                if (cuenta.getCodigoEstado().getPuedeReactivar() == 'N') {
                    this.error = "Cuenta Cerrada.";
                    MuestraMensaje.addError(error);
                    cuenta = null;
                } else {
                    if (cuenta.getCodigoEstado().getIndicaActiva() == 'S') {
                        if (cuenta.getSaldoDisponible().compareTo(valorTransaccion) < 0 && this.transaccion.getTipo() == 'D') {
                            this.error = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
                            MuestraMensaje.addError(error);
                            cuenta = null;
                        }
                    } else {
                        this.error = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaInactiva");
                        MuestraMensaje.addError(error);
                        cuenta = null;
                    }
                }
            } else {
                this.error = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaMasDeUnaConNumero");
                MuestraMensaje.addError(error);
                cuenta = null;
            }
        } else {
            this.error = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSeEcontroCuenta")
                    + " (" + numeroCuenta + ", " + this.getProductoIfip().getProducto().getMoneda().getNombre() + ", " + this.getProductoIfip().getProducto().getTipoProducto().getNombre() + ")";
            MuestraMensaje.addError(error);
            cuenta = null;
        }

        if (this.cuenta != null) {
            ejecucionTransaccionLotDet = new EjecucionTransaccionLotDet();
            ejecucionTransaccionLotDet.setCodigoCuenta(cuenta);
            ejecucionTransaccionLotDet.setValorTransaccion(valorTransaccion);
            this.itemsTransaccionLotDet.add(ejecucionTransaccionLotDet);
            this.ejecucionTransaccionLote.setTotalTransaccionado(this.ejecucionTransaccionLote.getTotalTransaccionado().add(valorTransaccion));
            this.ejecucionTransaccionLote.setNumeroTransacciones(this.ejecucionTransaccionLote.getNumeroTransacciones() + 1);
        } else {
            if (!numeroCuenta.trim().equals("")) {
                ejecucionTransaccionLotErr = new EjecucionTransaccionLotErr();
                ejecucionTransaccionLotErr.setError(error);
                ejecucionTransaccionLotErr.setNumeroCuenta(numeroCuenta);
                ejecucionTransaccionLotErr.setValorTransaccion(valorTransaccion);
                totalTransaccionError = totalTransaccionError.add(valorTransaccion);
                this.itemsTransaccionLotErr.add(ejecucionTransaccionLotErr);
            }

        }

    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//---
    //-- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    //-- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--

    //--//--//--//--//--//--//--//--//-- REFRESCAMIENTO DE COMBOS/LISTAS //--//--//--//--//--//--//--//--//--//---
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
        this.conceptoTransaccion = null;

    }

    /**
     * CUANDO CAMBIA EL PRODUCTO.
     */
    public void cambiaProductoIfip() {
        this.setItemsTransaccion(
                this.ejbFacadeConceptoTransaccionLote.getItemsTransaccionEjecucion(this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto(),
                        this.getProductoIfip().getProductoIfipPK().getCodigoMoneda(),
                        this.getProductoIfip().getProductoIfipPK().getCodigoIfip(),
                        'N'));

        this.transaccion = null;
        this.conceptoTransaccion = null;
        this.setItemsConceptoTransaccion(null);
    }

    /**
     * CUANDO CAMBIA LA TRANSACCION.
     */
    public void cambiaTransaccion() {
        this.setItemsConceptoTransaccion(this.ejbFacadeConceptoTransaccionLote.getItemsConceptoTransaccionEjecucion(this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto(),
                this.getProductoIfip().getProductoIfipPK().getCodigoMoneda(),
                this.transaccion.getCodigo(),
                this.getProductoIfip().getProductoIfipPK().getCodigoIfip(),
                'N'));

        if (this.archivo != null) {
            // //System.out.println("lee nuevamente el archivo");
            this.leeArchivoXSSF();
        }

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
     * @return the ejecucionTransaccionLote
     */
    public EjecucionTransaccionLote getEjecucionTransaccionLote() {
        return ejecucionTransaccionLote;
    }

    /**
     * @param ejecucionTransaccionLote the ejecucionTransaccionLote to set
     */
    public void setEjecucionTransaccionLote(EjecucionTransaccionLote ejecucionTransaccionLote) {
        this.ejecucionTransaccionLote = ejecucionTransaccionLote;
    }

    /**
     * @return the itemsTransaccionLotDet
     */
    public List<EjecucionTransaccionLotDet> getItemsTransaccionLotDet() {
        return itemsTransaccionLotDet;
    }

    /**
     * @param itemsTransaccionLotDet the itemsTransaccionLotDet to set
     */
    public void setItemsTransaccionLotDet(List<EjecucionTransaccionLotDet> itemsTransaccionLotDet) {
        this.itemsTransaccionLotDet = itemsTransaccionLotDet;
    }

    /**
     * @return the itemsTransaccionLotErr
     */
    public List<EjecucionTransaccionLotErr> getItemsTransaccionLotErr() {
        return itemsTransaccionLotErr;
    }

    /**
     * @param itemsTransaccionLotErr the itemsTransaccionLotErr to set
     */
    public void setItemsTransaccionLotErr(List<EjecucionTransaccionLotErr> itemsTransaccionLotErr) {
        this.itemsTransaccionLotErr = itemsTransaccionLotErr;
    }

    /**
     * @return the deshabilitaVerTransaccionesError
     */
    public boolean isDeshabilitaVerTransaccionesError() {
        return deshabilitaVerTransaccionesError;
    }

    /**
     * @param deshabilitaVerTransaccionesError the
     * deshabilitaVerTransaccionesError to set
     */
    public void setDeshabilitaVerTransaccionesError(boolean deshabilitaVerTransaccionesError) {
        this.deshabilitaVerTransaccionesError = deshabilitaVerTransaccionesError;
    }

    /**
     * @return the totalTransaccionError
     */
    public BigDecimal getTotalTransaccionError() {
        return totalTransaccionError;
    }

    /**
     * @param totalTransaccionError the totalTransaccionError to set
     */
    public void setTotalTransaccionError(BigDecimal totalTransaccionError) {
        this.totalTransaccionError = totalTransaccionError;
    }

    /**
     * @return the totalTransaccionErrorCadena
     */
    public String getTotalTransaccionErrorCadena() {
        return totalTransaccionErrorCadena;
    }

    /**
     * @param totalTransaccionErrorCadena the totalTransaccionErrorCadena to set
     */
    public void setTotalTransaccionErrorCadena(String totalTransaccionErrorCadena) {
        this.totalTransaccionErrorCadena = totalTransaccionErrorCadena;
    }

    /**
     * @return the totalTransaccionDetalleCadena
     */
    public String getTotalTransaccionDetalleCadena() {
        return totalTransaccionDetalleCadena;
    }

    /**
     * @param totalTransaccionDetalleCadena the totalTransaccionDetalleCadena to
     * set
     */
    public void setTotalTransaccionDetalleCadena(String totalTransaccionDetalleCadena) {
        this.totalTransaccionDetalleCadena = totalTransaccionDetalleCadena;
    }

}
