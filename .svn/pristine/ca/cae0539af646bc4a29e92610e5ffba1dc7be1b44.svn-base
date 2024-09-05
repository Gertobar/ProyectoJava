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
@Table(name = "MKS_ADQUISICIONES.TIPO_COMPROBANTE_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComprobanteCompra.findAll", query = "SELECT t FROM TipoComprobanteCompra t"),
    @NamedQuery(name = "TipoComprobanteCompra.findByCodigo", query = "SELECT t FROM TipoComprobanteCompra t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoComprobanteCompra.findByNombre", query = "SELECT t FROM TipoComprobanteCompra t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoComprobanteCompra.findBySiglas", query = "SELECT t FROM TipoComprobanteCompra t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoComprobanteCompra.findByEliminado", query = "SELECT t FROM TipoComprobanteCompra t WHERE t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoComprobanteCompra.findByCodigoYEliminado", query = "SELECT t FROM TipoComprobanteCompra t WHERE t.codigo = :codigo AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoComprobanteCompra.findByDocumentosProveedorEliminado", query = "SELECT DISTINCT t FROM DocumentosProveedor d  JOIN d.tipoComprobanteCompra  t  WHERE  d.documentosProveedorPK.codigoProveedor = :codigoProveedor AND t.eliminado = :eliminado AND t.codigo = d.documentosProveedorPK.codigoTipoComprobante"),
    @NamedQuery(name = "TipoComprobanteCompra.findByEsLiquidacionEliminado", query = "SELECT t FROM TipoComprobanteCompra t WHERE t.esLiquidacion = :esLiquidacion AND  t.eliminado = :eliminado")
})
public class TipoComprobanteCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String  findByEliminado="TipoComprobanteCompra.findByEliminado";
    public static final String  findByCodigoYEliminado="TipoComprobanteCompra.findByCodigoYEliminado";
    public static final String  findByDocumentosProveedorEliminado="TipoComprobanteCompra.findByDocumentosProveedorEliminado";
    public static final String  findByEsLiquidacionEliminado="TipoComprobanteCompra.findByEsLiquidacionEliminado";
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
    @Size(min = 1, max = 10)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoComprobante", fetch = FetchType.LAZY)
    private Collection<Compra> compraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoComprobanteCompra", fetch = FetchType.LAZY)
    private Collection<DocumentosProveedor> documentosProveedorCollection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_LIQUIDACION")
    private char esLiquidacion;

    public TipoComprobanteCompra() {
    }

    public TipoComprobanteCompra(Long codigo) {
        this.codigo = codigo;
    }

    public TipoComprobanteCompra(Long codigo, String nombre, String siglas, char eliminado) {
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    @XmlTransient
    public Collection<DocumentosProveedor> getDocumentosProveedorCollection() {
        return documentosProveedorCollection;
    }

    public void setDocumentosProveedorCollection(Collection<DocumentosProveedor> documentosProveedorCollection) {
        this.documentosProveedorCollection = documentosProveedorCollection;
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
        if (!(object instanceof TipoComprobanteCompra)) {
            return false;
        }
        TipoComprobanteCompra other = (TipoComprobanteCompra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.TipoComprobanteCompra[ codigo=" + codigo + " ]";
    }

    /**
     * @return the esLiquidacion
     */
    public char getEsLiquidacion() {
        return esLiquidacion;
    }

    /**
     * @param esLiquidacion the esLiquidacion to set
     */
    public void setEsLiquidacion(char esLiquidacion) {
        this.esLiquidacion = esLiquidacion;
    }
    
}
