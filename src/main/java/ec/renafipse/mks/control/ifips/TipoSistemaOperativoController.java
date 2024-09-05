package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.TipoSistemaOperativo;
import ec.renafipse.mks.negocio.ifips.TipoSistemaOperativoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoSistemaOperativoController")
@ViewScoped
public class TipoSistemaOperativoController extends AbstractController<TipoSistemaOperativo> implements Serializable {

    @EJB
    private TipoSistemaOperativoFacade ejbFacade;

    public TipoSistemaOperativoController() {
        super(TipoSistemaOperativo.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
