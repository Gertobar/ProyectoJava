package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> implements Serializable {

    @EJB
    private RolFacade ejbFacade;

    public RolController() {
        super(Rol.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
                
    }

}
