/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.cliente;

import java.util.ArrayList;

import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;

public class InfoSO {

    OperatingSystem os;

    public InfoSO() {
        os = OperatingSystem.getInstance();
    }

    public ArrayList<String> obtenerDatos() {
        ArrayList<String> datos = new ArrayList();
        datos.add("Descripcion: ");
        datos.add(os.getDescription());
        datos.add("Nombre: ");
        datos.add(os.getVendorName());
        datos.add("Version: ");
        datos.add(os.getVersion());
        datos.add("Arquitectura: ");
        datos.add(os.getArch());
        return datos;
    }
}
