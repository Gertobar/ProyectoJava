/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.cargas;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArcDetCom;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivo;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivoDet;
import ec.renafipse.mks.negocio.comunes.FormatoEntradaArchivoDetFacade;
import ec.renafipse.mks.negocio.comunes.FormatoEntradaArchivoFacade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

/**
 *
 * @author andres
 */
@Singleton(name = "cargaArchivoZip")
public class CargaArchivoZip {

    private ZipFile zipFile;
    private final String nombreArchivoDestino = System.getProperty("file.separator") + "archivos" + System.getProperty("file.separator") + "cargas" + System.getProperty("file.separator") + "Zip";
    private File archivo;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    @EJB
    private FormatoEntradaArchivoFacade ejbFacadeFormatoEntradaArchivo;
    @EJB
    private FormatoEntradaArchivoDetFacade ejbFacadeFormatoEntradaArchivoDet;
    @EJB
    private CargaArchivoXls cargaArchivoXls;
    @EJB
    private CargaArchivoXlsx cargaArchivoXlsx;
    @EJB
    private CargaArchivoCsv cargaArchivoCsv;
    @EJB
    private CargaArchivoTxt cargaArchivoTxt;

    /**
     *
     * @param nombreCompletoArchivo
     * @param formatoEntradaArchivo
     * @param listaFormatoEntradaArchivoDetalleComprimido
     * @return
     */
    public CargaArchivo cargaZip(String nombreCompletoArchivo, FormatoEntradaArchivo formatoEntradaArchivo, List<FormatoEntradaArcDetCom> listaFormatoEntradaArchivoDetalleComprimido) {
        CargaArchivo carga;
        int indiceArchivoRetorno = 0;
        String nombreArchivoRetorno;
        int indiceArchivoMd5 = 0;
        String nombreArchivoMd5 = "";
        try {
            //Obtiene el indice de los archivos de carga maximo dos archivos el archivo retorno y el md5
            for (int i = 0; i < listaFormatoEntradaArchivoDetalleComprimido.size(); i++) {
                //Valida que solo un archivo sea configurado para el retorno
                if (listaFormatoEntradaArchivoDetalleComprimido.get(i).getEsRetorno() == 'S') {
                    if (indiceArchivoRetorno == 0) {
                        indiceArchivoRetorno = i;
                    } else {
                        return new CargaArchivo(false, "Existe mas de un archivo parametrizado para retorno", null, null, "");
                    }
                }

                //Valida que solo un archivo sea configurado para el validar su MD5
                if (listaFormatoEntradaArchivoDetalleComprimido.get(i).getEsMd5() == 'S') {
                    if (indiceArchivoMd5 == 0) {
                        indiceArchivoMd5 = i;
                    } else {
                        return new CargaArchivo(false, "Existe mas de un archivo parametrizado para validar MD5", null, null, "");
                    }
                }
            }

            //Valida que no sea el mismo archivo parametrizado para validar el MD5
            if (indiceArchivoRetorno == indiceArchivoMd5) {
                return new CargaArchivo(false, "El mismo archivo esta parametrizado para validar MD5", null, null, "");
            }

            //Crea manejador de archivo zip
            zipFile = new ZipFile(nombreCompletoArchivo);

            // If zip file is password protected then set the password
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(formatoEntradaArchivo.getContrasena());
            }

            //Obtiene el listado de los archivos que se va descomprimir
            List fileHeaderList = zipFile.getFileHeaders();
            if (fileHeaderList.size() != formatoEntradaArchivo.getNumeroColumnasArchivos()) {
                return new CargaArchivo(false, "El numero de archivos parametrizados no coinciden con los del archivo descomprimido", null, null, "");
            }

            //Obtiene el nombre del archivo para carga
            FileHeader fileHeader = (FileHeader) fileHeaderList.get(indiceArchivoRetorno);
            nombreArchivoRetorno = nombreArchivoDestino + System.getProperty("file.separator") + fileHeader.getFileName();

            //Obtiene el nombre del archivo para validar su md5 en caso de estar parametizados
            if (indiceArchivoMd5 != 0) {
                fileHeader = (FileHeader) fileHeaderList.get(indiceArchivoMd5);
                nombreArchivoMd5 = nombreArchivoDestino + System.getProperty("file.separator") + fileHeader.getFileName();
            }

            //Verifica la existencia del archivo y borra en caso de existir
            archivo = new File(nombreArchivoRetorno);
            if (archivo.exists()) {
                archivo.delete();
            }

            //Verifica la existencia del archivo y borra en caso de existir
            if (indiceArchivoMd5 != 0) {
                archivo = new File(nombreArchivoMd5);
                if (archivo.exists()) {
                    archivo.delete();
                }
            }

            //Extrae archivos en la carpeta del servidor
            zipFile.extractAll(nombreArchivoDestino);
            carga = cargarArchivosZip(indiceArchivoRetorno, nombreArchivoRetorno, indiceArchivoMd5, nombreArchivoMd5, listaFormatoEntradaArchivoDetalleComprimido);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"CargaArchivoZip", "cargaZip", CapturaError.getErrorException(e)});
            return new CargaArchivo(false, CapturaError.getErrorException(e), null, null, "");
        }
        return carga;
    }
    
    /**
     * 
     * @param indiceArchivoRetorno
     * @param nombreCompletoArchivoRetorno
     * @param indiceArchivoMd5
     * @param nombreCompletoArchivoMd5
     * @param listaFormatoEntradaArchivoDetalleComprimido
     * @return 
     */
    private CargaArchivo cargarArchivosZip(int indiceArchivoRetorno, String nombreCompletoArchivoRetorno, int indiceArchivoMd5, String nombreCompletoArchivoMd5, List<FormatoEntradaArcDetCom> listaFormatoEntradaArchivoDetalleComprimido){
        CargaArchivo carga;
        String nombreFormatoDentroZip;
        FormatoEntradaArchivo formatoDentroZip;
        try {     
            nombreFormatoDentroZip = listaFormatoEntradaArchivoDetalleComprimido.get(indiceArchivoRetorno).getNombreFormatoEntArc().getNombre();
            formatoDentroZip = ejbFacadeFormatoEntradaArchivo.getFormatoEntradaArchivoPorNombre(nombreFormatoDentroZip);

            if (formatoDentroZip != null) {
                //Carga el archivo principal
                //3. Verifica la existencia de los detalle para el archivo con excepcion de los zip
                List<FormatoEntradaArchivoDet> listaFormatoEntradaArchivoDetalleDentroZip
                        = ejbFacadeFormatoEntradaArchivoDet.getListaFormatoEntradaArchivoDetallePorNombreFormato(nombreFormatoDentroZip);

                //4. Verifica la existencia de los detalle para el archivo con excepcion de los zip
                if (listaFormatoEntradaArchivoDetalleDentroZip != null) {
                    //5. Verifica la existencia de los detalle para el archivo con excepcion de los zip
                    if (listaFormatoEntradaArchivoDetalleDentroZip.size() > 0) {

                        if (formatoDentroZip.getFormatoCarga().toUpperCase().equals("XLS")) {
                            carga = cargaArchivoXls.cargaXls(nombreCompletoArchivoRetorno, formatoDentroZip, listaFormatoEntradaArchivoDetalleDentroZip);
                        } else {
                            if (formatoDentroZip.getFormatoCarga().toUpperCase().equals("XLSX")) {
                                carga = cargaArchivoXlsx.cargaXlsx(nombreCompletoArchivoRetorno, formatoDentroZip, listaFormatoEntradaArchivoDetalleDentroZip);
                            } else {
                                if (formatoDentroZip.getFormatoCarga().toUpperCase().equals("CSV")) {
                                    carga = cargaArchivoCsv.cargaCsv(nombreCompletoArchivoRetorno, formatoDentroZip, listaFormatoEntradaArchivoDetalleDentroZip);
                                } else {
                                    if (formatoDentroZip.getFormatoCarga().toUpperCase().equals("TXT")) {
                                        carga = cargaArchivoTxt.cargaTxt(nombreCompletoArchivoRetorno, formatoDentroZip, listaFormatoEntradaArchivoDetalleDentroZip);
                                    } else {
                                        return new CargaArchivo(false, "Error, formato de carga desconocido", null, null, "");
                                    }
                                }
                            }
                        }
                    } else {
                        return new CargaArchivo(false, "No existe detalle de columnas para formato de carga con el nombre " + nombreFormatoDentroZip, null, null, "");
                    }
                } else {
                    return new CargaArchivo(false, "No existe detalle de columnas para formato de carga con el nombre " + nombreFormatoDentroZip, null, null, "");
                }
            } else {
                return new CargaArchivo(false, "No existe ningun formato de carga con el nombre " + nombreFormatoDentroZip, null, null, "");
            }
            
            if (carga.isCargaCorrecta() && indiceArchivoMd5 != 0) {
                if (!validaMd5(carga.getMd5(), nombreCompletoArchivoMd5))
                    return new CargaArchivo(false, "El archivo cargado no corresponde con su validación MD5", null, null, "");
            }
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"CargaArchivoZip", "cargarArchivosZip", CapturaError.getErrorException(e)});
            return new CargaArchivo(false, CapturaError.getErrorException(e), null, null, "");
        }
        return carga;
    }
    
    /**
     * 
     * @param md5
     * @param nombreArchivoMd5
     * @return 
     */
    private boolean validaMd5(String md5, String nombreArchivoMd5) {
        boolean valido = false;
        try{           
            String cadena;
            String md5Archivo = "";
            fileReader = new FileReader(nombreArchivoMd5);
            bufferedReader = new BufferedReader(fileReader);
            while ((cadena = bufferedReader.readLine()) != null) {
                   md5Archivo =  cadena;
                   break;
            }
            bufferedReader.close();
            if(md5.equals(md5Archivo)){
                valido = true;
            }
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"CargaArchivoZip", "validaMd5", CapturaError.getErrorException(e)});
            return false;
        }
        return valido;
    }
}
