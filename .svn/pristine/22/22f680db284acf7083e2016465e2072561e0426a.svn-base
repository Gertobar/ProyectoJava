/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.cliente;

/**
 *
 * @author vastudillo
 */
import java.util.ArrayList;

import org.hyperic.sigar.NetInfo;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class InfoNet {

    Sigar s;
    NetInterfaceConfig net;
    NetInfo info;

    public InfoNet() {
        s = new Sigar();
        try {
            net = s.getNetInterfaceConfig(null);
            info = s.getNetInfo();
        } catch (SigarException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<String> obtenerDatos() {
        ArrayList<String> datos = new ArrayList();
        datos.add("IP primaria: ");
        datos.add(net.getAddress());
        datos.add("Mac primaria: ");
        datos.add(net.getHwaddr());
        datos.add("Host: ");
        datos.add(info.getHostName());
        return datos;
    }
}
