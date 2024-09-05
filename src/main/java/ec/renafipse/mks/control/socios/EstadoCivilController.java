package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.EstadoCivil;
import ec.renafipse.mks.negocio.socios.EstadoCivilFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "estadoCivilController")
@SessionScoped
public class EstadoCivilController extends AbstractController<EstadoCivil> implements Serializable {

    @Inject
    private EstadoCivilFacade ejbFacade;

    public EstadoCivilController() {
        super(EstadoCivil.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
