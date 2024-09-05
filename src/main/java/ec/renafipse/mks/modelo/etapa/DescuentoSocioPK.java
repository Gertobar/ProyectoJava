/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.etapa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class DescuentoSocioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CEDULA")
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorte;

    public DescuentoSocioPK() {
    }

    public DescuentoSocioPK(String cedula, Date fechaCorte) {
        this.cedula = cedula;
        this.fechaCorte = fechaCorte;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        hash += (fechaCorte != null ? fechaCorte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescuentoSocioPK)) {
            return false;
        }
        DescuentoSocioPK other = (DescuentoSocioPK) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        if ((this.fechaCorte == null && other.fechaCorte != null) || (this.fechaCorte != null && !this.fechaCorte.equals(other.fechaCorte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.etapa.DescuentoSocioPK[ cedula=" + cedula + ", fechaCorte=" + fechaCorte + " ]";
    }
    
}
