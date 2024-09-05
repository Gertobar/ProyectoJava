/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.utilitario;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.codec.binary.Hex;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

/**
 *
 * @author andres
 */
public class Utilidades {

    /**
     *
     * @param nombreArchivo
     * @return
     * @throws Exception
     */
    public static String generaMD5(String nombreArchivo) throws Exception {
        byte[] bytes = crearMd5(nombreArchivo);
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    /**
     *
     * @param nombreArchivo
     * @return
     */
    private static byte[] crearMd5(String nombreArchivo) {
        try {
            InputStream inputStream = new FileInputStream(nombreArchivo);
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;
            do {
                numRead = inputStream.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
            inputStream.close();
            return complete.digest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static String generaMD5(InputStream inputStream) throws Exception {
        byte[] bytes = crearMd5(inputStream);
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    /**
     *
     * @param inputStream
     * @return
     */
    private static byte[] crearMd5(InputStream inputStream) {
        try {
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;
            do {
                numRead = inputStream.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            inputStream.close();
            return complete.digest();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 
     * @param cantidadNumeroAleatorio
     * @return 
     */
    public static ArrayList<Integer> generaNumerosTemporales(int cantidadNumeroAleatorio){
        int numero;
        ArrayList<Integer> numeros = new ArrayList();
        // Genera cantidadNumeroAleatorio numeros entre 0 y 9
        for (int i = 1; i <= cantidadNumeroAleatorio; i++) {
            numero = (int) (Math.random() * 9 + 0);
            if (numeros.contains(numero)) {
                i--;
            } else {
                numeros.add(numero);
            }
        }
        return numeros;
    }
    
    /**
     * 
     * @param cadena
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws java.io.UnsupportedEncodingException 
     */   
    public static String getMd5DesdeCadena(String cadena) throws NoSuchAlgorithmException, UnsupportedEncodingException {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(cadena.getBytes("UTF-8"), 0, cadena.length());
            byte[] bt = md.digest();
            BigInteger bi = new BigInteger(1, bt);
            String md5 = bi.toString(16);
            return md5;
    }
    
    /**
     * 
     * @param valor
     * @return 
     */
    public static BigDecimal formatoValorPorSeparador(BigDecimal valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", simbolos);
        try {
            return new BigDecimal(df.format(valor.doubleValue()));
        } catch (NumberFormatException e) {
            simbolos.setDecimalSeparator(',');
            df = new DecimalFormat("#.00", simbolos);
            return new BigDecimal(df.format(valor.doubleValue()));
        }
    }
    
    /**
     * Retorna la edad por monto de una solicitud de credito y linea de credito
     * @param fecha
     * @param separador
     * @return 
     */
    public static String fechaStringMontoSolicitud(Date fecha, String separador) {
        String fechaCadena = "";
        try {
            int dia = fecha.getDate();
            if (dia < 10) {
                fechaCadena = '0' + String.valueOf(dia);
            } else {
                fechaCadena = String.valueOf(dia);
            }
            fechaCadena = fechaCadena + separador;
            int mes = fecha.getMonth() + 1;
            if (mes < 10) {
                fechaCadena += '0' + String.valueOf(mes);
            } else {
                fechaCadena += String.valueOf(mes);
            }
            fechaCadena  += separador;
            fechaCadena  += String.valueOf(fecha.getYear() + 1900);
        } catch (Exception ex) {
            return null;
        }
        return fechaCadena;
    }
}