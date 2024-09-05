/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.socios.Persona;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MKS_SEGURIDADES.USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "Usuario.findByCodigoIfip", query = "SELECT u FROM Usuario u WHERE u.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Usuario.findByCodigoPersona", query = "SELECT u FROM Usuario u WHERE u.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByFechaCreacion", query = "SELECT u FROM Usuario u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Usuario.findByCorreoElectronico", query = "SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Usuario.findByEliminado", query = "SELECT u FROM Usuario u WHERE u.eliminado = :eliminado"),
    // Personalizados
    @NamedQuery(name = "Usuario.findByIfipEliminado", query = "SELECT u FROM Usuario u WHERE u.codigoIfip.codigo = :codigoIfip AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByUsernameEliminado", query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByUsuarioPersona", query = "SELECT u FROM Usuario u JOIN u.codigoPersona p WHERE p.codigo = :codigoPersona"),
    @NamedQuery(name = "Usuario.findByUsuarioIfipEliminado", query = "SELECT u FROM Usuario u WHERE u.codigoIfip.codigo = :codigoIfip AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByPersonaIfip", query = "SELECT u FROM Usuario u WHERE u.codigoIfip.codigo = :codigoIfip AND u.codigoPersona.codigo = :codigoPersona"),
    @NamedQuery(name = "Usuario.findByUsuarioIfipDifUsuCon", query = "SELECT u FROM Usuario u WHERE u.codigoIfip.codigo = :codigoIfip AND u.codigo <> :codigoUsuario AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByUsuarioDiferenteIfipEliminado", query = "SELECT u FROM Usuario u JOIN u.codigoPersona p WHERE p.codigo = :codigoPersona AND u.codigoIfip <> :codigoIfip AND u.eliminado = :eliminado"),
    @NamedQuery(name = "Usuario.findByUsernameDistinct", query = "SELECT DISTINCT u.username FROM Usuario u WHERE u.username = :username")

})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByUsernameEliminado = "Usuario.findByUsernameEliminado";
    public static final String findByUsuarioPersona = "Usuario.findByUsuarioPersona";
    public static final String findByUsuarioSistema = "Usuario.findByUsuarioSistema";
    public static final String findByUsername = "Usuario.findByUsername";
    public static final String findByUsuarioIfipDifUsuCon = "Usuario.findByUsuarioIfipDifUsuCon";
    public static final String findByUsuarioIfipEliminado = "Usuario.findByUsuarioIfipEliminado";
    public static final String findByPersonaIfip = "Usuario.findByPersonaIfip";
    public static final String findByUsuarioDiferenteIfipEliminado = "Usuario.findByUsuarioDiferenteIfipEliminado";
    public static final String findByIfipEliminado = "Usuario.findByIfipEliminado";
    public static final String findByUsernameDistinct = "Usuario.findByUsernameDistinct";
    public static final String findByCodigo = "Usuario.findByCodigo";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_USUARIO")
    @SequenceGenerator(name = "GSQ_USUARIO", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_USUARIO")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long ifip;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long persona;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expiradoPor", fetch = FetchType.LAZY)
    private Collection<ExpiracionContrasena> expiracionContrasenaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registradoPor", fetch = FetchType.LAZY)
    private Collection<IfipSistema> ifipSistemaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bloqueadoPor", fetch = FetchType.LAZY)
    private Collection<BloqueoCuentaUsuario> bloqueoCuentaUsuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<RespuestaPreguntaUsuario> respuestaPreguntaUsuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignadoPor", fetch = FetchType.LAZY)
    private Collection<UsuarioPuntoIfipCobroPago> usuarioPuntoIfipCobroPagoCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private UsuarioPuntoIfipCobroPago usuarioPuntoIfipCobroPago;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private UsuarioGuiaDeposito usuarioGuiaDeposito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoUsuarioCreacion", fetch = FetchType.LAZY)
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "CODIGO_USUARIO_CREACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario codigoUsuarioCreacion;
    @JoinColumn(name = "CODIGO_TIPO_USUARIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoUsuario codigoTipoUsuario;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private UsuarioEfeProChe usuarioEfeProChe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignadoPor", fetch = FetchType.LAZY)
    private Collection<UsuarioSistema> usuarioSistemaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<UsuarioSistema> usuarioSistemaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignadoPor", fetch = FetchType.LAZY)
    private Collection<UsuarioIfipAgencia> usuarioIfipAgenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<UsuarioIfipAgencia> usuarioIfipAgenciaCollection1;

    public Usuario() {
    }

    public Usuario(Long codigo) {
        this.codigo = codigo;
    }

    public Usuario(Long codigo, Long ifip, Persona codigoPersona, String username, Date fechaCreacion, String correoElectronico, char eliminado) {
        this.codigo = codigo;
        this.ifip = ifip;
        this.codigoPersona = codigoPersona;
        this.username = username;
        this.fechaCreacion = fechaCreacion;
        this.correoElectronico = correoElectronico;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Persona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Persona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<ExpiracionContrasena> getExpiracionContrasenaCollection() {
        return expiracionContrasenaCollection;
    }

    public void setExpiracionContrasenaCollection(Collection<ExpiracionContrasena> expiracionContrasenaCollection) {
        this.expiracionContrasenaCollection = expiracionContrasenaCollection;
    }

    @XmlTransient
    public Collection<IfipSistema> getIfipSistemaCollection() {
        return ifipSistemaCollection;
    }

    public void setIfipSistemaCollection(Collection<IfipSistema> ifipSistemaCollection) {
        this.ifipSistemaCollection = ifipSistemaCollection;
    }

    @XmlTransient
    public Collection<BloqueoCuentaUsuario> getBloqueoCuentaUsuarioCollection() {
        return bloqueoCuentaUsuarioCollection;
    }

    public void setBloqueoCuentaUsuarioCollection(Collection<BloqueoCuentaUsuario> bloqueoCuentaUsuarioCollection) {
        this.bloqueoCuentaUsuarioCollection = bloqueoCuentaUsuarioCollection;
    }

    @XmlTransient
    public Collection<RespuestaPreguntaUsuario> getRespuestaPreguntaUsuarioCollection() {
        return respuestaPreguntaUsuarioCollection;
    }

    public void setRespuestaPreguntaUsuarioCollection(Collection<RespuestaPreguntaUsuario> respuestaPreguntaUsuarioCollection) {
        this.respuestaPreguntaUsuarioCollection = respuestaPreguntaUsuarioCollection;
    }

    @XmlTransient
    public Collection<UsuarioPuntoIfipCobroPago> getUsuarioPuntoIfipCobroPagoCollection() {
        return usuarioPuntoIfipCobroPagoCollection;
    }

    public void setUsuarioPuntoIfipCobroPagoCollection(Collection<UsuarioPuntoIfipCobroPago> usuarioPuntoIfipCobroPagoCollection) {
        this.usuarioPuntoIfipCobroPagoCollection = usuarioPuntoIfipCobroPagoCollection;
    }

    public UsuarioPuntoIfipCobroPago getUsuarioPuntoIfipCobroPago() {
        return usuarioPuntoIfipCobroPago;
    }

    public void setUsuarioPuntoIfipCobroPago(UsuarioPuntoIfipCobroPago usuarioPuntoIfipCobroPago) {
        this.usuarioPuntoIfipCobroPago = usuarioPuntoIfipCobroPago;
    }

    public UsuarioGuiaDeposito getUsuarioGuiaDeposito() {
        return usuarioGuiaDeposito;
    }

    public void setUsuarioGuiaDeposito(UsuarioGuiaDeposito usuarioGuiaDeposito) {
        this.usuarioGuiaDeposito = usuarioGuiaDeposito;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Usuario getCodigoUsuarioCreacion() {
        return codigoUsuarioCreacion;
    }

    public void setCodigoUsuarioCreacion(Usuario codigoUsuarioCreacion) {
        this.codigoUsuarioCreacion = codigoUsuarioCreacion;
    }

    public TipoUsuario getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(TipoUsuario codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public UsuarioEfeProChe getUsuarioEfeProChe() {
        return usuarioEfeProChe;
    }

    public void setUsuarioEfeProChe(UsuarioEfeProChe usuarioEfeProChe) {
        this.usuarioEfeProChe = usuarioEfeProChe;
    }

    @XmlTransient
    public Collection<UsuarioSistema> getUsuarioSistemaCollection() {
        return usuarioSistemaCollection;
    }

    public void setUsuarioSistemaCollection(Collection<UsuarioSistema> usuarioSistemaCollection) {
        this.usuarioSistemaCollection = usuarioSistemaCollection;
    }

    @XmlTransient
    public Collection<UsuarioSistema> getUsuarioSistemaCollection1() {
        return usuarioSistemaCollection1;
    }

    public void setUsuarioSistemaCollection1(Collection<UsuarioSistema> usuarioSistemaCollection1) {
        this.usuarioSistemaCollection1 = usuarioSistemaCollection1;
    }

    @XmlTransient
    public Collection<UsuarioIfipAgencia> getUsuarioIfipAgenciaCollection() {
        return usuarioIfipAgenciaCollection;
    }

    public void setUsuarioIfipAgenciaCollection(Collection<UsuarioIfipAgencia> usuarioIfipAgenciaCollection) {
        this.usuarioIfipAgenciaCollection = usuarioIfipAgenciaCollection;
    }

    @XmlTransient
    public Collection<UsuarioIfipAgencia> getUsuarioIfipAgenciaCollection1() {
        return usuarioIfipAgenciaCollection1;
    }

    public void setUsuarioIfipAgenciaCollection1(Collection<UsuarioIfipAgencia> usuarioIfipAgenciaCollection1) {
        this.usuarioIfipAgenciaCollection1 = usuarioIfipAgenciaCollection1;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.Usuario[ codigo=" + codigo + " ]";
    }

    /**
     * @return the ifip
     */
    public long getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(long ifip) {
        this.ifip = ifip;
    }

    /**
     * @return the persona
     */
    public long getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(long persona) {
        this.persona = persona;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
