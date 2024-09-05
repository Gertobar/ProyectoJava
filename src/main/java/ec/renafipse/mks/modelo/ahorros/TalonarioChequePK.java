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
 * @author v.astudillo
 */
@Embeddable
public class TalonarioChequePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ENT_FIN")
    private long codigoCuentaEntFin;

    public TalonarioChequePK() {
    }

    public TalonarioChequePK(long codigo, long codigoCuentaEntFin) {
        this.codigo = codigo;
        this.codigoCuentaEntFin = codigoCuentaEntFin;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoCuentaEntFin() {
        return codigoCuentaEntFin;
    }

    public void setCodigoCuentaEntFin(long codigoCuentaEntFin) {
        this.codigoCuentaEntFin = codigoCuentaEntFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigo;
        hash += (int) codigoCuentaEntFin;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioChequePK)) {
            return false;
        }
        TalonarioChequePK other = (TalonarioChequePK) object;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.codigoCuentaEntFin != other.codigoCuentaEntFin) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ahorros.TalonarioChequePK[ codigo=" + codigo + ", codigoCuentaEntFin=" + codigoCuentaEntFin + " ]";
    }
    
}
