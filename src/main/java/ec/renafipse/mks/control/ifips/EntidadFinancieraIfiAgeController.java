package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.ifips.EntidadFinancieraIfiAge;
import ec.renafipse.mks.modelo.ifips.EntidadFinancieraIfiAgePK;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;

import ec.renafipse.mks.negocio.comunes.EntidadFinancieraFacade;
import ec.renafipse.mks.negocio.ifips.EntidadFinancieraIfiAgeFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "entidadFinancieraIfiAgeController")
@ViewScoped
public class EntidadFinancieraIfiAgeController extends AbstractController<EntidadFinancieraIfiAge> implements Serializable {

    @EJB
    private EntidadFinancieraIfiAgeFacade ejbFacade;
    @EJB
    private EntidadFinancieraFacade ejbFacadeEntidadFinanciera;
    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;
    
    private List<IfipAgencia> itemsIfipAgencia;
    private List<EntidadFinancieraIfiAge> itemsEntidadFinancieraIfipAge;
    private List<EntidadFinanciera> itemsEntidadFinanciera;

    public EntidadFinancieraIfiAgeController() {
        super(EntidadFinancieraIfiAge.class);
    }

    public void nuevo(ActionEvent event) {
        this.setSelected(new EntidadFinancieraIfiAge());

    }

    public void eliminar(ActionEvent event) {
        this.delete(event);
        this.setItemsEntidadFinancieraIfipAge(this.ejbFacade.getItemsEntidadFinancieraIfip(ActivacionUsuario.getCodigoIfip()));

    }
    public void editar(ActionEvent event)
    {
    this.editar(event);
    this.setItemsEntidadFinancieraIfipAge(this.ejbFacade.getItemsEntidadFinancieraIfip(ActivacionUsuario.getCodigoIfip()));
    }

    public void guardar(ActionEvent event) {

        EntidadFinancieraIfiAgePK entidadFinancieraIfiAgePk = new EntidadFinancieraIfiAgePK();
        entidadFinancieraIfiAgePk.setCodigoEntidadFinanciera(this.getSelected().getEntidadFinanciera().getCodigo());
        entidadFinancieraIfiAgePk.setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
        
        //System.out.println("entidad ifip pk"+entidadFinancieraIfiAgePk);
        this.getSelected().setEntidadFinancieraIfiAgePK(entidadFinancieraIfiAgePk);
           
        this.getSelected().setIngresadoPor(ActivacionUsuario.getCodigoUsuario());
         //System.out.println("ingresado por"+this.getSelected().getIngresadoPor());
        this.getSelected().setFechaIngreso(this.getSelected().getFechaIngreso());
         //System.out.println("fecha ingreso por"+this.getSelected().getFechaIngreso());
        this.getSelected().setIfipAgencia(this.getSelected().getIfipAgencia());
         //System.out.println("ifipagencia"+this.getSelected().getIfipAgencia());
        this.getSelected().setEliminado(this.getSelected().getEliminado());
         //System.out.println("eliminado"+this.getSelected().getEliminado());
        this.ejbFacade.create(this.getSelected());
        this.setItemsEntidadFinancieraIfipAge(this.ejbFacade.findAll());
        String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
        MuestraMensaje.addSatisfactorio(msg);

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        
        this.setItemsIfipAgencia(this.ejbFacadeIfipAgencia.getItemsIfipAgencia(ActivacionUsuario.getCodigoIfip(),'N'));
        this.setItemsEntidadFinanciera(this.ejbFacadeEntidadFinanciera.getItemsEntidadFinancieraEliminado('N'));

    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getEntidadFinancieraIfiAgePK().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setEntidadFinancieraIfiAgePK(new ec.renafipse.mks.modelo.ifips.EntidadFinancieraIfiAgePK());
    }

    /**
     * @return the itemsEntidadFinanciera
     */
    public List<EntidadFinanciera> getItemsEntidadFinanciera() {
        return itemsEntidadFinanciera;
    }

    /**
     * @param itemsEntidadFinanciera the itemsEntidadFinanciera to set
     */
    public void setItemsEntidadFinanciera(List<EntidadFinanciera> itemsEntidadFinanciera) {
        this.itemsEntidadFinanciera = itemsEntidadFinanciera;
    }

    /**
     * @return the itemsEntidadFinancieraIfipAge
     */
    public List<EntidadFinancieraIfiAge> getItemsEntidadFinancieraIfipAge() {
        return itemsEntidadFinancieraIfipAge;
    }

    /**
     * @param itemsEntidadFinancieraIfipAge the itemsEntidadFinancieraIfipAge to
     * set
     */
    public void setItemsEntidadFinancieraIfipAge(List<EntidadFinancieraIfiAge> itemsEntidadFinancieraIfipAge) {
        this.itemsEntidadFinancieraIfipAge = itemsEntidadFinancieraIfipAge;
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
     * @return the entidadFinanciera
     */
}
