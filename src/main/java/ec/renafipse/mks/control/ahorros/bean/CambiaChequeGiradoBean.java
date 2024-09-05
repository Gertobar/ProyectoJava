package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.CambioChequeGirado;
import ec.renafipse.mks.modelo.ahorros.RetiroCheque;
import ec.renafipse.mks.modelo.ahorros.TalonarioChequeDetalle;
import ec.renafipse.mks.modelo.ahorros.TalonarioChequeDetallePK;
import ec.renafipse.mks.modelo.ahorros.TipoMotivoCambioCheque;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.negocio.ahorros.RetiroChequeFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioChequeDetalleFacade;
import ec.renafipse.mks.negocio.ahorros.TipoMotivoCambioChequeFacade;
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

@ManagedBean(name = "cambiaChequeGiradoBean")
@ViewScoped
public class CambiaChequeGiradoBean extends AbstractController<RetiroCheque> implements Serializable {

    @EJB
    private RetiroChequeFacade ejbFacade;

    @EJB
    private TipoMotivoCambioChequeFacade ejbFacadeTipoMotivoCambioCheque;
    
    @EJB
    private TalonarioChequeDetalleFacade ejbFacadeTalonarioChequeDetalle;
    
    //--------------------------------------------------------------------------
    // --PARAMETROS
    private RetiroCheque retiroCheque;    
    private CambioChequeGirado cambioChequeGirado;
    private TipoMotivoCambioCheque tipoMotivoCambioCheque;
    private LlamaSP llamaSP;
    private RequestContext context;
    private String msg;

    private List<RetiroCheque> itemsRetiroCheques;

    public CambiaChequeGiradoBean() {
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
                    new Object[]{"cambiaChequeGiradoBean", "init", CapturaError.getErrorException(ex)});
            }
        }
        this.retiroCheque = null;
        llamaSP = new LlamaSP();
        this.buscaRetirosChequesGirados();
        this.cambioChequeGirado = new CambioChequeGirado();
    }

    /**
     * Busca los Cheques Girados.
     */
    public void buscaRetirosChequesGirados() {     
        //System.out.println("BUsca Cheques");
        this.setItemsRetiroCheques(ejbFacade.getItemsCodigiIfipCodigoAgenciaEstadoUnoEstadoDos(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'G','E'));
        this.setRetiroCheque(null);
         
        
    }
    
    /**
     * Valida el nuevo de cheque a cambiar
     * @return true o false
     */
     private boolean validaNumeroCheque() {
        this.msg = null;
        if (this.cambioChequeGirado.getNumeroChequeNuevo() == this.retiroCheque.getTalonarioChequeDetalle().getTalonarioChequeDetallePK().getNumeroCheque())
        {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeDebeSerDiferente");
            return false;
        }
        //System.out.println("ifipCuentaEntidadFinancieraSel " + retiroCheque.getTalonarioChequeDetalle().getTalonarioCheque().getIfipCuentaEntidadFinanciera());
        TalonarioChequeDetalle talonarioChequeDetalle = this.ejbFacadeTalonarioChequeDetalle.find(new TalonarioChequeDetallePK(this.cambioChequeGirado.getNumeroChequeNuevo(), retiroCheque.getTalonarioChequeDetalle().getTalonarioCheque().getIfipCuentaEntidadFinanciera().getCodigo()));
        //System.out.println("TalonarioChequeDetalle " + talonarioChequeDetalle);
        if (talonarioChequeDetalle != null) {
            if (talonarioChequeDetalle.getEstado() != 'P') {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeDiferentePendiente");
                return false;
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroChequeNoExiste");
            return false;
        }

        return true;
    }
     
     /**
      * Comprueba el numero cheque.
      */
     public void compruebaCheque() {
        if (!validaNumeroCheque()) {
            MuestraMensaje.addError(msg);
        }

    }
     
     public void preparaCambiarCheque(ActionEvent actionEvent)
     {
         this.cambioChequeGirado = new CambioChequeGirado();
         this.tipoMotivoCambioCheque = null;
         
     }

    //------------------------------------------------------------------------
    // GUARDA LA ANULACION DEL CHEQUE
    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR LA ANULACION
    public void guardaCambioCheque(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        
        if (!this.validaNumeroCheque())
        {
            context.execute("procesandoDlg.hide()");
            return;
        }

        if (this.getRetiroCheque() == null) {
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCheque"));
            context.execute("procesandoDlg.hide()");
            return;
        }
        try {

            //System.out.println("Cehque cambiando");
            
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            java.sql.Timestamp fechaCambio = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_retiro_cheque.p_cambia");
            llamaSP.setNumeroParametros(9);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", this.retiroCheque.getTalonarioChequeDetalle().getTalonarioCheque().getIfipCuentaEntidadFinanciera().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque_anterior", this.retiroCheque.getTalonarioChequeDetalle().getTalonarioChequeDetallePK().getNumeroCheque()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque_nuevo", this.cambioChequeGirado.getNumeroChequeNuevo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_retiro", this.retiroCheque.getCodigo()});                        
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_motivo", this.tipoMotivoCambioCheque.getCodigo()});                        
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.cambioChequeGirado.getObservaciones()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_cambio", fechaCambio});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cambiado_por", ActivacionUsuario.getCodigoUsuario()});                        
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_retiro", String.valueOf(this.retiroCheque.getEstado())});
            
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeCambiado");
                MuestraMensaje.addInformacion(msg);
                
                this.init();
                context.execute("procesandoDlg.hide()");
                context.execute("CrearCambioDialogo.hide()");
                
                
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
                    new Object[]{"cambiaChequeGiradoBean", "guardaCambioCheque", CapturaError.getErrorException(ex)});
        }

    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<TipoMotivoCambioCheque> getItemsTipoMotivoCambioCheque() {
        return this.ejbFacadeTipoMotivoCambioCheque.getItemsEliminado('N');
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

    /**
     * @return the cambioChequeGirado
     */
    public CambioChequeGirado getCambioChequeGirado() {
        return cambioChequeGirado;
    }

    /**
     * @param cambioChequeGirado the cambioChequeGirado to set
     */
    public void setCambioChequeGirado(CambioChequeGirado cambioChequeGirado) {
        this.cambioChequeGirado = cambioChequeGirado;
    }

    /**
     * @return the tipoMotivoCambioCheque
     */
    public TipoMotivoCambioCheque getTipoMotivoCambioCheque() {
        return tipoMotivoCambioCheque;
    }

    /**
     * @param tipoMotivoCambioCheque the tipoMotivoCambioCheque to set
     */
    public void setTipoMotivoCambioCheque(TipoMotivoCambioCheque tipoMotivoCambioCheque) {
        this.tipoMotivoCambioCheque = tipoMotivoCambioCheque;
    }

}
