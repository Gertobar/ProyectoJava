package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.negocio.cajas.ChequeDepositadoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "chequeDepositadoController")
@ViewScoped
public class ChequeDepositadoController extends AbstractController<ChequeDepositado> implements Serializable {

    @EJB
    private ChequeDepositadoFacade ejbFacade;

    public ChequeDepositadoController() {
        super(ChequeDepositado.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
