package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.negocio.comunes.PeriodicidadFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "periodicidadController")
@ViewScoped
public class PeriodicidadController extends AbstractController<Periodicidad> implements Serializable {

    @EJB
    private PeriodicidadFacade ejbFacade;

    public PeriodicidadController() {
        super(Periodicidad.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
