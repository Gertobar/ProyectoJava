package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.EfectivizacionChequeDetalle;
import ec.renafipse.mks.negocio.cajas.EfectivizacionChequeDetalleFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "efectivizacionChequeDetalleController")
@ViewScoped
public class EfectivizacionChequeDetalleController extends AbstractController<EfectivizacionChequeDetalle> implements Serializable {

    @EJB
    private EfectivizacionChequeDetalleFacade ejbFacade;

    public EfectivizacionChequeDetalleController() {
        super(EfectivizacionChequeDetalle.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getEfectivizacionChequeDetallePK().setCodigoEfeChe(this.getSelected().getEfectivizacionCheque().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setEfectivizacionChequeDetallePK(new ec.renafipse.mks.modelo.cajas.EfectivizacionChequeDetallePK());
    }

}
