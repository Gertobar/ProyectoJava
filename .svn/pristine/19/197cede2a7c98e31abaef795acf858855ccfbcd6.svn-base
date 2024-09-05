package ec.renafipse.mks.control.reporteria;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.modelo.contables.PeriodoContable;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

@ManagedBean(name = "saldoBovedaContableBean")
@ViewScoped
public class SaldoBovedaContableBean extends AbstractController<Comprobante> implements Serializable {

    @EJB
    private PeriodoContableFacade ejbFacadePeriodoContable;
    
    
    private List <PeriodoContable> itemsPeriodoContable;
    private Date fechaInicio;
    private Date fechaCorte;
    private String periodoContable;

    private GeneraReporte generaReporte;

    @PostConstruct
    public void init() {
        setItemsPeriodoContable(ejbFacadePeriodoContable.findAll());
    }
    

    public void imprime(Long codIfip, String periodo,Date fecInicio,Date fecFin, String extension, String tipo) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        nombreReporte = "saldosBoveda";
        getGeneraReporte().getParametros().put("codigoIfip", codIfip);
        getGeneraReporte().getParametros().put("periodo", periodo);
        getGeneraReporte().getParametros().put("fechaInicio", fecInicio);
        getGeneraReporte().getParametros().put("fechaCorte", fecFin);
        String ruta=externalContext.getRealPath("/reporteria/contable/saldosBoveda/reporte/"+nombreReporte+".jasper");
        ruta=ruta.substring(0, ruta.lastIndexOf(nombreReporte));
        getGeneraReporte().getParametros().put("SUBREPORT_DIR", ruta);

        getGeneraReporte().exporta("/reporteria/contable/saldosBoveda/reporte/", nombreReporte,
                nombreReporte+"Contable"+ extension,
                tipo, externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }
    
    
    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        fechaInicio();
        this.imprime(ActivacionUsuario.getCodigoIfip(),this.getPeriodoContable(),this.fechaInicio,this.fechaCorte, ".pdf", "PDF");
        
    }

    public void imprimeExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        fechaInicio();
        this.imprime(ActivacionUsuario.getCodigoIfip(),this.getPeriodoContable(),this.fechaInicio,this.fechaCorte, ".xls", "XLS");
        
    }
    
    private void fechaInicio() {
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = "01/01/" + this.periodoContable;
            fechaInicio = formateador.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(ReclasificacionDPFSContableBean.class.getName()).log(Level.SEVERE, null, ex);
        }

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
     * @return the fechaFin
     */
    public Date getFechaCorte() {
        return fechaCorte;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaCorte(Date fechaFin) {
        this.fechaCorte = fechaFin;
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
     * @return the periodoContable
     */
    public String getPeriodoContable() {
        return periodoContable;
    }

    /**
     * @param periodoContable the periodoContable to set
     */
    public void setPeriodoContable(String periodoContable) {
        this.periodoContable = periodoContable;
    }

    /**
     * @return the itemsPeriodoContable
     */
    public List <PeriodoContable> getItemsPeriodoContable() {
        return itemsPeriodoContable;
    }

    /**
     * @param itemsPeriodoContable the itemsPeriodoContable to set
     */
    public void setItemsPeriodoContable(List <PeriodoContable> itemsPeriodoContable) {
        this.itemsPeriodoContable = itemsPeriodoContable;
    }

}
