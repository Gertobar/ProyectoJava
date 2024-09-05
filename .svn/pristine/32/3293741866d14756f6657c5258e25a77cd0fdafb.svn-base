package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.FraccionMoneda;
import ec.renafipse.mks.negocio.cajas.FraccionMonedaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "fraccionMonedaController")
@ViewScoped
public class FraccionMonedaController extends AbstractController<FraccionMoneda> implements Serializable {

    @EJB
    private FraccionMonedaFacade ejbFacade;

    public FraccionMonedaController() {
        super(FraccionMoneda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getFraccionMonedaPK().setCodigoTipoFraccion(this.getSelected().getTipoFraccionMoneda().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setFraccionMonedaPK(new ec.renafipse.mks.modelo.cajas.FraccionMonedaPK());
    }

}
