package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "movimientoCuentaController")
@ViewScoped
public class MovimientoCuentaController extends AbstractController<MovimientoCuenta> implements Serializable {

    @EJB
    private MovimientoCuentaFacade ejbFacade;

    public MovimientoCuentaController() {
        super(MovimientoCuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
