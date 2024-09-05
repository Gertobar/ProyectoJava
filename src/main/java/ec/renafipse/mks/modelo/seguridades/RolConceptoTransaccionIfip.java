/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccion;
import ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro;
import ec.renafipse.mks.negocio.ahorros.TransaccionFacade;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Viajes2
 */
@Entity
@Table(name = "MKS_SEGURIDADES.ROL_CONCEPTO_TRANSACCION_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolConceptoTransaccionIfip.findAll", query = "SELECT r FROM RolConceptoTransaccionIfip r"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByCodigoTipoProducto", query = "SELECT r FROM RolConceptoTransaccionIfip r WHERE r.rolConceptoTransaccionIfipPK.codigoTipoProducto = :codigoTipoProducto"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByCodigoMoneda", query = "SELECT r FROM RolConceptoTransaccionIfip r WHERE r.rolConceptoTransaccionIfipPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByCodigoConcepto", query = "SELECT r FROM RolConceptoTransaccionIfip r WHERE r.rolConceptoTransaccionIfipPK.codigoConcepto = :codigoConcepto"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByCodigoRol", query = "SELECT r FROM RolConceptoTransaccionIfip r WHERE r.rolConceptoTransaccionIfipPK.codigoRol = :codigoRol"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByCodigoIfip", query = "SELECT r FROM RolConceptoTransaccionIfip r WHERE r.rolConceptoTransaccionIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByFechaIngreso", query = "SELECT r FROM RolConceptoTransaccionIfip r WHERE r.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByEliminado", query = "SELECT r FROM RolConceptoTransaccionIfip r WHERE r.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByTransaccionesMovimentoCaja", query = "SELECT DISTINCT t FROM RolConceptoTransaccionIfip r JOIN  r.conceptoTransaccionPro p JOIN p.conceptoTransaccion c JOIN c.codigoTransaccion t WHERE r.rolConceptoTransaccionIfipPK.codigoTipoProducto = :codigoTipoProducto AND r.rolConceptoTransaccionIfipPK.codigoMoneda =:codigoMoneda AND r.rolConceptoTransaccionIfipPK.codigoIfip = :codigoIfip AND r.rolConceptoTransaccionIfipPK.codigoRol = :codigoRol AND r.eliminado = :eliminado AND p.eliminado = :eliminado AND c.eliminado = :eliminado AND t.eliminado = :eliminado AND p.mostrar = :mostrar"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByConceptosMovimentoCaja", query = "SELECT DISTINCT c FROM RolConceptoTransaccionIfip r JOIN r.conceptoTransaccionPro p JOIN p.conceptoTransaccion c JOIN c.codigoTransaccion t   WHERE r.rolConceptoTransaccionIfipPK.codigoTipoProducto = :codigoTipoProducto AND r.rolConceptoTransaccionIfipPK.codigoMoneda =:codigoMoneda AND r.rolConceptoTransaccionIfipPK.codigoIfip = :codigoIfip AND r.rolConceptoTransaccionIfipPK.codigoRol = :codigoRol AND c.codigoTransaccion.codigo = :codigoTransaccion AND r.eliminado = :eliminado AND p.eliminado = :eliminado AND c.eliminado = :eliminado AND t.eliminado = :eliminado AND p.mostrar = :mostrar"),
    //Los conceptos de las transacciones asigandos al rol Faltantes
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByConceptosPermisosRol", query = "SELECT p FROM RolConceptoTransaccionIfip r JOIN r.conceptoTransaccionPro p JOIN p.conceptoTransaccion c  WHERE r.rolConceptoTransaccionIfipPK.codigoTipoProducto = :codigoTipoProducto AND r.rolConceptoTransaccionIfipPK.codigoMoneda = :codigoMoneda AND r.rolConceptoTransaccionIfipPK.codigoIfip = :codigoIfip AND r.rolConceptoTransaccionIfipPK.codigoRol = :codigoRol AND c.codigoTransaccion.codigo = :codigoTransaccion AND r.eliminado = :eliminado AND p.eliminado = :eliminado AND c.eliminado = :eliminado AND c.codigoTransaccion.eliminado = :eliminado AND p.mostrar = :mostrar ORDER BY c.nombre"),
    @NamedQuery(name = "RolConceptoTransaccionIfip.findByRolConceptoTransaccionIfip", query = "SELECT r FROM RolConceptoTransaccionIfip r JOIN r.conceptoTransaccionPro p JOIN p.conceptoTransaccion c JOIN c.codigoTransaccion t WHERE r.rolConceptoTransaccionIfipPK.codigoMoneda = :codigoMoneda AND r.rolConceptoTransaccionIfipPK.codigoRol = :codigoRol AND t.codigo = :codigoTransaccion AND r.rolConceptoTransaccionIfipPK.codigoIfip = :codigoIfip AND r.rolConceptoTransaccionIfipPK.codigoTipoProducto = :codigoProducto ORDER BY c.nombre")        
})
public class RolConceptoTransaccionIfip implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByTransaccionesMovimentoCaja = "RolConceptoTransaccionIfip.findByTransaccionesMovimentoCaja";
    public static final String findByConceptosMovimentoCaja = "RolConceptoTransaccionIfip.findByConceptosMovimentoCaja";
    public static final String findByConceptosPermisosRol = "RolConceptoTransaccionIfip.findByConceptosPermisosRol";
    public static final String findByRolConceptoTransaccionIfip = "RolConceptoTransaccionIfip.findByRolConceptoTransaccionIfip";

    @EmbeddedId
    protected RolConceptoTransaccionIfipPK rolConceptoTransaccionIfipPK;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "INGRESADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario ingresadoPor;
    @JoinColumn(name = "CODIGO_ROL", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;
    /*@JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_CONCEPTO", referencedColumnName = "CODIGO_CONCEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionPro;*/

    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_CONCEPTO", referencedColumnName = "CODIGO_CONCEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ConceptoTransaccionPro conceptoTransaccionPro;
    public RolConceptoTransaccionIfip() {
    }

    public RolConceptoTransaccionIfip(RolConceptoTransaccionIfipPK rolConceptoTransaccionIfipPK) {
        this.rolConceptoTransaccionIfipPK = rolConceptoTransaccionIfipPK;
    }

    public RolConceptoTransaccionIfip(RolConceptoTransaccionIfipPK rolConceptoTransaccionIfipPK, char eliminado) {
        this.rolConceptoTransaccionIfipPK = rolConceptoTransaccionIfipPK;
        this.eliminado = eliminado;
    }

    public RolConceptoTransaccionIfip(long codigoTipoProducto, long codigoMoneda, long codigoConcepto, String codigoRol, long codigoIfip) {
        this.rolConceptoTransaccionIfipPK = new RolConceptoTransaccionIfipPK(codigoTipoProducto, codigoMoneda, codigoConcepto, codigoRol, codigoIfip);
    }

    public RolConceptoTransaccionIfipPK getRolConceptoTransaccionIfipPK() {
        return rolConceptoTransaccionIfipPK;
    }

    public void setRolConceptoTransaccionIfipPK(RolConceptoTransaccionIfipPK rolConceptoTransaccionIfipPK) {
        this.rolConceptoTransaccionIfipPK = rolConceptoTransaccionIfipPK;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Usuario getIngresadoPor() {
        return ingresadoPor;
    }

    public void setIngresadoPor(Usuario ingresadoPor) {
        this.ingresadoPor = ingresadoPor;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolConceptoTransaccionIfipPK != null ? rolConceptoTransaccionIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolConceptoTransaccionIfip)) {
            return false;
        }
        RolConceptoTransaccionIfip other = (RolConceptoTransaccionIfip) object;
        if ((this.rolConceptoTransaccionIfipPK == null && other.rolConceptoTransaccionIfipPK != null) || (this.rolConceptoTransaccionIfipPK != null && !this.rolConceptoTransaccionIfipPK.equals(other.rolConceptoTransaccionIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolConceptoTransaccionIfip[ rolConceptoTransaccionIfipPK=" + rolConceptoTransaccionIfipPK + " ]";
    }

    /**
     * @return the conceptoTransaccionPro
     */
   /* public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    /**
     * @param conceptoTransaccionPro the conceptoTransaccionPro to set
     */
    /*public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
    }

    /**
     * @return the conceptoTransaccionPro
     */
    public ConceptoTransaccionPro getConceptoTransaccionPro() {
        return conceptoTransaccionPro;
    }

    /**
     * @param conceptoTransaccionPro the conceptoTransaccionPro to set
     */
    public void setConceptoTransaccionPro(ConceptoTransaccionPro conceptoTransaccionPro) {
        this.conceptoTransaccionPro = conceptoTransaccionPro;
    }

}
