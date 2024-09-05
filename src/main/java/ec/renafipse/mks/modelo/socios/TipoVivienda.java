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
@Table(name = "MKS_SOCIOS.TIPO_VIVIENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVivienda.findAll", query = "SELECT t FROM TipoVivienda t"),
    @NamedQuery(name = "TipoVivienda.findByCodigo", query = "SELECT t FROM TipoVivienda t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoVivienda.findByCodigoOc", query = "SELECT t FROM TipoVivienda t WHERE t.codigoOc = :codigoOc"),
    @NamedQuery(name = "TipoVivienda.findByNombre", query = "SELECT t FROM TipoVivienda t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoVivienda.findByEliminado", query = "SELECT t FROM TipoVivienda t WHERE t.eliminado = :eliminado ORDER BY t.nombre")})
public class TipoVivienda implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoVivienda.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_VIVIENDA")
    @SequenceGenerator(name = "GSQ_TIPO_VIVIENDA", schema = "MKS_SOCIOS", allocationSize = 0, sequenceName = "SEQ_TIPO_VIVIENDA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_OC")
    private char codigoOc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoVivienda")
    private Collection<PersonaResidencia> personaResidenciaCollection;

    public TipoVivienda() {
    }

    public TipoVivienda(Long codigo) {
        this.codigo = codigo;
    }

    public TipoVivienda(Long codigo, char codigoOc, String nombre, char eliminado) {
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

    public char getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(char codigoOc) {
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
        if (!(object instanceof TipoVivienda)) {
            return false;
        }
        TipoVivienda other = (TipoVivienda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoVivienda[ codigo=" + codigo + " ]";
    }

}
