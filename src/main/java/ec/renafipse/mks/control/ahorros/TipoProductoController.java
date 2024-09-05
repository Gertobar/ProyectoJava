package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.TipoProducto;
import ec.renafipse.mks.negocio.ahorros.TipoProductoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoProductoController")
@ViewScoped
public class TipoProductoController extends AbstractController<TipoProducto> implements Serializable {

    @EJB
    private TipoProductoFacade ejbFacade;

    public TipoProductoController() {
        super(TipoProducto.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
