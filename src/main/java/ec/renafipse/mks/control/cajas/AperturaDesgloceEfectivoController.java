package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.AperturaDesgloceEfectivo;
import ec.renafipse.mks.negocio.cajas.AperturaDesgloceEfectivoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "aperturaDesgloceEfectivoController")
@ViewScoped
public class AperturaDesgloceEfectivoController extends AbstractController<AperturaDesgloceEfectivo> implements Serializable {

    @EJB
    private AperturaDesgloceEfectivoFacade ejbFacade;

    public AperturaDesgloceEfectivoController() {
        super(AperturaDesgloceEfectivo.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getAperturaDesgloceEfectivoPK().setCodigoApertura(this.getSelected().getAperturaDetalle().getAperturaDetallePK().getCodigoApertura());
        this.getSelected().getAperturaDesgloceEfectivoPK().setCodigoMoneda(this.getSelected().getAperturaDetalle().getAperturaDetallePK().getCodigoMoneda());
        this.getSelected().getAperturaDesgloceEfectivoPK().setCodigoTipoFraccion(this.getSelected().getFraccionMoneda().getFraccionMonedaPK().getCodigoTipoFraccion());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setAperturaDesgloceEfectivoPK(new ec.renafipse.mks.modelo.cajas.AperturaDesgloceEfectivoPK());
    }

}
