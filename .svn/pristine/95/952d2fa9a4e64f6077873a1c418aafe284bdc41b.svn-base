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
import javax.persistence.FetchType;
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
 * @author Andres
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_CARTERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCartera.findAll", query = "SELECT t FROM TipoCartera t"),
    @NamedQuery(name = "TipoCartera.findByCodigo", query = "SELECT t FROM TipoCartera t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoCartera.findByNombre", query = "SELECT t FROM TipoCartera t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCartera.findBySiglas", query = "SELECT t FROM TipoCartera t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoCartera.findByDescripcion", query = "SELECT t FROM TipoCartera t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoCartera.findByDiasVence", query = "SELECT t FROM TipoCartera t WHERE t.diasVence = :diasVence"),
    @NamedQuery(name = "TipoCartera.findByEliminado", query = "SELECT t FROM TipoCartera t WHERE t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoCartera.findTipoCarteraByProductoVigente", query = "SELECT DISTINCT t FROM ProductoCreditoMonedaIfip c JOIN c.productoCreditoMoneda m JOIN m.productoCredito p JOIN p.codigoTipoCartera t WHERE c.productoCreditoMonedaIfipPK.codigoMoneda = m.productoCreditoMonedaPK.codigoMoneda AND c.productoCreditoMonedaIfipPK.codigoProducto = m.productoCreditoMonedaPK.codigoProducto AND m.productoCreditoMonedaPK.codigoProducto = p.codigo AND p.codigoTipoCartera.codigo = t.codigo AND c.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND m.productoCreditoMonedaPK.codigoMoneda = :codigoMoneda AND c.eliminado = 'N' AND m.eliminado = 'N' AND p.eliminado = 'N' AND t.eliminado = 'N'"),
    @NamedQuery(name = "TipoCartera.findByCodigoOc", query = "SELECT t FROM TipoCartera t WHERE t.codigoOc = :codigoOc")})
public class TipoCartera implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByElminado="TipoCartera.findByEliminado";
    public static final String findTipoCarteraByProductoVigente = "TipoCartera.findTipoCarteraByProductoVigente";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_CARTERA")
    @SequenceGenerator(name = "GSQ_TIPO_CARTERA", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_TIPO_CARTERA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_VENCE")
    private int diasVence;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoCartera", fetch = FetchType.LAZY)
    private Collection<ProductoCredito> productoCreditoCollection;
    public TipoCartera() {
    }

    public TipoCartera(Long codigo) {
        this.codigo = codigo;
    }

    public TipoCartera(Long codigo, String nombre, String descripcion, int diasVence, char eliminado, String codigoOc) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasVence = diasVence;
        this.eliminado = eliminado;
        this.codigoOc = codigoOc;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDiasVence() {
        return diasVence;
    }

    public void setDiasVence(int diasVence) {
        this.diasVence = diasVence;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public String getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
    }

    @XmlTransient
    public Collection<ProductoCredito> getProductoCreditoCollection() {
        return productoCreditoCollection;
    }

    public void setProductoCreditoCollection(Collection<ProductoCredito> productoCreditoCollection) {
        this.productoCreditoCollection = productoCreditoCollection;
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
        if (!(object instanceof TipoCartera)) {
            return false;
        }
        TipoCartera other = (TipoCartera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoCartera[ codigo=" + codigo + " ]";
    }
    
}
