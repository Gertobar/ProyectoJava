package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import ec.renafipse.mks.negocio.creditos.EstadoCreditoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "estadoCreditoController")
@ViewScoped
public class EstadoCreditoController extends AbstractController<EstadoCredito> {

    @EJB
    private EstadoCreditoFacade ejbFacade;
    private EstadoCreditoController codigoEstadoDependienteController;

    /**
     * Initialize the concrete EstadoCredito controller bean. The
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
        codigoEstadoDependienteController = context.getApplication().evaluateExpressionGet(context, "#{estadoCreditoController}", EstadoCreditoController.class);
    }

    public EstadoCreditoController() {
        // Inform the Abstract parent controller of the concrete EstadoCredito?cap_first Entity
        super(EstadoCredito.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoEstadoDependienteController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of EstadoCredito entities
     * that are retrieved from EstadoCredito?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for EstadoCredito page
     */
    public String navigateEstadoCreditoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstadoCredito_items", this.getSelected().getEstadoCreditoCollection());
        }
        return "/estadoCredito/index";
    }

    /**
     * Sets the "selected" attribute of the EstadoCredito controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoEstadoDependiente(ActionEvent event) {
        if (this.getSelected() != null && codigoEstadoDependienteController.getSelected() == null) {
            codigoEstadoDependienteController.setSelected(this.getSelected().getCodigoEstadoDependiente());
        }
    }
}
