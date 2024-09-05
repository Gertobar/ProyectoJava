package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.AhorroTasaInteresIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.AhorroTasaInteresIfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;

import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "ahorroTasaInteresIfipController")
@ViewScoped
public class AhorroTasaInteresIfipController extends AbstractController<AhorroTasaInteresIfip> implements Serializable {

    @EJB
    private AhorroTasaInteresIfipFacade ejbFacade;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;
    @EJB
    private IfipFacade ejbFacadeIfip;

    private Usuario usuarioAhorroTasaInteres;
    private Ifip ifip;

    private List<Ifip> itemsIfipAhorroTasaInteres;
    private List<AhorroTasaInteresIfip> itemsAhorroTasaInteresIfip;

    public AhorroTasaInteresIfipController() {
        super(AhorroTasaInteresIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.ifip = this.ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        this.setItemsAhorroTasaInteresIfip(this.ejbFacade.getItemAhorroTasaInteresIfip(ifip));
        this.setUsuarioAhorroTasaInteres(this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario()));

        this.setItemsIfipAhorroTasaInteres(this.ejbFacadeIfip.getItemsIfipEliminado('N'));
    }

    public void create(ActionEvent event) {
        this.setSelected(new AhorroTasaInteresIfip());

    }

    public void guardar(ActionEvent event) {
        this.getSelected().setCodigoIfip(this.getSelected().getCodigoIfip());
        //System.out.println("ifip" + this.getSelected().getCodigoIfip().getCodigo());
        this.getSelected().setRegristradoPor(this.getUsuarioAhorroTasaInteres().getCodigo());
        //System.out.println("registrado por " + this.getUsuarioAhorroTasaInteres().getCodigo());
        this.getSelected().setValor(this.getSelected().getValor());
        //System.out.println("valor " + this.getSelected().getValor());
        this.getSelected().setFechaRegistro(this.getSelected().getFechaRegistro());
        //System.out.println("fecha registra" + this.getSelected().getFechaRegistro());
        this.getSelected().setEliminado(this.getSelected().getEliminado());
        //System.out.println("eliminado " + this.getSelected().getEliminado());
        this.ejbFacade.create(this.getSelected());
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
        MuestraMensaje.addSatisfactorio(msg);
        this.setItemsAhorroTasaInteresIfip(this.ejbFacade.getItemAhorroTasaInteresIfip(ifip));

    }

    /**
     * @return the usuarioAhorroTasaInteres
     */
    public Usuario getUsuarioAhorroTasaInteres() {
        return usuarioAhorroTasaInteres;
    }

    /**
     * @param usuarioAhorroTasaInteres the usuarioAhorroTasaInteres to set
     */
    public void setUsuarioAhorroTasaInteres(Usuario usuarioAhorroTasaInteres) {
        this.usuarioAhorroTasaInteres = usuarioAhorroTasaInteres;
    }

    /**
     * @return the itemsIfipAhorroTasaInteres
     */
    public List<Ifip> getItemsIfipAhorroTasaInteres() {
        return itemsIfipAhorroTasaInteres;
    }

    /**
     * @param itemsIfipAhorroTasaInteres the itemsIfipAhorroTasaInteres to set
     */
    public void setItemsIfipAhorroTasaInteres(List<Ifip> itemsIfipAhorroTasaInteres) {
        this.itemsIfipAhorroTasaInteres = itemsIfipAhorroTasaInteres;
    }

    /**
     * @return the itemsAhorroTasaInteresIfip
     */
    public List<AhorroTasaInteresIfip> getItemsAhorroTasaInteresIfip() {
        return itemsAhorroTasaInteresIfip;
    }

    /**
     * @param itemsAhorroTasaInteresIfip the itemsAhorroTasaInteresIfip to set
     */
    public void setItemsAhorroTasaInteresIfip(List<AhorroTasaInteresIfip> itemsAhorroTasaInteresIfip) {
        this.itemsAhorroTasaInteresIfip = itemsAhorroTasaInteresIfip;
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
     * @return the codigoIfip
     */
}
