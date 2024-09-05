package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.IntentoFallidoAccesoSistema;
import ec.renafipse.mks.negocio.seguridades.IntentoFallidoAccesoSistemaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "intentoFallidoAccesoSistemaController")
@ViewScoped
public class IntentoFallidoAccesoSistemaController extends AbstractController<IntentoFallidoAccesoSistema> implements Serializable {

    @EJB
    private IntentoFallidoAccesoSistemaFacade ejbFacade;

    public IntentoFallidoAccesoSistemaController() {
        super(IntentoFallidoAccesoSistema.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
