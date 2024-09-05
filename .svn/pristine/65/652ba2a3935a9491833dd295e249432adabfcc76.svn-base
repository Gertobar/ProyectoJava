package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngreso;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaIngresoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioFlujoCajaIngresoController")
@SessionScoped
public class SocioFlujoCajaIngresoController extends AbstractController<SocioFlujoCajaIngreso> implements Serializable {

    @Inject
    private SocioFlujoCajaIngresoFacade ejbFacade;

    public SocioFlujoCajaIngresoController() {
        super(SocioFlujoCajaIngreso.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getSocioFlujoCajaIngresoPK().setCodigoItemFluCaj(this.getSelected().getItemFlujoCaja().getCodigo());
        this.getSelected().getSocioFlujoCajaIngresoPK().setCodigoPersona(this.getSelected().getSocioFlujoCaja().getCodigoPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setSocioFlujoCajaIngresoPK(new ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngresoPK());
    }

}
