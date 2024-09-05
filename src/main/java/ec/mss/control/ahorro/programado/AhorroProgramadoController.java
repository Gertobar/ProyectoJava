/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.control.ahorro.programado;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import ec.mss.entidad.RespuestaServicio;
import ec.mss.entidad.ahorro.programado.AhorroProgramado;
import ec.mss.web.consumo.ahorro.AhorroProgramadoServicio;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import org.springframework.http.HttpStatus;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author andres
 */
@ViewScoped
@Named("ahorroProgramadoController")
public class AhorroProgramadoController implements Serializable {
    
    @EJB
    private AhorroProgramadoServicio ahorroProgramadoServicio;
    
    private AhorroProgramado ahorroProgramado;
    private List<AhorroProgramado> listaAhorroProgramado;
    private RespuestaServicio respuestaServico;
    private JsonElement jsonElement;
    private Type type;
        
    @PostConstruct
    public void init() {
        try{
            respuestaServico = ahorroProgramadoServicio.getListaAhorroProgramadoPorIfip();
            if (respuestaServico.getCodigo() != HttpStatus.OK) {
                MuestraMensaje.addError(respuestaServico.getMensaje());
            }else{
                jsonElement = respuestaServico.getTrama().get("trama");
                type = new TypeToken<List<AhorroProgramado>>() {}.getType();
                listaAhorroProgramado = new Gson().fromJson(jsonElement, type);
            }            
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"AhorroProgramadoController", "init", CapturaError.getErrorException(ex)});
        }
    }    
    
    /**
     * 
     * @param actionEvent 
     */
    public void guarda(ActionEvent actionEvent) {
    
    }
    
    /**
     * 
     * @param actionEvent 
     */
    public void muestra(ActionEvent actionEvent){
    
    }

    /**
     * @return the ahorroProgramado
     */
    public AhorroProgramado getAhorroProgramado() {
        return ahorroProgramado;
    }

    /**
     * @param ahorroProgramado the ahorroProgramado to set
     */
    public void setAhorroProgramado(AhorroProgramado ahorroProgramado) {
        this.ahorroProgramado = ahorroProgramado;
    }

    /**
     * @return the listaAhorroProgramado
     */
    public List<AhorroProgramado> getListaAhorroProgramado() {
        return listaAhorroProgramado;
    }

    /**
     * @param listaAhorroProgramado the listaAhorroProgramado to set
     */
    public void setListaAhorroProgramado(List<AhorroProgramado> listaAhorroProgramado) {
        this.listaAhorroProgramado = listaAhorroProgramado;
    }
    
}
