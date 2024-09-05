/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

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
public class TipoPerTipoIdePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PERSONA")
    private long codigoTipoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_IDENTIFICACION")
    private long codigoTipoIdentificacion;

    public TipoPerTipoIdePK() {
    }

    public TipoPerTipoIdePK(long codigoTipoPersona, long codigoTipoIdentificacion) {
        this.codigoTipoPersona = codigoTipoPersona;
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public long getCodigoTipoPersona() {
        return codigoTipoPersona;
    }

    public void setCodigoTipoPersona(long codigoTipoPersona) {
        this.codigoTipoPersona = codigoTipoPersona;
    }

    public long getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(long codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoTipoPersona;
        hash += (int) codigoTipoIdentificacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerTipoIdePK)) {
            return false;
        }
        TipoPerTipoIdePK other = (TipoPerTipoIdePK) object;
        if (this.codigoTipoPersona != other.codigoTipoPersona) {
            return false;
        }
        if (this.codigoTipoIdentificacion != other.codigoTipoIdentificacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoPerTipoIdePK[ codigoTipoPersona=" + codigoTipoPersona + ", codigoTipoIdentificacion=" + codigoTipoIdentificacion + " ]";
    }
    
}
