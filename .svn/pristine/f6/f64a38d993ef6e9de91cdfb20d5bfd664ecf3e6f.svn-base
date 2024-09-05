package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.Rol;
import ec.renafipse.mks.modelo.seguridades.RolOpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.RolOpcionOperacionPK;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.seguridades.MenuFacade;
import ec.renafipse.mks.negocio.seguridades.OpcionOperacionFacade;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import ec.renafipse.mks.negocio.seguridades.RolOpcionOperacionFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "rolOperacionController")
@ViewScoped
public class RolOpcionOperacionController extends AbstractController<RolOpcionOperacion> implements Serializable {

    @EJB
    private RolOpcionOperacionFacade ejbFacade;

    @EJB
    private RolFacade ejbFacadeRol;

    @EJB
    private OpcionOperacionFacade ejbFacadeOpcionOperacion;

    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private MenuFacade ejbFacadeMenu;

    //Creacion de variables tipo entidad para guardar los datos
    private Ifip ifip;
    private Rol rol;
    private OpcionOperacion opcionOperacion;
    private RolOpcionOperacion rolOpcionOperacion;
    private RolOpcionOperacionPK rolOpcionOperacionPK;

    private Menu ubiModulo;
    private Menu ubiMenu;
    private Menu ubiOpcion;
    private Rol ubicaRol;
    private String msg;
    private String mensaje;

    private List<Menu> itemsModulo;
    private List<Menu> itemsMenu;
    private List<Menu> itemsOpcion;
    private List<Rol> itemsRol;

    private List<OpcionOperacion> opcionOperacionExistentesList;
    private List<OpcionOperacion> opcionOperacionExistentesRolList;
    private List<OpcionOperacion> opcionOperacionFaltantesList;

    private DualListModel<OpcionOperacion> itemsOpcionOperacion;

    public RolOpcionOperacionController() {
        super(RolOpcionOperacion.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        msg = null;
        mensaje = null;
        ifip = new Ifip();
        rol = new Rol();

        this.ubiModulo = null;
        this.ubiMenu = null;
        this.ubiOpcion = null;
        this.ubicaRol = null;

        this.ifip = new Ifip();
        this.rol = new Rol();
        this.rolOpcionOperacionPK = new RolOpcionOperacionPK();
        this.opcionOperacion = new OpcionOperacion();
        this.rolOpcionOperacion = new RolOpcionOperacion();

        this.itemsModulo = this.ejbFacadeMenu.getItemsModulo(Long.parseLong("2"));
        this.itemsOpcionOperacion = new DualListModel<OpcionOperacion>();
        this.itemsRol = this.ejbFacadeRol.getItemsRol('N');
    }

    //LOGICA
    /**
     * Permite obtener los menus en cuanto a la eleccion del modulo
     */
    public void cambiaModulo() {
        this.setItemsMenu(this.getEjbFacadeMenu().getItemsHijosOpcionOperacion(this.getUbiModulo()));
        this.setItemsOpcion(this.getEjbFacadeMenu().getItemsHijosOpcionOperacion(this.getUbiMenu()));
        this.ubiMenu = null;
        this.ubiOpcion = null;
        this.itemsOpcionOperacion = new DualListModel<OpcionOperacion>();

    }

    /**
     * Permite obtener las opciones en cuanto a la eleccion del menu
     */
    public void cambiaMenu() {
        this.setItemsOpcion(this.getEjbFacadeMenu().getItemsHijosOpcionOperacion(this.getUbiMenu()));
        this.ubiOpcion = null;
        this.itemsOpcionOperacion = new DualListModel<OpcionOperacion>();
    }

    /**
     * Permite obterner las opciones operaciones al elegir la opcion y el rol
     */
    public void cambiaRol() {
        boolean existe = false;
        List<RolOpcionOperacion> listaRolOpcionOperacion;
        this.opcionOperacionExistentesList = new ArrayList<OpcionOperacion>();
        this.opcionOperacionExistentesRolList = new ArrayList<OpcionOperacion>();
        this.opcionOperacionFaltantesList = new ArrayList<OpcionOperacion>();

        if (this.ubicaRol == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneDatosFaltantes");
            //MuestraMensaje.addAdvertencia(msg);
        } else if (this.ubicaRol != null && this.ubiOpcion != null) {

            this.opcionOperacionExistentesRolList = this.ejbFacadeOpcionOperacion.getItemsOpcionOperacionRol(this.getUbiOpcion().getCodigo(), this.getUbicaRol().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N');
            this.opcionOperacionExistentesList = this.ejbFacadeOpcionOperacion.getItemsOpcionOperacion(this.getUbiOpcion().getCodigo(), 'N');

            for (OpcionOperacion OpOpEx : this.opcionOperacionExistentesList) {
                for (OpcionOperacion OpOpExRol : this.opcionOperacionExistentesRolList) {
                    if (OpOpEx.getCodigo().longValue() == OpOpExRol.getCodigo().longValue()) {
                        existe = true;
                    } else {
                        existe = false;
                    }
                    if (existe) {
                        break;
                    }
                }
                if (!existe) {
                    this.opcionOperacionFaltantesList.add(OpOpEx);
                } else {
                    listaRolOpcionOperacion = this.ejbFacade.getItemsOpcionOperacionRolMenu(this.ubicaRol.getCodigo(), OpOpEx.getCodigo(), ActivacionUsuario.getCodigoIfip());
                    if (listaRolOpcionOperacion.size() == 1) {
                        this.rolOpcionOperacion = listaRolOpcionOperacion.get(0);
                        if (String.valueOf(this.rolOpcionOperacion.getEliminado()).equals("S")) {
                            this.opcionOperacionFaltantesList.add(OpOpEx);
                            this.opcionOperacionExistentesRolList.remove(OpOpEx);
                        }
                    }
                }

            }
            this.itemsOpcionOperacion = new DualListModel<OpcionOperacion>(this.opcionOperacionFaltantesList, this.opcionOperacionExistentesRolList);
        }
    }

    /**
     * Guarda los roles por opcion operacion faltantes
     */
    public void guardaRolOpcionOperacionFaltantes() {
        msg = null;
        List<RolOpcionOperacion> listaOpcionOperacion;
        for (OpcionOperacion op : this.itemsOpcionOperacion.getSource()) {
            listaOpcionOperacion = this.ejbFacade.getItemsOpcionOperacionRolMenu(this.ubicaRol.getCodigo(), op.getCodigo().longValue(), ActivacionUsuario.getCodigoIfip());
            if (listaOpcionOperacion.size() == 1) {
                RolOpcionOperacion rolOpcionOperacionSource = listaOpcionOperacion.get(0);
                if (String.valueOf(rolOpcionOperacionSource.getEliminado()).equals("N")) {
                    rolOpcionOperacionSource.setEliminado('S');
                    this.ejbFacade.edit(rolOpcionOperacionSource);
                }
            }
            if (listaOpcionOperacion.size() > 1) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolOpcionOperacion");
            }
        }

        if (msg == null) {
            for (OpcionOperacion op : this.itemsOpcionOperacion.getTarget()) {
                listaOpcionOperacion = this.ejbFacade.getItemsOpcionOperacionRolMenu(this.ubicaRol.getCodigo(), op.getCodigo(), ActivacionUsuario.getCodigoIfip());
                if (listaOpcionOperacion.size() == 1) {
                    RolOpcionOperacion rolOpcionOperacionTarget = listaOpcionOperacion.get(0);
                    if (String.valueOf(rolOpcionOperacionTarget.getEliminado()).equals("S")) {
                        rolOpcionOperacionTarget.setEliminado('N');
                        this.ejbFacade.edit(rolOpcionOperacionTarget);
                    }
                } else if (listaOpcionOperacion.isEmpty()) {
                    rolOpcionOperacion = new RolOpcionOperacion();
                    this.rolOpcionOperacionPK.setCodigoOperacion(op.getCodigo());
                    this.rolOpcionOperacionPK.setCodigoIfip(ActivacionUsuario.getCodigoIfip());
                    this.rolOpcionOperacionPK.setCodigoRol(this.ubicaRol.getCodigo());
                    rolOpcionOperacion.setRolOpcionOperacionPK(rolOpcionOperacionPK);
                    rolOpcionOperacion.setEliminado('N');
                    this.ejbFacade.create(rolOpcionOperacion);
                }

                if (listaOpcionOperacion.size() > 1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnRolOpcionOperacion");
                }
            }

            if (msg == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RolOpcionOperacionGuardada");
                MuestraMensaje.addSatisfactorio(msg);
                init();

            } else {
                MuestraMensaje.addError(msg);
            }
        }
    }

    //FIN DE LA LOGICA
    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getRolOpcionOperacionPK().setCodigoRol(this.getSelected().getRol().getCodigo());
        this.getSelected().getRolOpcionOperacionPK().setCodigoOperacion(this.getSelected().getOpcionOperacion().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setRolOpcionOperacionPK(new ec.renafipse.mks.modelo.seguridades.RolOpcionOperacionPK());
    }

    /**
     * @return the ejbFacade
     */
    public RolOpcionOperacionFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(RolOpcionOperacionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ejbFacadeRol
     */
    public RolFacade getEjbFacadeRol() {
        return ejbFacadeRol;
    }

    /**
     * @param ejbFacadeRol the ejbFacadeRol to set
     */
    public void setEjbFacadeRol(RolFacade ejbFacadeRol) {
        this.ejbFacadeRol = ejbFacadeRol;
    }

    /**
     * @return the ejbFacadeOpcionOperacion
     */
    public OpcionOperacionFacade getEjbFacadeOpcionOperacion() {
        return ejbFacadeOpcionOperacion;
    }

    /**
     * @param ejbFacadeOpcionOperacion the ejbFacadeOpcionOperacion to set
     */
    public void setEjbFacadeOpcionOperacion(OpcionOperacionFacade ejbFacadeOpcionOperacion) {
        this.ejbFacadeOpcionOperacion = ejbFacadeOpcionOperacion;
    }

    /**
     * @return the ejbFacadeIfip
     */
    public IfipFacade getEjbFacadeIfip() {
        return ejbFacadeIfip;
    }

    /**
     * @param ejbFacadeIfip the ejbFacadeIfip to set
     */
    public void setEjbFacadeIfip(IfipFacade ejbFacadeIfip) {
        this.ejbFacadeIfip = ejbFacadeIfip;
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
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
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
     * @return the rolOpcionOperacion
     */
    public RolOpcionOperacion getRolOpcionOperacion() {
        return rolOpcionOperacion;
    }

    /**
     * @param rolOpcionOperacion the rolOpcionOperacion to set
     */
    public void setRolOpcionOperacion(RolOpcionOperacion rolOpcionOperacion) {
        this.rolOpcionOperacion = rolOpcionOperacion;
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
     * @return the opcionOperacionExistentesList
     */
    public List<OpcionOperacion> getOpcionOperacionExistentesList() {
        return opcionOperacionExistentesList;
    }

    /**
     * @param opcionOperacionExistentesList the opcionOperacionExistentesList to
     * set
     */
    public void setOpcionOperacionExistentesList(List<OpcionOperacion> opcionOperacionExistentesList) {
        this.opcionOperacionExistentesList = opcionOperacionExistentesList;
    }

    /**
     * @return the opcionOperacionFaltantesList
     */
    public List<OpcionOperacion> getOpcionOperacionFaltantesList() {
        return opcionOperacionFaltantesList;
    }

    /**
     * @param opcionOperacionFaltantesList the opcionOperacionFaltantesList to
     * set
     */
    public void setOpcionOperacionFaltantesList(List<OpcionOperacion> opcionOperacionFaltantesList) {
        this.opcionOperacionFaltantesList = opcionOperacionFaltantesList;
    }

    /**
     * @return the itemsOpcionOperacion
     */
    public DualListModel<OpcionOperacion> getItemsOpcionOperacion() {
        return itemsOpcionOperacion;
    }

    /**
     * @param itemsOpcionOperacion the itemsOpcionOperacion to set
     */
    public void setItemsOpcionOperacion(DualListModel<OpcionOperacion> itemsOpcionOperacion) {
        this.itemsOpcionOperacion = itemsOpcionOperacion;
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
     * @return the opcionOperacionExistentesRolList
     */
    public List<OpcionOperacion> getOpcionOperacionExistentesRolList() {
        return opcionOperacionExistentesRolList;
    }

    /**
     * @param opcionOperacionExistentesRolList the
     * opcionOperacionExistentesRolList to set
     */
    public void setOpcionOperacionExistentesRolList(List<OpcionOperacion> opcionOperacionExistentesRolList) {
        this.opcionOperacionExistentesRolList = opcionOperacionExistentesRolList;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
