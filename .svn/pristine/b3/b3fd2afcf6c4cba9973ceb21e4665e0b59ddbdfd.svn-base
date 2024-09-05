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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author renafipse2
 */
@Entity
@Table(name = "MKS_CONTABLES.CUENTAS_CUADRE_FINANCIERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentasCuadreFinanciero.findAll", query = "SELECT c FROM CuentasCuadreFinanciero c"),
    @NamedQuery(name = "CuentasCuadreFinanciero.findByCodigo", query = "SELECT c FROM CuentasCuadreFinanciero c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CuentasCuadreFinanciero.findByNombre", query = "SELECT c FROM CuentasCuadreFinanciero c WHERE c.nombre = :nombre"),
//Personalizadas
    @NamedQuery(name = "CuentasCuadreFinanciero.findByCodigoTipoCuadre", query = "SELECT c FROM CuentasCuadreFinanciero c WHERE c.codigoTipoCuadre.codigo = :codigoTipoCuadre")
})
public class CuentasCuadreFinanciero implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoTipoCuadre="CuentasCuadreFinanciero.findByCodigoTipoCuadre";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "CODIGO_TIPO_CUADRE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoCuadreFinanciero codigoTipoCuadre;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuenta planDeCuenta;

    public CuentasCuadreFinanciero() {
    }

    public CuentasCuadreFinanciero(Long codigo) {
        this.codigo = codigo;
    }

    public CuentasCuadreFinanciero(Long codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public TipoCuadreFinanciero getCodigoTipoCuadre() {
        return codigoTipoCuadre;
    }

    public void setCodigoTipoCuadre(TipoCuadreFinanciero codigoTipoCuadre) {
        this.codigoTipoCuadre = codigoTipoCuadre;
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
        if (!(object instanceof CuentasCuadreFinanciero)) {
            return false;
        }
        CuentasCuadreFinanciero other = (CuentasCuadreFinanciero) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CuentasCuadreFinanciero[ codigo=" + codigo + " ]";
    }

    /**
     * @return the planDeCuenta
     */
    public PlanDeCuenta getPlanDeCuenta() {
        return planDeCuenta;
    }

    /**
     * @param planDeCuenta the planDeCuenta to set
     */
    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
        this.planDeCuenta = planDeCuenta;
    }
    
}
