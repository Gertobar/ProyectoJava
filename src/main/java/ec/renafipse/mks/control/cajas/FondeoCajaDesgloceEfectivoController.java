package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivo;
import ec.renafipse.mks.negocio.cajas.FondeoCajaDesgloceEfectivoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "fondeoCajaDesgloceEfectivoController")
@ViewScoped
public class FondeoCajaDesgloceEfectivoController extends AbstractController<FondeoCajaDesgloceEfectivo> implements Serializable {

    @EJB
    private FondeoCajaDesgloceEfectivoFacade ejbFacade;

    public FondeoCajaDesgloceEfectivoController() {
        super(FondeoCajaDesgloceEfectivo.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getFondeoCajaDesgloceEfectivoPK().setCodigoMoneda(this.getSelected().getFondeoCajaDetalle().getFondeoCajaDetallePK().getCodigoMoneda());
        this.getSelected().getFondeoCajaDesgloceEfectivoPK().setCodigoFondeo(this.getSelected().getFondeoCajaDetalle().getFondeoCajaDetallePK().getCodigoFondeo());
        this.getSelected().getFondeoCajaDesgloceEfectivoPK().setCodigoTipoFraccion(this.getSelected().getFraccionMoneda().getFraccionMonedaPK().getCodigoTipoFraccion());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setFondeoCajaDesgloceEfectivoPK(new ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivoPK());
    }

}
