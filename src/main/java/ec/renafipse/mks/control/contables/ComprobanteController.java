package ec.renafipse.mks.control.contables;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.CierreAnual;
import ec.renafipse.mks.modelo.contables.CierreAnualPK;
import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.modelo.contables.ComprobanteDetalle;
import ec.renafipse.mks.modelo.contables.ComprobanteDetallePK;
import ec.renafipse.mks.modelo.contables.EstadoComprobante;
import ec.renafipse.mks.modelo.contables.ParametroContableIfip;
import ec.renafipse.mks.modelo.contables.ParametroContableIfipPK;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaIfip;
import ec.renafipse.mks.modelo.contables.PlanDeCuentaPK;
import ec.renafipse.mks.modelo.contables.TipoComprobante;
import ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta;
import ec.renafipse.mks.modelo.seguridades.UsuarioDesmayorizaCompro;
import ec.renafipse.mks.negocio.adquisiciones.ParametroContableIfipFacade;
import ec.renafipse.mks.negocio.contables.CierreAnualFacade;
import ec.renafipse.mks.negocio.contables.ComprobanteDetalleFacade;
import ec.renafipse.mks.negocio.contables.ComprobanteFacade;
import ec.renafipse.mks.negocio.contables.EstadoComprobanteFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import ec.renafipse.mks.negocio.contables.TipoComprobanteFacade;
import ec.renafipse.mks.negocio.contables.TipoPlanDeCuentaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioDesmayorizaComproFacade;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "comprobanteController")
@ViewScoped
public class ComprobanteController extends AbstractController<Comprobante> implements Serializable {

    @EJB
    private ComprobanteFacade ejbFacade;

    @EJB
    private PlanDeCuentaIfipFacade ejbFacadePlanDeCuentasIfip;

    @EJB
    private TipoComprobanteFacade ejbFacadeTipoComprobante;

    @EJB
    private EstadoComprobanteFacade ejbFacadeEstadoComprobante;

    @EJB
    private ComprobanteDetalleFacade ejbFacadeComprobanteDetalle;

    @EJB
    private TipoPlanDeCuentaFacade ejbFacadeTipoPlanCuenta;

    @EJB
    private UsuarioDesmayorizaComproFacade ejbFacadeDesmayorizaCompro;

    @EJB
    private ParametroContableIfipFacade ejbFacadeParametroContableIfip;

    @EJB
    private CierreAnualFacade ejbFacadeCierreAnual;

    private LlamaSP llamaSP;
    private GeneraReporte generaReporte;

    private Comprobante comprobante;
    private ComprobanteDetalle itemActual;
    private List<ComprobanteDetalle> itemsComprobanteDetalle;
    private List<ComprobanteDetalle> itemsAdicionalesDetalle;//Agregados en edicion
    private List<ComprobanteDetalle> itemsComprobanteGuardados;
    private List<PlanDeCuenta> itemsPlanDeCuenta;
    private List<Comprobante> itemsComprobante;
    private List<TipoComprobante> itemsTipoComprobante;
    private List<EstadoComprobante> itemsEstadoComprobante;

    private BigDecimal totalDebito;
    private BigDecimal totalCredito;
    private PlanDeCuenta planDeCuenta;
    private Date fechaActual;

    private char tipoFiltro;
    private Date fechaCriterio;
    private Timestamp fechaComprobante;
    private String numeroComprobanteCriterio;
    private RequestContext context;

    private PlanDeCuentaPK planCuentaPK;

    private boolean deshabilitarBotonGuardar;
    private boolean habilitarEdicion;
    private boolean mostrarFitrloFehca;
    private boolean mostrarFiltroNumComprobante;
    private boolean comprobanteGuardado;
    private boolean deshabilitaBotonDesmayoriza;
    private Date fechaMimina;
    private Date fechaMaxima;
    private String mensajeDetalle;
    private String msg;
    private String peridoCotable;

    private ComprobanteDetalle itemDetalleSel;

    /**
     * Initialize the concrete Comprobante controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     *
     */
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.deshabilitaBotonDesmayoriza = true;
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();
        this.setItemsPlanDeCuenta(this.ejbFacadePlanDeCuentasIfip.getItemsPlanCuentaVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
        this.setItemsTipoComprobante(this.ejbFacadeTipoComprobante.getItemsTipoComprobanteEliminado('N', 'S'));
        this.setItemsEstadoComprobante(this.ejbFacadeEstadoComprobante.getItemsEstadoComprobante('N'));
        this.tipoFiltro = 'N';
        this.mostrarFiltroNumComprobante = true;
        this.mostrarFitrloFehca = false;
        this.setFechaActual(new Date());
        this.setFechaMaxima(new Date());
        
        this.peridoCotable = ActivacionUsuario.getCodigoPeriodo();

        //this.setFechaMimina(this.fecha("01/01/" + ActivacionUsuario.getCodigoPeriodo()));
        if (Integer.parseInt(ActivacionUsuario.getCodigoPeriodo()) == new Date().getYear() + 1900) {
            this.setFechaMaxima(new Date());
        } else {
            try {
                this.setFechaMaxima(this.fecha("31/12/" + ActivacionUsuario.getCodigoPeriodo()));
            } catch (ParseException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"comprobanteController", "init", CapturaError.getErrorException(ex)});
            }
        }

        this.setFechaMimina(this.getFechaMaxima());
        // Colocando Fecha Mimina
        try {
            ParametroContableIfip pci = this.ejbFacadeParametroContableIfip.find(new ParametroContableIfipPK(1, ActivacionUsuario.getCodigoIfip()));
            if (pci != null) {
                this.fechaMimina = this.agregaDias(this.fechaMaxima, Long.parseLong(pci.getValor().toString()) * -1);
            }
        } catch (NumberFormatException ex) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaSoloNumeros");
            MuestraMensaje.addAdvertencia(msg);

        }

        // Verificando si puede desmayorizar
        UsuarioDesmayorizaCompro udc = ejbFacadeDesmayorizaCompro.find(ActivacionUsuario.getCodigoUsuario());
        //System.out.println(udc);
        if (udc != null) {
            //System.out.println(udc.getEliminado());
            deshabilitaBotonDesmayoriza = !(udc.getEliminado() == 'N');
            //System.out.println("deshabilitaBotonDesmayoriza " + deshabilitaBotonDesmayoriza);
        }
    }

    public ComprobanteController() {
        // Inform the Abstract parent controller of the concrete Comprobante?cap_first Entity
        super(Comprobante.class);
    }

    private void estadoPorDefecto() {
        for (EstadoComprobante estado : this.getItemsEstadoComprobante()) {
            if (estado.getSiglas() == 'M') {
                comprobante.setCodigoEstado(estado);
                return;
            }
        }

    }

    public void criterioDeBusqueda() {
        if (this.tipoFiltro == 'N') {
            this.mostrarFiltroNumComprobante = true;
            this.mostrarFitrloFehca = false;
        } else {
            this.mostrarFiltroNumComprobante = false;
            this.mostrarFitrloFehca = true;
        }
    }

    public void agregarItemDetalle() {
        ComprobanteDetallePK cdpk = new ComprobanteDetallePK(Long.parseLong("0"), this.planDeCuenta.getPlanDeCuentaPK().getCuentaContable(), this.planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan());
        itemActual.setComprobanteDetallePK(cdpk);
        itemActual.setPlanDeCuentaIfip(new PlanDeCuentaIfip(this.planDeCuenta.getPlanDeCuentaPK().getCuentaContable(), this.planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan(), ActivacionUsuario.getCodigoIfip()));
        itemActual.getPlanDeCuentaIfip().setPlanDeCuenta(planDeCuenta);
        itemActual.setLinea(Short.parseShort("0"));
        /* for (ComprobanteDetalle item : this.getItemsComprobanteDetalle()) {
         if (item.getComprobanteDetallePK().getCuentaContable().equals(itemActual.getComprobanteDetallePK().getCuentaContable()) && item.getComprobanteDetallePK().getCodigoTipoPlan() == itemActual.getComprobanteDetallePK().getCodigoTipoPlan()) {
         MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ItemComprobanteExistente"));
         return;
         }
         }*/
        if (itemActual.getValor().doubleValue() <= 0) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorMayorACero"));
            return;
        }
        try {
            this.itemsComprobanteDetalle.add((ComprobanteDetalle) BeanUtils.cloneBean(itemActual));
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        }
        //Habilita el boton guardar si los valores de debito y credito son iguales
        this.habilitarBotonGuardar();
    }

    public void agregarItemDetalleEdicion() {
        ComprobanteDetalle detalle = null;
        ComprobanteDetallePK cdpk = new ComprobanteDetallePK(this.getSelected().getCodigo(), this.planDeCuenta.getPlanDeCuentaPK().getCuentaContable(), this.planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan());
        itemActual.setComprobanteDetallePK(cdpk);
        itemActual.setPlanDeCuentaIfip(new PlanDeCuentaIfip(this.planDeCuenta.getPlanDeCuentaPK().getCuentaContable(), this.planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan(), ActivacionUsuario.getCodigoIfip()));
        itemActual.getPlanDeCuentaIfip().setPlanDeCuenta(planDeCuenta);
        itemActual.setLinea(Short.parseShort("0"));
        /*for (ComprobanteDetalle item : this.getItemsComprobanteDetalle()) {
         if (item.getComprobanteDetallePK().getCuentaContable().equals(itemActual.getComprobanteDetallePK().getCuentaContable()) && item.getComprobanteDetallePK().getCodigoTipoPlan() == itemActual.getComprobanteDetallePK().getCodigoTipoPlan()) {
         MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ItemComprobanteExistente"));
         return;
         }
         }*/
        if (itemActual.getValor().doubleValue() <= 0) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorMayorACero"));
            return;
        }
        try {
            detalle = (ComprobanteDetalle) BeanUtils.cloneBean(itemActual);
            this.itemsComprobanteDetalle.add(detalle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Habilita el boton guardar si los valores de debito y credito son iguales
        this.habilitarBotonGuardar();
    }

    public void quitarItemDetalle() {
        this.getItemsComprobanteDetalle().remove(itemDetalleSel);
        this.habilitarBotonGuardar();
    }

    public void quitarItemDetalleEdicion() {
        this.itemsComprobanteDetalle.remove(itemDetalleSel);
        this.habilitarBotonGuardar();
    }

    private boolean tipoPlanCuentaValido() {
        List<TipoPlanDeCuenta> planes = this.ejbFacadeTipoPlanCuenta.getItemsTipoPlanCuentaVigente('S');
        if (planes.size() != 1) {
            return false;
        } else {
            if (planes.get(0).getVigente() != 'S') {
                return false;
            } else {
                return true;
            }

        }
    }

    public void habilitarBotonGuardar() {
        //System.out.println("Habilito botones Gaurado"+comprobanteGuardado);
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", simbolos);
        if (!this.comprobanteGuardado) {
            totalDebito = new BigDecimal("0.00");
            totalCredito = new BigDecimal("0.00");
            for (ComprobanteDetalle item : this.getItemsComprobanteDetalle()) {
                //System.out.println(item.getPlanDeCuentaIfip().getPlanDeCuenta().getPlanDeCuentaPK().getCuentaContable()+":"+item.getValor());
                if (item.getValor().doubleValue() <= 0) {
                    mensajeDetalle = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ValorMayorACero");
                    this.setDeshabilitarBotonGuardar(true);
                    return;
                }
                try {
                    item.setValor(new BigDecimal(df.format(item.getValor().doubleValue())));
                } catch (NumberFormatException e) {
                    simbolos.setDecimalSeparator(',');
                    df = new DecimalFormat("#.00", simbolos);
                    item.setValor(new BigDecimal(df.format(item.getValor().doubleValue())));
                }
                if (item.getTipo() == 'D') {
                    totalDebito = totalDebito.add(item.getValor());
                } else {
                    totalCredito = totalCredito.add(item.getValor());
                }
                //System.out.println("Total debe: "+totalDebito);
                //System.out.println("Total credito: "+totalCredito);
            }
            //System.out.println((!totalDebito.toString().equals(totalCredito.toString())));
            deshabilitarBotonGuardar = (!totalDebito.toString().equals(totalCredito.toString()));
            if (totalDebito.toString().equals(totalCredito.toString())) {
                mensajeDetalle = "";
            } else {
                mensajeDetalle = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComprobanteNoCuadra");
            }
        }
    }

    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR EL COMPROBANTE
    public void guardarComprobante(ActionEvent actionEvent) {
        this.setFechaComprobante(new java.sql.Timestamp(new Date().getTime()));

        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaCabeceraComprobante()) {
                this.guardaDetalleComprobante();

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComprobanteGrabado");
                MuestraMensaje.addInformacion(msg);
                this.setSelected(comprobante);
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
                this.setDeshabilitarBotonGuardar(true);
                this.comprobanteGuardado = true;
                this.init();
                //this.nuevo(actionEvent);

                //context.execute("MovimientoBovedaFor.update()");
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
                    new Object[]{"comprobanteController", "guardarComprobante", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Guarda Cabecera de comprobante
     *
     * @return
     */
    private boolean guardaCabeceraComprobante() {

        this.setFechaComprobante(new java.sql.Timestamp(new Date().getTime()));
        llamaSP.setNombreSP("mks_contables.pkm_comprobante.p_registra_cabecera_comp");
        llamaSP.setNumeroParametros(14);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", ActivacionUsuario.getCodigoPeriodo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_comprobante", this.comprobante.getCodigoTipoComprobante().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia_ori", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_glosa", this.comprobante.getGlosa()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", new java.sql.Timestamp(this.comprobante.getFecha().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.getFechaComprobante()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", this.getTotalCredito()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", this.comprobante.getCodigoEstado().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", this.getFechaComprobante()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_numero_comprobante", Types.VARCHAR});
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            this.comprobante.setNumeroComprobante(llamaSP.getListResultado().get(0).toString());
            this.comprobante.setCodigo(Long.parseLong(llamaSP.getListResultado().get(1).toString()));
        }

        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Guarda detalle de comprobante
     *
     * @return
     */
    private boolean guardaDetalleComprobante() {
        //System.out.println("Guarda punto 1");
        llamaSP.setNombreSP("mks_contables.pkm_comprobante_detalle.p_registra_detalle_comp");
        llamaSP.setNumeroParametros(7);
        short linea = 1;
        // Insertando detalle de comprobante 
        //System.out.println("Guarda punto 2");
        //System.out.println(this.getItemsComprobanteDetalle());
        for (ComprobanteDetalle item : this.getItemsComprobanteDetalle()) {
            //System.out.println("Guarda detalle "+item);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            //System.out.println("p_codigo_comprobante"+ this.getComprobante().getCodigo());
            //System.out.println("p_cuenta_contable"+ item.getComprobanteDetallePK().getCuentaContable());
            //System.out.println("p_codigo_ifip"+ ActivacionUsuario.getCodigoIfip());
            //System.out.println("p_tipo"+ String.valueOf(item.getTipo()));
            //System.out.println("p_valor"+ item.getValor());
            //System.out.println("p_linea"+ linea);
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_comprobante", this.getComprobante().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuenta_contable", item.getComprobanteDetallePK().getCuentaContable()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_plan", item.getComprobanteDetallePK().getCodigoTipoPlan()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", String.valueOf(item.getTipo())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", item.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_linea", linea});
            linea++;

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            //System.out.println("ANTES registrar detalle");
            llamaSP.invocaSP();
            //System.out.println("DESPUES registrar detalle");

            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
            //System.out.println("Guardo detalle");
        }
        return llamaSP.isEjecucionCorrecta();
    }

    // --------------------------------------------------------------------------
    // -- PROCESO PARA EDITAR EL COMPROBANTE
    public void editarComprobante(ActionEvent actionEvent) {
        this.habilitarBotonGuardar();
        if (deshabilitarBotonGuardar) {
            return;
        }
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            this.setComprobante(this.getSelected());

            if (this.editaCabeceraComprobante()) {
                if (this.eliminaDetalleComprobante()) {
                    this.guardaDetalleComprobante();
                }

            }
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComprobanteEditado");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
                this.setDeshabilitarBotonGuardar(true);
                this.init();
                this.nuevo(actionEvent);
                this.comprobanteGuardado = true;

            } else {
                //System.out.println("FALLO");
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
                    new Object[]{"comprobanteController", "editarComprobante", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * Edita glosa y total de Comprobante
     *
     * @return
     */
    private boolean editaCabeceraComprobante() {

        this.setFechaComprobante(new java.sql.Timestamp(new Date().getTime()));
        llamaSP.setNombreSP("mks_contables.pkm_comprobante.p_actualiza_cabecera_comp");
        llamaSP.setNumeroParametros(7);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.getSelected().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha", new java.sql.Timestamp(this.getSelected().getFecha().getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_glosa", this.getSelected().getGlosa()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", this.getTotalCredito()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", 3});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", new java.sql.Timestamp(new Date().getTime())});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        //System.out.println("antes ejecutar editCabecera");
        // Invocando al SP
        llamaSP.invocaSP();
        //System.out.println("depues ejecutar editCabecera");

        if (llamaSP.isEjecucionCorrecta()) {
            this.getSelected().setTotal(this.getTotalCredito());
        }
        return llamaSP.isEjecucionCorrecta();
    }

    private boolean eliminaDetalleComprobante() {
        //System.out.println("Punto 0");
        llamaSP.setNombreSP("mks_contables.pkm_comprobante_detalle.p_elimina_detalle_com");
        llamaSP.setNumeroParametros(1);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.getSelected().getCodigo()});

        //System.out.println("Punto 1");
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        //System.out.println("Punto 2");
        return llamaSP.isEjecucionCorrecta();
    }

    /**
     * Edita detalle de comprobante (guarda nuevos y edita existentes)
     *
     * @return
     */
    private boolean editaDetalleComprobante() {

        llamaSP.setNombreSP("mks_contables.pkm_comprobante_detalle.p_registra_detalle_comp");
        llamaSP.setNumeroParametros(7);
        short linea = (short) (this.getItemsComprobanteDetalle().size() - this.getItemsAdicionalesDetalle().size() + 1);
        // Insertando detalle de comprobante
        // Guarda nuevos items
        for (ComprobanteDetalle item : this.getItemsAdicionalesDetalle()) {
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_comprobante", this.getSelected().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuenta_contable", item.getComprobanteDetallePK().getCuentaContable()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_plan", item.getComprobanteDetallePK().getCodigoTipoPlan()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", String.valueOf(item.getTipo())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", item.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_linea", linea});
            linea++;

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el registro fue correcto
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }
        //Actualiza items ateriores
        if (llamaSP.isEjecucionCorrecta()) {
            llamaSP.setNombreSP("mks_contables.pkm_comprobante_detalle.p_act_detalle_comp");
            llamaSP.setNumeroParametros(6);

            for (ComprobanteDetalle item : this.getItemsComprobanteDetalle()) {
                if (!this.getItemsAdicionalesDetalle().contains(item)) {
                    llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_comprobante", this.getSelected().getCodigo()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuenta_contable", item.getComprobanteDetallePK().getCuentaContable()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_plan", item.getComprobanteDetallePK().getCodigoTipoPlan()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", String.valueOf(item.getTipo())});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", item.getValor()});
                    llamaSP.getListParametrosEntrada().add(new Object[]{"p_linea", item.getLinea()});

                    llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                    // Invocando al SP
                    llamaSP.invocaSP();

                    // Verificando si el registro fue correcto
                    if (!llamaSP.isEjecucionCorrecta()) {
                        break;
                    }
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();
    }

    //-----------------------------------------------------------------------------
    //DESMAYORIZA
    public void desmayoriza() {
        if (this.getSelected() == null) {
            return;
        }

        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_contables.pkm_comprobante.p_cambia_estado");
            llamaSP.setNumeroParametros(4);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo", this.getSelected().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_estado", 1});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", new java.sql.Timestamp(new Date().getTime())});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComprobanteDesmayorizado");
                MuestraMensaje.addInformacion(msg);

                this.setDeshabilitaBotonDesmayoriza(true);
                obtieneComprobantes();
                this.setSelected(null);

            } else {
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"comprobanteController", "dsmayoriza", CapturaError.getErrorException(ex)});
        }
        this.setSelected(null);
    }

    /**
     * IMPRIME COMPROBANTE CONTABLE
     *
     * @param codCom
     * @param numCom
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeComprobante(Long codCom, String numCom) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigo", codCom);

        nombreReporte = "comprobante";

        getGeneraReporte().exporta("/contable/comprobantes/comprobante/reporte/", nombreReporte,
                nombreReporte + String.valueOf(numCom) + ".pdf",
                "PDF", externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }

    public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.imprimeComprobante(this.getSelected().getCodigo(), this.getSelected().getNumeroComprobante());
    }

    public void obtieneComprobantes() {
        try {
            if ((tipoFiltro == 'N' && Validaciones.cumpleRequerimientoCampo(numeroComprobanteCriterio, 12)) || (tipoFiltro == 'F' && (Validaciones.cumpleRequerimientoCampo(fechaCriterio)))) {
                this.setItemsComprobante(this.ejbFacade.getItemsComprobante(tipoFiltro, fechaCriterio, numeroComprobanteCriterio, ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia()));
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsComprobante(null);
                this.setSelected(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"socioBean", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Agrega días a una fecha
     *
     * @param fecha FEcha
     * @param dias Numero de dias a agregar
     * @return FEcha agregada días
     */
    private Date agregaDias(Date fecha, long dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, (int) dias);
        return calendar.getTime();
    }

    /**
     * @return the itemDetalleSel
     */
    public ComprobanteDetalle getItemDetalleSel() {
        return itemDetalleSel;
    }

    /**
     * @param itemDetalleSel the itemDetalleSel to set
     */
    public void setItemDetalleSel(ComprobanteDetalle itemDetalleSel) {
        this.itemDetalleSel = itemDetalleSel;
    }

    public void nuevo(ActionEvent event) {
        context = RequestContext.getCurrentInstance();

        // Validando el periodo contable si no ha sido cerrado
        if (!validaPeriodoContable()) {
            return;
        }

        context.execute("procesandoDlg.hide()");
        context.execute("ComprobanteCreateDialog.show()");

        this.setItemsPlanDeCuenta(this.ejbFacadePlanDeCuentasIfip.getItemsPlanCuentaVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
        this.setItemsTipoComprobante(this.ejbFacadeTipoComprobante.getItemsTipoComprobanteEliminado('N', 'S'));
        this.setComprobante(new Comprobante());
        this.setItemActual(new ComprobanteDetalle());
        this.setItemsComprobanteDetalle(new ArrayList<ComprobanteDetalle>());
        this.estadoPorDefecto();
        this.deshabilitarBotonGuardar = true;
        if (this.tipoPlanCuentaValido()) {
            this.comprobanteGuardado = false;
        } else {
            this.comprobanteGuardado = true;
            mensajeDetalle = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPlanCuentaError");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPlanCuentaError"));
            context.execute("ComprobanteCreateDialog.hide()");
            return;
        }

    }

    public void edita(ActionEvent event) {
        context = RequestContext.getCurrentInstance();
       
        // Validando el periodo contable si no ha sido cerrado
        if (!validaPeriodoContable()) {
            return;
        }
        
        context.execute("procesandoDlg.hide()");
        context.execute("ComprobanteEditDialog.show()");

        this.setItemActual(new ComprobanteDetalle());
        this.setItemsAdicionalesDetalle(new ArrayList<ComprobanteDetalle>());
        this.setItemsComprobanteDetalle(this.getEjbFacadeComprobanteDetalle().getItemsComprobanteDetalle(this.getSelected().getCodigo()));
        this.habilitarBotonGuardar();
        if (itemsPlanDeCuenta.size() > 0) {
            planDeCuenta = itemsPlanDeCuenta.get(0);
        }
        ComprobanteDetallePK cdpk = new ComprobanteDetallePK(Long.parseLong("0"), this.planDeCuenta.getPlanDeCuentaPK().getCuentaContable(), this.planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan());
        itemActual.setComprobanteDetallePK(cdpk);
        itemActual.setPlanDeCuentaIfip(new PlanDeCuentaIfip(this.planDeCuenta.getPlanDeCuentaPK().getCuentaContable(), this.planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan(), ActivacionUsuario.getCodigoIfip()));
        itemActual.getPlanDeCuentaIfip().setPlanDeCuenta(planDeCuenta);
        itemActual.setLinea(Short.parseShort("0"));
        itemActual.setValor(new BigDecimal("0.00"));
        this.setItemsAdicionalesDetalle(new ArrayList<ComprobanteDetalle>());
        if (this.getSelected().getCodigoEstado().getNombre().equals("MAYORIZADO")) {
            this.setHabilitarEdicion(false);
            this.setDeshabilitarBotonGuardar(true);
            mensajeDetalle = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EdicionNoPermitida");
        } else {
            if (this.tipoPlanCuentaValido()) {
                this.setHabilitarEdicion(true);
                this.setDeshabilitarBotonGuardar(false);
            } else {
                mensajeDetalle = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPlanCuentaError");
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TipoPlanCuentaError"));
            }

        }
        this.itemsComprobanteGuardados = new ArrayList<ComprobanteDetalle>();
        ComprobanteDetalle i = new ComprobanteDetalle();
        for (ComprobanteDetalle it : this.getItemsComprobanteDetalle()) {
            i.setComprobanteDetallePK(cdpk);
            i.setComprobante(this.getSelected());
            i.setLinea(Short.parseShort("0"));
            i.setPlanDeCuentaIfip(it.getPlanDeCuentaIfip());
            i.setTipo(it.getTipo());
            i.setValor(it.getValor());
            this.itemsComprobanteGuardados.add(i);
        }
        comprobanteGuardado = false;
    }

    /**
     * Validando si el periodo contable no ha sido cerrado
     *
     * @return
     */
    private boolean validaPeriodoContable() {
        // Verificando que el periodo sea el actual
        CierreAnual cierreAnual = ejbFacadeCierreAnual.find(new CierreAnualPK(ActivacionUsuario.getCodigoPeriodo(), ActivacionUsuario.getCodigoIfip()));

        if (cierreAnual != null) {
            if (cierreAnual.getEliminado() == 'N') {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoPuedeCrearEditarComprobantesPeriodoCerrado"));
                return false;
            }
        }

        return true;
    }

    public void formatoValor() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", simbolos);
        try {
            itemActual.setValor(new BigDecimal(df.format(itemActual.getValor().doubleValue())));
        } catch (NumberFormatException e) {
            simbolos.setDecimalSeparator(',');
            df = new DecimalFormat("#.00", simbolos);
            itemActual.setValor(new BigDecimal(df.format(itemActual.getValor().doubleValue())));
        }

    }

    public Date fecha(String fecha) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaD;

        fechaD = formateador.parse(fecha);
        //System.out.println("inicio " + fechaD + " " + fechaD.getYear());

        return fechaD;
    }

    /**
     * @return the listaComprobante
     */
    public List<Comprobante> getItemsComprobante() {
        return itemsComprobante;
    }

    /**
     * @param itemsComprobante the listaComprobante to set
     */
    public void setItemsComprobante(List<Comprobante> itemsComprobante) {
        this.itemsComprobante = itemsComprobante;
    }

    /**
     * @return the listaPlanDeCuenta
     */
    public List<PlanDeCuenta> getItemsPlanDeCuenta() {
        return itemsPlanDeCuenta;
    }

    /**
     * @param itemsPlanDeCuenta the listaPlanDeCuenta to set
     */
    public void setItemsPlanDeCuenta(List<PlanDeCuenta> itemsPlanDeCuenta) {
        this.itemsPlanDeCuenta = itemsPlanDeCuenta;
    }

    /**
     * @return the ejbFacadeTipoComprobante
     */
    public TipoComprobanteFacade getEjbFacadeTipoComprobante() {
        return ejbFacadeTipoComprobante;
    }

    /**
     * @param ejbFacadeTipoComprobante the ejbFacadeTipoComprobante to set
     */
    public void setEjbFacadeTipoComprobante(TipoComprobanteFacade ejbFacadeTipoComprobante) {
        this.ejbFacadeTipoComprobante = ejbFacadeTipoComprobante;
    }

    /**
     * @return the listaTipoComprobante
     */
    public List<TipoComprobante> getItemsTipoComprobante() {
        return itemsTipoComprobante;
    }

    /**
     * @param itemsTipoComprobante the listaTipoComprobante to set
     */
    public void setItemsTipoComprobante(List<TipoComprobante> itemsTipoComprobante) {
        this.itemsTipoComprobante = itemsTipoComprobante;
    }

    /**
     * @return the totalDebito
     */
    public BigDecimal getTotalDebito() {
        return totalDebito;
    }

    /**
     * @param totalDebito the totalDebito to set
     */
    public void setTotalDebito(BigDecimal totalDebito) {
        this.totalDebito = totalDebito;
    }

    /**
     * @return the totalCredito
     */
    public BigDecimal getTotalCredito() {
        return totalCredito;
    }

    /**
     * @param totalCredito the totalCredito to set
     */
    public void setTotalCredito(BigDecimal totalCredito) {
        this.totalCredito = totalCredito;
    }

    /**
     * @return the planDeCuenta
     */
    public PlanDeCuenta getPlanDeCuenta() {
        return planDeCuenta;
    }

    /**
     * @param planDeCuenta the planDeCuenta to set
     */
    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
        this.planDeCuenta = planDeCuenta;
    }

    /**
     * @return the mostrarFitrloFehca
     */
    public boolean isMostrarFitrloFehca() {
        return mostrarFitrloFehca;
    }

    /**
     * @param mostrarFitrloFehca the mostrarFitrloFehca to set
     */
    public void setMostrarFitrloFehca(boolean mostrarFitrloFehca) {
        this.mostrarFitrloFehca = mostrarFitrloFehca;
    }

    /**
     * @return the mostrarFiltroNumComprobante
     */
    public boolean isMostrarFiltroNumComprobante() {
        return mostrarFiltroNumComprobante;
    }

    /**
     * @param mostrarFiltroNumComprobante the mostrarFiltroNumComprobante to set
     */
    public void setMostrarFiltroNumComprobante(boolean mostrarFiltroNumComprobante) {
        this.mostrarFiltroNumComprobante = mostrarFiltroNumComprobante;
    }

    /**
     * @return the tipoFiltro
     */
    public char getTipoFiltro() {
        return tipoFiltro;
    }

    /**
     * @param tipoFiltro the tipoFiltro to set
     */
    public void setTipoFiltro(char tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    /**
     * @return the fechaCriterio
     */
    public Date getFechaCriterio() {
        return fechaCriterio;
    }

    /**
     * @param fechaCriterio the fechaCriterio to set
     */
    public void setFechaCriterio(Date fechaCriterio) {
        this.fechaCriterio = fechaCriterio;
    }

    /**
     * @return the numeroComprobanteCriterio
     */
    public String getNumeroComprobanteCriterio() {
        return numeroComprobanteCriterio;
    }

    /**
     * @param numeroComprobanteCriterio the numeroComprobanteCriterio to set
     */
    public void setNumeroComprobanteCriterio(String numeroComprobanteCriterio) {
        this.numeroComprobanteCriterio = numeroComprobanteCriterio;
    }

    /**
     * @return the planCuentaPK
     */
    public PlanDeCuentaPK getPlanCuentaPK() {
        return planCuentaPK;
    }

    /**
     * @param planCuentaPK the planCuentaPK to set
     */
    public void setPlanCuentaPK(PlanDeCuentaPK planCuentaPK) {
        this.planCuentaPK = planCuentaPK;
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

    /**
     * @return the itemsComprobanteDetalle
     */
    public List<ComprobanteDetalle> getItemsComprobanteDetalle() {
        return itemsComprobanteDetalle;
    }

    /**
     * @param itemsComprobanteDetalle the itemsComprobanteDetalle to set
     */
    public void setItemsComprobanteDetalle(List<ComprobanteDetalle> itemsComprobanteDetalle) {
        this.itemsComprobanteDetalle = itemsComprobanteDetalle;
    }

    /**
     * @return the comprobante
     */
    public Comprobante getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * @return the itemActual
     */
    public ComprobanteDetalle getItemActual() {
        return itemActual;
    }

    /**
     * @param itemActual the itemActual to set
     */
    public void setItemActual(ComprobanteDetalle itemActual) {
        this.itemActual = itemActual;
    }

    /**
     * @return the itemsEstadoComprobante
     */
    public List<EstadoComprobante> getItemsEstadoComprobante() {
        return itemsEstadoComprobante;
    }

    /**
     * @param itemsEstadoComprobante the itemsEstadoComprobante to set
     */
    public void setItemsEstadoComprobante(List<EstadoComprobante> itemsEstadoComprobante) {
        this.itemsEstadoComprobante = itemsEstadoComprobante;
    }

    /**
     * @return the mensajeDetalle
     */
    public String getMensajeDetalle() {
        return mensajeDetalle;
    }

    /**
     * @param mensajeDetalle the mensajeDetalle to set
     */
    public void setMensajeDetalle(String mensajeDetalle) {
        this.mensajeDetalle = mensajeDetalle;
    }

    /**
     * @return the fechaComprobante
     */
    public Timestamp getFechaComprobante() {
        return fechaComprobante;
    }

    /**
     * @param fechaComprobante the fechaComprobante to set
     */
    public void setFechaComprobante(Timestamp fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    /**
     * @return the ejbFacadeComprobanteDetalle
     */
    public ComprobanteDetalleFacade getEjbFacadeComprobanteDetalle() {
        return ejbFacadeComprobanteDetalle;
    }

    /**
     * @param ejbFacadeComprobanteDetalle the ejbFacadeComprobanteDetalle to set
     */
    public void setEjbFacadeComprobanteDetalle(ComprobanteDetalleFacade ejbFacadeComprobanteDetalle) {
        this.ejbFacadeComprobanteDetalle = ejbFacadeComprobanteDetalle;
    }

    /**
     * @return the itemsAdicionalesDetalle
     */
    public List<ComprobanteDetalle> getItemsAdicionalesDetalle() {
        return itemsAdicionalesDetalle;
    }

    /**
     * @param itemsAdicionalesDetalle the itemsAdicionalesDetalle to set
     */
    public void setItemsAdicionalesDetalle(List<ComprobanteDetalle> itemsAdicionalesDetalle) {
        this.itemsAdicionalesDetalle = itemsAdicionalesDetalle;
    }

    /**
     * @return the deshabilitarEdicion
     */
    public boolean isHabilitarEdicion() {
        return habilitarEdicion;
    }

    /**
     * @param habilitarEdicion the deshabilitarEdicion to set
     */
    public void setHabilitarEdicion(boolean habilitarEdicion) {
        this.habilitarEdicion = habilitarEdicion;
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
     * @return the comprobanteGuardado
     */
    public boolean isComprobanteGuardado() {
        return comprobanteGuardado;
    }

    /**
     * @param comprobanteGuardado the comprobanteGuardado to set
     */
    public void setComprobanteGuardado(boolean comprobanteGuardado) {
        this.comprobanteGuardado = comprobanteGuardado;
    }

    /**
     * @return the ejbFacadeTipoPlanCuenta
     */
    public TipoPlanDeCuentaFacade getEjbFacadeTipoPlanCuenta() {
        return ejbFacadeTipoPlanCuenta;
    }

    /**
     * @param ejbFacadeTipoPlanCuenta the ejbFacadeTipoPlanCuenta to set
     */
    public void setEjbFacadeTipoPlanCuenta(TipoPlanDeCuentaFacade ejbFacadeTipoPlanCuenta) {
        this.ejbFacadeTipoPlanCuenta = ejbFacadeTipoPlanCuenta;
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the fechaMimina
     */
    public Date getFechaMimina() {
        return fechaMimina;
    }

    /**
     * @param fechaMimina the fechaMimina to set
     */
    public void setFechaMimina(Date fechaMimina) {
        this.fechaMimina = fechaMimina;
    }

    /**
     * @return the fechaMaxima
     */
    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    /**
     * @param fechaMaxima the fechaMaxima to set
     */
    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    /**
     * @return the deshabilitaBotonDesmayoriza
     */
    public boolean isDeshabilitaBotonDesmayoriza() {
        return deshabilitaBotonDesmayoriza;
    }

    /**
     * @param deshabilitaBotonDesmayoriza the deshabilitaBotonDesmayoriza to set
     */
    public void setDeshabilitaBotonDesmayoriza(boolean deshabilitaBotonDesmayoriza) {
        this.deshabilitaBotonDesmayoriza = deshabilitaBotonDesmayoriza;
    }

    /**
     * @return the itemsComprobanteGuardados
     */
    public List<ComprobanteDetalle> getItemsComprobanteGuardados() {
        return itemsComprobanteGuardados;
    }

    /**
     * @param itemsComprobanteGuardados the itemsComprobanteGuardados to set
     */
    public void setItemsComprobanteGuardados(List<ComprobanteDetalle> itemsComprobanteGuardados) {
        this.itemsComprobanteGuardados = itemsComprobanteGuardados;
    }

    /**
     * @return the peridoCotable
     */
    public String getPeridoCotable() {
        return peridoCotable;
    }

    /**
     * @param peridoCotable the peridoCotable to set
     */
    public void setPeridoCotable(String peridoCotable) {
        this.peridoCotable = peridoCotable;
    }

}
