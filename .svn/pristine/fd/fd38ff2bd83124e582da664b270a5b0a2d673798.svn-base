/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.OPCION_OPERACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionOperacion.findAll", query = "SELECT o FROM OpcionOperacion o"),
    @NamedQuery(name = "OpcionOperacion.findByCodigo", query = "SELECT o FROM OpcionOperacion o WHERE o.codigo = :codigo"),
    @NamedQuery(name = "OpcionOperacion.findByNombre", query = "SELECT o FROM OpcionOperacion o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "OpcionOperacion.findByEliminado", query = "SELECT o FROM OpcionOperacion o WHERE o.eliminado = :eliminado"),
    //Personalizado
    @NamedQuery(name = "OpcionOperacion.findByOpcionOperacionTipoOperacionMenu", query = "SELECT o FROM OpcionOperacion o WHERE o.codigoMenu = :codigoMenu  AND o.codigoTipoOperacion = :codigoTipoOperacion"),
    @NamedQuery(name = "OpcionOperacion.findByOpcionOperacionExistentesRol", query = "SELECT o FROM OpcionOperacion o JOIN o.rolOpcionOperacionCollection r WHERE o.codigoMenu.codigo = :codigoMenu AND r.rolOpcionOperacionPK.codigoRol = :codigoRol AND r.rolOpcionOperacionPK.codigoIfip = :codigoIfip AND r.eliminado = :eliminado AND o.eliminado = :eliminado"),
    @NamedQuery(name = "OpcionOperacion.findByOpcionOperacionEliminado", query = "SELECT o FROM OpcionOperacion o WHERE o.codigoMenu.codigo = :codigoMenu AND o.eliminado = :eliminado"),
    @NamedQuery(name = "OpcionOperacion.findByOpcionOperaExistentesRolPorDef", query = "SELECT o FROM OpcionOperacion o JOIN o.rolOpcionOpePorDefCollection rd WHERE o.codigoMenu.codigo = :codigoMenu AND rd.rolOpcionOpePorDefPK.codigoRol = :codigoRol AND rd.eliminado = :eliminado AND o.eliminado = :eliminado")
})
public class OpcionOperacion implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByOpcionOperacionTipoOperacionMenu = "OpcionOperacion.findByOpcionOperacionTipoOperacionMenu";
    public static final String findByOpcionOperacionExistentesRol = "OpcionOperacion.findByOpcionOperacionExistentesRol";
    public static final String findByOpcionOperacionEliminado = "OpcionOperacion.findByOpcionOperacionEliminado";
    public static final String findByOpcionOperaExistentesRolPorDef = "OpcionOperacion.findByOpcionOperaExistentesRolPorDef";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_OPCION_OPERACION")
    @SequenceGenerator(name = "GSQ_OPCION_OPERACION", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_OPCION_OPERACION")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_OPERACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoOperacion codigoTipoOperacion;
    @JoinColumn(name = "CODIGO_MENU", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Menu codigoMenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionOperacion", fetch = FetchType.LAZY)
    private Collection<RolOpcionOpePorDef> rolOpcionOpePorDefCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionOperacion", fetch = FetchType.LAZY)
    private Collection<RolOpcionOperacion> rolOpcionOperacionCollection;

    public OpcionOperacion() {
    }

    public OpcionOperacion(Long codigo) {
        this.codigo = codigo;
    }

    public OpcionOperacion(Long codigo, String nombre, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoOperacion getCodigoTipoOperacion() {
        return codigoTipoOperacion;
    }

    public void setCodigoTipoOperacion(TipoOperacion codigoTipoOperacion) {
        this.codigoTipoOperacion = codigoTipoOperacion;
    }

    public Menu getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(Menu codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    @XmlTransient
    public Collection<RolOpcionOpePorDef> getRolOpcionOpePorDefCollection() {
        return rolOpcionOpePorDefCollection;
    }

    public void setRolOpcionOpePorDefCollection(Collection<RolOpcionOpePorDef> rolOpcionOpePorDefCollection) {
        this.rolOpcionOpePorDefCollection = rolOpcionOpePorDefCollection;
    }

    @XmlTransient
    public Collection<RolOpcionOperacion> getRolOpcionOperacionCollection() {
        return rolOpcionOperacionCollection;
    }

    public void setRolOpcionOperacionCollection(Collection<RolOpcionOperacion> rolOpcionOperacionCollection) {
        this.rolOpcionOperacionCollection = rolOpcionOperacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionOperacion)) {
            return false;
        }
        OpcionOperacion other = (OpcionOperacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.OpcionOperacion[ codigo=" + codigo + " ]";
    }

}
