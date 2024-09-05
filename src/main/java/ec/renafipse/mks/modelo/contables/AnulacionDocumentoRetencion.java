/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.ANULACION_DOCUMENTO_RETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnulacionDocumentoRetencion.findAll", query = "SELECT a FROM AnulacionDocumentoRetencion a"),
    @NamedQuery(name = "AnulacionDocumentoRetencion.findByCodigoDocumento", query = "SELECT a FROM AnulacionDocumentoRetencion a WHERE a.codigoDocumento = :codigoDocumento"),
    @NamedQuery(name = "AnulacionDocumentoRetencion.findByAnuladoPor", query = "SELECT a FROM AnulacionDocumentoRetencion a WHERE a.anuladoPor = :anuladoPor"),
    @NamedQuery(name = "AnulacionDocumentoRetencion.findByFechaAnulacion", query = "SELECT a FROM AnulacionDocumentoRetencion a WHERE a.fechaAnulacion = :fechaAnulacion"),
    @NamedQuery(name = "AnulacionDocumentoRetencion.findByObservaciones", query = "SELECT a FROM AnulacionDocumentoRetencion a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "AnulacionDocumentoRetencion.findByFechaSistema", query = "SELECT a FROM AnulacionDocumentoRetencion a WHERE a.fechaSistema = :fechaSistema")})
public class AnulacionDocumentoRetencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_DOCUMENTO")
    private Long codigoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANULADO_POR")
    private long anuladoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ANULACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @JoinColumn(name = "CODIGO_DOCUMENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TalonarioDocumentoRetDet talonarioDocumentoRetDet;

    public AnulacionDocumentoRetencion() {
    }

    public AnulacionDocumentoRetencion(Long codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public AnulacionDocumentoRetencion(Long codigoDocumento, long anuladoPor, Date fechaAnulacion, String observaciones, Date fechaSistema) {
        this.codigoDocumento = codigoDocumento;
        this.anuladoPor = anuladoPor;
        this.fechaAnulacion = fechaAnulacion;
        this.observaciones = observaciones;
        this.fechaSistema = fechaSistema;
    }

    public Long getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(Long codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public long getAnuladoPor() {
        return anuladoPor;
    }

    public void setAnuladoPor(long anuladoPor) {
        this.anuladoPor = anuladoPor;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public TalonarioDocumentoRetDet getTalonarioDocumentoRetDet() {
        return talonarioDocumentoRetDet;
    }

    public void setTalonarioDocumentoRetDet(TalonarioDocumentoRetDet talonarioDocumentoRetDet) {
        this.talonarioDocumentoRetDet = talonarioDocumentoRetDet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDocumento != null ? codigoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnulacionDocumentoRetencion)) {
            return false;
        }
        AnulacionDocumentoRetencion other = (AnulacionDocumentoRetencion) object;
        if ((this.codigoDocumento == null && other.codigoDocumento != null) || (this.codigoDocumento != null && !this.codigoDocumento.equals(other.codigoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.AnulacionDocumentoRetencion[ codigoDocumento=" + codigoDocumento + " ]";
    }
    
}
