package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.dpfs.TasaInteresProDpfMon;
import ec.renafipse.mks.negocio.dpfs.TasaInteresProDpfMonFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "tasaInteresProDpfMonController")
@ViewScoped
public class TasaInteresProDpfMonController extends AbstractController<TasaInteresProDpfMon> {

    @EJB
    private TasaInteresProDpfMonFacade ejbFacade;

    /**
     * Initialize the concrete TasaInteresProDpfMon controller bean. The
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
