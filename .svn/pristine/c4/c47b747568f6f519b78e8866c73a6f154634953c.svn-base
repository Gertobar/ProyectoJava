package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.dpfs.RangoDiasDpf;
import ec.renafipse.mks.negocio.dpfs.RangoDiasDpfFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "rangoDiasDpfController")
@ViewScoped
public class RangoDiasDpfController extends AbstractController<RangoDiasDpf> {

    @EJB
    private RangoDiasDpfFacade ejbFacade;

    /**
     * Initialize the concrete RangoDiasDpf controller bean. The
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
