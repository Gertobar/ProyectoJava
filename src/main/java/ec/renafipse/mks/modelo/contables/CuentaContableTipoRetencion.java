/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.CUENTA_CONTABLE_TIPO_RETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaContableTipoRetencion.findAll", query = "SELECT c FROM CuentaContableTipoRetencion c"),
    @NamedQuery(name = "CuentaContableTipoRetencion.findByCodigoTipoRetencion", query = "SELECT c FROM CuentaContableTipoRetencion c WHERE c.codigoTipoRetencion = :codigoTipoRetencion")})
public class CuentaContableTipoRetencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_RETENCION")
    private Long codigoTipoRetencion;
    @JoinColumn(name = "CODIGO_TIPO_RETENCION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TipoRetencion tipoRetencion;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private PlanDeCuenta planDeCuenta;

    public CuentaContableTipoRetencion() {
    }

    public CuentaContableTipoRetencion(Long codigoTipoRetencion) {
        this.codigoTipoRetencion = codigoTipoRetencion;
    }

    public Long getCodigoTipoRetencion() {
        return codigoTipoRetencion;
    }

    public void setCodigoTipoRetencion(Long codigoTipoRetencion) {
        this.codigoTipoRetencion = codigoTipoRetencion;
    }

    public TipoRetencion getTipoRetencion() {
        return tipoRetencion;
    }

    public void setTipoRetencion(TipoRetencion tipoRetencion) {
        this.tipoRetencion = tipoRetencion;
    }

    public PlanDeCuenta getPlanDeCuenta() {
        return planDeCuenta;
    }

    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
        this.planDeCuenta = planDeCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTipoRetencion != null ? codigoTipoRetencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContableTipoRetencion)) {
            return false;
        }
        CuentaContableTipoRetencion other = (CuentaContableTipoRetencion) object;
        if ((this.codigoTipoRetencion == null && other.codigoTipoRetencion != null) || (this.codigoTipoRetencion != null && !this.codigoTipoRetencion.equals(other.codigoTipoRetencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.contables.CuentaContableTipoRetencion[ codigoTipoRetencion=" + codigoTipoRetencion + " ]";
    }
    
}
