/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.adquisiciones;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PROVEEDOR_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorIfip.findAll", query = "SELECT p FROM ProveedorIfip p"),
    @NamedQuery(name = "ProveedorIfip.findByCodigoProveedor", query = "SELECT p FROM ProveedorIfip p WHERE p.proveedorIfipPK.codigoProveedor = :codigoProveedor"),
    @NamedQuery(name = "ProveedorIfip.findByCodigoIfip", query = "SELECT p FROM ProveedorIfip p WHERE p.proveedorIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ProveedorIfip.findByFechaRegistro", query = "SELECT p FROM ProveedorIfip p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ProveedorIfip.findByRegistradoPor", query = "SELECT p FROM ProveedorIfip p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "ProveedorIfip.findByEliminado", query = "SELECT p FROM ProveedorIfip p WHERE p.eliminado = :eliminado"),
    @NamedQuery(name = "ProveedorIfip.findByCodigoIfipNombreProveedor", query = "SELECT p FROM ProveedorIfip p  JOIN p.proveedor r JOIN r.persona n WHERE p.proveedorIfipPK = :codigoIfip AND n.nombreCompleto LIKE :nombreCompleto"),
//Personalizada 
    @NamedQuery(name = "ProveedorIfip.findByIdentificacion", query = "SELECT p FROM ProveedorIfip p  JOIN p.proveedor r JOIN r.persona pe WHERE pe.identificacion = :identificacion AND p.proveedorIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ProveedorIfip.findByNombreProveedor", query = "SELECT p FROM ProveedorIfip p  JOIN p.proveedor r JOIN r.persona pe WHERE pe.nombreCompleto LIKE :nombreCompleto AND p.proveedorIfipPK.codigoIfip = :codigoIfip AND p.eliminado = :eliminado ORDER BY pe.nombreCompleto "),
    @NamedQuery(name = "ProveedorIfip.findByIDProveedorIfipEliminado", query = "SELECT p FROM ProveedorIfip p  JOIN p.proveedor r JOIN r.persona pe WHERE pe.identificacion LIKE :identificacion AND p.proveedorIfipPK.codigoIfip = :codigoIfip AND p.eliminado = :eliminado ORDER BY pe.nombreCompleto "),
    @NamedQuery(name = "ProveedorIfip.findByCodigoProveedorIfipEliminado", query = "SELECT p FROM ProveedorIfip p  JOIN p.proveedor r JOIN r.persona pe WHERE p.proveedorIfipPK.codigoProveedor = :codigoProveedor and p.proveedorIfipPK.codigoIfip = :codigoIfip AND p.eliminado = :eliminado")
})
public class ProveedorIfip implements Serializable {

    public static final String findByCodigoProveedor = "ProveedorIfip.findByCodigoProveedor";
    public static final String findByCodigoIfipNombreProveedor = "ProveedorIfip.findByCodigoIfipNombreProveedor";
    public static final String findByIdentificacion = "ProveedorIfip.findByIdentificacion";
    public static final String findByIDProveedorIfipEliminado = "ProveedorIfip.findByIDProveedorIfipEliminado";
    public static final String findByCodigoProveedorIfipEliminado = "ProveedorIfip.findByCodigoProveedorIfipEliminado";
    public static final String findByNombreProveedorIfipEliminado = "ProveedorIfip.findByNombreProveedor";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProveedorIfipPK proveedorIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PROVEEDOR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;

    public ProveedorIfip() {
    }

    public ProveedorIfip(ProveedorIfipPK proveedorIfipPK) {
        this.proveedorIfipPK = proveedorIfipPK;
    }

    public ProveedorIfip(ProveedorIfipPK proveedorIfipPK, Date fechaRegistro, long registradoPor, char eliminado) {
        this.proveedorIfipPK = proveedorIfipPK;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.eliminado = eliminado;
    }

    public ProveedorIfip(long codigoProveedor, long codigoIfip) {
        this.proveedorIfipPK = new ProveedorIfipPK(codigoProveedor, codigoIfip);
    }

    public ProveedorIfipPK getProveedorIfipPK() {
        return proveedorIfipPK;
    }

    public void setProveedorIfipPK(ProveedorIfipPK proveedorIfipPK) {
        this.proveedorIfipPK = proveedorIfipPK;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorIfipPK != null ? proveedorIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorIfip)) {
            return false;
        }
        ProveedorIfip other = (ProveedorIfip) object;
        if ((this.proveedorIfipPK == null && other.proveedorIfipPK != null) || (this.proveedorIfipPK != null && !this.proveedorIfipPK.equals(other.proveedorIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.ProveedorIfip[ proveedorIfipPK=" + proveedorIfipPK + " ]";
    }

    /**
     * @return the usuarioRegistradoPor
     */
    public Usuario getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Usuario usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }

}
