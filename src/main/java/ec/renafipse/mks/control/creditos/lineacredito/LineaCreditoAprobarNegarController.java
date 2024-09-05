/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.creditos.lineacredito;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.FabricaUsuarioPerfil;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolAprNeg;
import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.negocio.creditos.FabricaUsuarioPerfilFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolEnvComFacade;
import ec.renafipse.mks.negocio.creditos.lineacredito.LineaCreditoSolicitudFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
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
@ManagedBean(name = "lineaCreditoAprobarNegarController")
@ViewScoped
public class LineaCreditoAprobarNegarController extends AbstractController<LineaCreditoSolAprNeg> implements Serializable {

    @EJB
    private LlamaSP llamaSP;
    @EJB
    private LineaCreditoSolicitudFacade ejbFacadeLineaCreditoSolicitud;
    @EJB
    private LineaCreditoSolEnvComFacade ejbFacadeLineaCreditoSolEnvCom;
    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;
    @EJB
    private FabricaUsuarioPerfilFacade ejbFacadeFabricaUsuarioPerfil;
    
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
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
            inicializaPropiedades();
            //Verificar que puedan acceder a aprobar solo fabrica quien aprueba
            if( !verificaSiPuedeAcceder() ){
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                Sesion.redireccionaPagina(url);
                return;
            }            
            codigosEstado = new ArrayList<Long>();
            codigosEstado.add(Long.valueOf("3"));
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoAgenciaListaCodigoEstado(ActivacionUsuario.getCodigoIfipAgencia(), codigosEstado);
            if(listaLineaCreditoSolicitud!=null){
                for(int a=0;a<listaLineaCreditoSolicitud.size();a++){
                    //Obtener la observacion del envio a comision
                    listaLineaCreditoSolicitud.get(a).setObservacionesEnvioComision(ejbFacadeLineaCreditoSolEnvCom.getObservacionByCodigoLineaCreditoSolicitud(listaLineaCreditoSolicitud.get(a).getCodigo()));
                    IfipAgencia agencia = ejbFacadeIfipAgencia.getAgenciaPorCodigo(listaLineaCreditoSolicitud.get(a).getCodigoIfipAgencia());
                    listaLineaCreditoSolicitud.get(a).setAgencia(agencia.getNombre());
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "init", CapturaError.getErrorException(ex)});
        }
    }
    
    private boolean verificaSiPuedeAcceder(){
        try{
            List<FabricaUsuarioPerfil> listaFabricaUsuarioPerfil = ejbFacadeFabricaUsuarioPerfil.getPerfilUsuarioFabrica(ActivacionUsuario.getCodigoUsuario(),ActivacionUsuario.getCodigoIfipAgencia());
            boolean esValido = false;
            if( listaFabricaUsuarioPerfil != null ){
                for( int a=0;a<listaFabricaUsuarioPerfil.size();a++ ){
                    if( listaFabricaUsuarioPerfil.get(a).getCodigoPerfil().getCodigo().compareTo(Long.valueOf("6"))==0 ){
                        esValido = true;
                        break;
                    }
                }
            }else
                return false;
            return esValido;
        }catch(Exception ex){
            return false;
        }
    }

    /**
     *
     */
    public void obtieneSolicitudesDeLinea() {
        try {
            codigosEstado = new ArrayList<Long>();
            codigosEstado.add(Long.valueOf("3"));
            listaLineaCreditoSolicitud = ejbFacadeLineaCreditoSolicitud.getListaLineaCreditoSolicitudPorCodigoAgenciaListaCodigoEstado(ActivacionUsuario.getCodigoIfipAgencia(), codigosEstado);
            if (listaLineaCreditoSolicitud != null) {
                if (listaLineaCreditoSolicitud.size() <= 0) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
            if(listaLineaCreditoSolicitud!=null){
                for(int a=0;a<listaLineaCreditoSolicitud.size();a++){
                    //Obtener la observacion del envio a comision
                    listaLineaCreditoSolicitud.get(a).setObservacionesEnvioComision(ejbFacadeLineaCreditoSolEnvCom.getObservacionByCodigoLineaCreditoSolicitud(listaLineaCreditoSolicitud.get(a).getCodigo()));
                    IfipAgencia agencia = ejbFacadeIfipAgencia.getAgenciaPorCodigo(listaLineaCreditoSolicitud.get(a).getCodigoIfipAgencia());
                    listaLineaCreditoSolicitud.get(a).setAgencia(agencia.getNombre());
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "obtieneSolicitudesDeLinea", CapturaError.getErrorException(ex)});
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
                    new Object[]{"LineaCreditoAprobarNegarController", "inicializaPropiedades", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void aprobarLineaSolicitud() {
        try {
            if (validaIngesoDatosEnLista()) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_creditos.pkm_linea_credito_sol_apr_neg.p_inserta_apr_neg_act_sol");
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                for (LineaCreditoSolicitud item : listaLineaCreditoSolicitudSeleccionada) {
                    llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                    llamaSP.setNumeroParametros(4);
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_linea_cre_sol", item.getCodigo()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_aprobado", "S"});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observaciones", item.getLineaCreditoSolAprNeg().getObservaciones()});
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
                listaLineaCreditoSolicitudSeleccionada = null;
            }
        } catch (Exception ex) {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "aprobarLineaSolicitud", CapturaError.getErrorException(ex)});
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
    public void negarLineaSolicitud() {
        try {
            if (validaIngesoDatosEnLista()) {
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_creditos.pkm_linea_credito_sol_apr_neg.p_inserta_apr_neg_act_sol");
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                for (LineaCreditoSolicitud item : listaLineaCreditoSolicitudSeleccionada) {
                    llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                    llamaSP.setNumeroParametros(4);
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_linea_cre_sol", item.getCodigo()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_aprobado", "N"});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observaciones", item.getLineaCreditoSolAprNeg().getObservaciones()});
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
                    new Object[]{"LineaCreditoAprobarNegarController", "negarLineaSolicitud", CapturaError.getErrorException(ex)});
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
                    item.getLineaCreditoSolAprNeg();
                } catch (Exception e) {
                    MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFormatoCampo"), "observaciones", fila, "diferente de nulo"));
                    return false;
                }
                if (item.getLineaCreditoSolAprNeg() == null) {
                    MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFormatoCampo"), "observaciones", fila, "difente de nulo"));
                    return false;
                } else {
                    if (item.getLineaCreditoSolAprNeg().getObservaciones() == null) {
                        MuestraMensaje.addError(MessageFormat.format(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFormatoCampo"), "observaciones", fila, "difente de nulo"));
                        return false;
                    } else {
                        if (item.getLineaCreditoSolAprNeg().getObservaciones().length() <= 0) {
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
                    new Object[]{"LineaCreditoAprobarNegarController", "validaIngesoDatosEnLista", CapturaError.getErrorException(ex)});
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
                lineaCreditoSolicitud.setLineaCreditoSolAprNeg(new LineaCreditoSolAprNeg(null, ""));
                listaLineaCreditoSolicitud.set(indiceSeleccionado, lineaCreditoSolicitud);
            } else {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LineaCreditoAprobarNegarController", "onRowSelect", "No se encuentra lineaCreditoSolicitud en el listado"});
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "onRowSelect", CapturaError.getErrorException(ex)});
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
                lineaCreditoSolicitud.setLineaCreditoSolAprNeg(null);
                listaLineaCreditoSolicitud.set(indiceSeleccionado, lineaCreditoSolicitud);
            } else {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LineaCreditoAprobarNegarController", "onRowUnselect", "No se encuentra lineaCreditoSolicitud en el listado"});
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "onRowUnselect", CapturaError.getErrorException(ex)});
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
                    item.setLineaCreditoSolAprNeg(new LineaCreditoSolAprNeg(null, ""));
                    listaLineaCreditoSolicitud.set(indice, item);
                    indice++;  
                }
            }else{
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenRegistros"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "onRowSelectList", CapturaError.getErrorException(ex)});
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
                    new Object[]{"LineaCreditoAprobarNegarController", "inicializaDialogo", CapturaError.getErrorException(ex)});
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
                            lineaCreditoSolicitud.setLineaCreditoSolAprNeg(new LineaCreditoSolAprNeg(null, observaciones));
                            listaLineaCreditoSolicitud.set(indiceSeleccionado, lineaCreditoSolicitud);
                            indiceSeleccionado = 0;
                            observaciones = "";
                        } else {
                            MuestraMensaje.addErrorCapaControl();
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                                    new Object[]{"LineaCreditoAprobarNegarController", "onRowSelect", "No se encuentra lineaCreditoSolicitud en el listado"});
                        }
                    }else{
                        int indice = 0;
                        for(LineaCreditoSolicitud item : listaLineaCreditoSolicitud){
                            item.setLineaCreditoSolAprNeg(new LineaCreditoSolAprNeg(null, observaciones));
                            listaLineaCreditoSolicitud.set(indice, item);
                            indice++;  
                        }
                    }
                }
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"LineaCreditoAprobarNegarController", "finalizaDialogo", CapturaError.getErrorException(ex)});
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
