package ec.renafipse.mks.control.comunes;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.negocio.comunes.EntidadFinancieraFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "entidadFinancieraController")
@ViewScoped
public class EntidadFinancieraController extends AbstractController<EntidadFinanciera> implements Serializable {

    @EJB
    private EntidadFinancieraFacade ejbFacade;

    private List<EntidadFinanciera>listaEntidadFinanciera;
    
    public EntidadFinancieraController() {
        super(EntidadFinanciera.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);  
        listaEntidadFinanciera=this.ejbFacade.findAll();

    }

    public void creaEntidadFinanciera(ActionEvent event) {        
        this.getSelected().getNombre();
        this.getSelected().getCodigoTipoEntidad().getCodigo();
        this.getSelected().getEliminado();
        this.ejbFacade.create(getSelected());
    }

    /**
     * @return the listaEntidadFinanciera
     */
    public List<EntidadFinanciera> getListaEntidadFinanciera() {
        return listaEntidadFinanciera;
    }

    /**
     * @param listaEntidadFinanciera the listaEntidadFinanciera to set
     */
    public void setListaEntidadFinanciera(List<EntidadFinanciera> listaEntidadFinanciera) {
        this.listaEntidadFinanciera = listaEntidadFinanciera;
    }
}
