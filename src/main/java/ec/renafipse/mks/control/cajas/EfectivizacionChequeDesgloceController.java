package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.EfectivizacionChequeDesgloce;
import ec.renafipse.mks.negocio.cajas.EfectivizacionChequeDesgloceFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "efectivizacionChequeDesgloceController")
@ViewScoped
public class EfectivizacionChequeDesgloceController extends AbstractController<EfectivizacionChequeDesgloce> implements Serializable {

    @EJB
    private EfectivizacionChequeDesgloceFacade ejbFacade;

    public EfectivizacionChequeDesgloceController() {
        super(EfectivizacionChequeDesgloce.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
