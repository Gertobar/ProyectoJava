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
public class BloqueoCuentaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA")
    private long codigoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_BLOQUEO")
    private long codigoTipoBloqueo;

    public BloqueoCuentaPK() {
    }

    public BloqueoCuentaPK(long codigoCuenta, long codigoTipoBloqueo) {
        this.codigoCuenta = codigoCuenta;
        this.codigoTipoBloqueo = codigoTipoBloqueo;
    }

    public long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public long getCodigoTipoBloqueo() {
        return codigoTipoBloqueo;
    }

    public void setCodigoTipoBloqueo(long codigoTipoBloqueo) {
        this.codigoTipoBloqueo = codigoTipoBloqueo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoCuenta;
        hash += (int) codigoTipoBloqueo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloqueoCuentaPK)) {
            return false;
        }
        BloqueoCuentaPK other = (BloqueoCuentaPK) object;
        if (this.codigoCuenta != other.codigoCuenta) {
            return false;
        }
        if (this.codigoTipoBloqueo != other.codigoTipoBloqueo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.BloqueoCuentaPK[ codigoCuenta=" + codigoCuenta + ", codigoTipoBloqueo=" + codigoTipoBloqueo + " ]";
    }
    
}
