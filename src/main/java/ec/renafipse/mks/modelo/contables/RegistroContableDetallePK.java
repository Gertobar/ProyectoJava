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
public class RegistroContableDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_REGISTRO")
    private long codigoRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PLAN")
    private long codigoTipoPlan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public RegistroContableDetallePK() {
    }

    public RegistroContableDetallePK(long codigoRegistro, String cuentaContable, long codigoTipoPlan, long codigoIfip) {
        this.codigoRegistro = codigoRegistro;
        this.cuentaContable = cuentaContable;
        this.codigoTipoPlan = codigoTipoPlan;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(long codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
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

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoRegistro;
        hash += (cuentaContable != null ? cuentaContable.hashCode() : 0);
        hash += (int) codigoTipoPlan;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroContableDetallePK)) {
            return false;
        }
        RegistroContableDetallePK other = (RegistroContableDetallePK) object;
        if (this.codigoRegistro != other.codigoRegistro) {
            return false;
        }
        if ((this.cuentaContable == null && other.cuentaContable != null) || (this.cuentaContable != null && !this.cuentaContable.equals(other.cuentaContable))) {
            return false;
        }
        if (this.codigoTipoPlan != other.codigoTipoPlan) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.RegistroContableDetallePK[ codigoRegistro=" + codigoRegistro + ", cuentaContable=" + cuentaContable + ", codigoTipoPlan=" + codigoTipoPlan + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
