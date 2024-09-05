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
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author andres
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.DOCUMENTOS_IFIP_AGENCIA_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosIfipAgenciaDet.findAll", query = "SELECT d FROM DocumentosIfipAgenciaDet d"),
    @NamedQuery(name = "DocumentosIfipAgenciaDet.findByCodigo", query = "SELECT d FROM DocumentosIfipAgenciaDet d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DocumentosIfipAgenciaDet.findByFechaGeneracion", query = "SELECT d FROM DocumentosIfipAgenciaDet d WHERE d.fechaGeneracion = :fechaGeneracion"),
    @NamedQuery(name = "DocumentosIfipAgenciaDet.findByNumero", query = "SELECT d FROM DocumentosIfipAgenciaDet d WHERE d.numero = :numero"),
    @NamedQuery(name = "DocumentosIfipAgenciaDet.findByNumeroComprobante", query = "SELECT d FROM DocumentosIfipAgenciaDet d WHERE d.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "DocumentosIfipAgenciaDet.findByObservaciones", query = "SELECT d FROM DocumentosIfipAgenciaDet d WHERE d.observaciones = :observaciones")})
public class DocumentosIfipAgenciaDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_GENERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private long numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NUMERO_COMPROBANTE")
    private String numeroComprobante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "CODIGO_DOCUMENTO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private DocumentosIfipAgencia codigoDocumentoIfip;

    public DocumentosIfipAgenciaDet() {
    }

    public DocumentosIfipAgenciaDet(Long codigo) {
        this.codigo = codigo;
    }

    public DocumentosIfipAgenciaDet(Long codigo, Date fechaGeneracion, long numero, String numeroComprobante, String observaciones) {
        this.codigo = codigo;
        this.fechaGeneracion = fechaGeneracion;
        this.numero = numero;
        this.numeroComprobante = numeroComprobante;
        this.observaciones = observaciones;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public DocumentosIfipAgencia getCodigoDocumentoIfip() {
        return codigoDocumentoIfip;
    }

    public void setCodigoDocumentoIfip(DocumentosIfipAgencia codigoDocumentoIfip) {
        this.codigoDocumentoIfip = codigoDocumentoIfip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosIfipAgenciaDet)) {
            return false;
        }
        DocumentosIfipAgenciaDet other = (DocumentosIfipAgenciaDet) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.DocumentosIfipAgenciaDet[ codigo=" + codigo + " ]";
    }
    
}
