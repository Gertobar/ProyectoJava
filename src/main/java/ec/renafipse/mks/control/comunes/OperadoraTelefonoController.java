package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.OperadoraTelefono;
import ec.renafipse.mks.negocio.comunes.OperadoraTelefonoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "operadoraTelefonoController")
@ViewScoped
public class OperadoraTelefonoController extends AbstractController<OperadoraTelefono> implements Serializable {

    @EJB
    private OperadoraTelefonoFacade ejbFacade;

    public OperadoraTelefonoController() {
        super(OperadoraTelefono.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
