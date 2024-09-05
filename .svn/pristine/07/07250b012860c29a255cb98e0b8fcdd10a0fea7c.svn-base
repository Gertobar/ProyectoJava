/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
public class ConceptoTransaccionProPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PRODUCTO")
    private long codigoTipoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONCEPTO")
    private long codigoConcepto;

    public ConceptoTransaccionProPK() {
    }

    public ConceptoTransaccionProPK(long codigoTipoProducto, long codigoMoneda, long codigoConcepto) {
        this.codigoTipoProducto = codigoTipoProducto;
        this.codigoMoneda = codigoMoneda;
        this.codigoConcepto = codigoConcepto;
    }

    public long getCodigoTipoProducto() {
        return codigoTipoProducto;
    }

    public void setCodigoTipoProducto(long codigoTipoProducto) {
        this.codigoTipoProducto = codigoTipoProducto;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoConcepto() {
        return codigoConcepto;
    }

    public void setCodigoConcepto(long codigoConcepto) {
        this.codigoConcepto = codigoConcepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoTipoProducto;
        hash += (int) codigoMoneda;
        hash += (int) codigoConcepto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptoTransaccionProPK)) {
            return false;
        }
        ConceptoTransaccionProPK other = (ConceptoTransaccionProPK) object;
        if (this.codigoTipoProducto != other.codigoTipoProducto) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoConcepto != other.codigoConcepto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionProPK[ codigoTipoProducto=" + codigoTipoProducto + ", codigoMoneda=" + codigoMoneda + ", codigoConcepto=" + codigoConcepto + " ]";
    }
    
}
