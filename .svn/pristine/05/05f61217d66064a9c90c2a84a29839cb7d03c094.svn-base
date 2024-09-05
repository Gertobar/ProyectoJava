package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.PeriodoContable;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "periodoContableController")
@ViewScoped
public class PeriodoContableController extends AbstractController<PeriodoContable> {

    @EJB
    private PeriodoContableFacade ejbFacade;

    private RequestContext context;

    /**
     * Initialize the concrete PeriodoContable controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    private List itemsPeriodoContable;
    private PeriodoContable periodoContable;

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsPeriodoContable(this.ejbFacade.findAll());
    }

    public void selecciona() {
        try {
            if (periodoContable == null) {
                return;
            }
            
            ActivacionUsuario.setCodigoPeriodo(periodoContable.getCodigo());
            System.out.println("Codigo Periodo contable "+periodoContable.getCodigo());
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlMenuMKS");
            //Accediendo a la ventana de no permisos
            Sesion.redireccionaPagina(url);
        } catch (IOException ex) {
            Logger.getLogger(PeriodoContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the itemsPeriodoContable
     */
    public List getItemsPeriodoContable() {
        return itemsPeriodoContable;
    }

    /**
     * @param itemsPeriodoContable the itemsPeriodoContable to set
     */
    public void setItemsPeriodoContable(List itemsPeriodoContable) {
        this.itemsPeriodoContable = itemsPeriodoContable;
    }

    /**
     * @return the periodoContable
     */
    public PeriodoContable getPeriodoContable() {
        return periodoContable;
    }

    /**
     * @param periodoContable the periodoContable to set
     */
    public void setPeriodoContable(PeriodoContable periodoContable) {
        this.periodoContable = periodoContable;
    }

}
