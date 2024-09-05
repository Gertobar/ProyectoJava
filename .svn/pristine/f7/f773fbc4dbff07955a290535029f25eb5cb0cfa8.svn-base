/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "MKS_CREDITOS.ACTIVIDAD_ECO_PRO_CRE_MON_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadEcoProCreMonIfip.findAll", query = "SELECT a FROM ActividadEcoProCreMonIfip a"),
    // Personalizado
    @NamedQuery(name = "ActividadEcoProCreMonIfip.findByNivelPadreEliminado", query = "SELECT a FROM ActividadEcoProCreMonIfip a JOIN a.actividadEconomica e WHERE a.actividadEconomica.codigo = e.codigo AND a.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND a.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND a.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND e.codigoNivel.codigo = :nivel AND e.eliminado = :eliminado AND e.codigoActEcoPad.codigo = :codigoPadre AND e.catalogo = :catalogo"),
    @NamedQuery(name = "ActividadEcoProCreMonIfip.findByNivelEliminado", query = "SELECT a FROM ActividadEcoProCreMonIfip a JOIN a.actividadEconomica e WHERE a.actividadEconomica.codigo = e.codigo AND a.productoCreditoMonedaIfip.productoCreditoMoneda.productoCredito.codigo = :codigoProducto AND a.productoCreditoMonedaIfip.productoCreditoMoneda.moneda.codigo = :codigoMoneda AND a.productoCreditoMonedaIfip.ifip.codigo = :codigoIfip AND e.codigoNivel.codigo = :nivel AND e.eliminado = :eliminado AND e.catalogo = :catalogo")
    })
public class ActividadEcoProCreMonIfip implements Serializable {
    public static final String findByNivelPadreEliminado = "ActividadEcoProCreMonIfip.findByNivelPadreEliminado";
    public static final String findByNivelEliminado = "ActividadEcoProCreMonIfip.findByNivelEliminado";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_ACTIVIDAD_ECONOMICA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ActividadEconomica actividadEconomica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @Basic(optional = false)
    @NotNull
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;

    public ActividadEcoProCreMonIfip() {
    }

    public ActividadEcoProCreMonIfip(Long codigo) {
        this.codigo = codigo;
    }

    public ActividadEcoProCreMonIfip(Long codigo, ActividadEconomica actividadEconomica, String vigente, ProductoCreditoMonedaIfip productoCreditoMonedaIfip) {
        this.codigo = codigo;
        this.actividadEconomica = actividadEconomica;
        this.vigente = vigente;
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public ActividadEconomica getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(ActividadEconomica actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public ProductoCreditoMonedaIfip getProductoCreditoMonedaIfip() {
        return productoCreditoMonedaIfip;
    }

    public void setProductoCreditoMonedaIfip(ProductoCreditoMonedaIfip productoCreditoMonedaIfip) {
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
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
        if (!(object instanceof ActividadEcoProCreMonIfip)) {
            return false;
        }
        ActividadEcoProCreMonIfip other = (ActividadEcoProCreMonIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ActividadEcoProCreMonIfip[ codigo=" + codigo + " ]";
    }
    
}
