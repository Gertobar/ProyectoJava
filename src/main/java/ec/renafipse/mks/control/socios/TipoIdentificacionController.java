package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.negocio.socios.TipoIdentificacionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "tipoIdentificacionController")
@SessionScoped
public class TipoIdentificacionController extends AbstractController<TipoIdentificacion> implements Serializable {

    @Inject
    private TipoIdentificacionFacade ejbFacade;

    public TipoIdentificacionController() {
        super(TipoIdentificacion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
