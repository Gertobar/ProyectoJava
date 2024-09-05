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
@Table(name = "MKS_AHORROS.LICITUD_FON_ORG_DEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFonOrgDest.findAll", query = "SELECT l FROM LicitudFonOrgDest l"),
    @NamedQuery(name = "LicitudFonOrgDest.findByCodigo", query = "SELECT l FROM LicitudFonOrgDest l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LicitudFonOrgDest.findByNombre", query = "SELECT l FROM LicitudFonOrgDest l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "LicitudFonOrgDest.findByDescripcion", query = "SELECT l FROM LicitudFonOrgDest l WHERE l.descripcion = :descripcion"),
    @NamedQuery(name = "LicitudFonOrgDest.findByEsOrigen", query = "SELECT l FROM LicitudFonOrgDest l WHERE l.esOrigen = :esOrigen"),
    @NamedQuery(name = "LicitudFonOrgDest.findByEsOrigenEliminado", query = "SELECT l FROM LicitudFonOrgDest l WHERE  l.esOrigen = :esOrigen AND l.eliminado = :eliminado")})


public class LicitudFonOrgDest implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEsOrigenEliminado = "LicitudFonOrgDest.findByEsOrigenEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_ORIGEN")
    private char esOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
   

    public LicitudFonOrgDest() {
    }

    public LicitudFonOrgDest(Long codigo) {
        this.codigo = codigo;
    }

    public LicitudFonOrgDest(Long codigo, String nombre, String descripcion, char esOrigen, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esOrigen = esOrigen;
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

    public char getEsOrigen() {
        return esOrigen;
    }

    public void setEsOrigen(char esOrigen) {
        this.esOrigen = esOrigen;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof LicitudFonOrgDest)) {
            return false;
        }
        LicitudFonOrgDest other = (LicitudFonOrgDest) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFonOrgDest[ codigo=" + codigo + " ]";
    }
    
}
