/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.cliente;

//import ec.renafipse.mks.capas.cliente.;
import ec.renafipse.mks.capas.cliente.InfoCPU;
import ec.renafipse.mks.capas.cliente.InfoNet;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vicastoc
 */
public class ObtieneInformacionCliente{

    public static String obtenerDireccionMAC() throws SocketException, UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();

        String macAddress = "";

        NetworkInterface network = NetworkInterface.getByInetAddress(ip);

        byte[] mac = network.getHardwareAddress();

        //System.out.print("Current MAC address : ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }

        ////System.out.println(sb.toString());
        macAddress = sb.toString();

        return null;
    }

    public static String obtenerNombreEquipo() {
        // String remoteUser = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteUser();
        String remoteNombreEquipo = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost();
        //////System.out.println("host: "+remoteNombreEquipo+" user "+remoteUser);
        return remoteNombreEquipo;
    }

    public static String obtenerDireccionIP() throws UnknownHostException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String remoteAddr = request.getRemoteAddr();
        Navegador browser = new Navegador(request,
                    request.getSession());
        return browser.getIp();
    }

}
