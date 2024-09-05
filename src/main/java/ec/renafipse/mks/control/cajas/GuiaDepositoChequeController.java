package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.cajas.EstadoChequeDepositado;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoCheque;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDet;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDetPK;
import ec.renafipse.mks.modelo.cajas.PeriodoEfeCheEntFin;
import ec.renafipse.mks.modelo.cajas.PeriodoEfectivizacionCheque;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.ifips.Boveda;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaCuentaEntFin;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.seguridades.ContrasenaUsuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioEfeProChe;
import ec.renafipse.mks.modelo.seguridades.UsuarioGuiaDeposito;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.cajas.ChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.EstadoChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.GuiaDepositoChequeFacade;
import ec.renafipse.mks.negocio.cajas.PeriodoEfeCheEntFinFacade;
import ec.renafipse.mks.negocio.cajas.TransferenciaChequeDepFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.BovedaDineroFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaCuentaEntFinFacade;
import ec.renafipse.mks.negocio.ifips.IfipCuentaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioEfeProCheFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioGuiaDepositoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

@ManagedBean(name = "guiaDepositoChequeController")
@ViewScoped
public class GuiaDepositoChequeController extends AbstractController<GuiaDepositoCheque> implements Serializable {

    @EJB
    private GuiaDepositoChequeFacade ejbFacade;

    @EJB
    private ChequeDepositadoFacade ejbFacadeChequeDepositado;

    @EJB
    private EstadoChequeDepositadoFacade ejbFacadeEstadoChequeDepositado;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private UsuarioGuiaDepositoFacade ejbFacadeUsuarioGuiaDeposito;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private TransferenciaChequeDepFacade ejbFacadeTransferenciaChequeDep;

    @EJB
    private IfipAgenciaCuentaEntFinFacade ejbFacadeIfipAgenciaCuentaEntFin;

    @EJB
    private PeriodoEfeCheEntFinFacade ejbFacadePeriodoEfeCheEntFin;

    @EJB
    private UsuarioEfeProCheFacade ejbFacadeUsuarioEfeProChe;

    @EJB
    private IfipCuentaEntidadFinancieraFacade ejbFacadeIfipCuentaEntFin;
    
    @EJB
    private BovedaDineroFacade ejbFacadeBovedaDinero;

    private LlamaSP llamaSP;

    private int numeroCheques;
    private BigDecimal total;
    private Timestamp fecha;
    private Timestamp fechaGuiaDeposito;
    private Long codigoGuiaDeposito;
    private String msg;
    private Date fechaGuia;

    private Timestamp fechaEfectivizacion;

    private boolean deshabilitaBotonEmitirGuia;
    private boolean deshabilitaComboEntFin;
    private Long equivalenteDias;
    private Long equivalenteMeses;
    private Long equivalenteAnios;
    private Moneda ubicaMoneda;
    private IfipAgenciaCuentaEntFin ubicaCuentaEntFin;
    private UsuarioEfeProChe usuarioResponsable;
    private EstadoChequeDepositado estadoCheque;
    private EstadoChequeDepositado codEstadoCheque;
    private GuiaDepositoCheque guiaDepositoCheque;
    private ChequeDepositado chequeDepositado;
    private GeneraReporte generaReporte;
    private GuiaDepositoChequeDetPK guiaDepositoChequeDetPk;
    private GuiaDepositoChequeDet guiaDepositoChequeDet;
    private PeriodoEfeCheEntFin periodoEfeCheEntFin;
    private PeriodoEfectivizacionCheque periodoEfectivizacionCheque;
    private Periodicidad periodicidad;
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera;
    private Boveda boveda;
    
    private List<Boveda> itemsBoveda;
    private List<Moneda> listaMoneda;
    private List<IfipAgenciaCuentaEntFin> listIfipAgenciaCuentaEntFin;
    private List<ChequeDepositado> listaChequesTransferidos;
    private List<GuiaDepositoCheque> listaGuiaDepositoCheque;
    private List<IfipCuentaEntidadFinanciera> itemsIfipCuentaEntidadFinanciera;
    private List<ChequeDepositado> itemsChequesGuiaSeleccionados;

    public GuiaDepositoChequeController() {
        super(GuiaDepositoCheque.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            //Obtengo usuario responsable de cheques depositados
            List<UsuarioGuiaDeposito> listaUsuarioGuiaDeposito = this.ejbFacadeUsuarioGuiaDeposito.getItemsUsuarioResponsableGuiaEliminado(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
            //Verifico si existe el usuario responsable de emitir la guia de desposito
            if (!listaUsuarioGuiaDeposito.isEmpty()) {
                if (listaUsuarioGuiaDeposito.size() == 1) {

                    List<UsuarioEfeProChe> listaUsuarioResponsableEfec = this.ejbFacadeUsuarioEfeProChe.getItemsUsuarioResponsableEfectizar(ActivacionUsuario.getCodigoIfipAgencia(), 'N');
                    if (!listaUsuarioResponsableEfec.isEmpty()) {
                        if (listaUsuarioResponsableEfec.size() == 1) {
                            setUsuarioResponsable(listaUsuarioResponsableEfec.get(0));
                            
                            //Instancion el proceso de llama Store Procedure
                            llamaSP = new LlamaSP();
            
                            this.setTotal(new BigDecimal("0.00"));
                            this.setNumeroCheques(0);
                            this.deshabilitaBotonEmitirGuia = true;
                            this.deshabilitaComboEntFin = true;
                            this.setFechaGuia(new Date());
                            this.fechaGuiaDeposito = new java.sql.Timestamp(new Date().getTime());
                            this.listaMoneda = this.ejbFacadeMoneda.findAll();
                            this.estadoCheque = this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("2"));
                            
                        }
                        if (listaUsuarioResponsableEfec.size() > 1) {
                            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnUsuarioResponsableReceptarChequesEmitidos"));
                            Sesion.redireccionaPagina(url);
                        }
                    } else {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteUsuarioResponsableReceptarCheques"));
                        Sesion.redireccionaPagina(url);
                    }

                }
                if (listaUsuarioGuiaDeposito.size() > 1) {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeunResponsableEmitirGuia"));
                    Sesion.redireccionaPagina(url);
                }
            } else {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoAsignadoParaRealizarGuia"));
                Sesion.redireccionaPagina(url);
            }
        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"guiaDepositoChequeController", "init", CapturaError.getErrorException(ex)});
            }
        }
    }

    public void calculaTotales(){
        this.setTotal(BigDecimal.ZERO);
        this.setNumeroCheques(0);
        totalCheques();
        numeroCheques();
    }
    
    public void cambiaMoneda() {
        try {
            this.listaChequesTransferidos = this.ejbFacadeChequeDepositado.getItemsChequesTransferidosGuia(this.ubicaMoneda.getCodigo(), this.estadoCheque.getCodigo(), ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoIfip());
            if (this.listaChequesTransferidos.isEmpty()) {
                this.deshabilitaBotonEmitirGuia = true;
                this.deshabilitaComboEntFin = true;
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenChequesAEmitir"));
                MuestraMensaje.addAdvertencia(getMsg());
            } else {
                //totalCheques();
                //numeroCheques();
                this.boveda = null;
                this.codEstadoCheque = this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("3"));
                this.deshabilitaBotonEmitirGuia = false;
                this.deshabilitaComboEntFin = false;
                //this.setListIfipAgenciaCuentaEntFin(this.ejbFacadeIfipAgenciaCuentaEntFin.getItemsIfipAgenciaCuentaEntFinEliminado(ActivacionUsuario.getCodigoIfipAgencia(), 'N'));
                this.setItemsIfipCuentaEntidadFinanciera(ejbFacadeIfipCuentaEntFin.getItemsCuentaFinancieraIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoIfip(), 'N'));
                this.setItemsBoveda(this.ejbFacadeBovedaDinero.getItemsBovedaMonedaFondeoCaja(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoUsuario(), ubicaMoneda.getCodigo(), BigDecimal.ZERO, 'N'));
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"guiaDepositoChequeController", "cambiaMoneda", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Calcula el total del valor de cada cheque transferido
     */
    public void totalCheques() {
        if (this.itemsChequesGuiaSeleccionados!= null) {
            for (int i = 0; i < this.itemsChequesGuiaSeleccionados.size(); i++) {
                this.total = this.itemsChequesGuiaSeleccionados.get(i).getValor().add(total);
                this.setTotal(total);
            }
        } else {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenChequesAEmitir");
            MuestraMensaje.addError(msg);
        }
    }

    public void emiteGuiaDeposito(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaGuiaDepositoCheque()) {
                this.guardaGuiaDepositoDes();
                this.deshabilitaBotonEmitirGuia = true;
                this.deshabilitaComboEntFin = true;

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("GuiaDepositoChequeGrabado"));
                MuestraMensaje.addInformacion(getMsg());
                this.ubicaCuentaEntFin = null;
                this.ubicaMoneda = null;
                this.listaMoneda.clear();
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
                //this.init();
                this.listaChequesTransferidos = null;

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
                    new Object[]{"guiaDepositoChequeController", "emiteGuiaDeposito", CapturaError.getErrorException(ex)});
        }

    }

    public boolean guardaGuiaDepositoCheque() throws UnknownHostException {
        this.setFechaGuiaDeposito(new java.sql.Timestamp(new Date().getTime()));

        if (this.ubicaMoneda != null) {
          //  //System.out.println("this.this.ifipCuentaEntidadFinanciera.getCodigo() " + this.ifipCuentaEntidadFinanciera.getCodigo()+" this.numeroCheques "+this.numeroCheques+" this.ubicaMoneda.getCodigo() "+this.ubicaMoneda.getCodigo());
          //  //System.out.println("ActivacionUsuario.getCodigoComputador() " + ActivacionUsuario.getCodigoComputador()+" this.fechaGuiaDeposito "+this.fechaGuiaDeposito);
          //  //System.out.println("this.total "+this.total);
            llamaSP.setNombreSP("mks_cajas.pkm_guia_deposito_cheque.p_guarda_guia_deposito_cheque");
            llamaSP.setNumeroParametros(15);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_boveda", boveda.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", this.ifipCuentaEntidadFinanciera.getCodigo()});            
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", this.fechaGuiaDeposito});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaGuiaDeposito});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", String.valueOf("E")});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_deposito", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones_deposito", null});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.ubicaMoneda.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheques", this.numeroCheques});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_cheques", this.total});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_guia", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();
            
            //System.out.println("paso Invoca "+llamaSP.isEjecucionCorrecta());
            if (llamaSP.isEjecucionCorrecta()) {
                this.codigoGuiaDeposito = Long.parseLong(llamaSP.getListResultado().get(0).toString());
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public Date obtieneFechaEfectivizacion() {
        Date envioFechaEfectivizacion = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss S");
        Calendar calendar = Calendar.getInstance();

        List<Periodicidad> listaPeriodicidadEntFinIfip = this.ejbFacadePeriodoEfeCheEntFin.getItemsPeriodicidadEntFinIfipEliminado(ifipCuentaEntidadFinanciera.getEntidadFinanciera().getCodigo(), ActivacionUsuario.getCodigoIfip(), 'N');

        if (!listaPeriodicidadEntFinIfip.isEmpty()) {

            if (listaPeriodicidadEntFinIfip.size() == 1) {

                for (Periodicidad per : listaPeriodicidadEntFinIfip) {
                    this.equivalenteDias = (BigDecimal.valueOf(per.getEquivalenciaDias()).longValueExact());

                    calendar.setTime(fechaGuiaDeposito);
                    calendar.add(Calendar.DATE, equivalenteDias.intValue());
                    String fechaEfec = formato.format(calendar.getTime());
                    try {
                        envioFechaEfectivizacion = formato.parse(fechaEfec);

                    } catch (ParseException ex) {
                        Logger.getLogger(GuiaDepositoCheque.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeUnParametroPeriodicidad"));
                MuestraMensaje.addInformacion(getMsg());
            }
        } else {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoParametrosPeriodicidad"));
            MuestraMensaje.addInformacion(getMsg());
        }
        return envioFechaEfectivizacion;

    }

    public boolean guardaGuiaDepositoDes() {
        this.setFechaEfectivizacion(new java.sql.Timestamp(obtieneFechaEfectivizacion().getTime()));

        llamaSP.setNombreSP("mks_cajas.pkm_guia_deposito_des.p_guarda_guia_deposito_des");
        llamaSP.setNumeroParametros(8);
        this.fechaGuiaDeposito = new java.sql.Timestamp(new Date().getTime());
        if (this.codEstadoCheque != null) {

            for (ChequeDepositado cd : this.itemsChequesGuiaSeleccionados) {
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cheque", cd.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.ubicaMoneda.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_guia_deposito", this.codigoGuiaDeposito});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_efectivizacion", this.fechaEfectivizacion});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", this.codEstadoCheque.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_posee", this.usuarioResponsable.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.fechaGuiaDeposito});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_estado", ActivacionUsuario.getCodigoUsuario()});

                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                    break;
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();

    }

    public void numeroCheques() {
        
        if (this.getListaChequesTransferidos() != null) {
            this.numeroCheques = this.getItemsChequesGuiaSeleccionados().size();
            /*for (int i = 0; i < this.getListaChequesTransferidos().size(); i++) {
                setNumeroCheques(getNumeroCheques() + 1);
                this.setNumeroCheques(getNumeroCheques());
            }*/
        } else {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenChequesAEmitir");
            MuestraMensaje.addError(msg);
        }
    }

    public void imprimeGuiaDepositoCheque(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.deshabilitaBotonEmitirGuia = true;
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoGuiaDeposito", this.codigoGuiaDeposito);

        nombreReporte = "guiaDepositoCheque";

        getGeneraReporte().exporta("/financiero/cajas/guiaDepositoCheque/reporte/", nombreReporte,
                nombreReporte + String.valueOf(codigoGuiaDeposito) + ".pdf",
                "PDF", externalContext, facesContext);
    }
    //FIN LOGICA
    /**
     * @return the deshabilitaBotonEmitirGuia
     */
    public boolean isDeshabilitaBotonEmitirGuia() {
        return deshabilitaBotonEmitirGuia;
    }

    /**
     * @param deshabilitaBotonEmitirGuia the deshabilitaBotonEmitirGuia to set
     */
    public void setDeshabilitaBotonEmitirGuia(boolean deshabilitaBotonEmitirGuia) {
        this.deshabilitaBotonEmitirGuia = deshabilitaBotonEmitirGuia;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
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
     * @return the listaChequesTransferidos
     */
    public List<ChequeDepositado> getListaChequesTransferidos() {
        return listaChequesTransferidos;
    }

    /**
     * @param listaChequesTransferidos the listaChequesTransferidos to set
     */
    public void setListaChequesTransferidos(List<ChequeDepositado> listaChequesTransferidos) {
        this.listaChequesTransferidos = listaChequesTransferidos;
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
     * @return the ubicaCuentaEntFin
     */
    public IfipAgenciaCuentaEntFin getUbicaCuentaEntFin() {
        return ubicaCuentaEntFin;
    }

    /**
     * @param ubicaCuentaEntFin the ubicaCuentaEntFin to set
     */
    public void setUbicaCuentaEntFin(IfipAgenciaCuentaEntFin ubicaCuentaEntFin) {
        this.ubicaCuentaEntFin = ubicaCuentaEntFin;
    }

    /**
     * @return the listIfipAgenciaCuentaEntFin
     */
    public List<IfipAgenciaCuentaEntFin> getListIfipAgenciaCuentaEntFin() {
        return listIfipAgenciaCuentaEntFin;
    }

    /**
     * @param listIfipAgenciaCuentaEntFin the listIfipAgenciaCuentaEntFin to set
     */
    public void setListIfipAgenciaCuentaEntFin(List<IfipAgenciaCuentaEntFin> listIfipAgenciaCuentaEntFin) {
        this.listIfipAgenciaCuentaEntFin = listIfipAgenciaCuentaEntFin;
    }

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
     * @return the fechaGuiaDeposito
     */
    public Timestamp getFechaGuiaDeposito() {
        return fechaGuiaDeposito;
    }

    /**
     * @param fechaGuiaDeposito the fechaGuiaDeposito to set
     */
    public void setFechaGuiaDeposito(Timestamp fechaGuiaDeposito) {
        this.fechaGuiaDeposito = fechaGuiaDeposito;
    }

    /**
     * @return the codigoGuiaDeposito
     */
    public Long getCodigoGuiaDeposito() {
        return codigoGuiaDeposito;
    }

    /**
     * @param codigoGuiaDeposito the codigoGuiaDeposito to set
     */
    public void setCodigoGuiaDeposito(Long codigoGuiaDeposito) {
        this.codigoGuiaDeposito = codigoGuiaDeposito;
    }

    /**
     * @return the guiaDepositoChequeDetPk
     */
    public GuiaDepositoChequeDetPK getGuiaDepositoChequeDetPk() {
        return guiaDepositoChequeDetPk;
    }

    /**
     * @param guiaDepositoChequeDetPk the guiaDepositoChequeDetPk to set
     */
    public void setGuiaDepositoChequeDetPk(GuiaDepositoChequeDetPK guiaDepositoChequeDetPk) {
        this.guiaDepositoChequeDetPk = guiaDepositoChequeDetPk;
    }

    /**
     * @return the guiaDepositoChequeDet
     */
    public GuiaDepositoChequeDet getGuiaDepositoChequeDet() {
        return guiaDepositoChequeDet;
    }

    /**
     * @param guiaDepositoChequeDet the guiaDepositoChequeDet to set
     */
    public void setGuiaDepositoChequeDet(GuiaDepositoChequeDet guiaDepositoChequeDet) {
        this.guiaDepositoChequeDet = guiaDepositoChequeDet;
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
     * @return the deshabilitaComboEntFin
     */
    public boolean isDeshabilitaComboEntFin() {
        return deshabilitaComboEntFin;
    }

    /**
     * @param deshabilitaComboEntFin the deshabilitaComboEntFin to set
     */
    public void setDeshabilitaComboEntFin(boolean deshabilitaComboEntFin) {
        this.deshabilitaComboEntFin = deshabilitaComboEntFin;
    }

    /**
     * @return the guiaDepositoCheque
     */
    public GuiaDepositoCheque getGuiaDepositoCheque() {
        return guiaDepositoCheque;
    }

    /**
     * @param guiaDepositoCheque the guiaDepositoCheque to set
     */
    public void setGuiaDepositoCheque(GuiaDepositoCheque guiaDepositoCheque) {
        this.guiaDepositoCheque = guiaDepositoCheque;
    }

    /**
     * @return the fechaGuia
     */
    public Date getFechaGuia() {
        return fechaGuia;
    }

    /**
     * @param fechaGuia the fechaGuia to set
     */
    public void setFechaGuia(Date fechaGuia) {
        this.fechaGuia = fechaGuia;
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
     * @return the equivalenteDias
     */
    public Long getEquivalenteDias() {
        return equivalenteDias;
    }

    /**
     * @param equivalenteDias the equivalenteDias to set
     */
    public void setEquivalenteDias(Long equivalenteDias) {
        this.equivalenteDias = equivalenteDias;
    }

    /**
     * @return the equivalenteMeses
     */
    public Long getEquivalenteMeses() {
        return equivalenteMeses;
    }

    /**
     * @param equivalenteMeses the equivalenteMeses to set
     */
    public void setEquivalenteMeses(Long equivalenteMeses) {
        this.equivalenteMeses = equivalenteMeses;
    }

    /**
     * @return the equivalenteAnios
     */
    public Long getEquivalenteAnios() {
        return equivalenteAnios;
    }

    /**
     * @param equivalenteAnios the equivalenteAnios to set
     */
    public void setEquivalenteAnios(Long equivalenteAnios) {
        this.equivalenteAnios = equivalenteAnios;
    }

    /**
     * @return the fechaEfectivizacion
     */
    public Timestamp getFechaEfectivizacion() {
        return fechaEfectivizacion;
    }

    /**
     * @param fechaEfectivizacion the fechaEfectivizacion to set
     */
    public void setFechaEfectivizacion(Timestamp fechaEfectivizacion) {
        this.fechaEfectivizacion = fechaEfectivizacion;
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
     * @param itemsIfipCuentaEntidadFinanciera the
     * itemsIfipCuentaEntidadFinanciera to set
     */
    public void setItemsIfipCuentaEntidadFinanciera(List<IfipCuentaEntidadFinanciera> itemsIfipCuentaEntidadFinanciera) {
        this.itemsIfipCuentaEntidadFinanciera = itemsIfipCuentaEntidadFinanciera;
    }

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
     * @return the boveda
     */
    public Boveda getBoveda() {
        return boveda;
    }

    /**
     * @param boveda the boveda to set
     */
    public void setBoveda(Boveda boveda) {
        this.boveda = boveda;
    }

    /**
     * @return the itemsChequesGuiaSeleccionados
     */
    public List<ChequeDepositado> getItemsChequesGuiaSeleccionados() {
        return itemsChequesGuiaSeleccionados;
    }

    /**
     * @param itemsChequesGuiaSeleccionados the itemsChequesGuiaSeleccionados to set
     */
    public void setItemsChequesGuiaSeleccionados(List<ChequeDepositado> itemsChequesGuiaSeleccionados) {
        this.itemsChequesGuiaSeleccionados = itemsChequesGuiaSeleccionados;
    }

}
