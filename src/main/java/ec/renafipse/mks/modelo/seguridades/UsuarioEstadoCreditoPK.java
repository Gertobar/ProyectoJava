/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vastudillo
 */
@Embeddable
public class UsuarioEstadoCreditoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO")
    private long codigoEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;

    public UsuarioEstadoCreditoPK() {
    }

    public UsuarioEstadoCreditoPK(long codigoEstado, long codigoUsuario) {
        this.codigoEstado = codigoEstado;
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(long codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoEstado;
        hash += (int) codigoUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEstadoCreditoPK)) {
            return false;
        }
        UsuarioEstadoCreditoPK other = (UsuarioEstadoCreditoPK) object;
        if (this.codigoEstado != other.codigoEstado) {
            return false;
        }
        if (this.codigoUsuario != other.codigoUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioEstadoCreditoPK[ codigoEstado=" + codigoEstado + ", codigoUsuario=" + codigoUsuario + " ]";
    }
    
}
