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
public class IfipMenuPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MENU")
    private long codigoMenu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public IfipMenuPK() {
    }

    public IfipMenuPK(long codigoMenu, long codigoIfip) {
        this.codigoMenu = codigoMenu;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(long codigoMenu) {
        this.codigoMenu = codigoMenu;
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
        hash += (int) codigoMenu;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipMenuPK)) {
            return false;
        }
        IfipMenuPK other = (IfipMenuPK) object;
        if (this.codigoMenu != other.codigoMenu) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.IfipMenuPK[ codigoMenu=" + codigoMenu + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
