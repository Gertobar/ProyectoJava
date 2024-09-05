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
import javax.persistence.FetchType;
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
@Table(name = "MKS_SOCIOS.NIVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel.findAll", query = "SELECT n FROM Nivel n"),
    @NamedQuery(name = "Nivel.findByCodigo", query = "SELECT n FROM Nivel n WHERE n.codigo = :codigo"),
    @NamedQuery(name = "Nivel.findByNombre", query = "SELECT n FROM Nivel n WHERE n.nombre = :nombre"),
    @NamedQuery(name = "Nivel.findByEliminado", query = "SELECT n FROM Nivel n WHERE n.eliminado = :eliminado ORDER BY n.nombre")})
public class Nivel implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findAll = "Nivel.findAll";
    public static final String findByEliminado = "Nivel.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_NIVEL")
    @SequenceGenerator(name = "GSQ_NIVEL", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_NIVEL")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoNivel", fetch = FetchType.LAZY )
    private Collection<PersonaInstitucionRep> personaInstitucionRepCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoNivel", fetch = FetchType.LAZY)
    private Collection<ActividadEconomica> actividadEconomicaCollection;

    public Nivel() {
    }

    public Nivel(Long codigo) {
        this.codigo = codigo;
    }

    public Nivel(Long codigo, String nombre, char eliminado) {
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
    public Collection<PersonaInstitucionRep> getPersonaInstitucionRepCollection() {
        return personaInstitucionRepCollection;
    }

    public void setPersonaInstitucionRepCollection(Collection<PersonaInstitucionRep> personaInstitucionRepCollection) {
        this.personaInstitucionRepCollection = personaInstitucionRepCollection;
    }

    @XmlTransient
    public Collection<ActividadEconomica> getActividadEconomicaCollection() {
        return actividadEconomicaCollection;
    }

    public void setActividadEconomicaCollection(Collection<ActividadEconomica> actividadEconomicaCollection) {
        this.actividadEconomicaCollection = actividadEconomicaCollection;
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
        if (!(object instanceof Nivel)) {
            return false;
        }
        Nivel other = (Nivel) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.Nivel[ codigo=" + codigo + " ]";
    }
    
}
