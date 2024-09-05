package ec.renafipse.mks.control.creditos.bean;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import ec.renafipse.mks.negocio.creditos.EstadoCreditoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "reporteCreditosContratadosBean")
@ViewScoped
public class ReporteCreditoContratadoBean extends AbstractController<Comprobante> implements Serializable {

    @EJB
    private EstadoCreditoFacade ejbFacadeEstadoCredito;
    
    private List<EstadoCredito> itemsEstadoCredito;
    private EstadoCredito estadoCredito;
    private Date fechaInicio;
    private Date fechaFin;

    private GeneraReporte generaReporte;
    
    

    @PostConstruct
    public void init() {
        this.setItemsEstadoCredito(this.ejbFacadeEstadoCredito.getItemsEliminado('N'));
    }
    

    public void imprime(Long codIfip,Date fechaInicio,Date fechaFin,long codigoEstado, String extension, String tipo) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        nombreReporte = "creditosContratados";
        getGeneraReporte().getParametros().put("codigoIfip", codIfip);
        getGeneraReporte().getParametros().put("fechaInicio", fechaInicio);
        getGeneraReporte().getParametros().put("fechaFin", fechaFin);
        getGeneraReporte().getParametros().put("codigoEstado", codigoEstado);
        getGeneraReporte().getParametros().put("codigoIfipAgencia", ActivacionUsuario.getCodigoIfipAgencia());
        String ruta=externalContext.getRealPath("/financiero/reportes/creditos/reporte/"+nombreReporte+".jasper");
        ruta=ruta.substring(0, ruta.lastIndexOf(nombreReporte));
        getGeneraReporte().getParametros().put("SUBREPORT_DIR", ruta);

        getGeneraReporte().exporta("/financiero/reportes/creditos/reporte/", nombreReporte,
                nombreReporte+ extension,
                tipo, externalContext, facesContext);

    }
    
    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.imprime(ActivacionUsuario.getCodigoIfip(),this.fechaInicio,this.fechaFin,this.estadoCredito.getCodigo(), ".pdf", "PDF");
        
    }

    public void imprimeExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.imprime(ActivacionUsuario.getCodigoIfip(),this.fechaInicio,this.fechaFin,this.estadoCredito.getCodigo(), ".xls", "XLS");
        
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
     * @return the itemsEstadoCredito
     */
    public List<EstadoCredito> getItemsEstadoCredito() {
        return itemsEstadoCredito;
    }

    /**
     * @param itemsEstadoCredito the itemsEstadoCredito to set
     */
    public void setItemsEstadoCredito(List<EstadoCredito> itemsEstadoCredito) {
        this.itemsEstadoCredito = itemsEstadoCredito;
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
     * @return the estadoCredito
     */
    public EstadoCredito getEstadoCredito() {
        return estadoCredito;
    }

    /**
     * @param estadoCredito the estadoCredito to set
     */
    public void setEstadoCredito(EstadoCredito estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

}
