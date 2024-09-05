/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.cliente;

import java.util.ArrayList;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class InfoCPU {

    Sigar s;

    public InfoCPU() {
        s = new Sigar();
    }

    public ArrayList<String> obtenerDatos() {
        ArrayList<String> datos = new ArrayList();
        try {
            CpuInfo cpu[] = s.getCpuInfoList();
            CpuInfo info = cpu[0];
            datos.add("Vendedor: ");
            datos.add(info.getVendor());
            datos.add("Modelo: ");
            datos.add(info.getModel());
            datos.add("Mhz: ");
            datos.add("" + info.getMhz());
            if (info.getCacheSize() != Sigar.FIELD_NOTIMPL) {
                datos.add("Tamaño de Cache: ");
                datos.add("" + info.getCacheSize());
            }
            if ((info.getTotalCores() != info.getTotalSockets()) || (info.getCoresPerSocket() > info.getTotalCores())) {
                datos.add("CPU´s Fisicas: ");
                datos.add("" + info.getTotalSockets());
                datos.add("Nucleos por CPU: ");
                datos.add("" + info.getCoresPerSocket());
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return datos;
    }
}
