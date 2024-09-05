package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.EstadoChequeDepositado;
import ec.renafipse.mks.negocio.cajas.EstadoChequeDepositadoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "estadoChequeDepositadoController")
@ViewScoped
public class EstadoChequeDepositadoController extends AbstractController<EstadoChequeDepositado> implements Serializable {

    @EJB
    private EstadoChequeDepositadoFacade ejbFacade;

    public EstadoChequeDepositadoController() {
        super(EstadoChequeDepositado.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
