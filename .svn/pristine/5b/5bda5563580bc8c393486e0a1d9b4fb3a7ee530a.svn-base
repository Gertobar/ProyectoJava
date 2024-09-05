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
@Table(name = "MKS_CAJAS.PROTESTO_CHEQUE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProtestoChequeDetalle.findAll", query = "SELECT p FROM ProtestoChequeDetalle p"),
    @NamedQuery(name = "ProtestoChequeDetalle.findByCodigoMoneda", query = "SELECT p FROM ProtestoChequeDetalle p WHERE p.protestoChequeDetallePK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ProtestoChequeDetalle.findByCodigoProChe", query = "SELECT p FROM ProtestoChequeDetalle p WHERE p.protestoChequeDetallePK.codigoProChe = :codigoProChe"),
    @NamedQuery(name = "ProtestoChequeDetalle.findByNumeroCheques", query = "SELECT p FROM ProtestoChequeDetalle p WHERE p.numeroCheques = :numeroCheques"),
    @NamedQuery(name = "ProtestoChequeDetalle.findByTotalCheques", query = "SELECT p FROM ProtestoChequeDetalle p WHERE p.totalCheques = :totalCheques"),
    @NamedQuery(name = "ProtestoChequeDetalle.findByTotalProtesto", query = "SELECT p FROM ProtestoChequeDetalle p WHERE p.totalProtesto = :totalProtesto")})
public class ProtestoChequeDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProtestoChequeDetallePK protestoChequeDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUES")
    private long numeroCheques;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CHEQUES")
    private BigDecimal totalCheques;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PROTESTO")
    private BigDecimal totalProtesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protestoChequeDetalle", fetch = FetchType.LAZY)
    private Collection<ProtestoChequeDesgloce> protestoChequeDesgloceCollection;
    @JoinColumn(name = "CODIGO_PRO_CHE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProtestoCheque protestoCheque;

    public ProtestoChequeDetalle() {
    }

    public ProtestoChequeDetalle(ProtestoChequeDetallePK protestoChequeDetallePK) {
        this.protestoChequeDetallePK = protestoChequeDetallePK;
    }

    public ProtestoChequeDetalle(ProtestoChequeDetallePK protestoChequeDetallePK, long numeroCheques, BigDecimal totalCheques, BigDecimal totalProtesto) {
        this.protestoChequeDetallePK = protestoChequeDetallePK;
        this.numeroCheques = numeroCheques;
        this.totalCheques = totalCheques;
        this.totalProtesto = totalProtesto;
    }

    public ProtestoChequeDetalle(long codigoMoneda, long codigoProChe) {
        this.protestoChequeDetallePK = new ProtestoChequeDetallePK(codigoMoneda, codigoProChe);
    }

    public ProtestoChequeDetallePK getProtestoChequeDetallePK() {
        return protestoChequeDetallePK;
    }

    public void setProtestoChequeDetallePK(ProtestoChequeDetallePK protestoChequeDetallePK) {
        this.protestoChequeDetallePK = protestoChequeDetallePK;
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

    public BigDecimal getTotalProtesto() {
        return totalProtesto;
    }

    public void setTotalProtesto(BigDecimal totalProtesto) {
        this.totalProtesto = totalProtesto;
    }

    @XmlTransient
    public Collection<ProtestoChequeDesgloce> getProtestoChequeDesgloceCollection() {
        return protestoChequeDesgloceCollection;
    }

    public void setProtestoChequeDesgloceCollection(Collection<ProtestoChequeDesgloce> protestoChequeDesgloceCollection) {
        this.protestoChequeDesgloceCollection = protestoChequeDesgloceCollection;
    }

    public ProtestoCheque getProtestoCheque() {
        return protestoCheque;
    }

    public void setProtestoCheque(ProtestoCheque protestoCheque) {
        this.protestoCheque = protestoCheque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (protestoChequeDetallePK != null ? protestoChequeDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProtestoChequeDetalle)) {
            return false;
        }
        ProtestoChequeDetalle other = (ProtestoChequeDetalle) object;
        if ((this.protestoChequeDetallePK == null && other.protestoChequeDetallePK != null) || (this.protestoChequeDetallePK != null && !this.protestoChequeDetallePK.equals(other.protestoChequeDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.ProtestoChequeDetalle[ protestoChequeDetallePK=" + protestoChequeDetallePK + " ]";
    }
    
}
