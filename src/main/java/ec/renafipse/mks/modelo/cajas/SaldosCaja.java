/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CAJAS.SALDOS_CAJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaldosCaja.findAll", query = "SELECT s FROM SaldosCaja s"),
    @NamedQuery(name = "SaldosCaja.findByCodigoApertura", query = "SELECT s FROM SaldosCaja s WHERE s.saldosCajaPK.codigoApertura = :codigoApertura"),
    @NamedQuery(name = "SaldosCaja.findByCodigoMoneda", query = "SELECT s FROM SaldosCaja s WHERE s.saldosCajaPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "SaldosCaja.findByTotalEfectivoApertura", query = "SELECT s FROM SaldosCaja s WHERE s.totalEfectivoApertura = :totalEfectivoApertura"),
    @NamedQuery(name = "SaldosCaja.findByTotalChequesApertura", query = "SELECT s FROM SaldosCaja s WHERE s.totalChequesApertura = :totalChequesApertura"),
    @NamedQuery(name = "SaldosCaja.findByTotalApertura", query = "SELECT s FROM SaldosCaja s WHERE s.totalApertura = :totalApertura"),
    @NamedQuery(name = "SaldosCaja.findByTotalEfectivoCierre", query = "SELECT s FROM SaldosCaja s WHERE s.totalEfectivoCierre = :totalEfectivoCierre"),
    @NamedQuery(name = "SaldosCaja.findByTotalChequesCierre", query = "SELECT s FROM SaldosCaja s WHERE s.totalChequesCierre = :totalChequesCierre"),
    @NamedQuery(name = "SaldosCaja.findByTotalCierre", query = "SELECT s FROM SaldosCaja s WHERE s.totalCierre = :totalCierre"),
    @NamedQuery(name = "SaldosCaja.findBySaldoEfectivo", query = "SELECT s FROM SaldosCaja s WHERE s.saldoEfectivo = :saldoEfectivo"),
    @NamedQuery(name = "SaldosCaja.findBySaldoCheques", query = "SELECT s FROM SaldosCaja s WHERE s.saldoCheques = :saldoCheques"),
    @NamedQuery(name = "SaldosCaja.findBySaldoTotal", query = "SELECT s FROM SaldosCaja s WHERE s.saldoTotal = :saldoTotal"),
    //Personalizados
    @NamedQuery(name = "SaldosCaja.findByIfipAgenciaEstado", query = "SELECT s FROM SaldosCaja s JOIN s.apertura a WHERE a.codigoIfipAgencia = :codigoIfipAgencia AND a.estado = :estado ORDER BY a.computador.nombre, s.moneda.nombre")
})
public class SaldosCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoApertura="SaldosCaja.findByCodigoApertura";
    public static final String findByIfipAgenciaEstado="SaldosCaja.findByIfipAgenciaEstado";
    @EmbeddedId
    protected SaldosCajaPK saldosCajaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_EFECTIVO_APERTURA")
    private BigDecimal totalEfectivoApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CHEQUES_APERTURA")
    private BigDecimal totalChequesApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_APERTURA")
    private BigDecimal totalApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_EFECTIVO_CIERRE")
    private BigDecimal totalEfectivoCierre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CHEQUES_CIERRE")
    private BigDecimal totalChequesCierre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CIERRE")
    private BigDecimal totalCierre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_EFECTIVO")
    private BigDecimal saldoEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CHEQUES")
    private BigDecimal saldoCheques;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_TOTAL")
    private BigDecimal saldoTotal;
    @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Apertura apertura;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;
    public SaldosCaja() {
    }

    public SaldosCaja(SaldosCajaPK saldosCajaPK) {
        this.saldosCajaPK = saldosCajaPK;
    }

    public SaldosCaja(SaldosCajaPK saldosCajaPK, BigDecimal totalEfectivoApertura, BigDecimal totalChequesApertura, BigDecimal totalApertura, BigDecimal totalEfectivoCierre, BigDecimal totalChequesCierre, BigDecimal totalCierre, BigDecimal saldoEfectivo, BigDecimal saldoCheques, BigDecimal saldoTotal) {
        this.saldosCajaPK = saldosCajaPK;
        this.totalEfectivoApertura = totalEfectivoApertura;
        this.totalChequesApertura = totalChequesApertura;
        this.totalApertura = totalApertura;
        this.totalEfectivoCierre = totalEfectivoCierre;
        this.totalChequesCierre = totalChequesCierre;
        this.totalCierre = totalCierre;
        this.saldoEfectivo = saldoEfectivo;
        this.saldoCheques = saldoCheques;
        this.saldoTotal = saldoTotal;
    }

    public SaldosCaja(long codigoApertura, long codigoMoneda) {
        this.saldosCajaPK = new SaldosCajaPK(codigoApertura, codigoMoneda);
    }

    public SaldosCajaPK getSaldosCajaPK() {
        return saldosCajaPK;
    }

    public void setSaldosCajaPK(SaldosCajaPK saldosCajaPK) {
        this.saldosCajaPK = saldosCajaPK;
    }

    public BigDecimal getTotalEfectivoApertura() {
        return totalEfectivoApertura;
    }

    public void setTotalEfectivoApertura(BigDecimal totalEfectivoApertura) {
        this.totalEfectivoApertura = totalEfectivoApertura;
    }

    public BigDecimal getTotalChequesApertura() {
        return totalChequesApertura;
    }

    public void setTotalChequesApertura(BigDecimal totalChequesApertura) {
        this.totalChequesApertura = totalChequesApertura;
    }

    public BigDecimal getTotalApertura() {
        return totalApertura;
    }

    public void setTotalApertura(BigDecimal totalApertura) {
        this.totalApertura = totalApertura;
    }

    public BigDecimal getTotalEfectivoCierre() {
        return totalEfectivoCierre;
    }

    public void setTotalEfectivoCierre(BigDecimal totalEfectivoCierre) {
        this.totalEfectivoCierre = totalEfectivoCierre;
    }

    public BigDecimal getTotalChequesCierre() {
        return totalChequesCierre;
    }

    public void setTotalChequesCierre(BigDecimal totalChequesCierre) {
        this.totalChequesCierre = totalChequesCierre;
    }

    public BigDecimal getTotalCierre() {
        return totalCierre;
    }

    public void setTotalCierre(BigDecimal totalCierre) {
        this.totalCierre = totalCierre;
    }

    public BigDecimal getSaldoEfectivo() {
        return saldoEfectivo;
    }

    public void setSaldoEfectivo(BigDecimal saldoEfectivo) {
        this.saldoEfectivo = saldoEfectivo;
    }

    public BigDecimal getSaldoCheques() {
        return saldoCheques;
    }

    public void setSaldoCheques(BigDecimal saldoCheques) {
        this.saldoCheques = saldoCheques;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saldosCajaPK != null ? saldosCajaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaldosCaja)) {
            return false;
        }
        SaldosCaja other = (SaldosCaja) object;
        if ((this.saldosCajaPK == null && other.saldosCajaPK != null) || (this.saldosCajaPK != null && !this.saldosCajaPK.equals(other.saldosCajaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.SaldosCaja[ saldosCajaPK=" + saldosCajaPK + " ]";
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

    /**
     * @return the apertura
     */
    public Apertura getApertura() {
        return apertura;
    }

    /**
     * @param apertura the apertura to set
     */
    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }
    
}
