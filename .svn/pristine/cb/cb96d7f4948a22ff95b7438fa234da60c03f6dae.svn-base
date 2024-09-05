/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author Mascoop
 */
@Entity
@Table(name = "MKS_CREDITOS.SOLICITUD_APROBADO_PEN_DOC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudAprobadoPenDoc.findAll", query = "SELECT s FROM SolicitudAprobadoPenDoc s"),
    @NamedQuery(name = "SolicitudAprobadoPenDoc.findByNumeroCredito", query = "SELECT s FROM SolicitudAprobadoPenDoc s WHERE s.solicitud.solicitudPK.numero = :numeroCredito AND s.solicitud.solicitudPK.codigoIfip = :codigoIfip")})
public class SolicitudAprobadoPenDoc implements Serializable {
    public static String findByNumeroCredito = "SolicitudAprobadoPenDoc.findByNumeroCredito";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "APROBADO")
    private String aprobado;
    @Size(max = 200)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO",insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;

    public SolicitudAprobadoPenDoc() {
    }

    public SolicitudAprobadoPenDoc(Long codigo) {
        this.codigo = codigo;
    }

    public SolicitudAprobadoPenDoc(Long codigo, Date fechaRegistro, long registradoPor, String aprobado) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.aprobado = aprobado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof SolicitudAprobadoPenDoc)) {
            return false;
        }
        SolicitudAprobadoPenDoc other = (SolicitudAprobadoPenDoc) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.SolicitudAprobadoPenDoc[ codigo=" + codigo + " ]";
    }

    /**
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     * @param solicitud the solicitud to set
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    
}
