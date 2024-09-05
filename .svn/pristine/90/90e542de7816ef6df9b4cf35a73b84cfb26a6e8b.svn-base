package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.FuenteIngresos;
import ec.renafipse.mks.negocio.socios.FuenteIngresosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "fuenteIngresosController")
@SessionScoped
public class FuenteIngresosController extends AbstractController<FuenteIngresos> implements Serializable {

    @Inject
    private FuenteIngresosFacade ejbFacade;

    public FuenteIngresosController() {
        super(FuenteIngresos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
