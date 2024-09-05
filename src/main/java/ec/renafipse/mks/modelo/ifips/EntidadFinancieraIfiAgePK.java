/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

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
public class EntidadFinancieraIfiAgePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private long codigoEntidadFinanciera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;

    public EntidadFinancieraIfiAgePK() {
    }

    public EntidadFinancieraIfiAgePK(long codigoEntidadFinanciera, long codigoIfipAgencia) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoEntidadFinanciera;
        hash += (int) codigoIfipAgencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadFinancieraIfiAgePK)) {
            return false;
        }
        EntidadFinancieraIfiAgePK other = (EntidadFinancieraIfiAgePK) object;
        if (this.codigoEntidadFinanciera != other.codigoEntidadFinanciera) {
            return false;
        }
        if (this.codigoIfipAgencia != other.codigoIfipAgencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.EntidadFinancieraIfiAgePK[ codigoEntidadFinanciera=" + codigoEntidadFinanciera + ", codigoIfipAgencia=" + codigoIfipAgencia + " ]";
    }
    
}
