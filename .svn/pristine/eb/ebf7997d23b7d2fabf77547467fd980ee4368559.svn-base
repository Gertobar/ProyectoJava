package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfip;
import ec.renafipse.mks.negocio.creditos.DiasEnvioNotificacionIfipFacade;
import ec.renafipse.mks.control.AbstractController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "diasEnvioNotificacionIfipController")
@SessionScoped
public class DiasEnvioNotificacionIfipController extends AbstractController<DiasEnvioNotificacionIfip> implements Serializable {

    @Inject
    private DiasEnvioNotificacionIfipFacade ejbFacade;

    public DiasEnvioNotificacionIfipController() {
        super(DiasEnvioNotificacionIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDiasEnvioNotificacionIfipPK(new ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfipPK());
    }

}
