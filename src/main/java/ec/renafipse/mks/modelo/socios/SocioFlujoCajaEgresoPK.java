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
 * @author andres
 */
@Embeddable
public class SocioFlujoCajaEgresoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ITEM_FLU_CAJ")
    private long codigoItemFluCaj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;

    public SocioFlujoCajaEgresoPK() {
    }

    public SocioFlujoCajaEgresoPK(long codigoItemFluCaj, long codigoPersona) {
        this.codigoItemFluCaj = codigoItemFluCaj;
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoItemFluCaj() {
        return codigoItemFluCaj;
    }

    public void setCodigoItemFluCaj(long codigoItemFluCaj) {
        this.codigoItemFluCaj = codigoItemFluCaj;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoItemFluCaj;
        hash += (int) codigoPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioFlujoCajaEgresoPK)) {
            return false;
        }
        SocioFlujoCajaEgresoPK other = (SocioFlujoCajaEgresoPK) object;
        if (this.codigoItemFluCaj != other.codigoItemFluCaj) {
            return false;
        }
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioFlujoCajaEgresoPK[ codigoItemFluCaj=" + codigoItemFluCaj + ", codigoPersona=" + codigoPersona + " ]";
    }
    
}