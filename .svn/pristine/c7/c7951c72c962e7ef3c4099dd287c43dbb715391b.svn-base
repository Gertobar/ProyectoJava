package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.TipoInstitucion;
import ec.renafipse.mks.negocio.socios.TipoInstitucionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "tipoInstitucionController")
@SessionScoped
public class TipoInstitucionController extends AbstractController<TipoInstitucion> implements Serializable {

    @Inject
    private TipoInstitucionFacade ejbFacade;

    public TipoInstitucionController() {
        super(TipoInstitucion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
