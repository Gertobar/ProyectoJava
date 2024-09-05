/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.CONCEPTO_TRANSACCION_PRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoTransaccionPro.findAll", query = "SELECT c FROM ConceptoTransaccionPro c"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByCodigoTipoProducto", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByCodigoMoneda", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByCodigoConcepto", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.conceptoTransaccionProPK.codigoConcepto = :codigoConcepto"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByMostrar", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.mostrar = :mostrar"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByRegistradoPor", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.registradoPor = :registradoPor"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByFechaRegistro", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByEliminado", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.eliminado = :eliminado"),
    //Personalizados
    //Transaccion
    @NamedQuery(name = "ConceptoTransaccionPro.findByTransaccionesPermisosRol", query = "SELECT DISTINCT t FROM ConceptoTransaccionPro c JOIN c.conceptoTransaccion ct JOIN ct.codigoTransaccion t WHERE c.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND c.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND c.eliminado = :eliminado AND ct.eliminado = :eliminado AND t.eliminado = :eliminado"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByPK", query = "SELECT  c FROM ConceptoTransaccionPro c WHERE c.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND c.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND c.conceptoTransaccionProPK.codigoConcepto = :codigoConcepto"),
//Los conceptos de las transacciones existentes
    // @NamedQuery(name = "ConceptoTransaccionPro.findByConceptosPermisosRol", query = "SELECT p FROM ConceptoTransaccionPro p JOIN p.conceptoTransaccion t WHERE t.codigoTransaccion.codigo = :codigoTransaccion AND p.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND p.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado AND p.conceptoTransaccion.eliminado = :eliminado AND p.mostrar = :mostrar ORDER BY p.conceptoTransaccion.codigoTransaccion.nombre")       
    //Transaccion
    @NamedQuery(name = "ConceptoTransaccionPro.findByTransaccionesContabilizada",
            query = "SELECT c FROM ConceptoTransaccionPro c "
            + "WHERE c.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto "
            + "AND c.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda "
            + "AND c.eliminado = :eliminado "
            + "AND c.conceptoTransaccion.codigoTransaccion.codigo = :codigoTransaccion "
            + "AND c.conceptoTransaccion.eliminado = :eliminado "
            + "AND c.conceptoTransaccion.codigoTransaccion.eliminado = :eliminado "
            + "AND c.conceptoTransaccion.contabiliza = :contabilizaSN "
            + "ORDER BY c.conceptoTransaccion.nombre"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByConceptosPermisosRol", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND c.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda  AND c.conceptoTransaccion.codigoTransaccion.codigo = :codigoTransaccion AND c.eliminado = :eliminado AND c.conceptoTransaccion.eliminado = :eliminado AND c.conceptoTransaccion.codigoTransaccion.eliminado = :eliminado  ORDER BY c.conceptoTransaccion.nombre"),
    @NamedQuery(name = "ConceptoTransaccionPro.findByCodigoTipoProductoMoneda", query = "SELECT c FROM ConceptoTransaccionPro c WHERE c.conceptoTransaccionProPK.codigoTipoProducto = :codigoTipoProducto AND c.conceptoTransaccionProPK.codigoMoneda = :codigoMoneda ORDER BY c.conceptoTransaccion.nombre" )
})
public class ConceptoTransaccionPro implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByTransaccionesPermisosRol = "ConceptoTransaccionPro.findByTransaccionesPermisosRol";
    public static final String findByConceptosPermisosRol = "ConceptoTransaccionPro.findByConceptosPermisosRol";
    public static final String findByPK = "ConceptoTransaccionPro.findByPK";
    public static final String findByTransaccionesContabilizada = "ConceptoTransaccionPro.findByTransaccionesContabilizada";
    public static final String findByCodigoTipoProductoMoneda = "ConceptoTransaccionPro.findByCodigoTipoProductoMoneda";

    //public static final String findByConceptosPermisosRol = "ConceptoTransaccionPro.findByConceptosPermisosRol";
    @EmbeddedId
    protected ConceptoTransaccionProPK conceptoTransaccionProPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOSTRAR")
    private char mostrar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
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
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "CODIGO_CONCEPTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ConceptoTransaccion conceptoTransaccion;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario registradoPorUsuario;

    public ConceptoTransaccionPro() {
    }

    public ConceptoTransaccionPro(ConceptoTransaccionProPK conceptoTransaccionProPK) {
        this.conceptoTransaccionProPK = conceptoTransaccionProPK;
    }

    public ConceptoTransaccionPro(ConceptoTransaccionProPK conceptoTransaccionProPK, char mostrar, long registradoPor, Date fechaRegistro, char eliminado) {
        this.conceptoTransaccionProPK = conceptoTransaccionProPK;
        this.mostrar = mostrar;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public ConceptoTransaccionPro(long codigoTipoProducto, long codigoMoneda, long codigoConcepto) {
        this.conceptoTransaccionProPK = new ConceptoTransaccionProPK(codigoTipoProducto, codigoMoneda, codigoConcepto);
    }

    public ConceptoTransaccionProPK getConceptoTransaccionProPK() {
        return conceptoTransaccionProPK;
    }

    public void setConceptoTransaccionProPK(ConceptoTransaccionProPK conceptoTransaccionProPK) {
        this.conceptoTransaccionProPK = conceptoTransaccionProPK;
    }

    public char getMostrar() {
        return mostrar;
    }

    public void setMostrar(char mostrar) {
        this.mostrar = mostrar;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ConceptoTransaccion getConceptoTransaccion() {
        return conceptoTransaccion;
    }

    public void setConceptoTransaccion(ConceptoTransaccion conceptoTransaccion) {
        this.conceptoTransaccion = conceptoTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conceptoTransaccionProPK != null ? conceptoTransaccionProPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptoTransaccionPro)) {
            return false;
        }
        ConceptoTransaccionPro other = (ConceptoTransaccionPro) object;
        if ((this.conceptoTransaccionProPK == null && other.conceptoTransaccionProPK != null) || (this.conceptoTransaccionProPK != null && !this.conceptoTransaccionProPK.equals(other.conceptoTransaccionProPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ConceptoTransaccionPro[ conceptoTransaccionProPK=" + conceptoTransaccionProPK + " ]";
    }

    /**
     * @return the registradoPorUsuario
     */
    public Usuario getRegistradoPorUsuario() {
        return registradoPorUsuario;
    }

    /**
     * @param registradoPorUsuario the registradoPorUsuario to set
     */
    public void setRegistradoPorUsuario(Usuario registradoPorUsuario) {
        this.registradoPorUsuario = registradoPorUsuario;
    }

}
