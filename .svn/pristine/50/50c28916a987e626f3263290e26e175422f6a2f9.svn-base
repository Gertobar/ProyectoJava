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
public class FirmanteCuentaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA")
    private long codigoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;

    public FirmanteCuentaPK() {
    }

    public FirmanteCuentaPK(long codigoCuenta, long codigoPersona) {
        this.codigoCuenta = codigoCuenta;
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoCuenta;
        hash += (int) codigoPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FirmanteCuentaPK)) {
            return false;
        }
        FirmanteCuentaPK other = (FirmanteCuentaPK) object;
        if (this.codigoCuenta != other.codigoCuenta) {
            return false;
        }
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.FirmanteCuentaPK[ codigoCuenta=" + codigoCuenta + ", codigoPersona=" + codigoPersona + " ]";
    }
    
}
