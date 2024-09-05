/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.componentes;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.clases.personas.PersonaListaNegra;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andres
 */
@ManagedBean(name = "listasUAFEComponenteController")
@ViewScoped
@FacesComponent("componenteAlertaUAF")
public class ListasUAFEComponente extends UINamingContainer implements Serializable {

    private String usuarioEnvia = "Mascoop";
    private String agencia = "";
    private String modulo = "";

    private String styleComponente;
    private String mensaje;
    private PersonaNatural personaNatural;

    private String personaListasNegras;
    private String tituloMensaje;
    private String mensajePanel = "";
    private int estiloMensaje;
    public static final int SENTENCIADO = 3;
    public static final int POLITICAMENTE_EXPUESTO = 2;
    public static final int HOMONIMO = 2;

    private int tipoPersona;

    private int CODIGO_SERVICIO_MENSAJE_CANAL = 18;
    private int CODIGO_ESTADO_MENSAJE = 1;

    private String nombres = "";
    private String identificacion = "";

    private java.lang.String widgetVar;

    private String imagen;
    private PersonaListaNegra personaListaNegra;

    private boolean mostraMensaje;
    private int tipoMensaje;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private UsuarioFacade ejbUsuarioFacade;

    /**
     *
     */
    @PostConstruct
    public void init() {
        try {
            setIdentificacion("");
            setNombres("");
            setImagen("../../../resources/images/informacion.png");
            styleComponente = "display: none !important;";
            mensaje = null;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BoletoComponenteController", "init", CapturaError.getErrorException(e)});
        }
    }

    /**
     *
     * @param codigoServicioBoleto
     * @param codigoServicio
     * @param widgetVar
     */
    public void abrirDialogo(java.lang.String identificacion, java.lang.String nombres, java.lang.Integer tipoMensaje, java.lang.String modulo, java.lang.String widgetVar) {
        try {
            setModulo(modulo);
            setTipoMensaje(tipoMensaje);
            this.widgetVar = widgetVar;
            styleComponente = "display: inline !important";
            mostraMensaje = false;

//            if (!cargarDatosMensaje(tipoMensaje)) {
//                styleComponente = "display: none !important;";
//                cerrarDialogo(widgetVar);
//            }
            if (identificacion != null) {
                if (!identificacion.isEmpty()) {
                    //  validaUAFPorIdentificacion(identificacion);
                }
            }
            if (!mostraMensaje) {
                if (nombres != null) {
                    if (!nombres.isEmpty()) {
//                        validaUAFPorIdentificacion(nombres);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ListasUAFEComponente", "abrirDialogo", CapturaError.getErrorException(e)});
            styleComponente = "display: none !important;";
            cerrarDialogo(widgetVar);
        }
    }

    /**
     *
     * @param widgetVar
     */
    public void cerrarDialogo(java.lang.String widgetVar) {
        try {
            widgetVar = "PF('" + widgetVar + "').hide();";
            RequestContext.getCurrentInstance().execute(widgetVar);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ListasUAFEComponente", "cerrarDialogo", CapturaError.getErrorException(e)});
        }
    }

    public void cerrar() {
        cerrarDialogo(this.widgetVar);
    }

    /**
     * @return the styleComponente
     */
    public String getStyleComponente() {
        return styleComponente;
    }

    /**
     * @param styleComponente the styleComponente to set
     */
    public void setStyleComponente(String styleComponente) {
        this.styleComponente = styleComponente;
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
     *
     * @return verdadero si la validacion se encuentra las personas que estan en
     * listas de homonimos y pep's y busca por identificacion
     */
    public boolean validaUAFPorIdentificacionONombres(String nombresOIdentificacion, boolean notificar) {
        setMostraMensaje(false);
        this.estiloMensaje = 0;
        boolean procesoValidado = true;
        if (nombresOIdentificacion.isEmpty()) {
            cerrarDialogo(widgetVar);
            return procesoValidado;
        }
        setMensajePanel("");
        setPersonaListasNegras("");
        try {
            personaListaNegra = this.ejbFacadePersona.consultaPersonaListasNegras(nombresOIdentificacion);
            if (personaListaNegra.getNombres() != null) {
                if (!personaListaNegra.getNombres().isEmpty()) {
                    setPersonaListasNegras(personaListaNegra.getNombres());
                    String mensajeLocal = "";
                    setTituloMensaje(mensajeLocal);
                    setTipoPersona(personaListaNegra.getTipoPersona());
                    switch (personaListaNegra.getTipoPersona()) {
                        case 1:
                            procesoValidado = true;
                            if (!notificar) {
                                break;
                            }
                            mensajeLocal = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersona") + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaHomonimos");
                            setTituloMensaje(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaHomonimos"));
                            MuestraMensaje.addAdvertencia(mensajeLocal);
                            setMensajePanel(mensajeLocal.toUpperCase() + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaComunica"));
                            this.estiloMensaje = 1;
                            setImagen("../../../resources/images/informacion.png");
                            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, getTituloMensaje(), getMensajePanel()));
                            enviarEmail();
                            setMostraMensaje(true);
                            break;
                        case 2:
                            procesoValidado = true;
                            if (!notificar) {
                                break;
                            }
                            mensajeLocal = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersona") + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaPoliticamenteExpuestas");
                            setTituloMensaje(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaPoliticamenteExpuestas"));
                            MuestraMensaje.addSatisfactorio(mensajeLocal);
                            setMensajePanel(mensajeLocal.toUpperCase() + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaComunica"));
                            this.estiloMensaje = 2;
                            setImagen("../../../resources/images/alerta.png");
                            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, getTituloMensaje(), getMensajePanel()));
                            enviarEmail();
                            setMostraMensaje(true);
                            break;

                        case 3:
                            procesoValidado = false;
                            if (!notificar) {
                                break;
                            }
                            mensajeLocal = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersona") + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaSentenciados");
                            setTituloMensaje(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaSentenciados"));
                            MuestraMensaje.addError(mensajeLocal);
                            setMensajePanel(mensajeLocal.toUpperCase() + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaComunica"));
                            this.estiloMensaje = 3;
                            setImagen("../../../resources/images/error.png");
                            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL, getTituloMensaje(), getMensajePanel()));
                            enviarEmail();
                            setMostraMensaje(true);
                            break;
                        default:
                            procesoValidado = false;
                            setMostraMensaje(false);
                    }
                } else {
                    setMostraMensaje(false);
                }
            } else {
                setMostraMensaje(false);
            }
        } catch (Exception localException) {
            setMostraMensaje(false);
        }
        return procesoValidado;
    }

    /**
     * Permite enviar el correo se debe tomar en cuenta la tabla parametrizada
     * de oficial_cumplimiento
     */
    public void enviarEmail() {
        try {            
            if (!mensaje.isEmpty()) {
                String mensajeEmailFormateado = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasMensajeEmail");
                try {
                    List<Usuario> usuarioLista = ejbUsuarioFacade.getUsuariobyCodigo(ActivacionUsuario.getCodigoUsuario());
                    usuarioEnvia = usuarioLista.get(0).getCodigoPersona().getNombreCompleto();
                    agencia = ejbFacadePersona.obtenerAgencia(ActivacionUsuario.getCodigoIfipAgencia()).getNombre();
                    modulo = getModulo();
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "modulo_____________________________" + modulo);
                } catch (Exception e) {
                }
                String mensajeEmail = String.format(
                        mensajeEmailFormateado,
                        this.ejbFacadePersona.getItemsByCodigo(
                                this.ejbFacadePersona.obtenerOficialCumplimiento().getCodigoPersona()).get(0).getNombreCompleto(),
                        personaListaNegra.getNombres(),
                        personaListaNegra.getIdentificacion(),
                        estiloMensaje == 1 ? ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaHomonimos") : estiloMensaje == 2 ? ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaPoliticamenteExpuestas") : estiloMensaje == 3 ? ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasTipoPersonaSentenciados") : "",
                        agencia,
                        modulo,
                        usuarioEnvia,
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasSaludosOrigen")
                );
                this.ejbFacadePersona.enviarEmail(
                        CODIGO_SERVICIO_MENSAJE_CANAL,
                        ActivacionUsuario.getCodigoUsuario(),
                        CODIGO_ESTADO_MENSAJE,
                        this.ejbFacadePersona.obtenerOficialCumplimiento().getCorreoElectronico(),
                        this.ejbFacadePersona.obtenerOficialCumplimiento().getCorreoElectronico(),
                        mensajeEmail,
                        new java.util.Date(),
                        "N",
                        this.ejbFacadePersona.obtenerOficialCumplimiento().getCorreoElectronico(),
                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListasNegrasAsuntoEmail") + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
            }
        } catch (Exception e) {
        }
    }

    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    public String getPersonaListasNegras() {
        return personaListasNegras;
    }

    public void setPersonaListasNegras(String personaListasNegras) {
        this.personaListasNegras = personaListasNegras;
    }

    public String getTituloMensaje() {
        return tituloMensaje;
    }

    public void setTituloMensaje(String tituloMensaje) {
        this.tituloMensaje = tituloMensaje;
    }

    public String getMensajePanel() {
        return mensajePanel;
    }

    public void setMensajePanel(String mensajePanel) {
        mensaje = mensajePanel;
        this.mensajePanel = mensajePanel;
    }

    public int getEstiloMensaje() {
        return estiloMensaje;
    }

    public void setEstiloMensaje(int estiloMensaje) {
        this.estiloMensaje = estiloMensaje;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isMostraMensaje() {
        return mostraMensaje;
    }

    public void setMostraMensaje(boolean mostraMensaje) {
        this.mostraMensaje = mostraMensaje;
    }

    public PersonaFacade getEjbFacadePersona() {
        return ejbFacadePersona;
    }

    public void setEjbFacadePersona(PersonaFacade ejbFacadePersona) {
        if (this.ejbFacadePersona == null) {
            this.ejbFacadePersona = ejbFacadePersona;
        }
    }

    public int getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(int tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public UsuarioFacade getEjbUsuarioFacade() {
        return ejbUsuarioFacade;
    }

    public void setEjbUsuarioFacade(UsuarioFacade ejbUsuarioFacade) {
        if (this.ejbUsuarioFacade == null) {
            this.ejbUsuarioFacade = ejbUsuarioFacade;
        }
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getUsuarioEnvia() {
        return usuarioEnvia;
    }

    public void setUsuarioEnvia(String usuarioEnvia) {
        this.usuarioEnvia = usuarioEnvia;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    
    
}
