package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDes;
import ec.renafipse.mks.negocio.cajas.GuiaDepositoChequeDesFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "guiaDepositoChequeDesController")
@ViewScoped
public class GuiaDepositoChequeDesController extends AbstractController<GuiaDepositoChequeDes> implements Serializable {

    @EJB
    private GuiaDepositoChequeDesFacade ejbFacade;

    public GuiaDepositoChequeDesController() {
        super(GuiaDepositoChequeDes.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
