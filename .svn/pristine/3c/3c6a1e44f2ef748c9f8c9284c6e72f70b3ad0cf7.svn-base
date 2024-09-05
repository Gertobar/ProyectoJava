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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_SEGURIDADES.USUARIO_CAMBIA_TASA_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioCambiaTasaDpf.findAll", query = "SELECT u FROM UsuarioCambiaTasaDpf u"),
    @NamedQuery(name = "UsuarioCambiaTasaDpf.findByCodigoUsuario", query = "SELECT u FROM UsuarioCambiaTasaDpf u WHERE u.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuarioCambiaTasaDpf.findByFechaAsignacion", query = "SELECT u FROM UsuarioCambiaTasaDpf u WHERE u.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "UsuarioCambiaTasaDpf.findByEliminado", query = "SELECT u FROM UsuarioCambiaTasaDpf u WHERE u.eliminado = :eliminado")})
public class UsuarioCambiaTasaDpf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ASIGNACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_USUARIO_ASIGNO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario codigoUsuarioAsigno;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public UsuarioCambiaTasaDpf() {
    }

    public UsuarioCambiaTasaDpf(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public UsuarioCambiaTasaDpf(Long codigoUsuario, Date fechaAsignacion, char eliminado) {
        this.codigoUsuario = codigoUsuario;
        this.fechaAsignacion = fechaAsignacion;
        this.eliminado = eliminado;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Usuario getCodigoUsuarioAsigno() {
        return codigoUsuarioAsigno;
    }

    public void setCodigoUsuarioAsigno(Usuario codigoUsuarioAsigno) {
        this.codigoUsuarioAsigno = codigoUsuarioAsigno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioCambiaTasaDpf)) {
            return false;
        }
        UsuarioCambiaTasaDpf other = (UsuarioCambiaTasaDpf) object;
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.seguridades.UsuarioCambiaTasaDpf[ codigoUsuario=" + codigoUsuario + " ]";
    }
    
}
