/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.consumo.ahorro;

import ec.mss.entidad.RespuestaServicio;
import ec.mss.web.consumo.ConsumoServicioRecurso;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.springframework.http.HttpStatus;

/**
 *
 * @author andres
 */
@Stateless(name = "ahorroProgramadoServicio")
public class AhorroProgramadoServicio {

    @EJB
    private ConsumoServicioRecurso consumoServicioRecurso;
    private final String nombreServicio = "ahorro-programado-servicio";
    private RespuestaServicio respuestaServicio;
    private HashMap<String, Object> parametros;

    /**
     *
     * @return
     */
    public RespuestaServicio getListaAhorroProgramadoPorIfip() {
        try {
            parametros = new HashMap<>();
            parametros.put("codigoIfip", ActivacionUsuario.getCodigoIfip());
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "listaAhorroProgramadoIfip", parametros);
            return respuestaServicio;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"AhorroServicio", "getListaAhorroProgramadoPorIfip", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
}
