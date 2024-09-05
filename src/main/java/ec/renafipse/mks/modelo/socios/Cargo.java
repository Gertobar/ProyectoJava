/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.CARGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByCodigo", query = "SELECT c FROM Cargo c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Cargo.findByCodigoOc", query = "SELECT c FROM Cargo c WHERE c.codigoOc = :codigoOc"),
    @NamedQuery(name = "Cargo.findByNombre", query = "SELECT c FROM Cargo c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cargo.findByEliminado", query = "SELECT c FROM Cargo c WHERE c.eliminado = :eliminado ORDER BY c.nombre")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "Cargo.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CARGO")
    @SequenceGenerator(name = "GSQ_CARGO", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_CARGO")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCargo")
    private Collection<PersonaTrabajoActEco> personaTrabajoActEcoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCargo")
    private Collection<PersonaInstitucionRep> personaInstitucionRepCollection;

    public Cargo() {
    }

    public Cargo(Long codigo) {
        this.codigo = codigo;
    }

    public Cargo(Long codigo, String codigoOc, String nombre, char eliminado) {
        this.codigo = codigo;
        this.codigoOc = codigoOc;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PersonaTrabajoActEco> getPersonaTrabajoActEcoCollection() {
        return personaTrabajoActEcoCollection;
    }

    public void setPersonaTrabajoActEcoCollection(Collection<PersonaTrabajoActEco> personaTrabajoActEcoCollection) {
        this.personaTrabajoActEcoCollection = personaTrabajoActEcoCollection;
    }

    @XmlTransient
    public Collection<PersonaInstitucionRep> getPersonaInstitucionRepCollection() {
        return personaInstitucionRepCollection;
    }

    public void setPersonaInstitucionRepCollection(Collection<PersonaInstitucionRep> personaInstitucionRepCollection) {
        this.personaInstitucionRepCollection = personaInstitucionRepCollection;
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
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.Cargo[ codigo=" + codigo + " ]";
    }
    
}
