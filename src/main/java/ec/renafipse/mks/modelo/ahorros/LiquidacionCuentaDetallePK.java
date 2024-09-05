/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vicastoc
 */
@Embeddable
public class LiquidacionCuentaDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LIQ_CTA")
    private long codigoLiqCta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA")
    private long codigoCuenta;

    public LiquidacionCuentaDetallePK() {
    }

    public LiquidacionCuentaDetallePK(long codigoLiqCta, long codigoCuenta) {
        this.codigoLiqCta = codigoLiqCta;
        this.codigoCuenta = codigoCuenta;
    }

    public long getCodigoLiqCta() {
        return codigoLiqCta;
    }

    public void setCodigoLiqCta(long codigoLiqCta) {
        this.codigoLiqCta = codigoLiqCta;
    }

    public long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoLiqCta;
        hash += (int) codigoCuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionCuentaDetallePK)) {
            return false;
        }
        LiquidacionCuentaDetallePK other = (LiquidacionCuentaDetallePK) object;
        if (this.codigoLiqCta != other.codigoLiqCta) {
            return false;
        }
        if (this.codigoCuenta != other.codigoCuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.LiquidacionCuentaDetallePK[ codigoLiqCta=" + codigoLiqCta + ", codigoCuenta=" + codigoCuenta + " ]";
    }
    
}
