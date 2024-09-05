package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.cajas.IngresoEgresoCajaController;
import ec.renafipse.mks.modelo.creditos.PagoCreditoIngEgrCaj;
import ec.renafipse.mks.negocio.creditos.PagoCreditoIngEgrCajFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "pagoCreditoIngEgrCajController")
@ViewScoped
public class PagoCreditoIngEgrCajController extends AbstractController<PagoCreditoIngEgrCaj> {

    @EJB
    private PagoCreditoIngEgrCajFacade ejbFacade;
    private PagoCreditoController codigoPagoCreditoController;
    private IngresoEgresoCajaController ingresoEgresoCajaController;

    /**
     * Initialize the concrete PagoCreditoIngEgrCaj controller bean. The
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
        codigoPagoCreditoController = context.getApplication().evaluateExpressionGet(context, "#{pagoCreditoController}", PagoCreditoController.class);
        ingresoEgresoCajaController = context.getApplication().evaluateExpressionGet(context, "#{ingresoEgresoCajaController}", IngresoEgresoCajaController.class);
    }

    public PagoCreditoIngEgrCajController() {
        // Inform the Abstract parent controller of the concrete PagoCreditoIngEgrCaj?cap_first Entity
        super(PagoCreditoIngEgrCaj.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoPagoCreditoController.setSelected(null);
        ingresoEgresoCajaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the PagoCredito controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoPagoCredito(ActionEvent event) {
        if (this.getSelected() != null && codigoPagoCreditoController.getSelected() == null) {
            codigoPagoCreditoController.setSelected(this.getSelected().getCodigoPagoCredito());
        }
    }

    /**
     * Sets the "selected" attribute of the IngresoEgresoCaja controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIngresoEgresoCaja(ActionEvent event) {
        if (this.getSelected() != null && ingresoEgresoCajaController.getSelected() == null) {
            ingresoEgresoCajaController.setSelected(this.getSelected().getIngresoEgresoCaja());
        }
    }
}
