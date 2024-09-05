package ec.renafipse.mks.control.etapa;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.ifips.*;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.etapa.DescuentoSocio;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfe;
import ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgencia;
import ec.renafipse.mks.negocio.etapa.DescuentoSocioFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.socios.GrupoInstitucionIfipAgenciaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
import org.primefaces.context.RequestContext;

@ManagedBean(name = "descuentSocioController")
@ViewScoped
public class DescuentSocioController extends AbstractController<DescuentoSocio> implements Serializable {

    @EJB
    private DescuentoSocioFacade ejbFacade;

    @EJB
    private GrupoInstitucionIfipAgenciaFacade ejbFacadeGrupoInstitucionIfipAgencia;

    private LlamaSP llamaSP;

    private List<DescuentoSocio> itemsDescuentoSocio;
    private GrupoInstitucionIfipAgencia grupoInstitucionIfipAgencia;
    private Date fechaInicio;
    private Date fechaFin;
    private String vencidas;
    private String ahorros;
    private String msg;
    private GeneraReporte generaReporte;
    private Date fechaActual;
    private Timestamp fechaGeneracion;

    public DescuentSocioController() {
        super(DescuentoSocio.class);
        llamaSP = new LlamaSP();
        this.fechaActual = new Date();
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    public void generaDescuentos() throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
        llamaSP = new LlamaSP();
        try {

            //context.execute("procesandoDlg.show()");
            Date fecGen = new Date();
            fechaGeneracion = new java.sql.Timestamp(fecGen.getTime());

            //context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_etapa.pkm_genera_descuentos.p_genera_descuentos");
            llamaSP.setNumeroParametros(8);
            
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pd_fecha_inicio", new java.sql.Timestamp(fechaInicio.getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pd_fecha_fin", new java.sql.Timestamp(this.fechaFin.getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pv_ahorros", this.ahorros});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pv_vencidas", this.vencidas});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_codigo_grupo", this.grupoInstitucionIfipAgencia.getGrupoInstitucion().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pn_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pd_fecha_generacion", fechaGeneracion});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();
            
            if (llamaSP.isEjecucionCorrecta()) {

                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                
                // Obteniendo los descuentos generados
                this.itemsDescuentoSocio =  this.ejbFacade.getItemsFechaCorte(fecGen);
                //System.out.println("itemsDescuentoSocio "+itemsDescuentoSocio);
                
                //context.execute("procesandoDlg.hide()");
                
                
                        //context.execute("MovimientoBovedaFor.update()");

            } else {
                //context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"movimientoBovedaController", "guardarMovimiento", CapturaError.getErrorException(ex)});
        }
    }

     public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;
       
        String fechaI = String.valueOf(this.fechaInicio.getDate())+String.valueOf((this.fechaInicio.getMonth()+1))+String.valueOf((this.fechaInicio.getYear()+1900));
        String fechaF = String.valueOf(this.fechaFin.getDate())+String.valueOf((this.fechaFin.getMonth()+1))+String.valueOf((this.fechaFin.getYear()+1900));
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("fechaCorte", this.fechaGeneracion);
        getGeneraReporte().getParametros().put("codigo_grupo", this.grupoInstitucionIfipAgencia.getGrupoInstitucion().getCodigo());
        nombreReporte = "descuento";

        getGeneraReporte().exporta("/financiero/socios/generacionDescuento/reporte/", nombreReporte,
                nombreReporte + fechaI+"-"+ fechaF+ ".xls",
                "XLS", externalContext, facesContext);
    }
    // ------------------------------------------------------------------------
    // -- COMBOS
    public List<GrupoInstitucionIfipAgencia> getItemsGrupoInstitucionIfipAgencia() {
        return this.ejbFacadeGrupoInstitucionIfipAgencia.getItemsGrupoIfipAgenciaEliminado(ActivacionUsuario.getCodigoIfipAgencia(), 'N');
    }

    /**
     * @return the itemsDescuentoSocio
     */
    public List<DescuentoSocio> getItemsDescuentoSocio() {
        return itemsDescuentoSocio;
    }

    /**
     * @param itemsDescuentoSocio the itemsDescuentoSocio to set
     */
    public void setItemsDescuentoSocio(List<DescuentoSocio> itemsDescuentoSocio) {
        this.itemsDescuentoSocio = itemsDescuentoSocio;
    }

    /**
     * @return the grupoInstitucionIfipAgencia
     */
    public GrupoInstitucionIfipAgencia getGrupoInstitucionIfipAgencia() {
        return grupoInstitucionIfipAgencia;
    }

    /**
     * @param grupoInstitucionIfipAgencia the grupoInstitucionIfipAgencia to set
     */
    public void setGrupoInstitucionIfipAgencia(GrupoInstitucionIfipAgencia grupoInstitucionIfipAgencia) {
        this.grupoInstitucionIfipAgencia = grupoInstitucionIfipAgencia;
    }

    
    /**
     * @return the vencidas
     */
    public String getVencidas() {
        return vencidas;
    }

    /**
     * @param vencidas the vencidas to set
     */
    public void setVencidas(String vencidas) {
        this.vencidas = vencidas;
    }

    /**
     * @return the ahorros
     */
    public String getAhorros() {
        return ahorros;
    }

    /**
     * @param ahorros the ahorros to set
     */
    public void setAhorros(String ahorros) {
        this.ahorros = ahorros;
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

}
