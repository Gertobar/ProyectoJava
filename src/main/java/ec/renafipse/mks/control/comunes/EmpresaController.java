package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Empresa;
import ec.renafipse.mks.negocio.comunes.EmpresaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "empresaController")
@ViewScoped
public class EmpresaController extends AbstractController<Empresa> implements Serializable {

    @EJB
    private EmpresaFacade ejbFacade;

    public EmpresaController() {
        super(Empresa.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
