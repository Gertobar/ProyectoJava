/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.adquisiciones;

import ec.renafipse.mks.modelo.ifips.IfipCuentaEntidadFinanciera;
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
@Table(name = "MKS_ADQUISICIONES.PAGO_COMPRA_DETALLE_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCompraDetalleCheque.findAll", query = "SELECT p FROM PagoCompraDetalleCheque p"),
    @NamedQuery(name = "PagoCompraDetalleCheque.findByCodigoPagoCompraDetalle", query = "SELECT p FROM PagoCompraDetalleCheque p WHERE p.codigoPagoCompraDetalle = :codigoPagoCompraDetalle"),
    @NamedQuery(name = "PagoCompraDetalleCheque.findByCodigoCuenta", query = "SELECT p FROM PagoCompraDetalleCheque p WHERE p.codigoCuenta = :codigoCuenta"),
    @NamedQuery(name = "PagoCompraDetalleCheque.findByValor", query = "SELECT p FROM PagoCompraDetalleCheque p WHERE p.valor = :valor"),
    @NamedQuery(name = "PagoCompraDetalleCheque.findByBeneficiario", query = "SELECT p FROM PagoCompraDetalleCheque p WHERE p.beneficiario = :beneficiario"),
    @NamedQuery(name = "PagoCompraDetalleCheque.findByNumeroCheque", query = "SELECT p FROM PagoCompraDetalleCheque p WHERE p.numeroCheque = :numeroCheque"),
//    @NamedQuery(name = "PagoCompraDetalleCheque.findByCodEnTtFinNumChe", query = "SELECT p FROM PagoCompraDetalleCheque p JOIN p.ifipCuentaEntidadFinanciera i where   p.numeroCheque = :numeroCheque AND i.entidadFinanciera.codigo = :codigoEntidadFinanciera"),
    @NamedQuery(name = "PagoCompraDetalleCheque.findByCodPagCompDet", query = "SELECT p FROM PagoCompraDetalleCheque p JOIN p.pagoCompraDetalle pcd Where pcd.codigo = :codigoPagComDet")
})
public class PagoCompraDetalleCheque implements Serializable {

    private static final long serialVersionUID = 1L;
    /// listar todos los los detalles del pago de compra segun CODIGO_ENTIDAD_FINANCIERA, NUMERO_CHEQUE, NUMERO_CUENTA
    public static final String findByCodEnTtFinNumChe = "PagoCompraDetalleCheque.findByCodEnTtFinNumChe";
    public static final String findByCodigoPagoCompraDetalle = "PagoCompraDetalleCheque.findByCodigoPagoCompraDetalle";
    public static final String findByCodPagCompDet = "PagoCompraDetalleCheque.findByCodPagCompDet";  

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGO_COMPRA_DETALLE")
    private Long codigoPagoCompraDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ENT_FIN")
    private long codigoCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "BENEFICIARIO")
    private String beneficiario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUE")
    private long numeroCheque;

    @JoinColumn(name = "CODIGO_CUENTA_ENT_FIN", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera;

    @JoinColumn(name = "CODIGO_PAGO_COMPRA_DETALLE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PagoCompraDetalle pagoCompraDetalle;

    public PagoCompraDetalleCheque() {
    }

    public PagoCompraDetalleCheque(Long codigoPagoCompraDetalle) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
    }

    public PagoCompraDetalleCheque(Long codigoPagoCompraDetalle, long codigoCuenta, BigDecimal valor, String beneficiario, long numeroCheque) {
        this.codigoPagoCompraDetalle = codigoPagoCompraDetalle;
        this.codigoCuenta = codigoCuenta;
        this.valor = valor;
        this.beneficiario = beneficiario;
        this.numeroCheque = numeroCheque;
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

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public long getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(long numeroCheque) {
        this.numeroCheque = numeroCheque;
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
        if (!(object instanceof PagoCompraDetalleCheque)) {
            return false;
        }
        PagoCompraDetalleCheque other = (PagoCompraDetalleCheque) object;
        if ((this.codigoPagoCompraDetalle == null && other.codigoPagoCompraDetalle != null) || (this.codigoPagoCompraDetalle != null && !this.codigoPagoCompraDetalle.equals(other.codigoPagoCompraDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCheque[ codigoPagoCompraDetalle=" + codigoPagoCompraDetalle + " ]";
    }

    /**
     * @return the ifipCuentaEntidadFinanciera
     */
    public IfipCuentaEntidadFinanciera getIfipCuentaEntidadFinanciera() {
        return ifipCuentaEntidadFinanciera;
    }

    /**
     * @param ifipCuentaEntidadFinanciera the ifipCuentaEntidadFinanciera to set
     */
    public void setIfipCuentaEntidadFinanciera(IfipCuentaEntidadFinanciera ifipCuentaEntidadFinanciera) {
        this.ifipCuentaEntidadFinanciera = ifipCuentaEntidadFinanciera;
    }

    /**
     * @return the pagoCompraDetalle
     */
    public PagoCompraDetalle getPagoCompraDetalle() {
        return pagoCompraDetalle;
    }

    /**
     * @param pagoCompraDetalle the pagoCompraDetalle to set
     */
    public void setPagoCompraDetalle(PagoCompraDetalle pagoCompraDetalle) {
        this.pagoCompraDetalle = pagoCompraDetalle;
    }

}
