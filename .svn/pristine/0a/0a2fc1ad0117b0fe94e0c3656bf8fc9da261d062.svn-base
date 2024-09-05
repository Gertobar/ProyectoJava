package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.MovimientoCuentaAdicional;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.cajas.EstadoChequeDepositado;
import ec.renafipse.mks.modelo.cajas.ProtestoCheque;
import ec.renafipse.mks.modelo.cajas.TipoProtestoCheque;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.UsuarioEfeProChe;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.ahorros.MovimientoCuentaAdicionalFacade;
import ec.renafipse.mks.negocio.cajas.ChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.EstadoChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.GuiaDepositoChequeDesFacade;
import ec.renafipse.mks.negocio.cajas.ProtestoChequeFacade;
import ec.renafipse.mks.negocio.cajas.TipoProtestoChequeFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.EntidadFinancieraIfiAgeFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioEfeProCheFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
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

@ManagedBean(name = "protestoChequeController")
@ViewScoped
public class ProtestoChequeController extends AbstractController<ProtestoCheque> implements Serializable {

    @EJB
    private ProtestoChequeFacade ejbFacade;

    @EJB
    private GuiaDepositoChequeDesFacade ejbFacadeGuiaDepositoChequeDes;

    @EJB
    private ChequeDepositadoFacade ejbFacadeChequeDepositado;

    @EJB
    private EstadoChequeDepositadoFacade ejbFacadeEstadoChequeDepositado;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private UsuarioEfeProCheFacade ejbFacadeUsuarioEfeProChe;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private TipoProtestoChequeFacade ejbFacadeTipoProtestoCheque;

    @EJB
    private EntidadFinancieraIfiAgeFacade ejbFacadeEntidadFinancieraIfiAge;

    @EJB
    private MovimientoCuentaAdicionalFacade ejbFacadeMovimientoCuentaAdicional;

    private int numeroCheques;
    private BigDecimal totalCheques;
    private BigDecimal totalProtesto;
    private BigDecimal valorAProtestar;
    private BigDecimal totalMovimiento;
    private BigDecimal valorCheque;
    private BigDecimal ubicaValorCheque;
    private Date fecha;
    private Timestamp fechaProtesto;
    private Long codigoProtesto;
    private String msg;
    private String comprobante;
    private Socio socio;
    private Cuenta cuenta;
    private ChequeDepositado cheque;

    private boolean deshabilitaBotonProtesto;
    private boolean deshabilitaValorProtesto;
    private Moneda ubicaMoneda;
    private TipoProtestoCheque ubicaTipoProtesto;
    private EntidadFinanciera ubicaEntFin;
    private ChequeDepositado ubicaCheque;
    private UsuarioEfeProChe usuarioResponsable;
    private EstadoChequeDepositado estadoCheque;
    private EstadoChequeDepositado codEstadoCheque;
    private GeneraReporte generaReporte;

    private ChequeDepositado chequeDepositadoSel;

    private List<Moneda> listaMoneda;
    private List<EntidadFinanciera> listaEntiFin;
    private List<ChequeDepositado> listaChequesEnGuia;
    private List<UsuarioEfeProChe> listaUsuarioEfeProChe;
    private List<TipoProtestoCheque> listaTipoProtestoCheque;
    private List<MovimientoCuentaAdicional> listaNumeroComprobantes;
    private List<ChequeDepositado> itemsSocio;
    
    private LlamaSP llamaSP;

    public ProtestoChequeController() {
        super(ProtestoCheque.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.ubicaCheque = null;
        this.setUbicaEntFin(null);
        this.ubicaMoneda = null;
        this.ubicaTipoProtesto = null;
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            if (ActivacionUsuario.getCodigoComputador() == null) {
             ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
             //Accediendo a la ventana de no permisos  
             Sesion.redireccionaPagina(url);
             }
            //Obtengo usuario responsable de cheques depositados
            listaUsuarioEfeProChe = this.ejbFacadeUsuarioEfeProChe.getItemsUsuarioResponsableEfectizarEliminado(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
            //Verifico si existe el usuario responsable de emitir la guia de desposito
            if (!listaUsuarioEfeProChe.isEmpty()) {
                if (listaUsuarioEfeProChe.size() == 1) {
                    //Instancion el proceso de llama Store Procedure
                    llamaSP = new LlamaSP();
                    setUsuarioResponsable(listaUsuarioEfeProChe.get(0));
                    this.setValorAProtestar(new BigDecimal("0.00"));
                    this.setTotalCheques(new BigDecimal("0.00"));
                    this.setTotalProtesto(new BigDecimal("0.00"));
                    this.setValorCheque(new BigDecimal("0.00"));
                    this.setNumeroCheques(0);
                    this.comprobante = null;
                    this.deshabilitaBotonProtesto = true;
                    this.deshabilitaValorProtesto = true;
                    this.setFecha(new Date());                    
                    this.listaMoneda = this.ejbFacadeMoneda.getItemsMonedas('N');
                    this.estadoCheque = this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("3"));
                    this.listaEntiFin = this.ejbFacadeChequeDepositado.getItemsEntFinChequeProtesto(ActivacionUsuario.getCodigoIfipAgencia(), this.estadoCheque.getCodigo());
                    this.listaTipoProtestoCheque = this.ejbFacadeTipoProtestoCheque.getItemsTipoProtestoEliminado('N');
                }
                if (listaUsuarioEfeProChe.size() > 1) {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeunResponsableParaProtestar"));
                    Sesion.redireccionaPagina(url);
                }
            } else {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoAsginadoParaProtestar"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"protestoChequeController", "init", CapturaError.getErrorException(ex)});
            }
        }

    }

    //INICIO LOGICA
    public void cambiaEntFin() {
        try {
            preparaProtesto();
            this.listaChequesEnGuia = this.ejbFacadeChequeDepositado.getItemsChequesAGuiaEntFin(ActivacionUsuario.getCodigoIfipAgencia(), this.ubicaMoneda.getCodigo(), this.getUbicaEntFin().getCodigo(), this.estadoCheque.getCodigo(), fecha);
            //System.out.println("listacheques " + listaChequesEnGuia.size());
            if (this.listaChequesEnGuia.isEmpty()) {
                this.deshabilitaBotonProtesto = true;
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenChequesAProtestar"));
                MuestraMensaje.addAdvertencia(getMsg());
            } else {
                //totalProtesto();              
                this.codEstadoCheque = this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("5"));
                this.deshabilitaBotonProtesto = false;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"protestoChequeController", "cambiaEntFin", CapturaError.getErrorException(ex)});
        }
    }

    public void preparaProtesto() {
        if (this.ubicaMoneda == null) {
            this.ubicaEntFin = null;
            this.ubicaCheque = null;
            this.ubicaTipoProtesto = null;
            this.setValorAProtestar(new BigDecimal("0.00"));
            this.deshabilitaValorProtesto = true;
        } else if (this.ubicaMoneda != null && this.ubicaEntFin == null) {
            this.ubicaCheque = null;
            this.ubicaTipoProtesto = null;
            this.setValorAProtestar(new BigDecimal("0.00"));
            this.deshabilitaValorProtesto = true;
        } else if (this.ubicaMoneda != null && this.ubicaEntFin != null && this.ubicaCheque == null) {
            this.ubicaTipoProtesto = null;
            this.setValorAProtestar(new BigDecimal("0.00"));
            this.deshabilitaValorProtesto = true;
        } else if (this.ubicaMoneda != null && this.ubicaEntFin != null && this.ubicaCheque != null && this.ubicaTipoProtesto == null) {
            this.setValorAProtestar(new BigDecimal("0.00"));
            this.deshabilitaValorProtesto = false;
            this.cheque = this.ejbFacadeChequeDepositado.getValorCheque(this.ubicaCheque.getCodigo());
            if (this.cheque != null) {
                this.setUbicaValorCheque(this.cheque.getValor());
            }
        } else if (this.ubicaMoneda != null && this.ubicaEntFin != null && this.ubicaCheque != null && this.ubicaTipoProtesto != null && this.valorAProtestar == null) {
            this.deshabilitaBotonProtesto = true;
        } else if (this.ubicaMoneda != null && this.ubicaEntFin != null && this.ubicaCheque != null && this.ubicaTipoProtesto != null && this.valorAProtestar != null) {
            this.deshabilitaBotonProtesto = false;
        }

    }

    public void realizarProtesto(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            if (this.guardaProtestoCheque()) {
                this.guardaProtestoDes();
                this.deshabilitaBotonProtesto = true;
            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProtestoChequeGrabado"));
                MuestraMensaje.addInformacion(getMsg());
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
                this.init();

            } else {
                context.execute("procesandoDlg.hide()");
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
                    new Object[]{"protestoChequeController", "realizarProtesto", CapturaError.getErrorException(ex)});
        }
    }

    public boolean guardaProtestoCheque() throws UnknownHostException {
        this.setFechaProtesto(new java.sql.Timestamp(new Date().getTime()));

        if (this.ubicaMoneda != null) {
            llamaSP.setNombreSP("mks_cajas.pkm_protesto_cheque.p_guarda_protesto_cheque");
            llamaSP.setNumeroParametros(11);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            //System.out.println("p_codigo_usuario: " + ActivacionUsuario.getCodigoUsuario());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            //System.out.println("p_codigo_computador: " + ActivacionUsuario.getCodigoComputador());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            //System.out.println("p_codigo_ifip_agencia: " + ActivacionUsuario.getCodigoIfipAgencia());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", this.fechaProtesto});
            //System.out.println("p_fecha: " + this.fechaProtesto);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaProtesto});
            //System.out.println("p_fecha_sistema: " + this.fechaProtesto);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "POR PROTESTO DE CHEQUE"});

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.ubicaMoneda.getCodigo()});
            //System.out.println("p_codigo_moneda: " + this.ubicaMoneda.getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheques", 1});
            //System.out.println("p_numero_cheques: " + 1);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_cheques", this.ubicaCheque.getValor()});
            //System.out.println("p_total_cheques: " + this.ubicaCheque.getValor());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_protesto", this.valorAProtestar});
            //System.out.println("p_total_protesto: " + this.valorAProtestar);

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_pro_che", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                this.codigoProtesto = Long.parseLong(llamaSP.getListResultado().get(0).toString());
                //System.out.println("codigoProtesto: " + this.codigoProtesto);
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    /* public String generaNumeroComprobante() {
     comprobante = "PROCHE";
     long numAleatorio = 1000000L;
     long valorDado = (long) Math.floor(Math.random() * numAleatorio);
     comprobante = comprobante + valorDado;
     listaNumeroComprobantes = this.ejbFacadeMovimientoCuentaAdicional.getItemsNumeroComprobanteIfip(ActivacionUsuario.getCodigoIfip(), comprobante);
     if (listaNumeroComprobantes.isEmpty()) {
     return comprobante;
     } else {
     generaNumeroComprobante();
     }
     return comprobante;
     }*/
    public boolean guardaProtestoDes() {
        llamaSP.setNombreSP("mks_cajas.pkm_protesto_cheque_des.p_guarda_protesto_des");
        llamaSP.setNumeroParametros(27);

        if (this.codEstadoCheque != null) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cheque", this.ubicaCheque.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.ubicaMoneda.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_pro_che_det", this.codigoProtesto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_pro_che", this.ubicaTipoProtesto.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", this.ubicaCheque.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_posee", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", this.codEstadoCheque.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.fechaProtesto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_estado", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.ubicaCheque.getMovimientoCuenta().getCodigoCuenta().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_cheque", this.ubicaCheque.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.ubicaCheque.getMovimientoCuenta().getCodigoCuenta().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_movimiento", this.fechaProtesto});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_efectivo", this.valorAProtestar});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_cheques", this.ubicaCheque.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_direccion_ip", this.ubicaCheque.getMovimientoCuenta().getMovimientoCuentaAdicional().getComputador().getDireccionIp()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "PCHE "+this.codigoProtesto+". OBSS: "+this.ubicaTipoProtesto.getNombre()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona", this.ubicaCheque.getMovimientoCuenta().getCodigoCuenta().getSocio().getCodigoPersona().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_modulo", 1L});
            
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje_formulario", Types.VARCHAR});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_formulario", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"pn_genera_formulario", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public void imprimeProtestoCheque(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.deshabilitaBotonProtesto = true;
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoProtesto", this.codigoProtesto);

        nombreReporte = "protestoCheque";

        getGeneraReporte().exporta("/financiero/cajas/protestoCheque/reporte/", nombreReporte,
                nombreReporte + String.valueOf(codigoProtesto) + ".pdf",
                "PDF", externalContext, facesContext);
    }

    //FIN LOGICA
    /**
     * @return the numeroCheques
     */
    public int getNumeroCheques() {
        return numeroCheques;
    }

    /**
     * @param numeroCheques the numeroCheques to set
     */
    public void setNumeroCheques(int numeroCheques) {
        this.numeroCheques = numeroCheques;
    }

    /**
     * @return the totalCheques
     */
    public BigDecimal getTotalCheques() {
        return totalCheques;
    }

    /**
     * @param totalCheques the totalCheques to set
     */
    public void setTotalCheques(BigDecimal totalCheques) {
        this.totalCheques = totalCheques;
    }

    /**
     * @return the totalProtesto
     */
    public BigDecimal getTotalProtesto() {
        return totalProtesto;
    }

    /**
     * @param totalProtesto the totalProtesto to set
     */
    public void setTotalProtesto(BigDecimal totalProtesto) {
        this.totalProtesto = totalProtesto;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the fechaProtesto
     */
    public Timestamp getFechaProtesto() {
        return fechaProtesto;
    }

    /**
     * @param fechaProtesto the fechaProtesto to set
     */
    public void setFechaProtesto(Timestamp fechaProtesto) {
        this.fechaProtesto = fechaProtesto;
    }

    /**
     * @return the codigoProtesto
     */
    public Long getCodigoProtesto() {
        return codigoProtesto;
    }

    /**
     * @param codigoProtesto the codigoProtesto to set
     */
    public void setCodigoProtesto(Long codigoProtesto) {
        this.codigoProtesto = codigoProtesto;
    }

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
     * @return the deshabilitaBotonProtesto
     */
    public boolean isDeshabilitaBotonProtesto() {
        return deshabilitaBotonProtesto;
    }

    /**
     * @param deshabilitaBotonProtesto the deshabilitaBotonProtesto to set
     */
    public void setDeshabilitaBotonProtesto(boolean deshabilitaBotonProtesto) {
        this.deshabilitaBotonProtesto = deshabilitaBotonProtesto;
    }

    /**
     * @return the ubicaMoneda
     */
    public Moneda getUbicaMoneda() {
        return ubicaMoneda;
    }

    /**
     * @param ubicaMoneda the ubicaMoneda to set
     */
    public void setUbicaMoneda(Moneda ubicaMoneda) {
        this.ubicaMoneda = ubicaMoneda;
    }

    /**
     * @return the usuarioResponsable
     */
    public UsuarioEfeProChe getUsuarioResponsable() {
        return usuarioResponsable;
    }

    /**
     * @param usuarioResponsable the usuarioResponsable to set
     */
    public void setUsuarioResponsable(UsuarioEfeProChe usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    /**
     * @return the estadoCheque
     */
    public EstadoChequeDepositado getEstadoCheque() {
        return estadoCheque;
    }

    /**
     * @param estadoCheque the estadoCheque to set
     */
    public void setEstadoCheque(EstadoChequeDepositado estadoCheque) {
        this.estadoCheque = estadoCheque;
    }

    /**
     * @return the codEstadoCheque
     */
    public EstadoChequeDepositado getCodEstadoCheque() {
        return codEstadoCheque;
    }

    /**
     * @param codEstadoCheque the codEstadoCheque to set
     */
    public void setCodEstadoCheque(EstadoChequeDepositado codEstadoCheque) {
        this.codEstadoCheque = codEstadoCheque;
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
     * @return the listaChequesEnGuia
     */
    public List<ChequeDepositado> getListaChequesEnGuia() {
        return listaChequesEnGuia;
    }

    /**
     * @param listaChequesEnGuia the listaChequesEnGuia to set
     */
    public void setListaChequesEnGuia(List<ChequeDepositado> listaChequesEnGuia) {
        this.listaChequesEnGuia = listaChequesEnGuia;
    }

    /**
     * @return the listaUsuarioEfeProChe
     */
    public List<UsuarioEfeProChe> getListaUsuarioEfeProChe() {
        return listaUsuarioEfeProChe;
    }

    /**
     * @param listaUsuarioEfeProChe the listaUsuarioEfeProChe to set
     */
    public void setListaUsuarioEfeProChe(List<UsuarioEfeProChe> listaUsuarioEfeProChe) {
        this.listaUsuarioEfeProChe = listaUsuarioEfeProChe;
    }

    /**
     * @return the listaEntiFin
     */
    public List<EntidadFinanciera> getListaEntiFin() {
        return listaEntiFin;
    }

    /**
     * @param listaEntiFin the listaEntiFin to set
     */
    public void setListaEntiFin(List<EntidadFinanciera> listaEntiFin) {
        this.listaEntiFin = listaEntiFin;
    }

    /**
     * @return the ubicaCheque
     */
    public ChequeDepositado getUbicaCheque() {
        return ubicaCheque;
    }

    /**
     * @param ubicaCheque the ubicaCheque to set
     */
    public void setUbicaCheque(ChequeDepositado ubicaCheque) {
        this.ubicaCheque = ubicaCheque;
    }

    /**
     * @return the valorAProtestar
     */
    public BigDecimal getValorAProtestar() {
        return valorAProtestar;
    }

    /**
     * @param valorAProtestar the valorAProtestar to set
     */
    public void setValorAProtestar(BigDecimal valorAProtestar) {
        this.valorAProtestar = valorAProtestar;
    }

    /**
     * @return the ubicaTipoProtesto
     */
    public TipoProtestoCheque getUbicaTipoProtesto() {
        return ubicaTipoProtesto;
    }

    /**
     * @param ubicaTipoProtesto the ubicaTipoProtesto to set
     */
    public void setUbicaTipoProtesto(TipoProtestoCheque ubicaTipoProtesto) {
        this.ubicaTipoProtesto = ubicaTipoProtesto;
    }

    /**
     * @return the listaTipoProtestoCheque
     */
    public List<TipoProtestoCheque> getListaTipoProtestoCheque() {
        return listaTipoProtestoCheque;
    }

    /**
     * @param listaTipoProtestoCheque the listaTipoProtestoCheque to set
     */
    public void setListaTipoProtestoCheque(List<TipoProtestoCheque> listaTipoProtestoCheque) {
        this.listaTipoProtestoCheque = listaTipoProtestoCheque;
    }

    /**
     * @return the ubicaEntFin
     */
    public EntidadFinanciera getUbicaEntFin() {
        return ubicaEntFin;
    }

    /**
     * @param ubicaEntFin the ubicaEntFin to set
     */
    public void setUbicaEntFin(EntidadFinanciera ubicaEntFin) {
        this.ubicaEntFin = ubicaEntFin;
    }

    /**
     * @return the listaNumeroComprobantes
     */
    public List<MovimientoCuentaAdicional> getListaNumeroComprobantes() {
        return listaNumeroComprobantes;
    }

    /**
     * @param listaNumeroComprobantes the listaNumeroComprobantes to set
     */
    public void setListaNumeroComprobantes(List<MovimientoCuentaAdicional> listaNumeroComprobantes) {
        this.listaNumeroComprobantes = listaNumeroComprobantes;
    }

    /**
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * @return the totalMovimiento
     */
    public BigDecimal getTotalMovimiento() {
        return totalMovimiento;
    }

    /**
     * @param totalMovimiento the totalMovimiento to set
     */
    public void setTotalMovimiento(BigDecimal totalMovimiento) {
        this.totalMovimiento = totalMovimiento;
    }

    /**
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * @return the chequeDepositadoSel
     */
    public ChequeDepositado getChequeDepositadoSel() {
        return chequeDepositadoSel;
    }

    /**
     * @param chequeDepositadoSel the chequeDepositadoSel to set
     */
    public void setChequeDepositadoSel(ChequeDepositado chequeDepositadoSel) {
        this.chequeDepositadoSel = chequeDepositadoSel;
    }

    /**
     * @return the itemsSocio
     */
    public List<ChequeDepositado> getItemsSocio() {
        return itemsSocio;
    }

    /**
     * @param itemsSocio the itemsSocio to set
     */
    public void setItemsSocio(List<ChequeDepositado> itemsSocio) {
        this.itemsSocio = itemsSocio;
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
     * @return the deshabilitaValorProtesto
     */
    public boolean isDeshabilitaValorProtesto() {
        return deshabilitaValorProtesto;
    }

    /**
     * @param deshabilitaValorProtesto the deshabilitaValorProtesto to set
     */
    public void setDeshabilitaValorProtesto(boolean deshabilitaValorProtesto) {
        this.deshabilitaValorProtesto = deshabilitaValorProtesto;
    }

    /**
     * @return the valorCheque
     */
    public BigDecimal getValorCheque() {
        return valorCheque;
    }

    /**
     * @param valorCheque the valorCheque to set
     */
    public void setValorCheque(BigDecimal valorCheque) {
        this.valorCheque = valorCheque;
    }



    /**
     * @return the cheque
     */
    public ChequeDepositado getCheque() {
        return cheque;
    }

    /**
     * @param cheque the cheque to set
     */
    public void setCheque(ChequeDepositado cheque) {
        this.cheque = cheque;
    }

    /**
     * @return the ubicaValorCheque
     */
    public BigDecimal getUbicaValorCheque() {
        return ubicaValorCheque;
    }

    /**
     * @param ubicaValorCheque the ubicaValorCheque to set
     */
    public void setUbicaValorCheque(BigDecimal ubicaValorCheque) {
        this.ubicaValorCheque = ubicaValorCheque;
    }

}
