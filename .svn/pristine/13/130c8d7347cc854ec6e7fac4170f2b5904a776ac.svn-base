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
public class GaranteNotificacionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_NOTIFICACION")
    private long codigoNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_GARANTE")
    private long codigoGarante;

    public GaranteNotificacionPK() {
    }

    public GaranteNotificacionPK(long codigoNotificacion, long codigoGarante) {
        this.codigoNotificacion = codigoNotificacion;
        this.codigoGarante = codigoGarante;
    }

    public long getCodigoNotificacion() {
        return codigoNotificacion;
    }

    public void setCodigoNotificacion(long codigoNotificacion) {
        this.codigoNotificacion = codigoNotificacion;
    }

    public long getCodigoGarante() {
        return codigoGarante;
    }

    public void setCodigoGarante(long codigoGarante) {
        this.codigoGarante = codigoGarante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoNotificacion;
        hash += (int) codigoGarante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GaranteNotificacionPK)) {
            return false;
        }
        GaranteNotificacionPK other = (GaranteNotificacionPK) object;
        if (this.codigoNotificacion != other.codigoNotificacion) {
            return false;
        }
        if (this.codigoGarante != other.codigoGarante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.GaranteNotificacionPK[ codigoNotificacion=" + codigoNotificacion + ", codigoGarante=" + codigoGarante + " ]";
    }
    
}
