/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.LIQUIDACION_CUENTA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LiquidacionCuentaDetalle.findAll", query = "SELECT l FROM LiquidacionCuentaDetalle l"),
    @NamedQuery(name = "LiquidacionCuentaDetalle.findByCodigoLiqCta", query = "SELECT l FROM LiquidacionCuentaDetalle l WHERE l.liquidacionCuentaDetallePK.codigoLiqCta = :codigoLiqCta"),
    @NamedQuery(name = "LiquidacionCuentaDetalle.findByCodigoCuenta", query = "SELECT l FROM LiquidacionCuentaDetalle l WHERE l.liquidacionCuentaDetallePK.codigoCuenta = :codigoCuenta"),
    @NamedQuery(name = "LiquidacionCuentaDetalle.findBySaldo", query = "SELECT l FROM LiquidacionCuentaDetalle l WHERE l.saldo = :saldo"),
    @NamedQuery(name = "LiquidacionCuentaDetalle.findByInteres", query = "SELECT l FROM LiquidacionCuentaDetalle l WHERE l.interes = :interes")})
public class LiquidacionCuentaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LiquidacionCuentaDetallePK liquidacionCuentaDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES")
    private BigDecimal interes;
    @JoinColumn(name = "CODIGO_LIQ_CTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LiquidacionCuentaCabecera liquidacionCuentaCabecera;
    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public LiquidacionCuentaDetalle() {
    }

    public LiquidacionCuentaDetalle(LiquidacionCuentaDetallePK liquidacionCuentaDetallePK) {
        this.liquidacionCuentaDetallePK = liquidacionCuentaDetallePK;
    }

    public LiquidacionCuentaDetalle(LiquidacionCuentaDetallePK liquidacionCuentaDetallePK, BigDecimal saldo, BigDecimal interes) {
        this.liquidacionCuentaDetallePK = liquidacionCuentaDetallePK;
        this.saldo = saldo;
        this.interes = interes;
    }

    public LiquidacionCuentaDetalle(long codigoLiqCta, long codigoCuenta) {
        this.liquidacionCuentaDetallePK = new LiquidacionCuentaDetallePK(codigoLiqCta, codigoCuenta);
    }

    public LiquidacionCuentaDetallePK getLiquidacionCuentaDetallePK() {
        return liquidacionCuentaDetallePK;
    }

    public void setLiquidacionCuentaDetallePK(LiquidacionCuentaDetallePK liquidacionCuentaDetallePK) {
        this.liquidacionCuentaDetallePK = liquidacionCuentaDetallePK;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public LiquidacionCuentaCabecera getLiquidacionCuentaCabecera() {
        return liquidacionCuentaCabecera;
    }

    public void setLiquidacionCuentaCabecera(LiquidacionCuentaCabecera liquidacionCuentaCabecera) {
        this.liquidacionCuentaCabecera = liquidacionCuentaCabecera;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liquidacionCuentaDetallePK != null ? liquidacionCuentaDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionCuentaDetalle)) {
            return false;
        }
        LiquidacionCuentaDetalle other = (LiquidacionCuentaDetalle) object;
        if ((this.liquidacionCuentaDetallePK == null && other.liquidacionCuentaDetallePK != null) || (this.liquidacionCuentaDetallePK != null && !this.liquidacionCuentaDetallePK.equals(other.liquidacionCuentaDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.LiquidacionCuentaDetalle[ liquidacionCuentaDetallePK=" + liquidacionCuentaDetallePK + " ]";
    }
    
}
