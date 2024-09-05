/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

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
public class ProcesoContableMadCarPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROCESO")
    private long codigoProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MADURACION")
    private long codigoMaduracion;

    public ProcesoContableMadCarPK() {
    }

    public ProcesoContableMadCarPK(long codigoProceso, long codigoMaduracion) {
        this.codigoProceso = codigoProceso;
        this.codigoMaduracion = codigoMaduracion;
    }

    public long getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(long codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public long getCodigoMaduracion() {
        return codigoMaduracion;
    }

    public void setCodigoMaduracion(long codigoMaduracion) {
        this.codigoMaduracion = codigoMaduracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoProceso;
        hash += (int) codigoMaduracion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoContableMadCarPK)) {
            return false;
        }
        ProcesoContableMadCarPK other = (ProcesoContableMadCarPK) object;
        if (this.codigoProceso != other.codigoProceso) {
            return false;
        }
        if (this.codigoMaduracion != other.codigoMaduracion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ProcesoContableMadCarPK[ codigoProceso=" + codigoProceso + ", codigoMaduracion=" + codigoMaduracion + " ]";
    }
    
}
