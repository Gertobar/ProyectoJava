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
import javax.validation.constraints.Size;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class CierreAnualPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CODIGO_PERIODO")
    private String codigoPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public CierreAnualPK() {
    }

    public CierreAnualPK(String codigoPeriodo, long codigoIfip) {
        this.codigoPeriodo = codigoPeriodo;
        this.codigoIfip = codigoIfip;
    }

    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
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
        hash += (codigoPeriodo != null ? codigoPeriodo.hashCode() : 0);
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreAnualPK)) {
            return false;
        }
        CierreAnualPK other = (CierreAnualPK) object;
        if ((this.codigoPeriodo == null && other.codigoPeriodo != null) || (this.codigoPeriodo != null && !this.codigoPeriodo.equals(other.codigoPeriodo))) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CierreAnualPK[ codigoPeriodo=" + codigoPeriodo + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
