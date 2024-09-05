package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaResidencia;
import ec.renafipse.mks.negocio.socios.PersonaResidenciaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaResidenciaController")
@SessionScoped
public class PersonaResidenciaController extends AbstractController<PersonaResidencia> implements Serializable {

    @Inject
    private PersonaResidenciaFacade ejbFacade;

    public PersonaResidenciaController() {
        super(PersonaResidencia.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
