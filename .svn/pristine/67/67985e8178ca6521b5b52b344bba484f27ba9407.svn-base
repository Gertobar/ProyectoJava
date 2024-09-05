package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionProPK;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.ProductoIfipPK;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.creditos.RubroConceptoTransaccion;
import ec.renafipse.mks.modelo.creditos.TipoRubro;
import ec.renafipse.mks.modelo.creditos.TipoRubroProCreMonIfi;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionProFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaIfipFacade;
import ec.renafipse.mks.negocio.creditos.RubroConceptoTransaccionFacade;
import ec.renafipse.mks.negocio.creditos.TipoRubroFacade;
import ec.renafipse.mks.negocio.creditos.TipoRubroProCreMonIfiFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
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
import org.primefaces.context.RequestContext;

@ManagedBean(name = "tipoRubroProCreMonIfiController")
@ViewScoped
public class TipoRubroProCreMonIfiController extends AbstractController<TipoRubroProCreMonIfi> {

    @EJB
    private TipoRubroProCreMonIfiFacade ejbFacade;

    @EJB
    private ProductoCreditoFacade ejbFacadeProductoCredito;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private ProductoCreditoMonedaIfipFacade ejbFacadeProductoCreditoMonedaIfip;

    @EJB
    private TipoRubroFacade ejbFacadeTipoRubro;

    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private ProductoIfipFacade ejbFacadeProductoIfip;

    @EJB
    private ConceptoTransaccionProFacade ejbFacadeConceptoTransaccionPro;

    @EJB
    private RubroConceptoTransaccionFacade ejbFacadeRubroConceptoTransaccion;
    /**
     * Initialize the concrete TipoRubroProCreMonIfi controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    private ProductoCredito productoCredito, auxPC;
    private Moneda moneda, auxMon;
    private Ifip ifip;
    private Usuario usuario;
    private TipoRubro auxTR;
    private Short auxOC;
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;
    private ProductoIfip productoIfip;
    private ConceptoTransaccionPro conceptoTransaccionPro;
    private RubroConceptoTransaccion rubroConceptoTransaccion;

    private boolean deshabilitaTransaccion;
    private boolean esEdicion;
    private RequestContext context;

    private List<TipoRubro> listaTipoRubro;
    private List<ProductoCredito> listaProductoCredito;
    private List<Moneda> listaMoneda;
    private List<TipoRubroProCreMonIfi> itemsTipoRubroProCreMonIfi;
    private List<ProductoIfip> itemsProductoIfip;
    private List<ConceptoTransaccionPro> itemsConceptoTransaccionPro;

    public TipoRubroProCreMonIfiController() {
        super(TipoRubroProCreMonIfi.class);

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListaTipoRubro(ejbFacadeTipoRubro.getItemsTipoRubro('N'));
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setProductoCredito(new ProductoCredito());
        setMoneda(new Moneda());
        setUsuario(ActivacionUsuario.getUsuario());
        setListaProductoCredito(ejbFacadeProductoCredito.getItemsProductoCredito(getIfip().getCodigo(), 'N'));
        this.buscar();

    }

    public void nuevo(ActionEvent event) {
        auxPC = null;
        auxMon = null;
        auxTR = null;
        auxOC = null;
        setProductoCredito(new ProductoCredito());
        setMoneda(new Moneda());
        setSelected(new TipoRubroProCreMonIfi());
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        getSelected().setFechaRegistro(new Date());
        getSelected().setCodigoTipoRubro(null);
        getSelected().setCodigoTipoRubro(new TipoRubro());
        this.conceptoTransaccionPro = null;
        this.productoIfip = null;
        this.getSelected().setCodigoTipoRubro(null);
        this.getSelected().setProductoCreditoMonedaIfip(null);
        this.moneda = null;
        this.productoCredito = null;
        this.productoCreditoMonedaIfip = null;
        getSelected().setUsuarioRegistradoPor(getUsuario());
        usuario = this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario());
        this.deshabilitaTransaccion = true;
        this.setEsEdicion(false);

    }

    public void edita(ActionEvent event) {
        if (getSelected() != null && getSelected().getProductoCreditoMonedaIfip() != null) {
            setIfip(getSelected().getProductoCreditoMonedaIfip().getIfip());
            setProductoCredito(getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito());
            setMoneda(getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda());
            this.setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoCredito(productoCredito.getCodigo(), ActivacionUsuario.getCodigoIfip()));
            usuario = this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario());
            this.deshabilitaTransaccion = true;
            if (this.getSelected().getCobradoEn() != 'D') {
                this.deshabilitaTransaccion = false;
                this.actualizaProductoCreditoMonedaIfip();
                this.rubroConceptoTransaccion = ejbFacadeRubroConceptoTransaccion.find(this.getSelected().getCodigo());
                if (rubroConceptoTransaccion != null) {
                    this.productoIfip = this.ejbFacadeProductoIfip.find(new ProductoIfipPK(this.rubroConceptoTransaccion.getCodigoTipoProducto(), this.rubroConceptoTransaccion.getCodigoMoneda(), this.getIfip().getCodigo()));
                    this.cambiaProductoAhorro();
                    this.conceptoTransaccionPro = this.ejbFacadeConceptoTransaccionPro.find(new ConceptoTransaccionProPK(this.rubroConceptoTransaccion.getCodigoTipoProducto(), this.rubroConceptoTransaccion.getCodigoMoneda(), this.rubroConceptoTransaccion.getCodigoConcepto()));
                }
            }
            this.setEsEdicion(true);

        } else {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionRegistro"));
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
                        new Object[]{"tipoRubroProCreMonIfiController", "eliminaTipoRubroProCreMonIfiController", CapturaError.getErrorException(ex)});
            }
        }

    }

    public void validateUnicoRubProMonOrden(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (this.isEsEdicion()) {
            return;
        }

        if (arg2 instanceof ProductoCredito) {
            auxPC = (ProductoCredito) arg2;
        } else if (arg2 instanceof Moneda) {
            auxMon = (Moneda) arg2;
        } else if (arg2 instanceof TipoRubro) {
            auxTR = (TipoRubro) arg2;
        } else if (arg2 instanceof Short) {
            auxOC = (Short) arg2;
        }
        if (auxPC != null && auxMon != null && auxTR != null && (ejbFacade.getTipoRubroProCreMonIfi(auxTR.getCodio(), auxPC.getCodigo(), auxMon.getCodigo(), ifip.getCodigo())) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo"), ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExisteRubroProductoMoneda")));
        }
        if (auxPC != null && auxMon != null && auxTR != null && auxOC != null && (ejbFacade.getItemsTipoRubroProCreMonIfiOrden(auxTR.getCodio(), auxPC.getCodigo(), auxMon.getCodigo(), ifip.getCodigo(), auxOC.shortValue())) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorInfo"), ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ExisteRubroProductoMoneda")));
        }

    }

    public Long codigo() {
        long max = 1;
        for (TipoRubroProCreMonIfi tipoRubroProCreMonIfi : ejbFacade.findAll()) {
            if (tipoRubroProCreMonIfi.getCodigo() > max) {
                max = tipoRubroProCreMonIfi.getCodigo() + 1;
            } else if (tipoRubroProCreMonIfi.getCodigo() == max) {
                max++;
            }
        }
        return max;
    }

    /**
     * Crea los rubros
     *
     * @param actionEvent
     */
    public void guardar(ActionEvent actionEvent) {
        try {
            boolean existeRubro = true;
            context = RequestContext.getCurrentInstance();
            if (validaTransaccion()) {
                if (!this.isEsEdicion()) {
                    //this.getSelected().setProductoCreditoMonedaIfip(productoCreditoMonedaIfip);
                    this.getSelected().setCodigoProducto(productoCreditoMonedaIfip.getProductoCreditoMonedaIfipPK().getCodigoProducto());
                    this.getSelected().setCodigoIfip(productoCreditoMonedaIfip.getProductoCreditoMonedaIfipPK().getCodigoIfip());
                    this.getSelected().setCodigoMoneda(productoCreditoMonedaIfip.getProductoCreditoMonedaIfipPK().getCodigoMoneda());
                    this.getSelected().setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                    // Guardando el rubro
                    this.ejbFacade.edit(this.getSelected());
                } else {
                    // Guardando el rubro
                    this.ejbFacade.edit(this.getSelected());
                }

                System.out.println("this.getSelected() " + this.getSelected().getCodigo());

                List<TipoRubroProCreMonIfi> listaTipoRubroProCreMonIfis = this.ejbFacade.getItemsTipoRubroProCreMonIfi(this.getSelected().getCodigoTipoRubro().getCodio(),
                        productoCreditoMonedaIfip.getProductoCreditoMonedaIfipPK().getCodigoProducto(),
                        productoCreditoMonedaIfip.getProductoCreditoMonedaIfipPK().getCodigoMoneda(),
                        productoCreditoMonedaIfip.getProductoCreditoMonedaIfipPK().getCodigoIfip());
                if (listaTipoRubroProCreMonIfis == null) {
                    return;
                }

                TipoRubroProCreMonIfi trpcmi = listaTipoRubroProCreMonIfis.get(0);
                this.rubroConceptoTransaccion = this.ejbFacadeRubroConceptoTransaccion.find(trpcmi.getCodigo());
                if (rubroConceptoTransaccion == null) {
                    existeRubro = false;
                    this.rubroConceptoTransaccion = new RubroConceptoTransaccion();
                }

                // Guardando la transaccion del rubro
                if (this.getSelected().getCobradoEn() != 'D') {

                    this.rubroConceptoTransaccion.setCodigoRubro(trpcmi.getCodigo());
                    this.rubroConceptoTransaccion.setCodigoConcepto(conceptoTransaccionPro.getConceptoTransaccionProPK().getCodigoConcepto());
                    this.rubroConceptoTransaccion.setCodigoMoneda(conceptoTransaccionPro.getConceptoTransaccionProPK().getCodigoMoneda());
                    this.rubroConceptoTransaccion.setCodigoTipoProducto(conceptoTransaccionPro.getConceptoTransaccionProPK().getCodigoTipoProducto());
                    this.rubroConceptoTransaccion.setFechaRegistro(new Date());
                    this.rubroConceptoTransaccion.setEliminado('N');
                    this.rubroConceptoTransaccion.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                    
                    if (this.getSelected().getEliminado() == 'S') {
                        this.rubroConceptoTransaccion.setEliminado('S');
                    } else {
                        this.rubroConceptoTransaccion.setEliminado('N');
                    }

                    /*if (existeRubro) {
                     this.ejbFacadeRubroConceptoTransaccion.edit(rubroConceptoTransaccion);
                     } else {
                     this.ejbFacadeRubroConceptoTransaccion.create(rubroConceptoTransaccion);
                     }*/
                    this.ejbFacadeRubroConceptoTransaccion.edit(rubroConceptoTransaccion);

                    this.setSelected(null);

                } else {
                    if (rubroConceptoTransaccion != null) {
                        this.rubroConceptoTransaccion.setEliminado('S');
                        this.ejbFacadeRubroConceptoTransaccion.edit(rubroConceptoTransaccion);

                    }
                }

                MuestraMensaje.addSatisfactorioPersistencia(1);
                this.buscar();
                context.execute("ProductoIfitCreateDialog.hide()");
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            ex.printStackTrace();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"tipoRubroProCreMonIfiController", "crear", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Busca los ubros
     */
    public void buscar() {
        this.itemsTipoRubroProCreMonIfi = this.ejbFacade.findAll();
    }

    /**
     * Valida si la Transaccion fue ingresada cuando el tipo de rubro es cobrado
     * al conceder
     *
     * @return
     */
    private boolean validaTransaccion() {
        if (this.getSelected().getCobradoEn() != 'D' && this.productoIfip == null && this.conceptoTransaccionPro == null) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseTransaccionDeDebitoDelRubro"));
            return false;
        }
        return true;
    }

    @Override
    protected void setEmbeddableKeys() {
        /* this.getSelected().setCodigoProducto(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoProducto());
         this.getSelected().setCodigoMoneda(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoMoneda());
         this.getSelected().setCodigoIfip(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoIfip());*/

        //this.getSelected().setProductoCreditoMonedaIfip(productoCreditoMonedaIfip);
        this.getSelected().setRegistradoPor(getSelected().getUsuarioRegistradoPor().getCodigo());
        getSelected().setFechaRegistro(new Date());
        //this.getSelected().setCodigoTipoRubroCode(this.getSelected().getCodigoTipoRubro().getCodio());
    }

    //-------------------------------------------------------------------------
    // -- METODOS DE CAMBIO DE COMBOS
    public void cambiaProductoAhorro() {
        this.setItemsConceptoTransaccionPro(this.ejbFacadeConceptoTransaccionPro.getItemsPermisosExistentesConceptoTransaccionProIfi(this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(), this.productoIfip.getProductoIfipPK().getCodigoMoneda(), 4L, 'N'));
        this.conceptoTransaccionPro = null;
    }

    public void cambiaCobradoEn() {
        if (this.getSelected().getCobradoEn() != 'D') {
            this.itemsProductoIfip = this.ejbFacadeProductoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.moneda.getCodigo(), 'N');
            this.deshabilitaTransaccion = false;
            this.productoIfip = null;
            this.conceptoTransaccionPro = null;
            this.getSelected().setCalculaSobre('M');
        } else {
            this.deshabilitaTransaccion = true;
            this.productoIfip = null;
            this.conceptoTransaccionPro = null;
            this.getSelected().setCalculaSobre('M');
        }
    }

    public void actualizaMoneda() {
        this.setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoCredito(productoCredito.getCodigo(), getIfip().getCodigo()));
    }

    public void actualizaProductoCreditoMonedaIfip() {
        productoCreditoMonedaIfip = ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditoMonedaIfip(productoCredito.getCodigo(), moneda.getCodigo(), getIfip().getCodigo());
        //this.getSelected().setProductoCreditoMonedaIfip(productoCreditoMonedaIfip);
        this.itemsProductoIfip = this.ejbFacadeProductoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.moneda.getCodigo(), 'N');
        this.productoIfip = null;
    }

    /**
     * @return the listaTipoRubro
     */
    public List<TipoRubro> getListaTipoRubro() {
        return listaTipoRubro;
    }

    /**
     * @param listaTipoRubro the listaTipoRubro to set
     */
    public void setListaTipoRubro(List<TipoRubro> listaTipoRubro) {
        this.listaTipoRubro = listaTipoRubro;
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
     * @return the itemsTipoRubroProCreMonIfi
     */
    public List<TipoRubroProCreMonIfi> getItemsTipoRubroProCreMonIfi() {
        return itemsTipoRubroProCreMonIfi;
    }

    /**
     * @param itemsTipoRubroProCreMonIfi the itemsTipoRubroProCreMonIfi to set
     */
    public void setItemsTipoRubroProCreMonIfi(List<TipoRubroProCreMonIfi> itemsTipoRubroProCreMonIfi) {
        this.itemsTipoRubroProCreMonIfi = itemsTipoRubroProCreMonIfi;
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
     * @return the conceptoTransaccionPro
     */
    public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    /**
     * @param conceptoTransaccionPro the conceptoTransaccionPro to set
     */
    public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
    }

    /**
     * @return the itemsProductoIfip
     */
    public List<ProductoIfip> getItemsProductoIfip() {
        return itemsProductoIfip;
    }

    /**
     * @param itemsProductoIfip the itemsProductoIfip to set
     */
    public void setItemsProductoIfip(List<ProductoIfip> itemsProductoIfip) {
        this.itemsProductoIfip = itemsProductoIfip;
    }

    /**
     * @return the itemsConceptoTransaccionPro
     */
    public List<ConceptoTransaccionPro> getItemsConceptoTransaccionPro() {
        return itemsConceptoTransaccionPro;
    }

    /**
     * @param itemsConceptoTransaccionPro the itemsConceptoTransaccionPro to set
     */
    public void setItemsConceptoTransaccionPro(List<ConceptoTransaccionPro> itemsConceptoTransaccionPro) {
        this.itemsConceptoTransaccionPro = itemsConceptoTransaccionPro;
    }

    /**
     * @return the deshabilitaTransaccion
     */
    public boolean isDeshabilitaTransaccion() {
        return deshabilitaTransaccion;
    }

    /**
     * @param deshabilitaTransaccion the deshabilitaTransaccion to set
     */
    public void setDeshabilitaTransaccion(boolean deshabilitaTransaccion) {
        this.deshabilitaTransaccion = deshabilitaTransaccion;
    }

    /**
     * @return the esEdicion
     */
    public boolean isEsEdicion() {
        return esEdicion;
    }

    /**
     * @param esEdicion the esEdicion to set
     */
    public void setEsEdicion(boolean esEdicion) {
        this.esEdicion = esEdicion;
    }

}
