package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.TipoOperacion;
import ec.renafipse.mks.negocio.seguridades.MenuFacade;
import ec.renafipse.mks.negocio.seguridades.OpcionOperacionFacade;
import ec.renafipse.mks.negocio.seguridades.TipoOperacionFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.primefaces.model.DualListModel;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "operacionController")
@ViewScoped
public class OpcionOperacionController extends AbstractController<OpcionOperacion> implements Serializable {

    @EJB
    private OpcionOperacionFacade ejbFacade;

    @EJB
    private MenuFacade ejbFacadeMenu;

    @EJB
    private TipoOperacionFacade ejbFacadeTipoOperacion;

    private OpcionOperacion opcionOperacion;
    private Menu menu;
    private TipoOperacion tipoOperacion;

    private Menu ubiModulo;
    private Menu ubiMenu;
    private Menu ubiOpcion;
    private TipoOperacion ubiTipoOpExis;
    private TipoOperacion ubiTipoOpFalt;
    private String msg;

    private List<Menu> itemsModulo;
    private List<Menu> itemsMenu;
    private List<Menu> itemsOpcion;

    private List<TipoOperacion> tiposOperacionExistentesOPList;
    private List<TipoOperacion> tiposOperacionExistentesList;
    private List<TipoOperacion> tiposOperacionFaltantesList;

    private DualListModel<TipoOperacion> itemsTipoOperacion;

    public OpcionOperacionController() {
        super(OpcionOperacion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        msg = null;
        this.ubiModulo = null;
        this.ubiMenu = null;
        this.ubiOpcion = null;
        this. menu = new Menu();
        this.opcionOperacion = new OpcionOperacion();       
        this.tipoOperacion = new TipoOperacion();
        this.itemsModulo = this.ejbFacadeMenu.getItemsModulo(Long.parseLong("2"));
        this.itemsTipoOperacion = new DualListModel<TipoOperacion>();

    }

    /**
     * Metodo que permite obtener los menus en cuanto a la eleccion del modulo
     */
    public void cambiaModulo() {
        this.setItemsMenu(this.getEjbFacadeMenu().getItemsHijosOpcionOperacion(this.getUbiModulo()));
        this.setItemsOpcion(this.getEjbFacadeMenu().getItemsHijosOpcionOperacion(this.getUbiMenu()));
        this.ubiMenu = null;
        this.ubiOpcion = null;
        this.itemsTipoOperacion = new DualListModel<TipoOperacion>();

    }

    /**
     * Metodo que permite obtener las opciones en cuanto a la eleccion del menu
     */
    public void cambiaMenu() {
        this.setItemsOpcion(this.getEjbFacadeMenu().getItemsHijosOpcionOperacion(this.getUbiMenu()));
        this.ubiOpcion = null;
        this.itemsTipoOperacion = new DualListModel<TipoOperacion>();
    }

    /**
     *
     */
    public void cambiaOpcion() {
        boolean existe = false;
        List<OpcionOperacion> listaOpcionOperacion;
        this.tiposOperacionExistentesList = new ArrayList<TipoOperacion>();
        this.tiposOperacionExistentesOPList = new ArrayList<TipoOperacion>();
        this.tiposOperacionFaltantesList = new ArrayList<TipoOperacion>();

        this.tiposOperacionExistentesOPList = this.ejbFacadeTipoOperacion.getItemsTipoOperacionExistentesOpOp(this.getUbiOpcion().getCodigo());
        this.tiposOperacionExistentesList = this.ejbFacadeTipoOperacion.getItemsTipoOperacion();

        for (TipoOperacion tiOpEx : this.tiposOperacionExistentesList) {
            for (TipoOperacion tiOpExOP : this.tiposOperacionExistentesOPList) {
                if (tiOpEx.getCodigo() == tiOpExOP.getCodigo()) {
                    existe = true;
                } else {
                    existe = false;
                }
                if (existe) {
                    break;
                }
            }

            if (!existe) {
                this.tiposOperacionFaltantesList.add(tiOpEx);
            } else {

                listaOpcionOperacion = this.ejbFacade.getItemsOpcionOperacionTipoOperacionMenu(this.ubiOpcion, tiOpEx);
                if (listaOpcionOperacion.size() == 1) {
                    this.opcionOperacion = listaOpcionOperacion.get(0);
                    if (String.valueOf(this.opcionOperacion.getEliminado()).equals("S")) {
                        this.tiposOperacionFaltantesList.add(tiOpEx);
                        this.tiposOperacionExistentesOPList.remove(tiOpEx);
                    }
                }
            }
        }
        this.itemsTipoOperacion = new DualListModel<TipoOperacion>(this.tiposOperacionFaltantesList, this.tiposOperacionExistentesOPList);
        //  this.ubiOpcion = null;
//        this.itemsTipoOperacion = new DualListModel<TipoOperacion>();
    }

    /**
     *
     */
    public void guardaTiposOperacionFaltantes() {
        msg = null;
        List<OpcionOperacion> listaOpcionOperacion;

        for (TipoOperacion to : this.itemsTipoOperacion.getSource()) {
            listaOpcionOperacion = this.ejbFacade.getItemsOpcionOperacionTipoOperacionMenu(this.ubiOpcion, to);
            if (listaOpcionOperacion.size() == 1) {
                OpcionOperacion opcionOperacionSource = listaOpcionOperacion.get(0);
                if (String.valueOf(opcionOperacionSource.getEliminado()).equals("N")) {
                    opcionOperacionSource.setEliminado('S');
                    this.ejbFacade.edit(opcionOperacionSource);
                }
            }
            if (listaOpcionOperacion.size() > 1) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnTipoOperacionYOpcion");
            }
        }

        if (msg == null) {
            for (TipoOperacion to : this.itemsTipoOperacion.getTarget()) {
                listaOpcionOperacion = this.ejbFacade.getItemsOpcionOperacionTipoOperacionMenu(this.ubiOpcion, to);
                if (listaOpcionOperacion.size() == 1) {
                    OpcionOperacion opcionOperacionTarget = listaOpcionOperacion.get(0);
                    if (String.valueOf(opcionOperacionTarget.getEliminado()).equals("S")) {
                        opcionOperacionTarget.setEliminado('N');
                        this.ejbFacade.edit(opcionOperacionTarget);
                    }
                } else if (listaOpcionOperacion.isEmpty()) {
                    this.opcionOperacion = new OpcionOperacion();
                    this.opcionOperacion.setCodigoMenu(this.ubiOpcion);
                    this.opcionOperacion.setCodigoTipoOperacion(to);
                    this.opcionOperacion.setNombre((to.getNombre()).toUpperCase() + " " + this.ubiOpcion.getNombre());
                    this.opcionOperacion.setEliminado('N');
                    this.ejbFacade.create(opcionOperacion);
                }

                if (listaOpcionOperacion.size() > 1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnTipoOperacionYOpcion");
                }
            }

            if (msg == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoOperacionGuardada");
                MuestraMensaje.addSatisfactorio(msg);
                init();
            } else {
                MuestraMensaje.addError(msg);
            }
        }
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
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
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
     * @return the ejbFacade
     */
    public OpcionOperacionFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(OpcionOperacionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ubiTipoOpExis
     */
    public TipoOperacion getUbiTipoOpExis() {
        return ubiTipoOpExis;
    }

    /**
     * @param ubiTipoOpExis the ubiTipoOpExis to set
     */
    public void setUbiTipoOpExis(TipoOperacion ubiTipoOpExis) {
        this.ubiTipoOpExis = ubiTipoOpExis;
    }

    /**
     * @return the ubiTipoOpFalt
     */
    public TipoOperacion getUbiTipoOpFalt() {
        return ubiTipoOpFalt;
    }

    /**
     * @param ubiTipoOpFalt the ubiTipoOpFalt to set
     */
    public void setUbiTipoOpFalt(TipoOperacion ubiTipoOpFalt) {
        this.ubiTipoOpFalt = ubiTipoOpFalt;
    }

    /**
     * @return the ejbFacadeMenu
     */
    public MenuFacade getEjbFacadeMenu() {
        return ejbFacadeMenu;
    }

    /**
     * @param ejbFacadeMenu the ejbFacadeMenu to set
     */
    public void setEjbFacadeMenu(MenuFacade ejbFacadeMenu) {
        this.ejbFacadeMenu = ejbFacadeMenu;
    }

    /**
     * @return the ejbFacadeTipoOperacion
     */
    public TipoOperacionFacade getEjbFacadeTipoOperacion() {
        return ejbFacadeTipoOperacion;
    }

    /**
     * @param ejbFacadeTipoOperacion the ejbFacadeTipoOperacion to set
     */
    public void setEjbFacadeTipoOperacion(TipoOperacionFacade ejbFacadeTipoOperacion) {
        this.ejbFacadeTipoOperacion = ejbFacadeTipoOperacion;
    }

    /**
     * @return the tipoOperacion
     */
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * @param tipoOperacion the tipoOperacion to set
     */
    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * @return the tiposOperacionExistentesOPList
     */
    public List<TipoOperacion> getTiposOperacionExistentesOPList() {
        return tiposOperacionExistentesOPList;
    }

    /**
     * @param tiposOperacionExistentesOPList the tiposOperacionExistentesOPList
     * to set
     */
    public void setTiposOperacionExistentesOPList(List<TipoOperacion> tiposOperacionExistentesOPList) {
        this.tiposOperacionExistentesOPList = tiposOperacionExistentesOPList;
    }

    /**
     * @return the tiposOperacionExistentesList
     */
    public List<TipoOperacion> getTiposOperacionExistentesList() {
        return tiposOperacionExistentesList;
    }

    /**
     * @param tiposOperacionExistentesList the tiposOperacionExistentesList to
     * set
     */
    public void setTiposOperacionExistentesList(List<TipoOperacion> tiposOperacionExistentesList) {
        this.tiposOperacionExistentesList = tiposOperacionExistentesList;
    }

    /**
     * @return the tiposOperacionFaltantesList
     */
    public List<TipoOperacion> getTiposOperacionFaltantesList() {
        return tiposOperacionFaltantesList;
    }

    /**
     * @param tiposOperacionFaltantesList the tiposOperacionFaltantesList to set
     */
    public void setTiposOperacionFaltantesList(List<TipoOperacion> tiposOperacionFaltantesList) {
        this.tiposOperacionFaltantesList = tiposOperacionFaltantesList;
    }

    /**
     * @return the itemsTipoOperacion
     */
    public DualListModel<TipoOperacion> getItemsTipoOperacion() {
        return itemsTipoOperacion;
    }

    /**
     * @param itemsTipoOperacion the itemsTipoOperacion to set
     */
    public void setItemsTipoOperacion(DualListModel<TipoOperacion> itemsTipoOperacion) {
        this.itemsTipoOperacion = itemsTipoOperacion;
    }

}
