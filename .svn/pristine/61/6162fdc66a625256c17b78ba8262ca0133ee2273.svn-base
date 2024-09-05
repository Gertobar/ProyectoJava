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

/**
 *
 * @author vastudillo
 */
@Embeddable
public class ProcesoContableIngEgrMonPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROCESO")
    private long codigoProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_INGRESO_EGRESO")
    private long codigoIngresoEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;

    public ProcesoContableIngEgrMonPK() {
    }

    public ProcesoContableIngEgrMonPK(long codigoProceso, long codigoIngresoEgreso, long codigoMoneda) {
        this.codigoProceso = codigoProceso;
        this.codigoIngresoEgreso = codigoIngresoEgreso;
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(long codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public long getCodigoIngresoEgreso() {
        return codigoIngresoEgreso;
    }

    public void setCodigoIngresoEgreso(long codigoIngresoEgreso) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoProceso;
        hash += (int) codigoIngresoEgreso;
        hash += (int) codigoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoContableIngEgrMonPK)) {
            return false;
        }
        ProcesoContableIngEgrMonPK other = (ProcesoContableIngEgrMonPK) object;
        if (this.codigoProceso != other.codigoProceso) {
            return false;
        }
        if (this.codigoIngresoEgreso != other.codigoIngresoEgreso) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ProcesoContableIngEgrMonPK[ codigoProceso=" + codigoProceso + ", codigoIngresoEgreso=" + codigoIngresoEgreso + ", codigoMoneda=" + codigoMoneda + " ]";
    }
    
}
