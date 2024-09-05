package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistemaPK;
import ec.renafipse.mks.negocio.seguridades.UsuarioSistemaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usuarioSistemaController")
@ViewScoped
public class UsuarioSistemaController extends AbstractController<UsuarioSistema> implements Serializable {

    @EJB
    private UsuarioSistemaFacade ejbFacade;

    public UsuarioSistemaController() {
        super(UsuarioSistema.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getUsuarioSistemaPK().setCodigoUsuario(this.getSelected().getUsuario().getCodigo());
        this.getSelected().getUsuarioSistemaPK().setCodigoSistema(this.getSelected().getSistema().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setUsuarioSistemaPK(new ec.renafipse.mks.modelo.seguridades.UsuarioSistemaPK());
    }

 
    /**
     * @return the ejbFacade
     */
    public UsuarioSistemaFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(UsuarioSistemaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
}