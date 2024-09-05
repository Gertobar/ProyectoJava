package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta;
import ec.renafipse.mks.negocio.contables.TipoPlanDeCuentaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "tipoPlanDeCuentaController")
@ViewScoped
public class TipoPlanDeCuentaController extends AbstractController<TipoPlanDeCuenta> {

    @EJB
    private TipoPlanDeCuentaFacade ejbFacade;

    /**
     * Initialize the concrete TipoPlanDeCuenta controller bean. The
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
    }

    public TipoPlanDeCuentaController() {
        // Inform the Abstract parent controller of the concrete TipoPlanDeCuenta?cap_first Entity
        super(TipoPlanDeCuenta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of PlanDeCuenta entities
     * that are retrieved from TipoPlanDeCuenta?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for PlanDeCuenta page
     */
    public String navigatePlanDeCuentaCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PlanDeCuenta_items", this.getSelected().getPlanDeCuentaCollection());
        }
        return "/planDeCuenta/index";
    }

}
