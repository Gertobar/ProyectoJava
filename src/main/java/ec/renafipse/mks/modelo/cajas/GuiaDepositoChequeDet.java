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
@Table(name = "MKS_CAJAS.GUIA_DEPOSITO_CHEQUE_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GuiaDepositoChequeDet.findAll", query = "SELECT g FROM GuiaDepositoChequeDet g"),
    @NamedQuery(name = "GuiaDepositoChequeDet.findByCodigoMoneda", query = "SELECT g FROM GuiaDepositoChequeDet g WHERE g.guiaDepositoChequeDetPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "GuiaDepositoChequeDet.findByCodigoGuiaDeposito", query = "SELECT g FROM GuiaDepositoChequeDet g WHERE g.guiaDepositoChequeDetPK.codigoGuiaDeposito = :codigoGuiaDeposito"),
    @NamedQuery(name = "GuiaDepositoChequeDet.findByNumeroCheques", query = "SELECT g FROM GuiaDepositoChequeDet g WHERE g.numeroCheques = :numeroCheques"),
    @NamedQuery(name = "GuiaDepositoChequeDet.findByTotalCheques", query = "SELECT g FROM GuiaDepositoChequeDet g WHERE g.totalCheques = :totalCheques")})
public class GuiaDepositoChequeDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GuiaDepositoChequeDetPK guiaDepositoChequeDetPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUES")
    private long numeroCheques;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CHEQUES")
    private BigDecimal totalCheques;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guiaDepositoChequeDet", fetch = FetchType.LAZY)
    private Collection<GuiaDepositoChequeDes> guiaDepositoChequeDesCollection;
    @JoinColumn(name = "CODIGO_GUIA_DEPOSITO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GuiaDepositoCheque guiaDepositoCheque;

    public GuiaDepositoChequeDet() {
    }

    public GuiaDepositoChequeDet(GuiaDepositoChequeDetPK guiaDepositoChequeDetPK) {
        this.guiaDepositoChequeDetPK = guiaDepositoChequeDetPK;
    }

    public GuiaDepositoChequeDet(GuiaDepositoChequeDetPK guiaDepositoChequeDetPK, long numeroCheques, BigDecimal totalCheques) {
        this.guiaDepositoChequeDetPK = guiaDepositoChequeDetPK;
        this.numeroCheques = numeroCheques;
        this.totalCheques = totalCheques;
    }

    public GuiaDepositoChequeDet(long codigoMoneda, long codigoGuiaDeposito) {
        this.guiaDepositoChequeDetPK = new GuiaDepositoChequeDetPK(codigoMoneda, codigoGuiaDeposito);
    }

    public GuiaDepositoChequeDetPK getGuiaDepositoChequeDetPK() {
        return guiaDepositoChequeDetPK;
    }

    public void setGuiaDepositoChequeDetPK(GuiaDepositoChequeDetPK guiaDepositoChequeDetPK) {
        this.guiaDepositoChequeDetPK = guiaDepositoChequeDetPK;
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

    @XmlTransient
    public Collection<GuiaDepositoChequeDes> getGuiaDepositoChequeDesCollection() {
        return guiaDepositoChequeDesCollection;
    }

    public void setGuiaDepositoChequeDesCollection(Collection<GuiaDepositoChequeDes> guiaDepositoChequeDesCollection) {
        this.guiaDepositoChequeDesCollection = guiaDepositoChequeDesCollection;
    }

    public GuiaDepositoCheque getGuiaDepositoCheque() {
        return guiaDepositoCheque;
    }

    public void setGuiaDepositoCheque(GuiaDepositoCheque guiaDepositoCheque) {
        this.guiaDepositoCheque = guiaDepositoCheque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guiaDepositoChequeDetPK != null ? guiaDepositoChequeDetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GuiaDepositoChequeDet)) {
            return false;
        }
        GuiaDepositoChequeDet other = (GuiaDepositoChequeDet) object;
        if ((this.guiaDepositoChequeDetPK == null && other.guiaDepositoChequeDetPK != null) || (this.guiaDepositoChequeDetPK != null && !this.guiaDepositoChequeDetPK.equals(other.guiaDepositoChequeDetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDet[ guiaDepositoChequeDetPK=" + guiaDepositoChequeDetPK + " ]";
    }
    
}
