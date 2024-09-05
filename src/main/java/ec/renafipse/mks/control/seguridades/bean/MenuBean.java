package ec.renafipse.mks.control.seguridades.bean;

import ec.renafipse.mks.control.seguridades.*;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.PermisoOperacion;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.Sistema;
import ec.renafipse.mks.negocio.seguridades.MenuFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name = "menuControl")
@SessionScoped
public class MenuBean extends AbstractController<Menu> implements Serializable {

    private Long codigoSistema;
    private String cabeceraLista;
    private Sistema sistema;
    private List<Menu> itemsMenu;
    private MenuModel menu;
    private String tituloVentana;
    private String urlAyuda;
    private Menu modulo;
    private Long codigoIfip;
    private String codigoRol;
    private PermisoOperacion permisoOperacion;
    private List<Menu> menuPadre;

    @EJB
    private MenuFacade ejbFacade;

    public MenuBean() {
        super(Menu.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Obtiene el menu (modulos, menu y opciones) del Sistema
     */
    public void obtieneMenuPorSistema() {
        this.cabeceraLista = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Sistema") + " " + sistema.getNombre().toUpperCase();
        this.setItemsMenu(this.ejbFacade.getItemsMenu(sistema));
    }

    /**
     * Metodo que devuelve los modulos que una ifip tiene asigando Usado en la
     * ventana de modulo
     *
     * @return
     */
    public List<Menu> getItemsModulos() {
        //////System.out.println("Codigo IFIP "+this.getCodigoIfip());
        return this.ejbFacade.getItemsModulos(codigoIfip, this.codigoRol);
    }

    /**
     * Metodo que Costruye el Menu del Modulo Usuado en la ventana menu
     */
    public void contruyeMenu() {
        List<Menu> listMenu = this.ejbFacade.getItemsMenu(modulo);
        this.setMenu(new DefaultMenuModel());
        DefaultSubMenu subMenu;
        DefaultMenuItem item = new DefaultMenuItem();
        item.setUrl("/login/modulo.jsf");
        item.setIcon("ui-icon-inicio");
        item.setTitle("Ir a Modulos ...");
        this.getMenu().addElement(item);

        for (Menu submenu : listMenu) {
            subMenu = new DefaultSubMenu(submenu.getNombre());
            List<Menu> listOpcionesMenu = this.ejbFacade.getItemsOpcionesMenu(codigoRol, submenu.getCodigo(), this.codigoIfip);
            for (Menu opcion : listOpcionesMenu) {
                item = new DefaultMenuItem(opcion.getNombre());
                item.setStyle("width: 100%;'");
                item.setAjax(true);
                item.setTitle(opcion.getTitulo());
                String url = "/mks/" + this.modulo.getAplicacion() + "/" + submenu.getAplicacion() + "/" + opcion.getAplicacion() + "/index.jsf";
                urlAyuda = "/mks/" + this.modulo.getAplicacion() + "/" + submenu.getAplicacion() + "/" + opcion.getAplicacion() + "/ayuda.jsf";

                item.setCommand("#{menuController.irOpcion('" + opcion.getTitulo() + "','" + url + "','" + urlAyuda + "'," + opcion.getCodigo() + ",'" + opcion.getControlador() + "')}");
                subMenu.addElement(item);
            }
            //[#{menuController.irOpcion('Definicion de Tipos de Usuario','/mks/se/se01/se0105/index.jsf','/mks/se/se01/se0105/ayuda.jsf',ec.renafipse.mksseguridades.control.Menu[ codigo=14 ],tipoUsuarioController)}]

            this.getMenu().addElement(subMenu);
        }
    }

    /**
     * Metodo que va a la Opcion de acuero a la seleccion de la Opcion Usado en
     * el metodo que construye el menu
     *
     * @param tituloVentana Titulo de Ventana a Colocar en la Barra
     * @param url Direccion Url a la que se debe ir al momento de seleccionar la
     * opcion
     * @param urlAyuda Url de Ayuda en Linea
     * @param codigoOpcion
     * @param controlador Nombre del Managed Bean realacionada con la pagina
     * @throws IOException
     */
    public void irOpcion(String tituloVentana, String url, String urlAyuda, Long codigoOpcion, String controlador)
            throws IOException {
        setTituloVentana(tituloVentana);
        setUrlAyuda(urlAyuda);

        // Obteniendo los permiso delos tipos de operaciones en caso de tener se coloca en false para 
        // inidicar que no se deshabiliten los botones
        List<OpcionOperacion> listOpcionOperacion = this.ejbFacade.getOpcionOperacion(codigoRol, codigoIfip, codigoOpcion);
        this.setPermisoOperacion(new PermisoOperacion());
        for (OpcionOperacion operacion : listOpcionOperacion) {

            switch (Integer.parseInt(operacion.getCodigoTipoOperacion().getCodigo().toString())) {
                case 1:
                    this.getPermisoOperacion().setNuevo(false);
                    break;

                case 2:
                    this.getPermisoOperacion().setEditar(false);
                    break;

                case 3:
                    this.getPermisoOperacion().setEliminar(false);
                    break;

                case 4:
                    this.getPermisoOperacion().setImprimir(false);
                    break;

                case 5:
                    this.getPermisoOperacion().setConsultar(false);
                    break;

                case 6:
                    this.getPermisoOperacion().setVer(false);
                    break;

                case 7:
                    this.getPermisoOperacion().setImprimirComprobante(false);
                    break;

                case 17:
                    this.getPermisoOperacion().setBloquear(false);
                    break;
                    
                case 18:
                    this.getPermisoOperacion().setDesbloquear(false);
                    break;

            }

        }
        // Colocando Variable de Sesion
        ActivacionUsuario.setControlador(controlador);
        ActivacionUsuario.setCodigoRol(codigoRol);
        ActivacionUsuario.setCodigoOpcion(codigoOpcion);

        // Direccionando a la Pagina
        Sesion.redireccionaPagina(url);
    }

    /**
     * Metodo que se va al menu del modulo seleccionado y contruye el menu Usado
     * en la ventana modulo al dar clic o seleccionar el modulo
     *
     * @throws IOException
     */
    public void irMenu() throws IOException {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlMenuMKS");
        this.contruyeMenu();
        Sesion.redireccionaPagina(url);
    }

    /**
     * Cambia el menu padre de la opcion
     */
    public void cambiaMenuPadre() {
        Character tipo = null;
        this.setMenuPadre(null);

        if (String.valueOf(this.getSelected().getTipo()).equals("M")) {

            tipo = 'D';
        }

        if (String.valueOf(this.getSelected().getTipo()).equals("O")) {
            tipo = 'M';
        }

        if (tipo != null) {
            this.setMenuPadre(this.ejbFacade.getItemsMenuPorTipo(tipo));
        }

    }

    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
    // --------------------------------------------------------------------------
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    /**
     * @return the menu
     */
    public MenuModel getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    /**
     * @return the tituloVentana
     */
    public String getTituloVentana() {
        return tituloVentana;
    }

    /**
     * @param tituloVentana the tituloVentana to set
     */
    public void setTituloVentana(String tituloVentana) {
        this.tituloVentana = tituloVentana;
    }

    /**
     * @return the urlAyuda
     */
    public String getUrlAyuda() {
        return urlAyuda;
    }

    /**
     * @param urlAyuda the urlAyuda to set
     */
    public void setUrlAyuda(String urlAyuda) {
        this.urlAyuda = urlAyuda;
    }

    /**
     * @return the modulo
     */
    public Menu getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(Menu modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the codigoIfip
     */
    public Long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the codigoRol
     */
    public String getCodigoRol() {
        return codigoRol;
    }

    /**
     * @param codigoRol the codigoRol to set
     */
    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    /**
     * @return the codigoSistema
     */
    public Long getCodigoSistema() {
        return codigoSistema;
    }

    /**
     * @param codigoSistema the codigoSistema to set
     */
    public void setCodigoSistema(Long codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    /**
     * @return the cabeceraLista
     */
    public String getCabeceraLista() {
        return cabeceraLista;
    }

    /**
     * @param cabeceraLista the cabeceraLista to set
     */
    public void setCabeceraLista(String cabeceraLista) {
        this.cabeceraLista = cabeceraLista;
    }

    /**
     * @return the sistema
     */
    public Sistema getSistema() {
        return sistema;
    }

    /**
     * @param sistema the sistema to set
     */
    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
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

    // -- FIN DE ENCAPSULAMIENTO PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    /**
     * @return the permisoOperacion
     */
    public PermisoOperacion getPermisoOperacion() {
        return permisoOperacion;
    }

    /**
     * @param permisoOperacion the permisoOperacion to set
     */
    public void setPermisoOperacion(PermisoOperacion permisoOperacion) {
        this.permisoOperacion = permisoOperacion;
    }

    /**
     * @return the menuPadre
     */
    public List<Menu> getMenuPadre() {
        return menuPadre;
    }

    /**
     * @param menuPadre the menuPadre to set
     */
    public void setMenuPadre(List<Menu> menuPadre) {
        this.menuPadre = menuPadre;
    }

}
