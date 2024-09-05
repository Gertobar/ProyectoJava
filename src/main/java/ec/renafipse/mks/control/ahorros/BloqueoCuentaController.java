package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.BloqueoCuenta;
import ec.renafipse.mks.negocio.ahorros.BloqueoCuentaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "bloqueoCuentaController")
@ViewScoped
public class BloqueoCuentaController extends AbstractController<BloqueoCuenta> implements Serializable {

    @EJB
    private BloqueoCuentaFacade ejbFacade;

    public BloqueoCuentaController() {
        super(BloqueoCuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getBloqueoCuentaPK().setCodigoTipoBloqueo(this.getSelected().getTipoBloqueoCuenta().getCodigo());
        this.getSelected().getBloqueoCuentaPK().setCodigoCuenta(this.getSelected().getCuenta().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setBloqueoCuentaPK(new ec.renafipse.mks.modelo.ahorros.BloqueoCuentaPK());
    }

}
