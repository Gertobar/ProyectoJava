package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Producto;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.ProductoIfipPK;
import ec.renafipse.mks.modelo.ahorros.ProductoPK;
import ec.renafipse.mks.modelo.ahorros.TipoProducto;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.ProductoFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TipoProductoFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "productoIfipController")
@ViewScoped
public class ProductoIfipController extends AbstractController<ProductoIfip> implements Serializable {

    @EJB
    private ProductoIfipFacade ejbFacade;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private TipoProductoFacade ejbFacadeTipoProducto;
    @EJB
    private MonedaFacade ejbFacadeMoneda;
    @EJB
    private ProductoFacade ejbFacadeProducto;

    private TipoProducto tipoProducto, auxTP;
    private Moneda moneda, auxMon;
        private Ifip ifip;
    private Producto producto;
    private Usuario usuario;
    private List<TipoProducto> listaTipoProducto;
    private List<Moneda> listaMoneda;
    private List<TasaInteresProductoIfipController> listaTasaInteresProductoIfip;

    public ProductoIfipController() {
        super(ProductoIfip.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setListaMoneda(ejbFacadeMoneda.getItemsIfipMonedas(ifip.getCodigo()));
        setTipoProducto(new TipoProducto());
        setMoneda(new Moneda());
        setUsuario(ActivacionUsuario.getUsuario());
    }

    @Override
    protected void setEmbeddableKeys() {
        
        getSelected().setProducto(ejbFacadeProducto.getProducto(tipoProducto.getCodigo(), moneda.getCodigo()));
        getSelected().getProductoIfipPK().setCodigoMoneda(moneda.getCodigo());
        getSelected().getProductoIfipPK().setCodigoTipoProducto(tipoProducto.getCodigo());
        getSelected().getProductoIfipPK().setCodigoIfip(getIfip().getCodigo());
        this.getSelected().setRegistradoPor( getSelected().getRegistradoPorUsuario().getCodigo());
        getSelected().setFechaRegistro(new Date());
    }

    @Override
    protected void initializeEmbeddableKey() {
        
        this.getSelected().setProductoIfipPK(new ec.renafipse.mks.modelo.ahorros.ProductoIfipPK());
    }

    public void nuevo(ActionEvent event) {
        auxMon = null;
        auxTP = null;
        if (getListaTipoProducto() != null) {
            getListaTipoProducto().clear();
        }
        producto = new Producto();
        setTipoProducto(new TipoProducto());
        setMoneda(new Moneda());
        setSelected(new ProductoIfip());
        initializeEmbeddableKey();
        getSelected().setProducto(new Producto());//En porceso de eliminacino
        getSelected().setFechaRegistro(new Date());
        getSelected().setRegistradoPorUsuario(getUsuario());
    }

    public void validateUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (arg2 instanceof TipoProducto) {
            auxTP = (TipoProducto) arg2;
        } else if (arg2 instanceof Moneda) {
            auxMon = (Moneda) arg2;
        }
        if (auxTP != null && auxMon != null && (ejbFacade.getItemsProductoIfip(getIfip().getCodigo(), auxMon.getCodigo(), auxTP.getCodigo()) != null)) {
            throw new ValidatorException(new FacesMessage("Ya existe este registro"));
        }
    }

    public void edita(ActionEvent event) {
//        //System.out.println("getSelected().getProducto().getTipoProducto()" + getSelected().getProducto().getTipoProducto());
//        //System.out.println("getSelected().getProducto().getMoneda()" + getSelected().getProducto().getMoneda());
        if (getSelected() != null) {
            producto = getSelected().getProducto();
            setTipoProducto(getSelected().getProducto().getTipoProducto());
            setMoneda(getSelected().getProducto().getMoneda());
            setIfip(ejbFacadeIfip.find(getSelected().getProductoIfipPK().getCodigoIfip()));
            getSelected().setFechaRegistro(new Date());
            getSelected().setRegistradoPorUsuario(getUsuario());
            initializeEmbeddableKey();
        }
    }

    public void eliminaProductoIfipController(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"productoIfipController", "eliminaProductoIfip", CapturaError.getErrorException(ex)});
            }
        }

    }

    public void actualizarMoneda() {
        if (moneda != null) {
            setListaTipoProducto(ejbFacadeTipoProducto.getItemsTipoProductoMoneda(moneda.getCodigo(), 'N'));// si el PRODUCTO no esta eliminado
        }
    }

    public void editarSiglasLibreta() {
        List<ProductoIfip> listaProductoIfip;

        if (tipoProducto.getCodigo() != null && ActivacionUsuario.getCodigoIfip() != null && moneda.getCodigo() != null) {
            String nombreTipoProducto = tipoProducto.getNombre();
            String nombreIfips = ifip.getNombre();
            String siglasLibreta = null;
            for (int i = 0; i < nombreIfips.length() - 2; i++) {
                String auxSiglasLibreta = nombreIfips.substring(0, i + 2) + nombreTipoProducto.substring(0, 2);
                listaProductoIfip = this.ejbFacade.getItemsProductoIfip(ifip.getCodigo(), moneda.getCodigo(), tipoProducto.getCodigo(), auxSiglasLibreta);
                if (!listaProductoIfip.isEmpty()) {
                    siglasLibreta = auxSiglasLibreta;
                    break;
                }
                listaProductoIfip = this.ejbFacade.getItemsProductoIfip(auxSiglasLibreta);
                if (listaProductoIfip.isEmpty()) {
                    siglasLibreta = auxSiglasLibreta;
                    break;
                }
            }
            this.getSelected().setSiglasLibreta(siglasLibreta);
        }
    }

    public void generarSiglasLibreta() {
        List<ProductoIfip> listaProductoIfip;
        //System.out.println(" Ifip: " + ActivacionUsuario.getCodigoIfip() + " Tipo: " + tipoProducto.getCodigo() + " mone: " + moneda.getCodigo());
        if (tipoProducto.getCodigo() != null && ActivacionUsuario.getCodigoIfip() != null && moneda.getCodigo() != null) {
            String nombreTipoProducto = tipoProducto.getNombre();
            String nombreIfips = getIfip().getNombre();
            String siglasLibreta = null;
            for (int i = 0; i < nombreIfips.length() - 2; i++) {
                String auxSiglasLibreta = nombreIfips.substring(0, i + 2) + nombreTipoProducto.substring(0, 2);
                listaProductoIfip = this.ejbFacade.getItemsProductoIfip(auxSiglasLibreta);
                if (listaProductoIfip.isEmpty()) {
                    siglasLibreta = auxSiglasLibreta;
                    break;
                }
            }
            this.getSelected().setSiglasLibreta(siglasLibreta);
        }
    }

    /**
     * @return the tipoProducto
     */
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
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
     * @return the listaTipoProducto
     */
    public List<TipoProducto> getListaTipoProducto() {
        return listaTipoProducto;
    }

    /**
     * @param listaTipoProducto the listaTipoProducto to set
     */
    public void setListaTipoProducto(List<TipoProducto> listaTipoProducto) {
        this.listaTipoProducto = listaTipoProducto;
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
     * @return the listaTasaInteresProductoIfip
     */
    public List<TasaInteresProductoIfipController> getListaTasaInteresProductoIfip() {
        return listaTasaInteresProductoIfip;
    }

    /**
     * @param listaTasaInteresProductoIfip the listaTasaInteresProductoIfip to set
     */
    public void setListaTasaInteresProductoIfip(List<TasaInteresProductoIfipController> listaTasaInteresProductoIfip) {
        this.listaTasaInteresProductoIfip = listaTasaInteresProductoIfip;
    }

}
