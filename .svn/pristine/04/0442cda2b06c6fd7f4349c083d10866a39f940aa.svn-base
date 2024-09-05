package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.EstadoComprobante;
import ec.renafipse.mks.negocio.contables.EstadoComprobanteFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "estadoComprobanteController")
@ViewScoped
public class EstadoComprobanteController extends AbstractController<EstadoComprobante> {

    @EJB
    private EstadoComprobanteFacade ejbFacade;

    /**
     * Initialize the concrete EstadoComprobante controller bean. The
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

    public EstadoComprobanteController() {
        // Inform the Abstract parent controller of the concrete EstadoComprobante?cap_first Entity
        super(EstadoComprobante.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of
     * SeguimientoEstadoComprobante entities that are retrieved from
     * EstadoComprobante?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for SeguimientoEstadoComprobante page
     */
    public String navigateSeguimientoEstadoComprobanteCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SeguimientoEstadoComprobante_items", this.getSelected().getSeguimientoEstadoComprobanteCollection());
        }
        return "/seguimientoEstadoComprobante/index";
    }

    /**
     * Sets the "items" attribute with a collection of ProcesoContable entities
     * that are retrieved from EstadoComprobante?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for ProcesoContable page
     */
    public String navigateProcesoContableCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProcesoContable_items", this.getSelected().getProcesoContableCollection());
        }
        return "/procesoContable/index";
    }

    /**
     * Sets the "items" attribute with a collection of Comprobante entities that
     * are retrieved from EstadoComprobante?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Comprobante page
     */
    public String navigateComprobanteCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Comprobante_items", this.getSelected().getComprobanteCollection());
        }
        return "/comprobante/index";
    }

}
