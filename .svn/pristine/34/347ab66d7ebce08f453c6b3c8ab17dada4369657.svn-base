/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_FORMULARIO_ADI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosFormularioAdi.findAll", query = "SELECT l FROM LicitudFondosFormularioAdi l"),
    @NamedQuery(name = "LicitudFondosFormularioAdi.findByCodigoFormulario", query = "SELECT l FROM LicitudFondosFormularioAdi l WHERE l.codigoFormulario = :codigoFormulario"),
    @NamedQuery(name = "LicitudFondosFormularioAdi.findByCodigoPersonaFirma", query = "SELECT l FROM LicitudFondosFormularioAdi l WHERE l.codigoPersonaFirma = :codigoPersonaFirma"),
    @NamedQuery(name = "LicitudFondosFormularioAdi.findByCodigoIfip", query = "SELECT l FROM LicitudFondosFormularioAdi l WHERE l.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "LicitudFondosFormularioAdi.findByCodigoIfipNumeroFormulario", query = "SELECT l FROM LicitudFondosFormularioAdi l WHERE l.numeroFormulario = :numeroFormulario AND l.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "LicitudFondosFormularioAdi.findByRealizaPersona", query = "SELECT l FROM LicitudFondosFormularioAdi l WHERE l.realizaPersona = :realizaPersona"),    
    @NamedQuery(name = "LicitudFondosFormularioAdi.findByFechaRegistro", query = "SELECT l FROM LicitudFondosFormularioAdi l WHERE l.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "LicitudFondosFormularioAdi.findByRegistradoPor", query = "SELECT l FROM LicitudFondosFormularioAdi l WHERE l.registradoPor = :registradoPor")})
public class LicitudFondosFormularioAdi implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByCodigoIfipNumeroFormulario = "LicitudFondosFormularioAdi.findByCodigoIfipNumeroFormulario";
    public static String findByCodigoFormulario = "LicitudFondosFormularioAdi.findByCodigoFormulario";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_FORMULARIO")
    private Long codigoFormulario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA_FIRMA")
    private long codigoPersonaFirma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NUMERO_FORMULARIO")
    private String numeroFormulario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REALIZA_PERSONA")
    private char realizaPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
     @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTUALIZADO_POR")
    private long actualizadoPor;
    @Column(name = "CODIGO_RELACION")
    private Long codigoRelacion;
    @JoinColumn(name = "CODIGO_ORIGEN", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LicitudFonOrgDest codigoOrigen;
    @JoinColumn(name = "CODIGO_DESTINO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LicitudFonOrgDest codigoDestino;
    @JoinColumn(name = "CODIGO_FORMULARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private LicitudFondosFormulario licitudFondosFormulario;

    public LicitudFondosFormularioAdi() {
    }

    public LicitudFondosFormularioAdi(Long codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }

    public LicitudFondosFormularioAdi(Long codigoFormulario, long codigoPersonaFirma, long codigoIfip, String numeroFormulario, char realizaPersona, Date fechaRegistro, long registradoPor) {
        this.codigoFormulario = codigoFormulario;
        this.codigoPersonaFirma = codigoPersonaFirma;
        this.codigoIfip = codigoIfip;
        this.numeroFormulario = numeroFormulario;
        this.realizaPersona = realizaPersona;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
    }

    public Long getCodigoFormulario() {
        return codigoFormulario;
    }

    public void setCodigoFormulario(Long codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }

    public long getCodigoPersonaFirma() {
        return codigoPersonaFirma;
    }

    public void setCodigoPersonaFirma(long codigoPersonaFirma) {
        this.codigoPersonaFirma = codigoPersonaFirma;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public char getRealizaPersona() {
        return realizaPersona;
    }

    public void setRealizaPersona(char realizaPersona) {
        this.realizaPersona = realizaPersona;
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
    

    public LicitudFondosFormulario getLicitudFondosFormulario() {
        return licitudFondosFormulario;
    }

    public void setLicitudFondosFormulario(LicitudFondosFormulario licitudFondosFormulario) {
        this.licitudFondosFormulario = licitudFondosFormulario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFormulario != null ? codigoFormulario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicitudFondosFormularioAdi)) {
            return false;
        }
        LicitudFondosFormularioAdi other = (LicitudFondosFormularioAdi) object;
        if ((this.codigoFormulario == null && other.codigoFormulario != null) || (this.codigoFormulario != null && !this.codigoFormulario.equals(other.codigoFormulario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosFormularioAdi[ codigoFormulario=" + codigoFormulario + " ]";
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the actualizadoPor
     */
    public Long getActualizadoPor() {
        return actualizadoPor;
    }

    /**
     * @param actualizadoPor the actualizadoPor to set
     */
    public void setActualizadoPor(Long actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    /**
     * @return the codigoRelacion
     */
    public long getCodigoRelacion() {
        return (codigoRelacion) == null ? -1L : codigoRelacion;
    }

    /**
     * @param codigoRelacion the codigoRelacion to set
     */
    public void setCodigoRelacion(long codigoRelacion) {
        this.codigoRelacion = codigoRelacion;
    }

    /**
     * @return the codigoOrigen
     */
    public LicitudFonOrgDest getCodigoOrigen() {
        return codigoOrigen;
    }

    /**
     * @param codigoOrigen the codigoOrigen to set
     */
    public void setCodigoOrigen(LicitudFonOrgDest codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    /**
     * @return the codigoDestino
     */
    public LicitudFonOrgDest getCodigoDestino() {
        return codigoDestino;
    }

    /**
     * @param codigoDestino the codigoDestino to set
     */
    public void setCodigoDestino(LicitudFonOrgDest codigoDestino) {
        this.codigoDestino = codigoDestino;
    }
    
}
