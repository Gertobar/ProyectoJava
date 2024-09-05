/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

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
public class FraccionMonedaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_FRACCION")
    private long codigoTipoFraccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;

    public FraccionMonedaPK() {
    }

    public FraccionMonedaPK(long codigoTipoFraccion, long codigoMoneda) {
        this.codigoTipoFraccion = codigoTipoFraccion;
        this.codigoMoneda = codigoMoneda;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoTipoFraccion;
        hash += (int) codigoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FraccionMonedaPK)) {
            return false;
        }
        FraccionMonedaPK other = (FraccionMonedaPK) object;
        if (this.codigoTipoFraccion != other.codigoTipoFraccion) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.FraccionMonedaPK[ codigoTipoFraccion=" + codigoTipoFraccion + ", codigoMoneda=" + codigoMoneda + " ]";
    }
    
}
