/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.control.adquisicones.bean;

import ec.renafipse.mks.capas.basedatos.LlamaSP;
import ec.renafipse.mks.capas.sesion.ActivacionUsuario;
import ec.renafipse.mks.capas.utilitario.CapturaError;
import ec.renafipse.mks.capas.utilitario.MuestraMensaje;
import ec.renafipse.mks.capas.utilitario.Validaciones;
import ec.renafipse.mks.modelo.adquisiciones.Ats;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import ec.renafipse.mks.control.AbstractController;
import ec.renafipse.mks.negocio.adquisiciones.AtsFacade;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

//import oracle.jdbc.OracleCallableStatement;
//import oracle.jdbc.OracleTypes;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author renafipse1
 */
@ManagedBean(name = "anexoTransaccionalBean")
@ViewScoped

public class AnexoTransaccionalBean extends AbstractController<Ats> implements Serializable {

    @EJB
    private AtsFacade ejbFacadeAts;

    @Inject
    private LlamaSP llamaSP;

    private Date fechaPeriodo;
    private String msg;
    private String ruta;
    private boolean existeRendimiento;
    private String nombreArchivo;
    private Timestamp fechaPeriodoTs;
    private UploadedFile uploadArchivo;
    private Ats atsSel;

    private List<Ats> itemsAts;
    private List<Ats> itemsAtsInforme;
    private List<Ats> itemsAtsBusq;

    private StreamedContent archivoBajar;

    private DefaultStreamedContent archivoXml;

    /**
     * Creates a new instance of AnexoTransaccionalBean
     */
    public AnexoTransaccionalBean() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacadeAts);
        this.fechaPeriodo = new Date();
        llamaSP = new LlamaSP();
        existeRendimiento = false;
    }

    public void reciboFecha(SelectEvent event) {
        //   //System.out.println("FECHA ESCOGIDA: " + fechaPeriodo);
    }

    public void obtengAnexoTransaccional(ActionEvent event) {

        recolecionDatos();

    }

    public void recolecionDatos() {
        if (this.fechaPeriodo != null) {
            RequestContext context = RequestContext.getCurrentInstance();
            try {
                context.execute("procesandoDlg.show()");
                // Cargando la conexion de BD
                llamaSP.cargaConexion();

                // Indicando que no cierre la conexion de la base de datos
                llamaSP.setCerrarConexion(false);

                // Indicando que no realice commit de forma automatica para poder realizar al final del flujo
                llamaSP.autoCommit(false);

                if (this.listaDatos()) {
                }

                // Verificando que la ejecucion del proceso ha sido correcta
                if (llamaSP.isEjecucionCorrecta()) {
                    // Guardamos lo realizado, cerramos la conexion y mostramos memnsaje de satisfactorio
                    llamaSP.commit();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);

                    if (escriboDatosArchivo()) {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeAts"));
                        MuestraMensaje.addInformacion(getMsg());
                        context.execute("procesandoDlg.hide()");
                    } else {
                        setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeAtsSinRegistros"));
                        MuestraMensaje.addInformacion(getMsg());
                        context.execute("procesandoDlg.hide()");
                    }

                } else {
                    context.execute("procesandoDlg.hide()");
                    llamaSP.rollback();
                    llamaSP.cerrarConexion();
                    llamaSP.setConexionBD(null);
                }

            } catch (UnknownHostException ex) {;
                context.execute("procesandoDlg.hide()");
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"anexoTransaccionalBean", "guardaDatosCabeceraDetalle", CapturaError.getErrorException(ex)});
            }
        } else {
            //MensajeFacturaTotalCompraZero
            setMsg(ResourceBundle.getBundle("/propiedadesMensajesEC").getString("MensajeFacturaTotalCompraZero"));
            MuestraMensaje.addAdvertencia(getMsg());
        }
    }

    public boolean listaDatos() throws UnknownHostException {

        if (this.fechaPeriodo != null) {

            this.setFechaPeriodoTs(new java.sql.Timestamp(this.fechaPeriodo.getTime()));
            llamaSP.setNombreSP("mks_adquisiciones.pkm_ats.p_lista_ats");
            llamaSP.setNumeroParametros(2);  //PARAMETROS DE ENTRADA Y SALIDA

            llamaSP.setListParametrosEntrada(new ArrayList<Object[]>());
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_fecha_corte", this.fechaPeriodoTs});
            llamaSP.getListParametrosEntrada().add(new Object[]{"p_codigo_ifip", ActivacionUsuario.getCodigoIfip()});

            /////SALIDA
            llamaSP.setListParametrosSalida(new ArrayList<Object[]>());

            // Invocando al SP
            llamaSP.invocaSP();

            if (llamaSP.isEjecucionCorrecta()) {
            }
        }

        return llamaSP.isEjecucionCorrecta();
    }

    public boolean escriboDatosArchivo() {
        boolean respuesta = false;
        if (ejbFacadeAts.getItemAtsFecha(getFechaPeriodo(), 'C').isEmpty() && ejbFacadeAts.getItemAtsFecha(getFechaPeriodo(), 'R').isEmpty()) {
            respuesta = false;
        } else {
            try {
                itemsAtsInforme = ejbFacadeAts.getItemAtsFecha(getFechaPeriodo(), 'H');

                String nombreFolder = "carpetaAts";
                String rutaFolder = ActivacionUsuario.getRutaImpresionArchivos() + nombreFolder;
                File dirFolder = new File(rutaFolder);

                if (!dirFolder.exists()) {
                    dirFolder.mkdirs();
                }

                String DATE_FORMAT = "MMyyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

                this.ruta = rutaFolder + "//AT-" + sdf.format(fechaPeriodo);
                this.nombreArchivo = rutaFolder + "//AT-" + sdf.format(fechaPeriodo) + ".xml";

                File archivo = new File(this.nombreArchivo);//rutaFolder + "//ATS_" + sdf.format(fechaPeriodo) + ".xml");

                if (!archivo.exists()) {
                    archivo.createNewFile();
                } else {
                    archivo.delete();
                    archivo.createNewFile();
                }

                // abrir en el  flujo de salida para sobrescribir un archivo de texto  
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(archivo)));

                out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>");
                out.println("<iva>");
                String datosCab[] = itemsAtsInforme.get(0).getLinea().split("<saltoMinka>");
                out.println(datosCab[0]);
                out.println(datosCab[1]);
                out.println(datosCab[2]);
                out.println(datosCab[3]);
                out.println(datosCab[4]);
                out.println(datosCab[5]);
                out.println(datosCab[6]);
                out.println(datosCab[7]);
                itemsAtsInforme = ejbFacadeAts.getItemAtsFecha(getFechaPeriodo(), 'C');
                /// para llenar los datos del archivo
                if (itemsAtsInforme.size() > 0) {
                    String datosCabecera[] = itemsAtsInforme.get(0).getLinea().split("<saltoMinka>");
                    System.out.println("DATOS RECUPERADOS");
                    for (int j = 0; j < datosCabecera.length; j++) {
                        System.out.println("[" + j + "]" + datosCabecera[j]);
                    }

//                    out.println(datosCabecera[0]);
//                    out.println(datosCabecera[1]);
//                    out.println(datosCabecera[2]);
//                    out.println(datosCabecera[3]);
//                    out.println(datosCabecera[4]);
//                    out.println(datosCabecera[52]);
//                    out.println(datosCabecera[5]);
//                    out.println(datosCabecera[6]);
                    out.println("<compras>");
                    for (int i = 0; i < itemsAtsInforme.size(); i++) {
                        //<saltoMinka>

                        String datosDetCompra[] = itemsAtsInforme.get(i).getLinea().split("<saltoMinka>");

                        out.println("<detalleCompras>");

                        out.println(datosDetCompra[7]);
                        out.println(datosDetCompra[8]);
                        out.println(datosDetCompra[9]);
                        out.println(datosDetCompra[10]);
                        out.println(datosDetCompra[30]);
                        out.println(datosDetCompra[11]);
                        out.println(datosDetCompra[12]);
                        out.println(datosDetCompra[13]);
                        out.println(datosDetCompra[14]);
                        out.println(datosDetCompra[15]);
                        out.println(datosDetCompra[16]);
                        out.println(datosDetCompra[17]);
                        out.println(datosDetCompra[18]);
                        out.println(datosDetCompra[19]);
                        out.println(datosDetCompra[47]);
                        out.println(datosDetCompra[20]);
                        out.println(datosDetCompra[21]);
                        out.println(datosDetCompra[31]);
                        out.println(datosDetCompra[32]);
                        out.println(datosDetCompra[22]);
                        out.println(datosDetCompra[54]);
                        out.println(datosDetCompra[23]);
                        out.println(datosDetCompra[24]);
                        out.println(datosDetCompra[43]);

                        //
                        out.println("<pagoExterior>");

                        out.println(datosDetCompra[25]);
                        out.println(datosDetCompra[26]);
                        out.println(datosDetCompra[27]);
                        out.println(datosDetCompra[28]);
                        out.println(datosDetCompra[44]);

                        out.println("</pagoExterior>");

                        if (!datosDetCompra[48].trim().equals("")) {
                            out.println("<formasDePago>");
                            out.println(datosDetCompra[48]);
                            out.println("</formasDePago>");
                        }
                        /////
                        out.println("<air>");
//                    out.println("<detalleAir>");

                        out.println(datosDetCompra[29]);
//                    out.println(datosDetCompra[30]);
//                    out.println(datosDetCompra[31]);
//                    out.println(datosDetCompra[32]);
//                    //out.println(datosDetCompra[33]);
//
//                    out.println("</detalleAir>");
                        out.println("</air>");
                        out.println(datosDetCompra[45]);
                        out.println(datosDetCompra[46]);
                        out.println(datosDetCompra[49]);
                        out.println(datosDetCompra[50]);
                        out.println(datosDetCompra[51]);

                        // CIERRE DETALLE COMPRAS
                        out.println("</detalleCompras>");
                    }
                    out.println("</compras>");
                }
                /////Ventas
                itemsAtsInforme = ejbFacadeAts.getItemAtsFecha(getFechaPeriodo(), 'V');
                out.println("<ventas>");
                for (int i = 0; i < itemsAtsInforme.size(); i++) {
                    String datosDetCompra[] = itemsAtsInforme.get(i).getLinea().split("<saltoMinka>");
                    out.println("<detalleVentas>");
                    out.println(datosDetCompra[0]);
                    out.println(datosDetCompra[1]);
                    out.println(datosDetCompra[2]);
                    out.println(datosDetCompra[3]);
                    out.println(datosDetCompra[4]);
                    out.println(datosDetCompra[5]);
                    out.println(datosDetCompra[6]);
                    out.println(datosDetCompra[7]);
                    out.println(datosDetCompra[8]);
                    out.println(datosDetCompra[9]);
                    out.println(datosDetCompra[10]);
                    out.println(datosDetCompra[11]);
                    out.println("</detalleVentas>");
                }
                out.println("</ventas>");

                ///Total ventas por establecimiento
                itemsAtsInforme = ejbFacadeAts.getItemAtsFecha(getFechaPeriodo(), 'E');
                out.println("<ventasEstablecimiento>");
                for (int i = 0; i < itemsAtsInforme.size(); i++) {
                    String datosDetCompra[] = itemsAtsInforme.get(i).getLinea().split("<saltoMinka>");
                    out.println("<ventaEst>");
                    out.println(datosDetCompra[0]);
                    out.println(datosDetCompra[1]);
                    out.println("</ventaEst>");
                }
                out.println("</ventasEstablecimiento>");

                ////Rendimientos financieros
                itemsAtsInforme = ejbFacadeAts.getItemAtsFecha(getFechaPeriodo(), 'R');
                itemsAtsBusq = new ArrayList<Ats>();

                out.println("<rendFinancieros>");
                for (int i = 0; i < itemsAtsInforme.size(); i++) {
                    String datosDetCompra[] = itemsAtsInforme.get(i).getLinea().split("<saltoMinka>");
                    boolean existe = false;
                    if (itemsAtsBusq != null) {
                        for (int r = 0; r < itemsAtsBusq.size(); r++) {
                            String existeDetalle[] = itemsAtsBusq.get(r).getLinea().split("<saltoMinka>");
                            if (datosDetCompra[1].equals(existeDetalle[1])) {
                                existe = true;
                            }
                        }
                    }
                    if (!existe) {
                        out.println("<detalleRendFinancieros>");
                        out.println(datosDetCompra[0]);
                        out.println(datosDetCompra[1]);
                        out.println(datosDetCompra[2]);
                        out.println("<ahorroPN>");
                        out.println(datosDetCompra[3]);
                        out.println(datosDetCompra[4]);
                        out.println("</ahorroPN>");
                        out.println("<ctaExenta>");
                        out.println(datosDetCompra[5]);
                        out.println(datosDetCompra[6]);
                        out.println("</ctaExenta>");
                        out.println("<retenciones>");
                        for (int j = 0; j < itemsAtsInforme.size(); j++) {
                            String datosDetalle[] = itemsAtsInforme.get(j).getLinea().split("<saltoMinka>");
                            
                            if (datosDetCompra[1].equals(datosDetalle[1])) {
                                itemsAtsBusq.add(itemsAtsInforme.get(j));

                                out.println("<detRet>");
                                out.println("<pagoExterior>");
                                out.println(datosDetalle[7]);
                                out.println(datosDetalle[8]);
                                out.println(datosDetalle[9]);
                                out.println(datosDetalle[10]);
                                out.println(datosDetalle[11]);
                                out.println("</pagoExterior>");
                                out.println(datosDetalle[12]);
                                out.println(datosDetalle[13]);
                                out.println(datosDetalle[14]);
                                out.println(datosDetalle[15]);
                                out.println(datosDetalle[16]);
                                out.println("<airRend>");
                                out.println(datosDetalle[17]);
                                out.println("</airRend>");
                                out.println("</detRet>");
                            }
                        }
                        out.println("</retenciones>");
                        out.println("</detalleRendFinancieros>");
                    }

                }
                out.println("</rendFinancieros>");

                out.println("</iva>");
                out.close();

                //---------------------------------------------------------------------
                // DESCARGANDO EL ARCHIVO
                //System.out.println("nombreArchivo " + nombreArchivo);
                archivo = new File(nombreArchivo);
                InputStream input = new FileInputStream(archivo);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

                setArchivoXml(
                        new DefaultStreamedContent(input, externalContext.getMimeType(archivo.getName()), archivo.getName()));

            } catch (IOException e) {
                // Muestra el Mensaje del Error en la Capa
                MuestraMensaje.addErrorCapaControl();
                // Registra el error en el log del servidor
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, CapturaError.errorNoCapturadoCapaDeControl,
                        new Object[]{"anexoTransaccionalBean", "escriboDatosArchivo", CapturaError.getErrorException(e)});
            }

        }
        return respuesta;
    }

    /**
     * @return the fechaPeriodo
     */
    public Date getFechaPeriodo() {
        return fechaPeriodo;
    }

    /**
     * @param fechaPeriodo the fechaPeriodo to set
     */
    public void setFechaPeriodo(Date fechaPeriodo) {
        this.fechaPeriodo = fechaPeriodo;
    }

    /**
     * @return the itemsAts
     */
    public List<Ats> getItemsAts() {
        return itemsAts;
    }

    /**
     * @param itemsAts the itemsAts to set
     */
    public void setItemsAts(List<Ats> itemsAts) {
        this.itemsAts = itemsAts;
    }

    /**
     * @return the atsSel
     */
    public Ats getAtsSel() {
        return atsSel;
    }

    /**
     * @param atsSel the atsSel to set
     */
    public void setAtsSel(Ats atsSel) {
        this.atsSel = atsSel;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the fechaPeriodoTs
     */
    public Timestamp getFechaPeriodoTs() {
        return fechaPeriodoTs;
    }

    /**
     * @param fechaPeriodoTs the fechaPeriodoTs to set
     */
    public void setFechaPeriodoTs(Timestamp fechaPeriodoTs) {
        this.fechaPeriodoTs = fechaPeriodoTs;
    }

    /**
     * @return the itemsAtsInforme
     */
    public List<Ats> getItemsAtsInforme() {
        return itemsAtsInforme;
    }

    /**
     * @param itemsAtsInforme the itemsAtsInforme to set
     */
    public void setItemsAtsInforme(List<Ats> itemsAtsInforme) {
        this.itemsAtsInforme = itemsAtsInforme;
    }

    /**
     * @return the uploadArchivo
     */
    public UploadedFile getUploadArchivo() {
        return uploadArchivo;
    }

    /**
     * @param uploadArchivo the uploadArchivo to set
     */
    public void setUploadArchivo(UploadedFile uploadArchivo) {
        this.uploadArchivo = uploadArchivo;
    }

    /**
     * @return the archivoBajar
     */
    public StreamedContent getArchivoBajar() {
        return archivoBajar;
    }

    /**
     * @param archivoBajar the archivoBajar to set
     */
    public void setArchivoBajar(StreamedContent archivoBajar) {
        this.archivoBajar = archivoBajar;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the existeRendimiento
     */
    public boolean isExisteRendimiento() {
        return existeRendimiento;
    }

    /**
     * @param existeRendimiento the existeRendimiento to set
     */
    public void setExisteRendimiento(boolean existeRendimiento) {
        this.existeRendimiento = existeRendimiento;
    }

    /**
     * @return the archivoXml
     */
    public DefaultStreamedContent getArchivoXml() {
        return archivoXml;
    }

    /**
     * @param archivoXml the archivoXml to set
     */
    public void setArchivoXml(DefaultStreamedContent archivoXml) {
        this.archivoXml = archivoXml;
    }
}
