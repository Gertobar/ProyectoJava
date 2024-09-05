package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioController")
@SessionScoped
public class SocioController extends AbstractController<Socio> implements Serializable {

    @Inject
    private SocioFacade ejbFacade;

    public SocioController() {
        super(Socio.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setSocioPK(new ec.renafipse.mks.modelo.socios.SocioPK());
    }

}
