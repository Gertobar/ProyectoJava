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
@Table(name = "MKS_SOCIOS.ITEM_SITUACION_PATRIMONIAL_MON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemSituacionPatrimonialMon.findAll", query = "SELECT i FROM ItemSituacionPatrimonialMon i")
    , @NamedQuery(name = "ItemSituacionPatrimonialMon.findByCodigoItemSituacionPat", query = "SELECT i FROM ItemSituacionPatrimonialMon i WHERE i.codigoItemSituacionPat = :codigoItemSituacionPat")
    , @NamedQuery(name = "ItemSituacionPatrimonialMon.findByMontoMinimo", query = "SELECT i FROM ItemSituacionPatrimonialMon i WHERE i.montoMinimo = :montoMinimo")
    , @NamedQuery(name = "ItemSituacionPatrimonialMon.findByMontoMaximo", query = "SELECT i FROM ItemSituacionPatrimonialMon i WHERE i.montoMaximo = :montoMaximo")
    , @NamedQuery(name = "ItemSituacionPatrimonialMon.findByEliminado", query = "SELECT i FROM ItemSituacionPatrimonialMon i WHERE i.eliminado = :eliminado")})
public class ItemSituacionPatrimonialMon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ITEM_SITUACION_PAT")
    private Long codigoItemSituacionPat;
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
    @JoinColumn(name = "CODIGO_ITEM_SITUACION_PAT", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ItemSituacionPatrimonial itemSituacionPatrimonial;

    public ItemSituacionPatrimonialMon() {
    }

    public ItemSituacionPatrimonialMon(Long codigoItemSituacionPat) {
        this.codigoItemSituacionPat = codigoItemSituacionPat;
    }

    public ItemSituacionPatrimonialMon(Long codigoItemSituacionPat, BigDecimal montoMinimo, BigDecimal montoMaximo, Character eliminado) {
        this.codigoItemSituacionPat = codigoItemSituacionPat;
        this.montoMinimo = montoMinimo;
        this.montoMaximo = montoMaximo;
        this.eliminado = eliminado;
    }

    public Long getCodigoItemSituacionPat() {
        return codigoItemSituacionPat;
    }

    public void setCodigoItemSituacionPat(Long codigoItemSituacionPat) {
        this.codigoItemSituacionPat = codigoItemSituacionPat;
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

    public ItemSituacionPatrimonial getItemSituacionPatrimonial() {
        return itemSituacionPatrimonial;
    }
    
    public void setItemSituacionPatrimonial(ItemSituacionPatrimonial itemSituacionPatrimonial) {
        this.itemSituacionPatrimonial = itemSituacionPatrimonial;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoItemSituacionPat != null ? codigoItemSituacionPat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemSituacionPatrimonialMon)) {
            return false;
        }
        ItemSituacionPatrimonialMon other = (ItemSituacionPatrimonialMon) object;
        if ((this.codigoItemSituacionPat == null && other.codigoItemSituacionPat != null) || (this.codigoItemSituacionPat != null && !this.codigoItemSituacionPat.equals(other.codigoItemSituacionPat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ItemSituacionPatrimonialMon[ codigoItemSituacionPat=" + codigoItemSituacionPat + " ]";
    }

  
}
