package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
import ec.renafipse.mks.negocio.comunes.TipoTelefonoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoTelefonoController")
@ViewScoped
public class TipoTelefonoController extends AbstractController<TipoTelefono> implements Serializable {

    @EJB
    private TipoTelefonoFacade ejbFacade;

    public TipoTelefonoController() {
        super(TipoTelefono.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
