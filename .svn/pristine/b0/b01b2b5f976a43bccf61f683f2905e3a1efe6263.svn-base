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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MKS_CONTABLES.SEGUIMIENTO_ESTADO_COMPROBANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoEstadoComprobante.findAll", query = "SELECT s FROM SeguimientoEstadoComprobante s"),
    @NamedQuery(name = "SeguimientoEstadoComprobante.findByCodigo", query = "SELECT s FROM SeguimientoEstadoComprobante s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SeguimientoEstadoComprobante.findByEstadoColocadoPor", query = "SELECT s FROM SeguimientoEstadoComprobante s WHERE s.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "SeguimientoEstadoComprobante.findByFechaEstado", query = "SELECT s FROM SeguimientoEstadoComprobante s WHERE s.fechaEstado = :fechaEstado")})
public class SeguimientoEstadoComprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_SEGUIMIENTO_ESTADO_COM")
    @SequenceGenerator(name = "GSQ_SEGUIMIENTO_ESTADO_COM", schema = "MKS_CONTABLES",  allocationSize = 0, sequenceName = "SEQ_SEGUIMIENTO_ESTADO_COM")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EstadoComprobante codigoEstado;
    @JoinColumn(name = "CODIGO_COMPROBANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comprobante codigoComprobante;

    public SeguimientoEstadoComprobante() {
    }

    public SeguimientoEstadoComprobante(Long codigo) {
        this.codigo = codigo;
    }

    public SeguimientoEstadoComprobante(Long codigo, long estadoColocadoPor, Date fechaEstado) {
        this.codigo = codigo;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEstado = fechaEstado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public EstadoComprobante getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(EstadoComprobante codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Comprobante getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(Comprobante codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
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
        if (!(object instanceof SeguimientoEstadoComprobante)) {
            return false;
        }
        SeguimientoEstadoComprobante other = (SeguimientoEstadoComprobante) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.SeguimientoEstadoComprobante[ codigo=" + codigo + " ]";
    }
    
}
