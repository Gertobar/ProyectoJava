/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.clases.personas;

/**
 *
 * @author willan
 */
import java.util.Date;

/**
 *Clase que permite mapear una busqueda de personas en las listas UAF como objeto
 * 
 * @author willan
 */
public class PersonaListaNegra {

    private String identificacion;
    private String nombres;
    private Date fecha;
    public static final int TIPO_HOMONIMO = 1;
    public static final int TIPO_SENTENCIADO = 2;
    public static final int TIPO_POLITICAMENTE_EXPUESTOS = 3;
    private int tipoPersona;

    /**
     *
     * @return devuelve la identificación
     */
    public String getIdentificacion() {
        return this.identificacion;
    }

    /**
     *
     * @param identificacion
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     *
     * @return devuelve los nombres
     */
    public String getNombres() {
        return this.nombres;
    }

    /**
     *
     * @param nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     *
     * @return devuelve la fecha
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * @return devuelve el tipo de persona
     */
    public int getTipoPersona() {
        return this.tipoPersona;
    }

    /**
     *
     * @param tipoPersona
     */
    public void setTipoPersona(int tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
