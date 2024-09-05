package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.RetiroCheque;
import ec.renafipse.mks.negocio.ahorros.RetiroChequeFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "anulaChequeGiradoBean")
@ViewScoped
public class AnulaChequeGiradoBean extends AbstractController<RetiroCheque> implements Serializable {

    @EJB
    private RetiroChequeFacade ejbFacade;

    //--------------------------------------------------------------------------
    // --PARAMETROS
    private RetiroCheque retiroCheque;    
    private LlamaSP llamaSP;
    private RequestContext context;
    private String msg;

    private List<RetiroCheque> itemsRetiroCheques;

    public AnulaChequeGiradoBean() {
        super(RetiroCheque.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        if (ActivacionUsuario.getCodigoComputador() == null) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"anulaChequeGiradoBean", "init", CapturaError.getErrorException(ex)});
            }
        }
        this.retiroCheque = null;
        llamaSP = new LlamaSP();
        this.buscaRetirosChequesGirados();
    }

    /**
     * Busca los Cheques Girados.
     */
    public void buscaRetirosChequesGirados() {     
        
        this.setItemsRetiroCheques(ejbFacade.getItemsCodigiIfipCodigoAgenciaEstado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'G'));
        this.setRetiroCheque(null);
         
        
    }

    //------------------------------------------------------------------------
    // GUARDA LA ANULACION DEL CHEQUE
    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR LA ANULACION
    public void guardaAnulacion(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();

        if (this.getRetiroCheque() == null) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCheque"));
            context.execute("procesandoDlg.hide()");
            return;
        }
        try {


            
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            java.sql.Timestamp fechaEntrega = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_retiro_cheque.p_anula");
            llamaSP.setNumeroParametros(8);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", this.retiroCheque.getTalonarioChequeDetalle().getTalonarioCheque().getIfipCuentaEntidadFinanciera().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque", this.retiroCheque.getTalonarioChequeDetalle().getTalonarioChequeDetallePK().getNumeroCheque()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_retiro", this.retiroCheque.getCodigo()});                        
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.retiroCheque.getCodigoCuenta().getCodigo()});                        
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", this.retiroCheque.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fechaEntrega});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "A"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
            
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeAnulado");
                MuestraMensaje.addInformacion(msg);
                
                this.init();
                
                //context.execute("MovimientoBovedaFor.update()");
            } else {
                context.execute("procesandoDlg.hide()");
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
                    new Object[]{"anulaChequeGiradoBean", "guardaAnulacion", CapturaError.getErrorException(ex)});
        }

    }

    

    /**
     * @return the itemsRetiroCheques
     */
    public List<RetiroCheque> getItemsRetiroCheques() {
        return itemsRetiroCheques;
    }

    /**
     * @param itemsRetiroCheques the itemsRetiroCheques to set
     */
    public void setItemsRetiroCheques(List<RetiroCheque> itemsRetiroCheques) {
        this.itemsRetiroCheques = itemsRetiroCheques;
    }

    /**
     * @return the retiroCheque
     */
    public RetiroCheque getRetiroCheque() {
        return retiroCheque;
    }

    /**
     * @param retiroCheque the retiroCheque to set
     */
    public void setRetiroCheque(RetiroCheque retiroCheque) {
        this.retiroCheque = retiroCheque;
    }

}
