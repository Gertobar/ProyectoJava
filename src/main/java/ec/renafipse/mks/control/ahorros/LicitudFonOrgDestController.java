package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.LicitudFonOrgDest;
import ec.renafipse.mks.negocio.ahorros.LicitudFonOrgDestFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "licitudFonOrgDestController")
@ViewScoped
public class LicitudFonOrgDestController extends AbstractController<LicitudFonOrgDest> implements Serializable {

    @EJB
    private LicitudFonOrgDestFacade ejbFacade;

    public LicitudFonOrgDestController() {
        super(LicitudFonOrgDest.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
