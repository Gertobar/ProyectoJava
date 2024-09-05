package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.negocio.ahorros.TransaccionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "transaccionController")
@ViewScoped
public class TransaccionController extends AbstractController<Transaccion> implements Serializable {

    @EJB
    private TransaccionFacade ejbFacade;

    public TransaccionController() {
        super(Transaccion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
