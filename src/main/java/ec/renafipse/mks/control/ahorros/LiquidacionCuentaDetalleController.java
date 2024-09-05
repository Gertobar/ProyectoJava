package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.LiquidacionCuentaDetalle;
import ec.renafipse.mks.negocio.ahorros.LiquidacionCuentaDetalleFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "liquidacionCuentaDetalleController")
@ViewScoped
public class LiquidacionCuentaDetalleController extends AbstractController<LiquidacionCuentaDetalle> implements Serializable {

    @EJB
    private LiquidacionCuentaDetalleFacade ejbFacade;

    public LiquidacionCuentaDetalleController() {
        super(LiquidacionCuentaDetalle.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getLiquidacionCuentaDetallePK().setCodigoLiqCta(this.getSelected().getLiquidacionCuentaCabecera().getCodigo());
        this.getSelected().getLiquidacionCuentaDetallePK().setCodigoCuenta(this.getSelected().getCuenta().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setLiquidacionCuentaDetallePK(new ec.renafipse.mks.modelo.ahorros.LiquidacionCuentaDetallePK());
    }

}
