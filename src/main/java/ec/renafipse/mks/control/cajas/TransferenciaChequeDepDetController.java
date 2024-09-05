package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDet;
import ec.renafipse.mks.negocio.cajas.TransferenciaChequeDepDetFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "transferenciaChequeDepDetController")
@ViewScoped
public class TransferenciaChequeDepDetController extends AbstractController<TransferenciaChequeDepDet> implements Serializable {

    @EJB
    private TransferenciaChequeDepDetFacade ejbFacade;

    public TransferenciaChequeDepDetController() {
        super(TransferenciaChequeDepDet.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getTransferenciaChequeDepDetPK().setCodigoTransferencia(this.getSelected().getTransferenciaChequeDep().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setTransferenciaChequeDepDetPK(new ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDetPK());
    }

}
