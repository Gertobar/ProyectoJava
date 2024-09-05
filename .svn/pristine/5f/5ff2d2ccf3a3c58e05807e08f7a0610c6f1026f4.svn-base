package ec.renafipse.mks.control.seguridades;

import ec.mss.web.configuracion.ConfiguracionServicio;
import ec.renafipse.mks.capas.basedatos.EjecutaConsultas;
import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.cliente.ObtieneInformacionCliente;
import ec.renafipse.mks.capas.utilitario.Utilidades;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.contables.PeriodoContable;
import ec.renafipse.mks.modelo.ifips.Computador;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfip;
import ec.renafipse.mks.modelo.ifips.ParametroServidorIfipPK;
import ec.renafipse.mks.modelo.seguridades.BloqueoCuentaUsuario;
import ec.renafipse.mks.modelo.seguridades.CaracterNoAcpetadoContrasena;
import ec.renafipse.mks.modelo.seguridades.IntentoFallidoAccesoSistema;
import ec.renafipse.mks.modelo.seguridades.PalabraNoAceptadaContrasena;
import ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfi;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioAccesoSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistemaPK;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.negocio.contables.PeriodoContableFacade;
import ec.renafipse.mks.negocio.contables.PlanDeCuentaFacade;
import ec.renafipse.mks.negocio.ifips.ComputadorFacade;
import ec.renafipse.mks.negocio.ifips.ParametroServidorIfipFacade;
import ec.renafipse.mks.negocio.seguridades.BloqueoCuentaUsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.CaracterNoAcpetadoContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.IntentoFallidoAccesoSistemaFacade;
import ec.renafipse.mks.negocio.seguridades.MotivoBloqueoCuentaFacade;
import ec.renafipse.mks.negocio.seguridades.PalabraNoAceptadaContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.ParametroGeneralSegIfiFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioAccesoSistemaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioSistemaFacade;
import ec.renafipse.mks.negocio.socios.PersonaFacade;
import groovy.xml.Entity;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private UsuarioFacade ejbFacade;

    @EJB
    private UsuarioAccesoSistemaFacade ejbFacadeUsuAcc;

    @EJB
    private UsuarioSistemaFacade ejbFacadeUsuarioSistema;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private ComputadorFacade ejbFacadeComputador;

    @EJB
    private ParametroGeneralSegIfiFacade ejbFacadeParametroGeneralSegIfi;

    @EJB
    private IntentoFallidoAccesoSistemaFacade ejbFacadeIntentoFallidoAccesoSistema;

    @EJB
    private BloqueoCuentaUsuarioFacade ejbFacadeBloqueoCuentaUsuario;

    @EJB
    private MotivoBloqueoCuentaFacade ejbFacadeMotivoBloqueoCuenta;

    @EJB
    private ParametroServidorIfipFacade ejbFacadeParametroServidorIfip;

    @EJB
    private CaracterNoAcpetadoContrasenaFacade ejbFacadeCarNoAceCon;

    @EJB
    private PalabraNoAceptadaContrasenaFacade ejbFacadePalNoAceCon;

    @EJB
    private PeriodoContableFacade ejbFacadePeriodoContable;

    @EJB
    private ConfiguracionServicio configuracionServicio;
    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Usado para el ingreso del username de la venta de login
    private String username;
    // Usado para el ingreso de la contraseña de la venta de login
    private String contrasena;
    // Almancena el la Entidad del Usuario con los datos de usuario quien accedio al sistema
    private Usuario usuario;
    // Guarda la Entidad del Acceso al Sistema del Usuario
    private UsuarioAccesoSistema usuarioAccesoSistema;
    // Fecha de Acceso al Sistema.
    private Date fecha;
    //Mensaje en el Dialogo de Cambio de Contraseña
    private String mensajeDialogoCambioContrasena;

    //Mensaje de procesos
    private String mensaje;

    //Numero de Intentos de Acceso
    private int numeroIntentos;
    private RequestContext context;

    private Persona persona;

    private String mensajeCorreoValido;
    private boolean habilitaPanelUsuario;
    private LlamaSP llamaSP;
    private EjecutaConsultas ejecutaConsultas;
    private PeriodoContable periodoContable;

    private List<Persona> itemsPersona;
    // FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------

    //Cambio de clave temporal 
    private String claveTemporal;
    private String claveNueva;
    private String claveConfirmacion;
    private String focoDialogoCambioContrasenaTemporal;

    public UsuarioController() {
        super(Usuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        persona = new Persona();
        this.username = null;
        this.contrasena = null;
        this.usuario = null;
        this.habilitaPanelUsuario = false;
        this.numeroIntentos = 0;
        focoDialogoCambioContrasenaTemporal = "claveTemporal";
        ActivacionUsuario.setCambiarContrasena(false);
        llamaSP = new LlamaSP();
        ejecutaConsultas = new EjecutaConsultas();

    }

    // --------------------------------------------------------------------------
    // METODOS DE LOGICA DE NEGOCIO 
    /**
     * Metodo que valida el usuario para el ingreso al Sistema
     */
    @SuppressWarnings("UseSpecificCatch")
    public void validaUsuario() {
        String msg = null;
        try {
            // Cargando la conexion de BD
            /*ejecutaConsultas.cargaConexion();

             // Indicando que no cierre la conexion de la base de datos
             ejecutaConsultas.setCerrarConexion(false);

             // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
             ejecutaConsultas.autoCommit(false);

             ejecutaConsultas.setQuery("SELECT * FROM mks_ahorros.tipo_producto where eliminado = :eliminado");
             ejecutaConsultas.setListParametrosEntrada(new ArrayList<Object[]>());
             ejecutaConsultas.getListParametrosEntrada().add(new Object[]{"eliminado", 'N'});
             ejecutaConsultas.ejecutaQuery();
            
             ejecutaConsultas.cerrarConexion();
             ejecutaConsultas.setConexionBD(null);*/

            List<CaracterNoAcpetadoContrasena> listaCaracterNoAcpetadoContrasena
                    = ejbFacadeCarNoAceCon.getItemsEliminado('N');
            for (CaracterNoAcpetadoContrasena cnac : listaCaracterNoAcpetadoContrasena) {
                if (this.contrasena.lastIndexOf(cnac.getCaracter()) != -1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContraseñaInaceptable");

                }
            }

            List<PalabraNoAceptadaContrasena> listaPalabraNoAceptadaContrasena
                    = this.ejbFacadePalNoAceCon.getItemsEliminado('N');

            for (PalabraNoAceptadaContrasena pnac : listaPalabraNoAceptadaContrasena) {
                if (this.contrasena.toUpperCase().lastIndexOf(pnac.getPalabra().toUpperCase()) != -1) {
                    msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContraseñaInaceptable");
                }
            }

            if (msg != null) {
                MuestraMensaje.addAdvertencia(msg);
                return;
            }
            ActivacionUsuario.setCambiarContrasena(false);
            // Validando credenciales del Usuario            
            List<Usuario> listUsuario = this.ejbFacade.getUsuario(this.getUsername());
            if (listUsuario.isEmpty()) {
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoExiste"));
                return;
            }

            UsuarioSistema usuarioSistema;
            // Consultando si el usuario esta habilitado para trabajar en una sola IFIP
            if (listUsuario.size() == 1) {
                // Colocando la Entidad del Usuario
                this.setUsuario((Usuario) listUsuario.get(0));

                // Validando si el usuario esta asignado al SISTEMA MINKASOFTWARE
                //List<UsuarioSistema> listUsuarioSistema = ejbFacade.getUsuarioSistema(getUsuario(), this.contrasena);
                UsuarioSistemaPK usuarioSistemaPK = new UsuarioSistemaPK();
                usuarioSistemaPK.setCodigoSistema(Long.parseLong("2"));
                usuarioSistemaPK.setCodigoUsuario(this.getUsuario().getCodigo());
                usuarioSistema = ejbFacadeUsuarioSistema.find(usuarioSistemaPK);
                if (usuarioSistema == null) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoExiste"));
                    return;
                }

                this.periodoContable = null;
                List<PeriodoContable> listaPeriodoContable = this.ejbFacadePeriodoContable.getItemPeriodoContable('S');
                if (!listaPeriodoContable.isEmpty()) {
                    if (listaPeriodoContable.size() == 1) {
                        this.periodoContable = listaPeriodoContable.get(0);
                    }
                }

                if (this.periodoContable == null) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("NoExistePeriodoConableActual"));
                    return;
                }

                //Verificando el Estado del Usuario
                String estadoUsuario = String.valueOf(usuarioSistema.getEstado());
                if (estadoUsuario.equals("T")) {
                    this.muestraDialogoCambioContrasenaTemporal();
                    return;
                }
                // Colocando el tiempo de inactividad que tiene el sistema
                Sesion.tiempoInactividad(100);

                // Consultando si el usuario esta asignado una sola vez al sistema y a la IFIP que tenga la contraseña correcta
                if (usuarioSistema.getContrasena().equals(Sesion.MD5(this.contrasena))) {
                    // Obteniendo los datos del Usuario en el Sistema asignado                   

                    if (estadoUsuario.equals("V") && !Validaciones.validaFechaIgualHoy(usuarioSistema.getFechaCaducaContrasena())) {

                        // Iniciando la variable de session con los datos del usuario mediante la entidad.                      
                        ActivacionUsuario.setUsuario(this.getUsuario());
                        ActivacionUsuario.setCodigoIfip(this.getUsuario().getCodigoIfip().getCodigo());
                        ActivacionUsuario.setCodigoUsuario(this.getUsuario().getCodigo());
                        ActivacionUsuario.setCodigoPeriodo(periodoContable.getCodigo());

                        //Colocando el codigo del computador al cual accede
                        List<Computador> listaComputador = this.ejbFacadeComputador.getItemsDireccionIpIfipAgenciaEliminado(this.usuario.getCodigoIfip().getCodigo(), ObtieneInformacionCliente.obtenerDireccionIP(), 'N');
                        //System.out.println("Computador " + listaComputador);
                        System.out.println("IP " + ObtieneInformacionCliente.obtenerDireccionIP());
                        if (listaComputador.size() == 1) {
                            ActivacionUsuario.setCodigoComputador(listaComputador.get(0).getCodigo());
                            ActivacionUsuario.setCodigoTipoSistemaOperativo(listaComputador.get(0).getCodigoTipoSistemaOperativo().getCodigo());
                            ActivacionUsuario.setRutaImpresora(listaComputador.get(0).getRutaImpresora());
                        }

                        // Inserta  el Acceso al Sistema
                        this.usuarioAccesoSistema = new UsuarioAccesoSistema();
                        this.usuarioAccesoSistema.setUsuarioSistema(usuarioSistema);
                        this.usuarioAccesoSistema.setDireccionIp(ObtieneInformacionCliente.obtenerDireccionIP());
                        this.usuarioAccesoSistema.setFechaAcceso(new Date());
                        this.usuarioAccesoSistema.setCodigoUsuario(usuarioSistema.getUsuarioSistemaPK().getCodigoUsuario());
                        this.usuarioAccesoSistema.setCodigoSistema(usuarioSistema.getUsuarioSistemaPK().getCodigoSistema());

                        //Obteniendo datos del servidor
                       
                        // Tipo Sistema Operativo Servidor de Aplicaciones
                        ParametroServidorIfip psi = this.ejbFacadeParametroServidorIfip.find(new ParametroServidorIfipPK(3L, ActivacionUsuario.getCodigoIfip()));
                        if (psi != null) {
                            ActivacionUsuario.setTipoSOServidorAppl(psi.getValor());
                        } else {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DefinaTipoServidor"));
                            this.username = null;
                            this.contrasena = null;
                            Sesion.invalidaSesion();
                            return;
                        }
                        // Ruta para crear el archivo para enviar a imprimir en las libretas
                        psi = this.ejbFacadeParametroServidorIfip.find(new ParametroServidorIfipPK(5L, ActivacionUsuario.getCodigoIfip()));
                        if (psi != null) {
                            ActivacionUsuario.setRutaImpresionArchivos(psi.getValor());
                        } else {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DefinaRutaCrearanArchivos"));
                            Sesion.invalidaSesion();
                            this.username = null;
                            this.contrasena = null;
                            return;
                        }

                        // Ruta subir los archivos
                        psi = this.ejbFacadeParametroServidorIfip.find(new ParametroServidorIfipPK(6L, ActivacionUsuario.getCodigoIfip()));
                        if (psi != null) {
                            ActivacionUsuario.setRutaSubidaArchivos(psi.getValor());
                        } else {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DefinaRutaSubirArchivos"));
                            Sesion.invalidaSesion();
                            this.username = null;
                            this.contrasena = null;
                            return;
                        }

                        // Ruta del arhivo de configuracion del servidor
                        psi = this.ejbFacadeParametroServidorIfip.find(new ParametroServidorIfipPK(9L, ActivacionUsuario.getCodigoIfip()));
                        if (psi != null) {
                           /* String path = new File(".").getAbsolutePath().trim();//.substring(0, new File(".").getAbsolutePath().lastIndexOf("bin")-1)+"/"+psi.getValor();
                            //System.err.println("Path 1 : " + path);
                            if (ActivacionUsuario.getTipoSOServidorAppl().trim().toUpperCase().equals("WINDOWS")) {
                                path = path.substring(0, path.lastIndexOf("bin") - 1) + "/" + psi.getValor();
                            } else {
                                path = path.substring(0, path.lastIndexOf("standalone") - 1) + "/" + psi.getValor();
                            }*/
                            System.err.println("Path 2: " + psi.getValor());
                            ActivacionUsuario.setRutaArchivoConfiguracionServidor(psi.getValor());
                        } else {
                            MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("DefinaRutaArchivoConfiguracionServidor"));
                            Sesion.invalidaSesion();
                            this.username = null;
                            this.contrasena = null;
                            return;
                        }

                        // Registrando el acceso al sistema
                        ejbFacadeUsuAcc.create(usuarioAccesoSistema);

                        // coloca la fecha de Acceso al Sistema
                        this.setFecha(this.getUsuarioAccesoSistema().getFechaAcceso());

                        //Colocando el codigo del acceso al sistema
                        ActivacionUsuario.setCodigoAccesoSistema(this.getUsuarioAccesoSistema().getCodigo());

                        String so = System.getProperty("os.name");
                        ActivacionUsuario.setTipoSOServidorAppl((so.toUpperCase().contains("LIN")) ? "LINUX" : "WINDOWS");

                        //System.out.println("Sistema Operataviot "+ActivacionUsuario.getTipoSOServidorAppl());
                        //Accediendo al Menu
                        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlAgenciaMKS");
                        Sesion.redireccionaPagina(url);                        
                        ActivacionUsuario.setServicio(configuracionServicio.generaServiciosAplicacion());                        
                        ActivacionUsuario.setCatalogoAutorizacion(configuracionServicio.generaCatalogoAutorizacion());                        
                        ActivacionUsuario.setCatalogoHeader(configuracionServicio.generaCatalogoHeaders());                          
                        // revisa si lo sprocesos se ejecutaron
                        this.revisaEjecucionProcesos();

                        // Si la contraseña ha caducado
                    } else if (estadoUsuario.equals("V") && Validaciones.validaFechaIgualHoy(usuarioSistema.getFechaCaducaContrasena())) {
                        // Actualizando el Estado de la Contraseña a Caducada
                        usuarioSistema.setEstado('C');
                        this.ejbFacadeUsuarioSistema.edit(usuarioSistema);
                        this.mensajeDialogoCambioContrasena = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaCaducada");
                        this.muestraDialogoCambioContrasena();
                        // Si el usuario está Eliminado en el Sistema    
                    } else if (estadoUsuario.equals("E")) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioNoExiste"));
                        // Si el Usuario tiene la Cuenta Bloqueada
                    } else if (estadoUsuario.equals("B")) {
                        MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioCuentaBloqueada"));
                        // Si la Contraseña del Usuario esta Expirada o Caducada
                    } else if (estadoUsuario.equals("X") || estadoUsuario.equals("C")) {
                        this.mensajeDialogoCambioContrasena = ((estadoUsuario.equals("X"))
                                ? ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaExpirada")
                                : ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaCaducada"));
                        ////System.out.println("Muestra Cambio Contraseña "+this.mensajeDialogoCambioContrasena);
                        this.muestraDialogoCambioContrasena();
                    }
                } else if (estadoUsuario.equals("B")) {
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioCuentaBloqueada"));
                } else {
                    // Muestra el Mensaje de Advertencia
                    MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioIncorrecto"));
                    this.numeroIntentos++;
                    // Obteniendo los parametros generales de la IFIP para encontrar el numero de Intentos de Acceso al Sistema
                    List<ParametroGeneralSegIfi> listParametroGeneralSegIfi
                            = ejbFacadeParametroGeneralSegIfi.getItemsCodigoIfipCodigoParametro(this.getUsuario().getCodigoIfip().getCodigo(), Long.parseLong("4"));
                    ////System.out.println("listParametroGeneralSegIfi "+listParametroGeneralSegIfi);
                    if (listParametroGeneralSegIfi.size() == 1) {
                        char bloqueoCuenta = 'N';
                        RequestContext context = RequestContext.getCurrentInstance();
                        if (this.numeroIntentos == Integer.parseInt(listParametroGeneralSegIfi.get(0).getValorNumerico().toString())) {
                            this.mensajeDialogoCambioContrasena = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioBloqueoCuenta");

                            usuarioSistema.setEstado('B');
                            this.ejbFacadeUsuarioSistema.edit(usuarioSistema);

                            BloqueoCuentaUsuario bcu = new BloqueoCuentaUsuario();
                            bcu.setUsuarioSistema(usuarioSistema);
                            bcu.setBloqueadoPor(usuarioSistema.getUsuario());
                            bcu.setCodigoUsuario(usuarioSistema.getUsuario().getCodigo());
                            bcu.setCodigoSistema(usuarioSistema.getSistema().getCodigo());
                            bcu.setFechaBloqueo(new Date());
                            bcu.setObservaciones("Usuario Bloqueo su Cuenta".toUpperCase());
                            bcu.setVigente('S');
                            bcu.setCodigoMotivoBloqueo(ejbFacadeMotivoBloqueoCuenta.find(Long.parseLong("1")));
                            bloqueoCuenta = 'S';

                            this.ejbFacadeBloqueoCuentaUsuario.create(bcu);

                            context.execute("BloqueoCuentaDialogo.show()");

                            ////System.out.println("Mostro BloqueoCuentaDialogo");
                        } else if (this.numeroIntentos == Integer.parseInt(listParametroGeneralSegIfi.get(0).getValorNumerico().toString()) - 1) {
                            this.mensajeDialogoCambioContrasena = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ProximoIntentoFallidoBloqueaCuenta");
                            context.execute("InformacionDialogo.show()");
                            ////System.out.println("Mostro Dialgo en 2");
                        }

                        IntentoFallidoAccesoSistema ifas = new IntentoFallidoAccesoSistema();
                        ifas.setBloqueoCuenta(bloqueoCuenta);
                        ifas.setContrasenaUsada(contrasena);
                        ifas.setFechaIntento(new Date());
                        ifas.setUsuarioSistema(usuarioSistema);
                        ifas.setCodigoUsuario(usuarioSistema.getUsuario().getCodigo());
                        ifas.setCodigoSistema(usuarioSistema.getSistema().getCodigo());

                        this.ejbFacadeIntentoFallidoAccesoSistema.create(ifas);
                    }
                }
            } else {
                // Muestra el Mensaje de Advertencia
                MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("UsuarioRegistradoMasUnaIfip"));
            }

        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"usuarioController", "validaUsuario", CapturaError.getErrorException(ex)});
        }

    }

    /**
     *
     */
    public void muestraDialogoCambioContrasenaTemporal() {
        try {
            RequestContext.getCurrentInstance().execute("procesandoDlg.hide()");
            RequestContext.getCurrentInstance().execute("CambioContrasenaTemporalDialogo.show()");
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"usuarioController", "muestraDialogoCambioContrasenaTemporal", CapturaError.getErrorException(ex)});
        }
    }

    /**
     *
     * @return
     */
    public boolean validaClaveTemporal() {
        try {
            if (claveTemporal == null) {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarClaveTemporal"));
                return false;
            } else {
                if (claveTemporal.length() <= 0) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarClaveTemporal"));
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"usuarioController", "validaClaveTemporal", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param muestraMensaje
     * @param actualizaClaveConfirmacion
     * @return
     */
    public boolean validaComplejidadClave(java.lang.Boolean muestraMensaje, java.lang.Boolean actualizaClaveConfirmacion) {
        try {
            if (actualizaClaveConfirmacion) {
                claveConfirmacion = "";
            }
            focoDialogoCambioContrasenaTemporal = "claveNueva";
            if (claveNueva == null) {
                if (muestraMensaje) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarNuevaClave"));
                }
                return false;
            } else {
                if (claveNueva.length() <= 0) {
                    if (muestraMensaje) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarNuevaClave"));
                    }
                    return false;
                }
            }
            boolean complejidadContrasena = false;
            String mensajeComplejidad = null;
            if (Validaciones.contrasenaComplejidadBaja(claveNueva)) {
                complejidadContrasena = false;
                mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadBaja");
            } else if (Validaciones.contrasenaComplejidadMedia(claveNueva)) {
                complejidadContrasena = true;
                mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadMedia");
            } else if (Validaciones.contrasenaComplejidadAlta(claveNueva)) {
                complejidadContrasena = true;
                mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SeguridadAlta");;
            } else if (mensajeComplejidad == null) {
                mensajeComplejidad = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaInsegura");;
            }
            if (muestraMensaje) {
                if (!complejidadContrasena) {
                    claveConfirmacion = "";
                }
                if (mensajeComplejidad != null && !complejidadContrasena) {
                    MuestraMensaje.addError(mensajeComplejidad);
                } else {
                    focoDialogoCambioContrasenaTemporal = "claveConfirma";
                }
            }
            return complejidadContrasena;
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"usuarioController", "validaComplejidadContrasena", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     * @param muestraMensaje
     * @return
     */
    public boolean validaCoincidenciaClave(java.lang.Boolean muestraMensaje) {
        try {
            focoDialogoCambioContrasenaTemporal = "claveConfirma";
            if (claveNueva == null) {
                if (muestraMensaje) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarNuevaClave"));
                }
                return false;
            } else {
                if (claveNueva.length() <= 0) {
                    if (muestraMensaje) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarNuevaClave"));
                    }
                    return false;
                }
            }
            if (claveConfirmacion == null) {
                if (muestraMensaje) {
                    MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarConfirmacionClave"));
                }
                return false;
            } else {
                if (claveConfirmacion.length() <= 0) {
                    if (muestraMensaje) {
                        MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("IngresarConfirmacionClave"));
                    }
                    return false;
                }
            }
            if (claveNueva.compareTo(claveConfirmacion) == 0) {
                focoDialogoCambioContrasenaTemporal = "cambiarClaveBot";
                return true;
            } else {
                MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ClaveNoCoincide"));
                return false;
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"usuarioController", "validaCoincidenciaClave", CapturaError.getErrorException(ex)});
            return false;
        }
    }

    /**
     *
     */
    public void cambiaContrasenaTemporal() {
        try {
            if (!validaClaveTemporal()) {
                return;
            }
            if (!validaComplejidadClave(true, false)) {
                return;
            }
            if (!validaCoincidenciaClave(true)) {
                return;
            }
            llamaSP.cargaConexion();
            llamaSP.setCerrarConexion(false);
            llamaSP.autoCommit(false);
            llamaSP.setNombreSP("mks_seguridades.pkm_usuario_clave_temporal.p_cambia_clave_tem_usu");
            llamaSP.setNumeroParametros(6);
            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_ifip", 1});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario", usuario.getCodigo()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_username", usuario.getUsername()});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_codigo_usuario_gen", 1});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_contrasena_tem", Utilidades.getMd5DesdeCadena(claveTemporal)});
            llamaSP.getListParametrosEntrada().add(new Object[]{"pt_contrasena_nue", Utilidades.getMd5DesdeCadena(claveNueva)});
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.invocaSP();
            if (llamaSP.isEjecucionCorrecta()) {
                llamaSP.commit();
                MuestraMensaje.addSatisfactorio(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("SatisfactorioCambioClave"));
                RequestContext.getCurrentInstance().execute("PF('CambioContrasenaTemporalDialogo').hide()");
            } else {
                if (llamaSP.getConexionBD() != null) {
                    llamaSP.rollback();
                }
                //MuestraMensaje.addError(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorCambioClave"));
            }
        } catch (Exception ex) {
            MuestraMensaje.addErrorCapaControl();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"usuarioController", "cambiaContrasenaTemporal", CapturaError.getErrorException(ex)});
            if (llamaSP.getConexionBD() != null) {
                llamaSP.rollback();
            }
        } finally {
            if (llamaSP.getConexionBD() != null) {
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);
            }
        }
    }

    public void muestraDialogoCambioContrasena() throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("CambioContrasenaDialogo.show()");

    }

    public void cambiaContrasena() throws UnknownHostException, IOException {
        // Iniciando la variable de session con los datos del usuario mediante la entidad.                      
        ActivacionUsuario.setUsuario(this.getUsuario());
        ActivacionUsuario.setCodigoIfip(this.getUsuario().getCodigoIfip().getCodigo());
        ActivacionUsuario.setCodigoUsuario(this.getUsuario().getCodigo());
        ActivacionUsuario.setCambiarContrasena(true);

        //Colocando el codigo del computador al cual accede
        List<Computador> listaComputador = this.ejbFacadeComputador.getItemsDireccionIpIfipAgenciaEliminado(this.usuario.getCodigoIfip().getCodigo(), ObtieneInformacionCliente.obtenerDireccionIP(), 'N');
        if (listaComputador.size() == 1) {
            ActivacionUsuario.setCodigoComputador(listaComputador.get(0).getCodigo());
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("CambioContrasenaDialogo.hide()");

        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlCambiaContrasena");
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    /**
     * Cierra la sesion del Sistema MINKASOFTWARE
     *
     * @throws IOException
     * @throws ServletException
     */
    public void cerrarSesion() throws IOException, ServletException {
        Sesion.cerrarSesion();
        this.init();
    }

    /**
     * Invalidano la Session
     *
     * @throws IOException
     * @throws ServletException
     */
    public void invalidaSesion() throws IOException, ServletException {
        Sesion.invalidaSesion();
        this.init();
    }

    /**
     * Validando la Sesion del Usuario
     *
     * @throws IOException
     */
    public void validaSesion() throws IOException {
        Sesion.validaSesion();
    }

    public boolean validaInicioSesion() {
        return this.getUsuario() != null;

    }

    public boolean habilitaPanel() {
        String msg = null;
        if (this.ejbFacadePersona.getPersona(persona.getIdentificacion()) != null) {
            habilitaPanelUsuario = false;
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("PersonaExiste");
            MuestraMensaje.addAdvertencia(msg);
        } else {
            habilitaPanelUsuario = true;
        }

        return habilitaPanelUsuario;

    }

    public boolean validaIdentificacion() {
        return this.persona.getIdentificacion() != null;
    }

    // --------------------------------------------------------------------------
    // --  REVISA LA EJECUCIIN DE PROCESOS
    private void revisaEjecucionProcesos() {
        //context = RequestContext.getCurrentInstance();

        try {
            //System.out.println("Guarda El retiro en cheque");

            context.execute("procesandoDlg.show()");
            // Cargando la conexion de BD
            llamaSP.cargaConexion();

            // Indicando que no cierre la conexion de la base de datos
            llamaSP.setCerrarConexion(false);

            // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
            llamaSP.autoCommit(false);

            // Guardando el Fondeo de Caja
            llamaSP.setNombreSP("mks_procesos_batch.pka_procesos_batch.p_revisa_ejecucion_total_proce");
            llamaSP.setNumeroParametros(1);

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());

            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());
            llamaSP.getListParametrosSalida().add(new Object[]{"pv_mensaje", Types.VARCHAR});

            // Invocando al SP
            llamaSP.invocaSP();
            // Verificando que la ejecucion del proceso ha sido correcta
            if (llamaSP.isEjecucionCorrecta()) {
                // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                llamaSP.commit();
                llamaSP.cerrarConexion();
                llamaSP.setConexionBD(null);

                if (llamaSP.getListResultado().get(0) != null) {
                    if (!llamaSP.getListResultado().get(0).toString().trim().equals("")) {
                        this.mensaje = llamaSP.getListResultado().get(0).toString();
                        MuestraMensaje.addAdvertencia(mensaje);

                    }
                }

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

    public void creaUsuario() {

    }

    public void validaCorreoEletronico() {

    }

    public void generaUsername() {

    }

    // FIN METODOS DE LOGICA DE NEGOCIO  
    // --------------------------------------------------------------------------
    // --------------------------------------------------------------------------
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // -- FIN DE ENCAPSULAMIENTO PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
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
     * @return the habilitaPanelUsuario
     */
    public boolean isHabilitaPanelUsuario() {
        return habilitaPanelUsuario;
    }

    /**
     * @param habilitaPanelUsuario the habilitaPanelUsuario to set
     */
    public void setHabilitaPanelUsuario(boolean habilitaPanelUsuario) {
        this.habilitaPanelUsuario = habilitaPanelUsuario;
    }

    /**
     * @return the mensajeDialogoCambioContrasena
     */
    public String getMensajeDialogoCambioContrasena() {
        return mensajeDialogoCambioContrasena;
    }

    /**
     * @param mensajeDialogoCambioContrasena the mensajeDialogoCambioContrasena
     * to set
     */
    public void setMensajeDialogoCambioContrasena(String mensajeDialogoCambioContrasena) {
        this.mensajeDialogoCambioContrasena = mensajeDialogoCambioContrasena;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the periodoContable
     */
    public PeriodoContable getPeriodoContable() {
        return periodoContable;
    }

    /**
     * @param periodoContable the periodoContable to set
     */
    public void setPeriodoContable(PeriodoContable periodoContable) {
        this.periodoContable = periodoContable;
    }

    /**
     * @return the claveTemporal
     */
    public String getClaveTemporal() {
        return claveTemporal;
    }

    /**
     * @param claveTemporal the claveTemporal to set
     */
    public void setClaveTemporal(String claveTemporal) {
        this.claveTemporal = claveTemporal;
    }

    /**
     * @return the claveNueva
     */
    public String getClaveNueva() {
        return claveNueva;
    }

    /**
     * @param claveNueva the claveNueva to set
     */
    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    /**
     * @return the claveConfirmacion
     */
    public String getClaveConfirmacion() {
        return claveConfirmacion;
    }

    /**
     * @param claveConfirmacion the claveConfirmacion to set
     */
    public void setClaveConfirmacion(String claveConfirmacion) {
        this.claveConfirmacion = claveConfirmacion;
    }

    /**
     * @return the focoDialogoCambioContrasenaTemporal
     */
    public String getFocoDialogoCambioContrasenaTemporal() {
        return focoDialogoCambioContrasenaTemporal;
    }

    /**
     * @param focoDialogoCambioContrasenaTemporal the
     * focoDialogoCambioContrasenaTemporal to set
     */
    public void setFocoDialogoCambioContrasenaTemporal(String focoDialogoCambioContrasenaTemporal) {
        this.focoDialogoCambioContrasenaTemporal = focoDialogoCambioContrasenaTemporal;
    }
}// FIN DE LA CLASE
