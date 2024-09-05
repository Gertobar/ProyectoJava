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
 * @author santiago
 */
@Embeddable
public class ReporteFragmentoIfipPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_REPORTE")
    private long codigoReporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_FRAGMENTO")
    private long codigoFragmento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public ReporteFragmentoIfipPK() {
    }

    public ReporteFragmentoIfipPK(long codigoReporte, long codigoFragmento, long codigoIfip) {
        this.codigoReporte = codigoReporte;
        this.codigoFragmento = codigoFragmento;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoReporte() {
        return codigoReporte;
    }

    public void setCodigoReporte(long codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    public long getCodigoFragmento() {
        return codigoFragmento;
    }

    public void setCodigoFragmento(long codigoFragmento) {
        this.codigoFragmento = codigoFragmento;
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
        hash += (int) codigoReporte;
        hash += (int) codigoFragmento;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteFragmentoIfipPK)) {
            return false;
        }
        ReporteFragmentoIfipPK other = (ReporteFragmentoIfipPK) object;
        if (this.codigoReporte != other.codigoReporte) {
            return false;
        }
        if (this.codigoFragmento != other.codigoFragmento) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.ReporteFragmentoIfipPK[ codigoReporte=" + codigoReporte + ", codigoFragmento=" + codigoFragmento + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
