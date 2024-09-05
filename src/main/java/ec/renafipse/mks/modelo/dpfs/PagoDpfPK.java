/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

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
public class PagoDpfPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_PAGO")
    private long numeroPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONTRATO")
    private long codigoContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public PagoDpfPK() {
    }

    public PagoDpfPK(long numeroPago, long codigoContrato, long codigoIfip) {
        this.numeroPago = numeroPago;
        this.codigoContrato = codigoContrato;
        this.codigoIfip = codigoIfip;
    }

    public long getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(long numeroPago) {
        this.numeroPago = numeroPago;
    }

    public long getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(long codigoContrato) {
        this.codigoContrato = codigoContrato;
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
        hash += (int) numeroPago;
        hash += (int) codigoContrato;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoDpfPK)) {
            return false;
        }
        PagoDpfPK other = (PagoDpfPK) object;
        if (this.numeroPago != other.numeroPago) {
            return false;
        }
        if (this.codigoContrato != other.codigoContrato) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.negocio.dpfs.PagoDpfPK[ numeroPago=" + numeroPago + ", codigoContrato=" + codigoContrato + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
