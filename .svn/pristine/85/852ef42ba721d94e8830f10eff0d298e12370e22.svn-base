package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.seguridades.BloqueoCuentaUsuario;
import ec.renafipse.mks.modelo.seguridades.ExpiracionContrasena;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistema;
import ec.renafipse.mks.modelo.seguridades.UsuarioSistemaPK;
import ec.renafipse.mks.negocio.seguridades.BloqueoCuentaUsuarioFacade;
import ec.renafipse.mks.negocio.seguridades.ExpiracionContrasenaFacade;
import ec.renafipse.mks.negocio.seguridades.UsuarioSistemaFacade;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean(name = "expiracionContrasenaController")
@ViewScoped
public class ExpiracionContrasenaController extends AbstractController<ExpiracionContrasena> implements Serializable {

    @EJB
    private ExpiracionContrasenaFacade ejbFacade;

    @EJB
    private UsuarioSistemaFacade ejbFacadeUsuSis;

    @EJB
    private BloqueoCuentaUsuarioFacade ejbFacadeBloCuenUsu;

    private ExpiracionContrasena expiracionContrasena;
    private BloqueoCuentaUsuario bloqueoCuencaUsuario;
    private UsuarioSistema usuarioSistema;
    private UsuarioSistema usuarioSistemaSel;
    private UsuarioSistemaPK usuSisPK;

    private List<ExpiracionContrasena> itemsExpiracionContrasena;
    private List<BloqueoCuentaUsuario> itemsBloqueoCuentaUsuario;
    private List<UsuarioSistema> itemsUsuSistema;

    private String criterio;
    private String msg;
    private String contrasenaAleatoria;

    public ExpiracionContrasenaController() {
        super(ExpiracionContrasena.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        setExpiracionContrasena(new ExpiracionContrasena());
        setUsuarioSistema(new UsuarioSistema());
        setBloqueoCuencaUsuario(new BloqueoCuentaUsuario());
        setUsuSisPK(new UsuarioSistemaPK());

        getUsuSisPK().setCodigoSistema(Long.parseLong("2"));
        getUsuSisPK().setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
        setUsuarioSistema(this.getEjbFacadeUsuSis().find(getUsuSisPK()));
        this.criterio = null;
        this.contrasenaAleatoria = null;
    }

    /**
     *
     * @param actionEvent
     */
    public void preparaExpiracion(ActionEvent actionEvent) {
        this.init();
    }

    /**
     * Metodo donde se obtiene los datos del usuario sistema en base del
     * criterio de la consulta
     */
    public void obtieneUsuariosSistema() {
        try {
            if (Validaciones.cumpleRequerimientoCampo(criterio, 4)) {
                this.setItemsUsuSistema(this.getEjbFacadeUsuSis().getItemsUsuariosExpCon(this.getCriterio(), ActivacionUsuario.getCodigoIfip(), getUsuSisPK().getCodigoSistema(), ActivacionUsuario.getCodigoUsuario()));
            } else {
                MuestraMensaje.addAdvertencia(Validaciones.msg);
                this.setItemsUsuSistema(null);
            }
        } catch (Exception ex) {
            // Muestra el Mensaje del Error en la Capa
            MuestraMensaje.addErrorCapaControl();
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"usuarioSistemaController", "obtieneUsuariosSistema", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Metodo que me permite expirar las contrasenas de los usuarios
     *
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public void expiraContrasena() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        msg = null;
        String estadoContrasenaUsu = null;
        //////System.out.println("CorreoElectronico: "+ this.usuarioSistemaSel.getUsuario().getCorreoElectronico());
        if (this.getUsuarioSistemaSel() != null) {
            estadoContrasenaUsu = String.valueOf(this.getUsuarioSistemaSel().getEstado());
            if (estadoContrasenaUsu.equals("V") || estadoContrasenaUsu.equals("X") || estadoContrasenaUsu.equals("C") || estadoContrasenaUsu.equals("B")) {

                getUsuarioSistemaSel().setEstado('X');
                setContrasenaAleatoria(String.valueOf(generaContrasenaNueva()));
                ////System.out.println("contrasenaSeteo: " + getContrasenaAleatoria());
                 ////System.out.println("contrasenaaa: " + Sesion.MD5(String.valueOf(generaContrasenaNueva())));
                getUsuarioSistemaSel().setContrasena(Sesion.MD5(String.valueOf(getContrasenaAleatoria())));
                getUsuarioSistemaSel().setFechaCambioContrasena(new Date());
                this.getEjbFacadeUsuSis().edit(getUsuarioSistemaSel());

                itemsExpiracionContrasena = this.getEjbFacade().getIemsExpiracionConUsuSisVig(getUsuarioSistemaSel(), 'S');
                if (itemsExpiracionContrasena != null) {
                    for (ExpiracionContrasena expc : itemsExpiracionContrasena) {
                        // ////System.out.println("lista: +" + itemsExpiracionContrasena);
                        expc.setVigente('N');
                        getEjbFacade().edit(expc);
                    }
                }
                //Creacion de una expiracion contrasena
                expiracionContrasena.setCodigoUsuario(this.getUsuarioSistemaSel().getUsuarioSistemaPK().getCodigoUsuario());
                expiracionContrasena.setCodigoSistema(this.getUsuarioSistemaSel().getUsuarioSistemaPK().getCodigoSistema());
                expiracionContrasena.setCodigoMotivoExpira(getExpiracionContrasena().getCodigoMotivoExpira());
                expiracionContrasena.setExpiradoPor(ActivacionUsuario.getUsuario()); 
                expiracionContrasena.setFechaExpiracion(new Date());
                expiracionContrasena.setVigente('S');
                expiracionContrasena.setObservaciones(getExpiracionContrasena().getObservaciones());
                getEjbFacade().create(expiracionContrasena);

                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaExpiradaCorrecta");
                enviarContrasenaMail();
            }
            try {
                if (msg != null) {

                    if (estadoContrasenaUsu.equals("B")) {
                        itemsBloqueoCuentaUsuario = this.getEjbFacadeBloCuenUsu().getIemsBloCuenUsuVig(getUsuarioSistemaSel(), 'S');
                        if (itemsBloqueoCuentaUsuario != null) {
                            for (BloqueoCuentaUsuario bcu : itemsBloqueoCuentaUsuario) {
                                //   ////System.out.println("lista: +" + itemsBloqueoCuentaUsuario);
                                bcu.setVigente('N');
                                this.getEjbFacadeBloCuenUsu().edit(bcu);
                            }
                        }
                    }
                } else {
                    if (estadoContrasenaUsu.equals("E")) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EstadoEliIncorrecto");
                        MuestraMensaje.addAdvertencia(msg);
                    }
                }
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    /**
     * Metodo para generar una contraseña nueva que consta de un numero
     * randomico de 7 digitos
     *
     * @return
     */
    public long generaContrasenaNueva() {
        long numAleatorio = 10000000L;
        long valorDado = (long) Math.floor(Math.random() * numAleatorio);
        ////System.out.println("contrasenaGenerada: " + valorDado);
        return valorDado;
    }

    /**
     * Metodo que permite enviar la nueva contraseña al mail del usuario por
     * motivo de expiracion
     */
    public void enviarContrasenaMail() {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "mail.renafipse.ec"); // 172.20.250.60 mail.renafipse.ec
            //props.setProperty("mail.smtp.ssl.trust", "enable");       
            // Puerto de renafipse para envio de correos
            props.setProperty("mail.smtp.port", "25");
            // Nombre del usuario
            props.setProperty("mail.smtp.user", "soportevimasif@renafipse.ec");
            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");
            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props, null);
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            // Quien envia el correo
            message.setFrom(new InternetAddress("soportevimasif@renafipse.ec"));
            // A quien va dirigido
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(this.getUsuarioSistemaSel().getUsuario().getCorreoElectronico()));
            //new InternetAddress("sistemas.rena@gmail.com"));
            //El asunto
            message.setSubject("Expiracion de su Clave");
            message.setSentDate(new Date());
            //Construccion del mensaje
            ////System.out.println("contraseñaMail: "+this.getContrasenaAleatoria());                     
            message.setText("         Username: " + this.getUsuarioSistemaSel().getUsuario().getUsername()+ "\n"
                    + " " + " Contrasena Nueva: " + this.getContrasenaAleatoria());
            
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("mail.renafipse.ec","soportevimasif@renafipse.ec", "soportevimasif123");
            t.sendMessage(message, message.getAllRecipients());
            // Cierre.
            t.close();
            MuestraMensaje.addSatisfactorio(msg);
        } catch (Exception e) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MailNoEnviado");
            MuestraMensaje.addAdvertencia(msg + this.getContrasenaAleatoria());
            e.printStackTrace();
        }
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
     * @return the ejbFacade
     */
    public ExpiracionContrasenaFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ExpiracionContrasenaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ejbFacadeUsuSis
     */
    public UsuarioSistemaFacade getEjbFacadeUsuSis() {
        return ejbFacadeUsuSis;
    }

    /**
     * @param ejbFacadeUsuSis the ejbFacadeUsuSis to set
     */
    public void setEjbFacadeUsuSis(UsuarioSistemaFacade ejbFacadeUsuSis) {
        this.ejbFacadeUsuSis = ejbFacadeUsuSis;
    }

    /**
     * @return the usuarioSistemaSel
     */
    public UsuarioSistema getUsuarioSistemaSel() {
        return usuarioSistemaSel;
    }

    /**
     * @param usuarioSistemaSel the usuarioSistemaSel to set
     */
    public void setUsuarioSistemaSel(UsuarioSistema usuarioSistemaSel) {
        this.usuarioSistemaSel = usuarioSistemaSel;
    }

    /**
     * @return the itemsUsuSistema
     */
    public List<UsuarioSistema> getItemsUsuSistema() {
        return itemsUsuSistema;
    }

    /**
     * @param itemsUsuSistema the itemsUsuSistema to set
     */
    public void setItemsUsuSistema(List<UsuarioSistema> itemsUsuSistema) {
        this.itemsUsuSistema = itemsUsuSistema;
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
     * @return the usuSisPK
     */
    public UsuarioSistemaPK getUsuSisPK() {
        return usuSisPK;
    }

    /**
     * @param usuSisPK the usuSisPK to set
     */
    public void setUsuSisPK(UsuarioSistemaPK usuSisPK) {
        this.usuSisPK = usuSisPK;
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
     * @return the bloqueoCuencaUsuario
     */
    public BloqueoCuentaUsuario getBloqueoCuencaUsuario() {
        return bloqueoCuencaUsuario;
    }

    /**
     * @param bloqueoCuencaUsuario the bloqueoCuencaUsuario to set
     */
    public void setBloqueoCuencaUsuario(BloqueoCuentaUsuario bloqueoCuencaUsuario) {
        this.bloqueoCuencaUsuario = bloqueoCuencaUsuario;
    }

    /**
     * @return the itemsBloqueoCuentaUsuario
     */
    public List<BloqueoCuentaUsuario> getItemsBloqueoCuentaUsuario() {
        return itemsBloqueoCuentaUsuario;
    }

    /**
     * @param itemsBloqueoCuentaUsuario the itemsBloqueoCuentaUsuario to set
     */
    public void setItemsBloqueoCuentaUsuario(List<BloqueoCuentaUsuario> itemsBloqueoCuentaUsuario) {
        this.itemsBloqueoCuentaUsuario = itemsBloqueoCuentaUsuario;
    }

    /**
     * @return the ejbFacadeBloCuenUsu
     */
    public BloqueoCuentaUsuarioFacade getEjbFacadeBloCuenUsu() {
        return ejbFacadeBloCuenUsu;
    }

    /**
     * @param ejbFacadeBloCuenUsu the ejbFacadeBloCuenUsu to set
     */
    public void setEjbFacadeBloCuenUsu(BloqueoCuentaUsuarioFacade ejbFacadeBloCuenUsu) {
        this.ejbFacadeBloCuenUsu = ejbFacadeBloCuenUsu;
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

    
    
    
}
