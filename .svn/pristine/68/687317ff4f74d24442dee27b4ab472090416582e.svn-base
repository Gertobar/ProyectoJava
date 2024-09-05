/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.EJECUCION_TRANSACCION_LOT_ERR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjecucionTransaccionLotErr.findAll", query = "SELECT e FROM EjecucionTransaccionLotErr e"),
    @NamedQuery(name = "EjecucionTransaccionLotErr.findByCodigo", query = "SELECT e FROM EjecucionTransaccionLotErr e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EjecucionTransaccionLotErr.findByNumeroCuenta", query = "SELECT e FROM EjecucionTransaccionLotErr e WHERE e.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "EjecucionTransaccionLotErr.findByValorTransaccion", query = "SELECT e FROM EjecucionTransaccionLotErr e WHERE e.valorTransaccion = :valorTransaccion"),
    @NamedQuery(name = "EjecucionTransaccionLotErr.findByError", query = "SELECT e FROM EjecucionTransaccionLotErr e WHERE e.error = :error")})
public class EjecucionTransaccionLotErr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_TRANSACCION")
    private BigDecimal valorTransaccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ERROR")
    private String error;
    @JoinColumn(name = "CODIGO_EJECUCION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EjecucionTransaccionLote codigoEjecucion;

    public EjecucionTransaccionLotErr() {
    }

    public EjecucionTransaccionLotErr(Long codigo) {
        this.codigo = codigo;
    }

    public EjecucionTransaccionLotErr(Long codigo, String numeroCuenta, BigDecimal valorTransaccion, String error) {
        this.codigo = codigo;
        this.numeroCuenta = numeroCuenta;
        this.valorTransaccion = valorTransaccion;
        this.error = error;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(BigDecimal valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public EjecucionTransaccionLote getCodigoEjecucion() {
        return codigoEjecucion;
    }

    public void setCodigoEjecucion(EjecucionTransaccionLote codigoEjecucion) {
        this.codigoEjecucion = codigoEjecucion;
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
        if (!(object instanceof EjecucionTransaccionLotErr)) {
            return false;
        }
        EjecucionTransaccionLotErr other = (EjecucionTransaccionLotErr) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.EjecucionTransaccionLotErr[ codigo=" + codigo + " ]";
    }
    
}
