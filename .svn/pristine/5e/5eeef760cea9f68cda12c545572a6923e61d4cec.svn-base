package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.ConvierteDato;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.FondeoCaja;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivo;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivoPK;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDetalle;
import ec.renafipse.mks.modelo.cajas.FondeoCajaDetallePK;
import ec.renafipse.mks.modelo.cajas.FraccionMoneda;
import ec.renafipse.mks.modelo.ifips.Boveda;
import ec.renafipse.mks.modelo.ifips.BovedaDinero;
import ec.renafipse.mks.modelo.ifips.BovedaDineroPK;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.negocio.cajas.FondeoCajaDesgloceEfectivoFacade;
import ec.renafipse.mks.negocio.cajas.FondeoCajaDetalleFacade;
import ec.renafipse.mks.negocio.cajas.FondeoCajaFacade;
import ec.renafipse.mks.negocio.cajas.FraccionMonedaFacade;
import ec.renafipse.mks.negocio.ifips.BovedaDineroFacade;
import ec.renafipse.mks.negocio.ifips.BovedaFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.ifips.IfipAgenciaFacade;
import ec.renafipse.mks.negocio.seguridades.RolOpcionOperacionFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioIfipAgenciaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
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
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "fondeoCajaController")
@ViewScoped
public class FondeoCajaController extends AbstractController<FondeoCaja> implements Serializable {

    @EJB
    private FondeoCajaFacade ejbFacade;

    @EJB
    private FondeoCajaDetalleFacade ejbFacadeFondeoCajaDetalle;

    @EJB
    private FondeoCajaDesgloceEfectivoFacade ejbFacadeDesgloceEfectivo;

    @EJB
    private ComputadorFacade ejbFacadeComputador;

    @EJB
    private BovedaFacade ejbFacadeBoveda;

    @EJB
    private BovedaDineroFacade ejbFacadeBovedaDinero;

    @EJB
    private UsuarioIfipAgenciaFacade ejbFacadeUsuarioIfipAgencia;

    @EJB
    private IfipAgenciaFacade ejbFacadeIfipAgencia;

    @EJB
    private FraccionMonedaFacade ejbFacadeFraccionMoneda;

    @EJB
    private RolOpcionOperacionFacade ejbFacadeRolOpcionOperacion;

    private LlamaSP llamaSP;

    // ---------------------------------------------------------------------------  
    // -- PARAMETROS PERSONALIZADOS
    private Date fechaInicioFondeo;
    private Date fechaFinFondeo;
    private String estadoFondeo;

    private boolean deshabilitaBotonGuardar;
    private String msg;
    private GeneraReporte generaReporte;
    private Long codigoFondeoCaja;

    private Computador computador;
    private Computador computadorCaja;
    private Usuario usuarioCajero;
    private Boveda boveda;
    private FondeoCaja fondeoCajaSel;
    private FondeoCajaDetalle fondeoCajaDetalle;
    private FondeoCajaDetalle fondeoCajaDetalleSel;
    private FondeoCajaDesgloceEfectivo fondeoCajaDesgloceEfectivo;
    private BovedaDinero bovedaDinero;
    

    private List<FondeoCaja> itemsFondeoCaja;
    private List<Boveda> itemsBoveda;
    private List<FondeoCajaDetalle> itemsFondeoCajaDetalle;
    private List<FondeoCajaDesgloceEfectivo> itemFondeoCajaDesgloceEfectivo;
    private List<Usuario> itemsUsuario;
    private List<Computador> itemsComputador;
    private Long codigoIfipAgenciaBoveda;

    public FondeoCajaController() {
        super(FondeoCaja.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            ////System.out.println("Codigo OPcion " + ActivacionUsuario.getCodigoOpcion());
            /*if (this.ejbFacadeRolOpcionOperacion.getItemsOperacionPermisosMenu(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoOpcion(), ActivacionUsuario.getCodigoIfip(), 'N').isEmpty()) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }*/
            
            //Instancion el proceso de llama Store Procedure
            llamaSP = new LlamaSP();            
            IfipAgencia ia =  this.ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia());
            System.out.println("ia "+ia.getCodigo());
            
             codigoIfipAgenciaBoveda = (ia.getEsVentanilla() == 'S') ? ia.getCodigoIfipAgenciaPadre().getCodigo() : ActivacionUsuario.getCodigoIfipAgencia();
            System.out.println("codigoIfipAgenciaBoveda "+codigoIfipAgenciaBoveda);
            if (this.ejbFacadeBoveda.getItemsResponsableIfipAgenciaEliminado(codigoIfipAgenciaBoveda, 
                    ActivacionUsuario.getCodigoUsuario(), 'N').isEmpty()) {                
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoResponsableBovedas"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }

            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            
            this.itemsUsuario = this.ejbFacadeUsuarioIfipAgencia.getItemsUsuariosFondeoCaja(ActivacionUsuario.getCodigoUsuario(),'S', ActivacionUsuario.getCodigoIfipAgencia(), ActivacionUsuario.getCodigoIfip(), 'N');
            this.setItemsBoveda(this.ejbFacadeBovedaDinero.getItemsBovedaMonedaFondeoCaja(codigoIfipAgenciaBoveda, ActivacionUsuario.getCodigoUsuario(), Long.parseLong("1"), BigDecimal.ZERO, 'N'));
            itemsComputador = this.ejbFacadeComputador.getItemsIfipAgenciaDestinoEliminado(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(), Long.parseLong("1"), 'N');
        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"fondeoCajaController", "init", CapturaError.getErrorException(ex)});
            }
        }

    }

    /**
     * Busca los fondeos de las cajas
     */
    public void buscaFondeos() {
        this.fondeoCajaSel = null;

        if (this.fechaFinFondeo == null && this.fechaInicioFondeo == null && this.estadoFondeo.trim().isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseCriterioConsulta");
            MuestraMensaje.addAdvertencia(msg);
            return;
        }
        if ((this.fechaFinFondeo == null && this.fechaInicioFondeo != null) || (this.fechaFinFondeo != null && this.fechaInicioFondeo == null)) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseLasDosFechas");
            MuestraMensaje.addAdvertencia(msg);
            return;
        }

        if (this.fechaFinFondeo != null && this.fechaInicioFondeo != null && !this.estadoFondeo.trim().isEmpty()) {
            this.setItemsFondeoCaja(this.ejbFacade.getItemsFechasFondeoEstado(fechaInicioFondeo, fechaFinFondeo, this.estadoFondeo.charAt(0), ActivacionUsuario.getCodigoIfipAgencia()));
        }

        if (this.fechaFinFondeo != null && this.fechaInicioFondeo != null && this.estadoFondeo.trim().isEmpty()) {
            this.setItemsFondeoCaja(this.ejbFacade.getItemsFechasFondeo(fechaInicioFondeo, fechaFinFondeo, ActivacionUsuario.getCodigoIfipAgencia()));
        }

        if (this.fechaFinFondeo == null && this.fechaInicioFondeo == null && !this.estadoFondeo.trim().isEmpty()) {
            this.setItemsFondeoCaja(this.ejbFacade.getItemsEstado(this.estadoFondeo.charAt(0), ActivacionUsuario.getCodigoIfipAgencia()));
        }
    }

    /**
     * CREA UN NUEVO FONDEO DE CAJA.
     *
     * @param actionEvent
     */
    public void creaFondeo(ActionEvent actionEvent) {
        try {

            this.fondeoCajaSel = new FondeoCaja();
            this.fondeoCajaSel.setCodigoComputadorCaja(ActivacionUsuario.getCodigoComputador());
            this.fondeoCajaSel.setCodigoUsuarioFondeo(ActivacionUsuario.getCodigoUsuario());
            this.fondeoCajaSel.setFechaSistema(new Date());
            this.fondeoCajaSel.setFechaFondeo(ConvierteDato.getFechaMedium(fondeoCajaSel.getFechaSistema()));
            this.fondeoCajaSel.setEstado('E');
            this.fondeoCajaSel.setIfipAgencia(ejbFacadeIfipAgencia.find(ActivacionUsuario.getCodigoIfipAgencia()));
            this.itemFondeoCajaDesgloceEfectivo = new ArrayList<FondeoCajaDesgloceEfectivo>();
            this.itemsFondeoCajaDetalle = new ArrayList<FondeoCajaDetalle>();
            this.usuarioCajero = null;
            this.computadorCaja = null;
            this.boveda = null;
            this.bovedaDinero = null;
            this.deshabilitaBotonGuardar = true;

            //Colocando las Bovedas 
            this.setItemsBoveda(this.ejbFacadeBovedaDinero.getItemsBovedaMonedaFondeoCaja(codigoIfipAgenciaBoveda, ActivacionUsuario.getCodigoUsuario(), Long.parseLong("1"), BigDecimal.ZERO, 'N'));

            // -- Creando la cabecera del detalle del fondeo
            FondeoCajaDetallePK fcdpk = new FondeoCajaDetallePK();
            fcdpk.setCodigoMoneda(Long.parseLong("1"));
            this.fondeoCajaDetalle = new FondeoCajaDetalle(fcdpk);
            this.fondeoCajaDetalle.setTotalFondeo(new BigDecimal("0.00"));
            this.fondeoCajaDetalle.setValorEfectivo(new BigDecimal("0.00"));

            // -- Creando el desgloce de efectivo
            List<FraccionMoneda> listaFraccionMoneda = this.ejbFacadeFraccionMoneda.getItemsEliminadoTipoFranccionMoneda(Long.parseLong("1"), 'N');

            for (FraccionMoneda fm : listaFraccionMoneda) {
                this.fondeoCajaDesgloceEfectivo = new FondeoCajaDesgloceEfectivo(new FondeoCajaDesgloceEfectivoPK());
                this.fondeoCajaDesgloceEfectivo.setFraccionMoneda(fm);
                this.fondeoCajaDesgloceEfectivo.setFondeoCajaDetalle(fondeoCajaDetalle);
                this.fondeoCajaDesgloceEfectivo.setCantidad(Long.parseLong("0"));
                this.fondeoCajaDesgloceEfectivo.setValor(fm.getTipoFraccionMoneda().getValor());
                this.fondeoCajaDesgloceEfectivo.setTotal(new BigDecimal("0.00"));

                this.itemFondeoCajaDesgloceEfectivo.add(fondeoCajaDesgloceEfectivo);
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"fondeoCajaController", "creaFondeo", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * GUARDA EL FONDEO DE LA CAJA.
     *
     * @param actionEvent
     */
    public void guardaFondeo(ActionEvent actionEvent) {

        /*try {

         BovedaDineroPK bdpk = new BovedaDineroPK(this.fondeoCajaSel.getBoveda().getCodigo(), Long.parseLong("1"));
         this.setBovedaDinero(this.ejbFacadeBovedaDinero.find(bdpk));

         //Verificando que aun exista Dinero en la Boveda
         if (getBovedaDinero().getValorEfectivo().subtract(this.fondeoCajaDetalle.getValorEfectivo()).compareTo(new BigDecimal("0.00")) < 0) {
         this.deshabilitaBotonGuardar = true;
         msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("BovedaSinDinero") + ": " + this.getBovedaDinero().getValorEfectivo().toString();
         MuestraMensaje.addError(msg);
         return;
         }

         // Descontando el dinero entregado a la Caja de la boveda            
         getBovedaDinero().setValorEfectivo(getBovedaDinero().getValorEfectivo().subtract(this.fondeoCajaDetalle.getTotalFondeo()));
         getBovedaDinero().setTotalDinero(getBovedaDinero().getValorEfectivo());
         this.ejbFacadeBovedaDinero.edit(getBovedaDinero());

         //Muestra Mensaje de Satisfactorio
         MuestraMensaje.addSatisfactorioPersistencia(1);

         ////System.out.println("FOndeo Guardado");

         } catch (Exception ex) {
         // Muestra el Mensaje del Error en la Capa
         MuestraMensaje.addErrorCapaControlFacace("ejbFacadeBovedaDinero");
         // Registra el error en el log del servidor
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
         new Object[]{"fondeoCajaController", "guardaFondeo", CapturaError.getErrorException(ex)});
         }

         try {
         // Guardando el Fondeo
         this.fondeoCajaSel.setCodigoBoveda(this.fondeoCajaSel.getBoveda().getCodigo());
         this.fondeoCajaSel.setCodigoComputadorCaja(this.getComputadorCaja().getCodigo());
         this.fondeoCajaSel.setCodigoIfipAgencia(this.fondeoCajaSel.getIfipAgencia().getCodigo());
         this.fondeoCajaSel.setCodigoUsuarioCaja(this.usuarioCajero.getCodigo());
         this.fondeoCajaSel.setCodigoUsuarioFondeo(ActivacionUsuario.getCodigoUsuario());
         this.fondeoCajaSel.setCodigoComputadorFondeo(ActivacionUsuario.getCodigoComputador());
         this.fondeoCajaSel.setUsuarioCaja(usuarioCajero);
         this.fondeoCajaSel.setComputadorCaja(computadorCaja);
         this.fondeoCajaSel.setComputadorFondeo(this.ejbFacadeComputador.find(ActivacionUsuario.getCodigoComputador()));
         this.fondeoCajaSel.setFechaSistema(new Date());
         this.fondeoCajaSel.setFechaFondeo(ConvierteDato.getFechaMedium(fondeoCajaSel.getFechaSistema()));

         //Creando el Fondeo de la Caja
         //  this.ejbFacade.ingresaFondeoCaja(fondeoCajaSel, fondeoCajaDetalle, itemFondeoCajaDesgloceEfectivo);
         // Persistiendo el fondeo
         this.ejbFacade.create(fondeoCajaSel);

         } catch (Exception ex) {
         // Muestra el Mensaje del Error en la Capa
         MuestraMensaje.addErrorCapaControlFacace("ejbFacade");
         // Registra el error en el log del servidor
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
         new Object[]{"fondeoCajaController", "guardaFondeo", CapturaError.getErrorException(ex)});
         }

         // Guardando del Detalle del Fondeo
         this.fondeoCajaDetalle.getFondeoCajaDetallePK().setCodigoFondeo(fondeoCajaSel.getCodigo());
         this.fondeoCajaDetalle.setFondeoCaja(fondeoCajaSel);

         try {
         // Persistiendo el detalle del fondeo
         ejbFacadeFondeoCajaDetalle.create(fondeoCajaDetalle);

         } catch (Exception ex) {
         // Muestra el Mensaje del Error en la Capa
         MuestraMensaje.addErrorCapaControlFacace("ejbFacadeFondeoCajaDetalle");
         // Registra el error en el log del servidor
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
         new Object[]{"fondeoCajaController", "guardaFondeo", CapturaError.getErrorException(ex)});
         }

         try {
         //Guardando el Desgloce del Detalle del Fondeo
         for (FondeoCajaDesgloceEfectivo fcde : this.itemFondeoCajaDesgloceEfectivo) {
         fcde.setFondeoCajaDesgloceEfectivoPK(new FondeoCajaDesgloceEfectivoPK(this.fondeoCajaDetalle.getFondeoCajaDetallePK().getCodigoFondeo(),
         this.fondeoCajaDetalle.getFondeoCajaDetallePK().getCodigoMoneda(),
         fcde.getFraccionMoneda().getTipoFraccionMoneda().getCodigo()));
         this.ejbFacadeDesgloceEfectivo.edit(fcde);
         }

         } catch (Exception ex) {
         // Muestra el Mensaje del Error en la Capa
         MuestraMensaje.addErrorCapaControlFacace("ejbFacadeDesgloceEfectivo");
         // Registra el error en el log del servidor
         Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
         new Object[]{"fondeoCajaController", "guardaFondeo", CapturaError.getErrorException(ex)});
         }*/
        RequestContext context = RequestContext.getCurrentInstance();
        try {

            //context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            if (this.guardaFondeoCaja()) {
                this.codigoFondeoCaja = ((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));
                if (this.guardaFondeoCajaDetalle()) {
                    if (this.guardaMovimientoBoveda()) {
                        this.guardaFondeoCajaDesgloceEfectivo();
                    }
                }
            }

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                MuestraMensaje.addSatisfactorioPersistencia(1);
                context.execute("procesandoDlg.hide()");
                context.execute("FondeoCajaCreateDialog.hide()");
                context.execute("imprimeFondeoDialog.show()");

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
                    new Object[]{"movimientoBovedaController", "guardarMovimiento", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * MUESRA EL FONDEO DE LA CAJA SELECCIONADO
     *
     * @param actionEvent
     */
    public void muestraFondeo(ActionEvent actionEvent) {
        this.setFondeoCajaDetalle(((List<FondeoCajaDetalle>) this.fondeoCajaSel.getFondeoCajaDetalleCollection()).get(0));
        this.setItemFondeoCajaDesgloceEfectivo(this.ejbFacadeDesgloceEfectivo.getItemsFondeoCajaDesgloceEfectivo(this.fondeoCajaDetalle.getFondeoCajaDetallePK().getCodigoFondeo(),
                this.fondeoCajaDetalle.getFondeoCajaDetallePK().getCodigoMoneda()));
    }

    /**
     * IMPRIME EL FONDEO DE LA CAJA SELECCIONADA
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimeFondeo(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

        String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoFondeo", (this.fondeoCajaSel.getCodigo() == null) ? this.codigoFondeoCaja : this.fondeoCajaSel.getCodigo());

        nombreReporte = "fondeoCaja";
        getGeneraReporte().exporta("/financiero/boveda/fondeoCaja/reporte/", nombreReporte,
                "fondeoCaja" + String.valueOf((this.fondeoCajaSel.getCodigo() == null) ? this.codigoFondeoCaja : this.fondeoCajaSel.getCodigo()) + ".pdf",
                "PDF", externalContext, facesContext);

    }

    /**
     * Realiza el calculo cuando la cantidad es modificada en el desgloce del
     * efectivo
     */
    public void cantidadModificada() {

        if (this.fondeoCajaSel.getBoveda() != null) {
            BigDecimal totalFondeo = new BigDecimal("0.00");
            BigDecimal valor;
            Long cantidad;
            BigDecimal total;

            for (int c = 0; c < this.itemFondeoCajaDesgloceEfectivo.size(); c++) {
                cantidad = this.itemFondeoCajaDesgloceEfectivo.get(c).getCantidad();
                valor = this.itemFondeoCajaDesgloceEfectivo.get(c).getValor();
                total = valor.multiply(new BigDecimal(cantidad.toString()));
                this.itemFondeoCajaDesgloceEfectivo.get(c).setTotal(total);
                totalFondeo = totalFondeo.add(total);
            }

            this.fondeoCajaDetalle.setTotalFondeo(totalFondeo);
            this.fondeoCajaDetalle.setValorEfectivo(totalFondeo);
            this.deshabilitaBotonGuardar();
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccionBoveda");
            MuestraMensaje.addError(msg);
        }

    }

    /**
     * BUSCA SI EL CAJERO TIENE UN FONDEO VIGENTE. Metodo usado en el listener
     * cuando cambia/seleccciona el cajero
     */
    public void buscaFondeoCajaCajeroVigente() {
        this.deshabilitaBotonGuardar = false;
        if (!this.ejbFacade.getItemsFondeoCajeroVigente(this.getUsuarioCajero().getCodigo()).isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CajeroConFondeoVigente");
            this.deshabilitaBotonGuardar = true;
            this.usuarioCajero = null;
            MuestraMensaje.addError(msg);
        } else {
            deshabilitaBotonGuardar();
        }

    }

    /**
     * BUSCA SI EL COMPUTADOR YA TIENE UN FONDEO VIGENTE. Metodo usado en el
     * listener cuando cambia/seleccciona el computador.
     */
    public void buscaFondeoComputadorCajaVigente() {
        this.deshabilitaBotonGuardar = false;
        if (!this.ejbFacade.getItemsFondeoComputadorVigente(this.getComputadorCaja().getCodigo()).isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorConFondeoVigente");
            this.deshabilitaBotonGuardar = true;
            this.computadorCaja = null;
            MuestraMensaje.addError(msg);

        } else {
            deshabilitaBotonGuardar();
        }

    }

    /**
     * BUSCA SI EL COMPUTADOR YA TIENE UN FONDEO VIGENTE. Metodo usado en el
     * listener cuando cambia/seleccciona el computador.
     */
    public void buscaBovedaDineroCaja() {
        BovedaDineroPK bdpk = new BovedaDineroPK(this.fondeoCajaSel.getBoveda().getCodigo(), Long.parseLong("1"));
        this.setBovedaDinero(this.ejbFacadeBovedaDinero.find(bdpk));
        // Si la boveda cuenta con dinero
        this.deshabilitaBotonGuardar();
    }

    /**
     * DESHABILITA EL BOTON GUARDAR.
     */
    public void deshabilitaBotonGuardar() {
        BovedaDineroPK bdpk = new BovedaDineroPK(this.fondeoCajaSel.getBoveda().getCodigo(), Long.parseLong("1"));
        this.setBovedaDinero(this.ejbFacadeBovedaDinero.find(bdpk));

        if (this.fondeoCajaDetalle.getValorEfectivo().compareTo(new BigDecimal("0.00")) > 0) {
            // Si la boveda cuenta con dinero
            if (getBovedaDinero().getValorEfectivo().subtract(this.fondeoCajaDetalle.getValorEfectivo()).compareTo(new BigDecimal("0.00")) < 0) {
                this.deshabilitaBotonGuardar = true;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("BovedaSinDinero") + ": " + this.getBovedaDinero().getValorEfectivo().toString();
                MuestraMensaje.addError(msg);
            } else {
                this.deshabilitaBotonGuardar = false;
            }

        } else {
            this.deshabilitaBotonGuardar = true;
        }
    }

    // ----------------------------------------------------------------------------------------------
    // -- LLAMAMIENTO A SP DE BASE DE DATOS CON JDBC MEDIANTE DATASORUCE DEL SERVIDOR DE APLICACIONES
    /**
     * GUARADA EL LA FONDEO DE LA CAJA (CABECERA)
     *
     * @return True / False si ejecuto correctamente
     */
    public boolean guardaFondeoCaja() {

        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_cajas.pkg_fondeo_caja.p_inserta_fondeo_caja");
        llamaSP.setNumeroParametros(9);

        Timestamp fechaSistema = new java.sql.Timestamp(new Date().getTime());
        
        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_boveda", this.fondeoCajaSel.getBoveda().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_fondeo", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador_fondeo", ActivacionUsuario.getCodigoComputador()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario_caja", this.usuarioCajero.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador_caja", this.computadorCaja.getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "E"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", fechaSistema});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();

    }

    /**
     * GUARDA EL DETALLE DEL FONDEO, AQUI ES POR EL TIPO DE MONEDA
     *
     * @return True / False si ejecuto correctamente
     */
    public boolean guardaFondeoCajaDetalle() {

        // Guardando el Fondeo de Caja
        llamaSP.setNombreSP("mks_cajas.pkg_fondeo_caja_detalle.p_inserta_fondeo_caja_detalle");
        llamaSP.setNumeroParametros(4);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_fondeo", this.codigoFondeoCaja});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.fondeoCajaDetalle.getFondeoCajaDetallePK().getCodigoMoneda()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_efectivo", this.fondeoCajaDetalle.getValorEfectivo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_fondeo", this.fondeoCajaDetalle.getTotalFondeo()});
        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

        // Invocando al SP
        llamaSP.invocaSP();

        return llamaSP.isEjecucionCorrecta();

    }

    /**
     * GUARDA EL MOVIMIENTO DE LA BOVEDA POR EGRESO DE FONDEO DE CAJA
     *
     * @return True / False si ejecuto correctamente
     */
    public boolean guardaMovimientoBoveda() {

        Timestamp fechaMovimiento = new java.sql.Timestamp(new Date().getTime());
        
        llamaSP.setNombreSP("mks_ifips.pkm_movimiento_boveda.p_guarda_movivimiento_boveda");
        llamaSP.setNumeroParametros(18);

        llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_boveda", this.fondeoCajaSel.getBoveda().getCodigo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", Long.parseLong("1")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_movimiento", Long.parseLong("2")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_usuario", ActivacionUsuario.getCodigoUsuario()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_computador", ActivacionUsuario.getCodigoComputador()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", null});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", null});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_interviene", "N"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", "E"});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema",fechaMovimiento});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_comprobante", "BC" + Long.parseLong("1") + this.codigoFondeoCaja.toString()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_efectivo", this.fondeoCajaDetalle.getValorEfectivo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor_cheques", new BigDecimal("0.00")});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_movimiento", this.fondeoCajaDetalle.getTotalFondeo()});
        llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", "FONDEO DE CAJA CODIGO: " + this.codigoFondeoCaja.toString()});

        llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
        llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

        // Invocando al SP
        llamaSP.invocaSP();

        if (llamaSP.isEjecucionCorrecta()) {

            Long codigoMovimientoBoveda = ((Long) Long.parseLong(llamaSP.getListResultado().get(0).toString()));

            llamaSP.setNombreSP("mks_ifips.pkg_movimiento_boveda_des_efe.p_inserta_desgloce_efectivo");
            llamaSP.setNumeroParametros(6);
            // Insertando degloce del movimiento 
            for (FondeoCajaDesgloceEfectivo fcde : this.itemFondeoCajaDesgloceEfectivo) {

                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_fraccion", fcde.getFraccionMoneda().getTipoFraccionMoneda().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.fondeoCajaDetalle.getFondeoCajaDetallePK().getCodigoMoneda()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_movimiento_boveda", codigoMovimientoBoveda});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_cantidad", fcde.getCantidad()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", fcde.getValor()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", fcde.getTotal()});

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
     * GUARDA EL DESGLOCE DEL EFECTIVO DEL FONDEO DE LA CAJA
     *
     * @return True / False si ejecuto correctamente
     */
     public boolean guardaFondeoCajaDesgloceEfectivo() {

        llamaSP.setNombreSP("mks_cajas.pkg_fondeo_caja_desgloce_efe.p_inserta_desgloce_efectivo");
        llamaSP.setNumeroParametros(6);
        // Insertando degloce del movimiento 
        for (FondeoCajaDesgloceEfectivo fcde : this.itemFondeoCajaDesgloceEfectivo) {

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_fraccion", fcde.getFraccionMoneda().getTipoFraccionMoneda().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.fondeoCajaDetalle.getFondeoCajaDetallePK().getCodigoMoneda()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_fondeo", this.codigoFondeoCaja});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_cantidad", fcde.getCantidad()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", fcde.getValor()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total", fcde.getTotal()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando si el desgloce no dio errores
            if (!llamaSP.isEjecucionCorrecta()) {
                break;
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }


    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    /**
     * Lista de Computadores
     *
     *
     * @return Lista de Computadores.
     */
    public List<Computador> getItemsComputador() {
        return this.itemsComputador;
    }


    public List<Usuario> getItemsUsuario() {
        return this.itemsUsuario;
    }

    // ---------------------------------------------------------------------------  
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    /**
     * @return the fechaFondeo
     */
    /**
     * @return the computador
     */
    public Computador getComputador() {
        return computador;
    }

    /**
     * @param computador the computador to set
     */
    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    /**
     * @return the itemsFondeoCaja
     */
    public List<FondeoCaja> getItemsFondeoCaja() {
        return itemsFondeoCaja;
    }

    /**
     * @param itemsFondeoCaja the itemsFondeoCaja to set
     */
    public void setItemsFondeoCaja(List<FondeoCaja> itemsFondeoCaja) {
        this.itemsFondeoCaja = itemsFondeoCaja;
    }

    /**
     * @return the fondeoCajaSel
     */
    public FondeoCaja getFondeoCajaSel() {
        return fondeoCajaSel;
    }

    /**
     * @param fondeoCajaSel the fondeoCajaSel to set
     */
    public void setFondeoCajaSel(FondeoCaja fondeoCajaSel) {
        this.fondeoCajaSel = fondeoCajaSel;
    }

    /**
     * @return the itemsBoveda
     */
    public List<Boveda> getItemsBoveda() {
        return itemsBoveda;
    }

    /**
     * @param itemsBoveda the itemsBoveda to set
     */
    public void setItemsBoveda(List<Boveda> itemsBoveda) {
        this.itemsBoveda = itemsBoveda;
    }

    /**
     * @return the fondeoCajaDetalle
     */
    public FondeoCajaDetalle getFondeoCajaDetalle() {
        return fondeoCajaDetalle;
    }

    /**
     * @param fondeoCajaDetalle the fondeoCajaDetalle to set
     */
    public void setFondeoCajaDetalle(FondeoCajaDetalle fondeoCajaDetalle) {
        this.fondeoCajaDetalle = fondeoCajaDetalle;
    }

    /**
     * @return the fondeoCajaDesgloceEfectivo
     */
    public FondeoCajaDesgloceEfectivo getFondeoCajaDesgloceEfectivo() {
        return fondeoCajaDesgloceEfectivo;
    }

    /**
     * @param fondeoCajaDesgloceEfectivo the fondeoCajaDesgloceEfectivo to set
     */
    public void setFondeoCajaDesgloceEfectivo(FondeoCajaDesgloceEfectivo fondeoCajaDesgloceEfectivo) {
        this.fondeoCajaDesgloceEfectivo = fondeoCajaDesgloceEfectivo;
    }

    /**
     * @return the itemsFondeoCajaDetalle
     */
    public List<FondeoCajaDetalle> getItemsFondeoCajaDetalle() {
        return itemsFondeoCajaDetalle;
    }

    /**
     * @param itemsFondeoCajaDetalle the itemsFondeoCajaDetalle to set
     */
    public void setItemsFondeoCajaDetalle(List<FondeoCajaDetalle> itemsFondeoCajaDetalle) {
        this.itemsFondeoCajaDetalle = itemsFondeoCajaDetalle;
    }

    /**
     * @return the itemFondeoCajaDesgloceEfectivo
     */
    public List<FondeoCajaDesgloceEfectivo> getItemFondeoCajaDesgloceEfectivo() {
        return itemFondeoCajaDesgloceEfectivo;
    }

    /**
     * @param itemFondeoCajaDesgloceEfectivo the itemFondeoCajaDesgloceEfectivo
     * to set
     */
    public void setItemFondeoCajaDesgloceEfectivo(List<FondeoCajaDesgloceEfectivo> itemFondeoCajaDesgloceEfectivo) {
        this.itemFondeoCajaDesgloceEfectivo = itemFondeoCajaDesgloceEfectivo;
    }

    /**
     * @return the fondeoCajaDetalleSel
     */
    public FondeoCajaDetalle getFondeoCajaDetalleSel() {
        return fondeoCajaDetalleSel;
    }

    /**
     * @param fondeoCajaDetalleSel the fondeoCajaDetalleSel to set
     */
    public void setFondeoCajaDetalleSel(FondeoCajaDetalle fondeoCajaDetalleSel) {
        this.fondeoCajaDetalleSel = fondeoCajaDetalleSel;
    }

    /**
     * @return the boveda
     */
    public Boveda getBoveda() {
        return boveda;
    }

    /**
     * @param boveda the boveda to set
     */
    public void setBoveda(Boveda boveda) {
        this.boveda = boveda;
    }

    /**
     * @return the computadorCaja
     */
    public Computador getComputadorCaja() {
        return computadorCaja;
    }

    /**
     * @param computadorCaja the computadorCaja to set
     */
    public void setComputadorCaja(Computador computadorCaja) {
        this.computadorCaja = computadorCaja;
    }

    /**
     * @return the usuarioCajero
     */
    public Usuario getUsuarioCajero() {
        return usuarioCajero;
    }

    /**
     * @param usuarioCajero the usuarioCajero to set
     */
    public void setUsuarioCajero(Usuario usuarioCajero) {
        this.usuarioCajero = usuarioCajero;
    }

    /**
     * @return the deshabilitaBotonGuardar
     */
    public boolean isDeshabilitaBotonGuardar() {
        return deshabilitaBotonGuardar;
    }

    /**
     * @param deshabilitaBotonGuardar the deshabilitaBotonGuardar to set
     */
    public void setDeshabilitaBotonGuardar(boolean deshabilitaBotonGuardar) {
        this.deshabilitaBotonGuardar = deshabilitaBotonGuardar;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
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
     * @return the bovedaDinero
     */
    public BovedaDinero getBovedaDinero() {
        return bovedaDinero;
    }

    /**
     * @param bovedaDinero the bovedaDinero to set
     */
    public void setBovedaDinero(BovedaDinero bovedaDinero) {
        this.bovedaDinero = bovedaDinero;
    }

    /**
     * @return the codigoFondeoCaja
     */
    public Long getCodigoFondeoCaja() {
        return codigoFondeoCaja;
    }

    /**
     * @param codigoFondeoCaja the codigoFondeoCaja to set
     */
    public void setCodigoFondeoCaja(Long codigoFondeoCaja) {
        this.codigoFondeoCaja = codigoFondeoCaja;
    }

    /**
     * @return the fechaInicioFondeo
     */
    public Date getFechaInicioFondeo() {
        return fechaInicioFondeo;
    }

    /**
     * @param fechaInicioFondeo the fechaInicioFondeo to set
     */
    public void setFechaInicioFondeo(Date fechaInicioFondeo) {
        this.fechaInicioFondeo = fechaInicioFondeo;
    }

    /**
     * @return the fechaFinFondeo
     */
    public Date getFechaFinFondeo() {
        return fechaFinFondeo;
    }

    /**
     * @param fechaFinFondeo the fechaFinFondeo to set
     */
    public void setFechaFinFondeo(Date fechaFinFondeo) {
        this.fechaFinFondeo = fechaFinFondeo;
    }

    /**
     * @return the estadoFondeo
     */
    public String getEstadoFondeo() {
        return estadoFondeo;
    }

    /**
     * @param estadoFondeo the estadoFondeo to set
     */
    public void setEstadoFondeo(String estadoFondeo) {
        this.estadoFondeo = estadoFondeo;
    }

    /**
     * @param itemsUsuario the itemsUsuario to set
     */
    public void setItemsUsuario(List<Usuario> itemsUsuario) {
        this.itemsUsuario = itemsUsuario;
    }

    /**
     * @param itemsComputador the itemsComputador to set
     */
    public void setItemsComputador(List<Computador> itemsComputador) {
        this.itemsComputador = itemsComputador;
    }

}
