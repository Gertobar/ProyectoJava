package ec.renafipse.mks.control.contables.reportes;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.modelo.contables.TipoPlanDeCuenta;
import ec.renafipse.mks.negocio.contables.ComprobanteFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaIfipFacade;
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
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "diarioGeneralBean")
@ViewScoped
public class DiarioGeneralBean extends AbstractController<Comprobante> implements Serializable {

    @EJB
    private PlanDeCuentaIfipFacade ejbFacadePlanDeCuentasIfip;
    
    @EJB
    private ComprobanteFacade ejbFacadeComprobante;
    
    @EJB
    private TipoPlanDeCuentaFacade ejbFacadeTipoPlanDeCuenta;

    private Long codigoTipoPlan;
    private String msg;
    private String periodoActual;
    private Date fechaActual;

    private Date primero;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Comprobante> itemsComprobante;
    
    private Comprobante seleccion;

    private GeneraReporte generaReporte;

    @PostConstruct
    public void init() {
        this.setPeriodoActual(ActivacionUsuario.getCodigoPeriodo());
        this.setFechaActual(new Date());
        try {
            this.fechaInicio();
        } catch (ParseException ex) {
            Logger.getLogger(DiarioGeneralBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultar(){
        if (validarFecha()) {
            this.setItemsComprobante(this.ejbFacadeComprobante.getItemsComprobanteDetalleFechaIfip(ActivacionUsuario.getCodigoIfip(), fechaInicio, fechaFin));
        }        
    }

    public boolean validarFecha() {
        String msg = null;
        if (fechaMayor(fechaFin, fechaActual) == 1) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechaInvalida");
        }
        if (fechaMayor(fechaInicio, primero) == -1) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechasInvalida");
        }
        if (fechaMayor(fechaFin, fechaInicio) == -1) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FechasInicioMenor");
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
            if (getItemsComprobante()!=null)getItemsComprobante().clear();
            return false;
        }
        return true;
    }

    //metodos para convertir lo receptado en los combos en meses de acuerdo como correponda
    private void fechaInicio() throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = "01/01/" + periodoActual;
        primero = formateador.parse(fecha);

    }
    
    /**
     * Obtiene el Plande Cuentas que corresponda a las FEchas
     */
    public void obtienePlanCuenta() {
    
        this.msg = null;
        List<TipoPlanDeCuenta> listaTipoPlanDeCuenta = this.ejbFacadeTipoPlanDeCuenta.getItemsFechaVigente(this.getFechaInicio(), this.fechaFin);
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

    public void imprimeMayor(Long codIfip,Date inicio,Date fin, String extension, String tipo) throws JRException, IOException, ClassNotFoundException {

        this.obtienePlanCuenta();
        if (this.msg != null) {
            return;
        }
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        nombreReporte = "diarioGeneral";
        getGeneraReporte().getParametros().put("codigoIfip", codIfip);
        getGeneraReporte().getParametros().put("fechaInicio", inicio);
        getGeneraReporte().getParametros().put("fechaFin", fin);
        getGeneraReporte().getParametros().put("codigoTipoPlan", this.codigoTipoPlan);
        String ruta=externalContext.getRealPath("/contable/reportes/diarioGeneral/reporte/"+nombreReporte+".jasper");
        ruta=ruta.substring(0, ruta.lastIndexOf(nombreReporte));
        getGeneraReporte().getParametros().put("SUBREPORT_DIR", ruta);

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        getGeneraReporte().exporta("/contable/reportes/diarioGeneral/reporte/", nombreReporte,
                nombreReporte+" desde "+ ConvierteDato.getFechaCortaSinSeparadores(inicio)+" hasta "+ConvierteDato.getFechaCortaSinSeparadores(fin)+ extension,
                tipo, externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }

    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (validarFecha()) {
            this.imprimeMayor(ActivacionUsuario.getCodigoIfip(),this.fechaInicio,this.fechaFin, ".pdf", "PDF");
        }
    }

    public void imprimeExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (validarFecha()) {
            this.imprimeMayor(ActivacionUsuario.getCodigoIfip(),this.fechaInicio,this.fechaFin, ".xls", "XLS");
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
        getGeneraReporte().getParametros().put("SUBREPORT_DIR", codCom);

        nombreReporte = "comprobante";

        getGeneraReporte().exporta("/contable/comprobantes/comprobante/reporte/", nombreReporte,
                nombreReporte + String.valueOf(numCom) + ".pdf",
                "PDF", externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }
    
    public void imprimeComprobante(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (seleccion!=null) this.imprimeComp(seleccion.getCodigo(), seleccion.getNumeroComprobante());
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
     * @return the primero
     */
    public Date getPrimero() {
        return primero;
    }

    /**
     * @param primero the primero to set
     */
    public void setPrimero(Date primero) {
        this.primero = primero;
    }

    /**
     * @return the itemsComprobante
     */
    public List<Comprobante> getItemsComprobante() {
        return itemsComprobante;
    }

    /**
     * @param itemsComprobante the itemsComprobante to set
     */
    public void setItemsComprobante(List<Comprobante> itemsComprobante) {
        this.itemsComprobante = itemsComprobante;
    }

    /**
     * @return the seleccion
     */
    public Comprobante getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(Comprobante seleccion) {
        this.seleccion = seleccion;
    }


}
