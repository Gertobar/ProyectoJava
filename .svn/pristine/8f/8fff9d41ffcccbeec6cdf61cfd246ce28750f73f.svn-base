package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "productoCreditoController")
@ViewScoped
public class ProductoCreditoController extends AbstractController<ProductoCredito> {

    @EJB
    private ProductoCreditoFacade ejbFacade;
    private TipoCarteraController codigoTipoCarteraController;

    /**
     * Initialize the concrete ProductoCredito controller bean. The
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
        codigoTipoCarteraController = context.getApplication().evaluateExpressionGet(context, "#{tipoCarteraController}", TipoCarteraController.class);
    }

    public ProductoCreditoController() {
        // Inform the Abstract parent controller of the concrete ProductoCredito?cap_first Entity
        super(ProductoCredito.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of ProductoCreditoMoneda
     * entities that are retrieved from ProductoCredito?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for ProductoCreditoMoneda page
     */
    public String navigateProductoCreditoMonedaCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProductoCreditoMoneda_items", this.getSelected().getProductoCreditoMonedaCollection());
        }
        return "/productoCreditoMoneda/index";
    }

    /**
     * Sets the "selected" attribute of the TipoCartera controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoTipoCartera(ActionEvent event) {
    }
}
