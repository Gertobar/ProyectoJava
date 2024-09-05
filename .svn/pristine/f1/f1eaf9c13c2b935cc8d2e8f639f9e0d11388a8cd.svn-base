/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.adquisiciones.GrupoArticulo;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.CUENTA_CONTABLE_GRU_ART_COM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaContableGruArtCom.findAll", query = "SELECT c FROM CuentaContableGruArtCom c"),
    @NamedQuery(name = "CuentaContableGruArtCom.findByCodigoGrupo", query = "SELECT c FROM CuentaContableGruArtCom c WHERE c.codigoGrupo = :codigoGrupo"),
//PERSONALIZADAS
    @NamedQuery(name = "CuentaContableGruArtCom.findItemsArticuloEliminado", query = "SELECT c FROM CuentaContableGruArtCom c WHERE c.grupoArticulo.eliminado = :eliminado ORDER BY c.grupoArticulo.nombre")
})
public class CuentaContableGruArtCom implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findItemsArticuloEliminado = "CuentaContableGruArtCom.findItemsArticuloEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_GRUPO")
    private Long codigoGrupo;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuenta planDeCuenta;
    @JoinColumn(name = "CODIGO_GRUPO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GrupoArticulo grupoArticulo;

    public CuentaContableGruArtCom() {
    }

    public CuentaContableGruArtCom(Long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public Long getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(Long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoGrupo != null ? codigoGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContableGruArtCom)) {
            return false;
        }
        CuentaContableGruArtCom other = (CuentaContableGruArtCom) object;
        if ((this.codigoGrupo == null && other.codigoGrupo != null) || (this.codigoGrupo != null && !this.codigoGrupo.equals(other.codigoGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CuentaContableGruArtCom[ codigoGrupo=" + codigoGrupo + " ]";
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

    /**
     * @return the grupoArticulo
     */
    public GrupoArticulo getGrupoArticulo() {
        return grupoArticulo;
    }

    /**
     * @param grupoArticulo the grupoArticulo to set
     */
    public void setGrupoArticulo(GrupoArticulo grupoArticulo) {
        this.grupoArticulo = grupoArticulo;
    }
    
}
