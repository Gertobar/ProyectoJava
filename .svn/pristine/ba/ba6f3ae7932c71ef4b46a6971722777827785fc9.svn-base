/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_TIPO_MOTIVO_EXC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosTipoMotivoExc.findAll", query = "SELECT l FROM LicitudFondosTipoMotivoExc l"),
    @NamedQuery(name = "LicitudFondosTipoMotivoExc.findByCodigo", query = "SELECT l FROM LicitudFondosTipoMotivoExc l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LicitudFondosTipoMotivoExc.findByDescripcion", query = "SELECT l FROM LicitudFondosTipoMotivoExc l WHERE l.descripcion = :descripcion"),
    @NamedQuery(name = "LicitudFondosTipoMotivoExc.findByEliminado", query = "SELECT l FROM LicitudFondosTipoMotivoExc l WHERE l.eliminado = :eliminado ORDER BY l.descripcion")})
public class LicitudFondosTipoMotivoExc implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "LicitudFondosTipoMotivoExc.findByEliminado";
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoMotivo")
    private Collection<LicitudFondosAprobarExc> licitudFondosAprobarExcCollection;

    public LicitudFondosTipoMotivoExc() {
    }

    public LicitudFondosTipoMotivoExc(Long codigo) {
        this.codigo = codigo;
    }

    public LicitudFondosTipoMotivoExc(Long codigo, String descripcion, char eliminado) {
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<LicitudFondosAprobarExc> getLicitudFondosAprobarExcCollection() {
        return licitudFondosAprobarExcCollection;
    }

    public void setLicitudFondosAprobarExcCollection(Collection<LicitudFondosAprobarExc> licitudFondosAprobarExcCollection) {
        this.licitudFondosAprobarExcCollection = licitudFondosAprobarExcCollection;
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
        if (!(object instanceof LicitudFondosTipoMotivoExc)) {
            return false;
        }
        LicitudFondosTipoMotivoExc other = (LicitudFondosTipoMotivoExc) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosTipoMotivoExc[ codigo=" + codigo + " ]";
    }
    
}
