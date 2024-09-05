package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.EstadoComprobante;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.modelo.contables.ProcesoContable;
import ec.renafipse.mks.modelo.contables.ProcesoContableDetalle;
import ec.renafipse.mks.modelo.contables.ProcesoContableDetallePK;
import ec.renafipse.mks.negocio.contables.EstadoComprobanteFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import ec.renafipse.mks.negocio.contables.ProcesoContableDetalleFacade;
import ec.renafipse.mks.negocio.contables.ProcesoContableFacade;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.apache.commons.beanutils.BeanUtils;

@ManagedBean(name = "procesoContableController")
@ViewScoped
public class ProcesoContableController extends AbstractController<ProcesoContable> implements Serializable {

    @EJB
    private ProcesoContableFacade ejbFacade;

    @EJB
    private ProcesoContableDetalleFacade ejbFacadeProcesoContableDetalle;

    @EJB
    private PlanDeCuentaIfipFacade ejbFacadePlanDeCuentasIfip;

    @EJB
    private PlanDeCuentaFacade ejbFacadePlanDeCuenta;

    @EJB
    private EstadoComprobanteFacade ejbFacadeEstadoComprobante;

    //listas para mostrar
    private List<ProcesoContable> itemsProcesoContable;

    private List<ProcesoContableDetalle> itemsProcesoContableDetalle;

    private List<PlanDeCuenta> itemsPlanDeCuenta;

    private List<EstadoComprobante> itemsEstadoComprobantes;

    private List<ProcesoContableDetalle> itemsProcesosContableDetallesReg;

    //objetos incluidos en la tabla 
    private ProcesoContableDetalle procesoDetalle;

    private ProcesoContable procesoContable;

    private PlanDeCuenta planDeCuenta;

    private EstadoComprobante codigoEstadoCom;

    private ProcesoContableDetallePK procesoContableDetallePK;

    //objetos seleccionados en la tabla
    private ProcesoContableDetalle procesoDetalleSel;

    private ProcesoContableDetalle procesoDetalleReg;

    private ProcesoContable procesoContableSel;

    // variables para validar
    private boolean deshabilitaAgregar;
    private boolean nuevoProceso;
    private boolean nuevoProcesoDetalle;
    
    private String opcionCD;

    /**
     * Initialize the concrete ProcesoContable controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    public ProcesoContableController() {
        // Inform the Abstract parent controller of the concrete ProcesoContable?cap_first Entity
        super(ProcesoContable.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        setItemsProcesoContable(ejbFacade.findAll());

        /* FacesContext context = FacesContext.getCurrentInstance();
         codigoEstadoComController = context.getApplication().evaluateExpressionGet(context, "#{estadoComprobanteController}", EstadoComprobanteController.class);*/
    }

    public void nuevo(ActionEvent event) {
        this.inicarObjetos();
        //iniciar boolean
        this.nuevoProceso = true;
        this.nuevoProcesoDetalle = true;
    }

    public void editar(ActionEvent event) {
        this.inicarObjetos();
        //iniciar boolean
        this.nuevoProceso = false;
        this.nuevoProcesoDetalle = false;      //Cargar Datos Detalle   

        
        this.itemsProcesoContableDetalle = ejbFacadeProcesoContableDetalle.getItemsProcesoDetalle(getProcesoContableSel().getCodigo());

    }

    //METODOS PERSONALIZADOS
    public void guardarProcesoContableDetalle() {
        this.guardarProcesoContable();
        int i = 1;
        String msg = null;
        if (isNuevoProcesoDetalle()) {
            if (getItemsProcesoContableDetalle() != null) {
                for (ProcesoContableDetalle pcd : getItemsProcesoContableDetalle()) {
                    pcd.getProcesoContableDetallePK().setCodigoProceso(getProcesoContable().getCodigo());
                    pcd.setProcesoContable(procesoContable);
                    i++;
                    pcd.setLinea(Short.parseShort(String.valueOf(i)));
                    ejbFacadeProcesoContableDetalle.create(pcd);
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                    this.itemsProcesoContable = this.ejbFacade.findAll();
                }
            }
        } else {

            for (ProcesoContableDetalle pcd : getItemsProcesoContableDetalle()) {

                if (pcd.getProcesoContableDetallePK().getCodigoProceso() == 0) {
                    pcd.getProcesoContableDetallePK().setCodigoProceso(getProcesoContableSel().getCodigo());
                    pcd.setProcesoContable(procesoContableSel);
                    pcd.setLinea(Short.parseShort(String.valueOf(i)));
                    ejbFacadeProcesoContableDetalle.create(pcd);
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                    this.itemsProcesoContable = this.ejbFacade.findAll();
                } else {
                    ejbFacadeProcesoContableDetalle.edit(pcd);
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                }
                i++;
            }
        }

        if (msg != null) {

            MuestraMensaje.addSatisfactorio(msg);

        }

    }

    public void guardarProcesoContable() {
        if (isNuevoProceso()) {
            this.procesoContable.setCodigoEstadoCom(this.procesoContable.getEstadoComprobante().getCodigo());
            ejbFacade.create(procesoContable);
        } else {
            this.procesoContableSel.setCodigoEstadoCom(this.procesoContableSel.getEstadoComprobante().getCodigo());
            ejbFacade.edit(procesoContableSel);
        }
    }

    public void agregarProcesoDetalle() throws InstantiationException, InvocationTargetException, NoSuchMethodException {

        setOpcionCD(null);
        this.procesoContableDetallePK = new ProcesoContableDetallePK(Long.parseLong("0"), getProcesoDetalle().getPlanDeCuenta().getPlanDeCuentaPK().getCuentaContable(), getProcesoDetalle().getPlanDeCuenta().getPlanDeCuentaPK().getCodigoTipoPlan());
        getProcesoDetalle().setProcesoContableDetallePK(procesoContableDetallePK);
        getProcesoDetalle().setLinea(Short.parseShort("1"));
        try {
            if(getProcesoDetalle().getTipo()== 'C')
            {
                setOpcionCD("Credito");
            }else{setOpcionCD("Debito");}
            
            this.getItemsProcesoContableDetalle().add((ProcesoContableDetalle) BeanUtils.cloneBean(getProcesoDetalle()));
            this.setDeshabilitaAgregar(true);
        } catch (IllegalAccessException e) {

        }

    }

    public void buscaProcesoDetalle() {

        String msg = null;
        boolean existe = false;
        if (!this.getItemsProcesoContableDetalle().isEmpty()) {
            for (ProcesoContableDetalle pt : this.getItemsProcesoContableDetalle()) {

                if (pt.getProcesoContableDetallePK().getCuentaContable().equals(getProcesoDetalle().getPlanDeCuenta().getPlanDeCuentaPK().getCuentaContable())) {
                    existe = true;
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaIngresada");
                    break;
                } else {
                    existe = false;
                }
            }
        }

        if (existe) {
            this.setDeshabilitaAgregar(true);
            MuestraMensaje.addError(msg);
        } else {
            this.setDeshabilitaAgregar(false);
        }
    }

    public void quitaProcesoDetalle() {
        boolean registrado = false;

        setProcesoDetalleReg(ejbFacadeProcesoContableDetalle.find(getProcesoDetalleSel().getProcesoContableDetallePK()));

        if (getProcesoDetalleReg() != null) {
            registrado = true;
        }
        if (registrado) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getItemsProcesoContableDetalle().remove(this.getProcesoDetalleSel());
        }
    }

    public void eliminaProcesoContable(ActionEvent event) {
        if (this.procesoContableSel != null) {
            try {
                this.procesoContableSel.setEliminado('S');
                this.ejbFacade.edit(this.procesoContableSel);
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.itemsProcesoContable = this.ejbFacade.findAll();
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"procesoContableController", "eliminaProcesoContable", CapturaError.getErrorException(ex)});
            }
        }

    }

    /**
     * @return the planDeCuenta
     */
    public void inicarObjetos() {
        this.setItemsPlanDeCuenta(ejbFacadePlanDeCuentasIfip.getItemsPlanCuentaVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
        this.setItemsEstadoComprobantes(ejbFacadeEstadoComprobante.findAll());
        this.setProcesoDetalleSel(new ProcesoContableDetalle());
        this.setProcesoContable(new ProcesoContable());
        this.setProcesoDetalle(new ProcesoContableDetalle());
        this.setCodigoEstadoCom(new EstadoComprobante());
        //iniciar Lista
        this.setItemsProcesoContableDetalle(new ArrayList<ProcesoContableDetalle>());
        setDeshabilitaAgregar(true);

    }

    public ProcesoContableFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ProcesoContableFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public ProcesoContableDetalleFacade getEjbFacadeProcesoContableDetalle() {
        return ejbFacadeProcesoContableDetalle;
    }

    public void setEjbFacadeProcesoContableDetalle(ProcesoContableDetalleFacade ejbFacadeProcesoContableDetalle) {
        this.ejbFacadeProcesoContableDetalle = ejbFacadeProcesoContableDetalle;
    }

    ;
    public PlanDeCuentaIfipFacade getEjbFacadePlanDeCuentasIfip() {
        return ejbFacadePlanDeCuentasIfip;
    }

    public void setEjbFacadePlanDeCuentasIfip(PlanDeCuentaIfipFacade ejbFacadePlanDeCuentasIfip) {
        this.ejbFacadePlanDeCuentasIfip = ejbFacadePlanDeCuentasIfip;
    }

    public PlanDeCuentaFacade getEjbFacadePlanDeCuenta() {
        return ejbFacadePlanDeCuenta;
    }

    public void setEjbFacadePlanDeCuenta(PlanDeCuentaFacade ejbFacadePlanDeCuenta) {
        this.ejbFacadePlanDeCuenta = ejbFacadePlanDeCuenta;
    }

    public ProcesoContable getProcesoContable() {
        return procesoContable;
    }

    public void setProcesoContable(ProcesoContable procesoContable) {
        this.procesoContable = procesoContable;
    }

    /**
     * @return the procesoDetalle
     */
    public ProcesoContableDetalle getProcesoDetalle() {
        return procesoDetalle;
    }

    /**
     * @param procesoDetalle the procesoDetalle to set
     */
    public void setProcesoDetalle(ProcesoContableDetalle procesoDetalle) {
        this.procesoDetalle = procesoDetalle;
    }

    public void crearProcesoContable(ActionEvent actionEvent) {
        procesoContable.setCodigoEstadoCom(null);

    }

    /**
     * @return the listaProcesoContable
     */
    public List<ProcesoContable> getListaProcesoContable() {
        return getItemsProcesoContable();
    }

    /**
     * @param listaProcesoContable the listaProcesoContable to set
     */
    public void setListaProcesoContable(List<ProcesoContable> listaProcesoContable) {
        this.setItemsProcesoContable(listaProcesoContable);
    }

    public PlanDeCuenta getPlanDeCuenta() {
        return planDeCuenta;
    }

    /**
     * @param planDeCuenta the planDeCuenta to set
     */
    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
        this.planDeCuenta = planDeCuenta;
    }

    /**
     * @return the itemsEstadoComprobantes
     */
    public List<EstadoComprobante> getItemsEstadoComprobantes() {
        return itemsEstadoComprobantes;
    }

    /**
     * @param itemsEstadoComprobantes the itemsEstadoComprobantes to set
     */
    public void setItemsEstadoComprobantes(List<EstadoComprobante> itemsEstadoComprobantes) {
        this.itemsEstadoComprobantes = itemsEstadoComprobantes;
    }

    /**
     * @return the codigoEstadoCom
     */
    public EstadoComprobante getCodigoEstadoCom() {
        return codigoEstadoCom;
    }

    /**
     * @param codigoEstadoCom the codigoEstadoCom to set
     */
    public void setCodigoEstadoCom(EstadoComprobante codigoEstadoCom) {
        this.codigoEstadoCom = codigoEstadoCom;
    }

    /**
     * @return the itemsPlanDeCuenta
     */
    public List<PlanDeCuenta> getItemsPlanDeCuenta() {
        return itemsPlanDeCuenta;
    }

    /**
     * @param itemsPlanDeCuenta the itemsPlanDeCuenta to set
     */
    public void setItemsPlanDeCuenta(List<PlanDeCuenta> itemsPlanDeCuenta) {
        this.itemsPlanDeCuenta = itemsPlanDeCuenta;
    }

    /**
     * @return the itemsProcesoContableDetalle
     */
    public List<ProcesoContableDetalle> getItemsProcesoContableDetalle() {
        return itemsProcesoContableDetalle;
    }

    /**
     * @param itemsProcesoContableDetalle the itemsProcesoContableDetalle to set
     */
    public void setItemsProcesoContableDetalle(List<ProcesoContableDetalle> itemsProcesoContableDetalle) {
        this.itemsProcesoContableDetalle = itemsProcesoContableDetalle;
    }

    /**
     * @return the procesoDetalleSel
     */
    public ProcesoContableDetalle getProcesoDetalleSel() {
        return procesoDetalleSel;
    }

    /**
     * @param procesoDetalleSel the procesoDetalleSel to set
     */
    public void setProcesoDetalleSel(ProcesoContableDetalle procesoDetalleSel) {
        this.procesoDetalleSel = procesoDetalleSel;
    }

    /**
     * @return the procesoContableDetallePK
     */
    public ProcesoContableDetallePK getProcesoContableDetallePK() {
        return procesoContableDetallePK;
    }

    /**
     * @param procesoContableDetallePK the procesoContableDetallePK to set
     */
    public void setProcesoContableDetallePK(ProcesoContableDetallePK procesoContableDetallePK) {
        this.procesoContableDetallePK = procesoContableDetallePK;
    }

    /**
     * @return the itemsProcesosContableDetallesReg
     */
    public List<ProcesoContableDetalle> getItemsProcesosContableDetallesReg() {
        return itemsProcesosContableDetallesReg;
    }

    /**
     * @param itemsProcesosContableDetallesReg the
     * itemsProcesosContableDetallesReg to set
     */
    public void setItemsProcesosContableDetallesReg(List<ProcesoContableDetalle> itemsProcesosContableDetallesReg) {
        this.itemsProcesosContableDetallesReg = itemsProcesosContableDetallesReg;
    }

    /**
     * @return the procesoDetalleReg
     */
    public ProcesoContableDetalle getProcesoDetalleReg() {
        return procesoDetalleReg;
    }

    /**
     * @param procesoDetalleReg the procesoDetalleReg to set
     */
    public void setProcesoDetalleReg(ProcesoContableDetalle procesoDetalleReg) {
        this.procesoDetalleReg = procesoDetalleReg;
    }

    /**
     * @return the deshabilitaAgregar
     */
    public boolean isDeshabilitaAgregar() {
        return deshabilitaAgregar;
    }

    /**
     * @param deshabilitaAgregar the deshabilitaAgregar to set
     */
    public void setDeshabilitaAgregar(boolean deshabilitaAgregar) {
        this.deshabilitaAgregar = deshabilitaAgregar;
    }

    /**
     * @return the nuevoProceso
     */
    public boolean isNuevoProceso() {
        return nuevoProceso;
    }

    /**
     * @param nuevoProceso the nuevoProceso to set
     */
    public void setNuevoProceso(boolean nuevoProceso) {
        this.nuevoProceso = nuevoProceso;
    }

    /**
     * @return the nuevoProcesoDetalle
     */
    public boolean isNuevoProcesoDetalle() {
        return nuevoProcesoDetalle;
    }

    /**
     * @param nuevoProcesoDetalle the nuevoProcesoDetalle to set
     */
    public void setNuevoProcesoDetalle(boolean nuevoProcesoDetalle) {
        this.nuevoProcesoDetalle = nuevoProcesoDetalle;
    }

    /**
     * @return the procesoContableSel
     */
    public ProcesoContable getProcesoContableSel() {
        return procesoContableSel;
    }

    /**
     * @param procesoContableSel the procesoContableSel to set
     */
    public void setProcesoContableSel(ProcesoContable procesoContableSel) {
        this.procesoContableSel = procesoContableSel;
    }

    /**
     * @return the itemsProcesoContable
     */
    public List<ProcesoContable> getItemsProcesoContable() {
        return itemsProcesoContable;
    }

    /**
     * @param itemsProcesoContable the itemsProcesoContable to set
     */
    public void setItemsProcesoContable(List<ProcesoContable> itemsProcesoContable) {
        this.itemsProcesoContable = itemsProcesoContable;
    }

    /**
     * @return the opcionCD
     */
    public String getOpcionCD() {
        return opcionCD;
    }

    /**
     * @param opcionCD the opcionCD to set
     */
    public void setOpcionCD(String opcionCD) {
        this.opcionCD = opcionCD;
    }

}
