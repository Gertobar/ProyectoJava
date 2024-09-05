package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.ConocimientoIfip;
import ec.renafipse.mks.negocio.socios.ConocimientoIfipFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "conocimientoIfipController")
@SessionScoped
public class ConocimientoIfipController extends AbstractController<ConocimientoIfip> implements Serializable {

    @Inject
    private ConocimientoIfipFacade ejbFacade;

    public ConocimientoIfipController() {
        super(ConocimientoIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
