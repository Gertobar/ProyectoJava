package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.TipoFirma;
import ec.renafipse.mks.negocio.ahorros.TipoFirmaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoFirmaController")
@ViewScoped
public class TipoFirmaController extends AbstractController<TipoFirma> implements Serializable {

    @EJB
    private TipoFirmaFacade ejbFacade;

    public TipoFirmaController() {
        super(TipoFirma.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
