package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.ahorros.MovimientoCuentaController;
import ec.renafipse.mks.modelo.creditos.PagoCreditoMovimientoCue;
import ec.renafipse.mks.negocio.creditos.PagoCreditoMovimientoCueFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "pagoCreditoMovimientoCueController")
@ViewScoped
public class PagoCreditoMovimientoCueController extends AbstractController<PagoCreditoMovimientoCue> {

    @EJB
    private PagoCreditoMovimientoCueFacade ejbFacade;
    private PagoCreditoController codigoPagoCreditoController;
    private MovimientoCuentaController movimientoCuentaController;

    /**
     * Initialize the concrete PagoCreditoMovimientoCue controller bean. The
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
        codigoPagoCreditoController = context.getApplication().evaluateExpressionGet(context, "#{pagoCreditoController}", PagoCreditoController.class);
        movimientoCuentaController = context.getApplication().evaluateExpressionGet(context, "#{movimientoCuentaController}", MovimientoCuentaController.class);
    }

    public PagoCreditoMovimientoCueController() {
        // Inform the Abstract parent controller of the concrete PagoCreditoMovimientoCue?cap_first Entity
        super(PagoCreditoMovimientoCue.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoPagoCreditoController.setSelected(null);
        movimientoCuentaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the PagoCredito controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoPagoCredito(ActionEvent event) {
        if (this.getSelected() != null && codigoPagoCreditoController.getSelected() == null) {
            codigoPagoCreditoController.setSelected(this.getSelected().getCodigoPagoCredito());
        }
    }

    /**
     * Sets the "selected" attribute of the MovimientoCuenta controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareMovimientoCuenta(ActionEvent event) {
        if (this.getSelected() != null && movimientoCuentaController.getSelected() == null) {
            movimientoCuentaController.setSelected(this.getSelected().getMovimientoCuenta());
        }
    }
}
