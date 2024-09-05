package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.GrupoCuentaContable;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaIfip;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaIfipPK;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.negocio.contables.GrupoCuentaContableFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "planDeCuentaIfipController")
@ViewScoped
public class PlanDeCuentaIfipController extends AbstractController<PlanDeCuentaIfip> {

    @EJB
    private PlanDeCuentaIfipFacade ejbFacade;

    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private GrupoCuentaContableFacade ejbFacadeGrupoCuentaContable;

    @EJB
    private PlanDeCuentaFacade ejbFacadePlanDeCuenta;

    private PlanDeCuentaController planDeCuentaController;

    private List<PlanDeCuenta> planDeCuentaExistentesList;
    private List<PlanDeCuentaIfip> planDeCuentaAsignadosNoEliminadosList;
    private List<PlanDeCuentaIfip> planDeCuentaAsignadosEliminadosList;
    private List<PlanDeCuentaIfip> planDeCuentaAsignadosList;
    private List<PlanDeCuentaIfip> planDeCuentaNoAsignadosList;

    private List<Ifip> itemsIfip;
    private List<GrupoCuentaContable> itemsGrupoCuentaContable;
    private Timestamp fechaRegistro;

    private Ifip ifip;
    private GrupoCuentaContable grupo;
    private short nivel;

    private DualListModel<PlanDeCuenta> itemsPlanDeCuenta;
    
    
        

    /**
     * Initialize the concrete PlanDeCuentaIfip controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setPlanDeCuentaExistentesList(new ArrayList<PlanDeCuenta>());
        this.setPlanDeCuentaAsignadosNoEliminadosList(new ArrayList<PlanDeCuentaIfip>());
        this.setPlanDeCuentaNoAsignadosList(new ArrayList<PlanDeCuentaIfip>());
        this.setPlanDeCuentaAsignadosEliminadosList(new ArrayList<PlanDeCuentaIfip>());
        this.setPlanDeCuentaAsignadosList(new ArrayList<PlanDeCuentaIfip>());
        this.setItemsPlanDeCuenta(new DualListModel<PlanDeCuenta>());
        this.setItemsIfip(this.ejbFacadeIfip.getItemsIfipEliminado('N'));
        this.setItemsGrupoCuentaContable(this.ejbFacadeGrupoCuentaContable.getItemsGrupoPlanCuentaEliminado('N'));
        this.ifip = new Ifip();

    }

    public void cambioIfip() {
        this.grupo = null;
        this.nivel = 0;
        this.getPlanDeCuentaAsignadosList().clear();
        this.getPlanDeCuentaNoAsignadosList().clear();
        this.itemsPlanDeCuenta = new DualListModel<PlanDeCuenta>();
    }

    public void actualizaListas() {
        PlanDeCuentaIfip planIfip;
        this.setFechaRegistro(new java.sql.Timestamp(new Date().getTime()));
        this.getPlanDeCuentaAsignadosList().clear();
        this.getPlanDeCuentaNoAsignadosList().clear();
        if (this.getIfip() != null && this.getGrupo() != null && nivel >= 3 && nivel <= 5) {
            this.setPlanDeCuentaExistentesList(this.ejbFacadePlanDeCuenta.getItemsPlanCuentaGrupoNivel(grupo.getCodigo(), this.nivel));
            this.setPlanDeCuentaAsignadosNoEliminadosList(this.ejbFacade.getItemsPlanCuentaIfipGrupoEliminadoNivel(this.getIfip().getCodigo(), this.grupo.getCodigo(), 'N', this.nivel));
            this.setPlanDeCuentaAsignadosEliminadosList(this.ejbFacade.getItemsPlanCuentaIfipGrupoEliminadoNivel(this.getIfip().getCodigo(), this.grupo.getCodigo(), 'S', this.nivel));
            //Clasifica planes de cuenta Asignados o No asignados
            for (PlanDeCuenta plan : this.getPlanDeCuentaExistentesList()) {
                planIfip = new PlanDeCuentaIfip(new PlanDeCuentaIfipPK(plan.getPlanDeCuentaPK().getCuentaContable(), plan.getPlanDeCuentaPK().getCodigoTipoPlan(), this.ifip.getCodigo()), ActivacionUsuario.getCodigoUsuario(), this.fechaRegistro, 'N');
                planIfip.setPlanDeCuenta(plan);
                boolean asignado = false;
                for (PlanDeCuentaIfip itemPlanIfip : this.getPlanDeCuentaAsignadosNoEliminadosList()) {
                    if (itemPlanIfip.getPlanDeCuentaIfipPK().equals(planIfip.getPlanDeCuentaIfipPK())) {
                        asignado = true;
                        planIfip = itemPlanIfip;
                        break;
                    }
                }
                if (asignado) {
                    this.getPlanDeCuentaAsignadosList().add(planIfip);
                } else {
                    for (PlanDeCuentaIfip itemPlanIfip : this.getPlanDeCuentaAsignadosEliminadosList()) {
                        if (itemPlanIfip.getPlanDeCuentaIfipPK().equals(planIfip.getPlanDeCuentaIfipPK())) {
                            planIfip = itemPlanIfip;
                            break;
                        }
                    }
                    this.getPlanDeCuentaNoAsignadosList().add(planIfip);
                }
            }
            this.obtieneListaPlanDeCuenta(planDeCuentaAsignadosList, planDeCuentaNoAsignadosList);
        }
        
    }
    
    private void obtieneListaPlanDeCuenta(List<PlanDeCuentaIfip> asignados,List<PlanDeCuentaIfip> noAsignados){
        List<PlanDeCuenta> planAsignados=new ArrayList<PlanDeCuenta>();
        for (PlanDeCuentaIfip planIfip:asignados){
            planAsignados.add(planIfip.getPlanDeCuenta());
        }
        List<PlanDeCuenta> planNoAsignados=new ArrayList<PlanDeCuenta>();
        for (PlanDeCuentaIfip planIfip:noAsignados){
            planNoAsignados.add(planIfip.getPlanDeCuenta());
        }
        this.itemsPlanDeCuenta = new DualListModel<PlanDeCuenta>(planNoAsignados, planAsignados);
    }
    
    private PlanDeCuentaIfip buscaPlanCuenta(PlanDeCuenta plan,List<PlanDeCuentaIfip> lista){
        for (PlanDeCuentaIfip planIfip : lista) {
            if (planIfip.getPlanDeCuenta().equals(plan))
                return planIfip;
        }
        return null;
    }
    
            
    public void guardaCuentasIfip() {
        List<PlanDeCuenta> planNoAsignados=this.itemsPlanDeCuenta.getSource();
        List<PlanDeCuenta> planAsignados=this.itemsPlanDeCuenta.getTarget();
        PlanDeCuentaIfip planIfip;
        for (PlanDeCuenta plan : planAsignados) {
            planIfip=this.buscaPlanCuenta(plan, this.getPlanDeCuentaAsignadosEliminadosList());
            if (planIfip!=null) {
                planIfip.setEliminado('N');
                this.ejbFacade.edit(planIfip);
            } else if (this.buscaPlanCuenta(plan, this.getPlanDeCuentaAsignadosNoEliminadosList())==null) {
                planIfip=this.buscaPlanCuenta(plan, this.getPlanDeCuentaNoAsignadosList());
                if (planIfip!=null)this.ejbFacade.create(planIfip);
            }
        }
        for (PlanDeCuenta plan : planNoAsignados) {
            planIfip=this.buscaPlanCuenta(plan, this.getPlanDeCuentaAsignadosNoEliminadosList());
            if (planIfip!=null) {
                planIfip.setEliminado('S');
                this.ejbFacade.edit(planIfip);
            } 
        }
        this.init();
        this.cambioIfip();
        MuestraMensaje.addSatisfactorio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado"));
        
    }
    
    

    public PlanDeCuentaIfipController() {
        // Inform the Abstract parent controller of the concrete PlanDeCuentaIfip?cap_first Entity
        super(PlanDeCuentaIfip.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPlanDeCuentaIfipPK().setCuentaContable(this.getSelected().getPlanDeCuenta().getPlanDeCuentaPK().getCuentaContable());
        this.getSelected().getPlanDeCuentaIfipPK().setCodigoTipoPlan(this.getSelected().getPlanDeCuenta().getPlanDeCuentaPK().getCodigoTipoPlan());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPlanDeCuentaIfipPK(new ec.renafipse.mks.modelo.contables.PlanDeCuentaIfipPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        planDeCuentaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of RegistroContable entities
     * that are retrieved from PlanDeCuentaIfip?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RegistroContable page
     */
    public String navigateRegistroContableCollection() {

        return "/registroContable/index";
    }

    /**
     * Sets the "selected" attribute of the PlanDeCuenta controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePlanDeCuenta(ActionEvent event) {
        if (this.getSelected() != null && planDeCuentaController.getSelected() == null) {
            planDeCuentaController.setSelected(this.getSelected().getPlanDeCuenta());
        }
    }

    /**
     * Sets the "items" attribute with a collection of ComprobanteDetalle
     * entities that are retrieved from PlanDeCuentaIfip?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for ComprobanteDetalle page
     */
    public String navigateComprobanteDetalleCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ComprobanteDetalle_items", this.getSelected().getComprobanteDetalleCollection());
        }
        return "/comprobanteDetalle/index";
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
     * @return the ejbFacadeIfip
     */
    public IfipFacade getEjbFacadeIfip() {
        return ejbFacadeIfip;
    }

    /**
     * @param ejbFacadeIfip the ejbFacadeIfip to set
     */
    public void setEjbFacadeIfip(IfipFacade ejbFacadeIfip) {
        this.ejbFacadeIfip = ejbFacadeIfip;
    }

    /**
     * @return the itemsIfip
     */
    public List<Ifip> getItemsIfip() {
        return itemsIfip;
    }

    /**
     * @param itemsIfip the itemsIfip to set
     */
    public void setItemsIfip(List<Ifip> itemsIfip) {
        this.itemsIfip = itemsIfip;
    }

    /**
     * @return the planDeCuentaExistentesList
     */
    public List<PlanDeCuenta> getPlanDeCuentaExistentesList() {
        return planDeCuentaExistentesList;
    }

    /**
     * @param planDeCuentaExistentesList the planDeCuentaExistentesList to set
     */
    public void setPlanDeCuentaExistentesList(List<PlanDeCuenta> planDeCuentaExistentesList) {
        this.planDeCuentaExistentesList = planDeCuentaExistentesList;
    }

    /**
     * @return the planDeCuentaAsignadosList
     */
    public List<PlanDeCuentaIfip> getPlanDeCuentaAsignadosNoEliminadosList() {
        return planDeCuentaAsignadosNoEliminadosList;
    }

    /*
    
     */
    public void setPlanDeCuentaAsignadosNoEliminadosList(List<PlanDeCuentaIfip> planDeCuentaAsignadosNoEliminadosList) {
        this.planDeCuentaAsignadosNoEliminadosList = planDeCuentaAsignadosNoEliminadosList;
    }

    /**
     * @return the planDeCuentaNoAsignadosList
     */
    public List<PlanDeCuentaIfip> getPlanDeCuentaNoAsignadosList() {
        return planDeCuentaNoAsignadosList;
    }

    /**
     * @param planDeCuentaNoAsignadosList the planDeCuentaNoAsignadosList to set
     */
    public void setPlanDeCuentaNoAsignadosList(List<PlanDeCuentaIfip> planDeCuentaNoAsignadosList) {
        this.planDeCuentaNoAsignadosList = planDeCuentaNoAsignadosList;
    }

    /**
     * @return the ejbFacadePlanDeCuenta
     */
    public PlanDeCuentaFacade getEjbFacadePlanDeCuenta() {
        return ejbFacadePlanDeCuenta;
    }

    /**
     * @param ejbFacadePlanDeCuenta the ejbFacadePlanDeCuenta to set
     */
    public void setEjbFacadePlanDeCuenta(PlanDeCuentaFacade ejbFacadePlanDeCuenta) {
        this.ejbFacadePlanDeCuenta = ejbFacadePlanDeCuenta;
    }

    /**
     * @return the planDeCuentaAsignadosEliminadosList
     */
    public List<PlanDeCuentaIfip> getPlanDeCuentaAsignadosEliminadosList() {
        return planDeCuentaAsignadosEliminadosList;
    }

    /**
     * @param planDeCuentaAsignadosEliminadosList the
     * planDeCuentaAsignadosEliminadosList to set
     */
    public void setPlanDeCuentaAsignadosEliminadosList(List<PlanDeCuentaIfip> planDeCuentaAsignadosEliminadosList) {
        this.planDeCuentaAsignadosEliminadosList = planDeCuentaAsignadosEliminadosList;
    }

    /**
     * @return the planDeCuentaAsignadosList
     */
    public List<PlanDeCuentaIfip> getPlanDeCuentaAsignadosList() {
        return planDeCuentaAsignadosList;
    }

    /**
     * @param planDeCuentaAsignadosList the planDeCuentaAsignadosList to set
     */
    public void setPlanDeCuentaAsignadosList(List<PlanDeCuentaIfip> planDeCuentaAsignadosList) {
        this.planDeCuentaAsignadosList = planDeCuentaAsignadosList;
    }

    /**
     * @return the itemsPlanDeCuenta
     */
    public DualListModel<PlanDeCuenta> getItemsPlanDeCuenta() {
        return itemsPlanDeCuenta;
    }

    /**
     * @param itemsPlanDeCuenta the itemsPlanDeCuenta to set
     */
    public void setItemsPlanDeCuenta(DualListModel<PlanDeCuenta> itemsPlanDeCuenta) {
        this.itemsPlanDeCuenta = itemsPlanDeCuenta;
    }

    /**
     * @return the grupo
     */
    public GrupoCuentaContable getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(GrupoCuentaContable grupo) {
        this.grupo = grupo;
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
     * @return the itemsGrupoCuentaContable
     */
    public List<GrupoCuentaContable> getItemsGrupoCuentaContable() {
        return itemsGrupoCuentaContable;
    }

    /**
     * @param itemsGrupoCuentaContable the itemsGrupoCuentaContable to set
     */
    public void setItemsGrupoCuentaContable(List<GrupoCuentaContable> itemsGrupoCuentaContable) {
        this.itemsGrupoCuentaContable = itemsGrupoCuentaContable;
    }

    /**
     * @return the nivel
     */
    public short getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the fechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
