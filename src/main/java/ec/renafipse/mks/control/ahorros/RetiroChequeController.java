package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.RetiroCheque;
import ec.renafipse.mks.modelo.ahorros.TalonarioCheque;
import ec.renafipse.mks.modelo.ahorros.TalonarioChequeDetalle;
import ec.renafipse.mks.modelo.ahorros.TalonarioChequeDetallePK;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
import ec.renafipse.mks.negocio.ahorros.ConceptoTransaccionProFacade;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.RetiroChequeFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioChequeDetalleFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioChequeFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipCuentaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.RolConceptoTransaccionIfipFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
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

@ManagedBean(name = "retiroChequeController")
@ViewScoped
public class RetiroChequeController extends AbstractController<RetiroCheque> implements Serializable {

    @EJB
    private RetiroChequeFacade ejbFacade;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private ConceptoTransaccionProFacade ejbFacadeConceptoTransaccionPro;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private TalonarioChequeFacade ejbFacadeTalonarioCheque;

    @EJB
    private TalonarioChequeDetalleFacade ejbFacadeTalonarioChequeDetalle;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    //--------------------------------------------------------------------------
    // --PARAMETROS
    private Moneda moneda;
    private ProductoIfip productoIfip;
    private Cuenta cuenta;
    private RetiroCheque retiroCheque;
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera;
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinancieraSel;
    private Cuenta cuentaSel;
    private BigDecimal valor;
    private LlamaSP llamaSP;

    private String numeroCuenta;
    private String msg;
    private String observaciones;
    private long numeroCheque;
    private String nombresSocio;
    private String mensajeDialogo;
    private String socioBeneficiario;
    private String nombreBeneficiario;

    private boolean deshabilitaBotonGuardar;
    private boolean deshabilitaNombreBeneficiario;
    private RequestContext context;

    private List<ProductoIfip> itemsProductoIfip;
    private List<Cuenta> itemsCuenta;

    public RetiroChequeController() {
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
                    new Object[]{"retiroChequeController", "init", CapturaError.getErrorException(ex)});
            }
        }
        setRetiroCheque(new RetiroCheque());
        llamaSP  = new LlamaSP();
        deshabilitaNombreBeneficiario = true;
        this.deshabilitaBotonGuardar = true;
        this.nombreBeneficiario = null;
        this.moneda = this.ejbFacadeMoneda.find(Long.parseLong("1"));
        socioBeneficiario = null;        
        this.ifipCuentaEntidadFinancieraSel = null;
        this.ifipCuentaEntidadFinanciera = null;
        this.cuenta = null;
        this.nombresSocio = null;
        this.valor = null;
        this.numeroCheque =  0;          
        this.cambiaMoneda();
        this.productoIfip = null;
        this.numeroCuenta = null;

    }

    //------------------------------------------------------------------------
    // GUARDA EL RETIRO DEL CHEQUE
    // --------------------------------------------------------------------------
    // -- PROCESO PARA GUARDAR EL MOVIMIENTO
    public void guardaRetiro(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        
        // Verificando que pasen las validaciones
        if (!this.validaciones(true)) {
            context.execute("procesandoDlg.hide()");
            return;
        }
        try {
            //System.out.println("Guarda El retiro en cheque");

            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            Timestamp fechaRetiro = new java.sql.Timestamp(new Date().getTime());
            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_ahorros.pkm_retiro_cheque.p_gira");
            llamaSP.setNumeroParametros(11);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta_ent_fin", this.ifipCuentaEntidadFinancieraSel.getCodigo()});            
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_cheque", this.numeroCheque});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_socio", this.cuenta.getCodigoSocio()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_cuenta", this.getCuenta().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", this.cuenta.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_es_socio_beneficiario", this.socioBeneficiario});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_beneficiario", this.nombreBeneficiario});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_valor", this.valor});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_estado", fechaRetiro});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_estado", "G"});
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
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ChequeGirado");
                MuestraMensaje.addInformacion(msg);
                context.execute("procesandoDlg.hide()");
                this.deshabilitaBotonGuardar = true;
                this.deshabilitaNombreBeneficiario = true;

                //context.execute("ImprimeComprobanteDialogo.show()");
                //this.init();
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
                    new Object[]{"retiroChequeController", "guardaRetiro", CapturaError.getErrorException(ex)});
        }

    }

    //---------------------------------------------------------------------------
    // -- METODOS DE LA LISTA DE BUSQUEDA DE CUENTAS
    public void preparaBusquedaCuentas(ActionEvent actionEvent) {
        this.setNombresSocio(null);
        this.setItemsCuenta(null);
        this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
    }

    public void buscaCuentas() {
        if (getNombresSocio().trim().isEmpty()) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioVacio") + " - " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsCuenta(null);
        } else if (this.getNombresSocio().trim().length() <= 6) {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CriterioMinimmoSieteCaracteres"));
            this.setItemsCuenta(null);
        } else {
            this.setMensajeDialogo(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta"));
            this.setMensajeDialogo(null);
            this.setItemsCuenta(ejbFacadeCuenta.getItemsMovimientoCuenta(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                    this.getNombresSocio().toUpperCase(), 'S'));
        }
    }

    public void seleccionaCuenta(ActionEvent actionEvent) {
        context = RequestContext.getCurrentInstance();
        this.cuenta = this.getCuentaSel();
        this.numeroCuenta = this.cuenta.getNumero();
        this.socioBeneficiario = null;
        this.habilitaBotonGuardar();
        //context.execute("totalEfectivoTex.focus();");
        //this.nombreBeneficiario = cuenta.getSocio().getCodigoPersona().getNombreCompleto();
        this.socioBeneficiario = null;
        this.deshabilitaNombreBeneficiario = true;

    }

    public void buscaCuenta() {

        if (!validaCuenta()) {
            MuestraMensaje.addError(msg);
        }
        //Verificando si se habilita el boton guardar
        habilitaBotonGuardar();
    }

    //---------------------------------------------------------------------------
    // -- METODOS DE VALIDACIONES
    public boolean validaciones(boolean muestraMensaje) {
        ////System.out.println("Validaciones");
        //Valida los campos del moviento si estan vacios o cumplen requerimientos minimos
        if (this.validaCampos()) {
            if (validaNumeroCheque()) {
                if (this.validaCuenta()) {
                    if (this.validaSaldoCuenta()) {
                        return true;
                    }

                }
            }
        }

        ////System.out.println("Culmina Validaciones");
        try {
            if (muestraMensaje) {
                MuestraMensaje.addError(msg);
            }
        } catch (Exception er) {
            return false;
        }
        return false;
    }

    @SuppressWarnings("empty-statement")
    public boolean validaCampos() {
        try {
            if (this.moneda == null) {
                return false;
            }
            //System.out.println("1.1");
            if (this.productoIfip == null) {
                return false;
            }

            //System.out.println("1.1");
            if (this.ifipCuentaEntidadFinancieraSel == null) {
                return false;
            }

            //System.out.println("1.3 " + this.numeroCuenta);
            if ((this.numeroCuenta != null) ? this.numeroCuenta.trim().isEmpty() : null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaRequerida");

                return false;
            }
            //System.out.println("1.4");
            if ((this.getValor() != null) ? this.getValor().toString().trim().isEmpty() : null) {
                this.getRetiroCheque().setValor(new BigDecimal("0.00"));

            }

            //System.out.println("Compara valor " + getValor());
            if (this.getValor().compareTo(new BigDecimal("0.00")) <= 0) {
                //System.out.println("Entro en comparacion de valor ");
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovmientoNoPuedeSerCero");
                return false;
            }
            //System.out.println("1.5");
            if (this.getObservaciones() != null) {
                if (this.getObservaciones().trim().length() > 0 && this.getObservaciones().trim().length() <= 4) {
                    msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                    return false;
                }

            }
            //System.out.println("1.6" + " " + socioBeneficiario);

            if (this.socioBeneficiario == null) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("SocioBeneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                return false;
            } else {
                if (this.socioBeneficiario.trim().equals("")) {
                    msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("SocioBeneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
                    return false;
                }
            }

            //System.out.println("1.7");
            //System.out.println("getSocioBeneficiario " + getSocioBeneficiario());
            if (this.getSocioBeneficiario().equals("N")) {
                //System.out.println("No Es Socio beneficiario " + nombreBeneficiario);
                if (this.nombreBeneficiario.trim().length() > 0 && this.nombreBeneficiario.trim().length() <= 8) {
                    msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Beneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoOchoCacteres");
                    return false;

                }

            }
            //System.out.println("Valida Campos OK");
        } catch (Exception er) {
            return false;
        }

        return true;
    }

    public boolean validaSaldoCuenta() {
        msg = null;
        Long codigoCuenta = cuenta.getCodigo();
        cuenta = this.ejbFacadeCuenta.find(codigoCuenta);
        if ((this.getValor() != null) ? this.getValor().toString().trim().isEmpty() : null) {
            this.setValor(new BigDecimal("0.00"));
        }

        if (this.getValor().compareTo(new BigDecimal("0.00")) <= 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovmientoNoPuedeSerCero");
            return false;
        }
        {
            if (this.cuenta.getSaldoDisponible().subtract(this.getValor()).compareTo(new BigDecimal("0.00")) < 0) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaSinSaldoDisponible");
                MuestraMensaje.addError(msg);

                return false;
            }

            return true;
        }

    }

    public boolean validaCuenta() {
        msg = null;
        List<Cuenta> listaCuenta = ejbFacadeCuenta.getItemsNumero(this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                this.productoIfip.getProductoIfipPK().getCodigoMoneda(), this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                this.numeroCuenta.toUpperCase(),
                new Long(1));
        ////System.out.println("Cuenta " + listaCuenta);
        if (listaCuenta.isEmpty()) {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroCuentaNoExiste");
        } else if (listaCuenta.size() == 1) {

            this.cuenta = listaCuenta.get(0);
            this.numeroCuenta = this.cuenta.getNumero();
            /*this.nombreBeneficiario = cuenta.getSocio().getCodigoPersona().getNombreCompleto();
             this.socioBeneficiario = 'S';*/
            //this.deshabilitaNombreBeneficiario = true;            
            //this.cambiaSocioBeneficiario();
            if (this.cuenta.getCodigoEstado().getCodigo().toString().equals("3")) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaCerrada");
            } else if (String.valueOf(this.cuenta.getCodigoEstado().getIndicaActiva()).equals("N")) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Cuenta") + this.cuenta.getCodigoEstado().getNombre();
            }
        } else {
            this.cuenta = null;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroMasUnaCuenta");
        }

        return (msg == null);
    }

    private boolean validaNumeroCheque() {
        this.msg = null;
        //System.out.println("ifipCuentaEntidadFinancieraSel " + ifipCuentaEntidadFinancieraSel);
        TalonarioChequeDetalle talonarioChequeDetalle = this.ejbFacadeTalonarioChequeDetalle.find(new TalonarioChequeDetallePK(this.numeroCheque, this.ifipCuentaEntidadFinancieraSel.getCodigo()));
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

    public void compruebaObservaciones() {
        msg = null;
        if (this.observaciones != null) {
            if (this.observaciones.trim().length() > 0 && this.observaciones.trim().length() <= 4) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Observaciones") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoCincoCacteres");
                MuestraMensaje.addError(msg);
            }

        }

        this.habilitaBotonGuardar();

    }

    public void compruebaBeneficiario() {
        this.msg = null;
        //System.out.println("getSocioBeneficiario " + getSocioBeneficiario() + " nombreBeneficiario " + nombreBeneficiario + " " + this.nombreBeneficiario.trim().length());

        if (this.socioBeneficiario == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("SocioBeneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            if (this.socioBeneficiario.trim().equals("")) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("SocioBeneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");

            } else {
                if (this.getSocioBeneficiario().equals("N")) {
                    //System.out.println("nombreBeneficiario " + nombreBeneficiario + " " + this.nombreBeneficiario.trim().length());
                    if (this.nombreBeneficiario.trim().length() > 0 && this.nombreBeneficiario.trim().length() <= 8) {
                        msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Beneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanioMinimoOchoCacteres");

                    }
                }
            }
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
        }
        this.habilitaBotonGuardar();
    }

    public void compruebaCheque() {
        if (!validaNumeroCheque()) {
            MuestraMensaje.addError(msg);
        }
        this.habilitaBotonGuardar();

    }

    public void compruebaSaldo() {
        if (!validaSaldoCuenta()) {
            MuestraMensaje.addError(msg);
        }
        this.habilitaBotonGuardar();

    }

    public void habilitaBotonGuardar() {
        ////System.out.println("Habilita boton guardar");
        this.setDeshabilitaBotonGuardar(!this.validaciones(false));
    }

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    public List<IfipCuentaEntidadFinanciera> getItemsIfipCuentaEntidadFinancieras() {
        return this.ejbFacadeTalonarioCheque.getIfipCuentaEntidadFinancieraCheque(ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia(),'N');
    }

    // ------------------ REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * CUANDO CAMBIA EL PRODUCTO. BUSCA SI EL SOCIO TIENE UNA CUENTA DE
     * CERTIFICADOS ASIGNADA Y ESTE ACTIVA.
     */
    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
        this.setProductoIfip(null);

        this.setCuenta(null);
        this.setNumeroCuenta(null);

        this.habilitaBotonGuardar();

    }

    /**
     * CUANDO CAMBIA EL PRODUCTO.
     */
    public void cambiaProductoIfip() {

        ConceptoTransaccionPro conceptoTransaccionPro = ejbFacadeConceptoTransaccionPro.getIConceptoTransaccionPro(productoIfip.getProductoIfipPK().getCodigoTipoProducto(), productoIfip.getProductoIfipPK().getCodigoMoneda(), (long) 11);
        if (conceptoTransaccionPro != null) {
            this.setCuenta(null);
            this.setNumeroCuenta(null);
            this.setCuenta(null);
            this.getRetiroCheque().setValor(null);
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TransaccionRetiroChequeNoExsiteProducto");
            MuestraMensaje.addError(msg);
            this.productoIfip = null;
        }

        this.habilitaBotonGuardar();

    }

    public void cambiaCuentaEntidadFinanciera() {
        //System.out.println("ifipCuentaEntidadFinanciera " + ifipCuentaEntidadFinanciera + "ifipCuentaEntidadFinancieraSel " + ifipCuentaEntidadFinancieraSel);
        ifipCuentaEntidadFinancieraSel = (ifipCuentaEntidadFinanciera != null) ? ifipCuentaEntidadFinanciera : null;
        //System.out.println("ifipCuentaEntidadFinanciera " + ifipCuentaEntidadFinanciera + "ifipCuentaEntidadFinancieraSel " + ifipCuentaEntidadFinancieraSel);
        this.habilitaBotonGuardar();
    }

    /**
     * CAmbia Socios Beneficiario
     */
    public void cambiaSocioBeneficiario() {
        //System.out.println("socio beneficiario " + this.socioBeneficiario);

        if (this.socioBeneficiario == null) {
            msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("SocioBeneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
        } else {
            if (this.socioBeneficiario.trim().equals("")) {
                msg = ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("SocioBeneficiario") + " : " + ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");

            } else {
                if (this.getSocioBeneficiario().equals("N")) {
                    //System.out.println("habilita beneficiario");
                    this.nombreBeneficiario = null;
                    deshabilitaNombreBeneficiario = false;

                } else {
                    deshabilitaNombreBeneficiario = true;
                    //System.out.println("Desactiva beneficiario");
                    if (cuenta != null) {
                        this.nombreBeneficiario = cuenta.getSocio().getCodigoPersona().getNombreCompleto();
                    }
                }

            }
        }

        this.habilitaBotonGuardar();
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
     * @return the itemsProductoIfip
     */
    public List<ProductoIfip> getItemsProductoIfip() {
        return itemsProductoIfip;
    }

    /**
     * @param itemsProductoIfip the itemsProductoIfip to set
     */
    public void setItemsProductoIfip(List<ProductoIfip> itemsProductoIfip) {
        this.itemsProductoIfip = itemsProductoIfip;
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
     * @return the productoIfip
     */
    public ProductoIfip getProductoIfip() {
        return productoIfip;
    }

    /**
     * @param productoIfip the productoIfip to set
     */
    public void setProductoIfip(ProductoIfip productoIfip) {
        this.productoIfip = productoIfip;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
     * @return the itemsCuenta
     */
    public List<Cuenta> getItemsCuenta() {
        return itemsCuenta;
    }

    /**
     * @param itemsCuenta the itemsCuenta to set
     */
    public void setItemsCuenta(List<Cuenta> itemsCuenta) {
        this.itemsCuenta = itemsCuenta;
    }

    /**
     * @return the nombresSocio
     */
    public String getNombresSocio() {
        return nombresSocio;
    }

    /**
     * @param nombresSocio the nombresSocio to set
     */
    public void setNombresSocio(String nombresSocio) {
        this.nombresSocio = nombresSocio;
    }

    /**
     * @return the mensajeDialogo
     */
    public String getMensajeDialogo() {
        return mensajeDialogo;
    }

    /**
     * @param mensajeDialogo the mensajeDialogo to set
     */
    public void setMensajeDialogo(String mensajeDialogo) {
        this.mensajeDialogo = mensajeDialogo;
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
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the socioBeneficiario
     */
    public String getSocioBeneficiario() {
        return socioBeneficiario;
    }

    /**
     * @param socioBeneficiario the socioBeneficiario to set
     */
    public void setSocioBeneficiario(String socioBeneficiario) {
        this.socioBeneficiario = socioBeneficiario;
    }

    /**
     * @return the nombreBeneficiario
     */
    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    /**
     * @param nombreBeneficiario the nombreBeneficiario to set
     */
    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    /**
     * @return the deshabilitaNombreBeneficiario
     */
    public boolean isDeshabilitaNombreBeneficiario() {
        return deshabilitaNombreBeneficiario;
    }

    /**
     * @param deshabilitaNombreBeneficiario the deshabilitaNombreBeneficiario to
     * set
     */
    public void setDeshabilitaNombreBeneficiario(boolean deshabilitaNombreBeneficiario) {
        this.deshabilitaNombreBeneficiario = deshabilitaNombreBeneficiario;
    }

    /**
     * @return the numeroCheque
     */
    public long getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(long numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

}
