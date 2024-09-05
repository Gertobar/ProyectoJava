/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.USUARIO_IFIP_AGENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioIfipAgencia.findAll", query = "SELECT u FROM UsuarioIfipAgencia u"),
    @NamedQuery(name = "UsuarioIfipAgencia.findByCodigoIfipAgencia", query = "SELECT u FROM UsuarioIfipAgencia u WHERE u.usuarioIfipAgenciaPK.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "UsuarioIfipAgencia.findByCodigoUsuario", query = "SELECT u FROM UsuarioIfipAgencia u WHERE u.usuarioIfipAgenciaPK.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuarioIfipAgencia.findByFechaAsignacion", query = "SELECT u FROM UsuarioIfipAgencia u WHERE u.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "UsuarioIfipAgencia.findByEliminado", query = "SELECT u FROM UsuarioIfipAgencia u WHERE u.eliminado = :eliminado"),
    // Personalizados
    @NamedQuery(name = "UsuarioIfipAgencia.findByAsiIfiAgeUsu", query = "SELECT u FROM UsuarioIfipAgencia u WHERE  u.ifipAgencia.codigoIfip.codigo = :codigoIfip AND  u.usuarioIfipAgenciaPK.codigoUsuario = :codigoUsuario AND u.eliminado = :eliminado"),
    @NamedQuery(name = "UsuarioIfipAgencia.findByUsuariosIfipAgenciaEliminado", query = "SELECT u.usuario FROM UsuarioIfipAgencia u WHERE  u.usuarioIfipAgenciaPK.codigoIfipAgencia = :codigoIfipAgencia AND u.eliminado = :eliminado"),
    @NamedQuery(name = "UsuarioIfipAgencia.findByUsuariosFondeoCaja", query = "SELECT r FROM UsuarioIfipAgencia u JOIN u.usuario r WHERE r.codigoIfip.codigo = :codigoIfip AND u.ifipAgencia.codigoIfip.codigo = :codigoIfip AND  u.usuarioIfipAgenciaPK.codigoIfipAgencia = :codigoIfipAgencia AND u.usuarioIfipAgenciaPK.codigoUsuario <> :codigoUsuario AND u.codigoRol.puedeAbrirCaja = :puedeAbrirCaja AND u.eliminado = :eliminado AND r.eliminado = :eliminado")
      
})
public class UsuarioIfipAgencia implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByAsiIfiAgeUsu = "UsuarioIfipAgencia.findByAsiIfiAgeUsu";
    public static final String findByUsuariosIfipAgenciaEliminado = "UsuarioIfipAgencia.findByUsuariosIfipAgenciaEliminado";
    public static final String findByUsuariosFondeoCaja = "UsuarioIfipAgencia.findByUsuariosFondeoCaja";
    @EmbeddedId
    protected UsuarioIfipAgenciaPK usuarioIfipAgenciaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ASIGNACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "ASIGNADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario asignadoPor;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "CODIGO_ROL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Rol codigoRol;
    @JoinColumn(name = "CODIGO_PERFIL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Perfil codigoPerfil;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO" , insertable = false, updatable = false)
    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    private IfipAgencia ifipAgencia;

    public UsuarioIfipAgencia() {
    }

    public UsuarioIfipAgencia(UsuarioIfipAgenciaPK usuarioIfipAgenciaPK) {
        this.usuarioIfipAgenciaPK = usuarioIfipAgenciaPK;
    }

    public UsuarioIfipAgencia(UsuarioIfipAgenciaPK usuarioIfipAgenciaPK, Date fechaAsignacion, char eliminado) {
        this.usuarioIfipAgenciaPK = usuarioIfipAgenciaPK;
        this.fechaAsignacion = fechaAsignacion;
        this.eliminado = eliminado;
    }

    public UsuarioIfipAgencia(long codigoIfipAgencia, long codigoUsuario) {
        this.usuarioIfipAgenciaPK = new UsuarioIfipAgenciaPK(codigoIfipAgencia, codigoUsuario);
    }

    public UsuarioIfipAgenciaPK getUsuarioIfipAgenciaPK() {
        return usuarioIfipAgenciaPK;
    }

    public void setUsuarioIfipAgenciaPK(UsuarioIfipAgenciaPK usuarioIfipAgenciaPK) {
        this.usuarioIfipAgenciaPK = usuarioIfipAgenciaPK;
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

    public Usuario getAsignadoPor() {
        return asignadoPor;
    }

    public void setAsignadoPor(Usuario asignadoPor) {
        this.asignadoPor = asignadoPor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Rol codigoRol) {
        this.codigoRol = codigoRol;
    }

    public Perfil getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(Perfil codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioIfipAgenciaPK != null ? usuarioIfipAgenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioIfipAgencia)) {
            return false;
        }
        UsuarioIfipAgencia other = (UsuarioIfipAgencia) object;
        if ((this.usuarioIfipAgenciaPK == null && other.usuarioIfipAgenciaPK != null) || (this.usuarioIfipAgenciaPK != null && !this.usuarioIfipAgenciaPK.equals(other.usuarioIfipAgenciaPK))) {
            return false;
        }
        return true;
    }
    
     @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia[ usuarioIfipAgenciaPK=" + usuarioIfipAgenciaPK + " ]";
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }
   
    
}
