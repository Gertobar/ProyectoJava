package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.PermisoOperacion;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.PeriodoContable;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.modelo.seguridades.Sistema;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.seguridades.MenuFacade;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name = "menuController")
@SessionScoped
public class MenuController extends AbstractController<Menu> implements Serializable {

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
    private String mensajeSinPermisosOpcion;
    private List<Menu> menuPadre;
    private StreamedContent logoCentral;
    private RequestContext context;

    private List<PeriodoContable> itemsPeriodoContable;
    private PeriodoContable periodoContable;

    @EJB
    private MenuFacade ejbFacade;

    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private PeriodoContableFacade ejbFacadePeriodoContable;

    public MenuController() {
        super(Menu.class);
    }

    @PostConstruct
    public void init() {

        super.setFacade(ejbFacade);
        this.setItemsPeriodoContable(this.ejbFacadePeriodoContable.getItemPeriodosContables());
        this.preparaImagenLogoCentral();

    }

    /**
     * Seleccione el Periodo Contable.
     */
    public void seleccionaPeriodo() {
        try {

            if (periodoContable == null) {
                return;
            }
            context = RequestContext.getCurrentInstance();
            this.contruyeMenu();

            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlMenuMKS");
            ActivacionUsuario.setCodigoPeriodo(this.periodoContable.getCodigo());
            context.execute("periodoContableDialog.hide()");
            Sesion.redireccionaPagina(url);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     *
     * @throws java.io.IOException
     */
    public void contruyeMenu() throws IOException {
        List<Menu> listMenu = this.ejbFacade.getItemsMenu(modulo);
        this.setMenu(new DefaultMenuModel());
        DefaultSubMenu subMenu;
        DefaultMenuItem item = new DefaultMenuItem();
        item.setUrl(ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlModuloMKSMenu"));
        item.setIcon(ResourceBundle.getBundle("/propiedadesObjetosEC").getString("IcoInicio"));
        item.setTitle(ResourceBundle.getBundle("/propiedadesTituloEC").getString("IrModulos"));
        this.getMenu().addElement(item);

        for (Menu submenu : listMenu) {
            subMenu = new DefaultSubMenu(submenu.getNombre());
            List<Menu> listOpcionesMenu = this.ejbFacade.getItemsOpcionesMenu(codigoRol, submenu.getCodigo(), this.codigoIfip);
            ////System.out.println(submenu.getNombre() + " " + listOpcionesMenu);
            for (Menu opcion : listOpcionesMenu) {

                item = new DefaultMenuItem(opcion.getNombre());
                item.setStyle("width: 100%;'");
                item.setAjax(true);
                item.setTitle(opcion.getTitulo());
                String url = "/mks/" + this.modulo.getAplicacion() + "/" + submenu.getAplicacion() + "/" + opcion.getAplicacion() + "/inicio.jsf";
                urlAyuda = "/mks/" + this.modulo.getAplicacion() + "/" + submenu.getAplicacion() + "/" + opcion.getAplicacion() + "/ayuda.jsf";

                item.setCommand("#{menuController.irOpcion('" + opcion.getTitulo() + "','" + url + "','" + urlAyuda + "'," + opcion.getCodigo() + ",'" + opcion.getControlador() + "')}");
                subMenu.addElement(item);
            }
            //[#{menuController.irOpcion('Definicion de Tipos de Usuario','/mks/se/se01/se0105/index.jsf','/mks/se/se01/se0105/ayuda.jsf',ec.renafipse.mksseguridades.control.Menu[ codigo=14 ],tipoUsuarioController)}]

            if (subMenu.getElements().size() > 0) {
                this.getMenu().addElement(subMenu);
            }
        }

        if (this.menu.getElements().size() <= 1) {
            String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioSinPermisosModulo"));
            //Accediendo a la ventana de no permisos            
            Sesion.redireccionaPagina(url);
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
        //////System.out.println(" listOpcionOperacion "+listOpcionOperacion);
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

                case 8:
                    this.getPermisoOperacion().setGuardar(false);
                    break;

                case 9:
                    this.getPermisoOperacion().setRetencion(false);
                    break;

                case 10:
                    this.getPermisoOperacion().setContabilizar(false);
                    break;

                case 11:
                    this.getPermisoOperacion().setAnular(false);
                    break;

                case 12:
                    this.getPermisoOperacion().setPrecancelar(false);
                    break;

                case 13:
                    this.getPermisoOperacion().setConceder(false);
                    break;

                case 14:
                    this.getPermisoOperacion().setPagare(false);
                    break;
                case 15:
                    this.getPermisoOperacion().setEntregar(false);
                    break;
                case 16:
                    this.getPermisoOperacion().setCambiar(false);
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
        context = RequestContext.getCurrentInstance();
        this.periodoContable = null;
        this.contruyeMenu();
        if (this.modulo.getCodigo() == 2L) {
            this.setItemsPeriodoContable(this.ejbFacadePeriodoContable.getItemPeriodosContables());
            this.periodoContable = null;
            context.execute("periodoContableDialog.show()");
        } else {
            List<PeriodoContable> listaPeriodoContable = this.ejbFacadePeriodoContable.getItemPeriodoContable('S');
            if (!listaPeriodoContable.isEmpty()) {
                if (listaPeriodoContable.size() == 1) {
                    this.periodoContable = listaPeriodoContable.get(0);
                }
            }

            if (this.periodoContable == null) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistePeriodoConableActual"));
                return;
            }
            
            //Asignando el Periodo Contable Actual si no seleccion el modulo contable
            ActivacionUsuario.setCodigoPeriodo(periodoContable.getCodigo());
            
            Sesion.redireccionaPagina(url);
        }

    }

    /**
     * Carga el Logo Central que se presentara en el menu
     */
    public void preparaImagenLogoCentral() {
        try {
            Ifip ifip = this.ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()!=null ? ActivacionUsuario.getCodigoIfip() : new Long("1"));
            //System.out.println("Path Logo Central "+ifip.getLogoCentral());
            String path = ifip.getLogoCentral();

            setLogoCentral(new DefaultStreamedContent(new FileInputStream(path), "image/png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuarioIfipAgenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
    // --------------------------------------------------------------------------
    // -- METODOS REFRESCAMIENTO DE COMBOS/LISTAS
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

    // -- FIN DE METODOS REFRESCAMIENTO DE COMBOS/LISTAS
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

    /**
     * @return the mensajeSinPermisosOpcion
     */
    public String getMensajeSinPermisosOpcion() {
        return ActivacionUsuario.getSinPermisosOpcion();
    }

    /**
     * @param mensajeSinPermisosOpcion the mensajeSinPermisosOpcion to set
     */
    public void setMensajeSinPermisosOpcion(String mensajeSinPermisosOpcion) {
        this.mensajeSinPermisosOpcion = mensajeSinPermisosOpcion;
    }

    /**
     * @return the logoCentral
     */
    public StreamedContent getLogoCentral() {
        return logoCentral;
    }

    /**
     * @param logoCentral the logoCentral to set
     */
    public void setLogoCentral(StreamedContent logoCentral) {
        this.logoCentral = logoCentral;
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

    /**
     * @return the itemsPeriodoContable
     */
    public List<PeriodoContable> getItemsPeriodoContable() {
        return itemsPeriodoContable;
    }

    /**
     * @param itemsPeriodoContable the itemsPeriodoContable to set
     */
    public void setItemsPeriodoContable(List<PeriodoContable> itemsPeriodoContable) {
        this.itemsPeriodoContable = itemsPeriodoContable;
    }

}
