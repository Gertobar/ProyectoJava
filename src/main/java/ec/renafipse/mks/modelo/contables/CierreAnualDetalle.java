/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.CIERRE_ANUAL_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CierreAnualDetalle.findAll", query = "SELECT c FROM CierreAnualDetalle c"),
    @NamedQuery(name = "CierreAnualDetalle.findByCodigoPeriodo", query = "SELECT c FROM CierreAnualDetalle c WHERE c.cierreAnualDetallePK.codigoPeriodo = :codigoPeriodo"),
    @NamedQuery(name = "CierreAnualDetalle.findByCuentaContable", query = "SELECT c FROM CierreAnualDetalle c WHERE c.cierreAnualDetallePK.cuentaContable = :cuentaContable"),
    @NamedQuery(name = "CierreAnualDetalle.findByCodigoTipoPlan", query = "SELECT c FROM CierreAnualDetalle c WHERE c.cierreAnualDetallePK.codigoTipoPlan = :codigoTipoPlan"),
    @NamedQuery(name = "CierreAnualDetalle.findByTotal", query = "SELECT c FROM CierreAnualDetalle c WHERE c.total = :total"),
    @NamedQuery(name = "CierreAnualDetalle.findByCodigoIfip", query = "SELECT c FROM CierreAnualDetalle c WHERE c.cierreAnualDetallePK.codigoIfip = :codigoIfip")})
public class CierreAnualDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CierreAnualDetallePK cierreAnualDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuenta planDeCuenta;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PERIODO", referencedColumnName = "CODIGO_PERIODO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CierreAnual cierreAnual;

    public CierreAnualDetalle() {
    }

    public CierreAnualDetalle(CierreAnualDetallePK cierreAnualDetallePK) {
        this.cierreAnualDetallePK = cierreAnualDetallePK;
    }

    public CierreAnualDetalle(CierreAnualDetallePK cierreAnualDetallePK, BigDecimal total) {
        this.cierreAnualDetallePK = cierreAnualDetallePK;
        this.total = total;
    }

    public CierreAnualDetalle(String codigoPeriodo, String cuentaContable, long codigoTipoPlan, long codigoIfip) {
        this.cierreAnualDetallePK = new CierreAnualDetallePK(codigoPeriodo, cuentaContable, codigoTipoPlan, codigoIfip);
    }

    public CierreAnualDetallePK getCierreAnualDetallePK() {
        return cierreAnualDetallePK;
    }

    public void setCierreAnualDetallePK(CierreAnualDetallePK cierreAnualDetallePK) {
        this.cierreAnualDetallePK = cierreAnualDetallePK;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PlanDeCuenta getPlanDeCuenta() {
        return planDeCuenta;
    }

    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
        this.planDeCuenta = planDeCuenta;
    }

    public CierreAnual getCierreAnual() {
        return cierreAnual;
    }

    public void setCierreAnual(CierreAnual cierreAnual) {
        this.cierreAnual = cierreAnual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cierreAnualDetallePK != null ? cierreAnualDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreAnualDetalle)) {
            return false;
        }
        CierreAnualDetalle other = (CierreAnualDetalle) object;
        if ((this.cierreAnualDetallePK == null && other.cierreAnualDetallePK != null) || (this.cierreAnualDetallePK != null && !this.cierreAnualDetallePK.equals(other.cierreAnualDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CierreAnualDetalle[ cierreAnualDetallePK=" + cierreAnualDetallePK + " ]";
    }
    
}
