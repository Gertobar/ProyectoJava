package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.TipoBloqueoCuenta;
import ec.renafipse.mks.negocio.ahorros.TipoBloqueoCuentaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoBloqueoCuentaController")
@ViewScoped
public class TipoBloqueoCuentaController extends AbstractController<TipoBloqueoCuenta> implements Serializable {

    @EJB
    private TipoBloqueoCuentaFacade ejbFacade;

    public TipoBloqueoCuentaController() {
        super(TipoBloqueoCuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
