package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDetalle;
import ec.renafipse.mks.negocio.cajas.FondeoCajaDetalleFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "fondeoCajaDetalleController")
@ViewScoped
public class FondeoCajaDetalleController extends AbstractController<FondeoCajaDetalle> implements Serializable {

    @EJB
    private FondeoCajaDetalleFacade ejbFacade;

    public FondeoCajaDetalleController() {
        super(FondeoCajaDetalle.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getFondeoCajaDetallePK().setCodigoFondeo(this.getSelected().getFondeoCaja().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setFondeoCajaDetallePK(new ec.renafipse.mks.modelo.cajas.FondeoCajaDetallePK());
    }

}
