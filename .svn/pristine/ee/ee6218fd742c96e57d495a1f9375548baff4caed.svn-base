package ec.renafipse.mks.control.seguridades.bean;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.ContrasenaUsuario;
import ec.renafipse.mks.modelo.seguridades.ExpiracionContrasena;
import ec.renafipse.mks.modelo.seguridades.MotivoExpiraContrasena;
import ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfi;
import ec.renafipse.mks.modelo.seguridades.Sistema;
import ec.renafipse.mks.modelo.seguridades.TipoUsuario;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioAccesoSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistemaPK;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
import ec.renafipse.mks.modelo.socios.TipoPersona;
import ec.renafipse.mks.negocio.ifips.IfipFacade;
import ec.renafipse.mks.negocio.seguridades.ExpiracionContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.MotivoExpiraContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.ParametroGeneralSegIfiFacade;
import ec.renafipse.mks.negocio.seguridades.SistemaFacade;
import ec.renafipse.mks.negocio.seguridades.TipoUsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioAccesoSistemaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioSistemaFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import ec.renafipse.mks.negocio.socios.TipoIdentificacionFacade;
import ec.renafipse.mks.negocio.socios.TipoPersonaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends AbstractController<Usuario> implements Serializable {

    @EJB
    private UsuarioFacade ejbFacade;

    @EJB
    private IfipFacade ejbFacadeIfip;

    @EJB
    private UsuarioAccesoSistemaFacade ejbFacadeUsuAcc;

    @EJB
    private UsuarioSistemaFacade ejbFacadeUsuarioSistema;

    @EJB
    private SistemaFacade ejbFacadeSistema;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private ParametroGeneralSegIfiFacade ejbFacadeParGenSegIfi;

    @EJB
    private ExpiracionContrasenaFacade ejbFacadeExpContrasena;

    @EJB
    private TipoIdentificacionFacade ejbFacadeTipoIdentificacion;

    @EJB
    private MotivoExpiraContrasenaFacade ejbFacadeMotExpContrasena;

    @EJB
    private TipoUsuarioFacade ejbFacadeTipoUsuario;

    @EJB
    private TipoPersonaFacade ejbFacadeTipoPersona;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Almancena el la Entidad del Usuario con los datos de usuario quien accedio al sistema
    private Usuario usuario;
    // Guarda la Entidad del Acceso al Sistema del Usuario
    private UsuarioAccesoSistema usuarioAccesoSistema;
    // Fecha de Acceso al Sistema.
    private Date fecha;

    private Usuario usuarioSel;
    private ParametroGeneralSegIfi paramGenSegIfi;
    private Persona persona;
    private Sistema sistema;
    private UsuarioSistema usuarioSistema;
    private UsuarioSistemaPK usuarioSistemaPK;
    private ExpiracionContrasena expiracionContrasena;
    private TipoUsuario tipoUsuario;
    private TipoPersona tipoPersona;
    private TipoIdentificacion tipoIdentificacion;
    private MotivoExpiraContrasena motivoExpContrasena;

    private String nombreCompletoUsuarioS;
    private String correoElectronicoUsuarioS;
    private String usernameS;
    private String identificacionS;

    private String mensajeCorreoValido;
    private String msg;
    private String mensajeUsername;
    private String contrasenaAleatoria;

    private boolean deshabilitaNombreCompletoUsuario;
    private boolean deshabilitaCorreoElectronicoUsuario;
    private boolean deshabilitaPanelUsuario;
    private boolean deshabilitaPanelUsuarioBot;
    private boolean deshabilitaCreacionUsuarioBot;

    private boolean existePersona;
    private boolean existeUsuario;
    private boolean existeSistema;

    private short diasGracia;
    private short mesesVigencia;

    private List<Persona> listaPersona;
    private List<Usuario> listaPersonaUsuario;
    private List<UsuarioSistema> listaUsuarioSistemaUserEstado;
    private List<ParametroGeneralSegIfi> listaParGenIfi;
    private List<ExpiracionContrasena> listaExpiracionContrasena;
    private List<Usuario> listaUsuario;
    private List<UsuarioSistema> listaUsuarioSistema;
    // FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------

    public UsuarioBean() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        listaUsuario = this.ejbFacade.getUsuarioEli(ActivacionUsuario.getCodigoIfip(), 'N');
        this.deshabilitaPanelUsuario = false;
        this.deshabilitaPanelUsuarioBot = false;
        this.deshabilitaNombreCompletoUsuario = true;
        this.deshabilitaCorreoElectronicoUsuario = true;
        this.deshabilitaCreacionUsuarioBot = true;
        this.existePersona = false;
        this.existeUsuario = false;
        this.existeSistema = false;

        this.identificacionS = null;
        this.nombreCompletoUsuarioS = null;
        this.correoElectronicoUsuarioS = null;
        this.usernameS = null;
        this.msg = null;
        this.mensajeUsername = null;

        this.mesesVigencia = -1;
        this.diasGracia = -1;

    }
    //--------------------------------------------------------------------
    // METODOS DE LOGICA DE NEGOCIO 
    /**
     *
     */

    public void obtieneParametros() {
        this.paramGenSegIfi = new ParametroGeneralSegIfi();
        this.setListaParGenIfi(this.ejbFacadeParGenSegIfi.getItemsParGenSegIfi(ActivacionUsuario.getCodigoIfip()));
        for (ParametroGeneralSegIfi pGSI : this.getListaParGenIfi()) {
            if (String.valueOf(pGSI.getParametroGeneralSegIfiPK().getCodigoParametro()).equals("1")) {
                this.setMesesVigencia(pGSI.getValorNumerico().shortValueExact());
                //////System.out.println("Meses: "+this.getMesesVigencia());
            }
            if (String.valueOf(pGSI.getParametroGeneralSegIfiPK().getCodigoParametro()).equals("2")) {
                this.setDiasGracia(pGSI.getValorNumerico().shortValueExact());
                //////System.out.println("Dias: "+this.getDiasGracia());
            }
        }
    }

    /**
     * Metodo que permite habilitar o no el panel cuando se busca si existe o no
     * la persona
     *
     * @return
     */
    private boolean validaUsuario() {
        this.nombreCompletoUsuarioS = null;
        this.correoElectronicoUsuarioS = null;
        this.usernameS = null;
        this.deshabilitaPanelUsuario = false;
        this.deshabilitaCorreoElectronicoUsuario = true;
        this.deshabilitaNombreCompletoUsuario = true;
        this.deshabilitaCreacionUsuarioBot = true;
        this.usernameS = null;
        this.usuario = new Usuario();
        this.persona = new Persona();
        this.usuarioSistema = new UsuarioSistema();
        this.usuarioSistemaPK = new UsuarioSistemaPK();
        Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        setListaPersona(this.ejbFacadePersona.getItemsIdenPersona(this.getIdentificacionS()));
        if (!listaPersona.isEmpty()) {
            if (this.listaPersona.size() == 1) {
                this.persona = this.listaPersona.get(0);
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaRegistrada");
                MuestraMensaje.addAdvertencia(msg);

                this.setNombreCompletoUsuarioS(this.getPersona().getNombreCompleto());
                setListaPersonaUsuario(this.ejbFacade.getUsuarioPersona(getPersona().getCodigo()));
                // Si Persona Existe
                if (getListaPersonaUsuario().isEmpty()) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoRegistrado");
                    //   MuestraMensaje.addAdvertencia(msg);
                    this.deshabilitaPanelUsuario = true;
                    this.deshabilitaNombreCompletoUsuario = true;
                    this.deshabilitaCorreoElectronicoUsuario = false;
                    this.ubicaUsername();
                    this.deshabilitaCreacionUsuarioBot = false;
                    this.existePersona = true;
                    this.existeUsuario = false;
                    return true;
                } else {
                    //Obtengo USERNAME porque la persona esta como usuario
                    this.usernameS = getListaPersonaUsuario().get(0).getUsername();
                    this.usuario = this.listaPersonaUsuario.get(0);
                    // Consulto si no esta activo en otra ifip
                    List<Usuario> listaUsuarioDiferenteIfip = this.ejbFacade.getItemsUsuarioDiferenteIfipEliminado(this.persona.getCodigo(), this.ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip()), 'N');
                    if (listaUsuarioDiferenteIfip.isEmpty()) {
                        // Obtengo el usuario mediante el codigo y la ifip                        
                        setListaPersonaUsuario(this.ejbFacade.getItemsPersonaIfip(ActivacionUsuario.getCodigoIfip(), getPersona().getCodigo()));

                        // Verifico si no existe como usuario para la IFIP
                        if (getListaPersonaUsuario().isEmpty()) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoRegistrado");
                            // MuestraMensaje.addAdvertencia(msg);
                            this.deshabilitaPanelUsuario = true;
                            this.deshabilitaNombreCompletoUsuario = true;
                            this.deshabilitaCorreoElectronicoUsuario = false;
                            this.existePersona = true;
                            this.existeUsuario = false;
                            this.ubicaUsername();
                            this.deshabilitaCreacionUsuarioBot = false;
                            return true;
                        } else {
                            if (getListaPersonaUsuario().size() == 1) {
                                if (String.valueOf(getListaPersonaUsuario().get(0).getEliminado()).equals("S")) {
                                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioExisteIfipEliminado");
                                    return false;
                                } else {
                                    UsuarioSistemaPK uspk = new UsuarioSistemaPK(getListaPersonaUsuario().get(0).getCodigo(), Long.parseLong("2"));
                                    usuarioSistema = this.ejbFacadeUsuarioSistema.find(uspk);
                                    // verificio si el usuario esta creado en el sistema
                                    if (usuarioSistema == null) {
                                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioActivoOtroSistema");
                                        // MuestraMensaje.addAdvertencia(msg);
                                        this.deshabilitaPanelUsuario = true;
                                        this.deshabilitaNombreCompletoUsuario = true;
                                        this.deshabilitaCorreoElectronicoUsuario = true;
                                        this.setCorreoElectronicoUsuarioS(getListaPersonaUsuario().get(0).getCorreoElectronico());
                                        this.setUsernameS(usernameS);
                                        this.deshabilitaCreacionUsuarioBot = false;
                                        this.existePersona = true;
                                        this.existeUsuario = true;
                                        this.existeSistema = false;
                                        return true;
                                    } else {
                                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioExiste");
                                        this.deshabilitaPanelUsuario = true;
                                        this.deshabilitaNombreCompletoUsuario = true;
                                        this.deshabilitaCorreoElectronicoUsuario = true;
                                        this.setCorreoElectronicoUsuarioS(getListaPersonaUsuario().get(0).getCorreoElectronico());
                                        this.setUsernameS(usernameS);
                                        this.existePersona = true;
                                        this.existeUsuario = true;
                                        this.deshabilitaCreacionUsuarioBot = true;
                                        return false;
                                    }
                                }
                            } else {
                                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioExisteMasUnaIfip");
                                return false;
                            }
                        }
                    } else {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioAsignadoOtraIfip");
                        return false;
                    }
                }
            } else {
                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IdentificacionAsignadoMasDeUnaPersona");
                return false;
            }
        } else {
            this.deshabilitaPanelUsuario = true;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaNoRegistrada");
            this.deshabilitaNombreCompletoUsuario = false;
            this.deshabilitaCorreoElectronicoUsuario = false;
            this.deshabilitaCreacionUsuarioBot = false;
            this.existePersona = false;
        }

        return true;
    }

    public void mostrarPanel() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //  preparaUsuario();
        if (!this.validaUsuario()) {
            MuestraMensaje.addAdvertencia(msg);
        } else {
            MuestraMensaje.addAdvertencia(msg);
        }
    }

    /**
     * Metodo para la creacion del usuario
     *
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public void creaUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //Instancio todas las entidades necesarias
        tipoUsuario = new TipoUsuario();
        tipoPersona = new TipoPersona();
        tipoIdentificacion = new TipoIdentificacion();
        sistema = new Sistema();
        motivoExpContrasena = new MotivoExpiraContrasena();
        expiracionContrasena = new ExpiracionContrasena();
        Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());
        obtieneParametros();
        if (this.getMesesVigencia() > -1 && this.getDiasGracia() > -1) {
            if (!existePersona) {
                //No existe la persona

                //Obtengo el tipo de identificacion Cedula
                tipoIdentificacion = this.ejbFacadeTipoIdentificacion.find(Long.parseLong("1"));
                if (tipoIdentificacion == null) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoTipoIdentificacion");
                    MuestraMensaje.addAdvertencia(msg);
                }

                //Obtengo el tipo de persona Natural
                tipoPersona = this.ejbFacadeTipoPersona.find(Long.parseLong("1"));
                if (tipoPersona == null) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoTipoPersona");
                    MuestraMensaje.addAdvertencia(msg);
                }

                //Creo la persona       
                Persona person = new Persona();
                person.setCodigoTipoIdentificacion(tipoIdentificacion);
                person.setCodigoTipoPersona(tipoPersona);
                person.setIdentificacion(this.getIdentificacionS());
                person.setNombreCompleto(this.getNombreCompletoUsuarioS());
                person.setFechaIngreso(new Date());
                person.setFechaActualizacion(new Date());
                person.setCorreoEletronico(null);
                this.ejbFacadePersona.create(person);

                //Obtengo el tipo de usuario Normal
                tipoUsuario = this.ejbFacadeTipoUsuario.find(Long.parseLong("2"));
                if (tipoUsuario == null) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoTipoUsuario");
                    MuestraMensaje.addAdvertencia(msg);
                }

                //Creo el usuario 
                Usuario usuarioCreacion = this.ejbFacade.find(ActivacionUsuario.getCodigoUsuario());
                //Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());                
                usuario.setCodigoUsuarioCreacion(usuarioCreacion);
                usuario.setCodigoTipoUsuario(tipoUsuario);
                usuario.setIfip(ActivacionUsuario.getCodigoIfip());
                usuario.setPersona(person.getCodigo());
                usuario.setUsername(this.getUsernameS());
                usuario.setFechaCreacion(new Date());
                usuario.setCorreoElectronico(this.getCorreoElectronicoUsuarioS());
                usuario.setEliminado('N');
                this.ejbFacade.create(usuario);

                //Obtengo el sistema Minkasoftware
                sistema = this.ejbFacadeSistema.find(Long.parseLong("2"));
                if (sistema == null) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoSistema");
                    MuestraMensaje.addAdvertencia(msg);

                }

                //Creo el usuario sistema
                usuarioSistemaPK.setCodigoUsuario(usuario.getCodigo());
                usuarioSistemaPK.setCodigoSistema(sistema.getCodigo());
                usuarioSistema.setUsuarioSistemaPK(usuarioSistemaPK);
                this.setContrasenaAleatoria(String.valueOf(generaContrasenaNueva()));
                usuarioSistema.setContrasena(Sesion.MD5(String.valueOf(getContrasenaAleatoria())));
                usuarioSistema.setFechaAsignacion(new Date());
                usuarioSistema.setAsignadoPor(ActivacionUsuario.getUsuario());
                usuarioSistema.setFechaCaducaContrasena(obtieneFechaCaducaContrasena());
                usuarioSistema.setMesesVigencia(this.getMesesVigencia());
                usuarioSistema.setDiasGracia(this.getDiasGracia());
                usuarioSistema.setEstado('X');
                usuarioSistema.setFechaCambioContrasena(new Date());
                this.ejbFacadeUsuarioSistema.create(usuarioSistema);

                //Obtengo el usuario sistema con estado Vigente si
                listaExpiracionContrasena = this.ejbFacadeExpContrasena.getIemsExpiracionConUsuSisVig(usuarioSistema, 'S');
                if (listaExpiracionContrasena != null) {
                    for (ExpiracionContrasena expc : listaExpiracionContrasena) {
                        expc.setVigente('N');
                        this.ejbFacadeExpContrasena.edit(expc);
                    }
                }

                //Obtengo el motivo de expiracion de la contrasena de la creacion de usuario
                motivoExpContrasena = this.ejbFacadeMotExpContrasena.find(Long.parseLong("3"));
                if (motivoExpContrasena == null) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoMotivoExpContrasena");
                    MuestraMensaje.addAdvertencia(msg);
                }

                //Creo una expiracion de la contrasena
                expiracionContrasena.setCodigoUsuario(usuarioSistema.getUsuarioSistemaPK().getCodigoUsuario());
                expiracionContrasena.setCodigoSistema(usuarioSistema.getUsuarioSistemaPK().getCodigoSistema());
                expiracionContrasena.setCodigoMotivoExpira(motivoExpContrasena);
                expiracionContrasena.setExpiradoPor(ActivacionUsuario.getUsuario());
                expiracionContrasena.setFechaExpiracion(new Date());
                expiracionContrasena.setVigente('S');
                expiracionContrasena.setObservaciones("POR CREACION DE NUEVO USUARIO");
                this.ejbFacadeExpContrasena.create(expiracionContrasena);

                enviarContrasenaMail();
                this.deshabilitaCreacionUsuarioBot = true;
                this.deshabilitaNombreCompletoUsuario = true;
                this.deshabilitaCorreoElectronicoUsuario = true;
                String msg1 = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioCreadoCorrectamente");
                MuestraMensaje.addSatisfactorio(msg1 + ifip.getNombre());

            } else {
                if (existePersona && !existeUsuario) {
                //No existe el usuario

                    //Obtengo el tipo de usuario Normal
                    tipoUsuario = this.ejbFacadeTipoUsuario.find(Long.parseLong("2"));
                    if (tipoUsuario == null) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoTipoUsuario");
                        MuestraMensaje.addAdvertencia(msg);
                    }

                    //Creo el usuario              
                    Usuario usuarioCreacion = this.ejbFacade.find(ActivacionUsuario.getCodigoUsuario());
                    //Ifip ifip = ejbFacadeIfip.find(ActivacionUsuario.getCodigoIfip());             
                    usuario.setCodigoUsuarioCreacion(usuarioCreacion);
                    usuario.setCodigoTipoUsuario(tipoUsuario);
                    usuario.setIfip(ActivacionUsuario.getCodigoIfip());
                    usuario.setPersona(persona.getCodigo());
                    usuario.setUsername(this.getUsernameS());
                    usuario.setFechaCreacion(new Date());
                    usuario.setCorreoElectronico(this.getCorreoElectronicoUsuarioS());
                    usuario.setEliminado('N');
                    this.ejbFacade.create(usuario);

                    //Obtengo el sistema Minkasoftware
                    sistema = this.ejbFacadeSistema.find(Long.parseLong("2"));
                    if (sistema == null) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoSistema");
                        MuestraMensaje.addAdvertencia(msg);
                    }

                    //Creo el usuario sistema
                    usuarioSistemaPK.setCodigoUsuario(usuario.getCodigo());
                    usuarioSistemaPK.setCodigoSistema(sistema.getCodigo());
                    usuarioSistema.setUsuarioSistemaPK(usuarioSistemaPK);
                    this.setContrasenaAleatoria(String.valueOf(generaContrasenaNueva()));
                    usuarioSistema.setContrasena(Sesion.MD5(String.valueOf(getContrasenaAleatoria())));
                    usuarioSistema.setFechaAsignacion(new Date());
                    usuarioSistema.setAsignadoPor(ActivacionUsuario.getUsuario());
                    usuarioSistema.setFechaCaducaContrasena(obtieneFechaCaducaContrasena());
                    usuarioSistema.setMesesVigencia(this.getMesesVigencia());
                    usuarioSistema.setDiasGracia(this.getDiasGracia());
                    usuarioSistema.setEstado('X');
                    usuarioSistema.setFechaCambioContrasena(new Date());
                    this.ejbFacadeUsuarioSistema.create(usuarioSistema);

                    //Obtengo el usuario sistema con estado Vigente si
                    listaExpiracionContrasena = this.ejbFacadeExpContrasena.getIemsExpiracionConUsuSisVig(usuarioSistema, 'S');
                    if (listaExpiracionContrasena != null) {
                        for (ExpiracionContrasena expc : listaExpiracionContrasena) {
                            expc.setVigente('N');
                            this.ejbFacadeExpContrasena.edit(expc);
                        }
                    }

                    //Obtengo el motivo de expiracion de la contrasena de la creacion de usuario
                    motivoExpContrasena = this.ejbFacadeMotExpContrasena.find(Long.parseLong("3"));
                    if (motivoExpContrasena == null) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoMotivoExpContrasena");
                        MuestraMensaje.addAdvertencia(msg);
                    }

                    //Creo una expiracion de la contrasena
                    expiracionContrasena.setCodigoUsuario(usuarioSistema.getUsuarioSistemaPK().getCodigoUsuario());
                    expiracionContrasena.setCodigoSistema(usuarioSistema.getUsuarioSistemaPK().getCodigoSistema());
                    expiracionContrasena.setCodigoMotivoExpira(motivoExpContrasena);
                    expiracionContrasena.setExpiradoPor(ActivacionUsuario.getUsuario());
                    expiracionContrasena.setFechaExpiracion(new Date());
                    expiracionContrasena.setVigente('S');
                    expiracionContrasena.setObservaciones("POR CREACION DE NUEVO USUARIO");
                    this.ejbFacadeExpContrasena.create(expiracionContrasena);

                    this.deshabilitaCreacionUsuarioBot = true;
                    this.deshabilitaNombreCompletoUsuario = true;
                    this.deshabilitaCorreoElectronicoUsuario = true;
                    enviarContrasenaMail();
                    String msg1 = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioCreadoCorrectamente");
                    MuestraMensaje.addSatisfactorio(msg1 + ifip.getNombre());
                } else {
                    if (existePersona && existeUsuario && !existeSistema) {
                        //Obtengo el sistema Minkasoftware
                        sistema = this.ejbFacadeSistema.find(Long.parseLong("2"));
                        if (sistema == null) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoSistema");
                            MuestraMensaje.addAdvertencia(msg);
                        }

                        //Creo el usuario sistema                        
                        UsuarioSistema usuSis = new UsuarioSistema();
                        UsuarioSistemaPK usuSisPk = new UsuarioSistemaPK();
                        usuSisPk.setCodigoUsuario(usuario.getCodigo());
                        usuSisPk.setCodigoSistema(sistema.getCodigo());
                        usuSis.setUsuarioSistemaPK(usuSisPk);
                        this.setContrasenaAleatoria(String.valueOf(generaContrasenaNueva()));
                        usuSis.setContrasena(Sesion.MD5(String.valueOf(getContrasenaAleatoria())));
                        usuSis.setFechaAsignacion(new Date());
                        usuSis.setAsignadoPor(ActivacionUsuario.getUsuario());
                        usuSis.setFechaCaducaContrasena(obtieneFechaCaducaContrasena());
                        usuSis.setMesesVigencia(this.getMesesVigencia());
                        usuSis.setDiasGracia(this.getDiasGracia());
                        usuSis.setEstado('X');
                        usuSis.setFechaCambioContrasena(new Date());
                        this.ejbFacadeUsuarioSistema.create(usuSis);

                        //Obtengo el usuario sistema con estado Vigente si
                        listaExpiracionContrasena = this.ejbFacadeExpContrasena.getIemsExpiracionConUsuSisVig(usuSis, 'S');
                        if (listaExpiracionContrasena != null) {
                            for (ExpiracionContrasena expc : listaExpiracionContrasena) {
                                expc.setVigente('N');
                                this.ejbFacadeExpContrasena.edit(expc);
                            }
                        }

                        //Obtengo el motivo de expiracion de la contrasena de la creacion de usuario
                        motivoExpContrasena = this.ejbFacadeMotExpContrasena.find(Long.parseLong("3"));
                        if (motivoExpContrasena == null) {
                            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoDefinidoMotivoExpContrasena");
                            MuestraMensaje.addAdvertencia(msg);
                        }

                        //Creo una expiracion de la contrasena
                        expiracionContrasena.setCodigoUsuario(usuSis.getUsuarioSistemaPK().getCodigoUsuario());
                        expiracionContrasena.setCodigoSistema(usuSis.getUsuarioSistemaPK().getCodigoSistema());
                        expiracionContrasena.setCodigoMotivoExpira(motivoExpContrasena);
                        expiracionContrasena.setExpiradoPor(ActivacionUsuario.getUsuario());
                        expiracionContrasena.setFechaExpiracion(new Date());
                        expiracionContrasena.setVigente('S');
                        expiracionContrasena.setObservaciones("POR CREACION DE NUEVO USUARIO");
                        this.ejbFacadeExpContrasena.create(expiracionContrasena);

                        this.deshabilitaCreacionUsuarioBot = true;
                        this.deshabilitaNombreCompletoUsuario = true;
                        this.deshabilitaCorreoElectronicoUsuario = true;
                        enviarContrasenaMail();
                        String msg1 = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioCreadoCorrectamenteSistema");
                        MuestraMensaje.addSatisfactorio(msg1 + ifip.getNombre());
                    }

                }
            }

        }
    }

    /**
     * Genera un username con el nombre completo de la persona
     *
     * @return
     */
    public String generaUsername() {
        boolean existeUsername = false;
        String[] nombresUsuario = this.getNombreCompletoUsuarioS().split(" ");
        String Nombre1 = "";
        String Nombre2 = "";
        String username = "";
        String Apellido = nombresUsuario[0].toString();
        username = nombresUsuario[2].substring(0, 1) + Apellido;
        List<Usuario> listaUsername = this.ejbFacade.getUsername(username);
        if (!listaUsername.isEmpty()) {
            existeUsername = true;
        }
        if (existeUsername) {
            for (int i = 0; i < nombresUsuario[2].length(); i++) {
                Nombre1 = nombresUsuario[2].substring(i, i + 1);
                for (int j = 0; j < nombresUsuario[3].length(); j++) {
                    Nombre2 = nombresUsuario[3].substring(j, j + 1);
                    username = Nombre1 + Nombre2 + Apellido;
                    if (this.ejbFacade.getUsername(username).isEmpty()) {
                        return username;
                    }

                }
            }
        }
        return username;
    }

    /**
     * Ubico el username en el cuadro de texto
     */
    public void ubicaUsername() {
//        if (this.usernameS != null) {
        this.setMensajeUsername(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsernameSeleccionado"));
        this.setUsernameS(generaUsername());

        //      }
    }

    /**
     * Metodo que permite enviar la nueva contraseña al mail del usuario por
     * motivo de expiracion
     */
    public void enviarContrasenaMail() {
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "172.20.250.60"); // 172.20.250.60 mail.renafipse.ec
            //props.setProperty("mail.smtp.ssl.trust", "enable");              
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.user", "soportevimasif@renafipse.ec");
            props.setProperty("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("soportevimasif@renafipse.ec"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(this.getCorreoElectronicoUsuarioS()));
            //new InternetAddress("sistemas.rena@gmail.com"));
            message.setSubject("Creacion de Usuario " + this.getNombreCompletoUsuarioS());
            message.setSentDate(new Date());
            message.setText(" Username: " + this.getUsernameS() + "\n"
                    + " " + "Contrasena: " + this.getContrasenaAleatoria());
            Transport t = session.getTransport("smtp");
            t.connect("mail.renafipse.ec", "soportevimasif@renafipse.ec", "soportevimasif123");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (Exception e) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MailNoEnviado");
            MuestraMensaje.addAdvertencia(msg + this.getContrasenaAleatoria());
            e.printStackTrace();
        }
    }

    /**
     * Se obtiene el mes inicial del año actual
     *
     * @param anio
     * @return
     */
    public Date obtieneFechaInicial(int anio) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaIni = "01/01/" + anio;
        Date fechaInicial = null;
        try {
            fechaInicial = formato.parse(fechaIni);

        } catch (ParseException ex) {
            Logger.getLogger(ContrasenaUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return fechaInicial;
    }

    /**
     * Se obtiene el mes final del año actual
     *
     * @param anio
     * @return
     */
    public Date obtieneFechaFinal(int anio) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFin = "31/12/" + anio;
        Date fechaFinal = null;
        try {
            fechaFinal = formato.parse(fechaFin);

        } catch (ParseException ex) {
            Logger.getLogger(ContrasenaUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return fechaFinal;
    }

    /**
     * Se obtiene la fecha de caducidad de la contrasena
     *
     * @return
     */
    public Date obtieneFechaCaducaContrasena() {
        obtieneParametros();
        GregorianCalendar calendario = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        calendario.add(Calendar.MONTH, this.getMesesVigencia());
        calendario.add(Calendar.DATE, this.getDiasGracia());
        String fecha = formato.format(calendario.getTime());
        Date envioFechaExpiracion = null;
        try {
            envioFechaExpiracion = formato.parse(fecha);

        } catch (ParseException ex) {
            Logger.getLogger(ContrasenaUsuario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return envioFechaExpiracion;

    }

    /**
     * Genera una contrasena aleatoria de 7 digitos
     *
     * @return
     */
    public long generaContrasenaNueva() {
        long numAleatorio = 10000000L;
        long valorDado = (long) Math.floor(Math.random() * numAleatorio);
        return valorDado;
    }

    /**
     *
     */
    public void eliminaUsuario() {
        msg = null;
        boolean registrado = false;

        if (this.getListaUsuario() != null) {
            for (Usuario u : this.getListaUsuario()) {
                if (u.getCodigo() == getUsuarioSel().getCodigo()) {
                    registrado = true;
                    break;
                }
            }
            if (registrado) {
                getUsuarioSel().setEliminado('S');
                this.ejbFacade.edit(getUsuarioSel());
                this.setListaUsuario(this.ejbFacade.getUsuarioEli(ActivacionUsuario.getCodigoIfip(), 'N'));

                List<UsuarioSistema> itemsUsuarioSistemaSel = this.ejbFacadeUsuarioSistema.getItemsUsuarioSistemaSel(getUsuarioSel().getCodigo(), 'E');
                if (itemsUsuarioSistemaSel.size() == 1) {
                    for (UsuarioSistema us : itemsUsuarioSistemaSel) {
                        if (us.getUsuarioSistemaPK().getCodigoUsuario() == getUsuarioSel().getCodigo()) {
                            registrado = true;
                            break;
                        }
                    }
                    if (registrado) {
                        UsuarioSistema us = new UsuarioSistema();
                        us = itemsUsuarioSistemaSel.get(0);
                        us.setEstado('E');
                        this.ejbFacadeUsuarioSistema.edit(us);
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioEliminado");
                        MuestraMensaje.addSatisfactorio(msg);
                    }

                }

            }
        }

    }

    // FIN METODOS DE LOGICA DE NEGOCIO  
    // --------------------------------------------------------------------------
    // --------------------------------------------------------------------------
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the usuarioAccesoSistema
     */
    public UsuarioAccesoSistema getUsuarioAccesoSistema() {
        return usuarioAccesoSistema;
    }

    /**
     * @param usuarioAccesoSistema the usuarioAccesoSistema to set
     */
    public void setUsuarioAccesoSistema(UsuarioAccesoSistema usuarioAccesoSistema) {
        this.usuarioAccesoSistema = usuarioAccesoSistema;
    }

    // -- FIN DE ENCAPSULAMIENTO PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
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
     * @return the usuarioSistemaPK
     */
    public UsuarioSistemaPK getUsuarioSistemaPK() {
        return usuarioSistemaPK;
    }

    /**
     * @param usuarioSistemaPK the usuarioSistemaPK to set
     */
    public void setUsuarioSistemaPK(UsuarioSistemaPK usuarioSistemaPK) {
        this.usuarioSistemaPK = usuarioSistemaPK;
    }

    /**
     * @return the usuarioSistema
     */
    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    /**
     * @param usuarioSistema the usuarioSistema to set
     */
    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    /**
     * @return the nombreCompletoUsuarioS
     */
    public String getNombreCompletoUsuarioS() {
        return nombreCompletoUsuarioS;
    }

    /**
     * @param nombreCompletoUsuarioS the nombreCompletoUsuarioS to set
     */
    public void setNombreCompletoUsuarioS(String nombreCompletoUsuarioS) {
        this.nombreCompletoUsuarioS = nombreCompletoUsuarioS;
    }

    /**
     * @return the correoElectronicoUsuarioS
     */
    public String getCorreoElectronicoUsuarioS() {
        return correoElectronicoUsuarioS;
    }

    /**
     * @param correoElectronicoUsuarioS the correoElectronicoUsuarioS to set
     */
    public void setCorreoElectronicoUsuarioS(String correoElectronicoUsuarioS) {
        this.correoElectronicoUsuarioS = correoElectronicoUsuarioS;
    }

    /**
     * @return the usernameS
     */
    public String getUsernameS() {
        return usernameS;
    }

    /**
     * @param usernameS the usernameS to set
     */
    public void setUsernameS(String usernameS) {
        this.usernameS = usernameS;
    }

    /**
     * @return the identificacionS
     */
    public String getIdentificacionS() {
        return identificacionS;
    }

    /**
     * @param identificacionS the identificacionS to set
     */
    public void setIdentificacionS(String identificacionS) {
        this.identificacionS = identificacionS;
    }

    /**
     * @return the deshabilitaNombreCompletoUsuarioB
     */
    public boolean isDeshabilitaNombreCompletoUsuario() {
        return deshabilitaNombreCompletoUsuario;
    }

    /**
     *
     * @param deshabilitaNombreCompletoUsuario
     */
    public void setDeshabilitaNombreCompletoUsuario(boolean deshabilitaNombreCompletoUsuario) {
        this.deshabilitaNombreCompletoUsuario = deshabilitaNombreCompletoUsuario;
    }

    /**
     * @return the deshabilitaCorreoElectronicoUsuario
     */
    public boolean isDeshabilitaCorreoElectronicoUsuario() {
        return deshabilitaCorreoElectronicoUsuario;
    }

    /**
     *
     * @param deshabilitaCorreoElectronicoUsuario
     */
    public void setDeshabilitaCorreoElectronicoUsuario(boolean deshabilitaCorreoElectronicoUsuario) {
        this.deshabilitaCorreoElectronicoUsuario = deshabilitaCorreoElectronicoUsuario;
    }

    /**
     * @return the mensajeCorreoValido
     */
    public String getMensajeCorreoValido() {
        return mensajeCorreoValido;
    }

    /**
     * @param mensajeCorreoValido the mensajeCorreoValido to set
     */
    public void setMensajeCorreoValido(String mensajeCorreoValido) {
        this.mensajeCorreoValido = mensajeCorreoValido;
    }

    /**
     * @return the habilitaPanelUsuario
     */
    public boolean isDeshabilitaPanelUsuario() {
        return deshabilitaPanelUsuario;
    }

    /**
     *
     * @param deshabilitaPanelUsuario
     */
    public void setDeshabilitaPanelUsuario(boolean deshabilitaPanelUsuario) {
        this.deshabilitaPanelUsuario = deshabilitaPanelUsuario;
    }

    /**
     * @return the diasGracia
     */
    public short getDiasGracia() {
        return diasGracia;
    }

    /**
     * @param diasGracia the diasGracia to set
     */
    public void setDiasGracia(short diasGracia) {
        this.diasGracia = diasGracia;
    }

    /**
     * @return the mesesVigencia
     */
    public short getMesesVigencia() {
        return mesesVigencia;
    }

    /**
     * @param mesesVigencia the mesesVigencia to set
     */
    public void setMesesVigencia(short mesesVigencia) {
        this.mesesVigencia = mesesVigencia;
    }

    /**
     * @return the contrasenaAleatoria
     */
    public String getContrasenaAleatoria() {
        return contrasenaAleatoria;
    }

    /**
     * @param contrasenaAleatoria the contrasenaAleatoria to set
     */
    public void setContrasenaAleatoria(String contrasenaAleatoria) {
        this.contrasenaAleatoria = contrasenaAleatoria;
    }

    /**
     * @return the expiracionContrasena
     */
    public ExpiracionContrasena getExpiracionContrasena() {
        return expiracionContrasena;
    }

    /**
     * @param expiracionContrasena the expiracionContrasena to set
     */
    public void setExpiracionContrasena(ExpiracionContrasena expiracionContrasena) {
        this.expiracionContrasena = expiracionContrasena;
    }

    /**
     * @return the deshabilitaCreacionUsuarioBot
     */
    public boolean isDeshabilitaCreacionUsuarioBot() {
        return deshabilitaCreacionUsuarioBot;
    }

    /**
     *
     * @param deshabilitaCreacionUsuarioBot
     */
    public void setDeshabilitaCreacionUsuarioBot(boolean deshabilitaCreacionUsuarioBot) {
        this.deshabilitaCreacionUsuarioBot = deshabilitaCreacionUsuarioBot;
    }

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
     * @return the motivoExpContrasena
     */
    public MotivoExpiraContrasena getMotivoExpContrasena() {
        return motivoExpContrasena;
    }

    /**
     * @param motivoExpContrasena the motivoExpContrasena to set
     */
    public void setMotivoExpContrasena(MotivoExpiraContrasena motivoExpContrasena) {
        this.motivoExpContrasena = motivoExpContrasena;
    }

    /**
     * @return the sistema
     */
    public Sistema getSistema() {
        return sistema;
    }

    /**
     * @param sistema the sistema to set
     */
    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    /**
     * @return the mensajeUsername
     */
    public String getMensajeUsername() {
        return mensajeUsername;
    }

    /**
     * @param mensajeUsername the mensajeUsername to set
     */
    public void setMensajeUsername(String mensajeUsername) {
        this.mensajeUsername = mensajeUsername;
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
     * @return the paramGenSegIfi
     */
    public ParametroGeneralSegIfi getParamGenSegIfi() {
        return paramGenSegIfi;
    }

    /**
     * @param paramGenSegIfi the paramGenSegIfi to set
     */
    public void setParamGenSegIfi(ParametroGeneralSegIfi paramGenSegIfi) {
        this.paramGenSegIfi = paramGenSegIfi;
    }

    /**
     * @return the existeUsuario
     */
    public boolean isExisteUsuario() {
        return existeUsuario;
    }

    /**
     * @param existeUsuario the existeUsuario to set
     */
    public void setExisteUsuario(boolean existeUsuario) {
        this.existeUsuario = existeUsuario;
    }

    /**
     * @return the usuarioSel
     */
    public Usuario getUsuarioSel() {
        return usuarioSel;
    }

    /**
     * @param usuarioSel the usuarioSel to set
     */
    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }

    /**
     * @return the desabilitaPanelUsuarioBot
     */
    public boolean isDeshabilitaPanelUsuarioBot() {
        return deshabilitaPanelUsuarioBot;
    }

    /**
     *
     * @param deshabilitaPanelUsuarioBot
     */
    public void setDeshabilitaPanelUsuarioBot(boolean deshabilitaPanelUsuarioBot) {
        this.deshabilitaPanelUsuarioBot = deshabilitaPanelUsuarioBot;
    }

    /**
     * @return the listPersona
     */
    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    /**
     * @param listaPersona the listaPersona to set
     */
    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    /**
     * @return the listaParGenIfi
     */
    public List<ParametroGeneralSegIfi> getListaParGenIfi() {
        return listaParGenIfi;
    }

    /**
     * @param listaParGenIfi the listaParGenIfi to set
     */
    public void setListaParGenIfi(List<ParametroGeneralSegIfi> listaParGenIfi) {
        this.listaParGenIfi = listaParGenIfi;
    }

    /**
     * @return the listaUsuario
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    /**
     * @return the listaPersonaUsuario
     */
    public List<Usuario> getListaPersonaUsuario() {
        return listaPersonaUsuario;
    }

    /**
     * @param listaPersonaUsuario the listaPersonaUsuario to set
     */
    public void setListaPersonaUsuario(List<Usuario> listaPersonaUsuario) {
        this.listaPersonaUsuario = listaPersonaUsuario;
    }

    /**
     * @return the existeSistema
     */
    public boolean isExisteSistema() {
        return existeSistema;
    }

    /**
     * @param existeSistema the existeSistema to set
     */
    public void setExisteSistema(boolean existeSistema) {
        this.existeSistema = existeSistema;
    }
}// FIN DE LA CLASE
