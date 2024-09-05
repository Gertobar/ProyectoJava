/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.utilitario;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

/**
 *
 * @author vicastoc
 */
public class CapturaError {

    // Usados para escribir en el log del servidor
    public static final String errorNoCapturado = "Error no capturado en metodo. Metodo: {0}. Error: {1}";
    public static final String errorNoCapturadoSP = "Error no capturado en invocar el SP : {0}. Error SQL: {1}. General : {2}";
    public static final String errorConxionBD = "Error al Cerrar la Conexion : {0}.";
    public static final String errorDadoPorSP = "Error en invocar el SP : {0}. Error SQL: {1}. Error Code{2}. Error  {3}";
    public static final String errorEnCapaDeNegocio = "Error en invocar en la Capa de Negocio: {0}. Error: {1}.";
    public static final String errorNoCapturadoCapaDeControl = "Error en el managed bean: {0}. Metodo: {1}. Error: {2}.";
    public static final String errorNoCapturadoCapaDeNegocio = "Error en el EJB: {0}. Metodo: {1}. Error: {2}.";

    public static String getErrorPersistencia(Exception ex) {
        if (ex.toString().toUpperCase().lastIndexOf("UK") != -1) {
            return ResourceBundle.getBundle("/propiedadesMensajesEC").getString("Uk");
        }

        if (ex.toString().toUpperCase().lastIndexOf("PK") != -1) {
            return ResourceBundle.getBundle("/propiedadesMensajesEC").getString("Pk");
        }

        if (ex.toString().toUpperCase().lastIndexOf("FK") != -1) {
            return ResourceBundle.getBundle("/propiedadesMensajesEC").getString("Fk");
        }

        if (ex.toString().toUpperCase().lastIndexOf("CK") != -1) {
            return ResourceBundle.getBundle("/propiedadesMensajesEC").getString("Ck");
        }
       
        return ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorPersistencia");
    }

    public static String getErrorException(Exception ext) {
        String excepcion = null;
        if (ext instanceof ArrayIndexOutOfBoundsException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DesbordamientoArreglo");
        }

        if (ext instanceof FileNotFoundException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ArchivoNoEncontrado");
        }

        if (ext instanceof DataFormatException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FormatoDeDato");

        }

        if (ext instanceof ClassNotFoundException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ClaseNoEncontrada");
            return excepcion;
        }

        if (ext instanceof ClassCastException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CasteoDeDatos");

        }

        if (ext instanceof IndexOutOfBoundsException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IndiceFueraRango");
        }

        if (ext instanceof NullPointerException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TratamientoValoresNulos");
        }

        if (ext instanceof SQLException) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SqlConErrores");
        }

        if (excepcion == null) {
            excepcion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("General");
        }
        excepcion += " " + ext.getLocalizedMessage();

        return excepcion;

    }

}
