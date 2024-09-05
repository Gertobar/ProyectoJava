/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.ifips.bean;


import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.ifips.Boveda;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.ifips.MovimientoBoveda;
import ec.renafipse.mks.negocio.ifips.BovedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.ifips.MovimientoBovedaFacade;
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

/**
 *
 * @author vastudillo
 */
@ManagedBean(name = "reporteMovmientosBovedaBean")
@ViewScoped
public class ReporteMovmientosBovedaBean extends AbstractController<MovimientoBoveda> implements Serializable {

    @EJB
    private MovimientoBovedaFacade ejbFacade;
  
    @EJB
    private IfipFacade ejbFacadeIfip;
    
    @EJB
    private BovedaFacade ejbFacadeBoveda;
    
    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    private Date fechaMovimiento;
    private Date fechaActual;
    private Boveda boveda;
    private GeneraReporte generaReporte;

    private List<Boveda> itemsBovedas;

    public ReporteMovmientosBovedaBean() {
        super(MovimientoBoveda.class);

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.fechaActual = new Date();
        this.itemsBovedas =  this.ejbFacadeBoveda.getItemsResponsableIfipAgenciaEliminado(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoUsuario(), 'N');
        //System.out.println("CodigoIfipAgencia: "+ActivacionUsuario.getCodigoIfipAgencia()+" getCodigoUsuario: "+ActivacionUsuario.getCodigoUsuario()+" N");
        //System.out.println("itemsBoedas"+itemsBovedas);
    }

       
    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
       
        String nombreReporte = "movimientosBoveda";
        Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        String nombreIfip = ifip.getCodigoTipoIfip().getNombre() + " " + ifip.getNombre();
        IfipAgencia agencia = ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("nombreIfip", nombreIfip);       
        getGeneraReporte().getParametros().put("agencia", agencia.getNombre());
        getGeneraReporte().getParametros().put("codigoIfipAgencia", ActivacionUsuario.getCodigoIfipAgencia());
        getGeneraReporte().getParametros().put("fechaMovimiento", this.fechaMovimiento);
        getGeneraReporte().getParametros().put("codigoBoveda", (this.boveda != null) ? boveda.getCodigo().toString() : "'%'");

        getGeneraReporte().exporta("/financiero/reportes/movmientosBoveda/reporte/", nombreReporte,
                "listadoMovimientosBoveda" + ".pdf",
                "PDF", externalContext, facesContext);
        ////System.out.println("impresion dada");

    }
    // -- FIN DE SOCIO
    // ----------------------------------------------------------------------------------

  

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
     * @return the fechaMovimiento
     */
    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * @param fechaMovimiento the fechaMovimiento to set
     */
    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
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
     * @return the itemsBovedas
     */
    public List<Boveda> getItemsBovedas() {
        return itemsBovedas;
    }

    /**
     * @param itemsBovedas the itemsBovedas to set
     */
    public void setItemsBovedas(List<Boveda> itemsBovedas) {
        this.itemsBovedas = itemsBovedas;
    }

}
