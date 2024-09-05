/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.DOCUMENTOS_PROVEEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosProveedor.findAll", query = "SELECT d FROM DocumentosProveedor d"),
    @NamedQuery(name = "DocumentosProveedor.findByCodigoProveedor", query = "SELECT d FROM DocumentosProveedor d WHERE d.documentosProveedorPK.codigoProveedor = :codigoProveedor"),
    @NamedQuery(name = "DocumentosProveedor.findByCodigoTipoComprobante", query = "SELECT d FROM DocumentosProveedor d WHERE d.documentosProveedorPK.codigoTipoComprobante = :codigoTipoComprobante"),
    @NamedQuery(name = "DocumentosProveedor.findByCodigoProveedorTipoComprobanteSerieIF", query = "SELECT d FROM DocumentosProveedor d WHERE d.documentosProveedorPK.codigoProveedor = :codigoProveedor AND d.documentosProveedorPK.codigoTipoComprobante = :codigoTipoComprobante AND d.documentosProveedorPK.serieDocumentos LIKE :serie AND d.inicioSerie = :inicioSerie AND d.finSerie=:finSerie"),
    @NamedQuery(name = "DocumentosProveedor.findByCodigoProveedorTipoComprobanteAut", query = "SELECT d FROM DocumentosProveedor d WHERE d.documentosProveedorPK.codigoProveedor = :codigoProveedor AND d.documentosProveedorPK.codigoTipoComprobante = :codigoTipoComprobante AND d.numeroAutorizacion =:numeroAutorizacion"),
    @NamedQuery(name = "DocumentosProveedor.findByCodProvTipoComprFechaEmision", query = "SELECT d FROM DocumentosProveedor d WHERE d.documentosProveedorPK.codigoProveedor = :codigoProveedor AND d.documentosProveedorPK.codigoTipoComprobante = :codigoTipoComprobante AND d.documentosProveedorPK.fechaEmision =:fechaEmision"),
    @NamedQuery(name = "DocumentosProveedor.findByCodigoProveedorTipoComprobanteSerie", query = "SELECT d FROM DocumentosProveedor d WHERE d.documentosProveedorPK.codigoProveedor = :codigoProveedor AND d.documentosProveedorPK.codigoTipoComprobante = :codigoTipoComprobante AND d.documentosProveedorPK.serieDocumentos LIKE :serie AND :numeroSerie BETWEEN d.inicioSerie AND d.finSerie"),
    @NamedQuery(name = "DocumentosProveedor.findByFechaEmision", query = "SELECT d FROM DocumentosProveedor d WHERE d.documentosProveedorPK.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "DocumentosProveedor.findByFechaCaduca", query = "SELECT d FROM DocumentosProveedor d WHERE d.fechaCaduca = :fechaCaduca"),
    @NamedQuery(name = "DocumentosProveedor.findByNumeroAutorizacion", query = "SELECT d FROM DocumentosProveedor d WHERE d.numeroAutorizacion = :numeroAutorizacion"),
    @NamedQuery(name = "DocumentosProveedor.findByDigitosComprobante", query = "SELECT d FROM DocumentosProveedor d WHERE d.digitosComprobante = :digitosComprobante"),
    @NamedQuery(name = "DocumentosProveedor.findByFechaIngreso", query = "SELECT d FROM DocumentosProveedor d WHERE d.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "DocumentosProveedor.findByRegistradoPor", query = "SELECT d FROM DocumentosProveedor d WHERE d.registradoPor = :registradoPor")})
public class DocumentosProveedor implements Serializable {

    public static final String findByCodigoProveedor = "DocumentosProveedor.findByCodigoProveedor";
    public static final String findByCodigoProveedorCodigoComprobanteNumeroSerie = "DocumentosProveedor.findByCodigoProveedorTipoComprobanteSerie";
    public static final String findByCodigoProveedorTipoComprobanteSerieIF = "DocumentosProveedor.findByCodigoProveedorTipoComprobanteSerieIF";
    public static final String findByCodigoProveedorTipoComprobanteAut = "DocumentosProveedor.findByCodigoProveedorTipoComprobanteAut";
    public static final String findByCodProvTipoComprFechaEmision="DocumentosProveedor.findByCodProvTipoComprFechaEmision";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentosProveedorPK documentosProveedorPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CADUCA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaduca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NUMERO_AUTORIZACION")
    private String numeroAutorizacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIGITOS_COMPROBANTE")
    private short digitosComprobante;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    
 
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;

    // personalizadas
    @Basic(optional = false)
    @NotNull
    @Column(name = "INICIO_SERIE")
    private long inicioSerie;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FIN_SERIE")
    private long finSerie;

//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "SERIE")
//    @Size(min = 7, max = 7)
//    private String serieDocumentos;

    @JoinColumn(name = "CODIGO_TIPO_COMPROBANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoComprobanteCompra tipoComprobanteCompra;
    @JoinColumn(name = "CODIGO_PROVEEDOR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;

    public DocumentosProveedor() {
    }

    public DocumentosProveedor(DocumentosProveedorPK documentosProveedorPK) {
        this.documentosProveedorPK = documentosProveedorPK;
    }

    public DocumentosProveedor(DocumentosProveedorPK documentosProveedorPK, Date fechaCaduca, String numeroAutorizacion, short digitosComprobante, long registradoPor) {
        this.documentosProveedorPK = documentosProveedorPK;
        this.fechaCaduca = fechaCaduca;
        this.numeroAutorizacion = numeroAutorizacion;
        this.digitosComprobante = digitosComprobante;
        this.registradoPor = registradoPor;
    }

    public DocumentosProveedor(long codigoProveedor, long codigoTipoComprobante, Date fechaEmision, String serieDocumentos) {
        this.documentosProveedorPK = new DocumentosProveedorPK(codigoProveedor, codigoTipoComprobante, fechaEmision,serieDocumentos);
    }

    public DocumentosProveedorPK getDocumentosProveedorPK() {
        return documentosProveedorPK;
    }

    public void setDocumentosProveedorPK(DocumentosProveedorPK documentosProveedorPK) {
        this.documentosProveedorPK = documentosProveedorPK;
    }

    public Date getFechaCaduca() {
        return fechaCaduca;
    }

    public void setFechaCaduca(Date fechaCaduca) {
        this.fechaCaduca = fechaCaduca;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public short getDigitosComprobante() {
        return digitosComprobante;
    }

    public void setDigitosComprobante(short digitosComprobante) {
        this.digitosComprobante = digitosComprobante;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public TipoComprobanteCompra getTipoComprobanteCompra() {
        return tipoComprobanteCompra;
    }

    public void setTipoComprobanteCompra(TipoComprobanteCompra tipoComprobanteCompra) {
        this.tipoComprobanteCompra = tipoComprobanteCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentosProveedorPK != null ? documentosProveedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosProveedor)) {
            return false;
        }
        DocumentosProveedor other = (DocumentosProveedor) object;
        if ((this.documentosProveedorPK == null && other.documentosProveedorPK != null) || (this.documentosProveedorPK != null && !this.documentosProveedorPK.equals(other.documentosProveedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.DocumentosProveedor[ documentosProveedorPK=" + documentosProveedorPK + " ]";
    }

    /**
     * @return the inicioSerie
     */
    public long getInicioSerie() {
        return inicioSerie;
    }

    /**
     * @param inicioSerie the inicioSerie to set
     */
    public void setInicioSerie(long inicioSerie) {
        this.inicioSerie = inicioSerie;
    }

    /**
     * @return the finSerie
     */
    public long getFinSerie() {
        return finSerie;
    }

    /**
     * @param finSerie the finSerie to set
     */
    public void setFinSerie(long finSerie) {
        this.finSerie = finSerie;
    }

    /**
     * @return the serieDocumentos
     */
//    public String getSerieDocumentos() {
//        return serieDocumentos;
//    }
//
//    /**
//     * @param serieDocumentos the serieDocumentos to set
//     */
//    public void setSerieDocumentos(String serieDocumentos) {
//        this.serieDocumentos = serieDocumentos;
//    }
 

}
