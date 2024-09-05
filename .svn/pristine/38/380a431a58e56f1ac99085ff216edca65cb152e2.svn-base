package ec.renafipse.mks.control.socios;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.Nivel;
import ec.renafipse.mks.negocio.socios.ActividadEconomicaFacade;
import ec.renafipse.mks.negocio.socios.NivelFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;

@Named(value = "actividadEconomicaController")
@SessionScoped
public class ActividadEconomicaController extends AbstractController<ActividadEconomica> implements Serializable {

    @Inject
    private ActividadEconomicaFacade ejbFacade;
    
    @Inject
    private NivelFacade ejbFacadeNivel;
    
    private List<Nivel> itemsNivel;

    public ActividadEconomicaController() {
        super(ActividadEconomica.class);
        
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsNivel(this.ejbFacadeNivel.getItemsNivel());
        
    }
    
    public List<Nivel> getItemsNivel (){
        return this.itemsNivel;
        
    }

    /**
     * @param itemsNivel the itemsNivel to set
     */
    public void setItemsNivel(List<Nivel> itemsNivel) {
        ////System.out.println("size nivel "+itemsNivel.size());
        this.itemsNivel = itemsNivel;
    }
    

}
