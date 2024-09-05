/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.IFIP_MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfipMenu.findAll", query = "SELECT i FROM IfipMenu i"),
    @NamedQuery(name = "IfipMenu.findByCodigoMenu", query = "SELECT i FROM IfipMenu i WHERE i.ifipMenuPK.codigoMenu = :codigoMenu"),
    @NamedQuery(name = "IfipMenu.findByCodigoIfip", query = "SELECT i FROM IfipMenu i WHERE i.ifipMenuPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "IfipMenu.findByEliminado", query = "SELECT i FROM IfipMenu i WHERE i.eliminado = :eliminado"), // Personalizados
})
public class IfipMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IfipMenuPK ifipMenuPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_MENU", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Menu menu;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;

    public IfipMenu() {
    }

    public IfipMenu(IfipMenuPK ifipMenuPK) {
        this.ifipMenuPK = ifipMenuPK;
    }

    public IfipMenu(IfipMenuPK ifipMenuPK, char eliminado) {
        this.ifipMenuPK = ifipMenuPK;
        this.eliminado = eliminado;
    }

    public IfipMenu(long codigoMenu, long codigoIfip) {
        this.ifipMenuPK = new IfipMenuPK(codigoMenu, codigoIfip);
    }

    public IfipMenuPK getIfipMenuPK() {
        return ifipMenuPK;
    }

    public void setIfipMenuPK(IfipMenuPK ifipMenuPK) {
        this.ifipMenuPK = ifipMenuPK;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ifipMenuPK != null ? ifipMenuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipMenu)) {
            return false;
        }
        IfipMenu other = (IfipMenu) object;
        if ((this.ifipMenuPK == null && other.ifipMenuPK != null) || (this.ifipMenuPK != null && !this.ifipMenuPK.equals(other.ifipMenuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.IfipMenu[ ifipMenuPK=" + ifipMenuPK + " ]";
    }

    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

}
