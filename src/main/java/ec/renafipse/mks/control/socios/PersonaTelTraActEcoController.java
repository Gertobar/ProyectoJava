package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaTelTraActEco;
import ec.renafipse.mks.negocio.socios.PersonaTelTraActEcoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaTelTraActEcoController")
@SessionScoped
public class PersonaTelTraActEcoController extends AbstractController<PersonaTelTraActEco> implements Serializable {

    @Inject
    private PersonaTelTraActEcoFacade ejbFacade;

    public PersonaTelTraActEcoController() {
        super(PersonaTelTraActEco.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPersonaTelTraActEcoPK().setCodigoPerTraActEco(this.getSelected().getPersonaTrabajoActEco().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPersonaTelTraActEcoPK(new ec.renafipse.mks.modelo.socios.PersonaTelTraActEcoPK());
    }

}
