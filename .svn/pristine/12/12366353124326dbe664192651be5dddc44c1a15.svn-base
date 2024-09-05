/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.componentes;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author andres
 */
@ManagedBean(name = "buscarPersonaComponenteController")
@ViewScoped
@FacesComponent("buscarPersonaComponente")
public class BuscarPersonaComponenteController extends UINamingContainer implements Serializable{
    
    @EJB
    private PersonaFacade ejbFacadePersona;
    private String tipoBusqueda;
    private String criterioBusqueda;
    private Persona persona; 
    private List<Persona> listaPersonas;
    private Long codigoPersona;
    private String nombreCompleto;
    private String mensajeCriterio;
    private java.lang.Boolean soloPersonaNatural;
            
    @PostConstruct
    public void init() {
        try {
            listaPersonas = null;
            persona = null;
            criterioBusqueda = "";
            tipoBusqueda= "N";
            codigoPersona = Long.valueOf("0");
            nombreCompleto = "";
        } catch (NumberFormatException ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "init", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     *
     * @param soloPersonaNatural
     */
    public void abrirDialogo(java.lang.Boolean soloPersonaNatural) {
        try {
            listaPersonas = null;
            persona = null;
            criterioBusqueda = "";
            tipoBusqueda= "N";
            codigoPersona = Long.valueOf("0");
            nombreCompleto = "";
            this.soloPersonaNatural = soloPersonaNatural;
            RequestContext.getCurrentInstance().update("listaPersonasComponenteForm");
        } catch (NumberFormatException ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "abrirDialogo", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     *
     */
    public void cambiaTipoBusqueda() {
        try {
            listaPersonas = null;
            persona = null;
            criterioBusqueda = "";
            codigoPersona = Long.valueOf("0");
            nombreCompleto = "";
        } catch (NumberFormatException ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "cambiaTipoBusqueda", CapturaError.getErrorException(ex)});
        }

    }
    
    /**
     *
     * @param event
     */
    public void actualizaMensaje(AjaxBehaviorEvent event) {
        try {
            if (!Validaciones.cumpleRequerimientoCampo(criterioBusqueda, 6)) {
                mensajeCriterio = Validaciones.msg;
            } else {
                mensajeCriterio = "";
            }
        } catch (NumberFormatException ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "actualizaMensaje", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     *
     * @param soloPersonaNatural
     */
    public void obtienePersonas(java.lang.Boolean soloPersonaNatural) {
        try {
            this.soloPersonaNatural = soloPersonaNatural;
            if (Validaciones.cumpleRequerimientoCampo(criterioBusqueda, 6)) {
                if (tipoBusqueda.compareTo("N") == 0) {
                    if (!this.soloPersonaNatural) {
                        listaPersonas = ejbFacadePersona.getItemsPersonaNombreCompleto(criterioBusqueda.toUpperCase());
                    }else{
                        listaPersonas = ejbFacadePersona.getItemsPersonaNombreCompletoPersonaNatural(criterioBusqueda.toUpperCase());
                    }
                } else {
                    if (tipoBusqueda.compareTo("I") == 0) {
                        if (!this.soloPersonaNatural) {
                            listaPersonas = ejbFacadePersona.getItemsPersonaIdentificacion(criterioBusqueda);
                        }else{
                            listaPersonas = ejbFacadePersona.getItemsPersonaIdentificacionPersonaNatural(criterioBusqueda);
                        }
                    } else {
                        MuestraMensaje.addAdvertencia("Tipo de busqueda no permitida");
                    }
                }
                if (listaPersonas == null) {
                    persona = null;
                    codigoPersona = Long.valueOf("0");
                    nombreCompleto = "";
                    mensajeCriterio = "NO EXISTEN REGISTROS PARA LA BUSQUEDA: " + criterioBusqueda;
                } else {
                    if (listaPersonas.size() <= 0) {
                        mensajeCriterio = "NO EXISTEN REGISTROS PARA LA BUSQUEDA: " + criterioBusqueda;
                        persona = null;
                        codigoPersona = Long.valueOf("0");
                        listaPersonas = null;
                        nombreCompleto = "";
                    }
                }
            } else {
                mensajeCriterio = Validaciones.msg;
                listaPersonas = null;
                persona = null;
                codigoPersona = Long.valueOf("0");
                nombreCompleto = "";
            }
        } catch (NumberFormatException ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "obtienePersonas", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        try {
            persona = ((Persona) event.getObject());
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "onRowSelect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowUnselect(UnselectEvent event) {
        try {
            persona = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "onRowUnselect", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     *
     * @param event
     */
    public void seleccionaPersona(ActionEvent event) {
        try {
            if (persona != null) {                
                codigoPersona = persona.getCodigo();
                nombreCompleto = persona.getNombreCompleto();                
            } else {
                codigoPersona = Long.valueOf("0");
                nombreCompleto = "";
            }
            tipoBusqueda= "N";
            criterioBusqueda = "";
            listaPersonas = null;
            persona = null;
        } catch (NumberFormatException ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BuscarPersonaComponenteController", "seleccionaPersona", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * @return the tipoBusqueda
     */
    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    /**
     * @param tipoBusqueda the tipoBusqueda to set
     */
    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    /**
     * @return the criterioBusqueda
     */
    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    /**
     * @param criterioBusqueda the criterioBusqueda to set
     */
    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the listaPersonas
     */
    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    /**
     * @param listaPersonas the listaPersonas to set
     */
    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    /**
     * @return the codigoPersona
     */
    public Long getCodigoPersona() {
        return codigoPersona;
    }

    /**
     * @param codigoPersona the codigoPersona to set
     */
    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the mensajeCriterio
     */
    public String getMensajeCriterio() {
        return mensajeCriterio;
    }

    /**
     * @param mensajeCriterio the mensajeCriterio to set
     */
    public void setMensajeCriterio(String mensajeCriterio) {
        this.mensajeCriterio = mensajeCriterio;
    }   
}