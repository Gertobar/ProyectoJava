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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vastudillo
 */
@Embeddable
public class DocumentosProveedorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROVEEDOR")
    private long codigoProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_COMPROBANTE")
    private long codigoTipoComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EMISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIE")
    @Size(min = 7, max = 7)
    private String serieDocumentos;

    public DocumentosProveedorPK() {
    }

    public DocumentosProveedorPK(long codigoProveedor, long codigoTipoComprobante, Date fechaEmision,String serieDocumentos) {
        this.codigoProveedor = codigoProveedor;
        this.codigoTipoComprobante = codigoTipoComprobante;
        this.fechaEmision = fechaEmision;
        this.serieDocumentos=serieDocumentos;
    }

    public long getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public long getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(long codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoProveedor;
        hash += (int) codigoTipoComprobante;
        hash += (fechaEmision != null ? fechaEmision.hashCode() : 0);
        hash += (serieDocumentos != null ? serieDocumentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosProveedorPK)) {
            return false;
        }
        DocumentosProveedorPK other = (DocumentosProveedorPK) object;
        if (this.codigoProveedor != other.codigoProveedor) {
            return false;
        }
        if (this.codigoTipoComprobante != other.codigoTipoComprobante) {
            return false;
        }
        if ((this.fechaEmision == null && other.fechaEmision != null) || (this.fechaEmision != null && !this.fechaEmision.equals(other.fechaEmision))) {
            return false;
        }
        if ((this.serieDocumentos == null && other.serieDocumentos != null) || (this.serieDocumentos != null && !this.serieDocumentos.equals(other.serieDocumentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.DocumentosProveedorPK[ codigoProveedor=" + codigoProveedor + ", codigoTipoComprobante=" + codigoTipoComprobante + ", fechaEmision=" + fechaEmision+ ", serie=" + serieDocumentos + " ]";
    }

    /**
     * @return the serieDocumentos
     */
    public String getSerieDocumentos() {
        return serieDocumentos;
    }

    /**
     * @param serieDocumentos the serieDocumentos to set
     */
    public void setSerieDocumentos(String serieDocumentos) {
        this.serieDocumentos = serieDocumentos;
    }
    
}
