package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.JsfUtil;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.creditos.TasaInteresCreditoIfip;
import ec.renafipse.mks.modelo.creditos.TasaInteresProCreMonIfi;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoMonedaIfipFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresCreditoIfipFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresProCreMonIfiFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.math.BigDecimal;
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
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "tasaInteresProCreMonIfiController")
@ViewScoped
public class TasaInteresProCreMonIfiController extends AbstractController<TasaInteresProCreMonIfi> {

    @EJB
    private TasaInteresProCreMonIfiFacade ejbFacade;

    @EJB
    private ProductoCreditoFacade ejbFacadeProductoCredito;
    @EJB
    private MonedaFacade ejbFacadeMoneda;
    @EJB
    private ProductoCreditoMonedaIfipFacade ejbFacadeProductoCreditoMonedaIfip;
    @EJB
    private TasaInteresCreditoIfipFacade ejbFacadeTasaInteresCreditoIfip;
    @EJB
    private IfipFacade ejbFacadeIfip;
    /**
     * Initialize the concrete TasaInteresProCreMonIfi controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    private ProductoCredito productoCredito, auxPC;
    ;
    private Moneda moneda, auxMon;
    ;
    private Ifip ifip;
    private Usuario usuario;
    private BigDecimal auxMI;
    private TasaInteresCreditoIfip auxCT;
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;
    private List<ProductoCredito> listaProductoCredito;
    private List<Moneda> listaMoneda;
    private List<TasaInteresCreditoIfip> listaTasaInteresCreditoIfip;

    public TasaInteresProCreMonIfiController() {
        super(TasaInteresProCreMonIfi.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setProductoCredito(new ProductoCredito());
        setMoneda(new Moneda());
         setUsuario(ActivacionUsuario.getUsuario());
        setListaProductoCredito(ejbFacadeProductoCredito.getItemsProductoCredito(ifip.getCodigo(), 'N'));//ProductoCreditoMonedaIfip no este eliminado
        setListaTasaInteresCreditoIfip(ejbFacadeTasaInteresCreditoIfip.getItemsTasaInteresCreditoIfip(ifip.getCodigo(), 'N'));// TasaInteresCreditoIfip no este eliminado
    }

    public void nuevo(ActionEvent event) {
        auxMon = null;
        auxCT = null;
        auxMI = null;
        auxPC = null;
        setProductoCredito(new ProductoCredito());
        setMoneda(new Moneda());
        setSelected(new TasaInteresProCreMonIfi());
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        getSelected().setFechaRegistro(new Date());
        getSelected().setCodigo(codigo());
        getSelected().setUsuarioRegistradoPor(getUsuario());
    }

    public void edita(ActionEvent event) {
        if (getSelected() != null && getSelected().getProductoCreditoMonedaIfip() != null) {
            setIfip(getSelected().getProductoCreditoMonedaIfip().getIfip());
            setProductoCredito(getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito());
            setMoneda(getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getMoneda());
            getSelected().setFechaRegistro(new Date());
            getSelected().setUsuarioRegistradoPor(getUsuario());
            setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoCredito(productoCredito.getCodigo(), ifip.getCodigo(), 'N'));
        } else {
            MuestraMensaje.addError("Seleccione un registro primero");
        }
    }

    public void actualizaMoneda() {
        if (productoCredito != null) {
            setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoCredito(productoCredito.getCodigo(), ifip.getCodigo(), 'N'));
        }

        ////System.out.println("Pasoo!! " +getListaMoneda().size()+" Pro "+productoCredito.getCodigo()+" Ifip "+ActivacionUsuario.getCodigoUsuario());
    }

    public void actualizaProductoCreditoMonedaIfip() {
        if (productoCredito != null && getMoneda() != null) {
            productoCreditoMonedaIfip = ejbFacadeProductoCreditoMonedaIfip.getItemsProductoCreditoMonedaIfip(productoCredito.getCodigo(), getMoneda().getCodigo(), ifip.getCodigo());
            this.getSelected().setProductoCreditoMonedaIfip(productoCreditoMonedaIfip);
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
                        new Object[]{"tasaInteresProCreMonIfiController", "eliminaTasaInteresProCreMonIfiController", CapturaError.getErrorException(ex)});
            }
        }

    }

    public void validateUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (arg2 instanceof ProductoCredito) {
            auxPC = (ProductoCredito) arg2;
        } else if (arg2 instanceof Moneda) {
            auxMon = (Moneda) arg2;
        } else if (arg2 instanceof BigDecimal) {
            auxMI = (BigDecimal) arg2;
        } else if (arg2 instanceof TasaInteresCreditoIfip) {
            auxCT = (TasaInteresCreditoIfip) arg2;
        }
        if (auxPC != null && auxMon != null && auxMI != null && auxCT != null && (ejbFacade.getTasaInteresProCreMonIfi(auxPC.getCodigo(), auxMon.getCodigo(), ifip.getCodigo(), auxCT.getCodigo(), auxMI) != null)) {
            //System.out.println(" auxPC " + auxPC + " auxMon " + auxMon + " Ifip " + ifip.getCodigo() + " auxMI " + auxMI + " auxCT " + auxCT);
            //System.out.println("Consulta: " + (ejbFacade.getTasaInteresProCreMonIfi(auxPC.getCodigo(), auxMon.getCodigo(), ifip.getCodigo(), auxCT.getCodigo(), auxMI)).getCodigo());
            throw new ValidatorException(new FacesMessage("Ya existe registro"));
        }
    }

    public Long codigo() {
        long max = 1;
        for (TasaInteresProCreMonIfi tasaInteresProCreMonIfi : ejbFacade.findAll()) {
            if (tasaInteresProCreMonIfi.getCodigo() > max) {
                max = tasaInteresProCreMonIfi.getCodigo() + 1;
            } else if (tasaInteresProCreMonIfi.getCodigo() == max) {
                max++;
            }
        }
        return max;
    }

    public void destruir(ActionEvent event) {
        //System.out.println("Paso aki!!!");
        if (this.getSelected() != null) {
             //System.out.println("LLegoo!!!");
            //this.setEmbeddableKeys();
            try {
                String msg = "";
                this.ejbFacade.remove(this.getSelected());
                 //System.out.println("Satisfacc!!!");
                MuestraMensaje.addSatisfactorioPersistencia(2);
            } catch (EJBException ex) {
                String msg = "";
                //System.out.print("Error en PERSISTENCIA " + ex.toString());
                msg = CapturaError.getErrorPersistencia(ex);
                if (msg == null) {
                    MuestraMensaje.addErrorPersistencia();
                } else {
                    MuestraMensaje.addErrorPersistencia(msg);
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, CapturaError.getErrorException(ex));
                MuestraMensaje.addErrorPersistencia();
            }
        }
               

    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().setCodigoProducto(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoProducto());
        this.getSelected().setCodigoMoneda(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoMoneda());
        this.getSelected().setCodigoIfip(this.getSelected().getProductoCreditoMonedaIfip().getProductoCreditoMonedaIfipPK().getCodigoIfip());
        this.getSelected().setCodigoTasaCode(this.getSelected().getCodigoTasa().getCodigo());
        this.getSelected().setRegistradoPor( getSelected().getUsuarioRegistradoPor().getCodigo());
        getSelected().setFechaRegistro(new Date());
//        if(ejbFacade.getItemsTipoRubroProCreMonIfiOrden(getSelected().getCodigoTipoRubro().getCodio(), productoCredito.getCodigo(), moneda.getCodigo(), ActivacionUsuario.getCodigoIfip(), getSelected().getOrdenCobro())!=null)
//            MuestraMensaje.addError("Ya existe registro con el mismo rubro, producto y moneda");
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
     * @return the listaTasaInteresCreditoIfip
     */
    public List<TasaInteresCreditoIfip> getListaTasaInteresCreditoIfip() {
        return listaTasaInteresCreditoIfip;
    }

    /**
     * @param listaTasaInteresCreditoIfip the listaTasaInteresCreditoIfip to set
     */
    public void setListaTasaInteresCreditoIfip(List<TasaInteresCreditoIfip> listaTasaInteresCreditoIfip) {
        this.listaTasaInteresCreditoIfip = listaTasaInteresCreditoIfip;
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
}
