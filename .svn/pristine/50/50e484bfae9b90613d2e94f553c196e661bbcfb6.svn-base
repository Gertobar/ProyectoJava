/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "MKS_ADQUISICIONES.FORMA_PAGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPago.findAll", query = "SELECT f FROM FormaPago f"),
    @NamedQuery(name = "FormaPago.findByCodigo", query = "SELECT f FROM FormaPago f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "FormaPago.findByNombre", query = "SELECT f FROM FormaPago f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FormaPago.findByCodigoOc", query = "SELECT f FROM FormaPago f WHERE f.codigoOc = :codigoOc"),
    @NamedQuery(name = "FormaPago.findByEliminado", query = "SELECT f FROM FormaPago f WHERE f.eliminado = :eliminado"),
    @NamedQuery(name = "FormaPago.findByCodigoEliminado", query = "SELECT f FROM FormaPago f WHERE f.codigo = :codigo AND f.eliminado = :eliminado")
})
public class FormaPago implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoEliminado = "FormaPago.findByCodigoEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Size(max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaPago", fetch = FetchType.LAZY)
    private Collection<PagoCompraDetalle> pagoCompraDetalleCollection;

    public FormaPago() {
    }

    public FormaPago(Long codigo) {
        this.codigo = codigo;
    }

    public FormaPago(Long codigo, String codigoOc, char eliminado) {
        this.codigo = codigo;
        this.codigoOc = codigoOc;
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

    public String getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PagoCompraDetalle> getPagoCompraDetalleCollection() {
        return pagoCompraDetalleCollection;
    }

    public void setPagoCompraDetalleCollection(Collection<PagoCompraDetalle> pagoCompraDetalleCollection) {
        this.pagoCompraDetalleCollection = pagoCompraDetalleCollection;
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
        if (!(object instanceof FormaPago)) {
            return false;
        }
        FormaPago other = (FormaPago) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.FormaPago[ codigo=" + codigo + " ]";
    }
    
}
