/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.COMPROBANTE_DESGLOCE_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComprobanteDesgloceCheque.findAll", query = "SELECT c FROM ComprobanteDesgloceCheque c"),
    @NamedQuery(name = "ComprobanteDesgloceCheque.findByCodigoComprobante", query = "SELECT c FROM ComprobanteDesgloceCheque c WHERE c.codigoComprobante = :codigoComprobante"),
    @NamedQuery(name = "ComprobanteDesgloceCheque.findByCodigoEntidadFinanciera", query = "SELECT c FROM ComprobanteDesgloceCheque c WHERE c.codigoEntidadFinanciera = :codigoEntidadFinanciera"),
    @NamedQuery(name = "ComprobanteDesgloceCheque.findByCodigoIfipAgencia", query = "SELECT c FROM ComprobanteDesgloceCheque c WHERE c.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "ComprobanteDesgloceCheque.findByNumeroCuenta", query = "SELECT c FROM ComprobanteDesgloceCheque c WHERE c.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "ComprobanteDesgloceCheque.findByNumeroCheque", query = "SELECT c FROM ComprobanteDesgloceCheque c WHERE c.numeroCheque = :numeroCheque"),
    @NamedQuery(name = "ComprobanteDesgloceCheque.findByBeneficiario", query = "SELECT c FROM ComprobanteDesgloceCheque c WHERE c.beneficiario = :beneficiario"),
    @NamedQuery(name = "ComprobanteDesgloceCheque.findByValor", query = "SELECT c FROM ComprobanteDesgloceCheque c WHERE c.valor = :valor")})
public class ComprobanteDesgloceCheque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPROBANTE")
    private Long codigoComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENTIDAD_FINANCIERA")
    private long codigoEntidadFinanciera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_CHEQUE")
    private String numeroCheque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BENEFICIARIO")
    private String beneficiario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "CODIGO_COMPROBANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Comprobante comprobante;

    public ComprobanteDesgloceCheque() {
    }

    public ComprobanteDesgloceCheque(Long codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public ComprobanteDesgloceCheque(Long codigoComprobante, long codigoEntidadFinanciera, long codigoIfipAgencia, String numeroCuenta, String numeroCheque, String beneficiario, BigDecimal valor) {
        this.codigoComprobante = codigoComprobante;
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.numeroCuenta = numeroCuenta;
        this.numeroCheque = numeroCheque;
        this.beneficiario = beneficiario;
        this.valor = valor;
    }

    public Long getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(Long codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public long getCodigoEntidadFinanciera() {
        return codigoEntidadFinanciera;
    }

    public void setCodigoEntidadFinanciera(long codigoEntidadFinanciera) {
        this.codigoEntidadFinanciera = codigoEntidadFinanciera;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoComprobante != null ? codigoComprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComprobanteDesgloceCheque)) {
            return false;
        }
        ComprobanteDesgloceCheque other = (ComprobanteDesgloceCheque) object;
        if ((this.codigoComprobante == null && other.codigoComprobante != null) || (this.codigoComprobante != null && !this.codigoComprobante.equals(other.codigoComprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ComprobanteDesgloceCheque[ codigoComprobante=" + codigoComprobante + " ]";
    }
    
}
