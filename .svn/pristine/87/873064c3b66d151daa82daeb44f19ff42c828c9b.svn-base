/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

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
public class TablaAmortizacionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CREDITO")
    private long numeroCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUOTA")
    private long cuota;

    public TablaAmortizacionPK() {
    }

    public TablaAmortizacionPK(long numeroCredito, long codigoIfip, long cuota) {
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
        this.cuota = cuota;
    }

    public long getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCuota() {
        return cuota;
    }

    public void setCuota(long cuota) {
        this.cuota = cuota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroCredito;
        hash += (int) codigoIfip;
        hash += (int) cuota;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaAmortizacionPK)) {
            return false;
        }
        TablaAmortizacionPK other = (TablaAmortizacionPK) object;
        if (this.numeroCredito != other.numeroCredito) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.cuota != other.cuota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TablaAmortizacionPK[ numeroCredito=" + numeroCredito + ", codigoIfip=" + codigoIfip + ", cuota=" + cuota + " ]";
    }
    
}
