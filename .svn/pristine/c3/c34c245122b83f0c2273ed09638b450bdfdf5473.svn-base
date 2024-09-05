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
 * @author renafipse1
 */
@Embeddable
public class DiasEnvioNotificacionIfipPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_NOTIFICACION")
    private long codigoTipoNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public DiasEnvioNotificacionIfipPK() {
    }

    public DiasEnvioNotificacionIfipPK(long codigoTipoNotificacion, long codigoIfip) {
        this.codigoTipoNotificacion = codigoTipoNotificacion;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoTipoNotificacion() {
        return codigoTipoNotificacion;
    }

    public void setCodigoTipoNotificacion(long codigoTipoNotificacion) {
        this.codigoTipoNotificacion = codigoTipoNotificacion;
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
        hash += (int) codigoTipoNotificacion;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiasEnvioNotificacionIfipPK)) {
            return false;
        }
        DiasEnvioNotificacionIfipPK other = (DiasEnvioNotificacionIfipPK) object;
        if (this.codigoTipoNotificacion != other.codigoTipoNotificacion) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.DiasEnvioNotificacionIfipPK[ codigoTipoNotificacion=" + codigoTipoNotificacion + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
