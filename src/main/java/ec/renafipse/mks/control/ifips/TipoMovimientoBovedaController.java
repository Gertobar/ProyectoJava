package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.TipoMovimientoBoveda;
import ec.renafipse.mks.negocio.ifips.TipoMovimientoBovedaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoMovimientoBovedaController")
@ViewScoped
public class TipoMovimientoBovedaController extends AbstractController<TipoMovimientoBoveda> implements Serializable {

    @EJB
    private TipoMovimientoBovedaFacade ejbFacade;

    public TipoMovimientoBovedaController() {
        super(TipoMovimientoBoveda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
