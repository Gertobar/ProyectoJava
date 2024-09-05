/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class ImpresionLibretaCuentaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private long codigoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_LIBRETA")
    private String numeroLibreta;

    public ImpresionLibretaCuentaPK() {
    }

    public ImpresionLibretaCuentaPK(long codigoMovimiento, String numeroLibreta) {
        this.codigoMovimiento = codigoMovimiento;
        this.numeroLibreta = numeroLibreta;
    }

    public long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public String getNumeroLibreta() {
        return numeroLibreta;
    }

    public void setNumeroLibreta(String numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoMovimiento;
        hash += (numeroLibreta != null ? numeroLibreta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpresionLibretaCuentaPK)) {
            return false;
        }
        ImpresionLibretaCuentaPK other = (ImpresionLibretaCuentaPK) object;
        if (this.codigoMovimiento != other.codigoMovimiento) {
            return false;
        }
        if ((this.numeroLibreta == null && other.numeroLibreta != null) || (this.numeroLibreta != null && !this.numeroLibreta.equals(other.numeroLibreta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ImpresionLibretaCuentaPK[ codigoMovimiento=" + codigoMovimiento + ", numeroLibreta=" + numeroLibreta + " ]";
    }
    
}
