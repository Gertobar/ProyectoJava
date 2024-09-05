package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.ClasePersona;
import ec.renafipse.mks.negocio.socios.ClasePersonaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "clasePersonaController")
@SessionScoped
public class ClasePersonaController extends AbstractController<ClasePersona> implements Serializable {

    @Inject
    private ClasePersonaFacade ejbFacade;

    public ClasePersonaController() {
        super(ClasePersona.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
