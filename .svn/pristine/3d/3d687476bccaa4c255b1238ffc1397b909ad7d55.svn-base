package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaInstitucionRep;
import ec.renafipse.mks.negocio.socios.PersonaInstitucionRepFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaInstitucionRepController")
@SessionScoped
public class PersonaInstitucionRepController extends AbstractController<PersonaInstitucionRep> implements Serializable {

    @Inject
    private PersonaInstitucionRepFacade ejbFacade;

    public PersonaInstitucionRepController() {
        super(PersonaInstitucionRep.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPersonaInstitucionRepPK().setCodigoPersonaRep(this.getSelected().getPersona().getCodigo());
        this.getSelected().getPersonaInstitucionRepPK().setCodigoPersona(this.getSelected().getPersonaInstitucion().getCodigoPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPersonaInstitucionRepPK(new ec.renafipse.mks.modelo.socios.PersonaInstitucionRepPK());
    }

}
