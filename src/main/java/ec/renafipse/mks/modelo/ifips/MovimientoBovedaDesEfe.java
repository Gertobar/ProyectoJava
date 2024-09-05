/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import ec.renafipse.mks.modelo.cajas.FraccionMoneda;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Viajes2
 */
@Entity
@Table(name = "MOVIMIENTO_BOVEDA_DES_EFE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoBovedaDesEfe.findAll", query = "SELECT m FROM MovimientoBovedaDesEfe m"),
    @NamedQuery(name = "MovimientoBovedaDesEfe.findByCodigoTipoFraccion", query = "SELECT m FROM MovimientoBovedaDesEfe m WHERE m.movimientoBovedaDesEfePK.codigoTipoFraccion = :codigoTipoFraccion"),
    @NamedQuery(name = "MovimientoBovedaDesEfe.findByCodigoMoneda", query = "SELECT m FROM MovimientoBovedaDesEfe m WHERE m.movimientoBovedaDesEfePK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "MovimientoBovedaDesEfe.findByCodigoMovimientoBoveda", query = "SELECT m FROM MovimientoBovedaDesEfe m WHERE m.movimientoBovedaDesEfePK.codigoMovimientoBoveda = :codigoMovimientoBoveda"),
    @NamedQuery(name = "MovimientoBovedaDesEfe.findByCantidad", query = "SELECT m FROM MovimientoBovedaDesEfe m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MovimientoBovedaDesEfe.findByValor", query = "SELECT m FROM MovimientoBovedaDesEfe m WHERE m.valor = :valor"),
    @NamedQuery(name = "MovimientoBovedaDesEfe.findByTotal", query = "SELECT m FROM MovimientoBovedaDesEfe m WHERE m.total = :total")})
public class MovimientoBovedaDesEfe implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovimientoBovedaDesEfePK movimientoBovedaDesEfePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private long cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @JoinColumn(name = "CODIGO_MOVIMIENTO_BOVEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MovimientoBoveda movimientoBoveda;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_FRACCION", referencedColumnName = "CODIGO_TIPO_FRACCION", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FraccionMoneda fraccionMoneda;
    
    public MovimientoBovedaDesEfe() {
    }

    public MovimientoBovedaDesEfe(MovimientoBovedaDesEfePK movimientoBovedaDesEfePK) {
        this.movimientoBovedaDesEfePK = movimientoBovedaDesEfePK;
    }

    public MovimientoBovedaDesEfe(MovimientoBovedaDesEfePK movimientoBovedaDesEfePK, long cantidad, BigDecimal valor, BigDecimal total) {
        this.movimientoBovedaDesEfePK = movimientoBovedaDesEfePK;
        this.cantidad = cantidad;
        this.valor = valor;
        this.total = total;
    }

    public MovimientoBovedaDesEfe(long codigoTipoFraccion, long codigoMoneda, long codigoMovimientoBoveda) {
        this.movimientoBovedaDesEfePK = new MovimientoBovedaDesEfePK(codigoTipoFraccion, codigoMoneda, codigoMovimientoBoveda);
    }

    public MovimientoBovedaDesEfePK getMovimientoBovedaDesEfePK() {
        return movimientoBovedaDesEfePK;
    }

    public void setMovimientoBovedaDesEfePK(MovimientoBovedaDesEfePK movimientoBovedaDesEfePK) {
        this.movimientoBovedaDesEfePK = movimientoBovedaDesEfePK;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public MovimientoBoveda getMovimientoBoveda() {
        return movimientoBoveda;
    }

    public void setMovimientoBoveda(MovimientoBoveda movimientoBoveda) {
        this.movimientoBoveda = movimientoBoveda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimientoBovedaDesEfePK != null ? movimientoBovedaDesEfePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoBovedaDesEfe)) {
            return false;
        }
        MovimientoBovedaDesEfe other = (MovimientoBovedaDesEfe) object;
        if ((this.movimientoBovedaDesEfePK == null && other.movimientoBovedaDesEfePK != null) || (this.movimientoBovedaDesEfePK != null && !this.movimientoBovedaDesEfePK.equals(other.movimientoBovedaDesEfePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfe[ movimientoBovedaDesEfePK=" + movimientoBovedaDesEfePK + " ]";
    }

    /**
     * @return the fraccionMoneda
     */
    public FraccionMoneda getFraccionMoneda() {
        return fraccionMoneda;
    }

    /**
     * @param fraccionMoneda the fraccionMoneda to set
     */
    public void setFraccionMoneda(FraccionMoneda fraccionMoneda) {
        this.fraccionMoneda = fraccionMoneda;
    }
    
}
