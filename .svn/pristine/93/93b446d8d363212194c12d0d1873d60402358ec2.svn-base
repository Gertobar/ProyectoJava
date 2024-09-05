package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.RegistroContableIngEgrCaj;
import ec.renafipse.mks.negocio.contables.RegistroContableIngEgrCajFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "registroContableIngEgrCajController")
@ViewScoped
public class RegistroContableIngEgrCajController extends AbstractController<RegistroContableIngEgrCaj> {

    @EJB
    private RegistroContableIngEgrCajFacade ejbFacade;
    private RegistroContableController codigoRegistroController;

    /**
     * Initialize the concrete RegistroContableIngEgrCaj controller bean. The
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
        codigoRegistroController = context.getApplication().evaluateExpressionGet(context, "#{registroContableController}", RegistroContableController.class);
    }

    public RegistroContableIngEgrCajController() {
        // Inform the Abstract parent controller of the concrete RegistroContableIngEgrCaj?cap_first Entity
        super(RegistroContableIngEgrCaj.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoRegistroController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the RegistroContable controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoRegistro(ActionEvent event) {
        if (this.getSelected() != null && codigoRegistroController.getSelected() == null) {
            codigoRegistroController.setSelected(this.getSelected().getCodigoRegistro());
        }
    }
}
