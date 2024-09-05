/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

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
import javax.persistence.JoinColumns;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.NOTIFICACION_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificacionCredito.findAll", query = "SELECT n FROM NotificacionCredito n"),
    @NamedQuery(name = "NotificacionCredito.findByCodigo", query = "SELECT n FROM NotificacionCredito n WHERE n.codigo = :codigo"),
    @NamedQuery(name = "NotificacionCredito.findByEsParaGarante", query = "SELECT n FROM NotificacionCredito n WHERE n.esParaGarante = :esParaGarante"),
    @NamedQuery(name = "NotificacionCredito.findByEstado", query = "SELECT n FROM NotificacionCredito n WHERE n.estado = :estado"),
    @NamedQuery(name = "NotificacionCredito.findByValor", query = "SELECT n FROM NotificacionCredito n WHERE n.valor = :valor"),
    @NamedQuery(name = "NotificacionCredito.findByAbono", query = "SELECT n FROM NotificacionCredito n WHERE n.abono = :abono"),
    @NamedQuery(name = "NotificacionCredito.findBySaldo", query = "SELECT n FROM NotificacionCredito n WHERE n.saldo = :saldo"),
    @NamedQuery(name = "NotificacionCredito.findByRegistradoPor", query = "SELECT n FROM NotificacionCredito n WHERE n.usuarioRegistradoPor = :usuarioRegistradoPor"),
    @NamedQuery(name = "NotificacionCredito.findByFechaRegistro", query = "SELECT n FROM NotificacionCredito n WHERE n.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "NotificacionCredito.findByEliminado", query = "SELECT n FROM NotificacionCredito n WHERE n.eliminado = :eliminado")})
public class NotificacionCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_NOTIFICACION_CREDITO")
    @SequenceGenerator(name = "GSQ_NOTIFICACION_CREDITO", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_NOTIFICACION_CREDITO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_GARANTE")
    private char esParaGarante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "ABONO")
    private BigDecimal abono;
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CREDITO")
    private long numeroCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUOTA")
    private long cuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_NOTIFICACION")
    private long codigoTipoNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANCELADA")
    private char cancelada;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_NOTIFICACION", referencedColumnName = "CODIGO_TIPO_NOTIFICACION", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TipoNotificacionIfip tipoNotificacionIfip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notificacionCredito", fetch = FetchType.LAZY)
    private Collection<PagoCreditoDetalleNot> pagoCreditoDetalleNotCollection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR", insertable = false, updatable = false)
    private Long usuarioRegistradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL_CUOTA")
    private BigDecimal saldoCapitalCuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private Long diasMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    
    public NotificacionCredito() {
    }

    public NotificacionCredito(Long codigo) {
        this.codigo = codigo;
    }

    public NotificacionCredito(Long codigo, char esParaGarante, char estado, BigDecimal valor, long usuarioRegistradoPor, Date fechaRegistro, char eliminado, BigDecimal saldoCapitalCuota, Long diasMora, String observaciones) {
        this.codigo = codigo;
        this.esParaGarante = esParaGarante;
        this.estado = estado;
        this.valor = valor;
        this.usuarioRegistradoPor = usuarioRegistradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
        this.saldoCapitalCuota = saldoCapitalCuota;
        this.diasMora = diasMora;
        this.observaciones = observaciones;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public char getEsParaGarante() {
        return esParaGarante;
    }

    public void setEsParaGarante(char esParaGarante) {
        this.esParaGarante = esParaGarante;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }
    
    public TipoNotificacionIfip getTipoNotificacionIfip() {
        return tipoNotificacionIfip;
    }

    public void setTipoNotificacionIfip(TipoNotificacionIfip tipoNotificacionIfip) {
        this.tipoNotificacionIfip = tipoNotificacionIfip;
    }
    
    @XmlTransient
    public Collection<PagoCreditoDetalleNot> getPagoCreditoDetalleNotCollection() {
        return pagoCreditoDetalleNotCollection;
    }

    public void setPagoCreditoDetalleNotCollection(Collection<PagoCreditoDetalleNot> pagoCreditoDetalleNotCollection) {
        this.pagoCreditoDetalleNotCollection = pagoCreditoDetalleNotCollection;
    }
    
    public Long getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    public void setUsuarioRegistradoPor(Long usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }

    public char getCancelada() {
        return cancelada;
    }

    public void setCancelada(char cancelada) {
        this.cancelada = cancelada;
    }

    public long getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoTipoNotificacion() {
        return codigoTipoNotificacion;
    }

    public void setCodigoTipoNotificacion(long codigoTipoNotificacion) {
        this.codigoTipoNotificacion = codigoTipoNotificacion;
    }

    public long getCuota() {
        return cuota;
    }

    public void setCuota(long cuota) {
        this.cuota = cuota;
    }

    public BigDecimal getSaldoCapitalCuota() {
        return saldoCapitalCuota;
    }

    public void setSaldoCapitalCuota(BigDecimal saldoCapitalCuota) {
        this.saldoCapitalCuota = saldoCapitalCuota;
    }

    public Long getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(Long diasMora) {
        this.diasMora = diasMora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof NotificacionCredito)) {
            return false;
        }
        NotificacionCredito other = (NotificacionCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.NotificacionCredito[ codigo=" + codigo + " ]";
    }
}