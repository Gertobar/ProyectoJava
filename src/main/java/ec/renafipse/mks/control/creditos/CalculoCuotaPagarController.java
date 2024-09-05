package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.negocio.creditos.CalculoCuotaPagarFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "calculoCuotaPagarController")
@ViewScoped
public class CalculoCuotaPagarController extends AbstractController<CalculoCuotaPagar> {

    @EJB
    private CalculoCuotaPagarFacade ejbFacade;
    private TablaAmortizacionController tablaAmortizacionController;

    /**
     * Initialize the concrete CalculoCuotaPagar controller bean. The
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
        tablaAmortizacionController = context.getApplication().evaluateExpressionGet(context, "#{tablaAmortizacionController}", TablaAmortizacionController.class);
    }

    public CalculoCuotaPagarController() {
        // Inform the Abstract parent controller of the concrete CalculoCuotaPagar?cap_first Entity
        super(CalculoCuotaPagar.class);
    }

//    @Override
//    protected void setEmbeddableKeys() {
//        this.getSelected().getCalculoCuotaPagarPK().setNumeroCredito(this.getSelected().getTablaAmortizacion().getTablaAmortizacionPK().getNumeroCredito());
//        this.getSelected().getCalculoCuotaPagarPK().setCodigoIfip(this.getSelected().getTablaAmortizacion().getTablaAmortizacionPK().getCodigoIfip());
//        this.getSelected().getCalculoCuotaPagarPK().setCuota(this.getSelected().getTablaAmortizacion().getTablaAmortizacionPK().getCuota());
//    }
//
//    @Override
//    protected void initializeEmbeddableKey() {
//        this.getSelected().setCalculoCuotaPagarPK(new ec.renafipse.mks.modelo.creditos.CalculoCuotaPagarPK());
//    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "selected" attribute of the TablaAmortizacion controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTablaAmortizacion(ActionEvent event) {
        
    }

    /**
     * Sets the "items" attribute with a collection of PagoCreditoDetalleCuota
     * entities that are retrieved from CalculoCuotaPagar?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for PagoCreditoDetalleCuota page
     */
//    public String navigatePagoCreditoDetalleCuotaCollection() {
//        if (this.getSelected() != null) {
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PagoCreditoDetalleCuota_items", this.getSelected().getPagoCreditoDetalleCuotaCollection());
//        }
//        return "/pagoCreditoDetalleCuota/index";
//    }

}
