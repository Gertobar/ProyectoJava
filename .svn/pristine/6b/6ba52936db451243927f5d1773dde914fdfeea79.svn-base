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
 * @author vicastoc
 */
@Embeddable
public class UsuarioSistemaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SISTEMA")
    private long codigoSistema;

    public UsuarioSistemaPK() {
    }

    public UsuarioSistemaPK(long codigoUsuario, long codigoSistema) {
        this.codigoUsuario = codigoUsuario;
        this.codigoSistema = codigoSistema;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(long codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoUsuario;
        hash += (int) codigoSistema;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSistemaPK)) {
            return false;
        }
        UsuarioSistemaPK other = (UsuarioSistemaPK) object;
        if (this.codigoUsuario != other.codigoUsuario) {
            return false;
        }
        if (this.codigoSistema != other.codigoSistema) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioSistemaPK[ codigoUsuario=" + codigoUsuario + ", codigoSistema=" + codigoSistema + " ]";
    }
    
}
