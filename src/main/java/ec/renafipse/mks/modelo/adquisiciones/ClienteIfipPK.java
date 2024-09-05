/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

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
public class ClienteIfipPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CLIENTE")
    private long codigoCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public ClienteIfipPK() {
    }

    public ClienteIfipPK(long codigoCliente, long codigoIfip) {
        this.codigoCliente = codigoCliente;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(long codigoCliente) {
        this.codigoCliente = codigoCliente;
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
        hash += (int) codigoCliente;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteIfipPK)) {
            return false;
        }
        ClienteIfipPK other = (ClienteIfipPK) object;
        if (this.codigoCliente != other.codigoCliente) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adqusiciones.ClienteIfipPK[ codigoCliente=" + codigoCliente + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
