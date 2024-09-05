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
@Table(name = "MKS_CAJAS.CIERRE_DESGLOCE_EFECTIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CierreDesgloceEfectivo.findAll", query = "SELECT c FROM CierreDesgloceEfectivo c"),
    @NamedQuery(name = "CierreDesgloceEfectivo.findByCodigoApertura", query = "SELECT c FROM CierreDesgloceEfectivo c WHERE c.cierreDesgloceEfectivoPK.codigoApertura = :codigoApertura"),
    @NamedQuery(name = "CierreDesgloceEfectivo.findByCodigoMoneda", query = "SELECT c FROM CierreDesgloceEfectivo c WHERE c.cierreDesgloceEfectivoPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "CierreDesgloceEfectivo.findByCodigoTipoFraccion", query = "SELECT c FROM CierreDesgloceEfectivo c WHERE c.cierreDesgloceEfectivoPK.codigoTipoFraccion = :codigoTipoFraccion"),
    @NamedQuery(name = "CierreDesgloceEfectivo.findByValor", query = "SELECT c FROM CierreDesgloceEfectivo c WHERE c.valor = :valor"),
    @NamedQuery(name = "CierreDesgloceEfectivo.findByCantidad", query = "SELECT c FROM CierreDesgloceEfectivo c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "CierreDesgloceEfectivo.findByTotal", query = "SELECT c FROM CierreDesgloceEfectivo c WHERE c.total = :total")})
public class CierreDesgloceEfectivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CierreDesgloceEfectivoPK cierreDesgloceEfectivoPK;
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
    private CierreDetalle cierreDetalle;

    public CierreDesgloceEfectivo() {
    }

    public CierreDesgloceEfectivo(CierreDesgloceEfectivoPK cierreDesgloceEfectivoPK) {
        this.cierreDesgloceEfectivoPK = cierreDesgloceEfectivoPK;
    }

    public CierreDesgloceEfectivo(CierreDesgloceEfectivoPK cierreDesgloceEfectivoPK, BigDecimal valor, long cantidad, BigDecimal total) {
        this.cierreDesgloceEfectivoPK = cierreDesgloceEfectivoPK;
        this.valor = valor;
        this.cantidad = cantidad;
        this.total = total;
    }

    public CierreDesgloceEfectivo(long codigoApertura, long codigoMoneda, long codigoTipoFraccion) {
        this.cierreDesgloceEfectivoPK = new CierreDesgloceEfectivoPK(codigoApertura, codigoMoneda, codigoTipoFraccion);
    }

    public CierreDesgloceEfectivoPK getCierreDesgloceEfectivoPK() {
        return cierreDesgloceEfectivoPK;
    }

    public void setCierreDesgloceEfectivoPK(CierreDesgloceEfectivoPK cierreDesgloceEfectivoPK) {
        this.cierreDesgloceEfectivoPK = cierreDesgloceEfectivoPK;
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

    public CierreDetalle getCierreDetalle() {
        return cierreDetalle;
    }

    public void setCierreDetalle(CierreDetalle cierreDetalle) {
        this.cierreDetalle = cierreDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cierreDesgloceEfectivoPK != null ? cierreDesgloceEfectivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreDesgloceEfectivo)) {
            return false;
        }
        CierreDesgloceEfectivo other = (CierreDesgloceEfectivo) object;
        if ((this.cierreDesgloceEfectivoPK == null && other.cierreDesgloceEfectivoPK != null) || (this.cierreDesgloceEfectivoPK != null && !this.cierreDesgloceEfectivoPK.equals(other.cierreDesgloceEfectivoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.CierreDesgloceEfectivo[ cierreDesgloceEfectivoPK=" + cierreDesgloceEfectivoPK + " ]";
    }
    
}
