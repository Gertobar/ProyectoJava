package ec.renafipse.mks.control.contables.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.PeriodoContable;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import java.io.Serializable;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "cierrePeriodoAnualBean")
@ViewScoped
public class CierrePeriodoAnualBean extends AbstractController<PeriodoContable> implements Serializable {

    @EJB
    private PeriodoContableFacade ejbFacade;

    @EJB
    private PlanDeCuentaIfipFacade ejbFacadePlanDeCuentasIfip;

   
    private LlamaSP llamaSP;
    private GeneraReporte generaReporte;

    private List<PlanDeCuenta> itemsPlanDeCuentaCuentaUtilidad;
    private List<PlanDeCuenta> itemsPlanDeCuentaCuentaPerdida;
    private List<PeriodoContable> itemsPeriodoContable;
    
    private PeriodoContable periodoContable;
    private PlanDeCuenta planCuentaCuentaUtilidad;
    private PlanDeCuenta planCuentaCuentaPerdida;

    /**
     * Initialize the concrete Comprobante controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     *
     * @throws java.text.ParseException
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
       
        //Instancion el proceso de llama Store Procedure
        setLlamaSP(new LlamaSP());
        this.setItemsPlanDeCuentaCuentaPerdida(this.ejbFacadePlanDeCuentasIfip.getItemsPlanCuentaVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
        this.setItemsPlanDeCuentaCuentaUtilidad(this.ejbFacadePlanDeCuentasIfip.getItemsPlanCuentaVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
        this.setItemsPeriodoContable(this.ejbFacade.findAll());
        
    }

    public CierrePeriodoAnualBean() {
        // Inform the Abstract parent controller of the concrete Comprobante?cap_first Entity
        super(PeriodoContable.class);
    }

   
    // --------------------------------------------------------------------------
    // -- PROCESO PARA CIRRE Y SU REVERSO
    /**
     * Cierra el Periodo Contable Seleccionado
     */
    public void cierePeriodoContable() {
       

        RequestContext context = RequestContext.getCurrentInstance();
        try {
         
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);            
            // Verificando que la ejecucion del proceso ha sido correcta
            
            llamaSP.setNombreSP("mks_contables.pkm_perido_contable.p_cierra");
            llamaSP.setNumeroParametros(7);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo_actual", periodoContable.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuenta_perdida", planCuentaCuentaPerdida.getPlanDeCuentaPK().getCuentaContable()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuenta_utilidad", planCuentaCuentaUtilidad.getPlanDeCuentaPK().getCuentaContable()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_plan", planCuentaCuentaPerdida.getPlanDeCuentaPK().getCodigoTipoPlan()});
            /////SALIDA
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_periodo_nuevo", Types.VARCHAR});

            // Invoca el SP
            llamaSP.invocaSP();
            
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CierreRealizadoConExito")+" "+llamaSP.getListResultado().get(0).toString();
                MuestraMensaje.addInformacion(msg);
                this.init();
                //this.nuevo(actionEvent);

                //context.execute("MovimientoBovedaFor.update()");
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cierrePeriodoAnualBean", "cierePeriodoContable", CapturaError.getErrorException(ex)});
        }

    }
    
    /**
     * Reversa el Cierre Anual del Periodo Contable
     */
    public void reversaCierePeriodoContable() {
       

        RequestContext context = RequestContext.getCurrentInstance();
        try {
          
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);            
            // Verificando que la ejecucion del proceso ha sido correcta
            
            llamaSP.setNombreSP("mks_contables.pkm_perido_contable.p_reversa_cierre");
            llamaSP.setNumeroParametros(3);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo_actual", periodoContable.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            
            /////SALIDA
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            
            // Invoca el SP
            llamaSP.invocaSP();
            
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CierreReversadoConExito")+" "+periodoContable.getCodigo();
                MuestraMensaje.addInformacion(msg);
                this.init();
                //this.nuevo(actionEvent);

                //context.execute("MovimientoBovedaFor.update()");
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cierrePeriodoAnualBean", "reversaCierePeriodoContable", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * @return the llamaSP
     */
    public LlamaSP getLlamaSP() {
        return llamaSP;
    }

    /**
     * @param llamaSP the llamaSP to set
     */
    public void setLlamaSP(LlamaSP llamaSP) {
        this.llamaSP = llamaSP;
    }

    /**
     * @return the generaReporte
     */
    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    /**
     * @param generaReporte the generaReporte to set
     */
    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

    /**
     * @return the itemsPlanDeCuentaCuentaUtilidad
     */
    public List<PlanDeCuenta> getItemsPlanDeCuentaCuentaUtilidad() {
        return itemsPlanDeCuentaCuentaUtilidad;
    }

    /**
     * @param itemsPlanDeCuentaCuentaUtilidad the itemsPlanDeCuentaCuentaUtilidad to set
     */
    public void setItemsPlanDeCuentaCuentaUtilidad(List<PlanDeCuenta> itemsPlanDeCuentaCuentaUtilidad) {
        this.itemsPlanDeCuentaCuentaUtilidad = itemsPlanDeCuentaCuentaUtilidad;
    }    

    /**
     * @return the itemsPeriodoContable
     */
    public List<PeriodoContable> getItemsPeriodoContable() {
        return itemsPeriodoContable;
    }

    /**
     * @param itemsPeriodoContable the itemsPeriodoContable to set
     */
    public void setItemsPeriodoContable(List<PeriodoContable> itemsPeriodoContable) {
        this.itemsPeriodoContable = itemsPeriodoContable;
    }

    /**
     * @return the itemsPlanDeCuentaCuentaPerdida
     */
    public List<PlanDeCuenta> getItemsPlanDeCuentaCuentaPerdida() {
        return itemsPlanDeCuentaCuentaPerdida;
    }

    /**
     * @param itemsPlanDeCuentaCuentaPerdida the itemsPlanDeCuentaCuentaPerdida to set
     */
    public void setItemsPlanDeCuentaCuentaPerdida(List<PlanDeCuenta> itemsPlanDeCuentaCuentaPerdida) {
        this.itemsPlanDeCuentaCuentaPerdida = itemsPlanDeCuentaCuentaPerdida;
    }

    /**
     * @return the periodoContable
     */
    public PeriodoContable getPeriodoContable() {
        return periodoContable;
    }

    /**
     * @param periodoContable the periodoContable to set
     */
    public void setPeriodoContable(PeriodoContable periodoContable) {
        this.periodoContable = periodoContable;
    }

    /**
     * @return the planCuentaCuentaUtilidad
     */
    public PlanDeCuenta getPlanCuentaCuentaUtilidad() {
        return planCuentaCuentaUtilidad;
    }

    /**
     * @param planCuentaCuentaUtilidad the planCuentaCuentaUtilidad to set
     */
    public void setPlanCuentaCuentaUtilidad(PlanDeCuenta planCuentaCuentaUtilidad) {
        this.planCuentaCuentaUtilidad = planCuentaCuentaUtilidad;
    }
   

    /**
     * @return the planCuentaCuentaPerdida
     */
    public PlanDeCuenta getPlanCuentaCuentaPerdida() {
        return planCuentaCuentaPerdida;
    }

    /**
     * @param planCuentaCuentaPerdida the planCuentaCuentaPerdida to set
     */
    public void setPlanCuentaCuentaPerdida(PlanDeCuenta planCuentaCuentaPerdida) {
        this.planCuentaCuentaPerdida = planCuentaCuentaPerdida;
    }


}
