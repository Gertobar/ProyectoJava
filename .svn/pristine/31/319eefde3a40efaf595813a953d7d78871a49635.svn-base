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
@Table(name = "MKS_SOCIOS.SOCIO_FLUJO_CAJA_INGRESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioFlujoCajaIngreso.findAll", query = "SELECT s FROM SocioFlujoCajaIngreso s"),
    @NamedQuery(name = "SocioFlujoCajaIngreso.findByCodigoItemFluCaj", query = "SELECT s FROM SocioFlujoCajaIngreso s WHERE s.socioFlujoCajaIngresoPK.codigoItemFluCaj = :codigoItemFluCaj"),
    @NamedQuery(name = "SocioFlujoCajaIngreso.findByTotalSocio", query = "SELECT s FROM SocioFlujoCajaIngreso s WHERE s.totalSocio = :totalSocio"),
    @NamedQuery(name = "SocioFlujoCajaIngreso.findByTotalConyuge", query = "SELECT s FROM SocioFlujoCajaIngreso s WHERE s.totalConyuge = :totalConyuge"),
    // Personalizados
    @NamedQuery(name = "SocioFlujoCajaIngreso.findByCodigoPersonaCodigoIfip", query = "SELECT s FROM SocioFlujoCajaIngreso s WHERE s.socioFlujoCajaIngresoPK.codigoPersona = :codigoPersona AND s.socioFlujoCajaIngresoPK.codigoItemFluCaj = :codigoItemFluCaj ORDER BY s.itemFlujoCaja.indice"),
    @NamedQuery(name = "SocioFlujoCajaIngreso.findByCodigoSocioCodigoIfip", query = "SELECT s FROM SocioFlujoCajaIngreso s WHERE s.socioFlujoCaja.codigoSocio = :codigoSocio AND s.socioFlujoCaja.codigoIfip = :codigoIfip"),    
    @NamedQuery(name = "SocioFlujoCajaIngreso.findByCodigoPersona", query = "SELECT s FROM SocioFlujoCajaIngreso s WHERE s.codigoPersona = :codigoPersona")
    //
        
})
public class SocioFlujoCajaIngreso implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSocioCodigoIfip = "SocioFlujoCajaIngreso.findByCodigoSocioCodigoIfip";
    @EmbeddedId
    protected SocioFlujoCajaIngresoPK socioFlujoCajaIngresoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_SOCIO")
    private BigDecimal totalSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CONYUGE")
    private BigDecimal totalConyuge;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO_SOCIO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SocioFlujoCaja socioFlujoCaja;
    @JoinColumn(name = "CODIGO_ITEM_FLU_CAJ", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ItemFlujoCaja itemFlujoCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA", insertable = false, updatable = false)
    private long codigoPersona;
    
    public SocioFlujoCajaIngreso() {
    }

    public SocioFlujoCajaIngreso(SocioFlujoCajaIngresoPK socioFlujoCajaIngresoPK) {
        this.socioFlujoCajaIngresoPK = socioFlujoCajaIngresoPK;
    }

    public SocioFlujoCajaIngreso(SocioFlujoCajaIngresoPK socioFlujoCajaIngresoPK, BigDecimal totalSocio, BigDecimal totalConyuge) {
        this.socioFlujoCajaIngresoPK = socioFlujoCajaIngresoPK;
        this.totalSocio = totalSocio;
        this.totalConyuge = totalConyuge;
    }

    public SocioFlujoCajaIngreso(long codigoItemFluCaj, long codigoPersona) {
        this.socioFlujoCajaIngresoPK = new SocioFlujoCajaIngresoPK(codigoItemFluCaj, codigoPersona);
    }

    public SocioFlujoCajaIngresoPK getSocioFlujoCajaIngresoPK() {
        return socioFlujoCajaIngresoPK;
    }

    public void setSocioFlujoCajaIngresoPK(SocioFlujoCajaIngresoPK socioFlujoCajaIngresoPK) {
        this.socioFlujoCajaIngresoPK = socioFlujoCajaIngresoPK;
    }
    
    public BigDecimal getTotalSocio() {
        return totalSocio;
    }

    public void setTotalSocio(BigDecimal totalSocio) {
        this.totalSocio = totalSocio;
    }

    public BigDecimal getTotalConyuge() {
        return totalConyuge;
    }

    public void setTotalConyuge(BigDecimal totalConyuge) {
        this.totalConyuge = totalConyuge;
    }
    
     public SocioFlujoCaja getSocioFlujoCaja() {
        return socioFlujoCaja;
    }

    public void setSocioFlujoCaja(SocioFlujoCaja socioFlujoCaja) {
        this.socioFlujoCaja = socioFlujoCaja;
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
        hash += (socioFlujoCajaIngresoPK != null ? socioFlujoCajaIngresoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioFlujoCajaIngreso)) {
            return false;
        }
        SocioFlujoCajaIngreso other = (SocioFlujoCajaIngreso) object;
        if ((this.socioFlujoCajaIngresoPK == null && other.socioFlujoCajaIngresoPK != null) || (this.socioFlujoCajaIngresoPK != null && !this.socioFlujoCajaIngresoPK.equals(other.socioFlujoCajaIngresoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioFlujoCajaIngreso[ socioFlujoCajaIngresoPK=" + socioFlujoCajaIngresoPK + " ]";
    }
}