/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.consumo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import ec.mss.entidad.RespuestaServicio;
import ec.mss.web.servicio.entidad.ServicioAplRecCatHea;
import ec.mss.web.servicio.entidad.ServicioAplicacion;
import ec.mss.web.servicio.entidad.ServicioAplicacionRecurso;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author andres
 */
@Stateless(name = "consumoServicioRecurso")
public class ConsumoServicioRecurso {

    private List<ServicioAplicacion> listaServicios;
    private ServicioAplicacion servicioAplicacion;
    private ServicioAplicacionRecurso servicioAplicacionRecurso;
    private HttpHeaders headers;
    private Gson gson;
    private JsonElement jsonElement;
    private JsonObject jsonObject;
    private String resultado;
    private MultiValueMap<String, String> parametersMap;
    private RestTemplate restTemplate;
    private HttpEntity<?> entity;
    private ResponseEntity<String> response;
    private HttpMethod metodo;
    private String url;
    private String[] division;
    private String parametroUrl;
    private String cadenaParametro;
    private String peticionJson;

    /**
     *
     * @param nombreServicio
     * @param nombreRecurso
     * @param parametros
     * @return
     */
    public RespuestaServicio consumoRecurso(String nombreServicio, String nombreRecurso, HashMap<String, Object> parametros) {
        listaServicios = null;
        servicioAplicacion = null;
        servicioAplicacionRecurso = null;
        parametersMap = null;
        try {
            listaServicios = ActivacionUsuario.getServicio();
            if (listaServicios == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ConsumoServicioRecurso", "consumoRecurso", "LISTADO DE SERVICOS"});
                return null;
            }
            servicioAplicacion = listaServicios.stream().filter(x -> (x.getNombre() == null ? nombreServicio == null : x.getNombre().equals(nombreServicio))).findFirst().get();
            if (servicioAplicacion == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ConsumoServicioRecurso", "consumoRecurso", " NO EXISTE SERVICIO " + nombreServicio});
                return null;
            }
            servicioAplicacionRecurso = servicioAplicacion.getServicioAplicacionRecursoCollection().stream().filter(x -> (x.getRecurso() == null ? nombreRecurso == null : x.getRecurso().equals(nombreRecurso))).findFirst().get();
            if (servicioAplicacionRecurso == null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ConsumoServicioRecurso", "consumoRecurso", " NO EXISTE SERVICIORECURSO " + nombreRecurso});
                return null;
            }
            url = "";
            headers = new HttpHeaders();
            gson = new Gson();
            restTemplate = new RestTemplate();
            if (!generaMetodoHttp(servicioAplicacionRecurso)) {
                return new RespuestaServicio(HttpStatus.SERVICE_UNAVAILABLE,
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MetodoHttpError"), jsonObject);
            }
            if (!generaUrl(servicioAplicacionRecurso, parametros)) {
                return new RespuestaServicio(HttpStatus.SERVICE_UNAVAILABLE,
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UrlError"), jsonObject);
            }
            if (!generaAutorizacion(servicioAplicacionRecurso)) {
                return new RespuestaServicio(HttpStatus.SERVICE_UNAVAILABLE,
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AutorizacionError"), jsonObject);
            }
            if (!generaHeaders(servicioAplicacionRecurso)) {
                return new RespuestaServicio(HttpStatus.SERVICE_UNAVAILABLE,
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("HeadersError"), jsonObject);
            }
            if (!generaBody(servicioAplicacionRecurso, parametros)) {
                return new RespuestaServicio(HttpStatus.SERVICE_UNAVAILABLE,
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("BodyError"), jsonObject);
            }
            if (parametersMap != null)
                entity = new HttpEntity<>(parametersMap, headers);
            else
                entity = new HttpEntity<>(peticionJson, headers);
            
            response = restTemplate.exchange(url, metodo, entity, String.class);
            resultado = response.getBody();
            if (response.getStatusCode() == HttpStatus.OK) {
                //System.out.println("RESPONSE  " + response.getBody());
                jsonElement = gson.fromJson(resultado, JsonElement.class);
                if (jsonElement instanceof JsonObject) {
                    //System.out.println("INSTANCIA 1" + jsonElement);
                    jsonObject = jsonElement.getAsJsonObject();
                }
                if (jsonElement instanceof JsonArray) {
                    //System.out.println("INSTANCIA 2" + jsonElement);
                    jsonObject.add("trama", jsonElement);
                }
                return new RespuestaServicio(response.getStatusCode(),
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProcesoSatisfactorio"), jsonObject);
            } else {
                if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
                    return new RespuestaServicio(response.getStatusCode(),
                            ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteContenido"), jsonObject);
                } else {
                    //System.out.println("AQUI 2" + response.getStatusCode());
                    String mensajeError = null;
                    jsonElement = gson.fromJson(resultado, JsonElement.class);
                    if (jsonElement instanceof JsonObject) {
                        jsonObject = jsonElement.getAsJsonObject();
                        mensajeError = jsonObject.get("message").toString();
                    }
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                            new Object[]{"ConsumoServicioRecurso", "consumoRecurso", resultado});
                    return new RespuestaServicio(response.getStatusCode(), mensajeError, jsonObject);
                }
            }
        } catch (HttpClientErrorException ex) {
            System.out.println("okokokokokok 1" + ex.getStatusCode());
            String mensajeError = null;
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                //    new Object[]{"ConsumoServicioRecurso", "consumoRecurso", ex.getResponseBodyAsString() != null ? ex.getResponseBodyAsString() : CapturaError.getErrorException(ex)});
                //return new RespuestaServicio(HttpStatus.NO_CONTENT, ex.getResponseBodyAsString(), null);
                jsonElement = gson.fromJson(ex.getResponseBodyAsString(), JsonElement.class);            
                if (jsonElement instanceof JsonObject) {
                    jsonObject = jsonElement.getAsJsonObject();
                    mensajeError = jsonObject.get("message").toString();
                }                               
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ConsumoServicioRecurso", "consumoRecurso", mensajeError != null ? mensajeError : CapturaError.getErrorException(ex)});
                return new RespuestaServicio(HttpStatus.NO_CONTENT, mensajeError != null ? mensajeError : CapturaError.getErrorException(ex), null);
            }else{
                jsonElement = gson.fromJson(ex.getResponseBodyAsString(), JsonElement.class);            
                if (jsonElement instanceof JsonObject) {
                    jsonObject = jsonElement.getAsJsonObject();
                    mensajeError = jsonObject.get("message").toString();
                }                               
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"ConsumoServicioRecurso", "consumoRecurso", mensajeError != null ? mensajeError : CapturaError.getErrorException(ex)});
                return new RespuestaServicio(HttpStatus.SEE_OTHER, mensajeError != null ? mensajeError : CapturaError.getErrorException(ex), null);
            }
        }catch (HttpServerErrorException ex){
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConsumoServicioRecurso", "consumoRecurso", ex.getResponseBodyAsString() != null ? ex.getResponseBodyAsString() : CapturaError.getErrorException(ex)});
                return new RespuestaServicio(HttpStatus.NO_CONTENT, ex.getResponseBodyAsString(), null);
            }else{
                return new RespuestaServicio(HttpStatus.CONFLICT, ex.getMessage(), null);
            }
        }
    }

    /**
     *
     * @param recurso
     * @return
     */
    public boolean generaMetodoHttp(ServicioAplicacionRecurso recurso) {
        try {
            switch (recurso.getRecursoHttp()) {
                case "POST":
                    metodo = HttpMethod.POST;
                    break;
                case "GET":
                    metodo = HttpMethod.GET;
                    break;
                case "PUT":
                    metodo = HttpMethod.PUT;
                    break;
            }
            return true;
        } catch (JsonSyntaxException | RestClientException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConsumoServicioRecurso", "generaMetodo", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param recurso
     * @param parametros
     * @return
     */
    public boolean generaUrl(ServicioAplicacionRecurso recurso, HashMap<String, Object> parametros) {
        try {
            cadenaParametro = generaParametroUrl(recurso.getRecursoUri(), parametros);
            if ("".equals(cadenaParametro)) {
                return false;
            }
            url = "http://"
                    + recurso.getCodigoServicioAplicacion().getIp() + ":"
                    + recurso.getCodigoServicioAplicacion().getPuerto() + "/"
                    + recurso.getCodigoServicioAplicacion().getNombre()
                    + cadenaParametro;
            //System.out.println("URLLLLLLLLLLLLLLLLLLLLLLLLLL GENERADAAAAAAAAAAAAAAAAAAAA " + url);
            return true;
        } catch (JsonSyntaxException | RestClientException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConsumoServicioRecurso", "generaUrl", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param recursoUri
     * @param parametros
     * @return
     */
    public String generaParametroUrl(String recursoUri, HashMap<String, Object> parametros) {
        try {
            division = recursoUri.split("&");
            if (division.length <= 1) {
                return recursoUri;
            } else {
                if (metodo == HttpMethod.GET) {
                    parametroUrl = "";
                    for (int i = 1; i < division.length; i++) {
                        if (i == 1){
                            parametroUrl += "?";
                        }
                        else{
                            parametroUrl += "&";
                        }
                        try {
                            Object valor = parametros.get(division[i]);
                            parametroUrl += division[i] + "=" + String.valueOf(valor);
                        } catch (Exception ex) {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                    new Object[]{"ConsumoServicioRecurso", "generaParametroUrl", CapturaError.getErrorException(ex) + " PARAMETRO NO EQUIVALENTE"});
                            return "";
                        }
                    }
                    parametroUrl = division[0] + parametroUrl;
                    return parametroUrl;
                } else {
                    return recursoUri;
                }
            }
        } catch (JsonSyntaxException | RestClientException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConsumoServicioRecurso", "generaParametroUrl", CapturaError.getErrorException(ex)});
            return "";
        }
    }

    /**
     *
     * @param recurso
     * @return
     */
    public boolean generaAutorizacion(ServicioAplicacionRecurso recurso) {
        try {
            recurso.getServicioAplRecCatAutSet().forEach((item) -> {
                switch (item.getCatalogoAutorizacion().getNombre()) {
                    case "BASIC":
                        String auth = "minkasoft" + ":" + "minkasoft";
                        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                        String authHeader = "Basic " + new String(encodedAuth);
                        headers.set("Authorization", authHeader);
                        if (recurso.getRecursoBody().equals("X-WWW-FORM-URLENCODED")) {
                            headers.set("Content-Type", "application/x-www-form-urlencoded");
                            parametersMap = new LinkedMultiValueMap<>();
                            parametersMap.add("username", "MSS");
                            parametersMap.add("password", "password");
                            parametersMap.add("grant_type", "password");
                        }
                        break;
                    case "NOAUTH":
                        break;
                    case "OAUTH2.0":
                        break;
                }
            });
            return true;
        } catch (JsonSyntaxException | RestClientException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConsumoServicioRecurso", "generaAutorizacion", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param recurso
     * @return
     */
    public boolean generaHeaders(ServicioAplicacionRecurso recurso) {
        try {
            recurso.getServicioAplRecCatHeaSet().forEach((ServicioAplRecCatHea item) -> {
                switch (item.getCatalogoHeader().getNombre()) {
                    case "Authorization":
                        if (recurso.getRecursoTipoToken() != null) {
                            if (recurso.getRecursoTipoToken().equals("Bearer")) {
                                headers.set("Authorization", "Bearer " + ActivacionUsuario.getTokenServicio());
                            }
                        } else {
                            headers.set("Authorization", ActivacionUsuario.getTokenServicio());
                        }
                        break;
                    case "Content-Type":
                        if (recurso.getRecursoBody().equals("APPLICATION/JSON")) {
                            headers.set("Content-Type", "application/json");
                        }
                        if (recurso.getRecursoBody().equals("X-WWW-FORM-URLENCODED")) {
                            headers.set("Content-Type", "application/x-www-form-urlencoded");
                        }
                        break;
                }
            });
            return true;
        } catch (JsonSyntaxException | RestClientException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConsumoServicioRecurso", "generaHeaders", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param recurso
     * @param parametros
     * @return
     */
    public boolean generaBody(ServicioAplicacionRecurso recurso, HashMap<String, Object> parametros) {
        try {
            if ((metodo != HttpMethod.GET) && (recurso.getRecursoBody().equals("APPLICATION/JSON"))) {
                String[] recursoBodyTrama = recurso.getRecursoBodyTrama().split("&");
                if (recursoBodyTrama.length <= 1) {
                    return true;
                }
                peticionJson = "{";
                for (int i = 1; i < recursoBodyTrama.length; i++) {
                    try {                
                        Object valor = parametros.get(recursoBodyTrama[i]);
                        if (!(valor instanceof JsonObject)){
                            if (!(valor instanceof String[])){
                                peticionJson += "\"" + recursoBodyTrama[i] + "\"" + ":" + "\"" + valor.toString() + "\",";
                            }else{
                                String valorString = "[";
                                for(String item : (String[])valor){
                                    valorString += "\"" + item + "\"" + ",";
                                }
                               peticionJson += "\"" + recursoBodyTrama[i] + "\"" + ":" + valorString.substring(0, valorString.length() -1 ) + "]" + ",";
                            }
                        }else{
                            peticionJson += "\"" + recursoBodyTrama[i] + "\"" + ":" + valor + ",";
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                new Object[]{"ConsumoServicioRecurso", "generaBody", CapturaError.getErrorException(ex) + " PARAMETRO NO EQUIVALENTE"});
                        return false;
                    }
                }                
                peticionJson = peticionJson.substring(0, peticionJson.length() -1 );
                peticionJson += "}";
            }
            return true;
        } catch (JsonSyntaxException | RestClientException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ConsumoServicioRecurso", "generaBody", CapturaError.getErrorException(ex)});
            return false;
        }
    }
}