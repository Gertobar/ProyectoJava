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
public class IfipAgenciaSecuenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SECUENCIA")
    private long codigoSecuencia;

    public IfipAgenciaSecuenciaPK() {
    }

    public IfipAgenciaSecuenciaPK(long codigoIfipAgencia, long codigoSecuencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoSecuencia = codigoSecuencia;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoSecuencia() {
        return codigoSecuencia;
    }

    public void setCodigoSecuencia(long codigoSecuencia) {
        this.codigoSecuencia = codigoSecuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoIfipAgencia;
        hash += (int) codigoSecuencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipAgenciaSecuenciaPK)) {
            return false;
        }
        IfipAgenciaSecuenciaPK other = (IfipAgenciaSecuenciaPK) object;
        if (this.codigoIfipAgencia != other.codigoIfipAgencia) {
            return false;
        }
        if (this.codigoSecuencia != other.codigoSecuencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipAgenciaSecuenciaPK[ codigoIfipAgencia=" + codigoIfipAgencia + ", codigoSecuencia=" + codigoSecuencia + " ]";
    }
    
}
