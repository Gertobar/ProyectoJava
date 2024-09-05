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
@Table(name = "MKS_SOCIOS.TIPO_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPersona.findAll", query = "SELECT t FROM TipoPersona t"),
    @NamedQuery(name = "TipoPersona.findByCodigo", query = "SELECT t FROM TipoPersona t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoPersona.findByNombre", query = "SELECT t FROM TipoPersona t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoPersona.findByEsParaInstitucion", query = "SELECT t FROM TipoPersona t WHERE t.esParaInstitucion = :esParaInstitucion"),
    @NamedQuery(name = "TipoPersona.findByEliminado", query = "SELECT t FROM TipoPersona t WHERE t.eliminado = :eliminado ORDER BY t.nombre"),
    @NamedQuery(name = "TipoPersona.findByEsParaInstitucionEliminado", query = "SELECT t FROM TipoPersona t WHERE t.esParaInstitucion = :esParaInstitucion AND t.eliminado = :eliminado ORDER by t.nombre")

})
public class TipoPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoPersona.findByEliminado";
    public static final String findAll = "TipoPersona.findAll";
    public static final String findByCodigo = "TipoPersona.findByCodigo";
    public static final String findByEsParaInstitucionEliminado = "TipoPersona.findByEsParaInstitucionEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_INSTITUCION")
    private char esParaInstitucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoPersona")
    private Collection<Persona> personaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPersona")
    private Collection<TipoPerTipoIde> tipoPerTipoIdeCollection;

    public TipoPersona() {
    }

    public TipoPersona(Long codigo) {
        this.codigo = codigo;
    }

    public TipoPersona(Long codigo, String nombre, char esParaInstitucion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.esParaInstitucion = esParaInstitucion;
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

    public char getEsParaInstitucion() {
        return esParaInstitucion;
    }

    public void setEsParaInstitucion(char esParaInstitucion) {
        this.esParaInstitucion = esParaInstitucion;
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
        if (!(object instanceof TipoPersona)) {
            return false;
        }
        TipoPersona other = (TipoPersona) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoPersona[ codigo=" + codigo + " ]";
    }
    
}
