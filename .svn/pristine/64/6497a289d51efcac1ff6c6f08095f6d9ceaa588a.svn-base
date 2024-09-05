/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.socios.Socio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.RETIRO_CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RetiroCheque.findAll", query = "SELECT r FROM RetiroCheque r"),
    @NamedQuery(name = "RetiroCheque.findByCodigo", query = "SELECT r FROM RetiroCheque r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RetiroCheque.findByCodigoSocio", query = "SELECT r FROM RetiroCheque r WHERE r.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "RetiroCheque.findByCodigoIfip", query = "SELECT r FROM RetiroCheque r WHERE r.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RetiroCheque.findByEsSocioBeneficiario", query = "SELECT r FROM RetiroCheque r WHERE r.esSocioBeneficiario = :esSocioBeneficiario"),
    @NamedQuery(name = "RetiroCheque.findByBeneficiario", query = "SELECT r FROM RetiroCheque r WHERE r.beneficiario = :beneficiario"),
    @NamedQuery(name = "RetiroCheque.findByValor", query = "SELECT r FROM RetiroCheque r WHERE r.valor = :valor"),
    @NamedQuery(name = "RetiroCheque.findByFechaEstado", query = "SELECT r FROM RetiroCheque r WHERE r.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "RetiroCheque.findByCodigiIfipCodigoAgenciaEstado", query = "SELECT r FROM RetiroCheque r JOIN r.talonarioChequeDetalle t JOIN t.talonarioCheque c WHERE r.codigoIfip = :codigoIfip AND c.codigoIfipAgencia = :codigoIfipAgencia  AND r.estado= :estado"),
    @NamedQuery(name = "RetiroCheque.findByCodigiIfipCodigoAgenciaEstadoUnoEstadoDos", query = "SELECT r FROM RetiroCheque r JOIN r.talonarioChequeDetalle t JOIN t.talonarioCheque c WHERE r.codigoIfip = :codigoIfip AND c.codigoIfipAgencia = :codigoIfipAgencia  AND r.estado IN (:estadoUno, :estadoDos)"),
    @NamedQuery(name = "RetiroCheque.findByEstadoColocadoPor", query = "SELECT r FROM RetiroCheque r WHERE r.estadoColocadoPor = :estadoColocadoPor")})
public class RetiroCheque implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByCodigiIfipCodigoAgenciaEstado = "RetiroCheque.findByCodigiIfipCodigoAgenciaEstado";
    public static String findByCodigiIfipCodigoAgenciaEstadoUnoEstadoDos = "RetiroCheque.findByCodigiIfipCodigoAgenciaEstadoUnoEstadoDos";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")
    private long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_SOCIO_BENEFICIARIO")
    private char esSocioBeneficiario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "BENEFICIARIO")
    private String beneficiario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ENT_FIN")
    private long codigoCuentaEntFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUE")
    private long numeroCheque;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA_ENT_FIN", referencedColumnName = "CODIGO_CUENTA_ENT_FIN", insertable = false, updatable = false),
        @JoinColumn(name = "NUMERO_CHEQUE", referencedColumnName = "NUMERO_CHEQUE", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private TalonarioChequeDetalle talonarioChequeDetalle;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne
    private MovimientoCuenta codigoMovimiento;
    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta codigoCuenta;
    

    public RetiroCheque() {
    }

    public RetiroCheque(Long codigo) {
        this.codigo = codigo;
    }

    public RetiroCheque(Long codigo, long codigoSocio, long codigoIfip, char esSocioBeneficiario, String beneficiario, BigDecimal valor, Date fechaEstado, char estado, long estadoColocadoPor) {
        this.codigo = codigo;
        this.codigoSocio = codigoSocio;
        this.codigoIfip = codigoIfip;
        this.esSocioBeneficiario = esSocioBeneficiario;
        this.beneficiario = beneficiario;
        this.valor = valor;
        this.fechaEstado = fechaEstado;
        this.estado = estado;
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public char getEsSocioBeneficiario() {
        return esSocioBeneficiario;
    }

    public void setEsSocioBeneficiario(char esSocioBeneficiario) {
        this.esSocioBeneficiario = esSocioBeneficiario;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public long getEstadoColocadoPor() {
        return estadoColocadoPor;
    }

    public void setEstadoColocadoPor(long estadoColocadoPor) {
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public TalonarioChequeDetalle getTalonarioChequeDetalle() {
        return talonarioChequeDetalle;
    }

    public void setTalonarioChequeDetalle(TalonarioChequeDetalle talonarioChequeDetalle) {
        this.talonarioChequeDetalle = talonarioChequeDetalle;
    }

    public MovimientoCuenta getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(MovimientoCuenta codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public Cuenta getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(Cuenta codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
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
        if (!(object instanceof RetiroCheque)) {
            return false;
        }
        RetiroCheque other = (RetiroCheque) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ahorros.RetiroCheque[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoCuentaEntFin
     */
    public long getCodigoCuentaEntFin() {
        return codigoCuentaEntFin;
    }

    /**
     * @param codigoCuentaEntFin the codigoCuentaEntFin to set
     */
    public void setCodigoCuentaEntFin(long codigoCuentaEntFin) {
        this.codigoCuentaEntFin = codigoCuentaEntFin;
    }

    /**
     * @return the numeroCheque
     */
    public long getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(long numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    
}
