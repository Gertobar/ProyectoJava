package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.CierreDesgloceEfectivo;
import ec.renafipse.mks.negocio.cajas.CierreDesgloceEfectivoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "cierreDesgloceEfectivoController")
@ViewScoped
public class CierreDesgloceEfectivoController extends AbstractController<CierreDesgloceEfectivo> implements Serializable {

    @EJB
    private CierreDesgloceEfectivoFacade ejbFacade;

    public CierreDesgloceEfectivoController() {
        super(CierreDesgloceEfectivo.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getCierreDesgloceEfectivoPK().setCodigoMoneda(this.getSelected().getCierreDetalle().getCierreDetallePK().getCodigoMoneda());
        this.getSelected().getCierreDesgloceEfectivoPK().setCodigoApertura(this.getSelected().getCierreDetalle().getCierreDetallePK().getCodigoApertura());
        this.getSelected().getCierreDesgloceEfectivoPK().setCodigoTipoFraccion(this.getSelected().getFraccionMoneda().getFraccionMonedaPK().getCodigoTipoFraccion());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setCierreDesgloceEfectivoPK(new ec.renafipse.mks.modelo.cajas.CierreDesgloceEfectivoPK());
    }

}
