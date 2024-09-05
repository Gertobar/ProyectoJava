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
public class RespuestaPreguntaUsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_BANCO_PREGUNTA")
    private long codigoBancoPregunta;

    public RespuestaPreguntaUsuarioPK() {
    }

    public RespuestaPreguntaUsuarioPK(long codigoUsuario, long codigoBancoPregunta) {
        this.codigoUsuario = codigoUsuario;
        this.codigoBancoPregunta = codigoBancoPregunta;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoBancoPregunta() {
        return codigoBancoPregunta;
    }

    public void setCodigoBancoPregunta(long codigoBancoPregunta) {
        this.codigoBancoPregunta = codigoBancoPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoUsuario;
        hash += (int) codigoBancoPregunta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPreguntaUsuarioPK)) {
            return false;
        }
        RespuestaPreguntaUsuarioPK other = (RespuestaPreguntaUsuarioPK) object;
        if (this.codigoUsuario != other.codigoUsuario) {
            return false;
        }
        if (this.codigoBancoPregunta != other.codigoBancoPregunta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RespuestaPreguntaUsuarioPK[ codigoUsuario=" + codigoUsuario + ", codigoBancoPregunta=" + codigoBancoPregunta + " ]";
    }
    
}
