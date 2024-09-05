/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vastudillo
 */
@Embeddable
public class ComprobanteDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPROBANTE")
    private long codigoComprobante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PLAN")
    private long codigoTipoPlan;

    public ComprobanteDetallePK() {
    }

    public ComprobanteDetallePK(long codigoComprobante, String cuentaContable, long codigoTipoPlan) {
        this.codigoComprobante = codigoComprobante;
        this.cuentaContable = cuentaContable;
        this.codigoTipoPlan = codigoTipoPlan;
    }

    public long getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(long codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public long getCodigoTipoPlan() {
        return codigoTipoPlan;
    }

    public void setCodigoTipoPlan(long codigoTipoPlan) {
        this.codigoTipoPlan = codigoTipoPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoComprobante;
        hash += (cuentaContable != null ? cuentaContable.hashCode() : 0);
        hash += (int) codigoTipoPlan;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComprobanteDetallePK)) {
            return false;
        }
        ComprobanteDetallePK other = (ComprobanteDetallePK) object;
        if (this.codigoComprobante != other.codigoComprobante) {
            return false;
        }
        if ((this.cuentaContable == null && other.cuentaContable != null) || (this.cuentaContable != null && !this.cuentaContable.equals(other.cuentaContable))) {
            return false;
        }
        if (this.codigoTipoPlan != other.codigoTipoPlan) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ComprobanteDetallePK[ codigoComprobante=" + codigoComprobante + ", cuentaContable=" + cuentaContable + ", codigoTipoPlan=" + codigoTipoPlan + " ]";
    }
    
}
