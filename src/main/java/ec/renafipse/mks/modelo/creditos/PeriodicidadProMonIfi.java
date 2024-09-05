/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
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
import javax.persistence.OneToOne;
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
@Table(name = "MKS_CREDITOS.PERIODICIDAD_PRO_MON_IFI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodicidadProMonIfi.findAll", query = "SELECT p FROM PeriodicidadProMonIfi p"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByCodigoProducto", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.periodicidadProMonIfiPK.codigoProducto = :codigoProducto"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByCodigoMoneda", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.periodicidadProMonIfiPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByCodigoIfip", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.periodicidadProMonIfiPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByCodigoPeriodicidad", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.periodicidadProMonIfiPK.codigoPeriodicidad = :codigoPeriodicidad"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByRegistradoPor", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByCuotasMaximas", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.cuotasMaximas = :cuotasMaximas"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByFechaRegistro", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByEliminado", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE p.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "PeriodicidadProMonIfi.findByPeriodicidadProMonIfiPK", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE P.periodicidadProMonIfiPK.codigoProducto = :codigoProducto AND p.periodicidadProMonIfiPK.codigoMoneda = :codigoMoneda AND p.periodicidadProMonIfiPK.codigoIfip = :codigoIfip AND p.periodicidadProMonIfiPK.codigoPeriodicidad = :codigoPeriodicidad"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByProMonIfi", query = "SELECT p.periodicidad FROM PeriodicidadProMonIfi p WHERE P.periodicidadProMonIfiPK.codigoProducto = :codigoProducto AND p.periodicidadProMonIfiPK.codigoMoneda = :codigoMoneda AND p.periodicidadProMonIfiPK.codigoIfip = :codigoIfip AND p.periodicidad.esParaCredito = :esParaCredito AND p.eliminado = :eliminado"),
    @NamedQuery(name = "PeriodicidadProMonIfi.findByCuotasMaximasPeriodicidad", query = "SELECT p FROM PeriodicidadProMonIfi p WHERE P.periodicidadProMonIfiPK.codigoProducto = :codigoProducto AND p.periodicidadProMonIfiPK.codigoMoneda = :codigoMoneda AND p.periodicidadProMonIfiPK.codigoIfip = :codigoIfip AND p.periodicidad.codigo = :codigoPeriodicidad AND p.eliminado = :eliminado")
})
public class PeriodicidadProMonIfi implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByPeriodicidadProMonIfiPK = "PeriodicidadProMonIfi.findByPeriodicidadProMonIfiPK";
    public static final String findByProMonIfi = "PeriodicidadProMonIfi.findByProMonIfi";
    public static final String findByCuotasMaximasPeriodicidad = "PeriodicidadProMonIfi.findByCuotasMaximasPeriodicidad";
    @EmbeddedId
    protected PeriodicidadProMonIfiPK periodicidadProMonIfiPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUOTAS_MAXIMAS")
    private long cuotasMaximas;
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
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodicidadProMonIfi", fetch = FetchType.LAZY)
    private Collection<Solicitud> solicitudCollection;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuarioRegistradoPor;
    @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodicidad periodicidad;

    public PeriodicidadProMonIfi() {
    }

    public PeriodicidadProMonIfi(PeriodicidadProMonIfiPK periodicidadProMonIfiPK) {
        this.periodicidadProMonIfiPK = periodicidadProMonIfiPK;
    }

    public PeriodicidadProMonIfi(PeriodicidadProMonIfiPK periodicidadProMonIfiPK, long registradoPor, long cuotasMaximas, Date fechaRegistro, char eliminado) {
        this.periodicidadProMonIfiPK = periodicidadProMonIfiPK;
        this.registradoPor = registradoPor;
        this.cuotasMaximas = cuotasMaximas;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public PeriodicidadProMonIfi(long codigoProducto, long codigoMoneda, long codigoIfip, long codigoPeriodicidad) {
        this.periodicidadProMonIfiPK = new PeriodicidadProMonIfiPK(codigoProducto, codigoMoneda, codigoIfip, codigoPeriodicidad);
    }

    public PeriodicidadProMonIfiPK getPeriodicidadProMonIfiPK() {
        return periodicidadProMonIfiPK;
    }

    public void setPeriodicidadProMonIfiPK(PeriodicidadProMonIfiPK periodicidadProMonIfiPK) {
        this.periodicidadProMonIfiPK = periodicidadProMonIfiPK;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public long getCuotasMaximas() {
        return cuotasMaximas;
    }

    public void setCuotasMaximas(long cuotasMaximas) {
        this.cuotasMaximas = cuotasMaximas;
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

    public ProductoCreditoMonedaIfip getProductoCreditoMonedaIfip() {
        return productoCreditoMonedaIfip;
    }

    public void setProductoCreditoMonedaIfip(ProductoCreditoMonedaIfip productoCreditoMonedaIfip) {
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodicidadProMonIfiPK != null ? periodicidadProMonIfiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodicidadProMonIfi)) {
            return false;
        }
        PeriodicidadProMonIfi other = (PeriodicidadProMonIfi) object;
        if ((this.periodicidadProMonIfiPK == null && other.periodicidadProMonIfiPK != null) || (this.periodicidadProMonIfiPK != null && !this.periodicidadProMonIfiPK.equals(other.periodicidadProMonIfiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfi[ periodicidadProMonIfiPK=" + periodicidadProMonIfiPK + " ]";
    }

    /**
     * @return the usuarioRegistradoPor
     */
    public Usuario getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Usuario usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }

    /**
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }
    
}
