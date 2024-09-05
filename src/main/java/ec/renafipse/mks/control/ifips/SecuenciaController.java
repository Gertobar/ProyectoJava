package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Secuencia;
import ec.renafipse.mks.negocio.ifips.SecuenciaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "secuenciaController")
@ViewScoped
public class SecuenciaController extends AbstractController<Secuencia> implements Serializable {

    @EJB
    private SecuenciaFacade ejbFacade;

    public SecuenciaController() {
        super(Secuencia.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
