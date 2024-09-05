package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.creditos.TipoNotificacion;
import ec.renafipse.mks.negocio.creditos.TipoNotificacionFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "tipoNotificacionController")
@ViewScoped
public class TipoNotificacionController extends AbstractController<TipoNotificacion> {

    @EJB
    private TipoNotificacionFacade ejbFacade;

    /**
     * Initialize the concrete TipoNotificacion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
