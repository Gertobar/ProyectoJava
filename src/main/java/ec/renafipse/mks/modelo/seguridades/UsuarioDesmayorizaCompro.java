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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "MKS_SEGURIDADES.USUARIO_DESMAYORIZA_COMPRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioDesmayorizaCompro.findAll", query = "SELECT u FROM UsuarioDesmayorizaCompro u"),
    @NamedQuery(name = "UsuarioDesmayorizaCompro.findByCodigoUsuario", query = "SELECT u FROM UsuarioDesmayorizaCompro u WHERE u.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "UsuarioDesmayorizaCompro.findByFechaAsignacion", query = "SELECT u FROM UsuarioDesmayorizaCompro u WHERE u.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "UsuarioDesmayorizaCompro.findByEliminado", query = "SELECT u FROM UsuarioDesmayorizaCompro u WHERE u.eliminado = :eliminado")
    
})
public class UsuarioDesmayorizaCompro implements Serializable {
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

    public UsuarioDesmayorizaCompro() {
    }

    public UsuarioDesmayorizaCompro(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public UsuarioDesmayorizaCompro(Long codigoUsuario, Date fechaAsignacion, char eliminado) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioDesmayorizaCompro)) {
            return false;
        }
        UsuarioDesmayorizaCompro other = (UsuarioDesmayorizaCompro) object;
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioDesmayorizaCompro[ codigoUsuario=" + codigoUsuario + " ]";
    }
    
}
