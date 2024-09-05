package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.ReferenciaEntidadFinanciera;
import ec.renafipse.mks.negocio.socios.ReferenciaEntidadFinancieraFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "referenciaEntidadFinancieraController")
@SessionScoped
public class ReferenciaEntidadFinancieraController extends AbstractController<ReferenciaEntidadFinanciera> implements Serializable {

    @Inject
    private ReferenciaEntidadFinancieraFacade ejbFacade;

    public ReferenciaEntidadFinancieraController() {
        super(ReferenciaEntidadFinanciera.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
