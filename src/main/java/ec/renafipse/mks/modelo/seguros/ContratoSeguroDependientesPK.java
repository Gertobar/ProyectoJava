/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author scordero
 */
@Embeddable
public class ContratoSeguroDependientesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SEGURO")
    private long codigoSeguro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;

    public ContratoSeguroDependientesPK() {
    }

    public ContratoSeguroDependientesPK(long codigoSeguro, long codigoPersona) {
        this.codigoSeguro = codigoSeguro;
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoSeguro() {
        return codigoSeguro;
    }

    public void setCodigoSeguro(long codigoSeguro) {
        this.codigoSeguro = codigoSeguro;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoSeguro;
        hash += (int) codigoPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoSeguroDependientesPK)) {
            return false;
        }
        ContratoSeguroDependientesPK other = (ContratoSeguroDependientesPK) object;
        if (this.codigoSeguro != other.codigoSeguro) {
            return false;
        }
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguros.ContratoSeguroDependientesPK[ codigoSeguro=" + codigoSeguro + ", codigoPersona=" + codigoPersona + " ]";
    }
    
}
