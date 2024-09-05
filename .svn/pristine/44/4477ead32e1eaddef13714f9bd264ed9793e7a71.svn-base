/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Socio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.LIQUIDACION_CUENTA_CABECERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LiquidacionCuentaCabecera.findAll", query = "SELECT l FROM LiquidacionCuentaCabecera l"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByCodigo", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByCodigoSocio", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByCodigoIfip", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByTotalInteresLiquidado", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.totalInteresLiquidado = :totalInteresLiquidado"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByTotalSaldoLiquidado", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.totalSaldoLiquidado = :totalSaldoLiquidado"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByTotalLiquidacion", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.totalLiquidacion = :totalLiquidacion"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByTotalCertificadosLiquidado", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.totalCertificadosLiquidado = :totalCertificadosLiquidado"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByLiquidadoPor", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.liquidadoPor = :liquidadoPor"),
    @NamedQuery(name = "LiquidacionCuentaCabecera.findByFechaLiquidacion", query = "SELECT l FROM LiquidacionCuentaCabecera l WHERE l.fechaLiquidacion = :fechaLiquidacion")})
public class LiquidacionCuentaCabecera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LIQUIDACION_CUENTA_CAB")
    @SequenceGenerator(name = "GSQ_LIQUIDACION_CUENTA_CAB", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_LIQUIDACION_CUENTA_CAB")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")    
    private Long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_INTERES_LIQUIDADO")
    private BigDecimal totalInteresLiquidado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_SALDO_LIQUIDADO")
    private BigDecimal totalSaldoLiquidado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_LIQUIDACION")
    private BigDecimal totalLiquidacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CERTIFICADOS_LIQUIDADO")
    private BigDecimal totalCertificadosLiquidado;
    /*@Basic(optional = false)
    @NotNull
    @Column(name = "LIQUIDADO_POR")*/
    @JoinColumn(name = "LIQUIDADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario liquidadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_LIQUIDACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiquidacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionCuentaCabecera", fetch = FetchType.LAZY)
    private Collection<LiquidacionCuentaDetalle> liquidacionCuentaDetalleCollection;

    public LiquidacionCuentaCabecera() {
    }

    public LiquidacionCuentaCabecera(Long codigo) {
        this.codigo = codigo;
    }

    public LiquidacionCuentaCabecera(Long codigo, Long codigoSocio, long codigoIfip, BigDecimal totalInteresLiquidado, BigDecimal totalSaldoLiquidado, BigDecimal totalLiquidacion, BigDecimal totalCertificadosLiquidado, Usuario liquidadoPor, Date fechaLiquidacion) {
        this.codigo = codigo;
        this.codigoSocio = codigoSocio;
        this.codigoIfip = codigoIfip;
        this.totalInteresLiquidado = totalInteresLiquidado;
        this.totalSaldoLiquidado = totalSaldoLiquidado;
        this.totalLiquidacion = totalLiquidacion;
        this.totalCertificadosLiquidado = totalCertificadosLiquidado;
        this.liquidadoPor = liquidadoPor;
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public BigDecimal getTotalInteresLiquidado() {
        return totalInteresLiquidado;
    }

    public void setTotalInteresLiquidado(BigDecimal totalInteresLiquidado) {
        this.totalInteresLiquidado = totalInteresLiquidado;
    }

    public BigDecimal getTotalSaldoLiquidado() {
        return totalSaldoLiquidado;
    }

    public void setTotalSaldoLiquidado(BigDecimal totalSaldoLiquidado) {
        this.totalSaldoLiquidado = totalSaldoLiquidado;
    }

    public BigDecimal getTotalLiquidacion() {
        return totalLiquidacion;
    }

    public void setTotalLiquidacion(BigDecimal totalLiquidacion) {
        this.totalLiquidacion = totalLiquidacion;
    }

    public BigDecimal getTotalCertificadosLiquidado() {
        return totalCertificadosLiquidado;
    }

    public void setTotalCertificadosLiquidado(BigDecimal totalCertificadosLiquidado) {
        this.totalCertificadosLiquidado = totalCertificadosLiquidado;
    }

    public Usuario getLiquidadoPor() {
        return liquidadoPor;
    }

    public void setLiquidadoPor(Usuario liquidadoPor) {
        this.liquidadoPor = liquidadoPor;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    @XmlTransient
    public Collection<LiquidacionCuentaDetalle> getLiquidacionCuentaDetalleCollection() {
        return liquidacionCuentaDetalleCollection;
    }

    public void setLiquidacionCuentaDetalleCollection(Collection<LiquidacionCuentaDetalle> liquidacionCuentaDetalleCollection) {
        this.liquidacionCuentaDetalleCollection = liquidacionCuentaDetalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionCuentaCabecera)) {
            return false;
        }
        LiquidacionCuentaCabecera other = (LiquidacionCuentaCabecera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.LiquidacionCuentaCabecera[ codigo=" + codigo + " ]";
    }
    
}
