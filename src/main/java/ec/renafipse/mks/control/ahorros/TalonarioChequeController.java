package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.control.ahorros.bean.*;
import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.RetiroCheque;
import ec.renafipse.mks.modelo.ahorros.TalonarioCheque;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import ec.renafipse.mks.negocio.ahorros.RetiroChequeFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioChequeFacade;
import ec.renafipse.mks.negocio.ifips.IfipCuentaEntidadFinancieraFacade;
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
import javax.faces.event.ActionListener;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "talonarioChequeController")
@ViewScoped
public class TalonarioChequeController extends AbstractController<TalonarioCheque> implements Serializable {

    @EJB
    private TalonarioChequeFacade ejbFacade;

    @EJB
    private IfipCuentaEntidadFinancieraFacade ejbFacadeIfipCuentaEntidadFinanciera;

    //--------------------------------------------------------------------------
    // --PARAMETROS
    private LlamaSP llamaSP;
    private RequestContext context;
    private String msg;
    private TalonarioCheque talonarioCheque;
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera;
    //private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinancieraSel;

    private List<TalonarioCheque> itemsTalonarioCheque;
    private List<IfipCuentaEntidadFinanciera> itemsIfipCuentaEntidadFinanciera;

    public TalonarioChequeController() {
        super(TalonarioCheque.class);
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
                    new Object[]{"talonarioChequeController", "init", CapturaError.getErrorException(ex)});
            }
        }
        this.buscaTalonarios();
        llamaSP = new LlamaSP();

    }

    /**
     * Busca los Cheques Girados.
     */
    public void buscaTalonarios() {

        this.setItemsTalonarioCheque(ejbFacade.getItemsCodigoIfipCodigoIfipAgencia(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia()));

    }

    public void preparaCrear(ActionEvent actionEvent) {
        this.talonarioCheque = new TalonarioCheque();
        this.ifipCuentaEntidadFinanciera = null;
        System.out.println(" ActivacionUsuario.getCodigoIfip() "+ActivacionUsuario.getCodigoIfip()+" "+ActivacionUsuario.getCodigoIfipAgencia());
        this.setItemsIfipCuentaEntidadFinanciera(this.ejbFacadeIfipCuentaEntidadFinanciera.getItemsCuentaFinancieraIfipAgencia(ActivacionUsuario.getCodigoIfipAgencia(),ActivacionUsuario.getCodigoIfip(), 'N'));
        System.out.println(" itemsIfipCuentaEntidadFinanciera "+this.itemsIfipCuentaEntidadFinanciera);
        
    }

    /**
     * VALIDA QUE EL NUMERO DE LA FIN DE SERIE SEA MAYOR A LA DE INICIO.
     */
    public void validaFinSerie() {
        if (this.getTalonarioCheque().getNumeroFin() <= this.getTalonarioCheque().getNumeroInicio()) {            
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SerieFinTalonarioMenorSerieInicio"));
        }
    }

    //------------------------------------------------------------------------
    // GUARDA LA ANULACION DEL CHEQUE
    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR LA ANULACION
    public void guardaTalonario(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        
        if (this.getTalonarioCheque().getNumeroFin() <= this.getTalonarioCheque().getNumeroInicio()) {            
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SerieFinTalonarioMenorSerieInicio"));
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

            java.sql.Timestamp fechaRegistro = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_talonario_cheque.p_guarda");
            llamaSP.setNumeroParametros(7);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", this.ifipCuentaEntidadFinanciera.getCodigo() });
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_inicio", this.getTalonarioCheque().getNumeroInicio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_fin", this.getTalonarioCheque().getNumeroFin()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", fechaRegistro});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_terminada", "N"});

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
                MuestraMensaje.addSatisfactorioPersistencia(1);
                context.execute("procesandoDlg.hide()");
                context.execute("TalonarioChequeCreateDialog.hide()");
                

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
     * @return the itemsTalonarioCheque
     */
    public List<TalonarioCheque> getItemsTalonarioCheque() {
        return itemsTalonarioCheque;
    }

    /**
     * @param itemsTalonarioCheque the itemsTalonarioCheque to set
     */
    public void setItemsTalonarioCheque(List<TalonarioCheque> itemsTalonarioCheque) {
        this.itemsTalonarioCheque = itemsTalonarioCheque;
    }

    /**
     * @return the talonarioCheque
     */
    public TalonarioCheque getTalonarioCheque() {
        return talonarioCheque;
    }

    /**
     * @param talonarioCheque the talonarioCheque to set
     */
    public void setTalonarioCheque(TalonarioCheque talonarioCheque) {
        this.talonarioCheque = talonarioCheque;
    }

    /**
     * @return the ifipCuentaEntidadFinanciera
     */
    public IfipCuentaEntidadFinanciera getIfipCuentaEntidadFinanciera() {
        return ifipCuentaEntidadFinanciera;
    }

    /**
     * @param ifipCuentaEntidadFinanciera the ifipCuentaEntidadFinanciera to set
     */
    public void setIfipCuentaEntidadFinanciera(IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera) {
        this.ifipCuentaEntidadFinanciera = ifipCuentaEntidadFinanciera;
    }

    /**
     * @return the itemsIfipCuentaEntidadFinanciera
     */
    public List<IfipCuentaEntidadFinanciera> getItemsIfipCuentaEntidadFinanciera() {
        return itemsIfipCuentaEntidadFinanciera;
    }

    /**
     * @param itemsIfipCuentaEntidadFinanciera the
     * itemsIfipCuentaEntidadFinanciera to set
     */
    public void setItemsIfipCuentaEntidadFinanciera(List<IfipCuentaEntidadFinanciera> itemsIfipCuentaEntidadFinanciera) {
        this.itemsIfipCuentaEntidadFinanciera = itemsIfipCuentaEntidadFinanciera;
    }

}
