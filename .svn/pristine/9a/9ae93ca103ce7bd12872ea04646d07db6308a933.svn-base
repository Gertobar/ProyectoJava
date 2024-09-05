/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.TRANSFERENCIA_ENTRE_CUENTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferenciaEntreCuentas.findAll", query = "SELECT t FROM TransferenciaEntreCuentas t"),
    @NamedQuery(name = "TransferenciaEntreCuentas.findByCodigo", query = "SELECT t FROM TransferenciaEntreCuentas t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TransferenciaEntreCuentas.findByValorTransferido", query = "SELECT t FROM TransferenciaEntreCuentas t WHERE t.valorTransferido = :valorTransferido"),
    @NamedQuery(name = "TransferenciaEntreCuentas.findByFechaTransferencia", query = "SELECT t FROM TransferenciaEntreCuentas t WHERE t.fechaTransferencia = :fechaTransferencia"),
    @NamedQuery(name = "TransferenciaEntreCuentas.findByDireccionIp", query = "SELECT t FROM TransferenciaEntreCuentas t WHERE t.direccionIp = :direccionIp"),
    @NamedQuery(name = "TransferenciaEntreCuentas.findByObservaciones", query = "SELECT t FROM TransferenciaEntreCuentas t WHERE t.observaciones = :observaciones"),
    @NamedQuery(name = "TransferenciaEntreCuentas.findByMismoSocio", query = "SELECT t FROM TransferenciaEntreCuentas t WHERE t.mismoSocio = :mismoSocio")})
public class TransferenciaEntreCuentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_TRANSFERIDO")
    private BigDecimal valorTransferido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_TRANSFERENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DIRECCION_IP")
    private String direccionIp;
    @Size(max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MISMO_SOCIO")
    private char mismoSocio;
    @JoinColumn(name = "CODIGO_MOVIMIENTO_DEBITO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MovimientoCuenta codigoMovimientoDebito;
    @JoinColumn(name = "CODIGO_MOVIMIENTO_CREDITO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MovimientoCuenta codigoMovimientoCredito;
    @JoinColumn(name = "CODIGO_CUENTA_ORIGEN", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Cuenta codigoCuentaOrigen;
    @JoinColumn(name = "CODIGO_CUENTA_DESTINO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Cuenta codigoCuentaDestino;
    @JoinColumn(name = "CODIGO_CONCEPTO_TRANSFERENCIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ConceptoTransaccionTransf codigoConceptoTransferencia;
    @JoinColumn(name = "CODIGO_CANAL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Canal codigoCanal;

    public TransferenciaEntreCuentas() {
    }

    public TransferenciaEntreCuentas(Long codigo) {
        this.codigo = codigo;
    }

    public TransferenciaEntreCuentas(Long codigo, BigDecimal valorTransferido, Date fechaTransferencia, String direccionIp, char mismoSocio) {
        this.codigo = codigo;
        this.valorTransferido = valorTransferido;
        this.fechaTransferencia = fechaTransferencia;
        this.direccionIp = direccionIp;
        this.mismoSocio = mismoSocio;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValorTransferido() {
        return valorTransferido;
    }

    public void setValorTransferido(BigDecimal valorTransferido) {
        this.valorTransferido = valorTransferido;
    }

    public Date getFechaTransferencia() {
        return fechaTransferencia;
    }

    public void setFechaTransferencia(Date fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getMismoSocio() {
        return mismoSocio;
    }

    public void setMismoSocio(char mismoSocio) {
        this.mismoSocio = mismoSocio;
    }

    public MovimientoCuenta getCodigoMovimientoDebito() {
        return codigoMovimientoDebito;
    }

    public void setCodigoMovimientoDebito(MovimientoCuenta codigoMovimientoDebito) {
        this.codigoMovimientoDebito = codigoMovimientoDebito;
    }

    public MovimientoCuenta getCodigoMovimientoCredito() {
        return codigoMovimientoCredito;
    }

    public void setCodigoMovimientoCredito(MovimientoCuenta codigoMovimientoCredito) {
        this.codigoMovimientoCredito = codigoMovimientoCredito;
    }

    public Cuenta getCodigoCuentaOrigen() {
        return codigoCuentaOrigen;
    }

    public void setCodigoCuentaOrigen(Cuenta codigoCuentaOrigen) {
        this.codigoCuentaOrigen = codigoCuentaOrigen;
    }

    public Cuenta getCodigoCuentaDestino() {
        return codigoCuentaDestino;
    }

    public void setCodigoCuentaDestino(Cuenta codigoCuentaDestino) {
        this.codigoCuentaDestino = codigoCuentaDestino;
    }

    public ConceptoTransaccionTransf getCodigoConceptoTransferencia() {
        return codigoConceptoTransferencia;
    }

    public void setCodigoConceptoTransferencia(ConceptoTransaccionTransf codigoConceptoTransferencia) {
        this.codigoConceptoTransferencia = codigoConceptoTransferencia;
    }

    public Canal getCodigoCanal() {
        return codigoCanal;
    }

    public void setCodigoCanal(Canal codigoCanal) {
        this.codigoCanal = codigoCanal;
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
        if (!(object instanceof TransferenciaEntreCuentas)) {
            return false;
        }
        TransferenciaEntreCuentas other = (TransferenciaEntreCuentas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TransferenciaEntreCuentas[ codigo=" + codigo + " ]";
    }
    
}
