package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.PersonaActividadEconomica;
import ec.renafipse.mks.negocio.socios.PersonaActividadEconomicaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "personaActividadEconomicaController")
@SessionScoped
public class PersonaActividadEconomicaController extends AbstractController<PersonaActividadEconomica> implements Serializable {

    @Inject
    private PersonaActividadEconomicaFacade ejbFacade;

    public PersonaActividadEconomicaController() {
        super(PersonaActividadEconomica.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPersonaActividadEconomicaPK().setCodigoActividadEconomica(this.getSelected().getActividadEconomica().getCodigo());
        this.getSelected().getPersonaActividadEconomicaPK().setCodigoPersona(this.getSelected().getPersona().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPersonaActividadEconomicaPK(new ec.renafipse.mks.modelo.socios.PersonaActividadEconomicaPK());
    }

}
