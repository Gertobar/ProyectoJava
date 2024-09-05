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
@Table(name = "MKS_SOCIOS.SOCIO_SITUACION_PATRIMONIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioSituacionPatrimonial.findAll", query = "SELECT s FROM SocioSituacionPatrimonial s"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByTotalActivos", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.totalActivos = :totalActivos"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByTotalPasivos", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.totalPasivos = :totalPasivos"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByTotalPatrimonio", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.totalPatrimonio = :totalPatrimonio"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByFechaIngreso", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByCodigoUsuarioIngreso", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.codigoUsuarioIngreso = :codigoUsuarioIngreso"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByCodigoUsuarioActualizo", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.codigoUsuarioActualizo = :codigoUsuarioActualizo"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByFechaActualizacion", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByCodigoPersona", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByCodigoSocio", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "SocioSituacionPatrimonial.findByCodigoIfip", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.codigoIfip = :codigoIfip"),
    //personalizadas
    @NamedQuery(name = "SocioSituacionPatrimonial.findByCodigoSocioIfip", query = "SELECT s FROM SocioSituacionPatrimonial s WHERE s.codigoSocio = :codigoSocio AND s.codigoIfip = :codigoIfip")
})
public class SocioSituacionPatrimonial implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CODIGO_SOCIO")
    private Long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_ACTIVOS")
    private BigDecimal totalActivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PASIVOS")
    private BigDecimal totalPasivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PATRIMONIO")
    private BigDecimal totalPatrimonio;
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
    @Column(name = "CODIGO_USUARIO_ACTUALIZO")
    private long codigoUsuarioActualizo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Long codigoPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socioSituacionPatrimonial")
    private Collection<SocioSituacionPatAct> socioSituacionPatActCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socioSituacionPatrimonial")
    private Collection<SocioSituacionPatPas> socioSituacionPatPasCollection;
    
    public SocioSituacionPatrimonial() {
    }

    public SocioSituacionPatrimonial(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }
    
    public SocioSituacionPatrimonial(Long codigoPersona, Long codigoSocio, Long codigoIfip) {
        this.codigoPersona = codigoPersona;
        this.codigoSocio = codigoSocio;
        this.codigoIfip = codigoIfip;
    }
     
    public SocioSituacionPatrimonial(Long codigoPersona, BigDecimal totalActivos, BigDecimal totalPasivos, BigDecimal totalPatrimonio, Date fechaIngreso, long codigoUsuarioIngreso, long codigoUsuarioActualizo, Date fechaActualizacion, long codigoIfip) {
        this.codigoPersona = codigoPersona;
        this.totalActivos = totalActivos;
        this.totalPasivos = totalPasivos;
        this.totalPatrimonio = totalPatrimonio;
        this.fechaIngreso = fechaIngreso;
        this.codigoUsuarioIngreso = codigoUsuarioIngreso;
        this.codigoUsuarioActualizo = codigoUsuarioActualizo;
        this.fechaActualizacion = fechaActualizacion;
        this.codigoIfip = codigoIfip;
    }

    public BigDecimal getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(BigDecimal totalActivos) {
        this.totalActivos = totalActivos;
    }

    public BigDecimal getTotalPasivos() {
        return totalPasivos;
    }

    public void setTotalPasivos(BigDecimal totalPasivos) {
        this.totalPasivos = totalPasivos;
    }

    public BigDecimal getTotalPatrimonio() {
        return totalPatrimonio;
    }

    public void setTotalPatrimonio(BigDecimal totalPatrimonio) {
        this.totalPatrimonio = totalPatrimonio;
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

    public long getCodigoUsuarioActualizo() {
        return codigoUsuarioActualizo;
    }

    public void setCodigoUsuarioActualizo(long codigoUsuarioActualizo) {
        this.codigoUsuarioActualizo = codigoUsuarioActualizo;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
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
    
    @XmlTransient
    public Collection<SocioSituacionPatAct> getSocioSituacionPatActCollection() {
        return socioSituacionPatActCollection;
    }

    public void setSocioSituacionPatActCollection(Collection<SocioSituacionPatAct> socioSituacionPatActCollection) {
        this.socioSituacionPatActCollection = socioSituacionPatActCollection;
    }

    @XmlTransient
    public Collection<SocioSituacionPatPas> getSocioSituacionPatPasCollection() {
        return socioSituacionPatPasCollection;
    }

    public void setSocioSituacionPatPasCollection(Collection<SocioSituacionPatPas> socioSituacionPatPasCollection) {
        this.socioSituacionPatPasCollection = socioSituacionPatPasCollection;
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
        if (!(object instanceof SocioSituacionPatrimonial)) {
            return false;
        }
        SocioSituacionPatrimonial other = (SocioSituacionPatrimonial) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonial[ codigoPersona=" + codigoPersona + " ]";
    }
    
}