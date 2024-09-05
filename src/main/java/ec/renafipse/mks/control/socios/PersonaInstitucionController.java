package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaInstitucion;
import ec.renafipse.mks.negocio.socios.PersonaInstitucionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaInstitucionController")
@SessionScoped
public class PersonaInstitucionController extends AbstractController<PersonaInstitucion> implements Serializable {

    @Inject
    private PersonaInstitucionFacade ejbFacade;

    public PersonaInstitucionController() {
        super(PersonaInstitucion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
