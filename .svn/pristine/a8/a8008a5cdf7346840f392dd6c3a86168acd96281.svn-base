package ec.renafipse.mks.control.dpfs;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.dpfs.TasaInteresDpfIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.dpfs.TasaInteresDpfIfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "tasaInteresDpfIfipController")
@ViewScoped
public class TasaInteresDpfIfipController extends AbstractController<TasaInteresDpfIfip> {

    @EJB
 private TasaInteresDpfIfipFacade ejbFacade;
   @EJB
    private IfipFacade ejbFacadeIfip;
   private Usuario usuario;
 private List<TasaInteresDpfIfip> itemsDpfTasaInteres;

 private Ifip ifip;
   
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
          this.ifip = this.ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        //this.setItemsDpfTasaInteres(this.ejbFacade.findAll());
        this.setItemsDpfTasaInteres(this.ejbFacade.getItemDpfTasaInteresIfip(ifip));
//        this.setItemsDpfTasaInteres(this.ejbFacade.getItemDpfTasaInteresIfip(ifip));
        System.out.println("ifip"+ifip);
        setUsuario(ActivacionUsuario.getUsuario());
        System.out.println("registrado por"+ getUsuario().getUsername());
    }
     @Override
    protected void setEmbeddableKeys() {
        
       
        //this.getSelected().setRegistradoPor( getSelected().getRegistradoPorUsuario().getCodigo());
        getSelected().setFechaRegistro(new Date());
        this.getSelected().setRegistradaPor(getUsuario());
          
    }


    /**
     * @return the itemsDpfTasaInteres
     */
    public List<TasaInteresDpfIfip> getItemsDpfTasaInteres() {
        return itemsDpfTasaInteres;
    }

    /**
     * @param itemsDpfTasaInteres the itemsDpfTasaInteres to set
     */
    public void setItemsDpfTasaInteres(List<TasaInteresDpfIfip> itemsDpfTasaInteres) {
        this.itemsDpfTasaInteres = itemsDpfTasaInteres;
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the itemsDpfTasaInteres
     */
   

}
