package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.TipoVivienda;
import ec.renafipse.mks.negocio.socios.TipoViviendaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "tipoViviendaController")
@SessionScoped
public class TipoViviendaController extends AbstractController<TipoVivienda> implements Serializable {

    @Inject
    private TipoViviendaFacade ejbFacade;

    public TipoViviendaController() {
        super(TipoVivienda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
