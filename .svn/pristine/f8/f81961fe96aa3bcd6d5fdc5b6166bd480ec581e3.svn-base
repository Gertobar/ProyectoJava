package ec.renafipse.mks.control.ahorros;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuenta;
import ec.renafipse.mks.modelo.ahorros.FirmanteCuentaPK;
import ec.renafipse.mks.modelo.ahorros.LibretaAsignadaCuenta;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.TalonarioLibretaAhorroDet;
import ec.renafipse.mks.modelo.ahorros.TipoFirma;
import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.EstadoCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.FirmanteCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.LibretaAsignadaCuentaFacade;
import ec.renafipse.mks.negocio.ahorros.ProductoIfipFacade;
import ec.renafipse.mks.negocio.ahorros.TalonarioLibretaAhorroDetFacade;
import ec.renafipse.mks.negocio.ahorros.TipoFirmaFacade;
import ec.renafipse.mks.negocio.comunes.MonedaFacade;
import ec.renafipse.mks.negocio.ifips.IfipMonedaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.SocioFacade;
import ec.renafipse.mks.negocio.socios.TipoIdentificacionFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
import org.primefaces.event.CellEditEvent;

@ManagedBean(name = "cuentaController")
@ViewScoped
public class CuentaController extends AbstractController<Cuenta> implements Serializable {

    @EJB
    private CuentaFacade ejbFacade;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private TipoIdentificacionFacade ejbFacadeTipIde;

    @EJB
    private TipoPersonaFacade ejbFacadeTipPer;

    @EJB
    private SocioFacade ejbFacadeSocio;

    @EJB
    private ProductoIfipFacade ejbFacadeProuctoIfip;

    @EJB
    private FirmanteCuentaFacade ejbFacadeFirmanteCuenta;

    @EJB
    private TalonarioLibretaAhorroDetFacade ejbFacadeTalonarioLibretaAhorroDet;

    @EJB
    private LibretaAsignadaCuentaFacade ejbFacadeLibretaAsignadaCuenta;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private EstadoCuentaFacade ejbFacadeEstadoCuenta;

    @EJB
    private IfipMonedaFacade ejbFacadeIfipMoneda;

    @EJB
    private MonedaFacade ejbFacadeMoneda;

    @EJB
    private TipoFirmaFacade ejbFacadeTipoFirma;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private String buscarPor;
    private String criterio;
    private String tituloPanel;
    private String identificacionFirmante;
    private String nombreFirmante;
    private String numeroLibreta;
    private String nuevoNumeroLibreta;
    private String numeroCuenta;
    private String identificacionPersonaBeneficiario;
    private int secuenciaCuenta;
    private String mensajeCriterio;
    private String nombrePersonaBusqueda;

    private boolean verPanelFirmantes;
    private boolean cuentaNueva;
    private boolean asignaNumeroLibreta;
    private boolean deshabilitaBotonGuardar;
    private boolean deshabilitaBotonAgregarFirmante;
    private boolean deshabilitaRegistrarFirmante;
    private boolean deshabilidaNombreFirmante;
    private boolean deshabilidaProducto;
    private boolean deshabilidaMoneda;
    private boolean deshabilidaTipoFirmante;
    private boolean deshabilidaBeneficiario;
    private boolean deshabilidaCodigoPersonaBeneficiario;
    private boolean deshabilidaNumeroCuenta;

    private Socio socioSel;
    private ProductoIfip productoIfip;
    private Persona personaFirmante;
    private FirmanteCuenta firmanteCuenta;
    private FirmanteCuenta firmanteCuentaSel;
    private TipoRelacion tipoRelacion;
    private TipoFirma tipoFirma;
    private TalonarioLibretaAhorroDet talonarioLibretaAhorroDet;
    private LibretaAsignadaCuenta libretaAsignadaCuenta;
    private Moneda moneda;
    private Persona personaBeneficiario;
    private Persona personaBeneficiarioSel;

    private List<Socio> itemsSocios;
    private List<Cuenta> itemsCuentasSocio;
    private List<FirmanteCuenta> itemsFirmanteCuenta;
    private List<ProductoIfip> itemsProductoIfip;
    private List<Persona> itemsPersona;

    private LlamaSP llamaSP;

    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public CuentaController() {
        super(Cuenta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.verPanelFirmantes = true;
        //Instancion el proceso de llama Store Procedure
        llamaSP = new LlamaSP();

    }

    // *******************************************************************************************
    // --------------------------------------------------------------------------
    // -- LOGICA
    public void edicionCeldasFirmantes(CellEditEvent event) {
        this.activaBotonGuardar();
    }

    /**
     * Obtiene los datos del socio en base del criterio de la consulta
     */
    public void obtieneCuentasSocios() {
        try {
            if (this.getSocioSel() != null) {
                this.setItemsCuentasSocio(this.ejbFacade.getItemsCuentasCodigoSocioCodigoIfip(this.socioSel.getSocioPK().getCodigo(), this.socioSel.getSocioPK().getCodigoIfip()));
                if (criterio == null) {
                    this.criterio = this.buscarPor.equals("N") ? this.getSocioSel().getCodigoPersona().getNombreCompleto() : this.getSocioSel().getCodigoPersona().getIdentificacion();
                }
            }
            this.setSelected(null);
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtieneCuentasSocios", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     */
    public void obtieneSocios() {
        try {
            this.itemsCuentasSocio = null;
            this.itemsSocios = null;
            this.setSocioSel(null);
            this.setSelected(null);
            if (Validaciones.cumpleRequerimientoCampo(criterio, 6)) {

                this.setItemsSocios(this.ejbFacadeSocio.getItemsSocio(this.getCriterio(), this.getBuscarPor(), ActivacionUsuario.getCodigoIfip()));
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

    /**
     * Obtiene los datos de las personas buscado por un nombre
     */
    public void obtienePersonas() {
        try {
            this.setPersonaBeneficiarioSel(null);
            this.setItemsPersona(null);
            if (Validaciones.cumpleRequerimientoCampo(this.nombrePersonaBusqueda, 10)) {
                this.setItemsPersona(this.ejbFacadePersona.getItemsPersona(this.nombrePersonaBusqueda.toUpperCase(), "N", 'N'));
            } else {
                this.mensajeCriterio = Validaciones.msg;

            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtienePersonas", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Prepara la lista de valores para la busqueda
     *
     * @param actionEvent
     */
    public void preparaBusquedaPersonaLista(ActionEvent actionEvent) {
        this.nombrePersonaBusqueda = null;
        this.personaBeneficiarioSel = null;
        this.itemsPersona = null;
        this.criterio = null;
    }

    /**
     * Selecciona la persona beneficiario
     */
    public void seleccionaPersona() {
        this.personaBeneficiario = personaBeneficiarioSel;
        this.identificacionPersonaBeneficiario = this.personaBeneficiario.getIdentificacion();
        this.activaBotonGuardar();
    }

    /**
     * PREPARA LA CREACION DE LA CUENTA AL PRESIONAR EL BOTON NUEVO
     *
     * @param actionEvent
     */
    public void creaCuenta(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        if (this.getSocioSel().getCodigoEstadoSocio().getCodigo() == 2) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioInactivo"));
            return;
        }
        this.inicializaCampos();
        context.execute("CuentaCreateDialog.show()");

    }

    /**
     * Inicializa campos de creacion
     */
    public void inicializaCampos() {
        this.setSelected(new Cuenta());
        this.setNuevoNumeroLibreta(null);
        this.verPanelFirmantes = true;
        this.deshabilitaBotonGuardar = true;
        this.deshabilidaNombreFirmante = true;
        this.deshabilitaBotonAgregarFirmante = true;
        this.deshabilitaRegistrarFirmante = true;
        this.itemsFirmanteCuenta = null;
        this.identificacionFirmante = null;
        this.nombreFirmante = null;
        this.deshabilidaTipoFirmante = false;
        this.deshabilidaMoneda = false;
        this.deshabilidaProducto = false;
        this.productoIfip = null;
        this.moneda = null;
        this.getSelected().setNumero("0");
        this.setCuentaNueva(true);
        this.tituloPanel = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CreacionCuenta");
        this.tipoFirma = null;
        this.deshabilidaTipoFirmante = false;
        this.deshabilidaBeneficiario = true;
        this.deshabilidaCodigoPersonaBeneficiario = true;
        this.personaBeneficiario = null;
        this.deshabilidaNumeroCuenta = false;
        this.asignaNumeroLibreta = false;
        this.personaBeneficiario = new Persona();
        this.numeroLibreta = null;
        this.identificacionPersonaBeneficiario = null;
    }

    /**
     * Inicializa los campos de edicion y verifica que el socio este activo
     *
     * @return
     */
    private boolean inicializaCamposEdicion() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (this.getSocioSel().getCodigoEstadoSocio().getCodigo() == 2) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioInactivo"));
            return false;
        }

        if (this.getSelected() == null) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneCuenta"));
            return false;
        }

        //this.nuevoNumeroLibreta = null;
        this.verPanelFirmantes = true;
        this.deshabilitaBotonGuardar = true;
        this.deshabilidaNombreFirmante = true;
        this.deshabilitaBotonAgregarFirmante = true;
        this.deshabilitaRegistrarFirmante = true;
        this.identificacionFirmante = null;
        this.nombreFirmante = null;
        this.deshabilidaTipoFirmante = false;
        this.deshabilidaMoneda = true;
        this.deshabilidaProducto = true;
        this.productoIfip = null;
        this.moneda = null;
        this.numeroLibreta = this.getSelected().getNumeroLibreta();
        this.tipoFirma = this.getSelected().getCodigoTipoFirma();

        if (tipoFirma.getTieneFirmas() == 'S') {
            this.setItemsFirmanteCuenta(this.ejbFacadeFirmanteCuenta.getItemsFirmanteCuenta(this.getSelected().getCodigo()));
        }
        this.setCuentaNueva(false);
        this.moneda = this.ejbFacadeMoneda.find(this.getSelected().getProductoIfip().getProductoIfipPK().getCodigoMoneda());
        this.cambiaMoneda();
        this.productoIfip = this.getSelected().getProductoIfip();
        this.deshabilidaTipoFirmante = true;
        this.asignaNumeroLibreta = false;
        this.numeroLibreta = null;

        if (this.getSelected().getCodigoPersonaBeneficiario() != null) {
            this.personaBeneficiario = this.ejbFacadePersona.find(this.getSelected().getCodigoPersonaBeneficiario());
            this.identificacionPersonaBeneficiario = personaBeneficiario.getIdentificacion();
        } else {
            this.identificacionPersonaBeneficiario = null;
            this.personaBeneficiario = new Persona();
        }
        return true;
    }

    /**
     * PREPARA LA EDICION DE UNA CUENTA
     *
     * @param actionEvent
     */
    public void editaCuenta(ActionEvent actionEvent) {

        RequestContext context = RequestContext.getCurrentInstance();
        if (this.getSocioSel().getCodigoEstadoSocio().getCodigo() == 2) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioInactivo"));
            return;
        }

        if (this.inicializaCamposEdicion()) {
            this.tituloPanel = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EditaCuenta");
            this.deshabilidaNumeroCuenta = true;
            this.deshabilidaBeneficiario = false;
            this.deshabilidaCodigoPersonaBeneficiario = false;
            if (this.getSelected().getCodigoTipoFirma().getTieneFirmas() == 'S') {
                this.deshabilidaBeneficiario = true;
                this.deshabilidaCodigoPersonaBeneficiario = true;
            } else {
                if (this.getSelected().getEsSocioBeneficiario() == 'S') {
                    this.deshabilidaCodigoPersonaBeneficiario = true;
                }
            }

            this.numeroLibreta = this.getSelected().getNumeroLibreta();
            context.execute("CuentaCreateDialog.show()");
        }

    }

    /**
     * PREPARA LA EDICION DE UNA CUENTA
     *
     * @param actionEvent
     */
    public void asignaLibreta(ActionEvent actionEvent) {

        RequestContext context = RequestContext.getCurrentInstance();
        if (this.getSocioSel().getCodigoEstadoSocio().getCodigo() == 2) {
            context.execute("procesandoDlg.hide()");
            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SocioInactivo"));
            return;
        }

        if (this.inicializaCamposEdicion()) {
            this.tituloPanel = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AsignarNumeroLibreta");
            this.deshabilidaNumeroCuenta = false;
            this.deshabilidaBeneficiario = true;
            this.deshabilidaCodigoPersonaBeneficiario = true;
            this.asignaNumeroLibreta = true;
            context.execute("AsignaLibretaCuentaDialog.show()");
        }
    }

    /**
     * GUARDA LA CUENTA YA SEA POR CREACION O EDICION.
     */
    public void guardaCuenta() {
        try {

            // Colocando valores ya sea por la creacion o edicion de la cuenta
            this.getSelected().setCodigoIfip(ActivacionUsuario.getCodigoIfip());
            this.getSelected().setActualizadoPor(this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario()));
            this.getSelected().setFechaActualizacion(new Date());

            // Si la cuenta es nueva
            if (this.isCuentaNueva()) {
                if (obtieneNumeroCuenta()) {

                    if (this.talonarioLibretaAhorroDet != null) {
                        this.getSelected().setNumeroLibreta(talonarioLibretaAhorroDet.getNumeroLibreta());
                    }

                    // Colocando valores cuando es por edicion de la cuenta
                    this.getSelected().setCreadaPor(this.ejbFacadeUsuario.find(ActivacionUsuario.getCodigoUsuario()));
                    this.getSelected().setFechaCreacion(new Date());
                    this.getSelected().setCodigoTipoFirma(tipoFirma);
                    this.getSelected().setActualizadoPor(this.getSelected().getCreadaPor());
                    this.getSelected().setFechaActualizacion(this.getSelected().getFechaCreacion());
                    this.getSelected().setCodigoMoneda(this.getProductoIfip().getProductoIfipPK().getCodigoMoneda());
                    this.getSelected().setCodigoSocio(this.getSocioSel().getSocioPK().getCodigo());
                    this.getSelected().setCodigoTipoProducto(this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto());
                    this.getSelected().setProductoIfip(getProductoIfip());
                    this.getSelected().setCodigoEstado(this.ejbFacadeEstadoCuenta.find(Long.parseLong("1")));
                    this.getSelected().setProvisionAcumulada(BigDecimal.ZERO);
                    this.getSelected().setSaldoBloqueado(BigDecimal.ZERO);
                    this.getSelected().setSaldoDisponible(BigDecimal.ZERO);
                    this.getSelected().setSaldoTotal(BigDecimal.ZERO);
                    this.getSelected().setNumero(numeroCuenta);
                    this.getSelected().setSecuenciaNumero(secuenciaCuenta);
                    this.getSelected().setCodigoPersonaBeneficiario(this.getTipoFirma().getTieneFirmas() == 'S' ? null : personaBeneficiario.getCodigo());
                    this.getSelected().setCodigoIfipAgenciaApertura(ActivacionUsuario.getCodigoIfipAgencia());

                    // Creando la cuenta 
                    this.ejbFacade.create(this.getSelected());
                    this.libretaAsignadaCuenta = new LibretaAsignadaCuenta();
                    this.libretaAsignadaCuenta.setCodigoUsuarioAsignadoPor(ActivacionUsuario.getCodigoUsuario());
                    this.libretaAsignadaCuenta.setCodigoCuenta(this.getSelected());
                    this.libretaAsignadaCuenta.setFechaAsignacion(new Date());
                    this.libretaAsignadaCuenta.setNumeroLibreta(this.getSelected().getNumeroLibreta());
                    // Insertando
                    this.ejbFacadeLibretaAsignadaCuenta.crear(libretaAsignadaCuenta);

                }

            } else {
                numeroLibreta = this.getSelected().getNumeroLibreta();
                if (this.talonarioLibretaAhorroDet != null) {
                    this.getSelected().setNumeroLibreta(talonarioLibretaAhorroDet.getNumeroLibreta());
                    numeroLibreta = talonarioLibretaAhorroDet.getNumeroLibreta();
                }

                // Consultando si se asigna un numero nuevo de libreta
                if (this.asignaNumeroLibreta) {
                    if (this.talonarioLibretaAhorroDet != null) {
                        this.getSelected().setNumeroLibreta(talonarioLibretaAhorroDet.getNumeroLibreta());
                        // Actualizando cuenta
                        this.ejbFacade.actualizaNumeroLibreta(this.getSelected(), talonarioLibretaAhorroDet.getNumeroLibreta());
                    }

                    // Asigando un nuevo numero de libreta a la cuenta, en caso de que se este asigando otro numero de libreta
                    // Actualizando datos del talonario
                    this.ejbFacade.actualizaLibretaTalonario(talonarioLibretaAhorroDet, 'A');

                    // Guardando la Libreta Asignada
                    this.libretaAsignadaCuenta = new LibretaAsignadaCuenta();
                    this.libretaAsignadaCuenta.setCodigoUsuarioAsignadoPor(ActivacionUsuario.getCodigoUsuario());
                    this.libretaAsignadaCuenta.setCodigoCuenta(this.getSelected());
                    this.libretaAsignadaCuenta.setFechaAsignacion(new Date());
                    this.libretaAsignadaCuenta.setNumeroLibreta(this.getSelected().getNumeroLibreta());
                    // Insertando
                    this.ejbFacadeLibretaAsignadaCuenta.crear(libretaAsignadaCuenta);

                    // Cuenta Editada
                } else {

                    // Actualizando el Beneficiario
                    this.ejbFacade.actualizaBeneficiario(this.getSelected().getEsSocioBeneficiario(), this.personaBeneficiario, this.getSelected().getCodigo());
                    // Actualizando el tipo de firma
                    this.ejbFacade.actualizaTipoFirma(this.getTipoFirma(), this.getSelected().getCodigo());

                }

                if (this.getTipoFirma().getTieneFirmas() == 'S') {
                    //System.out.println("Cuenta con Firmantes");
                    // Guardando los Firmantes
                    for (FirmanteCuenta fc : this.itemsFirmanteCuenta) {
                        //fc.setCuenta(this.getSelected());
                        fc.setFechaRegistro(new Date());
                        fc.setCodigoTipRel(fc.getTipoRelacion().getCodigo());
                        fc.getFirmanteCuentaPK().setCodigoCuenta(this.getSelected().getCodigo());
                        //System.out.println("Codigo cuenta firmante " + fc.getFirmanteCuentaPK().getCodigoCuenta());
                        if (this.ejbFacadeFirmanteCuenta.find(new FirmanteCuentaPK(fc.getFirmanteCuentaPK().getCodigoCuenta(), fc.getFirmanteCuentaPK().getCodigoPersona())) == null) {
                            //System.out.println("crea firmante");
                            this.ejbFacadeFirmanteCuenta.create(fc);
                        } else {
                            //System.out.println("Actualiza Firmante");
                            this.ejbFacadeFirmanteCuenta.actualizaFirmante(fc.getFirmanteCuentaPK(), fc.getTipoRelacion().getCodigo(), fc.getEliminado());
                        }
                    }
                }
            }

            // Mensaje de creacion o edicion de la cuenta
            String msg;
            if (this.isCuentaNueva()) {
                //Cuenta cuenta = this.ejbFacade.find(this.getSelected().getCodigo());
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaCreada");
                msg = msg + ". " + ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Producto") + ": " + this.getProductoIfip().getProducto().getTipoProducto().getNombre();
                msg = msg + ". " + ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Numero") + ": " + numeroCuenta;
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaModificada");
                msg = msg + ". " + ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Producto") + ": " + this.getProductoIfip().getProducto().getTipoProducto().getNombre();
                msg = msg + ". " + ResourceBundle.getBundle("/propiedadesEtiquetaEC").getString("Numero") + ": " + this.getSelected().getNumero();
            }

            // Mostrando el mensaje
            MuestraMensaje.addSatisfactorio(msg);

            RequestContext context = RequestContext.getCurrentInstance();
            if (asignaNumeroLibreta) {
                context.execute("AsignaLibretaCuentaDialog.hide()");
            } else {
                context.execute("CuentaCreateDialog.hide()");
            }

            // Obteniendo nuevamente las cuentas del socio
            this.obtieneCuentasSocios();
            this.inicializaCampos();
            this.setSelected(null);

        } catch (NumberFormatException ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "guardaCuenta", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * ACTIVA EL BOTON GUARDAR.
     */
    public void activaBotonGuardar() {
        try{
            this.deshabilitaBotonGuardar = true;
            if (this.numeroLibreta != null) {
                //System.out.println("tiene libreta");
                if (this.personaBeneficiario == null && this.getSelected().getEsSocioBeneficiario() == 'N') {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("BeneficiarioRequerido"));
                    return;
                }
                if (this.getTipoFirma().getTieneFirmas() == 'S') {
                    if (this.getItemsFirmanteCuenta() != null) {
                        if (itemsFirmanteCuenta == null ){
                            MuestraMensaje.addError("La cuenta fue marcada con un TIPO DE FIRMA pero no se encuentran firmantes registrados en el sistema");
                        }else{
                            if (itemsFirmanteCuenta.size() <= 0){
                                MuestraMensaje.addError("La cuenta fue marcada con un TIPO DE FIRMA pero no se encuentran firmantes registrados en el sistema");
                            }
                        }
                        for (FirmanteCuenta fc : this.itemsFirmanteCuenta) {
                            if (String.valueOf(fc.getEliminado()).equals("N")) {
                                this.deshabilitaBotonGuardar = false;
                            }
                            if (!deshabilitaBotonGuardar) {
                                break;
                            }
                        }
                    }
                } else {
                    this.deshabilitaBotonGuardar = false;
                }
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroLibretaNoExistente"));
            }
        } catch(Exception e)  {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"CuentaController", "activaBotonGuardar", CapturaError.getErrorException(e)});
            deshabilitaBotonGuardar = true;
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     * BUSCA EL NUMERO DE LIBRETA PARA VALIDAR SU ASIGNACION.
     */
    public void buscaNumeroLibreta() {
        try {
            String msg = null;
            // Verificando que el nuevo ingresado sea nuemerico
            try {
                Long.parseLong(this.getNumeroLibreta());
            } catch (NumberFormatException er) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("AceptaSoloNumericos");
                this.talonarioLibretaAhorroDet = null;
                this.deshabilitaBotonGuardar = true;
                MuestraMensaje.addError(msg);
                this.numeroLibreta = null;
                return;
            }
            this.talonarioLibretaAhorroDet = this.ejbFacadeTalonarioLibretaAhorroDet.getLibretaIfipProductoMonedaSerie(this.productoIfip.getProductoIfipPK().getCodigoTipoProducto(),
                    this.productoIfip.getProductoIfipPK().getCodigoMoneda(),
                    this.productoIfip.getProductoIfipPK().getCodigoIfip(),
                    Long.parseLong(this.numeroLibreta));

            if (this.talonarioLibretaAhorroDet == null) {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroLibretaNoExistente");
            } else {
                if (!String.valueOf(talonarioLibretaAhorroDet.getEstado()).equals("P")) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroLibretaYaAsignada");
                    this.talonarioLibretaAhorroDet = null;
                } else {

                    Formatter fmt = new Formatter();
                    this.setNumeroLibreta(fmt.format("%0" + talonarioLibretaAhorroDet.getCodigoTalLibAhoCab().getDigitosLibreta() + "d", Integer.parseInt(this.numeroLibreta)).toString());
                }
            }

            if (msg != null) {
                //this.deshabilitaBotonGuardar = true;
                MuestraMensaje.addError(msg);
                this.talonarioLibretaAhorroDet = null;
                this.numeroLibreta = null;
            }

            this.activaBotonGuardar();
        }catch(NumberFormatException e)  {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"CuentaController", "buscaNumeroLibreta", CapturaError.getErrorException(e)});
            deshabilitaBotonGuardar = true;
            MuestraMensaje.addErrorCapaControl();
        } catch(Exception e)  {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"CuentaController", "buscaNumeroLibreta", CapturaError.getErrorException(e)});
            deshabilitaBotonGuardar = true;
            MuestraMensaje.addErrorCapaControl();
        }
    }

    /**
     * Buscando la persona que sera beneficiario
     */
    public void buscaPersonaBeneficiario() {
        if (this.getSelected().getEsSocioBeneficiario() == 'N') {
            this.personaBeneficiario = this.ejbFacadePersona.getPersona(this.identificacionPersonaBeneficiario);
            this.identificacionPersonaBeneficiario = this.personaBeneficiario.getIdentificacion();
        }

        this.activaBotonGuardar();
    }

    //--------------------------------------------------------------------------------------
    // BASE DE DATOS
    private boolean obtieneNumeroCuenta() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {

            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            llamaSP.setNombreSP("mks_ahorros.pkm_cuenta.p_obtiene_siguiente_num_cta");
            llamaSP.setNumeroParametros(5);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_tipo_producto", this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_moneda", this.getProductoIfip().getProductoIfipPK().getCodigoMoneda()});

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"p_secuencia", Types.NUMERIC});
            llamaSP.getListParametrosSalida().add(new Object[]{"p_numero", Types.VARCHAR});

            // Invocando al SP
            llamaSP.invocaSP();

            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {

                llamaSP.cerrarConexion();

                // Recuperando datos
                this.secuenciaCuenta = Integer.parseInt(llamaSP.getListResultado().get(0).toString());
                this.numeroCuenta = llamaSP.getListResultado().get(1).toString();

                llamaSP.setConexionBD(null);

                //context.execute("MovimientoBovedaFor.update()");
            } else {
                context.execute("procesandoDlg.hide()");
                llamaSP.rollback();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);

                return false;
            }

        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"cuentaController", "obtieneNumeroCuenta", CapturaError.getErrorException(ex)});

            return false;
        }

        return true;
    }
    // -------------------------------------------------------------------------------------
    // --- FIRMANTES
    /**
     * Busca y Valida datos del firmante, si esta en la grilla y registrado como
     * persona.
     */
    public void buscaFirmante() {
        String msg = null;
        if (this.getTipoFirma().getTieneFirmas() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaNoPuedeTenerFirmantes");
            MuestraMensaje.addError(msg);
            return;
        }
        this.deshabilitaBotonAgregarFirmante = true;
        this.deshabilitaRegistrarFirmante = true;
        this.deshabilidaNombreFirmante = true;
        //////System.out.println("IdentificacionFirmante " + this.getIdentificacionFirmante());
        if (this.getIdentificacionFirmante().length() > 0) {
            if (Validaciones.validaCedula(this.getIdentificacionFirmante())) {
                this.setPersonaFirmante(null);
                this.setPersonaFirmante(this.ejbFacadePersona.getPersona(this.getIdentificacionFirmante()));
                if (this.getPersonaFirmante() != null) {
                    if (String.valueOf(this.getPersonaFirmante().getCodigoTipoPersona().getEsParaInstitucion()).equals("N")) {
                        if (this.itemsFirmanteCuenta != null) {
                            for (FirmanteCuenta fc : this.itemsFirmanteCuenta) {
                                if (fc.getFirmanteCuentaPK().getCodigoPersona() == getPersonaFirmante().getCodigo()) {
                                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FirmanteYaIngresado");
                                    break;
                                }
                            }
                        }
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaDebeSerNatural");
                    }
                } else {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoRegistrada");
                    this.deshabilitaRegistrarFirmante = false;
                    this.deshabilidaNombreFirmante = false;

                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            }

            if (msg != null) {
                MuestraMensaje.addAdvertencia(msg);
                this.setPersonaFirmante(null);
                this.deshabilitaBotonAgregarFirmante = true;
                this.nombreFirmante = null;
                this.identificacionFirmante = null;
            } else {
                this.deshabilitaBotonAgregarFirmante = false;
                this.nombreFirmante = this.getPersonaFirmante().getNombreCompleto();
            }
        }
    }

    /**
     * Cuando el Firmante no esta registrado en el sistema (base de datos) se
     * activa los casilleros y boton REGISTRA, este metodo guarda el firmante
     * como persona
     */
    public void registraFirmante() {
        String msg;
        if (this.getTipoFirma().getTieneFirmas() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaNoPuedeTenerFirmantes");
            MuestraMensaje.addError(msg);
            return;
        }
        //////System.out.println("Firmante ");
        if (Validaciones.validaCedula(this.getIdentificacionFirmante())) {
            if (this.getNombreFirmante() != null) {
                try {
                    Persona per = new Persona();
                    per.setCodigoTipoIdentificacion(this.ejbFacadeTipIde.find(1L));
                    per.setCodigoTipoPersona(this.ejbFacadeTipPer.find(1L));
                    per.setFechaActualizacion(new Date());
                    per.setFechaIngreso(new Date());
                    per.setNombreCompleto(this.getNombreFirmante());
                    per.setIdentificacion(this.getIdentificacionFirmante());
                    this.ejbFacadePersona.create(per);
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("RepresentanteRegistrado");
                    MuestraMensaje.addInformacion(msg);
                    this.buscaFirmante();
                } catch (Exception e) {
                    msg = CapturaError.getErrorPersistencia(e);
                    MuestraMensaje.addErrorPersistencia(msg);
                }
            }
        } else {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionIncorrecta");
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    /**
     * Agrega un Firmante a la Cuenta Boton Agregar
     */
    public void agregaFirmante() {
        String msg;
        if (this.getTipoFirma().getTieneFirmas() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaNoPuedeTenerFirmantes");
            MuestraMensaje.addError(msg);
            return;
        }

        if (this.getPersonaFirmante() != null) {

            //////System.out.println("Agrega Firmante");
            if (this.itemsFirmanteCuenta == null) {
                this.itemsFirmanteCuenta = new ArrayList<FirmanteCuenta>();
            }

            // Verificando que el firmante ya esta en la lista
            for (FirmanteCuenta fc : itemsFirmanteCuenta) {
                if (fc.getPersona().getIdentificacion().equals(personaFirmante.getIdentificacion())) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("FirmanteYaEstaEnLista");
                    MuestraMensaje.addError(msg);
                    return;
                }
            }

            this.setFirmanteCuenta(new FirmanteCuenta());
            this.firmanteCuenta.setFirmanteCuentaPK(new FirmanteCuentaPK());
            this.firmanteCuenta.getFirmanteCuentaPK().setCodigoPersona(this.personaFirmante.getCodigo());
            this.firmanteCuenta.getFirmanteCuentaPK().setCodigoCuenta((this.isCuentaNueva()) ? -1L : this.getSelected().getCodigo());
            this.firmanteCuenta.setCodigoTipRel(this.getTipoRelacion().getCodigo());
            this.firmanteCuenta.setEliminado('N');
            this.firmanteCuenta.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
            this.firmanteCuenta.setPersona(personaFirmante);
            this.firmanteCuenta.setTipoRelacion(this.getTipoRelacion());
            this.itemsFirmanteCuenta.add(firmanteCuenta);

            this.setFirmanteCuenta(new FirmanteCuenta());
            this.personaFirmante = null;
            this.deshabilidaNombreFirmante = true;
            this.deshabilitaBotonAgregarFirmante = true;
            this.nombreFirmante = null;
            this.identificacionFirmante = null;
            this.activaBotonGuardar();
        }
    }

    /**
     * Quita el firmante Selecciona de la Grilla. Validando Primero sin no esta
     * ingresado en la Base de Datos.
     */
    public void quitaFirmante() {
        boolean registrado = false;
        System.out.println("OUT 1 "+this.getFirmanteCuentaSel());
        System.out.println("OUT 2 "+this.getFirmanteCuentaSel().getFirmanteCuentaPK());
        System.out.println("OUT 3 "+this.getFirmanteCuentaSel().getFirmanteCuentaPK().getCodigoCuenta());
        if (String.valueOf(this.getFirmanteCuentaSel().getFirmanteCuentaPK().getCodigoCuenta()) != null) {
            if (this.ejbFacadeFirmanteCuenta.find(this.getFirmanteCuentaSel().getFirmanteCuentaPK()) != null) {
                registrado = true;
            }
        }

        if (registrado) {
            String msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoSePuedeQuitarRegistrado");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            this.getItemsFirmanteCuenta().remove(this.getFirmanteCuentaSel());
        }

    }
    // --- FIN FIRMANTES
    // -------------------------------------------------------------------------------------

    // ------------------ REFRESCAMIENTO DE COMBOS/LISTAS ---------------------
    /**
     * CUANDO CAMBIA TIPO DE FIRMANTE, PARA VER EL PANEL DE FIRMANTES O NO.
     */
    public void cambiaTipoFirma() {
        //System.out.println("tiene firmas "+this.getTipoFirma().getTieneFirmas());        
        this.getSelected().setEsSocioBeneficiario('S');
        if (this.getTipoFirma().getTieneFirmas() == 'S') {
            this.verPanelFirmantes = true;
            this.deshabilidaBeneficiario = true;
            this.deshabilidaCodigoPersonaBeneficiario = true;
            this.personaBeneficiario = null;
            this.identificacionPersonaBeneficiario = null;
            this.setItemsFirmanteCuenta(this.ejbFacadeFirmanteCuenta.getItemsFirmanteCuenta(this.getSelected().getCodigo()));
        } else {
            this.deshabilidaBeneficiario = false;
            this.getSelected().setCodigoPersonaBeneficiario(this.getSocioSel().getCodigoPersona().getCodigo());
            this.personaBeneficiario = this.getSocioSel().getCodigoPersona();
            this.identificacionPersonaBeneficiario = personaBeneficiario.getIdentificacion();
            this.itemsFirmanteCuenta = null;
        }

        this.deshabilidaNombreFirmante = true;
        this.deshabilitaBotonAgregarFirmante = true;
        this.deshabilitaRegistrarFirmante = true;
        this.identificacionFirmante = null;
        this.nombreFirmante = null;
        this.activaBotonGuardar();

    }

    /**
     * CUANDO CAMBIA EL PRODUCTO. BUSCA SI EL SOCIO TIENE UNA CUENTA DE
     * CERTIFICADOS ASIGNADA Y ESTE ACTIVA.
     */
    public void cambiaProducto() {
        String msg = null;
        if (String.valueOf(this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto()).equals("1")) {
            if (this.ejbFacade.getItemsCuentasSocioIfipTipoProductoEstado(this.getSocioSel().getSocioPK().getCodigo(),
                    this.getSocioSel().getSocioPK().getCodigoIfip(),
                    this.getProductoIfip().getProductoIfipPK().getCodigoMoneda(),
                    this.getProductoIfip().getProductoIfipPK().getCodigoTipoProducto(),
                    Long.parseLong("1")).size() > 0) {
                this.deshabilitaBotonGuardar = true;
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CuentaCertificadosAsignada");
            } else {
                this.activaBotonGuardar();
            }
        }

        if (msg != null) {
            MuestraMensaje.addError(msg);
        }

    }

    /**
     * CUANDO CAMBIA LA MONEDA O SELECCIONA.
     */
    public void cambiaMoneda() {
        this.setItemsProductoIfip(ejbFacadeProuctoIfip.getItemsProductoIfip(ActivacionUsuario.getCodigoIfip(), this.getMoneda().getCodigo(), 'N'));
        this.setProductoIfip(null);

    }

    /**
     * CUANDO CAMBIA EL COMBO DE SI EL SOCIO ES BENEFICIARIO
     */
    public void cambiaSocioBeneficiario() {
        if (this.getSelected().getEsSocioBeneficiario() == 'N') {
            this.deshabilidaCodigoPersonaBeneficiario = false;
            this.personaBeneficiario = null;
            this.identificacionPersonaBeneficiario = null;
        } else {
            if (this.getSocioSel() != null) {
                this.personaBeneficiario = this.getSocioSel().getCodigoPersona();
                this.identificacionPersonaBeneficiario = personaBeneficiario.getIdentificacion();
                this.deshabilidaCodigoPersonaBeneficiario = true;
            }
        }

        this.activaBotonGuardar();

    }
    // ------------------ FIN REFRESCAMIENTO DE COMBOS/LISTAS ---------------------

    // ---------------------------------------------------------------------------
    // -- LISTAS DE COMBOS QUE NO SON POR REFRESCAMIENTOS Y QYUE SON USADOS EN LAS PANTALLAS
    // -- NO SON ENCAPSULAMIENTO DE ATRIBUTOS (SET Y GET)
    public List<Moneda> getItemsMoneda() {
        return this.ejbFacadeIfipMoneda.getItemsCodigoIfipEliminado(ActivacionUsuario.getCodigoIfip(), 'N');
    }

    public List<TipoFirma> getItemsTipoFirma() {
        return this.ejbFacadeTipoFirma.getItemsTipoFirma('N');
    }
    // -- FIN LISTAS DE COMBOS 
    // ---------------------------------------------------------------------------

    // *******************************************************************************************
    // --------------------------------------------------------------------------    
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
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
     * @return the verPanelFirmantes
     */
    public boolean isVerPanelFirmantes() {
        return verPanelFirmantes;
    }

    /**
     * @param verPanelFirmantes the verPanelFirmantes to set
     */
    public void setVerPanelFirmantes(boolean verPanelFirmantes) {
        this.verPanelFirmantes = verPanelFirmantes;
    }

    /**
     * @return the cuentaNueva
     */
    public boolean isCuentaNueva() {
        return cuentaNueva;
    }

    /**
     * @param cuentaNueva the cuentaNueva to set
     */
    public void setCuentaNueva(boolean cuentaNueva) {
        this.cuentaNueva = cuentaNueva;
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
     * @return the deshabilitaBotonAgregarFirmante
     */
    public boolean isDeshabilitaBotonAgregarFirmante() {
        return deshabilitaBotonAgregarFirmante;
    }

    /**
     * @param deshabilitaBotonAgregarFirmante the
     * deshabilitaBotonAgregarFirmante to set
     */
    public void setDeshabilitaBotonAgregarFirmante(boolean deshabilitaBotonAgregarFirmante) {
        this.deshabilitaBotonAgregarFirmante = deshabilitaBotonAgregarFirmante;
    }

    /**
     * @return the itemsFirmanteCuenta
     */
    public List<FirmanteCuenta> getItemsFirmanteCuenta() {
        return itemsFirmanteCuenta;
    }

    /**
     * @param itemsFirmanteCuenta the itemsFirmanteCuenta to set
     */
    public void setItemsFirmanteCuenta(List<FirmanteCuenta> itemsFirmanteCuenta) {
        this.itemsFirmanteCuenta = itemsFirmanteCuenta;
    }

    /**
     * @return the identificacionFirmante
     */
    public String getIdentificacionFirmante() {
        return identificacionFirmante;
    }

    /**
     * @param identificacionFirmante the identificacionFirmante to set
     */
    public void setIdentificacionFirmante(String identificacionFirmante) {
        this.identificacionFirmante = identificacionFirmante;
    }

    /**
     * @return the nombreFirmante
     */
    public String getNombreFirmante() {
        return nombreFirmante;
    }

    /**
     * @param nombreFirmante the nombreFirmante to set
     */
    public void setNombreFirmante(String nombreFirmante) {
        this.nombreFirmante = nombreFirmante;
    }

    /**
     * @return the deshabilitaRegistrarFirmante
     */
    public boolean isDeshabilitaRegistrarFirmante() {
        return deshabilitaRegistrarFirmante;
    }

    /**
     * @param deshabilitaRegistrarFirmante the deshabilitaRegistrarFirmante to
     * set
     */
    public void setDeshabilitaRegistrarFirmante(boolean deshabilitaRegistrarFirmante) {
        this.deshabilitaRegistrarFirmante = deshabilitaRegistrarFirmante;
    }

    /**
     * @return the deshabilidaNombreFirmante
     */
    public boolean isDeshabilidaNombreFirmante() {
        return deshabilidaNombreFirmante;
    }

    /**
     * @param deshabilidaNombreFirmante the deshabilidaNombreFirmante to set
     */
    public void setDeshabilidaNombreFirmante(boolean deshabilidaNombreFirmante) {
        this.deshabilidaNombreFirmante = deshabilidaNombreFirmante;
    }

    /**
     * @return the personaFirmante
     */
    public Persona getPersonaFirmante() {
        return personaFirmante;
    }

    /**
     * @param personaFirmante the personaFirmante to set
     */
    public void setPersonaFirmante(Persona personaFirmante) {
        this.personaFirmante = personaFirmante;
    }

    /**
     * @return the firmanteCuenta
     */
    public FirmanteCuenta getFirmanteCuenta() {
        return firmanteCuenta;
    }

    /**
     * @param firmanteCuenta the firmanteCuenta to set
     */
    public void setFirmanteCuenta(FirmanteCuenta firmanteCuenta) {
        this.firmanteCuenta = firmanteCuenta;
    }

    /**
     * @return the firmanteCuentaSel
     */
    public FirmanteCuenta getFirmanteCuentaSel() {
        return firmanteCuentaSel;
    }

    /**
     * @param firmanteCuentaSel the firmanteCuentaSel to set
     */
    public void setFirmanteCuentaSel(FirmanteCuenta firmanteCuentaSel) {
        this.firmanteCuentaSel = firmanteCuentaSel;
    }

    /**
     * @return the tipoRelacion
     */
    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    /**
     * @return the talonarioLibretaAhorroDet
     */
    public TalonarioLibretaAhorroDet getTalonarioLibretaAhorroDet() {
        return talonarioLibretaAhorroDet;
    }

    /**
     * @param talonarioLibretaAhorroDet the talonarioLibretaAhorroDet to set
     */
    public void setTalonarioLibretaAhorroDet(TalonarioLibretaAhorroDet talonarioLibretaAhorroDet) {
        this.talonarioLibretaAhorroDet = talonarioLibretaAhorroDet;
    }

    /**
     * @return the libretaAsignadaCuenta
     */
    public LibretaAsignadaCuenta getLibretaAsignadaCuenta() {
        return libretaAsignadaCuenta;
    }

    /**
     * @param libretaAsignadaCuenta the libretaAsignadaCuenta to set
     */
    public void setLibretaAsignadaCuenta(LibretaAsignadaCuenta libretaAsignadaCuenta) {
        this.libretaAsignadaCuenta = libretaAsignadaCuenta;
    }

    /**
     * @return the numeroLibreta
     */
    public String getNumeroLibreta() {
        return numeroLibreta;
    }

    /**
     * @param numeroLibreta the numeroLibreta to set
     */
    public void setNumeroLibreta(String numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
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
     * @return the deshabilidaProducto
     */
    public boolean isDeshabilidaProducto() {
        return deshabilidaProducto;
    }

    /**
     * @param deshabilidaProducto the deshabilidaProducto to set
     */
    public void setDeshabilidaProducto(boolean deshabilidaProducto) {
        this.deshabilidaProducto = deshabilidaProducto;
    }

    /**
     * @return the deshabilidaMoneda
     */
    public boolean isDeshabilidaMoneda() {
        return deshabilidaMoneda;
    }

    /**
     * @param deshabilidaMoneda the deshabilidaMoneda to set
     */
    public void setDeshabilidaMoneda(boolean deshabilidaMoneda) {
        this.deshabilidaMoneda = deshabilidaMoneda;
    }

    /**
     * @return the deshabilidaTipoFirmante
     */
    public boolean isDeshabilidaTipoFirmante() {
        return deshabilidaTipoFirmante;
    }

    /**
     * @param deshabilidaTipoFirmante the deshabilidaTipoFirmante to set
     */
    public void setDeshabilidaTipoFirmante(boolean deshabilidaTipoFirmante) {
        this.deshabilidaTipoFirmante = deshabilidaTipoFirmante;
    }

    /**
     * @return the tituloPanel
     */
    public String getTituloPanel() {
        return tituloPanel;
    }

    /**
     * @param tituloPanel the tituloPanel to set
     */
    public void setTituloPanel(String tituloPanel) {
        this.tituloPanel = tituloPanel;
    }

    /**
     * @return the nuevoNumeroLibreta
     */
    public String getNuevaLibreta() {
        return getNuevoNumeroLibreta();
    }

    /**
     * @param nuevoNumeroLibreta the nuevoNumeroLibreta to set
     */
    public void setNuevaLibreta(String nuevoNumeroLibreta) {
        this.setNuevoNumeroLibreta(nuevoNumeroLibreta);
    }

    /**
     * @return the nuevoNumeroLibreta
     */
    public String getNuevoNumeroLibreta() {
        return nuevoNumeroLibreta;
    }

    /**
     * @param nuevoNumeroLibreta the nuevoNumeroLibreta to set
     */
    public void setNuevoNumeroLibreta(String nuevoNumeroLibreta) {
        this.nuevoNumeroLibreta = nuevoNumeroLibreta;
    }

    /**
     * @return the tipoFirma
     */
    public TipoFirma getTipoFirma() {
        return tipoFirma;
    }

    /**
     * @param tipoFirma the tipoFirma to set
     */
    public void setTipoFirma(TipoFirma tipoFirma) {
        this.tipoFirma = tipoFirma;
    }

    /**
     * @return the personaBeneficiario
     */
    public Persona getCodigoPersonaBeneficiario() {
        return personaBeneficiario;
    }

    /**
     * @param personaBeneficiario the personaBeneficiario to set
     */
    public void setCodigoPersonaBeneficiario(Persona personaBeneficiario) {
        this.personaBeneficiario = personaBeneficiario;
    }

    /**
     * @return the deshabilidaBeneficiario
     */
    public boolean isDeshabilidaBeneficiario() {
        return deshabilidaBeneficiario;
    }

    /**
     * @param deshabilidaBeneficiario the deshabilidaBeneficiario to set
     */
    public void setDeshabilidaBeneficiario(boolean deshabilidaBeneficiario) {
        this.deshabilidaBeneficiario = deshabilidaBeneficiario;
    }

    /**
     * @return the deshabilidaCodigoPersonaBeneficiario
     */
    public boolean isDeshabilidaCodigoPersonaBeneficiario() {
        return deshabilidaCodigoPersonaBeneficiario;
    }

    /**
     * @param deshabilidaCodigoPersonaBeneficiario the
     * deshabilidaCodigoPersonaBeneficiario to set
     */
    public void setDeshabilidaCodigoPersonaBeneficiario(boolean deshabilidaCodigoPersonaBeneficiario) {
        this.deshabilidaCodigoPersonaBeneficiario = deshabilidaCodigoPersonaBeneficiario;
    }

    /**
     * @return the deshabilidaNumeroCuenta
     */
    public boolean isDeshabilidaNumeroCuenta() {
        return deshabilidaNumeroCuenta;
    }

    /**
     * @param deshabilidaNumeroCuenta the deshabilidaNumeroCuenta to set
     */
    public void setDeshabilidaNumeroCuenta(boolean deshabilidaNumeroCuenta) {
        this.deshabilidaNumeroCuenta = deshabilidaNumeroCuenta;
    }

    /**
     * @return the asignaNumeroLibreta
     */
    public boolean isAsignaNumeroLibreta() {
        return asignaNumeroLibreta;
    }

    /**
     * @param asignaNumeroLibreta the asignaNumeroLibreta to set
     */
    public void setAsignaNumeroLibreta(boolean asignaNumeroLibreta) {
        this.asignaNumeroLibreta = asignaNumeroLibreta;
    }

    /**
     * @return the identificacionPersonaBeneficiario
     */
    public String getIdentificacionPersonaBeneficiario() {
        return identificacionPersonaBeneficiario;
    }

    /**
     * @param identificacionPersonaBeneficiario the
     * identificacionPersonaBeneficiario to set
     */
    public void setIdentificacionPersonaBeneficiario(String identificacionPersonaBeneficiario) {
        this.identificacionPersonaBeneficiario = identificacionPersonaBeneficiario;
    }

    /**
     * @return the personaBeneficiario
     */
    public Persona getPersonaBeneficiario() {
        return personaBeneficiario;
    }

    /**
     * @param personaBeneficiario the personaBeneficiario to set
     */
    public void setPersonaBeneficiario(Persona personaBeneficiario) {
        this.personaBeneficiario = personaBeneficiario;
    }

    /**
     * @return the itemsPersona
     */
    public List<Persona> getItemsPersona() {
        return itemsPersona;
    }

    /**
     * @param itemsPersona the itemsPersona to set
     */
    public void setItemsPersona(List<Persona> itemsPersona) {
        this.itemsPersona = itemsPersona;
    }

    /**
     * @return the mensajeCriterio
     */
    public String getMensajeCriterio() {
        return mensajeCriterio;
    }

    /**
     * @param mensajeCriterio the mensajeCriterio to set
     */
    public void setMensajeCriterio(String mensajeCriterio) {
        this.mensajeCriterio = mensajeCriterio;
    }

    /**
     * @return the nombrePersonaBusqueda
     */
    public String getNombrePersonaBusqueda() {
        return nombrePersonaBusqueda;
    }

    /**
     * @param nombrePersonaBusqueda the nombrePersonaBusqueda to set
     */
    public void setNombrePersonaBusqueda(String nombrePersonaBusqueda) {
        this.nombrePersonaBusqueda = nombrePersonaBusqueda;
    }

    /**
     * @return the personaBeneficiarioSel
     */
    public Persona getPersonaBeneficiarioSel() {
        return personaBeneficiarioSel;
    }

    /**
     * @param personaBeneficiarioSel the personaBeneficiarioSel to set
     */
    public void setPersonaBeneficiarioSel(Persona personaBeneficiarioSel) {
        this.personaBeneficiarioSel = personaBeneficiarioSel;
    }

}
