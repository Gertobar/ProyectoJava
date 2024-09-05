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
@Table(name = "MKS_SEGURIDADES.ROL_OPCION_OPERACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolOpcionOperacion.findAll", query = "SELECT r FROM RolOpcionOperacion r"),
    @NamedQuery(name = "RolOpcionOperacion.findByCodigoOperacion", query = "SELECT r FROM RolOpcionOperacion r WHERE r.rolOpcionOperacionPK.codigoOperacion = :codigoOperacion"),
    @NamedQuery(name = "RolOpcionOperacion.findByCodigoIfip", query = "SELECT r FROM RolOpcionOperacion r WHERE r.rolOpcionOperacionPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RolOpcionOperacion.findByCodigoRol", query = "SELECT r FROM RolOpcionOperacion r WHERE r.rolOpcionOperacionPK.codigoRol = :codigoRol"),
    @NamedQuery(name = "RolOpcionOperacion.findByEliminado", query = "SELECT r FROM RolOpcionOperacion r WHERE r.eliminado = :eliminado"),
    // PERSONALIZADOS
    // Obtiene los permisos que tiene una opcion del menu
    @NamedQuery(name = "RolOpcionOperacion.findByOpcionOperacion", query = "SELECT p FROM RolOpcionOperacion r JOIN r.opcionOperacion p WHERE r.rolOpcionOperacionPK.codigoRol = :codigoRol AND r.rolOpcionOperacionPK.codigoIfip = :codigoIfip AND r.eliminado = :eliminado AND p.codigoMenu.codigo = :codigoMenu AND p.eliminado = :eliminado"),
    @NamedQuery(name = "RolOpcionOperacion.findByOpcionOperacionRolMenu", query = "SELECT r FROM RolOpcionOperacion r JOIN r.opcionOperacion o WHERE o.codigo = :codigoOpcionOperacion AND r.rolOpcionOperacionPK.codigoRol = :codigoRol AND r.rolOpcionOperacionPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RolOpcionOperacion.findByOpcionOperacionPermisosMenu", query = "SELECT r.opcionOperacion FROM RolOpcionOperacion r JOIN r.opcionOperacion o WHERE r.rolOpcionOperacionPK.codigoIfip = :codigoIfip  AND r.rolOpcionOperacionPK.codigoRol = :codigoRol AND o.codigoMenu.codigo = :codigoMenu AND r.eliminado = :eliminado AND o.eliminado = :eliminado AND o.codigoMenu.eliminado = :eliminado")

})
public class RolOpcionOperacion implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByOpcionOperacion = "RolOpcionOperacion.findByOpcionOperacion";
    public static final String findByEliminado = "RolOpcionOperacion.findByEliminado";
    public static final String findByOpcionOperacionRolMenu = "RolOpcionOperacion.findByOpcionOperacionRolMenu";
    public static final String findByOpcionOperacionPermisosMenu = "RolOpcionOperacion.findByOpcionOperacionPermisosMenu";
    @EmbeddedId
    protected RolOpcionOperacionPK rolOpcionOperacionPK;
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

    public RolOpcionOperacion() {
    }

    public RolOpcionOperacion(RolOpcionOperacionPK rolOpcionOperacionPK) {
        this.rolOpcionOperacionPK = rolOpcionOperacionPK;
    }

    public RolOpcionOperacion(RolOpcionOperacionPK rolOpcionOperacionPK, char eliminado) {
        this.rolOpcionOperacionPK = rolOpcionOperacionPK;
        this.eliminado = eliminado;
    }

    public RolOpcionOperacion(long codigoOperacion, long codigoIfip, String codigoRol) {
        this.rolOpcionOperacionPK = new RolOpcionOperacionPK(codigoOperacion, codigoIfip, codigoRol);
    }

    public RolOpcionOperacionPK getRolOpcionOperacionPK() {
        return rolOpcionOperacionPK;
    }

    public void setRolOpcionOperacionPK(RolOpcionOperacionPK rolOpcionOperacionPK) {
        this.rolOpcionOperacionPK = rolOpcionOperacionPK;
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
        hash += (rolOpcionOperacionPK != null ? rolOpcionOperacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolOpcionOperacion)) {
            return false;
        }
        RolOpcionOperacion other = (RolOpcionOperacion) object;
        if ((this.rolOpcionOperacionPK == null && other.rolOpcionOperacionPK != null) || (this.rolOpcionOperacionPK != null && !this.rolOpcionOperacionPK.equals(other.rolOpcionOperacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolOpcionOperacion[ rolOpcionOperacionPK=" + rolOpcionOperacionPK + " ]";
    }

}
