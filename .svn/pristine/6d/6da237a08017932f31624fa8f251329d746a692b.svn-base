/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.APERTURA_DESGLOCE_EFECTIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AperturaDesgloceEfectivo.findAll", query = "SELECT a FROM AperturaDesgloceEfectivo a"),
    @NamedQuery(name = "AperturaDesgloceEfectivo.findByCodigoApertura", query = "SELECT a FROM AperturaDesgloceEfectivo a WHERE a.aperturaDesgloceEfectivoPK.codigoApertura = :codigoApertura"),
    @NamedQuery(name = "AperturaDesgloceEfectivo.findByCodigoMoneda", query = "SELECT a FROM AperturaDesgloceEfectivo a WHERE a.aperturaDesgloceEfectivoPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "AperturaDesgloceEfectivo.findByCodigoTipoFraccion", query = "SELECT a FROM AperturaDesgloceEfectivo a WHERE a.aperturaDesgloceEfectivoPK.codigoTipoFraccion = :codigoTipoFraccion"),
    @NamedQuery(name = "AperturaDesgloceEfectivo.findByValor", query = "SELECT a FROM AperturaDesgloceEfectivo a WHERE a.valor = :valor"),
    @NamedQuery(name = "AperturaDesgloceEfectivo.findByCantidad", query = "SELECT a FROM AperturaDesgloceEfectivo a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "AperturaDesgloceEfectivo.findByTotal", query = "SELECT a FROM AperturaDesgloceEfectivo a WHERE a.total = :total")})
public class AperturaDesgloceEfectivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AperturaDesgloceEfectivoPK aperturaDesgloceEfectivoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private long cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_FRACCION", referencedColumnName = "CODIGO_TIPO_FRACCION", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FraccionMoneda fraccionMoneda;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO_APERTURA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private AperturaDetalle aperturaDetalle;

    public AperturaDesgloceEfectivo() {
    }

    public AperturaDesgloceEfectivo(AperturaDesgloceEfectivoPK aperturaDesgloceEfectivoPK) {
        this.aperturaDesgloceEfectivoPK = aperturaDesgloceEfectivoPK;
    }

    public AperturaDesgloceEfectivo(AperturaDesgloceEfectivoPK aperturaDesgloceEfectivoPK, BigDecimal valor, long cantidad, BigDecimal total) {
        this.aperturaDesgloceEfectivoPK = aperturaDesgloceEfectivoPK;
        this.valor = valor;
        this.cantidad = cantidad;
        this.total = total;
    }

    public AperturaDesgloceEfectivo(long codigoApertura, long codigoMoneda, long codigoTipoFraccion) {
        this.aperturaDesgloceEfectivoPK = new AperturaDesgloceEfectivoPK(codigoApertura, codigoMoneda, codigoTipoFraccion);
    }

    public AperturaDesgloceEfectivoPK getAperturaDesgloceEfectivoPK() {
        return aperturaDesgloceEfectivoPK;
    }

    public void setAperturaDesgloceEfectivoPK(AperturaDesgloceEfectivoPK aperturaDesgloceEfectivoPK) {
        this.aperturaDesgloceEfectivoPK = aperturaDesgloceEfectivoPK;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public FraccionMoneda getFraccionMoneda() {
        return fraccionMoneda;
    }

    public void setFraccionMoneda(FraccionMoneda fraccionMoneda) {
        this.fraccionMoneda = fraccionMoneda;
    }

    public AperturaDetalle getAperturaDetalle() {
        return aperturaDetalle;
    }

    public void setAperturaDetalle(AperturaDetalle aperturaDetalle) {
        this.aperturaDetalle = aperturaDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aperturaDesgloceEfectivoPK != null ? aperturaDesgloceEfectivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AperturaDesgloceEfectivo)) {
            return false;
        }
        AperturaDesgloceEfectivo other = (AperturaDesgloceEfectivo) object;
        if ((this.aperturaDesgloceEfectivoPK == null && other.aperturaDesgloceEfectivoPK != null) || (this.aperturaDesgloceEfectivoPK != null && !this.aperturaDesgloceEfectivoPK.equals(other.aperturaDesgloceEfectivoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.AperturaDesgloceEfectivo[ aperturaDesgloceEfectivoPK=" + aperturaDesgloceEfectivoPK + " ]";
    }
    
}
