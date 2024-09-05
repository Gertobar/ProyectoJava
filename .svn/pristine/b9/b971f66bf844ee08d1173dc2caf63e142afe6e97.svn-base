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
@Table(name = "MKS_SOCIOS.PROFESION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesion.findAll", query = "SELECT p FROM Profesion p"),
    @NamedQuery(name = "Profesion.findByCodigo", query = "SELECT p FROM Profesion p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Profesion.findByCodigoOc", query = "SELECT p FROM Profesion p WHERE p.codigoOc = :codigoOc"),
    @NamedQuery(name = "Profesion.findByNombre", query = "SELECT p FROM Profesion p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Profesion.findByEliminado", query = "SELECT p FROM Profesion p WHERE p.eliminado = :eliminado")})
public class Profesion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "Profesion.findByEliminado";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PROFESION")
    @SequenceGenerator(name = "GSQ_PROFESION", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_PROFESION")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoProfesion")
    private Collection<PersonaNatural> personaNaturalCollection;

    public Profesion() {
    }

    public Profesion(Long codigo) {
        this.codigo = codigo;
    }

    public Profesion(Long codigo, String codigoOc, String nombre, char eliminado) {
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
    public Collection<PersonaNatural> getPersonaNaturalCollection() {
        return personaNaturalCollection;
    }

    public void setPersonaNaturalCollection(Collection<PersonaNatural> personaNaturalCollection) {
        this.personaNaturalCollection = personaNaturalCollection;
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
        if (!(object instanceof Profesion)) {
            return false;
        }
        Profesion other = (Profesion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.Profesion[ codigo=" + codigo + " ]";
    }
    
}
