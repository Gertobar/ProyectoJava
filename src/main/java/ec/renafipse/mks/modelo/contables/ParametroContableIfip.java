/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.PARAMETRO_CONTABLE_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroContableIfip.findAll", query = "SELECT p FROM ParametroContableIfip p"),
    @NamedQuery(name = "ParametroContableIfip.findByCodigoParametro", query = "SELECT p FROM ParametroContableIfip p WHERE p.parametroContableIfipPK.codigoParametro = :codigoParametro"),
    @NamedQuery(name = "ParametroContableIfip.findByCodigoIfip", query = "SELECT p FROM ParametroContableIfip p WHERE p.parametroContableIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ParametroContableIfip.findByValor", query = "SELECT p FROM ParametroContableIfip p WHERE p.valor = :valor")})
public class ParametroContableIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParametroContableIfipPK parametroContableIfipPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "CODIGO_PARAMETRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ParametroContable parametroContable;

    public ParametroContableIfip() {
    }

    public ParametroContableIfip(ParametroContableIfipPK parametroContableIfipPK) {
        this.parametroContableIfipPK = parametroContableIfipPK;
    }

    public ParametroContableIfip(ParametroContableIfipPK parametroContableIfipPK, String valor) {
        this.parametroContableIfipPK = parametroContableIfipPK;
        this.valor = valor;
    }

    public ParametroContableIfip(long codigoParametro, long codigoIfip) {
        this.parametroContableIfipPK = new ParametroContableIfipPK(codigoParametro, codigoIfip);
    }

    public ParametroContableIfipPK getParametroContableIfipPK() {
        return parametroContableIfipPK;
    }

    public void setParametroContableIfipPK(ParametroContableIfipPK parametroContableIfipPK) {
        this.parametroContableIfipPK = parametroContableIfipPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ParametroContable getParametroContable() {
        return parametroContable;
    }

    public void setParametroContable(ParametroContable parametroContable) {
        this.parametroContable = parametroContable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroContableIfipPK != null ? parametroContableIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroContableIfip)) {
            return false;
        }
        ParametroContableIfip other = (ParametroContableIfip) object;
        if ((this.parametroContableIfipPK == null && other.parametroContableIfipPK != null) || (this.parametroContableIfipPK != null && !this.parametroContableIfipPK.equals(other.parametroContableIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ParametroContableIfip[ parametroContableIfipPK=" + parametroContableIfipPK + " ]";
    }
    
}
