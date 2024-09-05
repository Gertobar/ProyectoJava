/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.basedatos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfe;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author vicastoc
 */
@Stateless(name = "LlamaSP")
public class LlamaSP {
    
    /*PARAMETROS PARA PRODUCCION*/
    //private final String sid = "jdbc:oracle:thin:@172.25.0.10:1521/mascoop";
    //private final String usuario = "MKP";
    //private final String contrasena = "MKP";
    /*PARAMETROS PARA PRODUCCION*/
    private String nombreSP;
    private int numeroParametros;
    private List<Object[]> listParametrosEntrada;
    private List<Object[]> listParametrosSalida;
    private List<Object> listResultado;
    private List<MovimientoBovedaDesEfe> itemsMovimientoBovedaDesEfes;   
    

    private String erroSql = null;
    private String erroCode = null;
    private String error = null;

    // -- Variable de conexion de la Base de Datos
    private Connection conexionBD;

    private boolean ejecucionCorrecta;
    private boolean cerrarConexion;

    // private static final String nombreDataSource = "java:/jboss/datasources/cobrospagos";
    public LlamaSP() {
    }

    public void invocaSP() {

        try {

            // Limpiando variables de control de error
            this.setError(null);
            this.setErroCode(null);
            this.setErroSql(null);
            this.setEjecucionCorrecta(true);

            if (this.conexionBD == null) {
                // No se ejecuto correctamente el SP
                this.setEjecucionCorrecta(false);

                // Muestra el mensaje
                MuestraMensaje.addErrorCapaNegocio();

                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoSP,
                        new Object[]{this.getNombreSP(), "InvocaSP", "Debe colocar la Conexion de la Base de Datos"});
            }

            // --------------------------------------------------------------------
            // --  Preparando el SP a invocar
            CallableStatement callableStatement;
            String prepareCall = "{call " + this.getNombreSP() + "(";
            for (int c = 0; c < this.getNumeroParametros(); c++) {
                prepareCall += (c == this.getNumeroParametros() - 1 ? "?,?,?,?)}" : "?,");

            }

            // Colocando el SP a invocar 
            callableStatement = getConexionBD().prepareCall(prepareCall);

            //////System.out.println("Proceso: "+prepareCall+" "+this.getListParametrosSalida().size());
            // -- Fin de preparar el SP a invocar
            // --------------------------------------------------------------------
            // --------------------------------------------------------------------
            // --  Colocando Parametros de Entrada
            for (int c = 0; c < this.getListParametrosEntrada().size(); c++) {
                callableStatement.setObject(((Object[]) this.getListParametrosEntrada().get(c))[0].toString(),
                        ((Object[]) this.getListParametrosEntrada().get(c))[1]);
            }

            // --------------------------------------------------------------------
            // --  Colocando Parametros de Salida - Excepto los que van por defecto
            // --  como error, errorSql, errorCode                                    
            for (int c = 0; c < this.getListParametrosSalida().size(); c++) {
                //////System.out.println(" c " + c);
                callableStatement.registerOutParameter(((Object[]) this.getListParametrosSalida().get(c))[0].toString(),
                        Integer.parseInt(((Object[]) this.getListParametrosSalida().get(c))[1].toString()));
            }

            //System.out.println("nombreSP " + this.nombreSP);
            ////System.out.println("getListParametrosEntrada " + getListParametrosEntrada());
            //System.out.println("getListParametrosSalida " + getListParametrosSalida());
            //-- Agregando los parametros de salida obligatorio
            callableStatement.registerOutParameter("pv_error_sql", Types.VARCHAR);
            callableStatement.registerOutParameter("pv_error_code", Types.VARCHAR);
            callableStatement.registerOutParameter("pv_error", Types.VARCHAR);            
            // -- Fin de colocar parametros de Salida
            // --------------------------------------------------------------------            

            //System.out.println("para ejecutar " + prepareCall);
            //-- Ejecutando el SP
            callableStatement.executeUpdate();

            //System.out.println("Ejecuto: " + prepareCall);

            List<Object> listResultadoSP = new ArrayList<Object>();
            // Verificando que no hayan existido errores
            if (callableStatement.getString("pv_error") == null) {

                // Recuperando paremetros de salida
                for (int c = 0; c < this.getListParametrosSalida().size(); c++) {
                    Object objeto = callableStatement.getObject((this.getListParametrosSalida().get(c))[0].toString());
                    listResultadoSP.add((objeto != null) ? objeto : null);
                }
                this.setListResultado(listResultadoSP);
            } else {

                this.setErroSql((callableStatement.getString("pv_error_sql") != null) ? callableStatement.getString("pv_error_sql").toString() : null);
                this.setErroCode((callableStatement.getString("pv_error_code") != null) ? callableStatement.getString("pv_error_code").toString() : null);
                this.setError(callableStatement.getString("pv_error").toString());

                // Muestra el mensaje
                MuestraMensaje.addAdvertencia(this.getError());

                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorDadoPorSP,
                        new Object[]{this.getNombreSP(), this.getErroSql(), this.getErroCode(), this.getError()});

                // No se ejecuto correctamente el SP
                this.setEjecucionCorrecta(false);
            }

            // Cerrando conexion de base de datos
            if (this.cerrarConexion) {
                getConexionBD().close();
            }

        } catch (Exception ex) {

            // No se ejecuto correctamente el SP
            this.setEjecucionCorrecta(false);

            // Muestra el mensaje
            MuestraMensaje.addErrorCapaNegocio();

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoSP,
                    new Object[]{this.getNombreSP(), this.getErroSql(), CapturaError.getErrorException(ex)});
            try {
                if (getConexionBD() != null) {
                    getConexionBD().close();
                }
            } catch (Exception eCBD) {
                // No se ejecuto correctamente el SP
                this.setEjecucionCorrecta(false);

                // Muestra el mensaje
                MuestraMensaje.addErrorCapaNegocio();

                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorConxionBD, new Object[]{CapturaError.getErrorException(eCBD)});

            }
        }
    }

    public void cargaConexion(){
        try {        
            //gestionConexion = new GestionConexion();
            this.conexionBD = new GestionConexion().crearConexion(ActivacionUsuario.getRutaArchivoConfiguracionServidor());            
        } catch (Exception ex) {
            Logger.getLogger(LlamaSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            this.conexionBD.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(LlamaSP.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void commit() {
        try {
            this.conexionBD.commit();
        } catch (SQLException ex) {
            Logger.getLogger(LlamaSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            if (!this.conexionBD.isClosed()) {
                this.conexionBD.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LlamaSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param autoCommit
     */
    public void autoCommit(boolean autoCommit) {
        try {
            this.conexionBD.setAutoCommit(autoCommit);
        } catch (SQLException ex) {
            Logger.getLogger(LlamaSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the nombreSP
     */
    public String getNombreSP() {
        return nombreSP;
    }

    /**
     * @param nombreSP the nombreSP to set
     */
    public void setNombreSP(String nombreSP) {
        this.nombreSP = nombreSP;
    }

    /**
     * @return the numeroParametros
     */
    public int getNumeroParametros() {
        return numeroParametros;
    }

    /**
     * @param numeroParametros the numeroParametros to set
     */
    public void setNumeroParametros(int numeroParametros) {
        this.numeroParametros = numeroParametros;
    }

    /**
     * @return the listParametrosEntrada
     */
    public List<Object[]> getListParametrosEntrada() {
        return listParametrosEntrada;
    }

    /**
     * @param listParametrosEntrada the listParametrosEntrada to set
     */
    public void setListParametrosEntrada(List<Object[]> listParametrosEntrada) {
        this.listParametrosEntrada = listParametrosEntrada;
    }

    /**
     * @return the listParametrosSalida
     */
    public List<Object[]> getListParametrosSalida() {
        return listParametrosSalida;
    }

    /**
     * @param listParametrosSalida the listParametrosSalida to set
     */
    public void setListParametrosSalida(List<Object[]> listParametrosSalida) {
        this.listParametrosSalida = listParametrosSalida;
    }

    /**
     * @return the listResultado
     */
    public List<Object> getListResultado() {
        return listResultado;
    }

    /**
     * @param listResultado the listResultado to set
     */
    public void setListResultado(List<Object> listResultado) {
        this.listResultado = listResultado;
    }

    /**
     * @return the erroSql
     */
    public String getErroSql() {
        return erroSql;
    }

    /**
     * @param erroSql the erroSql to set
     */
    public void setErroSql(String erroSql) {
        this.erroSql = erroSql;
    }

    /**
     * @return the erroCode
     */
    public String getErroCode() {
        return erroCode;
    }

    /**
     * @param erroCode the erroCode to set
     */
    public void setErroCode(String erroCode) {
        this.erroCode = erroCode;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the ejecucionCorrecta
     */
    public boolean isEjecucionCorrecta() {
        return ejecucionCorrecta;
    }

    /**
     * @param ejecucionCorrecta the ejecucionCorrecta to set
     */
    public void setEjecucionCorrecta(boolean ejecucionCorrecta) {
        this.ejecucionCorrecta = ejecucionCorrecta;
    }

    /**
     * @return the itemsMovimientoBovedaDesEfes
     */
    public List<MovimientoBovedaDesEfe> getItemsMovimientoBovedaDesEfes() {
        return itemsMovimientoBovedaDesEfes;
    }

    /**
     * @param itemsMovimientoBovedaDesEfes the itemsMovimientoBovedaDesEfes to
     * set
     */
    public void setItemsMovimientoBovedaDesEfes(List<MovimientoBovedaDesEfe> itemsMovimientoBovedaDesEfes) {
        this.itemsMovimientoBovedaDesEfes = itemsMovimientoBovedaDesEfes;
    }

    /**
     * @return the conexionBD
     */
    public Connection getConexionBD() {
        return conexionBD;
    }

    /**
     * @param conexionBD the conexionBD to set
     */
    public void setConexionBD(Connection conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * @return the cerrarConexion
     */
    public boolean isCerrarConexion() {
        return cerrarConexion;
    }

    /**
     * @param cerrarConexion the cerrarConexion to set
     */
    public void setCerrarConexion(boolean cerrarConexion) {
        this.cerrarConexion = cerrarConexion;
    }

}
