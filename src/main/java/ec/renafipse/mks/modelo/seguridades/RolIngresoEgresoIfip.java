/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.modelo.cajas.IngresoEgreso;
import ec.renafipse.mks.modelo.cajas.IngresoEgresoMoneda;
import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "MKS_SEGURIDADES.ROL_INGRESO_EGRESO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolIngresoEgresoIfip.findAll", query = "SELECT r FROM RolIngresoEgresoIfip r"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByCodigoIngresoEgreso", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.rolIngresoEgresoIfipPK.codigoIngresoEgreso = :codigoIngresoEgreso"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByCodigoMoneda", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.rolIngresoEgresoIfipPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByCodigoRol", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.rolIngresoEgresoIfipPK.codigoRol = :codigoRol"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByCodigoIfip", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.rolIngresoEgresoIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByIngresadoPor", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.ingresadoPor = :ingresadoPor"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByFechaIngreso", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByEliminado", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "RolIngresoEgresoIfip.findByIngresoEgresoCaja", query = "SELECT n FROM RolIngresoEgresoIfip r JOIN r.ingresoEgresoMoneda i JOIN i.ingresoEgreso n WHERE r.rolIngresoEgresoIfipPK.codigoMoneda = :codigoMoneda AND r.rolIngresoEgresoIfipPK.codigoRol = :codigoRol AND r.rolIngresoEgresoIfipPK.codigoIfip = :codigoIfip AND r.eliminado = :eliminado AND i.mostrar = :mostrar AND i.eliminado = :eliminado AND n.esIngreso = :esIngreso AND n.eliminado = :eliminado"),
    //existentes
    @NamedQuery(name = "RolIngresoEgresoIfip.findByIngresoEgresoPermisos", query = "SELECT i FROM RolIngresoEgresoIfip r JOIN r.ingresoEgresoMoneda i JOIN i.ingresoEgreso n WHERE r.rolIngresoEgresoIfipPK.codigoMoneda = :codigoMoneda AND r.rolIngresoEgresoIfipPK.codigoRol = :codigoRol AND r.rolIngresoEgresoIfipPK.codigoIfip = :codigoIfip AND n.esIngreso = :esIngreso AND i.mostrar = :mostrar"),
    @NamedQuery(name = "RolIngresoEgresoIfip.findByRolIngresoEgresoIfip", query = "SELECT r FROM RolIngresoEgresoIfip r WHERE r.rolIngresoEgresoIfipPK.codigoMoneda = :codigoMoneda AND r.rolIngresoEgresoIfipPK.codigoRol = :codigoRol AND r.rolIngresoEgresoIfipPK.codigoIfip = :codigoIfip AND r.rolIngresoEgresoIfipPK.codigoIngresoEgreso = :codigoIngresoEgreso")
})
public class RolIngresoEgresoIfip implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByIngresoEgresoCaja = "RolIngresoEgresoIfip.findByIngresoEgresoCaja";
    public static final String findByIngresoEgresoPermisos = "RolIngresoEgresoIfip.findByIngresoEgresoPermisos";
    public static final String findByRolIngresoEgresoIfip = "RolIngresoEgresoIfip.findByRolIngresoEgresoIfip";
    @EmbeddedId
    protected RolIngresoEgresoIfipPK rolIngresoEgresoIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INGRESADO_POR")
    private long ingresadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_ROL", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;
  
    @JoinColumns({
        @JoinColumn(name = "CODIGO_INGRESO_EGRESO", referencedColumnName = "CODIGO_INGRESO_EGRESO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private IngresoEgresoMoneda ingresoEgresoMoneda;
    public RolIngresoEgresoIfip() {
    }

    public RolIngresoEgresoIfip(RolIngresoEgresoIfipPK rolIngresoEgresoIfipPK) {
        this.rolIngresoEgresoIfipPK = rolIngresoEgresoIfipPK;
    }

    public RolIngresoEgresoIfip(RolIngresoEgresoIfipPK rolIngresoEgresoIfipPK, long ingresadoPor, Date fechaIngreso, char eliminado) {
        this.rolIngresoEgresoIfipPK = rolIngresoEgresoIfipPK;
        this.ingresadoPor = ingresadoPor;
        this.fechaIngreso = fechaIngreso;
        this.eliminado = eliminado;
    }

    public RolIngresoEgresoIfip(long codigoIngresoEgreso, long codigoMoneda, String codigoRol, long codigoIfip) {
        this.rolIngresoEgresoIfipPK = new RolIngresoEgresoIfipPK(codigoIngresoEgreso, codigoMoneda, codigoRol, codigoIfip);
    }

    public RolIngresoEgresoIfipPK getRolIngresoEgresoIfipPK() {
        return rolIngresoEgresoIfipPK;
    }

    public void setRolIngresoEgresoIfipPK(RolIngresoEgresoIfipPK rolIngresoEgresoIfipPK) {
        this.rolIngresoEgresoIfipPK = rolIngresoEgresoIfipPK;
    }

    public long getIngresadoPor() {
        return ingresadoPor;
    }

    public void setIngresadoPor(long ingresadoPor) {
        this.ingresadoPor = ingresadoPor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolIngresoEgresoIfipPK != null ? rolIngresoEgresoIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolIngresoEgresoIfip)) {
            return false;
        }
        RolIngresoEgresoIfip other = (RolIngresoEgresoIfip) object;
        if ((this.rolIngresoEgresoIfipPK == null && other.rolIngresoEgresoIfipPK != null) || (this.rolIngresoEgresoIfipPK != null && !this.rolIngresoEgresoIfipPK.equals(other.rolIngresoEgresoIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfip[ rolIngresoEgresoIfipPK=" + rolIngresoEgresoIfipPK + " ]";
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

   

    /**
     * @return the ingresoEgresoMoneda
     */
    public IngresoEgresoMoneda getIngresoEgresoMoneda() {
        return ingresoEgresoMoneda;
    }

    /**
     * @param ingresoEgresoMoneda the ingresoEgresoMoneda to set
     */
    public void setIngresoEgresoMoneda(IngresoEgresoMoneda ingresoEgresoMoneda) {
        this.ingresoEgresoMoneda = ingresoEgresoMoneda;
    }

}
