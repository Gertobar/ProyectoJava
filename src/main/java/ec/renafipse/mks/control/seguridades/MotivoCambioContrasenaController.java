package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.MotivoCambioContrasena;
import ec.renafipse.mks.negocio.seguridades.MotivoCambioContrasenaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "motivoCambioContrasenaController")
@ViewScoped
public class MotivoCambioContrasenaController extends AbstractController<MotivoCambioContrasena> implements Serializable {

    @EJB
    private MotivoCambioContrasenaFacade ejbFacade;

    public MotivoCambioContrasenaController() {
        super(MotivoCambioContrasena.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
