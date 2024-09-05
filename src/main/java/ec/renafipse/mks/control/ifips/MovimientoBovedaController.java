package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.FraccionMoneda;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.Boveda;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaCuentaEntFin;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.ifips.MovimientoBoveda;
import ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfe;
import ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfePK;
import ec.renafipse.mks.modelo.ifips.TipoMovimientoBoveda;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.cajas.FraccionMonedaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.BovedaDineroFacade;
import ec.renafipse.mks.negocio.ifips.BovedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaCuentaEntFinFacade;
import ec.renafipse.mks.negocio.ifips.IfipCuentaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.ifips.MovimientoBovedaFacade;
import ec.renafipse.mks.negocio.ifips.TipoMovimientoBovedaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "movimientoBovedaController")
@ViewScoped
public class MovimientoBovedaController extends AbstractController<MovimientoBoveda> implements Serializable {

    @EJB
    private MovimientoBovedaFacade ejbFacade;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private TipoMovimientoBovedaFacade ejbFacadeTipoMovimientoBoveda;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private BovedaFacade ejbFacadeBoveda;

    @EJB
    private BovedaDineroFacade ejbFacadeBovedaDinero;

    @EJB
    private FraccionMonedaFacade ejbFacadeFraccionMoneda;

  
    @EJB
    private AperturaFacade ejbFacadeApertura;
    
    @EJB 
    private IfipCuentaEntidadFinancieraFacade ejbFacadeIfipCuentaEntFin;

    
    private LlamaSP llamaSP;

    private boolean deshabilitaBotonGuardar;
    private boolean verPanelDesgloceEfectivo;
    private boolean guardo;
    private boolean verPanelEntidades;
    private boolean verPanelAperturaCajas;

    private String tipo;
    private String msg;
    private Long codigoMovimientoBoveda;
    private String interviene;

    private GeneraReporte generaReporte;
    private MovimientoBoveda movimientoBoveda;
    private Moneda moneda;
    private MovimientoBovedaDesEfe movimientoBovedaDesEfe;
    private IfipAgenciaCuentaEntFin ifipAgenciaCuentaEntFin;
    private Apertura apertura;
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera;

    private List<Boveda> itemsBoveda;
    private List<MovimientoBovedaDesEfe> itemsMovimientoBovedaDesEfe;
    private List<TipoMovimientoBoveda> itemsTipoMovimientoBoveda;
    private List<IfipAgenciaCuentaEntFin> itemsIfipAgenciaCuentaEntFin;
    private List<Apertura> itemsApertura;
    private List<IfipCuentaEntidadFinanciera> itemsIfipCuentaEntidadFinanciera;
    private int permiteTransaccionar;

    public MovimientoBovedaController() {
        super(MovimientoBoveda.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();

        if (this.ejbFacadeBoveda.getItemsResponsableIfipAgenciaEliminado(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoUsuario(), 'N').isEmpty()) {
            this.setItemsBoveda(this.ejbFacadeBovedaDinero.getItemsBovedaMonedaFondeoCaja(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoUsuario(), Long.parseLong("1"), BigDecimal.ZERO, 'N'));
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoResponsableBovedas"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoBovedaController", "init", CapturaError.getErrorException(ex)});
            }
        }

        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoBovedaController", "init", CapturaError.getErrorException(ex)});
            }
        }

        this.movimientoBoveda = new MovimientoBoveda();
        this.moneda = this.ejbFacadeMoneda.find(Long.parseLong("1"));
        this.cambiaMoneda();
        this.movimientoBoveda.setFechaSistema(new Date());
        this.movimientoBoveda.setFechaMovimiento(ConvierteDato.getFechaMedium(this.movimientoBoveda.getFechaSistema()));
        this.movimientoBoveda.setValorEfectivo(new BigDecimal("0.00"));
        this.deshabilitaBotonGuardar = true;
        this.verPanelDesgloceEfectivo = false;
        this.apertura = null;
        this.ifipAgenciaCuentaEntFin = null;

        this.cambiaTipo();
        this.tipo = null;
        this.verPanelAperturaCajas = false;
        this.verPanelAperturaCajas = false;
        this.interviene = "N";

        this.itemsMovimientoBovedaDesEfe = new ArrayList<MovimientoBovedaDesEfe>();
        this.cargaItemsDesgloceEfectivo();

    }

    /**
     * VERIFICA EL ACCESO A LOS MOVIMIENTOS DE BOVEDA
     */
    public void verificaHorario(){
        try {
            llamaSP.setNombreSP("mks_ifips.pkg_horario_movimiento_boveda.p_verifica_acceso_mov_bov");
            llamaSP.setNumeroParametros(1);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_puede_acceder", Types.NUMERIC});

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                Long permite = Long.parseLong(llamaSP.getListResultado().get(0).toString());
                if (permite == 1){
                    setPermiteTransaccionar(1);
                }else{
                    setPermiteTransaccionar(0);
                    MuestraMensaje.addError("No es posible ingresar Movimientos de Boveda en este horario.");
                }
            }else {
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, llamaSP.getErroSql(),new Object[]{"movimientoBovedaController", "guardarMovimiento", llamaSP.getErroSql()});
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoBovedaController", "verificaHorario", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * CARGA LOS ITEMS/FRACCIONES DEL DESFLOCE DEL EFECTIVO.
     */
    public void cargaItemsDesgloceEfectivo() {
        // -- Creando el desgloce de efectivo
        List<FraccionMoneda> listaFraccionMoneda = this.ejbFacadeFraccionMoneda.getItemsEliminadoTipoFranccionMoneda(this.getMoneda().getCodigo(), 'N');

        for (FraccionMoneda fm : listaFraccionMoneda) {
            this.movimientoBovedaDesEfe = new MovimientoBovedaDesEfe(new MovimientoBovedaDesEfePK());
            this.movimientoBovedaDesEfe.setFraccionMoneda(fm);
            this.movimientoBovedaDesEfe.setCantidad(Long.parseLong("0"));
            this.movimientoBovedaDesEfe.setValor(fm.getTipoFraccionMoneda().getValor());
            this.movimientoBovedaDesEfe.setTotal(new BigDecimal("0.00"));

            this.itemsMovimientoBovedaDesEfe.add(movimientoBovedaDesEfe);
        }
    }

    /**
     * GUARDA EL MOVIMIENTO DE LA BOVEDA.
     *
     * @param actionEvent
     * @throws java.io.IOException
     */
    public void guardarMovimiento(ActionEvent actionEvent) throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            this.msg = null;
            this.guardo = false;
            if (!this.ejbFacade.getItemsBodegaTipoMovimientoComprobante(this.getMovimientoBoveda().getCodigoBoveda().getCodigo(),
                    this.getMovimientoBoveda().getCodigoTipoMovimiento().getCodigo(),
                    this.getMovimientoBoveda().getComprobante()).isEmpty()) {
                this.msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComprobanteYaExistente");

                //this.deshabilitaBotonGuardar = true;
                //this.movimientoBoveda.setComprobante(null);
            }
            
            if (msg == null) {
                context.execute("procesandoDlg.show()");
                Timestamp fechaSistema = new java.sql.Timestamp(new Date().getTime());
                llamaSP.setNombreSP("mks_ifips.pkm_movimiento_boveda.p_guarda_movivimiento_boveda");
                llamaSP.setNumeroParametros(18);

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_boveda", this.movimientoBoveda.getCodigoBoveda().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.moneda.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_movimiento", this.movimientoBoveda.getCodigoTipoMovimiento().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", (this.ifipCuentaEntidadFinanciera != null) ? this.ifipCuentaEntidadFinanciera.getCodigo() : null});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", (this.apertura != null) ? this.apertura.getCodigo() : null});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_interviene", this.interviene});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", (String.valueOf(this.tipo))});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", fechaSistema});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_comprobante", this.movimientoBoveda.getComprobante()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_efectivo", this.movimientoBoveda.getValorEfectivo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_cheques", new BigDecimal("0.00")});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_movimiento", this.movimientoBoveda.getTotalMovimiento()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.movimientoBoveda.getObservaciones()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

                // Cargando la conexion de BD
                llamaSP.cargaConexion();

                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);

                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);

                // Invocando al SP
                llamaSP.invocaSP();

                if (llamaSP.isEjecucionCorrecta()) {

                    setCodigoMovimientoBoveda((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));

                    llamaSP.setNombreSP("mks_ifips.pkg_movimiento_boveda_des_efe.p_inserta_desgloce_efectivo");
                    llamaSP.setNumeroParametros(6);
                    // Insertando degloce del movimiento 
                    for (MovimientoBovedaDesEfe mbde : this.itemsMovimientoBovedaDesEfe) {

                        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_fraccion", mbde.getFraccionMoneda().getTipoFraccionMoneda().getCodigo()});
                        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.moneda.getCodigo()});
                        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movimiento_boveda", getCodigoMovimientoBoveda()});
                        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cantidad", mbde.getCantidad()});
                        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", mbde.getValor()});
                        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", mbde.getTotal()});

                        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                        // Invocando al SP
                        llamaSP.invocaSP();

                        // Verificando si el desgloce no dio errores
                        if (!llamaSP.isEjecucionCorrecta()) {
                            break;
                        }
                    }

                    // Verificando que la ejecucion del proceso ha sido correcta
                    if (llamaSP.isEjecucionCorrecta()) {
                        // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                        llamaSP.commit();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                        MuestraMensaje.addSatisfactorio(msg);
                        this.guardo = true;
                        this.deshabilitaBotonGuardar = true;

                        context.execute("procesandoDlg.hide()");
                        context.execute("imprimeDialog.show()");
                        this.init();
                        //context.execute("MovimientoBovedaFor.update()");

                    } else {
                        context.execute("procesandoDlg.hide()");
                        llamaSP.rollback();
                        llamaSP.cerrarConexion();
                        llamaSP.setConexionBD(null);
                    }
                } else {
                    context.execute("procesandoDlg.hide()");
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                }
            } else {
                context.execute("procesandoDlg.hide()");
                MuestraMensaje.addError(msg);
            }
        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoBovedaController", "guardarMovimiento", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Realiza el calculo cuando la cantidad es modificada en el desgloce del
     * efectivo.
     */
    public void cantidadModificada() {

        BigDecimal totalFondeo = new BigDecimal("0.00");
        BigDecimal valor;
        Long cantidad;
        BigDecimal total;

        for (int c = 0; c < this.itemsMovimientoBovedaDesEfe.size(); c++) {
            cantidad = this.itemsMovimientoBovedaDesEfe.get(c).getCantidad();
            valor = this.itemsMovimientoBovedaDesEfe.get(c).getValor();
            total = valor.multiply(new BigDecimal(cantidad.toString()));
            this.itemsMovimientoBovedaDesEfe.get(c).setTotal(total);
            totalFondeo = totalFondeo.add(total);
        }

        this.movimientoBoveda.setValorEfectivo(totalFondeo);
        this.movimientoBoveda.setTotalMovimiento(totalFondeo);
        this.deshabilitaBotonGuardar();

    }

    public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoMovimiento", this.getCodigoMovimientoBoveda());

        nombreReporte = "movimientoBoveda";

        getGeneraReporte().exporta("/financiero/boveda/movimientoBoveda/reporte/", nombreReporte,
                "movimientoBoveda" + String.valueOf(getCodigoMovimientoBoveda()) + ".pdf",
                "PDF", externalContext, facesContext);

    }

    public void cambiaMovimientoBoveda() {
        interviene = String.valueOf(this.movimientoBoveda.getCodigoTipoMovimiento().getInterviene());

        if (interviene.equals("C")) {
            this.setItemsApertura(this.ejbFacadeApertura.getItemsIfipAgenciaEstado(ActivacionUsuario.getCodigoIfipAgencia(), 'A'));
            this.apertura = null;
            this.verPanelAperturaCajas = true;
            this.verPanelEntidades = false;
            this.movimientoBoveda.setObservaciones("");
        } else if (interviene.equals("E")) {
            this.setItemsIfipCuentaEntidadFinanciera(ejbFacadeIfipCuentaEntFin.getItemsCuentaFinancieraIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoIfip(), 'N'));
            //this.setItemsIfipAgenciaCuentaEntFin(this.ejbFacadeIfipAgenciaCuentaEntFin.getItemsIfipAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
            this.ifipAgenciaCuentaEntFin = null;
            this.verPanelAperturaCajas = false;
            this.verPanelEntidades = true;
        } else {
            this.interviene = "N";
            this.verPanelAperturaCajas = false;
            this.verPanelEntidades = false;

        }

    }

    public void cambiaEntidadFinanciera() {

        if (this.movimientoBoveda.getCodigoTipoMovimiento() != null) {
            this.movimientoBoveda.setObservaciones(this.movimientoBoveda.getCodigoTipoMovimiento().getNombre() + " "
                    + this.getIfipCuentaEntidadFinanciera().getEntidadFinanciera().getNombre() + " "
                    + this.getIfipCuentaEntidadFinanciera().getTipoCuentaEntidadFinanciera().getNombre() + " "
                    + this.getIfipCuentaEntidadFinanciera().getNumeroCuenta());
            
        } else {
            this.movimientoBoveda.setObservaciones(null);
        }

    }

    public void cambiaAperturaCaja() {
        if (this.apertura != null) {
            this.movimientoBoveda.setObservaciones(this.movimientoBoveda.getCodigoTipoMovimiento().getNombre() + " CODIGO APERTURA "
                    + this.apertura.getCodigo() + " " + this.apertura.getComputador().getNombre() + " " + this.apertura.getUsuario().getCodigoPersona().getNombreCompleto());
        } else {
            this.movimientoBoveda.setObservaciones(null);
        }
    }

    /**
     * DESHABILITA EL BOTON GUARDAR.
     */
    public void deshabilitaBotonGuardar() {

        if (this.movimientoBoveda.getValorEfectivo().compareTo(new BigDecimal("0.00")) > 0) {
            // Si la boveda cuenta con dinero
            this.setDeshabilitaBotonGuardar(false);
        } else {
            this.setDeshabilitaBotonGuardar(true);
        }
    }

    /**
     * MUESTRA EL PANEL CUANDO SE PRESIONA EN EL BOTON DESGLOCE.
     */
    public void muestraPanelDesgloce() {
        this.verPanelDesgloceEfectivo = true;
    }

    /**
     * DESAPARECE EL PANEL CUANDO PRESIONA EL BOTON REGRESAR.
     */
    public void desaparecePanelDesgloce() {
        this.verPanelDesgloceEfectivo = false;
    }

    // ---------------------------------------------------------------------------
    // -- REFRESCAMIENTO DE COMBOS/LISTAS 
    public void cambiaTipo() {
        if (this.tipo != null) {
            this.setItemsTipoMovimientoBoveda(this.ejbFacadeTipoMovimientoBoveda.getItemsTipoEliminado(this.tipo.charAt(0), 'S', 'N'));
        }
        this.movimientoBoveda.setCodigoTipoMovimiento(null);
        this.apertura = null;
        this.ifipCuentaEntidadFinanciera = null;
    }

    public void cambiaMoneda() {
        //Colocando las Bovedas 
        this.setItemsBoveda(this.ejbFacadeBovedaDinero.getItemsBovedaMonedaMovimientoBoveda(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoUsuario(), Long.parseLong("1"), 'N'));
        this.movimientoBoveda.setCodigoBoveda(null);
    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    // ---------------------------------------------------------------------------  
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    /**
     * @return the itemsBoveda
     */
    public List<Boveda> getItemsBoveda() {
        return itemsBoveda;
    }

    /**
     * @param itemsBoveda the itemsBoveda to set
     */
    public void setItemsBoveda(List<Boveda> itemsBoveda) {
        this.itemsBoveda = itemsBoveda;
    }

    /**
     * @return the movimientoBoveda
     */
    public MovimientoBoveda getMovimientoBoveda() {
        return movimientoBoveda;
    }

    /**
     * @param movimientoBoveda the movimientoBoveda to set
     */
    public void setMovimientoBoveda(MovimientoBoveda movimientoBoveda) {
        this.movimientoBoveda = movimientoBoveda;
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
     * @return the itemsMovimientoBovedaDesEfe
     */
    public List<MovimientoBovedaDesEfe> getItemsMovimientoBovedaDesEfe() {
        return itemsMovimientoBovedaDesEfe;
    }

    /**
     * @param itemsMovimientoBovedaDesEfe the itemsMovimientoBovedaDesEfe to set
     */
    public void setItemsMovimientoBovedaDesEfe(List<MovimientoBovedaDesEfe> itemsMovimientoBovedaDesEfe) {
        this.itemsMovimientoBovedaDesEfe = itemsMovimientoBovedaDesEfe;
    }

    /**
     * @return the movimientoBovedaDesEfe
     */
    public MovimientoBovedaDesEfe getMovimientoBovedaDesEfe() {
        return movimientoBovedaDesEfe;
    }

    /**
     * @param movimientoBovedaDesEfe the movimientoBovedaDesEfe to set
     */
    public void setMovimientoBovedaDesEfe(MovimientoBovedaDesEfe movimientoBovedaDesEfe) {
        this.movimientoBovedaDesEfe = movimientoBovedaDesEfe;
    }

    /**
     * @return the deshabilitaBotonGuardar
     */
    public boolean isDeshabilitaBotonGuardar() {
        return deshabilitaBotonGuardar;
    }

    /**
     * @param deshabilitaBotonGuardar the deshabilitaBotonGuardar to set
     */
    public void setDeshabilitaBotonGuardar(boolean deshabilitaBotonGuardar) {
        this.deshabilitaBotonGuardar = deshabilitaBotonGuardar;
    }

    /**
     * @return the verPanelDesgloceEfectivo
     */
    public boolean isVerPanelDesgloceEfectivo() {
        return verPanelDesgloceEfectivo;
    }

    /**
     * @param verPanelDesgloceEfectivo the verPanelDesgloceEfectivo to set
     */
    public void setVerPanelDesgloceEfectivo(boolean verPanelDesgloceEfectivo) {
        this.verPanelDesgloceEfectivo = verPanelDesgloceEfectivo;
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

    /**
     * @return the itemsTipoMovimientoBoveda
     */
    public List<TipoMovimientoBoveda> getItemsTipoMovimientoBoveda() {
        return itemsTipoMovimientoBoveda;
    }

    /**
     * @param itemsTipoMovimientoBoveda the itemsTipoMovimientoBoveda to set
     */
    public void setItemsTipoMovimientoBoveda(List<TipoMovimientoBoveda> itemsTipoMovimientoBoveda) {
        this.itemsTipoMovimientoBoveda = itemsTipoMovimientoBoveda;
    }

    /**
     * @return the guardo
     */
    public boolean isGuardo() {
        return guardo;
    }

    /**
     * @param guardo the guardo to set
     */
    public void setGuardo(boolean guardo) {
        this.guardo = guardo;
    }

    /**
     * @return the generaReporte
     */
    public GeneraReporte getGeneraReporte() {
        return generaReporte;
    }

    /**
     * @param generaReporte the generaReporte to set
     */
    public void setGeneraReporte(GeneraReporte generaReporte) {
        this.generaReporte = generaReporte;
    }

    /**
     * @return the codigoMovimientoBoveda
     */
    public Long getCodigoMovimientoBoveda() {
        return codigoMovimientoBoveda;
    }

    /**
     * @param codigoMovimientoBoveda the codigoMovimientoBoveda to set
     */
    public void setCodigoMovimientoBoveda(Long codigoMovimientoBoveda) {
        this.codigoMovimientoBoveda = codigoMovimientoBoveda;
    }

    /**
     * @return the itemsIfipAgenciaCuentaEntFin
     */
    public List<IfipAgenciaCuentaEntFin> getItemsIfipAgenciaCuentaEntFin() {
        return itemsIfipAgenciaCuentaEntFin;
    }

    /**
     * @param itemsIfipAgenciaCuentaEntFin the itemsIfipAgenciaCuentaEntFin to
     * set
     */
    public void setItemsIfipAgenciaCuentaEntFin(List<IfipAgenciaCuentaEntFin> itemsIfipAgenciaCuentaEntFin) {
        this.itemsIfipAgenciaCuentaEntFin = itemsIfipAgenciaCuentaEntFin;
    }

    /**
     * @return the ifipAgenciaCuentaEntFin
     */
    public IfipAgenciaCuentaEntFin getIfipAgenciaCuentaEntFin() {
        return ifipAgenciaCuentaEntFin;
    }

    /**
     * @param ifipAgenciaCuentaEntFin the ifipAgenciaCuentaEntFin to set
     */
    public void setIfipAgenciaCuentaEntFin(IfipAgenciaCuentaEntFin ifipAgenciaCuentaEntFin) {
        this.ifipAgenciaCuentaEntFin = ifipAgenciaCuentaEntFin;
    }

    /**
     * @return the itemsApertura
     */
    public List<Apertura> getItemsApertura() {
        return itemsApertura;
    }

    /**
     * @param itemsApertura the itemsApertura to set
     */
    public void setItemsApertura(List<Apertura> itemsApertura) {
        this.itemsApertura = itemsApertura;
    }

    /**
     * @return the verPanelEntidades
     */
    public boolean isVerPanelEntidades() {
        return verPanelEntidades;
    }

    /**
     * @param verPanelEntidades the verPanelEntidades to set
     */
    public void setVerPanelEntidades(boolean verPanelEntidades) {
        this.verPanelEntidades = verPanelEntidades;
    }

    /**
     * @return the verPanelAperturaCajas
     */
    public boolean isVerPanelAperturaCajas() {
        return verPanelAperturaCajas;
    }

    /**
     * @param verPanelAperturaCajas the verPanelAperturaCajas to set
     */
    public void setVerPanelAperturaCajas(boolean verPanelAperturaCajas) {
        this.verPanelAperturaCajas = verPanelAperturaCajas;
    }

    /**
     * @return the apertura
     */
    public Apertura getApertura() {
        return apertura;
    }

    /**
     * @param apertura the apertura to set
     */
    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }

    /**
     * @return the ifipCuentaEntidadFinanciera
     */
    public IfipCuentaEntidadFinanciera getIfipCuentaEntidadFinanciera() {
        return ifipCuentaEntidadFinanciera;
    }

    /**
     * @param ifipCuentaEntidadFinanciera the ifipCuentaEntidadFinanciera to set
     */
    public void setIfipCuentaEntidadFinanciera(IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera) {
        this.ifipCuentaEntidadFinanciera = ifipCuentaEntidadFinanciera;
    }

    /**
     * @return the itemsIfipCuentaEntidadFinanciera
     */
    public List<IfipCuentaEntidadFinanciera> getItemsIfipCuentaEntidadFinanciera() {
        return itemsIfipCuentaEntidadFinanciera;
    }

    /**
     * @param itemsIfipCuentaEntidadFinanciera the itemsIfipCuentaEntidadFinanciera to set
     */
    public void setItemsIfipCuentaEntidadFinanciera(List<IfipCuentaEntidadFinanciera> itemsIfipCuentaEntidadFinanciera) {
        this.itemsIfipCuentaEntidadFinanciera = itemsIfipCuentaEntidadFinanciera;
    }

    /**
     * @return the permiteTransaccionar
     */
    public int getPermiteTransaccionar() {
        return permiteTransaccionar;
    }

    /**
     * @param permiteTransaccionar the permiteTransaccionar to set
     */
    public void setPermiteTransaccionar(int permiteTransaccionar) {
        this.permiteTransaccionar = permiteTransaccionar;
    }

}
