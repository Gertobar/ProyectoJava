package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.MotivoSocio;
import ec.renafipse.mks.negocio.socios.MotivoSocioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "motivoSocioController")
@SessionScoped
public class MotivoSocioController extends AbstractController<MotivoSocio> implements Serializable {

    @Inject
    private MotivoSocioFacade ejbFacade;

    public MotivoSocioController() {
        super(MotivoSocio.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
