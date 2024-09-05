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
import javax.validation.constraints.Size;

/**
 *
 * @author Viajes2
 */
@Embeddable
public class RolConceptoTransaccionIfipPK implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO_ROL")
    private String codigoRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public RolConceptoTransaccionIfipPK() {
    }

    public RolConceptoTransaccionIfipPK(long codigoTipoProducto, long codigoMoneda, long codigoConcepto, String codigoRol, long codigoIfip) {
        this.codigoTipoProducto = codigoTipoProducto;
        this.codigoMoneda = codigoMoneda;
        this.codigoConcepto = codigoConcepto;
        this.codigoRol = codigoRol;
        this.codigoIfip = codigoIfip;
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

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
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
        hash += (int) codigoTipoProducto;
        hash += (int) codigoMoneda;
        hash += (int) codigoConcepto;
        hash += (codigoRol != null ? codigoRol.hashCode() : 0);
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolConceptoTransaccionIfipPK)) {
            return false;
        }
        RolConceptoTransaccionIfipPK other = (RolConceptoTransaccionIfipPK) object;
        if (this.codigoTipoProducto != other.codigoTipoProducto) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoConcepto != other.codigoConcepto) {
            return false;
        }
        if ((this.codigoRol == null && other.codigoRol != null) || (this.codigoRol != null && !this.codigoRol.equals(other.codigoRol))) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfipPK[ codigoTipoProducto=" + codigoTipoProducto + ", codigoMoneda=" + codigoMoneda + ", codigoConcepto=" + codigoConcepto + ", codigoRol=" + codigoRol + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
