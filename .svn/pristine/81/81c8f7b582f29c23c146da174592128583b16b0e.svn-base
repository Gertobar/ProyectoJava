/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.componentes;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.negocio.socios.TipoPerTipoIdeFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import java.io.Serializable;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author andres
 */
@ManagedBean(name = "componentePersonaSocio")
@ViewScoped
public class PersonaSocioComponenteController implements Serializable {

    private Long codigoPersona;
    private String nombrePersona;
    private Long codigoSocio;
    private LlamaSP llamaSP;
    private boolean inactivoTextoNombre;
    private boolean inactivoBotonNuevaPersona;
    private String styleBusqueda;
    private String styleNuevaPersona;
    private String identificacionBusqueda;

    private Long tipoIdentificacionNuevaPersona;
    private Long tipoPersonaNuevaPersona;
    private String identificacionNuevaPersona;
    private String nombreNuevaPersona;
    private Date fechaCaducidadIdentificacionNuevaPersona;

    private List<TipoIdentificacion> listaTipoIdentificacion;
    private List<TipoPersona> listaTipoPersona;
    @EJB
    private TipoPerTipoIdeFacade ejbFacadeTipoPersonaTipoIdentificacion;
    @EJB
    private TipoPersonaFacade ejbFacadeTipoPersona;
    private String focoNuevaPersona = "";
    private String focoIdentificacion = "";
    private boolean inactivaControl;

    @PostConstruct
    public void init() {
        llamaSP = new LlamaSP();
        styleBusqueda = "display: inline";
        styleNuevaPersona = "display: none";
        nombreNuevaPersona = "";
        listaTipoPersona = ejbFacadeTipoPersona.findAll();
        tipoPersonaNuevaPersona = Long.valueOf("1");
        listaTipoIdentificacion = ejbFacadeTipoPersonaTipoIdentificacion.getItemsTipoIdentificacionVigente(tipoPersonaNuevaPersona);
        fechaCaducidadIdentificacionNuevaPersona = new Date();
        setFocoNuevaPersona("$(PrimeFaces.escapeClientId('" + JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "nombreNuevaPersona") + "')).focus()");
        setFocoIdentificacion("$(PrimeFaces.escapeClientId('" + JsfUtil.getNombreCompletoComponente(FacesContext.getCurrentInstance(), "identificacionPersona") + "')).focus()");
        inactivoBotonNuevaPersona = true;
    }

    /**
     *
     * @param identificacion
     */
    public void buscar(String identificacion) {
        try {
            identificacionBusqueda = identificacion;
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "buscar", CapturaError.getErrorException(e)});
            nombrePersona = "";
            codigoSocio = Long.valueOf("0");
            codigoPersona = Long.valueOf("0");
        }
    }

    /**
     *
     */
    public void buscarPersona() {
        try {
            if (identificacionBusqueda.length() > 4) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_socios.pkm_socio.p_obtiene_persona_cod_soc");
                llamaSP.setNumeroParametros(4);
                llamaSP.setListParametrosEntrada(new ArrayList());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificacion", identificacionBusqueda});
                llamaSP.setListParametrosSalida(new ArrayList());
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_nombre_persona", Types.VARCHAR});
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_socio", Types.LONGNVARCHAR});
                llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_persona", Types.LONGNVARCHAR});
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    nombrePersona = (String) llamaSP.getListResultado().get(0);
                    codigoSocio = Long.valueOf((String) llamaSP.getListResultado().get(1));
                    codigoPersona = Long.valueOf((String) llamaSP.getListResultado().get(2));
                    if (nombrePersona != null) {
                        if (nombrePersona.length() > 0){
                            inactivoBotonNuevaPersona = true;
                        }
                        else{
                            MuestraMensaje.addAdvertencia("Persona no existe en el sistema");
                            inactivoBotonNuevaPersona = false;
                        }
                    } else {
                        MuestraMensaje.addAdvertencia("Persona no existe en el sistema");
                        inactivoBotonNuevaPersona = false;
                    }
                } else {
                    nombrePersona = "";
                    codigoSocio = Long.valueOf("0");
                    codigoPersona = Long.valueOf("0");
                    inactivoBotonNuevaPersona = true;
                }
            }else{
                MuestraMensaje.addError("Debe ingresar minimo 5 caracteres para la búsqueda");
                nombrePersona = "";
                codigoSocio = Long.valueOf("0");
                codigoPersona = Long.valueOf("0");
                inactivoBotonNuevaPersona = true;
            }
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "buscarPersona", CapturaError.getErrorException(e)});
            nombrePersona = "";
            codigoSocio = Long.valueOf("0");
            codigoPersona = Long.valueOf("0");
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     */
    public void guardaPersona() {
        try {
            if (!validaIdentificacion()) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta"));
                return;
            }

            if (!validaNombreCompleto()) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseNombre"));
                return;
            }

            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_socios.pkg_persona.p_inserta");
            llamaSP.setNumeroParametros(9);
            llamaSP.setListParametrosEntrada(new ArrayList());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_ide", tipoIdentificacionNuevaPersona});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_per", tipoPersonaNuevaPersona});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificacion", identificacionNuevaPersona});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_nombre_completo", nombreNuevaPersona.toUpperCase()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_ingreso", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_actualizacion", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_correo_electronico", ""});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_caducidad_ide", new java.sql.Timestamp(fechaCaducidadIdentificacionNuevaPersona.getTime())});
            llamaSP.setListParametrosSalida(new ArrayList());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo", Types.VARCHAR});
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                llamaSP.commit();
                codigoPersona = Long.valueOf((String) llamaSP.getListResultado().get(0));
                codigoSocio = Long.valueOf("0");
                nombrePersona = nombreNuevaPersona.toUpperCase();
                styleBusqueda = "display: inline";
                styleNuevaPersona = "display: none";
                nombreNuevaPersona = "";
                inactivoTextoNombre = true;
                inactivoBotonNuevaPersona = true;
                identificacionBusqueda = identificacionNuevaPersona;
                MuestraMensaje.addSatisfactorio("Persona grabada satisfactoriamente");
            } else {
                llamaSP.rollback();
                nombrePersona = "";
                codigoSocio = Long.valueOf("0");
                codigoPersona = Long.valueOf("0");
                visualizaBusqueda();
                MuestraMensaje.addError("Error al guardar persona");
            }

        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "guardaPersona", CapturaError.getErrorException(e)});
            nombrePersona = "";
            codigoSocio = Long.valueOf("0");
            codigoPersona = Long.valueOf("0");
            visualizaBusqueda();
            MuestraMensaje.addError("Error al guardar persona");
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     * @param vaciar
     */
    public void vaciarControl(java.lang.Boolean vaciar) {
        try {
            if (vaciar) {
                nombrePersona = "";
                codigoSocio = Long.valueOf("0");
                codigoPersona = Long.valueOf("0");
                nombreNuevaPersona = "";
                identificacionNuevaPersona = "";
                identificacionBusqueda = "";
                visualizaBusqueda();
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "vaciarControl", CapturaError.getErrorException(e)});
        }
    }

    /**
     *
     * @param inactiva
     */
    public void inactivaControl(java.lang.Boolean inactiva) {
        try {
            inactivaControl = inactiva;
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "inactivaControl", CapturaError.getErrorException(e)});
        }
    }

    /**
     *
     */
    public void cambiaTipoIdentificacion() {
        try {
            listaTipoIdentificacion = ejbFacadeTipoPersonaTipoIdentificacion.getItemsTipoIdentificacionVigente(tipoPersonaNuevaPersona);
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "cambiaTipoIdentificacion", CapturaError.getErrorException(e)});
        }

    }

    /**
     *
     * @return
     */
    public boolean validaIdentificacion() {
        boolean valido = false;
        try {
            if (tipoIdentificacionNuevaPersona.compareTo(1L) == 0) {
                try {
                    Long.parseLong(identificacionNuevaPersona);
                } catch (Exception e) {
                    return false;
                }

                if (!Validaciones.validaCedula(identificacionNuevaPersona)) {
                    return false;
                }
            } else {
                if (tipoIdentificacionNuevaPersona.compareTo(2L) == 0) {
                    try {
                        Long.parseLong(identificacionNuevaPersona);
                    } catch (Exception e) {
                        return false;
                    }

                    if (!Validaciones.validaRucPersonaNatural(identificacionNuevaPersona)) {
                        if (!Validaciones.validarRucSociedadPrivada(identificacionNuevaPersona)) {
                            return false;
                        }
                    }
                } else {
                    return tipoIdentificacionNuevaPersona.compareTo(3L) == 0;
                }
            }
            valido = true;
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "validaIdentificacion", CapturaError.getErrorException(e)});
            valido = false;
        }
        return valido;
    }

    /**
     *
     * @return
     */
    public boolean validaNombreCompleto() {
        boolean valido;
        try {
            if (nombreNuevaPersona != null) {
                if (nombreNuevaPersona.length() > 5 && nombreNuevaPersona.length() <= 150) {
                    valido = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "validaNombreCompleto", CapturaError.getErrorException(e)});
            valido = false;
        }
        return valido;
    }

    /**
     *
     */
    public void ocultaBusqueda() {
        try {
            styleBusqueda = "display: none";
            styleNuevaPersona = "display: block";
            identificacionNuevaPersona = identificacionBusqueda;
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "ocultaBusqueda", CapturaError.getErrorException(e)});
            nombrePersona = "";
        }
    }

    /**
     *
     */
    public void visualizaBusqueda() {
        try {
            styleBusqueda = "display: inline";
            styleNuevaPersona = "display: none";
            identificacionBusqueda = "";
            nombreNuevaPersona = "";
            inactivoBotonNuevaPersona = true;
        } catch (Exception e) {
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"componentePersonaSocio", "visualizaPanelBusqueda", CapturaError.getErrorException(e)});
            nombrePersona = "";
        }
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
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
     * @return the inactivoTextoNombre
     */
    public boolean isInactivoTextoNombre() {
        return inactivoTextoNombre;
    }

    /**
     * @param inactivoTextoNombre the inactivo to set
     */
    public void setInactivoTextoNombre(boolean inactivoTextoNombre) {
        this.inactivoTextoNombre = inactivoTextoNombre;
    }

    /**
     * @return the inactivoBotonNuevaPersona
     */
    public boolean isInactivoBotonNuevaPersona() {
        return inactivoBotonNuevaPersona;
    }

    /**
     * @param inactivoBotonNuevaPersona the inactivoBotonNuevaPersona to set
     */
    public void setInactivoBotonNuevaPersona(boolean inactivoBotonNuevaPersona) {
        this.inactivoBotonNuevaPersona = inactivoBotonNuevaPersona;
    }

    /**
     * @return the styleBusqueda
     */
    public String getStyleBusqueda() {
        return styleBusqueda;
    }

    /**
     * @param styleBusqueda the styleBusqueda to set
     */
    public void setStyleBusqueda(String styleBusqueda) {
        this.styleBusqueda = styleBusqueda;
    }

    /**
     * @return the styleNuevaPersona
     */
    public String getStyleNuevaPersona() {
        return styleNuevaPersona;
    }

    /**
     * @param styleNuevaPersona the styleNuevaPersona to set
     */
    public void setStyleNuevaPersona(String styleNuevaPersona) {
        this.styleNuevaPersona = styleNuevaPersona;
    }

    /**
     * @return the itemsTipoIdentificacion
     */
    public List<TipoIdentificacion> getListaTipoIdentificacion() {
        return listaTipoIdentificacion;
    }

    /**
     * @param listaTipoIdentificacion the listaTipoIdentificacion to set
     */
    public void setListaTipoIdentificacion(List<TipoIdentificacion> listaTipoIdentificacion) {
        this.listaTipoIdentificacion = listaTipoIdentificacion;
    }

    /**
     * @return the listaTipoPersona
     */
    public List<TipoPersona> getListaTipoPersona() {
        return listaTipoPersona;
    }

    /**
     * @param listaTipoPersona the listaTipoPersona to set
     */
    public void setListaTipoPersona(List<TipoPersona> listaTipoPersona) {
        this.listaTipoPersona = listaTipoPersona;
    }

    /**
     * @return the identificacionNuevaPersona
     */
    public String getIdentificacionNuevaPersona() {
        return identificacionNuevaPersona;
    }

    /**
     * @param identificacionNuevaPersona the identificacionNuevaPersona to set
     */
    public void setIdentificacionNuevaPersona(String identificacionNuevaPersona) {
        this.identificacionNuevaPersona = identificacionNuevaPersona;
    }

    /**
     * @return the nombreNuevaPersona
     */
    public String getNombreNuevaPersona() {
        return nombreNuevaPersona;
    }

    /**
     * @param nombreNuevaPersona the nombreNuevaPersona to set
     */
    public void setNombreNuevaPersona(String nombreNuevaPersona) {
        this.nombreNuevaPersona = nombreNuevaPersona;
    }

    /**
     * @return the fechaCaducidadIdentificacionNuevaPersona
     */
    public Date getFechaCaducidadIdentificacionNuevaPersona() {
        return fechaCaducidadIdentificacionNuevaPersona;
    }

    /**
     * @param fechaCaducidadIdentificacionNuevaPersona the
     * fechaCaducidadIdentificacionNuevaPersona to set
     */
    public void setFechaCaducidadIdentificacionNuevaPersona(Date fechaCaducidadIdentificacionNuevaPersona) {
        this.fechaCaducidadIdentificacionNuevaPersona = fechaCaducidadIdentificacionNuevaPersona;
    }

    /**
     * @return the focoNuevaPersona
     */
    public String getFocoNuevaPersona() {
        return focoNuevaPersona;
    }

    /**
     * @param focoNuevaPersona the focoNuevaPersona to set
     */
    public void setFocoNuevaPersona(String focoNuevaPersona) {
        this.focoNuevaPersona = focoNuevaPersona;
    }

    /**
     * @return the focoIdentificacion
     */
    public String getFocoIdentificacion() {
        return focoIdentificacion;
    }

    /**
     * @param focoIdentificacion the focoIdentificacion to set
     */
    public void setFocoIdentificacion(String focoIdentificacion) {
        this.focoIdentificacion = focoIdentificacion;
    }

    /**
     * @return the tipoIdentificacionNuevaPersona
     */
    public Long getTipoIdentificacionNuevaPersona() {
        return tipoIdentificacionNuevaPersona;
    }

    /**
     * @param tipoIdentificacionNuevaPersona the tipoIdentificacionNuevaPersona
     * to set
     */
    public void setTipoIdentificacionNuevaPersona(Long tipoIdentificacionNuevaPersona) {
        this.tipoIdentificacionNuevaPersona = tipoIdentificacionNuevaPersona;
    }

    /**
     * @return the tipoPersonaNuevaPersona
     */
    public Long getTipoPersonaNuevaPersona() {
        return tipoPersonaNuevaPersona;
    }

    /**
     * @param tipoPersonaNuevaPersona the tipoPersonaNuevaPersona to set
     */
    public void setTipoPersonaNuevaPersona(Long tipoPersonaNuevaPersona) {
        this.tipoPersonaNuevaPersona = tipoPersonaNuevaPersona;
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
     * @return the inactivaControl
     */
    public boolean isInactivaControl() {
        return inactivaControl;
    }

    /**
     * @param inactivaControl the inactivaControles to set
     */
    public void setInactivaControl(boolean inactivaControl) {
        this.inactivaControl = inactivaControl;
    }

    /**
     * @return the identificacionBusqueda
     */
    public String getIdentificacionBusqueda() {
        return identificacionBusqueda;
    }

    /**
     * @param identificacionBusqueda the identificacionBusqueda to set
     */
    public void setIdentificacionBusqueda(String identificacionBusqueda) {
        this.identificacionBusqueda = identificacionBusqueda;
    }

}
