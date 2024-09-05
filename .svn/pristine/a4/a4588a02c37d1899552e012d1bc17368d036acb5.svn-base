/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_SOCIOS.SOCIO_FLUJO_CAJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioFlujoCaja.findAll", query = "SELECT s FROM SocioFlujoCaja s"),
    @NamedQuery(name = "SocioFlujoCaja.findByTotalIngresosSocio", query = "SELECT s FROM SocioFlujoCaja s WHERE s.totalIngresosSocio = :totalIngresosSocio"),
    @NamedQuery(name = "SocioFlujoCaja.findByTotalIngresosConyuge", query = "SELECT s FROM SocioFlujoCaja s WHERE s.totalIngresosConyuge = :totalIngresosConyuge"),
    @NamedQuery(name = "SocioFlujoCaja.findByTotalGastosSocio", query = "SELECT s FROM SocioFlujoCaja s WHERE s.totalGastosSocio = :totalGastosSocio"),
    @NamedQuery(name = "SocioFlujoCaja.findBySaldo", query = "SELECT s FROM SocioFlujoCaja s WHERE s.saldo = :saldo"),
    @NamedQuery(name = "SocioFlujoCaja.findByCobertura", query = "SELECT s FROM SocioFlujoCaja s WHERE s.cobertura = :cobertura"),
    @NamedQuery(name = "SocioFlujoCaja.findByFechaIngreso", query = "SELECT s FROM SocioFlujoCaja s WHERE s.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "SocioFlujoCaja.findByCodigoUsuarioIngreso", query = "SELECT s FROM SocioFlujoCaja s WHERE s.codigoUsuarioIngreso = :codigoUsuarioIngreso"),
    @NamedQuery(name = "SocioFlujoCaja.findByFechaActualizacion", query = "SELECT s FROM SocioFlujoCaja s WHERE s.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "SocioFlujoCaja.findByCodigoUsuarioActualizo", query = "SELECT s FROM SocioFlujoCaja s WHERE s.codigoUsuarioActualizo = :codigoUsuarioActualizo"),
    @NamedQuery(name = "SocioFlujoCaja.findByCodigoPersona", query = "SELECT s FROM SocioFlujoCaja s WHERE s.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "SocioFlujoCaja.findByCodigoSocio", query = "SELECT s FROM SocioFlujoCaja s WHERE s.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "SocioFlujoCaja.findByCodigoIfip", query = "SELECT s FROM SocioFlujoCaja s WHERE s.codigoIfip = :codigoIfip"),
    //personalizadas
    @NamedQuery(name = "SocioFlujoCaja.findByCodigoSocioIfip", query = "SELECT s FROM SocioFlujoCaja s WHERE s.codigoSocio = :codigoSocio AND s.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "SocioFlujoCaja.findByCodigoPersonaIfip", query = "SELECT s FROM SocioFlujoCaja s WHERE s.codigoPersona = :codigoPersona AND s.codigoIfip = :codigoIfip")    
    })
public class SocioFlujoCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSocioIfip ="SocioFlujoCaja.findByCodigoSocioIfip";
    @Column(name = "CODIGO_SOCIO")
    private Long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_INGRESOS_SOCIO")
    private BigDecimal totalIngresosSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_INGRESOS_CONYUGE")
    private BigDecimal totalIngresosConyuge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_GASTOS_SOCIO")
    private BigDecimal totalGastosSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COBERTURA")
    private BigDecimal cobertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_INGRESO")
    private long codigoUsuarioIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO_ACTUALIZO")
    private long codigoUsuarioActualizo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Long codigoPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socioFlujoCaja")
    private Collection<SocioFlujoCajaIngreso> socioFlujoCajaIngresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socioFlujoCaja")
    private Collection<SocioFlujoCajaEgreso> socioFlujoCajaEgresoCollection;

    public SocioFlujoCaja() {
    }

    public SocioFlujoCaja(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }
    
    public SocioFlujoCaja(Long codigoPersona, Long codigoSocio, Long codigoIfip) {
        this.codigoPersona = codigoPersona;
        this.codigoSocio = codigoSocio;
        this.codigoIfip = codigoIfip;
    }
    
    public SocioFlujoCaja(Long codigoPersona, BigDecimal totalIngresosSocio, BigDecimal totalIngresosConyuge, BigDecimal totalGastosSocio, BigDecimal saldo, BigDecimal cobertura, Date fechaIngreso, long codigoUsuarioIngreso, Date fechaActualizacion, long codigoUsuarioActualizo, long codigoIfip) {
        this.codigoPersona = codigoPersona;
        this.totalIngresosSocio = totalIngresosSocio;
        this.totalIngresosConyuge = totalIngresosConyuge;
        this.totalGastosSocio = totalGastosSocio;
        this.saldo = saldo;
        this.cobertura = cobertura;
        this.fechaIngreso = fechaIngreso;
        this.codigoUsuarioIngreso = codigoUsuarioIngreso;
        this.fechaActualizacion = fechaActualizacion;
        this.codigoUsuarioActualizo = codigoUsuarioActualizo;
        this.codigoIfip = codigoIfip;
    }

    public BigDecimal getTotalIngresosSocio() {
        return totalIngresosSocio;
    }

    public void setTotalIngresosSocio(BigDecimal totalIngresosSocio) {
        this.totalIngresosSocio = totalIngresosSocio;
    }

    public BigDecimal getTotalIngresosConyuge() {
        return totalIngresosConyuge;
    }

    public void setTotalIngresosConyuge(BigDecimal totalIngresosConyuge) {
        this.totalIngresosConyuge = totalIngresosConyuge;
    }

    public BigDecimal getTotalGastosSocio() {
        return totalGastosSocio;
    }

    public void setTotalGastosSocio(BigDecimal totalGastosSocio) {
        this.totalGastosSocio = totalGastosSocio;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getCobertura() {
        return cobertura;
    }

    public void setCobertura(BigDecimal cobertura) {
        this.cobertura = cobertura;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public long getCodigoUsuarioIngreso() {
        return codigoUsuarioIngreso;
    }

    public void setCodigoUsuarioIngreso(long codigoUsuarioIngreso) {
        this.codigoUsuarioIngreso = codigoUsuarioIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getCodigoUsuarioActualizo() {
        return codigoUsuarioActualizo;
    }

    public void setCodigoUsuarioActualizo(long codigoUsuarioActualizo) {
        this.codigoUsuarioActualizo = codigoUsuarioActualizo;
    }

    public Long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }
    
    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }
    
    @XmlTransient
    public Collection<SocioFlujoCajaIngreso> getSocioFlujoCajaIngresoCollection() {
        return socioFlujoCajaIngresoCollection;
    }
    
    @XmlTransient
    public Collection<SocioFlujoCajaEgreso> getSocioFlujoCajaEgresoCollection() {
        return socioFlujoCajaEgresoCollection;
    }

    public void setSocioFlujoCajaEgresoCollection(Collection<SocioFlujoCajaEgreso> socioFlujoCajaEgresoCollection) {
        this.socioFlujoCajaEgresoCollection = socioFlujoCajaEgresoCollection;
    }
    
    public void setSocioFlujoCajaIngresoCollection(Collection<SocioFlujoCajaIngreso> socioFlujoCajaIngresoCollection) {
        this.socioFlujoCajaIngresoCollection = socioFlujoCajaIngresoCollection;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPersona != null ? codigoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioFlujoCaja)) {
            return false;
        }
        SocioFlujoCaja other = (SocioFlujoCaja) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioFlujoCaja[ codigoPersona=" + codigoPersona + " ]";
    }
    
}
