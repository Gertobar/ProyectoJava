package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroCab;
import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroDet;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioLibretaAhorroCabFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioLibretaAhorroDetFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "talonarioLibretaAhorroCabController")
@ViewScoped
public class TalonarioLibretaAhorroCabController extends AbstractController<TalonarioLibretaAhorroCab> implements Serializable {

    @EJB
    private TalonarioLibretaAhorroCabFacade ejbFacade;

    @EJB
    private TalonarioLibretaAhorroDetFacade ejbFacadeTalonarioLibretaAhorroDet;
    
    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private boolean deshabilitarBotonGuardar;

    private Moneda moneda;
    private ProductoIfip productoIfip;
    private TalonarioLibretaAhorroCab talonarioLibretaAhorroCab;

    private List<ProductoIfip> itemsProductoIfip;

    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public TalonarioLibretaAhorroCabController() {
        super(TalonarioLibretaAhorroCab.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    /**
     * CREA UN NUEVO TALONARIO DE LIBRETAS
     *
     * @param actionEvent
     */
    public void crea(ActionEvent actionEvent) {
        this.setTalonarioLibretaAhorroCab(new TalonarioLibretaAhorroCab());
        this.setMoneda(null);
        this.setProductoIfip(null);
        this.deshabilitarBotonGuardar = true;

    }

    public void guardarTalonario(ActionEvent actionEvent) {
        try {
            // Validar que no exista la serie a asignar
            if ( ejbFacade.getCabeceraPorSerieTipoProducto(this.productoIfip.getProductoIfipPK().getCodigoIfip(),this.getTalonarioLibretaAhorroCab().getInicioSerie(),this.getTalonarioLibretaAhorroCab().getFinSerie(),productoIfip.getProducto().getTipoProducto().getCodigo()) ){
                  String msg = "Numeros de serie se cruzan con otro talonario.";
                  MuestraMensaje.addError(msg);
                  return;
            }
            // Insertando la cabecera del talonario
            this.getTalonarioLibretaAhorroCab().setCodigoIfip(this.productoIfip.getProductoIfipPK().getCodigoIfip());
            this.getTalonarioLibretaAhorroCab().setCodigoMoneda(this.productoIfip.getProductoIfipPK().getCodigoMoneda());
            this.getTalonarioLibretaAhorroCab().setCodigoTipoProducto(this.productoIfip.getProductoIfipPK().getCodigoTipoProducto());
            this.getTalonarioLibretaAhorroCab().setEliminado('N');
            this.getTalonarioLibretaAhorroCab().setFechaRegistro(new Date());
            this.getTalonarioLibretaAhorroCab().setRegistradoPor(this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario()));
            this.getTalonarioLibretaAhorroCab().setProductoIfip(productoIfip);
            this.ejbFacade.create(talonarioLibretaAhorroCab);

            // Insertando el Detalle del talonario para el Uso en las Cuentas
            String siglasLibreta = this.getProductoIfip().getSiglasLibreta();
            int digitosLibreta=(int)this.getTalonarioLibretaAhorroCab().getDigitosLibreta();
            String numeroLibreta;
            TalonarioLibretaAhorroDet tlad;
            for (int c=(int)this.getTalonarioLibretaAhorroCab().getInicioSerie(); c<=(int)this.getTalonarioLibretaAhorroCab().getFinSerie(); c++)
            {
                numeroLibreta = siglasLibreta + String.format("%0"+digitosLibreta+"d",c);
                tlad = new TalonarioLibretaAhorroDet();
                tlad.setCodigoTalLibAhoCab(talonarioLibretaAhorroCab);
                tlad.setEstado('P');
                tlad.setNumeroLibreta(numeroLibreta);
                tlad.setSerie(Long.parseLong(String.valueOf(c)));
                
                this.ejbFacadeTalonarioLibretaAhorroDet.edit(tlad);
                
            }
            
            MuestraMensaje.addSatisfactorioPersistencia(1);
            
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"TalonarioLibretaAhorroCabController", "guardarTalonario", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * BUSCA SI LA SERIE DE INICIO YA ESTE INGRESADA O ESTRA DENTRO DE UN RANGO
     * DE UN TALONARIO INGRESADO.
     */
    public void buscaTalonarioSerie() {
        if (this.ejbFacade.getItemsTalonarioLibAhoCabIfiTipoProducto(ActivacionUsuario.getCodigoIfip(), this.getTalonarioLibretaAhorroCab().getInicioSerie(),productoIfip.getProducto().getTipoProducto().getCodigo()).size() > 0) {
            this.deshabilitarBotonGuardar = true;
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SerieIncioTalonarioExistente");
            MuestraMensaje.addError(msg);
        } else {
            this.deshabilitarBotonGuardar = false;
        }
    }
    
    /**
     * VALIDA QUE EL NUMERO DE LA FIN DE SERIE SEA MAYOR A LA DE INICIO.
     */
    public void validaFinSerie() {
        if (this.getTalonarioLibretaAhorroCab().getFinSerie() <= this.getTalonarioLibretaAhorroCab().getInicioSerie()) {
            this.deshabilitarBotonGuardar = true;
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SerieFinTalonarioMenorSerieInicio");
            MuestraMensaje.addError(msg);
        } else {
            this.deshabilitarBotonGuardar = false;
        }
    }

    // ------------------ REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
        this.getTalonarioLibretaAhorroCab().setProductoIfip(null);

    }
    // ------------------ FIN REFRESCAMIENTO DE COMBOS/LISTAS ---------------------

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeMoneda.getItemsMonedas('N');
    }

    public List<TalonarioLibretaAhorroCab> getItemsTalonarioLibAhoCabIfi() {
        return this.ejbFacade.getItemsTalonarioLibAhoCabIfi(ActivacionUsuario.getCodigoIfip());
    }
    // -- FIN LISTAS DE COMBOS 
    // ---------------------------------------------------------------------------

    // *******************************************************************************************
    // --------------------------------------------------------------------------    
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
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
     * @return the talonarioLibretaAhorroCab
     */
    public TalonarioLibretaAhorroCab getTalonarioLibretaAhorroCab() {
        return talonarioLibretaAhorroCab;
    }

    /**
     * @param talonarioLibretaAhorroCab the talonarioLibretaAhorroCab to set
     */
    public void setTalonarioLibretaAhorroCab(TalonarioLibretaAhorroCab talonarioLibretaAhorroCab) {
        this.talonarioLibretaAhorroCab = talonarioLibretaAhorroCab;
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
     * @return the deshabilitarBotonGuardar
     */
    public boolean isDeshabilitarBotonGuardar() {
        return deshabilitarBotonGuardar;
    }

    /**
     * @param deshabilitarBotonGuardar the deshabilitarBotonGuardar to set
     */
    public void setDeshabilitarBotonGuardar(boolean deshabilitarBotonGuardar) {
        this.deshabilitarBotonGuardar = deshabilitarBotonGuardar;
    }
}
