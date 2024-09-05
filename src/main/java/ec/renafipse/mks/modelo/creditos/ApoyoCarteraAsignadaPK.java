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
 * @author Mascoop
 */
@Embeddable
public class ApoyoCarteraAsignadaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA", nullable = false)
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CALIFICACION_ASIGNACION", nullable = false)
    private long codigoCalificacionAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA", nullable = false)
    private long codigoIfipAgencia;

    public ApoyoCarteraAsignadaPK() {
    }

    public ApoyoCarteraAsignadaPK(long codigoPersona, long codigoCalificacionAsignacion, long codigoIfipAgencia) {
        this.codigoPersona = codigoPersona;
        this.codigoCalificacionAsignacion = codigoCalificacionAsignacion;
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoCalificacionAsignacion() {
        return codigoCalificacionAsignacion;
    }

    public void setCodigoCalificacionAsignacion(long codigoCalificacionAsignacion) {
        this.codigoCalificacionAsignacion = codigoCalificacionAsignacion;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPersona;
        hash += (int) codigoCalificacionAsignacion;
        hash += (int) codigoIfipAgencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApoyoCarteraAsignadaPK)) {
            return false;
        }
        ApoyoCarteraAsignadaPK other = (ApoyoCarteraAsignadaPK) object;
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        if (this.codigoCalificacionAsignacion != other.codigoCalificacionAsignacion) {
            return false;
        }
        if (this.codigoIfipAgencia != other.codigoIfipAgencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ApoyoCarteraAsignadaPK[ codigoPersona=" + codigoPersona + ", codigoCalificacionAsignacion=" + codigoCalificacionAsignacion + ", codigoIfipAgencia=" + codigoIfipAgencia + " ]";
    }
    
}
