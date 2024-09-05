package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.BancoPreguntaSesion;
import ec.renafipse.mks.negocio.seguridades.BancoPreguntaSesionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "bancoPreguntaSesionController")
@ViewScoped
public class BancoPreguntaSesionController extends AbstractController<BancoPreguntaSesion> implements Serializable {

    @EJB
    private BancoPreguntaSesionFacade ejbFacade;

    public BancoPreguntaSesionController() {
        super(BancoPreguntaSesion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
