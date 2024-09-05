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
import javax.persistence.JoinColumns;
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
@Table(name = "MKS_CAJAS.CIERRE_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CierreDetalle.findAll", query = "SELECT c FROM CierreDetalle c"),
    @NamedQuery(name = "CierreDetalle.findByCodigoApertura", query = "SELECT c FROM CierreDetalle c WHERE c.cierreDetallePK.codigoApertura = :codigoApertura"),
    @NamedQuery(name = "CierreDetalle.findByCodigoMoneda", query = "SELECT c FROM CierreDetalle c WHERE c.cierreDetallePK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "CierreDetalle.findByValorEfectivo", query = "SELECT c FROM CierreDetalle c WHERE c.valorEfectivo = :valorEfectivo"),
    @NamedQuery(name = "CierreDetalle.findByValorCheques", query = "SELECT c FROM CierreDetalle c WHERE c.valorCheques = :valorCheques"),
    @NamedQuery(name = "CierreDetalle.findByTipoDiferencia", query = "SELECT c FROM CierreDetalle c WHERE c.tipoDiferencia = :tipoDiferencia"),
    @NamedQuery(name = "CierreDetalle.findByValorDiferencia", query = "SELECT c FROM CierreDetalle c WHERE c.valorDiferencia = :valorDiferencia")})
public class CierreDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CierreDetallePK cierreDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_EFECTIVO")
    private BigDecimal valorEfectivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_CHEQUES")
    private BigDecimal valorCheques;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_DIFERENCIA")
    private char tipoDiferencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_DIFERENCIA")
    private BigDecimal valorDiferencia;
    @ManyToMany(mappedBy = "cierreDetalleCollection", fetch = FetchType.LAZY)
    private Collection<ChequeDepositado> chequeDepositadoCollection;
    @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO_APERTURA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cierre cierre;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO_APERTURA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private AperturaDetalle aperturaDetalle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cierreDetalle", fetch = FetchType.LAZY)
    private Collection<CierreDesgloceEfectivo> cierreDesgloceEfectivoCollection;

    public CierreDetalle() {
    }

    public CierreDetalle(CierreDetallePK cierreDetallePK) {
        this.cierreDetallePK = cierreDetallePK;
    }

    public CierreDetalle(CierreDetallePK cierreDetallePK, BigDecimal valorEfectivo, BigDecimal valorCheques, char tipoDiferencia, BigDecimal valorDiferencia) {
        this.cierreDetallePK = cierreDetallePK;
        this.valorEfectivo = valorEfectivo;
        this.valorCheques = valorCheques;
        this.tipoDiferencia = tipoDiferencia;
        this.valorDiferencia = valorDiferencia;
    }

    public CierreDetalle(long codigoApertura, long codigoMoneda) {
        this.cierreDetallePK = new CierreDetallePK(codigoApertura, codigoMoneda);
    }

    public CierreDetallePK getCierreDetallePK() {
        return cierreDetallePK;
    }

    public void setCierreDetallePK(CierreDetallePK cierreDetallePK) {
        this.cierreDetallePK = cierreDetallePK;
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

    public char getTipoDiferencia() {
        return tipoDiferencia;
    }

    public void setTipoDiferencia(char tipoDiferencia) {
        this.tipoDiferencia = tipoDiferencia;
    }

    public BigDecimal getValorDiferencia() {
        return valorDiferencia;
    }

    public void setValorDiferencia(BigDecimal valorDiferencia) {
        this.valorDiferencia = valorDiferencia;
    }

    @XmlTransient
    public Collection<ChequeDepositado> getChequeDepositadoCollection() {
        return chequeDepositadoCollection;
    }

    public void setChequeDepositadoCollection(Collection<ChequeDepositado> chequeDepositadoCollection) {
        this.chequeDepositadoCollection = chequeDepositadoCollection;
    }

    public Cierre getCierre() {
        return cierre;
    }

    public void setCierre(Cierre cierre) {
        this.cierre = cierre;
    }

    public AperturaDetalle getAperturaDetalle() {
        return aperturaDetalle;
    }

    public void setAperturaDetalle(AperturaDetalle aperturaDetalle) {
        this.aperturaDetalle = aperturaDetalle;
    }

    @XmlTransient
    public Collection<CierreDesgloceEfectivo> getCierreDesgloceEfectivoCollection() {
        return cierreDesgloceEfectivoCollection;
    }

    public void setCierreDesgloceEfectivoCollection(Collection<CierreDesgloceEfectivo> cierreDesgloceEfectivoCollection) {
        this.cierreDesgloceEfectivoCollection = cierreDesgloceEfectivoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cierreDetallePK != null ? cierreDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreDetalle)) {
            return false;
        }
        CierreDetalle other = (CierreDetalle) object;
        if ((this.cierreDetallePK == null && other.cierreDetallePK != null) || (this.cierreDetallePK != null && !this.cierreDetallePK.equals(other.cierreDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.CierreDetalle[ cierreDetallePK=" + cierreDetallePK + " ]";
    }
    
}
