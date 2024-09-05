package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.SegmentoIfip;
import ec.renafipse.mks.negocio.ifips.SegmentoIfipFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "segmentoIfipController")
@ViewScoped
public class SegmentoIfipController extends AbstractController<SegmentoIfip> implements Serializable {

    @EJB
    private SegmentoIfipFacade ejbFacade;

    public SegmentoIfipController() {
        super(SegmentoIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
