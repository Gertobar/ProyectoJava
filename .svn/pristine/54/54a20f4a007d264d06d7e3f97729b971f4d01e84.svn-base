/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.USUARIO_SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioSistema.findAll", query = "SELECT u FROM UsuarioSistema u"),
    @NamedQuery(name = "UsuarioSistema.findByCodigoUsuario", query = "SELECT u FROM UsuarioSistema u WHERE u.usuarioSistemaPK.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuarioSistema.findByCodigoSistema", query = "SELECT u FROM UsuarioSistema u WHERE u.usuarioSistemaPK.codigoSistema = :codigoSistema"),
    @NamedQuery(name = "UsuarioSistema.findByContrasena", query = "SELECT u FROM UsuarioSistema u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "UsuarioSistema.findByFechaAsignacion", query = "SELECT u FROM UsuarioSistema u WHERE u.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "UsuarioSistema.findByFechaExpiraContrasena", query = "SELECT u FROM UsuarioSistema u WHERE u.fechaCaducaContrasena = :fechaCaducaContrasena"),
    @NamedQuery(name = "UsuarioSistema.findByMesesVigencia", query = "SELECT u FROM UsuarioSistema u WHERE u.mesesVigencia = :mesesVigencia"),
    @NamedQuery(name = "UsuarioSistema.findByDiasGracia", query = "SELECT u FROM UsuarioSistema u WHERE u.diasGracia = :diasGracia"),
    @NamedQuery(name = "UsuarioSistema.findByEstado", query = "SELECT u FROM UsuarioSistema u WHERE u.estado = :estado"),
    // Personalizados
    @NamedQuery(name = "UsuarioSistema.findByUsuarioSistema", query = "SELECT u FROM UsuarioSistema u WHERE u.usuarioSistemaPK.codigoUsuario = :codigoUsuario AND u.usuarioSistemaPK.codigoSistema = :codigoSistema"),
    @NamedQuery(name = "UsuarioSistema.findByUsuarioSistemaNomIfi", query = "SELECT u FROM UsuarioSistema u  JOIN u.usuario us WHERE us.codigoPersona.nombreCompleto  LIKE :nombreCompleto AND us.codigoIfip.codigo = :codigoIfip AND u.usuarioSistemaPK.codigoSistema = :codigoSistema"),
    @NamedQuery(name = "UsuarioSistema.findByUsuarioSisNomIfiExpCon", query = "SELECT u FROM UsuarioSistema u  JOIN u.usuario us WHERE us.codigoPersona.nombreCompleto  LIKE :nombreCompleto AND us.codigoIfip.codigo = :codigoIfip AND u.usuarioSistemaPK.codigoSistema = :codigoSistema AND us.codigo <> :codigoUsuario"),
    @NamedQuery(name = "UsuarioSistema.findByUsuSistemaUsernameEstado", query = "SELECT u FROM UsuarioSistema u JOIN u.usuario us WHERE us.username = :username AND u.estado <> :estado AND u.usuarioSistemaPK.codigoSistema = :codigoSistema"),
    @NamedQuery(name = "UsuarioSistema.findByUsuSisEliminado", query = "SELECT u FROM UsuarioSistema u JOIN u.usuario us WHERE u.usuarioSistemaPK.codigoUsuario = :codigoUsuario AND u.estado <> :estado"),
    @NamedQuery(name = "UsuarioSistema.findByUsuSisBoveda", query = "SELECT us FROM UsuarioSistema u JOIN u.usuario us WHERE u.usuarioSistemaPK.codigoSistema = :codigoSistema AND  us.codigoIfip.codigo = :codigoIfip AND  us.codigo <> :codigoUsuario AND us.eliminado = :eliminado"),
    @NamedQuery(name = "UsuarioSistema.findByCodigoUsuarioCodigoSistema", query = "SELECT u FROM UsuarioSistema u WHERE u.usuario.codigo = :codigoUsuario AND u.sistema.codigo = :codigoSistema")
})

public class UsuarioSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByUsuarioSistema = "UsuarioSistema.findByUsuarioSistema";
    public static final String findByUsuarioSistemaNomIfi = "UsuarioSistema.findByUsuarioSistemaNomIfi";
    public static final String findByUsuarioSisNomIfiExpCon = "UsuarioSistema.findByUsuarioSisNomIfiExpCon";
    public static final String findByUsuSistemaUsernameEstado = "UsuarioSistema.findByUsuSistemaUsernameEstado";
    public static final String findByUsuSisEliminado = "UsuarioSistema.findByUsuSisEliminado";
    public static final String findByUsuSisBoveda = "UsuarioSistema.findByUsuSisBoveda";
            
    @EmbeddedId
    protected UsuarioSistemaPK usuarioSistemaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ASIGNACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CADUCA_CONTRASENA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducaContrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESES_VIGENCIA")
    private short mesesVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_GRACIA")
    private short diasGracia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CAMBIO_CONTRASENA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambioContrasena;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSistema", fetch = FetchType.LAZY)
    private Collection<ContrasenaUsuario> contrasenaUsuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSistema", fetch = FetchType.LAZY)
    private Collection<ExpiracionContrasena> expiracionContrasenaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSistema", fetch = FetchType.LAZY)
    private Collection<BloqueoCuentaUsuario> bloqueoCuentaUsuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSistema", fetch = FetchType.LAZY)
    private Collection<UsuarioAccesoSistema> usuarioAccesoSistemaCollection;
    @JoinColumn(name = "ASIGNADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario asignadoPor;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sistema sistema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSistema")
    private Collection<IntentoFallidoAccesoSistema> intentoFallidoAccesoSistemaCollection;

    public UsuarioSistema() {
    }

    public UsuarioSistema(UsuarioSistemaPK usuarioSistemaPK) {
        this.usuarioSistemaPK = usuarioSistemaPK;
    }

    public UsuarioSistema(UsuarioSistemaPK usuarioSistemaPK, String contrasena, Date fechaAsignacion, Date fechaCaducaContrasena, short mesesVigencia, short diasGracia, char estado) {
        this.usuarioSistemaPK = usuarioSistemaPK;
        this.contrasena = contrasena;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaCaducaContrasena = fechaCaducaContrasena;
        this.mesesVigencia = mesesVigencia;
        this.diasGracia = diasGracia;
        this.estado = estado;
    }

    public UsuarioSistema(long codigoUsuario, long codigoSistema) {
        this.usuarioSistemaPK = new UsuarioSistemaPK(codigoUsuario, codigoSistema);
    }

    public UsuarioSistemaPK getUsuarioSistemaPK() {
        return usuarioSistemaPK;
    }

    public void setUsuarioSistemaPK(UsuarioSistemaPK usuarioSistemaPK) {
        this.usuarioSistemaPK = usuarioSistemaPK;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

 
    public short getMesesVigencia() {
        return mesesVigencia;
    }

    public void setMesesVigencia(short mesesVigencia) {
        this.mesesVigencia = mesesVigencia;
    }

    public short getDiasGracia() {
        return diasGracia;
    }

    public void setDiasGracia(short diasGracia) {
        this.diasGracia = diasGracia;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<ContrasenaUsuario> getContrasenaUsuarioCollection() {
        return contrasenaUsuarioCollection;
    }

    public void setContrasenaUsuarioCollection(Collection<ContrasenaUsuario> contrasenaUsuarioCollection) {
        this.contrasenaUsuarioCollection = contrasenaUsuarioCollection;
    }

    @XmlTransient
    public Collection<ExpiracionContrasena> getExpiracionContrasenaCollection() {
        return expiracionContrasenaCollection;
    }

    public void setExpiracionContrasenaCollection(Collection<ExpiracionContrasena> expiracionContrasenaCollection) {
        this.expiracionContrasenaCollection = expiracionContrasenaCollection;
    }

    @XmlTransient
    public Collection<BloqueoCuentaUsuario> getBloqueoCuentaUsuarioCollection() {
        return bloqueoCuentaUsuarioCollection;
    }

    public void setBloqueoCuentaUsuarioCollection(Collection<BloqueoCuentaUsuario> bloqueoCuentaUsuarioCollection) {
        this.bloqueoCuentaUsuarioCollection = bloqueoCuentaUsuarioCollection;
    }

    @XmlTransient
    public Collection<UsuarioAccesoSistema> getUsuarioAccesoSistemaCollection() {
        return usuarioAccesoSistemaCollection;
    }

    public void setUsuarioAccesoSistemaCollection(Collection<UsuarioAccesoSistema> usuarioAccesoSistemaCollection) {
        this.usuarioAccesoSistemaCollection = usuarioAccesoSistemaCollection;
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

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @XmlTransient
    public Collection<IntentoFallidoAccesoSistema> getIntentoFallidoAccesoSistemaCollection() {
        return intentoFallidoAccesoSistemaCollection;
    }

    public void setIntentoFallidoAccesoSistemaCollection(Collection<IntentoFallidoAccesoSistema> intentoFallidoAccesoSistemaCollection) {
        this.intentoFallidoAccesoSistemaCollection = intentoFallidoAccesoSistemaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioSistemaPK != null ? usuarioSistemaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSistema)) {
            return false;
        }
        UsuarioSistema other = (UsuarioSistema) object;
        if ((this.usuarioSistemaPK == null && other.usuarioSistemaPK != null) || (this.usuarioSistemaPK != null && !this.usuarioSistemaPK.equals(other.usuarioSistemaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioSistema[ usuarioSistemaPK=" + usuarioSistemaPK + " ]";
    }

    /**
     * @return the fechaCambioContrasena
     */
    public Date getFechaCambioContrasena() {
        return fechaCambioContrasena;
    }

    /**
     * @param fechaCambioContrasena the fechaCambioContrasena to set
     */
    public void setFechaCambioContrasena(Date fechaCambioContrasena) {
        this.fechaCambioContrasena = fechaCambioContrasena;
    }

    /**
     * @return the fechaCaducaContrasena
     */
    public Date getFechaCaducaContrasena() {
        return fechaCaducaContrasena;
    }

    /**
     * @param fechaCaducaContrasena the fechaCaducaContrasena to set
     */
    public void setFechaCaducaContrasena(Date fechaCaducaContrasena) {
        this.fechaCaducaContrasena = fechaCaducaContrasena;
    }
    
}
