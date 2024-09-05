package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.RespuestaPreguntaUsuario;
import ec.renafipse.mks.negocio.seguridades.RespuestaPreguntaUsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "respuestaPreguntaUsuarioController")
@ViewScoped
public class RespuestaPreguntaUsuarioController extends AbstractController<RespuestaPreguntaUsuario> implements Serializable {

    @EJB
    private RespuestaPreguntaUsuarioFacade ejbFacade;

    public RespuestaPreguntaUsuarioController() {
        super(RespuestaPreguntaUsuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getRespuestaPreguntaUsuarioPK().setCodigoBancoPregunta(this.getSelected().getBancoPreguntaSesion().getCodigo());
        this.getSelected().getRespuestaPreguntaUsuarioPK().setCodigoUsuario(this.getSelected().getUsuario().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setRespuestaPreguntaUsuarioPK(new ec.renafipse.mks.modelo.seguridades.RespuestaPreguntaUsuarioPK());
    }

}
