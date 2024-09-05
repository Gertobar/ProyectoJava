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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.EJECUCION_TRANSACCION_LOT_DET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjecucionTransaccionLotDet.findAll", query = "SELECT e FROM EjecucionTransaccionLotDet e"),
    @NamedQuery(name = "EjecucionTransaccionLotDet.findByCodigo", query = "SELECT e FROM EjecucionTransaccionLotDet e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EjecucionTransaccionLotDet.findByValorTransaccion", query = "SELECT e FROM EjecucionTransaccionLotDet e WHERE e.valorTransaccion = :valorTransaccion")})
public class EjecucionTransaccionLotDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_TRANSACCION")
    private BigDecimal valorTransaccion;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MovimientoCuenta codigoMovimiento;
    @JoinColumn(name = "CODIGO_EJECUCION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EjecucionTransaccionLote codigoEjecucion;
    @JoinColumn(name = "CODIGO_CUENTA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Cuenta codigoCuenta;

    public EjecucionTransaccionLotDet() {
    }

    public EjecucionTransaccionLotDet(Long codigo) {
        this.codigo = codigo;
    }

    public EjecucionTransaccionLotDet(Long codigo, BigDecimal valorTransaccion) {
        this.codigo = codigo;
        this.valorTransaccion = valorTransaccion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(BigDecimal valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public MovimientoCuenta getCodigoMovmiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovmiento(MovimientoCuenta codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public EjecucionTransaccionLote getCodigoEjecucion() {
        return codigoEjecucion;
    }

    public void setCodigoEjecucion(EjecucionTransaccionLote codigoEjecucion) {
        this.codigoEjecucion = codigoEjecucion;
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
        if (!(object instanceof EjecucionTransaccionLotDet)) {
            return false;
        }
        EjecucionTransaccionLotDet other = (EjecucionTransaccionLotDet) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.EjecucionTransaccionLotDet[ codigo=" + codigo + " ]";
    }
    
}
