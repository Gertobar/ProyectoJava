/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.FragmentoReporte;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.Reporte;
import ec.renafipse.mks.modelo.ifips.ReporteFragmentoIfip;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.ifips.ReporteFragmentoIfipFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author scordero
 */
@ManagedBean(name = "ReporteFragmentoIfipController")
@ViewScoped
public class FragmentoreporteIfipController extends AbstractController<ReporteFragmentoIfip> implements Serializable {
    
   @EJB
   private ReporteFragmentoIfipFacade ejbFacade;
     @EJB
    private IfipFacade ejbFacadeIfip;
     
     private Reporte reporte;
     private FragmentoReporte fragmentoReporte;
     private ReporteFragmentoIfip reporteFragmentoIfip;
     private long codigoReporte;
     private long codigoFragentoReporte;
     
   private Ifip ifip;
   
   private List<ReporteFragmentoIfip> itemsReporteFragmentoIfip;
  
    /**
     * @return the ejbReporteFragmentoIfipFacade
     */
// @Override
//    protected void setEmbeddableKeys() {
//    getSelected().getReporteFragmentoIfipPK().setCodigoFragmento(fragmentoReporte.getCodigo());
//    getSelected().getReporteFragmentoIfipPK().setCodigoReporte(reporte.getCodigo());
//  
//            
//    }
//    
//       @Override
//    protected void initializeEmbeddableKey() {
//        
//        this.getSelected().setReporteFragmentoIfipPK(new ec.renafipse.mks.modelo.ifips.ReporteFragmentoIfipPK());
//    }

        @PostConstruct
    public void init() {
       
    super.setFacade(getEjbFacade());
   
         reporte=new Reporte();
    fragmentoReporte=new FragmentoReporte();
    
            setIfip(ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()));
            
       
    }

    /**
     * @param ejbReporteFragmentoIfipFacade the ejbReporteFragmentoIfipFacade to set
     */
   

    /**
     * @return the ifip
     */
    
    public void guardar()
    {
       
        
        this.ejbFacade.edit(reporteFragmentoIfip);
    }
    
    public void buscarReporte()
    {
          
        if(this.reporte!=null && this.fragmentoReporte!=null)
        {
      
              this.setReporteFragmentoIfip(this.ejbFacade.getFragmento(reporte.getCodigo(), fragmentoReporte.getCodigo(), ifip.getCodigo()));
         }
        
    }
    
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    /**
     * @return the itemsReporteFragmentoIfip
     */


    /**
     * @return the ejbFacade
     */
    public ReporteFragmentoIfipFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ReporteFragmentoIfipFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ejbFacadeIfip
     */
    public IfipFacade getEjbFacadeIfip() {
        return ejbFacadeIfip;
    }

    /**
     * @param ejbFacadeIfip the ejbFacadeIfip to set
     */
    public void setEjbFacadeIfip(IfipFacade ejbFacadeIfip) {
        this.ejbFacadeIfip = ejbFacadeIfip;
    }

    /**
     * @return the codigoFragmento
     */


    /**
     * @return the codigoReporte
     */
 
    /**
     * @return the itemsReporteFragmentoIfip
     */
    public List<ReporteFragmentoIfip> getItemsReporteFragmentoIfip() {
        return itemsReporteFragmentoIfip;
    }

    /**
     * @param itemsReporteFragmentoIfip the itemsReporteFragmentoIfip to set
     */
    public void setItemsReporteFragmentoIfip(List<ReporteFragmentoIfip> itemsReporteFragmentoIfip) {
        this.itemsReporteFragmentoIfip = itemsReporteFragmentoIfip;
    }

    /**
     * @return the codigoIfip
     */


    /**
     * @return the reporteFragmentoIfip
     */
    public ReporteFragmentoIfip getReporteFragmentoIfip() {
        return reporteFragmentoIfip;
    }

    /**
     * @param reporteFragmentoIfip the reporteFragmentoIfip to set
     */
    public void setReporteFragmentoIfip(ReporteFragmentoIfip reporteFragmentoIfip) {
        this.reporteFragmentoIfip = reporteFragmentoIfip;
    }

    /**
     * @return the reporte
     */
    public Reporte getReporte() {
        return reporte;
    }

    /**
     * @param reporte the reporte to set
     */
    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    /**
     * @return the fragmentoReporte
     */
    public FragmentoReporte getFragmentoReporte() {
        return fragmentoReporte;
    }

    /**
     * @param fragmentoReporte the fragmentoReporte to set
     */
    public void setFragmentoReporte(FragmentoReporte fragmentoReporte) {
        this.fragmentoReporte = fragmentoReporte;
    }

    /**
     * @return the codigoReporte
     */
    public long getCodigoReporte() {
        return codigoReporte;
    }

    /**
     * @param codigoReporte the codigoReporte to set
     */
    public void setCodigoReporte(long codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    /**
     * @return the codigoFragentoReporte
     */
    public long getCodigoFragentoReporte() {
        return codigoFragentoReporte;
    }

    /**
     * @param codigoFragentoReporte the codigoFragentoReporte to set
     */
    public void setCodigoFragentoReporte(long codigoFragentoReporte) {
        this.codigoFragentoReporte = codigoFragentoReporte;
    }

    /**
     * @return the frgamentoReporte
     */


    /**
     * @return the codigoIfip
     */


    /**
     * @return the ifip
     */
 
    /**
     * @param ifip the ifip to set
     */

}
