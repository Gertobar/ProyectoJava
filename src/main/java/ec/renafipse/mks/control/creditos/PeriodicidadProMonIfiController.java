package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.control.seguridades.UsuarioController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfi;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.creditos.TasaInteresCreditoIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.comunes.PeriodicidadFacade;
import ec.renafipse.mks.negocio.creditos.PeriodicidadProMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaIfipFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresCreditoIfipFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "periodicidadProMonIfiController")
@ViewScoped
public class PeriodicidadProMonIfiController extends AbstractController<PeriodicidadProMonIfi> {

    @EJB
    private PeriodicidadProMonIfiFacade ejbFacade;
    //private ProductoCreditoMonedaIfipController productoCreditoMonedaIfipController;
    //private UsuarioController usuarioRegistradoPorController;
    //PERSONALIZADAS
    @EJB
    private ProductoCreditoFacade ejbFacadeProductoCredito;
    @EJB
    private MonedaFacade ejbFacadeMoneda;
    @EJB
    private ProductoCreditoMonedaIfipFacade ejbFacadeProductoCreditoMonedaIfip;
    @EJB
    private PeriodicidadFacade ejbFacadePeriodicidad;
    @EJB
    private IfipFacade ejbFacadeIfip;
    
    private ProductoCredito productoCredito;
    private Moneda moneda;
    private Ifip ifip;
    private Usuario usuario;
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;
    private List<ProductoCredito> listaProductoCredito;
    private List<Moneda> listaMoneda;
    private List<Periodicidad> listaPeriodicidad;

    /**
     * Initialize the concrete PeriodicidadProMonIfi controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
        //productoCreditoMonedaIfipController = context.getApplication().evaluateExpressionGet(context, "#{productoCreditoMonedaIfipController}", ProductoCreditoMonedaIfipController.class);
        //usuarioRegistradoPorController = context.getApplication().evaluateExpressionGet(context, "#{usuarioController}", UsuarioController.class);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setUsuario(ActivacionUsuario.getUsuario());
        setProductoCredito(new ProductoCredito());
        setMoneda(new Moneda());
        setListaProductoCredito(ejbFacadeProductoCredito.getItemsProductoCredito(ifip.getCodigo()));
        setListaPeriodicidad(ejbFacadePeriodicidad.getItemsPeriodicidadEliminado('N'));
    }

    public PeriodicidadProMonIfiController() {
        // Inform the Abstract parent controller of the concrete PeriodicidadProMonIfi?cap_first Entity
        super(PeriodicidadProMonIfi.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPeriodicidadProMonIfiPK().setCodigoProducto(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoProducto());
        this.getSelected().getPeriodicidadProMonIfiPK().setCodigoMoneda(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoMoneda());
        this.getSelected().getPeriodicidadProMonIfiPK().setCodigoIfip(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoIfip());
        this.getSelected().getPeriodicidadProMonIfiPK().setCodigoPeriodicidad(this.getSelected().getPeriodicidad().getCodigo());
        this.getSelected().setRegistradoPor( getSelected().getUsuarioRegistradoPor().getCodigo());
        getSelected().setFechaRegistro(new Date());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPeriodicidadProMonIfiPK(new ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfiPK());
    }
    public void nuevo(ActionEvent event) {
        setProductoCredito(new ProductoCredito());
        setMoneda(new Moneda());
        setSelected(new PeriodicidadProMonIfi());
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        getSelected().setFechaRegistro(new Date());
        initializeEmbeddableKey();
        this.getSelected().setUsuarioRegistradoPor(getUsuario());
    }
    public void edita(ActionEvent event) {
        if (getSelected() != null && getSelected().getProductoCreditoMonedaIfip() != null) {
            setIfip(getSelected().getProductoCreditoMonedaIfip().getIfip());
            setProductoCredito(getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito());
            setMoneda(getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda());
            this.getSelected().setUsuarioRegistradoPor(getUsuario());
            this.setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoCredito(productoCredito.getCodigo(), ActivacionUsuario.getCodigoIfip()));
        } else {
            MuestraMensaje.addError("Seleccione un registro primero");
        }
    }
    public void actualizaMoneda() {
        this.setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoCredito(productoCredito.getCodigo(), ActivacionUsuario.getCodigoIfip()));
        ////System.out.println("Pasoo!! " +getListaMoneda().size()+" Pro "+productoCredito.getCodigo()+" Ifip "+ActivacionUsuario.getCodigoUsuario());
    }
    public void actualizaProductoCreditoMonedaIfip() {
        productoCreditoMonedaIfip = ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditoMonedaIfip(productoCredito.getCodigo(), moneda.getCodigo(), ActivacionUsuario.getCodigoIfip());
        this.getSelected().setProductoCreditoMonedaIfip(productoCreditoMonedaIfip);
    }
    public void validateUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (productoCredito != null && moneda != null && getSelected().getPeriodicidad()!=null && (ejbFacade.getPeriodicidadProMonIfi(productoCredito.getCodigo(), moneda.getCodigo(), ifip.getCodigo()) != null)) {
            throw new ValidatorException(new FacesMessage("Ya existe este registro"));
        }
    }
    public void eliminaConceptoTransaccion(ActionEvent event) {
        if (this.getSelected() != null && getSelected().getProductoCreditoMonedaIfip() != null) {
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
                        new Object[]{"periodicidadProMonIfiController", "eliminaPeriodicidadProMonIfiController", CapturaError.getErrorException(ex)});
            }
        }

    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "selected" attribute of the ProductoCreditoMonedaIfip controller
     * in order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductoCreditoMonedaIfip(ActionEvent event) {
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from PeriodicidadProMonIfi?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", this.getSelected().getSolicitudCollection());
        }
        return "/solicitud/index";
    }

    /**
     * @return the productoCredito
     */
    public ProductoCredito getProductoCredito() {
        return productoCredito;
    }

    /**
     * @param productoCredito the productoCredito to set
     */
    public void setProductoCredito(ProductoCredito productoCredito) {
        this.productoCredito = productoCredito;
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
     * @return the listaProductoCredito
     */
    public List<ProductoCredito> getListaProductoCredito() {
        return listaProductoCredito;
    }

    /**
     * @param listaProductoCredito the listaProductoCredito to set
     */
    public void setListaProductoCredito(List<ProductoCredito> listaProductoCredito) {
        this.listaProductoCredito = listaProductoCredito;
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
     * @return the listaPeriodicidad
     */
    public List<Periodicidad> getListaPeriodicidad() {
        return listaPeriodicidad;
    }

    /**
     * @param listaPeriodicidad the listaPeriodicidad to set
     */
    public void setListaPeriodicidad(List<Periodicidad> listaPeriodicidad) {
        this.listaPeriodicidad = listaPeriodicidad;
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
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
//    public void prepareUsuarioRegistradoPor(ActionEvent event) {
//        if (this.getSelected() != null && usuarioRegistradoPorController.getSelected() == null) {
//            usuarioRegistradoPorController.setSelected(this.getSelected().getUsuarioRegistradoPor());
//        }
//    }
}
