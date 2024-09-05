package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.LicitudFonCptoTran;
import ec.renafipse.mks.negocio.ahorros.LicitudFonCptoTranFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "licitudFonCptoTranController")
@ViewScoped
public class LicitudFonCptoTranController extends AbstractController<LicitudFonCptoTran> implements Serializable {

    @EJB
    private LicitudFonCptoTranFacade ejbFacade;

    public LicitudFonCptoTranController() {
        super(LicitudFonCptoTran.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
