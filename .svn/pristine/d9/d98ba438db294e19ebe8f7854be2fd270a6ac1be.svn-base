package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.EstadoSocio;
import ec.renafipse.mks.negocio.socios.EstadoSocioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "estadoSocioController")
@SessionScoped
public class EstadoSocioController extends AbstractController<EstadoSocio> implements Serializable {

    @Inject
    private EstadoSocioFacade ejbFacade;

    public EstadoSocioController() {
        super(EstadoSocio.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
