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
 * @author Viajes2
 */
@Embeddable
public class MovimientoBovedaDesEfePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_FRACCION")
    private long codigoTipoFraccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO_BOVEDA")
    private long codigoMovimientoBoveda;

    public MovimientoBovedaDesEfePK() {
    }

    public MovimientoBovedaDesEfePK(long codigoTipoFraccion, long codigoMoneda, long codigoMovimientoBoveda) {
        this.codigoTipoFraccion = codigoTipoFraccion;
        this.codigoMoneda = codigoMoneda;
        this.codigoMovimientoBoveda = codigoMovimientoBoveda;
    }

    public long getCodigoTipoFraccion() {
        return codigoTipoFraccion;
    }

    public void setCodigoTipoFraccion(long codigoTipoFraccion) {
        this.codigoTipoFraccion = codigoTipoFraccion;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoMovimientoBoveda() {
        return codigoMovimientoBoveda;
    }

    public void setCodigoMovimientoBoveda(long codigoMovimientoBoveda) {
        this.codigoMovimientoBoveda = codigoMovimientoBoveda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoTipoFraccion;
        hash += (int) codigoMoneda;
        hash += (int) codigoMovimientoBoveda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoBovedaDesEfePK)) {
            return false;
        }
        MovimientoBovedaDesEfePK other = (MovimientoBovedaDesEfePK) object;
        if (this.codigoTipoFraccion != other.codigoTipoFraccion) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoMovimientoBoveda != other.codigoMovimientoBoveda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfePK[ codigoTipoFraccion=" + codigoTipoFraccion + ", codigoMoneda=" + codigoMoneda + ", codigoMovimientoBoveda=" + codigoMovimientoBoveda + " ]";
    }
    
}
