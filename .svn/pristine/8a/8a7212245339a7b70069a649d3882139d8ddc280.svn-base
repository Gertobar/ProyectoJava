/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.modelo.comunes.Moneda;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.APERTURA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AperturaDetalle.findAll", query = "SELECT a FROM AperturaDetalle a"),
    @NamedQuery(name = "AperturaDetalle.findByCodigoApertura", query = "SELECT a FROM AperturaDetalle a WHERE a.aperturaDetallePK.codigoApertura = :codigoApertura"),
    @NamedQuery(name = "AperturaDetalle.findByCodigoMoneda", query = "SELECT a FROM AperturaDetalle a WHERE a.aperturaDetallePK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "AperturaDetalle.findByValorEfectivo", query = "SELECT a FROM AperturaDetalle a WHERE a.valorEfectivo = :valorEfectivo"),
    @NamedQuery(name = "AperturaDetalle.findByValorCheques", query = "SELECT a FROM AperturaDetalle a WHERE a.valorCheques = :valorCheques"),
    @NamedQuery(name = "AperturaDetalle.findByNumeroCheques", query = "SELECT a FROM AperturaDetalle a WHERE a.numeroCheques = :numeroCheques"),
    @NamedQuery(name = "AperturaDetalle.findByTotalApertura", query = "SELECT a FROM AperturaDetalle a WHERE a.totalApertura = :totalApertura")})
public class AperturaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoApertura = "AperturaDetalle.findByCodigoApertura";
    @EmbeddedId
    protected AperturaDetallePK aperturaDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_EFECTIVO")
    private BigDecimal valorEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_CHEQUES")
    private BigDecimal valorCheques;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUES")
    private long numeroCheques;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_APERTURA")
    private BigDecimal totalApertura;
    @JoinTable(name = "APERTURA_DESGLOCE_CHEQUE", joinColumns = {
        @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO_APERTURA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "CODIGO_CHEQUE", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToMany
    private Collection<ChequeDepositado> chequeDepositadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aperturaDetalle",fetch = FetchType.LAZY)
    private Collection<AperturaDesgloceEfectivo> aperturaDesgloceEfectivoCollection;
    @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Apertura apertura;
    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "aperturaDetalle", fetch = FetchType.LAZY)
    private CierreDetalle cierreDetalle;*/
      @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;

    public AperturaDetalle() {
    }

    public AperturaDetalle(AperturaDetallePK aperturaDetallePK) {
        this.aperturaDetallePK = aperturaDetallePK;
    }

    public AperturaDetalle(AperturaDetallePK aperturaDetallePK, BigDecimal valorEfectivo, BigDecimal valorCheques, long numeroCheques, BigDecimal totalApertura) {
        this.aperturaDetallePK = aperturaDetallePK;
        this.valorEfectivo = valorEfectivo;
        this.valorCheques = valorCheques;
        this.numeroCheques = numeroCheques;
        this.totalApertura = totalApertura;
    }

    public AperturaDetalle(long codigoApertura, long codigoMoneda) {
        this.aperturaDetallePK = new AperturaDetallePK(codigoApertura, codigoMoneda);
    }

    public AperturaDetallePK getAperturaDetallePK() {
        return aperturaDetallePK;
    }

    public void setAperturaDetallePK(AperturaDetallePK aperturaDetallePK) {
        this.aperturaDetallePK = aperturaDetallePK;
    }

    public BigDecimal getValorEfectivo() {
        return valorEfectivo;
    }

    public void setValorEfectivo(BigDecimal valorEfectivo) {
        this.valorEfectivo = valorEfectivo;
    }

    public BigDecimal getValorCheques() {
        return valorCheques;
    }

    public void setValorCheques(BigDecimal valorCheques) {
        this.valorCheques = valorCheques;
    }

    public long getNumeroCheques() {
        return numeroCheques;
    }

    public void setNumeroCheques(long numeroCheques) {
        this.numeroCheques = numeroCheques;
    }

    public BigDecimal getTotalApertura() {
        return totalApertura;
    }

    public void setTotalApertura(BigDecimal totalApertura) {
        this.totalApertura = totalApertura;
    }

    @XmlTransient
    public Collection<ChequeDepositado> getChequeDepositadoCollection() {
        return chequeDepositadoCollection;
    }

    public void setChequeDepositadoCollection(Collection<ChequeDepositado> chequeDepositadoCollection) {
        this.chequeDepositadoCollection = chequeDepositadoCollection;
    }

    @XmlTransient
    public Collection<AperturaDesgloceEfectivo> getAperturaDesgloceEfectivoCollection() {
        return aperturaDesgloceEfectivoCollection;
    }

    public void setAperturaDesgloceEfectivoCollection(Collection<AperturaDesgloceEfectivo> aperturaDesgloceEfectivoCollection) {
        this.aperturaDesgloceEfectivoCollection = aperturaDesgloceEfectivoCollection;
    }

    public Apertura getApertura() {
        return apertura;
    }

    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }

    /*public CierreDetalle getCierreDetalle() {
        return cierreDetalle;
    }

    public void setCierreDetalle(CierreDetalle cierreDetalle) {
        this.cierreDetalle = cierreDetalle;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aperturaDetallePK != null ? aperturaDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AperturaDetalle)) {
            return false;
        }
        AperturaDetalle other = (AperturaDetalle) object;
        if ((this.aperturaDetallePK == null && other.aperturaDetallePK != null) || (this.aperturaDetallePK != null && !this.aperturaDetallePK.equals(other.aperturaDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.AperturaDetalle[ aperturaDetallePK=" + aperturaDetallePK + " ]";
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    
}
