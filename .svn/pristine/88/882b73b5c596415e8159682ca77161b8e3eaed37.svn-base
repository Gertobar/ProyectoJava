/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.utilitario;

import ec.renafipse.mks.capas.cargas.CargaArchivo;
import ec.renafipse.mks.capas.cargas.CargaArchivoCsv;
import ec.renafipse.mks.capas.cargas.CargaArchivoTxt;
import ec.renafipse.mks.capas.cargas.CargaArchivoXls;
import ec.renafipse.mks.capas.cargas.CargaArchivoXlsx;
import ec.renafipse.mks.capas.cargas.CargaArchivoZip;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArcDetCom;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivo;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivoDet;
import ec.renafipse.mks.negocio.comunes.FormatoEntradaArcDetComFacade;
import ec.renafipse.mks.negocio.comunes.FormatoEntradaArchivoDetFacade;
import ec.renafipse.mks.negocio.comunes.FormatoEntradaArchivoFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author andres
 */
@Stateless(name = "cargaFormatoArchivo")
public class CargaFormatoArchivo {

    private String nombreFormato;
    private String nombreCompletoArchivo;
    private String cargaError;
    private boolean cargaCorrecta;
    private FormatoEntradaArchivo formatoEntradaArchivo;
    private List<FormatoEntradaArchivoDet> listaFormatoEntradaArchivoDetalle;
    private List<FormatoEntradaArcDetCom> listaFormatoEntradaArchivoDetalleComprimido;
    private ArrayList<Object[]> resultado;
    private ArrayList<Object[]> resultadoErrores;
    @EJB
    private CargaArchivoXls cargaArchivoXls;
    @EJB
    private CargaArchivoXlsx cargaArchivoXlsx;
    @EJB
    private CargaArchivoCsv cargaArchivoCsv;
    @EJB
    private CargaArchivoTxt cargaArchivoTxt;
    @EJB
    private CargaArchivoZip cargaArchivoZip;
    @EJB
    private FormatoEntradaArchivoFacade ejbFacadeFormatoEntradaArchivo;
    @EJB
    private FormatoEntradaArchivoDetFacade ejbFacadeFormatoEntradaArchivoDet;
    @EJB
    private FormatoEntradaArcDetComFacade ejbFacadeFormatoEntradaArcDetCom;

    /**
     * Constructor del ejb
     */
    public CargaFormatoArchivo() {
    }

    /**
     * Metodo utilizado para la carga del archivo parametrizado en la base de
     * datos
     */
    public void cargaArchivo() {
        try {
            String error;
            cargaError = "";
            //1.- Verifica que exista la propiedad nombreFormato
            if (nombreFormato != null) {
                if (nombreFormato.length() > 0) {
                    if (nombreCompletoArchivo != null) {
                        if (nombreCompletoArchivo.length() > 0) {
                            //2.- Verifica la existencia del formato
                            formatoEntradaArchivo = ejbFacadeFormatoEntradaArchivo.getFormatoEntradaArchivoPorNombre(nombreFormato);
                            if (formatoEntradaArchivo != null) {
                                boolean carga = false;
                                if (formatoEntradaArchivo.getFormatoCarga().toUpperCase().equals("ZIP")) {
                                    listaFormatoEntradaArchivoDetalleComprimido = ejbFacadeFormatoEntradaArcDetCom.getListaFormatoEntradaArchivoDetalleComprimidoPorFormato(formatoEntradaArchivo.getCodigo());
                                    if (listaFormatoEntradaArchivoDetalleComprimido != null) {
                                        if (listaFormatoEntradaArchivoDetalleComprimido.size() > 0) {
                                            CargaArchivo archivoCarga = cargaArchivoZip.cargaZip(nombreCompletoArchivo, formatoEntradaArchivo, listaFormatoEntradaArchivoDetalleComprimido);
                                            carga = archivoCarga.isCargaCorrecta();
                                            error = archivoCarga.getCargaError();
                                        } else {
                                            error = "No existe detalle de archivos para formato de carga con el nombre " + nombreFormato;
                                        }
                                        if (carga) {
                                            setCargaCorrecta(true);
                                        } else {
                                            setCargaCorrecta(false);
                                        }
                                    } else {
                                        error = "No existe detalle de archivos para formato de carga con el nombre " + nombreFormato;
                                    }
                                } else {
                                    //3. Verifica la existencia de los detalle para el archivo con excepcion de los zip
                                    listaFormatoEntradaArchivoDetalle = ejbFacadeFormatoEntradaArchivoDet.getListaFormatoEntradaArchivoDetallePorFormato(formatoEntradaArchivo.getCodigo());
                                    //4. Verifica la existencia de los detalle para el archivo con excepcion de los zip
                                    if (listaFormatoEntradaArchivoDetalle != null) {
                                        //5. Verifica la existencia de los detalle para el archivo con excepcion de los zip
                                        if (listaFormatoEntradaArchivoDetalle.size() > 0) {
                                            //6. Verifica la existencia de los detalle para el archivo con excepcion de los zip
                                            if (formatoEntradaArchivo.getNumeroColumnasArchivos()== listaFormatoEntradaArchivoDetalle.size()) {
                                                if (formatoEntradaArchivo.getFormatoCarga().toUpperCase().equals("XLS")) {
                                                    CargaArchivo archivoCarga = cargaArchivoXls.cargaXls(nombreCompletoArchivo, formatoEntradaArchivo, listaFormatoEntradaArchivoDetalle);
                                                    carga = archivoCarga.isCargaCorrecta();
                                                    error = archivoCarga.getCargaError();
                                                } else {
                                                    if (formatoEntradaArchivo.getFormatoCarga().toUpperCase().equals("XLSX")) {
                                                        CargaArchivo archivoCarga = cargaArchivoXlsx.cargaXlsx(nombreCompletoArchivo, formatoEntradaArchivo, listaFormatoEntradaArchivoDetalle);
                                                        carga = archivoCarga.isCargaCorrecta();
                                                        error = archivoCarga.getCargaError();
                                                    } else {
                                                        if (formatoEntradaArchivo.getFormatoCarga().toUpperCase().equals("CSV")) {
                                                            CargaArchivo archivoCarga = cargaArchivoCsv.cargaCsv(nombreCompletoArchivo, formatoEntradaArchivo, listaFormatoEntradaArchivoDetalle);
                                                            carga = archivoCarga.isCargaCorrecta();
                                                            error = archivoCarga.getCargaError();
                                                        } else {
                                                            if (formatoEntradaArchivo.getFormatoCarga().toUpperCase().equals("TXT")) {
                                                                CargaArchivo archivoCarga = cargaArchivoTxt.cargaTxt(nombreCompletoArchivo, formatoEntradaArchivo, listaFormatoEntradaArchivoDetalle);
                                                                carga = archivoCarga.isCargaCorrecta();
                                                                error = archivoCarga.getCargaError();
                                                            } else {
                                                                error = "Error, formato de carga desconocido";
                                                            }
                                                        }
                                                    }
                                                }
                                                if (carga) {
                                                    setCargaCorrecta(true);
                                                } else {
                                                    setCargaCorrecta(false);
                                                }
                                            } else {
                                                error = "Numero de columnas no coinciden con los parametros de detalle";
                                            }
                                        } else {
                                            error = "No existe detalle de columnas para formato de carga con el nombre " + nombreFormato;
                                        }
                                    } else {
                                        error = "No existe detalle de columnas para formato de carga con el nombre " + nombreFormato;
                                    }
                                }
                            } else {
                                error = "No existe ningun formato de carga con el nombre " + nombreFormato;
                            }
                        } else {
                            error = "Debe asignar un nombre completo para el archivo de carga";
                        }
                    } else {
                        error = "Debe asignar un nombre completo para el archivo de carga";
                    }
                } else {
                    error = "Debe asignar un nombre para el formato de carga";
                }
            } else {
                error = "Debe asignar un nombre para el formato de carga";
            }

            if (error.length() > 0) {
                setCargaCorrecta(false);
                setCargaError(error);
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"CargaFormatoArchivo", "cargaArchivo", CapturaError.getErrorException(e)});
            setCargaCorrecta(false);
            setCargaError(CapturaError.getErrorException(e));
        }
    }

    /**
     * @return the nombreFormato
     */
    private String getNombreFormato() {
        return nombreFormato;
    }

    /**
     * @param nombreFormato the nombreFormato to set
     */
    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    /**
     * @return the cargaError
     */
    public String getCargaError() {
        return cargaError;
    }

    /**
     * @param cargaError the cargaError to set
     */
    private void setCargaError(String cargaError) {
        this.cargaError = cargaError;
    }

    /**
     * @return the cargaCorrecta
     */
    public boolean isCargaCorrecta() {
        return cargaCorrecta;
    }

    /**
     * @param cargaCorrecta the cargaCorrecta to set
     */
    private void setCargaCorrecta(boolean cargaCorrecta) {
        this.cargaCorrecta = cargaCorrecta;
    }

    /**
     * @return the resultado
     */
    public ArrayList<Object[]> getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    private void setResultado(ArrayList<Object[]> resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the resultadoErrores
     */
    public ArrayList<Object[]> getResultadoErrores() {
        return resultadoErrores;
    }

    /**
     * @param resultadoErrores the resultadoErrores to set
     */
    private void setResultadoErrores(ArrayList<Object[]> resultadoErrores) {
        this.resultadoErrores = resultadoErrores;
    }

    /**
     * @return the nombreCompletoArchivo
     */
    private String getNombreCompletoArchivo() {
        return nombreCompletoArchivo;
    }

    /**
     * @param nombreCompletoArchivo the nombreCompletoArchivo to set
     */
    public void setNombreCompletoArchivo(String nombreCompletoArchivo) {
        this.nombreCompletoArchivo = nombreCompletoArchivo;
    }
}
