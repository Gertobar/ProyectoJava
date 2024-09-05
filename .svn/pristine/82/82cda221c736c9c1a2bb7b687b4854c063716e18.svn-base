/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.APERTURA_CAJA_FONDEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AperturaCajaFondeo.findAll", query = "SELECT a FROM AperturaCajaFondeo a"),
    @NamedQuery(name = "AperturaCajaFondeo.findByCodigoApertura", query = "SELECT a FROM AperturaCajaFondeo a WHERE a.codigoApertura = :codigoApertura"),
    @NamedQuery(name = "AperturaCajaFondeo.findByFecha", query = "SELECT a FROM AperturaCajaFondeo a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AperturaCajaFondeo.findByCodigoFondeo", query = "SELECT a FROM AperturaCajaFondeo a WHERE a.codigoFondeo = :codigoFondeo")})
public class AperturaCajaFondeo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoApertura = "AperturaCajaFondeo.findByCodigoApertura";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_APERTURA")
    private Long codigoApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "CODIGO_FONDEO", referencedColumnName = "CODIGO")
    @OneToOne(optional = false)
    private FondeoCaja codigoFondeo;
    @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Apertura apertura;

    public AperturaCajaFondeo() {
    }

    public AperturaCajaFondeo(Long codigoApertura) {
        this.codigoApertura = codigoApertura;
    }

    public AperturaCajaFondeo(Long codigoApertura, Date fecha) {
        this.codigoApertura = codigoApertura;
        this.fecha = fecha;
    }

    public Long getCodigoApertura() {
        return codigoApertura;
    }

    public void setCodigoApertura(Long codigoApertura) {
        this.codigoApertura = codigoApertura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public FondeoCaja getCodigoFondeo() {
        return codigoFondeo;
    }

    public void setCodigoFondeo(FondeoCaja codigoFondeo) {
        this.codigoFondeo = codigoFondeo;
    }

    public Apertura getApertura() {
        return apertura;
    }

    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoApertura != null ? codigoApertura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AperturaCajaFondeo)) {
            return false;
        }
        AperturaCajaFondeo other = (AperturaCajaFondeo) object;
        if ((this.codigoApertura == null && other.codigoApertura != null) || (this.codigoApertura != null && !this.codigoApertura.equals(other.codigoApertura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.AperturaCajaFondeo[ codigoApertura=" + codigoApertura + " ]";
    }
    
}
