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
public class GrupoInstitucionAgeDetPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_GRUPO")
    private long codigoGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")
    private long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public GrupoInstitucionAgeDetPK() {
    }

    public GrupoInstitucionAgeDetPK(long codigoGrupo, long codigoSocio, long codigoIfip) {
        this.codigoGrupo = codigoGrupo;
        this.codigoSocio = codigoSocio;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoGrupo;
        hash += (int) codigoSocio;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoInstitucionAgeDetPK)) {
            return false;
        }
        GrupoInstitucionAgeDetPK other = (GrupoInstitucionAgeDetPK) object;
        if (this.codigoGrupo != other.codigoGrupo) {
            return false;
        }
        if (this.codigoSocio != other.codigoSocio) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.GrupoInstitucionAgeDetPK[ codigoGrupo=" + codigoGrupo + ", codigoSocio=" + codigoSocio + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
