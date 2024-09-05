package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.negocio.comunes.UbicacionGeograficaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ubicacionGeograficaController")
@ViewScoped
public class UbicacionGeograficaController extends AbstractController<UbicacionGeografica> implements Serializable {

    @EJB
    private UbicacionGeograficaFacade ejbFacade;

    public UbicacionGeograficaController() {
        super(UbicacionGeografica.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
