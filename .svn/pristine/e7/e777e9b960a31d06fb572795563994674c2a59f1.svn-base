package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatrimonialFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioSituacionPatrimonialController")
@SessionScoped
public class SocioSituacionPatrimonialController extends AbstractController<SocioSituacionPatrimonial> implements Serializable {

    @Inject
    private SocioSituacionPatrimonialFacade ejbFacade;

    public SocioSituacionPatrimonialController() {
        super(SocioSituacionPatrimonial.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        //this.getSelected().getSocioSituacionPatrimonialPK().setCodigoIfip(this.getSelected().getSocio().getSocioPK().getCodigoIfip());
        //this.getSelected().getSocioSituacionPatrimonialPK().setCodigoSocio(this.getSelected().getSocio().getSocioPK().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setCodigoPersona(Long.valueOf("0"));
    }

}
