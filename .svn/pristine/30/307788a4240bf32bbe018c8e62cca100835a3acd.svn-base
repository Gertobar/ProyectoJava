package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.SeguimientoEstadoComprobante;
import ec.renafipse.mks.negocio.contables.SeguimientoEstadoComprobanteFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "seguimientoEstadoComprobanteController")
@ViewScoped
public class SeguimientoEstadoComprobanteController extends AbstractController<SeguimientoEstadoComprobante> {

    @EJB
    private SeguimientoEstadoComprobanteFacade ejbFacade;
    private EstadoComprobanteController codigoEstadoController;
    private ComprobanteController codigoComprobanteController;

    /**
     * Initialize the concrete SeguimientoEstadoComprobante controller bean. The
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
        codigoEstadoController = context.getApplication().evaluateExpressionGet(context, "#{estadoComprobanteController}", EstadoComprobanteController.class);
        codigoComprobanteController = context.getApplication().evaluateExpressionGet(context, "#{comprobanteController}", ComprobanteController.class);
    }

    public SeguimientoEstadoComprobanteController() {
        // Inform the Abstract parent controller of the concrete SeguimientoEstadoComprobante?cap_first Entity
        super(SeguimientoEstadoComprobante.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoEstadoController.setSelected(null);
        codigoComprobanteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the EstadoComprobante controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoEstado(ActionEvent event) {
        if (this.getSelected() != null && codigoEstadoController.getSelected() == null) {
            codigoEstadoController.setSelected(this.getSelected().getCodigoEstado());
        }
    }

    /**
     * Sets the "selected" attribute of the Comprobante controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoComprobante(ActionEvent event) {
        if (this.getSelected() != null && codigoComprobanteController.getSelected() == null) {
            codigoComprobanteController.setSelected(this.getSelected().getCodigoComprobante());
        }
    }
}
