package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.dpfs.CuentaContratoDpf;
import ec.renafipse.mks.negocio.dpfs.CuentaContratoDpfFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "cuentaContratoDpfController")
@ViewScoped
public class CuentaContratoDpfController extends AbstractController<CuentaContratoDpf> {

    @EJB
    private CuentaContratoDpfFacade ejbFacade;

    /**
     * Initialize the concrete CuentaContratoDpf controller bean. The
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
