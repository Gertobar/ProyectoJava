package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.RegistroContable;
import ec.renafipse.mks.negocio.contables.RegistroContableFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "registroContableController")
@ViewScoped
public class RegistroContableController extends AbstractController<RegistroContable> {

    @EJB
    private RegistroContableFacade ejbFacade;
    private ProcesoContableController codigoProcesoController;

    /**
     * Initialize the concrete RegistroContable controller bean. The
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
        codigoProcesoController = context.getApplication().evaluateExpressionGet(context, "#{procesoContableController}", ProcesoContableController.class);
    }

    public RegistroContableController() {
        // Inform the Abstract parent controller of the concrete RegistroContable?cap_first Entity
        super(RegistroContable.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoProcesoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the ProcesoContable controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoProceso(ActionEvent event) {
        if (this.getSelected() != null && codigoProcesoController.getSelected() == null) {
            codigoProcesoController.setSelected(this.getSelected().getCodigoProceso());
        }
    }

    /**
     * Sets the "items" attribute with a collection of RegistroContableDetalle
     * entities that are retrieved from RegistroContable?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for RegistroContableDetalle page
     */
    public String navigateRegistroContableDetalleCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroContableDetalle_items", this.getSelected().getRegistroContableDetalleCollection());
        }
        return "/registroContableDetalle/index";
    }

    /**
     * Sets the "items" attribute with a collection of RegistroContableIngEgrCaj
     * entities that are retrieved from RegistroContable?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for RegistroContableIngEgrCaj page
     */
    public String navigateRegistroContableIngEgrCajCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroContableIngEgrCaj_items", this.getSelected().getRegistroContableIngEgrCajCollection());
        }
        return "/registroContableIngEgrCaj/index";
    }

    /**
     * Sets the "items" attribute with a collection of RegistroContableMovCue
     * entities that are retrieved from RegistroContable?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for RegistroContableMovCue page
     */
    public String navigateRegistroContableMovCueCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroContableMovCue_items", this.getSelected().getRegistroContableMovCueCollection());
        }
        return "/registroContableMovCue/index";
    }

}
