package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubro;
import ec.renafipse.mks.negocio.creditos.PagoCreditoDetalleRubroFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

@ManagedBean(name = "pagoCreditoDetalleRubroController")
@ViewScoped
public class PagoCreditoDetalleRubroController extends AbstractController<PagoCreditoDetalleRubro> {

    @EJB
    private PagoCreditoDetalleRubroFacade ejbFacade;

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);             
    }

    public PagoCreditoDetalleRubroController() {
        // Inform the Abstract parent controller of the concrete PagoCreditoDetalleRubro?cap_first Entity
        super(PagoCreditoDetalleRubro.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPagoCreditoDetalleRubroPK().setCodigoPagoCredito(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getCodigoPagoCredito());
        this.getSelected().getPagoCreditoDetalleRubroPK().setNumeroCredito(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getNumeroCredito());
        this.getSelected().getPagoCreditoDetalleRubroPK().setCodigoIfip(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getCodigoIfip());
        this.getSelected().getPagoCreditoDetalleRubroPK().setCuota(this.getSelected().getPagoCreditoDetalleCuota().getPagoCreditoDetalleCuotaPK().getCuota());
        this.getSelected().getPagoCreditoDetalleRubroPK().setCodigoRubro(this.getSelected().getRubroTablaAmortizacion().getRubroTablaAmortizacionPK().getCodigoRubro());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPagoCreditoDetalleRubroPK(new ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubroPK());
    }


}
