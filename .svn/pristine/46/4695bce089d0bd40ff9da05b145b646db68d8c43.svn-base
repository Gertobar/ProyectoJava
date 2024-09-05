/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.dpfs;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.DOCUMENTO_CONTRATO_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoContratoDpf.findAll", query = "SELECT d FROM DocumentoContratoDpf d"),
    @NamedQuery(name = "DocumentoContratoDpf.findByCodigoContrato", query = "SELECT d FROM DocumentoContratoDpf d WHERE d.documentoContratoDpfPK.codigoContrato = :codigoContrato"),
    @NamedQuery(name = "DocumentoContratoDpf.findByCodigoIfip", query = "SELECT d FROM DocumentoContratoDpf d WHERE d.documentoContratoDpfPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "DocumentoContratoDpf.findByCodigoDocumento", query = "SELECT d FROM DocumentoContratoDpf d WHERE d.documentoContratoDpfPK.codigoDocumento = :codigoDocumento"),
    @NamedQuery(name = "DocumentoContratoDpf.findByEstado", query = "SELECT d FROM DocumentoContratoDpf d WHERE d.estado = :estado"),
    @NamedQuery(name = "DocumentoContratoDpf.findByRegistradoPor", query = "SELECT d FROM DocumentoContratoDpf d WHERE d.registradoPor = :registradoPor"),
    @NamedQuery(name = "DocumentoContratoDpf.findByFechaRegistro", query = "SELECT d FROM DocumentoContratoDpf d WHERE d.fechaRegistro = :fechaRegistro"),
    // -- PERSONALIZADO
    @NamedQuery(name = "DocumentoContratoDpf.findByCodigoContratoEstado", query = "SELECT d FROM DocumentoContratoDpf d WHERE d.documentoContratoDpfPK.codigoIfip = :codigoIfip AND   d.documentoContratoDpfPK.codigoContrato = :codigoContrato AND d.estado = :estado")})
public class DocumentoContratoDpf implements Serializable {
    
    public static final String findByCodigoContratoEstado = "DocumentoContratoDpf.findByCodigoContratoEstado";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentoContratoDpfPK documentoContratoDpfPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "CODIGO_DOCUMENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TalonarioDocumentoDpfDet talonarioDocumentoDpfDet;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CONTRATO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ContratoDpf contratoDpf;

    public DocumentoContratoDpf() {
    }

    public DocumentoContratoDpf(DocumentoContratoDpfPK documentoContratoDpfPK) {
        this.documentoContratoDpfPK = documentoContratoDpfPK;
    }

    public DocumentoContratoDpf(DocumentoContratoDpfPK documentoContratoDpfPK, char estado, long registradoPor, Date fechaRegistro) {
        this.documentoContratoDpfPK = documentoContratoDpfPK;
        this.estado = estado;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
    }

    public DocumentoContratoDpf(long codigoContrato, long codigoIfip, long codigoDocumento) {
        this.documentoContratoDpfPK = new DocumentoContratoDpfPK(codigoContrato, codigoIfip, codigoDocumento);
    }

    public DocumentoContratoDpfPK getDocumentoContratoDpfPK() {
        return documentoContratoDpfPK;
    }

    public void setDocumentoContratoDpfPK(DocumentoContratoDpfPK documentoContratoDpfPK) {
        this.documentoContratoDpfPK = documentoContratoDpfPK;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TalonarioDocumentoDpfDet getTalonarioDocumentoDpfDet() {
        return talonarioDocumentoDpfDet;
    }

    public void setTalonarioDocumentoDpfDet(TalonarioDocumentoDpfDet talonarioDocumentoDpfDet) {
        this.talonarioDocumentoDpfDet = talonarioDocumentoDpfDet;
    }

    public ContratoDpf getContratoDpf() {
        return contratoDpf;
    }

    public void setContratoDpf(ContratoDpf contratoDpf) {
        this.contratoDpf = contratoDpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentoContratoDpfPK != null ? documentoContratoDpfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoContratoDpf)) {
            return false;
        }
        DocumentoContratoDpf other = (DocumentoContratoDpf) object;
        if ((this.documentoContratoDpfPK == null && other.documentoContratoDpfPK != null) || (this.documentoContratoDpfPK != null && !this.documentoContratoDpfPK.equals(other.documentoContratoDpfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.DocumentoContratoDpf[ documentoContratoDpfPK=" + documentoContratoDpfPK + " ]";
    }

}
