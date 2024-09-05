package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioGuiaDeposito;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioGuiaDepositoFacade;
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

@ManagedBean(name = "usuarioGuiaDepositoController")
@ViewScoped
public class UsuarioGuiaDepositoController extends AbstractController<UsuarioGuiaDeposito> implements Serializable {

    @EJB
    private UsuarioGuiaDepositoFacade ejbFacade;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private IfipFacade ejbFacadeIfip;

    private UsuarioGuiaDeposito usuarioGuiaDeposito;

    private List<Usuario> itemsUsuario;
    private List<IfipAgencia> itemsIfipAgencia;
    private List<UsuarioGuiaDeposito> itemsUsuarioGuiaDeposito;

    public UsuarioGuiaDepositoController() {
        super(UsuarioGuiaDeposito.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setUsuarioGuiaDeposito(new UsuarioGuiaDeposito());
        this.setItemsUsuarioGuiaDeposito(this.ejbFacade.findAll());
        Ifip ifip = this.ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        this.itemsUsuario = this.ejbFacadeUsuario.getItemsUsuarioIfipDifUsuCon(ifip.getCodigo(), ActivacionUsuario.getCodigoUsuario(), 'N');
        this.itemsIfipAgencia = this.ejbFacadeIfipAgencia.getItemsIfipAgencia(ifip.getCodigo(), 'N');
    }

    /**
     *
     * @param event
     */
    public void nuevo(ActionEvent event) {
        this.setSelected(new UsuarioGuiaDeposito());
        this.getSelected().setFechaHabilitacion(new Date());
    }

    /**
     *
     * @param event
     */
    public void creaUsuarioGuiaDeposito(ActionEvent event) {
        boolean registrado = false;

        for (UsuarioGuiaDeposito ugd : this.getItemsUsuarioGuiaDeposito()) {
            if (ugd.getCodigoUsuario() == this.getSelected().getUsuario().getCodigo().longValue()
                    && ugd.getCodigoIfipAgencia() == this.getSelected().getIfipAgencia().getCodigo().longValue()) {
                registrado = true;
                break;
            }
        }
        if (registrado == true) {            
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioExisteAgencia");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getSelected().setCodigoUsuario(this.getSelected().getUsuario().getCodigo());
            this.getSelected().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
            this.getSelected().setCodigoHabilitadoPor(this.getSelected().getHabilitadoPor().getCodigo());
            this.getSelected().setFechaHabilitacion(this.getSelected().getFechaHabilitacion());
            this.getSelected().setEliminado(this.getSelected().getEliminado());
            this.ejbFacade.create(this.getSelected());
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
            MuestraMensaje.addSatisfactorio(msg);
            this.setItemsUsuarioGuiaDeposito(this.ejbFacade.findAll());
        }  
    }

    /**
     *
     * @param event
     */
    public void editaUsuarioGuiaDeposito(ActionEvent event) {
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
                this.setItemsUsuarioGuiaDeposito(this.ejbFacade.findAll());
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
    public void eliminaUsuarioGuiaDeposito(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                this.setItemsUsuarioGuiaDeposito(this.ejbFacade.findAll());
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
     * @return the itemsUsuarioGuiaDeposito
     */
    public List<UsuarioGuiaDeposito> getItemsUsuarioGuiaDeposito() {
        return itemsUsuarioGuiaDeposito;
    }

    /**
     * @param itemsUsuarioGuiaDeposito the itemsUsuarioGuiaDeposito to set
     */
    public void setItemsUsuarioGuiaDeposito(List<UsuarioGuiaDeposito> itemsUsuarioGuiaDeposito) {
        this.itemsUsuarioGuiaDeposito = itemsUsuarioGuiaDeposito;
    }

    /**
     * @return the usuarioGuiaDeposito
     */
    public UsuarioGuiaDeposito getUsuarioGuiaDeposito() {
        return usuarioGuiaDeposito;
    }

    /**
     * @param usuarioGuiaDeposito the usuarioGuiaDeposito to set
     */
    public void setUsuarioGuiaDeposito(UsuarioGuiaDeposito usuarioGuiaDeposito) {
        this.usuarioGuiaDeposito = usuarioGuiaDeposito;
    }

}
