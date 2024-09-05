package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.RegistroContableMovCue;
import ec.renafipse.mks.negocio.contables.RegistroContableMovCueFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "registroContableMovCueController")
@ViewScoped
public class RegistroContableMovCueController extends AbstractController<RegistroContableMovCue> {

    @EJB
    private RegistroContableMovCueFacade ejbFacade;
    private RegistroContableController codigoRegistroController;

    /**
     * Initialize the concrete RegistroContableMovCue controller bean. The
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

    public RegistroContableMovCueController() {
        // Inform the Abstract parent controller of the concrete RegistroContableMovCue?cap_first Entity
        super(RegistroContableMovCue.class);
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
