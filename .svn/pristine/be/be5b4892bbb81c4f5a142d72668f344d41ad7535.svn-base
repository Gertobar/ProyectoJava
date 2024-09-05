package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "certifiacdoCuentasSocioBean")
@ViewScoped
public class CertificadoCuentasSocioBean extends AbstractController<Socio> implements Serializable {

    @EJB
    private SocioFacade ejbFacade;

    @EJB
    private CuentaFacade ejbFacadeCuenta;
    
    
    private GeneraReporte generaReporte;
    
    private String buscarPor;
    private String criterio;
    
    private List<Socio> itemsSocios;
    private List<Cuenta> itemsCuentasSocio;
    
    private Socio socioSel;
    private Cuenta cuentaSel;

    

    public CertificadoCuentasSocioBean() {
        super(Socio.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        
    }
    
    /**
     *
     */
    public void obtieneSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(criterio, 6)) {
                this.setSocioSel(null);
                this.setSelected(null);
                this.setItemsSocios(this.ejbFacade.getItemsSocio(this.getCriterio(), this.getBuscarPor(), ActivacionUsuario.getCodigoIfip()));
                if (this.itemsSocios.isEmpty()) {
                    this.setItemsCuentasSocio(null);
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsSocios(null);
                this.setSocioSel(null);
                this.setSelected(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtieneSocios", CapturaError.getErrorException(ex)});
        }
    }
    
    public void obtieneCuentasSocios() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(criterio, 6)) {
                this.setItemsCuentasSocio(this.ejbFacadeCuenta.getItemsCuentasCodigoSocioCodigoIfip(this.socioSel.getSocioPK().getCodigo(), this.socioSel.getSocioPK().getCodigoIfip()));
                if (this.itemsCuentasSocio.isEmpty()) {
                    this.setSelected(null);
                }
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsCuentasSocio(null);
                this.setSelected(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtieneCuentasSocios", CapturaError.getErrorException(ex)});
        }
    }
    
        public void imprimeComprobante(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        if (socioSel.getCodigoEstadoSocio().getCodigo()!=1){
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneSocioActivo"));
            return;
        }    
        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        //System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoSocio", socioSel.getSocioPK().getCodigo());
        getGeneraReporte().getParametros().put("codigoIfip", socioSel.getSocioPK().getCodigoIfip());

        nombreReporte = "certificadoCuentas";

        getGeneraReporte().exporta("/financiero/ahorros/certificado/reporte/", nombreReporte,
                nombreReporte +  ".pdf",
                "PDF", externalContext, facesContext);

        //System.out.println("Imprimió Movimiento");
    }

    

    /**
     * @return the buscarPor
     */
    public String getBuscarPor() {
        return buscarPor;
    }

    /**
     * @param buscarPor the buscarPor to set
     */
    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    /**
     * @return the criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * @param criterio the criterio to set
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    /**
     * @return the itemsSocios
     */
    public List<Socio> getItemsSocios() {
        return itemsSocios;
    }

    /**
     * @param itemsSocios the itemsSocios to set
     */
    public void setItemsSocios(List<Socio> itemsSocios) {
        this.itemsSocios = itemsSocios;
    }

    /**
     * @return the socioSel
     */
    public Socio getSocioSel() {
        return socioSel;
    }

    /**
     * @param socioSel the socioSel to set
     */
    public void setSocioSel(Socio socioSel) {
        this.socioSel = socioSel;
    }

    /**
     * @return the itemsCuentasSocio
     */
    public List<Cuenta> getItemsCuentasSocio() {
        return itemsCuentasSocio;
    }

    /**
     * @param itemsCuentasSocio the itemsCuentasSocio to set
     */
    public void setItemsCuentasSocio(List<Cuenta> itemsCuentasSocio) {
        this.itemsCuentasSocio = itemsCuentasSocio;
    }

    /**
     * @return the cuentaSel
     */
    public Cuenta getCuentaSel() {
        return cuentaSel;
    }

    /**
     * @param cuentaSel the cuentaSel to set
     */
    public void setCuentaSel(Cuenta cuentaSel) {
        this.cuentaSel = cuentaSel;
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


}
