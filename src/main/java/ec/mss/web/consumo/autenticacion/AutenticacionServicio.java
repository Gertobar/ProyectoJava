/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.consumo.autenticacion;

import ec.mss.entidad.RespuestaServicio;
import ec.mss.web.consumo.ConsumoServicioRecurso;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.springframework.http.HttpStatus;

/**
 *
 * @author andres
 */
@Stateless(name="autenticacionServicio")
public class AutenticacionServicio {
    
    @EJB
    private ConsumoServicioRecurso consumoServicioRecurso;
    private final String nombreServicio = "autenticacion-servicio";
    private RespuestaServicio respuestaServicio;
    
    /**
     * 
     * @return 
     */
    public RespuestaServicio autenticaAplicacion(){
        try{
           respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "oauth", null);
           return respuestaServicio;
        }catch(Exception ex){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"AutenticacionServicio", "autenticaAplicacion", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }        
    }
}
