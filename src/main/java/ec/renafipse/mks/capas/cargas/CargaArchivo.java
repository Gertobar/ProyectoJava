/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.capas.cargas;

import java.util.ArrayList;

/**
 *
 * @author andres
 */
public class CargaArchivo {
    private boolean cargaCorrecta;
    private String cargaError;
    private ArrayList<Object[]> resultado;
    private ArrayList<Object[]> resultadoErrores;
    private String md5;

    public CargaArchivo(boolean cargaCorrecta, String cargaError, ArrayList<Object[]> resultado, ArrayList<Object[]> resultadoErrores, String md5) {
        this.cargaCorrecta = cargaCorrecta;
        this.cargaError = cargaError;
        this.resultado = resultado;
        this.resultadoErrores = resultadoErrores;
        this.md5 = md5;
    }
    /**
     * @return the cargaCorrecta
     */
    public boolean isCargaCorrecta() {
        return cargaCorrecta;
    }

    /**
     * @param cargaCorrecta the cargaCorrecta to set
     */
    public void setCargaCorrecta(boolean cargaCorrecta) {
        this.cargaCorrecta = cargaCorrecta;
    }

    /**
     * @return the cargaError
     */
    public String getCargaError() {
        return cargaError;
    }

    /**
     * @param cargaError the cargaError to set
     */
    public void setCargaError(String cargaError) {
        this.cargaError = cargaError;
    }

    /**
     * @return the resultado
     */
    public ArrayList<Object[]> getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(ArrayList<Object[]> resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the resultadoErrores
     */
    public ArrayList<Object[]> getResultadoErrores() {
        return resultadoErrores;
    }

    /**
     * @param resultadoErrores the resultadoErrores to set
     */
    public void setResultadoErrores(ArrayList<Object[]> resultadoErrores) {
        this.resultadoErrores = resultadoErrores;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
}
