package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaVinculado;
import ec.renafipse.mks.modelo.socios.PersonaVinculadoPK;
import ec.renafipse.mks.negocio.socios.PersonaVinculadoFacade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named("personaVinculadoController")
@SessionScoped
public class PersonaVinculadoController extends AbstractController<PersonaVinculado> implements Serializable {
  
    @Inject
    private PersonaVinculadoFacade ejbFacade;

    public PersonaVinculadoController() {
        super(PersonaVinculado.class);
    }
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPersonaVinculadoPK(new PersonaVinculadoPK());
    }
   
}
