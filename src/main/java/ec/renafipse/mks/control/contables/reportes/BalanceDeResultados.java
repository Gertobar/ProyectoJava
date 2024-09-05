package ec.renafipse.mks.control.contables.reportes;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta;
import ec.renafipse.mks.negocio.contables.ComprobanteFacade;
import ec.renafipse.mks.negocio.contables.TipoPlanDeCuentaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@ManagedBean(name = "balanceDeResultadosBean")
@ViewScoped
public class BalanceDeResultados extends AbstractController<Comprobante> implements Serializable {

    @EJB
    private ComprobanteFacade ejbFacade;
    
    @EJB
    private TipoPlanDeCuentaFacade ejbFacadeTipoPlanDeCuenta;

    private List<String> itemsPeriodos;

    private String periodoActual;
    private String mesInicioActual;
    private String msg;

    private boolean deshabilitaInicio;
    private boolean deshabilitaFin;
    private boolean deshabilitaExportar;
    
    private Long codigoTipoPlan;

    private RequestContext context;

    private GeneraReporte generaReporte;

    private int dia;

    private Date fechaInicio;
    private Date fechaCorte;

    private Date fechaMinima;
    private Date fechaMaxima;

    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        setItemsPeriodos(ejbFacade.getItemsPeriodo());
        try {
            iniciaParametros();
        } catch (ParseException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"balanceDeResultadosBean", "init", CapturaError.getErrorException(ex)});
        }
    }

    public void iniciaParametros() throws ParseException {
        periodoActual = ActivacionUsuario.getCodigoPeriodo();

        this.fechaMinima = convertirFecha("01/01/" + this.periodoActual);
        this.fechaInicio = this.fechaMinima;

       
        this.setFechaMinima(convertirFecha("01/01/" + this.periodoActual));

        this.fechaInicio = this.getFechaMinima();

        if (new Date().getYear() + 1900 == Integer.parseInt(periodoActual)) {
            this.setFechaMaxima(new Date());
        } else {
            this.setFechaMaxima(convertirFecha("31/12/" + this.periodoActual));
        }

        setDeshabilitaExportar(false);
        setDeshabilitaFin(false);
        setDeshabilitaInicio(false);

    }

    //cambia periodo 
    public void cambiaPeriodo() {
        String msg = null;
        //System.out.println("as" + periodoActual);
        if (periodoActual.equals("")) {
            setDeshabilitaFin(true);
            setDeshabilitaInicio(true);
            setDeshabilitaExportar(true);
            setMesInicioActual(null);
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            setDeshabilitaInicio(false);
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
    }

    public void cambiaMesInicio() {
        String msg = null;
        boolean exito = true;
        Date fechaHoy = new Date();
        if (getMesInicioActual() == null) {
            setDeshabilitaExportar(true);
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            if (Integer.parseInt(periodoActual) <= fechaHoy.getYear() + 1900) {

                if (Integer.parseInt(mesInicioActual) <= fechaHoy.getMonth() + 1) {
                    setDeshabilitaExportar(false);
                    msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Generar") + " "
                            + ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("BalanceDeResultados");
                    exito = true;
                } else {
                    msg = "NO SE PUEDE GENERAR EL REPORTE PARA ESE MES TODAVIA";
                    exito = false;
                    setDeshabilitaExportar(true);
                }

            } else {
                setDeshabilitaExportar(true);
            }

        }
        if (!exito && msg != null) {
            MuestraMensaje.addError(msg);
        } else {
            MuestraMensaje.addSatisfactorio(msg);
        }
    }

    public Date convertirFecha(String fecha) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        //Date fechac;        
        //fechaInicio = formateador.parse(fecha);
        //System.out.println("fecha " + fecha);

        return formateador.parse(fecha);
    }

     /**
     * Obtiene el Plande Cuentas que corresponda a las FEchas
     */
    public void obtienePlanCuenta() {

        this.msg = null;
        List<TipoPlanDeCuenta> listaTipoPlanDeCuenta = this.ejbFacadeTipoPlanDeCuenta.getItemsFechaVigente(this.getFechaInicio(), this.fechaCorte);
        // Colocando el Plan Vigente
        if (listaTipoPlanDeCuenta.isEmpty()) {
            listaTipoPlanDeCuenta = this.ejbFacadeTipoPlanDeCuenta.getItemsTipoPlanCuentaVigente('S');
            this.codigoTipoPlan = listaTipoPlanDeCuenta.get(0).getCodigo();
        } else {

            if (listaTipoPlanDeCuenta.size() == 1) {
                this.codigoTipoPlan = listaTipoPlanDeCuenta.get(0).getCodigo();

            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("VariosPlanCuentaEntreFechas"));
            }
        }
    }
    
    //metodo para exportar en Excel
    public void exportarExcel(ActionEvent event) throws ParseException, IOException {       

         this.obtienePlanCuenta();
        if (this.msg != null) {
            return;
        }
        
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());
        
        String directoriReporte = "/contable/reportes/estadoResultados/reporte/";
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());
        getGeneraReporte().getParametros().put("mesInicio", this.fechaInicio);
        getGeneraReporte().getParametros().put("mesFin", this.fechaCorte);
        getGeneraReporte().getParametros().put("codigoEstado", Long.parseLong("3"));
        getGeneraReporte().getParametros().put("periodoFiscal", periodoActual);
        getGeneraReporte().getParametros().put("mostrarEnBalance", "S");
        getGeneraReporte().getParametros().put("codigoTipoPlan", this.codigoTipoPlan);
        getGeneraReporte().getParametros().put("subReporteCabecera", getGeneraReporte().getSubReporte(externalContext, directoriReporte, "estadoResultadosExcelCabecera"));
        
        String nombreArchivo = "EstadoResultados-" + ConvierteDato.getFechaCortaSinSeparadores(fechaCorte);
        nombreReporte = "estadoResultadosExcel";
        getGeneraReporte().exporta("/contable/reportes/estadoResultados/reporte/", nombreReporte,
                nombreArchivo + ".xls",
                "XLS", externalContext, facesContext);

    }

    public void exportarPdf(ActionEvent event) throws ParseException, IOException {
        
         this.obtienePlanCuenta();
        if (this.msg != null) {
            return;
        }
      
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());
        getGeneraReporte().getParametros().put("mesInicio", this.fechaInicio);
        getGeneraReporte().getParametros().put("mesFin", this.fechaCorte);
        getGeneraReporte().getParametros().put("codigoEstado", Long.parseLong("3"));
        getGeneraReporte().getParametros().put("periodoFiscal", periodoActual);
        getGeneraReporte().getParametros().put("mostrarEnBalance", "S");
        getGeneraReporte().getParametros().put("codigoTipoPlan", this.codigoTipoPlan);

        String nombreArchivo = "EstadoResultados-"+ ConvierteDato.getFechaCortaSinSeparadores(fechaCorte);
        nombreReporte = "estadoResultados";
        getGeneraReporte().exporta("/contable/reportes/estadoResultados/reporte/", nombreReporte,
                nombreArchivo + ".pdf",
                "PDF", externalContext, facesContext);
        //System.out.println("pdf");

    }


    //metodos para convertir lo receptado en los combos en meses de acuerdo como correponda
    public Date fechaInicio(String mesInicio, String periodo) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio;
        String fecha = null;

        switch (Integer.parseInt(mesInicio)) {
            case 1:
                fecha = "01/01/" + periodo;
                break;
        }
        fechaInicio = formateador.parse(fecha);
        //System.out.println("inicio " + fechaInicio + " " + fechaInicio.getYear());

        return fechaInicio;
    }

    public Date fechaFinal(String mesFin, String periodo) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaFin;
        String fecha = null;
        Date fechaActual = new Date();

        //System.out.println("Año " + " " + fechaActual.getDate());

        switch (Integer.parseInt(mesFin)) {
            case 1:

                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/01/" + periodo;
                    } else {
                        fecha = "31/01/" + periodo;
                    }
                } else {
                    fecha = "31/01/" + periodo;
                }

                break;
            case 2:
                if ((Integer.parseInt(periodo) % 4) == 0) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/02/" + periodo;
                    } else {
                        fecha = "29/02/" + periodo;
                    }
                } else if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/02/" + periodo;
                    } else {
                        fecha = "28/02/" + periodo;
                    }
                } else {
                    fecha = "28/02/" + periodo;
                }
                break;
            case 3:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/03/" + periodo;
                    } else {
                        fecha = "31/03/" + periodo;
                    }
                } else {
                    fecha = "31/03/" + periodo;
                }

                break;
            case 4:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/04/" + periodo;
                    } else {
                        fecha = "30/04/" + periodo;
                    }
                } else {
                    fecha = "30/04/" + periodo;
                }

                break;
            case 5:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/05/" + periodo;
                    } else {
                        fecha = "31/05/" + periodo;
                    }
                } else {
                    fecha = "31/05/" + periodo;
                }

                break;
            case 6:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/06/" + periodo;
                    } else {
                        fecha = "30/06/" + periodo;
                    }
                } else {

                    fecha = "30/06/" + periodo;
                }

                break;
            case 7:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/07/" + periodo;
                    } else {
                        fecha = "31/07/" + periodo;
                    }
                } else {
                    fecha = "31/07/" + periodo;
                }

                break;
            case 8:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/08/" + periodo;
                    } else {
                        fecha = "31/08/" + periodo;
                    }
                } else {
                    fecha = "31/08/" + periodo;
                }

                break;
            case 9:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/09/" + periodo;
                    } else {
                        fecha = "30/09/" + periodo;
                    }
                } else {
                    fecha = "30/09/" + periodo;
                }

                break;
            case 10:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/10/" + periodo;
                    } else {
                        fecha = "31/10/" + periodo;
                    }
                } else {
                    fecha = "31/10/" + periodo;
                }

                break;
            case 11:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/11/" + periodo;
                    } else {
                        fecha = "30/11/" + periodo;
                    }
                } else {
                    fecha = "30/11/" + periodo;
                }

                break;
            case 12:
                if (fechaActual.getYear() + 1900 == Integer.parseInt(periodo)) {
                    if (fechaActual.getMonth() + 1 == Integer.parseInt(mesFin)) {
                        fecha = fechaActual.getDate() + "/12/" + periodo;
                    } else {
                        fecha = "31/12/" + periodo;
                    }
                } else {
                    fecha = "31/12/" + periodo;
                }
                break;
        }

        fechaFin = formateador.parse(fecha);
        //System.out.println(fechaFin);
        //System.out.println("Año" + " " + fechaFin.getYear() + 1900);
        return fechaFin;
    }

    /**
     * @return the ejbFacade
     */
    public ComprobanteFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ComprobanteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the itemsPeriodos
     */
    public List<String> getItemsPeriodos() {
        return itemsPeriodos;
    }

    /**
     * @param itemsPeriodos the itemsPeriodos to set
     */
    public void setItemsPeriodos(List<String> itemsPeriodos) {
        this.itemsPeriodos = itemsPeriodos;
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
     * @return the deshabilitaInicio
     */
    public boolean isDeshabilitaInicio() {
        return deshabilitaInicio;
    }

    /**
     * @param deshabilitaInicio the deshabilitaInicio to set
     */
    public void setDeshabilitaInicio(boolean deshabilitaInicio) {
        this.deshabilitaInicio = deshabilitaInicio;
    }

    /**
     * @return the deshabilitaFin
     */
    public boolean isDeshabilitaFin() {
        return deshabilitaFin;
    }

    /**
     * @param deshabilitaFin the deshabilitaFin to set
     */
    public void setDeshabilitaFin(boolean deshabilitaFin) {
        this.deshabilitaFin = deshabilitaFin;
    }

    /**
     * @return the deshabilitaExportar
     */
    public boolean isDeshabilitaExportar() {
        return deshabilitaExportar;
    }

    /**
     * @param deshabilitaExportar the deshabilitaExportar to set
     */
    public void setDeshabilitaExportar(boolean deshabilitaExportar) {
        this.deshabilitaExportar = deshabilitaExportar;
    }

    /**
     * @return the mesInicioActual
     */
    public String getMesInicioActual() {
        return mesInicioActual;
    }

    /**
     * @param mesInicioActual the mesInicioActual to set
     */
    public void setMesInicioActual(String mesInicioActual) {
        this.mesInicioActual = mesInicioActual;
    }

    /**
     * @return the mesInicioFin
     */
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
     * @return the context
     */
    public RequestContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(RequestContext context) {
        this.context = context;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
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
     * @return the fechaCorte
     */
    public Date getFechaCorte() {
        return fechaCorte;
    }

    /**
     * @param fechaCorte the fechaCorte to set
     */
    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
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

}
