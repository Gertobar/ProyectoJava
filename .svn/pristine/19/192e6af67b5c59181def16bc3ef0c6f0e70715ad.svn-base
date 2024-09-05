package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.IngresoEgreso;
import ec.renafipse.mks.modelo.cajas.IngresoEgresoMoneda;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfip;
import ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfipPK;
import ec.renafipse.mks.negocio.cajas.IngresoEgresoMonedaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import ec.renafipse.mks.negocio.seguridades.RolIngresoEgresoIfipFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "rolIngresoEgresoIfipController")
@ViewScoped
public class RolIngresoEgresoIfipController extends AbstractController<RolIngresoEgresoIfip> implements Serializable {

    @EJB
    private RolIngresoEgresoIfipFacade ejbFacade;
    private RolController rolController;

    @EJB
    private IngresoEgresoMonedaFacade ejbFacadeIngresoEgresoMoneda;

    @EJB
    private RolFacade ejbFacadeRol;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    private IngresoEgresoMoneda ingresoEgresoMoneda;
    private RolIngresoEgresoIfip rolIngresoEgresoIfip;
    private RolIngresoEgresoIfipPK rolIngresoEgresoIfipPK;
    private IngresoEgreso ingresoEgreso;
    private Moneda moneda;
    private Rol rol;

    private Moneda ubicaMoneda;
    private Rol ubicaRol;
    private String ubicaEsIngreso;
    private String msg;

    private List<Rol> listaRol;
    private List<Moneda> listaMoneda;

    private List<IngresoEgresoMoneda> listaIngresoEgresoMonedaExistentes;
    private List<IngresoEgresoMoneda> listaIngresoEgresoMonedaExistentesRol;
    private List<IngresoEgresoMoneda> listaIngresoEgresoMonedaFaltantes;

    private DualListModel<IngresoEgresoMoneda> itemsIngresoEgresoMoneda;

    /**
     * Initialize the concrete RolIngresoEgresoIfip controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
        rolController = context.getApplication().evaluateExpressionGet(context, "#{rolController}", RolController.class);

        this.msg = null;
        this.ubicaRol = null;
        this.ubicaMoneda = null;
        this.setUbicaEsIngreso(null);

        this.rolIngresoEgresoIfipPK = new RolIngresoEgresoIfipPK();
        this.rolIngresoEgresoIfip = new RolIngresoEgresoIfip();

        this.listaRol = this.ejbFacadeRol.getItemsRol('N');
        this.listaMoneda = this.ejbFacadeMoneda.getItemsMonedas('N');

        this.itemsIngresoEgresoMoneda = new DualListModel<IngresoEgresoMoneda>();

    }

    public RolIngresoEgresoIfipController() {
        // Inform the Abstract parent controller of the concrete RolIngresoEgresoIfip?cap_first Entity
        super(RolIngresoEgresoIfip.class);
    }
    //LOGICA

    /**
     * Obtiene los ingresos o egresos
     */
    public void cambia() {
        boolean existe = false;        
        List<RolIngresoEgresoIfip> listaRolIngresoEgresoIfip;

        this.listaIngresoEgresoMonedaExistentes = new ArrayList<IngresoEgresoMoneda>();
        this.listaIngresoEgresoMonedaExistentesRol = new ArrayList<IngresoEgresoMoneda>();
        this.listaIngresoEgresoMonedaFaltantes = new ArrayList<IngresoEgresoMoneda>();

        if (this.ubicaMoneda == null && this.ubicaEsIngreso == null && this.ubicaRol == null) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneDatosFaltantes"));
            //MuestraMensaje.addAdvertencia(msg);
        } else if (this.ubicaMoneda != null && this.ubicaEsIngreso == null && this.ubicaRol == null) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneDatosFaltantes"));
            //MuestraMensaje.addAdvertencia(msg);
        } else if (this.ubicaMoneda != null && this.ubicaEsIngreso != null && this.ubicaRol == null) {
            this.setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneDatosFaltantes"));
            //MuestraMensaje.addAdvertencia(msg);
        } else if (this.ubicaMoneda != null && this.ubicaEsIngreso != null && this.ubicaRol != null) {
            
            this.listaIngresoEgresoMonedaExistentes = this.ejbFacadeIngresoEgresoMoneda.getItemsIngresoEgresoPermisosFaltantes(this.ubicaEsIngreso.charAt(0), 'N', 'S');
            this.listaIngresoEgresoMonedaExistentesRol = this.ejbFacade.getItemsIngresoEgresoPermisosExistentes(this.ubicaMoneda.getCodigo(), ActivacionUsuario.getCodigoIfip(), this.ubicaRol.getCodigo(), this.ubicaEsIngreso.charAt(0), 'S');

            for (IngresoEgresoMoneda iemEx : this.listaIngresoEgresoMonedaExistentes) {
                for (IngresoEgresoMoneda iemExRol : this.listaIngresoEgresoMonedaExistentesRol) {
                    if (iemEx.getIngresoEgresoMonedaPK().getCodigoIngresoEgreso() == iemExRol.getIngresoEgresoMonedaPK().getCodigoIngresoEgreso()) {
                        existe = true;
                    } else {
                        existe = false;
                    }
                    if (existe) {
                        break;
                    }
                }
                if (!existe) {
                    this.listaIngresoEgresoMonedaFaltantes.add(iemEx);
                } else {
                    listaRolIngresoEgresoIfip = this.ejbFacade.getItemsRolIngresoEgresoIfip(this.ubicaMoneda.getCodigo(), this.ubicaRol.getCodigo(), ActivacionUsuario.getCodigoIfip(), iemEx.getIngresoEgresoMonedaPK().getCodigoIngresoEgreso());
                    if (listaRolIngresoEgresoIfip.size() == 1) {
                        this.rolIngresoEgresoIfip = listaRolIngresoEgresoIfip.get(0);
                        if (String.valueOf(this.rolIngresoEgresoIfip.getEliminado()).equals("S")) {
                            this.listaIngresoEgresoMonedaFaltantes.add(iemEx);
                            this.listaIngresoEgresoMonedaExistentesRol.remove(iemEx);
                        }
                    }
                }

            }
            this.itemsIngresoEgresoMoneda = new DualListModel<IngresoEgresoMoneda>(this.listaIngresoEgresoMonedaFaltantes, this.listaIngresoEgresoMonedaExistentesRol);
        }
    }

    /**
     * Guarda los roles de ingresos y egresos de la ifip
     */
    public void guardarolIngresoEgresoIfip() {
        msg = null;
        List<RolIngresoEgresoIfip> listaRolIngresoEgresoIfip;

        for (IngresoEgresoMoneda iem : this.itemsIngresoEgresoMoneda.getSource()) {
            listaRolIngresoEgresoIfip = this.ejbFacade.getItemsRolIngresoEgresoIfip(this.ubicaMoneda.getCodigo(), this.ubicaRol.getCodigo(), ActivacionUsuario.getCodigoIfip(), iem.getIngresoEgresoMonedaPK().getCodigoIngresoEgreso());
            if (listaRolIngresoEgresoIfip.size() == 1) {
                RolIngresoEgresoIfip rolIngresoEgresoIfipSource = listaRolIngresoEgresoIfip.get(0);
                if (String.valueOf(rolIngresoEgresoIfipSource.getEliminado()).equals("N")) {
                    rolIngresoEgresoIfipSource.setEliminado('S');
                    this.ejbFacade.edit(rolIngresoEgresoIfipSource);
                }
            }
            if (listaRolIngresoEgresoIfip.size() > 1) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolIngresoEgresoIfip");
            }
        }

        if (msg == null) {
            for (IngresoEgresoMoneda iem : this.itemsIngresoEgresoMoneda.getTarget()) {
                listaRolIngresoEgresoIfip = this.ejbFacade.getItemsRolIngresoEgresoIfip(this.ubicaMoneda.getCodigo(), this.ubicaRol.getCodigo(), ActivacionUsuario.getCodigoIfip(), iem.getIngresoEgresoMonedaPK().getCodigoIngresoEgreso());
                if (listaRolIngresoEgresoIfip.size() == 1) {
                    RolIngresoEgresoIfip rolIngresoEgresoIfipTarget = listaRolIngresoEgresoIfip.get(0);
                    if (String.valueOf(rolIngresoEgresoIfipTarget.getEliminado()).equals("S")) {
                        rolIngresoEgresoIfipTarget.setEliminado('N');
                        this.ejbFacade.edit(rolIngresoEgresoIfipTarget);
                    }
                } else if (listaRolIngresoEgresoIfip.isEmpty()) {

                    this.rolIngresoEgresoIfip = new RolIngresoEgresoIfip();
                    this.rolIngresoEgresoIfipPK.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                    this.rolIngresoEgresoIfipPK.setCodigoIngresoEgreso(iem.getIngresoEgresoMonedaPK().getCodigoIngresoEgreso());
                    this.rolIngresoEgresoIfipPK.setCodigoMoneda(this.ubicaMoneda.getCodigo());
                    this.rolIngresoEgresoIfipPK.setCodigoRol(this.ubicaRol.getCodigo());
                    this.rolIngresoEgresoIfip.setRolIngresoEgresoIfipPK(rolIngresoEgresoIfipPK);
                    this.rolIngresoEgresoIfip.setIngresadoPor(ActivacionUsuario.getCodigoUsuario());
                    this.rolIngresoEgresoIfip.setFechaIngreso(new Date());
                    this.rolIngresoEgresoIfip.setEliminado('N');
                    this.ejbFacade.create(rolIngresoEgresoIfip);
                }

                if (listaRolIngresoEgresoIfip.size() > 1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolIngresoEgresoIfip");
                }
            }

            if (msg == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RolIngresoEgresoIfipGuardada");
                MuestraMensaje.addSatisfactorio(msg);
                init();
            } else {
                MuestraMensaje.addError(msg);
            }
        }

    }

    //FIN LOGICA
    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getRolIngresoEgresoIfipPK().setCodigoRol(this.getSelected().getRol().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setRolIngresoEgresoIfipPK(new ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfipPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        rolController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRol(ActionEvent event) {
        if (this.getSelected() != null && rolController.getSelected() == null) {
            rolController.setSelected(this.getSelected().getRol());
        }
    }

    /**
     * @return the ingresoEgreso
     */
    public IngresoEgreso getIngresoEgreso() {
        return ingresoEgreso;
    }

    /**
     * @param ingresoEgreso the ingresoEgreso to set
     */
    public void setIngresoEgreso(IngresoEgreso ingresoEgreso) {
        this.ingresoEgreso = ingresoEgreso;
    }

    /**
     * @return the rolIngresoEgresoIfip
     */
    public RolIngresoEgresoIfip getRolIngresoEgresoIfip() {
        return rolIngresoEgresoIfip;
    }

    /**
     * @param rolIngresoEgresoIfip the rolIngresoEgresoIfip to set
     */
    public void setRolIngresoEgresoIfip(RolIngresoEgresoIfip rolIngresoEgresoIfip) {
        this.rolIngresoEgresoIfip = rolIngresoEgresoIfip;
    }

    /**
     * @return the ubicaMoneda
     */
    public Moneda getUbicaMoneda() {
        return ubicaMoneda;
    }

    /**
     * @param ubicaMoneda the ubicaMoneda to set
     */
    public void setUbicaMoneda(Moneda ubicaMoneda) {
        this.ubicaMoneda = ubicaMoneda;
    }

    /**
     * @return the ubicaRol
     */
    public Rol getUbicaRol() {
        return ubicaRol;
    }

    /**
     * @param ubicaRol the ubicaRol to set
     */
    public void setUbicaRol(Rol ubicaRol) {
        this.ubicaRol = ubicaRol;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the listaRol
     */
    public List<Rol> getListaRol() {
        return listaRol;
    }

    /**
     * @param listaRol the listaRol to set
     */
    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    /**
     * @return the listaMoneda
     */
    public List<Moneda> getListaMoneda() {
        return listaMoneda;
    }

    /**
     * @param listaMoneda the listaMoneda to set
     */
    public void setListaMoneda(List<Moneda> listaMoneda) {
        this.listaMoneda = listaMoneda;
    }

    /**
     * @return the rolIngresoEgresoIfipPK
     */
    public RolIngresoEgresoIfipPK getRolIngresoEgresoIfipPK() {
        return rolIngresoEgresoIfipPK;
    }

    /**
     * @param rolIngresoEgresoIfipPK the rolIngresoEgresoIfipPK to set
     */
    public void setRolIngresoEgresoIfipPK(RolIngresoEgresoIfipPK rolIngresoEgresoIfipPK) {
        this.rolIngresoEgresoIfipPK = rolIngresoEgresoIfipPK;
    }

    /**
     * @return the ubicaEsIngreso
     */
    public String getUbicaEsIngreso() {
        return ubicaEsIngreso;
    }

    /**
     * @param ubicaEsIngreso the ubicaEsIngreso to set
     */
    public void setUbicaEsIngreso(String ubicaEsIngreso) {
        this.ubicaEsIngreso = ubicaEsIngreso;
    }

    /**
     * @return the ingresoEgresoMoneda
     */
    public IngresoEgresoMoneda getIngresoEgresoMoneda() {
        return ingresoEgresoMoneda;
    }

    /**
     * @param ingresoEgresoMoneda the ingresoEgresoMoneda to set
     */
    public void setIngresoEgresoMoneda(IngresoEgresoMoneda ingresoEgresoMoneda) {
        this.ingresoEgresoMoneda = ingresoEgresoMoneda;
    }

    /**
     * @return the listaIngresoEgresoMonedaExistentes
     */
    public List<IngresoEgresoMoneda> getListaIngresoEgresoMonedaExistentes() {
        return listaIngresoEgresoMonedaExistentes;
    }

    /**
     * @param listaIngresoEgresoMonedaExistentes the
     * listaIngresoEgresoMonedaExistentes to set
     */
    public void setListaIngresoEgresoMonedaExistentes(List<IngresoEgresoMoneda> listaIngresoEgresoMonedaExistentes) {
        this.listaIngresoEgresoMonedaExistentes = listaIngresoEgresoMonedaExistentes;
    }

    /**
     * @return the listaIngresoEgresoMonedaExistentesRol
     */
    public List<IngresoEgresoMoneda> getListaIngresoEgresoMonedaExistentesRol() {
        return listaIngresoEgresoMonedaExistentesRol;
    }

    /**
     * @param listaIngresoEgresoMonedaExistentesRol the
     * listaIngresoEgresoMonedaExistentesRol to set
     */
    public void setListaIngresoEgresoMonedaExistentesRol(List<IngresoEgresoMoneda> listaIngresoEgresoMonedaExistentesRol) {
        this.listaIngresoEgresoMonedaExistentesRol = listaIngresoEgresoMonedaExistentesRol;
    }

    /**
     * @return the listaIngresoEgresoMonedaFaltantes
     */
    public List<IngresoEgresoMoneda> getListaIngresoEgresoMonedaFaltantes() {
        return listaIngresoEgresoMonedaFaltantes;
    }

    /**
     * @param listaIngresoEgresoMonedaFaltantes the
     * listaIngresoEgresoMonedaFaltantes to set
     */
    public void setListaIngresoEgresoMonedaFaltantes(List<IngresoEgresoMoneda> listaIngresoEgresoMonedaFaltantes) {
        this.listaIngresoEgresoMonedaFaltantes = listaIngresoEgresoMonedaFaltantes;
    }

    /**
     * @return the itemsIngresoEgresoMoneda
     */
    public DualListModel<IngresoEgresoMoneda> getItemsIngresoEgresoMoneda() {
        return itemsIngresoEgresoMoneda;
    }

    /**
     * @param itemsIngresoEgresoMoneda the itemsIngresoEgresoMoneda to set
     */
    public void setItemsIngresoEgresoMoneda(DualListModel<IngresoEgresoMoneda> itemsIngresoEgresoMoneda) {
        this.itemsIngresoEgresoMoneda = itemsIngresoEgresoMoneda;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
