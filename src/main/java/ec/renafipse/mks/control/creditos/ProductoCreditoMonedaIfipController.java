package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.seguridades.UsuarioController;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaIfipFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "productoCreditoMonedaIfipController")
@ViewScoped
public class ProductoCreditoMonedaIfipController extends AbstractController<ProductoCreditoMonedaIfip> {

    @EJB
    private ProductoCreditoMonedaIfipFacade ejbFacade;
    private ProductoCreditoMonedaController productoCreditoMonedaController;
    private UsuarioController usuarioRegistradoPorController;

    /**
     * Initialize the concrete ProductoCreditoMonedaIfip controller bean. The
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
        productoCreditoMonedaController = context.getApplication().evaluateExpressionGet(context, "#{productoCreditoMonedaController}", ProductoCreditoMonedaController.class);
        usuarioRegistradoPorController = context.getApplication().evaluateExpressionGet(context, "#{usuarioController}", UsuarioController.class);
    }

    public ProductoCreditoMonedaIfipController() {
        // Inform the Abstract parent controller of the concrete ProductoCreditoMonedaIfip?cap_first Entity
        super(ProductoCreditoMonedaIfip.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProductoCreditoMonedaIfipPK().setCodigoProducto(this.getSelected().getProductoCreditoMoneda().getProductoCreditoMonedaPK().getCodigoMoneda());
        this.getSelected().getProductoCreditoMonedaIfipPK().setCodigoMoneda(this.getSelected().getProductoCreditoMoneda().getProductoCreditoMonedaPK().getCodigoProducto());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setProductoCreditoMonedaIfipPK(new ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfipPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        productoCreditoMonedaController.setSelected(null);
        usuarioRegistradoPorController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of PeriodicidadProMonIfi
     * entities that are retrieved from ProductoCreditoMonedaIfip?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for PeriodicidadProMonIfi page
     */
    public String navigatePeriodicidadProMonIfiCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PeriodicidadProMonIfi_items", this.getSelected().getPeriodicidadProMonIfiCollection());
        }
        return "/periodicidadProMonIfi/index";
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from ProductoCreditoMonedaIfip?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", this.getSelected().getSolicitudCollection());
        }
        return "/solicitud/index";
    }

    /**
     * Sets the "items" attribute with a collection of TasaInteresProCreMonIfi
     * entities that are retrieved from ProductoCreditoMonedaIfip?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for TasaInteresProCreMonIfi page
     */
    public String navigateTasaInteresProCreMonIfiCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TasaInteresProCreMonIfi_items", this.getSelected().getTasaInteresProCreMonIfiCollection());
        }
        return "/tasaInteresProCreMonIfi/index";
    }

    /**
     * Sets the "items" attribute with a collection of TipoRubroProCreMonIfi
     * entities that are retrieved from ProductoCreditoMonedaIfip?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for TipoRubroProCreMonIfi page
     */
    public String navigateTipoRubroProCreMonIfiCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoRubroProCreMonIfi_items", this.getSelected().getTipoRubroProCreMonIfiCollection());
        }
        return "/tipoRubroProCreMonIfi/index";
    }

    /**
     * Sets the "selected" attribute of the ProductoCreditoMoneda controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductoCreditoMoneda(ActionEvent event) {
        if (this.getSelected() != null && productoCreditoMonedaController.getSelected() == null) {
            productoCreditoMonedaController.setSelected(this.getSelected().getProductoCreditoMoneda());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioRegistradoPor(ActionEvent event) {
        if (this.getSelected() != null && usuarioRegistradoPorController.getSelected() == null) {
            usuarioRegistradoPorController.setSelected(this.getSelected().getUsuarioRegistradoPor());
        }
    }
}
