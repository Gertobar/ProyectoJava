/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_SOCIOS.ITEM_FLUJO_CAJA_MONTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemFlujoCajaMonto.findAll", query = "SELECT i FROM ItemFlujoCajaMonto i")
    , @NamedQuery(name = "ItemFlujoCajaMonto.findByCodigoItemFlujoCaja", query = "SELECT i FROM ItemFlujoCajaMonto i WHERE i.codigoItemFlujoCaja = :codigoItemFlujoCaja")
    , @NamedQuery(name = "ItemFlujoCajaMonto.findByMontoMinimo", query = "SELECT i FROM ItemFlujoCajaMonto i WHERE i.montoMinimo = :montoMinimo")
    , @NamedQuery(name = "ItemFlujoCajaMonto.findByMontoMaximo", query = "SELECT i FROM ItemFlujoCajaMonto i WHERE i.montoMaximo = :montoMaximo")
    , @NamedQuery(name = "ItemFlujoCajaMonto.findByEliminado", query = "SELECT i FROM ItemFlujoCajaMonto i WHERE i.eliminado = :eliminado")})
public class ItemFlujoCajaMonto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ITEM_FLUJO_CAJA")
    private Long codigoItemFlujoCaja;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_MINIMO")
    private BigDecimal montoMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_MAXIMO")
    private BigDecimal montoMaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private Character eliminado;
    @JoinColumn(name = "CODIGO_ITEM_FLUJO_CAJA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ItemFlujoCaja itemFlujoCaja;

    public ItemFlujoCajaMonto() {
    }

    public ItemFlujoCajaMonto(Long codigoItemFlujoCaja) {
        this.codigoItemFlujoCaja = codigoItemFlujoCaja;
    }

    public ItemFlujoCajaMonto(Long codigoItemFlujoCaja, BigDecimal montoMinimo, BigDecimal montoMaximo, Character eliminado) {
        this.codigoItemFlujoCaja = codigoItemFlujoCaja;
        this.montoMinimo = montoMinimo;
        this.montoMaximo = montoMaximo;
        this.eliminado = eliminado;
    }

    public Long getCodigoItemFlujoCaja() {
        return codigoItemFlujoCaja;
    }

    public void setCodigoItemFlujoCaja(Long codigoItemFlujoCaja) {
        this.codigoItemFlujoCaja = codigoItemFlujoCaja;
    }

    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public ItemFlujoCaja getItemFlujoCaja() {
        return itemFlujoCaja;
    }

    public void setItemFlujoCaja(ItemFlujoCaja itemFlujoCaja) {
        this.itemFlujoCaja = itemFlujoCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoItemFlujoCaja != null ? codigoItemFlujoCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemFlujoCajaMonto)) {
            return false;
        }
        ItemFlujoCajaMonto other = (ItemFlujoCajaMonto) object;
        if ((this.codigoItemFlujoCaja == null && other.codigoItemFlujoCaja != null) || (this.codigoItemFlujoCaja != null && !this.codigoItemFlujoCaja.equals(other.codigoItemFlujoCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ItemFlujoCajaMonto[ codigoItemFlujoCaja=" + codigoItemFlujoCaja + " ]";
    }
    
}
