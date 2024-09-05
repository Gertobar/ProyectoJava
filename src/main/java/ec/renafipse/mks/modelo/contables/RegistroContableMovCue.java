/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.REGISTRO_CONTABLE_MOV_CUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroContableMovCue.findAll", query = "SELECT r FROM RegistroContableMovCue r"),
    @NamedQuery(name = "RegistroContableMovCue.findByCodigoMovimiento", query = "SELECT r FROM RegistroContableMovCue r WHERE r.codigoMovimiento = :codigoMovimiento")})
public class RegistroContableMovCue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MOVIMIENTO")
    private Long codigoMovimiento;
    @JoinColumn(name = "CODIGO_REGISTRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RegistroContable codigoRegistro;

    public RegistroContableMovCue() {
    }

    public RegistroContableMovCue(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public Long getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(Long codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public RegistroContable getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(RegistroContable codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMovimiento != null ? codigoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroContableMovCue)) {
            return false;
        }
        RegistroContableMovCue other = (RegistroContableMovCue) object;
        if ((this.codigoMovimiento == null && other.codigoMovimiento != null) || (this.codigoMovimiento != null && !this.codigoMovimiento.equals(other.codigoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.RegistroContableMovCue[ codigoMovimiento=" + codigoMovimiento + " ]";
    }
    
}
