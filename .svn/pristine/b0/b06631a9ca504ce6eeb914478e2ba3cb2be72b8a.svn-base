/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.tesoreria;

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
 * @author andres
 */
@Entity
@Table(name = "MKS_TESORERIA.TIPO_PARAMETRO_BANCO_CENTRAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoParametroBancoCentral.findByEliminado", query = "SELECT t FROM TipoParametroBancoCentral t WHERE t.eliminado = :eliminado")})
public class TipoParametroBancoCentral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_PARAMETRO_BANCO_CEN")
    @SequenceGenerator(name = "GSQ_TIPO_PARAMETRO_BANCO_CEN", schema = "MKS_TESORERIA",  allocationSize = 0, sequenceName = "SEQ_TIPO_PARAMETRO_BANCO_CEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoParBanCen")
    private Collection<ParametroBancoCentral> parametroBancoCentralCollection;

    public TipoParametroBancoCentral() {
    }

    public TipoParametroBancoCentral(Long codigo) {
        this.codigo = codigo;
    }

    public TipoParametroBancoCentral(Long codigo, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<ParametroBancoCentral> getParametroBancoCentralCollection() {
        return parametroBancoCentralCollection;
    }

    public void setParametroBancoCentralCollection(Collection<ParametroBancoCentral> parametroBancoCentralCollection) {
        this.parametroBancoCentralCollection = parametroBancoCentralCollection;
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
        if (!(object instanceof TipoParametroBancoCentral)) {
            return false;
        }
        TipoParametroBancoCentral other = (TipoParametroBancoCentral) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.tesoreria.TipoParametroBancoCentral[ codigo=" + codigo + " ]";
    }
    
}
