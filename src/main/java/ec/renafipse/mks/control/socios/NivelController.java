package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Nivel;
import ec.renafipse.mks.negocio.socios.NivelFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "nivelController")
@SessionScoped
public class NivelController extends AbstractController<Nivel> implements Serializable {

    @Inject
    private NivelFacade ejbFacade;

    public NivelController() {
        super(Nivel.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
