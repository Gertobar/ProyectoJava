package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.IngresoEgresoMoneda;
import ec.renafipse.mks.negocio.cajas.IngresoEgresoMonedaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ingresoEgresoMonedaController")
@ViewScoped
public class IngresoEgresoMonedaController extends AbstractController<IngresoEgresoMoneda> implements Serializable {

    @EJB
    private IngresoEgresoMonedaFacade ejbFacade;

    public IngresoEgresoMonedaController() {
        super(IngresoEgresoMoneda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getIngresoEgresoMonedaPK().setCodigoIngresoEgreso(this.getSelected().getIngresoEgreso().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setIngresoEgresoMonedaPK(new ec.renafipse.mks.modelo.cajas.IngresoEgresoMonedaPK());
    }

}
