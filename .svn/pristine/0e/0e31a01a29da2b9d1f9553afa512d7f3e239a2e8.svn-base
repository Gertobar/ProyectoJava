/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.TALONARIO_DOCUMENTO_RETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TalonarioDocumentoRetencion.findAll", query = "SELECT t FROM TalonarioDocumentoRetencion t"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByCodigoIfip", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.talonarioDocumentoRetencionPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByNumeroAutorizacion", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.numeroAutorizacion = :numeroAutorizacion"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findBySerie", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.serie = :serie"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByInicio", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.inicio = :inicio"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByFin", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.fin = :fin"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByDigitos", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.digitos = :digitos"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByFechaEmision", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByFechaCaducidad", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByEstado", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.estado = :estado"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByEstadoColocadoPor", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByFechaEstado", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByCodigo", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.talonarioDocumentoRetencionPK.codigo = :codigo"),
    @NamedQuery(name = "TalonarioDocumentoRetencion.findByCodigoIfipAgencia", query = "SELECT t FROM TalonarioDocumentoRetencion t WHERE t.codigoIfipAgencia = :codigoIfipAgencia")})
public class TalonarioDocumentoRetencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TalonarioDocumentoRetencionPK talonarioDocumentoRetencionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NUMERO_AUTORIZACION")
    private String numeroAutorizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "SERIE")
    private String serie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INICIO")
    private long inicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIN")
    private long fin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIGITOS")
    private int digitos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EMISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CADUCIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "talonarioDocumentoRetencion", fetch = FetchType.LAZY)
    private Collection<TalonarioDocumentoRetDet> talonarioDocumentoRetDetCollection;

    public TalonarioDocumentoRetencion() {
    }

    public TalonarioDocumentoRetencion(TalonarioDocumentoRetencionPK talonarioDocumentoRetencionPK) {
        this.talonarioDocumentoRetencionPK = talonarioDocumentoRetencionPK;
    }

    public TalonarioDocumentoRetencion(TalonarioDocumentoRetencionPK talonarioDocumentoRetencionPK, String numeroAutorizacion, String serie, long inicio, long fin, int digitos, Date fechaEmision, Date fechaCaducidad, char estado, long estadoColocadoPor, Date fechaEstado, long codigoIfipAgencia) {
        this.talonarioDocumentoRetencionPK = talonarioDocumentoRetencionPK;
        this.numeroAutorizacion = numeroAutorizacion;
        this.serie = serie;
        this.inicio = inicio;
        this.fin = fin;
        this.digitos = digitos;
        this.fechaEmision = fechaEmision;
        this.fechaCaducidad = fechaCaducidad;
        this.estado = estado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEstado = fechaEstado;
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public TalonarioDocumentoRetencion(long codigoIfip, long codigo) {
        this.talonarioDocumentoRetencionPK = new TalonarioDocumentoRetencionPK(codigoIfip, codigo);
    }

    public TalonarioDocumentoRetencionPK getTalonarioDocumentoRetencionPK() {
        return talonarioDocumentoRetencionPK;
    }

    public void setTalonarioDocumentoRetencionPK(TalonarioDocumentoRetencionPK talonarioDocumentoRetencionPK) {
        this.talonarioDocumentoRetencionPK = talonarioDocumentoRetencionPK;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

    public long getFin() {
        return fin;
    }

    public void setFin(long fin) {
        this.fin = fin;
    }

    public int getDigitos() {
        return digitos;
    }

    public void setDigitos(int digitos) {
        this.digitos = digitos;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public long getEstadoColocadoPor() {
        return estadoColocadoPor;
    }

    public void setEstadoColocadoPor(long estadoColocadoPor) {
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    @XmlTransient
    public Collection<TalonarioDocumentoRetDet> getTalonarioDocumentoRetDetCollection() {
        return talonarioDocumentoRetDetCollection;
    }

    public void setTalonarioDocumentoRetDetCollection(Collection<TalonarioDocumentoRetDet> talonarioDocumentoRetDetCollection) {
        this.talonarioDocumentoRetDetCollection = talonarioDocumentoRetDetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (talonarioDocumentoRetencionPK != null ? talonarioDocumentoRetencionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioDocumentoRetencion)) {
            return false;
        }
        TalonarioDocumentoRetencion other = (TalonarioDocumentoRetencion) object;
        if ((this.talonarioDocumentoRetencionPK == null && other.talonarioDocumentoRetencionPK != null) || (this.talonarioDocumentoRetencionPK != null && !this.talonarioDocumentoRetencionPK.equals(other.talonarioDocumentoRetencionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TalonarioDocumentoRetencion[ talonarioDocumentoRetencionPK=" + talonarioDocumentoRetencionPK + " ]";
    }
    
}
