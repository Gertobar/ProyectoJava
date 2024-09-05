package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.LiquidacionCuentaCabecera;
import ec.renafipse.mks.negocio.ahorros.LiquidacionCuentaCabeceraFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "liquidacionCuentaCabeceraController")
@ViewScoped
public class LiquidacionCuentaCabeceraController extends AbstractController<LiquidacionCuentaCabecera> implements Serializable {

    @EJB
    private LiquidacionCuentaCabeceraFacade ejbFacade;

    public LiquidacionCuentaCabeceraController() {
        super(LiquidacionCuentaCabecera.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
