/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_MOTIVO_PAGO_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMotivoPagoCredito.findAll", query = "SELECT t FROM TipoMotivoPagoCredito t"),
    @NamedQuery(name = "TipoMotivoPagoCredito.findByCodigo", query = "SELECT t FROM TipoMotivoPagoCredito t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoMotivoPagoCredito.findByNombre", query = "SELECT t FROM TipoMotivoPagoCredito t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMotivoPagoCredito.findByDescripcion", query = "SELECT t FROM TipoMotivoPagoCredito t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoMotivoPagoCredito.findByEliminado", query = "SELECT t FROM TipoMotivoPagoCredito t WHERE t.eliminado = :eliminado")
})

public class TipoMotivoPagoCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEliminado ="TipoMotivoPagoCredito.findByEliminado";
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
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoMotivoPagoCredito")
    private Collection<MotivoPagoCreditoTotal> motivoPagoCreditoTotalCollection;

    public TipoMotivoPagoCredito() {
    }

    public TipoMotivoPagoCredito(Long codigo) {
        this.codigo = codigo;
    }

    public TipoMotivoPagoCredito(Long codigo, String nombre, String descripcion, char eliminado) {
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
    public Collection<MotivoPagoCreditoTotal> getMotivoPagoCreditoTotalCollection() {
        return motivoPagoCreditoTotalCollection;
    }

    public void setMotivoPagoCreditoTotalCollection(Collection<MotivoPagoCreditoTotal> motivoPagoCreditoTotalCollection) {
        this.motivoPagoCreditoTotalCollection = motivoPagoCreditoTotalCollection;
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
        if (!(object instanceof TipoMotivoPagoCredito)) {
            return false;
        }
        TipoMotivoPagoCredito other = (TipoMotivoPagoCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.FINA.TipoMotivoPagoCredito[ codigo=" + codigo + " ]";
    }

}
