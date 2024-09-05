package ec.renafipse.mks.control.seguridades;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "bloqueoCuentaUsuarioController")
@ViewScoped
public class BloqueoCuentaUsuarioController extends AbstractController<BloqueoCuentaUsuario> implements Serializable {

    @EJB
    private BloqueoCuentaUsuarioFacade ejbFacade;

    @EJB
    private UsuarioSistemaFacade ejbFacadeUsuSis;

    @EJB
    private ExpiracionContrasenaFacade ejbFacadeExpiraContra;

    private BloqueoCuentaUsuario bloqueoCuentaUsuario;
    private UsuarioSistema usuarioSistema;
    private UsuarioSistema usuarioSistemaSel;
    private UsuarioSistemaPK usuSisPK;
    private ExpiracionContrasena expiracionContrasena;

    private List<BloqueoCuentaUsuario> itemsBloqueoCuentaUsuario;
    private List<ExpiracionContrasena> itemsExpiracionContrasena;
    private List<UsuarioSistema> itemsUsuSistema;

    private String criterio;
    private String msg;

    public BloqueoCuentaUsuarioController() {
        super(BloqueoCuentaUsuario.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(getEjbFacade());
        setUsuarioSistema(new UsuarioSistema());
        setBloqueoCuentaUsuario(new BloqueoCuentaUsuario());
        setUsuSisPK(new UsuarioSistemaPK());

        getUsuSisPK().setCodigoSistema(Long.parseLong("2"));
        getUsuSisPK().setCodigoUsuario(ActivacionUsuario.getCodigoUsuario());
        setUsuarioSistema(this.getEjbFacadeUsuSis().find(getUsuSisPK()));
        this.setCriterio(null);
    }

    
        /**
     *
     * @param actionEvent
     */
    public void preparaBloqueo(ActionEvent actionEvent) {
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
                    new Object[]{"bloqueoCuentaUsuarioController", "obtieneUsuariosSistema", CapturaError.getErrorException(ex)});
        }
    }

    /**
     * Metodo que me permite bloquear la cuenta del usuario
     *
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public void bloquaCuentaUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        msg = null;
        String estadoContrasena = null;

        // setUsuarioSistemaSel(getUsuarioSistema());
        if (this.getUsuarioSistemaSel() != null) {
            estadoContrasena = String.valueOf(this.getUsuarioSistemaSel().getEstado());
            //    ////System.out.println("estado: " + estadoContrasena);
            if (estadoContrasena.equals("V") || estadoContrasena.equals("C")) {

                getUsuarioSistemaSel().setEstado('B');
                getUsuarioSistemaSel().setFechaCambioContrasena(new Date());
                this.getEjbFacadeUsuSis().edit(getUsuarioSistemaSel());

                itemsBloqueoCuentaUsuario = this.getEjbFacade().getIemsBloCuenUsuVig(getUsuarioSistemaSel(), 'S');
                if (itemsBloqueoCuentaUsuario != null) {
                    for (BloqueoCuentaUsuario bcu : itemsBloqueoCuentaUsuario) {
                        bcu.setVigente('N');
                        getEjbFacade().edit(bcu);
                    }
                }

                bloqueoCuentaUsuario.setCodigoUsuario(this.getUsuarioSistemaSel().getUsuarioSistemaPK().getCodigoUsuario());
                bloqueoCuentaUsuario.setCodigoSistema(this.getUsuarioSistemaSel().getUsuarioSistemaPK().getCodigoSistema());
                bloqueoCuentaUsuario.setCodigoMotivoBloqueo(getBloqueoCuentaUsuario().getCodigoMotivoBloqueo());
                bloqueoCuentaUsuario.setBloqueadoPor(ActivacionUsuario.getUsuario());
                bloqueoCuentaUsuario.setFechaBloqueo(new Date());
                bloqueoCuentaUsuario.setObservaciones(getBloqueoCuentaUsuario().getObservaciones());
                bloqueoCuentaUsuario.setVigente('S');
                this.getEjbFacade().create(bloqueoCuentaUsuario);

                msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ContrasenaBloqueadaCorrecta");               
            }
            try {
                if (msg == null) {
                    if (estadoContrasena.equals("X")) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EstadoExpIncorrecto");
                        MuestraMensaje.addAdvertencia(msg);
                    }
                    if (estadoContrasena.equals("B")) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EstadoBloIncorrecto");
                        MuestraMensaje.addAdvertencia(msg);
                    }
                    if (estadoContrasena.equals("E")) {
                        msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("EstadoEliIncorrecto");
                        MuestraMensaje.addAdvertencia(msg);
                    }
                }else{
                    MuestraMensaje.addSatisfactorio(msg);
                }
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
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
     * @return the ejbFacadeExpiraContra
     */
    public ExpiracionContrasenaFacade getEjbFacadeExpiraContra() {
        return ejbFacadeExpiraContra;
    }

    /**
     * @param ejbFacadeExpiraContra the ejbFacadeExpiraContra to set
     */
    public void setEjbFacadeExpiraContra(ExpiracionContrasenaFacade ejbFacadeExpiraContra) {
        this.ejbFacadeExpiraContra = ejbFacadeExpiraContra;
    }

    /**
     * @return the bloqueoCuentaUsuario
     */
    public BloqueoCuentaUsuario getBloqueoCuentaUsuario() {
        return bloqueoCuentaUsuario;
    }

    /**
     * @param bloqueoCuentaUsuario the bloqueoCuentaUsuario to set
     */
    public void setBloqueoCuentaUsuario(BloqueoCuentaUsuario bloqueoCuentaUsuario) {
        this.bloqueoCuentaUsuario = bloqueoCuentaUsuario;
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
     * @return the itemsExpiracionContrasena
     */
    public List<ExpiracionContrasena> getItemsExpiracionContrasena() {
        return itemsExpiracionContrasena;
    }

    /**
     * @param itemsExpiracionContrasena the itemsExpiracionContrasena to set
     */
    public void setItemsExpiracionContrasena(List<ExpiracionContrasena> itemsExpiracionContrasena) {
        this.itemsExpiracionContrasena = itemsExpiracionContrasena;
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
     * @return the ejbFacade
     */
    public BloqueoCuentaUsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(BloqueoCuentaUsuarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

}
