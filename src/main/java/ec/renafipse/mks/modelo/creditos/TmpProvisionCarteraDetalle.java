/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "MKS_CREDITOS.TMP_PROVISION_CARTERA_DETALLE")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "TmpProvisionCarteraDetalle.findAllByFechaIfipOrder", query = "SELECT t FROM TmpProvisionCarteraDetalle t WHERE t.codigoIfip.codigo = :codigoIfip AND t.fecha = :fecha order by t.codigoIfipAgencia.nombre, t.codigoTipoCartera.nombre, t.codigoCalificacionTipoCartera.calificacion, t.numeroCredito")})
public class TmpProvisionCarteraDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private long diasMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_PROVISION")
    private BigDecimal valorProvision;
    @JoinColumn(name = "CODIGO_TIPO_CARTERA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoCartera codigoTipoCartera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CREDITO")
    private long numeroCredito;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private IfipAgencia codigoIfipAgencia;
    @JoinColumn(name = "CODIGO_CALIFICACION_TIPO_CAR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private CalificacionTipoCartera codigoCalificacionTipoCartera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GARANTIA_HIPOTECARIA")
    private int garantiaHipotecaria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GARNTIA_AUTOLIQUIDABLE")
    private int garantiaAutoliquidable;
    
    public TmpProvisionCarteraDetalle() {
    }

    public TmpProvisionCarteraDetalle(Long codigo) {
        this.codigo = codigo;
    }

    public TmpProvisionCarteraDetalle(Long codigo, Date fecha, BigDecimal saldoCapital, long diasMora, BigDecimal valorProvision, TipoCartera codigoTipoCartera, long numeroCredito, Ifip codigoIfip, IfipAgencia codigoIfipAgencia, CalificacionTipoCartera codigoCalificacionTipoCartera, int garantiaHipotecaria, int garantiaAutoliquidable) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.saldoCapital = saldoCapital;
        this.diasMora = diasMora;
        this.valorProvision = valorProvision;
        this.codigoTipoCartera = codigoTipoCartera;
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoCalificacionTipoCartera = codigoCalificacionTipoCartera;
        this.garantiaHipotecaria = garantiaHipotecaria;
        this.garantiaAutoliquidable = garantiaAutoliquidable;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public long getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
    }

    public BigDecimal getValorProvision() {
        return valorProvision;
    }

    public void setValorProvision(BigDecimal valorProvision) {
        this.valorProvision = valorProvision;
    }

    public TipoCartera getCodigoTipoCartera() {
        return codigoTipoCartera;
    }

    public void setCodigoTipoCartera(TipoCartera codigoTipoCartera) {
        this.codigoTipoCartera = codigoTipoCartera;
    }

    public long getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }
    
    public IfipAgencia getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(IfipAgencia codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public CalificacionTipoCartera getCodigoCalificacionTipoCartera() {
        return codigoCalificacionTipoCartera;
    }

    public void setCodigoCalificacionTipoCartera(CalificacionTipoCartera codigoCalificacionTipoCartera) {
        this.codigoCalificacionTipoCartera = codigoCalificacionTipoCartera;
    }
     
    public int getGarantiaHipotecaria() {
        return garantiaHipotecaria;
    }

    public void setGarantiaHipotecaria(int garantiaHipotecaria) {
        this.garantiaHipotecaria = garantiaHipotecaria;
    }

    public int getGarantiaAutoliquidable() {
        return garantiaAutoliquidable;
    }

    public void setGarantiaAutoliquidable(int garantiaAutoliquidable) {
        this.garantiaAutoliquidable = garantiaAutoliquidable;
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
        if (!(object instanceof TmpProvisionCarteraDetalle)) {
            return false;
        }
        TmpProvisionCarteraDetalle other = (TmpProvisionCarteraDetalle) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TmpProvisionCarteraDetalle[ codigo=" + codigo + " ]";
    }
}
