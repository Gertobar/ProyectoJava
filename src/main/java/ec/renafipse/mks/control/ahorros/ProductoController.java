package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Producto;
import ec.renafipse.mks.modelo.ahorros.TipoProducto;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.ProductoFacade;
import ec.renafipse.mks.negocio.ahorros.TipoProductoFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import java.io.Serializable;
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

@ManagedBean(name = "productoController")
@ViewScoped
public class ProductoController extends AbstractController<Producto> implements Serializable {

    @EJB
    private ProductoFacade ejbFacade;
    @EJB
    private MonedaFacade ejbFacadeMoneda;
    @EJB
    private TipoProductoFacade ejbFacadeTipoProducto;
    private TipoProducto tipoProducto;
    private Moneda moneda;
    private Usuario usuario;
    private List<Moneda> listaMoneda;
    private List<TipoProducto> listaTipoProducto;
    public ProductoController() {
        super(Producto.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListaMoneda(ejbFacadeMoneda.getItemsMonedas('N'));
        setListaTipoProducto(ejbFacadeTipoProducto.getItemsTipoProducto('N'));
        setUsuario(ActivacionUsuario.getUsuario());
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProductoPK().setCodigoTipoProducto(this.getSelected().getTipoProducto().getCodigo());
        this.getSelected().getProductoPK().setCodigoMoneda(this.getSelected().getMoneda().getCodigo());
        this.getSelected().setRegistradoPor( getSelected().getRegistradoPorUsuario().getCodigo());
        getSelected().setFechaRegistro(new Date());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setProductoPK(new ec.renafipse.mks.modelo.ahorros.ProductoPK());
    }
    public void nuevo(ActionEvent event) {
        setListaTipoProducto(ejbFacadeTipoProducto.getItemsTipoProducto('N'));
        setSelected(new Producto());
        getSelected().setFechaRegistro(new Date());
        getSelected().setRegistradoPorUsuario(getUsuario());
        initializeEmbeddableKey();
    }
    public void edita(ActionEvent event) {
        tipoProducto=null;
        moneda=null;
        if (getSelected() != null) {
            getSelected().setRegistradoPorUsuario(getUsuario());
            getSelected().setFechaRegistro(new Date());            
        } else {
            MuestraMensaje.addError("Seleccione un registro primero");
        }
    }
    public void eliminaProductoController(ActionEvent event) {
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
                        new Object[]{"productoController", "eliminaProducto", CapturaError.getErrorException(ex)});
            }
        }

    }
public void validateUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (arg2 instanceof TipoProducto) {
            tipoProducto = (TipoProducto) arg2;
        } else if (arg2 instanceof Moneda) {
            moneda = (Moneda) arg2;
        }
        if (tipoProducto != null && moneda != null && (ejbFacade.getProductoUnico(tipoProducto.getCodigo(), moneda.getCodigo()) != null)) {
            throw new ValidatorException(new FacesMessage("Ya existe este registro"));
        }
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

}
