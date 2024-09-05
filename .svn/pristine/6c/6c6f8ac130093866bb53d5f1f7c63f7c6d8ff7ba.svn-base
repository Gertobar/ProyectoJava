/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MKS_CONTABLES.CUENTA_CONTABLE_ENT_FIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaContableEntFin.findAll", query = "SELECT c FROM CuentaContableEntFin c"),
    @NamedQuery(name = "CuentaContableEntFin.findByCodigoEntidadFinanciera", query = "SELECT c FROM CuentaContableEntFin c WHERE c.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "CuentaContableEntFin.findByFechaRegistro", query = "SELECT c FROM CuentaContableEntFin c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "CuentaContableEntFin.findByEliminado", query = "SELECT c FROM CuentaContableEntFin c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "CuentaContableEntFin.findByCodigoEliminado", query = "SELECT c FROM CuentaContableEntFin c WHERE c.codigoEntidadFinanciera = :codigoEntidadFinanciera AND c.eliminado = :eliminado")})
public class CuentaContableEntFin implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoEliminado = "CuentaContableEntFin.findByCodigoEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private Long codigoEntidadFinanciera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN"),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE")})
    @ManyToOne(optional = false)
    private PlanDeCuenta planDeCuenta;

    public CuentaContableEntFin() {
    }

    public CuentaContableEntFin(Long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public CuentaContableEntFin(Long codigoEntidadFinanciera, Date fechaRegistro, char eliminado) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(Long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
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

    public PlanDeCuenta getPlanDeCuenta() {
        return planDeCuenta;
    }

    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
        this.planDeCuenta = planDeCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEntidadFinanciera != null ? codigoEntidadFinanciera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContableEntFin)) {
            return false;
        }
        CuentaContableEntFin other = (CuentaContableEntFin) object;
        if ((this.codigoEntidadFinanciera == null && other.codigoEntidadFinanciera != null) || (this.codigoEntidadFinanciera != null && !this.codigoEntidadFinanciera.equals(other.codigoEntidadFinanciera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CuentaContableEntFin[ codigoEntidadFinanciera=" + codigoEntidadFinanciera + " ]";
    }
    
}
