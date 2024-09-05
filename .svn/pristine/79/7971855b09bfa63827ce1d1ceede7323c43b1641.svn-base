/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.cargas;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.Utilidades;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivo;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivoDet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;

/**
 *
 * @author andres
 */
@Singleton(name = "cargaArchivoTxt")
public class CargaArchivoTxt {

    private InputStream inputStream;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final DateFormat dateFormatHour = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    private String md5;

    /**
     *
     * @param nombreCompletoArchivo
     * @param formatoEntradaArchivo
     * @param listaFormatoEntradaArchivoDetalle
     * @return
     */
    public CargaArchivo cargaTxt(String nombreCompletoArchivo, FormatoEntradaArchivo formatoEntradaArchivo, List<FormatoEntradaArchivoDet> listaFormatoEntradaArchivoDetalle) {
        CargaArchivo carga;
        List listadoArchivo;
        try {
            listadoArchivo = new ArrayList();
            String cadena;
            int numeroLinea = 0;
            inputStream = new FileInputStream(new File(nombreCompletoArchivo));
            fileReader = new FileReader(nombreCompletoArchivo);
            bufferedReader = new BufferedReader(fileReader);
            List listaLinea;
            String separador = "";

            if (formatoEntradaArchivo.getSeparadorColumnas().toUpperCase().equals("TABULAR")) {
                separador = "\\t";
            }

            if (formatoEntradaArchivo.getSeparadorColumnas().toUpperCase().equals("COMA")) {
                separador = ",";
            }

            if (formatoEntradaArchivo.getSeparadorColumnas().toUpperCase().equals("ESPACIO")) {
                separador = " ";
            }

            while ((cadena = bufferedReader.readLine()) != null) {
                if (numeroLinea >= formatoEntradaArchivo.getNumeroFilaInicial()) {
                    listaLinea = new ArrayList();
                    String[] vectorValores = cadena.split(separador);
                    listaLinea.addAll(Arrays.asList(vectorValores));
                    listadoArchivo.add(listaLinea);
                }
                numeroLinea++;
            }
            bufferedReader.close();
            if (formatoEntradaArchivo.getNumeroMaximoFilas() != Long.valueOf("0")) {
                if (formatoEntradaArchivo.getNumeroMaximoFilas() > (numeroLinea - formatoEntradaArchivo.getNumeroFilaInicial())) {
                    return new CargaArchivo(false, "El numero maximo de filas parametrizado es de: " + formatoEntradaArchivo.getNumeroMaximoFilas(), null, null, "");
                }
            }
            //Envia Objeto a validar las filas y columnas
            carga = cargaLineasTxt(listadoArchivo, formatoEntradaArchivo, listaFormatoEntradaArchivoDetalle);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"CargaArchivoTxt", "cargaTxt", CapturaError.getErrorException(e)});
            return new CargaArchivo(false, CapturaError.getErrorException(e), null, null, "");
        }
        return carga;
    }

    /**
     *
     * @param filas
     * @param formatoEntradaArchivo
     * @param listaFormatoEntradaArchivoDetalle
     * @return
     */
    private CargaArchivo cargaLineasTxt(List filas, FormatoEntradaArchivo formatoEntradaArchivo, List<FormatoEntradaArchivoDet> listaFormatoEntradaArchivoDetalle) {
        CargaArchivo carga;
        ArrayList<Object[]> listado = new ArrayList<Object[]>();
        ArrayList<Object[]> listadoError = new ArrayList<Object[]>();
        try {
            List camposObjetos;
            Object objeto[];
            Object objetoError[];
            Object campoActual;
            boolean columnaValida;
            int valorEntero;
            BigDecimal valorDecimal;
            Date valorFecha;
            for (int i = 0; i < filas.size(); i++) {
                //Obtiene las celdas convertidas en objetos
                camposObjetos = (List) filas.get(i);
                //Instancia un nuevo vector de objetos segun los parametros de numero de columnas
                objeto = new Object[formatoEntradaArchivo.getNumeroColumnasArchivos()];
                //Instancia un nuevo vector de objetos error que devuelve el numero de fila y el error que tiene
                objetoError = new Object[2];

                //Verifica que solo se de lectura a la informacion de la linea de cabecera y las lineas inicial y despueste de esta
                if ((i == formatoEntradaArchivo.getNumeroFilaCabecera()) || (i >= formatoEntradaArchivo.getNumeroFilaInicial())) {

                    //Valida el numero de columnas encontrado en una fila, si no permite carga con errores genera error en el carga.
                    if (formatoEntradaArchivo.getNumeroColumnasArchivos() != camposObjetos.size()) {
                        //Si permite lineas con error en caso de no tener el numero de columnas correcto genera una linea con error
                        if (formatoEntradaArchivo.getPermiteLineasConError() == 'N') {
                            return new CargaArchivo(false, "Numero de columnas del archivo no corresponde con los parametros, error en la fila "
                                    + (i + 1), null, null, "");
                        }
                    }

                    //Ingresa a verificar cada columna en caso de que la fila corresponda con los parametros
                    for (int j = 0; j < listaFormatoEntradaArchivoDetalle.size(); j++) {
                        valorEntero = 0;
                        valorDecimal = null;
                        valorFecha = null;

                        //Valida los parametros ingresados para el nombre de cada columna
                        if ((i == formatoEntradaArchivo.getNumeroFilaCabecera()) && (formatoEntradaArchivo.getVerificaCabeceraArchivo() == 'S')) {
                            //Toma valor de la celda actual
                            campoActual = (String) camposObjetos.get(j);
                            //Verifica cada nombre de columna dependiendo del orden parametrizado en el sistema 
                            if (!(campoActual.toString().toUpperCase().equals(listaFormatoEntradaArchivoDetalle.get(j).getNombreColumna().toUpperCase()))) {
                                return new CargaArchivo(false, "El nombre de columnas: "
                                        + campoActual.toString().toUpperCase()
                                        + " no corresponde con lo parametrizado: "
                                        + listaFormatoEntradaArchivoDetalle.get(j).getNombreColumna().toUpperCase()
                                        + ". Favor verifique el orden de columnas y si el archivo valida cabecera o no.", null, null, "");

                            }
                        }

                        //Verifica los datos de la columna
                        if (i >= formatoEntradaArchivo.getNumeroFilaInicial()) {
                            columnaValida = true;
                            //Verifica valores para una columna de tipo texto
                            if (listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna().toUpperCase().equals("TEXTO")) {
                                try {
                                    campoActual = (String) camposObjetos.get(j);
                                    if ((listaFormatoEntradaArchivoDetalle.get(j).getEsObligatorio() == 'S')) {
                                        if (campoActual.toString().replaceAll(" ", "").length() > 0) {
                                            objeto[j] = campoActual.toString();
                                        } else {
                                            columnaValida = false;
                                        }
                                    } else {
                                        objeto[j] = campoActual.toString();
                                    }
                                } catch (Exception e) {
                                    columnaValida = false;
                                }
                            } else {
                                //Verifica valores para una columna de tipo entero 
                                if (listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna().toUpperCase().equals("ENTERO")) {
                                    try {
                                        campoActual = (String) camposObjetos.get(j);
                                        double valorFloat = Double.valueOf(campoActual.toString());
                                        valorEntero = (int) valorFloat;
                                        objeto[j] = valorEntero;
                                    } catch (Exception e) {
                                        columnaValida = false;
                                    }
                                } else //Verifica valores para una columna de tipo decimal
                                {
                                    if (listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna().toUpperCase().equals("DECIMAL")) {
                                        try {
                                            campoActual = (String) camposObjetos.get(j);
                                            String valorString = campoActual.toString();
                                            if (formatoEntradaArchivo.getSeparadorDecimales().equals("COMA")) {
                                                valorString = valorString.replaceAll(",", ".");
                                            }
                                            valorDecimal = new BigDecimal(valorString);
                                            objeto[j] = valorDecimal;
                                        } catch (Exception e) {
                                            columnaValida = false;
                                        }
                                    } else //Verifica valores para una columna de tipo Date
                                    {
                                        if (listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna().toUpperCase().equals("FECHA")) {
                                            try {
                                                campoActual = (String) camposObjetos.get(j);
                                                campoActual = campoActual.toString().replaceAll("/", "-");
                                                valorFecha = dateFormat.parse(campoActual.toString());
                                                objeto[j] = valorFecha;
                                            } catch (Exception e) {
                                                columnaValida = false;
                                            }
                                        } else {
                                            if (listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna().toUpperCase().equals("FECHAHORA")) {
                                                try {
                                                    campoActual = (String) camposObjetos.get(j);
                                                    campoActual = campoActual.toString().replaceAll("/", "-");
                                                    valorFecha = dateFormatHour.parse(campoActual.toString());
                                                    objeto[j] = valorFecha;
                                                } catch (Exception e) {
                                                    columnaValida = false;
                                                }
                                            } else {
                                                return new CargaArchivo(false, "No se reconoce el tipo de dato: "
                                                        + listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna()
                                                        + ", error en la fila "
                                                        + (i + 1), null, null, "");
                                            }
                                        }
                                    }
                                }
                            }
                            //Verifica la columna Valida
                            if (!columnaValida) {
                                if ((formatoEntradaArchivo.getPermiteLineasConError() == 'N') || (listaFormatoEntradaArchivoDetalle.get(j).getEsObligatorio() == 'S')) {
                                    return new CargaArchivo(false, "No se permite la carga con errores, verifique los valores en la linea: "
                                            + (i + 1) + ", el campo: "
                                            + listaFormatoEntradaArchivoDetalle.get(j).getNombreColumna()
                                            + " es obligatorio y de tipo "
                                            + listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna()
                                            + ", no se puede obtener su valor.", null, null, "");
                                } else {
                                    objeto[j] = null;
                                    objetoError[0] = (i + 1);
                                    objetoError[1] = "Error al obtener valor en el campo: "
                                            + listaFormatoEntradaArchivoDetalle.get(j).getNombreColumna()
                                            + ", Verifique valores nulos o el tipo de dato: "
                                            + listaFormatoEntradaArchivoDetalle.get(j).getTipoDatoColumna()
                                            + ", error en la fila " + (i + 1);
                                }
                            }
                        }
                    }
                    //Añade una fila cargada correctamente
                    if (i >= formatoEntradaArchivo.getNumeroFilaInicial()) {
                        if (objetoError[0] == null) {
                            listado.add(objeto);
                        } else {
                            listadoError.add(objetoError);
                        }
                    }
                }
            }
            md5 = Utilidades.generaMD5(inputStream);
            carga = new CargaArchivo(true, "", listado, listadoError, md5);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"CargaArchivoTxt", " cargaLineasTxt", CapturaError.getErrorException(e)});
            return new CargaArchivo(false, CapturaError.getErrorException(e), null, null, "");
        }
        return carga;
    }
}
