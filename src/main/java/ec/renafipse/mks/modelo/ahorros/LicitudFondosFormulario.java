/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.creditos.CarteraGestionada;
import ec.renafipse.mks.modelo.socios.Persona;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_FORMULARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosFormulario.findAll", query = "SELECT l FROM LicitudFondosFormulario l"),
    @NamedQuery(name = "LicitudFondosFormulario.findByIdentificacionPersonaEstado", query = "SELECT l FROM LicitudFondosFormulario l WHERE l.persona.identificacion = :identificacion  AND l.codigoIfip = :codigoIfip AND l.estado = :estado AND l.codigoIfipAgencia = :codigoIfipAgencia ORDER BY l.codigo"),
    @NamedQuery(name = "LicitudFondosFormulario.findByEstado", query = "SELECT l FROM LicitudFondosFormulario l WHERE l.codigoIfip = :codigoIfip AND l.estado = :estado AND l.codigoIfipAgencia = :codigoIfipAgencia ORDER BY l.codigo")
})
@NamedNativeQueries({
    @NamedNativeQuery(name="LicitudFondosFormulario.findByIdentificacionPersonaEstadoAgencia",
            query= "SELECT l.*\n" +
"FROM   mks_ahorros.licitud_fondos_formulario l,\n" +
"       mks_socios.persona p,\n" +
"       mks_socios.socio so\n" +
"WHERE  l.codigo_persona = p.codigo\n" +
"AND    p.codigo = so.codigo_persona\n" +
"AND    p.identificacion = :identificacion\n" +
"AND    l.codigo_ifip = :codigoIfip\n" +
"AND    l.estado = :estado\n" +
"AND    (    l.codigo_ifip_agencia = :codigoIfipAgencia\n" +
"         OR so.codigo_ifip_agencia = :codigoIfipAgencia )\n" +
"ORDER BY l.codigo" , resultClass=LicitudFondosFormulario.class),
    @NamedNativeQuery(name="LicitudFondosFormulario.findByEstadoAgencia",
            query= "SELECT l.*\n" +
"FROM   mks_ahorros.licitud_fondos_formulario l,\n" +
"       mks_socios.persona p,\n" +
"       mks_socios.socio so\n" +
"WHERE  l.codigo_persona = p.codigo\n" +
"AND    p.codigo = so.codigo_persona\n" +
"AND    l.codigo_ifip = :codigoIfip\n" +
"AND    l.estado = :estado\n" +
"AND    (    l.codigo_ifip_agencia = :codigoIfipAgencia\n" +
"         OR so.codigo_ifip_agencia = :codigoIfipAgencia )\n" +
"ORDER BY l.codigo" , resultClass=LicitudFondosFormulario.class)
})
public class LicitudFondosFormulario implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByIdentificacionPersonaEstado = "LicitudFondosFormulario.findByIdentificacionPersonaEstado";
    public static final String findByEstado = "LicitudFondosFormulario.findByEstado";
    public static final String findByIdentificacionPersonaEstadoAgencia = "LicitudFondosFormulario.findByIdentificacionPersonaEstadoAgencia";
    public static final String findByEstadoAgencia = "LicitudFondosFormulario.findByEstadoAgencia";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_TRANSACCION")
    private long tipoTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private long codigoMovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUENTA")
    private BigInteger cuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTEO_INICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConteoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTEO_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConteoFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_INDIVIDUAL")
    private char esIndividual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private Long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTUALIZADO_POR")
    private Long actualizadoPor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFormulario", fetch = FetchType.LAZY)
    private Collection<LicitudFondosPerExcFor> licitudFondosPerExcForCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "licitudFondosFormulario",  fetch = FetchType.LAZY)
    private LicitudFondosFormularioAdi licitudFondosFormularioAdi;
    @JoinColumn(name = "CODIGO_MODULO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LicitudFondosModulo codigoModulo;
    @JoinColumn(name = "CODIGO_CONTROL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LicitudFondosControl codigoControl;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private MovimientoCuenta movimiento;

    public LicitudFondosFormulario() {
    }

    public LicitudFondosFormulario(Long codigo) {
        this.codigo = codigo;
    }

    public LicitudFondosFormulario(Long codigo, long codigoIfip, long codigoIfipAgencia, long codigoPersona, long tipoTransaccion, long codigoMovimiento, BigDecimal monto, BigInteger cuenta, Date fechaRegistro, char estado, Date fechaConteoInicial, Date fechaConteoFinal, char esIndividual) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoPersona = codigoPersona;
        this.tipoTransaccion = tipoTransaccion;
        this.codigoMovimiento = codigoMovimiento;
        this.monto = monto;
        this.cuenta = cuenta;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.fechaConteoInicial = fechaConteoInicial;
        this.fechaConteoFinal = fechaConteoFinal;
        this.esIndividual = esIndividual;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(long tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigInteger getCuenta() {
        return cuenta;
    }

    public void setCuenta(BigInteger cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaConteoInicial() {
        return fechaConteoInicial;
    }

    public void setFechaConteoInicial(Date fechaConteoInicial) {
        this.fechaConteoInicial = fechaConteoInicial;
    }

    public Date getFechaConteoFinal() {
        return fechaConteoFinal;
    }

    public void setFechaConteoFinal(Date fechaConteoFinal) {
        this.fechaConteoFinal = fechaConteoFinal;
    }

    public char getEsIndividual() {
        return esIndividual;
    }

    public void setEsIndividual(char esIndividual) {
        this.esIndividual = esIndividual;
    }

    @XmlTransient
    public Collection<LicitudFondosPerExcFor> getLicitudFondosPerExcForCollection() {
        return licitudFondosPerExcForCollection;
    }

    public void setLicitudFondosPerExcForCollection(Collection<LicitudFondosPerExcFor> licitudFondosPerExcForCollection) {
        this.licitudFondosPerExcForCollection = licitudFondosPerExcForCollection;
    }

    public LicitudFondosFormularioAdi getLicitudFondosFormularioAdi() {
        return licitudFondosFormularioAdi;
    }

    public void setLicitudFondosFormularioAdi(LicitudFondosFormularioAdi licitudFondosFormularioAdi) {
        this.licitudFondosFormularioAdi = licitudFondosFormularioAdi;
    }

    public LicitudFondosModulo getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(LicitudFondosModulo codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    public LicitudFondosControl getCodigoControl() {
        return codigoControl;
    }

    public void setCodigoControl(LicitudFondosControl codigoControl) {
        this.codigoControl = codigoControl;
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
        if (!(object instanceof LicitudFondosFormulario)) {
            return false;
        }
        LicitudFondosFormulario other = (LicitudFondosFormulario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosFormulario[ codigo=" + codigo + " ]";
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the movimiento
     */
    public MovimientoCuenta getMovimiento() {
        return movimiento;
    }

    /**
     * @param movimiento the movimiento to set
     */
    public void setMovimiento(MovimientoCuenta movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * Devuelve en cadena el valor del estado
     * @return 
     */
    public String getEstadoToString() {

        if (this.estado == 'P') {
            return "PENDIENTE";
        } else if (this.estado == 'R') {
            return "REGISTRADO";
        } else if (this.estado == 'A') {
            return "ANULADO";
        } else if (this.estado == 'I') {
            return "IMPRESO";
        }
        return "DESCONOCIDO";
    }

    /**
     * @return the registradoPor
     */
    public Long getRegistradoPor() {
        return registradoPor;
    }

    /**
     * @param registradoPor the registradoPor to set
     */
    public void setRegistradoPor(Long registradoPor) {
        this.registradoPor = registradoPor;
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the actualizadoPor
     */
    public Long getActualizadoPor() {
        return actualizadoPor;
    }

    /**
     * @param actualizadoPor the actualizadoPor to set
     */
    public void setActualizadoPor(Long actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

}
