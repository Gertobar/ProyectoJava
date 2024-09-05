package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "monedaController")
@ViewScoped
public class MonedaController extends AbstractController<Moneda> implements Serializable {

    @EJB
    private MonedaFacade ejbFacade;

    public MonedaController() {
        super(Moneda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
