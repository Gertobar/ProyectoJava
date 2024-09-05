package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.AperturaDetalle;
import ec.renafipse.mks.negocio.cajas.AperturaDetalleFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "aperturaDetalleController")
@ViewScoped
public class AperturaDetalleController extends AbstractController<AperturaDetalle> implements Serializable {

    @EJB
    private AperturaDetalleFacade ejbFacade;

    public AperturaDetalleController() {
        super(AperturaDetalle.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getAperturaDetallePK().setCodigoApertura(this.getSelected().getApertura().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setAperturaDetallePK(new ec.renafipse.mks.modelo.cajas.AperturaDetallePK());
    }

}
