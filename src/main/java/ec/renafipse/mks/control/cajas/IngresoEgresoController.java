package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.IngresoEgreso;
import ec.renafipse.mks.negocio.cajas.IngresoEgresoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ingresoEgresoController")
@ViewScoped
public class IngresoEgresoController extends AbstractController<IngresoEgreso> implements Serializable {

    @EJB
    private IngresoEgresoFacade ejbFacade;

    public IngresoEgresoController() {
        super(IngresoEgreso.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
