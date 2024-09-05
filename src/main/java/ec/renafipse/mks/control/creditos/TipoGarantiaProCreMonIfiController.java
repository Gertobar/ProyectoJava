package ec.renafipse.mks.control.creditos;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.TasaInteresCreditoIfip;
import ec.renafipse.mks.modelo.creditos.TasaInteresProCreMonIfi;
import ec.renafipse.mks.modelo.creditos.TipoGarantia;
import ec.renafipse.mks.modelo.creditos.TipoGarantiaProCreMonIfi;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.creditos.ProductoCreditoFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresCreditoIfipFacade;
import ec.renafipse.mks.negocio.creditos.TasaInteresProCreMonIfiFacade;
import ec.renafipse.mks.negocio.creditos.TipoGarantiaFacade;
import ec.renafipse.mks.negocio.creditos.TipoGarantiaProCreMonIfiFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.ArrayList;
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

@ManagedBean(name = "tipoGarantiaProCreMonIfiController")
@ViewScoped
public class TipoGarantiaProCreMonIfiController extends AbstractController<TipoGarantiaProCreMonIfi> {

    @EJB
    private TipoGarantiaProCreMonIfiFacade ejbFacade;
    @EJB
    private TipoGarantiaFacade ejbFacadeTipoGarantia;
    @EJB
    private TasaInteresProCreMonIfiFacade ejbFacadeTasaInteresProCreMonIfi;
    @EJB
    private ProductoCreditoFacade ejbFacadeProductoCredito;
    @EJB
    private MonedaFacade ejbFacadeMoneda;
    @EJB
    private IfipFacade ejbFacadeIfip;
    @EJB
    private TasaInteresCreditoIfipFacade ejbFacadeTasaInteresCreditoIfip;

    /**
     * Initialize the concrete TipoGarantiaProCreMonIfi controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    private TasaInteresProCreMonIfi tasaInteresProCreMonIfi, auxTasaInteresProCreMonIfi;
    private TipoGarantia tipoGarantia;
    private List<TipoGarantia> listaTipoGarantia;
    private List<TasaInteresProCreMonIfi> listaTasaInteresProCreMonIfi;
    private Usuario usuario;
    private ProductoCredito productoCredito;
    private Moneda moneda;
    private Ifip ifip;
    private List<ProductoCredito> listaProductoCredito;
    private List<Moneda> listaMoneda;
    private List<TasaInteresCreditoIfip> listTasaInteresCreditoIfip;
    private TasaInteresCreditoIfip tasaInteresCreditoIfip;

    public TipoGarantiaProCreMonIfiController() {
        super(TipoGarantiaProCreMonIfi.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
        setListaTipoGarantia(ejbFacadeTipoGarantia.getItemsTipoGarantia('N'));
        setListaTasaInteresProCreMonIfi(ejbFacadeTasaInteresProCreMonIfi.getItemsTasaInteresProCreMonIfi(getIfip().getCodigo(), 'N'));//posible eliminado
        setUsuario(ActivacionUsuario.getUsuario());
        setListaProductoCredito(ejbFacadeProductoCredito.getItemsTasaProductoCredito(getIfip().getCodigo(), 'N'));
    }

    @Override
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
        // getSelected().setCodigoTasaCode(getSelected().getCodigoTasa().getCodigo());
        getSelected().setCodigoTipoGarantia(tipoGarantia);
        getSelected().setCodigoTipoGarantiaCode(tipoGarantia.getCodigo());
        getSelected().setRegistradoPor(getSelected().getUsuarioRegistradoPor().getCodigo());
        getSelected().setCodigoTasa(tasaInteresProCreMonIfi);
        getSelected().setCodigoTasaCode(tasaInteresProCreMonIfi.getCodigo());
    }

    public void actualizaMoneda() {
        this.setListaMoneda(ejbFacadeMoneda.getItemsTasaMonedas(productoCredito.getCodigo(), getIfip().getCodigo(), 'N'));
        this.setListTasaInteresCreditoIfip(new ArrayList<TasaInteresCreditoIfip>());
        this.setMoneda(new Moneda());
        this.setTasaInteresCreditoIfip(new TasaInteresCreditoIfip());
        this.setListaTasaInteresProCreMonIfi(new ArrayList<TasaInteresProCreMonIfi>());
        auxTasaInteresProCreMonIfi = null;
        setTasaInteresProCreMonIfi(new TasaInteresProCreMonIfi());
        ////System.out.println("Pasoo!! " +getListaMoneda().size()+" Pro "+productoCredito.getCodigo()+" Ifip "+ActivacionUsuario.getCodigoUsuario());
    }

    public void actualizaTasaInteres() {

        this.setListTasaInteresCreditoIfip(ejbFacadeTasaInteresCreditoIfip.getItemsTasaInteres(productoCredito.getCodigo(), moneda.getCodigo(), getIfip().getCodigo(), 'N'));
        this.setTasaInteresCreditoIfip(new TasaInteresCreditoIfip());
        this.setListaTasaInteresProCreMonIfi(new ArrayList<TasaInteresProCreMonIfi>());
        auxTasaInteresProCreMonIfi = null;
        setTasaInteresProCreMonIfi(new TasaInteresProCreMonIfi());
        ////System.out.println("Pasoo!! " +getListaMoneda().size()+" Pro "+productoCredito.getCodigo()+" Ifip "+ActivacionUsuario.getCodigoUsuario());
    }

    public void actualizaMontoInicial() {
        setListaTasaInteresProCreMonIfi(ejbFacadeTasaInteresProCreMonIfi.getTasaInteresProCreMonIfi(productoCredito.getCodigo(), moneda.getCodigo(), getIfip().getCodigo(), tasaInteresCreditoIfip.getCodigo(), 'N'));
        auxTasaInteresProCreMonIfi = null;
        setTasaInteresProCreMonIfi(new TasaInteresProCreMonIfi());
        for (TasaInteresProCreMonIfi tasaInteresProCreMonIfi : getListaTasaInteresProCreMonIfi()) {
            //System.out.println("Monto: " + tasaInteresProCreMonIfi.getMontoInicial());
        }
    }

    public void actualizaTasaInteresProCreMonIfi() {
        auxTasaInteresProCreMonIfi = getTasaInteresProCreMonIfi();//setTasaInteresProCreMonIfi(ejbFacadeTasaInteresProCreMonIfi.getTasaInteresProCreMonIfiMonto(productoCredito.getCodigo(), moneda.getCodigo(), getIfip().getCodigo(), tasaInteresCreditoIfip.getCodigo(), 'N', getMontoInicial()));
    }

    public void nuevo(ActionEvent event) {
        auxTasaInteresProCreMonIfi = null;
        setSelected(new TipoGarantiaProCreMonIfi());
        getSelected().setFechaRegistro(new Date());
        setTipoGarantia(new TipoGarantia());
        
        this.setProductoCredito(new ProductoCredito());

        this.setMoneda(new Moneda());
        this.setListaMoneda(new ArrayList<Moneda>());

        this.setTasaInteresCreditoIfip(new TasaInteresCreditoIfip());
        this.setListTasaInteresCreditoIfip(new ArrayList<TasaInteresCreditoIfip>());

        this.setTasaInteresProCreMonIfi(new TasaInteresProCreMonIfi());
        this.setListaTasaInteresProCreMonIfi(new ArrayList<TasaInteresProCreMonIfi>());

        this.getSelected().setUsuarioRegistradoPor(getUsuario());

    }

    public void edita(ActionEvent event) {

        if (getSelected() != null) {
            setTipoGarantia(getSelected().getCodigoTipoGarantia());
            setTasaInteresProCreMonIfi(getSelected().getCodigoTasa());
            this.getSelected().setUsuarioRegistradoPor(getUsuario());
            getSelected().setFechaRegistro(new Date());

        } else {
            MuestraMensaje.addError("Seleccione un registro primero");
        }
    }

    public void validateUnico(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        tipoGarantia = (TipoGarantia) arg2;

        if (auxTasaInteresProCreMonIfi != null && tipoGarantia != null && (ejbFacade.getTipoGarantiaProCreMonIfi(tasaInteresProCreMonIfi.getCodigo(), tipoGarantia.getCodigo()) != null)) {
            throw new ValidatorException(new FacesMessage("Ya existe este registro"));
        }
    }

    public void eliminaConceptoTransaccion(ActionEvent event) {
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
                        new Object[]{"tipoGarantiaProCreMonIfiController", "eliminaTipoGarantiaProCreMonIfiController", CapturaError.getErrorException(ex)});
            }
        }

    }

    /**
     * @return the listaTipoGarantia
     */
    public List<TipoGarantia> getListaTipoGarantia() {
        return listaTipoGarantia;
    }

    /**
     * @param listaTipoGarantia the listaTipoGarantia to set
     */
    public void setListaTipoGarantia(List<TipoGarantia> listaTipoGarantia) {
        this.listaTipoGarantia = listaTipoGarantia;
    }

    /**
     * @return the listaTasaInteresProCreMonIfi
     */
    public List<TasaInteresProCreMonIfi> getListaTasaInteresProCreMonIfi() {
        return listaTasaInteresProCreMonIfi;
    }

    /**
     * @param listaTasaInteresProCreMonIfi the listaTasaInteresProCreMonIfi to
     * set
     */
    public void setListaTasaInteresProCreMonIfi(List<TasaInteresProCreMonIfi> listaTasaInteresProCreMonIfi) {
        this.listaTasaInteresProCreMonIfi = listaTasaInteresProCreMonIfi;
    }

    /**
     * @return the tasaInteresProCreMonIfi
     */
    public TasaInteresProCreMonIfi getTasaInteresProCreMonIfi() {
        return tasaInteresProCreMonIfi;
    }

    /**
     * @param tasaInteresProCreMonIfi the tasaInteresProCreMonIfi to set
     */
    public void setTasaInteresProCreMonIfi(TasaInteresProCreMonIfi tasaInteresProCreMonIfi) {
        this.tasaInteresProCreMonIfi = tasaInteresProCreMonIfi;
    }

    /**
     * @return the tipoGarantia
     */
    public TipoGarantia getTipoGarantia() {
        return tipoGarantia;
    }

    /**
     * @param tipoGarantia the tipoGarantia to set
     */
    public void setTipoGarantia(TipoGarantia tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
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
     * @return the tasaInteresCreditoIfip
     */
    public TasaInteresCreditoIfip getTasaInteresCreditoIfip() {
        return tasaInteresCreditoIfip;
    }

    /**
     * @param tasaInteresCreditoIfip the tasaInteresCreditoIfip to set
     */
    public void setTasaInteresCreditoIfip(TasaInteresCreditoIfip tasaInteresCreditoIfip) {
        this.tasaInteresCreditoIfip = tasaInteresCreditoIfip;
    }

    /**
     * @return the listTasaInteresCreditoIfip
     */
    public List<TasaInteresCreditoIfip> getListTasaInteresCreditoIfip() {
        return listTasaInteresCreditoIfip;
    }

    /**
     * @param listTasaInteresCreditoIfip the listTasaInteresCreditoIfip to set
     */
    public void setListTasaInteresCreditoIfip(List<TasaInteresCreditoIfip> listTasaInteresCreditoIfip) {
        this.listTasaInteresCreditoIfip = listTasaInteresCreditoIfip;
    }

}
