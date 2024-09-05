/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.FragmentoReporte;
import ec.renafipse.mks.modelo.ifips.Reporte;

import ec.renafipse.mks.negocio.ifips.ReporteFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author santiago
 */

@ManagedBean(name = "ReporteController")
@ViewScoped
public class ReporteController  extends AbstractController<Reporte> implements Serializable{
    
    @EJB
    private ReporteFacade ejbFacade;
    
    private Reporte reporte;
    private FragmentoReporte fragmentoReporte;
    

 public  ReporteController(){
    super(Reporte.class);
}
   

    @PostConstruct
    public void init() {
    super.setFacade(getEjbFacade());
    }
        public List<Reporte>getReportes()
    {
        return this.ejbFacade.getItemsreporte('N');
    }

    /**
     * @return the ejbFacade
     */
    public ReporteFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ReporteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
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

 
    
    
}
