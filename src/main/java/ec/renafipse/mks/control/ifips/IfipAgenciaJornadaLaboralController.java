package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboral;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaJornadaLaboralFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ifipAgenciaJornadaLaboralController")
@ViewScoped
public class IfipAgenciaJornadaLaboralController extends AbstractController<IfipAgenciaJornadaLaboral> implements Serializable {

    @EJB
    private IfipAgenciaJornadaLaboralFacade ejbFacade;

    public IfipAgenciaJornadaLaboralController() {
        super(IfipAgenciaJornadaLaboral.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getIfipAgenciaJornadaLaboralPK().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setIfipAgenciaJornadaLaboralPK(new ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboralPK());
    }

}
