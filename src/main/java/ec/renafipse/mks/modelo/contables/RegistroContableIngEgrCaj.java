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
@Table(name = "MKS_CONTABLES.REGISTRO_CONTABLE_ING_EGR_CAJ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroContableIngEgrCaj.findAll", query = "SELECT r FROM RegistroContableIngEgrCaj r"),
    @NamedQuery(name = "RegistroContableIngEgrCaj.findByCodigoIngEgrCaj", query = "SELECT r FROM RegistroContableIngEgrCaj r WHERE r.codigoIngEgrCaj = :codigoIngEgrCaj")})
public class RegistroContableIngEgrCaj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ING_EGR_CAJ")
    private Long codigoIngEgrCaj;
    @JoinColumn(name = "CODIGO_REGISTRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RegistroContable codigoRegistro;

    public RegistroContableIngEgrCaj() {
    }

    public RegistroContableIngEgrCaj(Long codigoIngEgrCaj) {
        this.codigoIngEgrCaj = codigoIngEgrCaj;
    }

    public Long getCodigoIngEgrCaj() {
        return codigoIngEgrCaj;
    }

    public void setCodigoIngEgrCaj(Long codigoIngEgrCaj) {
        this.codigoIngEgrCaj = codigoIngEgrCaj;
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
        hash += (codigoIngEgrCaj != null ? codigoIngEgrCaj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroContableIngEgrCaj)) {
            return false;
        }
        RegistroContableIngEgrCaj other = (RegistroContableIngEgrCaj) object;
        if ((this.codigoIngEgrCaj == null && other.codigoIngEgrCaj != null) || (this.codigoIngEgrCaj != null && !this.codigoIngEgrCaj.equals(other.codigoIngEgrCaj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.RegistroContableIngEgrCaj[ codigoIngEgrCaj=" + codigoIngEgrCaj + " ]";
    }
    
}
