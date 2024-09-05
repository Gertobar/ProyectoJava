/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

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
@Table(name = "MKS_IFIPS.SECUENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Secuencia.findAll", query = "SELECT s FROM Secuencia s"),
    @NamedQuery(name = "Secuencia.findByCodigo", query = "SELECT s FROM Secuencia s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Secuencia.findByNombre", query = "SELECT s FROM Secuencia s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Secuencia.findByEliminado", query = "SELECT s FROM Secuencia s WHERE s.eliminado = :eliminado")})
public class Secuencia implements Serializable {
    private static final long serialVersionUID = 1L;
     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_SECUENCIA")
    @SequenceGenerator(name = "GSQ_SECUENCIA", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_SECUENCIA")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secuencia")
    private Collection<IfipAgenciaSecuencia> ifipAgenciaSecuenciaCollection;

    public Secuencia() {
    }

    public Secuencia(Long codigo) {
        this.codigo = codigo;
    }

    public Secuencia(Long codigo, String nombre, char eliminado) {
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
    public Collection<IfipAgenciaSecuencia> getIfipAgenciaSecuenciaCollection() {
        return ifipAgenciaSecuenciaCollection;
    }

    public void setIfipAgenciaSecuenciaCollection(Collection<IfipAgenciaSecuencia> ifipAgenciaSecuenciaCollection) {
        this.ifipAgenciaSecuenciaCollection = ifipAgenciaSecuenciaCollection;
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
        if (!(object instanceof Secuencia)) {
            return false;
        }
        Secuencia other = (Secuencia) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.Secuencia[ codigo=" + codigo + " ]";
    }
    
}
