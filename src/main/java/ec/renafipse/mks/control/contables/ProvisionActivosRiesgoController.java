/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.clases.contables.ContableProvisionActivosRiesgo;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.creditos.CalificacionTipoCarteraIfipController;
import ec.renafipse.mks.modelo.creditos.TmpProvisionCarteraDetalle;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.creditos.TmpProvisionCarteraDetalleFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.Serializable;
import java.sql.ResultSet;
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
import oracle.jdbc.OracleTypes;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andres
 */
@ManagedBean(name = "provisionActivosRiesgoController")
@ViewScoped
public class ProvisionActivosRiesgoController extends AbstractController<TmpProvisionCarteraDetalle> implements Serializable {

    @EJB
    private TmpProvisionCarteraDetalleFacade ejbFacade;
    @EJB
    private IfipFacade ejbFacadeIfip;
    private LlamaSP llamaSP;
    private Ifip ifip;
    private Usuario usuario;
    private Date fechaProceso;
    private List<TmpProvisionCarteraDetalle> listaTmpProvisionCarteraDetalle;
    private List<TmpProvisionCarteraDetalle> listaTmpProvisionCarteraDetalleFiltro;
    private List<ContableProvisionActivosRiesgo> listaContableProvisionActivosRiesgo;
    private List<ContableProvisionActivosRiesgo> listaContableProvisionActivosRiesgoFiltro;
    private List<ContableProvisionActivosRiesgo> listaContableProvisionActivosRiesgoTotal;
    private FacesMessage mensaje;
    private RequestContext context;
    
    public ProvisionActivosRiesgoController(){
        super(TmpProvisionCarteraDetalle.class);
    }
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setUsuario(ActivacionUsuario.getUsuario());
        setFechaProceso(new Date());
        llamaSP = new LlamaSP();
    }
    
    /**
     * Método de la vista que sirve para generar la provisión preliminar en pantalla
     * que permite visualizar al usuario los valores y los contables
     * @param event 
     */
    public void generarProvisionPreliminar(ActionEvent event){
        //Encerar las propiedades
        setListaTmpProvisionCarteraDetalle(null);
        setListaContableProvisionActivosRiesgo(null);
        setListaContableProvisionActivosRiesgoTotal(null);
        try {// Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            // Formando parametros para el sp
            llamaSP.setNombreSP("mks_creditos.pkm_provision_cartera.p_genera_provision_temporal");
            llamaSP.setNumeroParametros(4);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha", new java.sql.Timestamp(getFechaProceso().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", String.valueOf(getIfip().getCodigo())});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pc_tmp_contable", OracleTypes.CURSOR});
            llamaSP.getListParametrosSalida().add(new Object[]{"pc_tmp_contable_tot", OracleTypes.CURSOR});
            // Invocando al SP
            llamaSP.invocaSP();           
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Obtener un cursor para los contables
                ResultSet resultSet = (ResultSet)llamaSP.getListResultado().get(0);
                ResultSet resultSetTotal = (ResultSet)llamaSP.getListResultado().get(1);
                if ((resultSet != null) && (resultSetTotal != null)){
                    // Obtener lista contables
                    List<ContableProvisionActivosRiesgo> lista = new ArrayList<ContableProvisionActivosRiesgo>();
                    while(resultSet.next()) { 
                        lista.add(new ContableProvisionActivosRiesgo(resultSet.getString("agencia"),
                                                                     resultSet.getString("tipo_cartera"),
                                                                     resultSet.getString("calificacion"),
                                                                     resultSet.getString("cuenta_debe"),
                                                                     resultSet.getString("cuenta_haber"),
                                                                     resultSet.getBigDecimal("valor_provision")));
                    }
                    // Obtener lista contables totalizada
                    List<ContableProvisionActivosRiesgo> listaTotal = new ArrayList<ContableProvisionActivosRiesgo>();
                    while(resultSetTotal.next()) { 
                        listaTotal.add(new ContableProvisionActivosRiesgo(resultSetTotal.getString("tipo_cartera"),
                                                                          resultSetTotal.getString("cuenta_debe"),
                                                                          resultSetTotal.getString("cuenta_haber"),
                                                                          resultSetTotal.getBigDecimal("valor_provision")));
                    }
                    // Obtener lista generada
                    setListaTmpProvisionCarteraDetalle(ejbFacade.getListaTmpProvisionCarteraDetalleOrdenada(getIfip().getCodigo(), new java.sql.Timestamp(getFechaProceso().getTime())));
                    if (getListaTmpProvisionCarteraDetalle() != null) {
                        if(getListaTmpProvisionCarteraDetalle().size() > 0) {
                            setListaContableProvisionActivosRiesgo(lista);
                            setListaContableProvisionActivosRiesgoTotal(listaTotal);
                            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "", 
                                                       ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProvisionPreliminarCorrecta"));
                        }
                        else
                            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "", 
                                                       ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDatosProvisionPreliminar"));
                    }
                    else
                        mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "", 
                                                   ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDatosProvisionPreliminar"));
                        
                }
                else
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "", 
                                               ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDatosProvisionPreliminar"));
                FacesContext.getCurrentInstance().addMessage(null,mensaje);
            }
        }
        catch(Exception e){
             Logger.getLogger(CalificacionTipoCarteraIfipController.class.getName()).log(Level.SEVERE, null, e);
             mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                        ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorGenerarProvisionPreliminar") + e.getMessage());
             FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        finally{
            if (llamaSP.getConexionBD() != null){
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }
    
    /**
     * Método de la vista que verifica si ya fue generada la provisión temporal de la pantalla,
     * además si ya existe una provisión con esa fecha presenta un dialogo de confimaciòn para
     * que el usuario decida si provisiona nuevamente lo cual genera una duplicidad de contables
     * @param event 
     */
    public void generarProvision(ActionEvent event){
       try { // Verifica datos de provisión temporal
           if ((getListaTmpProvisionCarteraDetalle() == null)){
               mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                          ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorListaTmpProvisionCarteraDetalle"));
               FacesContext.getCurrentInstance().addMessage(null, mensaje);
               return;
           }
           if ((getListaContableProvisionActivosRiesgo() == null)){
               mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                          ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorListaContableProvisionActivosRiesgo"));
               FacesContext.getCurrentInstance().addMessage(null, mensaje);
               return;
           }
           if ((getListaTmpProvisionCarteraDetalle().isEmpty())){
               mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                          ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorListaTmpProvisionCarteraDetalle"));
               FacesContext.getCurrentInstance().addMessage(null, mensaje);
               return;
           }
           if ((getListaContableProvisionActivosRiesgo().isEmpty())){
               mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                          ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorListaContableProvisionActivosRiesgo"));
               FacesContext.getCurrentInstance().addMessage(null, mensaje);
               return;
           }
           // Verifica si existe una provision actual con la misma fecha 
           int existe = existeProvisionPorFechaIfip();
           context = RequestContext.getCurrentInstance();
           if (existe == 1) // En caso de existir presenta un cuadro de dialogo para confirmar
               context.execute("PF('confirmacionProvisionDialog').show()");
           else if (existe == 0)// En caso de no existir genera la provisión
               guardarProvision(event);
          
           //context.execute("PF('generarProvisionPreliminarBot').disable();PF('generarProvisionBot').disable()");
       } catch (Exception ex) {
           Logger.getLogger(CalificacionTipoCarteraIfipController.class.getName()).log(Level.SEVERE, null, ex);
           mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                      ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorGrabar"));
           FacesContext.getCurrentInstance().addMessage(null, mensaje);
       }
       finally{
           if (llamaSP.getConexionBD() != null){
               llamaSP.cerrarConexion();
               llamaSP.setConexionBD(null);
           }
       }
    }
    
    /**
     * Método de la vista que guarda la provisión de cartera junto con sus contables.
     * @param event 
     */
    public void guardarProvision(ActionEvent event) {
        try {// Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos           
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            // Formar los parametros del sp
            llamaSP.setNombreSP("mks_creditos.pkm_provision_cartera.p_genera_provision");
            llamaSP.setNumeroParametros(3);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha", new java.sql.Timestamp(getFechaProceso().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", String.valueOf(getIfip().getCodigo())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", String.valueOf(getUsuario().getCodigo())});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.invocaSP();           
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()){
                setListaTmpProvisionCarteraDetalle(null);
                setListaContableProvisionActivosRiesgo(null);
                setListaContableProvisionActivosRiesgoTotal(null);
                MuestraMensaje.addSatisfactorioPersistencia(1);
            }
            else
                MuestraMensaje.addErrorPersistencia();
        } catch (Exception ex) {
            Logger.getLogger(CalificacionTipoCarteraIfipController.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                                       ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorGrabar"));
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }
    
    /**
     *  Método para la clase que sirve para verificar si ya existe una provisión de una misma fecha
     * @return, 0 si no existe, 1 si existe, 2 en caso de error y termina el proceso
     */
    public int existeProvisionPorFechaIfip(){
        int existe = 0;
        try {// Cargando la conexion de BD    
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            // Formar parametros de sp
            llamaSP.setNombreSP("mks_creditos.pkm_provision_cartera.p_existe_provision_por_fecha");
            llamaSP.setNumeroParametros(3);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha", new java.sql.Timestamp(getFechaProceso().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", String.valueOf(getIfip().getCodigo())});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo_pro", java.sql.Types.BIGINT});
            // Invocando al SP
            llamaSP.invocaSP();           
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                int codigoProvision = Integer.parseInt(llamaSP.getListResultado().get(0).toString());
                if (codigoProvision != 0)
                    existe = 1;
            }
            else{
                existe = 2;
                MuestraMensaje.addErrorPersistencia();
            }
        } catch (Exception ex) {
            Logger.getLogger(CalificacionTipoCarteraIfipController.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al verificar si existe provisión");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            existe = 2;
        }
        finally{
           if (llamaSP.getConexionBD() != null){
               llamaSP.cerrarConexion();
               llamaSP.setConexionBD(null);
           }
       }
       return existe;
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
     * @return the fechaProceso
     */
    public Date getFechaProceso() {
        return fechaProceso;
    }

    /**
     * @param fechaProceso the fechaProceso to set
     */
    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    /**
     * @return the listaTmpProvisionCarteraDetalle
     */
    public List<TmpProvisionCarteraDetalle> getListaTmpProvisionCarteraDetalle() {
        return listaTmpProvisionCarteraDetalle;
    }

    /**
     * @param listaTmpProvisionCarteraDetalle the listaTmpProvisionCarteraDetalle to set
     */
    public void setListaTmpProvisionCarteraDetalle(List<TmpProvisionCarteraDetalle> listaTmpProvisionCarteraDetalle) {
        this.listaTmpProvisionCarteraDetalle = listaTmpProvisionCarteraDetalle;
    }

    /**
     * @return the listaTmpProvisionCarteraDetalleFiltro
     */
    public List<TmpProvisionCarteraDetalle> getListaTmpProvisionCarteraDetalleFiltro() {
        return listaTmpProvisionCarteraDetalleFiltro;
    }

    /**
     * @param listaTmpProvisionCarteraDetalleFiltro the listaTmpProvisionCarteraDetalleFiltro to set
     */
    public void setListaTmpProvisionCarteraDetalleFiltro(List<TmpProvisionCarteraDetalle> listaTmpProvisionCarteraDetalleFiltro) {
        this.listaTmpProvisionCarteraDetalleFiltro = listaTmpProvisionCarteraDetalleFiltro;
    }

    /**
     * @return the listaContableProvisionActivosRiesgo
     */
    public List<ContableProvisionActivosRiesgo> getListaContableProvisionActivosRiesgo() {
        return listaContableProvisionActivosRiesgo;
    }

    /**
     * @param listaContableProvisionActivosRiesgo the listaContableProvisionActivosRiesgo to set
     */
    public void setListaContableProvisionActivosRiesgo(List<ContableProvisionActivosRiesgo> listaContableProvisionActivosRiesgo) {
        this.listaContableProvisionActivosRiesgo = listaContableProvisionActivosRiesgo;
    }

    /**
     * @return the listaContableProvisionActivosRiesgoFiltro
     */
    public List<ContableProvisionActivosRiesgo> getListaContableProvisionActivosRiesgoFiltro() {
        return listaContableProvisionActivosRiesgoFiltro;
    }

    /**
     * @param listaContableProvisionActivosRiesgoFiltro the listaContableProvisionActivosRiesgoFiltro to set
     */
    public void setListaContableProvisionActivosRiesgoFiltro(List<ContableProvisionActivosRiesgo> listaContableProvisionActivosRiesgoFiltro) {
        this.listaContableProvisionActivosRiesgoFiltro = listaContableProvisionActivosRiesgoFiltro;
    }

    /**
     * @return the listaContableProvisionActivosRiesgoTotal
     */
    public List<ContableProvisionActivosRiesgo> getListaContableProvisionActivosRiesgoTotal() {
        return listaContableProvisionActivosRiesgoTotal;
    }

    /**
     * @param listaContableProvisionActivosRiesgoTotal the listaContableProvisionActivosRiesgoTotal to set
     */
    public void setListaContableProvisionActivosRiesgoTotal(List<ContableProvisionActivosRiesgo> listaContableProvisionActivosRiesgoTotal) {
        this.listaContableProvisionActivosRiesgoTotal = listaContableProvisionActivosRiesgoTotal;
    }
    
}
