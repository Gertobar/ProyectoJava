/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.adquisiciones.ItemVenta;
import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.CUENTA_CONTABLE_ITEM_VENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaContableItemVenta.findAll", query = "SELECT c FROM CuentaContableItemVenta c"),    
    @NamedQuery(name = "CuentaContableItemVenta.findByRegistradoPor", query = "SELECT c FROM CuentaContableItemVenta c WHERE c.registradoPor = :registradoPor"),
    @NamedQuery(name = "CuentaContableItemVenta.findByFechaRegistro", query = "SELECT c FROM CuentaContableItemVenta c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "CuentaContableItemVenta.findByEliminado", query = "SELECT c FROM CuentaContableItemVenta c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "CuentaContableItemVenta.findByIfip", query = "SELECT c FROM CuentaContableItemVenta c WHERE c.planDeCuentaIfip.planDeCuentaIfipPK.codigoIfip = :codigoIfip ORDER BY c.itemVenta.nombre"),
    @NamedQuery(name = "CuentaContableItemVenta.findByItemVentaIfip", query = "SELECT i FROM CuentaContableItemVenta c JOIN c.itemVenta i WHERE c.planDeCuentaIfip.planDeCuentaIfipPK.codigoIfip = :codigoIfip AND i.eliminado = :eliminado  ORDER BY i.nombre")
})
public class CuentaContableItemVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String  findByIfip = "CuentaContableItemVenta.findByIfip";
    public static final String  findByItemVentaIfip = "CuentaContableItemVenta.findByItemVentaIfip";
    @EmbeddedId
    protected CuentaContableItemVentaPK cuentaContableItemVentaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PLAN")
    private long codigoTipoPlan;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
     @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
     
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuentaIfip planDeCuentaIfip;
    
    
    @JoinColumn(name = "CODIGO_ITEM_VENTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ItemVenta itemVenta; 

    public CuentaContableItemVenta() {
    }

    public CuentaContableItemVenta(CuentaContableItemVentaPK cuentaContableItemVentaPK) {
        this.cuentaContableItemVentaPK = cuentaContableItemVentaPK;
    }

    public CuentaContableItemVenta(CuentaContableItemVentaPK cuentaContableItemVentaPK, long registradoPor, Date fechaRegistro, char eliminado) {
        this.cuentaContableItemVentaPK = cuentaContableItemVentaPK;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public CuentaContableItemVenta(long codigoIfip, long codigoItemVenta) {
        this.cuentaContableItemVentaPK = new CuentaContableItemVentaPK(codigoIfip, codigoItemVenta);
    }

    public CuentaContableItemVentaPK getCuentaContableItemVentaPK() {
        return cuentaContableItemVentaPK;
    }

    public void setCuentaContableItemVentaPK(CuentaContableItemVentaPK cuentaContableItemVentaPK) {
        this.cuentaContableItemVentaPK = cuentaContableItemVentaPK;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public PlanDeCuentaIfip getPlanDeCuentaIfip() {
        return planDeCuentaIfip;
    }

    public void setPlanDeCuentaIfip(PlanDeCuentaIfip planDeCuentaIfip) {
        this.planDeCuentaIfip = planDeCuentaIfip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaContableItemVentaPK != null ? cuentaContableItemVentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContableItemVenta)) {
            return false;
        }
        CuentaContableItemVenta other = (CuentaContableItemVenta) object;
        if ((this.cuentaContableItemVentaPK == null && other.cuentaContableItemVentaPK != null) || (this.cuentaContableItemVentaPK != null && !this.cuentaContableItemVentaPK.equals(other.cuentaContableItemVentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CuentaContableItemVenta[ cuentaContableItemVentaPK=" + cuentaContableItemVentaPK + " ]";
    }

    /**
     * @return the itemVenta
     */
    public ItemVenta getItemVenta() {
        return itemVenta;
    }

    /**
     * @param itemVenta the itemVenta to set
     */
    public void setItemVenta(ItemVenta itemVenta) {
        this.itemVenta = itemVenta;
    }

    /**
     * @return the codigoTipoPlan
     */
    public long getCodigoTipoPlan() {
        return codigoTipoPlan;
    }

    /**
     * @param codigoTipoPlan the codigoTipoPlan to set
     */
    public void setCodigoTipoPlan(long codigoTipoPlan) {
        this.codigoTipoPlan = codigoTipoPlan;
    }

    /**
     * @return the cuentaContable
     */
    public String getCuentaContable() {
        return cuentaContable;
    }

    /**
     * @param cuentaContable the cuentaContable to set
     */
    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }
    
}
