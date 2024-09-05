/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.Socio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.CUENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByCodigo", query = "SELECT c FROM Cuenta c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Cuenta.findByCodigoSocio", query = "SELECT c FROM Cuenta c WHERE c.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "Cuenta.findByCreadaPor", query = "SELECT c FROM Cuenta c WHERE c.creadaPor = :creadaPor"),
    @NamedQuery(name = "Cuenta.findBySecuenciaNumero", query = "SELECT c FROM Cuenta c WHERE c.secuenciaNumero = :secuenciaNumero"),
    @NamedQuery(name = "Cuenta.findBySaldoDisponible", query = "SELECT c FROM Cuenta c WHERE c.saldoDisponible = :saldoDisponible"),
    @NamedQuery(name = "Cuenta.findBySaldoBloqueado", query = "SELECT c FROM Cuenta c WHERE c.saldoBloqueado = :saldoBloqueado"),
    @NamedQuery(name = "Cuenta.findBySaldoTotal", query = "SELECT c FROM Cuenta c WHERE c.saldoTotal = :saldoTotal"),
    @NamedQuery(name = "Cuenta.findByProvisionAcumulada", query = "SELECT c FROM Cuenta c WHERE c.provisionAcumulada = :provisionAcumulada"),
    @NamedQuery(name = "Cuenta.findByFechaCreacion", query = "SELECT c FROM Cuenta c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Cuenta.findByActualizadoPor", query = "SELECT c FROM Cuenta c WHERE c.actualizadoPor = :actualizadoPor"),
    @NamedQuery(name = "Cuenta.findByFechaActualizacion", query = "SELECT c FROM Cuenta c WHERE c.fechaActualizacion = :fechaActualizacion"),
    // Personalizados
    @NamedQuery(name = "Cuenta.findByCodigoSocioCodigoIfip", query = "SELECT c FROM Cuenta c WHERE c.codigoSocio = :codigoSocio AND c.codigoIfip = :codigoIfip ORDER BY c.codigoTipoProducto, c.codigo"),
    @NamedQuery(name = "Cuenta.findByCodigoSocioCodigoIfipCodigoEstado", query = "SELECT c FROM Cuenta c WHERE c.codigoSocio = :codigoSocio AND c.codigoIfip = :codigoIfip  AND c.codigoEstado.codigo = :codigoEstado ORDER BY c.codigoTipoProducto, c.codigo"),
    @NamedQuery(name = "Cuenta.findBySocioIfipMonedaTipoProductoEstado", query = "SELECT c FROM Cuenta c WHERE c.codigoSocio = :codigoSocio AND c.codigoIfip = :codigoIfip AND c.codigoMoneda = :codigoMoneda AND c.codigoTipoProducto = :codigoTipoProducto AND c.codigoEstado.codigo = :codigoEstado"),
    @NamedQuery(name = "Cuenta.findByMovimientoCuenta", query = "SELECT c FROM Cuenta c WHERE  c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND   c.socio.codigoPersona.nombreCompleto LIKE :nombreCompleto AND c.codigoEstado.indicaActiva = :indicaActiva  ORDER BY c.socio.codigoPersona.nombreCompleto, c.numero"),
    @NamedQuery(name = "Cuenta.findByEstadoCuenta", query = "SELECT c FROM Cuenta c WHERE  c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND   c.socio.codigoPersona.nombreCompleto LIKE :nombreCompleto  ORDER BY c.socio.codigoPersona.nombreCompleto, c.numero"),
    @NamedQuery(name = "Cuenta.findByNumero", query = "SELECT c FROM Cuenta c WHERE  c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND  c.numero = :numero and c.codigoEstado.codigo = :codigoEstado"),
    @NamedQuery(name = "Cuenta.findByNumeroAll", query = "SELECT c FROM Cuenta c WHERE  c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND  c.numero = :numero"),
    @NamedQuery(name = "Cuenta.findByPuedeReactivar", query = "SELECT c FROM Cuenta c WHERE  c.codigoSocio = :codigoSocio AND c.codigoIfip = :codigoIfip AND c.codigoEstado.puedeReactivar = :puedeReactivar ORDER BY c.productoIfip.productoIfipPK.codigoMoneda, c.productoIfip.productoIfipPK.codigoTipoProducto, c.numero"),
    @NamedQuery(name = "Cuenta.findByProductoMonedaPuedeReactivar", query = "SELECT c FROM Cuenta c JOIN c.productoIfip p WHERE  c.codigoSocio = :codigoSocio AND c.codigoIfip = :codigoIfip AND p.productoIfipPK.codigoIfip = :codigoIfip AND p.productoIfipPK.codigoMoneda =:codigoMoneda AND p.productoIfipPK.codigoTipoProducto = :codigoTipoProducto AND   c.codigoEstado.puedeReactivar = :puedeReactivar ORDER BY c.productoIfip.productoIfipPK.codigoMoneda, c.productoIfip.productoIfipPK.codigoTipoProducto, c.numero"),
    @NamedQuery(name = "Cuenta.findByCertAportPuedeReactivar", query = "SELECT c FROM Cuenta c WHERE  c.codigoSocio = :codigoSocio AND c.codigoIfip = :codigoIfip AND c.codigoEstado.puedeReactivar = :puedeReactivar AND c.tipoProducto.codigo = :codigoProducto AND c.saldoTotal > :saldoTotal"),
    @NamedQuery(name = "Cuenta.findByPuedeReactivarAhorros", query = "SELECT c FROM Cuenta c WHERE  c.codigoSocio = :codigoSocio AND c.codigoIfip = :codigoIfip AND c.codigoEstado.puedeReactivar = :puedeReactivar AND c.tipoProducto.codigo = :codigoProducto ORDER BY c.productoIfip.productoIfipPK.codigoMoneda, c.productoIfip.productoIfipPK.codigoTipoProducto, c.numero"),
    @NamedQuery(name = "Cuenta.findByEjecucionTransaccionLote", query = "SELECT c FROM Cuenta c JOIN c.productoIfip p WHERE c.codigoIfip = :codigoIfip AND c.numero = :numero AND p.productoIfipPK.codigoIfip = :codigoIfip AND p.productoIfipPK.codigoMoneda =:codigoMoneda AND p.productoIfipPK.codigoTipoProducto = :codigoTipoProducto"),
    @NamedQuery(name = "Cuenta.findByMonedaProductoIfipEstado", query = "SELECT c FROM Cuenta c WHERE  c.codigoTipoProducto = :codigoTipoProducto AND c.codigoMoneda = :codigoMoneda AND c.codigoIfip = :codigoIfip AND  c.codigoEstado.codigo = :codigoEstado ORDER BY c.numero "),
    @NamedQuery(name = "Cuenta.findByNumCuentaIfipSocioBenef", query = "SELECT c FROM Cuenta c WHERE c.codigoSocio = :codigoSocio AND c.codigoTipoProducto = :codigoTipoProducto AND c.codigoEstado.codigo = :codigoEstado AND  c.codigoMoneda = :codigoMoneda AND c.numero = :numero AND c.codigoIfip = :codigoIfip AND  c.esSocioBeneficiario = :esSocioBeneficiario"),    
    @NamedQuery(name = "Cuenta.findByCodigoPersonaCodigoIfipCodigoEstado", query = "SELECT c FROM Cuenta c WHERE c.socio.codigoPersona.codigo = :codigoPersona AND c.codigoIfip = :codigoIfip  AND c.codigoEstado.codigo = :codigoEstado ORDER BY c.codigoTipoProducto, c.codigo")
})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCodigoSocioCodigoIfip = "Cuenta.findByCodigoSocioCodigoIfip";
    public static final String findBySocioIfipMonedaTipoProductoEstado = "Cuenta.findBySocioIfipMonedaTipoProductoEstado";
    public static final String findByMovimientoCuenta = "Cuenta.findByMovimientoCuenta";
    public static final String findByEstadoCuenta = "Cuenta.findByEstadoCuenta";
    public static final String findByNumero = "Cuenta.findByNumero";
    public static final String findByNumeroAll = "Cuenta.findByNumeroAll";
    public static final String findByPuedeReactivar = "Cuenta.findByPuedeReactivar";
    public static final String findByProductoMonedaPuedeReactivar = "Cuenta.findByProductoMonedaPuedeReactivar";
    public static final String findByCertAportPuedeReactivar = "Cuenta.findByCertAportPuedeReactivar";
    public static final String findByPuedeReactivarAhorros = "Cuenta.findByPuedeReactivarAhorros";
    public static final String findByMonedaProductoIfipEstado = "Cuenta.findByMonedaProductoIfipEstado";
    public static final String findByCodigoSocioCodigoIfipCodigoEstado = "Cuenta.findByCodigoSocioCodigoIfipCodigoEstado"; 
    public static final String findByNumCuentaIfipSocioBenef = "Cuenta.findByNumCuentaIfipSocioBenef";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CUENTA")
    @SequenceGenerator(name = "GSQ_CUENTA", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_CUENTA")
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
    @Column(name = "CODIGO_TIPO_PRODUCTO")
    private long codigoTipoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "CREADA_POR")*/
    @JoinColumn(name = "CREADA_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario creadaPor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NUMERO")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA_NUMERO")
    private int secuenciaNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_DISPONIBLE")
    private BigDecimal saldoDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_BLOQUEADO")
    private BigDecimal saldoBloqueado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_TOTAL")
    private BigDecimal saldoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROVISION_ACUMULADA")
    private BigDecimal provisionAcumulada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "ACTUALIZADO_POR")*/
    @JoinColumn(name = "ACTUALIZADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario actualizadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "CODIGO_PERSONA_BENEFICIARIO")
    private Long codigoPersonaBeneficiario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA_APERTURA")
    private Long codigoIfipAgenciaApertura;

    @JoinColumn(name = "CODIGO_TIPO_FIRMA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoFirma codigoTipoFirma;
    /*@JoinColumn(name = "NUMERO_LIBRETA", referencedColumnName = "NUMERO_LIBRETA")
     @ManyToOne(optional = false)
     private TalonarioLibretaAhorroDet numeroLibreta;*/
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 20)
    @Column(name = "NUMERO_LIBRETA")
    private String numeroLibreta;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoIfip productoIfip;
    
    @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EstadoCuenta codigoEstado;
    
    @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoProducto tipoProducto;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_SOCIO_BENEFICIARIO")
    private char esSocioBeneficiario;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    @JoinColumn(name = "CODIGO_PERSONA_BENEFICIARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private Persona personaBeneficiario;
    public Cuenta() {
    }

    public Cuenta(Long codigo) {
        this.codigo = codigo;
    }

    public Cuenta(Long codigo, Long codigoSocio, Usuario creadaPor, String numero, int secuenciaNumero, BigDecimal saldoDisponible, BigDecimal saldoBloqueado, BigDecimal saldoTotal, BigDecimal provisionAcumulada, Date fechaCreacion, Usuario actualizadoPor, Date fechaActualizacion) {
        this.codigo = codigo;
        this.codigoSocio = codigoSocio;
        this.creadaPor = creadaPor;
        this.numero = numero;
        this.secuenciaNumero = secuenciaNumero;
        this.saldoDisponible = saldoDisponible;
        this.saldoBloqueado = saldoBloqueado;
        this.saldoTotal = saldoTotal;
        this.provisionAcumulada = provisionAcumulada;
        this.fechaCreacion = fechaCreacion;
        this.actualizadoPor = actualizadoPor;
        this.fechaActualizacion = fechaActualizacion;
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

    public Usuario getCreadaPor() {
        return creadaPor;
    }

    public void setCreadaPor(Usuario creadaPor) {
        this.creadaPor = creadaPor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getSecuenciaNumero() {
        return secuenciaNumero;
    }

    public void setSecuenciaNumero(int secuenciaNumero) {
        this.secuenciaNumero = secuenciaNumero;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public BigDecimal getSaldoBloqueado() {
        return saldoBloqueado;
    }

    public void setSaldoBloqueado(BigDecimal saldoBloqueado) {
        this.saldoBloqueado = saldoBloqueado;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public BigDecimal getProvisionAcumulada() {
        return provisionAcumulada;
    }

    public void setProvisionAcumulada(BigDecimal provisionAcumulada) {
        this.provisionAcumulada = provisionAcumulada;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(Usuario actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public TipoFirma getCodigoTipoFirma() {
        return codigoTipoFirma;
    }

    public void setCodigoTipoFirma(TipoFirma codigoTipoFirma) {
        this.codigoTipoFirma = codigoTipoFirma;
    }

    public String getNumeroLibreta() {
        return numeroLibreta;
    }

    public void setNumeroLibreta(String numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }

    public ProductoIfip getProductoIfip() {
        return productoIfip;
    }

    public void setProductoIfip(ProductoIfip productoIfip) {
        this.productoIfip = productoIfip;
    }

    public EstadoCuenta getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(EstadoCuenta codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    /*@XmlTransient
     public Collection<FirmanteCuenta> getFirmanteCuentaCollection() {
     return firmanteCuentaCollection;
     }

     public void setFirmanteCuentaCollection(Collection<FirmanteCuenta> firmanteCuentaCollection) {
     this.firmanteCuentaCollection = firmanteCuentaCollection;
     }

     @XmlTransient
     public Collection<ProvisionDiariaIntCtaDet> getProvisionDiariaIntCtaDetCollection() {
     return provisionDiariaIntCtaDetCollection;
     }

     public void setProvisionDiariaIntCtaDetCollection(Collection<ProvisionDiariaIntCtaDet> provisionDiariaIntCtaDetCollection) {
     this.provisionDiariaIntCtaDetCollection = provisionDiariaIntCtaDetCollection;
     }

     @XmlTransient
     public Collection<BloqueoCuenta> getBloqueoCuentaCollection() {
     return bloqueoCuentaCollection;
     }

     public void setBloqueoCuentaCollection(Collection<BloqueoCuenta> bloqueoCuentaCollection) {
     this.bloqueoCuentaCollection = bloqueoCuentaCollection;
     }

     @XmlTransient
     public Collection<MovimientoCuenta> getMovimientoCuentaCollection() {
     return movimientoCuentaCollection;
     }

     public void setMovimientoCuentaCollection(Collection<MovimientoCuenta> movimientoCuentaCollection) {
     this.movimientoCuentaCollection = movimientoCuentaCollection;
     }

     @XmlTransient
     public Collection<LibretaAsignadaCuenta> getLibretaAsignadaCuentaCollection() {
     return libretaAsignadaCuentaCollection;
     }

     public void setLibretaAsignadaCuentaCollection(Collection<LibretaAsignadaCuenta> libretaAsignadaCuentaCollection) {
     this.libretaAsignadaCuentaCollection = libretaAsignadaCuentaCollection;
     }

     public ProvisionInteresCuenta getProvisionInteresCuenta() {
     return provisionInteresCuenta;
     }

     public void setProvisionInteresCuenta(ProvisionInteresCuenta provisionInteresCuenta) {
     this.provisionInteresCuenta = provisionInteresCuenta;
     }
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.Cuenta[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoTipoProducto
     */
    public long getCodigoTipoProducto() {
        return codigoTipoProducto;
    }

    /**
     * @param codigoTipoProducto the codigoTipoProducto to set
     */
    public void setCodigoTipoProducto(long codigoTipoProducto) {
        this.codigoTipoProducto = codigoTipoProducto;
    }

    /**
     * @return the codigoMoneda
     */
    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * @param codigoMoneda the codigoMoneda to set
     */
    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    /**
     * @return the codigoIfip
     */
    public long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the tipoProducto
     */
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the esSocioBeneficiario
     */
    public char getEsSocioBeneficiario() {
        return esSocioBeneficiario;
    }

    /**
     * @param esSocioBeneficiario the esSocioBeneficiario to set
     */
    public void setEsSocioBeneficiario(char esSocioBeneficiario) {
        this.esSocioBeneficiario = esSocioBeneficiario;
    }

    /**
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
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
     * @return the codigoPersonaBeneficiario
     */
    public Long getCodigoPersonaBeneficiario() {
        return codigoPersonaBeneficiario;
    }

    /**
     * @param codigoPersonaBeneficiario the codigoPersonaBeneficiario to set
     */
    public void setCodigoPersonaBeneficiario(Long codigoPersonaBeneficiario) {
        this.codigoPersonaBeneficiario = codigoPersonaBeneficiario;
    }

    /**
     * @return the codigoIfipAgenciaApertura
     */
    public Long getCodigoIfipAgenciaApertura() {
        return codigoIfipAgenciaApertura;
    }

    /**
     * @param codigoIfipAgenciaApertura the codigoIfipAgenciaApertura to set
     */
    public void setCodigoIfipAgenciaApertura(Long codigoIfipAgenciaApertura) {
        this.codigoIfipAgenciaApertura = codigoIfipAgenciaApertura;
    }

    /**
     * @return the personaBeneficiario
     */
    public Persona getPersonaBeneficiario() {
        return personaBeneficiario;
    }

    /**
     * @param personaBeneficiario the personaBeneficiario to set
     */
    public void setPersonaBeneficiario(Persona personaBeneficiario) {
        this.personaBeneficiario = personaBeneficiario;
    }

  
}
