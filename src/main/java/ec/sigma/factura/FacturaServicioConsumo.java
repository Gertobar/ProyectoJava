package ec.sigma.factura;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Marco Paredes
 */
public class FacturaServicioConsumo {

//    private String rutaWS = "http://localhost:8080/sgmDE/FacturaService?WSDL";
    private String rutaWS = "http://172.25.0.6:8081/sgmDE/FacturaService?WSDL";
    private String errorProcesaServicio = null;
    private Integer numeroFactura = 0;

    private String itemId = "";
    private Double valor = 0.0;
    private String personaId = "";
    private String usuarioCod = "";
    private String trxOrigen = "";
    private String servicio = "";
    private String descripcionProducto = "";

    public void procesaServicio() {
        numeroFactura = 0;
        errorProcesaServicio = null;

        String xmlconsulta;
        Document xmlDocument;
        int httpEstado = 0;
        String xmlRespuesta = "";
        xmlconsulta = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://servicio.documento.sigma.ec/\">\n"
                + "   <soapenv:Header/>\n"
                + "   <soapenv:Body>\n"
                + "      <ser:generaFacturaSimple>\n"
                + "         <datos>\n"
                + "            <itemId>" + itemId + "</itemId>\n"
                + "            <valor>" + valor + "</valor>\n"
                + "            <personaId>" + personaId + "</personaId>\n"
                + "            <usuarioCod>" + usuarioCod + "</usuarioCod>\n"
                + "            <trxOrigen>" + trxOrigen + "</trxOrigen>\n"
                + "            <servicio>" + servicio + "</servicio>\n"
                + "            <descripcionProducto>" + descripcionProducto + "</descripcionProducto>\n"
                + "         </datos>\n"
                + "      </ser:generaFacturaSimple>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";

        InputStream inputStream = null;
//        System.out.println("\n\n\n");
//        System.out.println("==========URL============");
//        System.out.println(rutaWS);
//        System.out.println("==========Mensaje============");
//        System.out.println(xmlconsulta);
//        System.out.println("==========FIN Trazas============");

        URL url;
        HttpURLConnection connection;
        String responseString;
        try {
            url = new URL(rutaWS);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(xmlconsulta);
            outputStreamWriter.close();
            httpEstado = ((HttpURLConnection) connection).getResponseCode();
        } catch (Exception e) {
            errorProcesaServicio = "Error al genera la peticion al servicio" + e.getMessage();
            return;
        }

        try {
            if (httpEstado != HttpURLConnection.HTTP_OK) {
                inputStream = ((HttpURLConnection) connection).getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((responseString = bufferedReader.readLine()) != null) {
                xmlRespuesta = (xmlRespuesta + responseString);
            }
            bufferedReader.close();
            if (httpEstado != HttpURLConnection.HTTP_OK) {
                errorProcesaServicio = "Error con la respuesta de servicio codigo:" + httpEstado;
                return;
            }

        } catch (Exception e) {
            errorProcesaServicio = "Error al obtener la respuesta del servicio" + e.getMessage();
            return;
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlRespuesta));
            xmlDocument = db.parse(is);

            if (xmlDocument == null) {
                errorProcesaServicio = "No se ha podido recuperar el documento";
                return;
            }
            //valido que exista el nodo principal y por enede una repuesta
            NodeList nodeLst = xmlDocument.getElementsByTagName("return");
            if (nodeLst.getLength() == 0) {
                errorProcesaServicio = "No se ha podido recuperar el resultado";
                return;
            }
            for (int i = 0; i < nodeLst.getLength(); i++) {
                Node nodo = nodeLst.item(i);
                try {
                    String lsRetorno = nodo.getTextContent();
                    if (lsRetorno.startsWith("OK:")) {
                        numeroFactura =  Integer.valueOf(lsRetorno.substring(3));
                    } else {
                        errorProcesaServicio = lsRetorno;
                        return;
                    }
                } catch (Exception e) {
                    errorProcesaServicio = "Error al convertir el numero de factura desde el valor de respuesta:" + nodo.getTextContent();
                    return;
                }
            }
        } catch (Exception e) {
            errorProcesaServicio = "Error al procesar la respuesta del servicio" + e.getMessage();
        }

    }

/*
    public static void main(String[] args) {
        FacturaServicioConsumo factura = new FacturaServicioConsumo();
        factura.setOrgFacturaCod("MC_F");
        factura.setItemId("1");
        factura.setValor(15.0);
        factura.setPersonaId("25149");
        factura.setUsuarioCod("SYS");
        factura.setTrxOrigen("12345");
        factura.setServicio("cliente");
        factura.setDescripcionProducto("");

        factura.procesaServicio();
        if (factura.getErrorProcesaServicio() != null) {
            System.err.println("\n\nError:" + factura.getErrorProcesaServicio());
        } else {
            System.out.println("\n\nNumero Factura:" + factura.numeroFactura);
        }
    }
*/
    public String getRutaWS() {
        return rutaWS;
    }

    public void setRutaWS(String rutaWS) {
        this.rutaWS = rutaWS;
    }

    public String getErrorProcesaServicio() {
        return errorProcesaServicio;
    }

    public void setErrorProcesaServicio(String errorProcesaServicio) {
        this.errorProcesaServicio = errorProcesaServicio;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getUsuarioCod() {
        return usuarioCod;
    }

    public void setUsuarioCod(String usuarioCod) {
        this.usuarioCod = usuarioCod;
    }

    public String getTrxOrigen() {
        return trxOrigen;
    }

    public void setTrxOrigen(String trxOrigen) {
        this.trxOrigen = trxOrigen;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

}
