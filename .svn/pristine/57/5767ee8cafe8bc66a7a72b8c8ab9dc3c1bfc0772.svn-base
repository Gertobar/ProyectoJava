package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.BovedaDinero;
import ec.renafipse.mks.negocio.ifips.BovedaDineroFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "bovedaDineroController")
@ViewScoped
public class BovedaDineroController extends AbstractController<BovedaDinero> implements Serializable {

    @EJB
    private BovedaDineroFacade ejbFacade;

    public BovedaDineroController() {
        super(BovedaDinero.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getBovedaDineroPK().setCodigoBoveda(this.getSelected().getBoveda().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setBovedaDineroPK(new ec.renafipse.mks.modelo.ifips.BovedaDineroPK());
    }

}
