/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.tesoreria;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.CargaFormatoArchivo;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.tesoreria.ParametroBanCenUsuMod;
import ec.renafipse.mks.modelo.tesoreria.ParametroBancoCentral;
import ec.renafipse.mks.modelo.tesoreria.TipoParametroBancoCentral;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.tesoreria.ParametroBanCenUsuModFacade;
import ec.renafipse.mks.negocio.tesoreria.ParametroBancoCentralFacade;
import ec.renafipse.mks.negocio.tesoreria.TipoParametroBancoCentralFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author andres
 */
@ManagedBean(name = "parametrosBancoCentralIfipController")
@ViewScoped
public class ParametrosBancoCentralIfipController extends AbstractController<ParametroBancoCentral> implements Serializable {

    @EJB
    private ParametroBancoCentralFacade ejbFacade;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private TipoParametroBancoCentralFacade ejbFacadeTipoParametroBancoCentralFacade;
    @EJB
    private ParametroBanCenUsuModFacade ejbFacadeParametroBanCenUsuModFacade;
    
    //*BORRAR*/
    @EJB
    CargaFormatoArchivo cargaFormatoArchivo;
    
    
    private Usuario usuario;
    private Ifip ifip;
    private List<ParametroBancoCentral> listaParametrosBancoCentral;
    private List<TipoParametroBancoCentral> listaTipoParametroBancoCentral;
    private List<ParametroBanCenUsuMod> listaParametroBanCenUsuMod;
    private boolean edicion;
    private FacesMessage msg;

    public ParametrosBancoCentralIfipController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip((Ifip) ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setUsuario(ActivacionUsuario.getUsuario());
        setListaTipoParametroBancoCentral(ejbFacadeTipoParametroBancoCentralFacade.getTipoParametroBancoCentralEliminado('N'));
        setListaParametrosBancoCentral(ejbFacade.getTipoParametroBancoCentral(ActivacionUsuario.getCodigoIfip()));
    }

    public void nuevo(ActionEvent event) {
        try {
            ParametroBancoCentral parametroBancoCentral = new ParametroBancoCentral();
            parametroBancoCentral.setCodigoTipoParBanCen(new TipoParametroBancoCentral());
            parametroBancoCentral.setCodigoIfip(getIfip());
            parametroBancoCentral.setEliminado('N');
            parametroBancoCentral.setValorFecha(null);
            parametroBancoCentral.setValorNumerico(BigDecimal.ZERO);
            parametroBancoCentral.setValorTexto("");
            listaParametroBanCenUsuMod = new ArrayList<ParametroBanCenUsuMod>();
            ParametroBanCenUsuMod parametroBanCenUsuMod = new ParametroBanCenUsuMod();
            parametroBanCenUsuMod.setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
            parametroBanCenUsuMod.setFecha(new java.sql.Timestamp(new Date().getTime()));
            parametroBanCenUsuMod.setAccion("NUEVO");
            parametroBanCenUsuMod.setCodigoParametroBanCen(parametroBancoCentral);
            listaParametroBanCenUsuMod.add(parametroBanCenUsuMod);
            parametroBancoCentral.setParametroBanCenUsuModCollection(listaParametroBanCenUsuMod);
            setSelected(parametroBancoCentral);
            edicion = false;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"parametrosBancoCentralIfipController", "nuevo", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error, al crear un nuevo parametro");
        }
    }

    public void edita(ActionEvent event) {
        try {
            if (getSelected() != null) {
                ParametroBancoCentral parametroBancoCentral = getSelected();
                listaParametroBanCenUsuMod = ejbFacadeParametroBanCenUsuModFacade.getListaParametroBanCenUsuModParametro(getSelected().getCodigo());
                ParametroBanCenUsuMod parametroBanCenUsuMod = new ParametroBanCenUsuMod();
                parametroBanCenUsuMod.setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
                parametroBanCenUsuMod.setFecha(new java.sql.Timestamp(new Date().getTime()));
                parametroBanCenUsuMod.setAccion("EDITA");
                parametroBanCenUsuMod.setCodigoParametroBanCen(parametroBancoCentral);
                listaParametroBanCenUsuMod.add(parametroBanCenUsuMod);
                parametroBancoCentral.setParametroBanCenUsuModCollection(listaParametroBanCenUsuMod);
                edicion = true;
            } else {
                MuestraMensaje.addError("Seleccione un registro primero");
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"parametrosBancoCentralIfipController", "edita", CapturaError.getErrorException(e)});
            MuestraMensaje.addError("Error, al editar un nuevo parametro");
        }
    }

    public void muestra(ActionEvent event) {
        try{
            cargaFormatoArchivo.setNombreFormato("CARGASPI2Z");
            cargaFormatoArchivo.setNombreCompletoArchivo("/home/andres/Documentos/SPI02.zip");
            cargaFormatoArchivo.cargaArchivo();
            boolean a = cargaFormatoArchivo.isCargaCorrecta();
            if (!cargaFormatoArchivo.isCargaCorrecta()) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", cargaFormatoArchivo.getCargaError());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else{
                ArrayList<Object[]> resultado = cargaFormatoArchivo.getResultado();
                ArrayList<Object[]> resultadoErrores = cargaFormatoArchivo.getResultadoErrores();
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Archivo cargado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", CapturaError.getErrorPersistencia(e));
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorPersistencia(e),
                    new Object[]{"parametrosBancoCentralIfipController", "muestra",
                        CapturaError.getErrorPersistencia(e)});
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
    }

    public void guardar(ActionEvent event) {
        try {
            if (!validaValores()) {
                return;
            }

            if (!edicion) {
                ejbFacade.create(getSelected());
            } else {
                ejbFacade.edit(getSelected());
            }
            setListaParametrosBancoCentral(ejbFacade.getTipoParametroBancoCentral(ActivacionUsuario.getCodigoIfip()));
            listaParametroBanCenUsuMod = null;
            MuestraMensaje.addSatisfactorioPersistencia(1);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", CapturaError.getErrorPersistencia(e));
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorPersistencia(e),
                    new Object[]{"parametrosBancoCentralIfipController", "guardar",
                        CapturaError.getErrorPersistencia(e)});
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public boolean validaValores() {
        boolean valido = false;
        try {
            if (getSelected() != null) {
                if (getSelected().getValorNumerico().toString().length() > 0
                        || getSelected().getValorTexto().length() > 0
                        || getSelected().getValorFecha().toString().length() > 0) {
                    valido = true;
                }
                //Valida valor numero
                try {
                    if (getSelected().getValorNumerico().toString().length() > 0) {
                        BigDecimal valor = getSelected().getValorNumerico();
                        if (valor.compareTo(BigDecimal.ZERO) == 0) {
                            if (getSelected().getValorTexto().length() <= 0
                                    && getSelected().getValorFecha() == null) {
                                MuestraMensaje.addError("Error, debe ingresar al menos un valor para el parámetro");
                                FacesContext.getCurrentInstance().validationFailed();
                                return false;
                            } else {
                                getSelected().setValorNumerico(null);
                            }
                        }
                    }
                } catch (Exception e) {
                    MuestraMensaje.addError("Error, solo se permiten valores numericos mayores que 0.");
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e), new Object[]{"parametrosBancoCentralIfipController", "validaValores", CapturaError.getErrorException(e)});
                    FacesContext.getCurrentInstance().validationFailed();
                    return false;
                }
                //Valida valor texto
                if (getSelected().getValorTexto().length() > 10) {
                    MuestraMensaje.addError("Error, longitud maxima permitida es de 10 caracteres.");
                    FacesContext.getCurrentInstance().validationFailed();
                    return false;
                }
            }

            if (valido == false) {
                MuestraMensaje.addError("Error, debe ingresar al menos un valor para el parámetro");
                FacesContext.getCurrentInstance().validationFailed();
            }

        } catch (Exception e) {
            MuestraMensaje.addError("Error en la validación de campos de valores.");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e), new Object[]{"parametrosBancoCentralIfipController", "validaValores", CapturaError.getErrorException(e)});
            FacesContext.getCurrentInstance().validationFailed();
            return false;
        }
        return valido;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    /**
     * @return the listaParametrosBancoCentral
     */
    public List<ParametroBancoCentral> getListaParametrosBancoCentral() {
        return listaParametrosBancoCentral;
    }

    /**
     * @param listaParametrosBancoCentral the listaParametrosBancoCentral to set
     */
    public void setListaParametrosBancoCentral(List<ParametroBancoCentral> listaParametrosBancoCentral) {
        this.listaParametrosBancoCentral = listaParametrosBancoCentral;
    }

    /**
     * @return the listaTipoParametroBancoCentral
     */
    public List<TipoParametroBancoCentral> getListaTipoParametroBancoCentral() {
        return listaTipoParametroBancoCentral;
    }

    /**
     * @param listaTipoParametroBancoCentral the listaTipoParametroBancoCentral
     * to set
     */
    public void setListaTipoParametroBancoCentral(List<TipoParametroBancoCentral> listaTipoParametroBancoCentral) {
        this.listaTipoParametroBancoCentral = listaTipoParametroBancoCentral;
    }

    /**
     * @return the listaParametroBanCenUsuMod
     */
    public List<ParametroBanCenUsuMod> getListaParametroBanCenUsuMod() {
        return listaParametroBanCenUsuMod;
    }

    /**
     * @param listaParametroBanCenUsuMod the listaParametroBanCenUsuMod to set
     */
    public void setListaParametroBanCenUsuMod(List<ParametroBanCenUsuMod> listaParametroBanCenUsuMod) {
        this.listaParametroBanCenUsuMod = listaParametroBanCenUsuMod;
    }

}
