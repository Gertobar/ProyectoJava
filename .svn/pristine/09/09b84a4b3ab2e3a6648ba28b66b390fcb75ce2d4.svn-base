package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.CierreDetalle;
import ec.renafipse.mks.negocio.cajas.CierreDetalleFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "cierreDetalleController")
@ViewScoped
public class CierreDetalleController extends AbstractController<CierreDetalle> implements Serializable {

    @EJB
    private CierreDetalleFacade ejbFacade;

    public CierreDetalleController() {
        super(CierreDetalle.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getCierreDetallePK().setCodigoMoneda(this.getSelected().getAperturaDetalle().getAperturaDetallePK().getCodigoMoneda());
        this.getSelected().getCierreDetallePK().setCodigoApertura(this.getSelected().getAperturaDetalle().getAperturaDetallePK().getCodigoApertura());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setCierreDetallePK(new ec.renafipse.mks.modelo.cajas.CierreDetallePK());
    }

}
