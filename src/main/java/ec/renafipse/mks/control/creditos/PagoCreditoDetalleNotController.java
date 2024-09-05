package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleNot;
import ec.renafipse.mks.negocio.creditos.PagoCreditoDetalleNotFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "pagoCreditoDetalleNotController")
@ViewScoped
public class PagoCreditoDetalleNotController extends AbstractController<PagoCreditoDetalleNot> {

    @EJB
    private PagoCreditoDetalleNotFacade ejbFacade;
    private PagoCreditoDetalleCuotaController pagoCreditoDetalleCuotaController;
    private NotificacionCreditoController notificacionCreditoController;

    /**
     * Initialize the concrete PagoCreditoDetalleNot controller bean. The
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
        pagoCreditoDetalleCuotaController = context.getApplication().evaluateExpressionGet(context, "#{pagoCreditoDetalleCuotaController}", PagoCreditoDetalleCuotaController.class);
        notificacionCreditoController = context.getApplication().evaluateExpressionGet(context, "#{notificacionCreditoController}", NotificacionCreditoController.class);
    }

    public PagoCreditoDetalleNotController() {
        // Inform the Abstract parent controller of the concrete PagoCreditoDetalleNot?cap_first Entity
        super(PagoCreditoDetalleNot.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPagoCreditoDetalleNotPK().setCodigoPagoCredito(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getCodigoPagoCredito());
        this.getSelected().getPagoCreditoDetalleNotPK().setNumeroCredito(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getNumeroCredito());
        this.getSelected().getPagoCreditoDetalleNotPK().setCodigoIfip(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getCodigoIfip());
        this.getSelected().getPagoCreditoDetalleNotPK().setCuota(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getCuota());
        this.getSelected().getPagoCreditoDetalleNotPK().setCodigoNotificacion(this.getSelected().getNotificacionCredito().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPagoCreditoDetalleNotPK(new ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleNotPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        pagoCreditoDetalleCuotaController.setSelected(null);
        notificacionCreditoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the PagoCreditoDetalleCuota controller
     * in order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePagoCreditoDetalleCuota(ActionEvent event) {
        if (this.getSelected() != null && pagoCreditoDetalleCuotaController.getSelected() == null) {
            pagoCreditoDetalleCuotaController.setSelected(this.getSelected().getPagoCreditoDetalleCuota());
        }
    }

    /**
     * Sets the "selected" attribute of the NotificacionCredito controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareNotificacionCredito(ActionEvent event) {
        if (this.getSelected() != null && notificacionCreditoController.getSelected() == null) {
            notificacionCreditoController.setSelected(this.getSelected().getNotificacionCredito());
        }
    }
}
