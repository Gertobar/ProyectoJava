package ec.renafipse.mks.control.contables.reportes;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.CierreAnual;
import ec.renafipse.mks.modelo.contables.CierreAnualPK;
import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.modelo.contables.ComprobanteDetalle;
import ec.renafipse.mks.modelo.contables.ComprobanteDetalleManual;
import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
import ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta;
import ec.renafipse.mks.negocio.contables.CierreAnualFacade;
import ec.renafipse.mks.negocio.contables.ComprobanteDetalleFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
import ec.renafipse.mks.negocio.contables.TipoPlanDeCuentaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import oracle.jdbc.OracleTypes;

@ManagedBean(name = "mayorGeneralBean")
@ViewScoped
public class MayorGeneralBean extends AbstractController<Comprobante> implements Serializable {

    @EJB
    private PlanDeCuentaIfipFacade ejbFacadePlanDeCuentasIfip;

    @EJB
    private ComprobanteDetalleFacade ejbFacadeComprobanteDetalle;

    @EJB
    private CierreAnualFacade ejbFacadeCierreAnual;

    @EJB
    private TipoPlanDeCuentaFacade ejbFacadeTipoPlanDeCuenta;

    private String periodoActual;
    private Date fechaIncioConsultaSaldo;

    private PlanDeCuenta planDeCuenta;
    private Date fechaInicio;
    private Date fechaFin;
    private List<PlanDeCuenta> itemsPlanDeCuenta;
    private List<ComprobanteDetalle> itemsComprobanteDetalle;
    private List<ComprobanteDetalleManual> itemsComprobanteDetalleManual;
    private List<String[]> mayorGeneral;
    private String[] seleccion;
    private Date fechaMinima;
    private Date fechaMaxima;
    private BigDecimal saldoInicial;

    private GeneraReporte generaReporte;

    private LlamaSP llamaSP;

    @PostConstruct
    public void init() {
        try {
            //Instancion el proceso de llama Store Procedure
            llamaSP = new LlamaSP();

            this.setPeriodoActual(ActivacionUsuario.getCodigoPeriodo());

            //this.setItemsPlanDeCuenta(this.getEjbFacadePlanDeCuentasIfip().getItemsPlanCuentaVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
            periodoActual = ActivacionUsuario.getCodigoPeriodo();

            this.setFechaMinima(convertirFecha("01/01/" + this.periodoActual));

            this.fechaInicio = this.getFechaMinima();

            this.fechaIncioConsultaSaldo = fechaInicio;

            CierreAnual cierreAnual = ejbFacadeCierreAnual.find(new CierreAnualPK(ActivacionUsuario.getCodigoPeriodo(), ActivacionUsuario.getCodigoIfip()));

            this.setFechaMaxima(new Date());

            if (new Date().getYear() + 1900 == Integer.parseInt(periodoActual)) {
                this.setFechaMaxima(new Date());
            } else {
                if (cierreAnual != null) {
                    if (cierreAnual.getEliminado() == 'N') {
                        this.setFechaMaxima(convertirFecha("31/12/" + this.periodoActual));
                    } else {
                        this.setFechaMaxima(new Date());
                    }
                }
            }

            this.saldoInicial = new BigDecimal("0.00");

        } catch (ParseException ex) {
            Logger.getLogger(MayorGeneralBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene el Plande Cuentas que corresponda a las FEchas
     */
    public void obtienePlanCuenta() {
        this.setItemsComprobanteDetalle(null);
        this.setItemsPlanDeCuenta(null);
        this.planDeCuenta = null;

        List<TipoPlanDeCuenta> listaTipoPlanDeCuenta = this.ejbFacadeTipoPlanDeCuenta.getItemsFechaVigente(this.getFechaInicio(), this.getFechaFin());
        // Colocando el Plan Vigente
        if (listaTipoPlanDeCuenta.isEmpty()) {
            this.setItemsPlanDeCuenta(this.getEjbFacadePlanDeCuentasIfip().getItemsPlanCuentaVigenteEliminado(ActivacionUsuario.getCodigoIfip(), 'S', 'S', 'N'));
        } else {
            if (listaTipoPlanDeCuenta.size() == 1) {
                this.setItemsPlanDeCuenta(this.getEjbFacadePlanDeCuentasIfip().getItemsCodigoIfipCodigoTipoPlan(ActivacionUsuario.getCodigoIfip(), listaTipoPlanDeCuenta.get(0).getCodigo()));
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("VariosPlanCuentaEntreFechas"));
            }
        }
    }
    
    public void obtieneMayor() throws SQLException{
        llamaSP.cargaConexion();

        // Indicando que no cierre la conexion de la base de datos
        llamaSP.setCerrarConexion(false);

        // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
        llamaSP.autoCommit(false);

        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_contables.pkm_comprobante.p_obtiene_mayor_general");
        llamaSP.setNumeroParametros(7);
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_cuenta_contable", planDeCuenta.getPlanDeCuentaPK().getCuentaContable()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_periodo", ActivacionUsuario.getCodigoPeriodo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_plan", planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip ", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_inicio", new java.sql.Timestamp(this.fechaInicio.getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_fin", new java.sql.Timestamp(this.fechaFin.getTime())});
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"crt_mayor_general", OracleTypes.CURSOR});

        // Invocando al SP
        llamaSP.invocaSP();
        if (llamaSP.isEjecucionCorrecta()) {
            if( llamaSP.getListResultado().size() > 0 ){
                ResultSet resultado = ( ResultSet ) llamaSP.getListResultado().get(0);
                this.itemsComprobanteDetalleManual=new ArrayList<ComprobanteDetalleManual>();
                while( resultado.next() ) {
                    this.itemsComprobanteDetalleManual.add(new ComprobanteDetalleManual( resultado.getLong(1),
                                                                                         resultado.getString(2),
                                                                                         resultado.getLong(3),
                                                                                         resultado.getLong(4),
                                                                                         resultado.getString(5),
                                                                                         resultado.getBigDecimal(6),
                                                                                         resultado.getLong(7),
                                                                                         resultado.getString(8),
                                                                                         resultado.getString(9),
                                                                                         resultado.getString(10),
                                                                                         resultado.getDate(11)));
                }
            }
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
        } else {
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
        }
    }
    
    public void consultar() throws SQLException {
        if (validarFecha()) {
            if (this.obtieneSaldoIncial()) {
                this.obtieneMayor();
                //this.setItemsComprobanteDetalle(this.ejbFacadeComprobanteDetalle.getItemsComprobanteDetalleFechaIfipCuenta(ActivacionUsuario.getCodigoIfip(), fechaInicio, fechaFin, planDeCuenta.getPlanDeCuentaPK().getCuentaContable(), planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan()));
                this.mayorGeneral = new ArrayList<String[]>();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                double debito = 0, credito = 0, saldo = this.saldoInicial.doubleValue();

                for (ComprobanteDetalleManual detalle : this.getItemsComprobanteDetalleManual()) {
                    //System.out.println("Numero Comprobante: "+detalle.getComprobante().getNumeroComprobante()+" Valor "+detalle.getValor());
                    debito = 0;
                    credito = 0;
                    if (planDeCuenta.getCodigoGrupo().getCodigo() == 2 || planDeCuenta.getCodigoGrupo().getCodigo() == 3 || planDeCuenta.getCodigoGrupo().getCodigo() == 5) {

                        if (detalle.getTipo().equals("D")) {
                            debito = detalle.getValor().doubleValue() * -1;
                            credito = 0;
                        } else {
                            credito = (detalle.getValor().doubleValue());
                            debito = 0;
                        }
                        saldo = saldo + debito + credito;
                    } else {
                        //if (planDeCuenta.getCodigoGrupo().getCodigo() == 1 || planDeCuenta.getCodigoGrupo().getCodigo() == 4) {
                        if (detalle.getTipo().equals("D")) {
                            debito = detalle.getValor().doubleValue();
                            credito = 0;
                        } else {
                            credito = (detalle.getValor().doubleValue() * -1);
                            debito = 0;
                        }
                        saldo = saldo + credito + debito;

                    }
                    mayorGeneral.add(new String[]{detalle.getNumeroComprobante(),
                        detalle.getCodigoComprobante().toString(),
                        detalle.getEstado(),
                        detalle.getGlosa(),
                        formateador.format(detalle.getFecha()),
                        formatoValor(credito),
                        formatoValor(debito),
                        formatoValor(saldo)});

                }
            }
        }

    }

    private boolean obtieneSaldoIncial() {
        this.saldoInicial = new BigDecimal("0.00");
        // Cargando la conexion de BD
        llamaSP.cargaConexion();

        // Indicando que no cierre la conexion de la base de datos
        llamaSP.setCerrarConexion(false);

        // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
        llamaSP.autoCommit(false);

        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_contables.pkg_comprobante.p_obtiene_saldo_cuenta");
        llamaSP.setNumeroParametros(8);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_cuenta_contable", planDeCuenta.getPlanDeCuentaPK().getCuentaContable()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_plan", planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_grupo ", planDeCuenta.getCodigoGrupo().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_periodo", ActivacionUsuario.getCodigoPeriodo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pd_fecha_inicio", new java.sql.Timestamp(this.fechaIncioConsultaSaldo.getTime())});
        llamaSP.getListParametrosEntrada().add(new Object[]{"pd_fecha_fin", new java.sql.Timestamp(this.fechaInicio.getTime())});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"pn_saldo", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {
            llamaSP.commit();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
            saldoInicial = new BigDecimal(llamaSP.getListResultado().get(0).toString());
            System.out.println("SALDO INICIAL: "+saldoInicial);
        } else {
            llamaSP.rollback();
            llamaSP.cerrarConexion();
            llamaSP.setConexionBD(null);
        }
        return llamaSP.isEjecucionCorrecta();
    }

    private String formatoValor(double valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.00", simbolos);
        try {
            return df.format(valor);
        } catch (NumberFormatException e) {
            simbolos.setDecimalSeparator(',');
            df = new DecimalFormat("#0.00", simbolos);
            return df.format(valor);
        }

    }

    public boolean validarFecha() {
        String msg = null;
        if (fechaMayor(fechaFin, fechaMaxima) == 1) {
            //System.out.println("Mayor q hoy");
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechaFinInvalida");
        }
        if (fechaMayor(fechaFin, fechaInicio) == -1) {
            //System.out.println("Menor q primero");
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechaFinInvalida");
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
            return false;
        }
        return true;
    }

    //metodos para convertir lo receptado en los combos en meses de acuerdo como correponda
   /* private void fechaInicio() throws ParseException {
     SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
     String fecha = "01/01/" + periodoActual;
     fechaInicio = formateador.parse(fecha);

     }*/
    public void imprimeMayor(Long codIfip, String cueCont, Date inicio, Date fin, String extension, String tipo) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoIfip", codIfip);
        getGeneraReporte().getParametros().put("cuentaContable", cueCont);
        getGeneraReporte().getParametros().put("fechaInicio", inicio);
        getGeneraReporte().getParametros().put("fechaFin", fin);
        getGeneraReporte().getParametros().put("codigoTipoPlan", planDeCuenta.getPlanDeCuentaPK().getCodigoTipoPlan());
        getGeneraReporte().getParametros().put("codigoPeriodo", this.getPeriodoActual());
        

        nombreReporte = "mayorGeneral";

        getGeneraReporte().exporta("/contable/reportes/mayorGeneral/reporte/", nombreReporte,
                nombreReporte + cueCont + extension,
                tipo, externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }

    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (validarFecha()) {
            this.imprimeMayor(ActivacionUsuario.getCodigoIfip(), this.getPlanDeCuenta().getPlanDeCuentaPK().getCuentaContable(), this.fechaInicio, this.fechaFin, ".pdf", "PDF");
        }
    }

    public void imprimeExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (validarFecha()) {
            this.imprimeMayor(ActivacionUsuario.getCodigoIfip(), this.getPlanDeCuenta().getPlanDeCuentaPK().getCuentaContable(), this.fechaInicio, this.fechaFin, ".xls", "XLS");
        }
    }

    public void imprimeComp(Long codCom, String numCom) throws JRException, IOException, ClassNotFoundException {

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

    public void imprimeComprobante(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (seleccion != null) {
            this.imprimeComp(Long.parseLong(seleccion[1]), seleccion[0]);
        }
    }

    private int fechaMayor(Date fechaDate1, Date fechaDate2) {
        int resultado;

        if (fechaDate1.before(fechaDate2)) {
            resultado = -1;
        } else {
            if (fechaDate2.before(fechaDate1)) {
                resultado = 1;
            } else {
                resultado = 0;
            }
        }

        return resultado;
    }

    public Date convertirFecha(String fecha) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        //Date fechac;        
        //fechaInicio = formateador.parse(fecha);
        //System.out.println("fecha " + fecha);

        return formateador.parse(fecha);
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
     * @return the periodoActual
     */
    public String getPeriodoActual() {
        return periodoActual;
    }

    /**
     * @param periodoActual the periodoActual to set
     */
    public void setPeriodoActual(String periodoActual) {
        this.periodoActual = periodoActual;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the itemsPlanDeCuenta
     */
    public List<PlanDeCuenta> getItemsPlanDeCuenta() {
        return itemsPlanDeCuenta;
    }

    /**
     * @param itemsPlanDeCuenta the itemsPlanDeCuenta to set
     */
    public void setItemsPlanDeCuenta(List<PlanDeCuenta> itemsPlanDeCuenta) {
        this.itemsPlanDeCuenta = itemsPlanDeCuenta;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the ejbFacadePlanDeCuentasIfip
     */
    public PlanDeCuentaIfipFacade getEjbFacadePlanDeCuentasIfip() {
        return ejbFacadePlanDeCuentasIfip;
    }

    /**
     * @param ejbFacadePlanDeCuentasIfip the ejbFacadePlanDeCuentasIfip to set
     */
    public void setEjbFacadePlanDeCuentasIfip(PlanDeCuentaIfipFacade ejbFacadePlanDeCuentasIfip) {
        this.ejbFacadePlanDeCuentasIfip = ejbFacadePlanDeCuentasIfip;
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
     * @return the mayorGeneral
     */
    public List<String[]> getMayorGeneral() {
        return mayorGeneral;
    }

    /**
     * @param mayorGeneral the mayorGeneral to set
     */
    public void setMayorGeneral(List<String[]> mayorGeneral) {
        this.mayorGeneral = mayorGeneral;
    }

    /**
     * @return the seleccion
     */
    public String[] getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(String[] seleccion) {
        this.seleccion = seleccion;
    }

    /**
     * @return the fechaMinima
     */
    public Date getFechaMinima() {
        return fechaMinima;
    }

    /**
     * @param fechaMinima the fechaMinima to set
     */
    public void setFechaMinima(Date fechaMinima) {
        this.fechaMinima = fechaMinima;
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
     * @return the saldoInicial
     */
    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    /**
     * @param saldoInicial the saldoInicial to set
     */
    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    /**
     * @return the fechaIncioConsultaSaldo
     */
    public Date getFechaIncioConsultaSaldo() {
        return fechaIncioConsultaSaldo;
    }

    /**
     * @param fechaIncioConsultaSaldo the fechaIncioConsultaSaldo to set
     */
    public void setFechaIncioConsultaSaldo(Date fechaIncioConsultaSaldo) {
        this.fechaIncioConsultaSaldo = fechaIncioConsultaSaldo;
    }

    /**
     * @return the itemsComprobanteDetalleManual
     */
    public List<ComprobanteDetalleManual> getItemsComprobanteDetalleManual() {
        return itemsComprobanteDetalleManual;
    }

    /**
     * @param itemsComprobanteDetalleManual the itemsComprobanteDetalleManual to set
     */
    public void setItemsComprobanteDetalleManual(List<ComprobanteDetalleManual> itemsComprobanteDetalleManual) {
        this.itemsComprobanteDetalleManual = itemsComprobanteDetalleManual;
    }

}
