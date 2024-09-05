package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.ItemVenta;
import ec.renafipse.mks.modelo.contables.CuentaContableItemVenta;
import ec.renafipse.mks.modelo.contables.CuentaContableItemVentaPK;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaIfip;
import ec.renafipse.mks.negocio.adquisiciones.ItemVentaFacade;
import ec.renafipse.mks.negocio.contables.CuentaContableItemVentaFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "cuentaContableItemVentaController")
@ViewScoped
public class CuentaContableItemVentaController extends AbstractController<CuentaContableItemVenta> {

    @EJB
    private CuentaContableItemVentaFacade ejbFacade;

    @EJB
    private PlanDeCuentaIfipFacade ejbFacadePlanDeCuentaIfip;

    @EJB
    private ItemVentaFacade ejbFacadeItemVenta;

    private List<CuentaContableItemVenta> itemsCuentaContableItemVenta;
    private List<ItemVenta> itemsItemVenta;
    private List<PlanDeCuentaIfip> itemsPlanDeCuentaIfip;

    /**
     * Initialize the concrete CuentaContableItemVenta controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsCuentaContableItemVenta(this.ejbFacade.getItemsIfip(ActivacionUsuario.getCodigoIfip()));
        this.setItemsPlanDeCuentaIfip(ejbFacadePlanDeCuentaIfip.getItemsPlanCuentaIfipVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
        this.setItemsItemVenta(ejbFacadeItemVenta.getItemsEliminado('N'));
    }

    public CuentaContableItemVentaController() {
        // Inform the Abstract parent controller of the concrete CuentaContableItemVenta?cap_first Entity
        super(CuentaContableItemVenta.class);
    }

    /**
     * Obtiene las cuentas contables de la ifip asignadas al Item de la Venta
     */
    public void buscar() {
        this.setItemsCuentaContableItemVenta(this.ejbFacade.getItemsIfip(ActivacionUsuario.getCodigoIfip()));
    }

    public void crear(ActionEvent actionEvent) {
        this.saveNew(actionEvent);
        this.buscar();
    }

    public void editar(ActionEvent actionEvent) {
        this.save(actionEvent);
        this.buscar();
    }
    
    public void cambiaItemVenta()
    {
        CuentaContableItemVenta cciv = this.ejbFacade.find(new  CuentaContableItemVentaPK(ActivacionUsuario.getCodigoIfip(),this.getSelected().getItemVenta().getCodigo()));
        if (cciv != null)
        {
            this.getSelected().setItemVenta(null);
            MuestraMensaje.addAdvertencia(null);
        }
    }
    
    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getCuentaContableItemVentaPK().setCodigoItemVenta(this.getSelected().getItemVenta().getCodigo());
        this.getSelected().getCuentaContableItemVentaPK().setCodigoIfip(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCodigoIfip());
        this.getSelected().setCuentaContable(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCuentaContable());
        this.getSelected().setCodigoTipoPlan(this.getSelected().getPlanDeCuentaIfip().getPlanDeCuentaIfipPK().getCodigoTipoPlan());
        this.getSelected().setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
        this.getSelected().setFechaRegistro(new Date());
        this.getSelected().setEliminado('N');
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setCuentaContableItemVentaPK(new ec.renafipse.mks.modelo.contables.CuentaContableItemVentaPK());
    }

    /**
     * @return the itemsCuentaContableItemVenta
     */
    public List<CuentaContableItemVenta> getItemsCuentaContableItemVenta() {
        return itemsCuentaContableItemVenta;
    }

    /**
     * @param itemsCuentaContableItemVenta the itemsCuentaContableItemVenta to
     * set
     */
    public void setItemsCuentaContableItemVenta(List<CuentaContableItemVenta> itemsCuentaContableItemVenta) {
        this.itemsCuentaContableItemVenta = itemsCuentaContableItemVenta;
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

    /**
     * @return the itemsPlanDeCuentaIfip
     */
    public List<PlanDeCuentaIfip> getItemsPlanDeCuentaIfip() {
        return itemsPlanDeCuentaIfip;
    }

    /**
     * @param itemsPlanDeCuentaIfip the itemsPlanDeCuentaIfip to set
     */
    public void setItemsPlanDeCuentaIfip(List<PlanDeCuentaIfip> itemsPlanDeCuentaIfip) {
        this.itemsPlanDeCuentaIfip = itemsPlanDeCuentaIfip;
    }

}
