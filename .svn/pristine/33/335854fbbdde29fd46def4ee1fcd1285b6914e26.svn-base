/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.ahorros.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.reportes.GeneraReporte;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.LicitudFonCptoTran;
import ec.renafipse.mks.modelo.ahorros.LicitudFonOrgDest;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormulario;
import ec.renafipse.mks.modelo.ahorros.LicitudFondosFormularioAdi;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.TipoRelacion;
import ec.renafipse.mks.negocio.ahorros.CuentaFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFonCptoTranFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFonOrgDestFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosFormularioAdiFacade;
import ec.renafipse.mks.negocio.ahorros.LicitudFondosFormularioFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.TipoIdentificacionFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import ec.renafipse.mks.negocio.socios.TipoRelacionFacade;
import java.io.IOException;
import java.io.Serializable;
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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author v.astudillo
 */
@ManagedBean(name = "licitudFondosBean")
@ViewScoped
public class LicitudFondosBean extends AbstractController<LicitudFondosFormulario> implements Serializable {

    @EJB
    private LicitudFondosFormularioFacade ejbFacade;

    @EJB
    private LicitudFondosFormularioAdiFacade ejbFacadeLicitudFondosFormularioAdi;

    @EJB
    private LicitudFonOrgDestFacade ejbFacadeLicitudFonOrgDest;

    @EJB
    private CuentaFacade ejbFacadeCuenta;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private TipoIdentificacionFacade ejbFacadeTipoIdentificacion;

    @EJB
    private TipoPersonaFacade ejbFacadeTipoPersona;

    @EJB
    private TipoRelacionFacade ejbFacadeTipoRelacion;

    @EJB
    private LicitudFonCptoTranFacade ejbFacadeLicitudFonCptoTran;

    private String criterio;
    private String nombrePersona;
    private String dialogoMostrar;
    private String msg;
    private String numeroFormulario;

    private char estadoLicitud;

    private Long codigoFormulario;

    private RequestContext context;

    private boolean deshabilitaIdentificacionPersonaFirma;
    private boolean deshabilitaNombrePersonaFirma;
    private boolean deshabilitaBotonGuardarLicitud;
    private boolean deshabilitaBotonImprimirLicitud;
    private boolean deshabilitaBotonCerrarLicitud;
    private boolean deshabilitaComboOrigen;
    private boolean deshabilitaComboDestino;
    private boolean deshabilitaBotonFormulario;
    private boolean deshabilitaBotonAnular;
    private boolean existePersonaFirmaLicitud;
    private boolean existeLicitudFondosFormulario;
    private boolean llamadoOtrasVentanas;

    private LicitudFondosFormulario licitudFondosFormulario;
    private LicitudFondosFormularioAdi licitudFondosFormularioAdi;
    private LicitudFonOrgDest licitudFondosOrigen;
    private LicitudFonOrgDest licitudFondosDestino;
    private Cuenta cuenta;
    private Persona personaFirmaLicitud;
    private TipoRelacion tipoRelacion;

    private LlamaSP llamaSP;
    private GeneraReporte generaReporte;

    private List<LicitudFonOrgDest> itemsLicitudFondosOrigen;
    private List<LicitudFonOrgDest> itemsLicitudFondosDestino;
    private List<LicitudFondosFormulario> itemsLicitudFondosFormulario;
    private List<TipoRelacion> itemsTipoRelacion;
    private List<String> listaComponentesActualizar;

    public LicitudFondosBean() {
        super(LicitudFondosFormulario.class);
    }

    @PostConstruct
    public void init() {
        // //System.out.println("INicia datos");   
        super.setFacade(this.ejbFacade);
        this.licitudFondosFormularioAdi = null;
        this.tipoRelacion = null;
        this.itemsTipoRelacion = null;
        this.personaFirmaLicitud = null;
        this.cuenta = null;
        this.deshabilitaIdentificacionPersonaFirma = true;
        this.deshabilitaNombrePersonaFirma = true;
        this.deshabilitaBotonFormulario = true;
        this.deshabilitaBotonAnular = true;
        this.existeLicitudFondosFormulario = false;
        this.dialogoMostrar = null;
        this.llamadoOtrasVentanas = false;
        this.deshabilitaBotonGuardarLicitud = false;
        this.deshabilitaBotonCerrarLicitud = true;
        this.deshabilitaBotonImprimirLicitud = true;
        this.deshabilitaComboDestino = true;
        this.deshabilitaComboOrigen = true;
        this.cargarListaOrigenes('S');
        this.cargarListaDestinos('N');
        this.licitudFondosOrigen = null;
        this.licitudFondosDestino = null;

    }

    /**
     * Para ser llamado desde otros controles
     */
    public void preparaLicitudFondos() {
        this.init();
        this.llamadoOtrasVentanas = true;
        this.licitudFondosFormulario = this.ejbFacade.find(this.codigoFormulario);
        this.licitudFondosFormularioAdi = new LicitudFondosFormularioAdi();
        this.itemsTipoRelacion = ejbFacadeTipoRelacion.getItemsEsParaFirmantesEliminado('S', 'N');
        this.obtieneDatosCuenta();
    }

    /**
     * Prepara el formulario cuando se llama de la venta de licitud de fondos
     *
     *
     * @param actionEvent
     */
    public void preparaLicitudFondosFormulario(ActionEvent actionEvent) {
        try {
            this.init();
            this.itemsTipoRelacion = ejbFacadeTipoRelacion.getItemsEsParaFirmantesEliminado('S', 'N');
            this.cargarListaOrigenes('S');
            this.cargarListaDestinos('N');

            this.deshabilitaBotonCerrarLicitud = false;
            this.licitudFondosFormularioAdi = ejbFacadeLicitudFondosFormularioAdi.getLicitudFondosFormularioAdi(licitudFondosFormulario.getCodigo());
            System.out.println(licitudFondosFormularioAdi);
            // Si la solicitud existe
            if (this.licitudFondosFormularioAdi != null) {
                existeLicitudFondosFormulario = true;
                this.licitudFondosOrigen = this.licitudFondosFormularioAdi.getCodigoOrigen();
                this.licitudFondosDestino = this.licitudFondosFormularioAdi.getCodigoDestino();
                this.cuenta = this.ejbFacadeCuenta.find(this.licitudFondosFormularioAdi.getLicitudFondosFormulario().getCuenta().longValue());
                this.personaFirmaLicitud = this.ejbFacadePersona.find(this.licitudFondosFormularioAdi.getCodigoPersonaFirma());
                this.numeroFormulario = this.licitudFondosFormularioAdi.getNumeroFormulario();
                this.deshabilitaBotonImprimirLicitud = false;
                this.existePersonaFirmaLicitud = true;
                this.tipoRelacion = this.ejbFacadeTipoRelacion.find(licitudFondosFormularioAdi.getCodigoRelacion());
                if (this.licitudFondosFormularioAdi.getRealizaPersona() == 'N') {
                    this.deshabilitaIdentificacionPersonaFirma = false;
                }
            } else {
                this.licitudFondosFormularioAdi = new LicitudFondosFormularioAdi();
                this.obtieneDatosCuenta();
            }

            // Buscando la transaccion en la parametrizacion de licitud de fondos.
            List<LicitudFonCptoTran> listaLicitudFonCptoTran = ejbFacadeLicitudFonCptoTran.getByTipo(getLicitudFondosFormulario().getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoConcepto(),
                    getLicitudFondosFormulario().getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoMoneda(),
                    getLicitudFondosFormulario().getMovimiento().getConceptoTransaccionPro().getConceptoTransaccionProPK().getCodigoTipoProducto(),
                    getLicitudFondosFormulario().getCodigoIfip(),
                    'N');

            if (listaLicitudFonCptoTran.size() == 1) {

                switch (listaLicitudFonCptoTran.get(0).getTipo()) {
                    case 'A':
                        this.setDeshabilitaComboDestino(false);
                        this.setDeshabilitaComboOrigen(false);
                        break;
                    case 'O':
                        this.setDeshabilitaComboOrigen(false);
                        break;
                    default:
                        this.setDeshabilitaComboDestino(false);
                        break;
                }

            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExisteTransaccionLicitudFondos"));
                return;
            }

            listaComponentesActualizar = new ArrayList<String>();
            listaComponentesActualizar.add("LicitudFondosListForm");
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            //MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            ex.printStackTrace();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"licitudFondosBean", "preparaLicitudFondosFormulario", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Obteniendo datos de la cuenta del registro del formulario
     */
    private void obtieneDatosCuenta() {
        this.setCuenta(ejbFacadeCuenta.find(this.getLicitudFondosFormulario().getCuenta().longValue()));
    }

    /**
     * Cierra el Formulario de licitud de Fondos
     */
    public void cerrar() {
        context = RequestContext.getCurrentInstance();
        if (this.dialogoMostrar != null) {
            context.execute(this.dialogoMostrar + ".show()");
        }
        if (listaComponentesActualizar != null) {
            context.update(listaComponentesActualizar);
        }
        this.init();
        licitudFondosFormulario = null;
        this.obtieneLicitudFondos();

        if (!this.isLlamadoOtrasVentanas()) {
            listaComponentesActualizar = new ArrayList<String>();
            listaComponentesActualizar.add("LicitudFondosListForm");
            context.update(listaComponentesActualizar);
        }

    }

    /**
     * Obtiene las licitudes de Fondo de la Persona
     */
    public void obtieneLicitudFondos() {
        try {
            this.nombrePersona = null;
            this.itemsLicitudFondosFormulario = null;
            this.licitudFondosFormulario = null;
            if (this.criterio != null) {

                if (!this.criterio.trim().equals("")) {
                    this.itemsLicitudFondosFormulario = this.ejbFacade.getItemsIdentificacionPersonaEstadoAgencia(criterio, ActivacionUsuario.getCodigoIfip(), this.estadoLicitud, ActivacionUsuario.getCodigoIfipAgencia());
                    if (this.getItemsLicitudFondosFormulario().size() > 0) {
                        this.nombrePersona = this.getItemsLicitudFondosFormulario().get(0).getPersona().getNombreCompleto();
                    }
                } else {
                    this.itemsLicitudFondosFormulario = this.ejbFacade.getItemsEstadoAgencia(this.estadoLicitud, ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia());
                }
            } else {
                this.itemsLicitudFondosFormulario = this.ejbFacade.getItemsEstadoAgencia(this.estadoLicitud, ActivacionUsuario.getCodigoIfip(), ActivacionUsuario.getCodigoIfipAgencia());
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"licitudFondosBean", "obtieneLicitudFondos", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Cuando seleccione el formulario de licitud
     */
    public void seleccionaLicitud() {

        if (this.licitudFondosFormulario.getEstado() == 'A') {
            this.deshabilitaBotonFormulario = true;
            this.deshabilitaBotonAnular = true;
        } else {
            this.deshabilitaBotonFormulario = false;
            this.deshabilitaBotonAnular = false;
        }

    }

    /**
     * Anula el formulario de Licitud de Fondos
     *
     * @param actionEvent
     */
    public void anularFormulario(ActionEvent actionEvent) {
        // Actualizando el estado
        this.ejbFacade.actualizaEstadoLicitudFondos(this.licitudFondosFormulario.getCodigo(), 'A', new Date(), ActivacionUsuario.getCodigoUsuario());
        this.obtieneLicitudFondos();
    }

    /**
     * Carga el listado para seleccionar el origen o destino del dinero
     *
     * @param esOrigen S=SI N=NO
     */
    public void cargarListaOrigenes(char esOrigen) {
        this.setItemsLicitudFondosOrigen(this.ejbFacadeLicitudFonOrgDest.getItemsEsOrigenEliminado(esOrigen, 'N'));
    }

    public void cargarListaDestinos(char esOrigen) {
        this.setItemsLicitudFondosDestino(this.ejbFacadeLicitudFonOrgDest.getItemsEsOrigenEliminado(esOrigen, 'N'));
    }

    //-------------------------------------------------------------------------------------------
    // METODOS DE LLAMADAS A PROCEDIMIENTOS ALMACENADOS
    /**
     * Guarda el Formulario
     */
    /**
     * Guarda la Impresion de la Libreta
     *
     * @param actionEvent
     */
    public void guardaLicitud(ActionEvent actionEvent) {

        context = RequestContext.getCurrentInstance();

        if (!this.validaPersonaFirmante()) {
            return;
        }

        // Guardando la persona que firma la licitud de fondo
        if (!this.isExistePersonaFirmaLicitud()) {
            this.ejbFacadePersona.create(personaFirmaLicitud);
        }

        //Instancion el proceso de llama Store Procedure
        setLlamaSP(new LlamaSP());

        // //System.out.println("Guarda Formulario");
        try {

            if (!this.existeLicitudFondosFormulario) {

                // //System.out.println("Guarda Formulario " + this.personaFirmaLicitud);
                java.sql.Timestamp fechaRegistroLicitud = new java.sql.Timestamp(new Date().getTime());
                llamaSP.cargaConexion();
                llamaSP.setCerrarConexion(false);
                llamaSP.autoCommit(false);
                llamaSP.setNombreSP("mks_ahorros.pkm_licitud_fondos.p_guarda_formulario_adicional");
                llamaSP.setNumeroParametros(10);
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_formulario", this.licitudFondosFormulario.getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_origen", (this.licitudFondosOrigen != null) ? this.licitudFondosOrigen.getCodigo() : null});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_destino", (this.licitudFondosDestino != null) ? this.licitudFondosDestino.getCodigo() : null});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_persona_firma", (this.licitudFondosFormularioAdi.getRealizaPersona() == 'S') ? this.personaFirmaLicitud.getCodigo() : ((this.personaFirmaLicitud.getCodigo() != null) ? this.personaFirmaLicitud.getCodigo() : null)});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_formulario", null});
                //llamaSP.getListParametrosEntrada().add(new Object[]{"p_numero_formulario", this.licitudFondosFormularioAdi.getNumeroFormulario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_realiza_persona", String.valueOf(this.licitudFondosFormularioAdi.getRealizaPersona())});
                //llamaSP.getListParametrosEntrada().add(new Object[]{"p_identificacion", this.personaFirmaLicitud.getIdentificacion()});
                //llamaSP.getListParametrosEntrada().add(new Object[]{"p_nombre_completo", this.personaFirmaLicitud.getNombreCompleto()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_registro", fechaRegistroLicitud});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_registrado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_relacion", (this.tipoRelacion != null) ? this.tipoRelacion.getCodigo() : null});
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    // //System.out.println("Formulario Guardado");
                    llamaSP.commit();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    this.setDeshabilitaBotonGuardarLicitud(true);
                    this.deshabilitaBotonCerrarLicitud = false;
                    this.deshabilitaBotonImprimirLicitud = false;
                    //licitudFondosFormulario = null;
                    if (!this.isLlamadoOtrasVentanas()) {
                        MuestraMensaje.addSatisfactorioPersistencia(1);
                    }

                    //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MovimientoGrabado");
                    //MuestraMensaje.addInformacion(msg);
                } else {
                    context.execute("procesandoDlg.hide()");
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                    this.setDeshabilitaBotonGuardarLicitud(false);
                    this.deshabilitaBotonImprimirLicitud = true;
                    this.deshabilitaBotonCerrarLicitud = true;
                }
            } else {
                if (!this.numeroFormulario.equals(this.licitudFondosFormularioAdi.getNumeroFormulario())) {
                    if (this.ejbFacadeLicitudFondosFormularioAdi.getItemsNumeroFormularioCodigoIfio(this.licitudFondosFormularioAdi.getNumeroFormulario(), ActivacionUsuario.getCodigoIfip()).size() > 0) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NumeroDeFormularioYaAsigando"));
                        return;
                    }
                }

                // Actualizando datos del formulario adicional
                this.ejbFacadeLicitudFondosFormularioAdi.actualizaLicitudFondos(this.licitudFondosFormularioAdi.getCodigoFormulario(), licitudFondosOrigen, licitudFondosDestino, this.personaFirmaLicitud.getCodigo(), licitudFondosFormularioAdi.getNumeroFormulario(), new Date(), ActivacionUsuario.getCodigoUsuario(), (this.getTipoRelacion() != null) ? this.getTipoRelacion().getCodigo() : null, this.licitudFondosFormularioAdi.getRealizaPersona());

                if (!this.isLlamadoOtrasVentanas()) {
                    MuestraMensaje.addSatisfactorioPersistencia(1);
                }

            }
        } catch (Exception ex) {
            context.execute("procesandoDlg.hide()");
            ex.printStackTrace();
            this.setDeshabilitaBotonGuardarLicitud(false);
            this.deshabilitaBotonImprimirLicitud = true;
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"licitudFondosBean", "guardaLicitud", CapturaError.getErrorException(ex)});
        }

    }
    // FIN DE METODOS DE PROCEDIMIENTOS ALMACENADOS
    //----------------------------------------------------------------------------

    //---------------------------------------------------------------
    // -- MEOTODOS FIRMA DE PERSONA EL FORMULARIO DE LICITUD
    /**
     * VAlida datos de la persona que va a firmar la licitud
     *
     * @return
     */
    private boolean validaPersonaFirmante() {
        if (this.personaFirmaLicitud.getIdentificacion() == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseIdentificacion");
            MuestraMensaje.addError(msg);
            return false;
        }
        if (!Validaciones.validaCedula(this.personaFirmaLicitud.getIdentificacion())) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CedulaIncorrecta");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.personaFirmaLicitud.getNombreCompleto() == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseNombre");
            MuestraMensaje.addError(msg);
            return false;
        }

        if (this.tipoRelacion == null && this.licitudFondosFormularioAdi.getRealizaPersona() == 'N') {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeleccioneTipoRelacion");
            MuestraMensaje.addError(msg);
            return false;
        }

        return true;
    }

    /**
     * CAmbio de realiza persona
     */
    public void cambiaRealizaPersona() {
        this.setDeshabilitaIdentificacionPersonaFirma(true);
        this.personaFirmaLicitud = null;
        this.tipoRelacion = null;
        if (this.licitudFondosFormularioAdi.getRealizaPersona() == 'S') {
            this.personaFirmaLicitud = this.cuenta.getSocio().getCodigoPersona();
            this.setExistePersonaFirmaLicitud(true);
        } else {
            MuestraMensaje.addInformacion(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresePersonaFirmaLicitud"));
            this.setDeshabilitaIdentificacionPersonaFirma(false);
            this.personaFirmaLicitud = new Persona();
            this.personaFirmaLicitud.setIdentificacion(null);
            this.personaFirmaLicitud.setNombreCompleto(null);
        }
    }

    /**
     * Busca la Persona que va a firmar la Licitud de Fondos
     */
    public void buscaPersonaFirmante() {
        this.setMsg(null);
        if (this.personaFirmaLicitud.getIdentificacion() == null) {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseIdentificacion"));
            MuestraMensaje.addError(getMsg());
            return;
        }
        this.setExistePersonaFirmaLicitud(true);
        if (Validaciones.validaCedula(this.personaFirmaLicitud.getIdentificacion())) {
            Persona persona = this.ejbFacadePersona.getPersona(this.personaFirmaLicitud.getIdentificacion());
            if (persona != null) {
                this.setDeshabilitaNombrePersonaFirma(true);
                this.personaFirmaLicitud = persona;
            } else {
                this.setDeshabilitaNombrePersonaFirma(false);
                this.personaFirmaLicitud.setNombreCompleto(null);
                this.personaFirmaLicitud.setCodigoTipoIdentificacion(this.ejbFacadeTipoIdentificacion.find(1L));
                this.personaFirmaLicitud.setCodigoTipoPersona(this.ejbFacadeTipoPersona.find(1L));
                this.personaFirmaLicitud.setFechaIngreso(new Date());
                this.personaFirmaLicitud.setFechaActualizacion(new Date());
                this.personaFirmaLicitud.setCodigo(0L);
                this.setExistePersonaFirmaLicitud(false);

                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngreseNombre"));
                MuestraMensaje.addInformacion(getMsg());
            }
        } else {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CedulaIncorrecta"));
            MuestraMensaje.addError(getMsg());

        }
    }

    //-------------------------------------------------------------------------------
    // IMPRESIONES
    /**
     * Impresion de Licitud de Fondos
     *
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void imprimirLicitud(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException {
        String nombreReporte;

        // Actualizando a impreso el formulario de licitud de fondos
        ejbFacade.actualizaEstadoLicitudFondos(this.licitudFondosFormulario.getCodigo(), 'I', new Date(), ActivacionUsuario.getCodigoUsuario());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        setGeneraReporte(new GeneraReporte());
        getGeneraReporte().setParametros(new HashMap<String, Object>());

        getGeneraReporte().getParametros().put("codigoFormulario", this.licitudFondosFormulario.getCodigo());

        nombreReporte = "licitudFondos";

        getGeneraReporte().exporta("/financiero/ahorros/licitudFondos/reporte/", nombreReporte,
                nombreReporte + this.licitudFondosFormulario.getCodigo() + ".pdf",
                "PDF", externalContext, facesContext);

        //   licitudFondosFormulario = null;
    }

    /**
     * @return the licitudFondosFormulario
     */
    public LicitudFondosFormulario getLicitudFondosFormulario() {
        return licitudFondosFormulario;
    }

    /**
     * @param licitudFondosFormulario the licitudFondosFormulario to set
     */
    public void setLicitudFondosFormulario(LicitudFondosFormulario licitudFondosFormulario) {
        this.licitudFondosFormulario = licitudFondosFormulario;
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
     * @return the llamaSP
     */
    public LlamaSP getLlamaSP() {
        return llamaSP;
    }

    /**
     * @param llamaSP the llamaSP to set
     */
    public void setLlamaSP(LlamaSP llamaSP) {
        this.llamaSP = llamaSP;
    }

    /**
     * @return the licitudFondosFormularioAdi
     */
    public LicitudFondosFormularioAdi getLicitudFondosFormularioAdi() {
        return licitudFondosFormularioAdi;
    }

    /**
     * @param licitudFondosFormularioAdi the licitudFondosFormularioAdi to set
     */
    public void setLicitudFondosFormularioAdi(LicitudFondosFormularioAdi licitudFondosFormularioAdi) {
        this.licitudFondosFormularioAdi = licitudFondosFormularioAdi;
    }

    /**
     * @return the personaFirmaLicitud
     */
    public Persona getPersonaFirmaLicitud() {
        return personaFirmaLicitud;
    }

    /**
     * @param personaFirmaLicitud the personaFirmaLicitud to set
     */
    public void setPersonaFirmaLicitud(Persona personaFirmaLicitud) {
        this.personaFirmaLicitud = personaFirmaLicitud;
    }

    /**
     * @return the deshabilitaIdentificacionPersonaFirma
     */
    public boolean isDeshabilitaIdentificacionPersonaFirma() {
        return deshabilitaIdentificacionPersonaFirma;
    }

    /**
     * @param deshabilitaIdentificacionPersonaFirma the
     * deshabilitaIdentificacionPersonaFirma to set
     */
    public void setDeshabilitaIdentificacionPersonaFirma(boolean deshabilitaIdentificacionPersonaFirma) {
        this.deshabilitaIdentificacionPersonaFirma = deshabilitaIdentificacionPersonaFirma;
    }

    /**
     * @return the deshabilitaNombrePersonaFirma
     */
    public boolean isDeshabilitaNombrePersonaFirma() {
        return deshabilitaNombrePersonaFirma;
    }

    /**
     * @param deshabilitaNombrePersonaFirma the deshabilitaNombrePersonaFirma to
     * set
     */
    public void setDeshabilitaNombrePersonaFirma(boolean deshabilitaNombrePersonaFirma) {
        this.deshabilitaNombrePersonaFirma = deshabilitaNombrePersonaFirma;
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
     * @return the deshabilitaBotonGuardarLicitud
     */
    public boolean isDeshabilitaBotonGuardarLicitud() {
        return deshabilitaBotonGuardarLicitud;
    }

    /**
     * @param deshabilitaBotonGuardarLicitud the deshabilitaBotonGuardarLicitud
     * to set
     */
    public void setDeshabilitaBotonGuardarLicitud(boolean deshabilitaBotonGuardarLicitud) {
        this.deshabilitaBotonGuardarLicitud = deshabilitaBotonGuardarLicitud;
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
     * @return the existePersonaFirmaLicitud
     */
    public boolean isExistePersonaFirmaLicitud() {
        return existePersonaFirmaLicitud;
    }

    /**
     * @param existePersonaFirmaLicitud the existePersonaFirmaLicitud to set
     */
    public void setExistePersonaFirmaLicitud(boolean existePersonaFirmaLicitud) {
        this.existePersonaFirmaLicitud = existePersonaFirmaLicitud;
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
     * @return the estadoLicitud
     */
    public char getEstadoLicitud() {
        return estadoLicitud;
    }

    /**
     * @param estadoLicitud the estadoLicitud to set
     */
    public void setEstadoLicitud(char estadoLicitud) {
        this.estadoLicitud = estadoLicitud;
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    /**
     * @return the itemsLicitudFondosFormulario
     */
    public List<LicitudFondosFormulario> getItemsLicitudFondosFormulario() {
        return itemsLicitudFondosFormulario;
    }

    /**
     * @param itemsLicitudFondosFormulario the itemsLicitudFondosFormulario to
     * set
     */
    public void setItemsLicitudFondosFormulario(List<LicitudFondosFormulario> itemsLicitudFondosFormulario) {
        this.itemsLicitudFondosFormulario = itemsLicitudFondosFormulario;
    }

    /**
     * @return the deshabilitaBotonFormulario
     */
    public boolean isDeshabilitaBotonFormulario() {
        return deshabilitaBotonFormulario;
    }

    /**
     * @param deshabilitaBotonFormulario the deshabilitaBotonFormulario to set
     */
    public void setDeshabilitaBotonFormulario(boolean deshabilitaBotonFormulario) {
        this.deshabilitaBotonFormulario = deshabilitaBotonFormulario;
    }

    /**
     * @return the deshabilitaBotonAnular
     */
    public boolean isDeshabilitaBotonAnular() {
        return deshabilitaBotonAnular;
    }

    /**
     * @param deshabilitaBotonAnular the deshabilitaBotonAnular to set
     */
    public void setDeshabilitaBotonAnular(boolean deshabilitaBotonAnular) {
        this.deshabilitaBotonAnular = deshabilitaBotonAnular;
    }

    /**
     * @return the existeLicitudFondosFormulario
     */
    public boolean isExisteLicitudFondosFormulario() {
        return existeLicitudFondosFormulario;
    }

    /**
     * @param existeLicitudFondosFormulario the existeLicitudFondosFormulario to
     * set
     */
    public void setExisteLicitudFondosFormulario(boolean existeLicitudFondosFormulario) {
        this.existeLicitudFondosFormulario = existeLicitudFondosFormulario;
    }

    /**
     * @return the codigoFormulario
     */
    public Long getCodigoFormulario() {
        return codigoFormulario;
    }

    /**
     * @param codigoFormulario the codigoFormulario to set
     */
    public void setCodigoFormulario(Long codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }

    /**
     * @return the dialogoMostrar
     */
    public String getDialogoMostrar() {
        return dialogoMostrar;
    }

    /**
     * @param dialogoMostrar the dialogoMostrar to set
     */
    public void setDialogoMostrar(String dialogoMostrar) {
        this.dialogoMostrar = dialogoMostrar;
    }

    /**
     * @return the llamadoOtrasVentanas
     */
    public boolean isLlamadoOtrasVentanas() {
        return llamadoOtrasVentanas;
    }

    /**
     * @param llamadoOtrasVentanas the llamadoOtrasVentanas to set
     */
    public void setLlamadoOtrasVentanas(boolean llamadoOtrasVentanas) {
        this.llamadoOtrasVentanas = llamadoOtrasVentanas;
    }

    /**
     * @return the listaComponentesActualizar
     */
    public List<String> getListaComponentesActualizar() {
        return listaComponentesActualizar;
    }

    /**
     * @param listaComponentesActualizar the listaComponentesActualizar to set
     */
    public void setListaComponentesActualizar(List<String> listaComponentesActualizar) {
        this.listaComponentesActualizar = listaComponentesActualizar;
    }

    /**
     * @return the numeroFormulario
     */
    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    /**
     * @param numeroFormulario the numeroFormulario to set
     */
    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    /**
     * @return the deshabilitaBotonImprimirLicitud
     */
    public boolean isDeshabilitaBotonImprimirLicitud() {
        return deshabilitaBotonImprimirLicitud;
    }

    /**
     * @param deshabilitaBotonImprimirLicitud the
     * deshabilitaBotonImprimirLicitud to set
     */
    public void setDeshabilitaBotonImprimirLicitud(boolean deshabilitaBotonImprimirLicitud) {
        this.deshabilitaBotonImprimirLicitud = deshabilitaBotonImprimirLicitud;
    }

    /**
     * @return the deshabilitaBotonCerrarLicitud
     */
    public boolean isDeshabilitaBotonCerrarLicitud() {
        return deshabilitaBotonCerrarLicitud;
    }

    /**
     * @param deshabilitaBotonCerrarLicitud the deshabilitaBotonCerrarLicitud to
     * set
     */
    public void setDeshabilitaBotonCerrarLicitud(boolean deshabilitaBotonCerrarLicitud) {
        this.deshabilitaBotonCerrarLicitud = deshabilitaBotonCerrarLicitud;
    }

    /**
     * @return the itemsTipoRelacion
     */
    public List<TipoRelacion> getItemsTipoRelacion() {
        return itemsTipoRelacion;
    }

    /**
     * @param itemsTipoRelacion the itemsTipoRelacion to set
     */
    public void setItemsTipoRelacion(List<TipoRelacion> itemsTipoRelacion) {
        this.itemsTipoRelacion = itemsTipoRelacion;
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
     * @return the licitudFondosOrigen
     */
    public LicitudFonOrgDest getLicitudFondosOrigen() {
        return licitudFondosOrigen;
    }

    /**
     * @param licitudFondosOrigen the licitudFondosOrigen to set
     */
    public void setLicitudFondosOrigen(LicitudFonOrgDest licitudFondosOrigen) {
        this.licitudFondosOrigen = licitudFondosOrigen;
    }

    /**
     * @return the licitudFondosDestino
     */
    public LicitudFonOrgDest getLicitudFondosDestino() {
        return licitudFondosDestino;
    }

    /**
     * @param licitudFondosDestino the licitudFondosDestino to set
     */
    public void setLicitudFondosDestino(LicitudFonOrgDest licitudFondosDestino) {
        this.licitudFondosDestino = licitudFondosDestino;
    }

    /**
     * @return the deshabilitaComboOrigen
     */
    public boolean isDeshabilitaComboOrigen() {
        return deshabilitaComboOrigen;
    }

    /**
     * @param deshabilitaComboOrigen the deshabilitaComboOrigen to set
     */
    public void setDeshabilitaComboOrigen(boolean deshabilitaComboOrigen) {
        this.deshabilitaComboOrigen = deshabilitaComboOrigen;
    }

    /**
     * @return the deshabilitaComboDestino
     */
    public boolean isDeshabilitaComboDestino() {
        return deshabilitaComboDestino;
    }

    /**
     * @param deshabilitaComboDestino the deshabilitaComboDestino to set
     */
    public void setDeshabilitaComboDestino(boolean deshabilitaComboDestino) {
        this.deshabilitaComboDestino = deshabilitaComboDestino;
    }

    /**
     * @return the itemsLicitudFondosOrigen
     */
    public List<LicitudFonOrgDest> getItemsLicitudFondosOrigen() {
        return itemsLicitudFondosOrigen;
    }

    /**
     * @param itemsLicitudFondosOrigen the itemsLicitudFondosOrigen to set
     */
    public void setItemsLicitudFondosOrigen(List<LicitudFonOrgDest> itemsLicitudFondosOrigen) {
        this.itemsLicitudFondosOrigen = itemsLicitudFondosOrigen;
    }

    /**
     * @return the itemsLicitudFondosDestino
     */
    public List<LicitudFonOrgDest> getItemsLicitudFondosDestino() {
        return itemsLicitudFondosDestino;
    }

    /**
     * @param itemsLicitudFondosDestino the itemsLicitudFondosDestino to set
     */
    public void setItemsLicitudFondosDestino(List<LicitudFonOrgDest> itemsLicitudFondosDestino) {
        this.itemsLicitudFondosDestino = itemsLicitudFondosDestino;
    }

}
