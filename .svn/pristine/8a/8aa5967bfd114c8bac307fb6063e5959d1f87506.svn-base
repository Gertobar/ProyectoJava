package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.PeriodoEfectivizacionCheque;
import ec.renafipse.mks.negocio.cajas.PeriodoEfectivizacionChequeFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "periodoEfectivizacionChequeController")
@ViewScoped
public class PeriodoEfectivizacionChequeController extends AbstractController<PeriodoEfectivizacionCheque> implements Serializable {

    @EJB
    private PeriodoEfectivizacionChequeFacade ejbFacade;

    public PeriodoEfectivizacionChequeController() {
        super(PeriodoEfectivizacionCheque.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
