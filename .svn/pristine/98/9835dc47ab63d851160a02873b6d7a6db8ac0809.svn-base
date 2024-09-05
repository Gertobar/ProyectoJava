/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ahorros.MovimientoCuenta;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PAGO_CREDITO_MOVIMIENTO_CUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCreditoMovimientoCue.findAll", query = "SELECT p FROM PagoCreditoMovimientoCue p"),
    @NamedQuery(name = "PagoCreditoMovimientoCue.findByCodigoMovimiento", query = "SELECT p FROM PagoCreditoMovimientoCue p WHERE p.codigoMovimiento = :codigoMovimiento")})
public class PagoCreditoMovimientoCue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private Long codigoMovimiento;
    @JoinColumn(name = "CODIGO_PAGO_CREDITO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PagoCredito codigoPagoCredito;
    @JoinColumn(name = "CODIGO_MOVIMIENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MovimientoCuenta movimientoCuenta;

    public PagoCreditoMovimientoCue() {
    }

    public PagoCreditoMovimientoCue(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public Long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public PagoCredito getCodigoPagoCredito() {
        return codigoPagoCredito;
    }

    public void setCodigoPagoCredito(PagoCredito codigoPagoCredito) {
        this.codigoPagoCredito = codigoPagoCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMovimiento != null ? codigoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCreditoMovimientoCue)) {
            return false;
        }
        PagoCreditoMovimientoCue other = (PagoCreditoMovimientoCue) object;
        if ((this.codigoMovimiento == null && other.codigoMovimiento != null) || (this.codigoMovimiento != null && !this.codigoMovimiento.equals(other.codigoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PagoCreditoMovimientoCue[ codigoMovimiento=" + codigoMovimiento + " ]";
    }

    /**
     * @return the movimientoCuenta
     */
    public MovimientoCuenta getMovimientoCuenta() {
        return movimientoCuenta;
    }

    /**
     * @param movimientoCuenta the movimientoCuenta to set
     */
    public void setMovimientoCuenta(MovimientoCuenta movimientoCuenta) {
        this.movimientoCuenta = movimientoCuenta;
    }
    
}
