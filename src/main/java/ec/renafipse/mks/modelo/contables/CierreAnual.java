    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.CIERRE_ANUAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CierreAnual.findAll", query = "SELECT c FROM CierreAnual c"),
    @NamedQuery(name = "CierreAnual.findByCodigoPeriodo", query = "SELECT c FROM CierreAnual c WHERE c.cierreAnualPK.codigoPeriodo = :codigoPeriodo"),
    @NamedQuery(name = "CierreAnual.findByTotalIngresos", query = "SELECT c FROM CierreAnual c WHERE c.totalIngresos = :totalIngresos"),
    @NamedQuery(name = "CierreAnual.findByTotalEgresos", query = "SELECT c FROM CierreAnual c WHERE c.totalEgresos = :totalEgresos"),
    @NamedQuery(name = "CierreAnual.findByValorCierre", query = "SELECT c FROM CierreAnual c WHERE c.valorCierre = :valorCierre"),
    @NamedQuery(name = "CierreAnual.findByEsUtilidad", query = "SELECT c FROM CierreAnual c WHERE c.esUtilidad = :esUtilidad"),
    @NamedQuery(name = "CierreAnual.findByFechaRegistro", query = "SELECT c FROM CierreAnual c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "CierreAnual.findByRegistradoPor", query = "SELECT c FROM CierreAnual c WHERE c.registradoPor = :registradoPor"),
    @NamedQuery(name = "CierreAnual.findByCodigoIfip", query = "SELECT c FROM CierreAnual c WHERE c.cierreAnualPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "CierreAnual.findByEliminado", query = "SELECT c FROM CierreAnual c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "CierreAnual.findByActualizadoPor", query = "SELECT c FROM CierreAnual c WHERE c.actualizadoPor = :actualizadoPor"),
    @NamedQuery(name = "CierreAnual.findByFechaActualizacion", query = "SELECT c FROM CierreAnual c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CierreAnual implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CierreAnualPK cierreAnualPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_INGRESOS")
    private BigDecimal totalIngresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_EGRESOS")
    private BigDecimal totalEgresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_CIERRE")
    private BigDecimal valorCierre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_UTILIDAD")
    private char esUtilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Column(name = "ACTUALIZADO_POR")
    private Long actualizadoPor;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cierreAnual")
    private Collection<CierreAnualDetalle> cierreAnualDetalleCollection;
    @JoinColumn(name = "CODIGO_PERIODO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PeriodoContable periodoContable;
    @JoinColumn(name = "CODIGO_PERIODO_NUEVO", referencedColumnName = "CODIGO")
    @ManyToOne
    private PeriodoContable codigoPeriodoNuevo;
    @JoinColumn(name = "CODIGO_COMPROBANTE_APERTURA", referencedColumnName = "CODIGO")
    @ManyToOne
    private Comprobante codigoComprobanteApertura;
    @JoinColumn(name = "CODIGO_COMPROBANTE_CIERRE", referencedColumnName = "CODIGO")
    @ManyToOne
    private Comprobante codigoComprobanteCierre;

    public CierreAnual() {
    }

    public CierreAnual(CierreAnualPK cierreAnualPK) {
        this.cierreAnualPK = cierreAnualPK;
    }

    public CierreAnual(CierreAnualPK cierreAnualPK, BigDecimal totalIngresos, BigDecimal totalEgresos, BigDecimal valorCierre, char esUtilidad, Date fechaRegistro, long registradoPor, char eliminado) {
        this.cierreAnualPK = cierreAnualPK;
        this.totalIngresos = totalIngresos;
        this.totalEgresos = totalEgresos;
        this.valorCierre = valorCierre;
        this.esUtilidad = esUtilidad;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.eliminado = eliminado;
    }

    public CierreAnual(String codigoPeriodo, long codigoIfip) {
        this.cierreAnualPK = new CierreAnualPK(codigoPeriodo, codigoIfip);
    }

    public CierreAnualPK getCierreAnualPK() {
        return cierreAnualPK;
    }

    public void setCierreAnualPK(CierreAnualPK cierreAnualPK) {
        this.cierreAnualPK = cierreAnualPK;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public BigDecimal getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(BigDecimal totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public BigDecimal getValorCierre() {
        return valorCierre;
    }

    public void setValorCierre(BigDecimal valorCierre) {
        this.valorCierre = valorCierre;
    }

    public char getEsUtilidad() {
        return esUtilidad;
    }

    public void setEsUtilidad(char esUtilidad) {
        this.esUtilidad = esUtilidad;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Long getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(Long actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @XmlTransient
    public Collection<CierreAnualDetalle> getCierreAnualDetalleCollection() {
        return cierreAnualDetalleCollection;
    }

    public void setCierreAnualDetalleCollection(Collection<CierreAnualDetalle> cierreAnualDetalleCollection) {
        this.cierreAnualDetalleCollection = cierreAnualDetalleCollection;
    }

    public PeriodoContable getPeriodoContable() {
        return periodoContable;
    }

    public void setPeriodoContable(PeriodoContable periodoContable) {
        this.periodoContable = periodoContable;
    }

    public PeriodoContable getCodigoPeriodoNuevo() {
        return codigoPeriodoNuevo;
    }

    public void setCodigoPeriodoNuevo(PeriodoContable codigoPeriodoNuevo) {
        this.codigoPeriodoNuevo = codigoPeriodoNuevo;
    }

    public Comprobante getCodigoComprobanteApertura() {
        return codigoComprobanteApertura;
    }

    public void setCodigoComprobanteApertura(Comprobante codigoComprobanteApertura) {
        this.codigoComprobanteApertura = codigoComprobanteApertura;
    }

    public Comprobante getCodigoComprobanteCierre() {
        return codigoComprobanteCierre;
    }

    public void setCodigoComprobanteCierre(Comprobante codigoComprobanteCierre) {
        this.codigoComprobanteCierre = codigoComprobanteCierre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cierreAnualPK != null ? cierreAnualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreAnual)) {
            return false;
        }
        CierreAnual other = (CierreAnual) object;
        if ((this.cierreAnualPK == null && other.cierreAnualPK != null) || (this.cierreAnualPK != null && !this.cierreAnualPK.equals(other.cierreAnualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CierreAnual[ cierreAnualPK=" + cierreAnualPK + " ]";
    }
    
}
