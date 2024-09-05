/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.judiciales;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.TIPO_DOCUMENTO_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumentoCredito.findAll", query = "SELECT t FROM TipoDocumentoCredito t"),
    @NamedQuery(name = "TipoDocumentoCredito.findByCodigo", query = "SELECT t FROM TipoDocumentoCredito t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoDocumentoCredito.findByDescripcion", query = "SELECT t FROM TipoDocumentoCredito t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoDocumentoCredito.findByObservacion", query = "SELECT t FROM TipoDocumentoCredito t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TipoDocumentoCredito.findByEliminado", query = "SELECT t FROM TipoDocumentoCredito t WHERE t.eliminado = :eliminado")})
public class TipoDocumentoCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_DOCUMENTO_CREDITO")
    @SequenceGenerator(name = "GSQ_TIPO_DOCUMENTO_CREDITO", schema = "MKS_JUDICIALES", allocationSize = 0, sequenceName = "SEQ_TIPO_DOCUMENTO_CREDITO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoDocumentoCredito")
    private Collection<DocumentoCreditoEntregado> documentoCreditoEntregadoCollection;

    public TipoDocumentoCredito() {
    }

    public TipoDocumentoCredito(Long codigo) {
        this.codigo = codigo;
    }

    public TipoDocumentoCredito(Long codigo, String descripcion, String eliminado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<DocumentoCreditoEntregado> getDocumentoCreditoEntregadoCollection() {
        return documentoCreditoEntregadoCollection;
    }

    public void setDocumentoCreditoEntregadoCollection(Collection<DocumentoCreditoEntregado> documentoCreditoEntregadoCollection) {
        this.documentoCreditoEntregadoCollection = documentoCreditoEntregadoCollection;
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
        if (!(object instanceof TipoDocumentoCredito)) {
            return false;
        }
        TipoDocumentoCredito other = (TipoDocumentoCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.TipoDocumentoCredito[ codigo=" + codigo + " ]";
    }
    
}
