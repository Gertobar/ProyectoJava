package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.ItemFlujoCaja;
import ec.renafipse.mks.negocio.socios.ItemFlujoCajaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "itemFlujoCajaController")
@SessionScoped
public class ItemFlujoCajaController extends AbstractController<ItemFlujoCaja> implements Serializable {

    @Inject
    private ItemFlujoCajaFacade ejbFacade;

    public ItemFlujoCajaController() {
        super(ItemFlujoCaja.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
