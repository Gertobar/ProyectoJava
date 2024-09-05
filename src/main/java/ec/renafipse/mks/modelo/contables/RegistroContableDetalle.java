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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.REGISTRO_CONTABLE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroContableDetalle.findAll", query = "SELECT r FROM RegistroContableDetalle r"),
    @NamedQuery(name = "RegistroContableDetalle.findByCodigoRegistro", query = "SELECT r FROM RegistroContableDetalle r WHERE r.registroContableDetallePK.codigoRegistro = :codigoRegistro"),
    @NamedQuery(name = "RegistroContableDetalle.findByCuentaContable", query = "SELECT r FROM RegistroContableDetalle r WHERE r.registroContableDetallePK.cuentaContable = :cuentaContable"),
    @NamedQuery(name = "RegistroContableDetalle.findByCodigoTipoPlan", query = "SELECT r FROM RegistroContableDetalle r WHERE r.registroContableDetallePK.codigoTipoPlan = :codigoTipoPlan"),
    @NamedQuery(name = "RegistroContableDetalle.findByCodigoIfip", query = "SELECT r FROM RegistroContableDetalle r WHERE r.registroContableDetallePK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RegistroContableDetalle.findByValor", query = "SELECT r FROM RegistroContableDetalle r WHERE r.valor = :valor"),
    @NamedQuery(name = "RegistroContableDetalle.findByTipo", query = "SELECT r FROM RegistroContableDetalle r WHERE r.tipo = :tipo")})
public class RegistroContableDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroContableDetallePK registroContableDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @JoinColumn(name = "CODIGO_REGISTRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RegistroContable registroContable;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuentaIfip planDeCuentaIfip;

    public RegistroContableDetalle() {
    }

    public RegistroContableDetalle(RegistroContableDetallePK registroContableDetallePK) {
        this.registroContableDetallePK = registroContableDetallePK;
    }

    public RegistroContableDetalle(RegistroContableDetallePK registroContableDetallePK, BigDecimal valor, char tipo) {
        this.registroContableDetallePK = registroContableDetallePK;
        this.valor = valor;
        this.tipo = tipo;
    }

    public RegistroContableDetalle(long codigoRegistro, String cuentaContable, long codigoTipoPlan, long codigoIfip) {
        this.registroContableDetallePK = new RegistroContableDetallePK(codigoRegistro, cuentaContable, codigoTipoPlan, codigoIfip);
    }

    public RegistroContableDetallePK getRegistroContableDetallePK() {
        return registroContableDetallePK;
    }

    public void setRegistroContableDetallePK(RegistroContableDetallePK registroContableDetallePK) {
        this.registroContableDetallePK = registroContableDetallePK;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public RegistroContable getRegistroContable() {
        return registroContable;
    }

    public void setRegistroContable(RegistroContable registroContable) {
        this.registroContable = registroContable;
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
        hash += (registroContableDetallePK != null ? registroContableDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroContableDetalle)) {
            return false;
        }
        RegistroContableDetalle other = (RegistroContableDetalle) object;
        if ((this.registroContableDetallePK == null && other.registroContableDetallePK != null) || (this.registroContableDetallePK != null && !this.registroContableDetallePK.equals(other.registroContableDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.RegistroContableDetalle[ registroContableDetallePK=" + registroContableDetallePK + " ]";
    }
    
}
