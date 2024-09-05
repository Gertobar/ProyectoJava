/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.DOCUMENTO_RETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoRetencion.findAll", query = "SELECT d FROM DocumentoRetencion d"),
    @NamedQuery(name = "DocumentoRetencion.findByCodigoRetencion", query = "SELECT d FROM DocumentoRetencion d WHERE d.documentoRetencionPK.codigoRetencion = :codigoRetencion"),
    @NamedQuery(name = "DocumentoRetencion.findByCodigoDocumento", query = "SELECT d FROM DocumentoRetencion d WHERE d.documentoRetencionPK.codigoDocumento = :codigoDocumento"),
    @NamedQuery(name = "DocumentoRetencion.findByEstado", query = "SELECT d FROM DocumentoRetencion d WHERE d.estado = :estado"),
    @NamedQuery(name = "DocumentoRetencion.findByEstadoColocadoPor", query = "SELECT d FROM DocumentoRetencion d WHERE d.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "DocumentoRetencion.findByFechaEstado", query = "SELECT d FROM DocumentoRetencion d WHERE d.fechaEstado = :fechaEstado"),
    //PERSONALIZADOS
    @NamedQuery(name = "DocumentoRetencion.findByDocumentoRetencionCompra", query = "SELECT d FROM DocumentoRetencion d JOIN d.retencion r JOIN r.compra c   WHERE c.codigo = :codigo AND c.codigoIfip = :codigoIfip AND c.codigoIfipAgencia = :codigoIfipAgencia AND d.estado = :estado"),
    @NamedQuery(name = "DocumentoRetencion.findByDocumentoRetencionEstado", query = "SELECT d FROM DocumentoRetencion d  WHERE d.documentoRetencionPK.codigoRetencion = :codigoRetencion AND d.estado = :estado")
})
public class DocumentoRetencion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByDocumentoRetencionCompra = "DocumentoRetencion.findByDocumentoRetencionCompra";
    public static final String findByDocumentoRetencionEstado = "DocumentoRetencion.findByDocumentoRetencionEstado";
    @EmbeddedId
    protected DocumentoRetencionPK documentoRetencionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @JoinColumn(name = "CODIGO_DOCUMENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TalonarioDocumentoRetDet talonarioDocumentoRetDet;
    @JoinColumn(name = "CODIGO_RETENCION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Retencion retencion;

    public DocumentoRetencion() {
    }

    public DocumentoRetencion(DocumentoRetencionPK documentoRetencionPK) {
        this.documentoRetencionPK = documentoRetencionPK;
    }

    public DocumentoRetencion(DocumentoRetencionPK documentoRetencionPK, char estado, long estadoColocadoPor, Date fechaEstado) {
        this.documentoRetencionPK = documentoRetencionPK;
        this.estado = estado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEstado = fechaEstado;
    }

    public DocumentoRetencion(long codigoRetencion, long codigoDocumento) {
        this.documentoRetencionPK = new DocumentoRetencionPK(codigoRetencion, codigoDocumento);
    }

    public DocumentoRetencionPK getDocumentoRetencionPK() {
        return documentoRetencionPK;
    }

    public void setDocumentoRetencionPK(DocumentoRetencionPK documentoRetencionPK) {
        this.documentoRetencionPK = documentoRetencionPK;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public long getEstadoColocadoPor() {
        return estadoColocadoPor;
    }

    public void setEstadoColocadoPor(long estadoColocadoPor) {
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public TalonarioDocumentoRetDet getTalonarioDocumentoRetDet() {
        return talonarioDocumentoRetDet;
    }

    public void setTalonarioDocumentoRetDet(TalonarioDocumentoRetDet talonarioDocumentoRetDet) {
        this.talonarioDocumentoRetDet = talonarioDocumentoRetDet;
    }

    public Retencion getRetencion() {
        return retencion;
    }

    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentoRetencionPK != null ? documentoRetencionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoRetencion)) {
            return false;
        }
        DocumentoRetencion other = (DocumentoRetencion) object;
        if ((this.documentoRetencionPK == null && other.documentoRetencionPK != null) || (this.documentoRetencionPK != null && !this.documentoRetencionPK.equals(other.documentoRetencionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.DocumentoRetencion[ documentoRetencionPK=" + documentoRetencionPK + " ]";
    }
    
}
