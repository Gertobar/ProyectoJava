package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.TipoComprobante;
import ec.renafipse.mks.negocio.contables.TipoComprobanteFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "tipoComprobanteController")
@ViewScoped
public class TipoComprobanteController extends AbstractController<TipoComprobante> {

    @EJB
    private TipoComprobanteFacade ejbFacade;

    /**
     * Initialize the concrete TipoComprobante controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
