package ec.renafipse.mks.control.adquisicones;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.ItemVenta;
import ec.renafipse.mks.negocio.adquisiciones.ItemVentaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "itemVentaController")
@ViewScoped
public class ItemVentaController extends AbstractController<ItemVenta> {

    @EJB
    private ItemVentaFacade ejbFacade;
    
    private List<ItemVenta> itemsItemVenta;

    /**
     * Initialize the concrete ItemVenta controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.buscar();
    }

    public ItemVentaController() {
        // Inform the Abstract parent controller of the concrete ItemVenta?cap_first Entity
        super(ItemVenta.class);
    }
    
     /**
     * Obtiene las cuentas contables de la ifip asignadas al Item de la Venta
     */
    public void buscar() {
        this.setItemsItemVenta(this.ejbFacade.findAll());
    }

    public void crear(ActionEvent actionEvent) {
        this.saveNew(actionEvent);
        this.buscar();
    }

    public void editar(ActionEvent actionEvent) {
        this.save(actionEvent);
        this.buscar();
    }

    /**
     * @return the itemsItemVenta
     */
    public List<ItemVenta> getItemsItemVenta() {
        return itemsItemVenta;
    }

    /**
     * @param itemsItemVenta the itemsItemVenta to set
     */
    public void setItemsItemVenta(List<ItemVenta> itemsItemVenta) {
        this.itemsItemVenta = itemsItemVenta;
    }

}
