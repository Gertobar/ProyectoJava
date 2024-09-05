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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_SOCIOS.SOCIO_SITUACION_PAT_ACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioSituacionPatAct.findAll", query = "SELECT s FROM SocioSituacionPatAct s"),
    @NamedQuery(name = "SocioSituacionPatAct.findByCodigoItemSitPat", query = "SELECT s FROM SocioSituacionPatAct s WHERE s.socioSituacionPatActPK.codigoItemSitPat = :codigoItemSitPat"),
    @NamedQuery(name = "SocioSituacionPatAct.findByTotal", query = "SELECT s FROM SocioSituacionPatAct s WHERE s.total = :total"),
     //Personalizados
    @NamedQuery(name = "SocioSituacionPatAct.findByCodigoSocioCodigoIfip", query = "SELECT s FROM SocioSituacionPatAct s WHERE s.socioSituacionPatrimonial.codigoSocio = :codigoSocio AND s.socioSituacionPatrimonial.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "SocioSituacionPatAct.findByCodigoPersona", query = "SELECT s FROM SocioSituacionPatAct s WHERE s.codigoPersona = :codigoPersona")
    })
public class SocioSituacionPatAct implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSocioCodigoIfip = "SocioSituacionPatAct.findByCodigoSocioCodigoIfip";
    @EmbeddedId
    protected SocioSituacionPatActPK socioSituacionPatActPK;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO_SOCIO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SocioSituacionPatrimonial socioSituacionPatrimonial;
    @JoinColumn(name = "CODIGO_ITEM_SIT_PAT", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ItemSituacionPatrimonial itemSituacionPatrimonial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA", insertable = false, updatable = false)
    private long codigoPersona;

    public SocioSituacionPatAct() {
    }

    public SocioSituacionPatAct(SocioSituacionPatActPK socioSituacionPatActPK) {
        this.socioSituacionPatActPK = socioSituacionPatActPK;
    }

    public SocioSituacionPatAct(SocioSituacionPatActPK socioSituacionPatActPK, BigDecimal total) {
        this.socioSituacionPatActPK = socioSituacionPatActPK;
        this.total = total;
    }

    public SocioSituacionPatAct(long codigoItemSitPat, long codigoPersona) {
        this.socioSituacionPatActPK = new SocioSituacionPatActPK(codigoItemSitPat, codigoPersona);
    }

    public SocioSituacionPatActPK getSocioSituacionPatActPK() {
        return socioSituacionPatActPK;
    }

    public void setSocioSituacionPatActPK(SocioSituacionPatActPK socioSituacionPatActPK) {
        this.socioSituacionPatActPK = socioSituacionPatActPK;
    }
    
    public SocioSituacionPatrimonial getSocioSituacionPatrimonial() {
        return socioSituacionPatrimonial;
    }

    public void setSocioSituacionPatrimonial(SocioSituacionPatrimonial socioSituacionPatrimonial) {
        this.socioSituacionPatrimonial = socioSituacionPatrimonial;
    }

    public ItemSituacionPatrimonial getItemSituacionPatrimonial() {
        return itemSituacionPatrimonial;
    }

    public void setItemSituacionPatrimonial(ItemSituacionPatrimonial itemSituacionPatrimonial) {
        this.itemSituacionPatrimonial = itemSituacionPatrimonial;
    }
    
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socioSituacionPatActPK != null ? socioSituacionPatActPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioSituacionPatAct)) {
            return false;
        }
        SocioSituacionPatAct other = (SocioSituacionPatAct) object;
        if ((this.socioSituacionPatActPK == null && other.socioSituacionPatActPK != null) || (this.socioSituacionPatActPK != null && !this.socioSituacionPatActPK.equals(other.socioSituacionPatActPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioSituacionPatAct[ socioSituacionPatActPK=" + socioSituacionPatActPK + " ]";
    }
}