/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class GrupoInstitucionIfipAgenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_GRUPO")
    private long codigoGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;

    public GrupoInstitucionIfipAgenciaPK() {
    }

    public GrupoInstitucionIfipAgenciaPK(long codigoGrupo, long codigoIfipAgencia) {
        this.codigoGrupo = codigoGrupo;
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoGrupo;
        hash += (int) codigoIfipAgencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoInstitucionIfipAgenciaPK)) {
            return false;
        }
        GrupoInstitucionIfipAgenciaPK other = (GrupoInstitucionIfipAgenciaPK) object;
        if (this.codigoGrupo != other.codigoGrupo) {
            return false;
        }
        if (this.codigoIfipAgencia != other.codigoIfipAgencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.GrupoInstitucionIfipAgenciaPK[ codigoGrupo=" + codigoGrupo + ", codigoIfipAgencia=" + codigoIfipAgencia + " ]";
    }
    
}
