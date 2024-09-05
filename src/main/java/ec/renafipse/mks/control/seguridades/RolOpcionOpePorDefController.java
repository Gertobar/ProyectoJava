package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDef;
import ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDefPK;
import ec.renafipse.mks.negocio.seguridades.MenuFacade;
import ec.renafipse.mks.negocio.seguridades.OpcionOperacionFacade;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import ec.renafipse.mks.negocio.seguridades.RolOpcionOpePorDefFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "rolOperacionPorDefectoController")
@ViewScoped
public class RolOpcionOpePorDefController extends AbstractController<RolOpcionOpePorDef> implements Serializable {

    @EJB
    private RolOpcionOpePorDefFacade ejbFacade;

    @EJB
    private MenuFacade ejbFacadeMenu;

    @EJB
    private RolFacade ejbFacadeRol;

    @EJB
    private OpcionOperacionFacade ejbFacadeOpcionOperacion;

    private Rol rol;
    private OpcionOperacion opcionOperacion;
    private RolOpcionOpePorDef rolOpcionOpePorDef;
    private RolOpcionOpePorDefPK rolOpcionOpePorDefPK;

    private Menu ubiModulo;
    private Menu ubiMenu;
    private Menu ubiOpcion;
    private Rol ubicaRol;
    private String msg;

    private List<Menu> itemsModulo;
    private List<Menu> itemsMenu;
    private List<Menu> itemsOpcion;
    private List<Rol> itemsRol;

    private List<OpcionOperacion> opcionOpePorDefExistentesList;
    private List<OpcionOperacion> opcionOpePorDefExistentesRolList;
    private List<OpcionOperacion> opcionOpePorDefFaltantesList;

    private DualListModel<OpcionOperacion> itemsOpcionOperacionPorDefecto;

    public RolOpcionOpePorDefController() {
        super(RolOpcionOpePorDef.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        msg = null;
        rol = new Rol();

        ubiModulo = null;
        ubiMenu = null;
        ubiOpcion = null;
        ubicaRol = null;

        opcionOperacion = new OpcionOperacion();
        rolOpcionOpePorDef = new RolOpcionOpePorDef();
        rolOpcionOpePorDefPK = new RolOpcionOpePorDefPK();

        this.itemsModulo = this.ejbFacadeMenu.getItemsModulo(Long.parseLong("2"));
        this.itemsRol = this.ejbFacadeRol.getItemsRol('N');

        this.itemsOpcionOperacionPorDefecto = new DualListModel<OpcionOperacion>();

        this.opcionOpePorDefExistentesList = new ArrayList<OpcionOperacion>();
        this.opcionOpePorDefExistentesRolList = new ArrayList<OpcionOperacion>();
        this.opcionOpePorDefFaltantesList = new ArrayList<OpcionOperacion>();

    }

    //LOGICA 
    /**
     * Metodo que permite obtener los menus en cuanto a la eleccion del modulo
     */
    public void cambiaModulo() {
        this.itemsMenu = this.ejbFacadeMenu.getItemsHijosOpcionOperacion(this.ubiModulo);
        this.itemsOpcion = this.ejbFacadeMenu.getItemsHijosOpcionOperacion(this.ubiMenu);
        this.ubiMenu = null;
        this.ubiOpcion = null;
        this.itemsOpcionOperacionPorDefecto = new DualListModel<OpcionOperacion>();
    }

    /**
     * Metodo que permite obtener las opciones en cuanto a la eleccion del menu
     */
    public void cambiaMenu() {
        this.itemsOpcion = this.ejbFacadeMenu.getItemsHijosOpcionOperacion(this.ubiMenu);
        this.ubiOpcion = null;
        this.itemsOpcionOperacionPorDefecto = new DualListModel<OpcionOperacion>();
    }

    /**
     * Se obtiene las opciones operaciones que no estan en rol opcion opeacion
     * por defecto
     */
    public void cambiaRol() {
        boolean existe = false;
        List<RolOpcionOpePorDef> listaRolOpcionOperacionPorDef;

        if (this.ubicaRol == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneDatosFaltantes");
            //MuestraMensaje.addAdvertencia(msg);
        } else if (this.ubiOpcion != null && this.ubicaRol != null) {

            this.opcionOpePorDefExistentesRolList = this.ejbFacadeOpcionOperacion.getItemsOpcionOperacionRolPorDef(this.getUbiOpcion().getCodigo(), this.getUbicaRol().getCodigo(), 'N');
            this.opcionOpePorDefExistentesList = this.ejbFacadeOpcionOperacion.getItemsOpcionOperacion(this.getUbiOpcion().getCodigo(), 'N');

            for (OpcionOperacion OpOpPorDefEx : this.opcionOpePorDefExistentesList) {
                for (OpcionOperacion OpOpPorDefExRol : this.opcionOpePorDefExistentesRolList) {
                    if (OpOpPorDefEx.getCodigo().intValue() == OpOpPorDefExRol.getCodigo().intValue()) {
                        existe = true;
                    } else {
                        existe = false;
                    }
                    if (existe) {
                        break;
                    }
                }
                if (!existe) {
                    this.opcionOpePorDefFaltantesList.add(OpOpPorDefEx);
                } else {
                    listaRolOpcionOperacionPorDef = this.ejbFacade.getItemsOpcionOperacionRolMenu(this.ubicaRol.getCodigo(), OpOpPorDefEx.getCodigo().longValue());
                    if (listaRolOpcionOperacionPorDef.size() == 1) {
                        this.rolOpcionOpePorDef = listaRolOpcionOperacionPorDef.get(0);
                        if (String.valueOf(this.rolOpcionOpePorDef.getEliminado()).equals("S")) {
                            this.opcionOpePorDefFaltantesList.add(OpOpPorDefEx);
                            this.opcionOpePorDefExistentesRolList.remove(OpOpPorDefEx);
                        }
                    }
                }

            }
            this.itemsOpcionOperacionPorDefecto = new DualListModel<OpcionOperacion>(this.opcionOpePorDefFaltantesList, this.opcionOpePorDefExistentesRolList);

        }
    }

    /**
     *
     */
    public void guardaRolOpcionOpePorDefFaltantes() {
        msg = null;
        List<RolOpcionOpePorDef> listaOpcionOperacionPorDefecto;

        for (OpcionOperacion op : this.itemsOpcionOperacionPorDefecto.getSource()) {
            listaOpcionOperacionPorDefecto = this.ejbFacade.getItemsOpcionOperacionRolMenu(this.ubicaRol.getCodigo(), op.getCodigo().longValue());
            if (listaOpcionOperacionPorDefecto.size() == 1) {
                RolOpcionOpePorDef rolOpcionOpePorDefSource = listaOpcionOperacionPorDefecto.get(0);
                if (String.valueOf(rolOpcionOpePorDefSource.getEliminado()).equals("N")) {
                    rolOpcionOpePorDefSource.setEliminado('S');
                    this.ejbFacade.edit(rolOpcionOpePorDefSource);
                }
            }
            if (listaOpcionOperacionPorDefecto.size() > 1) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolOpcionOpePorDef");
            }
        }

        if (msg == null) {
            for (OpcionOperacion op : this.itemsOpcionOperacionPorDefecto.getTarget()) {
                listaOpcionOperacionPorDefecto = this.ejbFacade.getItemsOpcionOperacionRolMenu(this.ubicaRol.getCodigo(), op.getCodigo().longValue());
                if (listaOpcionOperacionPorDefecto.size() == 1) {
                    RolOpcionOpePorDef rolOpcionOpePorDefTarget = listaOpcionOperacionPorDefecto.get(0);
                    if (String.valueOf(rolOpcionOpePorDefTarget.getEliminado()).equals("S")) {
                        rolOpcionOpePorDefTarget.setEliminado('N');
                        this.ejbFacade.edit(rolOpcionOpePorDefTarget);
                    }
                } else if (listaOpcionOperacionPorDefecto.isEmpty()) {

                    this.rolOpcionOpePorDef = new RolOpcionOpePorDef();
                    this.rolOpcionOpePorDefPK.setCodigoRol(this.ubicaRol.getCodigo());
                    this.rolOpcionOpePorDefPK.setCodigoOperacion(op.getCodigo().longValue());
                    this.rolOpcionOpePorDef.setRolOpcionOpePorDefPK(rolOpcionOpePorDefPK);
                    this.rolOpcionOpePorDef.setEliminado('N');
                    this.ejbFacade.create(rolOpcionOpePorDef);
                }

                if (listaOpcionOperacionPorDefecto.size() > 1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolOpcionOpePorDef");
                }
            }

            if (msg == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RolOpcionOpePorDefGuardada");
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
        this.getSelected().getRolOpcionOpePorDefPK().setCodigoRol(this.getSelected().getRol().getCodigo());
        this.getSelected().getRolOpcionOpePorDefPK().setCodigoOperacion(this.getSelected().getOpcionOperacion().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setRolOpcionOpePorDefPK(new ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDefPK());
    }

    /**
     * @return the ubiModulo
     */
    public Menu getUbiModulo() {
        return ubiModulo;
    }

    /**
     * @param ubiModulo the ubiModulo to set
     */
    public void setUbiModulo(Menu ubiModulo) {
        this.ubiModulo = ubiModulo;
    }

    /**
     * @return the ubiMenu
     */
    public Menu getUbiMenu() {
        return ubiMenu;
    }

    /**
     * @param ubiMenu the ubiMenu to set
     */
    public void setUbiMenu(Menu ubiMenu) {
        this.ubiMenu = ubiMenu;
    }

    /**
     * @return the ubiOpcion
     */
    public Menu getUbiOpcion() {
        return ubiOpcion;
    }

    /**
     * @param ubiOpcion the ubiOpcion to set
     */
    public void setUbiOpcion(Menu ubiOpcion) {
        this.ubiOpcion = ubiOpcion;
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
     * @return the itemsModulo
     */
    public List<Menu> getItemsModulo() {
        return itemsModulo;
    }

    /**
     * @param itemsModulo the itemsModulo to set
     */
    public void setItemsModulo(List<Menu> itemsModulo) {
        this.itemsModulo = itemsModulo;
    }

    /**
     * @return the itemsMenu
     */
    public List<Menu> getItemsMenu() {
        return itemsMenu;
    }

    /**
     * @param itemsMenu the itemsMenu to set
     */
    public void setItemsMenu(List<Menu> itemsMenu) {
        this.itemsMenu = itemsMenu;
    }

    /**
     * @return the itemsOpcion
     */
    public List<Menu> getItemsOpcion() {
        return itemsOpcion;
    }

    /**
     * @param itemsOpcion the itemsOpcion to set
     */
    public void setItemsOpcion(List<Menu> itemsOpcion) {
        this.itemsOpcion = itemsOpcion;
    }

    /**
     * @return the itemsRol
     */
    public List<Rol> getItemsRol() {
        return itemsRol;
    }

    /**
     * @param itemsRol the itemsRol to set
     */
    public void setItemsRol(List<Rol> itemsRol) {
        this.itemsRol = itemsRol;
    }

    /**
     * @return the itemsOpcionOperacionPorDefecto
     */
    public DualListModel<OpcionOperacion> getItemsOpcionOperacionPorDefecto() {
        return itemsOpcionOperacionPorDefecto;
    }

    /**
     * @param itemsOpcionOperacionPorDefecto the itemsOpcionOperacionPorDefecto
     * to set
     */
    public void setItemsOpcionOperacionPorDefecto(DualListModel<OpcionOperacion> itemsOpcionOperacionPorDefecto) {
        this.itemsOpcionOperacionPorDefecto = itemsOpcionOperacionPorDefecto;
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

    /**
     * @return the opcionOperacion
     */
    public OpcionOperacion getOpcionOperacion() {
        return opcionOperacion;
    }

    /**
     * @param opcionOperacion the opcionOperacion to set
     */
    public void setOpcionOperacion(OpcionOperacion opcionOperacion) {
        this.opcionOperacion = opcionOperacion;
    }

    /**
     * @return the rolOpcionOpePorDef
     */
    public RolOpcionOpePorDef getRolOpcionOpePorDef() {
        return rolOpcionOpePorDef;
    }

    /**
     * @param rolOpcionOpePorDef the rolOpcionOpePorDef to set
     */
    public void setRolOpcionOpePorDef(RolOpcionOpePorDef rolOpcionOpePorDef) {
        this.rolOpcionOpePorDef = rolOpcionOpePorDef;
    }

    /**
     * @return the rolOpcionOpePorDefPK
     */
    public RolOpcionOpePorDefPK getRolOpcionOpePorDefPK() {
        return rolOpcionOpePorDefPK;
    }

    /**
     * @param rolOpcionOpePorDefPK the rolOpcionOpePorDefPK to set
     */
    public void setRolOpcionOpePorDefPK(RolOpcionOpePorDefPK rolOpcionOpePorDefPK) {
        this.rolOpcionOpePorDefPK = rolOpcionOpePorDefPK;
    }

    /**
     * @return the opcionOpePorDefExistentesList
     */
    public List<OpcionOperacion> getOpcionOpePorDefExistentesList() {
        return opcionOpePorDefExistentesList;
    }

    /**
     * @param opcionOpePorDefExistentesList the opcionOpePorDefExistentesList to
     * set
     */
    public void setOpcionOpePorDefExistentesList(List<OpcionOperacion> opcionOpePorDefExistentesList) {
        this.opcionOpePorDefExistentesList = opcionOpePorDefExistentesList;
    }

    /**
     * @return the opcionOpePorDefExistentesRolList
     */
    public List<OpcionOperacion> getOpcionOpePorDefExistentesRolList() {
        return opcionOpePorDefExistentesRolList;
    }

    /**
     * @param opcionOpePorDefExistentesRolList the
     * opcionOpePorDefExistentesRolList to set
     */
    public void setOpcionOpePorDefExistentesRolList(List<OpcionOperacion> opcionOpePorDefExistentesRolList) {
        this.opcionOpePorDefExistentesRolList = opcionOpePorDefExistentesRolList;
    }

    /**
     * @return the opcionOpePorDefFaltantesList
     */
    public List<OpcionOperacion> getOpcionOpePorDefFaltantesList() {
        return opcionOpePorDefFaltantesList;
    }

    /**
     * @param opcionOpePorDefFaltantesList the opcionOpePorDefFaltantesList to
     * set
     */
    public void setOpcionOpePorDefFaltantesList(List<OpcionOperacion> opcionOpePorDefFaltantesList) {
        this.opcionOpePorDefFaltantesList = opcionOpePorDefFaltantesList;
    }

}
