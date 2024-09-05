/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.VENTA_ESTADO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaEstadoIfip.findAll", query = "SELECT v FROM VentaEstadoIfip v"),
    @NamedQuery(name = "VentaEstadoIfip.findByCodigo", query = "SELECT v FROM VentaEstadoIfip v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "VentaEstadoIfip.findByCodigoIfip", query = "SELECT v FROM VentaEstadoIfip v WHERE v.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "VentaEstadoIfip.findByNombre", query = "SELECT v FROM VentaEstadoIfip v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "VentaEstadoIfip.findByDescripcion", query = "SELECT v FROM VentaEstadoIfip v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "VentaEstadoIfip.findByEliminado", query = "SELECT v FROM VentaEstadoIfip v WHERE v.eliminado = :eliminado")})
public class VentaEstadoIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
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

    public VentaEstadoIfip() {
    }

    public VentaEstadoIfip(Long codigo) {
        this.codigo = codigo;
    }

    public VentaEstadoIfip(Long codigo, long codigoIfip, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
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

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
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
        if (!(object instanceof VentaEstadoIfip)) {
            return false;
        }
        VentaEstadoIfip other = (VentaEstadoIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.VentaEstadoIfip[ codigo=" + codigo + " ]";
    }
    
}
