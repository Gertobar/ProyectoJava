package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Profesion;
import ec.renafipse.mks.negocio.socios.ProfesionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "profesionController")
@SessionScoped
public class ProfesionController extends AbstractController<Profesion> implements Serializable {

    @Inject
    private ProfesionFacade ejbFacade;

    public ProfesionController() {
        super(Profesion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
