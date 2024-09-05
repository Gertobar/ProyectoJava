package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.Transaccion;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.contables.ProcesoContable;
import ec.renafipse.mks.modelo.contables.ProcesoContableConTraPro;
import ec.renafipse.mks.modelo.contables.ProcesoContableConTraProPK;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionFacade;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionProFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.contables.ProcesoContableConTraProFacade;
import ec.renafipse.mks.negocio.contables.ProcesoContableFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "procesoContableConTraProController")
@ViewScoped
public class ProcesoContableConTraProController extends AbstractController<ProcesoContableConTraPro> implements Serializable {

    @EJB
    private ProcesoContableConTraProFacade ejbFacade;

    @EJB
    private ProcesoContableFacade ejbFacadeProcesoContable;

    @EJB
    private ProductoIfipFacade ejbFacadeProductoIfip;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ConceptoTransaccionFacade ejbFacadeConceptoTransaccion;

    @EJB
    private ConceptoTransaccionProFacade ejbFacadeConceptoTransaccionPro;

    //Lista en este proceso
    private DualListModel<ProcesoContable> itemDualListModelProcesosContable;

    //todos
    private List<ProcesoContable> itemsProcesosContables;
    private List<ProcesoContable> existentesProcesosContables;
    private List<ProcesoContable> faltantesProcesosContables;
    private List<ProcesoContableConTraPro> itemsProcesoContableConTraPro;
    private List<ProcesoContableConTraPro> itemsProcesoContableConTraProExiste;
    

    //lista existente Por concepto
    private List<Moneda> itemsMonedas;
    private List<ProductoIfip> itemProductosifip;
    private List<Transaccion> itemTransacciones;
    private List<ConceptoTransaccionPro> itemsConceptosTransacciones;

    //Lista Procesos Contable concepto transaccion Producto
    private List<ProcesoContableConTraPro> existentesProcesosContablesConTraPro;
    //lista la cargar los procesos con el campo eliminado 'S'
    private List<ProcesoContableConTraPro> existentesProcesosContablesConTraProTodos;
    // objetos 
    private ProcesoContableConTraPro procesoContableConTranPro;
    private Moneda monedaActul;

    private ProductoIfip productoActualIfip;
    private Transaccion transaccionActual;
    private ConceptoTransaccionPro conceptoTransaccionActual;
    private ProcesoContable procesoContableActual;
    private ProcesoContable procesoContable;
    private ProcesoContable procesoContableSel;
    private ProcesoContableConTraPro procesoContableConTraPro;

    private ProcesoContableConTraPro procesoContableConTraProSel;

    private Usuario usuario;

    //variables Boolean
    private boolean nuevoProcesoTransaccion;

    /**
     * Initialize the concrete ProcesoContableConTraPro controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        this.iniciaObjetos();

    }

    public ProcesoContableConTraProController() {
        // Inform the Abstract parent controller of the concrete ProcesoContableConTraPro?cap_first Entity
        super(ProcesoContableConTraPro.class);

    }

    public List<Moneda> getItemsMonedas() {
        try {
            itemsMonedas = this.getEjbFacadeIfipMoneda().getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
        } catch (Exception e) {
        }
        return itemsMonedas;

    }

    public void iniciaObjetos() {

        setItemProductosifip(new ArrayList<ProductoIfip>());
        setItemTransacciones(new ArrayList<Transaccion>());
        setItemsConceptosTransacciones(new ArrayList<ConceptoTransaccionPro>());

        setUsuario(new Usuario());

        setItemDualListModelProcesosContable(new DualListModel<ProcesoContable>());
        setItemsProcesosContables(new ArrayList<ProcesoContable>());
        setExistentesProcesosContables(new ArrayList<ProcesoContable>());
        setFaltantesProcesosContables(new ArrayList<ProcesoContable>());

        setExistentesProcesosContablesConTraPro(new ArrayList<ProcesoContableConTraPro>());
        setExistentesProcesosContablesConTraProTodos(new ArrayList<ProcesoContableConTraPro>());

        setNuevoProcesoTransaccion(true);
        setProcesoContableConTranPro(new ProcesoContableConTraPro());

    }

    //Cuando Se Cambia La Moneda carga los productos
    public void cambiaMoneda() {
        String msg = null;

        getExistentesProcesosContables().clear();
        getFaltantesProcesosContables().clear();

        if (monedaActul == null) {
            itemProductosifip.clear();
            itemTransacciones.clear();
            itemsConceptosTransacciones.clear();
            productoActualIfip = null;
            transaccionActual = null;
            conceptoTransaccionActual = null;
            
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Moneda")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            setItemProductosifip(ejbFacadeProductoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), getMonedaActul().getCodigo(), 'N'));
            //System.out.println("Codigo Ifip " + ActivacionUsuario.getCodigoIfip());
            //System.out.println("Codigo Moneda " + monedaActul.getCodigo());

        }
        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());

    }

    //Cuando selecciona o cambia el producto se cargan las transacciones
    public void cambiaProducto() {
        String msg = null;

        getExistentesProcesosContables().clear();
        getFaltantesProcesosContables().clear();

        if (productoActualIfip == null) {
            itemTransacciones.clear();
            itemsConceptosTransacciones.clear();
            transaccionActual = null;
            conceptoTransaccionActual = null;
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Producto")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            transaccionActual = null;
            conceptoTransaccionActual = null;
            this.setItemTransacciones(this.getEjbFacadeConceptoTransaccionPro().getItemsTransaccionesPermisosRol(this.productoActualIfip.getProductoIfipPK().getCodigoTipoProducto(), this.monedaActul.getCodigo(), 'N'));
            itemsConceptosTransacciones.clear();
        }
        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());

    }

    //cambia la transaccion Carga los conceptos transacciones
    public void cambiaTransaccion() {
        String msg = null;

        getExistentesProcesosContables().clear();
        getFaltantesProcesosContables().clear();

        if (transaccionActual == null) {
            itemsConceptosTransacciones.clear();
            conceptoTransaccionActual = null;
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Transaccion")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            conceptoTransaccionActual = null;
            this.setItemsConceptosTransacciones(this.ejbFacadeConceptoTransaccionPro.getItemsContabilizaExistentesConceptoTransaccionProIfi(this.productoActualIfip.getProductoIfipPK().getCodigoTipoProducto(), this.monedaActul.getCodigo(), this.transaccionActual.getCodigo(), 'N', 'S'));
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());

    }

    public void cambiaConceptoTransaccion() {
        String msg = null;

        getExistentesProcesosContables().clear();
        getFaltantesProcesosContables().clear();
        getItemsProcesosContables().clear();

        if (conceptoTransaccionActual == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        }
        if (msg != null) {
            MuestraMensaje.addError(msg);
//            this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());

            return;
        }

        getExistentesProcesosContablesConTraPro().clear();
        setItemsProcesosContables(ejbFacadeProcesoContable.getItemsProcesosContablesEliminado('N'));
        setItemsProcesosContables(ejbFacadeProcesoContable.findAll());
        
        //System.out.println(productoActualIfip.getProductoIfipPK().getCodigoTipoProducto());
        setExistentesProcesosContablesConTraPro(ejbFacade.getItemsProcesosContablesExistentesMonedaTipoProductoTransaccionConcepto(getMonedaActul().getCodigo(), productoActualIfip.getProductoIfipPK().getCodigoTipoProducto(), transaccionActual.getCodigo(), conceptoTransaccionActual.getConceptoTransaccionProPK().getCodigoConcepto(), 'N'));
        

//        boolean existe = true;
//        if (!getExistentesProcesosContablesConTraPro().isEmpty()) {
//
//            for (ProcesoContable pcall : getItemsProcesosContables()) {
//
//                for (ProcesoContableConTraPro pcexistente : getExistentesProcesosContablesConTraPro()) {
//
//                    Long codigoProcesoRelacion = pcexistente.getProcesoContableConTraProPK().getCodigoProceso();
//
//                    if (pcall.getCodigo().longValue() == codigoProcesoRelacion.longValue()) {
//                        existe = true;
//                        break;
//                    } else {
//
//                        existe = false;
//                    }
//                }
//                //asignacion a laslistas despues de la verificacion si existe o no en la relacion
//                if (existe == false) {
//                    getFaltantesProcesosContables().add(pcall);
//                    existe = true;
//                } else {
//                    getExistentesProcesosContables().add(pcall);
//                    existe = true;
//                }
//            }
//        } else {
//            for (ProcesoContable pcall : getItemsProcesosContables()) {
//                getFaltantesProcesosContables().add(pcall);
//            }
//        }
//
//        this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());
    }
    
    public void agregarProcesoContable(){
        for (ProcesoContableConTraPro proCon:existentesProcesosContablesConTraPro){
            if (procesoContable.getCodigo()==proCon.getProcesoContable().getCodigo()){
                String msg =ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProcesoContableAgregado");
                MuestraMensaje.addError(msg);
                return;
            }
        }
        ProcesoContableConTraPro nuevo=new ProcesoContableConTraPro(procesoContable.getCodigo(), productoActualIfip.getProducto().getProductoPK().getCodigoTipoProducto(), monedaActul.getCodigo(), conceptoTransaccionActual.getConceptoTransaccionProPK().getCodigoConcepto());
        nuevo.setProcesoContable(procesoContable);
        nuevo.setEliminado('N');
        nuevo.setFechaRegistro(new Date());
        nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
        existentesProcesosContablesConTraPro.add(nuevo);
    }
    
    public void quitarProcesoContable(){
        if (procesoContableConTraProSel!=null){
            existentesProcesosContablesConTraPro.remove(procesoContableConTraProSel);
        }
    }

    public void guardarProcesoContableTransaccion() {
        for (ProcesoContableConTraPro pro: ejbFacade.getItemsProcesosContablesExistentesMonedaTipoProductoTransaccionConcepto(getMonedaActul().getCodigo(), productoActualIfip.getProductoIfipPK().getCodigoTipoProducto(), transaccionActual.getCodigo(), conceptoTransaccionActual.getConceptoTransaccionProPK().getCodigoConcepto(), 'N')){
            pro.setEliminado('S');
            ejbFacade.edit(pro);
        }
        for (ProcesoContableConTraPro pro:existentesProcesosContablesConTraPro){
            ejbFacade.edit(pro);
        }
        
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
        MuestraMensaje.addSatisfactorio(msg);
//        try {
//            String msg = null;
//            System.out.println("Entro 1");
//            if (monedaActul == null || productoActualIfip == null || transaccionActual == null || conceptoTransaccionActual == null) {
//                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoEligo");
//                MuestraMensaje.addError(msg);
//                return;
//            }
//
//            System.out.println("Entro 2");
//
//            if (itemDualListModelProcesosContable != null) {
//                if (getItemDualListModelProcesosContable().getTarget().isEmpty() && getExistentesProcesosContablesConTraPro().isEmpty()) {
//                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListaExistentes");
//                    MuestraMensaje.addError(msg);
//                    System.out.println("Entro 3");
//                    return;
//                }
//
//                getExistentesProcesosContablesConTraProTodos().clear();
//                setExistentesProcesosContablesConTraProTodos(ejbFacade.getItemsProcesosContablesExistentesMonedaTipoProductoTransaccionConceptoTodos(getMonedaActul().getCodigo(), productoActualIfip.getProductoIfipPK().getCodigoTipoProducto(), transaccionActual.getCodigo(), conceptoTransaccionActual.getConceptoTransaccionProPK().getCodigoConcepto()));
//
//                if (!getExistentesProcesosContablesConTraProTodos().isEmpty()) {
//
//                    System.out.println("Entro 4");
//
//                    for (ProcesoContable source : itemDualListModelProcesosContable.getSource()) {
//                        for (ProcesoContableConTraPro existente : getExistentesProcesosContablesConTraPro()) {
//                            Long codigoProcesoExistente = existente.getProcesoContableConTraProPK().getCodigoProceso();
//                            if (source.getCodigo().longValue() == codigoProcesoExistente.longValue()) {
//                                existente.setEliminado('S');
//                                System.out.println("Guarda 1");
//                                ejbFacade.edit(existente);
//                                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//                            }
//                        }
//                    }
//                    boolean existe = true;
//
//                    System.out.println("Entro 6");
//                    for (ProcesoContable target : itemDualListModelProcesosContable.getTarget()) {
//                        for (ProcesoContableConTraPro existente : getExistentesProcesosContablesConTraProTodos()) {
//                            Long codigoProcesoExistente = existente.getProcesoContableConTraProPK().getCodigoProceso();
//                            if (target.getCodigo().longValue() == codigoProcesoExistente.longValue()) {
//                                existente.setEliminado('N');
//                                System.out.println("Guarda 2");
//                                ejbFacade.edit(existente);
//                                existe = true;
//                                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//                                break;
//                            } else {
//                                existe = false;
//                            }
//                        }
//
//                        System.out.println("Entro 8");
//                        if (existe == false) {
//
//                            ProcesoContableConTraProPK procesoContableConTraProPK = new ProcesoContableConTraProPK(
//                                    target.getCodigo(),
//                                    productoActualIfip.getProductoIfipPK().getCodigoTipoProducto(),
//                                    monedaActul.getCodigo(),
//                                    conceptoTransaccionActual.getConceptoTransaccionProPK().getCodigoConcepto());
//                            System.out.println("Crea 1");
//                            procesoContableConTranPro.setProcesoContableConTraProPK(procesoContableConTraProPK);
//                            procesoContableConTranPro.setEliminado('N');
//                            procesoContableConTranPro.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
//                            procesoContableConTranPro.setProcesoContable(target);
//                            procesoContableConTranPro.setConceptoTransaccionPro(conceptoTransaccionActual);
//                            procesoContableConTranPro.setFechaRegistro(new Date());
//                            ejbFacade.create(procesoContableConTranPro);
//                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//                            existe = true;
//                        }
//
//                    }
//                } else {
//                    System.out.println("Entro 9");
//                    //System.out.println("Aqui sin registros en la relacion");
//                    for (ProcesoContable target : itemDualListModelProcesosContable.getTarget()) {
//
//                        System.out.println("Crea 2");
//
//                        ProcesoContableConTraProPK procesoContableConTraProPK = new ProcesoContableConTraProPK(
//                                target.getCodigo(),
//                                productoActualIfip.getProductoIfipPK().getCodigoTipoProducto(),
//                                monedaActul.getCodigo(),
//                                conceptoTransaccionActual.getConceptoTransaccionProPK().getCodigoConcepto());
//                        procesoContableConTranPro.setProcesoContableConTraProPK(procesoContableConTraProPK);
//                        procesoContableConTranPro.setEliminado('N');
//                        procesoContableConTranPro.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
//                        procesoContableConTranPro.setProcesoContable(target);
//                        procesoContableConTranPro.setConceptoTransaccionPro(conceptoTransaccionActual);
//                        procesoContableConTranPro.setFechaRegistro(new Date());
//                        ejbFacade.create(procesoContableConTranPro);
//                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//
//                    }
//
//                }
//            }
//            getItemProductosifip().clear();
//            getItemTransacciones().clear();
//            getItemsConceptosTransacciones().clear();
//            getFaltantesProcesosContables().clear();
//            getExistentesProcesosContables().clear();
//            setMonedaActul(null);
//            setProductoActualIfip(null);
//            setTransaccionActual(null);
//            setConceptoTransaccionActual(null);
//            this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());
//            if (msg != null) {
//                MuestraMensaje.addSatisfactorio(msg);
//                msg = null;
//            }
//        } catch (Exception wx) {
//            wx.printStackTrace();
//        }

    }

    /**
     * @return the ejbFacade
     */
    public ProcesoContableConTraProFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ProcesoContableConTraProFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ejbFacadeConceptoTransaccion
     */
    public ConceptoTransaccionFacade getEjbFacadeConceptoTransaccion() {
        return ejbFacadeConceptoTransaccion;
    }

    /**
     * @param ejbFacadeConceptoTransaccion the ejbFacadeConceptoTransaccion to
     * set
     */
    public void setEjbFacadeConceptoTransaccion(ConceptoTransaccionFacade ejbFacadeConceptoTransaccion) {
        this.ejbFacadeConceptoTransaccion = ejbFacadeConceptoTransaccion;
    }

    /**
     * @return the ejbFacadeProcesoContable
     */
    public ProcesoContableFacade getEjbFacadeProcesoContable() {
        return ejbFacadeProcesoContable;
    }

    /**
     * @param ejbFacadeProcesoContable the ejbFacadeProcesoContable to set
     */
    public void setEjbFacadeProcesoContable(ProcesoContableFacade ejbFacadeProcesoContable) {
        this.ejbFacadeProcesoContable = ejbFacadeProcesoContable;
    }

    /**
     * @return the itemsMonedas
     */
    /**
     * @param itemsMonedas the itemsMonedas to set
     */
    public void setItemsMonedas(List<Moneda> itemsMonedas) {
        this.itemsMonedas = itemsMonedas;
    }

    /**
     * @return the itemsProcesosContables
     */
    public List<ProcesoContable> getItemsProcesosContables() {
        return itemsProcesosContables;
    }

    /**
     * @param itemsProcesosContables the itemsProcesosContables to set
     */
    public void setItemsProcesosContables(List<ProcesoContable> itemsProcesosContables) {
        this.itemsProcesosContables = itemsProcesosContables;
    }

    /**
     * @return the ejbFacadeProductoIfip
     */
    public ProductoIfipFacade getEjbFacadeProductoIfip() {
        return ejbFacadeProductoIfip;
    }

    /**
     * @param ejbFacadeProductoIfip the ejbFacadeProductoIfip to set
     */
    public void setEjbFacadeProductoIfip(ProductoIfipFacade ejbFacadeProductoIfip) {
        this.ejbFacadeProductoIfip = ejbFacadeProductoIfip;
    }

    /**
     * @return the procesoContableConTranPro
     */
    public ProcesoContableConTraPro getProcesoContableConTranPro() {
        return procesoContableConTranPro;
    }

    /**
     * @param procesoContableConTranPro the procesoContableConTranPro to set
     */
    public void setProcesoContableConTranPro(ProcesoContableConTraPro procesoContableConTranPro) {
        this.procesoContableConTranPro = procesoContableConTranPro;
    }

    /**
     * @return the monedaActul
     */
    public Moneda getMonedaActul() {
        return monedaActul;
    }

    /**
     * @param monedaActul the monedaActul to set
     */
    public void setMonedaActul(Moneda monedaActul) {
        this.monedaActul = monedaActul;
    }

    /**
     * @return the conceptoTransaccionActual
     */
    /**
     * @return the procesoContableConTraProSel
     */
    public ProcesoContableConTraPro getProcesoContableConTraProSel() {
        return procesoContableConTraProSel;
    }

    /**
     * @param procesoContableConTraProSel the procesoContableConTraProSel to set
     */
    public void setProcesoContableConTraProSel(ProcesoContableConTraPro procesoContableConTraProSel) {
        this.procesoContableConTraProSel = procesoContableConTraProSel;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nuevoProcesoTransaccion
     */
    public boolean isNuevoProcesoTransaccion() {
        return nuevoProcesoTransaccion;
    }

    /**
     * @param nuevoProcesoTransaccion the nuevoProcesoTransaccion to set
     */
    public void setNuevoProcesoTransaccion(boolean nuevoProcesoTransaccion) {
        this.nuevoProcesoTransaccion = nuevoProcesoTransaccion;
    }

    /**
     * @return the itemDualListModelProcesosContable
     */
    public DualListModel<ProcesoContable> getItemDualListModelProcesosContable() {
        return itemDualListModelProcesosContable;
    }

    /**
     * @param itemDualListModelProcesosContable the
     * itemDualListModelProcesosContable to set
     */
    public void setItemDualListModelProcesosContable(DualListModel<ProcesoContable> itemDualListModelProcesosContable) {
        this.itemDualListModelProcesosContable = itemDualListModelProcesosContable;
    }

    /**
     * @return the transaccionActual
     */
    public Transaccion getTransaccionActual() {
        return transaccionActual;
    }

    /**
     * @param transaccionActual the transaccionActual to set
     */
    public void setTransaccionActual(Transaccion transaccionActual) {
        this.transaccionActual = transaccionActual;
    }

    /**
     * @return the ejbFacadeConceptoTransaccionPro
     */
    public ConceptoTransaccionProFacade getEjbFacadeConceptoTransaccionPro() {
        return ejbFacadeConceptoTransaccionPro;
    }

    /**
     * @param ejbFacadeConceptoTransaccionPro the
     * ejbFacadeConceptoTransaccionPro to set
     */
    public void setEjbFacadeConceptoTransaccionPro(ConceptoTransaccionProFacade ejbFacadeConceptoTransaccionPro) {
        this.ejbFacadeConceptoTransaccionPro = ejbFacadeConceptoTransaccionPro;
    }

    /**
     * @return the itemTransacciones
     */
    public List<Transaccion> getItemTransacciones() {
        return itemTransacciones;
    }

    /**
     * @param itemTransacciones the itemTransacciones to set
     */
    public void setItemTransacciones(List<Transaccion> itemTransacciones) {
        this.itemTransacciones = itemTransacciones;
    }

    /**
     * @return the procesoContableActual
     */
    public ProcesoContable getProcesoContableActual() {
        return procesoContableActual;
    }

    /**
     * @param procesoContableActual the procesoContableActual to set
     */
    public void setProcesoContableActual(ProcesoContable procesoContableActual) {
        this.procesoContableActual = procesoContableActual;
    }

    /**
     * @return the itemProductosifip
     */
    public List<ProductoIfip> getItemProductosifip() {
        return itemProductosifip;
    }

    /**
     * @param itemProductosifip the itemProductosifip to set
     */
    public void setItemProductosifip(List<ProductoIfip> itemProductosifip) {
        this.itemProductosifip = itemProductosifip;
    }

    /**
     * @return the productoActualIfip
     */
    public ProductoIfip getProductoActualIfip() {
        return productoActualIfip;
    }

    /**
     * @param productoActualIfip the productoActualIfip to set
     */
    public void setProductoActualIfip(ProductoIfip productoActualIfip) {
        this.productoActualIfip = productoActualIfip;
    }

    /**
     * @return the ejbFacadeIfipMoneda
     */
    public IfipMonedaFacade getEjbFacadeIfipMoneda() {
        return ejbFacadeIfipMoneda;
    }

    /**
     * @param ejbFacadeIfipMoneda the ejbFacadeIfipMoneda to set
     */
    public void setEjbFacadeIfipMoneda(IfipMonedaFacade ejbFacadeIfipMoneda) {
        this.ejbFacadeIfipMoneda = ejbFacadeIfipMoneda;
    }

    /**
     * @return the conceptoTransaccionActual
     */
    public ConceptoTransaccionPro getConceptoTransaccionActual() {
        return conceptoTransaccionActual;
    }

    /**
     * @param conceptoTransaccionActual the conceptoTransaccionActual to set
     */
    public void setConceptoTransaccionActual(ConceptoTransaccionPro conceptoTransaccionActual) {
        this.conceptoTransaccionActual = conceptoTransaccionActual;
    }

    /**
     * @return the itemsConceptosTransacciones
     */
    public List<ConceptoTransaccionPro> getItemsConceptosTransacciones() {
        return itemsConceptosTransacciones;
    }

    /**
     * @param itemsConceptosTransacciones the itemsConceptosTransacciones to set
     */
    public void setItemsConceptosTransacciones(List<ConceptoTransaccionPro> itemsConceptosTransacciones) {
        this.itemsConceptosTransacciones = itemsConceptosTransacciones;
    }

    /**
     * @return the existentesProcesosContables
     */
    public List<ProcesoContable> getExistentesProcesosContables() {
        return existentesProcesosContables;
    }

    /**
     * @param existentesProcesosContables the existentesProcesosContables to set
     */
    public void setExistentesProcesosContables(List<ProcesoContable> existentesProcesosContables) {
        this.existentesProcesosContables = existentesProcesosContables;
    }

    /**
     * @return the faltantesProcesosContables
     */
    public List<ProcesoContable> getFaltantesProcesosContables() {
        return faltantesProcesosContables;
    }

    /**
     * @param faltantesProcesosContables the faltantesProcesosContables to set
     */
    public void setFaltantesProcesosContables(List<ProcesoContable> faltantesProcesosContables) {
        this.faltantesProcesosContables = faltantesProcesosContables;
    }

    /**
     * @return the existentesProcesosContablesConTraPro
     */
    public List<ProcesoContableConTraPro> getExistentesProcesosContablesConTraPro() {
        return existentesProcesosContablesConTraPro;
    }

    /**
     * @param existentesProcesosContablesConTraPro the
     * existentesProcesosContablesConTraPro to set
     */
    public void setExistentesProcesosContablesConTraPro(List<ProcesoContableConTraPro> existentesProcesosContablesConTraPro) {
        this.existentesProcesosContablesConTraPro = existentesProcesosContablesConTraPro;
    }

    /**
     * @return the existentesProcesosContablesConTraProTodos
     */
    public List<ProcesoContableConTraPro> getExistentesProcesosContablesConTraProTodos() {
        return existentesProcesosContablesConTraProTodos;
    }

    /**
     * @param existentesProcesosContablesConTraProTodos the
     * existentesProcesosContablesConTraProTodos to set
     */
    public void setExistentesProcesosContablesConTraProTodos(List<ProcesoContableConTraPro> existentesProcesosContablesConTraProTodos) {
        this.existentesProcesosContablesConTraProTodos = existentesProcesosContablesConTraProTodos;
    }

    /**
     * @return the itemsProcesoContableConTraPro
     */
    public List<ProcesoContableConTraPro> getItemsProcesoContableConTraPro() {
        return itemsProcesoContableConTraPro;
    }

    /**
     * @param itemsProcesoContableConTraPro the itemsProcesoContableConTraPro to set
     */
    public void setItemsProcesoContableConTraPro(List<ProcesoContableConTraPro> itemsProcesoContableConTraPro) {
        this.itemsProcesoContableConTraPro = itemsProcesoContableConTraPro;
    }

    /**
     * @return the procesoContable
     */
    public ProcesoContable getProcesoContable() {
        return procesoContable;
    }

    /**
     * @param procesoContable the procesoContable to set
     */
    public void setProcesoContable(ProcesoContable procesoContable) {
        this.procesoContable = procesoContable;
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
     * @return the procesoContableConTraPro
     */
    public ProcesoContableConTraPro getProcesoContableConTraPro() {
        return procesoContableConTraPro;
    }

    /**
     * @param procesoContableConTraPro the procesoContableConTraPro to set
     */
    public void setProcesoContableConTraPro(ProcesoContableConTraPro procesoContableConTraPro) {
        this.procesoContableConTraPro = procesoContableConTraPro;
    }

    /**
     * @return the itemsProcesoContableConTraProExiste
     */
    public List<ProcesoContableConTraPro> getItemsProcesoContableConTraProExiste() {
        return itemsProcesoContableConTraProExiste;
    }

    /**
     * @param itemsProcesoContableConTraProExiste the itemsProcesoContableConTraProExiste to set
     */
    public void setItemsProcesoContableConTraProExiste(List<ProcesoContableConTraPro> itemsProcesoContableConTraProExiste) {
        this.itemsProcesoContableConTraProExiste = itemsProcesoContableConTraProExiste;
    }

    /**
     * @return the productoActualIfip
     */
}
