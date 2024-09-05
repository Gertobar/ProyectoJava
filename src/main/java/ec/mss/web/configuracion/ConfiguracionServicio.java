/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.configuracion;

/*import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;*/
import ec.mss.web.servicio.entidad.CatalogoAutorizacion;
import ec.mss.web.servicio.entidad.CatalogoHeader;
import ec.mss.web.servicio.entidad.ServicioAplicacion;
import ec.mss.web.servicio.repositorio.CatalogoAutorizacionFacade;
import ec.mss.web.servicio.repositorio.CatalogoHeaderFacade;
//import ec.mss.web.servicio.entidad.ServicioAplicacionRecurso;
import ec.mss.web.servicio.repositorio.ServicioAplicacionFacade;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author andres
 */
@Stateless(name = "configuracionServicio")
public class ConfiguracionServicio {

    @EJB
    private ServicioAplicacionFacade ejbServicioAplicacion;
    @EJB
    private CatalogoHeaderFacade ejbCatalogoHeader;
    @EJB
    private CatalogoAutorizacionFacade ejbCatalogoAutorizacion;
    
    //private List<ServicioAplicacion> listaRecursos;
    //private JsonObject listaServicios;
    
    /**
     * 
     * @return 
     */
    public List<ServicioAplicacion> generaServiciosAplicacion() {
        try {
            return ejbServicioAplicacion.getPorEstado(Short.valueOf("1"));            
            /*listaRecursos.forEach((final ServicioAplicacionRecurso servicioAplicacionRecurso)
                    -> {
                System.out.println(servicioAplicacionRecurso.getCodigoServicioAplicacion().getIp());
                
            });*/
            //listaServicios = new JsonObject();           
            //JsonArray jsonPropertyPredicates = new JsonArray();
            
            /*JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("" );
            jsonObject.addProperty("value", str);*/
            /*
            listaRecursos.stream()
                .sorted((p1, p2) -> p1.getNombre().compareTo(p2.getNombre()))
                .forEach((final ServicioAplicacion servicioAplicacion)
                    -> {
                    JsonArray jsonArrayRecursos = new JsonArray();
                    for (ServicioAplicacionRecurso itemServicioAplicacionRecurso : servicioAplicacion.getServicioAplicacionRecursoCollection() ){                       
                        JsonObject innerObject = new JsonObject();
                        innerObject.addProperty("nombre",names[i]);
                        innerObject.addProperty("uri",names[i]);
                        innerObject.addProperty("protocolo", names[i]);
                        
                    }
                    
                    System.out.println("VALOR " + servicioAplicacion.getNombre());
                });   */         
        } catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConfiguracionServicio", "generaServiciosAplicacion", CapturaError.getErrorException(ex)});
            return null;
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<CatalogoHeader> generaCatalogoHeaders(){
        try{
            return ejbCatalogoHeader.getPorCodigoIfip(ActivacionUsuario.getCodigoIfip());
        }catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConfiguracionServicio", "generaCatalogoHeaders", CapturaError.getErrorException(ex)});
            return null;
        }
    }
    
     /**
     * 
     * @return 
     */
    public List<CatalogoAutorizacion> generaCatalogoAutorizacion(){
        try{
            return ejbCatalogoAutorizacion.getPorCodigoIfip(ActivacionUsuario.getCodigoIfip());
        }catch (NumberFormatException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConfiguracionServicio", "generaCatalogoAutorizacion", CapturaError.getErrorException(ex)});
            return null;
        }
    }
}
