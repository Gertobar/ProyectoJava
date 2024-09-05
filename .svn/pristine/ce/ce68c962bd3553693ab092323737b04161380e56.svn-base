package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.ProtestoChequeDesgloce;
import ec.renafipse.mks.negocio.cajas.ProtestoChequeDesgloceFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "protestoChequeDesgloceController")
@ViewScoped
public class ProtestoChequeDesgloceController extends AbstractController<ProtestoChequeDesgloce> implements Serializable {

    @EJB
    private ProtestoChequeDesgloceFacade ejbFacade;

    public ProtestoChequeDesgloceController() {
        super(ProtestoChequeDesgloce.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
