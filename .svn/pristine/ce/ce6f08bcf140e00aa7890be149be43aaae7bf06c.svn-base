/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.control.ifips;

import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.FragmentoReporte;
import ec.renafipse.mks.negocio.ifips.FragmentoReporteFacade;
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

@ManagedBean(name = "FragmentoReporteController")
@ViewScoped
public class FragmentoReporteController  extends AbstractController<FragmentoReporte> implements Serializable{
    
    @EJB
    private FragmentoReporteFacade ejbFacade;
  
  private FragmentoReporte fragmentoReporte;

 public  FragmentoReporteController(){
    super(FragmentoReporte.class);
 
}
  

    @PostConstruct
    public void init() {
    super.setFacade(getEjbFacade());
    }

    /**
     * @return the ejbFacade
     */
    public FragmentoReporteFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(FragmentoReporteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<FragmentoReporte> getFragmentoReportes()
    {
        return this.ejbFacade.getItemsreporte('N');
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
     * @return the ejbFacade
     */
   

    /**
     * @param ejbFacade the ejbFacade to set
     */
   
    
}
