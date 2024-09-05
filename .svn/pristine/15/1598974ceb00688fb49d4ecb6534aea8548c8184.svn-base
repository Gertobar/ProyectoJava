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
@Table(name = "MKS_SOCIOS.NIVEL_INSTRUCCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelInstruccion.findAll", query = "SELECT n FROM NivelInstruccion n"),
    @NamedQuery(name = "NivelInstruccion.findByCodigo", query = "SELECT n FROM NivelInstruccion n WHERE n.codigo = :codigo"),
    @NamedQuery(name = "NivelInstruccion.findByNombre", query = "SELECT n FROM NivelInstruccion n WHERE n.nombre = :nombre"),
    @NamedQuery(name = "NivelInstruccion.findByEliminado", query = "SELECT n FROM NivelInstruccion n WHERE n.eliminado = :eliminado")})
public class NivelInstruccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_NIVEL_INSTRUCCION")
    @SequenceGenerator(name = "GSQ_NIVEL_INSTRUCCION", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_NIVEL_INSTRUCCION")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoNivelInstruccion")
    private Collection<Instruccion> instruccionCollection;

    public NivelInstruccion() {
    }

    public NivelInstruccion(Long codigo) {
        this.codigo = codigo;
    }

    public NivelInstruccion(Long codigo, String nombre, char eliminado) {
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
    public Collection<Instruccion> getInstruccionCollection() {
        return instruccionCollection;
    }

    public void setInstruccionCollection(Collection<Instruccion> instruccionCollection) {
        this.instruccionCollection = instruccionCollection;
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
        if (!(object instanceof NivelInstruccion)) {
            return false;
        }
        NivelInstruccion other = (NivelInstruccion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.NivelInstruccion[ codigo=" + codigo + " ]";
    }
    
}
