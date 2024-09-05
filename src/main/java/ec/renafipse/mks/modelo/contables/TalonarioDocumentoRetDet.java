/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.TALONARIO_DOCUMENTO_RET_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioDocumentoRetDet.findAll", query = "SELECT t FROM TalonarioDocumentoRetDet t"),
    @NamedQuery(name = "TalonarioDocumentoRetDet.findByCodigo", query = "SELECT t FROM TalonarioDocumentoRetDet t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TalonarioDocumentoRetDet.findBySerie", query = "SELECT t FROM TalonarioDocumentoRetDet t WHERE t.serie = :serie"),
    @NamedQuery(name = "TalonarioDocumentoRetDet.findByNumero", query = "SELECT t FROM TalonarioDocumentoRetDet t WHERE t.numero = :numero"),
    @NamedQuery(name = "TalonarioDocumentoRetDet.findByEstado", query = "SELECT t FROM TalonarioDocumentoRetDet t WHERE t.estado = :estado"),
    @NamedQuery(name = "TalonarioDocumentoRetDet.findByFechaEstado", query = "SELECT t FROM TalonarioDocumentoRetDet t WHERE t.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "TalonarioDocumentoRetDet.findByEstadoColocadoPor", query = "SELECT t FROM TalonarioDocumentoRetDet t WHERE t.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "TalonarioDocumentoRetDet.findBySerieTalonarioSerieDocumento", query = "SELECT t FROM TalonarioDocumentoRetDet t JOIN t.talonarioDocumentoRetencion r WHERE r.serie = :serie AND t.serie=:serieDocumento")
})
public class TalonarioDocumentoRetDet implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findBySerieTalonarioSerieDocumento = "TalonarioDocumentoRetDet.findBySerieTalonarioSerieDocumento";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIE")
    private long serie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "talonarioDocumentoRetDet", fetch = FetchType.LAZY)
    private AnulacionDocumentoRetencion anulacionDocumentoRetencion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "talonarioDocumentoRetDet", fetch = FetchType.LAZY)
    private Collection<DocumentoRetencion> documentoRetencionCollection;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TALONARIO", referencedColumnName = "CODIGO", insertable = false, updatable =  false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TalonarioDocumentoRetencion talonarioDocumentoRetencion;

    public TalonarioDocumentoRetDet() {
    }

    public TalonarioDocumentoRetDet(Long codigo) {
        this.codigo = codigo;
    }

    public TalonarioDocumentoRetDet(Long codigo, long serie, String numero, char estado, Date fechaEstado, long estadoColocadoPor) {
        this.codigo = codigo;
        this.serie = serie;
        this.numero = numero;
        this.estado = estado;
        this.fechaEstado = fechaEstado;
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getSerie() {
        return serie;
    }

    public void setSerie(long serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public long getEstadoColocadoPor() {
        return estadoColocadoPor;
    }

    public void setEstadoColocadoPor(long estadoColocadoPor) {
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public AnulacionDocumentoRetencion getAnulacionDocumentoRetencion() {
        return anulacionDocumentoRetencion;
    }

    public void setAnulacionDocumentoRetencion(AnulacionDocumentoRetencion anulacionDocumentoRetencion) {
        this.anulacionDocumentoRetencion = anulacionDocumentoRetencion;
    }

    @XmlTransient
    public Collection<DocumentoRetencion> getDocumentoRetencionCollection() {
        return documentoRetencionCollection;
    }

    public void setDocumentoRetencionCollection(Collection<DocumentoRetencion> documentoRetencionCollection) {
        this.documentoRetencionCollection = documentoRetencionCollection;
    }

    public TalonarioDocumentoRetencion getTalonarioDocumentoRetencion() {
        return talonarioDocumentoRetencion;
    }

    public void setTalonarioDocumentoRetencion(TalonarioDocumentoRetencion talonarioDocumentoRetencion) {
        this.talonarioDocumentoRetencion = talonarioDocumentoRetencion;
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
        if (!(object instanceof TalonarioDocumentoRetDet)) {
            return false;
        }
        TalonarioDocumentoRetDet other = (TalonarioDocumentoRetDet) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetDet[ codigo=" + codigo + " ]";
    }
    
}
