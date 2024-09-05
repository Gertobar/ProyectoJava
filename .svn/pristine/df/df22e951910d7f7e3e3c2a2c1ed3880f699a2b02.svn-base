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
public class TalonarioDocumentoRetencionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private long codigo;

    public TalonarioDocumentoRetencionPK() {
    }

    public TalonarioDocumentoRetencionPK(long codigoIfip, long codigo) {
        this.codigoIfip = codigoIfip;
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoIfip;
        hash += (int) codigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioDocumentoRetencionPK)) {
            return false;
        }
        TalonarioDocumentoRetencionPK other = (TalonarioDocumentoRetencionPK) object;
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetencionPK[ codigoIfip=" + codigoIfip + ", codigo=" + codigo + " ]";
    }
    
}
