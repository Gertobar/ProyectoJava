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
@Table(name = "MKS_CONTABLES.DOCUMENTO_RETENCION_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoRetencionDpf.findAll", query = "SELECT d FROM DocumentoRetencionDpf d"),
    @NamedQuery(name = "DocumentoRetencionDpf.findByCodigoRetencion", query = "SELECT d FROM DocumentoRetencionDpf d WHERE d.documentoRetencionDpfPK.codigoRetencion = :codigoRetencion"),
    @NamedQuery(name = "DocumentoRetencionDpf.findByCodigoDocumento", query = "SELECT d FROM DocumentoRetencionDpf d WHERE d.documentoRetencionDpfPK.codigoDocumento = :codigoDocumento"),
    @NamedQuery(name = "DocumentoRetencionDpf.findByEstado", query = "SELECT d FROM DocumentoRetencionDpf d WHERE d.estado = :estado"),
    @NamedQuery(name = "DocumentoRetencionDpf.findByEstadoColocadoPor", query = "SELECT d FROM DocumentoRetencionDpf d WHERE d.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "DocumentoRetencionDpf.findByFechaEstado", query = "SELECT d FROM DocumentoRetencionDpf d WHERE d.fechaEstado = :fechaEstado"),
    //Personalizados
    @NamedQuery(name = "DocumentoRetencionDpf.findByContratoDpf", query = "SELECT d FROM DocumentoRetencionDpf d JOIN d.retencionDpf r WHERE r.codigoContrato = :codigoContrato")
})
public class DocumentoRetencionDpf implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String  findByContratoDpf = "DocumentoRetencionDpf.findByContratoDpf";
    @EmbeddedId
    protected DocumentoRetencionDpfPK documentoRetencionDpfPK;
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
    private RetencionDpf retencionDpf;

    public DocumentoRetencionDpf() {
    }

    public DocumentoRetencionDpf(DocumentoRetencionDpfPK documentoRetencionDpfPK) {
        this.documentoRetencionDpfPK = documentoRetencionDpfPK;
    }

    public DocumentoRetencionDpf(DocumentoRetencionDpfPK documentoRetencionDpfPK, char estado, long estadoColocadoPor, Date fechaEstado) {
        this.documentoRetencionDpfPK = documentoRetencionDpfPK;
        this.estado = estado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEstado = fechaEstado;
    }

    public DocumentoRetencionDpf(long codigoRetencion, long codigoDocumento) {
        this.documentoRetencionDpfPK = new DocumentoRetencionDpfPK(codigoRetencion, codigoDocumento);
    }

    public DocumentoRetencionDpfPK getDocumentoRetencionDpfPK() {
        return documentoRetencionDpfPK;
    }

    public void setDocumentoRetencionDpfPK(DocumentoRetencionDpfPK documentoRetencionDpfPK) {
        this.documentoRetencionDpfPK = documentoRetencionDpfPK;
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

    public RetencionDpf getRetencionDpf() {
        return retencionDpf;
    }

    public void setRetencionDpf(RetencionDpf retencionDpf) {
        this.retencionDpf = retencionDpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentoRetencionDpfPK != null ? documentoRetencionDpfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoRetencionDpf)) {
            return false;
        }
        DocumentoRetencionDpf other = (DocumentoRetencionDpf) object;
        if ((this.documentoRetencionDpfPK == null && other.documentoRetencionDpfPK != null) || (this.documentoRetencionDpfPK != null && !this.documentoRetencionDpfPK.equals(other.documentoRetencionDpfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.DocumentoRetencionDpf[ documentoRetencionDpfPK=" + documentoRetencionDpfPK + " ]";
    }
    
}
