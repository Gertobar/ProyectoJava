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
public class TipoGarantiaCreditoPK implements Serializable {
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
    @Column(name = "CODIGO_TIPO_GARANTIA")
    private long codigoTipoGarantia;

    public TipoGarantiaCreditoPK() {
    }

    public TipoGarantiaCreditoPK(long numeroCredito, long codigoIfip, long codigoTipoGarantia) {
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
        this.codigoTipoGarantia = codigoTipoGarantia;
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

    public long getCodigoTipoGarantia() {
        return codigoTipoGarantia;
    }

    public void setCodigoTipoGarantia(long codigoTipoGarantia) {
        this.codigoTipoGarantia = codigoTipoGarantia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroCredito;
        hash += (int) codigoIfip;
        hash += (int) codigoTipoGarantia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoGarantiaCreditoPK)) {
            return false;
        }
        TipoGarantiaCreditoPK other = (TipoGarantiaCreditoPK) object;
        if (this.numeroCredito != other.numeroCredito) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.codigoTipoGarantia != other.codigoTipoGarantia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoGarantiaCreditoPK[ numeroCredito=" + numeroCredito + ", codigoIfip=" + codigoIfip + ", codigoTipoGarantia=" + codigoTipoGarantia + " ]";
    }
    
}
