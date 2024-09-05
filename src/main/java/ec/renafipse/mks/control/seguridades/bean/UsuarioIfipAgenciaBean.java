package ec.renafipse.mks.control.seguridades.bean;

import ec.renafipse.mks.control.seguridades.*;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Perfil;
import ec.renafipse.mks.modelo.seguridades.Rol;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.PerfilFacade;
import ec.renafipse.mks.negocio.seguridades.RolFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioIfipAgenciaFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "usuarioIfipAgenciaBean")
@SessionScoped
public class UsuarioIfipAgenciaBean extends AbstractController<UsuarioIfipAgencia> implements Serializable {

    @EJB
    private UsuarioIfipAgenciaFacade ejbFacade;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private PerfilFacade ejbFacadePerfil;

    @EJB
    private RolFacade ejbFacadeRol;

    private List<UsuarioIfipAgencia> itemsUsuarioIfipAgencia;
    private List<Usuario> itemsUsuario;
    private List<Perfil> itemsPerfil;
    private List<IfipAgencia> itemsIfipAgencia;
    private List<Rol> itemsRol;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Almacena la entidad de la agencia seleccionada por el usuario, 
    // variable usada en metodo listener de la ventana de angecias
    private UsuarioIfipAgencia usuarioIfipAgencia;
    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------

    public UsuarioIfipAgenciaBean() {
        super(UsuarioIfipAgencia.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setItemsUsuarioIfipAgencia(this.ejbFacade.findAll());
        this.itemsIfipAgencia = this.ejbFacadeIfipAgencia.getItemsIfipAgencia(ActivacionUsuario.getCodigoIfip(), 'N');
        this.itemsUsuario = this.ejbFacadeUsuario.getItemsUsuarioIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
        this.itemsPerfil = this.ejbFacadePerfil.getItemsPerfil('N');
        this.itemsRol = this.ejbFacadeRol.getItemsRol('N');
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getUsuarioIfipAgenciaPK().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
        this.getSelected().getUsuarioIfipAgenciaPK().setCodigoUsuario(this.getSelected().getUsuario().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setUsuarioIfipAgenciaPK(new ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgenciaPK());
    }

    /**
     *
     * @param event
     */
    public void nuevo(ActionEvent event) {
        this.setSelected(new UsuarioIfipAgencia());
        initializeEmbeddableKey();
        this.getSelected().setFechaAsignacion(new Date());
    }

    /**
     * Metodo
     *
     * @param event
     */
    public void creaUsuarioIfipAgencia(ActionEvent event) {
        boolean registrado = false;
        for (UsuarioIfipAgencia uia : this.itemsUsuarioIfipAgencia) {
            if (uia.getUsuarioIfipAgenciaPK().getCodigoUsuario() == this.getSelected().getUsuario().getCodigo()
                    && uia.getUsuarioIfipAgenciaPK().getCodigoIfipAgencia() == this.getSelected().getIfipAgencia().getCodigo()) {
                registrado = true;
                break;
            }
        }
        if (registrado == true) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioExisteAgencia");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.initializeEmbeddableKey();
            this.setEmbeddableKeys();
            this.getSelected().getCodigoRol().setCodigo(this.getSelected().getCodigoRol().getCodigo());
            this.getSelected().getCodigoPerfil().setCodigo(this.getSelected().getCodigoPerfil().getCodigo());
            this.getSelected().setAsignadoPor(this.getSelected().getAsignadoPor());
            this.getSelected().setFechaAsignacion(this.getSelected().getFechaAsignacion());
            this.getSelected().setEliminado(this.getSelected().getEliminado());
            this.ejbFacade.create(this.getSelected());
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
            MuestraMensaje.addSatisfactorio(msg);
            this.setItemsUsuarioIfipAgencia(this.ejbFacade.findAll());
        }
    }

    /**
     *
     * @param event
     */
    public void editaUsuarioIfipAgencia(ActionEvent event) {
        boolean registrado = false;

        if (this.getSelected() != null) {
            try {
                this.initializeEmbeddableKey();
                this.setEmbeddableKeys();
                this.getSelected().getCodigoRol().setCodigo(this.getSelected().getCodigoRol().getCodigo());
                this.getSelected().getCodigoPerfil().setCodigo(this.getSelected().getCodigoPerfil().getCodigo());
                this.getSelected().setAsignadoPor(this.getSelected().getAsignadoPor());
                this.getSelected().setFechaAsignacion(this.getSelected().getFechaAsignacion());
                this.getSelected().setEliminado(this.getSelected().getEliminado());
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsUsuarioIfipAgencia(this.ejbFacade.findAll());
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
    public void eliminaUsuarioIfipAgencia(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsUsuarioIfipAgencia(this.ejbFacade.findAll());
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
     * @return the itemsUsuarioIfipAgencia
     */
    public List<UsuarioIfipAgencia> getItemsUsuarioIfipAgencia() {
        return itemsUsuarioIfipAgencia;
    }

    /**
     * @param itemsUsuarioIfipAgencia the itemsUsuarioIfipAgencia to set
     */
    public void setItemsUsuarioIfipAgencia(List<UsuarioIfipAgencia> itemsUsuarioIfipAgencia) {
        this.itemsUsuarioIfipAgencia = itemsUsuarioIfipAgencia;
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Metodo que devuelve los items de las agencias que el usuario tiene
     * asignado Usado en el datagrip de la venta /login/agencia.xhtml
     *
     * @return
     */
    public List<UsuarioIfipAgencia> getItemsPermisosIfiAgeUsu() {
        List<UsuarioIfipAgencia> listUsuarioIfipAgencia = this.ejbFacade.getItemsPermisosIfiAgeUsu(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfip());
        return listUsuarioIfipAgencia;

    }

    /**
     * Metodo que acceder en la ventana de modulos Usado al dar clic o acceder a
     * la agencia
     *
     * @throws IOException
     */
    public void irModulos() throws IOException {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlModuloMKS");
        Sesion.tiempoInactividad((int) this.getUsuarioIfipAgencia().getCodigoPerfil().getTiempoInactividad());
        Sesion.redireccionaPagina(url);
    }
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------

    // --------------------------------------------------------------------------
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    /**
     * @return the usuarioIfipAgencia
     */
    public UsuarioIfipAgencia getUsuarioIfipAgencia() {
        return usuarioIfipAgencia;
    }

    /**
     * @param usuarioIfipAgencia the usuarioIfipAgencia to set
     */
    public void setUsuarioIfipAgencia(UsuarioIfipAgencia usuarioIfipAgencia) {
        this.usuarioIfipAgencia = usuarioIfipAgencia;
    }

    // -- FIN DE ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
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
     * @return the itemsPerfil
     */
    public List<Perfil> getItemsPerfil() {
        return itemsPerfil;
    }

    /**
     * @param itemsPerfil the itemsPerfil to set
     */
    public void setItemsPerfil(List<Perfil> itemsPerfil) {
        this.itemsPerfil = itemsPerfil;
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

}
