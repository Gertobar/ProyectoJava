/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.TRANSFERENCIA_CHEQUE_DEP_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferenciaChequeDepDet.findAll", query = "SELECT t FROM TransferenciaChequeDepDet t"),
    @NamedQuery(name = "TransferenciaChequeDepDet.findByCodigoMoneda", query = "SELECT t FROM TransferenciaChequeDepDet t WHERE t.transferenciaChequeDepDetPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "TransferenciaChequeDepDet.findByCodigoTransferencia", query = "SELECT t FROM TransferenciaChequeDepDet t WHERE t.transferenciaChequeDepDetPK.codigoTransferencia = :codigoTransferencia"),
    @NamedQuery(name = "TransferenciaChequeDepDet.findByNumeroCheques", query = "SELECT t FROM TransferenciaChequeDepDet t WHERE t.numeroCheques = :numeroCheques"),
    @NamedQuery(name = "TransferenciaChequeDepDet.findByTotalCheques", query = "SELECT t FROM TransferenciaChequeDepDet t WHERE t.totalCheques = :totalCheques")

})
public class TransferenciaChequeDepDet implements Serializable {
    private static final long serialVersionUID = 1L;    
    public static final String findByCodigoTransferencia="TransferenciaChequeDepDet.findByCodigoTransferencia";
            
    @EmbeddedId
    protected TransferenciaChequeDepDetPK transferenciaChequeDepDetPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUES")
    private long numeroCheques;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CHEQUES")
    private BigDecimal totalCheques;
    @JoinColumn(name = "CODIGO_TRANSFERENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TransferenciaChequeDep transferenciaChequeDep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transferenciaChequeDepDet", fetch = FetchType.LAZY)
    private Collection<TransferenciaChequeDesgloce> transferenciaChequeDesgloceCollection;

    public TransferenciaChequeDepDet() {
    }

    public TransferenciaChequeDepDet(TransferenciaChequeDepDetPK transferenciaChequeDepDetPK) {
        this.transferenciaChequeDepDetPK = transferenciaChequeDepDetPK;
    }

    public TransferenciaChequeDepDet(TransferenciaChequeDepDetPK transferenciaChequeDepDetPK, long numeroCheques, BigDecimal totalCheques) {
        this.transferenciaChequeDepDetPK = transferenciaChequeDepDetPK;
        this.numeroCheques = numeroCheques;
        this.totalCheques = totalCheques;
    }

    public TransferenciaChequeDepDet(long codigoMoneda, long codigoTransferencia) {
        this.transferenciaChequeDepDetPK = new TransferenciaChequeDepDetPK(codigoMoneda, codigoTransferencia);
    }

    public TransferenciaChequeDepDetPK getTransferenciaChequeDepDetPK() {
        return transferenciaChequeDepDetPK;
    }

    public void setTransferenciaChequeDepDetPK(TransferenciaChequeDepDetPK transferenciaChequeDepDetPK) {
        this.transferenciaChequeDepDetPK = transferenciaChequeDepDetPK;
    }

    public long getNumeroCheques() {
        return numeroCheques;
    }

    public void setNumeroCheques(long numeroCheques) {
        this.numeroCheques = numeroCheques;
    }

    public BigDecimal getTotalCheques() {
        return totalCheques;
    }

    public void setTotalCheques(BigDecimal totalCheques) {
        this.totalCheques = totalCheques;
    }

    public TransferenciaChequeDep getTransferenciaChequeDep() {
        return transferenciaChequeDep;
    }

    public void setTransferenciaChequeDep(TransferenciaChequeDep transferenciaChequeDep) {
        this.transferenciaChequeDep = transferenciaChequeDep;
    }

    @XmlTransient
    public Collection<TransferenciaChequeDesgloce> getTransferenciaChequeDesgloceCollection() {
        return transferenciaChequeDesgloceCollection;
    }

    public void setTransferenciaChequeDesgloceCollection(Collection<TransferenciaChequeDesgloce> transferenciaChequeDesgloceCollection) {
        this.transferenciaChequeDesgloceCollection = transferenciaChequeDesgloceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transferenciaChequeDepDetPK != null ? transferenciaChequeDepDetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferenciaChequeDepDet)) {
            return false;
        }
        TransferenciaChequeDepDet other = (TransferenciaChequeDepDet) object;
        if ((this.transferenciaChequeDepDetPK == null && other.transferenciaChequeDepDetPK != null) || (this.transferenciaChequeDepDetPK != null && !this.transferenciaChequeDepDetPK.equals(other.transferenciaChequeDepDetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDet[ transferenciaChequeDepDetPK=" + transferenciaChequeDepDetPK + " ]";
    }
    
}
