package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import static ec.renafipse.mks.capas.sesion.ActivacionUsuario.setUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.IngresoEgreso;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.contables.ProcesoContable;
import ec.renafipse.mks.modelo.contables.ProcesoContableIngEgrMon;
import ec.renafipse.mks.modelo.contables.ProcesoContableIngEgrMonPK;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.cajas.IngresoEgresoMonedaFacade;
import ec.renafipse.mks.negocio.contables.ProcesoContableFacade;
import ec.renafipse.mks.negocio.contables.ProcesoContableIngEgrMonFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "procesoContableIngEgrMonController")
@ViewScoped
public class ProcesoContableIngEgrMonController extends AbstractController<ProcesoContableIngEgrMon> {

    @EJB
    private ProcesoContableIngEgrMonFacade ejbFacade;

    @EJB
    private ProcesoContableFacade ejbFacadeProcesoContable;

    @EJB
    private IngresoEgresoMonedaFacade ejbFacadeIngresoEgresoMoneda;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;
    // declaracion listas
    //Lista en este proceso
    private DualListModel<ProcesoContable> itemDualListModelProcesosContable;

    //todos
    private List<ProcesoContable> itemsProcesosContables;
    private List<ProcesoContable> existentesProcesosContables;
    private List<ProcesoContable> faltantesProcesosContables;
    private List<ProcesoContableIngEgrMon> existentesProcesoContableIngEgreMon;
    private List<ProcesoContableIngEgrMon> existentesProcesoContableIngEgreMonTodos;

    private List<Moneda> itemsMonedas;
    private List<IngresoEgreso> itemsIngresoEgreso;

    //declaracion de objetos
    private Moneda monedaActual;
    private IngresoEgreso ingresoEgresoActual;
    private ProcesoContableIngEgrMon procesoContableIngEgreMonActual;
    private ProcesoContable procesoContable;
    private char tipo;
    private boolean desabilitada;

    /**
     * Initialize the concrete ProcesoContableIngEgrMon controller bean. The
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

    public ProcesoContableIngEgrMonController() {
        // Inform the Abstract parent controller of the concrete ProcesoContableIngEgrMon?cap_first Entity
        super(ProcesoContableIngEgrMon.class);
    }

    //Metodos perzonalizados
    public void iniciaObjetos() {

        setUsuario(new Usuario());
        setItemDualListModelProcesosContable(new DualListModel<ProcesoContable>());
        setItemsProcesosContables(new ArrayList<ProcesoContable>());
        setExistentesProcesosContables(new ArrayList<ProcesoContable>());
        setFaltantesProcesosContables(new ArrayList<ProcesoContable>());
        setExistentesProcesoContableIngEgreMon(new ArrayList<ProcesoContableIngEgrMon>());
        setExistentesProcesoContableIngEgreMonTodos(new ArrayList<ProcesoContableIngEgrMon>());

        setProcesoContableIngEgreMonActual(new ProcesoContableIngEgrMon());
        getProcesoContableIngEgreMonActual().setFechaRegistro(new Date());
        setDesabilitada(true);
    }

    //cuando Cambia moneda
    public void cambiaMoneda() {
        String msg = null;

        getExistentesProcesosContables().clear();
        getFaltantesProcesosContables().clear();
        getItemsProcesosContables().clear();
        getExistentesProcesoContableIngEgreMon().clear();

        if (monedaActual == null) {
            itemsIngresoEgreso.clear();
            ingresoEgresoActual = null;
            setDesabilitada(true);
            setTipo(' ');
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Moneda")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            setDesabilitada(false);

        }
        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());

    }

    public void cambiaTipo() {

        String msg = null;

        getExistentesProcesosContables().clear();
        getFaltantesProcesosContables().clear();
        getItemsProcesosContables().clear();
        getExistentesProcesoContableIngEgreMon().clear();
        //System.out.println("tipo " + tipo);

        if (tipo == 'V') {
            itemsIngresoEgreso.clear();
            ingresoEgresoActual = null;
            //setDesabilitada(true);            
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Tipo")
                    + " " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            setItemsIngresoEgreso(ejbFacadeIngresoEgresoMoneda.getItemsIngresoEgreso(getMonedaActual().getCodigo(), 'N', getTipo(), 'S'));

        }
        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());
    }

    public void cambiaIngresoEgreso() {

        String msg = null;

        getExistentesProcesosContables().clear();
        getFaltantesProcesosContables().clear();
        getItemsProcesosContables().clear();

        if (ingresoEgresoActual == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        }
        if (msg != null) {
            MuestraMensaje.addError(msg);
            this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());
            return;
        }

        getExistentesProcesoContableIngEgreMon().clear();
        setItemsProcesosContables(ejbFacadeProcesoContable.findAll());

        setExistentesProcesoContableIngEgreMon(ejbFacade.getItemsProcesosContablesExistentesIngresoegresoMoneda(getMonedaActual().getCodigo(), getIngresoEgresoActual().getCodigo(), 'N'));

//        boolean existe = true;setItemsProcesosContables
//        if (!getExistentesProcesoContableIngEgreMon().isEmpty()) {
//
//            for (ProcesoContable pcall : getItemsProcesosContables()) {
//
//                for (ProcesoContableIngEgrMon pcexistente : getExistentesProcesoContableIngEgreMon()) {
//
//                    Long codigoProcesoRelacion = pcexistente.getProcesoContableIngEgrMonPK().getCodigoProceso();
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

    public void agregarProcesoContable() {
        for (ProcesoContableIngEgrMon proCon : existentesProcesoContableIngEgreMon) {
            if (procesoContable.getCodigo() == proCon.getProcesoContable().getCodigo()) {
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProcesoContableAgregado");
                MuestraMensaje.addError(msg);
                return;
            }
        }
        ProcesoContableIngEgrMon nuevo = new ProcesoContableIngEgrMon(procesoContable.getCodigo(), getIngresoEgresoActual().getCodigo(), getMonedaActual().getCodigo());
        nuevo.setProcesoContable(procesoContable);
        nuevo.setEliminado('N');
        nuevo.setFechaRegistro(new Date());
        nuevo.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
        getExistentesProcesoContableIngEgreMon().add(nuevo);
    }

    public void quitarProcesoContable() {
        if (procesoContableIngEgreMonActual != null) {
            existentesProcesoContableIngEgreMon.remove(procesoContableIngEgreMonActual);
        }
    }

    public void guardarProcesoContableTransaccion() {
        if (existentesProcesoContableIngEgreMon.isEmpty()) {
            //String msg = ResourceBundle.getBundle("/propiedadesEtiquetaBL").getString("NoExistenRegistros");
            MuestraMensaje.addError("No hay registros");
            return;
        }
        for (ProcesoContableIngEgrMon pro
                : ejbFacade.getItemsProcesosContablesExistentesIngresoegresoMoneda(
                        getMonedaActual().getCodigo(),
                        getIngresoEgresoActual().getCodigo(), 'N')) {
            pro.setEliminado('S');
            ejbFacade.edit(pro);
        }
        for (ProcesoContableIngEgrMon pro : existentesProcesoContableIngEgreMon) {
            ejbFacade.edit(pro);
        }

        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
        MuestraMensaje.addSatisfactorio(msg);
//        String msg = null;
//        //System.out.println("aqui moneda ");
//
//        if (monedaActual == null || tipo == 'V' || ingresoEgresoActual == null) {
//            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoEligo");
//            MuestraMensaje.addError(msg);
//            return;
//        }
//
//        if (itemDualListModelProcesosContable != null) {
//            if (getItemDualListModelProcesosContable().getTarget().isEmpty() && getExistentesProcesoContableIngEgreMon().isEmpty()) {
//                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ListaExistentes");
//                MuestraMensaje.addError(msg);
//                return;
//            }
//
//            getExistentesProcesoContableIngEgreMonTodos().clear();
//            setExistentesProcesoContableIngEgreMonTodos(ejbFacade.getItemsProcesosContablesExistentesIngresoegresoMonedaTodos(monedaActual.getCodigo(), getIngresoEgresoActual().getCodigo()));
//
//            if (!getExistentesProcesoContableIngEgreMonTodos().isEmpty()) {
//
//                for (ProcesoContable source : itemDualListModelProcesosContable.getSource()) {
//                    for (ProcesoContableIngEgrMon existente : getExistentesProcesoContableIngEgreMon()) {
//                        Long codigoProcesoExistente = existente.getProcesoContableIngEgrMonPK().getCodigoProceso();
//                        if (source.getCodigo().longValue() == codigoProcesoExistente.longValue()) {
//                            existente.setEliminado('S');
//                            ejbFacade.edit(existente);
//                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//                        }
//                    }
//                }
//                boolean existe = true;
//
//                for (ProcesoContable target : itemDualListModelProcesosContable.getTarget()) {
//                    for (ProcesoContableIngEgrMon existente : getExistentesProcesoContableIngEgreMonTodos()) {
//                        Long codigoProcesoExistente = existente.getProcesoContableIngEgrMonPK().getCodigoProceso();
//                        if (target.getCodigo().longValue() == codigoProcesoExistente.longValue()) {
//                            existente.setEliminado('N');
//                            ejbFacade.edit(existente);
//                            existe = true;
//                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//                            break;
//                        } else {
//                            existe = false;
//                        }
//                    }
//
//                    if (existe == false) {
//
//                        ProcesoContableIngEgrMonPK procesoContableIngEgrMonPK = new ProcesoContableIngEgrMonPK(
//                                target.getCodigo(),
//                                getIngresoEgresoActual().getCodigo(),
//                                monedaActual.getCodigo()
//                        );
//
//                        procesoContableIngEgreMonActual.setProcesoContableIngEgrMonPK(procesoContableIngEgrMonPK);
//                        procesoContableIngEgreMonActual.setEliminado('N');
//                        procesoContableIngEgreMonActual.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
//                        procesoContableIngEgreMonActual.setProcesoContable(target);
//                        procesoContableIngEgreMonActual.setFechaRegistro(new Date());
//                        ejbFacade.create(procesoContableIngEgreMonActual);
//                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//                        existe = true;
//                    }
//
//                }
//            } else {
//
//                for (ProcesoContable target : itemDualListModelProcesosContable.getTarget()) {
//
//                    ProcesoContableIngEgrMonPK procesoContableIngEgrMonPK = new ProcesoContableIngEgrMonPK(
//                            target.getCodigo(),
//                            getIngresoEgresoActual().getCodigo(),
//                            monedaActual.getCodigo()
//                    );
//
//                    procesoContableIngEgreMonActual.setProcesoContableIngEgrMonPK(procesoContableIngEgrMonPK);
//                    procesoContableIngEgreMonActual.setEliminado('N');
//                    procesoContableIngEgreMonActual.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
//                    procesoContableIngEgreMonActual.setProcesoContable(target);
//                    procesoContableIngEgreMonActual.setFechaRegistro(new Date());
//                    ejbFacade.create(procesoContableIngEgreMonActual);;
//                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
//
//                }
//
//            }
//        }
//
//        getFaltantesProcesosContables().clear();
//        getExistentesProcesosContables().clear();
//        setMonedaActual(null);
//        getItemsProcesosContables().clear();
//        setIngresoEgresoActual(null);
//        getItemsIngresoEgreso().clear();
//        setDesabilitada(true);
//        tipo = ' ';
//        this.itemDualListModelProcesosContable = new DualListModel<ProcesoContable>(this.getFaltantesProcesosContables(), this.getExistentesProcesosContables());
//        if (msg != null) {
//            MuestraMensaje.addSatisfactorio(msg);
//
//        }

    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProcesoContableIngEgrMonPK().setCodigoProceso(this.getSelected().getProcesoContable().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setProcesoContableIngEgrMonPK(new ec.renafipse.mks.modelo.contables.ProcesoContableIngEgrMonPK());
    }

    /**
     * @return the ejbFacade
     */
    public ProcesoContableIngEgrMonFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ProcesoContableIngEgrMonFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
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
     * @return the itemsMonedas
     */
    public List<Moneda> getItemsMonedas() {
        try {
            itemsMonedas = this.getEjbFacadeIfipMoneda().getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
        } catch (Exception e) {
        }
        return itemsMonedas;
    }

    /**
     * @param itemsMonedas the itemsMonedas to set
     */
    public void setItemsMonedas(List<Moneda> itemsMonedas) {
        this.itemsMonedas = itemsMonedas;
    }

    /**
     * @return the existentesProcesoContableIngEgreMon
     */
    public List<ProcesoContableIngEgrMon> getExistentesProcesoContableIngEgreMon() {
        return existentesProcesoContableIngEgreMon;
    }

    /**
     * @param existentesProcesoContableIngEgreMon the
     * existentesProcesoContableIngEgreMon to set
     */
    public void setExistentesProcesoContableIngEgreMon(List<ProcesoContableIngEgrMon> existentesProcesoContableIngEgreMon) {
        this.existentesProcesoContableIngEgreMon = existentesProcesoContableIngEgreMon;
    }

    /**
     * @return the existentesProcesoContableIngEgreMonTodos
     */
    public List<ProcesoContableIngEgrMon> getExistentesProcesoContableIngEgreMonTodos() {
        return existentesProcesoContableIngEgreMonTodos;
    }

    /**
     * @param existentesProcesoContableIngEgreMonTodos the
     * existentesProcesoContableIngEgreMonTodos to set
     */
    public void setExistentesProcesoContableIngEgreMonTodos(List<ProcesoContableIngEgrMon> existentesProcesoContableIngEgreMonTodos) {
        this.existentesProcesoContableIngEgreMonTodos = existentesProcesoContableIngEgreMonTodos;
    }

    /**
     * @return the monedaActual
     */
    public Moneda getMonedaActual() {
        return monedaActual;
    }

    /**
     * @param monedaActual the monedaActual to set
     */
    public void setMonedaActual(Moneda monedaActual) {
        this.monedaActual = monedaActual;
    }

    /**
     * @return the ingresoEgresoActual
     */
    public IngresoEgreso getIngresoEgresoActual() {
        return ingresoEgresoActual;
    }

    /**
     * @param ingresoEgresoActual the ingresoEgresoActual to set
     */
    public void setIngresoEgresoActual(IngresoEgreso ingresoEgresoActual) {
        this.ingresoEgresoActual = ingresoEgresoActual;
    }

    /**
     * @return the procesoContableIngEgreMonActual
     */
    public ProcesoContableIngEgrMon getProcesoContableIngEgreMonActual() {
        return procesoContableIngEgreMonActual;
    }

    /**
     * @param procesoContableIngEgreMonActual the
     * procesoContableIngEgreMonActual to set
     */
    public void setProcesoContableIngEgreMonActual(ProcesoContableIngEgrMon procesoContableIngEgreMonActual) {
        this.procesoContableIngEgreMonActual = procesoContableIngEgreMonActual;
    }

    /**
     * @return the itemsIngresoEgreso
     */
    public List<IngresoEgreso> getItemsIngresoEgreso() {
        return itemsIngresoEgreso;
    }

    /**
     * @param itemsIngresoEgreso the itemsIngresoEgreso to set
     */
    public void setItemsIngresoEgreso(List<IngresoEgreso> itemsIngresoEgreso) {
        this.itemsIngresoEgreso = itemsIngresoEgreso;
    }

    /**
     * @return the eliminado
     */
    /**
     * @return the desabilitada
     */
    public boolean isDesabilitada() {
        return desabilitada;
    }

    /**
     * @param desabilitada the desabilitada to set
     */
    public void setDesabilitada(boolean desabilitada) {
        this.desabilitada = desabilitada;
    }

    /**
     * @return the tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
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

}
