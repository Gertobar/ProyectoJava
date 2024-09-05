package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.GrupoCuentaContable;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaPK;
import ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta;
import ec.renafipse.mks.negocio.contables.GrupoCuentaContableFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaFacade;
import ec.renafipse.mks.negocio.contables.TipoPlanDeCuentaFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "planDeCuentaController")
@ViewScoped
public class PlanDeCuentaController extends AbstractController<PlanDeCuenta> {

    @EJB
    private PlanDeCuentaFacade ejbFacade;

    @EJB
    private TipoPlanDeCuentaFacade ejbFacadeTipoPlanDeCuenta;

    @EJB
    private GrupoCuentaContableFacade ejbFacadeGrupoCuentaContable;
    
    @EJB
    private TipoPlanDeCuentaFacade ejbFacadeTipoPlanCuenta;

    private List<PlanDeCuenta> itemsPlanCuenta;
    private List<TipoPlanDeCuenta> itemsTipoPlanCuenta;
    private List<GrupoCuentaContable> itemsGrupoPlanCuenta;
    private List<PlanDeCuenta> itemsPlanCuentaPadre;
    private boolean deshabilitarGuardar;
    
    private PlanDeCuenta cuentaPadre;

    /**
     * Initialize the concrete PlanDeCuenta controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsPlanCuenta(this.ejbFacade.getItemsPlanCuenta());
        this.setItemsTipoPlanCuenta(this.ejbFacadeTipoPlanDeCuenta.getItemsTipoPlanCuentaVigenteEliminado('S', 'N'));
        this.setItemsGrupoPlanCuenta(this.ejbFacadeGrupoCuentaContable.getItemsGrupoPlanCuentaEliminado('N'));
    }

    public void nuevo(ActionEvent event) {
        this.setItemsPlanCuentaPadre(new ArrayList<PlanDeCuenta>());
        this.setSelected(new PlanDeCuenta());
        this.getSelected().setPlanDeCuentaPK(new PlanDeCuentaPK());
        if (!this.tipoPlanCuentaValido()){
            this.deshabilitarGuardar=true;
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPlanCuentaError"));
        }else{
            this.deshabilitarGuardar=false;            
        }
        //this.setCuentaPadre(new PlanDeCuenta());
    }
    
    public void edita(ActionEvent event) {
        if (!this.tipoPlanCuentaValido()){
            this.deshabilitarGuardar=true;
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPlanCuentaError"));
        }else{
            this.actualizarPadres();
            this.deshabilitarGuardar=false;
            PlanDeCuentaPK pk=new PlanDeCuentaPK(this.getSelected().getCuentaPadre(), this.getSelected().getPlanDeCuentaPK().getCodigoTipoPlan());
            this.setCuentaPadre(this.ejbFacade.find(pk));
        }
        //this.setCuentaPadre(new PlanDeCuenta());
    }
    
    public void guardar(ActionEvent event){
        this.saveNew(event);
        this.setItemsPlanCuenta(this.ejbFacade.findAll());
    }
    
    public void editar(ActionEvent event){
        this.save(event);
        this.setItemsPlanCuenta(this.ejbFacade.findAll());        
    }

    public void actualizarPadres() {
        //System.out.println(this.getSelected().getTipoPlanDeCuenta()+"--"+this.getSelected().getCodigoGrupo());
        if (this.getSelected().getTipoPlanDeCuenta() != null && this.getSelected().getCodigoGrupo() != null) {
            this.getSelected().getPlanDeCuentaPK().setCodigoTipoPlan(this.getSelected().getTipoPlanDeCuenta().getCodigo());
            this.setItemsPlanCuentaPadre(this.ejbFacade.getItemsPlanCuentaGrupoTipo(this.getSelected().getTipoPlanDeCuenta().getCodigo(), this.getSelected().getCodigoGrupo().getCodigo(), 'N'));
        } else {
            this.setItemsPlanCuentaPadre(null);
        }
    }
    
    public void obtenerCodigoPadre(){
        if (this.getCuentaPadre()!=null){
            this.getSelected().setCuentaPadre(this.getCuentaPadre().getPlanDeCuentaPK().getCuentaContable());
        }else{
            this.getSelected().setCuentaPadre(null);
        }
    }
    /**
     * Verifica si existe solo un tipo de cuenta vigente el cual no cebe estar eliminado
     * @return 
     */
    private boolean tipoPlanCuentaValido() {
        List<TipoPlanDeCuenta> planes = this.ejbFacadeTipoPlanCuenta.getItemsTipoPlanCuentaVigente('S');
        if (planes.size() != 1) {
            return false;
        } else {
            if (planes.get(0).getVigente() != 'S') {
                return false;
            } else {
                return true;
            }

        }
    }
    
    public void eliminaPlanDeCuenta(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.itemsPlanCuenta = this.ejbFacade.findAll();
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"conceptoTransaccionController", "eliminaConceptoTransaccion", CapturaError.getErrorException(ex)});
            }
        }

    }
    
     /**
     * @return the itemsPlanCuenta
     */
    public List<PlanDeCuenta> getItemsPlanCuenta() {
        return itemsPlanCuenta;
    }

    /**
     * @param itemsPlanCuenta the itemsPlanCuenta to set
     */
    public void setItemsPlanCuenta(List<PlanDeCuenta> itemsPlanCuenta) {
        this.itemsPlanCuenta = itemsPlanCuenta;
    }

    /**
     * @return the ejbFacadeTipoPlanDeCuenta
     */
    public TipoPlanDeCuentaFacade getEjbFacadeTipoPlanDeCuenta() {
        return ejbFacadeTipoPlanDeCuenta;
    }

    /**
     * @param ejbFacadeTipoPlanDeCuenta the ejbFacadeTipoPlanDeCuenta to set
     */
    public void setEjbFacadeTipoPlanDeCuenta(TipoPlanDeCuentaFacade ejbFacadeTipoPlanDeCuenta) {
        this.ejbFacadeTipoPlanDeCuenta = ejbFacadeTipoPlanDeCuenta;
    }

    /**
     * @return the itemsTipoPlanCuenta
     */
    public List<TipoPlanDeCuenta> getItemsTipoPlanCuenta() {
        return itemsTipoPlanCuenta;
    }

    /**
     * @param itemsTipoPlanCuenta the itemsTipoPlanCuenta to set
     */
    public void setItemsTipoPlanCuenta(List<TipoPlanDeCuenta> itemsTipoPlanCuenta) {
        this.itemsTipoPlanCuenta = itemsTipoPlanCuenta;
    }

    /**
     * @return the ejbFacadeGrupoCuentaContable
     */
    public GrupoCuentaContableFacade getEjbFacadeGrupoCuentaContable() {
        return ejbFacadeGrupoCuentaContable;
    }

    /**
     * @param ejbFacadeGrupoCuentaContable the ejbFacadeGrupoCuentaContable to
     * set
     */
    public void setEjbFacadeGrupoCuentaContable(GrupoCuentaContableFacade ejbFacadeGrupoCuentaContable) {
        this.ejbFacadeGrupoCuentaContable = ejbFacadeGrupoCuentaContable;
    }

    /**
     * @return the itemsGrupoPlanCuenta
     */
    public List<GrupoCuentaContable> getItemsGrupoPlanCuenta() {
        return itemsGrupoPlanCuenta;
    }

    /**
     * @param itemsGrupoPlanCuenta the itemsGrupoPlanCuenta to set
     */
    public void setItemsGrupoPlanCuenta(List<GrupoCuentaContable> itemsGrupoPlanCuenta) {
        this.itemsGrupoPlanCuenta = itemsGrupoPlanCuenta;
    }

    /**
     * @return the itemsGrupoPlanCuentaPadre
     */
    public List<PlanDeCuenta> getItemsPlanCuentaPadre() {
        return itemsPlanCuentaPadre;
    }

    /**
     * @param itemsGrupoPlanCuentaPadre the itemsGrupoPlanCuentaPadre to set
     */
    public void setItemsPlanCuentaPadre(List<PlanDeCuenta> itemsGrupoPlanCuentaPadre) {
        this.itemsPlanCuentaPadre = itemsGrupoPlanCuentaPadre;
    }

    /**
     * @return the cuentaPadre
     */
    public PlanDeCuenta getCuentaPadre() {
        return cuentaPadre;
    }

    /**
     * @param cuentaPadre the cuentaPadre to set
     */
    public void setCuentaPadre(PlanDeCuenta cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
    }

    /**
     * @return the deshabilitarGuardar
     */
    public boolean isDeshabilitarGuardar() {
        return deshabilitarGuardar;
    }

    /**
     * @param deshabilitarGuardar the deshabilitarGuardar to set
     */
    public void setDeshabilitarGuardar(boolean deshabilitarGuardar) {
        this.deshabilitarGuardar = deshabilitarGuardar;
    }

}
