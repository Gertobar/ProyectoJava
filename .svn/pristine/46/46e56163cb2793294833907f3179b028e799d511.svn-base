/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

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
public class IfipAgenciaJornadaLaboralPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA")
    private short dia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MERIDIANO")
    private char meridiano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;

    public IfipAgenciaJornadaLaboralPK() {
    }

    public IfipAgenciaJornadaLaboralPK(short dia, char meridiano, long codigoIfipAgencia) {
        this.dia = dia;
        this.meridiano = meridiano;
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public short getDia() {
        return dia;
    }

    public void setDia(short dia) {
        this.dia = dia;
    }

    public char getMeridiano() {
        return meridiano;
    }

    public void setMeridiano(char meridiano) {
        this.meridiano = meridiano;
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
        hash += (int) dia;
        hash += (int) meridiano;
        hash += (int) codigoIfipAgencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipAgenciaJornadaLaboralPK)) {
            return false;
        }
        IfipAgenciaJornadaLaboralPK other = (IfipAgenciaJornadaLaboralPK) object;
        if (this.dia != other.dia) {
            return false;
        }
        if (this.meridiano != other.meridiano) {
            return false;
        }
        if (this.codigoIfipAgencia != other.codigoIfipAgencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.IfipAgenciaJornadaLaboralPK[ dia=" + dia + ", meridiano=" + meridiano + ", codigoIfipAgencia=" + codigoIfipAgencia + " ]";
    }
    
}
