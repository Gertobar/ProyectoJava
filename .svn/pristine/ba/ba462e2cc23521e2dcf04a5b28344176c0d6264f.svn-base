/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.RUBRO_TABLA_AMORTIZACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RubroTablaAmortizacion.findAll", query = "SELECT r FROM RubroTablaAmortizacion r"),
    @NamedQuery(name = "RubroTablaAmortizacion.findByNumeroCredito", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.rubroTablaAmortizacionPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "RubroTablaAmortizacion.findByCodigoIfip", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.rubroTablaAmortizacionPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RubroTablaAmortizacion.findByCuota", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.rubroTablaAmortizacionPK.cuota = :cuota"),
    @NamedQuery(name = "RubroTablaAmortizacion.findByCodigoRubro", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.rubroTablaAmortizacionPK.codigoRubro = :codigoRubro"),
    @NamedQuery(name = "RubroTablaAmortizacion.findByEstado", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.estado = :estado"),
    @NamedQuery(name = "RubroTablaAmortizacion.findByValor", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.valor = :valor"),
    @NamedQuery(name = "RubroTablaAmortizacion.findByAbono", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.abono = :abono"),
    @NamedQuery(name = "RubroTablaAmortizacion.findBySaldo", query = "SELECT r FROM RubroTablaAmortizacion r WHERE r.saldo = :saldo")})
public class RubroTablaAmortizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RubroTablaAmortizacionPK rubroTablaAmortizacionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABONO")
    private BigDecimal abono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @JoinColumn(name = "CODIGO_RUBRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoRubroProCreMonIfi tipoRubroProCreMonIfi;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO_CREDITO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false),
        @JoinColumn(name = "CUOTA", referencedColumnName = "CUOTA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TablaAmortizacion tablaAmortizacion;

    public RubroTablaAmortizacion() {
    }

    public RubroTablaAmortizacion(RubroTablaAmortizacionPK rubroTablaAmortizacionPK) {
        this.rubroTablaAmortizacionPK = rubroTablaAmortizacionPK;
    }

    public RubroTablaAmortizacion(RubroTablaAmortizacionPK rubroTablaAmortizacionPK, char estado, BigDecimal valor, BigDecimal abono, BigDecimal saldo) {
        this.rubroTablaAmortizacionPK = rubroTablaAmortizacionPK;
        this.estado = estado;
        this.valor = valor;
        this.abono = abono;
        this.saldo = saldo;
    }

    public RubroTablaAmortizacion(long numeroCredito, long codigoIfip, long cuota, long codigoRubro) {
        this.rubroTablaAmortizacionPK = new RubroTablaAmortizacionPK(numeroCredito, codigoIfip, cuota, codigoRubro);
    }

    public RubroTablaAmortizacionPK getRubroTablaAmortizacionPK() {
        return rubroTablaAmortizacionPK;
    }

    public void setRubroTablaAmortizacionPK(RubroTablaAmortizacionPK rubroTablaAmortizacionPK) {
        this.rubroTablaAmortizacionPK = rubroTablaAmortizacionPK;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public TipoRubroProCreMonIfi getTipoRubroProCreMonIfi() {
        return tipoRubroProCreMonIfi;
    }

    public void setTipoRubroProCreMonIfi(TipoRubroProCreMonIfi tipoRubroProCreMonIfi) {
        this.tipoRubroProCreMonIfi = tipoRubroProCreMonIfi;
    }

    public TablaAmortizacion getTablaAmortizacion() {
        return tablaAmortizacion;
    }

    public void setTablaAmortizacion(TablaAmortizacion tablaAmortizacion) {
        this.tablaAmortizacion = tablaAmortizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubroTablaAmortizacionPK != null ? rubroTablaAmortizacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroTablaAmortizacion)) {
            return false;
        }
        RubroTablaAmortizacion other = (RubroTablaAmortizacion) object;
        if ((this.rubroTablaAmortizacionPK == null && other.rubroTablaAmortizacionPK != null) || (this.rubroTablaAmortizacionPK != null && !this.rubroTablaAmortizacionPK.equals(other.rubroTablaAmortizacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.RubroTablaAmortizacion[ rubroTablaAmortizacionPK=" + rubroTablaAmortizacionPK + " ]";
    }
    
}
