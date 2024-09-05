/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_APROBAR_EXC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosAprobarExc.findAll", query = "SELECT l FROM LicitudFondosAprobarExc l"),
    @NamedQuery(name = "LicitudFondosAprobarExc.findByCodigoExcepcion", query = "SELECT l FROM LicitudFondosAprobarExc l WHERE l.codigoExcepcion = :codigoExcepcion"),
    @NamedQuery(name = "LicitudFondosAprobarExc.findByFechaInicioExcp", query = "SELECT l FROM LicitudFondosAprobarExc l WHERE l.fechaInicioExcp = :fechaInicioExcp"),
    @NamedQuery(name = "LicitudFondosAprobarExc.findByFechaFinExcp", query = "SELECT l FROM LicitudFondosAprobarExc l WHERE l.fechaFinExcp = :fechaFinExcp"),
    @NamedQuery(name = "LicitudFondosAprobarExc.findByAprobadoPor", query = "SELECT l FROM LicitudFondosAprobarExc l WHERE l.aprobadoPor = :aprobadoPor"),
    @NamedQuery(name = "LicitudFondosAprobarExc.findByFechaAprobacion", query = "SELECT l FROM LicitudFondosAprobarExc l WHERE l.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "LicitudFondosAprobarExc.findByObservaciones", query = "SELECT l FROM LicitudFondosAprobarExc l WHERE l.observaciones = :observaciones"),
    @NamedQuery(name = "LicitudFondosAprobarExc.findByEliminado", query = "SELECT l FROM LicitudFondosAprobarExc l WHERE l.eliminado = :eliminado")})
public class LicitudFondosAprobarExc implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_EXCEPCION")
    private Long codigoExcepcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_MOTIVO")
    private Long codigoTipoMotivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO_EXCP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioExcp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN_EXCP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinExcp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APROBADO_POR")
    private long aprobadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    @Size(max = 200)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_MOTIVO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LicitudFondosTipoMotivoExc tipoMotivo;
    @JoinColumn(name = "CODIGO_EXCEPCION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private LicitudFondosPerExcFor licitudFondosPerExcFor;

    public LicitudFondosAprobarExc() {
    }

    public LicitudFondosAprobarExc(Long codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public LicitudFondosAprobarExc(Long codigoExcepcion, Date fechaInicioExcp, Date fechaFinExcp, long aprobadoPor, Date fechaAprobacion, char eliminado) {
        this.codigoExcepcion = codigoExcepcion;
        this.fechaInicioExcp = fechaInicioExcp;
        this.fechaFinExcp = fechaFinExcp;
        this.aprobadoPor = aprobadoPor;
        this.fechaAprobacion = fechaAprobacion;
        this.eliminado = eliminado;
    }

    public Long getCodigoExcepcion() {
        return codigoExcepcion;
    }

    public void setCodigoExcepcion(Long codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public Date getFechaInicioExcp() {
        return fechaInicioExcp;
    }

    public void setFechaInicioExcp(Date fechaInicioExcp) {
        this.fechaInicioExcp = fechaInicioExcp;
    }

    public Date getFechaFinExcp() {
        return fechaFinExcp;
    }

    public void setFechaFinExcp(Date fechaFinExcp) {
        this.fechaFinExcp = fechaFinExcp;
    }

    public long getAprobadoPor() {
        return aprobadoPor;
    }

    public void setAprobadoPor(long aprobadoPor) {
        this.aprobadoPor = aprobadoPor;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

  
    public LicitudFondosPerExcFor getLicitudFondosPerExcFor() {
        return licitudFondosPerExcFor;
    }

    public void setLicitudFondosPerExcFor(LicitudFondosPerExcFor licitudFondosPerExcFor) {
        this.licitudFondosPerExcFor = licitudFondosPerExcFor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoExcepcion != null ? codigoExcepcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicitudFondosAprobarExc)) {
            return false;
        }
        LicitudFondosAprobarExc other = (LicitudFondosAprobarExc) object;
        if ((this.codigoExcepcion == null && other.codigoExcepcion != null) || (this.codigoExcepcion != null && !this.codigoExcepcion.equals(other.codigoExcepcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosAprobarExc[ codigoExcepcion=" + codigoExcepcion + " ]";
    }

    /**
     * @return the tipoMotivo
     */
    public LicitudFondosTipoMotivoExc getTipoMotivo() {
        return tipoMotivo;
    }

    /**
     * @param tipoMotivo the tipoMotivo to set
     */
    public void setTipoMotivo(LicitudFondosTipoMotivoExc tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }

    /**
     * @return the codigoTipoMotivo
     */
    public Long getCodigoTipoMotivo() {
        return codigoTipoMotivo;
    }

    /**
     * @param codigoTipoMotivo the codigoTipoMotivo to set
     */
    public void setCodigoTipoMotivo(Long codigoTipoMotivo) {
        this.codigoTipoMotivo = codigoTipoMotivo;
    }
    
}
