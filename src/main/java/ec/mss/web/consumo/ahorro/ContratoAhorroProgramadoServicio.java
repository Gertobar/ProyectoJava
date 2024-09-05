/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.consumo.ahorro;

import ec.mss.entidad.RespuestaServicio;
import ec.mss.entidad.ahorro.programado.ContratoAhorroProgramado;
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
@Stateless(name = "contratoAhorroProgramadoServicio")
public class ContratoAhorroProgramadoServicio {

    @EJB
    private ConsumoServicioRecurso consumoServicioRecurso;
    private final String nombreServicio = "ahorro-programado-servicio";
    private RespuestaServicio respuestaServicio;   
    private HashMap<String, Object> parametros;
    /**
     *
     * @param codigoPersona
     * @return
     */
    public RespuestaServicio getListaContratoAhorroProgramadoPorPersona(Long codigoPersona) {
        try {
            parametros = new HashMap<>();
            parametros.put("codigoPersona", codigoPersona);
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "listaContratoAhorroProgramadoPersona", parametros);
            return respuestaServicio;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoServicio", "getListaContratoAhorroProgramadoPorPersona", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }

    /**
     * 
     * @param codigoAhorroProgramado
     * @param monto
     * @param codigoPeriodicidad
     * @param numeroCuotas
     * @param diaPago
     * @return 
     */
    public RespuestaServicio calculaTablaNuevoContratoAhorro(Long codigoAhorroProgramado,
            BigDecimal monto,
            Long codigoPeriodicidad,
            Short numeroCuotas,
            Long diaPago
    ) {
        try {
            parametros = new HashMap<>();            
            parametros.put("codigoAhorroProgramado", codigoAhorroProgramado);
            parametros.put("monto", monto);
            parametros.put("codigoPeriodicidad", codigoPeriodicidad);
            parametros.put("numeroCuotas", numeroCuotas);
            parametros.put("diaPago", diaPago);            
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "calculaTablaNuevoContratoAhorro", parametros);
            return respuestaServicio;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoServicio", "calculaTablaNuevoContratoAhorro", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
    
    
    
    /**
     * 
     * @param contratoAhorroProgramado
     * @return 
     */
    public RespuestaServicio addContratoAhorroProgramado(ContratoAhorroProgramado contratoAhorroProgramado){
        try{
            parametros = new HashMap<>();
            parametros.put("codigo", "");
            parametros.put("codigoIfip", contratoAhorroProgramado.getCodigoIfip());
            parametros.put("codigoIfipAgencia", contratoAhorroProgramado.getCodigoIfipAgencia());
            parametros.put("codigoPeriodicidad", contratoAhorroProgramado.getCodigoPeriodicidad());
            parametros.put("codigoPersona", contratoAhorroProgramado.getCodigoPersona());
            parametros.put("codigoPersonaBeneficiario", contratoAhorroProgramado.getCodigoPersonaBeneficiario());
            parametros.put("codigoCuentaDebita", contratoAhorroProgramado.getCodigoCuentaDebita());
            parametros.put("codigoCuentaAcredita", contratoAhorroProgramado.getCodigoCuentaAcredita());
            parametros.put("codigoCuentaAhorroPro", contratoAhorroProgramado.getCodigoCuentaAhorroPro());
            parametros.put("codigoAhorroProgramado", contratoAhorroProgramado.getCodigoAhorroProgramado());
            parametros.put("monto", contratoAhorroProgramado.getMonto());
            parametros.put("tasaInteres", "");
            parametros.put("fechaCaptacion", "");
            parametros.put("fechaVencimiento", "");
            parametros.put("estado", contratoAhorroProgramado.getEstado());
            parametros.put("numeroCuotas", contratoAhorroProgramado.getNumeroCuotas());
            parametros.put("diaPago", contratoAhorroProgramado.getDiaPago());
            parametros.put("interes", "");
            parametros.put("codigoMotivoAhorroPro", contratoAhorroProgramado.getCodigoMotivoAhorroPro());
            parametros.put("renovacionAutomatica", contratoAhorroProgramado.getRenovacionAutomatica());
            parametros.put("codigoUsuario", contratoAhorroProgramado.getCodigoUsuario());
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "addContratoAhorroProgramado", parametros);
            return respuestaServicio;
        }catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoServicio", "calculaTablaNuevoContratoAhorro", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }
    
    /**
     * 
     * @param codigoContratoAhorroProgramado
     * @return 
     */
    public RespuestaServicio calculaPenalizacionContratoAhorro(Long codigoContratoAhorroProgramado){
        try{
            parametros = new HashMap<>();
            parametros.put("codigoContratoAhorroProgramado", codigoContratoAhorroProgramado);           
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "calculaPenalizacionContratoAhorro", parametros);
            return respuestaServicio;
        }catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoServicio", "calculaPenalizacionContratoAhorro", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }

    /**
     * 
     * @param codigoContratoAhorroProgramado
     * @param codigoMotivoPrecancelacion
     * @param codigoUsuario
     * @param codigoComputador
     * @param direccionIp
     * @return 
     */
     public RespuestaServicio precancelacionContratoAhorro( Long codigoContratoAhorroProgramado,
                                                            Short codigoMotivoPrecancelacion,
                                                            Long codigoUsuario,
                                                            Long codigoComputador,
                                                            String direccionIp){
        try {
            parametros = new HashMap<>();            
            parametros.put("codigoContratoAhorroProgramado", codigoContratoAhorroProgramado);
            parametros.put("codigoMotivoPrecancelacion", codigoMotivoPrecancelacion);
            parametros.put("codigoUsuario", codigoUsuario);
            parametros.put("codigoComputador", codigoComputador);
            parametros.put("direccionIp", direccionIp);            
            respuestaServicio = consumoServicioRecurso.consumoRecurso(nombreServicio, "precancelacionContratoAhorro", parametros);
            return respuestaServicio;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ContratoAhorroProgramadoServicio", "precancelacionContratoAhorro", CapturaError.getErrorException(ex)});
            return new RespuestaServicio(HttpStatus.SEE_OTHER, CapturaError.getErrorException(ex), null);
        }
    }

}
