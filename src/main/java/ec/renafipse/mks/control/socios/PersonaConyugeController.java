package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.negocio.socios.PersonaConyugeFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaConyugeController")
@SessionScoped
public class PersonaConyugeController extends AbstractController<PersonaConyuge> implements Serializable {

    @EJB
    private PersonaConyugeFacade ejbFacade;

    public PersonaConyugeController() {
        super(PersonaConyuge.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
