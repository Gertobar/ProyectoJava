/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.impresiones;

import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PrinterName;
import org.primefaces.context.RequestContext;

/**
 *
 * @author v.astudillo
 */
public class ImpresionImpresora {

    /**
     * Realiza la impresiona de la linea en la libreta
     *
     * @param linea
     * @throws InterruptedException
     */
    private String impresion;
    private String impresora;
    private boolean ImprimeEnArchivo;
    private int lineasImpresas;
    private DecimalFormat formatoValores;
    private SimpleDateFormat formatoFechas;
    private AttributeSet attributeSet;
    private DocFlavor flavor;
    private PrintService[] printService;
    private DocPrintJob docPrintJob;
    private Doc doc;
    private boolean impresionCorrecta;
    private File archivo;

    public ImpresionImpresora(String impresora) {
        this.impresion = "";
        this.impresora = impresora;
        formatoValores = new DecimalFormat("###,###,##0.00");
        formatoFechas = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    }

    /**
     * Agrega las libneas en la cadena para la impresion
     *
     * @param linea
     * @throws InterruptedException
     */
    public void imprimeLinea(String linea) throws InterruptedException {

        try {
            if (!this.isImprimeEnArchivo()) {

                impresion += ((linea != null) ? linea : "") + "\n";
                if (linea != null) {
                    this.setLineasImpresas(this.getLineasImpresas() + 1);
                }
            } /*else {
             if (linea == null) {
             pw.println();
             } else {
             pw.println(linea);
             }
             }*/

        } catch (Exception pwe) {
            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorIniciarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ImpresionImpresora", "imprimeLinea", CapturaError.getErrorException(pwe)});
        }

    }

    /**
     * Envio de Caracteres a la Impresora
     *
     * @return
     */
    public boolean enviaImpresora() {
        try {
            /**
             * Add extra tag in modules/sun/jdk/main/module.xml of jbossAs as
             * mentioned: path name="sun/print"
             *
             * Open up resources.jar from your JRE, and extract
             * META-INF/services/javax.print.PrintServiceLookup and copy to
             * location
             * modules/sun/jdk/main/service-loader-resources/META-INF/services
             * of JbossAS.
             *
             * Restart JBoss and run the application.
             */

            // Si es WINDOWS enviamos como cadena
            //if (ActivacionUsuario.getTipoSOServidorAppl().equals("WINDOWS")) {
            //Ubicando la Impresora
            //String impresora = ActivacionUsuario.getRutaImpresora();

            /* PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
             for (int i = 0; i < services.length; i++) {
             System.out.println(services[i].getName());
             }*/

            setAttributeSet(new HashAttributeSet());

            getAttributeSet().add(new PrinterName(impresora, null));

            setFlavor(DocFlavor.BYTE_ARRAY.AUTOSENSE);

            //Encontrando la impresora que debe estar conectada o instalada en el servidor
            setPrintService(PrintServiceLookup.lookupPrintServices(getFlavor(), getAttributeSet()));

                // //System.out.println("Imprimiendo en : " + printService[0]);
            setDocPrintJob(getPrintService()[0].createPrintJob());

            setDoc(new SimpleDoc(impresion.getBytes(), getFlavor(), null));

            // Enviado a Imprimir
            getDocPrintJob().print(getDoc(), new HashPrintRequestAttributeSet());
            /*} else {

             String nombreArchivo = "/tmp/" + ActivacionUsuario.getCodigoUsuario() + ".txt";

             ////System.out.println("nombreArchivo "+nombreArchivo+" impresora "+impresora);
             ////System.out.println(impresion);
             archivo = new File(nombreArchivo);

             if (archivo.exists()) {
             archivo.delete();
             }

             archivo.createNewFile();

             // abrir en el  flujo de salida para sobrescribir un archivo de texto          
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(archivo)));
             out.println(impresion);
             out.close();

             // Enviando a la impresoraas
             //String[] commando = {"sh","-c","/usr/bin/iconv -f utf-8 -t latin1 "+nombreArchivo+" |lpr -P "+impresora};
             String[] commando = {"sh", "-c", "lp -d " + impresora + " -o cpi=15 -o lpi=8 -o page-left=15 -o page-right=0 -o page-top=19 -o page-bottom=0 " + nombreArchivo};
             //String[] commando = {"sh", "-c", "enscript -f Courier10 " +nombreArchivo+ " -d "+impresora};

             System.out.println("Comando " + commando);
             Runtime.getRuntime().exec(commando);
             }*/

        } catch (PrintException pwe) {

            MuestraMensaje.addAdvertencia(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("ErrorEnviarImpresora"));
            // Registra el error en el log del servidor
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                    new Object[]{"ImpresionImpresora", "enviaImpresora", CapturaError.getErrorException(pwe)});

            return false;
        } /*catch (IOException ex) {
         Logger.getLogger(ImpresionImpresora.class.getName()).log(Level.SEVERE, null, ex);
         }*/

        return true;

    }

    public String LPad(String str, Integer length) {
        //return String.format("%1$-" + ((length-str.length()) > 0 ? length-str.length() : 1) + "s", "")+str;

        return String.format("%1$" + length + "s", str);
    }

    public String RPad(String str, Integer length) {
        return String.format("%1$-" + length + "s", str);
        //return String.format("%" + (length - str.length()) + "s", "") + str;
    }

    public void colcarFormatoFechaHora() {
        setFormatoFechas(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"));
    }

    public void colcarFormatoFecha() {
        setFormatoFechas(new SimpleDateFormat("dd/MM/yyyy"));
    }

    /**
     * @return the impresion
     */
    public String getImpresion() {
        return impresion;
    }

    /**
     * @param impresion the impresion to set
     */
    public void setImpresion(String impresion) {
        this.impresion = impresion;
    }

    /**
     * @return the ImprimeEnArchivo
     */
    public boolean isImprimeEnArchivo() {
        return ImprimeEnArchivo;
    }

    /**
     * @param ImprimeEnArchivo the ImprimeEnArchivo to set
     */
    public void setImprimeEnArchivo(boolean ImprimeEnArchivo) {
        this.ImprimeEnArchivo = ImprimeEnArchivo;
    }

    /**
     * @return the lineasImpresas
     */
    public int getLineasImpresas() {
        return lineasImpresas;
    }

    /**
     * @param lineasImpresas the lineasImpresas to set
     */
    public void setLineasImpresas(int lineasImpresas) {
        this.lineasImpresas = lineasImpresas;
    }

    /**
     * @return the impresora
     */
    public String getImpresora() {
        return impresora;
    }

    /**
     * @param impresora the impresora to set
     */
    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }

    /**
     * @return the formatoValores
     */
    public DecimalFormat getFormatoValores() {
        return formatoValores;
    }

    /**
     * @param formatoValores the formatoValores to set
     */
    public void setFormatoValores(DecimalFormat formatoValores) {
        this.formatoValores = formatoValores;
    }

    /**
     * @return the formatoFechas
     */
    public SimpleDateFormat getFormatoFechas() {
        return formatoFechas;
    }

    /**
     * @param formatoFechas the formatoFechas to set
     */
    public void setFormatoFechas(SimpleDateFormat formatoFechas) {
        this.formatoFechas = formatoFechas;
    }

    /**
     * @return the attributeSet
     */
    public AttributeSet getAttributeSet() {
        return attributeSet;
    }

    /**
     * @param attributeSet the attributeSet to set
     */
    public void setAttributeSet(AttributeSet attributeSet) {
        this.attributeSet = attributeSet;
    }

    /**
     * @return the flavor
     */
    public DocFlavor getFlavor() {
        return flavor;
    }

    /**
     * @param flavor the flavor to set
     */
    public void setFlavor(DocFlavor flavor) {
        this.flavor = flavor;
    }

    /**
     * @return the printService
     */
    public PrintService[] getPrintService() {
        return printService;
    }

    /**
     * @param printService the printService to set
     */
    public void setPrintService(PrintService[] printService) {
        this.printService = printService;
    }

    /**
     * @return the docPrintJob
     */
    public DocPrintJob getDocPrintJob() {
        return docPrintJob;
    }

    /**
     * @param docPrintJob the docPrintJob to set
     */
    public void setDocPrintJob(DocPrintJob docPrintJob) {
        this.docPrintJob = docPrintJob;
    }

    /**
     * @return the doc
     */
    public Doc getDoc() {
        return doc;
    }

    /**
     * @param doc the doc to set
     */
    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    /**
     * @return the impresionCorrecta
     */
    public boolean isImpresionCorrecta() {
        return impresionCorrecta;
    }

    /**
     * @param impresionCorrecta the impresionCorrecta to set
     */
    public void setImpresionCorrecta(boolean impresionCorrecta) {
        this.impresionCorrecta = impresionCorrecta;
    }
    
  
    /**
     * @return the archivo
     */
    public File getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

}
