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
import javax.validation.constraints.Size;

/**
 *
 * @author vicastoc
 */
@Embeddable
public class PersonaTelTraActEcoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PER_TRA_ACT_ECO")
    private long codigoPerTraActEco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "NUMERO")
    private String numero;

    public PersonaTelTraActEcoPK() {
    }

    public PersonaTelTraActEcoPK(long codigoPerTraActEco, String numero) {
        this.codigoPerTraActEco = codigoPerTraActEco;
        this.numero = numero;
    }

    public long getCodigoPerTraActEco() {
        return codigoPerTraActEco;
    }

    public void setCodigoPerTraActEco(long codigoPerTraActEco) {
        this.codigoPerTraActEco = codigoPerTraActEco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPerTraActEco;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaTelTraActEcoPK)) {
            return false;
        }
        PersonaTelTraActEcoPK other = (PersonaTelTraActEcoPK) object;
        if (this.codigoPerTraActEco != other.codigoPerTraActEco) {
            return false;
        }
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaTelTraActEcoPK[ codigoPerTraActEco=" + codigoPerTraActEco + ", numero=" + numero + " ]";
    }
    
}
