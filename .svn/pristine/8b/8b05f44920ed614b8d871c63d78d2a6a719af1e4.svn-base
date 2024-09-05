package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.TipoPerTipoIde;
import ec.renafipse.mks.negocio.socios.TipoPerTipoIdeFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "tipoPerTipoIdeController")
@SessionScoped
public class TipoPerTipoIdeController extends AbstractController<TipoPerTipoIde> implements Serializable {

    @Inject
    private TipoPerTipoIdeFacade ejbFacade;

    public TipoPerTipoIdeController() {
        super(TipoPerTipoIde.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getTipoPerTipoIdePK().setCodigoTipoPersona(this.getSelected().getTipoPersona().getCodigo());
        this.getSelected().getTipoPerTipoIdePK().setCodigoTipoIdentificacion(this.getSelected().getTipoIdentificacion().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setTipoPerTipoIdePK(new ec.renafipse.mks.modelo.socios.TipoPerTipoIdePK());
    }

}
