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
 * @author Santiago
 */
@Embeddable
public class ProductoCreditoTipoPersonaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRODUCTO")
    private long codigoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PERSONA")
    private long codigoTipoPersona;

    public ProductoCreditoTipoPersonaPK() {
    }

    public ProductoCreditoTipoPersonaPK(long codigoProducto, long codigoMoneda, long codigoIfip, long codigoTipoPersona) {
        this.codigoProducto = codigoProducto;
        this.codigoMoneda = codigoMoneda;
        this.codigoIfip = codigoIfip;
        this.codigoTipoPersona = codigoTipoPersona;
    }

    public long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoTipoPersona() {
        return codigoTipoPersona;
    }

    public void setCodigoTipoPersona(long codigoTipoPersona) {
        this.codigoTipoPersona = codigoTipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoProducto;
        hash += (int) codigoMoneda;
        hash += (int) codigoIfip;
        hash += (int) codigoTipoPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoCreditoTipoPersonaPK)) {
            return false;
        }
        ProductoCreditoTipoPersonaPK other = (ProductoCreditoTipoPersonaPK) object;
        if (this.codigoProducto != other.codigoProducto) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.codigoTipoPersona != other.codigoTipoPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ProductoCreditoTipoPersonaPK[ codigoProducto=" + codigoProducto + ", codigoMoneda=" + codigoMoneda + ", codigoIfip=" + codigoIfip + ", codigoTipoPersona=" + codigoTipoPersona + " ]";
    }
    
}
