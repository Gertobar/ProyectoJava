/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.EXPIRACION_CONTRASENA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExpiracionContrasena.findAll", query = "SELECT e FROM ExpiracionContrasena e"),
    @NamedQuery(name = "ExpiracionContrasena.findByCodigo", query = "SELECT e FROM ExpiracionContrasena e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "ExpiracionContrasena.findByFechaExpiracion", query = "SELECT e FROM ExpiracionContrasena e WHERE e.fechaExpiracion = :fechaExpiracion"),
    @NamedQuery(name = "ExpiracionContrasena.findByVigente", query = "SELECT e FROM ExpiracionContrasena e WHERE e.vigente = :vigente"),
    // Personalizados
    @NamedQuery(name = "ExpiracionContrasena.findByExpiracionConUsuSisVig", query = "SELECT e FROM ExpiracionContrasena e WHERE e.usuarioSistema = :usuarioSistema AND e.vigente = :vigente")  
})

public class ExpiracionContrasena implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigo = "ExpiracionContrasena.findByCodigo";
    public static final String findByExpiracionConUsuSisVig = "ExpiracionContrasena.findByExpiracionConUsuSisVig";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_EXPIRACION_CONTRASENA")
    @SequenceGenerator(name = "GSQ_EXPIRACION_CONTRASENA", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_EXPIRACION_CONTRASENA")
    @Column(name = "CODIGO")
    private Long codigo;
     @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SISTEMA")
    private Long codigoSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EXPIRACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private char vigente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO_USUARIO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO_SISTEMA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private UsuarioSistema usuarioSistema;
    @JoinColumn(name = "EXPIRADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario expiradoPor;
    @JoinColumn(name = "CODIGO_MOTIVO_EXPIRA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MotivoExpiraContrasena codigoMotivoExpira;

    public ExpiracionContrasena() {
    }

    public ExpiracionContrasena(Long codigo) {
        this.codigo = codigo;
    }

    public ExpiracionContrasena(Long codigo, Date fechaExpiracion, char vigente) {
        this.codigo = codigo;
        this.fechaExpiracion = fechaExpiracion;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public Usuario getExpiradoPor() {
        return expiradoPor;
    }

    public void setExpiradoPor(Usuario expiradoPor) {
        this.expiradoPor = expiradoPor;
    }

    public MotivoExpiraContrasena getCodigoMotivoExpira() {
        return codigoMotivoExpira;
    }

    public void setCodigoMotivoExpira(MotivoExpiraContrasena codigoMotivoExpira) {
        this.codigoMotivoExpira = codigoMotivoExpira;
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
        if (!(object instanceof ExpiracionContrasena)) {
            return false;
        }
        ExpiracionContrasena other = (ExpiracionContrasena) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.ExpiracionContrasena[ codigo=" + codigo + " ]";
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the codigoUsuario
     */
    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * @param codigoUsuario the codigoUsuario to set
     */
    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * @return the codigoSistema
     */
    public Long getCodigoSistema() {
        return codigoSistema;
    }

    /**
     * @param codigoSistema the codigoSistema to set
     */
    public void setCodigoSistema(Long codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

}
