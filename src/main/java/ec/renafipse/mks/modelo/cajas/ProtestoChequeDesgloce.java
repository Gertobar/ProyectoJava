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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.PROTESTO_CHEQUE_DESGLOCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProtestoChequeDesgloce.findAll", query = "SELECT p FROM ProtestoChequeDesgloce p"),
    @NamedQuery(name = "ProtestoChequeDesgloce.findByCodigoCheque", query = "SELECT p FROM ProtestoChequeDesgloce p WHERE p.codigoCheque = :codigoCheque"),
    @NamedQuery(name = "ProtestoChequeDesgloce.findByValor", query = "SELECT p FROM ProtestoChequeDesgloce p WHERE p.valor = :valor")})
public class ProtestoChequeDesgloce implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CHEQUE")
    private Long codigoCheque;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "CODIGO_TIPO_PRO_CHE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoProtestoCheque codigoTipoProChe;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_PRO_CHE_DET", referencedColumnName = "CODIGO_PRO_CHE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProtestoChequeDetalle protestoChequeDetalle;
    @JoinColumn(name = "CODIGO_CHEQUE", referencedColumnName = "CODIGO_CHEQUE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private GuiaDepositoChequeDes guiaDepositoChequeDes;

    public ProtestoChequeDesgloce() {
    }

    public ProtestoChequeDesgloce(Long codigoCheque) {
        this.codigoCheque = codigoCheque;
    }

    public ProtestoChequeDesgloce(Long codigoCheque, BigDecimal valor) {
        this.codigoCheque = codigoCheque;
        this.valor = valor;
    }

    public Long getCodigoCheque() {
        return codigoCheque;
    }

    public void setCodigoCheque(Long codigoCheque) {
        this.codigoCheque = codigoCheque;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoProtestoCheque getCodigoTipoProChe() {
        return codigoTipoProChe;
    }

    public void setCodigoTipoProChe(TipoProtestoCheque codigoTipoProChe) {
        this.codigoTipoProChe = codigoTipoProChe;
    }

    public ProtestoChequeDetalle getProtestoChequeDetalle() {
        return protestoChequeDetalle;
    }

    public void setProtestoChequeDetalle(ProtestoChequeDetalle protestoChequeDetalle) {
        this.protestoChequeDetalle = protestoChequeDetalle;
    }

    public GuiaDepositoChequeDes getGuiaDepositoChequeDes() {
        return guiaDepositoChequeDes;
    }

    public void setGuiaDepositoChequeDes(GuiaDepositoChequeDes guiaDepositoChequeDes) {
        this.guiaDepositoChequeDes = guiaDepositoChequeDes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCheque != null ? codigoCheque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProtestoChequeDesgloce)) {
            return false;
        }
        ProtestoChequeDesgloce other = (ProtestoChequeDesgloce) object;
        if ((this.codigoCheque == null && other.codigoCheque != null) || (this.codigoCheque != null && !this.codigoCheque.equals(other.codigoCheque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.ProtestoChequeDesgloce[ codigoCheque=" + codigoCheque + " ]";
    }
    
}
