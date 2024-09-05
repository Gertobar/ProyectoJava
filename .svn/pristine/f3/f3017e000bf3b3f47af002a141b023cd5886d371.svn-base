package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaTelefono;
import ec.renafipse.mks.negocio.socios.PersonaTelefonoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaTelefonoController")
@SessionScoped
public class PersonaTelefonoController extends AbstractController<PersonaTelefono> implements Serializable {

    @Inject
    private PersonaTelefonoFacade ejbFacade;

    public PersonaTelefonoController() {
        super(PersonaTelefono.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
