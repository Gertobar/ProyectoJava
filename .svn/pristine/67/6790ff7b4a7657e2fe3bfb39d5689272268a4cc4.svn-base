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
@Table(name = "MKS_CAJAS.EFECTIVIZACION_CHEQUE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EfectivizacionChequeDetalle.findAll", query = "SELECT e FROM EfectivizacionChequeDetalle e"),
    @NamedQuery(name = "EfectivizacionChequeDetalle.findByCodigoMoneda", query = "SELECT e FROM EfectivizacionChequeDetalle e WHERE e.efectivizacionChequeDetallePK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "EfectivizacionChequeDetalle.findByCodigoEfeChe", query = "SELECT e FROM EfectivizacionChequeDetalle e WHERE e.efectivizacionChequeDetallePK.codigoEfeChe = :codigoEfeChe"),
    @NamedQuery(name = "EfectivizacionChequeDetalle.findByNumeroCheques", query = "SELECT e FROM EfectivizacionChequeDetalle e WHERE e.numeroCheques = :numeroCheques"),
    @NamedQuery(name = "EfectivizacionChequeDetalle.findByTotalCheques", query = "SELECT e FROM EfectivizacionChequeDetalle e WHERE e.totalCheques = :totalCheques")})
public class EfectivizacionChequeDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EfectivizacionChequeDetallePK efectivizacionChequeDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUES")
    private long numeroCheques;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CHEQUES")
    private BigDecimal totalCheques;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "efectivizacionChequeDetalle", fetch = FetchType.LAZY)
    private Collection<EfectivizacionChequeDesgloce> efectivizacionChequeDesgloceCollection;
    @JoinColumn(name = "CODIGO_EFE_CHE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EfectivizacionCheque efectivizacionCheque;

    public EfectivizacionChequeDetalle() {
    }

    public EfectivizacionChequeDetalle(EfectivizacionChequeDetallePK efectivizacionChequeDetallePK) {
        this.efectivizacionChequeDetallePK = efectivizacionChequeDetallePK;
    }

    public EfectivizacionChequeDetalle(EfectivizacionChequeDetallePK efectivizacionChequeDetallePK, long numeroCheques, BigDecimal totalCheques) {
        this.efectivizacionChequeDetallePK = efectivizacionChequeDetallePK;
        this.numeroCheques = numeroCheques;
        this.totalCheques = totalCheques;
    }

    public EfectivizacionChequeDetalle(long codigoMoneda, long codigoEfeChe) {
        this.efectivizacionChequeDetallePK = new EfectivizacionChequeDetallePK(codigoMoneda, codigoEfeChe);
    }

    public EfectivizacionChequeDetallePK getEfectivizacionChequeDetallePK() {
        return efectivizacionChequeDetallePK;
    }

    public void setEfectivizacionChequeDetallePK(EfectivizacionChequeDetallePK efectivizacionChequeDetallePK) {
        this.efectivizacionChequeDetallePK = efectivizacionChequeDetallePK;
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
    public Collection<EfectivizacionChequeDesgloce> getEfectivizacionChequeDesgloceCollection() {
        return efectivizacionChequeDesgloceCollection;
    }

    public void setEfectivizacionChequeDesgloceCollection(Collection<EfectivizacionChequeDesgloce> efectivizacionChequeDesgloceCollection) {
        this.efectivizacionChequeDesgloceCollection = efectivizacionChequeDesgloceCollection;
    }

    public EfectivizacionCheque getEfectivizacionCheque() {
        return efectivizacionCheque;
    }

    public void setEfectivizacionCheque(EfectivizacionCheque efectivizacionCheque) {
        this.efectivizacionCheque = efectivizacionCheque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (efectivizacionChequeDetallePK != null ? efectivizacionChequeDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EfectivizacionChequeDetalle)) {
            return false;
        }
        EfectivizacionChequeDetalle other = (EfectivizacionChequeDetalle) object;
        if ((this.efectivizacionChequeDetallePK == null && other.efectivizacionChequeDetallePK != null) || (this.efectivizacionChequeDetallePK != null && !this.efectivizacionChequeDetallePK.equals(other.efectivizacionChequeDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.EfectivizacionChequeDetalle[ efectivizacionChequeDetallePK=" + efectivizacionChequeDetallePK + " ]";
    }
    
}
