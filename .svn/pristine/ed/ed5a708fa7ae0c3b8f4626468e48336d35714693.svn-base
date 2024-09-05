/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class RolReporteDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO_ROL")
    private String codigoRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_REPORTE")
    private long codigoReporte;

    public RolReporteDetallePK() {
    }

    public RolReporteDetallePK(String codigoRol, long codigoReporte) {
        this.codigoRol = codigoRol;
        this.codigoReporte = codigoReporte;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public long getCodigoReporte() {
        return codigoReporte;
    }

    public void setCodigoReporte(long codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRol != null ? codigoRol.hashCode() : 0);
        hash += (int) codigoReporte;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolReporteDetallePK)) {
            return false;
        }
        RolReporteDetallePK other = (RolReporteDetallePK) object;
        if ((this.codigoRol == null && other.codigoRol != null) || (this.codigoRol != null && !this.codigoRol.equals(other.codigoRol))) {
            return false;
        }
        if (this.codigoReporte != other.codigoReporte) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolReporteDetallePK[ codigoRol=" + codigoRol + ", codigoReporte=" + codigoReporte + " ]";
    }
    
}
