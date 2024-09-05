/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.consumo.dispensador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ec.mss.entidad.RespuestaServicio;
import ec.mss.modelo.dispensador.proceso.ContratoCdmRq;
import ec.mss.modelo.dispensador.proceso.ContratoMonederoRq;
import ec.mss.web.consumo.ConsumoServicioRecurso;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import java.math.BigDecimal;
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
@Stateless(name = "dispensadorServicio")
public class DispensadorServicio {

    @EJB
    private ConsumoServicioRecurso consumoServicioRecurso;
    private final String nombreServicio = "transaccion-cdm-servicio";
    private RespuestaServicio respuestaServicio;
    private HashMap<String, Object> parametros;
    /**
     *
     * @param codigoPersona
     * @return
     */
    public RespuestaServicio obtieneContratoCdmPersona(Long codigoPersona) {
        try {
            parametros = new HashMap<>();
            parametros.put("codigoPersona", codigoPersona);
            parametros.put("codigoContrato", "");
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "obtieneContratoCdmPersona", parametros);
            return respuestaServicio;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"DispensadorServicio", "obtieneContratoCdmPersona", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
    
    private Gson gson;
    private String jsonString;
    private JsonParser jsonParser;
    private JsonObject jsonObject;
    private ContratoMonederoRq contratoMonederoRq;
    /**
     *
     * @param parametro
     * @return
     */
    public RespuestaServicio guardaNuevoContrato(ContratoCdmRq parametro) {
        try {
            parametros = new HashMap<>();
            gson = new Gson();
            jsonString = "";
            parametros.put("codigoCanalSevicio", parametro.getCodigoCanalSevicio() == null ? "" : parametro.getCodigoCanalSevicio());
            parametros.put("codigoPersona", parametro.getCodigoPersona());
            parametros.put("codigoIfipAgencia", parametro.getCodigoIfipAgencia());
            parametros.put("codigoUsuario", parametro.getCodigoUsuario());
            parametros.put("estado", parametro.getEstado());
            parametros.put("celular", parametro.getCelular());
            parametros.put("correoElectronico", parametro.getCorreoElectronico());
            contratoMonederoRq = new ContratoMonederoRq();
            contratoMonederoRq.setCodigoCuenta(parametro.getContratoMonederoRq().getCodigoCuenta());
            contratoMonederoRq.setIdentificadorVinculo(parametro.getContratoMonederoRq().getIdentificadorVinculo() == null ? "" : parametro.getContratoMonederoRq().getIdentificadorVinculo());
            contratoMonederoRq.setSaldo(parametro.getContratoMonederoRq().getSaldo() == null ? new BigDecimal("0") : parametro.getContratoMonederoRq().getSaldo());
            contratoMonederoRq.setSaldoMinimo(parametro.getContratoMonederoRq().getSaldoMinimo() == null ? new BigDecimal("0") : parametro.getContratoMonederoRq().getSaldoMinimo());
            contratoMonederoRq.setMontoMaximoDispensar(parametro.getContratoMonederoRq().getMontoMaximoDispensar());
            contratoMonederoRq.setMontoMaximoDispensarMen(parametro.getContratoMonederoRq().getMontoMaximoDispensarMen());
            contratoMonederoRq.setUltimaIdPeticion(parametro.getContratoMonederoRq().getUltimaIdPeticion() == null ? "" : parametro.getContratoMonederoRq().getUltimaIdPeticion());
            contratoMonederoRq.setIdentificadorPropietario(parametro.getContratoMonederoRq().getIdentificadorPropietario() == null ? "" : parametro.getContratoMonederoRq().getIdentificadorPropietario());
            jsonString = gson.toJson(contratoMonederoRq);
            jsonParser = new JsonParser();
            jsonObject = new JsonObject();
            jsonObject = jsonParser.parse(jsonString).getAsJsonObject();
            parametros.put("contratoMonederoRq", jsonObject);
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "registraContratoCdm", parametros);
            return respuestaServicio;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"DispensadorServicio", "guardaNuevoContrato", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
    
    /**
     * 
     * @param codigoContrato
     * @param montoMaximoDispensar
     * @param montoMaximoDispensarMensual
     * @return 
     */
    public RespuestaServicio guardaEdicionContrato(Long codigoContrato, BigDecimal montoMaximoDispensar, BigDecimal montoMaximoDispensarMensual){
        try{
            parametros = new HashMap<>();
            parametros.put("codigoContrato", codigoContrato);
            parametros.put("montoMaximoDispensar", montoMaximoDispensar);
            parametros.put("montoMaximoDispensarMensual", montoMaximoDispensarMensual);
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "editaContratoCdm", parametros);
            return respuestaServicio;
        }catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"DispensadorServicio", "guardaEdicionContrato", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
    
    /**
     * 
     * @param codigoUsuario
     * @param codigoContrato
     * @param estado
     * @param motivo
     * @return 
     */
    public RespuestaServicio actualizaEstadoContratoCdm(Long codigoUsuario, Long codigoContrato, String estado, String motivo){
        try{
            parametros = new HashMap<>();
            parametros.put("codigoUsuario", codigoUsuario);
            parametros.put("codigoContratoCdm", codigoContrato);
            parametros.put("estado", estado);
            parametros.put("motivo", motivo);
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "actualizaEstadoContratoCdm", parametros);
            return respuestaServicio;
        }catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"DispensadorServicio", "cambiaEstadoContrato", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
    
    /**
     * 
     * @param codigoMonedero
     * @param token
     * @return 
     */
    public RespuestaServicio guardaContratoVinculacion(Long codigoMonedero, String token){
        try{
            parametros = new HashMap<>();
            parametros.put("codigoMonedero", codigoMonedero);
            parametros.put("token", token);
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "registraVinculoServicio", parametros);
            return respuestaServicio;
        }catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"DispensadorServicio", "guardaContratoVinculacion", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
}
