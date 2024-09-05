package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMoneda;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "productoCreditoMonedaController")
@ViewScoped
public class ProductoCreditoMonedaController extends AbstractController<ProductoCreditoMoneda> {

    @EJB
    private ProductoCreditoMonedaFacade ejbFacade;
    private ProductoCreditoController productoCreditoController;

    /**
     * Initialize the concrete ProductoCreditoMoneda controller bean. The
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
        productoCreditoController = context.getApplication().evaluateExpressionGet(context, "#{productoCreditoController}", ProductoCreditoController.class);
        
    }

    public ProductoCreditoMonedaController() {
        // Inform the Abstract parent controller of the concrete ProductoCreditoMoneda?cap_first Entity
        super(ProductoCreditoMoneda.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProductoCreditoMonedaPK().setCodigoProducto(this.getSelected().getProductoCredito().getCodigo());
        this.getSelected().getProductoCreditoMonedaPK().setCodigoMoneda(this.getSelected().getMoneda().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setProductoCreditoMonedaPK(new ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        productoCreditoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of ProductoCreditoMonedaIfip
     * entities that are retrieved from ProductoCreditoMoneda?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for ProductoCreditoMonedaIfip page
     */
    public String navigateProductoCreditoMonedaIfipCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProductoCreditoMonedaIfip_items", this.getSelected().getProductoCreditoMonedaIfipCollection());
        }
        return "/productoCreditoMonedaIfip/index";
    }

    /**
     * Sets the "selected" attribute of the ProductoCredito controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductoCredito(ActionEvent event) {
        if (this.getSelected() != null && productoCreditoController.getSelected() == null) {
            productoCreditoController.setSelected(this.getSelected().getProductoCredito());
        }
    }

    /**
     * Sets the "selected" attribute of the Moneda controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareMoneda(ActionEvent event) {
    }
}
