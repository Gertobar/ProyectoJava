package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.LiquidacionCuentaCabecera;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.SocioPK;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.LiquidacionCuentaCabeceraFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "liquidacionCuentaBean")
@ViewScoped
public class LiquidacionCuentaBean extends AbstractController<LiquidacionCuentaCabecera> implements Serializable {

    @EJB
    private LiquidacionCuentaCabeceraFacade ejbFacade;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    private LlamaSP llamaSP;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private Long codigoSocio;
    private String nombreSocio;
    private String nombreSocioBusqueda;
    private BigDecimal totalLiquidacion;
    private BigDecimal totalIntereses;
    private BigDecimal totalSaldo;
    private BigDecimal totalCertificados;
    private String msg;
    private Timestamp fechaLiquidacion;
    private Long codigoLiquidacion;
    private GeneraReporte generaReporte;
    private String retiraSocio;

    private boolean deshabilitaBotonLiquidar;

    private Socio socioSel;

    private List<Socio> itemsSocio;
    private List<Cuenta> itemsCuentas;
    private List<Cuenta> itemsCuentasSeleccionado;

    public LiquidacionCuentaBean() {
        super(LiquidacionCuentaCabecera.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");

        try {

            //System.out.println("INicia datos");
            //Instancion el proceso de llama Store Procedure
            llamaSP = new LlamaSP();

            ////System.out.println("Direccion IP " + ObtieneInformacionCliente.obtenerDireccionIP() + " Codigo Computador " + ActivacionUsuario.getCodigoComputador());
            /*if (this.ejbFacadeRolOpcionOperacion.getItemsOperacionPermisosMenu(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoOpcion(), ActivacionUsuario.getCodigoIfip(), 'N').isEmpty()) {
             ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
             //Accediendo a la ventana de no permisos            
             Sesion.redireccionaPagina(url);
             }*/
            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            ////System.out.println("PAsa computador");
            List<Apertura> listaApertura = this.ejbFacadeApertura.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
            // Verifico que si tiene aperturado la caja
            if (!listaApertura.isEmpty()) {
                if (listaApertura.size() == 1) {
                    // Validamos si la Apertura de la Caja pertenece a la Fecha de Hoy
                    if (!Validaciones.validaFechaIgualHoy(listaApertura.get(0).getFechaApertura())) {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AperturaNoCorrespondeDiaActual") + " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(listaApertura.get(0).getFechaApertura()));
                        //Accediendo a la ventana de no permisos            
                        Sesion.redireccionaPagina(url);
                    }
                } else {

                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioMasUnaApertura"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }

            } else {
                listaApertura = this.ejbFacadeApertura.getItemsUsuarioEstado(ActivacionUsuario.getCodigoUsuario(), 'A');
                if (!listaApertura.isEmpty()) {
                    String nombreAgencia = null;
                    if (listaApertura.size() == 1) {
                        nombreAgencia = listaApertura.get(0).getIfipAgencia().getNombre();
                    }
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCajaOtraAgencia") + " " + nombreAgencia);
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                } else {

                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioSinAperturaCaja"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }
            }

            this.limpiaVariables();
        } catch (IOException e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"LiquidacionCuentaBean", "init", CapturaError.getErrorException(ex)});
            }

            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"liquidacionCuentaBean", "init", CapturaError.getErrorException(e)});
        }
    }

    /**
     * Guarda la Liquidación de las Cuentas
     *
     * @param actionEvent
     */
    public void guardarLiquidacionCuentas(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            ////System.out.println("Guarda Movimiento");

            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaLiquidacion()) {
                ////System.out.println("Guarda Cheques");
                if (this.guardaDesgloceLiquidacion()) {
                    //this.guardaMovimientoCaja();
                }
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LiquidacionGuardada");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
                this.init();

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
                    new Object[]{"liquidacionCuentaBean", "guardarLiquidacionCuentas", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * GUARDA EL MOVIMIENTO DE LA CUENTA, AFECTANDO A LA CUENTA DEL SOCIO Y SUS
     * MOVIMIENTOS
     *
     * @return
     * @throws UnknownHostException
     */
    private boolean guardaLiquidacion() throws UnknownHostException {

        this.fechaLiquidacion = new java.sql.Timestamp(new Date().getTime());

        llamaSP.setNombreSP("mks_ahorros.pkm_liquidacion_cuenta.p_guarda_liquidacion");
        llamaSP.setNumeroParametros(14);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.socioSel.getSocioPK().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.socioSel.getSocioPK().getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_liquidado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_liquidacion", this.fechaLiquidacion});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaLiquidacion});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", Long.parseLong("1")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_interes_liquidado", this.totalIntereses.setScale(2, BigDecimal.ROUND_HALF_EVEN)});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_saldo_liquidado", this.totalSaldo});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_liquidacion", this.totalLiquidacion});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_certificados_liquidado", this.totalCertificados});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pv_retira_socio", this.retiraSocio});
       
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.codigoLiquidacion = Long.parseLong(llamaSP.getListResultado().get(0).toString());
        }
        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * GUARDA EL DESGLOCE DE LOS CHEQUES REGISTRADOS EN EL MOVIMIENTO.
     *
     * @return
     */
    private boolean guardaDesgloceLiquidacion() throws UnknownHostException {

        llamaSP.setNombreSP("mks_ahorros.pkm_liquidacion_cuenta_des.p_guarda_desgloce");
        llamaSP.setNumeroParametros(18);
        // Insertando degloce del movimiento 
        for (Cuenta c : itemsCuentasSeleccionado) {

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_liquidacion", this.codigoLiquidacion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", c.getCodigoSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", c.getSocio().getCodigoPersona().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", c.getProductoIfip().getProductoIfipPK().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", c.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", c.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_liquidacion", this.fechaLiquidacion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", c.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", ObtieneInformacionCliente.obtenerDireccionIP()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_saldo", c.getSaldoDisponible()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_interes", c.getProvisionAcumulada().setScale(2, RoundingMode.HALF_UP)});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", 1L});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});
            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoLiquidacion", this.codigoLiquidacion);

        nombreReporte = "liquidacionCuenta";

        getGeneraReporte().exporta("/financiero/ahorros/liquidacionCuenta/reporte/", nombreReporte,
                nombreReporte + "-" + String.valueOf(codigoLiquidacion) + ".pdf",
                "PDF", externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }

    public void calculaTotal(SelectEvent event) {
        Cuenta cuenta = (Cuenta) event.getObject();
        if (String.valueOf(cuenta.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()).equals("1")) {
            this.itemsCuentasSeleccionado = this.itemsCuentas;
        }
        this.deshabilitaBotonLiquidar();
    }

    public void calculaTotal(UnselectEvent unselectEvent) {
        this.deshabilitaBotonLiquidar();

    }

    public void calculaTotalSeleccionTodas(ToggleSelectEvent event) {
        this.deshabilitaBotonLiquidar();

    }

    public void deshabilitaBotonLiquidar() {

        totalLiquidacion = new BigDecimal("0.00");
        totalSaldo = new BigDecimal("0.00");
        totalIntereses = new BigDecimal("0.00000");
        totalCertificados = new BigDecimal("0.00");
        this.retiraSocio = "N";

        this.setDeshabilitaBotonLiquidar(false);
        if (this.itemsCuentasSeleccionado.isEmpty()) {
            this.setDeshabilitaBotonLiquidar(true);
        } else {

            this.deshabilitaBotonLiquidar = false;
            for (Cuenta c : itemsCuentasSeleccionado) {
                totalSaldo = totalSaldo.add(c.getSaldoDisponible());
                totalIntereses = totalIntereses.add(c.getProvisionAcumulada());

                if (c.getSaldoBloqueado().compareTo(new BigDecimal("0.00")) > 0) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LiquidacionSaldoBloqueado") + ": " + c.getNumero());
                    totalLiquidacion = new BigDecimal("0.00");
                    totalSaldo = new BigDecimal("0.00");
                    totalIntereses = new BigDecimal("0.00000");
                    totalCertificados = new BigDecimal("0.00");
                    this.setDeshabilitaBotonLiquidar(true);
                    break;

                } else if (String.valueOf(c.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()).equals("1")) {
                    MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("LiquidacionRetiroSocio"));
                    totalCertificados = c.getSaldoDisponible();
                    this.retiraSocio = "S";
                }

                if (this.deshabilitaBotonLiquidar) {
                    break;
                }
            }
            totalLiquidacion = totalSaldo.add(totalIntereses);
        }
    }

    private void limpiaVariables() {
        this.nombreSocioBusqueda = null;
        this.itemsSocio = null;
        this.itemsCuentas = null;
        this.itemsCuentasSeleccionado = null;
        this.nombreSocio = null;
        this.codigoSocio = null;

        totalLiquidacion = new BigDecimal("0.00");
        totalSaldo = new BigDecimal("0.00");
        totalIntereses = new BigDecimal("0.00000");
        totalCertificados = new BigDecimal("0.00");
        this.retiraSocio = "N";
        this.setDeshabilitaBotonLiquidar(true);
    }

    public void preparaBusquedaSocios(ActionEvent actionEvent) throws IOException {
        this.limpiaVariables();
    }

    public void obtieneSocios() {

        try {
            if (Validaciones.cumpleRequerimientoCampo(this.nombreSocioBusqueda, 6)) {
                this.setSocioSel(null);
                this.setSelected(null);
                //System.out.println("nombreSocioBusqueda " + nombreSocioBusqueda);
                this.setItemsSocio(this.ejbFacadeSocio.getItemsSocio(this.nombreSocioBusqueda, "N", ActivacionUsuario.getCodigoIfip()));
                if (this.itemsSocio.isEmpty()) {
                    this.setItemsCuentas(null);
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsSocio(null);
                this.setSocioSel(null);
                this.setSelected(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    public void obtieneSocio() {
        this.itemsCuentas = null;
        this.itemsCuentasSeleccionado = null;
        this.nombreSocio = null;
        this.socioSel = this.ejbFacadeSocio.find(new SocioPK(this.getCodigoSocio(), ActivacionUsuario.getCodigoIfip()));
        this.obtieneCuentasSocios();

    }

    public void seleccionaSocio(ActionEvent actionEvent) {
        this.codigoSocio = this.socioSel.getSocioPK().getCodigo();
        this.obtieneCuentasSocios();
    }

    public void obtieneCuentasSocios() {
        try {

            if (this.socioSel != null) {
                if (String.valueOf(this.socioSel.getCodigoEstadoSocio().getIndicaActivo()).equals("S")) {
                    this.nombreSocio = this.socioSel.getCodigoPersona().getNombreCompleto();
                    this.setItemsCuentas(this.ejbFacadeCuenta.getItemsPuedeReactivar(this.socioSel.getSocioPK().getCodigo(), this.socioSel.getSocioPK().getCodigoIfip(), 'S'));
                    ////System.out.println("Cuentas "+this.itemsCuentas);
                    //System.out.println("Cuentas " + this.socioSel + " " + this.itemsCuentas);
                    if (this.itemsCuentas.isEmpty()) {
                        this.setSelected(null);
                    }

                } else {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioInactivo") + "{" + this.socioSel.getCodigoPersona().getNombreCompleto() + "," + this.socioSel.getCodigoEstadoSocio().getNombre() + "}");
                    this.itemsCuentas = null;
                }
            } else {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioNoExiste"));
                this.itemsCuentas = null;
                this.nombreSocio = null;
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtieneCuentasSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * @return the nombreSocio
     */
    public String getNombreSocio() {
        return nombreSocio;
    }

    /**
     * @param nombreSocio the nombreSocio to set
     */
    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    /**
     * @return the socioSel
     */
    public Socio getSocioSel() {
        return socioSel;
    }

    /**
     * @param socioSel the socioSel to set
     */
    public void setSocioSel(Socio socioSel) {
        this.socioSel = socioSel;
    }

    /**
     * @return the itemsSocio
     */
    public List<Socio> getItemsSocio() {
        return itemsSocio;
    }

    /**
     * @param itemsSocio the itemsSocio to set
     */
    public void setItemsSocio(List<Socio> itemsSocio) {
        this.itemsSocio = itemsSocio;
    }

    /**
     * @return the itemsCuentas
     */
    public List<Cuenta> getItemsCuentas() {
        return itemsCuentas;
    }

    /**
     * @param itemsCuentas the itemsCuentas to set
     */
    public void setItemsCuentas(List<Cuenta> itemsCuentas) {
        this.itemsCuentas = itemsCuentas;
    }

    /**
     * @return the codigoSocio
     */
    public Long getCodigoSocio() {
        return codigoSocio;
    }

    /**
     * @param codigoSocio the codigoSocio to set
     */
    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    /**
     * @return the nombreSocioBusqueda
     */
    public String getNombreSocioBusqueda() {
        return nombreSocioBusqueda;
    }

    /**
     * @param nombreSocioBusqueda the nombreSocioBusqueda to set
     */
    public void setNombreSocioBusqueda(String nombreSocioBusqueda) {
        this.nombreSocioBusqueda = nombreSocioBusqueda;
    }

    /**
     * @return the itemsCuentasSeleccionado
     */
    public List<Cuenta> getItemsCuentasSeleccionado() {
        return itemsCuentasSeleccionado;
    }

    /**
     * @param itemsCuentasSeleccionado the itemsCuentasSeleccionado to set
     */
    public void setItemsCuentasSeleccionado(List<Cuenta> itemsCuentasSeleccionado) {
        this.itemsCuentasSeleccionado = itemsCuentasSeleccionado;
    }

    /**
     * @return the totalLiquidacion
     */
    public BigDecimal getTotalLiquidacion() {
        return totalLiquidacion;
    }

    /**
     * @param totalLiquidacion the totalLiquidacion to set
     */
    public void setTotalLiquidacion(BigDecimal totalLiquidacion) {
        this.totalLiquidacion = totalLiquidacion;
    }

    /**
     * @return the deshabilitaBotonLiquidar
     */
    public boolean isDeshabilitaBotonLiquidar() {
        return deshabilitaBotonLiquidar;
    }

    /**
     * @param deshabilitaBotonLiquidar the deshabilitaBotonLiquidar to set
     */
    public void setDeshabilitaBotonLiquidar(boolean deshabilitaBotonLiquidar) {
        this.deshabilitaBotonLiquidar = deshabilitaBotonLiquidar;
    }

    /**
     * @return the totalIntereses
     */
    public BigDecimal getTotalIntereses() {
        return totalIntereses;
    }

    /**
     * @param totalIntereses the totalIntereses to set
     */
    public void setTotalIntereses(BigDecimal totalIntereses) {
        this.totalIntereses = totalIntereses;
    }

    /**
     * @return the totalSaldo
     */
    public BigDecimal getTotalSaldo() {
        return totalSaldo;
    }

    /**
     * @param totalSaldo the totalSaldo to set
     */
    public void setTotalSaldo(BigDecimal totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    /**
     * @return the totalCertificados
     */
    public BigDecimal getTotalCertificados() {
        return totalCertificados;
    }

    /**
     * @param totalCertificados the totalCertificados to set
     */
    public void setTotalCertificados(BigDecimal totalCertificados) {
        this.totalCertificados = totalCertificados;
    }

    /**
     * @return the codigoLiquidacion
     */
    public Long getCodigoLiquidacion() {
        return codigoLiquidacion;
    }

    /**
     * @param codigoLiquidacion the codigoLiquidacion to set
     */
    public void setCodigoLiquidacion(Long codigoLiquidacion) {
        this.codigoLiquidacion = codigoLiquidacion;
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

}
