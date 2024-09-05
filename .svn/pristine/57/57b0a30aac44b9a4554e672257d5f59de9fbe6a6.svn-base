package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ifipController")
@ViewScoped
public class IfipController extends AbstractController<Ifip> implements Serializable {

    @EJB
    private IfipFacade ejbFacade;
    
    
   private Ifip ifip;

    public IfipController() {
        super(Ifip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    
    
    
    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

}
