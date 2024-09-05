/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.reportes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 *
 * @author vicastoc
 */
public class GeneraReporte {

    private HashMap<String, Object> parametros;
    private byte[] bytesReporte;
    private List<String> listasubReportes;

    /**
     * @return the bytesReporte
     */
    public byte[] getBytesReporte() {
        return bytesReporte;
    }

    /**
     * @param bytesReporte the bytesReporte to set
     */
    public void setBytesReporte(byte[] bytesReporte) {
        this.bytesReporte = bytesReporte;
    }

    /**
     * @return the listasubReportes
     */
    public List<String> getListasubReportes() {
        return listasubReportes;
    }

    /**
     * @param listasubReportes the listasubReportes to set
     */
    public void setListasubReportes(List<String> listasubReportes) {
        this.listasubReportes = listasubReportes;
    }

    /**
     *
     */
    public enum opcionesExportar {

        PDF, HTML, EXCEL, RTF, DOCX, TXT
    }

    /**
     *
     * @param directorioReporte
     * @param nombreReporte
     * @param nombreReportePdf
     * @param tipo
     * @param externalContext
     * @param facesContext
     */
    public void exporta(String directorioReporte, String nombreReporte, String nombreReportePdf, String tipo, ExternalContext externalContext, FacesContext facesContext) {
        try {
            Configuracion configuracion = new Configuracion();
            Connection conexion = configuracion.obtieneConexionBD();
            configuracion.compilaReporte(externalContext, directorioReporte, nombreReporte);
            JasperPrint impresionJasper = null;
            //Compilando subreportes
            /*if (!this.listasubReportes.isEmpty())
             {
             for (String subRreporte : listasubReportes)
             {
             //System.out.println("Subreporte "+subRreporte);
             configuracion.compilaReporte(externalContext, directorioReporte, subRreporte);
             }
             }*/
            String reporteAbsoluto = externalContext.getRealPath(directorioReporte + nombreReporte + ".jasper");
            System.out.println("reporteAbsoluto " + reporteAbsoluto);
            File archivoJasper = new File(reporteAbsoluto);

            if (!archivoJasper.exists()) {
                reporteAbsoluto = directorioReporte + nombreReporte + ".jasper";
                System.out.println("RUTA REPORTE 1 " + reporteAbsoluto);
            }

            if (nombreReporte.equals("solicitudCreditoSimulacion")) {
                System.out.println("reporte sin BD ");
                System.out.println("Ruta Simulacion: " + reporteAbsoluto);
                System.out.println("Parametros Size: " + parametros.size());

//                 for(Map.Entry<String, Object> entry : parametros.entrySet()) {
//                    String key = entry.getKey();
//                    Object value = entry.getValue();
//                     System.out.println("datos : {"+key+"} -> ["+value+"]");
//                    }
                JRDataSource beanColDataSource = new JREmptyDataSource();
                impresionJasper = configuracion.creaReporteLocal(reporteAbsoluto, parametros, beanColDataSource);

            } else {
                System.out.println("reporte con BD : ");
                impresionJasper = configuracion.creaReporte(reporteAbsoluto, parametros, conexion);
            }

            //impresionJasper = configuracion.creaReporte(reporteAbsoluto, parametros, conexion);
            conexion.close();

            if (tipo.equals("PDF")) {
                this.exportaRepotePDF(nombreReportePdf, impresionJasper, externalContext, facesContext);
                System.out.println("Exportado");
            } else if (tipo.equals("XLS")) {
                System.out.println("Exportando EXCEL");
                this.exportReporteEXCEL(nombreReportePdf, impresionJasper, externalContext, facesContext);
            } else if (tipo.equals("TXT")) {
                System.out.println("Exportando TXT");
                this.exportReporteTXT(nombreReportePdf, impresionJasper, externalContext, facesContext);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exportaBytes(String directorioReporte, String nombreReporte, String nombreReportePdf, String tipo, ExternalContext externalContext, FacesContext facesContext) {
        try {
            Configuracion configuracion = new Configuracion();
            Connection conexion = configuracion.obtieneConexionBD();
            configuracion.compilaReporte(externalContext, directorioReporte, nombreReporte);
            String reporteAbsoluto = externalContext.getRealPath(directorioReporte + nombreReporte + ".jasper");
            JasperPrint impresionJasper = configuracion.creaReporte(reporteAbsoluto, parametros, conexion);
            conexion.close();

            if (tipo.equals("PDF")) {
                bytesReporte = JasperExportManager.exportReportToPdf(impresionJasper);
            }
        } catch (Exception ex) {
        }
    }

    public JasperReport getSubReporte(ExternalContext contexto, String directorioReporte, String nombreSubReporte) {
        JasperReport subReporte = null;
        try {
            String reporteJasper = directorioReporte + nombreSubReporte + ".jasper";
            String archivoJRXML = reporteJasper.substring(0, reporteJasper.indexOf(".jasper")) + ".jrxml";
            File fileJasper = new File(reporteJasper);
            File fileJRXML = new File(archivoJRXML);
            if (!fileJasper.exists() || !fileJRXML.exists()) {
                reporteJasper = contexto.getRealPath(directorioReporte + nombreSubReporte + ".jasper");
                archivoJRXML = reporteJasper.substring(0, reporteJasper.indexOf(".jasper")) + ".jrxml";
            }
            subReporte = JasperCompileManager.compileReport(new FileInputStream(archivoJRXML));//(JasperReport) JRLoader.loadObject(this.getClass().getResource(directorioReporte+nombreSubReporte + ".jasper"));

        } catch (JRException ex) {
            Logger.getLogger(GeneraReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneraReporte.class.getName()).log(Level.SEVERE, null, ex);
        }

        return subReporte;
    }

    /**
     *
     * @param nombreReportePdf
     * @param impresionJasper
     * @param externalContext
     * @param facesContext
     * @throws JRException
     * @throws IOException
     */
    private void exportaRepotePDF(String nombreReportePdf, JasperPrint impresionJasper, ExternalContext externalContext, FacesContext facesContext) throws JRException, IOException {
        bytesReporte = JasperExportManager.exportReportToPdf(impresionJasper);
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.addHeader("Content-disposition", "attachment;filename=" + nombreReportePdf);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(impresionJasper, servletOutputStream);
        /*response.setContentLength(bytesReporte.length);  
         response.getOutputStream().write(bytesReporte);  
         response.setContentType("application/pdf");*/
        facesContext.responseComplete();
    }

    /**
     *
     * @param exportar
     * @param impresionJasper
     * @param salida
     * @throws JRException
     */
    private void exportaReporte(JRAbstractExporter exportar, JasperPrint impresionJasper, PrintWriter salida) throws JRException {
        exportar.setParameter(JRExporterParameter.JASPER_PRINT, impresionJasper);
        exportar.setParameter(JRExporterParameter.OUTPUT_WRITER, salida);
        exportar.exportReport();
    }

    /**
     *
     * @param impresionJasper
     * @param salida
     * @throws JRException
     */
    private void exportReporteXHML(JasperPrint impresionJasper, PrintWriter salida) throws JRException {
        JRHtmlExporter exportar = new JRHtmlExporter();
        exportar.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        exportar.setParameter(JRExporterParameter.OUTPUT_WRITER, salida);
        exportar.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exportar.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "ISO-8859-9");
        //exportar.setParameter(JRHtmlExporterParameter.IMAGES_URI, "/SampleReportJSF/servlets/image?image=");//SampleReportJSF is the name of the project

        exportaReporte(exportar, impresionJasper, salida);
    }

    /**
     *
     * @param impresionJasper
     * @param salida
     * @throws JRException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void exportReporteEXCEL(String nombreReporteExcel, JasperPrint impresionJasper, ExternalContext externalContext, FacesContext facesContext) throws JRException, FileNotFoundException, IOException {
        //ByteArrayOutputStream colocaSalida = new ByteArrayOutputStream();
        //OutputStream salidaArchivo = new FileOutputStream(new File("d:/output/JasperReport1.xls"));//make sure to have the directory. excel file will export here

        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        OutputStream servletOutputStream = response.getOutputStream();

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        JRXlsExporter exportarXLS = new JRXlsExporter();

        exportarXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, impresionJasper);
        exportarXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
        exportarXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exportarXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exportarXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exportarXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exportarXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
        exportarXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
        exportarXLS.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.FALSE);
        exportarXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);

        exportarXLS.exportReport();

        response.setHeader("Content-disposition", "attachment; filename=" + nombreReporteExcel);
        response.setContentType("application/vnd.ms-excel");
        response.setContentLength(arrayOutputStream.toByteArray().length);
        servletOutputStream.write(arrayOutputStream.toByteArray());
        servletOutputStream.flush();
        servletOutputStream.close();

        facesContext.responseComplete();
    }

    private void exportReporteTXT(String nombreReporteTXT, JasperPrint impresionJasper, ExternalContext externalContext, FacesContext facesContext) throws JRException, FileNotFoundException, IOException {
        //ByteArrayOutputStream colocaSalida = new ByteArrayOutputStream();
        //OutputStream salidaArchivo = new FileOutputStream(new File("d:/output/JasperReport1.xls"));//make sure to have the directory. excel file will export here

        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        OutputStream servletOutputStream = response.getOutputStream();

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        JRTextExporter exportarTXT = new JRTextExporter();

        exportarTXT.setParameter(JRExporterParameter.JASPER_PRINT, impresionJasper);
        exportarTXT.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
        exportarTXT.setParameter(JRExporterParameter.PROGRESS_MONITOR, null);
        exportarTXT.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Float(35));
        exportarTXT.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Float(200));
        exportarTXT.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(13));
        exportarTXT.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(14));
        exportarTXT.setParameter(JRTextExporterParameter.CHARACTER_ENCODING, "iso-8859-1");
        exportarTXT.exportReport();
        response.setHeader("Content-disposition", "attachment; filename=" + nombreReporteTXT);
        response.setContentType("application/vnd.ms-excel");
        response.setContentLength(arrayOutputStream.toByteArray().length);
        servletOutputStream.write(arrayOutputStream.toByteArray());
        servletOutputStream.flush();
        servletOutputStream.close();

        facesContext.responseComplete();
    }

    /**
     * @return the parametros
     */
    public HashMap<String, Object> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(HashMap<String, Object> parametros) {
        this.parametros = parametros;
    }

    /**
     * Realizado  por Willan Jara 22-11-2017
     * Metodo que permite generar varios reportes por dependiendo del nombre asignado
     * La concideracion es que los datos de la lista nombreReportes
     * deben estar en el mismo directorio
     * 
     * @param directorioReporte
     * @param nombreReportes nombre de los reportes que se encuentran en el mismo directorio
     * @param nombreReportePdf
     * @param tipo
     * @param externalContext
     * @param facesContext
     */
    public void exportaVariasPagias(String directorioReporte, List<String> nombreReportes, String nombreReportePdf, String tipo, ExternalContext externalContext, FacesContext facesContext) {
        try {
            //Se valida que el listado sea diferente de nulo y que tenga valor
            if (nombreReportes == null) {
                return;
            }
            if (nombreReportes.isEmpty()) {
                return;
            }
            Configuracion configuracion = new Configuracion();
            Connection conexion = configuracion.obtieneConexionBD();
            int i = 0;

            JasperPrint impresionJasper = null;
            JasperPrint impresionJasperPagina = null;
            //Se recorre el listado para obtener los nombres
            for (String nombre : nombreReportes) {
                configuracion.compilaReporte(externalContext, directorioReporte, nombre);
                String reporteAbsoluto = externalContext.getRealPath(directorioReporte + nombre + ".jasper");
                File archivoJasper = new File(reporteAbsoluto);
                if (!archivoJasper.exists()) {
                    //Los reporetes deben estar compilados
                    reporteAbsoluto = directorioReporte + nombre + ".jasper";
                }
                if (i == 0) {
                    impresionJasper = configuracion.creaReporte(reporteAbsoluto, this.parametros, conexion);
                } else {
                    impresionJasperPagina = configuracion.creaReporte(reporteAbsoluto, this.parametros, conexion);
                    List pages = impresionJasperPagina.getPages();
                    for (int j = 0; j < pages.size(); j++) {
                        JRPrintPage object = (JRPrintPage) pages.get(j);
                        impresionJasper.addPage(object);
                    }
                }
                i++;
            }
            conexion.close();
            if (tipo.equals("PDF")) {
                exportaRepotePDF(nombreReportePdf, impresionJasper, externalContext, facesContext);
            } else if (tipo.equals("XLS")) {
                exportReporteEXCEL(nombreReportePdf, impresionJasper, externalContext, facesContext);
            } else if (tipo.equals("TXT")) {
                exportReporteTXT(nombreReportePdf, impresionJasper, externalContext, facesContext);
            }
        } catch (IOException localException) {
            Logger.getLogger(GeneraReporte.class.getName()).log(Level.SEVERE, "ERROR EN REPORETE MULTIPAGINA", localException);
        } catch (SQLException localException) {
            Logger.getLogger(GeneraReporte.class.getName()).log(Level.SEVERE, "ERROR EN REPORETE MULTIPAGINA", localException);
        } catch (NamingException localException) {
            Logger.getLogger(GeneraReporte.class.getName()).log(Level.SEVERE, "ERROR EN REPORETE MULTIPAGINA", localException);
        } catch (JRException localException) {
            Logger.getLogger(GeneraReporte.class.getName()).log(Level.SEVERE, "ERROR EN REPORETE MULTIPAGINA", localException);
        }
    }

}
