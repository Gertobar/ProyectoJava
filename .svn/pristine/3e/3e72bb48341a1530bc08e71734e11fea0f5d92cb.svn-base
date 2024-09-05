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
@Table(name = "MKS_SEGURIDADES.USUARIO_EFE_PRO_CHE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEfeProChe.findAll", query = "SELECT u FROM UsuarioEfeProChe u"),
    @NamedQuery(name = "UsuarioEfeProChe.findByCodigoUsuario", query = "SELECT u FROM UsuarioEfeProChe u WHERE u.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuarioEfeProChe.findByCodigoIfipAgencia", query = "SELECT u FROM UsuarioEfeProChe u WHERE u.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "UsuarioEfeProChe.findByCodigoHabilitadoPor", query = "SELECT u FROM UsuarioEfeProChe u WHERE u.codigoHabilitadoPor = :codigoHabilitadoPor"),
    @NamedQuery(name = "UsuarioEfeProChe.findByFechaHabilitacion", query = "SELECT u FROM UsuarioEfeProChe u WHERE u.fechaHabilitacion = :fechaHabilitacion"),
    @NamedQuery(name = "UsuarioEfeProChe.findByEliminado", query = "SELECT u FROM UsuarioEfeProChe u WHERE u.eliminado = :eliminado"),
//Personalizados
    @NamedQuery(name = "UsuarioEfeProChe.findByUsuarioResponsableEfectivizacionEliminado", query = "SELECT u FROM UsuarioEfeProChe u WHERE u.codigoUsuario = :codigoUsuario AND u.codigoIfipAgencia = :codigoIfipAgencia AND u.eliminado = :eliminado"),
    @NamedQuery(name = "UsuarioEfeProChe.findByUsuarioResponsableEfectivizacion", query = "SELECT u FROM UsuarioEfeProChe u WHERE u.codigoIfipAgencia = :codigoIfipAgencia AND u.eliminado = :eliminado")

})
public class UsuarioEfeProChe implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByUsuarioResponsableEfectivizacionEliminado = "UsuarioEfeProChe.findByUsuarioResponsableEfectivizacionEliminado";
    public static final String findByUsuarioResponsableEfectivizacion = "UsuarioEfeProChe.findByUsuarioResponsableEfectivizacion";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private Long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HABILITADO_POR")
    private Long codigoHabilitadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HABILITACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHabilitacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    private IfipAgencia ifipAgencia;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "HABILITADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario habilitadoPor;

    public UsuarioEfeProChe() {
    }

    public UsuarioEfeProChe(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public UsuarioEfeProChe(Long codigoUsuario, Long codigoIfipAgencia, Long codigoHabilitadoPor, Date fechaHabilitacion, char eliminado) {
        this.codigoUsuario = codigoUsuario;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoHabilitadoPor = codigoHabilitadoPor;
        this.fechaHabilitacion = fechaHabilitacion;
        this.eliminado = eliminado;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaHabilitacion() {
        return fechaHabilitacion;
    }

    public void setFechaHabilitacion(Date fechaHabilitacion) {
        this.fechaHabilitacion = fechaHabilitacion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof UsuarioEfeProChe)) {
            return false;
        }
        UsuarioEfeProChe other = (UsuarioEfeProChe) object;
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioEfeProChe[ codigoUsuario=" + codigoUsuario + " ]";
    }

    /**
     * @return the codigoIfipAgencia
     */
    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    /**
     * @param codigoIfipAgencia the codigoIfipAgencia to set
     */
    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
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

    /**
     * @return the codigoHabilitadoPor
     */
    public long getCodigoHabilitadoPor() {
        return codigoHabilitadoPor;
    }

    /**
     * @param codigoHabilitadoPor the codigoHabilitadoPor to set
     */
    public void setCodigoHabilitadoPor(long codigoHabilitadoPor) {
        this.codigoHabilitadoPor = codigoHabilitadoPor;
    }

    /**
     * @return the habilitadoPor
     */
    public Usuario getHabilitadoPor() {
        return habilitadoPor;
    }

    /**
     * @param habilitadoPor the habilitadoPor to set
     */
    public void setHabilitadoPor(Usuario habilitadoPor) {
        this.habilitadoPor = habilitadoPor;
    }

}
