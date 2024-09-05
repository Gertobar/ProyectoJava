/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.cajas.IngresoEgresoCaja;
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
@Table(name = "MKS_CREDITOS.PAGO_CREDITO_ING_EGR_CAJ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCreditoIngEgrCaj.findAll", query = "SELECT p FROM PagoCreditoIngEgrCaj p"),
    @NamedQuery(name = "PagoCreditoIngEgrCaj.findByCodigoIngresoEgreso", query = "SELECT p FROM PagoCreditoIngEgrCaj p WHERE p.codigoIngresoEgreso = :codigoIngresoEgreso")})
public class PagoCreditoIngEgrCaj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_INGRESO_EGRESO")
    private Long codigoIngresoEgreso;
    @JoinColumn(name = "CODIGO_PAGO_CREDITO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PagoCredito codigoPagoCredito;
    @JoinColumn(name = "CODIGO_INGRESO_EGRESO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private IngresoEgresoCaja ingresoEgresoCaja;

    public PagoCreditoIngEgrCaj() {
    }

    public PagoCreditoIngEgrCaj(Long codigoIngresoEgreso) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
    }

    public Long getCodigoIngresoEgreso() {
        return codigoIngresoEgreso;
    }

    public void setCodigoIngresoEgreso(Long codigoIngresoEgreso) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
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
        hash += (codigoIngresoEgreso != null ? codigoIngresoEgreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCreditoIngEgrCaj)) {
            return false;
        }
        PagoCreditoIngEgrCaj other = (PagoCreditoIngEgrCaj) object;
        if ((this.codigoIngresoEgreso == null && other.codigoIngresoEgreso != null) || (this.codigoIngresoEgreso != null && !this.codigoIngresoEgreso.equals(other.codigoIngresoEgreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PagoCreditoIngEgrCaj[ codigoIngresoEgreso=" + codigoIngresoEgreso + " ]";
    }

    /**
     * @return the ingresoEgresoCaja
     */
    public IngresoEgresoCaja getIngresoEgresoCaja() {
        return ingresoEgresoCaja;
    }

    /**
     * @param ingresoEgresoCaja the ingresoEgresoCaja to set
     */
    public void setIngresoEgresoCaja(IngresoEgresoCaja ingresoEgresoCaja) {
        this.ingresoEgresoCaja = ingresoEgresoCaja;
    }
    
}
