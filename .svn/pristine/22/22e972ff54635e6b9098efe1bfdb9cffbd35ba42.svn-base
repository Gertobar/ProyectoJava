/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

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
@Table(name = "MKS_SEGURIDADES.PARAMETRO_GENERAL_SEGURIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroGeneralSeguridad.findAll", query = "SELECT p FROM ParametroGeneralSeguridad p"),
    @NamedQuery(name = "ParametroGeneralSeguridad.findByCodigo", query = "SELECT p FROM ParametroGeneralSeguridad p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroGeneralSeguridad.findByNombre", query = "SELECT p FROM ParametroGeneralSeguridad p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ParametroGeneralSeguridad.findByDescripcion", query = "SELECT p FROM ParametroGeneralSeguridad p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ParametroGeneralSeguridad.findByEliminado", query = "SELECT p FROM ParametroGeneralSeguridad p WHERE p.eliminado = :eliminado")})
public class ParametroGeneralSeguridad implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroGeneralSeguridad")
    private Collection<ParametroGeneralSegIfi> parametroGeneralSegIfiCollection;

    public ParametroGeneralSeguridad() {
    }

    public ParametroGeneralSeguridad(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroGeneralSeguridad(Long codigo, String nombre, String descripcion, char eliminado) {
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
    public Collection<ParametroGeneralSegIfi> getParametroGeneralSegIfiCollection() {
        return parametroGeneralSegIfiCollection;
    }

    public void setParametroGeneralSegIfiCollection(Collection<ParametroGeneralSegIfi> parametroGeneralSegIfiCollection) {
        this.parametroGeneralSegIfiCollection = parametroGeneralSegIfiCollection;
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
        if (!(object instanceof ParametroGeneralSeguridad)) {
            return false;
        }
        ParametroGeneralSeguridad other = (ParametroGeneralSeguridad) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.ParametroGeneralSeguridad[ codigo=" + codigo + " ]";
    }
    
}
