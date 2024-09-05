package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDet;
import ec.renafipse.mks.negocio.socios.GrupoInstitucionAgeDetFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "grupoInstitucionAgeDetController")
@ViewScoped
public class GrupoInstitucionAgeDetController extends AbstractController<GrupoInstitucionAgeDet> implements Serializable {

    @EJB
    private GrupoInstitucionAgeDetFacade ejbFacade;

    public GrupoInstitucionAgeDetController() {
        super(GrupoInstitucionAgeDet.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getGrupoInstitucionAgeDetPK().setCodigoIfip(this.getSelected().getSocio().getSocioPK().getCodigoIfip());
        this.getSelected().getGrupoInstitucionAgeDetPK().setCodigoGrupo(this.getSelected().getGrupoInstitucionIfipAgencia().getGrupoInstitucionIfipAgenciaPK().getCodigoGrupo());
        this.getSelected().getGrupoInstitucionAgeDetPK().setCodigoSocio(this.getSelected().getSocio().getSocioPK().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setGrupoInstitucionAgeDetPK(new ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDetPK());
    }

}
