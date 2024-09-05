
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
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
@Table(name = "MKS_CONTABLES.PROCESO_CONTABLE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoContableDetalle.findAll", query = "SELECT p FROM ProcesoContableDetalle p"),
    @NamedQuery(name = "ProcesoContableDetalle.findByCodigoProceso", query = "SELECT p FROM ProcesoContableDetalle p WHERE p.procesoContableDetallePK.codigoProceso = :codigoProceso"),
    @NamedQuery(name = "ProcesoContableDetalle.findByCuentaContable", query = "SELECT p FROM ProcesoContableDetalle p WHERE p.procesoContableDetallePK.cuentaContable = :cuentaContable"),
    @NamedQuery(name = "ProcesoContableDetalle.findByCodigoTipoPlan", query = "SELECT p FROM ProcesoContableDetalle p WHERE p.procesoContableDetallePK.codigoTipoPlan = :codigoTipoPlan"),
    @NamedQuery(name = "ProcesoContableDetalle.findByTipo", query = "SELECT p FROM ProcesoContableDetalle p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "ProcesoContableDetalle.findByLinea", query = "SELECT p FROM ProcesoContableDetalle p WHERE p.linea = :linea"),
    @NamedQuery(name = "ProcesoContableDetalle.findByEliminado", query = "SELECT p FROM ProcesoContableDetalle p WHERE p.eliminado = :eliminado"),

//Personalizada
    @NamedQuery(name = "ProcesoContableDetalle.findProcesosDetalle", query = "SELECT p FROM ProcesoContableDetalle p WHERE p.procesoContableDetallePK.codigoProceso = :codigoProceso")
})

public class ProcesoContableDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findProcesosDetalle = "ProcesoContableDetalle.findProcesosDetalle";

    @EmbeddedId
    protected ProcesoContableDetallePK procesoContableDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINEA")
    private short linea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PROCESO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProcesoContable procesoContable;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
        @JoinColumn(name = "CUENTA_CONTABLE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PlanDeCuenta planDeCuenta;

    public ProcesoContableDetalle() {
    }

    public ProcesoContableDetalle(ProcesoContableDetallePK procesoContableDetallePK) {
        this.procesoContableDetallePK = procesoContableDetallePK;
    }

    public ProcesoContableDetalle(ProcesoContableDetallePK procesoContableDetallePK, char tipo, short linea, char eliminado) {
        this.procesoContableDetallePK = procesoContableDetallePK;
        this.tipo = tipo;
        this.linea = linea;
        this.eliminado = eliminado;
    }

    public ProcesoContableDetalle(long codigoProceso, String cuentaContable, long codigoTipoPlan) {
        this.procesoContableDetallePK = new ProcesoContableDetallePK(codigoProceso, cuentaContable, codigoTipoPlan);
    }

    public ProcesoContableDetallePK getProcesoContableDetallePK() {
        return procesoContableDetallePK;
    }

    public void setProcesoContableDetallePK(ProcesoContableDetallePK procesoContableDetallePK) {
        this.procesoContableDetallePK = procesoContableDetallePK;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public short getLinea() {
        return linea;
    }

    public void setLinea(short linea) {
        this.linea = linea;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public ProcesoContable getProcesoContable() {
        return procesoContable;
    }

    public void setProcesoContable(ProcesoContable procesoContable) {
        this.procesoContable = procesoContable;
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
        hash += (procesoContableDetallePK != null ? procesoContableDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoContableDetalle)) {
            return false;
        }
        ProcesoContableDetalle other = (ProcesoContableDetalle) object;
        if ((this.procesoContableDetallePK == null && other.procesoContableDetallePK != null) || (this.procesoContableDetallePK != null && !this.procesoContableDetallePK.equals(other.procesoContableDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ProcesoContableDetalle[ procesoContableDetallePK=" + procesoContableDetallePK + " ]";
    }
    
}
