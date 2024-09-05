package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.SocioSituacionPatPas;
import ec.renafipse.mks.negocio.socios.SocioSituacionPatPasFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "socioSituacionPatPasController")
@SessionScoped
public class SocioSituacionPatPasController extends AbstractController<SocioSituacionPatPas> implements Serializable {

    @Inject
    private SocioSituacionPatPasFacade ejbFacade;

    public SocioSituacionPatPasController() {
        super(SocioSituacionPatPas.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getSocioSituacionPatPasPK().setCodigoItemSitPat(this.getSelected().getItemSituacionPatrimonial().getCodigo());
        this.getSelected().getSocioSituacionPatPasPK().setCodigoPersona(this.getSelected().getSocioSituacionPatrimonial().getCodigoPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setSocioSituacionPatPasPK(new ec.renafipse.mks.modelo.socios.SocioSituacionPatPasPK());
    }

}
