package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Sector;
import ec.renafipse.mks.negocio.socios.SectorFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "sectorController")
@SessionScoped
public class SectorController extends AbstractController<Sector> implements Serializable {

    @Inject
    private SectorFacade ejbFacade;

    public SectorController() {
        super(Sector.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
