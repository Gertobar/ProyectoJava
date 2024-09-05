package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatAct;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatActFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioSituacionPatActController")
@SessionScoped
public class SocioSituacionPatActController extends AbstractController<SocioSituacionPatAct> implements Serializable {

    @Inject
    private SocioSituacionPatActFacade ejbFacade;

    public SocioSituacionPatActController() {
        super(SocioSituacionPatAct.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getSocioSituacionPatActPK().setCodigoItemSitPat(this.getSelected().getItemSituacionPatrimonial().getCodigo());
        this.getSelected().getSocioSituacionPatActPK().setCodigoPersona(this.getSelected().getSocioSituacionPatrimonial().getCodigoPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setSocioSituacionPatActPK(new ec.renafipse.mks.modelo.socios.SocioSituacionPatActPK());
    }

}
