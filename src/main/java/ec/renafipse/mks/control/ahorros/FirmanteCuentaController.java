package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.negocio.ahorros.FirmanteCuentaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "firmanteCuentaController")
@ViewScoped
public class FirmanteCuentaController extends AbstractController<FirmanteCuenta> implements Serializable {

    @EJB
    private FirmanteCuentaFacade ejbFacade;

    public FirmanteCuentaController() {
        super(FirmanteCuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
       // this.getSelected().getFirmanteCuentaPK().setCodigoCuenta(this.getSelected().getCuenta().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setFirmanteCuentaPK(new ec.renafipse.mks.modelo.ahorros.FirmanteCuentaPK());
    }

}
