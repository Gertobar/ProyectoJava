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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.INSTRUCCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instruccion.findAll", query = "SELECT i FROM Instruccion i"),
    @NamedQuery(name = "Instruccion.findByCodigo", query = "SELECT i FROM Instruccion i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "Instruccion.findByNombre", query = "SELECT i FROM Instruccion i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Instruccion.findByEliminado", query = "SELECT i FROM Instruccion i WHERE i.eliminado = :eliminado ORDER BY i.nombre")})
public class Instruccion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "Instruccion.findByEliminado";
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
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_NIVEL_INSTRUCCION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private NivelInstruccion codigoNivelInstruccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoInstruccion")
    private Collection<PersonaNatural> personaNaturalCollection;

    public Instruccion() {
    }

    public Instruccion(Long codigo) {
        this.codigo = codigo;
    }

    public Instruccion(Long codigo, String nombre, char eliminado) {
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

    public NivelInstruccion getCodigoNivelInstruccion() {
        return codigoNivelInstruccion;
    }

    public void setCodigoNivelInstruccion(NivelInstruccion codigoNivelInstruccion) {
        this.codigoNivelInstruccion = codigoNivelInstruccion;
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
        if (!(object instanceof Instruccion)) {
            return false;
        }
        Instruccion other = (Instruccion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.Instruccion[ codigo=" + codigo + " ]";
    }
    
}
