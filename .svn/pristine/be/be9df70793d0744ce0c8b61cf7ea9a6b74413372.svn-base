package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaAdicionalFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "movimientoCuentaAdicionalController")
@ViewScoped
public class MovimientoCuentaAdicionalController extends AbstractController<MovimientoCuentaAdicional> implements Serializable {

    @EJB
    private MovimientoCuentaAdicionalFacade ejbFacade;

    public MovimientoCuentaAdicionalController() {
        super(MovimientoCuentaAdicional.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
