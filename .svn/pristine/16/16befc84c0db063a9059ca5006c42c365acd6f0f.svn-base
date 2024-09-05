/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.capas.cargas;

import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivo;
import ec.renafipse.mks.modelo.comunes.FormatoEntradaArchivoDet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;

/**
 *
 * @author andres
 */
@Singleton(name="cargaArchivoCsv")
public class CargaArchivoCsv {
    
    /**
     * 
     * @param nombreCompletoArchivo
     * @param formatoEntradaArchivo
     * @param listaFormatoEntradaArchivoDetalle
     * @return 
     */
    public CargaArchivo cargaCsv(String nombreCompletoArchivo, FormatoEntradaArchivo formatoEntradaArchivo, List<FormatoEntradaArchivoDet> listaFormatoEntradaArchivoDetalle) {
        CargaArchivo carga = null;
        try {

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, CapturaError.getErrorException(e),
                    new Object[]{"CargaArchivoCsv", "cargaCsv", CapturaError.getErrorException(e)});
            return new CargaArchivo(false, CapturaError.getErrorException(e), null, null, "");
        }
        return carga;
    }
}
