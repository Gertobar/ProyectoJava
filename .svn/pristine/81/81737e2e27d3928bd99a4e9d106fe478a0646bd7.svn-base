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
@Table(name = "MKS_SOCIOS.SOCIO_SITUACION_PAT_PAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioSituacionPatPas.findAll", query = "SELECT s FROM SocioSituacionPatPas s"),
    @NamedQuery(name = "SocioSituacionPatPas.findByCodigoItemSitPat", query = "SELECT s FROM SocioSituacionPatPas s WHERE s.socioSituacionPatPasPK.codigoItemSitPat = :codigoItemSitPat"),
    @NamedQuery(name = "SocioSituacionPatPas.findByTotal", query = "SELECT s FROM SocioSituacionPatPas s WHERE s.total = :total"),
    // Personalizados
    @NamedQuery(name = "SocioSituacionPatPas.findByCodigoSocioCodigoIfip", query = "SELECT s FROM SocioSituacionPatPas s WHERE s.socioSituacionPatrimonial.codigoSocio = :codigoSocio AND s.socioSituacionPatrimonial.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "SocioSituacionPatPas.findByCodigoPersona", query = "SELECT s FROM SocioSituacionPatPas s WHERE s.codigoPersona = :codigoPersona")
})
public class SocioSituacionPatPas implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSocioCodigoIfip = "SocioSituacionPatPas.findByCodigoSocioCodigoIfip";
    @EmbeddedId
    protected SocioSituacionPatPasPK socioSituacionPatPasPK;
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

    public SocioSituacionPatPas() {
    }

    public SocioSituacionPatPas(SocioSituacionPatPasPK socioSituacionPatPasPK) {
        this.socioSituacionPatPasPK = socioSituacionPatPasPK;
    }

    public SocioSituacionPatPas(SocioSituacionPatPasPK socioSituacionPatPasPK, BigDecimal total) {
        this.socioSituacionPatPasPK = socioSituacionPatPasPK;
        this.total = total;
    }

    public SocioSituacionPatPas(long codigoItemSitPat, long codigoPersona) {
        this.socioSituacionPatPasPK = new SocioSituacionPatPasPK(codigoItemSitPat, codigoPersona);
    }

    public SocioSituacionPatPasPK getSocioSituacionPatPasPK() {
        return socioSituacionPatPasPK;
    }

    public void setSocioSituacionPatPasPK(SocioSituacionPatPasPK socioSituacionPatPasPK) {
        this.socioSituacionPatPasPK = socioSituacionPatPasPK;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
    
    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socioSituacionPatPasPK != null ? socioSituacionPatPasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioSituacionPatPas)) {
            return false;
        }
        SocioSituacionPatPas other = (SocioSituacionPatPas) object;
        if ((this.socioSituacionPatPasPK == null && other.socioSituacionPatPasPK != null) || (this.socioSituacionPatPasPK != null && !this.socioSituacionPatPasPK.equals(other.socioSituacionPatPasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioSituacionPatPas[ socioSituacionPatPasPK=" + socioSituacionPatPasPK + " ]";
    }
    
}