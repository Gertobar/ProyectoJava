package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.ProtestoChequeDetalle;
import ec.renafipse.mks.negocio.cajas.ProtestoChequeDetalleFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "protestoChequeDetalleController")
@ViewScoped
public class ProtestoChequeDetalleController extends AbstractController<ProtestoChequeDetalle> implements Serializable {

    @EJB
    private ProtestoChequeDetalleFacade ejbFacade;

    public ProtestoChequeDetalleController() {
        super(ProtestoChequeDetalle.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProtestoChequeDetallePK().setCodigoProChe(this.getSelected().getProtestoCheque().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setProtestoChequeDetallePK(new ec.renafipse.mks.modelo.cajas.ProtestoChequeDetallePK());
    }

}
