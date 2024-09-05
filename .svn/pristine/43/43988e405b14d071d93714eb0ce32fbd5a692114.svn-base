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
 * @author v.astudillo
 */
@Embeddable
public class RetencionDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_RETENCION")
    private long codigoRetencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_RETENCION")
    private long codigoTipoRetencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPRA_DETALLE")
    private long codigoCompraDetalle;

    public RetencionDetallePK() {
    }

    public RetencionDetallePK(long codigoRetencion, long codigoTipoRetencion, long codigoCompraDetalle) {
        this.codigoRetencion = codigoRetencion;
        this.codigoTipoRetencion = codigoTipoRetencion;
        this.codigoCompraDetalle = codigoCompraDetalle;
    }

    public long getCodigoRetencion() {
        return codigoRetencion;
    }

    public void setCodigoRetencion(long codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    public long getCodigoTipoRetencion() {
        return codigoTipoRetencion;
    }

    public void setCodigoTipoRetencion(long codigoTipoRetencion) {
        this.codigoTipoRetencion = codigoTipoRetencion;
    }

    public long getCodigoCompraDetalle() {
        return codigoCompraDetalle;
    }

    public void setCodigoCompraDetalle(long codigoCompraDetalle) {
        this.codigoCompraDetalle = codigoCompraDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoRetencion;
        hash += (int) codigoTipoRetencion;
        hash += (int) codigoCompraDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetencionDetallePK)) {
            return false;
        }
        RetencionDetallePK other = (RetencionDetallePK) object;
        if (this.codigoRetencion != other.codigoRetencion) {
            return false;
        }
        if (this.codigoTipoRetencion != other.codigoTipoRetencion) {
            return false;
        }
        if (this.codigoCompraDetalle != other.codigoCompraDetalle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.RetencionDetallePK[ codigoRetencion=" + codigoRetencion + ", codigoTipoRetencion=" + codigoTipoRetencion + ", codigoCompraDetalle=" + codigoCompraDetalle + " ]";
    }
    
}
