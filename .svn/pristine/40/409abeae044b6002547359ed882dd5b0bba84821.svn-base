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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.USUARIO_PUNTO_IFIP_COBRO_PAGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioPuntoIfipCobroPago.findAll", query = "SELECT u FROM UsuarioPuntoIfipCobroPago u"),
    @NamedQuery(name = "UsuarioPuntoIfipCobroPago.findByCodigoUsuario", query = "SELECT u FROM UsuarioPuntoIfipCobroPago u WHERE u.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuarioPuntoIfipCobroPago.findByCodigoPuntoIfip", query = "SELECT u FROM UsuarioPuntoIfipCobroPago u WHERE u.codigoPuntoIfip = :codigoPuntoIfip"),
    @NamedQuery(name = "UsuarioPuntoIfipCobroPago.findByFechaAsignacion", query = "SELECT u FROM UsuarioPuntoIfipCobroPago u WHERE u.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "UsuarioPuntoIfipCobroPago.findByEliminado", query = "SELECT u FROM UsuarioPuntoIfipCobroPago u WHERE u.eliminado = :eliminado")})
public class UsuarioPuntoIfipCobroPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PUNTO_IFIP")
    private long codigoPuntoIfip;
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
    @OneToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "CODIGO_ROL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Rol codigoRol;

    public UsuarioPuntoIfipCobroPago() {
    }

    public UsuarioPuntoIfipCobroPago(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public UsuarioPuntoIfipCobroPago(Long codigoUsuario, long codigoPuntoIfip, Date fechaAsignacion, char eliminado) {
        this.codigoUsuario = codigoUsuario;
        this.codigoPuntoIfip = codigoPuntoIfip;
        this.fechaAsignacion = fechaAsignacion;
        this.eliminado = eliminado;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoPuntoIfip() {
        return codigoPuntoIfip;
    }

    public void setCodigoPuntoIfip(long codigoPuntoIfip) {
        this.codigoPuntoIfip = codigoPuntoIfip;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPuntoIfipCobroPago)) {
            return false;
        }
        UsuarioPuntoIfipCobroPago other = (UsuarioPuntoIfipCobroPago) object;
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioPuntoIfipCobroPago[ codigoUsuario=" + codigoUsuario + " ]";
    }
    
}
