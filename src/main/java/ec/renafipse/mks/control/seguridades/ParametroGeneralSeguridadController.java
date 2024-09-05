package ec.renafipse.mks.control.seguridades;


import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.ParametroGeneralSeguridad;
import ec.renafipse.mks.negocio.seguridades.ParametroGeneralSeguridadFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "parametroGeneralSeguridadController")
@ViewScoped
public class ParametroGeneralSeguridadController extends AbstractController<ParametroGeneralSeguridad> implements Serializable {

    @EJB
    private ParametroGeneralSeguridadFacade ejbFacade;

    public ParametroGeneralSeguridadController() {
        super(ParametroGeneralSeguridad.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
