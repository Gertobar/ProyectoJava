package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.DestinoComputador;
import ec.renafipse.mks.negocio.ifips.DestinoComputadorFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "destinoComputadorController")
@ViewScoped
public class DestinoComputadorController extends AbstractController<DestinoComputador> implements Serializable {

    @EJB
    private DestinoComputadorFacade ejbFacade;

    public DestinoComputadorController() {
        super(DestinoComputador.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
