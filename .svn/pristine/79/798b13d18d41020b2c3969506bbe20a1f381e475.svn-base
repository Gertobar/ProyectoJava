/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.APERTURA_CAJA_MOVIMIENTO_CTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AperturaCajaMovimientoCta.findAll", query = "SELECT a FROM AperturaCajaMovimientoCta a"),
    @NamedQuery(name = "AperturaCajaMovimientoCta.findByCodigoMovimiento", query = "SELECT a FROM AperturaCajaMovimientoCta a WHERE a.codigoMovimiento = :codigoMovimiento"),
    @NamedQuery(name = "AperturaCajaMovimientoCta.findByEstado", query = "SELECT a FROM AperturaCajaMovimientoCta a WHERE a.estado = :estado")})
public class AperturaCajaMovimientoCta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private Long codigoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Apertura codigoApertura;

    public AperturaCajaMovimientoCta() {
    }

    public AperturaCajaMovimientoCta(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public AperturaCajaMovimientoCta(Long codigoMovimiento, char estado) {
        this.codigoMovimiento = codigoMovimiento;
        this.estado = estado;
    }

    public Long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Apertura getCodigoApertura() {
        return codigoApertura;
    }

    public void setCodigoApertura(Apertura codigoApertura) {
        this.codigoApertura = codigoApertura;
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
        if (!(object instanceof AperturaCajaMovimientoCta)) {
            return false;
        }
        AperturaCajaMovimientoCta other = (AperturaCajaMovimientoCta) object;
        if ((this.codigoMovimiento == null && other.codigoMovimiento != null) || (this.codigoMovimiento != null && !this.codigoMovimiento.equals(other.codigoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.AperturaCajaMovimientoCta[ codigoMovimiento=" + codigoMovimiento + " ]";
    }
    
}
