/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author renafipse1
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PAGO_COMPRA_DETALLE_CUE_AH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCompraDetalleCueAh.findAll", query = "SELECT p FROM PagoCompraDetalleCueAh p"),
    @NamedQuery(name = "PagoCompraDetalleCueAh.findByCodigoPagoCompraDetalle", query = "SELECT p FROM PagoCompraDetalleCueAh p WHERE p.codigoPagoCompraDetalle = :codigoPagoCompraDetalle"),
    @NamedQuery(name = "PagoCompraDetalleCueAh.findByCodigoCuenta", query = "SELECT p FROM PagoCompraDetalleCueAh p WHERE p.codigoCuenta = :codigoCuenta"),
    @NamedQuery(name = "PagoCompraDetalleCueAh.findByValor", query = "SELECT p FROM PagoCompraDetalleCueAh p WHERE p.valor = :valor"),
    
    })
public class PagoCompraDetalleCueAh implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPagoCompraDetalle = "PagoCompraDetalleCueAh.findByCodigoPagoCompraDetalle";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGO_COMPRA_DETALLE")
    private Long codigoPagoCompraDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA")
    private long codigoCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
  
    
    @JoinColumn(name = "CODIGO_PAGO_COMPRA_DETALLE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PagoCompraDetalle pagoCompraDetalleCueA;


    public PagoCompraDetalleCueAh() {
    }

    public PagoCompraDetalleCueAh(Long codigoPagoCompraDetalle) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
    }

    public PagoCompraDetalleCueAh(Long codigoPagoCompraDetalle, long codigoCuenta) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
        this.codigoCuenta = codigoCuenta;
   
    }

    public Long getCodigoPagoCompraDetalle() {
        return codigoPagoCompraDetalle;
    }

    public void setCodigoPagoCompraDetalle(Long codigoPagoCompraDetalle) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
    }

    public long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPagoCompraDetalle != null ? codigoPagoCompraDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCompraDetalleCueAh)) {
            return false;
        }
        PagoCompraDetalleCueAh other = (PagoCompraDetalleCueAh) object;
        if ((this.codigoPagoCompraDetalle == null && other.codigoPagoCompraDetalle != null) || (this.codigoPagoCompraDetalle != null && !this.codigoPagoCompraDetalle.equals(other.codigoPagoCompraDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCueAh[ codigoPagoCompraDetalle=" + codigoPagoCompraDetalle + " ]";
    }

    /**
     * @return the pagoCompraDetalleCueA
     */
    public PagoCompraDetalle getPagoCompraDetalleCueA() {
        return pagoCompraDetalleCueA;
    }

    /**
     * @param pagoCompraDetalleCueA the pagoCompraDetalleCueA to set
     */
    public void setPagoCompraDetalleCueA(PagoCompraDetalle pagoCompraDetalleCueA) {
        this.pagoCompraDetalleCueA = pagoCompraDetalleCueA;
    }
    
}
