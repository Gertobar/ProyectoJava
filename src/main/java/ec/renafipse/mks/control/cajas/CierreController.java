package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.AperturaDetalle;
import ec.renafipse.mks.modelo.cajas.Cierre;
import ec.renafipse.mks.modelo.cajas.CierreDesgloceEfectivo;
import ec.renafipse.mks.modelo.cajas.CierreDesgloceEfectivoPK;
import ec.renafipse.mks.modelo.cajas.CierreDetalle;
import ec.renafipse.mks.modelo.cajas.FraccionMoneda;
import ec.renafipse.mks.modelo.cajas.SaldosCaja;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfip;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfipPK;
import ec.renafipse.mks.negocio.cajas.AperturaCajaFondeoFacade;
import ec.renafipse.mks.negocio.cajas.AperturaDetalleFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.cajas.CierreFacade;
import ec.renafipse.mks.negocio.cajas.FraccionMonedaFacade;
import ec.renafipse.mks.negocio.cajas.SaldosCajaFacade;
import ec.renafipse.mks.negocio.ifips.ParametroServidorIfipFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
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
import org.primefaces.context.RequestContext;

@ManagedBean(name = "cierreController")
@ViewScoped
public class CierreController extends AbstractController<Cierre> implements Serializable {

    @EJB
    private CierreFacade ejbFacade;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private AperturaDetalleFacade ejbFacadeAperturaDetalle;

    @EJB
    private SaldosCajaFacade ejbFacadeSaldosCaja;

    @EJB
    private FraccionMonedaFacade ejbFacadeFraccionMoneda;

    @EJB
    private AperturaCajaFondeoFacade ejbFacadeAperturaFondeoCaja;

    @EJB
    private ParametroServidorIfipFacade ejbFacadeParametroServidorIfip;

    private LlamaSP llamaSP;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private boolean deshabilitaBotonGuardar;
    private boolean tieneSaldosChequeCaja;
    private String mensajeCabecera;
    private String mensajeConfirmacion;
    private Long codigoApertura;
    private String msg;
    private Timestamp fechaCierre;

    private Apertura apertura;
    private CierreDesgloceEfectivo cierreDesgloceEfectivo;
    private CierreDetalle cierreDetalle;
    private GeneraReporte generaReporte;

    private List<AperturaDetalle> itemsAperturaDetalle;
    private List<CierreDesgloceEfectivo> itemsCierreDesgloceEfectivo;
    private List<SaldosCaja> itemsSaldosCaja;
    private String descripcion;

    public CierreController() {
        super(Cierre.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            this.setDeshabilitaBotonGuardar(true);
            this.setTieneSaldosChequeCaja(false);
            this.mensajeCabecera = null;
            this.mensajeConfirmacion = null;

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

                    this.codigoApertura = listaApertura.get(0).getCodigo();
                    this.apertura = listaApertura.get(0);
                    setItemsSaldosCaja(ejbFacadeSaldosCaja.getItemsCodigoApertura(codigoApertura));
                    if (getItemsSaldosCaja().isEmpty()) {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenSaldosCaja"));
                        //Accediendo a la ventana de no permisos            
                        Sesion.redireccionaPagina(url);
                    } else {
                        this.deshabilitaBotonGuardar = false;

                        for (SaldosCaja sc : getItemsSaldosCaja()) {
                            if (sc.getSaldoCheques().compareTo(new BigDecimal("0.00")) > 0) {
                                this.setTieneSaldosChequeCaja(true);
                                this.deshabilitaBotonGuardar = true;
                                this.mensajeCabecera = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CajaTieneCheques");
                            }
                        }

                        //Instancion el proceso de llama Store Procedure
                        llamaSP = new LlamaSP();

                        this.setItemsAperturaDetalle(this.ejbFacadeAperturaDetalle.getItemsCodigoApertura(codigoApertura));

                        //Preparando el detalle del Cierre, en caso de que se tenga mas monedas se debera realizar el cirrre por monedas.
                        this.cierreDetalle = new CierreDetalle(this.itemsAperturaDetalle.get(0).getAperturaDetallePK().getCodigoApertura(),
                                this.itemsAperturaDetalle.get(0).getAperturaDetallePK().getCodigoMoneda());
                        this.cierreDetalle.setValorEfectivo(new BigDecimal("0.00"));
                        this.cierreDetalle.setValorCheques(new BigDecimal("0.00"));

                        this.cargaItemsDesgloceEfectivo();

                        if (this.mensajeCabecera == null) {
                            this.calculaDiferencia();
                        }
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
        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"cierreController", "init", CapturaError.getErrorException(ex)});
            }
        }

    }

    // ---------------------------------------------------------------------------
    // -- GUARDA EL CIERRE DE LA CAJA.
    public void guardaCierre(ActionEvent actionEvent) {
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

            if (this.guardaCierreCabecera()) {
                if (this.guardaDetalleCierre()) {
                    if (this.actualizaEstadoApertura()) {
                        if (this.actualizaEstadoFondeo()) {
                            this.guardaCierreDesgloceEfectivo();
                        }

                    }
                    //  }*/
                }
            }

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CierreGuardado");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("ConfirmacionDialogo.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
                this.deshabilitaBotonGuardar = true;

                //context.execute("MovimientoBovedaFor.update()");
            } else {
                context.execute("procesandoDlg.hide()");
                context.execute("ConfirmacionDialogo.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cierreControles", "guardaCierre", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * GUARDA LA CABECERA DEL CIERRE DE LA CAJA
     *
     * @return
     * @throws UnknownHostException
     */
    private boolean guardaCierreCabecera() throws UnknownHostException {
        this.fechaCierre = new java.sql.Timestamp(new Date().getTime());        
        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_cajas.pkg_cierre.p_inserta");
        llamaSP.setNumeroParametros(5);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.codigoApertura});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_cierre", fechaCierre});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", fechaCierre});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuadrada", (String.valueOf(this.getCierreDetalle().getTipoDiferencia()).equals("C") ? "S" : "N")});
        
        //Permite almacenar la descripcion
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_descripcion", getDescripcion()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * ACTUALIZA EL ESTADO DE LA APERTURA DE LA CAJA.
     *
     * @return
     * @throws UnknownHostException
     */
    private boolean actualizaEstadoApertura() throws UnknownHostException {

        this.fechaCierre = new java.sql.Timestamp(new Date().getTime());
        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_cajas.pkg_apertura.p_actualiza_estado");
        llamaSP.setNumeroParametros(2);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.codigoApertura});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "C"});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * ACTUALIZA EL ESTADO DE LA APERTURA
     *
     * @return
     */
    public boolean actualizaEstadoFondeo() {
        // Guardando el Fondeo de Caja para la Apertura
        llamaSP.setNombreSP("mks_cajas.pkg_fondeo_caja.p_actualiza_estado");
        llamaSP.setNumeroParametros(2);
        Long codigoFondeo = this.ejbFacadeAperturaFondeoCaja.find(this.codigoApertura).getCodigoFondeo().getCodigo();
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", codigoFondeo});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "C"});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * GUARDA EL DETALLE DEL CIERRE.
     *
     * @return
     * @throws UnknownHostException
     */
    private boolean guardaDetalleCierre() throws UnknownHostException {

        this.fechaCierre = new java.sql.Timestamp(new Date().getTime());
        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_cajas.pkm_cierre_detalle.p_guarda_cierre_detalle");
        llamaSP.setNumeroParametros(10);
        //System.out.println(" this.getCierreDetalle().getValorEfectivo() "+this.getCierreDetalle().getValorEfectivo());

        //System.out.println(" this.getCierreDetalle().getValorCheques() "+this.getCierreDetalle().getValorCheques());
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.getCierreDetalle().getCierreDetallePK().getCodigoApertura()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.getCierreDetalle().getCierreDetallePK().getCodigoMoneda()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaCierre});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_efectivo", this.getCierreDetalle().getValorEfectivo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_cheques", this.getCierreDetalle().getValorCheques()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_tipo_diferencia", Types.VARCHAR});

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();

    }

    /**
     * GUARDA EL DESGLOCE DEL EFECTIVO DEL CIERRE DE CAJA
     *
     * @return
     */
    private boolean guardaCierreDesgloceEfectivo() {

        llamaSP.setNombreSP("mks_cajas.pkg_cierre_desgloce_efectivo.p_inserta");
        llamaSP.setNumeroParametros(6);
        //System.out.println("itemsCierreDesgloceEfectivo " + itemsCierreDesgloceEfectivo);
        // Insertando degloce del movimiento 
        for (CierreDesgloceEfectivo cde : this.itemsCierreDesgloceEfectivo) {

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_fraccion", cde.getCierreDesgloceEfectivoPK().getCodigoTipoFraccion()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", cde.getCierreDesgloceEfectivoPK().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", cde.getCierreDesgloceEfectivoPK().getCodigoApertura()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cantidad", cde.getCantidad()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", cde.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", cde.getTotal()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * IMPRIME LA APERTURA DE LA CAJA
     *
     * @param actionEvent
     */
    public void imprimeCierre(ActionEvent actionEvent) {
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoApertura", this.codigoApertura);

        nombreReporte = "cierreCaja";
        getGeneraReporte().exporta("/financiero/cajas/cierre/reporte/", nombreReporte,
                nombreReporte + this.codigoApertura + ".pdf",
                "PDF", externalContext, facesContext);
        //System.out.println("Cierre imprimio");
    }
    // -- FIN DE GUARDAR EL CIERRE
    // ----------------------------------------------------------------------------

    /**
     * CARGA LOS ITEMS/FRACCIONES DEL DESFLOCE DEL EFECTIVO.
     */
    public void cargaItemsDesgloceEfectivo() {
        // -- Creando el desgloce de efectivo
        List<FraccionMoneda> listaFraccionMoneda = this.ejbFacadeFraccionMoneda.getItemsEliminadoTipoFranccionMoneda(Long.parseLong("1"), 'N');
        this.itemsCierreDesgloceEfectivo = new ArrayList<CierreDesgloceEfectivo>();
        for (FraccionMoneda fm : listaFraccionMoneda) {
            this.cierreDesgloceEfectivo = new CierreDesgloceEfectivo(new CierreDesgloceEfectivoPK());
            this.cierreDesgloceEfectivo.getCierreDesgloceEfectivoPK().setCodigoApertura(this.getCierreDetalle().getCierreDetallePK().getCodigoApertura());
            this.cierreDesgloceEfectivo.getCierreDesgloceEfectivoPK().setCodigoMoneda(this.getCierreDetalle().getCierreDetallePK().getCodigoMoneda());
            this.cierreDesgloceEfectivo.getCierreDesgloceEfectivoPK().setCodigoTipoFraccion(fm.getTipoFraccionMoneda().getCodigo());
            this.cierreDesgloceEfectivo.setFraccionMoneda(fm);
            this.cierreDesgloceEfectivo.setCantidad(Long.parseLong("0"));
            this.cierreDesgloceEfectivo.setValor(fm.getTipoFraccionMoneda().getValor());
            this.cierreDesgloceEfectivo.setTotal(new BigDecimal("0.00"));

            this.getItemsCierreDesgloceEfectivo().add(cierreDesgloceEfectivo);
        }
    }

    /**
     * Realiza el calculo cuando la cantidad es modificada en el desgloce del
     * efectivo.
     */
    public void cantidadModificada() {

        BigDecimal totalCierreEfectivo = new BigDecimal("0.00");
        BigDecimal valor;
        Long cantidad;
        BigDecimal total;

        for (int c = 0; c < this.itemsCierreDesgloceEfectivo.size(); c++) {
            cantidad = this.itemsCierreDesgloceEfectivo.get(c).getCantidad();
            valor = this.itemsCierreDesgloceEfectivo.get(c).getValor();
            total = valor.multiply(new BigDecimal(cantidad.toString()));
            this.itemsCierreDesgloceEfectivo.get(c).setTotal(total);
            totalCierreEfectivo = totalCierreEfectivo.add(total);
        }

        this.cierreDetalle.setValorEfectivo(totalCierreEfectivo);
        this.calculaDiferencia();

    }

    private void calculaDiferencia() {
        BigDecimal saldoCajaEfectivo = new BigDecimal("0.00");
        DecimalFormat formatoValores = new DecimalFormat("###,###,##0.00");
        //BigDecimal saldoCajaCheques = new BigDecimal("0.00");
        for (SaldosCaja sc : itemsSaldosCaja) {
            if (sc.getSaldosCajaPK().getCodigoApertura() == this.getCierreDetalle().getCierreDetallePK().getCodigoApertura()
                    && sc.getSaldosCajaPK().getCodigoMoneda() == this.getCierreDetalle().getCierreDetallePK().getCodigoMoneda()) {
                saldoCajaEfectivo = sc.getSaldoEfectivo();
                //      saldoCajaCheques = sc.getSaldoCheques();
                break;
            }
        }

        boolean mostrarDiferencia = false;
        // Ruta para crear el archivo para enviar a imprimir en las libretas
        ParametroServidorIfip psi = this.ejbFacadeParametroServidorIfip.find(new ParametroServidorIfipPK((long) 7, ActivacionUsuario.getCodigoIfip()));
        if (psi != null) {
            if (psi.getValor().trim().equals("S")) {
                mostrarDiferencia = true;
            }
        }
        this.mensajeCabecera = null;
        this.mensajeConfirmacion = null;
        this.getCierreDetalle().setValorDiferencia(new BigDecimal("0.00"));
        this.getCierreDetalle().setTipoDiferencia('C');
        BigDecimal diferencia;
        ////System.err.println("Calcula Diferencia");
        deshabilitaBotonGuardar = false;
        if (this.getCierreDetalle().getValorEfectivo().compareTo(saldoCajaEfectivo) > 0) {
            diferencia = this.getCierreDetalle().getValorEfectivo().subtract(saldoCajaEfectivo);
            this.mensajeCabecera = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CajaConSobrante") + ((mostrarDiferencia) ? "\n DIFERENCIA: " + formatoValores.format(diferencia.doubleValue()) : "");
            this.getCierreDetalle().setTipoDiferencia('S');
            deshabilitaBotonGuardar = true;
        } else if (this.getCierreDetalle().getValorEfectivo().compareTo(saldoCajaEfectivo) < 0) {
            diferencia = saldoCajaEfectivo.subtract(this.getCierreDetalle().getValorEfectivo());
            this.mensajeCabecera = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CajaConFaltante") + ((mostrarDiferencia) ? "\n DIFERENCIA: " + formatoValores.format(diferencia.doubleValue()) : "");
            this.getCierreDetalle().setTipoDiferencia('F');
            deshabilitaBotonGuardar = true;
        }
        if (this.getCierreDetalle().getValorEfectivo().compareTo(new BigDecimal("0.00")) == 0) {
            this.mensajeConfirmacion = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CierreCajaCero");
        }
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
     * @return the mensajeCabecera
     */
    public String getMensajeCabecera() {
        return mensajeCabecera;
    }

    /**
     * @param mensajeCabecera the mensajeCabecera to set
     */
    public void setMensajeCabecera(String mensajeCabecera) {
        this.mensajeCabecera = mensajeCabecera;
    }

    /**
     * @return the tieneSaldosChequeCaja
     */
    public boolean isTieneSaldosChequeCaja() {
        return tieneSaldosChequeCaja;
    }

    /**
     * @param tieneSaldosChequeCaja the tieneSaldosChequeCaja to set
     */
    public void setTieneSaldosChequeCaja(boolean tieneSaldosChequeCaja) {
        this.tieneSaldosChequeCaja = tieneSaldosChequeCaja;
    }

    /**
     * @return the itemsAperturaDetalle
     */
    public List<AperturaDetalle> getItemsAperturaDetalle() {
        return itemsAperturaDetalle;
    }

    /**
     * @param itemsAperturaDetalle the itemsAperturaDetalle to set
     */
    public void setItemsAperturaDetalle(List<AperturaDetalle> itemsAperturaDetalle) {
        this.itemsAperturaDetalle = itemsAperturaDetalle;
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
     * @return the itemsCierreDesgloceEfectivo
     */
    public List<CierreDesgloceEfectivo> getItemsCierreDesgloceEfectivo() {
        return itemsCierreDesgloceEfectivo;
    }

    /**
     * @param itemsCierreDesgloceEfectivo the itemsCierreDesgloceEfectivo to set
     */
    public void setItemsCierreDesgloceEfectivo(List<CierreDesgloceEfectivo> itemsCierreDesgloceEfectivo) {
        this.itemsCierreDesgloceEfectivo = itemsCierreDesgloceEfectivo;
    }

    /**
     * @return the cierreDetalle
     */
    public CierreDetalle getCierreDetalle() {
        return cierreDetalle;
    }

    /**
     * @param cierreDetalle the cierreDetalle to set
     */
    public void setCierreDetalle(CierreDetalle cierreDetalle) {
        this.cierreDetalle = cierreDetalle;
    }

    /**
     * @return the itemsSaldosCaja
     */
    public List<SaldosCaja> getItemsSaldosCaja() {
        return itemsSaldosCaja;
    }

    /**
     * @param itemsSaldosCaja the itemsSaldosCaja to set
     */
    public void setItemsSaldosCaja(List<SaldosCaja> itemsSaldosCaja) {
        this.itemsSaldosCaja = itemsSaldosCaja;
    }

    /**
     * @return the mensajeConfirmacion
     */
    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    /**
     * @param mensajeConfirmacion the mensajeConfirmacion to set
     */
    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
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
     *
     * @return devuelve la descripcion 
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
