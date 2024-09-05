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
 * @author Andres
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.ITEM_VENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemVenta.findAll", query = "SELECT i FROM ItemVenta i"),
    @NamedQuery(name = "ItemVenta.findByCodigo", query = "SELECT i FROM ItemVenta i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "ItemVenta.findByNombre", query = "SELECT i FROM ItemVenta i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "ItemVenta.findByDescripcion", query = "SELECT i FROM ItemVenta i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "ItemVenta.findByTipo", query = "SELECT i FROM ItemVenta i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "ItemVenta.findByCodigoProcesoContable", query = "SELECT i FROM ItemVenta i WHERE i.codigoProcesoContable = :codigoProcesoContable"),
    @NamedQuery(name = "ItemVenta.findByEliminado", query = "SELECT i FROM ItemVenta i WHERE i.eliminado = :eliminado")})
public class ItemVenta implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROCESO_CONTABLE")
    private long codigoProcesoContable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SUSTENTO_TRIBUTARIO")
    private long codigoSustentoTributario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESGLOSA_IVA")
    private char desglosaIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;

    public ItemVenta() {
    }

    public ItemVenta(Long codigo) {
        this.codigo = codigo;
    }

    public ItemVenta(Long codigo, String nombre, String descripcion, char tipo, long codigoProcesoContable, long codigoSustentoTributario, char desglosaIva, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.codigoProcesoContable = codigoProcesoContable;
        this.codigoSustentoTributario = codigoSustentoTributario;
        this.desglosaIva = desglosaIva;
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

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public long getCodigoProcesoContable() {
        return codigoProcesoContable;
    }

    public void setCodigoProcesoContable(long codigoProcesoContable) {
        this.codigoProcesoContable = codigoProcesoContable;
    }
    
    public long getCodigoSustentoTributario() {
        return codigoSustentoTributario;
    }

    public void setCodigoSustentoTributario(long codigoSustentoTributario) {
        this.codigoSustentoTributario = codigoSustentoTributario;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }
    
    public char getDesglosaIva() {
        return desglosaIva;
    }

    public void setDesglosaIva(char desglosaIva) {
        this.desglosaIva = desglosaIva;
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
        if (!(object instanceof ItemVenta)) {
            return false;
        }
        ItemVenta other = (ItemVenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.ItemVenta[ codigo=" + codigo + " ]";
    }
}
