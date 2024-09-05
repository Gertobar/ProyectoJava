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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author renafipse1
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PAGO_COMPRA_DETALLE_TRANSF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCompraDetalleTransf.findAll", query = "SELECT p FROM PagoCompraDetalleTransf p"),
    @NamedQuery(name = "PagoCompraDetalleTransf.findByCodigoPagoCompraDetalle", query = "SELECT p FROM PagoCompraDetalleTransf p WHERE p.codigoPagoCompraDetalle = :codigoPagoCompraDetalle"),
    @NamedQuery(name = "PagoCompraDetalleTransf.findByCodigoCuentaEntFin", query = "SELECT p FROM PagoCompraDetalleTransf p WHERE p.codigoCuentaEntFin = :codigoCuentaEntFin"),
    @NamedQuery(name = "PagoCompraDetalleTransf.findByValor", query = "SELECT p FROM PagoCompraDetalleTransf p WHERE p.valor = :valor"),
    @NamedQuery(name = "PagoCompraDetalleTransf.findByNumeroCuenta", query = "SELECT p FROM PagoCompraDetalleTransf p WHERE p.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "PagoCompraDetalleTransf.findByCodigoEntidadFinanciera", query = "SELECT p FROM PagoCompraDetalleTransf p WHERE p.codigoEntidadFinanciera = :codigoEntidadFinanciera")
    })
public class PagoCompraDetalleTransf implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPagoCompraDetalle = "PagoCompraDetalleTransf.findByCodigoPagoCompraDetalle";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGO_COMPRA_DETALLE")
    private Long codigoPagoCompraDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ENT_FIN")
    private long codigoCuentaEntFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private long codigoEntidadFinanciera;
 
    @JoinColumn(name = "CODIGO_PAGO_COMPRA_DETALLE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PagoCompraDetalle pagoCompraDetalleTra;
    
    public PagoCompraDetalleTransf() {
    }

    public PagoCompraDetalleTransf(Long codigoPagoCompraDetalle) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
    }

    public PagoCompraDetalleTransf(Long codigoPagoCompraDetalle, long codigoCuentaEntFin, BigDecimal valor, String numeroCuenta, long codigoEntidadFinanciera) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
        this.codigoCuentaEntFin = codigoCuentaEntFin;
        this.valor = valor;
        this.numeroCuenta = numeroCuenta;
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    
    }

    public Long getCodigoPagoCompraDetalle() {
        return codigoPagoCompraDetalle;
    }

    public void setCodigoPagoCompraDetalle(Long codigoPagoCompraDetalle) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
    }

    public long getCodigoCuentaEntFin() {
        return codigoCuentaEntFin;
    }

    public void setCodigoCuentaEntFin(long codigoCuentaEntFin) {
        this.codigoCuentaEntFin = codigoCuentaEntFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
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
        if (!(object instanceof PagoCompraDetalleTransf)) {
            return false;
        }
        PagoCompraDetalleTransf other = (PagoCompraDetalleTransf) object;
        if ((this.codigoPagoCompraDetalle == null && other.codigoPagoCompraDetalle != null) || (this.codigoPagoCompraDetalle != null && !this.codigoPagoCompraDetalle.equals(other.codigoPagoCompraDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.negocio.adquisiciones.PagoCompraDetalleTransf[ codigoPagoCompraDetalle=" + codigoPagoCompraDetalle + " ]";
    }

    /**
     * @return the pagoCompraDetalleTra
     */
    public PagoCompraDetalle getPagoCompraDetalleTra() {
        return pagoCompraDetalleTra;
    }

    /**
     * @param pagoCompraDetalleTra the pagoCompraDetalleTra to set
     */
    public void setPagoCompraDetalleTra(PagoCompraDetalle pagoCompraDetalleTra) {
        this.pagoCompraDetalleTra = pagoCompraDetalleTra;
    }
    
}
