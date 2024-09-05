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
 * @author v.astudillo
 */
@Embeddable
public class TipoRubroCreditoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_RUBRO")
    private long codigoTipoRubro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CREDITO")
    private long numeroCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public TipoRubroCreditoPK() {
    }

    public TipoRubroCreditoPK(long codigoTipoRubro, long numeroCredito, long codigoIfip) {
        this.codigoTipoRubro = codigoTipoRubro;
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoTipoRubro() {
        return codigoTipoRubro;
    }

    public void setCodigoTipoRubro(long codigoTipoRubro) {
        this.codigoTipoRubro = codigoTipoRubro;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoTipoRubro;
        hash += (int) numeroCredito;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRubroCreditoPK)) {
            return false;
        }
        TipoRubroCreditoPK other = (TipoRubroCreditoPK) object;
        if (this.codigoTipoRubro != other.codigoTipoRubro) {
            return false;
        }
        if (this.numeroCredito != other.numeroCredito) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoRubroCreditoPK[ codigoTipoRubro=" + codigoTipoRubro + ", numeroCredito=" + numeroCredito + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
