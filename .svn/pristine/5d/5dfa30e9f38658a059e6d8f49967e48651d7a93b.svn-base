package ec.renafipse.mks.control.cajas;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.impresiones.ImpresionComprobantes;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.cajas.Apertura;
import ec.renafipse.mks.modelo.cajas.IngresoEgreso;
import ec.renafipse.mks.modelo.cajas.IngresoEgresoCaja;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.negocio.cajas.AperturaFacade;
import ec.renafipse.mks.negocio.cajas.IngresoEgresoCajaFacade;
import ec.renafipse.mks.negocio.cajas.IngresoEgresoMonedaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.RolIngresoEgresoIfipFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
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

@ManagedBean(name = "ingresoEgresoCajaController")
@ViewScoped
public class IngresoEgresoCajaController extends AbstractController<IngresoEgresoCaja> implements Serializable {

    @EJB
    private IngresoEgresoCajaFacade ejbFacade;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private IngresoEgresoMonedaFacade ejbFacadeIngresoEgresoMoneda;

    @EJB
    private AperturaFacade ejbFacadeApertura;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private RolIngresoEgresoIfipFacade ejbFacadeRolIngresoEgresoIfip;

    private LlamaSP llamaSP;
    ;

    //---------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    private String msg;
    private Long codigoApertura;
    private boolean deshabilitaBotonGuardar;
    private String tipo;
    private Long codigoIngresoEgreso;
    private Timestamp fechaIngreso;
    private GeneraReporte generaReporte;

    private Moneda moneda;
    private IngresoEgreso ingresoEgreso;
    private IngresoEgresoCaja ingresoEgresoCaja;

    private List<IngresoEgreso> itemsIngresoEgreso;

    public IngresoEgresoCajaController() {
        super(IngresoEgresoCaja.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlSinPermisosOpcionMKS");
        try {
            this.setDeshabilitaBotonGuardar(true);

            //////System.out.println("Direccion IP " + ObtieneInformacionCliente.obtenerDireccionIP() + " Codigo Computador " + ActivacionUsuario.getCodigoComputador());
            /*if (this.ejbFacadeRolOpcionOperacion.getItemsOperacionPermisosMenu(ActivacionUsuario.getCodigoRol(), ActivacionUsuario.getCodigoOpcion(), ActivacionUsuario.getCodigoIfip(), 'N').isEmpty()) {
             ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
             //Accediendo a la ventana de no permisos            
             Sesion.redireccionaPagina(url);
             }*/
            if (ActivacionUsuario.getCodigoComputador() == null) {
                ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ComputadorNoRegistrado"));
                //Accediendo a la ventana de no permisos            
                Sesion.redireccionaPagina(url);
            }
            //////System.out.println("PAsa computador");
            List<Apertura> listaApertura = this.ejbFacadeApertura.getItemsAperturaUsuarioIfipAgencia(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfipAgencia(), 'A');
            // Verifico que si tiene aperturado la caja
            if (!listaApertura.isEmpty()) {
                if (listaApertura.size() == 1) {
                    if (Validaciones.validaFechaIgualHoy(listaApertura.get(0).getFechaApertura())) {
                        //Instancion el proceso de llama Store Procedure
                        llamaSP = new LlamaSP();
                        this.setCodigoApertura(listaApertura.get(0).getCodigo());
                        this.tipo = null;
                        this.ingresoEgreso = null;
                        this.moneda = this.ejbFacadeMoneda.find(Long.parseLong("1"));
                        //this.setItemsIngresoEgreso(this.ejbFacadeIngresoEgresoMoneda.getIngresoEgresoMonedaEliminadoMostrar(this.moneda.getCodigo(), 'N', 'S'));
                        this.ingresoEgresoCaja = new IngresoEgresoCaja();
                        this.ingresoEgresoCaja.setTotalCheques(new BigDecimal("0.00"));
                        this.ingresoEgresoCaja.setTotalIngresoEgreso(new BigDecimal("0.00"));
                    } else {
                        ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AperturaNoCorrespondeDiaActual") + " " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(listaApertura.get(0).getFechaApertura()));
                        //Accediendo a la ventana de no permisos            
                        Sesion.redireccionaPagina(url);
                    }
                } else {
                    //System.out.println("Validacin en INgresos Egresos de Apuertura HOY");
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioMasUnaApertura"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }

            } else {

                listaApertura = this.ejbFacadeApertura.getItemsUsuarioEstado(ActivacionUsuario.getCodigoUsuario(), 'A');
                if (!listaApertura.isEmpty()) {
                    String nombreAgencia = null;
                    if (listaApertura.size() == 1) {
                        nombreAgencia = listaApertura.get(0).getIfipAgencia().getNombre();
                    }
                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAperturoCajaOtraAgencia") + " " + nombreAgencia);
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                } else {

                    ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioSinAperturaCaja"));
                    //Accediendo a la ventana de no permisos            
                    Sesion.redireccionaPagina(url);
                }

            }
        } catch (Exception e) {
            ActivacionUsuario.setSinPermisosOpcion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoTienePermisosOpcion"));
            try {
                //Accediendo a la ventana de no permisos
                Sesion.redireccionaPagina(url);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ingresoEgresoCajaController", "init", CapturaError.getErrorException(ex)});
            }
        }

    }

    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR EL INGRESO / EGRESO
    public void guardaIngresoEgreso(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            //////System.out.println("Guarda Movimiento");

            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            this.fechaIngreso = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_cajas.pkm_ingreso_egreso_caja.p_guarda_ingreso_igreso");
            llamaSP.setNumeroParametros(13);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ingreso_egreso", this.ingresoEgreso.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.moneda.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_apertura", this.codigoApertura});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip_agencia", ActivacionUsuario.getCodigoIfipAgencia()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_ingreso_egreso", this.fechaIngreso});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_sistema", this.fechaIngreso});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_tipo", this.tipo});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_efectivo", this.ingresoEgresoCaja.getTotalEfectivo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_cheques", this.ingresoEgresoCaja.getTotalCheques()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_total_ingreso_egreso", this.ingresoEgresoCaja.getTotalIngresoEgreso()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_observaciones", this.ingresoEgresoCaja.getObservaciones()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_codigo", Types.NUMERIC});

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                this.codigoIngresoEgreso = Long.parseLong(llamaSP.getListResultado().get(0).toString());
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresoEgresoGrabado");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                context.execute("ImprimeComprobanteDialogo.show()");
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
                    new Object[]{"ingresoEgresoCajaController", "guardarMovimiento", CapturaError.getErrorException(ex)});
        }

    }

    /**
     * IMPRIME EL COMPROBANTE DEL INGRESO/EGRESO
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprime(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {

      /*  String nombreReporte;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        ////System.out.println("Imprime Movimiento");
        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoIngresoEgreso", this.codigoIngresoEgreso);

        nombreReporte = "ingresoEgresoCaja";

        getGeneraReporte().exporta("/financiero/cajas/ingresoEgresoCaja/reporte/", nombreReporte,
                nombreReporte + String.valueOf(this.codigoIngresoEgreso) + ".pdf",
                "PDF", externalContext, facesContext);*/
        IngresoEgresoCaja iec = this.ejbFacade.find(codigoIngresoEgreso);
        ImpresionComprobantes impresionComprobantes = new ImpresionComprobantes(ActivacionUsuario.getRutaImpresora());
        
        impresionComprobantes.ingresoEgresoCaja(iec, iec.getCodigoApertura().getUsuario(),this.ejbFacadeMoneda.find(iec.getIngresoEgresoMoneda().getIngresoEgresoMonedaPK().getCodigoMoneda()));

        ////System.out.println("Imprimió Movimiento");
    }
    // -- FIN PROCESO PARA GUARDAR EL INGRESO / EGRESO
    // --------------------------------------------------------------------------
    /**
     * DESHABILITA O HABILITA EL BOTON GUARDAR
     */
    public void deshabilitaBotonGuardar() {
        this.deshabilitaBotonGuardar = !this.validaciones();
    }

    /**
     * REALIZA LAS VALIDACIONES DEL FORMULARIO
     *
     * @return
     */
    private boolean validaciones() {
        return this.validaCampos();
    }

    /**
     * VALIDA LOS CAMPOS DEL INGRESO/EGRESO
     *
     * @return
     */
    public boolean validaCampos() {
        try {
            if (this.moneda == null) {
                return false;
            }

            if (this.tipo == null) {
                return false;
            }
            ////System.out.println("1.2 getIngresoEgreso() " + getIngresoEgreso());
            if (this.getIngresoEgreso() == null) {
                return false;
            }

            ////System.out.println("1.5");
            if ((this.ingresoEgresoCaja != null) ? this.ingresoEgresoCaja.getTotalEfectivo().toString().trim().isEmpty() : null) {
                this.ingresoEgresoCaja.setTotalEfectivo(new BigDecimal("0.00"));
            }

            ////System.out.println("1.7 " + this.ingresoEgresoCaja.getObservaciones());
            if ((this.ingresoEgresoCaja.getObservaciones() != null) ? ingresoEgresoCaja.getObservaciones().trim().isEmpty() : null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ObservacionesRequerido");
                return false;
            }

            ////System.out.println("1.9");
            if ((ingresoEgresoCaja.getObservaciones() != null) ? ingresoEgresoCaja.getObservaciones().length() <= 4 : null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                return false;
            }

            if (this.ingresoEgresoCaja.getTotalIngresoEgreso().compareTo(new BigDecimal("0.01")) < 0) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TotalIngresoCajaNoPuedeSerCero");
                return false;
            }
            ////System.out.println("1.11");
        } catch (Exception er) {
            return false;
        }

        return true;
    }

    public void calculaTotal() {
        this.ingresoEgresoCaja.setTotalIngresoEgreso(new BigDecimal("0.00"));
        this.ingresoEgresoCaja.setTotalIngresoEgreso(this.ingresoEgresoCaja.getTotalEfectivo().add(this.ingresoEgresoCaja.getTotalCheques()));
        this.deshabilitaBotonGuardar();
    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');

    }

    // ------------------ REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.ingresoEgreso = null;
        this.tipo = null;
        this.setItemsIngresoEgreso(null);
        this.deshabilitaBotonGuardar();

    }

    /**
     * CUANDO CAMBIA TIPO DE INGRESO/EGRESO
     */
    public void cambiaTipo() {
        this.ingresoEgreso = null;
        this.setItemsIngresoEgreso(ejbFacadeRolIngresoEgresoIfip.getIngresoEgresoCaja(this.moneda.getCodigo(), ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoRol(), (this.tipo.equals("I") ? 'S' : 'N'), 'N', 'S'));
        this.deshabilitaBotonGuardar();

    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the ingresoEgreso
     */
    public IngresoEgreso getIngresoEgreso() {
        return ingresoEgreso;
    }

    /**
     * @param ingresoEgreso the ingresoEgreso to set
     */
    public void setIngresoEgreso(IngresoEgreso ingresoEgreso) {
        this.ingresoEgreso = ingresoEgreso;
    }

    /**
     * @return the itemsIngresoEgreso
     */
    public List<IngresoEgreso> getItemsIngresoEgreso() {
        return itemsIngresoEgreso;
    }

    /**
     * @param itemsIngresoEgreso the itemsIngresoEgreso to set
     */
    public void setItemsIngresoEgreso(List<IngresoEgreso> itemsIngresoEgreso) {
        this.itemsIngresoEgreso = itemsIngresoEgreso;
    }

    /**
     * @return the ingresoEgresoCaja
     */
    public IngresoEgresoCaja getIngresoEgresoCaja() {
        return ingresoEgresoCaja;
    }

    /**
     * @param ingresoEgresoCaja the ingresoEgresoCaja to set
     */
    public void setIngresoEgresoCaja(IngresoEgresoCaja ingresoEgresoCaja) {
        this.ingresoEgresoCaja = ingresoEgresoCaja;
    }

    /**
     * @return the codigoApertura
     */
    public Long getCodigoApertura() {
        return codigoApertura;
    }

    /**
     * @param codigoApertura the codigoApertura to set
     */
    public void setCodigoApertura(Long codigoApertura) {
        this.codigoApertura = codigoApertura;
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the codigoIngresoEgreso
     */
    public Long getCodigoIngresoEgreso() {
        return codigoIngresoEgreso;
    }

    /**
     * @param codigoIngresoEgreso the codigoIngresoEgreso to set
     */
    public void setCodigoIngresoEgreso(Long codigoIngresoEgreso) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
    }

    /**
     * @return the fechaIngreso
     */
    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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
