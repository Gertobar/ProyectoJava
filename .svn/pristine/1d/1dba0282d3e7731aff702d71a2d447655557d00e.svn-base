package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDet;
import ec.renafipse.mks.negocio.cajas.GuiaDepositoChequeDetFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "guiaDepositoChequeDetController")
@ViewScoped
public class GuiaDepositoChequeDetController extends AbstractController<GuiaDepositoChequeDet> implements Serializable {

    @EJB
    private GuiaDepositoChequeDetFacade ejbFacade;

    public GuiaDepositoChequeDetController() {
        super(GuiaDepositoChequeDet.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getGuiaDepositoChequeDetPK().setCodigoGuiaDeposito(this.getSelected().getGuiaDepositoCheque().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setGuiaDepositoChequeDetPK(new ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDetPK());
    }

}
