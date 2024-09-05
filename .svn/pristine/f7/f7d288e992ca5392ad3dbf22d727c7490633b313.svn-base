/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

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
@Table(name = "MKS_SEGURIDADES.ROL_OPCION_OPE_POR_DEF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolOpcionOpePorDef.findAll", query = "SELECT r FROM RolOpcionOpePorDef r"),
    @NamedQuery(name = "RolOpcionOpePorDef.findByCodigoRol", query = "SELECT r FROM RolOpcionOpePorDef r WHERE r.rolOpcionOpePorDefPK.codigoRol = :codigoRol"),
    @NamedQuery(name = "RolOpcionOpePorDef.findByCodigoOperacion", query = "SELECT r FROM RolOpcionOpePorDef r WHERE r.rolOpcionOpePorDefPK.codigoOperacion = :codigoOperacion"),
    @NamedQuery(name = "RolOpcionOpePorDef.findByEliminado", query = "SELECT r FROM RolOpcionOpePorDef r WHERE r.eliminado = :eliminado"),
//Personalizadas
    @NamedQuery(name = "RolOpcionOpePorDef.findByOpcionOpeRolPorDefMenu", query = "SELECT r FROM RolOpcionOpePorDef r WHERE r.rolOpcionOpePorDefPK.codigoOperacion = :codigoOpcionOperacion AND r.rolOpcionOpePorDefPK.codigoRol = :codigoRol")
})
public class RolOpcionOpePorDef implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByOpcionOpeRolPorDefMenu = "RolOpcionOpePorDef.findByOpcionOpeRolPorDefMenu";

    @EmbeddedId
    protected RolOpcionOpePorDefPK rolOpcionOpePorDefPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_ROL", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "CODIGO_OPERACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OpcionOperacion opcionOperacion;

    public RolOpcionOpePorDef() {
    }

    public RolOpcionOpePorDef(RolOpcionOpePorDefPK rolOpcionOpePorDefPK) {
        this.rolOpcionOpePorDefPK = rolOpcionOpePorDefPK;
    }

    public RolOpcionOpePorDef(RolOpcionOpePorDefPK rolOpcionOpePorDefPK, char eliminado) {
        this.rolOpcionOpePorDefPK = rolOpcionOpePorDefPK;
        this.eliminado = eliminado;
    }

    public RolOpcionOpePorDef(String codigoRol, long codigoOperacion) {
        this.rolOpcionOpePorDefPK = new RolOpcionOpePorDefPK(codigoRol, codigoOperacion);
    }

    public RolOpcionOpePorDefPK getRolOpcionOpePorDefPK() {
        return rolOpcionOpePorDefPK;
    }

    public void setRolOpcionOpePorDefPK(RolOpcionOpePorDefPK rolOpcionOpePorDefPK) {
        this.rolOpcionOpePorDefPK = rolOpcionOpePorDefPK;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public OpcionOperacion getOpcionOperacion() {
        return opcionOperacion;
    }

    public void setOpcionOperacion(OpcionOperacion opcionOperacion) {
        this.opcionOperacion = opcionOperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolOpcionOpePorDefPK != null ? rolOpcionOpePorDefPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolOpcionOpePorDef)) {
            return false;
        }
        RolOpcionOpePorDef other = (RolOpcionOpePorDef) object;
        if ((this.rolOpcionOpePorDefPK == null && other.rolOpcionOpePorDefPK != null) || (this.rolOpcionOpePorDefPK != null && !this.rolOpcionOpePorDefPK.equals(other.rolOpcionOpePorDefPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolOpcionOpePorDef[ rolOpcionOpePorDefPK=" + rolOpcionOpePorDefPK + " ]";
    }

}
