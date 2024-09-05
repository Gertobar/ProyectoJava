/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.CIERRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cierre.findAll", query = "SELECT c FROM Cierre c")
    ,
    @NamedQuery(name = "Cierre.findByCodigoApertura", query = "SELECT c FROM Cierre c WHERE c.codigoApertura = :codigoApertura")
    ,
    @NamedQuery(name = "Cierre.findByFechaCierre", query = "SELECT c FROM Cierre c WHERE c.fechaCierre = :fechaCierre")
    ,
    @NamedQuery(name = "Cierre.findByFechaSistema", query = "SELECT c FROM Cierre c WHERE c.fechaSistema = :fechaSistema")
    ,
    @NamedQuery(name = "Cierre.findByCuadrada", query = "SELECT c FROM Cierre c WHERE c.cuadrada = :cuadrada")})
public class Cierre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_APERTURA")
    private Long codigoApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUADRADA")
    private char cuadrada;
    @JoinColumn(name = "CODIGO_APERTURA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Apertura apertura;
    @Basic(optional = true)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cierre", fetch = FetchType.LAZY)
    private Collection<CierreDetalle> cierreDetalleCollection;

    public Cierre() {
    }

    public Cierre(Long codigoApertura) {
        this.codigoApertura = codigoApertura;
    }

    public Cierre(Long codigoApertura, Date fechaCierre, Date fechaSistema, char cuadrada) {
        this.codigoApertura = codigoApertura;
        this.fechaCierre = fechaCierre;
        this.fechaSistema = fechaSistema;
        this.cuadrada = cuadrada;
    }

    public Long getCodigoApertura() {
        return codigoApertura;
    }

    public void setCodigoApertura(Long codigoApertura) {
        this.codigoApertura = codigoApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public char getCuadrada() {
        return cuadrada;
    }

    public void setCuadrada(char cuadrada) {
        this.cuadrada = cuadrada;
    }

    public Apertura getApertura() {
        return apertura;
    }

    public void setApertura(Apertura apertura) {
        this.apertura = apertura;
    }

    @XmlTransient
    public Collection<CierreDetalle> getCierreDetalleCollection() {
        return cierreDetalleCollection;
    }

    public void setCierreDetalleCollection(Collection<CierreDetalle> cierreDetalleCollection) {
        this.cierreDetalleCollection = cierreDetalleCollection;
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
        if (!(object instanceof Cierre)) {
            return false;
        }
        Cierre other = (Cierre) object;
        if ((this.codigoApertura == null && other.codigoApertura != null) || (this.codigoApertura != null && !this.codigoApertura.equals(other.codigoApertura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.Cierre[ codigoApertura=" + codigoApertura + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
