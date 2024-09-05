package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.PermisoOperacion;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.BloqueoCuenta;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.TipoBloqueoCuenta;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.OpcionOperacion;
import ec.renafipse.mks.negocio.ahorros.BloqueoCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TipoBloqueoCuentaFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.MenuFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "bloqueoDesbloqueoCuentaBean")
@ViewScoped
public class BloqueoDesbloqueoCuentaBean extends AbstractController<Cuenta> implements Serializable {

    @EJB
    private CuentaFacade ejbFacade;
    
    @EJB
    private MenuFacade ejbFacadeMenu;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private TipoBloqueoCuentaFacade ejbFacadeTipoBloqueoCuenta;

    @EJB
    private BloqueoCuentaFacade ejbFacadeBloqueoCuenta;

    //--------------------------------------------------------------------------
    // --PARAMETROS
    private LlamaSP llamaSP;
    private RequestContext context;
    private String msg;
    private String nombresSocio;
    private String numeroCuenta;
    private String mensajeDialogo;
    private String observaciones;
    private String tipo;
    private BigDecimal valor;

    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Cuenta cuenta;
    private Cuenta cuentaSel;
    private TipoBloqueoCuenta tipoBloqueoCuenta;
    private BloqueoCuenta BloqueoCuentaSel;

    private List<ProductoIfip> itemsProductoIfip;
    private List<Cuenta> itemsCuenta;
    private List<TipoBloqueoCuenta> itemsTipoBloqueoCuenta;
    private List<BloqueoCuenta> itemsBloqueoCuenta;
    private PermisoOperacion permisoOperacion;
    
    private boolean deshabilitaBtnBloquear;
    private boolean deshabilitaBtnDesbloquear;
    private boolean deshabilitarBtnGuardar;

    public BloqueoDesbloqueoCuentaBean() {
        super(Cuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        permisos();
        this.deshabilitaBtnBloquear = this.getPermisoOperacion().isBloquear();
        this.deshabilitaBtnDesbloquear = this.getPermisoOperacion().isDesbloquear();
        
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"bloqueoDesbloqueoCuentaBean", "init", CapturaError.getErrorException(ex)});
            }
        }
        this.cuenta = null;
        this.cuentaSel = null;
        this.moneda = null;
        this.productoIfip = null;
        this.itemsProductoIfip = null;
        this.observaciones = null;
        this.numeroCuenta = null;
        this.tipoBloqueoCuenta = null;
        this.valor = null;
        llamaSP = new LlamaSP();
        
        this.tipo = null;
        this.itemsTipoBloqueoCuenta = this.ejbFacadeTipoBloqueoCuenta.getItemsMostrarElimiando('S', 'N');
        this.itemsBloqueoCuenta = null;
        
        if(this.deshabilitaBtnBloquear && this.deshabilitaBtnDesbloquear){
            this.deshabilitarBtnGuardar = true;
        
        }else{
            this.deshabilitarBtnGuardar = false;
        }
        
    }

    public void desgloseBloqueos() {
        if (cuenta != null) {
            this.itemsBloqueoCuenta = this.ejbFacadeBloqueoCuenta.getItemsCuentaVigente(cuenta, 'S');
        } else {
            MuestraMensaje.addAdvertencia("Seleccione una cuenta para mostrar el desglose");
            return;
        }

    }

    //------------------------------------------------------------------------
    // GUARDA EL BLOQUEO O DESBLOQUEO DEL VALOR DE LA CUENTA
    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR EL BLOQUEO O DESBLOQUEO DE LA CUENTA
    public void guardar() {
        context = RequestContext.getCurrentInstance();

        if (!validaBloqueoDesbloqueo()) {
            MuestraMensaje.addError(msg);
            return;
        }
        context.execute("procesandoDlg.show()");
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Bloqueo o Desbloqueo de Valor
            llamaSP.setNombreSP("mks_ahorros.pkm_bloqueo_desbloqueo_cuenta.p_guarda");
            llamaSP.setNumeroParametros(13);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.cuenta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_bloqueo", this.tipoBloqueoCuenta.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", this.tipo});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", this.valor});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_disponible_antes", this.cuenta.getSaldoDisponible()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_bloqueado_antes", this.cuenta.getSaldoBloqueado()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo_total_antes", this.cuenta.getSaldoTotal()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_bloqueado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.observaciones});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                if (tipo.equals("B")) {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("BloqueoCuentaSatisfactoriamente"));
                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DesbloqueoCuentaSatisfactoriamente"));
                }

                MuestraMensaje.addInformacion(getMsg());

                this.init();

                context.execute("procesandoDlg.hide()");

                //context.execute("MovimientoBovedaFor.update()");
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (IOException ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"anulaChequeGiradoBean", "guardaAnulacion", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Valida si los datos del Bloqueo estan correctos
     *
     * @return true correctos y false incorrecto
     */
    private boolean validaBloqueoDesbloqueo() {
        this.msg = null;
        if (this.tipo == null || this.tipoBloqueoCuenta == null || this.valor == null || this.observaciones == null || this.numeroCuenta == null) {
            this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCamposRequeridos");
        } else {
            if (this.observaciones.isEmpty() || this.numeroCuenta.isEmpty()) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCamposRequeridos");
            } else {
                if (this.valor.equals(BigDecimal.ZERO)) {
                    this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorMayorACero");
                } else {
                    if (Validaciones.cumpleRequerimientoCampo(observaciones, 6)) {
                        if (this.tipo.equals("B")) {
                            if (this.valor.compareTo(this.cuenta.getSaldoDisponible()) > 0) {
                                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
                            }
                        } else {
                            if (this.valor.compareTo(this.cuenta.getSaldoBloqueado()) > 0) {
                                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SaldoBloqueadoInsuficiente");
                            }
                        }
                    } else {
                        this.msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + Validaciones.msg;
                    }
                }
            }
        }
        return this.msg == null;
    }

    //---------------------------------------------------------------------------
    // -- METODOS DE LA LISTA DE BUSQUEDA DE CUENTAS
    public void preparaBusquedaCuentas(ActionEvent actionEvent) {
        this.nombresSocio = null;
        this.setItemsCuenta(null);
        this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
    }

    public void buscaCuentas() {
        if (nombresSocio.trim().isEmpty()) {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
            this.setItemsCuenta(null);
        } else if (this.nombresSocio.trim().length() <= 6) {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres");
            this.setItemsCuenta(null);
        } else {
            this.mensajeDialogo = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta");
            this.mensajeDialogo = null;
            this.setItemsCuenta(this.ejbFacade.getItemsMovimientoCuenta(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                    this.nombresSocio.toUpperCase(), 'S'));
        }
    }

    public void seleccionaCuenta(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        this.cuenta = this.cuentaSel;
        this.numeroCuenta = this.cuenta.getNumero();
        this.setValor(null);
    }

    /**
     * Busca la cuenta del Socio.
     */
    public void buscaCuenta() {
       try{
        msg = null;
        List<Cuenta> listaCuenta = this.ejbFacade.getItemsNumero(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                this.numeroCuenta.toUpperCase(),
                new Long(1));
        ////System.out.println("Cuenta " + listaCuenta);
        if (listaCuenta.isEmpty()) {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuentaNoExiste");
        } else if (listaCuenta.size() == 1) {

            this.cuenta = listaCuenta.get(0);
            this.numeroCuenta = this.cuenta.getNumero();
            if (this.cuenta.getCodigoEstado().getCodigo().toString().equals("3")) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaCerrada");
            } else if (String.valueOf(this.cuenta.getCodigoEstado().getIndicaActiva()).equals("N")) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cuenta") + this.cuenta.getCodigoEstado().getNombre();
            }
        } else {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroMasUnaCuenta");
        }
       }catch(Exception ex){
           Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"BloqueoDesbloqueoCuentaBean", "buscaCuenta", CapturaError.getErrorException(ex)});
       }

    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    //----------------- REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * CUANDO CAMBIA EL PRODUCTO. BUSCA SI EL SOCIO TIENE UNA CUENTA DE
     * CERTIFICADOS ASIGNADA Y ESTE ACTIVA.
     */
    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
        this.setProductoIfip(null);
        this.cuenta = null;
        this.numeroCuenta = null;
        this.cuenta = null;

    }

    /**
     * CUANDO CAMBIA EL PRODUCTO.
     */
    public void cambiaProductoIfip() {
        this.cuenta = null;
        this.numeroCuenta = null;
        this.cuenta = null;
    }
    
    
    private void permisos(){
            // Obteniendo los permiso delos tipos de operaciones en caso de tener se coloca en false para 
        // inidicar que no se deshabiliten los botones
        List<OpcionOperacion> listOpcionOperacion = this.ejbFacadeMenu.getOpcionOperacion(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoOpcion());
        this.setPermisoOperacion(new PermisoOperacion());
        for (OpcionOperacion operacion : listOpcionOperacion) {

            switch (Integer.parseInt(operacion.getCodigoTipoOperacion().getCodigo().toString())) {
                case 1:
                    this.getPermisoOperacion().setNuevo(false);
                    break;

                case 2:
                    this.getPermisoOperacion().setEditar(false);
                    break;

                case 3:
                    this.getPermisoOperacion().setEliminar(false);
                    break;

                case 4:
                    this.getPermisoOperacion().setImprimir(false);
                    break;

                case 5:
                    this.getPermisoOperacion().setConsultar(false);
                    break;

                case 6:
                    this.getPermisoOperacion().setVer(false);
                    break;

                case 7:
                    this.getPermisoOperacion().setImprimirComprobante(false);
                    break;

                case 17:
                    this.getPermisoOperacion().setBloquear(false);
                    break;
                    
                case 18:
                    this.getPermisoOperacion().setDesbloquear(false);
                    break;

            }

        }
    
    
    }

    //-------------------------------------------------------------------------------
    // ENCAPSULAMIENTO DE VARIABLES
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

    /**
     * @return the nombresSocio
     */
    public String getNombresSocio() {
        return nombresSocio;
    }

    /**
     * @param nombresSocio the nombresSocio to set
     */
    public void setNombresSocio(String nombresSocio) {
        this.nombresSocio = nombresSocio;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the mensajeDialogo
     */
    public String getMensajeDialogo() {
        return mensajeDialogo;
    }

    /**
     * @param mensajeDialogo the mensajeDialogo to set
     */
    public void setMensajeDialogo(String mensajeDialogo) {
        this.mensajeDialogo = mensajeDialogo;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the cuentaSel
     */
    public Cuenta getCuentaSel() {
        return cuentaSel;
    }

    /**
     * @param cuentaSel the cuentaSel to set
     */
    public void setCuentaSel(Cuenta cuentaSel) {
        this.cuentaSel = cuentaSel;
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
     * @return the itemsCuenta
     */
    public List<Cuenta> getItemsCuenta() {
        return itemsCuenta;
    }

    /**
     * @param itemsCuenta the itemsCuenta to set
     */
    public void setItemsCuenta(List<Cuenta> itemsCuenta) {
        this.itemsCuenta = itemsCuenta;
    }

    /**
     * @return the tipoBloqueoCuenta
     */
    public TipoBloqueoCuenta getTipoBloqueoCuenta() {
        return tipoBloqueoCuenta;
    }

    /**
     * @param tipoBloqueoCuenta the tipoBloqueoCuenta to set
     */
    public void setTipoBloqueoCuenta(TipoBloqueoCuenta tipoBloqueoCuenta) {
        this.tipoBloqueoCuenta = tipoBloqueoCuenta;
    }

    /**
     * @return the itemsTipoBloqueoCuenta
     */
    public List<TipoBloqueoCuenta> getItemsTipoBloqueoCuenta() {
        return itemsTipoBloqueoCuenta;
    }

    /**
     * @param itemsTipoBloqueoCuenta the itemsTipoBloqueoCuenta to set
     */
    public void setItemsTipoBloqueoCuenta(List<TipoBloqueoCuenta> itemsTipoBloqueoCuenta) {
        this.itemsTipoBloqueoCuenta = itemsTipoBloqueoCuenta;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<BloqueoCuenta> getItemsBloqueoCuenta() {
        return itemsBloqueoCuenta;
    }

    public void setItemsBloqueoCuenta(List<BloqueoCuenta> itemsBloqueoCuenta) {
        this.itemsBloqueoCuenta = itemsBloqueoCuenta;
    }

    public PermisoOperacion getPermisoOperacion() {
        return permisoOperacion;
    }

    public void setPermisoOperacion(PermisoOperacion permisoOperacion) {
        this.permisoOperacion = permisoOperacion;
    }

    public boolean isDeshabilitaBtnBloquear() {
        return deshabilitaBtnBloquear;
    }

    public void setDeshabilitaBtnBloquear(boolean deshabilitaBtnBloquear) {
        this.deshabilitaBtnBloquear = deshabilitaBtnBloquear;
    }

    public boolean isDeshabilitaBtnDesbloquear() {
        return deshabilitaBtnDesbloquear;
    }

    public void setDeshabilitaBtnDesbloquear(boolean deshabilitaBtnDesbloquear) {
        this.deshabilitaBtnDesbloquear = deshabilitaBtnDesbloquear;
    }

    public boolean isDeshabilitarBtnGuardar() {
        return deshabilitarBtnGuardar;
    }

    public void setDeshabilitarBtnGuardar(boolean deshabilitarBtnGuardar) {
        this.deshabilitarBtnGuardar = deshabilitarBtnGuardar;
    }

}
