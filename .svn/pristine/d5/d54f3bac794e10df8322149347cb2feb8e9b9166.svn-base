package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionProPK;
import ec.renafipse.mks.modelo.ahorros.Producto;
import ec.renafipse.mks.modelo.ahorros.TipoProducto;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionProFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoFacade;
import ec.renafipse.mks.negocio.ahorros.TipoProductoFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
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

@ManagedBean(name = "conceptoTransaccionProController")
@ViewScoped
public class ConceptoTransaccionProController extends AbstractController<ConceptoTransaccionPro> implements Serializable {

    @EJB
    private ConceptoTransaccionProFacade ejbFacade;
    @EJB
    private TipoProductoFacade ejbFacadeTipoProducto;
    @EJB
    private MonedaFacade ejbFacadeMoneda;
    @EJB
    private ProductoFacade ejbFacadeProducto;
    @EJB
    private IfipFacade ejbFacadeIfip;
    
    private TipoProducto tipoProducto,auxTP;
    private Moneda moneda,auxMon;
    private Usuario usuario;
    private Ifip ifip;
    private ConceptoTransaccion auxCT;
    private List<TipoProducto> listaTipoProducto;
    private List<Moneda> listaMoneda;

    public ConceptoTransaccionProController() {
        super(ConceptoTransaccionPro.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setTipoProducto(new TipoProducto());
        setMoneda(new Moneda());
        setUsuario(ActivacionUsuario.getUsuario());
        setListaTipoProducto(ejbFacadeTipoProducto.getItemsTipoProductoMonedaIfip(getIfip().getCodigo(),'N'));// tipo producto de producto registrado en la ifip
        
    }

    @Override
    protected void setEmbeddableKeys() {

        this.getSelected().getConceptoTransaccionProPK().setCodigoConcepto(this.getSelected().getConceptoTransaccion().getCodigo());
        this.getSelected().getConceptoTransaccionProPK().setCodigoTipoProducto(this.getSelected().getProducto().getProductoPK().getCodigoTipoProducto());
        this.getSelected().getConceptoTransaccionProPK().setCodigoMoneda(this.getSelected().getProducto().getProductoPK().getCodigoMoneda());
        this.getSelected().setRegistradoPor(getSelected().getRegistradoPorUsuario().getCodigo());
        getSelected().setFechaRegistro(new Date());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setConceptoTransaccionProPK(new ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionProPK());
    }

    public void cargarMonedas() {
        setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoIfip(tipoProducto.getCodigo(), getIfip().getCodigo()));
    }

    public void actualizarMoneda() {
        this.getSelected().setProducto(ejbFacadeProducto.getProducto(tipoProducto.getCodigo(),moneda.getCodigo()));
    }

    public void nuevo(ActionEvent event) {
        auxMon = null;
        auxCT = null;
        auxCT = null;
        setTipoProducto(new TipoProducto());
        setMoneda(new Moneda());
        setSelected(new ConceptoTransaccionPro());
        initializeEmbeddableKey();
        getSelected().setFechaRegistro(new Date());
        getSelected().setRegistradoPorUsuario(getUsuario());
    }

    public void editable(ActionEvent event) {
        setTipoProducto(getSelected().getProducto().getTipoProducto());
        setMoneda(getSelected().getProducto().getMoneda());
         setListaMoneda(ejbFacadeMoneda.getItemsMonedasProductoIfip(tipoProducto.getCodigo(), getIfip().getCodigo()));
        initializeEmbeddableKey();
        getSelected().setFechaRegistro(new Date());
        getSelected().setRegistradoPorUsuario(getUsuario());
    }
public void validateUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        if (arg2 instanceof TipoProducto) {
            auxTP = (TipoProducto) arg2;
        } else if (arg2 instanceof Moneda) {
            auxMon = (Moneda) arg2;
        } else if (arg2 instanceof ConceptoTransaccion) {
            auxCT = (ConceptoTransaccion) arg2;
        } 
        if (auxTP != null && auxMon != null && auxCT != null && (ejbFacade.getIConceptoTransaccionPro(auxTP.getCodigo(), auxMon.getCodigo(), auxCT.getCodigo())!= null)) {
             throw new ValidatorException(new FacesMessage("Ya existe registro"));
        }
    }
    public void eliminaConceptoTransaccionPro(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.getSelected().setEliminado('S');
                this.ejbFacade.edit(this.getSelected());
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RegistroEliminado");
                MuestraMensaje.addSatisfactorio(msg);
                // this.listaConceptoTransaccionPro = this.ejbFacade.findAll();
            } catch (Exception ex) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"conceptoTransaccionProController", "eliminaConceptoTransaccionPro", CapturaError.getErrorException(ex)});
            }
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
}
