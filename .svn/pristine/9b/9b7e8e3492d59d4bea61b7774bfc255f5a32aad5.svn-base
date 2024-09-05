/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.entidad;

import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;

/**
 *
 * @author andres
 */
public class RespuestaServicio {
    
    private HttpStatus codigo;
    private String mensaje;
    private JsonObject trama;

    public RespuestaServicio() {
    }

    public RespuestaServicio(HttpStatus codigo, String mensaje, JsonObject trama) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.trama = trama;
    }

    @Override
    public String toString() {
        return "RespuestaServicio{" + "codigo=" + codigo + ", mensaje=" + mensaje + ", trama=" + trama + '}';
    }
    
    /**
     * @return the codigo
     */
    public HttpStatus getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(HttpStatus codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the trama
     */
    public JsonObject getTrama() {
        return trama;
    }

    /**
     * @param trama the trama to set
     */
    public void setTrama(JsonObject trama) {
        this.trama = trama;
    }
}
