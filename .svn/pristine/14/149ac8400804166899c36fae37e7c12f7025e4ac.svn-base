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
public class UsuarioIfipAgenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;

    public UsuarioIfipAgenciaPK() {
    }

    public UsuarioIfipAgenciaPK(long codigoIfipAgencia, long codigoUsuario) {
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
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
        hash += (int) codigoIfipAgencia;
        hash += (int) codigoUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioIfipAgenciaPK)) {
            return false;
        }
        UsuarioIfipAgenciaPK other = (UsuarioIfipAgenciaPK) object;
        if (this.codigoIfipAgencia != other.codigoIfipAgencia) {
            return false;
        }
        if (this.codigoUsuario != other.codigoUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgenciaPK[ codigoIfipAgencia=" + codigoIfipAgencia + ", codigoUsuario=" + codigoUsuario + " ]";
    }
    
}
