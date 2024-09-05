package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.TipoPersona;

import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;

@Named(value = "tipoPersonaController")
@SessionScoped
public class TipoPersonaController extends AbstractController<TipoPersona> implements Serializable {

    @Inject
    private TipoPersonaFacade ejbFacade;

    public TipoPersonaController() {
        super(TipoPersona.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
    
     // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Obtiene los tipos de persona vigentes (No eliminados)
     * @return  Tipo de Persona
     */
    public List<TipoPersona> getItemsTipoPersonaVigente()
    { 
        return this.ejbFacade.getItemsTipoPersonaVigentes();
    }

   
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------

}
