/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.contables.PlanDeCuenta;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.CUENTA_COTABLE_GENERAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaCotableGeneral.findAll", query = "SELECT c FROM CuentaCotableGeneral c"),
    @NamedQuery(name = "CuentaCotableGeneral.findByCodigo", query = "SELECT c FROM CuentaCotableGeneral c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CuentaCotableGeneral.findByDestino", query = "SELECT c FROM CuentaCotableGeneral c WHERE c.destino = :destino")})
public class CuentaCotableGeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESTINO")
    private String destino;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private PlanDeCuenta planDeCuenta;

    public CuentaCotableGeneral() {
    }

    public CuentaCotableGeneral(Long codigo) {
        this.codigo = codigo;
    }

    public CuentaCotableGeneral(Long codigo, String destino) {
        this.codigo = codigo;
        this.destino = destino;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaCotableGeneral)) {
            return false;
        }
        CuentaCotableGeneral other = (CuentaCotableGeneral) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.contables.CuentaCotableGeneral[ codigo=" + codigo + " ]";
    }
    
}
