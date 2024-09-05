package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.TipoFraccionMoneda;
import ec.renafipse.mks.negocio.cajas.TipoFraccionMonedaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoFraccionMonedaController")
@ViewScoped
public class TipoFraccionMonedaController extends AbstractController<TipoFraccionMoneda> implements Serializable {

    @EJB
    private TipoFraccionMonedaFacade ejbFacade;

    public TipoFraccionMonedaController() {
        super(TipoFraccionMoneda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
