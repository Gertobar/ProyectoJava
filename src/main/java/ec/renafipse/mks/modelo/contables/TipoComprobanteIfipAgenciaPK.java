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
 * @author vastudillo
 */
@Embeddable
public class TipoComprobanteIfipAgenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_COMPROBANTE")
    private long codigoTipoComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CODIGO_PERIODO")
    private String codigoPeriodo;

    public TipoComprobanteIfipAgenciaPK() {
    }

    public TipoComprobanteIfipAgenciaPK(long codigoTipoComprobante, long codigoIfip, String codigoPeriodo) {
        this.codigoTipoComprobante = codigoTipoComprobante;
        this.codigoIfip = codigoIfip;
        this.codigoPeriodo = codigoPeriodo;
    }

    public long getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(long codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoTipoComprobante;
        hash += (int) codigoIfip;
        hash += (codigoPeriodo != null ? codigoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComprobanteIfipAgenciaPK)) {
            return false;
        }
        TipoComprobanteIfipAgenciaPK other = (TipoComprobanteIfipAgenciaPK) object;
        if (this.codigoTipoComprobante != other.codigoTipoComprobante) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if ((this.codigoPeriodo == null && other.codigoPeriodo != null) || (this.codigoPeriodo != null && !this.codigoPeriodo.equals(other.codigoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TipoComprobanteIfipAgenciaPK[ codigoTipoComprobante=" + codigoTipoComprobante + ", codigoIfip=" + codigoIfip + ", codigoPeriodo=" + codigoPeriodo + " ]";
    }
    
}
