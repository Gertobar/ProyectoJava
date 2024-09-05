package ec.renafipse.mks.control.ifips;


import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Boveda;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ifips.BovedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "bovedaController")
@ViewScoped
public class BovedaController extends AbstractController<Boveda> implements Serializable {

    @EJB
    private BovedaFacade ejbFacade;
    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;
    @EJB 
    private UsuarioFacade ejbFacadeUsuario;
    
    private List<Usuario> itemsUsuario;
    private List<IfipAgencia> itemsIfipAgencia;
    

    
    
       
   // private List<Boveda> itemsBoveda;
    
    public BovedaController() {
        super(Boveda.class);
    }

    public void create(ActionEvent event)
    {
    this.setSelected(new Boveda());
        
    }
    public void guardar(ActionEvent event)
    {
         // this.ifipAgencia=this.getSelected().getCodigoIfipAgencia();
       //System.out.println("entre  guardar  boveda");
          this.getSelected().setNombre(this.getSelected().getNombre());
          //System.out.println("nombre"+this.getSelected().getNombre());
          this.getSelected().setDescripcion(this.getSelected().getDescripcion());
          //System.out.println("descripcion"+this.getSelected().getDescripcion());
          this.getSelected().setAncho(this.getSelected().getAncho());
          //System.out.println("ancho"+this.getSelected().getAncho());
          this.getSelected().setLargo(this.getSelected().getLargo());
          //System.out.println("largo"+this.getSelected().getLargo());
          this.getSelected().setAltura(this.getSelected().getAltura());
          //System.out.println("altura"+this.getSelected().getAltura());
          this.getSelected().setTieneCamaras(this.getSelected().getTieneCamaras());
          //System.out.println("tiene camaras"+this.getSelected().getTieneCamaras());          
         // this.getSelected().setResponsable(this.getSelected().getResponsableBoveda().getCodigo());
          this.getSelected().setResponsableBoveda(this.getSelected().getResponsableBoveda());
          //System.out.println("responsable"+this.getSelected().getResponsableBoveda());
          this.getSelected().setIfipAgenciaBoveda(this.getSelected().getCodigoIfipAgencia().getCodigo());
          //System.out.println("ifip agencia "+this.getSelected().getCodigoIfipAgencia());
          this.getSelected().setEliminado(this.getSelected().getEliminado());
          //System.out.println("eliminado"+this.getSelected().getEliminado());
          
          this.ejbFacade.create(this.getSelected());
      //    this.setItemsIfipAgencia(this.ejbFacadeIfipAgencia.getItemsIfipAgenciaPorIfip(ActivacionUsuario.getCodigoIfip()));
              String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
        MuestraMensaje.addSatisfactorio(msg);

         
          
    
    }
            
    
    @PostConstruct
    public void init() {
      super.setFacade(ejbFacade);
      this.setItemsUsuario(this.ejbFacadeUsuario.findAll());
//      this.setItemsUsuario(this.ejbFacadeUsuario.getItemsUsuarioIfipEliminado(ActivacionUsuario.getCodigoIfip(),'N'));
//      this.setItemsIfipAgencia(this.ejbFacadeIfipAgencia.getItemsIfipAgenciaPorIfip(ActivacionUsuario.getCodigoIfip()));
      this.setItemsIfipAgencia(this.ejbFacadeIfipAgencia.findAll());
//      
       //System.out.println("lista de usuarios "+this.getItemsUsuario());
       //System.out.println("lista de ifipAgencias "+this.getItemsIfipAgencia());
        
      
      
       // this.itemsBoveda=this.ejbFacade.getItemsResponsableIfipEliminado("CAJERO/A", ActivacionUsuario.getCodigoIfip(), 'N');
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






   
    
}
