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
@Table(name = "MKS_SOCIOS.SOCIO_FLUJO_CAJA_EGRESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioFlujoCajaEgreso.findAll", query = "SELECT s FROM SocioFlujoCajaEgreso s"),
    @NamedQuery(name = "SocioFlujoCajaEgreso.findByCodigoItemFluCaj", query = "SELECT s FROM SocioFlujoCajaEgreso s WHERE s.socioFlujoCajaEgresoPK.codigoItemFluCaj = :codigoItemFluCaj"),
    @NamedQuery(name = "SocioFlujoCajaEgreso.findByTotalSocio", query = "SELECT s FROM SocioFlujoCajaEgreso s WHERE s.totalSocio = :totalSocio"),
     // Personalizados
    @NamedQuery(name = "SocioFlujoCajaEgreso.findByCodigoSocioCodigoIfip", query = "SELECT s FROM SocioFlujoCajaEgreso s WHERE s.socioFlujoCaja.codigoSocio = :codigoSocio AND s.socioFlujoCaja.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "SocioFlujoCajaEgreso.findByCodigoPersona", query = "SELECT s FROM SocioFlujoCajaEgreso s WHERE s.codigoPersona = :codigoPersona")
})
public class SocioFlujoCajaEgreso implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByCodigoSocioCodigoIfip = "SocioFlujoCajaEgreso.findByCodigoSocioCodigoIfip";
    @EmbeddedId
    protected SocioFlujoCajaEgresoPK socioFlujoCajaEgresoPK;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO_SOCIO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SocioFlujoCaja socioFlujoCaja;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_SOCIO")
    private BigDecimal totalSocio;
    @JoinColumn(name = "CODIGO_ITEM_FLU_CAJ", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ItemFlujoCaja itemFlujoCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA", insertable = false, updatable = false)
    private long codigoPersona;

    public SocioFlujoCajaEgreso() {
    }

    public SocioFlujoCajaEgreso(SocioFlujoCajaEgresoPK socioFlujoCajaEgresoPK) {
        this.socioFlujoCajaEgresoPK = socioFlujoCajaEgresoPK;
    }

    public SocioFlujoCajaEgreso(SocioFlujoCajaEgresoPK socioFlujoCajaEgresoPK, BigDecimal totalSocio) {
        this.socioFlujoCajaEgresoPK = socioFlujoCajaEgresoPK;
        this.totalSocio = totalSocio;
    }

    public SocioFlujoCajaEgreso(long codigoItemFluCaj, long codigoPersona) {
        this.socioFlujoCajaEgresoPK = new SocioFlujoCajaEgresoPK(codigoItemFluCaj, codigoPersona);
    }

    public SocioFlujoCajaEgresoPK getSocioFlujoCajaEgresoPK() {
        return socioFlujoCajaEgresoPK;
    }

    public void setSocioFlujoCajaEgresoPK(SocioFlujoCajaEgresoPK socioFlujoCajaEgresoPK) {
        this.socioFlujoCajaEgresoPK = socioFlujoCajaEgresoPK;
    }

    public SocioFlujoCaja getSocioFlujoCaja() {
        return socioFlujoCaja;
    }

    public void setSocioFlujoCaja(SocioFlujoCaja socioFlujoCaja) {
        this.socioFlujoCaja = socioFlujoCaja;
    }
    
    public BigDecimal getTotalSocio() {
        return totalSocio;
    }

    public void setTotalSocio(BigDecimal totalSocio) {
        this.totalSocio = totalSocio;
    }
    
    public ItemFlujoCaja getItemFlujoCaja() {
        return itemFlujoCaja;
    }

    public void setItemFlujoCaja(ItemFlujoCaja itemFlujoCaja) {
        this.itemFlujoCaja = itemFlujoCaja;
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
        hash += (socioFlujoCajaEgresoPK != null ? socioFlujoCajaEgresoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioFlujoCajaEgreso)) {
            return false;
        }
        SocioFlujoCajaEgreso other = (SocioFlujoCajaEgreso) object;
        if ((this.socioFlujoCajaEgresoPK == null && other.socioFlujoCajaEgresoPK != null) || (this.socioFlujoCajaEgresoPK != null && !this.socioFlujoCajaEgresoPK.equals(other.socioFlujoCajaEgresoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgreso[ socioFlujoCajaEgresoPK=" + socioFlujoCajaEgresoPK + " ]";
    }
    
}