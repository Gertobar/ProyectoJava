package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.JerarquiaUbicacionGeografica;
import ec.renafipse.mks.negocio.comunes.JerarquiaUbicacionGeograficaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "jerarquiaUbicacionGeograficaController")
@ViewScoped
public class JerarquiaUbicacionGeograficaController extends AbstractController<JerarquiaUbicacionGeografica> implements Serializable {

    @EJB
    private JerarquiaUbicacionGeograficaFacade ejbFacade;

    public JerarquiaUbicacionGeograficaController() {
        super(JerarquiaUbicacionGeografica.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
