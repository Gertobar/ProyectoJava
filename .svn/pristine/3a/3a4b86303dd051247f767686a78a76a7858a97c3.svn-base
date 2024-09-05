package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.IfipMenu;
import ec.renafipse.mks.modelo.seguridades.Menu;
import ec.renafipse.mks.negocio.seguridades.IfipMenuFacade;
import ec.renafipse.mks.negocio.seguridades.MenuFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "ifipMenuController")
@ViewScoped
public class IfipMenuController extends AbstractController<IfipMenu> implements Serializable {

    @EJB
    private IfipMenuFacade ejbFacade;

    @EJB
    private MenuFacade ejbFacadeMenu;

    private List<IfipMenu> itemsIfipMenu;

    private List<Menu> itemsModulosMenu;

    public IfipMenuController() {
        super(IfipMenu.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.itemsIfipMenu = this.ejbFacade.findAll();
        this.itemsModulosMenu = this.ejbFacadeMenu.getItemsModulo(Long.parseLong("2"));
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getIfipMenuPK().setCodigoMenu(this.getSelected().getMenu().getCodigo());
        this.getSelected().getIfipMenuPK().setCodigoIfip(this.getSelected().getIfip().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setIfipMenuPK(new ec.renafipse.mks.modelo.seguridades.IfipMenuPK());
    }

    public void creaIfipMenu(ActionEvent event) {
        boolean registrado = false;
        this.initializeEmbeddableKey();
        this.setEmbeddableKeys();

        for (IfipMenu is : this.itemsIfipMenu) {
            if (is.getIfipMenuPK().getCodigoIfip() == this.getSelected().getIfipMenuPK().getCodigoIfip()
                    && is.getIfipMenuPK().getCodigoMenu() == this.getSelected().getIfipMenuPK().getCodigoMenu()) {
                registrado = true;
                break;
            }
        }
        if (registrado == true) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExisteIfipMenu");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getSelected().setEliminado(this.getSelected().getEliminado());
            this.ejbFacade.create(this.getSelected());
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
            MuestraMensaje.addSatisfactorio(msg);
            this.setItemsIfipMenu(this.ejbFacade.findAll());

        }
 
    }
    

    public void editaIfipMenu(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.setEmbeddableKeys();
                this.getSelected().setEliminado(this.getSelected().getEliminado());
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsIfipMenu(this.ejbFacade.findAll());
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"usuarioIfipAgenciaBean", "editaUsuarioIfipAgencia", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     *
     * @param event
     */
    public void eliminaIfipMenu(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsIfipMenu(this.ejbFacade.findAll());
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"usuarioIfipAgenciaBean", "eliminaUsuarioIfipAgencia", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     * @return the itemsIfipMenu
     */
    public List<IfipMenu> getItemsIfipMenu() {
        return itemsIfipMenu;
    }

    /**
     * @param itemsIfipMenu the itemsIfipMenu to set
     */
    public void setItemsIfipMenu(List<IfipMenu> itemsIfipMenu) {
        this.itemsIfipMenu = itemsIfipMenu;
    }

    /**
     * @return the itemsModulosMenu
     */
    public List<Menu> getItemsModulosMenu() {
        return itemsModulosMenu;
    }

    /**
     * @param itemsModulosMenu the itemsModulosMenu to set
     */
    public void setItemsModulosMenu(List<Menu> itemsModulosMenu) {
        this.itemsModulosMenu = itemsModulosMenu;
    }

}
