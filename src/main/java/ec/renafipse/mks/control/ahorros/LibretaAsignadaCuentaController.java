package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.LibretaAsignadaCuenta;
import ec.renafipse.mks.negocio.ahorros.LibretaAsignadaCuentaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "libretaAsignadaCuentaController")
@ViewScoped
public class LibretaAsignadaCuentaController extends AbstractController<LibretaAsignadaCuenta> implements Serializable {

    @EJB
    private LibretaAsignadaCuentaFacade ejbFacade;

    public LibretaAsignadaCuentaController() {
        super(LibretaAsignadaCuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
