/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Mascoop
 */
@Entity
@Table(name = "SOLICITUD_DEVUELTA", catalog = "", schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudDevuelta.findAll", query = "SELECT s FROM SolicitudDevuelta s")
    , @NamedQuery(name = "SolicitudDevuelta.findByNumeroCredito", query = "SELECT s FROM SolicitudDevuelta s WHERE s.numeroCredito = :numeroCredito AND s.codigoIfip = :codigoIfip AND s.vigente = 'S'")
    })
public class SolicitudDevuelta implements Serializable {
    public static String findByNumeroCredito = "SolicitudDevuelta.findByNumeroCredito";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR", nullable = false)
    private long registradoPor;
    @Size(max = 500)
    @Column(length = 500)
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO_CREDITO", nullable = false)
    private long codigoEstadoCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO_CREDITO_DEVUELTO", nullable = false)
    private long codigoEstadoCreditoDevuelto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CREDITO", nullable = false)
    private long numeroCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP", nullable = false)
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOLICITUD_MOTIVO_DEV", nullable = false)
    private long codigoSolicitudMotivoDev;
    @Size(max = 1)
    @Column(length = 1)
    private String vigente;
    

    public SolicitudDevuelta() {
    }

    public SolicitudDevuelta(Long codigo) {
        this.codigo = codigo;
    }

    public SolicitudDevuelta(Long codigo, Date fechaRegistro, long registradoPor, long codigoEstadoCredito, long codigoEstadoCreditoDevuelto, long numeroCredito, long codigoIfip, long codigoSolicitudMotivoDev) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
        this.codigoEstadoCredito = codigoEstadoCredito;
        this.codigoEstadoCreditoDevuelto = codigoEstadoCreditoDevuelto;
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
        this.codigoSolicitudMotivoDev = codigoSolicitudMotivoDev;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public long getCodigoEstadoCredito() {
        return codigoEstadoCredito;
    }

    public void setCodigoEstadoCredito(long codigoEstadoCredito) {
        this.codigoEstadoCredito = codigoEstadoCredito;
    }

    public long getCodigoEstadoCreditoDevuelto() {
        return codigoEstadoCreditoDevuelto;
    }

    public void setCodigoEstadoCreditoDevuelto(long codigoEstadoCreditoDevuelto) {
        this.codigoEstadoCreditoDevuelto = codigoEstadoCreditoDevuelto;
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

    public long getCodigoSolicitudMotivoDev() {
        return codigoSolicitudMotivoDev;
    }

    public void setCodigoSolicitudMotivoDev(long codigoSolicitudMotivoDev) {
        this.codigoSolicitudMotivoDev = codigoSolicitudMotivoDev;
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
        if (!(object instanceof SolicitudDevuelta)) {
            return false;
        }
        SolicitudDevuelta other = (SolicitudDevuelta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.SolicitudDevuelta[ codigo=" + codigo + " ]";
    }

    /**
     * @return the vigente
     */
    public String getVigente() {
        return vigente;
    }

    /**
     * @param vigente the vigente to set
     */
    public void setVigente(String vigente) {
        this.vigente = vigente;
    }
    
}
