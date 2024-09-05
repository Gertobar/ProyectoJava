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
@Table(name = "MKS_SOCIOS.TIPO_IDENTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t"),
    @NamedQuery(name = "TipoIdentificacion.findByCodigo", query = "SELECT t FROM TipoIdentificacion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoIdentificacion.findByNombre", query = "SELECT t FROM TipoIdentificacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoIdentificacion.findBySiglas", query = "SELECT t FROM TipoIdentificacion t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoIdentificacion.findByEliminado", query = "SELECT t FROM TipoIdentificacion t WHERE t.eliminado = :eliminado")})
public class TipoIdentificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigo = "TipoIdentificacion.findByCodigo";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_IDENTIFICACION")
    @SequenceGenerator(name = "GSQ_TIPO_IDENTIFICACION", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_TIPO_IDENTIFICACION")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGLAS")
    private char siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoIdentificacion", fetch = FetchType.LAZY)
    private Collection<Persona> personaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIdentificacion", fetch = FetchType.LAZY)
    private Collection<TipoPerTipoIde> tipoPerTipoIdeCollection;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(Long codigo) {
        this.codigo = codigo;
    }

    public TipoIdentificacion(Long codigo, String nombre, char siglas, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
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

    public char getSiglas() {
        return siglas;
    }

    public void setSiglas(char siglas) {
        this.siglas = siglas;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @XmlTransient
    public Collection<TipoPerTipoIde> getTipoPerTipoIdeCollection() {
        return tipoPerTipoIdeCollection;
    }

    public void setTipoPerTipoIdeCollection(Collection<TipoPerTipoIde> tipoPerTipoIdeCollection) {
        this.tipoPerTipoIdeCollection = tipoPerTipoIdeCollection;
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
        if (!(object instanceof TipoIdentificacion)) {
            return false;
        }
        TipoIdentificacion other = (TipoIdentificacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoIdentificacion[ codigo=" + codigo + " ]";
    }
    
}
