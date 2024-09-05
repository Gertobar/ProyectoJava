package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.NivelInstruccion;
import ec.renafipse.mks.negocio.socios.NivelInstruccionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "nivelInstruccionController")
@SessionScoped
public class NivelInstruccionController extends AbstractController<NivelInstruccion> implements Serializable {

    @Inject
    private NivelInstruccionFacade ejbFacade;

    public NivelInstruccionController() {
        super(NivelInstruccion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
