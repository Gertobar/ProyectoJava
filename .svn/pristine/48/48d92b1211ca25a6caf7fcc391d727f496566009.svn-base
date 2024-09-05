package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.Sistema;
import ec.renafipse.mks.negocio.seguridades.SistemaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "sistemaController")
@ViewScoped
public class SistemaController extends AbstractController<Sistema> implements Serializable {

    @EJB
    private SistemaFacade ejbFacade;

    public SistemaController() {
        super(Sistema.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
