package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import ec.renafipse.mks.negocio.socios.TipoRelacionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "tipoRelacionController")
@SessionScoped
public class TipoRelacionController extends AbstractController<TipoRelacion> implements Serializable {

    @Inject
    private TipoRelacionFacade ejbFacade;

    public TipoRelacionController() {
        super(TipoRelacion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
