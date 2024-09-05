package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleCuota;
import ec.renafipse.mks.negocio.creditos.PagoCreditoDetalleCuotaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "pagoCreditoDetalleCuotaController")
@ViewScoped
public class PagoCreditoDetalleCuotaController extends AbstractController<PagoCreditoDetalleCuota> {

    @EJB
    private PagoCreditoDetalleCuotaFacade ejbFacade;
    private PagoCreditoController pagoCreditoController;
    private CalculoCuotaPagarController calculoCuotaPagarController;

    /**
     * Initialize the concrete PagoCreditoDetalleCuota controller bean. The
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
        pagoCreditoController = context.getApplication().evaluateExpressionGet(context, "#{pagoCreditoController}", PagoCreditoController.class);
        calculoCuotaPagarController = context.getApplication().evaluateExpressionGet(context, "#{calculoCuotaPagarController}", CalculoCuotaPagarController.class);
    }

    public PagoCreditoDetalleCuotaController() {
        // Inform the Abstract parent controller of the concrete PagoCreditoDetalleCuota?cap_first Entity
        super(PagoCreditoDetalleCuota.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPagoCreditoDetalleCuotaPK().setCodigoPagoCredito(this.getSelected().getPagoCredito().getCodigo());
        this.getSelected().getPagoCreditoDetalleCuotaPK().setNumeroCredito(this.getSelected().getCalculoCuotaPagar().getCalculoCuotaPagarPK().getNumeroCredito());
        this.getSelected().getPagoCreditoDetalleCuotaPK().setCodigoIfip(this.getSelected().getCalculoCuotaPagar().getCalculoCuotaPagarPK().getCodigoIfip());
        this.getSelected().getPagoCreditoDetalleCuotaPK().setCuota(this.getSelected().getCalculoCuotaPagar().getCalculoCuotaPagarPK().getCuota());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPagoCreditoDetalleCuotaPK(new ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleCuotaPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        pagoCreditoController.setSelected(null);
        calculoCuotaPagarController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the PagoCredito controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePagoCredito(ActionEvent event) {
        if (this.getSelected() != null && pagoCreditoController.getSelected() == null) {
            pagoCreditoController.setSelected(this.getSelected().getPagoCredito());
        }
    }

    /**
     * Sets the "selected" attribute of the CalculoCuotaPagar controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalculoCuotaPagar(ActionEvent event) {
        if (this.getSelected() != null && calculoCuotaPagarController.getSelected() == null) {
            calculoCuotaPagarController.setSelected(this.getSelected().getCalculoCuotaPagar());
        }
    }
}
