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
@Table(name = "MKS_CAJAS.FONDEO_CAJA_DESGLOCE_EFECTIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findAll", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f"),
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findByCodigoFondeo", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f WHERE f.fondeoCajaDesgloceEfectivoPK.codigoFondeo = :codigoFondeo"),
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findByCodigoMoneda", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f WHERE f.fondeoCajaDesgloceEfectivoPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findByCodigoTipoFraccion", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f WHERE f.fondeoCajaDesgloceEfectivoPK.codigoTipoFraccion = :codigoTipoFraccion"),
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findByValor", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f WHERE f.valor = :valor"),
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findByCantidad", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f WHERE f.cantidad = :cantidad"),
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findByTotal", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f WHERE f.total = :total"),
    //Personalizados
    @NamedQuery(name = "FondeoCajaDesgloceEfectivo.findByCodigoFondeoCodigoMoneda", query = "SELECT f FROM FondeoCajaDesgloceEfectivo f WHERE f.fondeoCajaDesgloceEfectivoPK.codigoFondeo = :codigoFondeo AND f.fondeoCajaDesgloceEfectivoPK.codigoMoneda = :codigoMoneda")
})
public class FondeoCajaDesgloceEfectivo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoFondeoCodigoMoneda = "FondeoCajaDesgloceEfectivo.findByCodigoFondeoCodigoMoneda";
    @EmbeddedId
    protected FondeoCajaDesgloceEfectivoPK fondeoCajaDesgloceEfectivoPK;
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
        @JoinColumn(name = "CODIGO_FONDEO", referencedColumnName = "CODIGO_FONDEO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FondeoCajaDetalle fondeoCajaDetalle;

    public FondeoCajaDesgloceEfectivo() {
    }

    public FondeoCajaDesgloceEfectivo(FondeoCajaDesgloceEfectivoPK fondeoCajaDesgloceEfectivoPK) {
        this.fondeoCajaDesgloceEfectivoPK = fondeoCajaDesgloceEfectivoPK;
    }

    public FondeoCajaDesgloceEfectivo(FondeoCajaDesgloceEfectivoPK fondeoCajaDesgloceEfectivoPK, BigDecimal valor, long cantidad, BigDecimal total) {
        this.fondeoCajaDesgloceEfectivoPK = fondeoCajaDesgloceEfectivoPK;
        this.valor = valor;
        this.cantidad = cantidad;
        this.total = total;
    }

    public FondeoCajaDesgloceEfectivo(long codigoFondeo, long codigoMoneda, long codigoTipoFraccion) {
        this.fondeoCajaDesgloceEfectivoPK = new FondeoCajaDesgloceEfectivoPK(codigoFondeo, codigoMoneda, codigoTipoFraccion);
    }

    public FondeoCajaDesgloceEfectivoPK getFondeoCajaDesgloceEfectivoPK() {
        return fondeoCajaDesgloceEfectivoPK;
    }

    public void setFondeoCajaDesgloceEfectivoPK(FondeoCajaDesgloceEfectivoPK fondeoCajaDesgloceEfectivoPK) {
        this.fondeoCajaDesgloceEfectivoPK = fondeoCajaDesgloceEfectivoPK;
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

    public FondeoCajaDetalle getFondeoCajaDetalle() {
        return fondeoCajaDetalle;
    }

    public void setFondeoCajaDetalle(FondeoCajaDetalle fondeoCajaDetalle) {
        this.fondeoCajaDetalle = fondeoCajaDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fondeoCajaDesgloceEfectivoPK != null ? fondeoCajaDesgloceEfectivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FondeoCajaDesgloceEfectivo)) {
            return false;
        }
        FondeoCajaDesgloceEfectivo other = (FondeoCajaDesgloceEfectivo) object;
        if ((this.fondeoCajaDesgloceEfectivoPK == null && other.fondeoCajaDesgloceEfectivoPK != null) || (this.fondeoCajaDesgloceEfectivoPK != null && !this.fondeoCajaDesgloceEfectivoPK.equals(other.fondeoCajaDesgloceEfectivoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.FondeoCajaDesgloceEfectivo[ fondeoCajaDesgloceEfectivoPK=" + fondeoCajaDesgloceEfectivoPK + " ]";
    }
    
}
