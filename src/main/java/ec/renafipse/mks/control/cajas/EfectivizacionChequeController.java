package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.modelo.cajas.EfectivizacionCheque;
import ec.renafipse.mks.modelo.cajas.EstadoChequeDepositado;
import ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDes;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.UsuarioEfeProChe;
import ec.renafipse.mks.negocio.cajas.ChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.EfectivizacionChequeFacade;
import ec.renafipse.mks.negocio.cajas.EstadoChequeDepositadoFacade;
import ec.renafipse.mks.negocio.cajas.GuiaDepositoChequeDesFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "efectivizacionChequeController")
@ViewScoped
public class EfectivizacionChequeController extends AbstractController<EfectivizacionCheque> implements Serializable {

    @EJB
    private EfectivizacionChequeFacade ejbFacade;

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

    private LlamaSP llamaSP;

    private int numeroCheques;
    private BigDecimal totalCheques;
    private BigDecimal totalEfectivizacion;
    private Date fecha;
    private Timestamp fechaEfectivizacion;
    private Long codigoEfectivizacion;
    private String msg;

    private boolean deshabilitaBotonEfectifizar;
    private Moneda ubicaMoneda;
    private UsuarioEfeProChe usuarioResponsable;
    private EstadoChequeDepositado estadoCheque;
    private EstadoChequeDepositado codEstadoCheque;
    private ChequeDepositado chequeDepositadoSel;
    private GeneraReporte generaReporte;

    private List<Moneda> listaMoneda;
    private List<ChequeDepositado> listaChequeEnGuia;
    private List<UsuarioEfeProChe> listaUsuarioEfeProChe;
    private List<ChequeDepositado> listaChequeDepositadoSel;

    public EfectivizacionChequeController() {
        super(EfectivizacionCheque.class);

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            /*if (ActivacionUsuario.getCodigoComputador() == null) {
             ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
             //Accediendo a la ventana de no permisos            
             Sesion.redireccionaPagina(url);
             }*/
            //Obtengo usuario responsable de cheques depositados
            listaUsuarioEfeProChe = this.ejbFacadeUsuarioEfeProChe.getItemsUsuarioResponsableEfectizarEliminado(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'N');
            //Verifico si existe el usuario responsable de emitir la guia de desposito
            if (!listaUsuarioEfeProChe.isEmpty()) {
                if (listaUsuarioEfeProChe.size() == 1) {
                    
                    //Instancion el proceso de llama Store Procedure
                    llamaSP = new LlamaSP();
            
                    setUsuarioResponsable(listaUsuarioEfeProChe.get(0));
                    this.setTotalCheques(new BigDecimal("0.00"));
                    this.setTotalEfectivizacion(new BigDecimal("0.00"));
                    this.setNumeroCheques(0);
                    this.deshabilitaBotonEfectifizar = true;
                    this.setFecha(new Date());
//                    this.fechaEfectivizacion = new java.sql.Timestamp(new Date().getTime());
                    this.setListaMoneda(this.ejbFacadeMoneda.getItemsIfipMonedas(ActivacionUsuario.getCodigoIfip()));
                    this.listaChequeDepositadoSel  = new ArrayList<ChequeDepositado>();
                    this.listaChequeDepositadoSel.clear();
                    this.setEstadoCheque(this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("3")));                    
                }
                if (listaUsuarioEfeProChe.size() > 1) {
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MasDeunResponsableParaEfectivizacion"));
                    Sesion.redireccionaPagina(url);
                }
            } else {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoAsignadoParaEfectivizar"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
        } catch (Exception e) {
            //System.out.println("error en efectizacion de cheques");
            e.printStackTrace();
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"efectivizacionChequeController", "init", CapturaError.getErrorException(ex)});
            }
        }

    }

    //INICIO LOGICA
    public void cambiaMoneda() {
        try {
            this.listaChequeEnGuia = this.ejbFacadeGuiaDepositoChequeDes.getItemsChequesEmitidosGuia(this.ubicaMoneda.getCodigo(), this.estadoCheque.getCodigo(), fecha,ActivacionUsuario.getCodigoIfipAgencia());
            if (this.listaChequeEnGuia.isEmpty()) {
                this.deshabilitaBotonEfectifizar = true;
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenChequesAEfectivizar"));
                MuestraMensaje.addAdvertencia(getMsg());
            } else {
                totalChequesAEfetivizar();
                this.codEstadoCheque = this.ejbFacadeEstadoChequeDepositado.find(Long.parseLong("4"));
                this.deshabilitaBotonEfectifizar = false;
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"efectivizacionChequeController", "cambiaMoneda", CapturaError.getErrorException(ex)});
        }
    }

    public void emiteEfectivizacion(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            numeroCheques();
            if (this.guardaEfectivizacionoCheque()) {
                this.guardaEfectivizacionDes();
                this.deshabilitaBotonEfectifizar = true;

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequesEfectivizadosSatisfactoriamente"));
                MuestraMensaje.addInformacion(getMsg());
                this.listaChequeEnGuia = this.ejbFacadeGuiaDepositoChequeDes.getItemsChequesEmitidosGuia(this.ubicaMoneda.getCodigo(), this.estadoCheque.getCodigo(), fecha,ActivacionUsuario.getCodigoIfipAgencia());
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
                    new Object[]{"guiaDepositoChequeController", "emiteGuiaDeposito", CapturaError.getErrorException(ex)});
        }
    }

    public boolean guardaEfectivizacionoCheque() throws UnknownHostException {
        this.setFechaEfectivizacion(new java.sql.Timestamp(new Date().getTime()));

        if (this.ubicaMoneda != null) {
            llamaSP.setNombreSP("mks_cajas.pkm_efectivizacion_cheque.p_guarda_efectivizacion_cheque");
            llamaSP.setNumeroParametros(10);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", this.fechaEfectivizacion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaEfectivizacion});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "POR EFECTIVIZACION DE CHEQUE"});

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.ubicaMoneda.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheques", this.numeroCheques});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_cheques", this.totalEfectivizacion});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo_efec", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
                this.codigoEfectivizacion = Long.parseLong(llamaSP.getListResultado().get(0).toString());
                //System.out.println("codigoEfectivizacion: " + this.codigoEfectivizacion);
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    public boolean guardaEfectivizacionDes() {
        llamaSP.setNombreSP("mks_cajas.pkm_efectivizacion_cheque_des.p_guarda_efectivizacion_des");
        llamaSP.setNumeroParametros(9);

        if (this.codEstadoCheque != null) {

            for (ChequeDepositado cd : this.getListaChequeDepositadoSel()) {
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cheque", cd.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.ubicaMoneda.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_efe_che_det", this.codigoEfectivizacion});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_posee", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", this.codEstadoCheque.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.fechaEfectivizacion});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_estado", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", cd.getMovimientoCuenta().getCodigoCuenta().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_cheque", cd.getValor()});

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

    /**
     * Calcula el total del valor de los cheques
     */
    public void totalChequesAEfetivizar() {
        if (this.listaChequeEnGuia != null) {
            this.setTotalCheques(new BigDecimal("0.00"));
            for (int i = 0; i < this.listaChequeEnGuia.size(); i++) {
                totalCheques = this.listaChequeEnGuia.get(i).getValor().add(totalCheques);
                this.setTotalCheques(totalCheques);
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenCheques");
            MuestraMensaje.addError(msg);
        }
    }

    public void calculaTotalSeleccionados(SelectEvent event) {
        this.totalEfectivizacionCheques();
    }

    public void calculaTotalNoSeleccionados(UnselectEvent event) {
        this.totalEfectivizacionCheques();
    }

    public void calculaTotalSeleccionTodas(ToggleSelectEvent event) {
        this.totalEfectivizacionCheques();

    }

    /**
     * Calcula el total del valor de los cheques
     */
    public void totalEfectivizacionCheques() {
        if (this.getListaChequeDepositadoSel() != null) {
            this.setTotalEfectivizacion(new BigDecimal("0.00"));
            for (int i = 0; i < this.getListaChequeDepositadoSel().size(); i++) {
                totalEfectivizacion = this.getListaChequeDepositadoSel().get(i).getValor().add(totalEfectivizacion);
                this.setTotalEfectivizacion(totalEfectivizacion);
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenCheques");
            MuestraMensaje.addError(msg);
        }
    }

    public void numeroCheques() {
        if (this.getListaChequeDepositadoSel() != null) {
            for (int i = 0; i < this.getListaChequeDepositadoSel().size(); i++) {
                numeroCheques++;
                this.setNumeroCheques(numeroCheques);
            }
        } else {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistenCheques");
            MuestraMensaje.addError(msg);
        }
    }

    public void imprimeEfectivizacionCheque(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.deshabilitaBotonEfectifizar = true;
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoEfectivizacion", this.codigoEfectivizacion);

        nombreReporte = "efectivizacionCheque";

        getGeneraReporte().exporta("/financiero/cajas/efectivizacionCheque/reporte/", nombreReporte,
                nombreReporte + String.valueOf(codigoEfectivizacion) + ".pdf",
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
     * @return the codigoEfectivizacion
     */
    public Long getCodigoEfectivizacion() {
        return codigoEfectivizacion;
    }

    /**
     * @param codigoEfectivizacion the codigoEfectivizacion to set
     */
    public void setCodigoEfectivizacion(Long codigoEfectivizacion) {
        this.codigoEfectivizacion = codigoEfectivizacion;
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
     * @return the deshabilitaBotonEfectifizar
     */
    public boolean isDeshabilitaBotonEfectifizar() {
        return deshabilitaBotonEfectifizar;
    }

    /**
     * @param deshabilitaBotonEfectifizar the deshabilitaBotonEfectifizar to set
     */
    public void setDeshabilitaBotonEfectifizar(boolean deshabilitaBotonEfectifizar) {
        this.deshabilitaBotonEfectifizar = deshabilitaBotonEfectifizar;
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
     * @return the listaChequeDepositadoSel
     */
    public List<ChequeDepositado> getListaChequeDepositadoSel() {
        return listaChequeDepositadoSel;
    }

    /**
     * @param listaChequeDepositadoSel the listaChequeDepositadoSel to set
     */
    public void setListaChequeDepositadoSel(List<ChequeDepositado> listaChequeDepositadoSel) {
        this.listaChequeDepositadoSel = listaChequeDepositadoSel;
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
     * @return the totalEfectivizacion
     */
    public BigDecimal getTotalEfectivizacion() {
        return totalEfectivizacion;
    }

    /**
     * @param totalEfectivizacion the totalEfectivizacion to set
     */
    public void setTotalEfectivizacion(BigDecimal totalEfectivizacion) {
        this.totalEfectivizacion = totalEfectivizacion;
    }

    /**
     * @return the listaChequeEnGuia
     */
    public List<ChequeDepositado> getListaChequeEnGuia() {
        return listaChequeEnGuia;
    }

    /**
     * @param listaChequeEnGuia the listaChequeEnGuia to set
     */
    public void setListaChequeEnGuia(List<ChequeDepositado> listaChequeEnGuia) {
        this.listaChequeEnGuia = listaChequeEnGuia;
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

}
