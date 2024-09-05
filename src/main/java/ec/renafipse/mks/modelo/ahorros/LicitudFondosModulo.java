/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_MODULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosModulo.findAll", query = "SELECT l FROM LicitudFondosModulo l"),
    @NamedQuery(name = "LicitudFondosModulo.findByCodigo", query = "SELECT l FROM LicitudFondosModulo l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LicitudFondosModulo.findByNombre", query = "SELECT l FROM LicitudFondosModulo l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "LicitudFondosModulo.findByDescripcion", query = "SELECT l FROM LicitudFondosModulo l WHERE l.descripcion = :descripcion"),
    @NamedQuery(name = "LicitudFondosModulo.findByEliminado", query = "SELECT l FROM LicitudFondosModulo l WHERE l.eliminado = :eliminado")})
public class LicitudFondosModulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_ORIGEN")
    private char esOrigen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoModulo", fetch = FetchType.LAZY)
    private Collection<LicitudFondosPerExcFor> licitudFondosPerExcForCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoModulo", fetch = FetchType.LAZY)
    private Collection<LicitudFondosFormulario> licitudFondosFormularioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoModulo", fetch = FetchType.LAZY)
    private Collection<LicitudFondosControl> licitudFondosControlCollection;

    public LicitudFondosModulo() {
    }

    public LicitudFondosModulo(Long codigo) {
        this.codigo = codigo;
    }

    public LicitudFondosModulo(Long codigo, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public Collection<LicitudFondosPerExcFor> getLicitudFondosPerExcForCollection() {
        return licitudFondosPerExcForCollection;
    }

    public void setLicitudFondosPerExcForCollection(Collection<LicitudFondosPerExcFor> licitudFondosPerExcForCollection) {
        this.licitudFondosPerExcForCollection = licitudFondosPerExcForCollection;
    }

    @XmlTransient
    public Collection<LicitudFondosFormulario> getLicitudFondosFormularioCollection() {
        return licitudFondosFormularioCollection;
    }

    public void setLicitudFondosFormularioCollection(Collection<LicitudFondosFormulario> licitudFondosFormularioCollection) {
        this.licitudFondosFormularioCollection = licitudFondosFormularioCollection;
    }

    @XmlTransient
    public Collection<LicitudFondosControl> getLicitudFondosControlCollection() {
        return licitudFondosControlCollection;
    }

    public void setLicitudFondosControlCollection(Collection<LicitudFondosControl> licitudFondosControlCollection) {
        this.licitudFondosControlCollection = licitudFondosControlCollection;
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
        if (!(object instanceof LicitudFondosModulo)) {
            return false;
        }
        LicitudFondosModulo other = (LicitudFondosModulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosModulo[ codigo=" + codigo + " ]";
    }

    /**
     * @return the esOrigen
     */
    public char getEsOrigen() {
        return esOrigen;
    }

    /**
     * @param esOrigen the esOrigen to set
     */
    public void setEsOrigen(char esOrigen) {
        this.esOrigen = esOrigen;
    }
    
}
