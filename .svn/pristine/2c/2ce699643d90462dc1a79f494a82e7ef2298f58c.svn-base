package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipMoneda;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ifipMonedaController")
@ViewScoped
public class IfipMonedaController extends AbstractController<IfipMoneda> implements Serializable {

    @EJB
    private IfipMonedaFacade ejbFacade;

    public IfipMonedaController() {
        super(IfipMoneda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getIfipMonedaPK().setCodigoIfip(this.getSelected().getIfip().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setIfipMonedaPK(new ec.renafipse.mks.modelo.ifips.IfipMonedaPK());
    }

}
