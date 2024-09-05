package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioEfeProChe;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioEfeProCheFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "usuarioEfeProCheController")
@ViewScoped
public class UsuarioEfeProCheController extends AbstractController<UsuarioEfeProChe> implements Serializable {

    @EJB
    private UsuarioEfeProCheFacade ejbFacade;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    private UsuarioEfeProChe usuarioEfeProChe;

    private List<Usuario> itemsUsuario;
    private List<UsuarioEfeProChe> itemsUsuarioEfeproChe;
    private List<IfipAgencia> itemsIfipAgencia;

    public UsuarioEfeProCheController() {
        super(UsuarioEfeProChe.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        usuarioEfeProChe = new UsuarioEfeProChe();
        this.itemsUsuarioEfeproChe = this.ejbFacade.findAll();
        this.itemsIfipAgencia = this.ejbFacadeIfipAgencia.getItemsIfipAgencia(ActivacionUsuario.getCodigoIfip(), 'N');
        this.itemsUsuario = this.ejbFacadeUsuario.getItemsUsuarioIfipDifUsuCon(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoUsuario(), 'N');
    }

    public void nuevo(ActionEvent actionEvent) {
        this.setSelected(new UsuarioEfeProChe());
        this.getSelected().setFechaHabilitacion(new Date());
    }

    /**
     *
     * @param event
     */
    public void creaUsuarioEfeProChe(ActionEvent event) {
        boolean registrado = false;
        for (UsuarioEfeProChe uepc : this.itemsUsuarioEfeproChe) {
            if (uepc.getCodigoUsuario() == this.getSelected().getUsuario().getCodigo().longValue()
                    && uepc.getCodigoIfipAgencia() == this.getSelected().getIfipAgencia().getCodigo().longValue()) {
                registrado = true;
                break;
            }
        }
        if (registrado == true) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioExisteAgencia");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getSelected().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
            this.getSelected().setCodigoUsuario(this.getSelected().getUsuario().getCodigo());
            this.getSelected().setCodigoHabilitadoPor(this.getSelected().getHabilitadoPor().getCodigo());
            this.getSelected().setFechaHabilitacion(this.getSelected().getFechaHabilitacion());
            this.getSelected().setEliminado(this.getSelected().getEliminado());
            this.ejbFacade.create(this.getSelected());
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
            MuestraMensaje.addSatisfactorio(msg);
            this.itemsUsuarioEfeproChe = this.ejbFacade.findAll();

        }
    }

    /**
     *
     * @param event
     */
    public void editaUsuarioEfeProChe(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
                this.getSelected().setCodigoUsuario(this.getSelected().getUsuario().getCodigo());
                this.getSelected().setCodigoHabilitadoPor(this.getSelected().getHabilitadoPor().getCodigo());
                this.getSelected().setFechaHabilitacion(this.getSelected().getFechaHabilitacion());
                this.getSelected().setEliminado(this.getSelected().getEliminado());
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsUsuarioEfeproChe(this.ejbFacade.findAll());
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"usuarioEfeProCheController", "editaUsuarioEfeProChe", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     *
     * @param event
     */
    public void eliminaUsuarioEfeProChe(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsUsuarioEfeproChe(this.ejbFacade.findAll());
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"usuarioEfeProCheController", "eliminaUsuarioEfeProChe", CapturaError.getErrorException(ex)});
            }
        }
    }

    /**
     * @return the itemsUsuario
     */
    public List<Usuario> getItemsUsuario() {
        return itemsUsuario;
    }

    /**
     * @param itemsUsuario the itemsUsuario to set
     */
    public void setItemsUsuario(List<Usuario> itemsUsuario) {
        this.itemsUsuario = itemsUsuario;
    }

    /**
     * @return the itemsIfipAgencia
     */
    public List<IfipAgencia> getItemsIfipAgencia() {
        return itemsIfipAgencia;
    }

    /**
     * @param itemsIfipAgencia the itemsIfipAgencia to set
     */
    public void setItemsIfipAgencia(List<IfipAgencia> itemsIfipAgencia) {
        this.itemsIfipAgencia = itemsIfipAgencia;
    }

    /**
     * @return the itemsUsuarioEfeproChe
     */
    public List<UsuarioEfeProChe> getItemsUsuarioEfeproChe() {
        return itemsUsuarioEfeproChe;
    }

    /**
     * @param itemsUsuarioEfeproChe the itemsUsuarioEfeproChe to set
     */
    public void setItemsUsuarioEfeproChe(List<UsuarioEfeProChe> itemsUsuarioEfeproChe) {
        this.itemsUsuarioEfeproChe = itemsUsuarioEfeproChe;
    }

    /**
     * @return the usuarioEfeProChe
     */
    public UsuarioEfeProChe getUsuarioEfeProChe() {
        return usuarioEfeProChe;
    }

    /**
     * @param usuarioEfeProChe the usuarioEfeProChe to set
     */
    public void setUsuarioEfeProChe(UsuarioEfeProChe usuarioEfeProChe) {
        this.usuarioEfeProChe = usuarioEfeProChe;
    }

}
