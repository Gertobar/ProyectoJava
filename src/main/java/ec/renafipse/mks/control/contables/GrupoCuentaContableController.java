package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.GrupoCuentaContable;
import ec.renafipse.mks.negocio.contables.GrupoCuentaContableFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "grupoCuentaContableController")
@ViewScoped
public class GrupoCuentaContableController extends AbstractController<GrupoCuentaContable> {

    @EJB
    private GrupoCuentaContableFacade ejbFacade;

    /**
     * Initialize the concrete GrupoCuentaContable controller bean. The
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

    public GrupoCuentaContableController() {
        // Inform the Abstract parent controller of the concrete GrupoCuentaContable?cap_first Entity
        super(GrupoCuentaContable.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of PlanDeCuenta entities
     * that are retrieved from GrupoCuentaContable?cap_first and returns the
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
