package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaSecuencia;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaSecuenciaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ifipAgenciaSecuenciaController")
@ViewScoped
public class IfipAgenciaSecuenciaController extends AbstractController<IfipAgenciaSecuencia> implements Serializable {

    @EJB
    private IfipAgenciaSecuenciaFacade ejbFacade;

    public IfipAgenciaSecuenciaController() {
        super(IfipAgenciaSecuencia.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getIfipAgenciaSecuenciaPK().setCodigoSecuencia(this.getSelected().getSecuencia().getCodigo());
        this.getSelected().getIfipAgenciaSecuenciaPK().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setIfipAgenciaSecuenciaPK(new ec.renafipse.mks.modelo.ifips.IfipAgenciaSecuenciaPK());
    }

}
