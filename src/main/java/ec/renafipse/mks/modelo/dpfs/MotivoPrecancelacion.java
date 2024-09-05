/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_DPFS.MOTIVO_PRECANCELACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoPrecancelacion.findAll", query = "SELECT m FROM MotivoPrecancelacion m"),
    @NamedQuery(name = "MotivoPrecancelacion.findByCodigo", query = "SELECT m FROM MotivoPrecancelacion m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MotivoPrecancelacion.findByNombre", query = "SELECT m FROM MotivoPrecancelacion m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MotivoPrecancelacion.findByDescripcion", query = "SELECT m FROM MotivoPrecancelacion m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MotivoPrecancelacion.findByEliminado", query = "SELECT m FROM MotivoPrecancelacion m WHERE m.eliminado = :eliminado ORDER BY m.nombre")})
public class MotivoPrecancelacion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "MotivoPrecancelacion.findByEliminado";
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_MOTIVO_PRECANCELACION")
    @SequenceGenerator(name = "GSQ_MOTIVO_PRECANCELACION", schema = "MKS_DPFS",  allocationSize = 0, sequenceName = "SEQ_MOTIVO_PRECANCELACION")
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
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public MotivoPrecancelacion() {
    }

    public MotivoPrecancelacion(Long codigo) {
        this.codigo = codigo;
    }

    public MotivoPrecancelacion(Long codigo, String nombre, String descripcion, char eliminado) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoPrecancelacion)) {
            return false;
        }
        MotivoPrecancelacion other = (MotivoPrecancelacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.MotivoPrecancelacion[ codigo=" + codigo + " ]";
    }
    
}
