package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaActEcoPri;
import ec.renafipse.mks.negocio.socios.PersonaActEcoPriFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaActEcoPriController")
@SessionScoped
public class PersonaActEcoPriController extends AbstractController<PersonaActEcoPri> implements Serializable {

    @Inject
    private PersonaActEcoPriFacade ejbFacade;

    public PersonaActEcoPriController() {
        super(PersonaActEcoPri.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
