package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.SocioMenorEdadRep;
import ec.renafipse.mks.negocio.socios.SocioMenorEdadRepFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioMenorEdadRepController")
@SessionScoped
public class SocioMenorEdadRepController extends AbstractController<SocioMenorEdadRep> implements Serializable {

    @Inject
    private SocioMenorEdadRepFacade ejbFacade;

    public SocioMenorEdadRepController() {
        super(SocioMenorEdadRep.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getSocioMenorEdadRepPK().setCodigoSocio(this.getSelected().getSocio1().getSocioPK().getCodigoIfip());
        this.getSelected().getSocioMenorEdadRepPK().setCodigoIfip(this.getSelected().getSocio1().getSocioPK().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setSocioMenorEdadRepPK(new ec.renafipse.mks.modelo.socios.SocioMenorEdadRepPK());
    }

}
