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

/**
 *
 * @author SISTEMAS
 */
@Embeddable
public class InformeTecnicoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "NUMERO_SOLICITUD")
    private long numeroSolicitud;
    @Basic(optional = false)
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public InformeTecnicoPK() {
    }

    public InformeTecnicoPK(long numeroSolicitud, long codigoIfip) {
        this.numeroSolicitud = numeroSolicitud;
        this.codigoIfip = codigoIfip;
    }

    public long getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(long numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
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
        hash += (int) numeroSolicitud;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformeTecnicoPK)) {
            return false;
        }
        InformeTecnicoPK other = (InformeTecnicoPK) object;
        if (this.numeroSolicitud != other.numeroSolicitud) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject2.InformeTecnicoPK[ numeroSolicitud=" + numeroSolicitud + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
