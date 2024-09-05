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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.SOLICITUD_PAGARE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudPagare.findAll", query = "SELECT s FROM SolicitudPagare s"),
    @NamedQuery(name = "SolicitudPagare.findByNumeroCredito", query = "SELECT s FROM SolicitudPagare s WHERE s.solicitudPagarePK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "SolicitudPagare.findByCodigoIfip", query = "SELECT s FROM SolicitudPagare s WHERE s.solicitudPagarePK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "SolicitudPagare.findByNumeroPagare", query = "SELECT s FROM SolicitudPagare s WHERE s.numeroPagare = :numeroPagare"),
    @NamedQuery(name = "SolicitudPagare.findByRegistradoPor", query = "SELECT s FROM SolicitudPagare s WHERE s.registradoPor = :registradoPor"),
    @NamedQuery(name = "SolicitudPagare.findByFechaRegistro", query = "SELECT s FROM SolicitudPagare s WHERE s.fechaRegistro = :fechaRegistro")})
public class SolicitudPagare implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudPagarePK solicitudPagarePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_PAGARE")
    private long numeroPagare;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public SolicitudPagare() {
    }

    public SolicitudPagare(SolicitudPagarePK solicitudPagarePK) {
        this.solicitudPagarePK = solicitudPagarePK;
    }

    public SolicitudPagare(SolicitudPagarePK solicitudPagarePK, long numeroPagare, long registradoPor, Date fechaRegistro) {
        this.solicitudPagarePK = solicitudPagarePK;
        this.numeroPagare = numeroPagare;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
    }

    public SolicitudPagare(long numeroCredito, long codigoIfip) {
        this.solicitudPagarePK = new SolicitudPagarePK(numeroCredito, codigoIfip);
    }

    public SolicitudPagarePK getSolicitudPagarePK() {
        return solicitudPagarePK;
    }

    public void setSolicitudPagarePK(SolicitudPagarePK solicitudPagarePK) {
        this.solicitudPagarePK = solicitudPagarePK;
    }

    public long getNumeroPagare() {
        return numeroPagare;
    }

    public void setNumeroPagare(long numeroPagare) {
        this.numeroPagare = numeroPagare;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudPagarePK != null ? solicitudPagarePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudPagare)) {
            return false;
        }
        SolicitudPagare other = (SolicitudPagare) object;
        if ((this.solicitudPagarePK == null && other.solicitudPagarePK != null) || (this.solicitudPagarePK != null && !this.solicitudPagarePK.equals(other.solicitudPagarePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.SolicitudPagare[ solicitudPagarePK=" + solicitudPagarePK + " ]";
    }
    
}
