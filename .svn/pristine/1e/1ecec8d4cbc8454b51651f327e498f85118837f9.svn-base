/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.creditos.EstadoCredito;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_SEGURIDADES.USUARIO_ESTADO_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEstadoCredito.findAll", query = "SELECT u FROM UsuarioEstadoCredito u"),
    @NamedQuery(name = "UsuarioEstadoCredito.findByCodigoEstado", query = "SELECT u FROM UsuarioEstadoCredito u WHERE u.usuarioEstadoCreditoPK.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "UsuarioEstadoCredito.findByCodigoUsuario", query = "SELECT u FROM UsuarioEstadoCredito u WHERE u.usuarioEstadoCreditoPK.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuarioEstadoCredito.findByFechaRegistro", query = "SELECT u FROM UsuarioEstadoCredito u WHERE u.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "UsuarioEstadoCredito.findByEliminado", query = "SELECT u FROM UsuarioEstadoCredito u WHERE u.eliminado = :eliminado"),
    //Personalizadas
    @NamedQuery(name = "UsuarioEstadoCredito.findByUsuarioEstadoCredito", query = "SELECT u FROM UsuarioEstadoCredito u JOIN u.estadoCredito e JOIN u.usuario us WHERE e.codigo = :codigoEstadoCredito AND us.codigo = :codigoUsuario AND u.eliminado = :eliminado")})
public class UsuarioEstadoCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByUsuarioEstadoCredito = "UsuarioEstadoCredito.findByUsuarioEstadoCredito";
    @EmbeddedId
    protected UsuarioEstadoCreditoPK usuarioEstadoCreditoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EstadoCredito estadoCredito;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Usuario usuario;

    public UsuarioEstadoCredito() {
    }

    public UsuarioEstadoCredito(UsuarioEstadoCreditoPK usuarioEstadoCreditoPK) {
        this.usuarioEstadoCreditoPK = usuarioEstadoCreditoPK;
    }

    public UsuarioEstadoCredito(UsuarioEstadoCreditoPK usuarioEstadoCreditoPK, Date fechaRegistro, char eliminado) {
        this.usuarioEstadoCreditoPK = usuarioEstadoCreditoPK;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public UsuarioEstadoCredito(long codigoEstado, long codigoUsuario) {
        this.usuarioEstadoCreditoPK = new UsuarioEstadoCreditoPK(codigoEstado, codigoUsuario);
    }

    public UsuarioEstadoCreditoPK getUsuarioEstadoCreditoPK() {
        return usuarioEstadoCreditoPK;
    }

    public void setUsuarioEstadoCreditoPK(UsuarioEstadoCreditoPK usuarioEstadoCreditoPK) {
        this.usuarioEstadoCreditoPK = usuarioEstadoCreditoPK;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioEstadoCreditoPK != null ? usuarioEstadoCreditoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEstadoCredito)) {
            return false;
        }
        UsuarioEstadoCredito other = (UsuarioEstadoCredito) object;
        if ((this.usuarioEstadoCreditoPK == null && other.usuarioEstadoCreditoPK != null) || (this.usuarioEstadoCreditoPK != null && !this.usuarioEstadoCreditoPK.equals(other.usuarioEstadoCreditoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioEstadoCredito[ usuarioEstadoCreditoPK=" + usuarioEstadoCreditoPK + " ]";
    }

    /**
     * @return the estadoCredito
     */
    public EstadoCredito getEstadoCredito() {
        return estadoCredito;
    }

    /**
     * @param estadoCredito the estadoCredito to set
     */
    public void setEstadoCredito(EstadoCredito estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
