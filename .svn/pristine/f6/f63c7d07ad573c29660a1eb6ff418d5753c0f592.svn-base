package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.MotivoBloqueoCuenta;
import ec.renafipse.mks.negocio.seguridades.MotivoBloqueoCuentaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "motivoBloqueoCuentaController")
@ViewScoped
public class MotivoBloqueoCuentaController extends AbstractController<MotivoBloqueoCuenta> implements Serializable {

    @EJB
    private MotivoBloqueoCuentaFacade ejbFacade;

    public MotivoBloqueoCuentaController() {
        super(MotivoBloqueoCuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
