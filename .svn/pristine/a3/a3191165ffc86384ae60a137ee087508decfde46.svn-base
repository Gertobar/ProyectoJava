/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.lineacredito;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolEnvCom;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author andy
 */
@ManagedBean(name = "lineaCreditoEnvioComisionController")
@ViewScoped
public class LineaCreditoEnvioComisionController extends AbstractController<LineaCreditoSolEnvCom> implements Serializable {

    @EJB
    private LlamaSP llamaSP;
    @EJB
    private LineaCreditoSolicitudFacade ejbFacadeLineaCreditoSolicitud;
    private boolean inactivaBotonEnvio;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitud;
    private List<LineaCreditoSolicitud> listaLineaCreditoSolicitudSeleccionada;
    private LineaCreditoSolicitud lineaCreditoSolicitud;
    private List<Long> codigosEstado;
    private String observaciones;
    private int indiceSeleccionado;
    private boolean seleccionaTodos;

    @PostConstruct
    public void init() {
        try {
            inicializaPropiedades();
            codigosEstado = new ArrayList<Long>();
            codigosEstado.add(Long.valueOf("2"));
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoAgenciaListaCodigoEstado(ActivacionUsuario.getCodigoIfipAgencia(), codigosEstado);
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "init", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void obtieneSolicitudesDeLinea() {
        try {
            codigosEstado = new ArrayList<Long>();
            codigosEstado.add(Long.valueOf("2"));
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoAgenciaListaCodigoEstado(ActivacionUsuario.getCodigoIfipAgencia(), codigosEstado);
            if (listaLineaCreditoSolicitud != null) {
                if (listaLineaCreditoSolicitud.size() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "obtieneSolicitudesDeLinea", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void inicializaPropiedades() {
        try {
            inactivaBotonEnvio = true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "inicializaPropiedades", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void enviaLineaSolicitudComision() {
        try {
            if (validaIngesoDatosEnLista()) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_creditos.pkm_linea_credito_sol_env_com.p_inserta_env_com_act_sol");
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                for (LineaCreditoSolicitud item : listaLineaCreditoSolicitudSeleccionada) {
                    llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                    llamaSP.setNumeroParametros(3);
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_linea_cre_sol", item.getCodigo()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observaciones", item.getLineaCreditoSolEnvCom().getObservaciones()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                    llamaSP.invocaSP();
                    if (llamaSP.isEjecucionCorrecta()) {
                    } else {
                        llamaSP.rollback();
                        MuestraMensaje.addErrorCapaControl();
                        return;
                    }
                }
                MuestraMensaje.addSatisfactorioPersistencia(1);
                llamaSP.commit();
                obtieneSolicitudesDeLinea();
            }
        } catch (Exception ex) {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "enviaLineaSolicitudComision", CapturaError.getErrorException(ex)});
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean validaIngesoDatosEnLista() {
        try {
            int fila = 0;
            if (listaLineaCreditoSolicitudSeleccionada == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionRegistro"));
                return false;
            }
            for (LineaCreditoSolicitud item : listaLineaCreditoSolicitudSeleccionada) {
                fila++;
                try {
                    item.getLineaCreditoSolEnvCom();
                } catch (Exception e) {
                    MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFormatoCampo"), "observaciones", fila, "difente de nulo"));
                    return false;
                }
                if (item.getLineaCreditoSolEnvCom() == null) {
                    MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFormatoCampo"), "observaciones", fila, "difente de nulo"));
                    return false;
                } else {
                    if (item.getLineaCreditoSolEnvCom().getObservaciones() == null) {
                        MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFormatoCampo"), "observaciones", fila, "difente de nulo"));
                        return false;
                    } else {
                        if (item.getLineaCreditoSolEnvCom().getObservaciones().length() <= 0) {
                            MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFormatoCampo"), "observaciones", fila, "difente de nulo"));
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "validaIngesoDatosEnLista", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        indiceSeleccionado = 0;
        seleccionaTodos = false;
        try {
            lineaCreditoSolicitud = (LineaCreditoSolicitud) event.getObject();
            if (listaLineaCreditoSolicitud.contains(lineaCreditoSolicitud)) {
                indiceSeleccionado = listaLineaCreditoSolicitud.indexOf(lineaCreditoSolicitud);
                lineaCreditoSolicitud.setLineaCreditoSolEnvCom(new LineaCreditoSolEnvCom(null, ""));
                listaLineaCreditoSolicitud.set(indiceSeleccionado, lineaCreditoSolicitud);
            } else {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LineaCreditoEnvioComisionController", "onRowSelect", "No se encuentra lineaCreditoSolicitud en el listado"});
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "onRowSelect", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @param event
     */
    public void onRowUnselect(UnselectEvent event) {
        indiceSeleccionado = 0;
        seleccionaTodos = false;
        try {
            lineaCreditoSolicitud = (LineaCreditoSolicitud) event.getObject();
            if (listaLineaCreditoSolicitud.contains(lineaCreditoSolicitud)) {
                indiceSeleccionado = listaLineaCreditoSolicitud.indexOf(lineaCreditoSolicitud);
                lineaCreditoSolicitud.setLineaCreditoSolEnvCom(null);
                listaLineaCreditoSolicitud.set(indiceSeleccionado, lineaCreditoSolicitud);
            } else {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LineaCreditoEnvioComisionController", "onRowUnselect", "No se encuentra lineaCreditoSolicitud en el listado"});
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "onRowUnselect", CapturaError.getErrorException(ex)});
        }
    }
    
     /**
     *
     * @param event
     */
    public void onRowSelectList(ToggleSelectEvent event) {
        int indice = 0;
        seleccionaTodos = true;
        try {
            if (listaLineaCreditoSolicitud != null){
                for(LineaCreditoSolicitud item : listaLineaCreditoSolicitud){
                    item.setLineaCreditoSolEnvCom(new LineaCreditoSolEnvCom(null, ""));
                    listaLineaCreditoSolicitud.set(indice, item);
                    indice++;  
                }
            }else{
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "onRowSelectList", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     *
     */
    public void inicializaDialogo() {
        try {
            observaciones = "";
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "inicializaDialogo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void finalizaDialogo() {
        lineaCreditoSolicitud = null;
        try {
            if (observaciones == null) {
            } else {
                if (observaciones.length() <= 0) {
                } else {
                    if (!seleccionaTodos){
                        lineaCreditoSolicitud = listaLineaCreditoSolicitud.get(indiceSeleccionado);
                        if (listaLineaCreditoSolicitud.contains(lineaCreditoSolicitud)) {
                            lineaCreditoSolicitud.setLineaCreditoSolEnvCom(new LineaCreditoSolEnvCom(null, observaciones));
                            listaLineaCreditoSolicitud.set(indiceSeleccionado, lineaCreditoSolicitud);
                            indiceSeleccionado = 0;
                            observaciones = "";
                        } else {
                            MuestraMensaje.addErrorCapaControl();
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                    new Object[]{"LineaCreditoEnvioComisionController", "onRowSelect", "No se encuentra lineaCreditoSolicitud en el listado"});
                        }
                    }else{
                        int indice = 0;
                        for(LineaCreditoSolicitud item : listaLineaCreditoSolicitud){
                            item.setLineaCreditoSolEnvCom(new LineaCreditoSolEnvCom(null, observaciones));
                            listaLineaCreditoSolicitud.set(indice, item);
                            indice++;  
                        }
                    }
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoEnvioComisionController", "finalizaDialogo", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the inactivaBotonEnvio
     */
    public boolean isInactivaBotonEnvio() {
        return inactivaBotonEnvio;
    }

    /**
     * @param inactivaBotonEnvio the inactivaBotonEnvio to set
     */
    public void setInactivaBotonEnvio(boolean inactivaBotonEnvio) {
        this.inactivaBotonEnvio = inactivaBotonEnvio;
    }

    /**
     * @return the listaLineaCreditoSolicitud
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitud() {
        return listaLineaCreditoSolicitud;
    }

    /**
     * @param listaLineaCreditoSolicitud the listaLineaCreditoSolicitud to set
     */
    public void setListaLineaCreditoSolicitud(List<LineaCreditoSolicitud> listaLineaCreditoSolicitud) {
        this.listaLineaCreditoSolicitud = listaLineaCreditoSolicitud;
    }

    /**
     * @return the lineaCreditoSolicitud
     */
    public LineaCreditoSolicitud getLineaCreditoSolicitud() {
        return lineaCreditoSolicitud;
    }

    /**
     * @param lineaCreditoSolicitud the lineaCreditoSolicitud to set
     */
    public void setLineaCreditoSolicitud(LineaCreditoSolicitud lineaCreditoSolicitud) {
        this.lineaCreditoSolicitud = lineaCreditoSolicitud;
    }

    /**
     * @return the listaLineaCreditoSolicitudSeleccionada
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudSeleccionada() {
        return listaLineaCreditoSolicitudSeleccionada;
    }

    /**
     * @param listaLineaCreditoSolicitudSeleccionada the
     * listaLineaCreditoSolicitudSeleccionada to set
     */
    public void setListaLineaCreditoSolicitudSeleccionada(List<LineaCreditoSolicitud> listaLineaCreditoSolicitudSeleccionada) {
        this.listaLineaCreditoSolicitudSeleccionada = listaLineaCreditoSolicitudSeleccionada;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
