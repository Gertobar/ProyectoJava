package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.MotivoExpiraContrasena;
import ec.renafipse.mks.negocio.seguridades.MotivoExpiraContrasenaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "motivoExpiraContrasenaController")
@ViewScoped
public class MotivoExpiraContrasenaController extends AbstractController<MotivoExpiraContrasena> implements Serializable {

    @EJB
    private MotivoExpiraContrasenaFacade ejbFacade;

    public MotivoExpiraContrasenaController() {
        super(MotivoExpiraContrasena.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
