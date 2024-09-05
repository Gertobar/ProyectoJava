/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.adquisicones.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorCuentaEntFinFacade;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.adquisiciones.DocumentosProveedor;
import ec.renafipse.mks.modelo.adquisiciones.DocumentosProveedorPK;
import ec.renafipse.mks.modelo.adquisiciones.Proveedor;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorCuentaEntFin;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfip;
import ec.renafipse.mks.modelo.adquisiciones.ProveedorIfipPK;
import ec.renafipse.mks.modelo.adquisiciones.TipoComprobanteCompra;
import ec.renafipse.mks.modelo.adquisiciones.TipoContribuyente;
import ec.renafipse.mks.modelo.comunes.EntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.OperadoraTelefono;
import ec.renafipse.mks.modelo.comunes.TipoCuentaEntidadFinanciera;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.PersonaTelefono;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.negocio.adquisiciones.DocumentosProveedorFacade;
//import ec.renafipse.mks.negocio.adquisiciones.ProveedorCuentaEntFinFacade;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorFacade;
import ec.renafipse.mks.negocio.adquisiciones.ProveedorIfipFacade;
import ec.renafipse.mks.negocio.adquisiciones.TipoComprobanteCompraFacade;
import ec.renafipse.mks.negocio.adquisiciones.TipoContribuyenteFacade;
import ec.renafipse.mks.negocio.comunes.EntidadFinancieraFacade;
import ec.renafipse.mks.negocio.comunes.OperadoraTelefonoFacade;
import ec.renafipse.mks.negocio.comunes.TipoCuentaEntidadFinancieraFacade;
import ec.renafipse.mks.negocio.comunes.TipoTelefonoFacade;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.TipoIdentificacionFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author vicastoc
 */
@ManagedBean(name = "proveedorBean")
@ViewScoped
public class ProveedorBean extends AbstractController<Proveedor> implements Serializable {

    @Inject
    private ProveedorFacade ejbFacadeProveedor;

    @EJB
    private ProveedorIfipFacade ejbFacadeProveedorIfip;

    @EJB
    private DocumentosProveedorFacade ejbFacadeDocumentosProveedor;

    @EJB
    private ProveedorCuentaEntFinFacade ejbFacadeProveedorCuentaEntFin;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private UsuarioFacade ejbFacadeUsuario;

    @EJB
    private TipoIdentificacionFacade ejbFacadeTipoIdentificacion;

    @EJB
    private TipoContribuyenteFacade ejbFacadeTipoContribuyente;

    @EJB
    private TipoPersonaFacade ejbFacadeTipoPersona;

    @EJB
    private OperadoraTelefonoFacade ejbFacadeOperadoraTelefono;

    @EJB
    private TipoTelefonoFacade ejbFacadeTipoTelefono;

    @EJB
    private TipoComprobanteCompraFacade ejbFacadeTipoComprobanteCompra;

    @EJB
    private EntidadFinancieraFacade ejbFacadeEntidadFinanciera;

    @EJB
    private TipoCuentaEntidadFinancieraFacade ejbFacadeTipCueEntFinanciera;

    @EJB
    private IfipFacade ejbFacadeIfip;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    private Proveedor proveedor;
    private Proveedor proveedorSel;
    private ProveedorIfip proveedorIfip;
    private DocumentosProveedor documentosProveedor;
    private DocumentosProveedor documentosProveedorSel;
    private Persona persona;
    private Persona personaP;
    private Persona personaSel;
    private DocumentosProveedorPK documentosProveedorPK;
    private Usuario usuario;
    private Usuario usuarioProveedorIfip;
    private Usuario registradoProvPor;
    private TipoIdentificacion tipoIdentificacion;
    private TipoContribuyente tipoContribuyente;
    private TipoPersona tipoPersona;
    private TipoComprobanteCompra tipoComprobanteCompra;
    private PersonaTelefono personaTelefono;
    private ProveedorCuentaEntFin proveedorCuentaEntFin;
    private EntidadFinanciera entidadFinanciera;
    private TipoCuentaEntidadFinanciera tipoCuentaEntidadFinanciera;

    private List<Proveedor> itemsProveedor;
    private List<ProveedorIfip> itemsProveedoresIfip;
    private List<TipoContribuyente> itemsTipoContribuyente;
    private List<DocumentosProveedor> itemsDocumentosProveedor;
    private List<ProveedorCuentaEntFin> itemsProveedorCuentaEntFin;
    private List<Socio> itemsSocios;
    private List<Persona> itemsPersona;
    private List<Usuario> itemsUsuario;
    private List<Usuario> itemsUsuarioProveedorIfip;
    private List<TipoIdentificacion> itemsTipoIdentificacion;
    private List<TipoPersona> itemsTipoPersona;
    private List<TipoComprobanteCompra> itemsTipoComprobanteCompra;
    private List<EntidadFinanciera> itemsEntidadFinanciera;
    private List<TipoCuentaEntidadFinanciera> itemsTipoCuentaEntidadFinanciera;
    private String msg;

    private int estadoProveedor;
    private int estadoOpProveedor;
    private short digitosComproProv;

    private Long iniSerieDocumentosProv;
    private Long finSerieDocumentosProv;
    private Long codigoEntidadFinanciera;
    private Long codigoProveedor;
    private Long codigoTipoCuentaEntidadFinanciera;
    private Long codigoPersona;
    private String buscarPor;
    private String identificacion;
    private String nombreCompleto;
    private String fechaIngreso;
    private String fechaActualizacion;
    private String correoElectronico;
    private String criterioConsulta;
    private String numeroTelefono;
    private String serieDocumentosProv;
    private String numeroAutorizacionDocumento;
    private String numeroCuentaProCEF;
    private String nombreCuentaProCEF;
    private String observacionesProCEF;
    private String estadoProCEF;
    private boolean personaNueva;
    private boolean existePersona;
    private boolean existe;
    private boolean saltar;
    private boolean personaExistente;
    private boolean proveedorExistente;
    private boolean documentosProveedorExistente;
    private boolean cuentaEntfinProExistente;
    private boolean ifipProveeExistente;
    private boolean estadoIngresoPro;
    private boolean estadoIngresoProIfip;
    private boolean estadoProCEFEdicion;
    private boolean desHabilitaRise;
    private boolean banderaCueEntidad;
    private boolean banderaCuenEntMov;

    private Date fechaEmisionDocumento;
    private Date fechaIngresoPersona;
    private Date fechaActualizacionPersona;
    private Date fechaCaducaDocumento;
    private Date fechaIngresoDocumento;
    private Date fechaRegistroProIfip;
    private Date fechaRegistroProAux;

    private Timestamp fechaRegistroProTs;
    private Timestamp fechaIngresoProveedorTs;
    private Timestamp fechaActualizacionProveedorTs;
    private Timestamp fechaEmisionDocumentoTs;
    private Timestamp fechaCaducaDocumentoTs;
    private Timestamp fechaIngresoDocumentoTs;
    private Timestamp fechaRegistroProIfipTs;
    
    private LlamaSP llamaSP;

    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public ProveedorBean() {
        super(Proveedor.class);
//        this.personaP=new Persona();
    }

    @PostConstruct
    public void init() {
        llamaSP = new LlamaSP();
        super.setFacade(ejbFacadeProveedor);
        this.persona = new Persona();

        // setItemsUsuario(ejbFacadeUsuario.findAll());
        // setItemsUsuarioProveedorIfip(ejbFacadeUsuario.findAll());
        itemsTipoIdentificacion = new ArrayList<TipoIdentificacion>();
        List<TipoIdentificacion> itemsTipoIdentAux = ejbFacadeTipoIdentificacion.findAll();
        for (int i = 0; i < 2; i++) {
            itemsTipoIdentificacion.add(itemsTipoIdentAux.get(i));
        }
        setItemsTipoIdentificacion(ejbFacadeTipoIdentificacion.findAll());
        setItemsTipoPersona(ejbFacadeTipoPersona.findAll());
        setItemsTipoContribuyente(ejbFacadeTipoContribuyente.getItemsTipConEliminado());
        //setItemsPersona(ejbFacadePersona.findAll());
        setItemsTipoComprobanteCompra(ejbFacadeTipoComprobanteCompra.getItemsTipoComprobanteEliminado('N'));
        setItemsEntidadFinanciera(ejbFacadeEntidadFinanciera.getItemsEntidadFinancieraEliminado('N'));
        setItemsTipoCuentaEntidadFinanciera(ejbFacadeTipCueEntFinanciera.getItemsTipoCuentaEntFinEliminado('N'));
//        
        this.nombreCompleto = null;
        this.fechaIngreso = null;
        this.fechaActualizacion = null;
        this.correoElectronico = null;
        //proveedorSel = new Proveedor();
        proveedor = new Proveedor();
        this.buscarPor = "N";
        estadoProveedor = 0;   //
        fechaIngresoDocumento = new Date();
        fechaCaducaDocumento = new Date();
        fechaEmisionDocumento = new Date();
        fechaRegistroProIfip = new Date();
        estadoOpProveedor = 0;
        this.desHabilitaRise = false;
        this.banderaCuenEntMov = false;
    }

    public void preparaProveedorComponente(ActionEvent actionEvent) throws IOException {

        inicioCampos();
    }

    public void inicioCampos() {
        tipoCuentaEntidadFinanciera = null;
        entidadFinanciera = null;
        numeroCuentaProCEF = null;
        nombreCuentaProCEF = null;
        observacionesProCEF = null;

        identificacion = null;
        itemsPersona = null;
        itemsProveedor = null;
        setItemsDocumentosProveedor(new ArrayList<DocumentosProveedor>());
        itemsProveedorCuentaEntFin = null;
        itemsProveedoresIfip = null;

        persona = new Persona();
        tipoPersona = new TipoPersona();
        tipoIdentificacion = new TipoIdentificacion();
        tipoContribuyente = new TipoContribuyente();
        proveedor = new Proveedor();
        usuario = new Usuario();
        registradoProvPor = new Usuario();
        usuarioProveedorIfip = new Usuario();
        documentosProveedor = new DocumentosProveedor();
        proveedorCuentaEntFin = new ProveedorCuentaEntFin();
        proveedorIfip = new ProveedorIfip();
        tipoComprobanteCompra = new TipoComprobanteCompra();
        tipoCuentaEntidadFinanciera = new TipoCuentaEntidadFinanciera();
        entidadFinanciera = new EntidadFinanciera();

        personaExistente = false;
        proveedorExistente = false;
        documentosProveedorExistente = false;
        cuentaEntfinProExistente = false;
        ifipProveeExistente = false;
        nombreCompleto = null;
        fechaIngreso = null;
        fechaActualizacion = null;
        setFechaIngresoPersona(new Date());

        setFechaActualizacionPersona(new Date());
        setFechaRegistroProAux(new Date());
        correoElectronico = null;
        criterioConsulta = null;
        serieDocumentosProv = null;
        numeroAutorizacionDocumento = null;
        numeroCuentaProCEF = null;
        nombreCuentaProCEF = null;
        observacionesProCEF = null;
        estadoProCEF = "N";
        digitosComproProv = 0;
        iniSerieDocumentosProv = 0L;
        finSerieDocumentosProv = 0L;
        codigoProveedor = ejbFacadeProveedor.getSecuenciaCodigoProveedor();
        // opcion ingresar es = 1
        estadoOpProveedor = 1;
        proveedor.setAgenteRetencion('N');
        proveedor.setEliminado('N');
        proveedorIfip.setEliminado('N');
        estadoIngresoPro = true;
        estadoIngresoProIfip = true;
        estadoProCEFEdicion = true;
        setBanderaCueEntidad(false);
        this.banderaCuenEntMov = false;
    }

    public void preparoDatosProveedorExistente(ActionEvent actionEvent) throws IOException {

        tipoCuentaEntidadFinanciera = null;
        entidadFinanciera = null;
        numeroCuentaProCEF = null;
        nombreCuentaProCEF = null;
        observacionesProCEF = null;
        this.setEntidadFinanciera(null);
        this.setTipoCuentaEntidadFinanciera(null);
        this.setNumeroCuentaProCEF(null);
        this.setNombreCuentaProCEF(null);
        this.setObservacionesProCEF(null);
        this.setEstadoProCEF(null);

        setPersona(proveedorSel.getPersona());
        identificacion = persona.getIdentificacion();
        setFechaIngresoPersona(persona.getFechaIngreso());
        setFechaActualizacionPersona(new Date());
        setProveedor(proveedorSel);
        setCodigoPersona(proveedorSel.getCodigoPersona());
        this.setTipoContribuyente(ejbFacadeTipoContribuyente.getItemsTipConEliByCodigo(proveedorSel.getCodigoTipoContribuyente()).get(0));
        setFechaRegistroProAux(proveedorSel.getFechaRegistro());
        this.setItemsDocumentosProveedor(ejbFacadeDocumentosProveedor.getItemsDocumentosProveedorfindByCodigoProveedor(proveedorSel.getCodigo()));
        setTipoPersona(persona.getCodigoTipoPersona());
        this.setUsuario(ejbFacadeUsuario.getUsuariobyCodigo(proveedorSel.getRegistradoPor()).get(0));
        this.setTipoIdentificacion(ejbFacadeTipoIdentificacion.getItemsTipoIdentificaciobyCodigo(persona.getCodigoTipoIdentificacion().getCodigo()).get(0));
        List<ProveedorCuentaEntFin> listaPcef = ejbFacadeProveedorCuentaEntFin.getItemsCuentaEntfinbyCodigoProveedor(proveedor.getCodigo());

        if (!listaPcef.isEmpty()) {
            if (listaPcef.size() == 1) {

                this.setProveedorCuentaEntFin(listaPcef.get(0));
                this.setEntidadFinanciera(ejbFacadeEntidadFinanciera.getItemsEntFin(proveedorCuentaEntFin.getCodigoEntidadFinanciera()).get(0));
                this.setTipoCuentaEntidadFinanciera(ejbFacadeTipCueEntFinanciera.getItemsTipCueEntFin(proveedorCuentaEntFin.getCodigoTipCue()).get(0));
                this.setNumeroCuentaProCEF(proveedorCuentaEntFin.getNumeroCuenta());
                this.setNombreCuentaProCEF(proveedorCuentaEntFin.getNombreCuenta());
                this.setObservacionesProCEF(proveedorCuentaEntFin.getObservaciones());
                this.setEstadoProCEF("N");
            } else {
                MuestraMensaje.addAdvertencia("Proveedor Tienen Mas de UNa cuenta Asignanda");
            }
        }

        this.setProveedorIfip(this.ejbFacadeProveedorIfip.find(new ProveedorIfipPK(this.proveedorSel.getCodigo(), ActivacionUsuario.getCodigoIfip())));
        if (this.proveedorIfip != null) {
            this.setUsuarioProveedorIfip(proveedorIfip.getUsuarioRegistradoPor());
            this.fechaRegistroProIfip = proveedorIfip.getFechaRegistro();
        }
        estadoIngresoPro = true;
        estadoProCEFEdicion = true;
        estadoIngresoProIfip = false;
        estadoOpProveedor = 2;
        setBanderaCueEntidad(false);
        this.banderaCuenEntMov = false;
    }

    public void obtieneProveedor() {
        if (this.getBuscarPor().equals("I")) {
            buscaProveedoresPorId();
        } else if (this.getBuscarPor().equals("N")) {
            buscaProveedoresPorNombre();

        } else {
            //msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeSeleccioneFecha");
            //MuestraMensaje.addAdvertencia(msg);
        }
    }

    public void cambioRespuestaRise() {
        if (String.valueOf(proveedor.getRetieneIva()).equals("S")) {
            proveedor.setTieneRise('N');
            this.desHabilitaRise = true;
        } else {
            this.desHabilitaRise = false;
        }

    }

    public void cambioCriterioCuentaEnt() {

        if (tipoCuentaEntidadFinanciera != null
                || entidadFinanciera != null
                || numeroCuentaProCEF.length() > 1
                || nombreCuentaProCEF.length() > 1
                || observacionesProCEF.length() > 1) {
            banderaCuenEntMov = true;

        } else if (tipoCuentaEntidadFinanciera == null
                || entidadFinanciera == null
                || numeroCuentaProCEF.length() == 0
                || nombreCuentaProCEF.length() == 0
                || observacionesProCEF.length() == 0) {

            banderaCuenEntMov = false;
            tipoCuentaEntidadFinanciera = new TipoCuentaEntidadFinanciera();
            entidadFinanciera = new EntidadFinanciera();
            numeroCuentaProCEF = "";
            nombreCuentaProCEF = "";
            observacionesProCEF = "";

        }
    }

    public void buscaProveedoresPorNombre() {
        if (getCriterioConsulta() == null) {
            setMsg("Persona no Existe");
            MuestraMensaje.addAdvertencia(getMsg());

        } else {
            this.setItemsProveedoresIfip(ejbFacadeProveedorIfip.getItemsNombresProveedorIfipEliminado(getCriterioConsulta().toUpperCase(), ActivacionUsuario.getCodigoIfip(), 'N'));
            if (!this.itemsProveedoresIfip.isEmpty()) {
                if (this.itemsProveedoresIfip.size() == 1) {
                    proveedorIfip = this.getItemsProveedoresIfip().get(0);
                    this.setItemsProveedor(ejbFacadeProveedor.getItemsProveedorByCodigo(proveedorIfip.getProveedor().getCodigo()));
                    this.setPersona(proveedorIfip.getProveedor().getPersona());
                    this.criterioConsulta = proveedorIfip.getProveedor().getPersona().getNombreCompleto();
                }
            } else {
                this.itemsProveedor = null;
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente"));
                setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
                MuestraMensaje.addAdvertencia(getMsg());
            }
        }
    }

    public void buscaProveedoresPorId() {
        if (getCriterioConsulta() == null) {
            setMsg("Persona no Existe");
            MuestraMensaje.addAdvertencia(getMsg());

        } else {
            this.setItemsProveedoresIfip(ejbFacadeProveedorIfip.getItemsIdProveedorIfipEliminado(getCriterioConsulta(), ActivacionUsuario.getCodigoIfip(), 'N'));
            if (!this.itemsProveedoresIfip.isEmpty()) {
                if (this.itemsProveedoresIfip.size() == 1) {
                    proveedorIfip = this.getItemsProveedoresIfip().get(0);
                    this.setItemsProveedor(ejbFacadeProveedor.getItemsProveedorByCodigo(proveedorIfip.getProveedor().getCodigo()));
                    this.setPersona(proveedorIfip.getProveedor().getPersona());
                    this.criterioConsulta = proveedorIfip.getProveedor().getPersona().getIdentificacion();                    
                }
            } else {
                ///MensajeProveedorInexistente
                this.itemsProveedor = null;
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorInexistente"));
                setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
                MuestraMensaje.addAdvertencia(getMsg());
            }
        }
    }
    /*
     Metodo que sirve para identificar en que estado estauna persona
     si estaado opcion =1 es porq la persona no esta ingresada aun en el sistema
     si estaado opcion =2 es cuando la persona consta en el sistema pero 
     no consta com proveeedor   
     si estaado opcion =3 es cuando consta como proveedor pero no tiene 
     documentos asignados
     si estaado opcion =4 es cuando el proveedor tiene doumentos pero no 
     esta asignado a una entidad
     si estaado opcion = 5es cuando el proveedor no esta en una ifip
     */

    public void buscaPersonaPorIdentificion() {
        if (getIdentificacion() == null) {
            setMsg("Persona no Existe");
            MuestraMensaje.addAdvertencia(getMsg());

            inicioCampos();
        } else {

            String rucAux = this.identificacion.substring(0, identificacion.length() - 3);
            String rucUltimoNumeroAux = this.identificacion.substring(identificacion.length() - 3, identificacion.length());

            /*if (getIdentificacion().length() == 10 && Validaciones.validaCedula(getIdentificacion())) {
             this.setItemsPersona(ejbFacadePersona.getItemsIdenPersona(getIdentificacion()));
             if (!this.itemsPersona.isEmpty()) {

             if (this.itemsPersona.size() == 1) {
             personaExistente = true;
             // OBETENER Y CARGAR LOS DATOS DE LAS PERSONAS
             this.setPersona(itemsPersona.get(0));
             // PARA OBTENER Y CARGAR LOS DATOS DEL TIPO DE PERSONA
             this.setTipoPersona(ejbFacadeTipoPersona.getItemsTipoPersonabyCodigo(persona.getCodigoTipoPersona().getCodigo()).get(0));
             // PARA OBTENER Y CARGAR LOS DATOS DEL TIPO DE IDENTIFICION
             //System.out.println("la pesona buscada es......" + tipoPersona.getNombre());
             this.setTipoIdentificacion(ejbFacadeTipoIdentificacion.getItemsTipoIdentificaciobyCodigo(persona.getCodigoTipoIdentificacion().getCodigo()).get(0));
             //System.out.println("la pesona buscada es......" + tipoIdentificacion.getNombre());

             ///VALIDASI ES PROVEEDOR
             this.setItemsProveedor(ejbFacadeProveedor.getItemsfindByProveedorCodPersona(persona.getCodigo()));

             //System.out.println("****************************LOS ITEMS DE LOS PROVEEDORES SON es......" + itemsProveedor.size());
             if (!this.itemsProveedor.isEmpty()) {

             proveedorExistente = true;

             //ingresar el en el objeto proveedor mediante la cedula de la persona
             this.setProveedor(itemsProveedor.get(0));

             //PARA OBTNER EL CODIGO DEL PROVEEDOR 
             codigoProveedor = proveedor.getCodigo();
             //PARA OBTENER EL USUARIO QUE INGRESO AL PROVEEDOR 
             this.setUsuario(ejbFacadeUsuario.getUsuariobyCodigo(proveedor.getRegistradoPor()).get(0));
             /// PAA OBTENER EL TIPO DE COBNTRIBUYENTE
             this.setTipoContribuyente(ejbFacadeTipoContribuyente.getItemsTipConEliByCodigo(proveedor.getCodigoTipoContribuyente()).get(0));
             ///VALIDA SI EL PROVEEDOR TIENE DOCUMENTOS ASIGNADOS
             this.setItemsDocumentosProveedor(ejbFacadeDocumentosProveedor.getItemsDocumentosProveedorfindByCodigoProveedor(itemsProveedor.get(0).getCodigo()));

             if (!this.itemsDocumentosProveedor.isEmpty()) {
             //System.out.println("el total de talonarios son " + itemsDocumentosProveedor.size());
             documentosProveedorExistente = true;
             this.setDocumentosProveedor(itemsDocumentosProveedor.get(0));
             this.setTipoComprobanteCompra(ejbFacadeTipoComprobanteCompra.getItemsTipoComprobantefindByCodigo(documentosProveedor.getTipoComprobanteCompra().getCodigo(), 'N').get(0));

             ///VALIDA SIE EL PROVEEDOR TIENE UNA CUENTA EN UNA ENTIDAD FINANCIERA
             this.setItemsProveedorCuentaEntFin(ejbFacadeProveedorCuentaEntFin.getItemsCuentaEntfinbyCodigoProveedor(itemsProveedor.get(0).getCodigo()));

             if (!this.itemsProveedorCuentaEntFin.isEmpty()) {

             cuentaEntfinProExistente = true;
             this.setProveedorCuentaEntFin(itemsProveedorCuentaEntFin.get(0));

             numeroCuentaProCEF = proveedorCuentaEntFin.getNumeroCuenta();
             nombreCuentaProCEF = proveedorCuentaEntFin.getNombreCuenta();
             observacionesProCEF = proveedorCuentaEntFin.getObservaciones();
             estadoProCEF = String.valueOf(proveedorCuentaEntFin.getEliminado());
             codigoEntidadFinanciera = proveedorCuentaEntFin.getCodigoEntidadFinanciera();
             codigoTipoCuentaEntidadFinanciera = proveedorCuentaEntFin.getCodigoTipCue();
             entidadFinanciera = ejbFacadeEntidadFinanciera.getItemsEntFin(codigoEntidadFinanciera).get(0);
             entidadFinanciera = ejbFacadeEntidadFinanciera.getItemsEntFin(codigoEntidadFinanciera).get(0);
             tipoCuentaEntidadFinanciera = ejbFacadeTipCueEntFinanciera.getItemsTipCueEntFin(codigoTipoCuentaEntidadFinanciera).get(0);
             // ent

             // VALIDA SI ELPROVEEDOR ESTA ASIGNADO A UNA IFIP
             this.setItemsProveedoresIfip(ejbFacadeProveedorIfip.getItemsIdProveedorIfipEliminado(getIdentificacion(), ActivacionUsuario.getCodigoIfip(), 'N'));

             if (!this.itemsProveedoresIfip.isEmpty()) {

             ifipProveeExistente = true;
             this.setProveedorIfip(itemsProveedoresIfip.get(0));
             this.setUsuarioProveedorIfip(ejbFacadeUsuario.getUsuariobyCodigo(proveedorIfip.getRegistradoPor()).get(0));
             setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajePersonaExistente"));
             setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
             MuestraMensaje.addAdvertencia(getMsg());

             } else {
             estadoProveedor = 5;
             ifipProveeExistente = false;
             }
             } else {
             estadoProveedor = 4;
             cuentaEntfinProExistente = false;
             }
             } else {
             estadoProveedor = 3;
             documentosProveedorExistente = false;

             }
             } else {
             ///MensajeProveedorInexistente
             estadoProveedor = 2;
             proveedorExistente = false;
             }
             } else {

             }
             } else {

             setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajePersonaInExistente"));
             MuestraMensaje.addAdvertencia(getMsg());
             estadoProveedor = 1;
             this.itemsPersona = null;
             personaExistente = false;
             setTipoIdentificacion(ejbFacadeTipoIdentificacion.getItemsTipoIdentificaciobyCodigo(1L).get(0));
             setTipoPersona(ejbFacadeTipoPersona.getItemsTipoPersonabyCodigo(1L).get(0));

             }
             } else */

            if ((getIdentificacion().length() == 13 && Validaciones.validaCedula(rucAux) && rucUltimoNumeroAux.equals("001")) || (getIdentificacion().length() == 13 && rucUltimoNumeroAux.equals("001"))||(getIdentificacion().length() == 10 && Validaciones.validaCedula(getIdentificacion()))) {
                this.setItemsPersona(ejbFacadePersona.getItemsIdenPersona(getIdentificacion()));
                if (!this.itemsPersona.isEmpty()) {

                    if (this.itemsPersona.size() == 1) {
                        personaExistente = true;
                        // OBETENER Y CARGAR LOS DATOS DE LAS PERSONAS
                        this.setPersona(itemsPersona.get(0));
                        // PARA OBTENER Y CARGAR LOS DATOS DEL TIPO DE PERSONA
                        this.setTipoPersona(ejbFacadeTipoPersona.getItemsTipoPersonabyCodigo(persona.getCodigoTipoPersona().getCodigo()).get(0));
                        // PARA OBTENER Y CARGAR LOS DATOS DEL TIPO DE IDENTIFICION
                        this.setTipoIdentificacion(ejbFacadeTipoIdentificacion.getItemsTipoIdentificaciobyCodigo(persona.getCodigoTipoIdentificacion().getCodigo()).get(0));

                        ///VALIDASI ES PROVEEDOR
                        this.setItemsProveedor(ejbFacadeProveedor.getItemsfindByProveedorCodPersona(persona.getCodigo()));

                        if (!this.itemsProveedor.isEmpty()) {

                            proveedorExistente = true;

                            //ingresar el en el objeto proveedor mediante la cedula de la persona
                            this.setProveedor(itemsProveedor.get(0));

                            //PARA OBTNER EL CODIGO DEL PROVEEDOR 
                            codigoProveedor = proveedor.getCodigo();
                            //PARA OBTENER EL USUARIO QUE INGRESO AL PROVEEDOR 
                            this.setUsuario(ejbFacadeUsuario.getUsuariobyCodigo(proveedor.getRegistradoPor()).get(0));
                            /// PAA OBTENER EL TIPO DE COBNTRIBUYENTE
                            this.setTipoContribuyente(ejbFacadeTipoContribuyente.getItemsTipConEliByCodigo(proveedor.getCodigoTipoContribuyente()).get(0));
                            ///VALIDA SI EL PROVEEDOR TIENE DOCUMENTOS ASIGNADOS
                            this.setItemsDocumentosProveedor(ejbFacadeDocumentosProveedor.getItemsDocumentosProveedorfindByCodigoProveedor(itemsProveedor.get(0).getCodigo()));

                            if (!this.itemsDocumentosProveedor.isEmpty()) {
                                documentosProveedorExistente = true;
                                this.setDocumentosProveedor(itemsDocumentosProveedor.get(0));
                                this.setTipoComprobanteCompra(ejbFacadeTipoComprobanteCompra.getItemsTipoComprobantefindByCodigo(documentosProveedor.getTipoComprobanteCompra().getCodigo(), 'N').get(0));

                                ///VALIDA SIE EL PROVEEDOR TIENE UNA CUENTA EN UNA ENTIDAD FINANCIERA
                                this.setItemsProveedorCuentaEntFin(ejbFacadeProveedorCuentaEntFin.getItemsCuentaEntfinbyCodigoProveedor(itemsProveedor.get(0).getCodigo()));

                                if (!this.itemsProveedorCuentaEntFin.isEmpty()) {

                                    cuentaEntfinProExistente = true;
                                    this.setProveedorCuentaEntFin(itemsProveedorCuentaEntFin.get(0));

                                    numeroCuentaProCEF = proveedorCuentaEntFin.getNumeroCuenta();
                                    nombreCuentaProCEF = proveedorCuentaEntFin.getNombreCuenta();
                                    observacionesProCEF = proveedorCuentaEntFin.getObservaciones();
                                    estadoProCEF = String.valueOf(proveedorCuentaEntFin.getEliminado());
                                    codigoEntidadFinanciera = proveedorCuentaEntFin.getCodigoEntidadFinanciera();
                                    codigoTipoCuentaEntidadFinanciera = proveedorCuentaEntFin.getCodigoTipCue();
                                    entidadFinanciera = ejbFacadeEntidadFinanciera.getItemsEntFin(codigoEntidadFinanciera).get(0);
                                    entidadFinanciera = ejbFacadeEntidadFinanciera.getItemsEntFin(codigoEntidadFinanciera).get(0);
                                    tipoCuentaEntidadFinanciera = ejbFacadeTipCueEntFinanciera.getItemsTipCueEntFin(codigoTipoCuentaEntidadFinanciera).get(0);
                                    // ent

                                    // VALIDA SI ELPROVEEDOR ESTA ASIGNADO A UNA IFIP
                                    this.setItemsProveedoresIfip(ejbFacadeProveedorIfip.getItemsIdProveedorIfipEliminado(getIdentificacion(), ActivacionUsuario.getCodigoIfip(), 'N'));

                                    if (!this.itemsProveedoresIfip.isEmpty()) {

                                        ifipProveeExistente = true;
                                        estadoIngresoProIfip = false;
                                        this.setProveedorIfip(itemsProveedoresIfip.get(0));
                                        this.setUsuarioProveedorIfip(ejbFacadeUsuario.getUsuariobyCodigo(proveedorIfip.getRegistradoPor()).get(0));
                                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajePersonaExistente"));
                                        setMsg(getMsg() + " EN LA IFIP " + ActivacionUsuario.getCodigoIfip());
                                        MuestraMensaje.addAdvertencia(getMsg());

                                    } else {
                                        estadoProveedor = 5;
                                        ifipProveeExistente = false;
                                    }
                                } else {
                                    estadoProveedor = 4;
                                    cuentaEntfinProExistente = false;
                                }
                            } else {
                                estadoProveedor = 3;
                                documentosProveedorExistente = false;

                            }
                        } else {
                            ///MensajeProveedorInexistente
                            estadoProveedor = 2;
                            proveedorExistente = false;
                        }
                    } else {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionAsignadoMasDeUnaPersona"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    }
                } else {

                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajePersonaInExistente"));
                    MuestraMensaje.addAdvertencia(getMsg());
                    estadoProveedor = 1;
                    this.itemsPersona = null;
                    personaExistente = false;
                    String identificacionAux=getIdentificacion();
                    inicioCampos();
                    setIdentificacion(identificacionAux);
                    if (getIdentificacion().length() == 13){
                        setTipoIdentificacion(ejbFacadeTipoIdentificacion.getItemsTipoIdentificaciobyCodigo(2L).get(0));
                        setTipoPersona(ejbFacadeTipoPersona.getItemsTipoPersonabyCodigo(2L).get(0));
                    }else if (getIdentificacion().length() == 10){
                        setTipoIdentificacion(ejbFacadeTipoIdentificacion.getItemsTipoIdentificaciobyCodigo(1L).get(0));
                        setTipoPersona(ejbFacadeTipoPersona.getItemsTipoPersonabyCodigo(1L).get(0));
                    }
                    

                }
            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeDocumentoInvalido"));
                MuestraMensaje.addAdvertencia(getMsg());
                inicioCampos();

                // ent
            }
        }
    }

    public void enlaceTipoIdPerson() {

    }

    /**
     * Metodo para dar segumiento al wizard del socio
     *
     * @param event
     * @return Evento
     */
    public String sigueProcesoProveedor(FlowEvent event) {
        try {
            if (isSaltar()) {
                setSaltar(false);
                return "confirmacionTab";
            } else {

                if (event.getOldStep().equals("datosBasicosTab") && event.getNewStep().equals("datosProveedorTab")) {

                    if (persona != null) {
                        if (Validaciones.validaCorreoElectronico("" + persona.getCorreoEletronico())) {
                            return "datosProveedorTab";
                        } else {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorCorreoInvalido"));
                        }

                    }
                } else if (event.getOldStep().equals("datosProveedorTab") && event.getNewStep().equals("documentosProveedorTab")) {

                    if (persona != null) {
                            return "documentosProveedorTab";
                        }                        
                } else if (event.getOldStep().equals("documentosProveedorTab") && event.getNewStep().equals("pruebaCuentaFinTab")) {

                    if (persona != null) {
                        return "pruebaCuentaFinTab";
                    }
                } else if (event.getOldStep().equals("pruebaCuentaFinTab") && event.getNewStep().equals("proveedorIfipTab")) {

                    if (persona != null) {
                        return "proveedorIfipTab";
                    }
                } else if (event.getOldStep().equals("proveedorIfipTab") && event.getNewStep().equals("confirmacionTab")) {

                    if (persona != null) {
                        return "confirmacionTab";
                    }
                        }                        

            }

            return event.getNewStep();

        } catch (Exception ex) {
            return "datosBasicosTab";
        }
    }

    public void agregaDatosPersona() {

        persona.setCodigoTipoIdentificacion(tipoIdentificacion);
        persona.setCodigoTipoPersona(tipoPersona);
        persona.setIdentificacion(identificacion);
        persona.setFechaIngreso(this.fechaIngresoPersona);
        persona.setFechaActualizacion(this.fechaActualizacionPersona);
    }

    public void agregaDatosProveedor() {
        this.setFechaRegistroProTs(new java.sql.Timestamp(fechaRegistroProAux.getTime()));
        //proveedor.setCodigo(codigoProveedor);
        proveedor.setCodigoPersona(ejbFacadePersona.getItemsIdenPersona(identificacion).get(0).getCodigo());
        proveedor.setTipoContribuyente(tipoContribuyente);
        proveedor.setCodigoTipoContribuyente(tipoContribuyente.getCodigo());
        proveedor.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
        proveedor.setFechaRegistro(fechaRegistroProTs);
    }

    public void agregaDocumento() {
      
        /// PARA VALIDAR  Q LOS CAMPOS NO ESTE VACIOS
        if (fechaIngresoDocumento.before(fechaCaducaDocumento)) {
            if (tipoComprobanteCompra != null && fechaEmisionDocumento != null
                    && fechaCaducaDocumento != null && validaNumero(numeroAutorizacionDocumento)
                    && new BigDecimal(digitosComproProv).compareTo(BigDecimal.ZERO) > 0
                    && fechaIngresoDocumento != null) {

                /// PARA VALIDAR LOS DETALLES SIN REVISAR ANTES SISTAN GUARDADOS EN LA BASES DE DATOS
                if (!validaDocProvCodProTipComFechEmi(codigoProveedor, tipoComprobanteCompra.getCodigo(), fechaEmisionDocumento,serieDocumentosProv)) {

                    // valida que esten en los dettalles un codigo de proveedor, tipo de comprobente y numero de autorizacion ya 
                    // ingresados en los detalles
                    if (!validaDocProvCodProTipComNumAuto(codigoProveedor, tipoComprobanteCompra.getCodigo(), numeroAutorizacionDocumento,serieDocumentosProv)) {

                        if (!validaDocProvCodProTipComSerieInicioSerie(codigoProveedor,
                                tipoComprobanteCompra.getCodigo(),
                                serieDocumentosProv, iniSerieDocumentosProv,
                                finSerieDocumentosProv)) {
                            ////VLIDA Q LOS VALORES DE INICIO Y FIN DE SERIE SEAN VAVIOS 
                            if (new BigDecimal(iniSerieDocumentosProv).compareTo(BigDecimal.ZERO) > 0
                                    && new BigDecimal(finSerieDocumentosProv).compareTo(BigDecimal.ZERO) > 0) {
                                
                                /// CONSULTA PARA SABER  SI EXISTE EN LA BASE DE DATOS REGISTRADO  EL CODIGO DEL PROVEEDOR
                                /// CON TIPO DE COMPROBANTE Y UN NUMERO DE AUTORIZACION
                                if (ejbFacadeDocumentosProveedor.getItemsDatosfindByCodigoProveedorTipoComprobanteAut(codigoProveedor,
                                        tipoComprobanteCompra.getCodigo(), numeroAutorizacionDocumento).isEmpty()) {

                                    ///VALIDA Q EL CODIGO DEL PROVEEDOR CON UN TIPO DE COMPRA Y LA FECHA DE EMISION NO ESTEN YA INGRESADAS 
                                    //EN LA BASE DE DATOS 
                                    if (ejbFacadeDocumentosProveedor.getItemsDatosfindByCodProvTipoComprFechaEmision(codigoProveedor,
                                            tipoComprobanteCompra.getCodigo(), fechaEmisionDocumento).isEmpty()) {

                                        /// VALIDA Q LA SERIE A SER INGRESADA CUMPLA CON UN PARAMETRO ESPECIFICADO
                                        if (validaNumeroSerie(serieDocumentosProv)) {

                                            // VALIDA QUE EL UN CODIGO DE UN PROVEEDOR CON UN TIPO DE COMPROBANTE , 
                                            /// UN NUMERO DE SERIE , INICIO SERE Y FIN SERIE NO SE REPITAN
                                            if ((ejbFacadeDocumentosProveedor.getItemsDatosProveedorComprobanteSerie(proveedor.getCodigo(),
                                                    tipoComprobanteCompra.getCodigo(), serieDocumentosProv,
                                                    iniSerieDocumentosProv, finSerieDocumentosProv)).isEmpty()) {

                                                documentosProveedor = new DocumentosProveedor();
                                                documentosProveedorPK = new DocumentosProveedorPK(codigoProveedor, tipoComprobanteCompra.getCodigo(), fechaEmisionDocumento,serieDocumentosProv);
                                                documentosProveedor.setDocumentosProveedorPK(documentosProveedorPK);
                                                documentosProveedor.setTipoComprobanteCompra(getTipoComprobanteCompra());
                                                documentosProveedor.setFechaCaduca(getFechaCaducaDocumento());
                                                documentosProveedor.setNumeroAutorizacion(getNumeroAutorizacionDocumento());
                                                documentosProveedor.setDigitosComprobante(getDigitosComproProv());
                                                documentosProveedor.setFechaIngreso(getFechaIngresoDocumento());
                                                documentosProveedor.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());
                                                documentosProveedor.setInicioSerie(getIniSerieDocumentosProv());
                                                documentosProveedor.setFinSerie(getFinSerieDocumentosProv());
                                                //documentosProveedor.setSerieDocumentos(getSerieDocumentosProv());

                                                try {

                                                    this.itemsDocumentosProveedor.add(documentosProveedor);
                                                    limpioCamposDocumentosPro();

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                            } else {
                                                //MensajeTalonarioSProveedor
                                                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorTipoComprSerie"));
                                                MuestraMensaje.addAdvertencia(getMsg());
                                            }

                                        } else {
                                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroSerie"));
                                            MuestraMensaje.addAdvertencia(getMsg());

                                        }

                                    } else {
                                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorTipoComprSerieD"));
                                        MuestraMensaje.addAdvertencia(getMsg());
                                    }

                                } else {
                                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorTipoComprobanteNumAuto"));
                                    MuestraMensaje.addAdvertencia(getMsg());
                                }

                            } else {
                                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCampoCantidadZero"));
                                MuestraMensaje.addAdvertencia(getMsg());

                            }
                        } else {
                            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorTipoComprobanteNumAutoD"));
                            MuestraMensaje.addAdvertencia(getMsg());
                        }

                    } else {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorTipoComprobanteNumAutoD"));
                        MuestraMensaje.addAdvertencia(getMsg());
                    }

                } else {
                    setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorTipoComprFechaEmiD"));
                    MuestraMensaje.addAdvertencia(getMsg());
                }

            } else {
                setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaCamposVaciosDetCab"));
                MuestraMensaje.addAdvertencia(getMsg());
            }
        } else {
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedoreFechasIncorrectas"));
            MuestraMensaje.addAdvertencia(getMsg());
        }
    }

    public void eliminaDetalleDocsProveedor() {

        if (this.itemsDocumentosProveedor.size() > 0) {
            if (codigoProveedor > 0L) {

                if (ejbFacadeDocumentosProveedor.getItemsDatosfindByCodigoProveedorTipoComprobanteAut(codigoProveedor,
                        documentosProveedorSel.getTipoComprobanteCompra().getCodigo(), documentosProveedorSel.getNumeroAutorizacion()).isEmpty()) {

                    ///VALIDA Q EL CODIGO DEL PROVEEDOR CON UN TIPO DE COMPRA Y LA FECHA DE EMISION NO ESTEN YA INGRESADAS 
                    //EN LA BASE DE DATOS 
                    if (ejbFacadeDocumentosProveedor.getItemsDatosfindByCodProvTipoComprFechaEmision(codigoProveedor,
                            documentosProveedorSel.getTipoComprobanteCompra().getCodigo(), documentosProveedorSel.getFechaIngreso()).isEmpty()) {

                        /// VALIDA Q LA SERIE A SER INGRESADA CUMPLA CON UN PARAMETRO ESPECIFICADO
                        if (validaNumeroSerie(documentosProveedorSel.getDocumentosProveedorPK().getSerieDocumentos())) {

                            // VALIDA QUE EL UN CODIGO DE UN PROVEEDOR CON UN TIPO DE COMPROBANTE , 
                            /// UN NUMERO DE SERIE , INICIO SERE Y FIN SERIE NO SE REPITAN
                            if ((ejbFacadeDocumentosProveedor.getItemsDatosProveedorComprobanteSerie(codigoProveedor,
                                    documentosProveedorSel.getTipoComprobanteCompra().getCodigo(), documentosProveedorSel.getDocumentosProveedorPK().getSerieDocumentos(),
                                    documentosProveedorSel.getInicioSerie(), documentosProveedorSel.getFinSerie())).isEmpty()) {
                                this.itemsDocumentosProveedor.remove(this.documentosProveedorSel);
                            }

                        }

                    }

                }

            }
        }
    }

    public void limpioCamposDocumentosPro() {
        documentosProveedor = new DocumentosProveedor();
    }

    //CODIGO_PROVEEDOR, CODIGO_TIPO_COMPROBANTE, FECHA_EMISION, SERIE
    public boolean validaDocProvCodProTipComFechEmi(Long pro, Long tipoCom, Date fechEmision,String serie) {
        boolean resultado = false;
        for (int i = 0; i < itemsDocumentosProveedor.size(); i++) {
            DocumentosProveedor docPro = itemsDocumentosProveedor.get(i);
            if ((docPro.getDocumentosProveedorPK().getFechaEmision().compareTo(fechEmision) == 0)
                    && (docPro.getTipoComprobanteCompra().getCodigo() == tipoCom)
                    && (docPro.getDocumentosProveedorPK().getCodigoProveedor() == pro)
                    && (docPro.getDocumentosProveedorPK().getSerieDocumentos().equals(serie))) {
                resultado = true;
            }
        }
        return resultado;
    }

    //CODIGO_PROVEEDOR, CODIGO_TIPO_COMPROBANTE, NUMERO_AUTORIZACION
    public boolean validaDocProvCodProTipComNumAuto(Long pro, Long tipoCom, String numeroAuto,String serie) {
        boolean resultado = false;
        for (int i = 0; i < itemsDocumentosProveedor.size(); i++) {
            DocumentosProveedor docPro = itemsDocumentosProveedor.get(i);
            if ((docPro.getNumeroAutorizacion().equals(numeroAuto))
                    && (docPro.getTipoComprobanteCompra().getCodigo() == tipoCom)
                    && (docPro.getDocumentosProveedorPK().getCodigoProveedor() == pro)
                    && (docPro.getDocumentosProveedorPK().getSerieDocumentos().equals(serie))) {
                resultado = true;
            }
        }
        return resultado;
    }

    //CODIGO_PROVEEDOR, CODIGO_TIPO_COMPROBANTE, SERIE, INICIO_SERIE
    public boolean validaDocProvCodProTipComSerieInicioSerie(Long pro, Long tipoCom, String serie, Long inicioSerie, Long finSerie) {
        boolean resultado = false;
        for (int i = 0; i < itemsDocumentosProveedor.size(); i++) {
            DocumentosProveedor docPro = itemsDocumentosProveedor.get(i);
            if ((docPro.getDocumentosProveedorPK().getSerieDocumentos().equals(serie))
                    && (docPro.getInicioSerie() == inicioSerie)
                    && (docPro.getFinSerie() == finSerie)
                    && (docPro.getTipoComprobanteCompra().getCodigo() == tipoCom)
                    && (docPro.getDocumentosProveedorPK().getCodigoProveedor() == pro)) {

                resultado = true;
            }
        }
        return resultado;
    }

    public boolean agregaCuentaEntidadFina() {
        banderaCueEntidad = false;
        if (tipoCuentaEntidadFinanciera != null
                && entidadFinanciera != null
                && numeroCuentaProCEF != null
                && nombreCuentaProCEF != null
                && tipoCuentaEntidadFinanciera.getCodigo() > 0L
                && entidadFinanciera.getCodigo() > 0L) {

            if (proveedorCuentaEntFin == null) {
                proveedorCuentaEntFin = new ProveedorCuentaEntFin();
            }

            proveedorCuentaEntFin.setCodigoProveedor(proveedor);
            proveedorCuentaEntFin.setCodigoTipCue(tipoCuentaEntidadFinanciera.getCodigo());
            proveedorCuentaEntFin.setCodigoEntidadFinanciera(entidadFinanciera.getCodigo());
            proveedorCuentaEntFin.setNumeroCuenta(numeroCuentaProCEF);
            proveedorCuentaEntFin.setNombreCuenta(nombreCuentaProCEF);
            proveedorCuentaEntFin.setObservaciones(observacionesProCEF);
            proveedorCuentaEntFin.setEliminado('N');

            banderaCueEntidad = true;
        }
        return banderaCueEntidad;
    }

    public void agregaProveedorIfip() {

        proveedorIfip.setProveedorIfipPK(new ProveedorIfipPK(proveedor.getCodigo(), ActivacionUsuario.getCodigoIfip()));
        proveedorIfip.setFechaRegistro(fechaRegistroProIfip);
        proveedorIfip.setRegistradoPor(ActivacionUsuario.getCodigoUsuario());

    }

    public boolean validaNumero(String cadena) {
        Pattern pat = Pattern.compile("0+[1-9][0-9]*|[1-9]|^[1-9]([0-9]+$)");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validaNumeroSerie(String cadena) {
        if (cadena.length()!=7){
            
            return false;
        }
        Pattern pat = Pattern.compile("((0{0,2}[1-9]|0{0,1}[1-9][0-9]|[1-9][0-9][0-9]))(\\-)((0{0,2}[1-9]|0{0,1}[1-9][0-9]|[1-9][0-9][0-9]))");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validaNumeroSerieAuto() {
        if (this.serieDocumentosProv.length()!=7){
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroSerie7Digitos"));
            MuestraMensaje.addAdvertencia(getMsg());

            return false;
        }
        Pattern pat = Pattern.compile("((0{0,2}[1-9]|0{0,1}[1-9][0-9]|[1-9][0-9][0-9]))(\\-)((0{0,2}[1-9]|0{0,1}[1-9][0-9]|[1-9][0-9][0-9]))");
        Matcher mat = pat.matcher(this.serieDocumentosProv);
        if (mat.matches()) {
            return true;
        } else {

            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeNumeroSerie"));
            MuestraMensaje.addAdvertencia(getMsg());

            return false;
        }
    }

    public void guardaProveedor(ActionEvent actionEvent) {       
        boolean correcto = false;
        double codigoProveedor;
        try {
            // Cargando la conexion de BD
            llamaSP.cargaConexion();
            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false); 
            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_adquisiciones.pkm_proveedor.p_guarda_proveedor");
            llamaSP.setNumeroParametros(24);            
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", ActivacionUsuario.getCodigoIfip()});
            if( persona.getCodigo() != null )
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", persona.getCodigo() });
            else
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_persona", -1 });
            if( persona.getCodigo() != null )
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_ide", persona.getCodigoTipoIdentificacion().getCodigo() });
            else
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_ide", getTipoIdentificacion().getCodigo() });
            if( persona.getCodigo() != null )
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_per", persona.getCodigoTipoPersona().getCodigo() });
            else
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_per", getTipoPersona().getCodigo() });
            if( persona.getCodigo() != null )
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificacion", persona.getIdentificacion()});
            else
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_identificacion", getIdentificacion()});
            if( persona.getCodigo() != null )
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_nombre_completo", persona.getNombreCompleto()});
            else
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_nombre_completo", persona.getNombreCompleto()});
            if( persona.getCodigo() != null )
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_correo_electronico", persona.getCorreoEletronico()});
            else
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_correo_electronico", getCorreoElectronico()});
            if( persona.getCodigo()!= null )
                if ( persona.getFechaCaducidadIdentificacion() != null )
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_caducidad_ide", new java.sql.Timestamp(persona.getFechaCaducidadIdentificacion().getTime())});
                else
                    llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_caducidad_ide", new java.sql.Timestamp(new Date().getTime())});
            else
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_caducidad_ide", new java.sql.Timestamp(new Date().getTime())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_proveedor", proveedor.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_contribuyente", getTipoContribuyente().getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_retiene_iva", String.valueOf(proveedor.getRetieneIva())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_tiene_rise", String.valueOf(proveedor.getTieneRise())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_eliminado", "N"});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_lleva_contabilidad", String.valueOf(proveedor.getLlevaContabilidad())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_agente_retencion", String.valueOf(proveedor.getAgenteRetencion())});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_direccion", proveedor.getDireccion()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_telefono", proveedor.getTelefono()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_registrado_por", ActivacionUsuario.getCodigoUsuario()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tip_cue", entidadFinanciera != null ? tipoCuentaEntidadFinanciera.getCodigo() : 0 });
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_entidad_financiera",entidadFinanciera != null ? entidadFinanciera.getCodigo() : -1 });
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_cuenta", entidadFinanciera != null ? getNumeroCuentaProCEF() : "0" });
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_nombre_cuenta", entidadFinanciera != null ? getNombreCuentaProCEF() : "0" });
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_observaciones", entidadFinanciera != null ? getObservacionesProCEF() : "0" });
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pt_codigo", Types.NUMERIC});
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                if( llamaSP.getError() == null ){
                    codigoProveedor = Double.valueOf(llamaSP.getListResultado().get(0).toString());
                    correcto = true;
                }else{
                    MuestraMensaje.addErrorCapaControl();
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error al guardar el proveedor."+llamaSP.getError(),
                    new Object[]{"ProveedorBean", "guardaProveedor", llamaSP.getErroSql()});
                    llamaSP.rollback();
                    return;
                }
            }else{
                MuestraMensaje.addErrorCapaControl();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error al guardar el proveedor."+llamaSP.getError(),
                new Object[]{"ProveedorBean", "guardaProveedor", llamaSP.getErroSql()});
                llamaSP.rollback();
                return;
            }
            // Guardar los documentos del proveedor
            for (DocumentosProveedor dc : itemsDocumentosProveedor) {
                //Elimina los documentos
                llamaSP.setNombreSP("mks_adquisiciones.pkg_documento_proveedor.p_elimina");
                llamaSP.setNumeroParametros(11);            
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_proveedor", codigoProveedor});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_comprobante", dc.getTipoComprobanteCompra().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_emision", new java.sql.Timestamp(dc.getDocumentosProveedorPK().getFechaEmision().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_caduca", new java.sql.Timestamp(dc.getFechaCaduca().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_autorizacion", dc.getNumeroAutorizacion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_digitos_comprobante", dc.getDigitosComprobante()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_ingreso", new java.sql.Timestamp(dc.getFechaIngreso().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_registrado_por", dc.getRegistradoPor()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_inicio_serie", dc.getInicioSerie()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fin_serie", dc.getFinSerie()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_serie", dc.getDocumentosProveedorPK().getSerieDocumentos()});
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    if( llamaSP.getError() == null ){
                        correcto = true;
                    }else{
                        MuestraMensaje.addErrorCapaControl();
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error al eliminar los documentos del proveedor."+llamaSP.getError(),
                        new Object[]{"ProveedorBean", "guardaProveedor", llamaSP.getErroSql()});
                        llamaSP.rollback();
                        correcto = false;
                        return;
                    }
                }else{
                    MuestraMensaje.addErrorCapaControl();
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error al guardar los documentos del proveedor."+llamaSP.getError(),
                    new Object[]{"ProveedorBean", "guardaProveedor", llamaSP.getErroSql()});
                    llamaSP.rollback();
                    correcto = false;
                    return;
                }
                //Inserta los documentos
                llamaSP.setNombreSP("mks_adquisiciones.pkg_documento_proveedor.p_inserta");
                llamaSP.setNumeroParametros(11);            
                llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_proveedor", codigoProveedor});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_tipo_comprobante", dc.getTipoComprobanteCompra().getCodigo()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_emision", new java.sql.Timestamp(dc.getDocumentosProveedorPK().getFechaEmision().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_caduca", new java.sql.Timestamp(dc.getFechaCaduca().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_numero_autorizacion", dc.getNumeroAutorizacion()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_digitos_comprobante", dc.getDigitosComprobante()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fecha_ingreso", new java.sql.Timestamp(dc.getFechaIngreso().getTime())});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_registrado_por", ActivacionUsuario.getCodigoUsuario()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_inicio_serie", dc.getInicioSerie()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_fin_serie", dc.getFinSerie()});
                llamaSP.getListParametrosEntrada().add(new Object[]{"pt_serie", dc.getDocumentosProveedorPK().getSerieDocumentos()});
                llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
                llamaSP.invocaSP();
                if (llamaSP.isEjecucionCorrecta()) {
                    if( llamaSP.getError() == null ){
                        correcto = true;
                    }else{
                        MuestraMensaje.addErrorCapaControl();
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error al insertar los documentos del proveedor."+llamaSP.getError(),
                        new Object[]{"ProveedorBean", "guardaProveedor", llamaSP.getErroSql()});
                        llamaSP.rollback();
                        correcto = false;
                        return;
                    }
                }else{
                    MuestraMensaje.addErrorCapaControl();
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error al insertar los documentos del proveedor."+llamaSP.getError(),
                    new Object[]{"ProveedorBean", "guardaProveedor", llamaSP.getErroSql()});
                    llamaSP.rollback();
                    correcto = false;
                    return;
                }
            }
            if(correcto){
                llamaSP.commit();
                MuestraMensaje.addSatisfactorio("Proveedor Guardado Correctamente.");
            }else{
                llamaSP.rollback();
            }
            
        }catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            llamaSP.rollback();
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error general. "+llamaSP.getError(),
                    new Object[]{"ProveedorBean", "guardaProveedor", llamaSP.getErroSql()});
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ProveedorBean", "guardaProveedor", CapturaError.getErrorException(ex)});
        }
        finally{
             if( llamaSP.getConexionBD()!= null ) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
             }
        }
    }

    /**
     * Guarda al Proveedor.
     */
    /*
    public void guardoDatosProveedorIngreso() {
        if (ejbFacadePersona.getItemsIdenPersona(getIdentificacion()).isEmpty()) {
            agregaDatosPersona();
            ejbFacadePersona.create(persona);
            //System.out.println("INICIO PARA GUARDAR  DATOS DE PERSONA");
        } else if (ejbFacadePersona.getItemsIdenPersona(getIdentificacion()).size() == 1) {
            agregaDatosPersona();
            ejbFacadePersona.edit(persona);
            //System.out.println("INICIO PARA EDITAR  DATOS DE PERSONA");
        }

        if (ejbFacadeProveedor.getItemsProveedorByIdentificacion(getIdentificacion()).isEmpty()) {
            agregaDatosProveedor();
            ejbFacadeProveedor.create(proveedor);
            //System.out.println("INICIO PARA CREAR DATOS DEL PROVEEDOR");
        } else if (ejbFacadeProveedor.getItemsProveedorByIdentificacion(getIdentificacion()).size() == 1) {
            agregaDatosProveedor();
            ejbFacadeProveedor.edit(proveedor);
            //System.out.println("INICIO PARA EDITAR DATOS DEL PROVEEDOR");
        }

        this.setItemsPersona(ejbFacadePersona.getItemsIdenPersona(getIdentificacion()));
        Proveedor pro = ejbFacadeProveedor.getItemsfindByProveedorCodPersona(ejbFacadePersona.getItemsIdenPersona(getIdentificacion()).get(0).getCodigo()).get(0);

        for (DocumentosProveedor dc : itemsDocumentosProveedor) {
            dc.setProveedor(proveedor);
            ejbFacadeDocumentosProveedor.edit(dc);
        }

        if (proveedorCuentaEntFin.getCodigo() == null) {
            //System.out.println("Crea Cuenta Etnidad");
            ejbFacadeProveedorCuentaEntFin.create(proveedorCuentaEntFin);
        } else {
            //System.out.println("Edita Cuenta Etnidad");
            ejbFacadeProveedorCuentaEntFin.edit(proveedorCuentaEntFin);
        }

        ejbFacadeProveedorIfip.edit(proveedorIfip);
    }

    public void editaDatosProveedorIngreso() {

        if (ejbFacadePersona.getItemsByCodigo(codigoPersona).size() == 1) {
            agregaDatosPersona();
            ejbFacadePersona.edit(persona);
            //System.out.println("EDITO PERSONA");
        }

        if (ejbFacadeProveedor.getItemsProveedorByIdentificacion(getIdentificacion()).size() == 1) {
            agregaDatosProveedor();
            ejbFacadeProveedor.edit(proveedor);
            //System.out.println("EDITO PROVEEDOR");
        }

        Proveedor pro = ejbFacadeProveedor.getItemsProveedorByCodigo(codigoProveedor).get(0);

        for (int i = 0; i < itemsDocumentosProveedor.size(); i++) {
            DocumentosProveedor docProv = itemsDocumentosProveedor.get(i);
            docProv.setProveedor(pro);
            itemsDocumentosProveedor.set(i, docProv);
        }

        for (DocumentosProveedor dc : itemsDocumentosProveedor) {
            dc.setProveedor(proveedor);
            ejbFacadeDocumentosProveedor.edit(dc);
        }

        agregaCuentaEntidadFina();

        if (proveedorCuentaEntFin.getCodigo() == null) {
            //System.out.println("Crea Cuenta Etnidad");
            ejbFacadeProveedorCuentaEntFin.create(proveedorCuentaEntFin);
        } else {
            //System.out.println("Edita Cuenta Etnidad");
            ejbFacadeProveedorCuentaEntFin.edit(proveedorCuentaEntFin);
        }

        ejbFacadeProveedorIfip.edit(proveedorIfip);

        /*for (int i = 0; i < itemsDocumentosProveedor.size(); i++) {

         if ((ejbFacadeDocumentosProveedor.getItemsDatosProveedorComprobanteSerie(pro.getCodigo(),
         itemsDocumentosProveedor.get(i).getTipoComprobanteCompra().getCodigo(),
         itemsDocumentosProveedor.get(i).getSerieDocumentos(),
         itemsDocumentosProveedor.get(i).getInicioSerie(),
         itemsDocumentosProveedor.get(i).getFinSerie())).isEmpty()) {
         // //System.out.println("CODIGOS DEL PROVEEDO  " + itemsDocumentosProveedor.get(i).getProveedor().getCodigo());
         ////System.out.println("CODIGOS DEL PROVEEDO  " + itemsDocumentosProveedor.get(i).getDocumentosProveedorPK().getCodigoProveedor());

         ejbFacadeDocumentosProveedor.create(itemsDocumentosProveedor.get(i));
         //System.out.println("INICIO PARA CREAR DOCUMENTOS DEL PROVEEDOR");
         } else if ((ejbFacadeDocumentosProveedor.getItemsDatosProveedorComprobanteSerie(pro.getCodigo(),
         itemsDocumentosProveedor.get(i).getTipoComprobanteCompra().getCodigo(),
         itemsDocumentosProveedor.get(i).getSerieDocumentos(),
         itemsDocumentosProveedor.get(i).getInicioSerie(),
         itemsDocumentosProveedor.get(i).getFinSerie())).size() == 1) {

         ejbFacadeDocumentosProveedor.edit(itemsDocumentosProveedor.get(i));
         //System.out.println("INICIO PARA EDITAR DOCUMENTOS DEL PROVEEDOR");
         }
         }

         if (ejbFacadeProveedorCuentaEntFin.getItemsCuentaEntfinbyCodigoProveedor(pro.getCodigo()).isEmpty()) {

         if (agregaCuentaEntidadFina()) {
         //System.out.println("creo CUENTA ENTIDAD FINACIERA");
         ejbFacadeProveedorCuentaEntFin.create(proveedorCuentaEntFin);

         }
         } else {

         if (agregaCuentaEntidadFina()) {
         //System.out.println("EDITO CUENTA ENTIDAD FINACIERA");
         ejbFacadeProveedorCuentaEntFin.edit(proveedorCuentaEntFin);

         }

         }*/

        /*if (ejbFacadeProveedorIfip.getItemsProveedorIfip(pro.getCodigo()).size() == 1) {

         agregaProveedorIfip();

         if (proveedorIfip.getEliminado() == 'S') {

         ejbFacadeProveedorIfip.remove(proveedorIfip);
         //System.out.println("EDITO EL PROVEEDOR EN LA IFP");
         setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedorEliminadoIfip"));
         MuestraMensaje.addInformacion(getMsg());

         setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedoreEditado"));
         MuestraMensaje.addInformacion(getMsg());
         this.proveedorSel = null;
         this.itemsProveedor = null;

         } else if (proveedorIfip.getEliminado() == 'N') {
         ejbFacadeProveedorIfip.edit(proveedorIfip);
         //System.out.println("EDITO EL PROVEEDOR EN LA IFP");
         setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeProveedoreEditado"));
         MuestraMensaje.addInformacion(getMsg());
         this.proveedorSel = null;
         this.itemsProveedor = null;
         }

         }
    }*/

    /**
     * @return the personaP
     */
    public Persona getPersonaP() {
        return personaP;
    }

    /**
     * @param personaP the personaP to set
     */
    public void setPersonaP(Persona personaP) {
        this.personaP = personaP;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * @return the proveedorSel
     */
    public Proveedor getProveedorSel() {
        return proveedorSel;
    }

    /**
     * @param proveedorSel the proveedorSel to set
     */
    public void setProveedorSel(Proveedor proveedorSel) {
        this.proveedorSel = proveedorSel;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the personaSel
     */
    public Persona getPersonaSel() {
        return personaSel;
    }

    /**
     * @param personaSel the personaSel to set
     */
    public void setPersonaSel(Persona personaSel) {
        this.personaSel = personaSel;
    }

    /**
     * @return the proveedorCuentaEntFin
     */
    public ProveedorCuentaEntFin getProveedorCuentaEntFin() {
        return proveedorCuentaEntFin;
    }

    /**
     * @param proveedorCuentaEntFin the proveedorCuentaEntFin to set
     */
    public void setProveedorCuentaEntFin(ProveedorCuentaEntFin proveedorCuentaEntFin) {
        this.proveedorCuentaEntFin = proveedorCuentaEntFin;
    }

    /**
     * @return the itemsProveedor
     */
    public List<Proveedor> getItemsProveedor() {
        return itemsProveedor;
    }

    /**
     * @param itemsProveedor the itemsProveedor to set
     */
    public void setItemsProveedor(List<Proveedor> itemsProveedor) {
        this.itemsProveedor = itemsProveedor;
    }

    /**
     * @return the itemsTipoContribuyente
     */
    public List<TipoContribuyente> getItemsTipoContribuyente() {
        return itemsTipoContribuyente;
    }

    /**
     * @param itemsTipoContribuyente the itemsTipoContribuyente to set
     */
    public void setItemsTipoContribuyente(List<TipoContribuyente> itemsTipoContribuyente) {
        this.itemsTipoContribuyente = itemsTipoContribuyente;
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
     * @return the itemsTipoIdentificacion
     */
    public List<TipoIdentificacion> getItemsTipoIdentificacion() {
        return itemsTipoIdentificacion;
    }

    /**
     * @param itemsTipoIdentificacion the itemsTipoIdentificacion to set
     */
    public void setItemsTipoIdentificacion(List<TipoIdentificacion> itemsTipoIdentificacion) {
        this.itemsTipoIdentificacion = itemsTipoIdentificacion;
    }

    /**
     * @return the existePersona
     */
    public boolean isExistePersona() {
        return existePersona;
    }

    /**
     * @param existePersona the existePersona to set
     */
    public void setExistePersona(boolean existePersona) {
        this.existePersona = existePersona;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the itemsUsuario
     */
    public List<Usuario> getItemsUsuario() {
        return itemsUsuario;
    }

    /**
     * @param itemsUsuario the itemsUsuario to set
     */
    public void setItemsUsuario(List<Usuario> itemsUsuario) {
        this.itemsUsuario = itemsUsuario;
    }

    /**
     * @return the tipoIdentificacion
     */
    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * @param tipoIdentificacion the tipoIdentificacion to set
     */
    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    /**
     * @return the tipoPersona
     */
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * @return the itemsTipoPersona
     */
    public List<TipoPersona> getItemsTipoPersona() {
        return itemsTipoPersona;
    }

    /**
     * @param itemsTipoPersona the itemsTipoPersona to set
     */
    public void setItemsTipoPersona(List<TipoPersona> itemsTipoPersona) {
        this.itemsTipoPersona = itemsTipoPersona;
    }

    /**
     * @return the personaNueva
     */
    public boolean isPersonaNueva() {
        return personaNueva;
    }

    /**
     * @param personaNueva the personaNueva to set
     */
    public void setPersonaNueva(boolean personaNueva) {
        this.personaNueva = personaNueva;
    }

    /**
     * @return the existe
     */
    public boolean isExiste() {
        return existe;
    }

    /**
     * @param existe the existe to set
     */
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    /**
     * @return the tipoContribuyente
     */
    public TipoContribuyente getTipoContribuyente() {
        return tipoContribuyente;
    }

    /**
     * @param tipoContribuyente the tipoContribuyente to set
     */
    public void setTipoContribuyente(TipoContribuyente tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    /**
     * @return the proveedorIfip
     */
    public ProveedorIfip getProveedorIfip() {
        return proveedorIfip;
    }

    /**
     * @param proveedorIfip the proveedorIfip to set
     */
    public void setProveedorIfip(ProveedorIfip proveedorIfip) {
        this.proveedorIfip = proveedorIfip;
    }

    /**
     * @return the documentosProveedor
     */
    public DocumentosProveedor getDocumentosProveedor() {
        return documentosProveedor;
    }

    /**
     * @param documentosProveedor the documentosProveedor to set
     */
    public void setDocumentosProveedor(DocumentosProveedor documentosProveedor) {
        this.documentosProveedor = documentosProveedor;
    }

    /**
     * @return the itemsDocumentosProveedor
     */
    public List<DocumentosProveedor> getItemsDocumentosProveedor() {
        return itemsDocumentosProveedor;
    }

    /**
     * @param itemsDocumentosProveedor the itemsDocumentosProveedor to set
     */
    public void setItemsDocumentosProveedor(List<DocumentosProveedor> itemsDocumentosProveedor) {
        this.itemsDocumentosProveedor = itemsDocumentosProveedor;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the fechaIngreso
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaActualizacion
     */
    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the personaTelefono
     */
    public PersonaTelefono getPersonaTelefono() {
        return personaTelefono;
    }

    /**
     * @param personaTelefono the personaTelefono to set
     */
    public void setPersonaTelefono(PersonaTelefono personaTelefono) {
        this.personaTelefono = personaTelefono;
    }

    public List<OperadoraTelefono> getItemsOperadoraTelefono() {
        return this.ejbFacadeOperadoraTelefono.getItemsOperadoraTelefonoEliminado('N');
    }

    public List<TipoTelefono> getItemsTipoTelefono() {
        return this.ejbFacadeTipoTelefono.getItemsTipoTelefonoEliminado('N');
    }

    /**
     * @return the numeroTelefono
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * @param numeroTelefono the numeroTelefono to set
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

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
     * @return the criterioConsulta
     */
    public String getCriterioConsulta() {
        return criterioConsulta;
    }

    /**
     * @param criterioConsulta the criterioConsulta to set
     */
    public void setCriterioConsulta(String criterioConsulta) {
        this.criterioConsulta = criterioConsulta;
    }

    /**
     * @return the itemsProveedoresIfip
     */
    public List<ProveedorIfip> getItemsProveedoresIfip() {
        return itemsProveedoresIfip;
    }

    /**
     * @param itemsProveedoresIfip the itemsProveedoresIfip to set
     */
    public void setItemsProveedoresIfip(List<ProveedorIfip> itemsProveedoresIfip) {
        this.itemsProveedoresIfip = itemsProveedoresIfip;
    }

    /**
     * @return the saltar
     */
    public boolean isSaltar() {
        return saltar;
    }

    /**
     * @param saltar the saltar to set
     */
    public void setSaltar(boolean saltar) {
        this.saltar = saltar;
    }

    /**
     * @return the estadoProveedor
     */
    public int getEstadoProveedor() {
        return estadoProveedor;
    }

    /**
     * @param estadoProveedor the estadoProveedor to set
     */
    public void setEstadoProveedor(int estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    /**
     * @return the itemsProveedorCuentaEntFin
     */
    public List<ProveedorCuentaEntFin> getItemsProveedorCuentaEntFin() {
        return itemsProveedorCuentaEntFin;
    }

    /**
     * @param itemsProveedorCuentaEntFin the itemsProveedorCuentaEntFin to set
     */
    public void setItemsProveedorCuentaEntFin(List<ProveedorCuentaEntFin> itemsProveedorCuentaEntFin) {
        this.itemsProveedorCuentaEntFin = itemsProveedorCuentaEntFin;
    }

    /**
     * @return the usuarioProveedorIfip
     */
    public Usuario getUsuarioProveedorIfip() {
        return usuarioProveedorIfip;
    }

    /**
     * @param usuarioProveedorIfip the usuarioProveedorIfip to set
     */
    public void setUsuarioProveedorIfip(Usuario usuarioProveedorIfip) {
        this.usuarioProveedorIfip = usuarioProveedorIfip;
    }

    /**
     * @return the itemsUsuarioProveedorIfip
     */
    public List<Usuario> getItemsUsuarioProveedorIfip() {
        return itemsUsuarioProveedorIfip;
    }

    /**
     * @param itemsUsuarioProveedorIfip the itemsUsuarioProveedorIfip to set
     */
    public void setItemsUsuarioProveedorIfip(List<Usuario> itemsUsuarioProveedorIfip) {
        this.itemsUsuarioProveedorIfip = itemsUsuarioProveedorIfip;
    }

    /**
     * @return the personaExistente
     */
    public boolean isPersonaExistente() {
        return personaExistente;
    }

    /**
     * @param personaExistente the personaExistente to set
     */
    public void setPersonaExistente(boolean personaExistente) {
        this.personaExistente = personaExistente;
    }

    /**
     * @return the fechaIngresoProveedorTs
     */
    public Timestamp getFechaIngresoProveedorTs() {
        return fechaIngresoProveedorTs;
    }

    /**
     * @param fechaIngresoProveedorTs the fechaIngresoProveedorTs to set
     */
    public void setFechaIngresoProveedorTs(Timestamp fechaIngresoProveedorTs) {
        this.fechaIngresoProveedorTs = fechaIngresoProveedorTs;
    }

    /**
     * @return the fechaActualizacionProveedorTs
     */
    public Timestamp getFechaActualizacionProveedorTs() {
        return fechaActualizacionProveedorTs;
    }

    /**
     * @param fechaActualizacionProveedorTs the fechaActualizacionProveedorTs to
     * set
     */
    public void setFechaActualizacionProveedorTs(Timestamp fechaActualizacionProveedorTs) {
        this.fechaActualizacionProveedorTs = fechaActualizacionProveedorTs;
    }

    /**
     * @return the proveedorExistente
     */
    public boolean isProveedorExistente() {
        return proveedorExistente;
    }

    /**
     * @param proveedorExistente the proveedorExistente to set
     */
    public void setProveedorExistente(boolean proveedorExistente) {
        this.proveedorExistente = proveedorExistente;
    }

    /**
     * @return the cuentaEntfinProExistente
     */
    public boolean isCuentaEntfinProExistente() {
        return cuentaEntfinProExistente;
    }

    /**
     * @param cuentaEntfinProExistente the cuentaEntfinProExistente to set
     */
    public void setCuentaEntfinProExistente(boolean cuentaEntfinProExistente) {
        this.cuentaEntfinProExistente = cuentaEntfinProExistente;
    }

    /**
     * @return the ifipProveeExistente
     */
    public boolean isIfipProveeExistente() {
        return ifipProveeExistente;
    }

    /**
     * @param ifipProveeExistente the ifipProveeExistente to set
     */
    public void setIfipProveeExistente(boolean ifipProveeExistente) {
        this.ifipProveeExistente = ifipProveeExistente;
    }

    /**
     * @return the documentosProveedorExistente
     */
    public boolean isDocumentosProveedorExistente() {
        return documentosProveedorExistente;
    }

    /**
     * @param documentosProveedorExistente the documentosProveedorExistente to
     * set
     */
    public void setDocumentosProveedorExistente(boolean documentosProveedorExistente) {
        this.documentosProveedorExistente = documentosProveedorExistente;
    }

    /**
     * @return the documentosProveedorSel
     */
    public DocumentosProveedor getDocumentosProveedorSel() {
        return documentosProveedorSel;
    }

    /**
     * @param documentosProveedorSel the documentosProveedorSel to set
     */
    public void setDocumentosProveedorSel(DocumentosProveedor documentosProveedorSel) {
        this.documentosProveedorSel = documentosProveedorSel;
    }

    /**
     * @return the fechaEmisionDocumento
     */
    public Date getFechaEmisionDocumento() {
        return fechaEmisionDocumento;
    }

    /**
     * @param fechaEmisionDocumento the fechaEmisionDocumento to set
     */
    public void setFechaEmisionDocumento(Date fechaEmisionDocumento) {
        this.fechaEmisionDocumento = fechaEmisionDocumento;
    }

    /**
     * @return the fechaEmisionDocumentoTs
     */
    public Timestamp getFechaEmisionDocumentoTs() {
        return fechaEmisionDocumentoTs;
    }

    /**
     * @param fechaEmisionDocumentoTs the fechaEmisionDocumentoTs to set
     */
    public void setFechaEmisionDocumentoTs(Timestamp fechaEmisionDocumentoTs) {
        this.fechaEmisionDocumentoTs = fechaEmisionDocumentoTs;
    }

    /**
     * @return the fechaCaducaDocumento
     */
    public Date getFechaCaducaDocumento() {
        return fechaCaducaDocumento;
    }

    /**
     * @param fechaCaducaDocumento the fechaCaducaDocumento to set
     */
    public void setFechaCaducaDocumento(Date fechaCaducaDocumento) {
        this.fechaCaducaDocumento = fechaCaducaDocumento;
    }

    /**
     * @return the fechaCaducaDocumentoTs
     */
    public Timestamp getFechaCaducaDocumentoTs() {
        return fechaCaducaDocumentoTs;
    }

    /**
     * @param fechaCaducaDocumentoTs the fechaCaducaDocumentoTs to set
     */
    public void setFechaCaducaDocumentoTs(Timestamp fechaCaducaDocumentoTs) {
        this.fechaCaducaDocumentoTs = fechaCaducaDocumentoTs;
    }

    /**
     * @return the numeroAutorizacionDocumento
     */
    public String getNumeroAutorizacionDocumento() {
        return numeroAutorizacionDocumento;
    }

    /**
     * @param numeroAutorizacionDocumento the numeroAutorizacionDocumento to set
     */
    public void setNumeroAutorizacionDocumento(String numeroAutorizacionDocumento) {
        this.numeroAutorizacionDocumento = numeroAutorizacionDocumento;
    }

    /**
     * @return the fechaIngresoDocumento
     */
    public Date getFechaIngresoDocumento() {
        return fechaIngresoDocumento;
    }

    /**
     * @param fechaIngresoDocumento the fechaIngresoDocumento to set
     */
    public void setFechaIngresoDocumento(Date fechaIngresoDocumento) {
        this.fechaIngresoDocumento = fechaIngresoDocumento;
    }

    /**
     * @return the fechaIngresoDocumentoTs
     */
    public Timestamp getFechaIngresoDocumentoTs() {
        return fechaIngresoDocumentoTs;
    }

    /**
     * @param fechaIngresoDocumentoTs the fechaIngresoDocumentoTs to set
     */
    public void setFechaIngresoDocumentoTs(Timestamp fechaIngresoDocumentoTs) {
        this.fechaIngresoDocumentoTs = fechaIngresoDocumentoTs;
    }

    /**
     * @return the iniSerieDocumentosProv
     */
    public Long getIniSerieDocumentosProv() {
        return iniSerieDocumentosProv;
    }

    /**
     * @param iniSerieDocumentosProv the iniSerieDocumentosProv to set
     */
    public void setIniSerieDocumentosProv(Long iniSerieDocumentosProv) {
        this.iniSerieDocumentosProv = iniSerieDocumentosProv;
    }

    /**
     * @return the finSerieDocumentosProv
     */
    public Long getFinSerieDocumentosProv() {
        return finSerieDocumentosProv;
    }

    /**
     * @param finSerieDocumentosProv the finSerieDocumentosProv to set
     */
    public void setFinSerieDocumentosProv(Long finSerieDocumentosProv) {
        this.finSerieDocumentosProv = finSerieDocumentosProv;
    }

    /**
     * @return the serieDocumentosProv
     */
    public String getSerieDocumentosProv() {
        return serieDocumentosProv;
    }

    /**
     * @param serieDocumentosProv the serieDocumentosProv to set
     */
    public void setSerieDocumentosProv(String serieDocumentosProv) {
        this.serieDocumentosProv = serieDocumentosProv;
    }

    /**
     * @return the digitosComproProv
     */
    public short getDigitosComproProv() {
        return digitosComproProv;
    }

    /**
     * @param digitosComproProv the digitosComproProv to set
     */
    public void setDigitosComproProv(short digitosComproProv) {
        this.digitosComproProv = digitosComproProv;
    }

    /**
     * @return the tipoComprobanteCompra
     */
    public TipoComprobanteCompra getTipoComprobanteCompra() {
        return tipoComprobanteCompra;
    }

    /**
     * @param tipoComprobanteCompra the tipoComprobanteCompra to set
     */
    public void setTipoComprobanteCompra(TipoComprobanteCompra tipoComprobanteCompra) {
        this.tipoComprobanteCompra = tipoComprobanteCompra;
    }

    /**
     * @return the itemsTipoComprobanteCompra
     */
    public List<TipoComprobanteCompra> getItemsTipoComprobanteCompra() {
        return itemsTipoComprobanteCompra;
    }

    /**
     * @param itemsTipoComprobanteCompra the itemsTipoComprobanteCompra to set
     */
    public void setItemsTipoComprobanteCompra(List<TipoComprobanteCompra> itemsTipoComprobanteCompra) {
        this.itemsTipoComprobanteCompra = itemsTipoComprobanteCompra;
    }

    /**
     * @return the itemsEntidadFinanciera
     */
    public List<EntidadFinanciera> getItemsEntidadFinanciera() {
        return itemsEntidadFinanciera;
    }

    /**
     * @param itemsEntidadFinanciera the itemsEntidadFinanciera to set
     */
    public void setItemsEntidadFinanciera(List<EntidadFinanciera> itemsEntidadFinanciera) {
        this.itemsEntidadFinanciera = itemsEntidadFinanciera;
    }

    /**
     * @return the itemsTipoCuentaEntidadFinanciera
     */
    public List<TipoCuentaEntidadFinanciera> getItemsTipoCuentaEntidadFinanciera() {
        return itemsTipoCuentaEntidadFinanciera;
    }

    /**
     * @param itemsTipoCuentaEntidadFinanciera the
     * itemsTipoCuentaEntidadFinanciera to set
     */
    public void setItemsTipoCuentaEntidadFinanciera(List<TipoCuentaEntidadFinanciera> itemsTipoCuentaEntidadFinanciera) {
        this.itemsTipoCuentaEntidadFinanciera = itemsTipoCuentaEntidadFinanciera;
    }

    /**
     * @return the numeroCuentaProCEF
     */
    public String getNumeroCuentaProCEF() {
        return numeroCuentaProCEF;
    }

    /**
     * @param numeroCuentaProCEF the numeroCuentaProCEF to set
     */
    public void setNumeroCuentaProCEF(String numeroCuentaProCEF) {
        this.numeroCuentaProCEF = numeroCuentaProCEF;
    }

    /**
     * @return the nombreCuentaProCEF
     */
    public String getNombreCuentaProCEF() {
        return nombreCuentaProCEF;
    }

    /**
     * @param nombreCuentaProCEF the nombreCuentaProCEF to set
     */
    public void setNombreCuentaProCEF(String nombreCuentaProCEF) {
        this.nombreCuentaProCEF = nombreCuentaProCEF;
    }

    /**
     * @return the observacionesProCEF
     */
    public String getObservacionesProCEF() {
        return observacionesProCEF;
    }

    /**
     * @param observacionesProCEF the observacionesProCEF to set
     */
    public void setObservacionesProCEF(String observacionesProCEF) {
        this.observacionesProCEF = observacionesProCEF;
    }

    /**
     * @return the estadoProCEF
     */
    public String getEstadoProCEF() {
        return estadoProCEF;
    }

    /**
     * @param estadoProCEF the estadoProCEF to set
     */
    public void setEstadoProCEF(String estadoProCEF) {
        this.estadoProCEF = estadoProCEF;
    }

    /**
     * @return the codigoEntidadFinanciera
     */
    public Long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    /**
     * @param codigoEntidadFinanciera the codigoEntidadFinanciera to set
     */
    public void setCodigoEntidadFinanciera(Long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    /**
     * @return the codigoTipoCuentaEntidadFinanciera
     */
    public Long getCodigoTipoCuentaEntidadFinanciera() {
        return codigoTipoCuentaEntidadFinanciera;
    }

    /**
     * @param codigoTipoCuentaEntidadFinanciera the
     * codigoTipoCuentaEntidadFinanciera to set
     */
    public void setCodigoTipoCuentaEntidadFinanciera(Long codigoTipoCuentaEntidadFinanciera) {
        this.codigoTipoCuentaEntidadFinanciera = codigoTipoCuentaEntidadFinanciera;
    }

    /**
     * @return the entidadFinanciera
     */
    public EntidadFinanciera getEntidadFinanciera() {
        return entidadFinanciera;
    }

    /**
     * @param entidadFinanciera the entidadFinanciera to set
     */
    public void setEntidadFinanciera(EntidadFinanciera entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    /**
     * @return the tipoCuentaEntidadFinanciera
     */
    public TipoCuentaEntidadFinanciera getTipoCuentaEntidadFinanciera() {
        return tipoCuentaEntidadFinanciera;
    }

    /**
     * @param tipoCuentaEntidadFinanciera the tipoCuentaEntidadFinanciera to set
     */
    public void setTipoCuentaEntidadFinanciera(TipoCuentaEntidadFinanciera tipoCuentaEntidadFinanciera) {
        this.tipoCuentaEntidadFinanciera = tipoCuentaEntidadFinanciera;
    }

    /**
     * @return the fechaRegistroProIfip
     */
    public Date getFechaRegistroProIfip() {
        return fechaRegistroProIfip;
    }

    /**
     * @param fechaRegistroProIfip the fechaRegistroProIfip to set
     */
    public void setFechaRegistroProIfip(Date fechaRegistroProIfip) {
        this.fechaRegistroProIfip = fechaRegistroProIfip;
    }

    /**
     * @return the fechaRegistroProIfipTs
     */
    public Timestamp getFechaRegistroProIfipTs() {
        return fechaRegistroProIfipTs;
    }

    /**
     * @param fechaRegistroProIfipTs the fechaRegistroProIfipTs to set
     */
    public void setFechaRegistroProIfipTs(Timestamp fechaRegistroProIfipTs) {
        this.fechaRegistroProIfipTs = fechaRegistroProIfipTs;
    }

    /**
     * @return the registradoProvPor
     */
    public Usuario getRegistradoProvPor() {
        return registradoProvPor;
    }

    /**
     * @param registradoProvPor the registradoProvPor to set
     */
    public void setRegistradoProvPor(Usuario registradoProvPor) {
        this.registradoProvPor = registradoProvPor;
    }

    /**
     * @return the codigoProveedor
     */
    public Long getCodigoProveedor() {
        return codigoProveedor;
    }

    /**
     * @param codigoProveedor the codigoProveedor to set
     */
    public void setCodigoProveedor(Long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    /**
     * @return the fechaRegistroProAux
     */
    public Date getFechaRegistroProAux() {
        return fechaRegistroProAux;
    }

    /**
     * @param fechaRegistroProAux the fechaRegistroProAux to set
     */
    public void setFechaRegistroProAux(Date fechaRegistroProAux) {
        this.fechaRegistroProAux = fechaRegistroProAux;
    }

    /**
     * @return the fechaRegistroProTs
     */
    public Timestamp getFechaRegistroProTs() {
        return fechaRegistroProTs;
    }

    /**
     * @param fechaRegistroProTs the fechaRegistroProTs to set
     */
    public void setFechaRegistroProTs(Timestamp fechaRegistroProTs) {
        this.fechaRegistroProTs = fechaRegistroProTs;
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
     * @return the estadoOpProveedor
     */
    public int getEstadoOpProveedor() {
        return estadoOpProveedor;
    }

    /**
     * @param estadoOpProveedor the estadoOpProveedor to set
     */
    public void setEstadoOpProveedor(int estadoOpProveedor) {
        this.estadoOpProveedor = estadoOpProveedor;
    }

    /**
     * @return the estadoIngresoPro
     */
    public boolean isEstadoIngresoPro() {
        return estadoIngresoPro;
    }

    /**
     * @param estadoIngresoPro the estadoIngresoPro to set
     */
    public void setEstadoIngresoPro(boolean estadoIngresoPro) {
        this.estadoIngresoPro = estadoIngresoPro;
    }

    /**
     * @return the estadoIngresoProIfip
     */
    public boolean isEstadoIngresoProIfip() {
        return estadoIngresoProIfip;
    }

    /**
     * @param estadoIngresoProIfip the estadoIngresoProIfip to set
     */
    public void setEstadoIngresoProIfip(boolean estadoIngresoProIfip) {
        this.estadoIngresoProIfip = estadoIngresoProIfip;
    }

    /**
     * @return the codigoPersona
     */
    public Long getCodigoPersona() {
        return codigoPersona;
    }

    /**
     * @param codigoPersona the codigoPersona to set
     */
    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    /**
     * @return the estadoProCEFEdicion
     */
    public boolean isEstadoProCEFEdicion() {
        return estadoProCEFEdicion;
    }

    /**
     * @param estadoProCEFEdicion the estadoProCEFEdicion to set
     */
    public void setEstadoProCEFEdicion(boolean estadoProCEFEdicion) {
        this.estadoProCEFEdicion = estadoProCEFEdicion;
    }

    /**
     * @return the fechaIngresoPersona
     */
    public Date getFechaIngresoPersona() {
        return fechaIngresoPersona;
    }

    /**
     * @param fechaIngresoPersona the fechaIngresoPersona to set
     */
    public void setFechaIngresoPersona(Date fechaIngresoPersona) {
        this.fechaIngresoPersona = fechaIngresoPersona;
    }

    /**
     * @return the fechaActualizacionPersona
     */
    public Date getFechaActualizacionPersona() {
        return fechaActualizacionPersona;
    }

    /**
     * @param fechaActualizacionPersona the fechaActualizacionPersona to set
     */
    public void setFechaActualizacionPersona(Date fechaActualizacionPersona) {
        this.fechaActualizacionPersona = fechaActualizacionPersona;
    }

    /**
     * @return the desHabilitaRise
     */
    public boolean isDesHabilitaRise() {
        return desHabilitaRise;
    }

    /**
     * @param desHabilitaRise the desHabilitaRise to set
     */
    public void setDesHabilitaRise(boolean desHabilitaRise) {
        this.desHabilitaRise = desHabilitaRise;
    }

    /**
     * @return the banderaCueEntidad
     */
    public boolean isBanderaCueEntidad() {
        return banderaCueEntidad;
    }

    /**
     * @param banderaCueEntidad the banderaCueEntidad to set
     */
    public void setBanderaCueEntidad(boolean banderaCueEntidad) {
        this.banderaCueEntidad = banderaCueEntidad;
    }

    /**
     * @return the banderaCuenEntMov
     */
    public boolean isBanderaCuenEntMov() {
        return banderaCuenEntMov;
    }

    /**
     * @param banderaCuenEntMov the banderaCuenEntMov to set
     */
    public void setBanderaCuenEntMov(boolean banderaCuenEntMov) {
        this.banderaCuenEntMov = banderaCuenEntMov;
    }

}
