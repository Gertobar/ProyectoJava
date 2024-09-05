package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Cargo;
import ec.renafipse.mks.negocio.socios.CargoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "cargoController")
@SessionScoped
public class CargoController extends AbstractController<Cargo> implements Serializable {

    @Inject
    private CargoFacade ejbFacade;

    public CargoController() {
        super(Cargo.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
