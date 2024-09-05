package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.ComprobanteDetalle;
import ec.renafipse.mks.negocio.contables.ComprobanteDetalleFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "comprobanteDetalleController")
@ViewScoped
public class ComprobanteDetalleController extends AbstractController<ComprobanteDetalle> {

    @EJB
    private ComprobanteDetalleFacade ejbFacade;
    private PlanDeCuentaIfipController planDeCuentaIfipController;
    private ComprobanteController comprobanteController;

    /**
     * Initialize the concrete ComprobanteDetalle controller bean. The
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
        planDeCuentaIfipController = context.getApplication().evaluateExpressionGet(context, "#{planDeCuentaIfipController}", PlanDeCuentaIfipController.class);
        comprobanteController = context.getApplication().evaluateExpressionGet(context, "#{comprobanteController}", ComprobanteController.class);
    }

    public ComprobanteDetalleController() {
        // Inform the Abstract parent controller of the concrete ComprobanteDetalle?cap_first Entity
        super(ComprobanteDetalle.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getComprobanteDetallePK().setCodigoComprobante(this.getSelected().getComprobante().getCodigo());
        this.getSelected().getComprobanteDetallePK().setCuentaContable(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCuentaContable());
        this.getSelected().getComprobanteDetallePK().setCodigoTipoPlan(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCodigoTipoPlan());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setComprobanteDetallePK(new ec.renafipse.mks.modelo.contables.ComprobanteDetallePK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
       
    }

    /**
     * Sets the "selected" attribute of the PlanDeCuentaIfip controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePlanDeCuentaIfip(ActionEvent event) {
        
    }

    /**
     * Sets the "selected" attribute of the Comprobante controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareComprobante(ActionEvent event) {
        if (this.getSelected() != null && comprobanteController.getSelected() == null) {
            comprobanteController.setSelected(this.getSelected().getComprobante());
        }
    }
}
