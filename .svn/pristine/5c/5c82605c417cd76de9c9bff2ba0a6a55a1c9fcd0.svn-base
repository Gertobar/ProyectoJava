package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.negocio.comunes.TipoCuentaEntidadFinancieraFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoCuentaEntidadFinancieraController")
@ViewScoped
public class TipoCuentaEntidadFinancieraController extends AbstractController<TipoCuentaEntidadFinanciera> implements Serializable {

    @EJB
    private TipoCuentaEntidadFinancieraFacade ejbFacade;

    public TipoCuentaEntidadFinancieraController() {
        super(TipoCuentaEntidadFinanciera.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
