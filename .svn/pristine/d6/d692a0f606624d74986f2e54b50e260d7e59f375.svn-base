package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.UsuarioPuntoIfipCobroPago;
import ec.renafipse.mks.negocio.seguridades.UsuarioPuntoIfipCobroPagoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usuarioPuntoIfipCobroPagoController")
@ViewScoped
public class UsuarioPuntoIfipCobroPagoController extends AbstractController<UsuarioPuntoIfipCobroPago> implements Serializable {

    @EJB
    private UsuarioPuntoIfipCobroPagoFacade ejbFacade;

    public UsuarioPuntoIfipCobroPagoController() {
        super(UsuarioPuntoIfipCobroPago.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
