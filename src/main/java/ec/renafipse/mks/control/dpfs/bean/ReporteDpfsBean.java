/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.dpfs.bean;


import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.dpfs.ContratoDpf;
import ec.renafipse.mks.modelo.ifips.Boveda;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.negocio.dpfs.ContratoDpfFacade;
import ec.renafipse.mks.negocio.ifips.BovedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
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
@ManagedBean(name = "reporteDpfsBean")
@ViewScoped
public class ReporteDpfsBean extends AbstractController<ContratoDpf> implements Serializable {

    @EJB
    private ContratoDpfFacade ejbFacade;
  
    @EJB
    private IfipFacade ejbFacadeIfip;
    
    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;
    
    @EJB
    private BovedaFacade ejbFacadeBoveda;

    private Date fechaInicio;
    private Date fechaFin;
    private String estadoContrato;
    private Date fechaActual;
    private Boveda boveda;
    private GeneraReporte generaReporte;

    private List<Boveda> itemsBovedas;

    public ReporteDpfsBean() {
        super(ContratoDpf.class);

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.fechaActual = new Date();
        this.itemsBovedas =  this.ejbFacadeBoveda.getItemsResponsableIfipAgenciaEliminado(ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoUsuario(), 'N');
    }

    
    public void imprimePdf(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
       
        String nombreReporte = "dpfs";
        Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        IfipAgencia agencia = ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia());
        
        String nombreIfip = ifip.getCodigoTipoIfip().getNombre() + " " + ifip.getNombre();
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("nombreIfip", nombreIfip);       
        getGeneraReporte().getParametros().put("codigoIfipAgencia", ActivacionUsuario.getCodigoIfipAgencia());
        getGeneraReporte().getParametros().put("codigoIfip", ActivacionUsuario.getCodigoIfip());
        getGeneraReporte().getParametros().put("fechaInicio", this.fechaInicio);
        getGeneraReporte().getParametros().put("fechaFin", this.fechaFin);
        getGeneraReporte().getParametros().put("agencia", agencia.getNombre());
        getGeneraReporte().getParametros().put("estadoContrato", (this.estadoContrato != null) ? ((!this.estadoContrato.trim().equals("")) ? this.estadoContrato : "%") : "%");

        getGeneraReporte().exporta("/financiero/reportes/dpfs/reporte/", nombreReporte,
                "listadoDpfs" +  ".pdf",
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
     * @return the estadoContrato
     */
    public String getEstadoContrato() {
        return estadoContrato;
    }

    /**
     * @param estadoContrato the estadoContrato to set
     */
    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

}
