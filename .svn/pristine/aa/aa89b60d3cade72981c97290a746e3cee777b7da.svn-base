/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.sesion;

import ec.mss.entidad.Catalogo;
import ec.mss.web.servicio.entidad.CatalogoAutorizacion;
import ec.mss.web.servicio.entidad.CatalogoHeader;
import ec.mss.web.servicio.entidad.ServicioAplicacion;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.util.List;

/**
 *
 * @author vicastoc
 */
public class ActivacionUsuario {

    // -------------------------------------------------------------------------
    // PROPIEDAD SET
    public static void setCodigoOpcion(Long codigoOpcion) {
        Sesion.setVariable("codigoOpcion", codigoOpcion);
    }

    public static void setCodigoRol(String codigoRol) {
        Sesion.setVariable("codigoRol", codigoRol);
    }

    public static void setCodigoIfip(Long codigoIfip) {
        Sesion.setVariable("codigoIfip", codigoIfip);
    }

    public static void setControlador(String controlador) {
        Sesion.setVariable("controlador", controlador);
    }

    public static void setCodigoUsuario(Long codigoUsuario) {
        Sesion.setVariable("codigoUsuario", codigoUsuario);
    }

    public static void setUsuario(Usuario usuario) {
        Sesion.setVariable("usuario", usuario);
    }

    public static void setCodigoAccesoSistema(Long codigoAccesoSistema) {
        Sesion.setVariable("codigoAccesoSistemaa", codigoAccesoSistema);
    }

    public static void setCodigoIfipAgencia(Long codigoIfipAgencia) {
        Sesion.setVariable("codigoIfipAgencia", codigoIfipAgencia);
    }

    public static void setSinPermisosOpcion(String sinPermisosOpcion) {
        Sesion.setVariable("sinPermisosOpcion", sinPermisosOpcion);
    }

    public static void setCodigoComputador(Long codigoComputador) {
        Sesion.setVariable("codigoComputador", codigoComputador);
    }

    public static void setCambiarContrasena(boolean cambiarContrasena) {
        Sesion.setVariable("cambiarContrasena", cambiarContrasena);
    }

    public static void setCodigoPeriodo(String codigoPeriodo) {
        Sesion.setVariable("codigoPeriodo", codigoPeriodo);
    }

    public static void setCodigoTipoSistemaOperativo(Long codigoTipoSistemaOperativo) {
        Sesion.setVariable("codigoTipoSistemaOperativo", codigoTipoSistemaOperativo);
    }

    public static void setRutaImpresora(String rutaImpresora) {
        Sesion.setVariable("rutaImpresora", rutaImpresora);
    }

    public static void setTipoSOServidorAppl(String tipoSOServidorAppl) {
        Sesion.setVariable("tipoSOServidorAppl", tipoSOServidorAppl);
    }

    public static void setRutaImpresionArchivos(String rutaImpresionArchivos) {
        Sesion.setVariable("rutaImpresionArchivos", rutaImpresionArchivos);
    }

    public static void setRutaSubidaArchivos(String rutaSubidaArchivos) {
        Sesion.setVariable("rutaSubidaArchivos", rutaSubidaArchivos);
    }
    
     public static void setTipoSOServidor(String tipoSOServidor) {
        Sesion.setVariable("tipoSOServidor", tipoSOServidor);
    }
     
     public static void setCodigoFormularioLicitudFondos(Long codigoFormulario) {
        Sesion.setVariable("codigoFormulario", codigoFormulario);
    }

     public static void setListaCamposActualzar(List<String> listaComponentesActualizar) {
        Sesion.setVariable("listaComponentesActualizar", listaComponentesActualizar);
    }
     
      public static void setDialagoAMostrar(String dialogoAMostrar) {
        Sesion.setVariable("dialogoAMostrar", dialogoAMostrar);
    }
      
    public static void setRutaArchivoConfiguracionServidor(String rutaArchivoConfiguracionServidor) {
        Sesion.setVariable("rutaArchivoConfiguracionServidor", rutaArchivoConfiguracionServidor);
    }
    
    public static void setServicio(List<ServicioAplicacion> listaServicioAplicacion) {
        Sesion.setVariable("servicio", listaServicioAplicacion);
    }
    
    public static void setCatalogoAutorizacion(List<CatalogoAutorizacion> listaCatalogoAutorizacion) {
        Sesion.setVariable("catalogoAutorizacion", listaCatalogoAutorizacion);
    }
    
    public static void setCatalogoHeader(List<CatalogoHeader> listaCatalogoHeader) {
        Sesion.setVariable("catalogoHeader", listaCatalogoHeader);
    } 
    
    public static void setCatalogo(List<Catalogo> listaCatalogo) {
        Sesion.setVariable("catalogo", listaCatalogo);
    } 
    
    public static void setTokenServicio(String token) {
        Sesion.setVariable("token", token);
    }
    
    public static void setTokenRefreshServicio(String tokenRefresh) {
        Sesion.setVariable("tokenRefresh", tokenRefresh);
    }
    
    public static void setTokenTipo(String tokenTipo) {
        Sesion.setVariable("tokenTipo", tokenTipo);
    }

    // -------------------------------------------------------------------------
    // PROPIEDAD GET
    public static Usuario getUsuario() {
        return (Usuario) Sesion.getVariable("usuario");
    }

    /**
     * @return the codigoUsuario
     */
    public static Long getCodigoUsuario() {
        return (Sesion.getVariable("codigoUsuario") != null) ? Long.parseLong(Sesion.getVariable("codigoUsuario").toString()) : null;
    }

    public static Long getCodigoIfip() {
        return (Sesion.getVariable("codigoIfip") != null) ? Long.parseLong(Sesion.getVariable("codigoIfip").toString()) : null;
    }

    public static Long getCodigoOpcion() {
        return (Sesion.getVariable("codigoOpcion") != null) ? Long.parseLong(Sesion.getVariable("codigoOpcion").toString()) : null;
    }

    public static String getCodigoRol() {

        return (Sesion.getVariable("codigoRol") != null) ? Sesion.getVariable("codigoRol").toString() : null;
    }

    public static String getControlador() {
        return (Sesion.getVariable("controlador") != null) ? Sesion.getVariable("controlador").toString() : null;
    }

    public static Long getCodigoAccesoSistema() {
        return (Sesion.getVariable("codigoAccesoSistemaa") != null) ? Long.parseLong(Sesion.getVariable("codigoAccesoSistemaa").toString()) : null;
    }

    public static Long getCodigoIfipAgencia() {
        return (Sesion.getVariable("codigoIfipAgencia") != null) ? Long.parseLong(Sesion.getVariable("codigoIfipAgencia").toString()) : null;
    }

    public static String getSinPermisosOpcion() {
        return (Sesion.getVariable("sinPermisosOpcion") != null) ? Sesion.getVariable("sinPermisosOpcion").toString() : null;
    }

    public static Long getCodigoComputador() {
        return (Sesion.getVariable("codigoComputador") != null) ? Long.parseLong(Sesion.getVariable("codigoComputador").toString()) : null;
    }

    public static boolean isCambiarContrasena() {
        return (Sesion.getVariable("cambiarContrasena") != null) ? Boolean.parseBoolean(Sesion.getVariable("cambiarContrasena").toString()) : false;
    }

    public static String getCodigoPeriodo() {
        return (Sesion.getVariable("codigoPeriodo") != null) ? Sesion.getVariable("codigoPeriodo").toString() : null;
    }

    public static String getRutaImpresora() {
        return (Sesion.getVariable("rutaImpresora") != null) ? Sesion.getVariable("rutaImpresora").toString() : null;
    }

    public static Long getCodigoTipoSistemaOperativo() {
        return (Sesion.getVariable("codigoTipoSistemaOperativo") != null) ? Long.parseLong(Sesion.getVariable("codigoTipoSistemaOperativo").toString()) : null;
    }

    public static String getTipoSOServidorAppl() {
        return (Sesion.getVariable("tipoSOServidorAppl") != null) ? Sesion.getVariable("tipoSOServidorAppl").toString() : null;
    }

    public static String getRutaImpresionArchivos() {
        return (Sesion.getVariable("rutaImpresionArchivos") != null) ? Sesion.getVariable("rutaImpresionArchivos").toString() : null;
    }

    public static String getRutaSubidaArchivos() {
        return (Sesion.getVariable("rutaSubidaArchivos") != null) ? Sesion.getVariable("rutaSubidaArchivos").toString() : null;
    }
    
     public static String getTipoSOServidor() {
        return (Sesion.getVariable("tipoSOServidor") != null) ? Sesion.getVariable("tipoSOServidor").toString() : null;
    }     
     
     public static Long getCodigoFormularioLicitudFondos() {
        return (Sesion.getVariable("codigoFormulario") != null) ? Long.parseLong(Sesion.getVariable("codigoFormulario").toString()) : null;
    }
     
     public static List<String> getListaCamposActualzar() {
        return (Sesion.getVariable("listaComponentesActualizar") != null) ? (List<String>) Sesion.getVariable("listaComponentesActualizar") : null;
     
    }
     
     public static String getDialagoAMostrar() {
        return (Sesion.getVariable("dialogoAMostrar") != null) ? Sesion.getVariable("dialogoAMostrar").toString() : null;
    }
    
    public static String getRutaArchivoConfiguracionServidor() {
        return (Sesion.getVariable("rutaArchivoConfiguracionServidor") != null) ? Sesion.getVariable("rutaArchivoConfiguracionServidor").toString() : null;
    }
     
    public static List<ServicioAplicacion> getServicio() {
        return (Sesion.getVariable("servicio") != null) ? (List<ServicioAplicacion>) Sesion.getVariable("servicio") : null;
    }
        
    public static List<CatalogoAutorizacion> getCatalogoAutorizacion() {
        return (Sesion.getVariable("catalogoAutorizacion") != null) ? (List<CatalogoAutorizacion>) Sesion.getVariable("catalogoAutorizacion") : null;
    }
    
    public static List<CatalogoHeader>  getCatalogoHeader() {
        return (Sesion.getVariable("catalogoHeader") != null) ? (List<CatalogoHeader>) Sesion.getVariable("catalogoHeader") : null;
    } 
    
    public static List<Catalogo>  getCatalogo() {
        return (Sesion.getVariable("catalogo") != null) ? (List<Catalogo>) Sesion.getVariable("catalogo") : null;
    }
    
    public static String getTokenServicio() {
        return (Sesion.getVariable("token") != null) ? Sesion.getVariable("token").toString() : null;
    }
    
    public static String getTokenRefreshServicio() {
        return (Sesion.getVariable("tokenRefresh") != null) ? Sesion.getVariable("tokenRefresh").toString() : null;
    }
    
    public static String getTokenTipo() {
        return (Sesion.getVariable("tokenTipo") != null) ? Sesion.getVariable("tokenTipo").toString() : null;
    }
    
}
