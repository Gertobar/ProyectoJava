/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.basedatos;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FliaAstudillo
 */
public class GestionConexion {

    private String usuario = "MKP";
    private String contrasena = "MKP";

    public GestionConexion() {
    }

    public Connection crearConexion(String pathArchivoConfiguracion) {
        Connection conexionBD = null;
        try {
            //pathArchivoConfiguracion="/opt/wildfly-11.0.0.Final/standalone/configuration/standalone.xml";
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conexionBD = DriverManager.getConnection(this.obtieneURL(pathArchivoConfiguracion), usuario, contrasena);
            /*if (conexionBD != null) {
                System.err.println("Conexion "+conexionBD.toString());
            } else {
                conexionBD = null;
            }*/
        } catch (Exception ex) {
            Logger.getLogger(GestionConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexionBD;
    }

    public void rollback(Connection conexionBD) {
        try {
            conexionBD.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(GestionConexion.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void commit(Connection conexionBD) {
        try {
            conexionBD.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GestionConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion(Connection conexionBD) {
        try {
            if (!conexionBD.isClosed()) {
                conexionBD.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param conexionBD
     * @param autoCommit
     */
    public void autoCommit(Connection conexionBD, boolean autoCommit) {
        try {
            conexionBD.setAutoCommit(autoCommit);
        } catch (SQLException ex) {
            Logger.getLogger(GestionConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene la URL del Archivo de Configuracion
     *
     * @return URL
     */
    private String obtieneURL(String pathArchivoConfiguracion) {
        String URL = null;
        boolean datasourseMKS = false;
        try {

            String cadena;
            int posicionInicio = 0;
            int posicionFin = 0;
            String cadenaInicio = "<connection-url>";
            String cadenaFin = "</connection-url>";
            String cadenaDatasource = "java:jboss/datasources/mks";
            FileReader f = new FileReader(pathArchivoConfiguracion);
            BufferedReader b = new BufferedReader(f);

            while ((cadena = b.readLine()) != null) {

                if (cadena.lastIndexOf(cadenaDatasource) != -1) {
                    datasourseMKS = true;
                }

                if (cadena.lastIndexOf(cadenaInicio) != -1 && datasourseMKS) {
                    posicionInicio = cadena.lastIndexOf(cadenaInicio);
                    posicionInicio += cadenaInicio.length();
                    posicionFin = cadena.lastIndexOf(cadenaFin);
                    URL = cadena.substring(posicionInicio, posicionFin);
                    b.close();
                    break;

                }
            }
            b.close();
        } catch (IOException exe) {
            // Muestra el mensaje
            MuestraMensaje.addAdvertencia("Error al leer archivo de configuracion para crear  la conexion hacia la base de datos");

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorConxionBD,
                    new Object[]{CapturaError.getErrorException(exe)});
        }

        System.out.print("URL: " + URL);
        return URL;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    private String getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
