/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.adquisiciones.CompraDetalle;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.RETENCION_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RetencionDetalle.findAll", query = "SELECT r FROM RetencionDetalle r"),
    @NamedQuery(name = "RetencionDetalle.findByCodigoRetencion", query = "SELECT r FROM RetencionDetalle r WHERE r.retencionDetallePK.codigoRetencion = :codigoRetencion"),
    @NamedQuery(name = "RetencionDetalle.findByCodigoTipoRetencion", query = "SELECT r FROM RetencionDetalle r WHERE r.retencionDetallePK.codigoTipoRetencion = :codigoTipoRetencion"),
    @NamedQuery(name = "RetencionDetalle.findByCodigoCompraDetalle", query = "SELECT r FROM RetencionDetalle r WHERE r.retencionDetallePK.codigoCompraDetalle = :codigoCompraDetalle"),
    @NamedQuery(name = "RetencionDetalle.findByBaseImponible", query = "SELECT r FROM RetencionDetalle r WHERE r.baseImponible = :baseImponible"),
    @NamedQuery(name = "RetencionDetalle.findByPorcentaje", query = "SELECT r FROM RetencionDetalle r WHERE r.porcentaje = :porcentaje"),
    @NamedQuery(name = "RetencionDetalle.findByTotal", query = "SELECT r FROM RetencionDetalle r WHERE r.total = :total")})
public class RetencionDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoRetencion="RetencionDetalle.findByCodigoRetencion";
    
    
    @EmbeddedId
    protected RetencionDetallePK retencionDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BASE_IMPONIBLE")
    private BigDecimal baseImponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @JoinColumn(name = "CODIGO_TIPO_RETENCION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoRetencion tipoRetencion;
    @JoinColumn(name = "CODIGO_RETENCION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Retencion retencion;
    
    @JoinColumn(name = "CODIGO_COMPRA_DETALLE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CompraDetalle compraDetalle;

    public RetencionDetalle() {
    }

    public RetencionDetalle(RetencionDetallePK retencionDetallePK) {
        this.retencionDetallePK = retencionDetallePK;
    }

    public RetencionDetalle(RetencionDetallePK retencionDetallePK, BigDecimal baseImponible, BigDecimal porcentaje, BigDecimal total) {
        this.retencionDetallePK = retencionDetallePK;
        this.baseImponible = baseImponible;
        this.porcentaje = porcentaje;
        this.total = total;
    }

    public RetencionDetalle(long codigoRetencion, long codigoTipoRetencion, long codigoCompraDetalle) {
        this.retencionDetallePK = new RetencionDetallePK(codigoRetencion, codigoTipoRetencion, codigoCompraDetalle);
    }

    public RetencionDetallePK getRetencionDetallePK() {
        return retencionDetallePK;
    }

    public void setRetencionDetallePK(RetencionDetallePK retencionDetallePK) {
        this.retencionDetallePK = retencionDetallePK;
    }

    public BigDecimal getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(BigDecimal baseImponible) {
        this.baseImponible = baseImponible;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

 

    public TipoRetencion getTipoRetencion() {
        return tipoRetencion;
    }

    public void setTipoRetencion(TipoRetencion tipoRetencion) {
        this.tipoRetencion = tipoRetencion;
    }

    public Retencion getRetencion() {
        return retencion;
    }

    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retencionDetallePK != null ? retencionDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetencionDetalle)) {
            return false;
        }
        RetencionDetalle other = (RetencionDetalle) object;
        if ((this.retencionDetallePK == null && other.retencionDetallePK != null) || (this.retencionDetallePK != null && !this.retencionDetallePK.equals(other.retencionDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.RetencionDetalle[ retencionDetallePK=" + retencionDetallePK + " ]";
    }

    /**
     * @return the compraDetalle
     */
    public CompraDetalle getCompraDetalle() {
        return compraDetalle;
    }

    /**
     * @param compraDetalle the compraDetalle to set
     */
    public void setCompraDetalle(CompraDetalle compraDetalle) {
        this.compraDetalle = compraDetalle;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
