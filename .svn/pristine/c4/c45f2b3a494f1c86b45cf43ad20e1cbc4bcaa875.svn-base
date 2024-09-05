package ec.renafipse.mks.control.seguridades;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ec.mss.entidad.Catalogo;
import ec.mss.entidad.RespuestaServicio;
import ec.mss.web.consumo.autenticacion.AutenticacionServicio;
import ec.mss.web.consumo.catalogo.CatalogoServicio;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.sesion.Sesion;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia;
import ec.renafipse.mks.negocio.seguridades.UsuarioIfipAgenciaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.http.HttpStatus;

@ManagedBean(name = "usuarioIfipAgenciaController")
@SessionScoped
public class UsuarioIfipAgenciaController extends AbstractController<UsuarioIfipAgencia> implements Serializable {

    @EJB
    private UsuarioIfipAgenciaFacade ejbFacade;
    @EJB
    private AutenticacionServicio autenticacionServicio;
    @EJB
    private CatalogoServicio catalogoServicio;

    private String valor;
    private RespuestaServicio respuestaServicio;
    private JsonObject jsonObject;
    private JsonElement jsonElement;
    private Type type;
    private List<Catalogo> listaCatalogo;

    // --------------------------------------------------------------------------
    // -- PARAMETROS PERSONALIZADOS
    // Almacena la entidad de la agencia seleccionada por el usuario, 
    // variable usada en metodo listener de la ventana de angecias
    private UsuarioIfipAgencia usuarioIfipAgencia;
    private IfipAgencia ifipAgencia;
    private Usuario usuario;

    // -- FIN DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    public UsuarioIfipAgenciaController() {
        super(UsuarioIfipAgencia.class);
    }

    @PostConstruct
    public void init() {

        super.setFacade(ejbFacade);

    }

    @Override
    protected void setEmbeddableKeys() {
        //this.getSelected().getUsuarioIfipAgenciaPK().setCodigoIfipAgencia(this.getSelected().getIfipAgencia().getCodigo());
        this.getSelected().getUsuarioIfipAgenciaPK().setCodigoUsuario(this.getSelected().getUsuario().getCodigo());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setUsuarioIfipAgenciaPK(new ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgenciaPK());
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     * Metodo que devuelve los items de las agencias que el usuario tiene
     * asignado Usado en el datagrip de la venta /login/agencia.xhtml
     *
     * @return
     */
    public List<UsuarioIfipAgencia> getItemsPermisosIfiAgeUsu() {
        List<UsuarioIfipAgencia> listUsuarioIfipAgencia = this.ejbFacade.getItemsPermisosIfiAgeUsu(ActivacionUsuario.getCodigoUsuario(), ActivacionUsuario.getCodigoIfip());
        return listUsuarioIfipAgencia;

    }

    /**
     * Metodo que acceder en la ventana de modulos Usado al dar clic o acceder a
     * la agencia
     *
     * @throws IOException
     */
    public void irModulos() throws IOException {
        String url = ResourceBundle.getBundle("/propiedadesObjetosEC").getString("UrlModuloMKS");
        this.setIfipAgencia(this.getUsuarioIfipAgencia().getIfipAgencia());
        ActivacionUsuario.setCodigoIfipAgencia(this.getUsuarioIfipAgencia().getIfipAgencia().getCodigo());
        autenticaServicio();
        cargaCatalogoServicio();
        Sesion.tiempoInactividad((int) this.getUsuarioIfipAgencia().getCodigoPerfil().getTiempoInactividad());
        Sesion.redireccionaPagina(url);
    }
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------

    /**
     *
     */
    public void autenticaServicio() {
        try {
            jsonObject = null;
            valor = null;
            respuestaServicio = autenticacionServicio.autenticaAplicacion();
            jsonObject = respuestaServicio.getTrama();
            if (jsonObject != null) {
                valor = jsonObject.get("access_token").getAsString();
                ActivacionUsuario.setTokenServicio(valor);
                valor = jsonObject.get("refresh_token").getAsString();
                ActivacionUsuario.setTokenRefreshServicio(valor);
                valor = jsonObject.get("token_type").getAsString();
                ActivacionUsuario.setTokenTipo(valor);
            } else {
                MuestraMensaje.addError(respuestaServicio.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"UsuarioIfipAgenciaController", "autenticaServicio", CapturaError.getErrorException(ex)});
        }
    }
    
    /**
     * 
     */
    public void cargaCatalogoServicio() {
        try {
            respuestaServicio = catalogoServicio.catalogoAplicacion();
            if (respuestaServicio.getCodigo() != HttpStatus.OK && respuestaServicio.getCodigo() != HttpStatus.NO_CONTENT) {
                ActivacionUsuario.setCatalogo(null);
            } else {
                if (respuestaServicio.getCodigo() != HttpStatus.NO_CONTENT) {
                    jsonElement = respuestaServicio.getTrama().get("trama");
                    type = new TypeToken<List<Catalogo>>() {
                    }.getType();
                    listaCatalogo = new Gson().fromJson(jsonElement, type);
                    ActivacionUsuario.setCatalogo(listaCatalogo);
                } else {
                    ActivacionUsuario.setCatalogo(null);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"UsuarioIfipAgenciaController", "cargaCatalogoServicio", CapturaError.getErrorException(ex)});
        }
    }

    // --------------------------------------------------------------------------
    // -- ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    /**
     * @return the usuarioIfipAgencia
     */
    public UsuarioIfipAgencia getUsuarioIfipAgencia() {
        return usuarioIfipAgencia;
    }

    /**
     * @param usuarioIfipAgencia the usuarioIfipAgencia to set
     */
    public void setUsuarioIfipAgencia(UsuarioIfipAgencia usuarioIfipAgencia) {
        this.usuarioIfipAgencia = usuarioIfipAgencia;
    }

    // -- FIN DE ENCAPSULAMIENTO DE PARAMETROS PERSONALIZADOS
    // --------------------------------------------------------------------------
    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
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

}
