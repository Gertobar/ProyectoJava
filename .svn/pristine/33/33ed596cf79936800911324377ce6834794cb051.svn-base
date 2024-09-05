package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.ComprobanteDesgloceCheque;
import ec.renafipse.mks.negocio.contables.ComprobanteDesgloceChequeFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "comprobanteDesgloceChequeController")
@ViewScoped
public class ComprobanteDesgloceChequeController extends AbstractController<ComprobanteDesgloceCheque> {

    @EJB
    private ComprobanteDesgloceChequeFacade ejbFacade;
    private ComprobanteController comprobanteController;

    /**
     * Initialize the concrete ComprobanteDesgloceCheque controller bean. The
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
        comprobanteController = context.getApplication().evaluateExpressionGet(context, "#{comprobanteController}", ComprobanteController.class);
    }

    public ComprobanteDesgloceChequeController() {
        // Inform the Abstract parent controller of the concrete ComprobanteDesgloceCheque?cap_first Entity
        super(ComprobanteDesgloceCheque.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        comprobanteController.setSelected(null);
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
