package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.cajas.EstadoChequeDepositado;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoCheque;
import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDep;
import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDet;
import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDetPK;
import ec.renafipse.mks.modelo.cajas.TransferenciaChequeDesgloce;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.UsuarioGuiaDeposito;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.cajas.ChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.EstadoChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.TransferenciaChequeDepDetFacade;
import ec.renafipse.mks.negocio.cajas.TransferenciaChequeDepFacade;
import ec.renafipse.mks.negocio.cajas.TransferenciaChequeDesgloceFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioGuiaDepositoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "transferenciaChequeDepController")
@ViewScoped
public class TransferenciaChequeDepController extends AbstractController<TransferenciaChequeDep> implements Serializable {

    @EJB
    private TransferenciaChequeDepFacade ejbFacade;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private ChequeDepositadoFacade ejbFacadeChequeDepositado;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private UsuarioGuiaDepositoFacade ejbFacadeUsuarioGuiaDeposito;

    @EJB
    private EstadoChequeDepositadoFacade ejbFacadeEstadoChequeDepositado;

    @EJB
    private TransferenciaChequeDepDetFacade ejbFacadeTransferenciaChequeDepDet;

    @EJB
    private TransferenciaChequeDepFacade ejbFacadeTransferenciaChequeDep;

    @EJB
    private TransferenciaChequeDesgloceFacade ejbFacadeTransferenciaChequeDes;

    private LlamaSP llamaSP;

    private boolean deshabilitaBotonTransferir;
    private int numeroCheques;
    private BigDecimal total;
    private String numeroCheque;
    private BigDecimal valorCheque;
    private String numeroCuenta;
    private String msg;
    private Long codigoTransferencia;
    private Timestamp fechaTransferencia;

    private Moneda moneda;
    private Moneda codMoneda;
    private Long codApertura;
    private Long codigoIngresoEgreso;
    private UsuarioGuiaDeposito usuarioDestino;
    private Computador computador;
    private GuiaDepositoCheque guiaDepositoCheque;
    private IfipAgencia ifipAgencia;
    private TransferenciaChequeDep transferenciaChequeDep;
    private TransferenciaChequeDepDet transferenciaChequeDepDet;
    private TransferenciaChequeDesgloce transferenciaChequeDesgloce;
    private ChequeDepositado chequeDepositado;
    private EstadoChequeDepositado estadoChequeDepositado;
    private GeneraReporte generaReporte;
    private TransferenciaChequeDepDetPK transferenciaChequeDepDetPK;

    private List<TransferenciaChequeDep> itemsTransferenciaChequeDep;
    private List<ChequeDepositado> itemsChequeDepositado;
    private List<TransferenciaChequeDepDet> itemsTransferenciaChequeDepDet;
    private List<TransferenciaChequeDesgloce> itemsTransferenciaChequeDes;

    public TransferenciaChequeDepController() {
        super(TransferenciaChequeDep.class);
    }

    @PostConstruct
    public void init() {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
//            if (ActivacionUsuario.getCodigoComputador() == null) {
//                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
//                //Accediendo a la ventana de no permisos            
//                Sesion.redireccionaPagina(url);
//            }

            List<Apertura> listaApertura = this.ejbFacadeApertura.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
            // Verifico que si tiene aperturado la caja
            if (!listaApertura.isEmpty()) {
                if (listaApertura.size() == 1) {
                    // if (Validaciones.validaFechaIgualHoy(listaApertura.get(0).getFechaApertura())) {
                    //Obtengo usuario responsable de cheques depositados
                    List<UsuarioGuiaDeposito> listaUsuarioGuiaDeposito = this.ejbFacadeUsuarioGuiaDeposito.getItemsUsuarioGuiaDepositoIfipAgenciaEliminado(ActivacionUsuario.getCodigoIfipAgencia(), 'N');
                    //VErifico que si existan usuarios responsables de hacer la transferencia
                    if (!listaUsuarioGuiaDeposito.isEmpty()) {
                        if (listaUsuarioGuiaDeposito.size() == 1) {
                            
                            //Instancion el proceso de llama Store Procedure
                            llamaSP = new LlamaSP();
                            
                            this.setDeshabilitaBotonTransferir(true);
                            setCodApertura(listaApertura.get(0).getCodigo());
                            transferenciaChequeDesgloce = new TransferenciaChequeDesgloce();
                            setUsuarioDestino(listaUsuarioGuiaDeposito.get(0));
                            EstadoChequeDepositado estadoCheque = this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("1"));
                            this.codMoneda = this.ejbFacadeMoneda.find(Long.parseLong("1"));
                            this.setTotal(new BigDecimal("0.00"));
                            this.numeroCheques = 0;
                            this.itemsChequeDepositado = this.ejbFacadeChequeDepositado.getItemsChequeDepositadoATransferir(getCodMoneda().getCodigo(), estadoCheque, ActivacionUsuario.getCodigoUsuario());
                            if (itemsChequeDepositado.isEmpty()) {
                                this.deshabilitaBotonTransferir = true;
                            } else {
                                this.deshabilitaBotonTransferir = false;
                                totalCheques();
                                numeroCheques();
                            }
                            this.estadoChequeDepositado = this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("2"));
                        }
                        if (listaUsuarioGuiaDeposito.size() > 1) {
                            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeunResponsableTranferirCheque"));
                            Sesion.redireccionaPagina(url);

                        }
                    } else {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteUsuarioResponsableTransferirCheque"));
                        Sesion.redireccionaPagina(url);
                    }

                    /*  } else {
                     ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AperturaNoCorrespondeDiaActual") + " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(listaApertura.get(0).getFechaApertura()));
                     //Accediendo a la ventana de no permisos            
                     Sesion.redireccionaPagina(url);
                     }*/
                } else {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioMasUnaApertura"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }

            } else {

                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioSinAperturaCaja"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
        } catch (Exception e) {
            //System.out.println("no llegooo");
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"transferenciaChequeDepController", "init", CapturaError.getErrorException(ex)});
            }
        }

    }

    //*****************INICIO DE LA LOGICA*****************************//
    /**
     * Calcula el total del valor de los cheques
     */
    public void totalCheques() {
        if (this.getItemsChequeDepositado() != null) {
            for (int i = 0; i < this.getItemsChequeDepositado().size(); i++) {
                total = this.getItemsChequeDepositado().get(i).getValor().add(total);
                this.setTotal(total);
                //System.out.println("total: " + getTotal());
            }
        } else {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteChequesATransferir");
            MuestraMensaje.addError(msg);
        }
    }

    public void numeroCheques() {
        if (this.getItemsChequeDepositado() != null) {
            for (int i = 0; i < this.getItemsChequeDepositado().size(); i++) {
                numeroCheques++;
                this.setNumeroCheques(numeroCheques);
            }
        } else {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenCheques");
            MuestraMensaje.addError(msg);
        }
    }

    public void transfiereChequeDep(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaTransferenciaChequeDep()) {
                this.guardaTransferenciaChequeDes();
                this.setDeshabilitaBotonTransferir(true);

            }

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TransferenciaChequeGrabado");
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
                    new Object[]{"transferenciaChequeDepController", "transfiereChequeDep", CapturaError.getErrorException(ex)});
        }

    }

    /**
     *
     * @return @throws UnknownHostException
     */
    public boolean guardaTransferenciaChequeDep() throws UnknownHostException {
        this.fechaTransferencia = new java.sql.Timestamp(new Date().getTime());

        llamaSP.setNombreSP("mks_cajas.pkm_transferencia_cheque.p_guarda_transferencia_cheque");
        llamaSP.setNumeroParametros(16);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_destino", this.usuarioDestino.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", this.fechaTransferencia});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaTransferencia});

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.codApertura});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.codMoneda.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheques", this.numeroCheques});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_cheques", this.total});

        //Ingreso y Egreso de caja
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso_egreso", this.fechaTransferencia});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_ingreso_egreso ", this.total});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "POR TRANSFERENCIA DE CHEQUES"});
        
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_ing_egr", Types.NUMERIC});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_transferencia", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.codigoIngresoEgreso = Long.parseLong(llamaSP.getListResultado().get(0).toString());
            //System.out.println("codigoIngEgr: " + codigoIngresoEgreso);
            this.codigoTransferencia = Long.parseLong(llamaSP.getListResultado().get(1).toString());
            //System.out.println("codigoTrans: " + codigoTransferencia);
        }
        return llamaSP.isEjecucionCorrecta();
    }

    /**
     *
     * @return
     */
    public boolean guardaTransferenciaChequeDes() {
        llamaSP.setNombreSP("mks_cajas.pkm_transferencia_cheque_des.p_guarda_transferencia_des");
        llamaSP.setNumeroParametros(7);
        this.fechaTransferencia = new java.sql.Timestamp(new Date().getTime());

        for (ChequeDepositado cd : this.itemsChequeDepositado) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cheque", cd.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.codMoneda.getCodigo()});
            //System.out.println("p_codigo_moneda: " + this.codMoneda.getCodigo());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_transferencia", this.codigoTransferencia});
            //System.out.println("p_codigo_transferencia: " + this.codigoTransferencia);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_posee", this.usuarioDestino.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", this.estadoChequeDepositado.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado ", this.fechaTransferencia});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_estado", ActivacionUsuario.getCodigoUsuario()});

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
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeTransferenciaCheque(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.deshabilitaBotonTransferir = true;
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoTransferencia", this.codigoTransferencia);

        nombreReporte = "transferenciaCheques";

        getGeneraReporte().exporta("/financiero/cajas/transferenciaCheque/reporte/", nombreReporte,
                nombreReporte + String.valueOf(codigoTransferencia) + ".pdf",
                "PDF", externalContext, facesContext);
    }
    //FIN LOGICA
    /**
     * @return the itemsTransferenciaChequeDep
     */
    public List<TransferenciaChequeDep> getItemsTransferenciaChequeDep() {
        return itemsTransferenciaChequeDep;
    }

    /**
     * @param itemsTransferenciaChequeDep the itemsTransferenciaChequeDep to set
     */
    public void setItemsTransferenciaChequeDep(List<TransferenciaChequeDep> itemsTransferenciaChequeDep) {
        this.itemsTransferenciaChequeDep = itemsTransferenciaChequeDep;
    }

    /**
     * @return the deshabilitaBotonTransferir
     */
    public boolean isDeshabilitaBotonTransferir() {
        return deshabilitaBotonTransferir;
    }

    /**
     * @param deshabilitaBotonTransferir the deshabilitaBotonTransferir to set
     */
    public void setDeshabilitaBotonTransferir(boolean deshabilitaBotonTransferir) {
        this.deshabilitaBotonTransferir = deshabilitaBotonTransferir;
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
     * @return the itemsChequeDepositado
     */
    public List<ChequeDepositado> getItemsChequeDepositado() {
        return itemsChequeDepositado;
    }

    /**
     * @param itemsChequeDepositado the itemsChequeDepositado to set
     */
    public void setItemsChequeDepositado(List<ChequeDepositado> itemsChequeDepositado) {
        this.itemsChequeDepositado = itemsChequeDepositado;
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
     * @return the numeroCheque
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
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
     * @return the fechaTransferencia
     */
    public Timestamp getFechaTransferencia() {
        return fechaTransferencia;
    }

    /**
     * @param fechaTransferencia the fechaTransferencia to set
     */
    public void setFechaTransferencia(Timestamp fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
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
     * @return the computador
     */
    public Computador getComputador() {
        return computador;
    }

    /**
     * @param computador the computador to set
     */
    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    /**
     * @return the transferenciaChequeDep
     */
    public TransferenciaChequeDep getTransferenciaChequeDep() {
        return transferenciaChequeDep;
    }

    /**
     * @param transferenciaChequeDep the transferenciaChequeDep to set
     */
    public void setTransferenciaChequeDep(TransferenciaChequeDep transferenciaChequeDep) {
        this.transferenciaChequeDep = transferenciaChequeDep;
    }

    /**
     * @return the transferenciaChequeDepDet
     */
    public TransferenciaChequeDepDet getTransferenciaChequeDepDet() {
        return transferenciaChequeDepDet;
    }

    /**
     * @param transferenciaChequeDepDet the transferenciaChequeDepDet to set
     */
    public void setTransferenciaChequeDepDet(TransferenciaChequeDepDet transferenciaChequeDepDet) {
        this.transferenciaChequeDepDet = transferenciaChequeDepDet;
    }

    /**
     * @return the transferenciaChequeDesgloce
     */
    public TransferenciaChequeDesgloce getTransferenciaChequeDesgloce() {
        return transferenciaChequeDesgloce;
    }

    /**
     * @param transferenciaChequeDesgloce the transferenciaChequeDesgloce to set
     */
    public void setTransferenciaChequeDesgloce(TransferenciaChequeDesgloce transferenciaChequeDesgloce) {
        this.transferenciaChequeDesgloce = transferenciaChequeDesgloce;
    }

    /**
     * @return the usuarioDestino
     */
    public UsuarioGuiaDeposito getUsuarioDestino() {
        return usuarioDestino;
    }

    /**
     * @param usuarioDestino the usuarioDestino to set
     */
    public void setUsuarioDestino(UsuarioGuiaDeposito usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    /**
     * @return the codMoneda
     */
    public Moneda getCodMoneda() {
        return codMoneda;
    }

    /**
     * @param codMoneda the codMoneda to set
     */
    public void setCodMoneda(Moneda codMoneda) {
        this.codMoneda = codMoneda;
    }

    /**
     * @return the estadoChequeDepositado
     */
    public EstadoChequeDepositado getEstadoChequeDepositado() {
        return estadoChequeDepositado;
    }

    /**
     * @param estadoChequeDepositado the estadoChequeDepositado to set
     */
    public void setEstadoChequeDepositado(EstadoChequeDepositado estadoChequeDepositado) {
        this.estadoChequeDepositado = estadoChequeDepositado;
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
     * @return the itemsTransferenciaChequeDepDet
     */
    public List<TransferenciaChequeDepDet> getItemsTransferenciaChequeDepDet() {
        return itemsTransferenciaChequeDepDet;
    }

    /**
     * @param itemsTransferenciaChequeDepDet the itemsTransferenciaChequeDepDet
     * to set
     */
    public void setItemsTransferenciaChequeDepDet(List<TransferenciaChequeDepDet> itemsTransferenciaChequeDepDet) {
        this.itemsTransferenciaChequeDepDet = itemsTransferenciaChequeDepDet;
    }

    /**
     * @return the itemsTransferenciaChequeDes
     */
    public List<TransferenciaChequeDesgloce> getItemsTransferenciaChequeDes() {
        return itemsTransferenciaChequeDes;
    }

    /**
     * @param itemsTransferenciaChequeDes the itemsTransferenciaChequeDes to set
     */
    public void setItemsTransferenciaChequeDes(List<TransferenciaChequeDesgloce> itemsTransferenciaChequeDes) {
        this.itemsTransferenciaChequeDes = itemsTransferenciaChequeDes;
    }

    /**
     * @return the chequeDepositado
     */
    public ChequeDepositado getChequeDepositado() {
        return chequeDepositado;
    }

    /**
     * @param chequeDepositado the chequeDepositado to set
     */
    public void setChequeDepositado(ChequeDepositado chequeDepositado) {
        this.chequeDepositado = chequeDepositado;
    }

    /**
     * @return the codigoTransferencia
     */
    public Long getCodigoTransferencia() {
        return codigoTransferencia;
    }

    /**
     * @param codigoTransferencia the codigoTransferencia to set
     */
    public void setCodigoTransferencia(Long codigoTransferencia) {
        this.codigoTransferencia = codigoTransferencia;
    }

    /**
     * @return the codApertura
     */
    public Long getCodApertura() {
        return codApertura;
    }

    /**
     * @param codApertura the codApertura to set
     */
    public void setCodApertura(Long codApertura) {
        this.codApertura = codApertura;
    }

    /**
     * @return the codigoIngresoEgreso
     */
    public Long getCodigoIngresoEgreso() {
        return codigoIngresoEgreso;
    }

    /**
     * @param codigoIngresoEgreso the codigoIngresoEgreso to set
     */
    public void setCodigoIngresoEgreso(Long codigoIngresoEgreso) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
    }

}
