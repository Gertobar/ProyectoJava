package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.AhorroTasaInteresIfip;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.ProductoIfipPK;
import ec.renafipse.mks.modelo.ahorros.TasaInteresProductoIfip;
import ec.renafipse.mks.modelo.ahorros.TipoProducto;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TasaInteresProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TipoProductoFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "tasaInteresProductoIfipController")
@ViewScoped
public class TasaInteresProductoIfipController extends AbstractController<TasaInteresProductoIfip> implements Serializable {

    @EJB
    private TasaInteresProductoIfipFacade ejbFacade;
    @EJB
    private TipoProductoFacade ejbFacadeTipoProducto;
    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private MonedaFacade ejbFacadeMoneda;
    @EJB
    private ProductoIfipFacade ejbFacadeProdutoIfip;
    private long varangoInicial;
    private TipoProducto tipoProducto;
    private Moneda moneda;
    private Date fechaRegistro;
    private AhorroTasaInteresIfip tasaInteres;
    private List<TasaInteresProductoIfip> itemsTasaInteresProductoIfip;
    private List<TipoProducto> listaTipoProducto;
    private List<Moneda> listaMoneda;
    private Ifip ifip;
    private ProductoIfip productoIfip;
    private Usuario usuario;
    private String msg;

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setitemsTasaInteresProductoIfip(ejbFacade.findAll());
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setListaMoneda(ejbFacadeMoneda.getItemsIfipMonedas(getIfip().getCodigo()));
        setMoneda(new Moneda());

    }

    public void nuevo(ActionEvent event) {
        if (getListaTipoProducto() != null) {
            getListaTipoProducto().clear();
        }
        productoIfip = new ProductoIfip();
                        
  
        setMoneda(new Moneda());
        setSelected(new TasaInteresProductoIfip());
    
        getSelected().setFechaRegistro(new Date());
        getSelected().setProductoIfip(new ProductoIfip());//En porceso de eliminacino
        getSelected().setFechaRegistro(new Date());
        getSelected().setRegistradoUsuarioPor(getUsuario());
      //  //System.out
       
                
        //System.out.println("llegoooo nuevo"+getSelected().getCodigoTipoProducto());

    }

    public boolean validarI(Long codigoTipoProducto,Long codigoTasaInteres,Long rangoInicial,Long rangoFinal)
    {
        int numRegs = ejbFacade.findAll().size();
   
                     
        boolean respuesta = false;
      
        for (int i = 0; i < numRegs; i++) {
            if (ejbFacade.getItemsTasaInteresProductoIfipI(codigoTipoProducto, codigoTasaInteres, rangoFinal, rangoInicial,'N').isEmpty()) {

                respuesta = true;
            } else {
                respuesta = false;
               
            }
        }
       return respuesta;
    }

    public void actualizarMoneda() {
        if (moneda != null) {
            setListaTipoProducto(ejbFacadeTipoProducto.getItemsTipoProductoMoneda(moneda.getCodigo(), 'N'));// si el PRODUCTO no esta eliminado
        }
    }
    public boolean validaRango()
    { 
        boolean respuesta=false;
        if(getSelected().getRangoFinal()<=getSelected().getRangoInicial())
        {
           
          
//           String Msg=ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RangosInconsistentes");
//                  MuestraMensaje.addAdvertencia(Msg);
             respuesta=true;
        }
        return respuesta;
    }

    public void guardar(ActionEvent event) {
    
        ProductoIfip pi = this.ejbFacadeProdutoIfip.find(new ProductoIfipPK(tipoProducto.getCodigo(), moneda.getCodigo(), ifip.getCodigo()));
        if (pi == null) {
            throw new ValidatorException(new FacesMessage("Ya existe este registro"));
        } else {
            
            this.getSelected().setProductoIfip(pi);
            this.getSelected().setCodigoMoneda(moneda.getCodigo());
            //System.out.println("moneda" + moneda.getCodigo());
            this.getSelected().setCodigoTipoProducto(tipoProducto.getCodigo());
            //System.out.println("tipoprod" + tipoProducto.getCodigo());
            this.getSelected().setCodigoIfip(ifip.getCodigo());
            //System.out.println("ifip" + ifip.getCodigo());
            this.getSelected().setTasaInteres(getSelected().getCodigoTasaInteres().getCodigo());
            //System.out.println("interes" + this.getSelected().getCodigoTasaInteres().getCodigo());
            this.getSelected().setRangoInicial(getSelected().getRangoInicial());
            //System.out.println("inicial" + this.getSelected().getRangoInicial());
            this.getSelected().setRangoFinal(this.getSelected().getRangoFinal());
            //System.out.println("final" + this.getSelected().getRangoFinal());
            this.getSelected().setRegistradoPor(ActivacionUsuario.getUsuario().getCodigo());
            //System.out.println("registrado por" + ActivacionUsuario.getUsuario().getCodigo());
            this.getSelected().setFechaRegistro(getSelected().getFechaRegistro());
            //System.out.println("registro" + getSelected().getFechaRegistro());
            this.getSelected().setEliminado(this.getSelected().getEliminado());
            //System.out.println("setEliminado" + this.getSelected().getEliminado());
             if(!validaRango())
                    {
                       
                        if (this.ejbFacade.getItemsTasaInteresProductoIfip(tipoProducto.getCodigo(), ifip.getCodigo(), moneda.getCodigo(), getSelected().getRangoInicial()).isEmpty()) 
                                {
                            if (validarI(tipoProducto.getCodigo(),this.getSelected().getCodigoTasaInteres().getCodigo(),getSelected().getRangoInicial(),getSelected().getRangoFinal())) 
                            {

                            this.ejbFacade.edit(this.getSelected());
                            this.setitemsTasaInteresProductoIfip(this.ejbFacade.findAll());
                            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroGrabado");
                            MuestraMensaje.addSatisfactorio(msg);
                           
       
                            
                             }else
                            {
                                  this.setitemsTasaInteresProductoIfip(this.ejbFacade.findAll());
                                String Msg=ResourceBundle.getBundle("/propiedadesMensajesEC").getString("YaExisteTasaInteres");
                                 MuestraMensaje.addAdvertencia(Msg);
                                 
                            }

                               }else
                               {
                                     this.setitemsTasaInteresProductoIfip(this.ejbFacade.findAll());
                                    String Msg=ResourceBundle.getBundle("/propiedadesMensajesEC").getString("YaExisteTasaInteres");
                                    MuestraMensaje.addAdvertencia(Msg);
                                    
                               }
                         }else
                             {
                            this.setitemsTasaInteresProductoIfip(this.ejbFacade.findAll());
                                 setMsg("Rango Final menor al Inicial");
                                 MuestraMensaje.addAdvertencia(getMsg());
                                 
                             }
                }
                   
        }

    

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ifip getIfip() {
        return ifip;
    }

    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    public Usuario getUsuarioTasaProd() {
        return getUsuario();
    }

    public void setUsuarioTasaProd(Usuario usuario) {
        this.setUsuario(usuario);
    }

    public List<TipoProducto> getListaTipoProducto() {
        return listaTipoProducto;
    }

    public void setListaTipoProducto(List<TipoProducto> listaTipoProducto) {
        this.listaTipoProducto = listaTipoProducto;
    }

    public TasaInteresProductoIfipController() {
        super(TasaInteresProductoIfip.class);
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the productoIfip
     */
    /**
     * @return the tasaInteres
     */
    public AhorroTasaInteresIfip getTasaInteres() {
        return tasaInteres;
    }

    /**
     * @param tasaInteres the tasaInteres to set
     */
    public void setTasaInteres(AhorroTasaInteresIfip tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    /**
     * @return the itemProductoIfip
     */
    /**
     * @return the ejbFacadeProductoIfip
     */
    public TipoProductoFacade getEjbFacadeTipoProducto() {
        return ejbFacadeTipoProducto;
    }

    /**
     * @param ejbFacadeTipoProducto the ejbFacadeTipoProducto to set
     */
    public void setEjbFacadeTipoProducto(TipoProductoFacade ejbFacadeTipoProducto) {
        this.ejbFacadeTipoProducto = ejbFacadeTipoProducto;
    }

    /**
     * @return the listaMoneda
     */
    public List<Moneda> getListaMoneda() {
        return listaMoneda;
    }

    /**
     * @param listaMoneda the listaMoneda to set
     */
    public void setListaMoneda(List<Moneda> listaMoneda) {
        this.listaMoneda = listaMoneda;
    }

    /**
     * @return the productoIfip
     */
    public ProductoIfip getProductoIfip() {
        return productoIfip;
    }

    /**
     * @param productoIfip the productoIfip to set
     */
    public void setProductoIfip(ProductoIfip productoIfip) {
        this.productoIfip = productoIfip;
    }

    /**
     * @return the itemsTasaInteresProductoIfip
     */
    public List<TasaInteresProductoIfip> getitemsTasaInteresProductoIfip() {
        return getItemsTasaInteresProductoIfip();
    }

    /**
     * @param itemsTasaInteresProductoIfip the itemsTasaInteresProductoIfip to set
     */
    public void setitemsTasaInteresProductoIfip(List<TasaInteresProductoIfip> itemsTasaInteresProductoIfip) {
        this.setItemsTasaInteresProductoIfip(itemsTasaInteresProductoIfip);
    }

    /**
     * @return the itemsTasaInteresProductoIfip
     */
    public List<TasaInteresProductoIfip> getItemsTasaInteresProductoIfip() {
        return itemsTasaInteresProductoIfip;
    }

    /**
     * @param itemsTasaInteresProductoIfip the itemsTasaInteresProductoIfip to set
     */
    public void setItemsTasaInteresProductoIfip(List<TasaInteresProductoIfip> itemsTasaInteresProductoIfip) {
        this.itemsTasaInteresProductoIfip = itemsTasaInteresProductoIfip;
    }

    /**
     * @return the varangoInicial
     */
    public long getVarangoInicial() {
        
        return varangoInicial;
    }

    /**
     * @param varangoInicial the varangoInicial to set
     */
    public void setVarangoInicial(long varangoInicial) {
        this.varangoInicial = varangoInicial;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

//    private void setMsg(String string) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private String getMsg() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    /**
     * @return the rangoInicial
     */
}
