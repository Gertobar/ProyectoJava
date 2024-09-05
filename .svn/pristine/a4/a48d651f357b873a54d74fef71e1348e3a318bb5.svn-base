/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.CalificacionTipoCartera;
import ec.renafipse.mks.modelo.creditos.TipoCartera;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.creditos.CalificacionTipoCarteraIfipFacade;
import ec.renafipse.mks.negocio.creditos.TipoCarteraFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
 * @author Andres
 */
@ManagedBean(name = "calificacionTipoCarteraIfipController")
@ViewScoped
public class CalificacionTipoCarteraIfipController extends AbstractController<CalificacionTipoCartera> implements Serializable{
    
    @EJB
    private CalificacionTipoCarteraIfipFacade ejbFacade;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private TipoCarteraFacade ejbFacadeTipoCartera;
    private Ifip ifip;
    private String msgValorUnico;
    private Usuario usuario;
    private TipoCartera tipoCartera;
    private List<TipoCartera> listaTipoCartera;
    private LlamaSP llamaSP;
    private List<CalificacionTipoCartera> listaCalificacionTipoCartera;
    private FacesMessage msg;
    
    public CalificacionTipoCarteraIfipController(){
        super(CalificacionTipoCartera.class);
    }
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListaCalificacionTipoCartera(ejbFacade.getListaCalificacionTipoCarteraOrdenada());
        llamaSP = new LlamaSP();
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setUsuario(ActivacionUsuario.getUsuario());
        setTipoCartera(new TipoCartera());
        setListaTipoCartera(ejbFacadeTipoCartera.getItemsTipoCarteraEliminado('N'));
    }
    
    /**
     * Método de la vista que sirve para crear un nuevo objeto
     * @param event 
     */
    public void nuevo(ActionEvent event) {
        setTipoCartera(new TipoCartera());
        setUsuario(getUsuario());
        setSelected(new CalificacionTipoCartera());
        getSelected().setFechaUltimaModificacion(new Date());
        getSelected().setCodigo(Long.parseLong("0"));
    }
    
    /**
     * Método de la vista que sirve para seleccionar un objeto para edición
     * @param event 
     */
    public void edita(ActionEvent event) {
        if (getSelected() != null) {
            setTipoCartera(getSelected().getCodigoTipoCartera());
            setUsuario(getUsuario());
        } else {
            MuestraMensaje.addError("Seleccione un registro primero");
        }
    }
    
    /**
     * Método que valida que el nombre de calificación por tipo cartera sea único
     */
    public void validaUnicaCalificacion() {
        msg = null;
        if (getSelected().getCodigoTipoCartera() == null) 
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoCarteraNulo"));
        if (getSelected().getCalificacion().trim().isEmpty())
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString(""));
        CalificacionTipoCartera calificacionTipoCartera = ejbFacade.getCalificacionTipoCartera( Integer.valueOf(getIfip().getCodigo().toString()),
                                                                                                getSelected().getCodigoTipoCartera().getCodigo(), 
                                                                                                getSelected().getCalificacion().trim().toUpperCase());
        if (calificacionTipoCartera != null)
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CalificacionCarteraExiste"));
        if (msg != null){
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().validationFailed();
        }
    }
    
    /**
     * Método que valida si el rango de provisión se encuentra bien ingresado
     */
    public void validaRangoValorProvisionActual(){
        msg = null;
        Double valorProvision;
        try {
            valorProvision = new Double(getSelected().getValorActualProvision().toString());            
            if(!((valorProvision >= (Double)this.getSelected().getProvisionDesde().doubleValue())&&(valorProvision <= (Double)this.getSelected().getProvisionHasta().doubleValue())))
                msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RangoValorProvisionActual"));
        }catch(Exception e){
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos"));
            FacesContext.getCurrentInstance().validationFailed();
        }  
        if (msg != null){
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().validationFailed();
        }
    }
    
    /**
     * Método que valida si los rangos de mora estan bien ingresados
     */
    public void validaRangoDiasMora(){
        Long valorDiasMoraInicial;
        msg = null;
        try {
            valorDiasMoraInicial = getSelected().getDiasMoraInicial();
            if(!(valorDiasMoraInicial <= this.getSelected().getDiasMoraFinal()))
                msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DiasMoraInicial"));
        }catch(Exception e){
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos"));
            FacesContext.getCurrentInstance().validationFailed();
        } 
        if (msg != null){
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().validationFailed();
        }
    }
    
    /**
     * Método que recorre las validaciones del formulario
     */
    public void validaFormulario(){
       if (getSelected().getCodigo() == 0)//Unicamente cuando es una nueva calificaciòn se valida el nombre por tipo de cartera
        validaUnicaCalificacion();
       
       validaRangoValorProvisionActual();//Valida rango provisión
       validaRangoDiasMora();//Valida rango dias de mora
    }
    
    /**
     * Método de la vista que sirve para guardar una calificación tipo cartera 
     * @param event 
     */
    public void guarda(ActionEvent event){
        try {//Valida si los datos ingresados en el formulario estan correctos
            validaFormulario();
            if (FacesContext.getCurrentInstance().isValidationFailed())//Valida campos requeridos
                return;
            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_creditos.pkm_calificacion_tip_car.p_inserta_calificacion_tip_car");
            llamaSP.setNumeroParametros(13);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo", getSelected().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", getIfip().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_cartera", getSelected().getCodigoTipoCartera().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_calificacion", getSelected().getCalificacion().toString().toUpperCase()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_dias_mora_inicial", getSelected().getDiasMoraInicial()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_dias_mora_final", getSelected().getDiasMoraFinal()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_provision_desde", getSelected().getProvisionDesde()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_provision_hasta", getSelected().getProvisionHasta()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_valor_actual_provision", getSelected().getValorActualProvision()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", String.valueOf(getUsuario().getCodigo())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_maquina", ObtieneInformacionCliente.obtenerNombreEquipo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_eliminado", String.valueOf(getSelected().getEliminado())});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            // Invocando al SP
            llamaSP.invocaSP();           
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();    
                setListaCalificacionTipoCartera(ejbFacade.getListaCalificacionTipoCarteraOrdenada());
                MuestraMensaje.addSatisfactorioPersistencia(1);
            } else
                llamaSP.rollback();
        } catch (Exception ex) {
            Logger.getLogger(CalificacionTipoCarteraIfipController.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, 
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                                                          ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorGrabar")));
        }finally{
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }
    /**
     * 
     * @return msgValorUnico
     */
    public String getMsgValorUnico() {
        return msgValorUnico;
    }
    
    /**
     * 
     * @param msgValorUnico 
     */
    public void setMsgValorUnico(String msgValorUnico) {
        this.msgValorUnico = msgValorUnico;
    }
    
    /**
     * 
     * @return usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }
    
    /**
     * 
     * @param usuario 
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
    * 
    * @return ifip
    */
    public Ifip getIfip() {
        return ifip;
    }
    
    /**
     * 
     * @param ifip 
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    /**
     * @return the listaTipoCartera
     */
    public List<TipoCartera> getListaTipoCartera() {
        return listaTipoCartera;
    }

    /**
     * @param listaTipoCartera the listaTipoCartera to set
     */
    public void setListaTipoCartera(List<TipoCartera> listaTipoCartera) {
        this.listaTipoCartera = listaTipoCartera;
    }

    /**
     * @return the tipoCartera
     */
    public TipoCartera getTipoCartera() {
        return tipoCartera;
    }

    /**
     * @param tipoCartera the tipoCartera to set
     */
    public void setTipoCartera(TipoCartera tipoCartera) {
        this.tipoCartera = tipoCartera;
    }

    /**
     * @return the listaCalificacionTipoCartera
     */
    public List<CalificacionTipoCartera> getListaCalificacionTipoCartera() {
        return listaCalificacionTipoCartera;
    }

    /**
     * @param listaCalificacionTipoCartera the listaCalificacionTipoCartera to set
     */
    public void setListaCalificacionTipoCartera(List<CalificacionTipoCartera> listaCalificacionTipoCartera) {
        this.listaCalificacionTipoCartera = listaCalificacionTipoCartera;
    }
}
