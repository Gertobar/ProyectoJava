package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.RegistroContableDetalle;
import ec.renafipse.mks.negocio.contables.RegistroContableDetalleFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "registroContableDetalleController")
@ViewScoped
public class RegistroContableDetalleController extends AbstractController<RegistroContableDetalle> {

    @EJB
    private RegistroContableDetalleFacade ejbFacade;
    private RegistroContableController registroContableController;
    private PlanDeCuentaIfipController planDeCuentaIfipController;

    /**
     * Initialize the concrete RegistroContableDetalle controller bean. The
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
        registroContableController = context.getApplication().evaluateExpressionGet(context, "#{registroContableController}", RegistroContableController.class);
        planDeCuentaIfipController = context.getApplication().evaluateExpressionGet(context, "#{planDeCuentaIfipController}", PlanDeCuentaIfipController.class);
    }

    public RegistroContableDetalleController() {
        // Inform the Abstract parent controller of the concrete RegistroContableDetalle?cap_first Entity
        super(RegistroContableDetalle.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getRegistroContableDetallePK().setCodigoRegistro(this.getSelected().getRegistroContable().getCodigo());
        this.getSelected().getRegistroContableDetallePK().setCuentaContable(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCuentaContable());
        this.getSelected().getRegistroContableDetallePK().setCodigoTipoPlan(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCodigoTipoPlan());
        this.getSelected().getRegistroContableDetallePK().setCodigoIfip(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCodigoIfip());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setRegistroContableDetallePK(new ec.renafipse.mks.modelo.contables.RegistroContableDetallePK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        planDeCuentaIfipController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the RegistroContable controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroContable(ActionEvent event) {
    }

    /**
     * Sets the "selected" attribute of the PlanDeCuentaIfip controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePlanDeCuentaIfip(ActionEvent event) {
        if (this.getSelected() != null && planDeCuentaIfipController.getSelected() == null) {
            planDeCuentaIfipController.setSelected(this.getSelected().getPlanDeCuentaIfip());
        }
    }
}
