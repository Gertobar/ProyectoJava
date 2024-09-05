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
@Table(name = "MKS_SOCIOS.SECTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sector.findAll", query = "SELECT s FROM Sector s"),
    @NamedQuery(name = "Sector.findByCodigo", query = "SELECT s FROM Sector s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Sector.findByNombre", query = "SELECT s FROM Sector s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sector.findByEliminado", query = "SELECT s FROM Sector s WHERE s.eliminado = :eliminado ORDER BY s.nombre")})
public class Sector implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByEliminado = "Sector.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_SECTOR")
    @SequenceGenerator(name = "GSQ_SECTOR", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_SECTOR")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSector")
    private Collection<PersonaTrabajoActEco> personaTrabajoActEcoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSector")
    private Collection<PersonaResidencia> personaResidenciaCollection;

    public Sector() {
    }

    public Sector(Long codigo) {
        this.codigo = codigo;
    }

    public Sector(Long codigo, String nombre, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
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
    public Collection<PersonaResidencia> getPersonaResidenciaCollection() {
        return personaResidenciaCollection;
    }

    public void setPersonaResidenciaCollection(Collection<PersonaResidencia> personaResidenciaCollection) {
        this.personaResidenciaCollection = personaResidenciaCollection;
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
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.Sector[ codigo=" + codigo + " ]";
    }
    
}
