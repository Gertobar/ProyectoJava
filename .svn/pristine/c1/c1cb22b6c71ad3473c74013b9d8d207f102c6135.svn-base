package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.SeguimientoChequeDep;
import ec.renafipse.mks.negocio.cajas.SeguimientoChequeDepFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "seguimientoChequeDepController")
@ViewScoped
public class SeguimientoChequeDepController extends AbstractController<SeguimientoChequeDep> implements Serializable {

    @EJB
    private SeguimientoChequeDepFacade ejbFacade;

    public SeguimientoChequeDepController() {
        super(SeguimientoChequeDep.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
