/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.EFECTIVIZACION_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EfectivizacionCheque.findAll", query = "SELECT e FROM EfectivizacionCheque e"),
    @NamedQuery(name = "EfectivizacionCheque.findByCodigo", query = "SELECT e FROM EfectivizacionCheque e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EfectivizacionCheque.findByCodigoUsuario", query = "SELECT e FROM EfectivizacionCheque e WHERE e.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "EfectivizacionCheque.findByCodigoComputador", query = "SELECT e FROM EfectivizacionCheque e WHERE e.codigoComputador = :codigoComputador"),
    @NamedQuery(name = "EfectivizacionCheque.findByCodigoIfipAgencia", query = "SELECT e FROM EfectivizacionCheque e WHERE e.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "EfectivizacionCheque.findByFecha", query = "SELECT e FROM EfectivizacionCheque e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EfectivizacionCheque.findByFechaSistema", query = "SELECT e FROM EfectivizacionCheque e WHERE e.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "EfectivizacionCheque.findByObservaciones", query = "SELECT e FROM EfectivizacionCheque e WHERE e.observaciones = :observaciones")})
public class EfectivizacionCheque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_EFECTIVIZACION_CHEQUE")
    @SequenceGenerator(name = "GSQ_EFECTIVIZACION_CHEQUE", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_EFECTIVIZACION_CHEQUE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR")
    private long codigoComputador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "efectivizacionCheque", fetch = FetchType.LAZY)
    private Collection<EfectivizacionChequeDetalle> efectivizacionChequeDetalleCollection;

    public EfectivizacionCheque() {
    }

    public EfectivizacionCheque(Long codigo) {
        this.codigo = codigo;
    }

    public EfectivizacionCheque(Long codigo, long codigoUsuario, long codigoComputador, long codigoIfipAgencia, Date fecha, Date fechaSistema, String observaciones) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.codigoComputador = codigoComputador;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fecha = fecha;
        this.fechaSistema = fechaSistema;
        this.observaciones = observaciones;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoComputador() {
        return codigoComputador;
    }

    public void setCodigoComputador(long codigoComputador) {
        this.codigoComputador = codigoComputador;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<EfectivizacionChequeDetalle> getEfectivizacionChequeDetalleCollection() {
        return efectivizacionChequeDetalleCollection;
    }

    public void setEfectivizacionChequeDetalleCollection(Collection<EfectivizacionChequeDetalle> efectivizacionChequeDetalleCollection) {
        this.efectivizacionChequeDetalleCollection = efectivizacionChequeDetalleCollection;
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
        if (!(object instanceof EfectivizacionCheque)) {
            return false;
        }
        EfectivizacionCheque other = (EfectivizacionCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.EfectivizacionCheque[ codigo=" + codigo + " ]";
    }
    
}
