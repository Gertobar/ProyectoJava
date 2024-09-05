/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.utilitario;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vicastoc
 */
public class Validaciones {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String msgBaja = "(?=^.{1,5}$)(?=.*[A-Z])(?=.*[a-z]).*";
    private static final String msgMedia = "(?=^.{6,8}$)(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*";
    private static final String msgAlta = "(?=^.{8,15}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*";
    public static String msg = "";
    private static final Integer tipoRucNatural = 2;
    private static final Integer tipoRucPrivada = 3;
    private static final Integer tipoRucPublica = 4;

    /**
     *
     * @param cedula
     * @return
     */
    public static boolean validaCedula(String cedula) {
        boolean cedulaCorrecta = false;
        try {
            if (cedula == null) {
                return false;
            }
            if (cedula.length() == 10) // ConstantesApp.LongitudCedula  
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito <= 6) {

                    // Coeficientes de validaciÃ³n cÃ©dula  
                    // El decimo digito se lo considera dÃ­gito verificador  
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};

                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;

                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {

                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {

            cedulaCorrecta = false;
        }

        return cedulaCorrecta;

    }

    /**
     *
     * @param correoElectronico
     * @return
     */
    public static boolean validaCorreoElectronico(String correoElectronico) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correoElectronico);
        return matcher.matches();

    }

    public static boolean validaPersonaMayorEdad(Date fechaNacimiento) {

        int y1 = fechaNacimiento.getYear() + 1900;
        int m1 = fechaNacimiento.getMonth() + 1;
        int d1 = fechaNacimiento.getDate();

        GregorianCalendar fechaNacimientoActual = new GregorianCalendar();
        int y2 = fechaNacimientoActual.get(GregorianCalendar.YEAR);
        int m2 = fechaNacimientoActual.get(GregorianCalendar.MONTH) + 1;
        int d2 = fechaNacimientoActual.get(GregorianCalendar.DAY_OF_MONTH);
        int anos = y2 - y1;
        anos = anos - (((m1 - m2) > 0) ? 1 : (((d1 - d2) > 0) ? 1 : 0));
        return anos >= 18;
    }

    public static boolean validaFechaMenorHoy(Date fecha) {

        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);

        try {
            return fecha.before(formato.parse(formato.format(new Date())));
        } catch (ParseException ex) {
            Logger.getLogger(Validaciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        // return formato.format(fecha).compareTo((formato.format(new Date()))) < 0;
    }

    public static boolean validaFechaMayorHoy(Date fecha) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);
        try {
            return fecha.after(formato.parse(formato.format(new Date())));
        } catch (ParseException ex) {
            Logger.getLogger(Validaciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean validaFechaIgualHoy(Date fecha) {

        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);
        try {

            if (fecha.before(formato.parse(formato.format(new Date())))) {
                return false;
            } else {
                return !formato.parse(formato.format(new Date())).before(fecha);
            }
            //return formato.format(fecha).compareTo((formato.format(new Date()))) == 0;
        } catch (ParseException ex) {
            Logger.getLogger(Validaciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean validaEntreFechasMenorIgual(Date fechaComparacion, Date fechaComparar) {

        if (fechaComparacion.before(fechaComparar)) {
            return true;
        } else {
            return !fechaComparar.before(fechaComparacion);
        }

    }

    public static boolean contrasenaComplejidadBaja(String contrasena) {
        Pattern pattern = Pattern.compile(msgBaja);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public static boolean contrasenaComplejidadMedia(String contrasena) {
        Pattern pattern = Pattern.compile(msgMedia);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public static boolean contrasenaComplejidadAlta(String contrasena) {
        Pattern pattern = Pattern.compile(msgAlta);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }

    public static boolean validaTelefonoConvencional(String telefono) {
        try {
            Integer.parseInt(telefono);
            return telefono.length() == 9;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean validaTelefonoCelular(String celular) {
        try {
            Integer.parseInt(celular);
            return celular.length() > 9;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean cumpleRequerimientoCampo(String texto, int tamanioMinimo) {
        if (texto.isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        if (texto.trim().length() == 0) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        if (texto.length() < tamanioMinimo) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("TamanoDebeSerMayorA") + " " + tamanioMinimo;
            return false;
        }

        return true;
    }

    public static boolean cumpleRequerimientoCampo(int valor, int cantidadMinima) {
        if (String.valueOf(valor).isEmpty()) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        if (valor < cantidadMinima) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CantidadMinimaAceptada") + " " + cantidadMinima;
            return false;
        }

        return true;
    }

    public static boolean cumpleRequerimientoCampo(Date fecha) {

        if (fecha == null) {
            msg = ResourceBundle.getBundle("/propiedadesMensajesEC").getString("CampoRequerido");
            return false;
        }

        return true;
    }

    /**
     * @param numero de ruc persona natural
     * @return true si es un documento v&aacutelido
     * @throws Exception
     */
    public static boolean validaRucPersonaNatural(String numero) throws Exception {
        try {
            validacionInicialRuc(numero, 13);
            validacionCodigoProvincia(numero.substring(0, 2));
            validacionTercerDigito(String.valueOf(numero.charAt(2)), tipoRucNatural);
            validacionCodigoEstablecimiento(numero.substring(10, 13));
            algoritmoModulo10(numero.substring(0, 9), Integer.parseInt(String.valueOf(numero.charAt(9))));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     *
     * @param numero ruc empresa privada
     * @return
     * @throws Exception
     */
    public static boolean validarRucSociedadPrivada(String numero) throws Exception {

        // validaciones
        try {
            validacionInicialRuc(numero, 13);
            validacionCodigoProvincia(numero.substring(0, 2));
            validacionTercerDigito(String.valueOf(numero.charAt(2)), tipoRucPrivada);
            validacionCodigoEstablecimiento(numero.substring(10, 13));
            algoritmoModulo11(numero.substring(0, 9), Integer.parseInt(String.valueOf(numero.charAt(9))), tipoRucPrivada);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * @param numero
     * @param caracteres
     * @return
     * @throws Exception
     */
    protected static boolean validacionInicialRuc(String numero, int caracteres) throws Exception {
        if (StringUtils.isEmpty(numero)) {
            throw new Exception("Valor no puede estar vacio");
        }

        if (!NumberUtils.isDigits(numero)) {
            throw new Exception("Valor ingresado solo puede tener dígitos");
        }

        if (numero.length() != caracteres) {
            throw new Exception("Valor ingresado debe tener " + caracteres + " caracteres");
        }

        return true;
    }

    /**
     * @param numero en el rango de numero de provincias del ecuador
     * @return
     * @throws Exception
     */
    protected static boolean validacionCodigoProvincia(String numero) throws Exception {
        if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 24) {
            throw new Exception("Codigo de Provincia (dos primeros dígitos) no deben ser mayor a 24 ni menores a 0");
        }

        return true;
    }

    /**
     * @param numero
     * @param tipo de documento cedula, ruc
     * @return
     * @throws Exception
     */
    protected static boolean validacionTercerDigito(String numero, Integer tipo) throws Exception {
        switch (tipo) {
            case 1:
            case 2:

                if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 5) {
                    throw new Exception("Tercer dígito debe ser mayor o igual a 0 y menor a 6 para cédulas y RUC de persona natural ... permitidos de 0 a 5");
                }
                break;
            case 3:
                if (Integer.parseInt(numero) != 9) {
                    throw new Exception("Tercer dígito debe ser igual a 9 para sociedades privadas");
                }
                break;

            case 4:
                if (Integer.parseInt(numero) != 6) {
                    throw new Exception("Tercer dígito debe ser igual a 6 para sociedades públicas");
                }
                break;
            default:
                throw new Exception("Tipo de Identificacion no existe.");
        }

        return true;
    }

    /**
     * @param digitosIniciales
     * @param digitoVerificador
     * @return
     * @throws Exception
     */
    protected static boolean algoritmoModulo10(String digitosIniciales, int digitoVerificador) throws Exception {
        Integer[] arrayCoeficientes = new Integer[]{2, 1, 2, 1, 2, 1, 2, 1, 2};

        Integer[] digitosInicialesTMP = new Integer[digitosIniciales.length()];
        int indice = 0;
        for (char valorPosicion : digitosIniciales.toCharArray()) {
            digitosInicialesTMP[indice] = NumberUtils.createInteger(String.valueOf(valorPosicion));
            indice++;
        }

        int total = 0;
        int key = 0;
        for (Integer valorPosicion : digitosInicialesTMP) {
            if (key < arrayCoeficientes.length) {
                valorPosicion = (digitosInicialesTMP[key] * arrayCoeficientes[key]);

                if (valorPosicion >= 10) {
                    char[] valorPosicionSplit = String.valueOf(valorPosicion).toCharArray();
                    valorPosicion = (Integer.parseInt(String.valueOf(valorPosicionSplit[0]))) + (Integer.parseInt(String.valueOf(valorPosicionSplit[1])));

                }
                total = total + valorPosicion;
            }

            key++;
        }
        int residuo = total % 10;
        int resultado;

        if (residuo == 0) {
            resultado = 0;
        } else {
            resultado = 10 - residuo;
        }

        if (resultado != digitoVerificador) {
            throw new Exception("Dígitos iniciales no validan contra Dígito Idenficador");
        }

        return true;
    }

    /**
     * @param numero
     * @return
     * @throws Exception
     */
    protected static boolean validacionCodigoEstablecimiento(String numero) throws Exception {
        if (Integer.parseInt(numero) < 1) {
            throw new Exception("Código de establecimiento no puede ser 0");
        }
        return true;
    }

    /**
     * @param digitosIniciales
     * @param digitoVerificador
     * @param tipo
     * @return
     * @throws Exception
     */
    protected static boolean algoritmoModulo11(String digitosIniciales, int digitoVerificador, Integer tipo) throws Exception {
        Integer[] arrayCoeficientes = null;

        switch (tipo) {

            case 3:
                arrayCoeficientes = new Integer[]{4, 3, 2, 7, 6, 5, 4, 3, 2};
                break;
            case 4:
                arrayCoeficientes = new Integer[]{3, 2, 7, 6, 5, 4, 3, 2};
                break;
            default:
                throw new Exception("Tipo de Identificacion no existe.");
        }

        Integer[] digitosInicialesTMP = new Integer[digitosIniciales.length()];
        int indice = 0;
        for (char valorPosicion : digitosIniciales.toCharArray()) {
            digitosInicialesTMP[indice] = NumberUtils.createInteger(String.valueOf(valorPosicion));
            indice++;
        }

        int total = 0;
        int key = 0;
        for (Integer valorPosicion : digitosInicialesTMP) {
            if (key < arrayCoeficientes.length) {
                valorPosicion = (digitosInicialesTMP[key] * arrayCoeficientes[key]);

                if (valorPosicion >= 10) {
                    char[] valorPosicionSplit = String.valueOf(valorPosicion).toCharArray();
                    valorPosicion = (Integer.parseInt(String.valueOf(valorPosicionSplit[0]))) + (Integer.parseInt(String.valueOf(valorPosicionSplit[1])));
                    System.out.println(valorPosicion);
                }
                total = total + valorPosicion;
            }

            key++;
        }

        int residuo = total % 11;
        int resultado;

        if (residuo == 0) {
            resultado = 0;
        } else {
            resultado = (11 - residuo);
        }

        if (resultado != digitoVerificador) {
            throw new Exception("Dígitos iniciales no validan contra Dígito Idenficador");
        }

        return true;
    }

}
