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

/**
 *
 * @author vicastoc
 */
@Embeddable
public class ParametroGeneralSegIfiPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PARAMETRO")
    private long codigoParametro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public ParametroGeneralSegIfiPK() {
    }

    public ParametroGeneralSegIfiPK(long codigoParametro, long codigoIfip) {
        this.codigoParametro = codigoParametro;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoParametro() {
        return codigoParametro;
    }

    public void setCodigoParametro(long codigoParametro) {
        this.codigoParametro = codigoParametro;
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
        hash += (int) codigoParametro;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroGeneralSegIfiPK)) {
            return false;
        }
        ParametroGeneralSegIfiPK other = (ParametroGeneralSegIfiPK) object;
        if (this.codigoParametro != other.codigoParametro) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfiPK[ codigoParametro=" + codigoParametro + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
