package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.AperturaCajaMovimientoCta;
import ec.renafipse.mks.negocio.cajas.AperturaCajaMovimientoCtaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "aperturaCajaMovimientoCtaController")
@ViewScoped
public class AperturaCajaMovimientoCtaController extends AbstractController<AperturaCajaMovimientoCta> implements Serializable {

    @EJB
    private AperturaCajaMovimientoCtaFacade ejbFacade;

    public AperturaCajaMovimientoCtaController() {
        super(AperturaCajaMovimientoCta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
