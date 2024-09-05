/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_CONTROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosControl.findAll", query = "SELECT l FROM LicitudFondosControl l"),
    @NamedQuery(name = "LicitudFondosControl.findByCodigo", query = "SELECT l FROM LicitudFondosControl l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LicitudFondosControl.findByFechaControlInicial", query = "SELECT l FROM LicitudFondosControl l WHERE l.fechaControlInicial = :fechaControlInicial"),
    @NamedQuery(name = "LicitudFondosControl.findByFechaControlFinal", query = "SELECT l FROM LicitudFondosControl l WHERE l.fechaControlFinal = :fechaControlFinal"),
    @NamedQuery(name = "LicitudFondosControl.findByCodigoPersona", query = "SELECT l FROM LicitudFondosControl l WHERE l.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "LicitudFondosControl.findByValorAcumulado", query = "SELECT l FROM LicitudFondosControl l WHERE l.valorAcumulado = :valorAcumulado"),
    @NamedQuery(name = "LicitudFondosControl.findByValorCompensacion", query = "SELECT l FROM LicitudFondosControl l WHERE l.valorCompensacion = :valorCompensacion"),
    @NamedQuery(name = "LicitudFondosControl.findByCodigoIfip", query = "SELECT l FROM LicitudFondosControl l WHERE l.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "LicitudFondosControl.findByCodigoMovimiento", query = "SELECT l FROM LicitudFondosControl l WHERE l.codigoMovimiento = :codigoMovimiento")})
public class LicitudFondosControl implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTROL_INICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaControlInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTROL_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaControlFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_ACUMULADO")
    private BigDecimal valorAcumulado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_COMPENSACION")
    private BigDecimal valorCompensacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private long codigoMovimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoControl")
    private Collection<LicitudFondosFormulario> licitudFondosFormularioCollection;
    @JoinColumn(name = "CODIGO_MODULO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LicitudFondosModulo codigoModulo;

    public LicitudFondosControl() {
    }

    public LicitudFondosControl(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public LicitudFondosControl(BigDecimal codigo, Date fechaControlInicial, Date fechaControlFinal, long codigoPersona, BigDecimal valorAcumulado, BigDecimal valorCompensacion, long codigoIfip, long codigoMovimiento) {
        this.codigo = codigo;
        this.fechaControlInicial = fechaControlInicial;
        this.fechaControlFinal = fechaControlFinal;
        this.codigoPersona = codigoPersona;
        this.valorAcumulado = valorAcumulado;
        this.valorCompensacion = valorCompensacion;
        this.codigoIfip = codigoIfip;
        this.codigoMovimiento = codigoMovimiento;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Date getFechaControlInicial() {
        return fechaControlInicial;
    }

    public void setFechaControlInicial(Date fechaControlInicial) {
        this.fechaControlInicial = fechaControlInicial;
    }

    public Date getFechaControlFinal() {
        return fechaControlFinal;
    }

    public void setFechaControlFinal(Date fechaControlFinal) {
        this.fechaControlFinal = fechaControlFinal;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public BigDecimal getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(BigDecimal valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }

    public BigDecimal getValorCompensacion() {
        return valorCompensacion;
    }

    public void setValorCompensacion(BigDecimal valorCompensacion) {
        this.valorCompensacion = valorCompensacion;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    @XmlTransient
    public Collection<LicitudFondosFormulario> getLicitudFondosFormularioCollection() {
        return licitudFondosFormularioCollection;
    }

    public void setLicitudFondosFormularioCollection(Collection<LicitudFondosFormulario> licitudFondosFormularioCollection) {
        this.licitudFondosFormularioCollection = licitudFondosFormularioCollection;
    }

    public LicitudFondosModulo getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(LicitudFondosModulo codigoModulo) {
        this.codigoModulo = codigoModulo;
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
        if (!(object instanceof LicitudFondosControl)) {
            return false;
        }
        LicitudFondosControl other = (LicitudFondosControl) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosControl[ codigo=" + codigo + " ]";
    }
    
}
