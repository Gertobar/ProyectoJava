/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Viajes2
 */
@Entity
@Table(name = "MKS_IFIPS.BOVEDA_DINERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BovedaDinero.findAll", query = "SELECT b FROM BovedaDinero b"),
    @NamedQuery(name = "BovedaDinero.findByCodigoBoveda", query = "SELECT b FROM BovedaDinero b WHERE b.bovedaDineroPK.codigoBoveda = :codigoBoveda"),
    @NamedQuery(name = "BovedaDinero.findByCodigoMoneda", query = "SELECT b FROM BovedaDinero b WHERE b.bovedaDineroPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "BovedaDinero.findByValorEfectivo", query = "SELECT b FROM BovedaDinero b WHERE b.valorEfectivo = :valorEfectivo"),
    @NamedQuery(name = "BovedaDinero.findByTotalDinero", query = "SELECT b FROM BovedaDinero b WHERE b.totalDinero = :totalDinero"),
    //Perssonalizados
    @NamedQuery(name = "BovedaDinero.findByBovedaMonedaFondeoCaja", query = "SELECT v FROM BovedaDinero b JOIN b.boveda v WHERE v.codigoIfipAgencia.codigo = :codigoIfipAgencia AND v.responsable = :responsable AND v.eliminado = :eliminado AND b.bovedaDineroPK.codigoMoneda = :codigoMoneda AND b.totalDinero >= :totalDinero"),
    @NamedQuery(name = "BovedaDinero.findByBovedaMonedaMovimientoBoveda", query = "SELECT v FROM BovedaDinero b JOIN b.boveda v WHERE v.codigoIfipAgencia.codigo = :codigoIfipAgencia AND b.bovedaDineroPK.codigoMoneda = :codigoMoneda AND v.responsable = :responsable AND v.eliminado = :eliminado ")
})
public class BovedaDinero implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByBovedaMonedaFondeoCaja="BovedaDinero.findByBovedaMonedaFondeoCaja";
    public static final String findByBovedaMonedaMovimientoBoveda="BovedaDinero.findByBovedaMonedaMovimientoBoveda";
    @EmbeddedId
    protected BovedaDineroPK bovedaDineroPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_EFECTIVO")
    private BigDecimal valorEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_DINERO")
    private BigDecimal totalDinero;
    @JoinColumn(name = "CODIGO_BOVEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Boveda boveda;

    public BovedaDinero() {
    }

    public BovedaDinero(BovedaDineroPK bovedaDineroPK) {
        this.bovedaDineroPK = bovedaDineroPK;
    }

    public BovedaDinero(BovedaDineroPK bovedaDineroPK, BigDecimal valorEfectivo, BigDecimal totalDinero) {
        this.bovedaDineroPK = bovedaDineroPK;
        this.valorEfectivo = valorEfectivo;
        this.totalDinero = totalDinero;
    }

    public BovedaDinero(long codigoBoveda, long codigoMoneda) {
        this.bovedaDineroPK = new BovedaDineroPK(codigoBoveda, codigoMoneda);
    }

    public BovedaDineroPK getBovedaDineroPK() {
        return bovedaDineroPK;
    }

    public void setBovedaDineroPK(BovedaDineroPK bovedaDineroPK) {
        this.bovedaDineroPK = bovedaDineroPK;
    }

    public BigDecimal getValorEfectivo() {
        return valorEfectivo;
    }

    public void setValorEfectivo(BigDecimal valorEfectivo) {
        this.valorEfectivo = valorEfectivo;
    }

    public BigDecimal getTotalDinero() {
        return totalDinero;
    }

    public void setTotalDinero(BigDecimal totalDinero) {
        this.totalDinero = totalDinero;
    }

    public Boveda getBoveda() {
        return boveda;
    }

    public void setBoveda(Boveda boveda) {
        this.boveda = boveda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bovedaDineroPK != null ? bovedaDineroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BovedaDinero)) {
            return false;
        }
        BovedaDinero other = (BovedaDinero) object;
        if ((this.bovedaDineroPK == null && other.bovedaDineroPK != null) || (this.bovedaDineroPK != null && !this.bovedaDineroPK.equals(other.bovedaDineroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.BovedaDinero[ bovedaDineroPK=" + bovedaDineroPK + " ]";
    }
    
}
