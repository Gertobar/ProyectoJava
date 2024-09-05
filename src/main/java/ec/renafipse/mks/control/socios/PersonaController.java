package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;

@Named(value = "personaController1")
@ViewScoped
public class PersonaController extends AbstractController<Persona> implements Serializable {

    @EJB
    private PersonaFacade ejbFacade;

    private String buscarPor;
    private String criterio;
    private List<Persona> itemsPersona;
    private String busca;
    
    public PersonaController() {
        super(Persona.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
    
  
    public void obtieneDatos()
    {
         ////System.out.println("criterio "+criterio+" "+this.buscarPor+" busca "+busca);
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

}
