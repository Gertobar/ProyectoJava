package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.ProcesoContableDetalle;
import ec.renafipse.mks.negocio.contables.ProcesoContableDetalleFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "procesoContableDetalleController")
@ViewScoped
public class ProcesoContableDetalleController extends AbstractController<ProcesoContableDetalle> {

    @EJB
    private ProcesoContableDetalleFacade ejbFacade;
    private ProcesoContableController procesoContableController;
    private PlanDeCuentaController planDeCuentaController;

    /**
     * Initialize the concrete ProcesoContableDetalle controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
        procesoContableController = context.getApplication().evaluateExpressionGet(context, "#{procesoContableController}", ProcesoContableController.class);
        planDeCuentaController = context.getApplication().evaluateExpressionGet(context, "#{planDeCuentaController}", PlanDeCuentaController.class);
    }

    public ProcesoContableDetalleController() {
        // Inform the Abstract parent controller of the concrete ProcesoContableDetalle?cap_first Entity
        super(ProcesoContableDetalle.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProcesoContableDetallePK().setCodigoProceso(this.getSelected().getProcesoContable().getCodigo());
        this.getSelected().getProcesoContableDetallePK().setCuentaContable(this.getSelected().getPlanDeCuenta().getPlanDeCuentaPK().getCuentaContable());
        this.getSelected().getProcesoContableDetallePK().setCodigoTipoPlan(this.getSelected().getPlanDeCuenta().getPlanDeCuentaPK().getCodigoTipoPlan());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setProcesoContableDetallePK(new ec.renafipse.mks.modelo.contables.ProcesoContableDetallePK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        procesoContableController.setSelected(null);
        planDeCuentaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of RegistroContable entities
     * that are retrieved from ProcesoContableDetalle?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RegistroContable page
     */
    public String navigateRegistroContableCollection() {
      
        return "/registroContable/index";
    }

    /**
     * Sets the "selected" attribute of the ProcesoContable controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProcesoContable(ActionEvent event) {
        if (this.getSelected() != null && procesoContableController.getSelected() == null) {
            procesoContableController.setSelected(this.getSelected().getProcesoContable());
        }
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
}
