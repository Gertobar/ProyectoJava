package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.AperturaCajaFondeo;
import ec.renafipse.mks.negocio.cajas.AperturaCajaFondeoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "aperturaCajaFondeoController")
@ViewScoped
public class AperturaCajaFondeoController extends AbstractController<AperturaCajaFondeo> implements Serializable {

    @EJB
    private AperturaCajaFondeoFacade ejbFacade;

    public AperturaCajaFondeoController() {
        super(AperturaCajaFondeo.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
