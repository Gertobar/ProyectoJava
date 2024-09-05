package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.TipoEntidadFinanciera;
import ec.renafipse.mks.negocio.comunes.TipoEntidadFinancieraFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoEntidadFinancieraController")
@ViewScoped
public class TipoEntidadFinancieraController extends AbstractController<TipoEntidadFinanciera> implements Serializable {

    @EJB
    private TipoEntidadFinancieraFacade ejbFacade;

    public TipoEntidadFinancieraController() {
        super(TipoEntidadFinanciera.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
