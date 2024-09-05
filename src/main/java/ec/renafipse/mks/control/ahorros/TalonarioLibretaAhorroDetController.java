package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroDet;
import ec.renafipse.mks.negocio.ahorros.TalonarioLibretaAhorroDetFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "talonarioLibretaAhorroDetController")
@ViewScoped
public class TalonarioLibretaAhorroDetController extends AbstractController<TalonarioLibretaAhorroDet> implements Serializable {

    @EJB
    private TalonarioLibretaAhorroDetFacade ejbFacade;

    public TalonarioLibretaAhorroDetController() {
        super(TalonarioLibretaAhorroDet.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
