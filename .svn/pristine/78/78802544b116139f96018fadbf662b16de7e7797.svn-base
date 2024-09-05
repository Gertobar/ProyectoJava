package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.PeriodoEfeCheEntFin;
import ec.renafipse.mks.negocio.cajas.PeriodoEfeCheEntFinFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "periodoEfeCheEntFinController")
@ViewScoped
public class PeriodoEfeCheEntFinController extends AbstractController<PeriodoEfeCheEntFin> implements Serializable {

    @EJB
    private PeriodoEfeCheEntFinFacade ejbFacade;

    public PeriodoEfeCheEntFinController() {
        super(PeriodoEfeCheEntFin.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
