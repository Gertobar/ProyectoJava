/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.utilitario;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author vicastoc
 */
public class MuestraMensaje {

    /*
     //Detalles de Mensajes
     public static final String ERRORCAPANEGOCIO = "Error en capa de Negocio. Comuniquese con Soporte. RENAFIPSE";
     public static final String ERRORCAPACONTROL = "Error en capa de Control. Comuniquese con Soporte. RENAFIPSE";
     public static final String REGISTROGRABADO = "Registro Grabado Actualizado Satisfactoriamente";
     public static final String REGISTROELIMINADO = "Registro Eliminado Satisfactoriamente";
    
     //Informacion del Mensaje
     private static final String ERRORINFO = "Error";
     private static final String FATALERRORINFO = "Fatal Error";
     private static final String ADVERTENCIAINFO = "Adevertencia";
     private static final String INFORMACIONINFO = "Informacion";
     private static final String SATISFACTORIOINFO = "Satisfactorio";*/
    // Tipos de Severidad del Mensaje
    private static final int INFORMACION = 0;
    private static final int SATISFACTORIO = 0;
    private static final int ADVERTENCIA = 1;
    private static final int ERROR = 2;
    private static final int FATALERROR = 2;

    // error en capa de negocio
    public static void addErrorCapaNegocio() {
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapaNegocio");
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        mensaje(titulo, msg, ERROR);
    }

    // error en capa de negocio
    public static void addErrorCapaControl() {
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapaControl");
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        mensaje(titulo, msg, ERROR);
    }

    /**
     * Muestra el mensaje de error al usuario con el nombre del Facade en donde
     * se proporciono el error
     *
     * @param nombreFacade
     */
    public static void addErrorCapaControlFacace(String nombreFacade) {
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapaControlFacade") + " " + nombreFacade;
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        mensaje(titulo, msg, ERROR);
    }
    //MENSAJES DE PROCESOS ALMACENADOS

    public static void addErrorLlamaSP(String msg) {
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        mensaje(titulo, msg, ERROR);
    }

    public static void addFatalErrorLlamaSP(Exception ext) {
        String msg = ext.toString();
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FatalErrorInfo");
        mensaje(titulo, msg, FATALERROR);
    }

    //MENSAJES DE PERSISTENCIA
    public static void addErrorPersistencia() {
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCapaControl");
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        mensaje(titulo, msg, ERROR);
    }

    public static void addErrorPersistencia(String msg) {
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        mensaje(titulo, msg, ERROR);
    }

    public static void addSatisfactorioPersistencia(int tipo) {
        if (tipo == 1) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
            String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SatisfactorioInfo");
            mensaje(titulo, msg, INFORMACION);
        } else {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
            String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SatisfactorioInfo");
            mensaje(titulo, msg, INFORMACION);
        }
    }

    // MENSAJES GENERALES
    public static void addInformacion(String msg) {
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("InformacionInfo");
        mensaje(titulo, msg, INFORMACION);
    }

    public static void addSatisfactorio(String msg) {
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SatisfactorioInfo");
        mensaje(titulo, msg, SATISFACTORIO);
    }

    public static void addAdvertencia(String msg) {
        if (msg != null) {
            String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AdvertenciaInfo");
            mensaje(titulo, msg, ADVERTENCIA);
        }
    }

    public static void addError(String msg) {
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        mensaje(titulo, msg, ERROR);
    }

    public static void addFatalError(String msg) {
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FatalErrorInfo");
        mensaje(titulo, msg, FATALERROR);
    }

    // Mensaje
    private static void mensaje(String informacion, String mensaje, int severidadMsg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage.Severity severidad = null;
        if (severidadMsg == 0) {
            severidad = FacesMessage.SEVERITY_INFO;
        }
        if (severidadMsg == 1) {
            severidad = FacesMessage.SEVERITY_WARN;
        }
        if (severidadMsg == 2) {
            severidad = FacesMessage.SEVERITY_ERROR;
        }
        if (severidadMsg == 3) {
            severidad = FacesMessage.SEVERITY_FATAL;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, informacion, mensaje));
    }

    /**
     * Devuelve el objeto Face de Un error
     *
     * @param msg
     * @return
     */
    public static FacesMessage getFaceError(String msg) {
        String titulo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo");
        FacesMessage.Severity severidad = FacesMessage.SEVERITY_ERROR;
        FacesMessage faceMsg
                = new FacesMessage(titulo, msg);
        faceMsg.setSeverity(severidad);

        return faceMsg;
    }

}
