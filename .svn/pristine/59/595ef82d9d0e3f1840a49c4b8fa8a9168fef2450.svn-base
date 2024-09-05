package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.SaldosCaja;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.negocio.cajas.SaldosCajaFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

@ManagedBean(name = "saldosCajaController")
@ViewScoped
public class SaldosCajaController extends AbstractController<SaldosCaja> {

    @EJB
    private SaldosCajaFacade ejbFacade;
    
    @EJB 
    private IfipAgenciaFacade ejbFacadeIfipAgencia;
    
    //------------------------------------------------------------------------
    // Metodos personalizados
    private IfipAgencia ifipAgencia;
    private Date fechaConsulta;
    
    private List<SaldosCaja> itemsSaldosCaja;
  
    @PostConstruct    
    public void init() {
        super.setFacade(ejbFacade);
        this.ifipAgencia = this.ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia());
        this.setFechaConsulta(new Date());
        this.setItemsSaldosCaja(this.ejbFacade.getItemsIfipAgenciaEstado(ActivacionUsuario.getCodigoIfipAgencia(), 'A'));
    }

    public SaldosCajaController() {
        // Inform the Abstract parent controller of the concrete SaldosCaja?cap_first Entity
        super(SaldosCaja.class);
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    /**
     * @return the fechaConsulta
     */
    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * @param fechaConsulta the fechaConsulta to set
     */
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * @return the itemsSaldosCaja
     */
    public List<SaldosCaja> getItemsSaldosCaja() {
        return itemsSaldosCaja;
    }

    /**
     * @param itemsSaldosCaja the itemsSaldosCaja to set
     */
    public void setItemsSaldosCaja(List<SaldosCaja> itemsSaldosCaja) {
        this.itemsSaldosCaja = itemsSaldosCaja;
    }

}
