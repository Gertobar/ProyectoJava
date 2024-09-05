package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.TipoIfip;
import ec.renafipse.mks.negocio.ifips.TipoIfipFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoIfipController")
@ViewScoped
public class TipoIfipController extends AbstractController<TipoIfip> implements Serializable {

    @EJB
    private TipoIfipFacade ejbFacade;

    public TipoIfipController() {
        super(TipoIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
