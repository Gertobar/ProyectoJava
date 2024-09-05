package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgreso;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaEgresoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioFlujoCajaEgresoController")
@SessionScoped
public class SocioFlujoCajaEgresoController extends AbstractController<SocioFlujoCajaEgreso> implements Serializable {

    @Inject
    private SocioFlujoCajaEgresoFacade ejbFacade;

    public SocioFlujoCajaEgresoController() {
        super(SocioFlujoCajaEgreso.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getSocioFlujoCajaEgresoPK().setCodigoPersona(this.getSelected().getSocioFlujoCaja().getCodigoPersona());
        this.getSelected().getSocioFlujoCajaEgresoPK().setCodigoItemFluCaj(this.getSelected().getItemFlujoCaja().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setSocioFlujoCajaEgresoPK(new ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgresoPK());
    }

}
