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
@Table(name = "MKS_SEGURIDADES.INTENTO_FALLIDO_ACCESO_SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IntentoFallidoAccesoSistema.findAll", query = "SELECT i FROM IntentoFallidoAccesoSistema i"),
    @NamedQuery(name = "IntentoFallidoAccesoSistema.findByCodigo", query = "SELECT i FROM IntentoFallidoAccesoSistema i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "IntentoFallidoAccesoSistema.findByFechaIntento", query = "SELECT i FROM IntentoFallidoAccesoSistema i WHERE i.fechaIntento = :fechaIntento"),
    @NamedQuery(name = "IntentoFallidoAccesoSistema.findByContrasenaUsada", query = "SELECT i FROM IntentoFallidoAccesoSistema i WHERE i.contrasenaUsada = :contrasenaUsada"),
    @NamedQuery(name = "IntentoFallidoAccesoSistema.findByBloqueoCuenta", query = "SELECT i FROM IntentoFallidoAccesoSistema i WHERE i.bloqueoCuenta = :bloqueoCuenta")})
public class IntentoFallidoAccesoSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_INTENTO_FALLIDO_ACCESO_SIS")
    @SequenceGenerator(name = "GSQ_INTENTO_FALLIDO_ACCESO_SIS", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_INTENTO_FALLIDO_ACCESO_SIS")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INTENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIntento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CONTRASENA_USADA")
    private String contrasenaUsada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BLOQUEO_CUENTA")
    private char bloqueoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SISTEMA")
    private Long codigoSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO_SISTEMA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO_USUARIO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private UsuarioSistema usuarioSistema;

    public IntentoFallidoAccesoSistema() {
    }

    public IntentoFallidoAccesoSistema(Long codigo) {
        this.codigo = codigo;
    }

    public IntentoFallidoAccesoSistema(Long codigo, Date fechaIntento, String contrasenaUsada, char bloqueoCuenta) {
        this.codigo = codigo;
        this.fechaIntento = fechaIntento;
        this.contrasenaUsada = contrasenaUsada;
        this.bloqueoCuenta = bloqueoCuenta;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaIntento() {
        return fechaIntento;
    }

    public void setFechaIntento(Date fechaIntento) {
        this.fechaIntento = fechaIntento;
    }

    public String getContrasenaUsada() {
        return contrasenaUsada;
    }

    public void setContrasenaUsada(String contrasenaUsada) {
        this.contrasenaUsada = contrasenaUsada;
    }

    public char getBloqueoCuenta() {
        return bloqueoCuenta;
    }

    public void setBloqueoCuenta(char bloqueoCuenta) {
        this.bloqueoCuenta = bloqueoCuenta;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
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
        if (!(object instanceof IntentoFallidoAccesoSistema)) {
            return false;
        }
        IntentoFallidoAccesoSistema other = (IntentoFallidoAccesoSistema) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.IntentoFallidoAccesoSistema[ codigo=" + codigo + " ]";
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
    
}
