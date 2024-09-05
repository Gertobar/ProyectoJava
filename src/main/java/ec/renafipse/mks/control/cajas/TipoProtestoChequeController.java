package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.TipoProtestoCheque;
import ec.renafipse.mks.negocio.cajas.TipoProtestoChequeFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoProtestoChequeController")
@ViewScoped
public class TipoProtestoChequeController extends AbstractController<TipoProtestoCheque> implements Serializable {

    @EJB
    private TipoProtestoChequeFacade ejbFacade;

    public TipoProtestoChequeController() {
        super(TipoProtestoCheque.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
