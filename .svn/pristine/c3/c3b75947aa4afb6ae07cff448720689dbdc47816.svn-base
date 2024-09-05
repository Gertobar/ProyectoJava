package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.RetiroCheque;
import ec.renafipse.mks.modelo.cajas.ChequeDepositado;
import ec.renafipse.mks.negocio.ahorros.RetiroChequeFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "darDeBajaChequeGiradoBean")
@ViewScoped
public class DarDeBajaChequeGiradoBean extends AbstractController<RetiroCheque> implements Serializable {

    @EJB
    private RetiroChequeFacade ejbFacade;

    //--------------------------------------------------------------------------
    // --PARAMETROS
    private RetiroCheque retiroCheque;
    private LlamaSP llamaSP;
    private RequestContext context;
    private String msg;

    private boolean deshabilitarBotonDarDeBaja;

    private List<RetiroCheque> itemsRetiroCheques;
    private List<RetiroCheque> itemsRetiroChequesSeleccionado;

    public DarDeBajaChequeGiradoBean() {
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
                    new Object[]{"darDeBajaChequeGiradoBean", "init", CapturaError.getErrorException(ex)});
            }
        }
        this.retiroCheque = null;
        this.deshabilitarBotonDarDeBaja = true;
        llamaSP = new LlamaSP();
        this.buscaRetirosChequesGirados();
    }

    /**
     * Busca los Cheques Girados.
     */
    public void buscaRetirosChequesGirados() {
        // Buscando Cheques Entregados
        this.setItemsRetiroCheques(ejbFacade.getItemsCodigiIfipCodigoAgenciaEstado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), 'E'));
        this.setRetiroCheque(null);
        this.deshabilitarBotonDarDeBaja = true;

    }

    public void selecciona(SelectEvent event) {
        this.deshabilitaBotonDarDeBaja();
    }

    public void selecciona(UnselectEvent unselectEvent) {
        this.deshabilitaBotonDarDeBaja();

    }

    public void selecciona(ToggleSelectEvent event) {
        this.deshabilitaBotonDarDeBaja();

    }

    private void deshabilitaBotonDarDeBaja() {
        this.deshabilitarBotonDarDeBaja = false;
        if (this.itemsRetiroChequesSeleccionado.isEmpty()) {
            this.deshabilitarBotonDarDeBaja = true;
        }
    }
        //------------------------------------------------------------------------
    // GUARDA LAS BAJAS DE LOS CHEQUES GIRADOS
    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR LA ANULACION
    public void guardaDarDeBaja(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();

        //System.out.println("items de baja "+this.itemsRetiroChequesSeleccionado);
        if (this.itemsRetiroChequesSeleccionado.isEmpty()) {
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

            
            // Guardando el detalle de retiros
            guardaDesgloceRetiros();
            
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequesBajados");
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
            ex.printStackTrace();
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"darDeBajaChequeGiradoBean", "guardaDarDeBaja", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * GUARDA EL DESGLOCE DE LOS CHEQUES REGISTRADOS EN EL MOVIMIENTO.
     *
     * @return
     */
    private boolean guardaDesgloceRetiros() {

        java.sql.Timestamp fechaDeBaja = new java.sql.Timestamp(new Date().getTime());
        
        if (!this.itemsRetiroChequesSeleccionado.isEmpty()) {
            llamaSP.setNombreSP("mks_ahorros.pkm_retiro_cheque.p_baja");
            llamaSP.setNumeroParametros(6);
            // Insertando degloce del movimiento 
            for (RetiroCheque rc : this.itemsRetiroChequesSeleccionado) {
                
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", rc.getCodigoCuentaEntFin()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque", rc.getNumeroCheque()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_retiro", rc.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fechaDeBaja});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "C"});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado_colocado_por", ActivacionUsuario.getCodigoUsuario()});
                
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

                // Invocando al SP
                llamaSP.invocaSP();

                // Verificando si el desgloce no dio errores
                if (!llamaSP.isEjecucionCorrecta()) {
                    break;
                }
            }
        }
        return llamaSP.isEjecucionCorrecta();
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
     * @return the itemsRetiroChequesSeleccionado
     */
    public List<RetiroCheque> getItemsRetiroChequesSeleccionado() {
        return itemsRetiroChequesSeleccionado;
    }

    /**
     * @param itemsRetiroChequesSeleccionado the itemsRetiroChequesSeleccionado
     * to set
     */
    public void setItemsRetiroChequesSeleccionado(List<RetiroCheque> itemsRetiroChequesSeleccionado) {
        this.itemsRetiroChequesSeleccionado = itemsRetiroChequesSeleccionado;
    }

    /**
     * @return the deshabilitarBotonDarDeBaja
     */
    public boolean isDeshabilitarBotonDarDeBaja() {
        return deshabilitarBotonDarDeBaja;
    }

    /**
     * @param deshabilitarBotonDarDeBaja the deshabilitarBotonDarDeBaja to set
     */
    public void setDeshabilitarBotonDarDeBaja(boolean deshabilitarBotonDarDeBaja) {
        this.deshabilitarBotonDarDeBaja = deshabilitarBotonDarDeBaja;
    }

}
