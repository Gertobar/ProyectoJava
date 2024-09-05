package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.TipoComputador;
import ec.renafipse.mks.negocio.ifips.TipoComputadorFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoComputadorController")
@ViewScoped
public class TipoComputadorController extends AbstractController<TipoComputador> implements Serializable {

    @EJB
    private TipoComputadorFacade ejbFacade;

    public TipoComputadorController() {
        super(TipoComputador.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
