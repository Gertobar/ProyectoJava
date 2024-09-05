package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaTrabajoActEco;
import ec.renafipse.mks.negocio.socios.PersonaTrabajoActEcoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaTrabajoActEcoController")
@SessionScoped
public class PersonaTrabajoActEcoController extends AbstractController<PersonaTrabajoActEco> implements Serializable {

    @Inject
    private PersonaTrabajoActEcoFacade ejbFacade;

    public PersonaTrabajoActEcoController() {
        super(PersonaTrabajoActEco.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
