/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.TipoPersonaObservable;
import ec.renafipse.mks.modelo.socios.UafCarga;
import ec.renafipse.mks.modelo.socios.UafHomonimo;
import ec.renafipse.mks.modelo.socios.UafPersonaPoliticamenteExp;
import ec.renafipse.mks.modelo.socios.UafSentenciado;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaObservableFacade;
import ec.renafipse.mks.negocio.socios.UafCargaFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.FileUploadEvent;
import com.monitorjbl.xlsx.StreamingReader;
import com.monitorjbl.xlsx.impl.StreamingCell;
import ec.renafipse.mks.capas.basedatos.LlamaSP;
import java.util.ResourceBundle;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Andres
 */
@ManagedBean(name="personaUafeController")
@ViewScoped
public final class PersonaUafeController extends AbstractController<UafCarga> implements Serializable{
    
    private Date fechaCorte;
    private TipoPersonaObservable tipoPersonaObservable;
    private UafCarga uafCarga;
    private List<TipoPersonaObservable> listaTipoPersonaObservable;
    private List<UafHomonimo> listaUafHomonimo;
    private List<UafPersonaPoliticamenteExp> listaUafPersonaPoliticamenteExp;
    private List<UafSentenciado> listaUafSentenciado;
    private List<UafHomonimo> listaUafHomonimoCarga;
    private List<UafPersonaPoliticamenteExp> listaUafPersonaPoliticamenteExpCarga;
    private List<UafSentenciado> listaUafSentenciadoCarga;
    private UafHomonimo uafHomonimoCarga;
    private UafPersonaPoliticamenteExp uafPersonaPoliticamenteExpCarga;
    private UafSentenciado uafSentenciadoCarga;
    private Ifip ifip;
    private Usuario usuario;
    private String destinoArchivo;
    private String nombreArchivo;
    private File archivo;
    private FileInputStream fileInputStream;
    private FacesMessage msg;
    private LlamaSP llamaSP;
    private StringBuilder mensajeAdvertenciaPeps;
    private int linea;
            
    @EJB
    private UafCargaFacade ejbFacade;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private TipoPersonaObservableFacade ejbFacadeTipoPersonaObservable;
    
    public PersonaUafeController(){
        super(UafCarga.class);
    }
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setUsuario(ActivacionUsuario.getUsuario());
        setFechaCorte(new Date());
        destinoArchivo = ActivacionUsuario.getRutaSubidaArchivos();
        llamaSP = new LlamaSP();
    }
    /**
     * Método de la vista que sirve para guardar una carga de archivos UAFE
     * @param event 
     */
    public void guardar(ActionEvent event) {
        try {
             if (FacesContext.getCurrentInstance().isValidationFailed())//Valida campos requeridos
                return;
             
             if ((getListaUafHomonimo()==null) && (getListaUafPersonaPoliticamenteExp() == null) && (getListaUafSentenciado() == null)) {//valida que existan registros cargados
                 msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", 
                                         ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorListasUaf"));
                 FacesContext.getCurrentInstance().addMessage(null, msg);
                 return; 
             }
             //asignación de valores para nueva carga
             uafCarga.setCodigoIfip(getIfip());
             uafCarga.setCodigoTipoPersonaObs(getTipoPersonaObservable());
             uafCarga.setCodigoUsuario(getUsuario());
             uafCarga.setFechaCorte(getFechaCorte());
             uafCarga.setObservaciones(nombreArchivo);
             int totalRegistros = 0;
             if (getListaUafHomonimo()!=null){
                 uafCarga.setUafHomonimoList(getListaUafHomonimo());
                 totalRegistros = totalRegistros + getListaUafHomonimo().size();
             }
             if (getListaUafPersonaPoliticamenteExp()!=null){
                 uafCarga.setUafPersonaPoliticamenteExpList(getListaUafPersonaPoliticamenteExp());
                 totalRegistros = totalRegistros + getListaUafPersonaPoliticamenteExp().size();
             }
             if (getListaUafSentenciado()!=null){
                 uafCarga.setUafSentenciadoList(getListaUafSentenciado());
                 totalRegistros = totalRegistros + getListaUafSentenciado().size();
             }
             uafCarga.setTotalRegistros(totalRegistros);
             ejbFacade.create(uafCarga);
             // Cargando la conexion de BD
             llamaSP.cargaConexion();
             // Indicando que no cierre la conexion de la base de datos
             llamaSP.setCerrarConexion(false);
             // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
             llamaSP.autoCommit(false);
             // Formando parametros para el sp
             llamaSP.setNombreSP("mks_historicos.pkm_uaf_carga.p_actualiza_carga");
             llamaSP.setNumeroParametros(2);
             llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
             llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha", new java.sql.Timestamp(getFechaCorte().getTime())});
             llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_uaf_carga", String.valueOf(uafCarga.getCodigo())});
             llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
             // Invocando al SP
             llamaSP.invocaSP();           
             // Verificando que la ejecucion del proceso ha sido correcta
             if (llamaSP.isEjecucionCorrecta()) {
                  MuestraMensaje.addSatisfactorioPersistencia(1);
             } else {
                 msg = new FacesMessage( FacesMessage.SEVERITY_WARN, "", 
                                         ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AdvertenciaEliminaEspaciosUaf")); 
                                         FacesContext.getCurrentInstance().addMessage(null, msg);
             }
             setListaUafHomonimo(null);
             setListaUafPersonaPoliticamenteExp(null);
             setListaUafSentenciado(null);
             setTipoPersonaObservable(null);
             uafCarga = null;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "guardar", CapturaError.getErrorException(e)});
            
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", 
                                    CapturaError.getErrorException(e));
                 FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        finally{
            if (llamaSP.getConexionBD() != null){
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }
    
    /**
     * Método de la vista que funciona en el evento change de combo tipo de persona, se utiliza para asignar la variable tipo persona
     * @param event 
     */ 
    public void handleChange(AjaxBehaviorEvent event){  
        try{
            TipoPersonaObservable tipo = (TipoPersonaObservable) ((UIOutput) event.getSource()).getValue();
            setTipoPersonaObservable(tipo);
        }catch (Exception e) {
            setTipoPersonaObservable(null);
            //Muestra el Mensaje del Error en la Capa
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", CapturaError.getErrorException(e));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "handleChange", CapturaError.getErrorException(e)});
        }
    }
    
    /**
     * Método de la vista que funciona en el evento change del calendario, se utiliza para asignar la variable fecha corte
     * @param event 
     */
    public void dateChange(SelectEvent event) {
        try{
            Date fecha;
            fecha = (Date)((UIOutput) event.getSource()).getValue();
            setFechaCorte(fecha);
        }catch (Exception e) {
            setTipoPersonaObservable(null);
            //Muestra el Mensaje del Error en la Capa
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", CapturaError.getErrorException(e));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "dateChange", CapturaError.getErrorException(e)});
        }
    }
    
    /**
     * Método de la vista que controla la carga de archivos
     * @param event 
     */
    public void cargaArchivo(FileUploadEvent event){
        try{
            if (FacesContext.getCurrentInstance().isValidationFailed())//Valida campos requeridos
                return;
            
            if (getTipoPersonaObservable() == null){//Valida seleccion del tipo de persona
                msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", 
                                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPersonaObservableNulo"));
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
            if (getTipoPersonaObservable().getCodigo() == 0){//Valida seleccion del tipo de persona
                msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", 
                                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPersonaObservableNulo"));
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
            encerrarListados();
            if (uafCarga == null){//Crea nueva carga siempre que sea nula
                uafCarga = new UafCarga();
                uafCarga.setFechaCorte(getFechaCorte());
            }
            nombreArchivo = event.getFile().getFileName();//toma nombre archivo
            archivo = new File(this.destinoArchivo + nombreArchivo);//toma carpeta raiz del usuario
            if (archivo.exists()) {
                archivo.delete();
            }
            if (copiarArchivo(event.getFile().getInputstream())) {
                //Leyendo archio en formato 2007 o superior
                if (!leeArchivoXLSX()) {
                    //Leyendo archivo en formato 2003 o inferior
                    leeArchivoXLS();
                }
            }
            else{
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                       ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CopiarArchivoUaf") + " : " + this.destinoArchivo );
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            //Muestra el Mensaje del Error en la Capa
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", CapturaError.getErrorException(e));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "cargaArchivo", CapturaError.getErrorException(e)});
            uafCarga = null;
        }
    }
    /**
     * Metodo utilizado para vaciar las variables que se usan para una nueva carga UafCarga
     */
    public void encerrarListados(){
        if (getTipoPersonaObservable().getCodigo() == 1)
            setListaUafHomonimo(null);
       
        if (getTipoPersonaObservable().getCodigo() == 2)
            setListaUafPersonaPoliticamenteExp(null);
        
        if (getTipoPersonaObservable().getCodigo() == 3)
            setListaUafSentenciado(null);
        
        listaUafHomonimoCarga = null;
        listaUafPersonaPoliticamenteExpCarga = null;
        listaUafSentenciadoCarga = null;
        uafHomonimoCarga = null;
        uafPersonaPoliticamenteExpCarga = null;
        uafSentenciadoCarga = null;
    }
    
    /**
     * Metodo utilizado para copiar el archivo al directorio local del usuario
     * @param inputStream
     * @return 
     */
     public boolean copiarArchivo(InputStream inputStream) {
        try {
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
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "copiarArchivo", CapturaError.getErrorException(e)});
            return false;
        }
        return true;
    }
     
     /**
      * Método utilizado para leer archivo XLSX con rowCache, abre archivo forma todas las celdas y lo envia a leer celdas
      * @return 
      */
     private boolean leeArchivoXLSX() {
        List cellDataList = new ArrayList();
        StreamingCell celda;
        try {
            InputStream is = new FileInputStream(new File(this.destinoArchivo + nombreArchivo));
            StreamingReader reader = StreamingReader.builder()
                    .rowCacheSize(100)    // numero de filas que mantiene en memoria
                    .bufferSize(4096)     // tamaño del buffer para leer el archivo InputStream
                    .sheetIndex(0)        // indice de la hoja usada
                    .read(is);            // archivo XLSX
            List cellTempList;
            for (Row fila : reader) {
                cellTempList = new ArrayList();
                for (Cell columna : fila) {
                    celda = (StreamingCell)columna;
                    cellTempList.add(celda);
                }
                cellDataList.add(cellTempList);
            }
            leeCeldasArchivoXLSX(cellDataList);
        } catch (Exception e) {
            //Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "leeArchivoXLSX", CapturaError.getErrorException(e)});

            return false;
        }
        return true;
    }
     
     /**
      * Método utilizado para leer archivo XLS con rowCache, abre archivo forma todas las celdas y lo envia a leer celdas
      * @return 
      */
    private boolean leeArchivoXLS() {
        List cellDataList;
        try {
            cellDataList = new ArrayList();
            fileInputStream = new FileInputStream(this.destinoArchivo + nombreArchivo);
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
     * Método utilizado para leer los valores de las celdas formadas de un archivo XLSX
     * @param cellDataList 
     */
    private void leeCeldasArchivoXLSX(List cellDataList) {       
        ArrayList<Object[]> listado = new ArrayList<Object[]>();
        List cellTempList;
        Object[] entidad;
        StreamingCell celdaActual;
        try {
            for (int i = 1; i < cellDataList.size(); i++) {
                cellTempList =(List) cellDataList.get(i);
                entidad = new Object[5];
                if(getTipoPersonaObservable().getCodigo() == 2)
                    if (cellTempList.size()>5)
                        cellTempList.remove(4);
                
                for (int j = 0; j < cellTempList.size(); j++) {
                    celdaActual = (StreamingCell) cellTempList.get(j);
                    switch(j) {
                                case 0:
                                    entidad[0] = celdaActual.getStringCellValue();
                                    break;
                                case 1:
                                    entidad[1] = celdaActual.getStringCellValue();
                                    break;
                                case 2:
                                    entidad[2] = celdaActual.getStringCellValue();
                                    break;
                                case 3:
                                    entidad[3] = celdaActual.getStringCellValue();
                                    break;
                                case 4:
                                    entidad[4] = celdaActual.getStringCellValue();
                                    break;
                            }
                }
                listado.add(entidad);
            }
            creaListadosCarga(listado);
        } catch (Exception e) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LeeCeldaArchivoXlsx"));
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "leeCeldasArchivoXLSX", CapturaError.getErrorException(e)});
        }
    } 
    
    /**
     * Método utilizado para leer los valores de las celdas formadas de un archivo XLS
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
               entidad = new Object[5];
               if(getTipoPersonaObservable().getCodigo() == 2)
                   if (cellTempList.size()>5) 
                       cellTempList.remove(4);
               
               for (int j = 0; j < cellTempList.size(); j++) {
                   celdaActual = (HSSFCell) cellTempList.get(j);
                   celdaActual.setCellType(Cell.CELL_TYPE_STRING);
                    switch(j) {
                                case 0:
                                    entidad[0] = celdaActual.getStringCellValue();
                                    break;
                                case 1:
                                    entidad[1] = celdaActual.getStringCellValue();
                                    break;
                                case 2:
                                    entidad[2] = celdaActual.getStringCellValue();
                                    break;
                                case 3:
                                    entidad[3] = celdaActual.getStringCellValue();
                                    break;
                                case 4:
                                    entidad[4] = celdaActual.getStringCellValue();
                                    break;
                            }
               }
               listado.add(entidad);
            }
            creaListadosCarga(listado);
        } catch (Exception e) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LeeCeldaArchivoXls"));
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "leeCeldasArchivoXLS", CapturaError.getErrorException(e)});
        }
    }
    
    /**
     * Método utilizado para manejar la carga de celdas se el tipo de persona seleccionado
     * @param lista 
     */
    public void creaListadosCarga(ArrayList<Object[]> lista){
        try {   
            switch (Integer.valueOf(getTipoPersonaObservable().getCodigo().toString())) { 
                case 1:     
                    creaListadoCargaHomonimos(lista);
                    break; 
                case 2: 
                    creaListadoCargaPeps(lista);
                    break;  
                case 3: 
                    creaListadoCargaSentenciados(lista);
                    break;  
            }
        }
        catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "creaListadosCarga", CapturaError.getErrorException(e)});
        }
    }
    
    /**
     * Método utilizado para formar el listado de carga de homonimos valida los valores del archivo
     * @param lista 
     */
    public void creaListadoCargaHomonimos(ArrayList<Object[]> lista){
        listaUafHomonimoCarga = new ArrayList<UafHomonimo>(); 
        linea = 0;
        try {
            for (int c = 0; c < lista.size(); c++) {   
                linea = (c + 2);
                uafHomonimoCarga = new UafHomonimo();
                if(lista.get(c)[0].toString() != null){
                    if(lista.get(c)[0].toString().length() > 0)
                        uafHomonimoCarga.setTipoIdentificacion(lista.get(c)[0].toString());
                    else{
                        MuestraMensaje.addError("Campo tipo identificación nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                        setListaUafHomonimo(null);
                        return;
                    }
                }
                else{ MuestraMensaje.addError("Campo tipo identificación nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                      setListaUafHomonimo(null);
                      return;
                }

                if(lista.get(c)[1].toString() != null){
                    if(lista.get(c)[1].toString().length() > 0)
                        uafHomonimoCarga.setNumeroIdentificacion(lista.get(c)[1].toString()); 
                    else{
                        MuestraMensaje.addError("Campo número identificación nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                        setListaUafHomonimo(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo número identificación nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                         setListaUafHomonimo(null);
                         return;
                }

                if(lista.get(c)[2].toString() != null){
                    if(lista.get(c)[2].toString().length() > 0)
                        uafHomonimoCarga.setApellidos(lista.get(c)[2].toString());
                    else{
                        MuestraMensaje.addError("Campo apellidos nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                        setListaUafHomonimo(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo apellidos nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                        setListaUafHomonimo(null);
                        return;
                }

                if(lista.get(c)[3].toString() != null){
                    if(lista.get(c)[3].toString().length() > 0)
                        uafHomonimoCarga.setNombres(lista.get(c)[3].toString());
                    else{
                        MuestraMensaje.addError("Campo nombres nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                        setListaUafHomonimo(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo nombres nulo en la linea: " + (c + 2) + ", solo se permite NACIONALIDAD como nula.");
                         setListaUafHomonimo(null);
                         return;
                }

                if(lista.get(c)[4].toString() == null)
                    uafHomonimoCarga.setNacionalidad("");
                else
                    uafHomonimoCarga.setNacionalidad(lista.get(c)[4].toString());

                uafHomonimoCarga.setFecha(getFechaCorte());
                uafHomonimoCarga.setCodigoUafCarga(uafCarga);
                listaUafHomonimoCarga.add(uafHomonimoCarga);
             }
             setListaUafHomonimo(listaUafHomonimoCarga);
        }
        catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "creaListadoCargaHomonimos", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error en el formato del archivo, verifique la linea: " + linea);
            setListaUafHomonimo(null);
        }
    }
    
    /**
     * Método utilizado para formar el listado de carga de Peps valida los valores del archivo
     * @param lista 
     */
    public void creaListadoCargaPeps(ArrayList<Object[]> lista){
        listaUafPersonaPoliticamenteExpCarga = new ArrayList<UafPersonaPoliticamenteExp>();
        linea = 0;
        mensajeAdvertenciaPeps = new StringBuilder();
        try {
            for (int c = 0; c < lista.size(); c++) {
                linea = (c + 2);
                uafPersonaPoliticamenteExpCarga = new UafPersonaPoliticamenteExp();
                if(lista.get(c)[0].toString() != null){
                    if(lista.get(c)[0].toString().length() > 0)
                        if (!lista.get(c)[0].toString().toUpperCase().trim().equals("NULL"))
                            uafPersonaPoliticamenteExpCarga.setDocumento(lista.get(c)[0].toString());
                        else{ MuestraMensaje.addError("Campo documento nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                              setListaUafPersonaPoliticamenteExp(null);
                              return;
                        }
                    else{
                        MuestraMensaje.addError("Campo documento nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                        setListaUafPersonaPoliticamenteExp(null);
                        return;
                    }
                }
                else{ MuestraMensaje.addError("Campo documento nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                      setListaUafPersonaPoliticamenteExp(null);
                      return;
                }

                if(lista.get(c)[1].toString() != null){
                    if(lista.get(c)[1].toString().length() > 0)
                        if (!lista.get(c)[1].toString().toUpperCase().trim().equals("NULL"))
                            uafPersonaPoliticamenteExpCarga.setApellidosNombres(lista.get(c)[1].toString()); 
                        else{
                            MuestraMensaje.addError("Campo apellidos nombres nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                            setListaUafPersonaPoliticamenteExp(null);
                            return;
                        }
                    else{
                        MuestraMensaje.addError("Campo apellidos nombres nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                        setListaUafPersonaPoliticamenteExp(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo apellidos nombres nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                         setListaUafPersonaPoliticamenteExp(null);
                         return;
                }

                if(lista.get(c)[2].toString() != null){
                    if(lista.get(c)[2].toString().length() > 0)
                        if (!lista.get(c)[2].toString().toUpperCase().trim().equals("NULL"))
                            uafPersonaPoliticamenteExpCarga.setDenominacionPuesto(lista.get(c)[2].toString());
                        else{
                            MuestraMensaje.addError("Campo denominacion puesto nombres nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                            setListaUafPersonaPoliticamenteExp(null);
                            return;
                        }
                    else{
                        MuestraMensaje.addError("Campo denominacion puesto nombres nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                        setListaUafPersonaPoliticamenteExp(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo denominacion puesto nombres nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                         setListaUafPersonaPoliticamenteExp(null);
                         return;
                }

                if(lista.get(c)[3].toString() == null)
                    uafPersonaPoliticamenteExpCarga.setEntidad("");
                else
                    uafPersonaPoliticamenteExpCarga.setEntidad(lista.get(c)[3].toString());

                if(lista.get(c)[4].toString() != null){
                    if(lista.get(c)[4].toString().length() > 0)
                        if (!lista.get(c)[4].toString().toUpperCase().trim().equals("NULL"))
                            if ((lista.get(c)[4].toString().toUpperCase().trim().length() == 1))
                                uafPersonaPoliticamenteExpCarga.setEstado(lista.get(c)[4].toString());
                            else{
                                MuestraMensaje.addError("Campo estado en la linea: " + (c + 2) + ", solo debe tener un digito.");
                                setListaUafPersonaPoliticamenteExp(null);
                                return;
                            }
                        else{
                            MuestraMensaje.addError("Campo estado nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                            setListaUafPersonaPoliticamenteExp(null);
                            return;
                        }
                    else{
                        MuestraMensaje.addError("Campo estado nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                        setListaUafPersonaPoliticamenteExp(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo estado nulo en la linea: " + (c + 2) + ", solo se permite ENTIDAD como nula.");
                         setListaUafPersonaPoliticamenteExp(null);
                         return;
                }
                uafPersonaPoliticamenteExpCarga.setFecha(getFechaCorte());
                uafPersonaPoliticamenteExpCarga.setCodigoUafCarga(uafCarga);
                
                if(!(lista.get(c)[0].toString().toUpperCase().trim().equals("DOCUMENTO")&&
                    lista.get(c)[1].toString().toUpperCase().trim().equals("APELLIDOS_NOMBRES")&&
                    lista.get(c)[4].toString().toUpperCase().trim().equals("ESTADO"))
                  )
                    listaUafPersonaPoliticamenteExpCarga.add(uafPersonaPoliticamenteExpCarga);
                else{
                    mensajeAdvertenciaPeps.append(((c + 2) + ","));
                }                
            }
            if (mensajeAdvertenciaPeps.length() > 0)
                MuestraMensaje.addAdvertencia("Existen " + mensajeAdvertenciaPeps.length() + " lineas que pertenece a una cabecera DOCUMENTO, APELLIDOS_NOMBRES, DENOMINACION_PUESTO, ENTIDAD, ESTADO, sim embargo el archivo sera procesado");
            
            setListaUafPersonaPoliticamenteExp(listaUafPersonaPoliticamenteExpCarga);
        }catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "creaListadoCargaPeps", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error en el formato del archivo, verifique la linea: " + linea);
            setListaUafPersonaPoliticamenteExp(null);
        }
    }
    
    /**
     * Método utilizado para formar el listado de carga de Sentenciados valida los valores del archivo
     * @param lista 
     */
    public void creaListadoCargaSentenciados(ArrayList<Object[]> lista){
        listaUafSentenciadoCarga = new ArrayList<UafSentenciado>();
        linea = 0;
        try {
            for (int c = 0; c < lista.size(); c++) {
                linea = (c + 2);
                uafSentenciadoCarga = new UafSentenciado();
                if(lista.get(c)[0].toString() != null){
                    if(lista.get(c)[0].toString().length() > 0)
                        uafSentenciadoCarga.setTipoIdentificacion(lista.get(c)[0].toString());
                    else{
                        MuestraMensaje.addError("Campo tipo identificación nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                        setListaUafSentenciado(null);
                        return;
                    }
                }
                else{ MuestraMensaje.addError("Campo tipo identificación nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                      setListaUafSentenciado(null);
                      return;
                }

                if(lista.get(c)[1].toString() != null){
                    if(lista.get(c)[1].toString().length() > 0)
                        uafSentenciadoCarga.setNumeroIdentificacion(lista.get(c)[1].toString()); 
                    else{
                        MuestraMensaje.addError("Campo número identificación nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                        setListaUafSentenciado(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo número identificación nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                         setListaUafSentenciado(null);
                         return;
                }

                if(lista.get(c)[2].toString() != null){
                    if(lista.get(c)[2].toString().length() > 0)
                        uafSentenciadoCarga.setApellidos(lista.get(c)[2].toString());
                    else{
                        MuestraMensaje.addError("Campo apellidos nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                        setListaUafSentenciado(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo apellidos nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                         setListaUafSentenciado(null);
                         return;
                }

                if(lista.get(c)[3].toString() != null){
                    if(lista.get(c)[3].toString().length() > 0)
                        uafSentenciadoCarga.setNombres(lista.get(c)[3].toString());
                    else{
                        MuestraMensaje.addError("Campo nombres nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                        setListaUafSentenciado(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo nombres nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                         setListaUafSentenciado(null);
                         return;
                }

                if(lista.get(c)[4].toString() != null){
                    if(lista.get(c)[4].toString().length() > 0)
                        uafSentenciadoCarga.setNacionalidad(lista.get(c)[4].toString());
                    else{
                        MuestraMensaje.addError("Campo nacionalidad nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                        setListaUafSentenciado(null);
                        return;
                    }
                } else { MuestraMensaje.addError("Campo nacionalidad nulo en la linea: " + (c + 2) + ", no se permite campos nulos.");
                         setListaUafSentenciado(null);
                         return;
                }

                uafSentenciadoCarga.setFecha(getFechaCorte());
                uafSentenciadoCarga.setCodigoUafCarga(uafCarga);
                listaUafSentenciadoCarga.add(uafSentenciadoCarga);
            }
            setListaUafSentenciado(listaUafSentenciadoCarga);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaUafeController", "creaListadoCargaSentenciados", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error en el formato del archivo, verifique la linea: " + linea);
            setListaUafSentenciado(null);
        }
    }

    /**
     * @return the fechaCorte
     */
    public Date getFechaCorte() {
        return fechaCorte;
    }

    /**
     * @param fechaCorte the fechaCorte to set
     */
    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    /**
     * @return the listaUafHomonimo
     */
    public List<UafHomonimo> getListaUafHomonimo() {
        return listaUafHomonimo;
    }

    /**
     * @param listaUafHomonimo the listaUafHomonimo to set
     */
    public void setListaUafHomonimo(List<UafHomonimo> listaUafHomonimo) {
        this.listaUafHomonimo = listaUafHomonimo;
    }

    /**
     * @return the listaUafPersonaPoliticamenteExp
     */
    public List<UafPersonaPoliticamenteExp> getListaUafPersonaPoliticamenteExp() {
        return listaUafPersonaPoliticamenteExp;
    }

    /**
     * @param listaUafPersonaPoliticamenteExp the listaUafPersonaPoliticamenteExp to set
     */
    public void setListaUafPersonaPoliticamenteExp(List<UafPersonaPoliticamenteExp> listaUafPersonaPoliticamenteExp) {
        this.listaUafPersonaPoliticamenteExp = listaUafPersonaPoliticamenteExp;
    }

    /**
     * @return the listaUafSentenciado
     */
    public List<UafSentenciado> getListaUafSentenciado() {
        return listaUafSentenciado;
    }

    /**
     * @param listaUafSentenciado the listaUafSentenciado to set
     */
    public void setListaUafSentenciado(List<UafSentenciado> listaUafSentenciado) {
        this.listaUafSentenciado = listaUafSentenciado;
    }

    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * 
     * @return 
     */
    public List<TipoPersonaObservable> getListaTipoPersonaObservable() {
        return ejbFacadeTipoPersonaObservable.findAll();
    }
    
    /**
     * 
     * @return 
     */
    public TipoPersonaObservable getTipoPersonaObservable() {
        return tipoPersonaObservable;
    }

   /**
    * 
    * @param tipoPersonaObservable 
    */
    public void setTipoPersonaObservable(TipoPersonaObservable tipoPersonaObservable) {
        this.tipoPersonaObservable = tipoPersonaObservable;
    }
}
