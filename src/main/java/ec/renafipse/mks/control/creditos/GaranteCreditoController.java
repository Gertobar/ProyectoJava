package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.socios.PersonaNaturalController;
import ec.renafipse.mks.modelo.creditos.GaranteCredito;
import ec.renafipse.mks.negocio.creditos.GaranteCreditoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "garanteCreditoController")
@ViewScoped
public class GaranteCreditoController extends AbstractController<GaranteCredito> {

    @EJB
    private GaranteCreditoFacade ejbFacade;
    private TipoGarantiaCreditoController tipoGarantiaCreditoController;
    private PersonaNaturalController personaNaturalController;

    /**
     * Initialize the concrete GaranteCredito controller bean. The
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
        tipoGarantiaCreditoController = context.getApplication().evaluateExpressionGet(context, "#{tipoGarantiaCreditoController}", TipoGarantiaCreditoController.class);
        personaNaturalController = context.getApplication().evaluateExpressionGet(context, "#{personaNaturalController}", PersonaNaturalController.class);
    }

    public GaranteCreditoController() {
        // Inform the Abstract parent controller of the concrete GaranteCredito?cap_first Entity
        super(GaranteCredito.class);
    }

}
