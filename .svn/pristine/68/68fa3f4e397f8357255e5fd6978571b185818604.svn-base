package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.dpfs.MotivoPrecancelacion;
import ec.renafipse.mks.negocio.dpfs.MotivoPrecancelacionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "motivoPrecancelacionController")
@SessionScoped
public class MotivoPrecancelacionController extends AbstractController<MotivoPrecancelacion> implements Serializable {

    @Inject
    private MotivoPrecancelacionFacade ejbFacade;

    public MotivoPrecancelacionController() {
        super(MotivoPrecancelacion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
