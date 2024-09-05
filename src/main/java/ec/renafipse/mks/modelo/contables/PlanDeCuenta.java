
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.PLAN_DE_CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanDeCuenta.findAll", query = "SELECT p FROM PlanDeCuenta p ORDER BY p.planDeCuentaPK.cuentaContable"),
    @NamedQuery(name = "PlanDeCuenta.findByCuentaContable", query = "SELECT p FROM PlanDeCuenta p WHERE p.planDeCuentaPK.cuentaContable = :cuentaContable"),
    @NamedQuery(name = "PlanDeCuenta.findByCodigoTipoPlan", query = "SELECT p FROM PlanDeCuenta p WHERE p.planDeCuentaPK.codigoTipoPlan = :codigoTipoPlan"),
    @NamedQuery(name = "PlanDeCuenta.findByNombre", query = "SELECT p FROM PlanDeCuenta p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PlanDeCuenta.findByEsDeMovimiento", query = "SELECT p FROM PlanDeCuenta p WHERE p.esDeMovimiento = :esDeMovimiento"),
    @NamedQuery(name = "PlanDeCuenta.findByPorDefecto", query = "SELECT p FROM PlanDeCuenta p WHERE p.porDefecto = :porDefecto"),
    @NamedQuery(name = "PlanDeCuenta.findByEliminado", query = "SELECT p FROM PlanDeCuenta p WHERE p.eliminado = :eliminado"),
//personalizados
    @NamedQuery(name = "PlanDeCuenta.findBycodigoGrupoNivel", query = "SELECT p FROM PlanDeCuenta p WHERE p.codigoGrupo.codigo = :codigoGrupo AND p.nivel = :nivel"),
    @NamedQuery(name = "PlanDeCuenta.findByCuentaContableEliminado", query = "SELECT p FROM PlanDeCuenta p WHERE p.planDeCuentaPK.cuentaContable = :cuentaContable AND p.eliminado = :eliminado"),
    @NamedQuery(name = "PlanDeCuenta.findByCodigoTipoPlanCodigoGrupo", query = "SELECT p FROM PlanDeCuenta p WHERE p.planDeCuentaPK.codigoTipoPlan = :codigoTipoPlan AND p.codigoGrupo.codigo = :codigoGrupo AND p.eliminado = :eliminado ORDER BY p.planDeCuentaPK.cuentaContable")})
public class PlanDeCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoTipoPlanCodigoGrupo="PlanDeCuenta.findByCodigoTipoPlanCodigoGrupo";
    public static final String findByEliminado="PlanDeCuenta.findByEliminado";
    public static final String findBycodigoGrupoNivel="PlanDeCuenta.findBycodigoGrupoNivel";
    public static final String findAll="PlanDeCuenta.findAll";
    public static final String findByCuentaContableEliminado="PlanDeCuenta.findByCuentaContableEliminado";
    @EmbeddedId
    protected PlanDeCuentaPK planDeCuentaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_DE_MOVIMIENTO")
    private char esDeMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POR_DEFECTO")
    private char porDefecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Column(name = "CUENTA_PADRE")
    private String cuentaPadre;
    @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPlanDeCuenta tipoPlanDeCuenta;   
//    @JoinColumns({
//        @JoinColumn(name = "CODIGO_TIPO_PLAN", referencedColumnName = "CODIGO_TIPO_PLAN", insertable = false, updatable = false),
//        @JoinColumn(name = "CUENTA_PADRE", referencedColumnName = "CUENTA_CONTABLE", insertable = false, updatable = false)})
//    @ManyToOne(optional = false)
//    private PlanDeCuenta planDeCuenta;
    @JoinColumn(name = "CODIGO_GRUPO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private GrupoCuentaContable codigoGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL")
    private short nivel;
  

    public PlanDeCuenta() {
    }

    public PlanDeCuenta(PlanDeCuentaPK planDeCuentaPK) {
        this.planDeCuentaPK = planDeCuentaPK;
    }

    public PlanDeCuenta(PlanDeCuentaPK planDeCuentaPK, String nombre, char esDeMovimiento, char porDefecto, char eliminado) {
        this.planDeCuentaPK = planDeCuentaPK;
        this.nombre = nombre;
        this.esDeMovimiento = esDeMovimiento;
        this.porDefecto = porDefecto;
        this.eliminado = eliminado;
    }

    public PlanDeCuenta(String cuentaContable, long codigoTipoPlan) {
        this.planDeCuentaPK = new PlanDeCuentaPK(cuentaContable, codigoTipoPlan);
    }

    public PlanDeCuentaPK getPlanDeCuentaPK() {
        return planDeCuentaPK;
    }

    public void setPlanDeCuentaPK(PlanDeCuentaPK planDeCuentaPK) {
        this.planDeCuentaPK = planDeCuentaPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getEsDeMovimiento() {
        return esDeMovimiento;
    }

    public void setEsDeMovimiento(char esDeMovimiento) {
        this.esDeMovimiento = esDeMovimiento;
    }

    public char getPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(char porDefecto) {
        this.porDefecto = porDefecto;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoPlanDeCuenta getTipoPlanDeCuenta() {
        return tipoPlanDeCuenta;
    }

    public void setTipoPlanDeCuenta(TipoPlanDeCuenta tipoPlanDeCuenta) {
        this.tipoPlanDeCuenta = tipoPlanDeCuenta;
    }

/*
    public PlanDeCuenta getPlanDeCuenta() {
        return planDeCuenta;
    }

    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
        this.planDeCuenta = planDeCuenta;
    }*/

    public GrupoCuentaContable getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(GrupoCuentaContable codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planDeCuentaPK != null ? planDeCuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanDeCuenta)) {
            return false;
        }
        PlanDeCuenta other = (PlanDeCuenta) object;
        if ((this.planDeCuentaPK == null && other.planDeCuentaPK != null) || (this.planDeCuentaPK != null && !this.planDeCuentaPK.equals(other.planDeCuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.PlanDeCuenta[ planDeCuentaPK=" + planDeCuentaPK + " ]";
    }

    /**
     * @return the cuentaPadre
     */
    public String getCuentaPadre() {
        return cuentaPadre;
    }

    /**
     * @param cuentaPadre the cuentaPadre to set
     */
    public void setCuentaPadre(String cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
    }

//    /**
//     * @return the planDeCuenta
//     */
//    public PlanDeCuenta getPlanDeCuenta() {
//        return planDeCuenta;
//    }
//
//    /**
//     * @param planDeCuenta the planDeCuenta to set
//     */
//    public void setPlanDeCuenta(PlanDeCuenta planDeCuenta) {
//        this.planDeCuenta = planDeCuenta;
//    }

    /**
     * @return the nivel
     */
    public short getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(short nivel) {
        this.nivel = nivel;
    }
    
}
