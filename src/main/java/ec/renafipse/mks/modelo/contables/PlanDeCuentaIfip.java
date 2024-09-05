
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.PLAN_DE_CUENTA_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanDeCuentaIfip.findAll", query = "SELECT p FROM PlanDeCuentaIfip p"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByCuentaContable", query = "SELECT p FROM PlanDeCuentaIfip p WHERE p.planDeCuentaIfipPK.cuentaContable = :cuentaContable"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByCodigoTipoPlan", query = "SELECT p FROM PlanDeCuentaIfip p WHERE p.planDeCuentaIfipPK.codigoTipoPlan = :codigoTipoPlan"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByCodigoIfip", query = "SELECT p FROM PlanDeCuentaIfip p WHERE p.planDeCuentaIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByReistradoPor", query = "SELECT p FROM PlanDeCuentaIfip p WHERE p.reistradoPor = :reistradoPor"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByFechaRegistro", query = "SELECT p FROM PlanDeCuentaIfip p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByEliminado", query = "SELECT p FROM PlanDeCuentaIfip p WHERE p.eliminado = :eliminado"),
//Personalisadas
    @NamedQuery(name = "PlanDeCuentaIfip.findByPlanCuentaVigente", query = "SELECT pc FROM PlanDeCuentaIfip p JOIN p.planDeCuenta pc JOIN pc.tipoPlanDeCuenta tp WHERE p.planDeCuentaIfipPK.codigoIfip = :codigoIfip AND pc.esDeMovimiento = :esDeMovimiento AND tp.vigente = :vigente AND p.eliminado = :eliminado ORDER BY pc.planDeCuentaPK.cuentaContable"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByCodigoIfipGrupoEliminadoNivel", query = "SELECT p FROM PlanDeCuentaIfip p WHERE p.planDeCuentaIfipPK.codigoIfip = :codigoIfip AND p.planDeCuenta.codigoGrupo.codigo = :codigoGrupo AND p.eliminado= :eliminado AND p.planDeCuenta.nivel = :nivel ORDER BY p.planDeCuenta.planDeCuentaPK.cuentaContable"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByCodigoIfipEliminado", query = "SELECT p.planDeCuenta FROM PlanDeCuentaIfip p WHERE p.planDeCuentaIfipPK.codigoIfip = :codigoIfip AND p.eliminado= :eliminado ORDER BY p.planDeCuenta.planDeCuentaPK.cuentaContable"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByPlanCuentaIfipVigente", query = "SELECT p FROM PlanDeCuentaIfip p JOIN p.planDeCuenta pc JOIN pc.tipoPlanDeCuenta tp WHERE p.planDeCuentaIfipPK.codigoIfip = :codigoIfip AND pc.esDeMovimiento = :esDeMovimiento AND tp.vigente = :vigente AND p.eliminado = :eliminado ORDER BY pc.planDeCuentaPK.cuentaContable"),
    @NamedQuery(name = "PlanDeCuentaIfip.findByCodigoIfipCodigoTipoPlan", query = "SELECT p.planDeCuenta FROM PlanDeCuentaIfip p WHERE p.planDeCuentaIfipPK.codigoIfip = :codigoIfip AND p.planDeCuentaIfipPK.codigoTipoPlan = :codigoTipoPlan  AND p.planDeCuenta.esDeMovimiento= 'S' ORDER BY p.planDeCuentaIfipPK.cuentaContable")
})
public class PlanDeCuentaIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByPlanCuentaVigente="PlanDeCuentaIfip.findByPlanCuentaVigente";
    public static final String findByPlanCuentaIfipVigente="PlanDeCuentaIfip.findByPlanCuentaIfipVigente";
    public static final String findByCodigoIfipGrupoEliminadoNivel="PlanDeCuentaIfip.findByCodigoIfipGrupoEliminadoNivel";
    public static final String findByCodigoIfipEliminado="PlanDeCuentaIfip.findByCodigoIfipEliminado";
    public static final String findByCodigoIfipCodigoTipoPlan="PlanDeCuentaIfip.findByCodigoIfipCodigoTipoPlan";
    @EmbeddedId
    protected PlanDeCuentaIfipPK planDeCuentaIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REISTRADO_POR")
    private long reistradoPor;
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
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuenta planDeCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planDeCuentaIfip")
    private Collection<RegistroContableDetalle> registroContableDetalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planDeCuentaIfip")
    private Collection<ComprobanteDetalle> comprobanteDetalleCollection;

    public PlanDeCuentaIfip() {
    }

    public PlanDeCuentaIfip(PlanDeCuentaIfipPK planDeCuentaIfipPK) {
        this.planDeCuentaIfipPK = planDeCuentaIfipPK;
    }

    public PlanDeCuentaIfip(PlanDeCuentaIfipPK planDeCuentaIfipPK, long reistradoPor, Date fechaRegistro, char eliminado) {
        this.planDeCuentaIfipPK = planDeCuentaIfipPK;
        this.reistradoPor = reistradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public PlanDeCuentaIfip(String cuentaContable, long codigoTipoPlan, long codigoIfip) {
        this.planDeCuentaIfipPK = new PlanDeCuentaIfipPK(cuentaContable, codigoTipoPlan, codigoIfip);
    }

    public PlanDeCuentaIfipPK getPlanDeCuentaIfipPK() {
        return planDeCuentaIfipPK;
    }

    public void setPlanDeCuentaIfipPK(PlanDeCuentaIfipPK planDeCuentaIfipPK) {
        this.planDeCuentaIfipPK = planDeCuentaIfipPK;
    }

    public long getReistradoPor() {
        return reistradoPor;
    }

    public void setReistradoPor(long reistradoPor) {
        this.reistradoPor = reistradoPor;
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

    @XmlTransient
    public Collection<RegistroContableDetalle> getRegistroContableDetalleCollection() {
        return registroContableDetalleCollection;
    }

    public void setRegistroContableDetalleCollection(Collection<RegistroContableDetalle> registroContableDetalleCollection) {
        this.registroContableDetalleCollection = registroContableDetalleCollection;
    }

    @XmlTransient
    public Collection<ComprobanteDetalle> getComprobanteDetalleCollection() {
        return comprobanteDetalleCollection;
    }

    public void setComprobanteDetalleCollection(Collection<ComprobanteDetalle> comprobanteDetalleCollection) {
        this.comprobanteDetalleCollection = comprobanteDetalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planDeCuentaIfipPK != null ? planDeCuentaIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanDeCuentaIfip)) {
            return false;
        }
        PlanDeCuentaIfip other = (PlanDeCuentaIfip) object;
        if ((this.planDeCuentaIfipPK == null && other.planDeCuentaIfipPK != null) || (this.planDeCuentaIfipPK != null && !this.planDeCuentaIfipPK.equals(other.planDeCuentaIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.PlanDeCuentaIfip[ planDeCuentaIfipPK=" + planDeCuentaIfipPK + " ]";
    }
    
}
