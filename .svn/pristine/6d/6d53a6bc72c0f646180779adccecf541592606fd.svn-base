package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.TipoComputador;
import ec.renafipse.mks.modelo.ifips.TipoSistemaOperativo;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.ifips.TipoComputadorFacade;
import ec.renafipse.mks.negocio.ifips.TipoSistemaOperativoFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "computadorController")
@ViewScoped
public class ComputadorController extends AbstractController<Computador> implements Serializable {

    @EJB
    private ComputadorFacade ejbFacade;
    @EJB
    private IfipFacade EjbIfipFacade;
    @EJB    
    private TipoSistemaOperativoFacade ejbFacadeTipoSistemaOperativo;
    @EJB
    private TipoComputadorFacade ejbFacadeTipoComputador;
  
    private Computador computador;
    private List<Computador> itemsComputador;
    private Ifip ifip;
    private List<TipoSistemaOperativo> itemSistemasOperativos;
    private List<TipoComputador> itemsTipoComputador;
   

    public ComputadorController() {
        super(Computador.class);
        
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.ifip = this.EjbIfipFacade.find(ActivacionUsuario.getCodigoIfip());
        this.setItemSistemasOperativos(this.ejbFacadeTipoSistemaOperativo.getItemsSistemaOperativoEliminado('N'));
        this.setItemsComputador(this.ejbFacade.getItemIfipComputador(ActivacionUsuario.getCodigoIfip()));
        this.setItemsTipoComputador(this.ejbFacadeTipoComputador.getItemsSistemaOperativoEliminado('N'));
        
        
     
      

    }
    
    public void nuevo (ActionEvent event)
    {
        this.setSelected(new Computador());
    }
  
 
    public void guardar(ActionEvent event) {
        this.getSelected().setNombre(this.getSelected().getNombre().toString());
        this.getSelected().setRutaImpresora(this.getSelected().getRutaImpresora().toString());
        this.getSelected().setDireccionIp(this.getSelected().getDireccionIp().toString());
        this.getSelected().setDireccionMac(this.getSelected().getDireccionMac().toString());
        this.getSelected().setCodigoTipoComputador(this.getSelected().getCodigoTipoComputador());
        this.getSelected().setCodigoIfipAgenciaPertenece(this.getSelected().getCodigoIfipAgenciaPertenece());
        this.getSelected().setCodigoIfip(ifip);
        this.getSelected().setCodigoDestinoComputador(this.getSelected().getCodigoDestinoComputador());
        this.getSelected().setEliminado(this.getSelected().getEliminado());
        
        
        this.ejbFacade.create(this.getSelected());
        this.itemsComputador = this.ejbFacade.getItemIfipComputador(ActivacionUsuario.getCodigoIfip());
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
        MuestraMensaje.addSatisfactorio(msg);
     
    }
   

    /**
     * @return the itemsComputador
     */
    public List<Computador> getItemsComputador() {
        return itemsComputador;
    }
    /**
     * @param itemsComputador the itemsComputador to set
     */
    public void setItemsComputador(List<Computador> itemsComputador) {
        this.itemsComputador = itemsComputador;
    }
    /**
     * @return the EjbIfipFacade
     */
    public IfipFacade getEjbIfipFacade() {
        return EjbIfipFacade;
    }
    /**
     * @param EjbIfipFacade the EjbIfipFacade to set
     */
    public void setEjbIfipFacade(IfipFacade EjbIfipFacade) {
        this.EjbIfipFacade = EjbIfipFacade;
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
     * @return the computador
     */
    public Computador getComputador() {
        return computador;
    }

    /**
     * @param computador the computador to set
     */
    public void setComputador(Computador computador) {
        this.computador = computador;
    }

  
    public List<TipoSistemaOperativo> getItemSistemasOperativos() {
        return itemSistemasOperativos;
    }

    /**
     * @param itemSistemasOperativos the itemSistemasOperativos to set
     */
    public void setItemSistemasOperativos(List<TipoSistemaOperativo> itemSistemasOperativos) {
        this.itemSistemasOperativos = itemSistemasOperativos;
    }

    /**
     * @return the itemsTipoComputador
     */
    public List<TipoComputador> getItemsTipoComputador() {
        return itemsTipoComputador;
    }

    /**
     * @param itemsTipoComputador the itemsTipoComputador to set
     */
    public void setItemsTipoComputador(List<TipoComputador> itemsTipoComputador) {
        this.itemsTipoComputador = itemsTipoComputador;
    }

  

}
