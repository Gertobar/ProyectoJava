package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaCuentaEntFin;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaCuentaEntFinFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ifipAgenciaCuentaEntFinController")
@ViewScoped
public class IfipAgenciaCuentaEntFinController extends AbstractController<IfipAgenciaCuentaEntFin> implements Serializable {

    @EJB
    private IfipAgenciaCuentaEntFinFacade ejbFacade;

    public IfipAgenciaCuentaEntFinController() {
        super(IfipAgenciaCuentaEntFin.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
