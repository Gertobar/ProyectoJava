/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.componentes;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.socios.SocioFacade;
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
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author andres
 */
@ManagedBean(name = "buscarSocioComponenteController")
@ViewScoped
@FacesComponent("buscarSocioComponente")
public class BuscarSocioComponenteController extends UINamingContainer implements Serializable {

    @EJB
    private SocioFacade ejbFacadeSocio;
    private String mensajeCriterio;
    private String tipoBusqueda;
    private String criterioBusqueda;
    private List<Socio> listaSocios;
    private Socio socio;
    private String componentesActualizar;
    private Long codigoSocio;
    private Long codigoPersona;
    private String nombreCompleto;
    private boolean soloSociosAgencia;

    @PostConstruct
    public void init() {
        try {
            criterioBusqueda = "";
            tipoBusqueda = "N";
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "init", CapturaError.getErrorException(ex)});
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
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "actualizaMensaje", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(criterioBusqueda, 6)) {

                if (tipoBusqueda.compareTo("N") == 0) {
                    if (!soloSociosAgencia) {
                        listaSocios = this.ejbFacadeSocio.getItemsNombresIndicaActivo(criterioBusqueda, ActivacionUsuario.getCodigoIfip(), 'S');
                    } else {
                        listaSocios = this.ejbFacadeSocio.getItemsNombresIndicaActivoAgencia(criterioBusqueda, ActivacionUsuario.getCodigoIfip(), 'S', ActivacionUsuario.getCodigoIfipAgencia());
                    }
                } else {
                    if (tipoBusqueda.compareTo("I") == 0) {
                        if (!soloSociosAgencia) {
                            listaSocios = this.ejbFacadeSocio.getItemsIdentificacionIndicaActivo(criterioBusqueda, ActivacionUsuario.getCodigoIfip(), 'S');
                        } else {
                            listaSocios = this.ejbFacadeSocio.getItemsSociofindByIdIfipAgenciaIndicaActivo(criterioBusqueda, ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'S');
                        }
                    } else {
                        MuestraMensaje.addAdvertencia("Tipo de busqueda no permitida");
                    }
                }
                if (listaSocios == null) {
                    socio = null;
                    codigoSocio = Long.valueOf("0");
                    codigoPersona = Long.valueOf("0");
                    nombreCompleto = "";
                    mensajeCriterio = "NO EXISTEN REGISTROS PARA LA BUSQUEDA: " + criterioBusqueda;
                } else {
                    if (listaSocios.size() <= 0) {
                        mensajeCriterio = "NO EXISTEN REGISTROS PARA LA BUSQUEDA: " + criterioBusqueda;
                        socio = null;
                        listaSocios = null;
                        codigoSocio = Long.valueOf("0");
                        codigoPersona = Long.valueOf("0");
                        nombreCompleto = "";
                    }
                }
            } else {
                mensajeCriterio = Validaciones.msg;
                listaSocios = null;
                socio = null;
                codigoSocio = Long.valueOf("0");
                codigoPersona = Long.valueOf("0");
                nombreCompleto = "";
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        try {
            socio = ((Socio) event.getObject());
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "onRowSelect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowUnselect(UnselectEvent event) {
        try {
            socio = null;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "onRowUnselect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void seleccionaSocio() {
        try {
            if (socio != null) {
                codigoSocio = socio.getSocioPK().getCodigo();
                codigoPersona = socio.getCodigoPersona().getCodigo();
                nombreCompleto = socio.getCodigoPersona().getNombreCompleto();
            } else {
                codigoSocio = Long.valueOf("0");
                codigoPersona = Long.valueOf("0");
                nombreCompleto = "0";
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "seleccionaSocio", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param soloSociosAgencia
     */
    public void abrirDialogo(java.lang.Boolean soloSociosAgencia) {
        try {
            listaSocios = null;
            socio = null;
            criterioBusqueda = "";
            codigoSocio = Long.valueOf("0");
            codigoPersona = Long.valueOf("0");
            nombreCompleto = "";
            this.soloSociosAgencia = soloSociosAgencia;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "abrirDialogo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void cambiaTipoBusqueda() {
        try {
            listaSocios = null;
            socio = null;
            criterioBusqueda = "";
            codigoSocio = Long.valueOf("0");
            codigoPersona = Long.valueOf("0");
            nombreCompleto = "";
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"buscarSocioComponenteController", "abrirDialogo", CapturaError.getErrorException(ex)});
        }
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
     * @return the listaSocios
     */
    public List<Socio> getListaSocios() {
        return listaSocios;
    }

    /**
     * @param listaSocios the listaSocios to set
     */
    public void setListaSocios(List<Socio> listaSocios) {
        this.listaSocios = listaSocios;
    }

    /**
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * @return the componentesActualizar
     */
    public String getComponentesActualizar() {
        return componentesActualizar;
    }

    /**
     * @param componentesActualizar the componentesActualizar to set
     */
    public void setComponentesActualizar(String componentesActualizar) {
        this.componentesActualizar = componentesActualizar;
    }

    /**
     * @return the codigoSocio
     */
    public Long getCodigoSocio() {
        return codigoSocio;
    }

    /**
     * @param codigoSocio the codigoSocio to set
     */
    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
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
}
