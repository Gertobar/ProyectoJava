package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaTelefono;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaTelefonoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ifipAgenciaTelefonoController")
@ViewScoped
public class IfipAgenciaTelefonoController extends AbstractController<IfipAgenciaTelefono> implements Serializable {

    @EJB
    private IfipAgenciaTelefonoFacade ejbFacade;

    public IfipAgenciaTelefonoController() {
        super(IfipAgenciaTelefono.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
