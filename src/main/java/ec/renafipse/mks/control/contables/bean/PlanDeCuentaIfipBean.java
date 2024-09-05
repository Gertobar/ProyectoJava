package ec.renafipse.mks.control.contables.bean;


import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaIfip;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "planDeCuentaIfipBean")
@ViewScoped
public class PlanDeCuentaIfipBean extends AbstractController<PlanDeCuentaIfip> {

    @EJB
    private PlanDeCuentaIfipFacade ejbFacade;
    
    private GeneraReporte generaReporte;

    
    private List<PlanDeCuenta> itemsPlanCuenta;

    
    /**
     * Initialize the concrete PlanDeCuentaIfip controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        this.setItemsPlanCuenta(this.ejbFacade.getItemsPlanCuentaIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N'));
    }

    
    public void imprimeComprobante(Long codIfip,String extension, String tipo) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigo", codIfip);

        if (tipo.equals("PDF"))nombreReporte = "catalogoCuentas";else nombreReporte = "catalogoCuentasExcel";

        getGeneraReporte().exporta("/contable/reportes/catalogoCuentas/reporte/", nombreReporte,
                nombreReporte + extension,
                tipo, externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }

    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.imprimeComprobante(ActivacionUsuario.getCodigoIfip(), ".pdf", "PDF");
    }
    
    public void imprimeExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.imprimeComprobante(ActivacionUsuario.getCodigoIfip(), ".xls", "XLS");
    }
    
    /**
     * Sets the "items" attribute with a collection of ComprobanteDetalle
     * entities that are retrieved from PlanDeCuentaIfip?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for ComprobanteDetalle page
     */
    public String navigateComprobanteDetalleCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ComprobanteDetalle_items", this.getSelected().getComprobanteDetalleCollection());
        }
        return "/comprobanteDetalle/index";
    }

    /**
     * @return the itemsPlanCuenta
     */
    public List<PlanDeCuenta> getItemsPlanCuenta() {
        return itemsPlanCuenta;
    }

    /**
     * @param itemsPlanCuenta the itemsPlanCuenta to set
     */
    public void setItemsPlanCuenta(List<PlanDeCuenta> itemsPlanCuenta) {
        this.itemsPlanCuenta = itemsPlanCuenta;
    }

    /**
     * @return the ejbFacade
     */
    public PlanDeCuentaIfipFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(PlanDeCuentaIfipFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the generaReporte
     */
    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    /**
     * @param generaReporte the generaReporte to set
     */
    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

}
