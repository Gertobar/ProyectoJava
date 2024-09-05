package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.UsuarioAccesoSistema;
import ec.renafipse.mks.negocio.seguridades.UsuarioAccesoSistemaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usuarioAccesoSistemaController")
@ViewScoped
public class UsuarioAccesoSistemaController extends AbstractController<UsuarioAccesoSistema> implements Serializable {

    @EJB
    private UsuarioAccesoSistemaFacade ejbFacade;

    public UsuarioAccesoSistemaController() {
        super(UsuarioAccesoSistema.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
