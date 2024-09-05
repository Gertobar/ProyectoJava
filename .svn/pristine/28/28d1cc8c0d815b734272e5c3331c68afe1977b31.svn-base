package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgencia;
import ec.renafipse.mks.negocio.socios.GrupoInstitucionIfipAgenciaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "grupoInstitucionIfipAgenciaController")
@ViewScoped
public class GrupoInstitucionIfipAgenciaController extends AbstractController<GrupoInstitucionIfipAgencia> implements Serializable {

    @EJB
    private GrupoInstitucionIfipAgenciaFacade ejbFacade;

    public GrupoInstitucionIfipAgenciaController() {
        super(GrupoInstitucionIfipAgencia.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getGrupoInstitucionIfipAgenciaPK().setCodigoGrupo(this.getSelected().getGrupoInstitucion().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setGrupoInstitucionIfipAgenciaPK(new ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgenciaPK());
    }

}
