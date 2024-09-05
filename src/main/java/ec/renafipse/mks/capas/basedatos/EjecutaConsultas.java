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
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author vicastoc
 */
@Stateless(name = "ejecutaConsultas")
public class EjecutaConsultas {

    //@Resource(mappedName = "java:/jboss/datasources/mks")
    private DataSource datasource;

    private final String jndi = "java:/jboss/datasources/mks";
    private String nombreSP;
    private int numeroParametros;
    private List<Object[]> listParametrosEntrada;
    private List<Object[]> listFilas;
    private List<String> listColumnas;
    private List<Integer> listTipoDato;

    private String erroSql = null;
    private String erroCode = null;
    private String error = null;
    private String query = null;
    private boolean formatoFechaHora;
    private boolean formatoDecimales;

    // -- Variable de conexion de la Base de Datos
    private Connection conexionBD;

    private boolean ejecucionCorrecta;
    private boolean cerrarConexion;
    private DecimalFormat formatoValores;
    private SimpleDateFormat formatoFechas;

    // private static final String nombreDataSource = "java:/jboss/datasources/cobrospagos";
    public EjecutaConsultas() {
    }

    public void ejecutaQuery() throws ParseException {

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
                        new Object[]{this.getNombreSP(), "EjecutaConsultas", "Debe colocar la Conexion de la Base de Datos"});
            }

            //org.jboss.jca.adapters.jdbc.jdk6.WrappedConnectionJDK6 wc = (org.jboss.jca.adapters.jdbc.jdk6.WrappedConnectionJDK6) con;
            // Preparando el query para ser enviado
            /*OraclePreparedStatement stmt = (OraclePreparedStatement) getConexionBD().prepareStatement(query);

             // --------------------------------------------------------------------
             // --  Colocando Parametros de Entrada
             for (int c = 0; c < this.getListParametrosEntrada().size(); c++) {
             stmt.setObjectAtName(((Object[]) this.getListParametrosEntrada().get(c))[0].toString(),
             ((Object[]) this.getListParametrosEntrada().get(c))[1]);
             }*/
            //System.out.println("Preparando consulta");
            // Preparando el query para ser enviado
            //OraclePreparedStatement stmt = (OraclePreparedStatement) getConexionBD().prepareStatement(query);
            PreparedStatement stmt = getConexionBD().prepareStatement(query);
            // --------------------------------------------------------------------
            // --  Colocando Parametros de Entrada
            for (int c = 0; c < this.getListParametrosEntrada().size(); c++) {
                stmt.setObject(c + 1,
                        ((Object[]) this.getListParametrosEntrada().get(c))[1]);
            }

            // Ejecutando la Consulta
            ResultSet rs = stmt.executeQuery();

            // Obteniendo los datos de las Columnas
            ResultSetMetaData columns = rs.getMetaData();

            listColumnas = new ArrayList<String>();
            setListTipoDato(new ArrayList<Integer>());
            setListFilas(new ArrayList<Object[]>());

            // --------------------------------------------------------------------
            // Colocando las columnas
            for (int c = 0; c < columns.getColumnCount(); c++) {
                this.getListColumnas().add(columns.getColumnName(c + 1));
                if (columns.getColumnTypeName(c + 1).equals("NUMBER")) {
                    if (columns.getScale(c + 1) > 0) {
                        this.getListTipoDato().add(1); // BigDencimal
                    } else {
                        this.getListTipoDato().add(2); // Entero
                    }
                } else if (columns.getColumnTypeName(c + 1).equals("DATE")) {
                    this.getListTipoDato().add(3); // Fecha
                } else {
                    this.getListTipoDato().add(4); // Strning 
                }
                /*//System.out.println("Tipo Columna " + columns.getColumnType(c + 1));
                //System.out.println("Precision " + columns.getPrecision(c + 1));
                //System.out.println("Scala " + columns.getScale(c + 1));
                //System.out.println("Class Name" + columns.getColumnClassName(c + 1));
                //System.out.println("Column Tipo Name " + columns.getColumnTypeName(c + 1));*/
            }

            // Colocando los Formastos de Fecha y Valores            
            formatoValores = new DecimalFormat("###,###,###,##0.00");
            if (this.isFormatoFechaHora()) {
                formatoFechas = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
            } else {
                formatoFechas = new SimpleDateFormat("dd/MM/yyyy");
            }

            //-----------------------------------------------------------------------
            // Obteniendo Datos
            Object[] dato;
            Object object;
            //BigDecimal numero;
            int fila = -1;
            while (rs.next()) {
                dato = new String[listColumnas.size()];
                fila++;
                for (int i = 0; i < listColumnas.size(); i++) {
                    object = rs.getObject(i + 1);
                    if (object != null) {

                        /*  if (fila == 0) {
                         if (object instanceof Date) {
                         this.getListTIpoDato().add((this.isFormatoFechaHora()) ? 1 : 2);
                         } else if (object instanceof BigDecimal) {
                         this.getListTIpoDato().add((this.isFormatoDecimales()) ? 3 : 4);
                         } else if (object instanceof Long) {
                         this.getListTIpoDato().add(5);
                         } else {
                         this.getListTIpoDato().add(6);
                         }
                         }*/
                        if (object instanceof Date) {
                            //(this.isFormatoFechaHora()) ? this.convertirFecha((Date) object) : this.convertirFecha((Date) object)
                            dato[i] = formatoFechas.format((Date) object); //formatoFechas.parse();
                        } else if (object instanceof BigDecimal) {
                            if (this.isFormatoDecimales()) {
                                //numero = new BigDecimal(formatoValores.format(((BigDecimal) object).doubleValue()));
                                dato[i] = (this.getListTipoDato().get(i) == 1) ? formatoValores.format(((BigDecimal) object).doubleValue()) : object.toString();
                                        
                            } else {
                                //numero = new BigDecimal(object.toString());
                                dato[i] = object.toString();//(Object) numero;
                            }
                        }/* else if (object instanceof Long) {
                         dato[i] = (Object) Long.parseLong(object.toString());

                         } */ else {
                            dato[i] = object.toString();
                        }
                    } else {
                        dato[i] = null;
                    }

                }
                getListFilas().add(dato);
            }

        } catch (SQLException ex) {
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
            } catch (SQLException eCBD) {
                // No se ejecuto correctamente el SP
                this.setEjecucionCorrecta(false);

                // Muestra el mensaje
                MuestraMensaje.addErrorCapaNegocio();

                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorConxionBD, new Object[]{CapturaError.getErrorException(eCBD)});

            }
        }
    }

    public void cargaConexion() {
        try {
            InitialContext ic = new InitialContext();
            datasource = (DataSource) ic.lookup(jndi);
            datasource.setLoginTimeout(30);
            this.setConexionBD(this.datasource.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(EjecutaConsultas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(EjecutaConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            this.conexionBD.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(EjecutaConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            this.conexionBD.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EjecutaConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarConexion() {
        try {
            if (!this.conexionBD.isClosed()) {
                this.conexionBD.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EjecutaConsultas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EjecutaConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String convertirFecha(Date fecha) throws ParseException {

        String fechaAux = ((fecha.getDate() < 10) ? "0" + (fecha.getDate()) : fecha.getDate()) + "/" + ((fecha.getMonth() + 1 < 10) ? "0" + (fecha.getMonth() + 1) : fecha.getMonth() + 1) + "/" + (fecha.getYear() + 1900);
        //System.out.println("fechaAux  " + fechaAux);
        return fechaAux;
    }

    public String convertirFechaHora(Date fecha) throws ParseException {
        //System.out.println("fecha " + fecha.getSeconds());
        String fechaAux = ((fecha.getDate() < 10) ? "0" + (fecha.getDate()) : fecha.getDate()) + "/" + ((fecha.getMonth() + 1 < 10) ? "0" + (fecha.getMonth() + 1) : fecha.getMonth() + 1) + "/" + (fecha.getYear() + 1900)
                + " " + ((fecha.getHours() < 10) ? "0" + (fecha.getHours()) : fecha.getHours()) + ":" + ((fecha.getMinutes() < 10) ? "0" + (fecha.getMinutes()) : fecha.getMinutes()) + ":" + ((fecha.getSeconds() < 10) ? "0" + (fecha.getSeconds()) : fecha.getSeconds());

        //System.out.println("fechaAux Hora " + fechaAux);
        return fechaAux;
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

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return the listColumnas
     */
    public List<String> getListColumnas() {
        return listColumnas;
    }

    /**
     * @param listColumnas the listColumnas to set
     */
    public void setListColumnas(List<String> listColumnas) {
        this.listColumnas = listColumnas;
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
     * @return the formatoFechaHora
     */
    public boolean isFormatoFechaHora() {
        return formatoFechaHora;
    }

    /**
     * @param formatoFechaHora the formatoFechaHora to set
     */
    public void setFormatoFechaHora(boolean formatoFechaHora) {
        this.formatoFechaHora = formatoFechaHora;
    }

    /**
     * @return the formatoDecimales
     */
    public boolean isFormatoDecimales() {
        return formatoDecimales;
    }

    /**
     * @param formatoDecimales the formatoDecimales to set
     */
    public void setFormatoDecimales(boolean formatoDecimales) {
        this.formatoDecimales = formatoDecimales;
    }

    /**
     * @return the listFilas
     */
    public List<Object[]> getListFilas() {
        return listFilas;
    }

    /**
     * @param listFilas the listFilas to set
     */
    public void setListFilas(List<Object[]> listFilas) {
        this.listFilas = listFilas;
    }

    /**
     * @return the listTIpoDato
     */
    public List<Integer> getListTipoDato() {
        return listTipoDato;
    }

    /**
     * @param listTIpoDato the listTIpoDato to set
     */
    public void setListTipoDato(List<Integer> listTIpoDato) {
        this.listTipoDato = listTIpoDato;
    }

}
