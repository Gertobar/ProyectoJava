/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.utilitario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

/**
 *
 * @author vicastoc
 */
public class ConvierteDato {

    public static Date getFechaMedium(Date fecha) {
        try {
            DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formato.format(fecha));
            return date;
        } catch (Exception ex) {
            return fecha;
        }

    }

    public static String llenaCerosIzquierda(int numerosCeros, int numero) {
        Formatter fmt = new Formatter();
        return fmt.format("%0" + numerosCeros + "d", numero).toString();
    }

    public static Date getFechaCortaDate(Date fecha, String separador) {

        String fechaCadena = "";

        try {

            int dia = fecha.getDate();
            if (dia < 10) {
                fechaCadena = '0' + String.valueOf(dia);
            } else {
                fechaCadena = String.valueOf(dia);
            }

            fechaCadena = fechaCadena + separador;
            //System.out.println("fechaCadena 1 "+fechaCadena);

            int mes = fecha.getMonth() + 1;
            if (mes < 10) {
                fechaCadena += '0' + String.valueOf(mes);
            } else {
                fechaCadena += String.valueOf(mes);
            }

            //System.out.println("fechaCadena 2 "+fechaCadena);
            fechaCadena += separador;

            fechaCadena += String.valueOf(fecha.getYear() + 1900);
            //System.out.println("fechaCadena  " + fechaCadena);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(fechaCadena);

        } catch (ParseException ex) {
            return null;

        }

    }
    
    /**
     * Devuelve en cadena la fecha en formato corto
     * @param fecha Fecha a converir en String de fecha formato corta
     * @return Fecha Corta -String
     */
     public static String getFechaCortaSinSeparadores(Date fecha) {

        String fechaCadena = "";
        int dia = fecha.getDate();
        if (dia < 10) {
            fechaCadena = '0' + String.valueOf(dia);
        } else {
            fechaCadena = String.valueOf(dia);
        }
        
        int mes = fecha.getMonth() + 1;
        if (mes < 10) {
            fechaCadena += '0' + String.valueOf(mes);
        } else {
            fechaCadena += String.valueOf(mes);
        }
       
        return fechaCadena += String.valueOf(fecha.getYear() + 1900);

    }

    /**
     * Agrega días a una fecha
     *
     * @param fecha FEcha
     * @param dias Numero de dias a agregar
     * @return FEcha agregada días
     */
    public static  Date agregaDias(Date fecha, long dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, (int) dias);
        return calendar.getTime();
    }

    public static  Date fecha(String fecha) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaD;
        fechaD = formateador.parse(fecha);
        return fechaD;
    }
}
