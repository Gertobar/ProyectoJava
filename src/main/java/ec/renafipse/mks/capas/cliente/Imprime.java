/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.cliente;

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
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

/**
 *
 * @author v.astudillo
 */
public class Imprime {

    public void imprime() throws PrintException {
        String impresora = "\\\\192.168.1.100\\epson";
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (int i = 0; i < services.length; i++) {
            //System.out.println(services[i].getName());
        }

        //PrinterJob job = PrinterJob.getPrinterJob();
        //System.out.println("impresora " + impresora);
        //Inclusion del nombre de impresora y sus atributos
       /* AttributeSet attributeSet = new HashAttributeSet();

         attributeSet.add(new PrinterName(impresora, null));*/

        PrintServiceAttributeSet attributeSet = new HashPrintServiceAttributeSet();
        //System.out.println("getPrinter() = " + impresora); // here i give my printer name
        attributeSet.add(new PrinterName(impresora, null));

        //attributeSet = new HashAttributeSet();            
        //Soporte de color o no
        //attributeSet.add(ColorSupported.NOT_SUPPORTED);
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, attributeSet);
        //PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

        //System.out.println("printService.length en : " + printService.length);
        //System.out.println("Imprimiendo en : " + printService[0].getName());
        DocPrintJob docPrintJob = printService[0].createPrintJob();

        String string = "Texo que se imprime\n";
        string += "en la impresora predeterminada\n";
        string += "fin del ejemplo\n";

        Doc doc = new SimpleDoc(string.getBytes(), flavor, null);

        docPrintJob.print(doc, new HashPrintRequestAttributeSet());
    }

}
