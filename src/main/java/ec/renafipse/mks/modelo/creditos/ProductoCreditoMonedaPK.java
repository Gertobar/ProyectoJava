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
 * @author vastudillo
 */
@Embeddable
public class ProductoCreditoMonedaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRODUCTO")
    private long codigoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;

    public ProductoCreditoMonedaPK() {
    }

    public ProductoCreditoMonedaPK(long codigoProducto, long codigoMoneda) {
        this.codigoProducto = codigoProducto;
        this.codigoMoneda = codigoMoneda;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoProducto;
        hash += (int) codigoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoCreditoMonedaPK)) {
            return false;
        }
        ProductoCreditoMonedaPK other = (ProductoCreditoMonedaPK) object;
        if (this.codigoProducto != other.codigoProducto) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaPK[ codigoProducto=" + codigoProducto + ", codigoMoneda=" + codigoMoneda + " ]";
    }
    
}
