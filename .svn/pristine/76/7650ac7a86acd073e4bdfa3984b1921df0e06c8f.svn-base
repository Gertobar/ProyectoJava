package ec.renafipse.mks.capas.cliente;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Objeto que permite obtener informacion del navegador, S.O y otros .
 *
 * @author juan pablo Japa juan.japal@hotmail.com
 *
 */
public class Navegador extends HttpServlet {

    @SuppressWarnings("compatibility:-3349372871696841482")
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String version;
    private String sistemaOperativo;
    private String ip;
    private String userAgente;

    private DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

    private transient HttpServletRequest request;
    private transient HttpSession session;

    /**
     * Constructor que permite inicilizar variables.
     *
     * @param request
     * @param session
     */
    public Navegador(HttpServletRequest request, HttpSession session) {
        this.request = request;
        this.session = session;
        this.setUserAgente(getRequest().getHeader("User-Agent"));
        this.inicializar();
    }

    /**
     * Metodo que permite inicializar.
     *
     */
    public void inicializar() {
        this.nombreBrowser();
        this.sistemaOperativo();
        this.versionBrowser();
        this.clienteIp();
    }

    private void nombreBrowser() {
        if (getUserAgente().indexOf("MSIE") != -1) {
            setNombre("Microsoft Internet Explorer");
        } else if (getUserAgente().indexOf("Chrome") != -1) {
            setNombre("Google Chrome ");
        } else if (getUserAgente().indexOf("Firefox") != -1) {
            setNombre("Mozilla Firefox");
        } else {
            setNombre("Desconocido");
        }
    }

    private void sistemaOperativo() {
        if (getUserAgente().toLowerCase().indexOf("windows") >= 0) {
            setSistemaOperativo("Windows");
        } else if (getUserAgente().toLowerCase().indexOf("mac") >= 0) {
            setSistemaOperativo("Mac");
        } else if (getUserAgente().toLowerCase().indexOf("x11") >= 0) {
            setSistemaOperativo("Unix");
        } else if (getUserAgente().toLowerCase().indexOf("android") >= 0) {
            setSistemaOperativo("Android");
        } else if (getUserAgente().toLowerCase().indexOf("iphone") >= 0) {
            setSistemaOperativo("IPhone");
        } else {
            setSistemaOperativo("Desconocido");
        }
    }

    private void versionBrowser() {
        int tmpPos;
        String tmpString;
        if (this.getNombre() == "Microsoft Internet Explorer") {
            String str = this.getUserAgente().substring(this.getUserAgente().indexOf("msie") + 5);
            setVersion(str.substring(0, str.indexOf(";")));
        } else {
            tmpString
                    = (this.getUserAgente().substring(tmpPos = (this.getUserAgente().indexOf("/")) + 1,
                            tmpPos + this.getUserAgente().indexOf(" "))).trim();
            setVersion(tmpString.substring(0, tmpString.indexOf(" ")));
        }
    }

    private void clienteIp() {
       // String ip;
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ip = getRequest().getHeader("X-Forwarded-For");
        //System.out.println("HTTP_CLIENT_IP " + request.getHeader("HTTP_CLIENT_IP"));
        //System.out.println("Proxy-Client-IP " + request.getHeader("Proxy-Client-IP".toUpperCase()));
        //System.out.println("WL-Proxy-Client-IP " + request.getHeader("WL-Proxy-Client-IP".toUpperCase()));
        //System.out.println("HTTP_X_FORWARDED_FOR " + request.getHeader("HTTP_X_FORWARDED_FOR"));
        //System.out.println("VIA " + request.getHeader("VIA"));
        //System.out.println("X-Forwarded-For " + request.getHeader("X-HTTP_X_FORWARDED_FOR-For".toUpperCase()));

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            setIp(getRequest().getHeader("Proxy-Client-IP"));
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            setIp(getRequest().getHeader("WL-Proxy-Client-IP"));
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            setIp(getRequest().getHeader("HTTP_CLIENT_IP"));
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            setIp(getRequest().getHeader("HTTP_X_FORWARDED_FOR"));
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            setIp(getRequest().getRemoteAddr());
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            this.setIp(request.getHeader("VIA"));

        }
        if (ip == null || ip.length() == 0) {
            //request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String remoteAddr = request.getRemoteAddr();            
            try {
                InetAddress inetAddress = InetAddress.getByName(remoteAddr);
                this.setIp(inetAddress.getHostAddress());
            } catch (UnknownHostException ex) {              
                Logger.getLogger(Navegador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * @return
     */
    public String getSessionId() {
        return session.getId();
    }

    /**
     * Metodo que permite obtener el tiempo de creacion del session.
     *
     * @return
     */
    public String getSessionCreate() {
        return formatter.format(session.getCreationTime());
    }

    /**
     * Metodo que permite obtener el LastAccessed.
     *
     * @return
     */
    public String getLastAccessed() {
        return formatter.format(session.getLastAccessedTime());
    }

    /**
     * Metodo que permite obtener el protocolo.
     *
     * @return
     */
    public String getProtocolo() {
        return request.getProtocol();
    }

    /**
     * Metodo que Permite obtener el cookie.
     *
     * @return
     */
    public String getCookie() {
        return request.getHeader("cookie");
    }

    /**
     * Metodo que permite obtener el nombre.
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite ingresar el nombre del browser.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que Permite obtener la version del browser.
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * Metodo que permite ingresar el numero de version del browser.
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Metodo que permite obtener el host.
     *
     * @return
     */
    public String getHost() {
        return request.getHeader("Host");
    }

    /**
     * Metodo que permite Obtener el sistema Operativo.
     *
     * @return
     */
    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    /**
     * Metodo que permite ingresar el nombre del sistema operativo.
     *
     * @param sistemaOperativo
     */
    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    /**
     * Metodo que permite obtener el nombre del lenguaje.
     *
     * @return
     */
    public String getLenguaje() {
        return request.getHeader("Accept-Language");
    }

    /**
     * Metodo que permite obtener la direcci�n Ip.
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * Metodo que permite ingresar la direcci�n ip.
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Metodo que permite obtener el UserAgente.
     *
     * @return
     */
    public String getUserAgente() {
        return userAgente;
    }

    /**
     * Metodo que permite obtener ingresar userAgente.
     *
     * @param userAgente
     */
    public void setUserAgente(String userAgente) {
        this.userAgente = userAgente;
    }

    /**
     * Metodo que permite obtener el Request.
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Metodo que permite ingresar el request.
     *
     * @param request
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Metodo que permite obtener el session.
     *
     * @return
     */
    public HttpSession getSession() {
        return session;
    }

    /**
     * Metodo que permite hacer ingreso del session.
     *
     * @param session
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }
}
