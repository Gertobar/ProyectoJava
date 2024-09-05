package ec.renafipse.mks.control.reporteria;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.contables.Comprobante;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "saldoAhorrosBean")
@ViewScoped
public class SaldoAhorrosBean extends AbstractController<Comprobante> implements Serializable {

    private Date fechaCorte;

    private GeneraReporte generaReporte;

    @PostConstruct
    public void init() {
    }
    

    public void imprime(Long codIfip,Date fechaCorte, String extension, String tipo) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        nombreReporte = "saldosAhorros";
        getGeneraReporte().getParametros().put("codigoIfip", codIfip);
        getGeneraReporte().getParametros().put("fechaCorte", fechaCorte);
        String ruta=externalContext.getRealPath("/reporteria/financiero/saldosAhorros/reporte/"+nombreReporte+".jasper");
        ruta=ruta.substring(0, ruta.lastIndexOf(nombreReporte));
        getGeneraReporte().getParametros().put("SUBREPORT_DIR", ruta);

        getGeneraReporte().exporta("/reporteria/financiero/saldosAhorros/reporte/", nombreReporte,
                nombreReporte+"Financiero"+ extension,
                tipo, externalContext, facesContext);

        ////System.out.println("Imprimió Movimiento");
    }
    
    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.imprime(ActivacionUsuario.getCodigoIfip(),this.fechaCorte, ".pdf", "PDF");
        
    }

    public void imprimeExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        this.imprime(ActivacionUsuario.getCodigoIfip(),this.fechaCorte, ".xls", "XLS");
        
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

}
