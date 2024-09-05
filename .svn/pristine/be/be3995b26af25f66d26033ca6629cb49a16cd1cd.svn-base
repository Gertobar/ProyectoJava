/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.seguridades.negocio.dao;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;

import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vicastoc
 */
@Stateless
public class SesionMKSDAO {

    private LlamaSP llamaSp = //Instancion el proceso de llama Store Procedure
            new LlamaSP();

    private String erroSql = null;
    private String erroCode = null;
    private String error = null;
    private Vector vecDatos = null;
    private boolean ejecucionCorrecta;

    public void validarUsuario(String username,
            String contrasena) {
        this.setVecDatos(new Vector());

        try {
            //////System.out.println("IP: "+ObtieneInformacionCliente.obtenerDireccionIP()+" HN "+ObtieneInformacionCliente.obtenerNombreEquipo());
            llamaSp.setNombreSP("mks_seguridades.pkg_login.p_valida_usuario");
            llamaSp.setNumeroParametros(14);

            llamaSp.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSp.getListParametrosEntrada().add(new Object[]{"p_username", username});
            llamaSp.getListParametrosEntrada().add(new Object[]{"pv_contrasena", contrasena});
            llamaSp.getListParametrosEntrada().add(new Object[]{"p_codigo_sistema", 2});
            llamaSp.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});

            llamaSp.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSp.getListParametrosSalida().add(new Object[]{"p_codigo_usuario", Types.NUMERIC});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_correo_electronico", Types.VARCHAR});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_nombre_completo", Types.VARCHAR});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_nombre_punto_ifip", Types.VARCHAR});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_codigo_punto_ifip", Types.NUMERIC});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_nombre_ifip", Types.VARCHAR});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_codigo_ifip", Types.NUMERIC});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_codigo_acceso", Types.NUMERIC});
            llamaSp.getListParametrosSalida().add(new Object[]{"p_codigo_rol", Types.VARCHAR});
            llamaSp.getListParametrosSalida().add(new Object[]{"pv_fecha_acceso", Types.VARCHAR});

            llamaSp.invocaSP();

            //////System.out.println("datos "+llamaSp.getListParametrosSalida());
            if (llamaSp.isEjecucionCorrecta()) {
                for (int c = 0; c < llamaSp.getListResultado().size(); c++) {
                    this.getVecDatos().add((llamaSp.getListResultado().get(c) != null) ? llamaSp.getListResultado().get(c).toString() : null);
                }
            }
            // --  Fin de Llamar al SP
            // -----------------------------------------------------------------------

            // Colicando el estado de la ejecucion del SP
            this.setEjecucionCorrecta(llamaSp.isEjecucionCorrecta());

        } catch (Exception e) {
            // Ejecucion no satisfactoria
            this.setEjecucionCorrecta(false);

            // Muestra el mensaje
            MuestraMensaje.addErrorCapaNegocio();

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorEnCapaDeNegocio,
                    new Object[]{"SESION ,MKP", CapturaError.getErrorException(e)});

        }
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
     * @return the vecDatos
     */
    public Vector getVecDatos() {
        return vecDatos;
    }

    /**
     * @param vecDatos the vecDatos to set
     */
    public void setVecDatos(Vector vecDatos) {
        this.vecDatos = vecDatos;
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

}
