/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.asignacionCartera;

import com.monitorjbl.xlsx.StreamingReader;
import com.monitorjbl.xlsx.impl.StreamingCell;
import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.EntidadDataModel;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.asignacionCartera.CarteraAsignada;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.creditos.SolicitudFacade;
import ec.renafipse.mks.negocio.creditos.asignacionCartera.CarteraAsignadaFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author willan
 */
/**
 *
 * @author willan
 */
@ManagedBean(name = "carteraAsignadaController")
//@SessionScoped
@ViewScoped
public class CarteraAsignadaController extends AbstractController<CarteraAsignada> implements Serializable {

    public static final String CORRECTO = "CORRECTO";
    @EJB
    private CarteraAsignadaFacade ejbFacade;
    @EJB
    private PersonaFacade personasFacade;
    @EJB
    private SolicitudFacade solicitudFacade;

    private EntidadDataModel dataModel;
    private List<CarteraAsignada> carteraAsignadasLista;
    private CarteraAsignada selected;
    private Persona personaRecuperador;
    private Persona socioSolicitud;
    private long codigoSolicitud;
    private String nombresPersona;

    private Long codigoPersona;
    private String nombreCompletoPersona = "";
    private String nombreArchivo = "";

    private LlamaSP llamaSP;

    private FacesMessage msg;

    private StreamedContent archivoExcel;

    private String pathArchivo;

    @Override
    public CarteraAsignada getSelected() {
        return selected;
    }

    @Override
    public void setSelected(CarteraAsignada selected) {
        this.selected = selected;
    }

    public LazyDataModel<CarteraAsignada> getModel() {
        if (dataModel == null) {
            dataModel = ejbFacade.getEntidadDataModel();
        }
        return dataModel;
    }

    @PostConstruct
    public void init() {
        try {
            llamaSP = new LlamaSP();
            dataModel = ejbFacade.getEntidadDataModel();
            //carteraAsignadasLista = getEjbFacade().findAll();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ReliquidacionCreditoController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Permite actualizar los registros luego de haberse insertado por el
     * archivo
     */
    public void actualizarListado() {
        dataModel = null;
        ejbFacade.setEntidadDataModel(null);
        getModel();
    }

    public CarteraAsignadaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CarteraAsignadaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<CarteraAsignada> getCarteraAsignadasLista() {
        return carteraAsignadasLista;
    }

    public void setCarteraAsignadasLista(List<CarteraAsignada> carteraAsignadasLista) {
        this.carteraAsignadasLista = carteraAsignadasLista;
    }

    public Persona getPersonaRecuperador() {
        return personaRecuperador;
    }

    public void setPersonaRecuperador(Persona personaRecuperador) {
        this.personaRecuperador = personaRecuperador;
    }

    public void seleccionaPersona() {
        try {
            nombreCompletoPersona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteNombreCompleto");
            if (nombreCompletoPersona == null || nombreCompletoPersona.isEmpty()) {
                nombreCompletoPersona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteNombreCompleto");
                codigoPersona = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona"));

            } else {
                codigoPersona = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mostrarDialogoCrearForm:componenteBusca:listaPersonasComponenteForm:buscaPersonaComponenteCodigoPersona"));
            }
            setPersonaRecuperador(personasFacade.getItemsByCodigo(codigoPersona).get(0));
        } catch (NumberFormatException e) {
        }
    }

    /**
     * Edita el registro seleccionado
     *
     */
    public void nuevo() {
        try {
            nombreCompletoPersona = "";
            personaRecuperador = new Persona();
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TasaIntenresProductoDPFMontoDetalleController", "edita", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Edita el registro seleccionado
     *
     */
    public void edita() {
        try {
            nombreCompletoPersona = "";
            if (getSelected() == null) {
                MuestraMensaje.addError("Seleccione un registro primero");
            } else {
                setPersonaRecuperador(personasFacade.getItemsByCodigo(getSelected().getCodigoRecuperador().getCodigoPersona()).get(0));
                codigoPersona = getPersonaRecuperador().getCodigo();
                nombreCompletoPersona = getPersonaRecuperador().getNombreCompleto();
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TasaIntenresProductoDPFMontoDetalleController", "edita", CapturaError.getErrorException(ex)});
        }
    }

    public Persona getSocioSolicitud() {
        return socioSolicitud;
    }

    public void setSocioSolicitud(Persona socioSolicitud) {
        this.socioSolicitud = socioSolicitud;
    }

    public long getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(long codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public String getNombresPersona() {
        return nombresPersona;
    }

    public void setNombresPersona(String nombresPersona) {
        this.nombresPersona = nombresPersona;
    }

    public void buscarSocio() {
        long codigoSolicitudl = 0l;
        nombresPersona = "";
        try {
            codigoSolicitudl = Long.valueOf(codigoSolicitud);
            if (!existeNumeroCredito(codigoSolicitudl)) {
                List<Solicitud> solicitudList = solicitudFacade.getItemsNumeroCodigoIfip(codigoSolicitud, ActivacionUsuario.getCodigoUsuario());
                if (!solicitudList.isEmpty()) {
                    setSocioSolicitud(solicitudList.get(0).getSocio().getCodigoPersona());
                    nombresPersona = solicitudList.get(0).getSocio().getCodigoPersona().getNombreCompleto();
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * *
     * VALIDA LOS CREDITOS QUE VAN A TENER GARANTIA DPF
     */
    public boolean validaCreditoGarantiaCredito(long codigoSolicitudl) {
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkm_asignacion_cartera.p_buscar_credito_na");
            llamaSP.setNumeroParametros(2);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero", codigoSolicitudl});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_existe", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getError() == null) {
                    if (llamaSP.getListResultado().get(0) != null) {
                        return Integer.parseInt(llamaSP.getListResultado().get(0).toString()) > 0;
                    } else {
                        return false;
                    }
                } else {
                    MuestraMensaje.addError(llamaSP.getError());
                    return false;
                }
            } else {
                MuestraMensaje.addError(llamaSP.getError());
                return false;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
                    new Object[]{"SolicitudController", "validaCreditoGarantiaDpf", llamaSP.getErroSql()});
            return false;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     * Permite buscar un credito en la cartera asignada
     *
     * @param numeroCredito
     * @return devuelve verdadero si el numero de credito ya esta registrado
     */
    public boolean existeNumeroCredito(long numeroCredito) {
        boolean existe = false;
        try {
            validaCreditoGarantiaCredito(numeroCredito);
        } catch (NumberFormatException e) {
        }
        return existe;
    }

    /**
     *
     * Permite registrar un
     *
     */
    public void crear() {

        RequestContext contexto = RequestContext.getCurrentInstance();
        contexto.execute("procesandoDlg.show()");
        long codigoRecuperadorParametro = personaRecuperador.getCodigo();
        //long codigoSolicitudParametro = codigoSolicitud;                       
        crear(codigoRecuperadorParametro, codigoSolicitud);
        contexto.execute("procesandoDlg.hide()");
        contexto.execute("CarteraAsignadaCreateDialog.hide()");
    }

    /**
     *
     * Permite registrar un credito a cartera asignada
     *
     * @param codigoRecuperadorParametro
     * @param codigoSolicitudParametro
     * @param mensaje mensaje de la llamada al sp
     * @return verdadero si el registro está insertado
     *
     */
    public String crear(long codigoRecuperadorParametro, long codigoSolicitudParametro) {
        String mensaje = "";
        if (!existePersona(codigoRecuperadorParametro)) {
            mensaje = String.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreateCarteraAsignadaMensajeSocio"), String.valueOf(codigoRecuperadorParametro));
            return mensaje;
        }
        if (!existeCredito(codigoSolicitudParametro)) {
            mensaje = String.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreateCarteraAsignadaMensajeCredito"), String.valueOf(codigoSolicitudParametro));
            return mensaje;
        }
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.PKM_ASIGNACION_CARTERA.p_inserta_registros");
            llamaSP.setNumeroParametros(5);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_recuperador", codigoRecuperadorParametro});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_credito", codigoSolicitudParametro});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_inserto", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if (llamaSP.getError() == null) {
                    llamaSP.commit();
                    if (llamaSP.getListResultado().get(0) != null) {
                        //System.out.println("llamaSP.getListResultado().get(0) " + llamaSP.getListResultado().get(0));
                        mensaje = Integer.parseInt(llamaSP.getListResultado().get(0).toString()) == 0 ? CORRECTO : Integer.parseInt(llamaSP.getListResultado().get(0).toString()) == 1 ? "INCORRECTO" : "CREDITO YA REGISTRADO";

                    }
                } else {
                    mensaje = llamaSP.getError();
                    //MuestraMensaje.addError(llamaSP.getError());
                    //creado=  false;
                }
            } else {
                mensaje = llamaSP.getError();
                //MuestraMensaje.addError(llamaSP.getError());
                // creado= false;
            }
        } catch (Exception ex) {
            mensaje = llamaSP.getError();
            // Muestra el Mensaje del Error en la Capa
            //MuestraMensaje.addErrorCapaControl();
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getError(),
              //      new Object[]{"SolicitudController", "validaCreditoGarantiaDpf", llamaSP.getErroSql()});
            //creado= false;
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
            //creado= true;
        }
        return mensaje;
    }

    /**
     * Metodo que permite verificar si la persona existe
     *
     * @param codigoPersona
     */
    private boolean existePersona(long codigoPersona) {
        boolean existePersona = false;
        try {
            Persona persona = personasFacade.getItemsByCodigo(codigoPersona).get(0);
            if (persona != null) {
                existePersona = true;
            }
        } catch (Exception e) {
        }
        return existePersona;
    }

    /**
     * Metodo que permite verificar si existe el credito
     *
     * @param codigoCredito
     */
    private boolean existeCredito(long codigoCredito) {
        boolean existeCredito = false;
        try {
            Solicitud solicitudCredito = solicitudFacade.getItemsNumeroCodigoIfip(codigoCredito, ActivacionUsuario.getCodigoIfip()).get(0);
            if (solicitudCredito != null) {
                existeCredito = true;
            }
        } catch (Exception e) {
        }
        return existeCredito;
    }

    /**
     * Método de la vista que controla la carga de archivos
     *
     * @param event
     */
    public void cargaArchivo(FileUploadEvent event) {

        nombreArchivo = event.getFile().getFileName();//toma nombre archivo        
        File archivo;
        String destinoArchivo = ActivacionUsuario.getRutaSubidaArchivos();
        try {
            archivo = new File(destinoArchivo + nombreArchivo);//toma carpeta raiz del usuario
            if (archivo.exists()) {
                archivo.delete();
            }
            if (copiarArchivo(event.getFile().getInputstream(), destinoArchivo, nombreArchivo)) {
                //Leyendo archio en formato 2007 o superior
                if (!leerXLSXArchivo(destinoArchivo)) {
                    //Leyendo archivo en formato 2003 o inferior
                    leeArchivoXLS();
                }
            } else {
                //msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                //       ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CopiarArchivoUaf") + " : " + destinoArchivo);
                //FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (IOException e) {
        }

    }

    /**
     * Metodo utilizado para copiar el archivo al directorio local del usuario
     *
     * @param inputStream
     * @return
     */
    public boolean copiarArchivo(InputStream inputStream, String destinoArchivo, String archivo) {
        try {
            OutputStream out = new FileOutputStream(destinoArchivo + archivo);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "copiarArchivo", CapturaError.getErrorException(e)});
            return false;
        }
        return true;
    }

    /**
     *
     * @param destinoArchivo
     * @throws IOException
     */
    public boolean leerXLSXArchivo(String destinoArchivo) throws IOException {
        try {

            long time_start, time_end;
            time_start = System.currentTimeMillis();

            ArrayList<Object[]> listado = new ArrayList<Object[]>();
            Object[] entidad = new Object[3];
            InputStream ExcelFileToRead = new FileInputStream(destinoArchivo + nombreArchivo);
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            int j = 0;
            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                entidad = new Object[3];
                int i = 0;
                entidad[2] = "CORRECTO";
                if (j > 0) {
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();//cell.getCellType() == XSSFCell.CELL_TYPE_STRING
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                            break;
                        }
                        if (i == 0) {//XSSFCell.CELL_TYPE_NUMERIC
                            if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                                entidad[0] = cell.getStringCellValue();
                            }
                            if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                                entidad[0] = cell.getNumericCellValue();
                            }
                            i++;
                        } else if (i == 1) {
                            if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                                entidad[1] = cell.getStringCellValue();
                            }
                            if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                                entidad[1] = cell.getNumericCellValue();
                            }
                            i++;
                        }
                    }
                    if (entidad[0] != null) {
                        listado.add(entidad);
                    }
                }
                j++;
            }
            time_end = System.currentTimeMillis();
            System.out.println("the task has taken " + (time_end - time_start) + " milliseconds");
            
            insertaDatosArchivo(listado);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Método utilizado para leer archivo XLSX con rowCache, abre archivo forma
     * todas las celdas y lo envia a leer celdas
     *
     * @return
     */
    private boolean leeArchivoXLSX(int destinoArchivo
    ) {
        List cellDataList = new ArrayList();
        StreamingCell celda;
        try {
            InputStream is = new FileInputStream(new File(destinoArchivo + nombreArchivo));
            StreamingReader reader = StreamingReader.builder()
                    .rowCacheSize(100) // numero de filas que mantiene en memoria
                    .bufferSize(4096) // tamaño del buffer para leer el archivo InputStream
                    .sheetIndex(0) // indice de la hoja usada
                    .read(is);            // archivo XLSX
            List cellTempList;
            for (Row fila : reader) {
                cellTempList = new ArrayList();
                for (Cell columna : fila) {
                    celda = (StreamingCell) columna;
                    cellTempList.add(celda);
                }
                cellDataList.add(cellTempList);
            }
            leeCeldasArchivoXLSX(cellDataList);
        } catch (Exception e) {
            e.printStackTrace();
//Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "leeArchivoXLSX", CapturaError.getErrorException(e)});

            return false;
        }

        return true;
    }

    /**
     * Método utilizado para leer los valores de las celdas formadas de un
     * archivo XLSX
     *
     * @param cellDataList
     */
    private void leeCeldasArchivoXLSX(List cellDataList) {
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        ArrayList<Object[]> listado = new ArrayList<Object[]>();
        List cellTempList;
        Object[] entidad;
        StreamingCell celdaActual;
        try {
            for (int i = 1; i < cellDataList.size(); i++) {
                cellTempList = (List) cellDataList.get(i);
                entidad = new Object[3];
                entidad[2] = "CORRECTO";
                for (int j = 0; j < cellTempList.size(); j++) {
                    celdaActual = (StreamingCell) cellTempList.get(j);
                    switch (j) {
                        case 0:
                            entidad[0] = celdaActual.getStringCellValue();
                            break;
                        case 1:
                            entidad[1] = celdaActual.getStringCellValue();
                            break;
                    }
                }
                listado.add(entidad);
            }
            insertaDatosArchivo(listado);
            time_end = System.currentTimeMillis();
            System.out.println("the task has taken " + (time_end - time_start) + " milliseconds");
        } catch (Exception e) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LeeCeldaArchivoXlsx"));
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "leeCeldasArchivoXLSX", CapturaError.getErrorException(e)});
        }
    }

    /**
     *
     * Metodo que permite insertar los datos cargados en el archivo excel
     *
     * @param listado datos obtenidos
     * @return devuelve verdadero si la carga es exitosa
     */
    public boolean insertaDatosArchivo(ArrayList<Object[]> listado) {
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        boolean procesoCorrecto = false;
        ArrayList<Object[]> aux = new ArrayList<>();
        try {
            for (int i = 0; i < listado.size(); i++) {
                try {

                    Object[] datosVector = listado.get(i);
                    long codigoSolicitudParametro
                            = Long.valueOf((new Double(datosVector[0].toString())).longValue());
                    long codigoRecuperadorParametro = Long.valueOf((new Double(datosVector[1].toString())).longValue());

                    datosVector[2] = crear(codigoRecuperadorParametro, codigoSolicitudParametro);
                    aux.add(datosVector);
                } catch (Exception e) {
                }
            }
            time_end = System.currentTimeMillis();
            System.out.println("the task has taken " + (time_end - time_start) + " milliseconds");
            String destinoArchivo = ActivacionUsuario.getRutaSubidaArchivos();
            time_start = System.currentTimeMillis();
            escribirXLSXFile(destinoArchivo, aux, nombreArchivo, false);
            time_end = System.currentTimeMillis();
            System.out.println("the task has taken " + (time_end - time_start) + " milliseconds");
            time_start = System.currentTimeMillis();
            actualizarListado();
            time_end = System.currentTimeMillis();
            System.out.println("the task has taken " + (time_end - time_start) + " milliseconds");
            procesoCorrecto = true;
        } catch (Exception e) {
            procesoCorrecto = false;
        }

        return procesoCorrecto;
    }

    /**
     * Método utilizado para leer archivo XLS con rowCache, abre archivo forma
     * todas las celdas y lo envia a leer celdas
     *
     * @return
     */
    private boolean leeArchivoXLS() {
        String destinoArchivo = "";
        List cellDataList;
        try {
            cellDataList = new ArrayList();
            FileInputStream fileInputStream = new FileInputStream(destinoArchivo + nombreArchivo);
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            HSSFRow hssfRow;
            Iterator iterator;
            List cellTempList;
            HSSFCell hssfCell;
            while (rowIterator.hasNext()) {
                hssfRow = (HSSFRow) rowIterator.next();
                iterator = hssfRow.cellIterator();
                cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
            leeCeldasArchivoXLS(cellDataList);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "leeArchivoXLS", CapturaError.getErrorException(e)});
            return false;
        }
        return true;
    }

    /**
     * Método utilizado para leer los valores de las celdas formadas de un
     * archivo XLS
     *
     * @param cellDataList
     */
    private void leeCeldasArchivoXLS(List cellDataList) {
        ArrayList<Object[]> listado = new ArrayList<Object[]>();
        List cellTempList;
        Object[] entidad;
        HSSFCell celdaActual;
        try {
            for (int i = 1; i < cellDataList.size(); i++) {
                cellTempList = (List) cellDataList.get(i);
                entidad = new Object[3];
                entidad[2] = "CORRECTO";
                for (int j = 0; j < cellTempList.size(); j++) {
                    celdaActual = (HSSFCell) cellTempList.get(j);
                    celdaActual.setCellType(Cell.CELL_TYPE_STRING);
                    switch (j) {
                        case 0:
                            entidad[0] = celdaActual.getStringCellValue();
                            break;
                        case 1:
                            entidad[1] = celdaActual.getStringCellValue();
                            break;
                    }
                }
                listado.add(entidad);
            }
            insertaDatosArchivo(listado);
        } catch (Exception e) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LeeCeldaArchivoXls"));
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "leeCeldasArchivoXLS", CapturaError.getErrorException(e)});
        }
    }

    public void escribirXLSFile(String destinoArchivo,
            ArrayList<Object[]> listado
    ) throws IOException {
        String excelFileName = destinoArchivo + nombreArchivo;
        String sheetName = "Ejecucion";//name of sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        for (int r = 0; r < listado.size(); r++) {
            HSSFRow row = sheet.createRow(r);

            Object[] datosVector = null;
            if (r > 0) {
                datosVector = listado.get(r - 1);
            }
            for (int c = 0; c < 3; c++) {
                HSSFCell cell = row.createCell(c);
                if (r == 0) {
                    HSSFCellStyle style = wb.createCellStyle();
                    style.setWrapText(true);
                    style.setLocked(true);

                    HSSFFont font = wb.createFont();
                    font.setBold(true);
                    style.setFont(font);
                    cell.setCellStyle(style);

                    if (c == 0) {
                        cell.setCellValue("CREDITO");
                    } else if (c == 1) {
                        cell.setCellValue("RECUPERADOR");
                    } else if (c == 2) {
                        cell.setCellValue("ESTADO");
                    }
                    sheet.autoSizeColumn(r);
                } else {
                    try {
                        if (c == 0) {
                            cell.setCellValue(datosVector[0].toString());
                        } else if (c == 1) {
                            cell.setCellValue(datosVector[1].toString());
                        } else if (c == 2) {
                            cell.setCellValue(datosVector[2].toString());
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        FileOutputStream fileOut = new FileOutputStream(excelFileName);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public void escribirXLSXFile(String destinoArchivo,
            ArrayList<Object[]> listado, String parametroNombreArchivo, boolean esPlantilla
    ) throws IOException {
        String excelFileName = destinoArchivo + "tmp" + parametroNombreArchivo;
        String sheetName = "Ejecucion";
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        for (int r = 0; r < listado.size(); r++) {

            XSSFRow row = sheet.createRow(r);
            sheet.autoSizeColumn(r);
            Object[] datosVector = null;
            if (r > 0) {
                datosVector = listado.get(r - 1);
            }
            for (int c = 0; c < 3; c++) {
                XSSFCell cell = row.createCell(c);
                if (r == 0) {

                    XSSFCellStyle style = wb.createCellStyle();
                    style.setBorderTop((short) 6); // double lines border
                    style.setBorderBottom((short) 1); // single line border
                    XSSFFont font = wb.createFont();
                    font.setFontHeightInPoints((short) 15);
                    font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                    style.setFont(font);
                    style.setLocked(true);
                    cell.setCellStyle(style);
                    if (c == 0) {
                        cell.setCellValue("CREDITO");
                    } else if (c == 1) {
                        cell.setCellValue("RECUPERADOR");
                    } else if (c == 2 && !esPlantilla) {
                        cell.setCellValue("ESTADO");
                    }
                } else {
                    try {
                        if (c == 0) {
                            cell.setCellValue(datosVector[0].toString());
                        } else if (c == 1) {
                            cell.setCellValue(datosVector[1].toString());
                        } else if (c == 2) {
                            XSSFCellStyle style = wb.createCellStyle();
                            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                            if (datosVector[2].toString().equals(CORRECTO)) {
                                style.setFillForegroundColor(new XSSFColor(new java.awt.Color(45, 201, 55)));
                            } else {
                                style.setFillForegroundColor(new XSSFColor(new java.awt.Color(231, 180, 22)));
                            }
                            cell.setCellStyle(style);
                            cell.setCellValue(datosVector[2].toString());
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        try {
            File archivo = new File(excelFileName);//toma carpeta raiz del usuario
            if (archivo.exists()) {
                archivo.delete();
            }
        } catch (Exception e) {
        }

//        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "**********************");
//        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "**********************");
//        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "**********************");
//        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "********************** excelFileName ");
//        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, excelFileName);
        FileOutputStream fileOut = new FileOutputStream(excelFileName);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
        pathArchivo = excelFileName;
    }

    /**
     * Metodo que permite generar una plantilla
     */
    public void generaExcel() {
        try {
            if (pathArchivo == null) {
                pathArchivo = ActivacionUsuario.getRutaSubidaArchivos();
            }
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "pathArchivo " + pathArchivo);

            InputStream stream = new FileInputStream(new File(pathArchivo));
            setArchivoExcel(new DefaultStreamedContent(stream, "application/vnd.ms-excel", "tmp" + nombreArchivo));
            msg = new FacesMessage("Información", "Archivo generado correctamente");
            //FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ", ex.getLocalizedMessage());
            //FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Metodo que permite generar una plantilla para cargar como ejemplo de
     * datos
     */
    public void generaPlantillaExcel() {
        try {
            String destinoArchivo = ActivacionUsuario.getRutaSubidaArchivos();
            ArrayList<Object[]> aux = new ArrayList<>();
            aux.add(new Object[]{});
            escribirXLSXFile(destinoArchivo, aux, "plantilla.xlsx", true);
            InputStream stream = new FileInputStream(new File(destinoArchivo + "/plantilla.xlsx"));
            setArchivoExcel(new DefaultStreamedContent(stream, "application/vnd.ms-excel", "plantilla.xlsx"));
            msg = new FacesMessage("Información", "Archivo generado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ", ex.getLocalizedMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * @return the archivoExcel
     */
    public StreamedContent getArchivoExcel() {
        return archivoExcel;
    }

    /**
     * @param archivoExcel the archivoExcel to set
     */
    public void setArchivoExcel(StreamedContent archivoExcel) {
        this.archivoExcel = archivoExcel;
    }
}
