package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDesgloce;
import ec.renafipse.mks.negocio.cajas.TransferenciaChequeDesgloceFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "transferenciaChequeDesgloceController")
@ViewScoped
public class TransferenciaChequeDesgloceController extends AbstractController<TransferenciaChequeDesgloce> implements Serializable {

    @EJB
    private TransferenciaChequeDesgloceFacade ejbFacade;

    public TransferenciaChequeDesgloceController() {
        super(TransferenciaChequeDesgloce.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
