package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.EstadoCuenta;
import ec.renafipse.mks.negocio.ahorros.EstadoCuentaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "estadoCuentaController")
@ViewScoped
public class EstadoCuentaController extends AbstractController<EstadoCuenta> implements Serializable {

    @EJB
    private EstadoCuentaFacade ejbFacade;

    public EstadoCuentaController() {
        super(EstadoCuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
