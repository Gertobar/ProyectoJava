package ec.renafipse.mks.control.seguridades;


import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfi;
import ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfiPK;
import ec.renafipse.mks.negocio.seguridades.ParametroGeneralSegIfiFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "parametroGeneralSegIfiController")
@ViewScoped
public class ParametroGeneralSegIfiController extends AbstractController<ParametroGeneralSegIfi> implements Serializable {

    @EJB
    private ParametroGeneralSegIfiFacade ejbFacade;
    
    private List<ParametroGeneralSegIfi> itemsParGenIfi;

    public ParametroGeneralSegIfiController() {
        super(ParametroGeneralSegIfi.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsParGenIfi(this.ejbFacade.getItemsParGenSegIfi(ActivacionUsuario.getCodigoIfip()));
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getParametroGeneralSegIfiPK().setCodigoParametro(this.getSelected().getParametroGeneralSeguridad().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setParametroGeneralSegIfiPK(new ParametroGeneralSegIfiPK());
    }

    /**
     * @return the itemsParGenIfi
     */
    public List<ParametroGeneralSegIfi> getItemsParGenIfi() {
        return itemsParGenIfi;
    }

    /**
     * @param itemsParGenIfi the itemsParGenIfi to set
     */
    public void setItemsParGenIfi(List<ParametroGeneralSegIfi> itemsParGenIfi) {
        this.itemsParGenIfi = itemsParGenIfi;
    }

}
