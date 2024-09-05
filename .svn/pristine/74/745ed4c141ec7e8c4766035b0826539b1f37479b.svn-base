package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonial;
import ec.renafipse.mks.negocio.socios.ItemSituacionPatrimonialFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "itemSituacionPatrimonialController")
@SessionScoped
public class ItemSituacionPatrimonialController extends AbstractController<ItemSituacionPatrimonial> implements Serializable {

    @Inject
    private ItemSituacionPatrimonialFacade ejbFacade;

    public ItemSituacionPatrimonialController() {
        super(ItemSituacionPatrimonial.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
