package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.ReferenciaPersonal;
import ec.renafipse.mks.negocio.socios.ReferenciaPersonalFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "referenciaPersonalController")
@SessionScoped
public class ReferenciaPersonalController extends AbstractController<ReferenciaPersonal> implements Serializable {

    @Inject
    private ReferenciaPersonalFacade ejbFacade;

    public ReferenciaPersonalController() {
        super(ReferenciaPersonal.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
