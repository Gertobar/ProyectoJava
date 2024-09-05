package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.PersonaNaturalFacade;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;

@Named(value = "personaNaturalController")
@ViewScoped
public class PersonaNaturalController extends AbstractController<PersonaNatural> implements Serializable {

  
    
    @EJB
    private PersonaNaturalFacade ejbFacade;
    
    @EJB
    private PersonaFacade ejbFacadePersona;
    
    private String buscarPor;
    private String criterio;
    private List<Persona> itemsPersona;
    private String busca;

    private Persona personaSel;
    
    public PersonaNaturalController() {
        super(PersonaNatural.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

     
     // *******************************************************************************************
    // --------------------------------------------------------------------------
    // -- LOGICA
    /**
     * Obtiene los datos del socio en base del criterio de la consulta
     */
    public void obtienePersonasNaturales() {
        //System.out.println(" this.getCriterio() "+this.getCriterio()+" this.getBuscarPor() "+this.getBuscarPor());
        try {
            if (Validaciones.cumpleRequerimientoCampo(criterio, 6)) {
                this.setItemsPersona(this.ejbFacadePersona.getItemsPersona(this.getCriterio(), this.getBuscarPor(), 'N'));
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsPersona(null);
                this.setPersonaSel(null);
                
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"personaBean", "obtienePersonasNaturales", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    /**
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    /**
     * @return the itemsPersona
     */
    public List<Persona> getItemsPersona() {
        return itemsPersona;
    }

    /**
     * @param itemsPersona the itemsPersona to set
     */
    public void setItemsPersona(List<Persona> itemsPersona) {
        this.itemsPersona = itemsPersona;
    }

    /**
     * @return the busca
     */
    public String getBusca() {
        return busca;
    }

    /**
     * @param busca the busca to set
     */
    public void setBusca(String busca) {
        this.busca = busca;
    }

    /**
     * @return the personaSel
     */
    public Persona getPersonaSel() {
        return personaSel;
    }

    /**
     * @param personaSel the personaSel to set
     */
    public void setPersonaSel(Persona personaSel) {
        this.personaSel = personaSel;
    }

}
