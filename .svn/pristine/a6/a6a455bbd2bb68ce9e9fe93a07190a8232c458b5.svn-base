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
public class ProcesoContableDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROCESO")
    private long codigoProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PLAN")
    private long codigoTipoPlan;

    public ProcesoContableDetallePK() {
    }

    public ProcesoContableDetallePK(long codigoProceso, String cuentaContable, long codigoTipoPlan) {
        this.codigoProceso = codigoProceso;
        this.cuentaContable = cuentaContable;
        this.codigoTipoPlan = codigoTipoPlan;
    }

    public long getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(long codigoProceso) {
        this.codigoProceso = codigoProceso;
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
        hash += (int) codigoProceso;
        hash += (cuentaContable != null ? cuentaContable.hashCode() : 0);
        hash += (int) codigoTipoPlan;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoContableDetallePK)) {
            return false;
        }
        ProcesoContableDetallePK other = (ProcesoContableDetallePK) object;
        if (this.codigoProceso != other.codigoProceso) {
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
        return "ec.renafipse.mks.modelo.contables.ProcesoContableDetallePK[ codigoProceso=" + codigoProceso + ", cuentaContable=" + cuentaContable + ", codigoTipoPlan=" + codigoTipoPlan + " ]";
    }
    
}
