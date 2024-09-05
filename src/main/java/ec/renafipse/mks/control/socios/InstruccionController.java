package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Instruccion;
import ec.renafipse.mks.negocio.socios.InstruccionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "instruccionController")
@SessionScoped
public class InstruccionController extends AbstractController<Instruccion> implements Serializable {

    @Inject
    private InstruccionFacade ejbFacade;

    public InstruccionController() {
        super(Instruccion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
