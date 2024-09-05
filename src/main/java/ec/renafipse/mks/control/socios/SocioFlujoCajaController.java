package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.SocioFlujoCaja;
import ec.renafipse.mks.negocio.socios.SocioFlujoCajaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioFlujoCajaController")
@SessionScoped
public class SocioFlujoCajaController extends AbstractController<SocioFlujoCaja> implements Serializable {

    @Inject
    private SocioFlujoCajaFacade ejbFacade;

    public SocioFlujoCajaController() {
        super(SocioFlujoCaja.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        //this.getSelected().getSocioFlujoCajaPK().setCodigoSocio(this.getSelected().getSocio().getSocioPK().getCodigoIfip());
        //this.getSelected().getSocioFlujoCajaPK().setCodigoIfip(this.getSelected().getSocio().getSocioPK().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setCodigoSocio(Long.valueOf("0"));
    }

}
