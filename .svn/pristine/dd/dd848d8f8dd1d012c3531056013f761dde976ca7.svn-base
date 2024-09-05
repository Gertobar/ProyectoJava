/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

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
@Table(name = "MKS_ADQUISICIONES.PARAMETRO_COMPRA_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroCompraIfip.findAll", query = "SELECT p FROM ParametroCompraIfip p"),
    @NamedQuery(name = "ParametroCompraIfip.findByCodigoParametro", query = "SELECT p FROM ParametroCompraIfip p WHERE p.parametroCompraIfipPK.codigoParametro = :codigoParametro"),
    @NamedQuery(name = "ParametroCompraIfip.findByCodigoIfip", query = "SELECT p FROM ParametroCompraIfip p WHERE p.parametroCompraIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ParametroCompraIfip.findByValor", query = "SELECT p FROM ParametroCompraIfip p WHERE p.valor = :valor"),
////PERSONALIZADOS
    @NamedQuery(name = "ParametroCompraIfip.findByCodigIfipParametro", query = "SELECT p FROM ParametroCompraIfip p WHERE p.parametroCompraIfipPK.codigoIfip = :codigoIfip AND p.parametroCompraIfipPK.codigoParametro = :codigoParametro")
    
})
public class ParametroCompraIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String findByIfipEntreFecha="Comprobante.findByIfipEntreFecha";
    @EmbeddedId
    protected ParametroCompraIfipPK parametroCompraIfipPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "CODIGO_PARAMETRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ParametroCompra parametroCompra;

    public ParametroCompraIfip() {
    }

    public ParametroCompraIfip(ParametroCompraIfipPK parametroCompraIfipPK) {
        this.parametroCompraIfipPK = parametroCompraIfipPK;
    }

    public ParametroCompraIfip(ParametroCompraIfipPK parametroCompraIfipPK, String valor) {
        this.parametroCompraIfipPK = parametroCompraIfipPK;
        this.valor = valor;
    }

    public ParametroCompraIfip(long codigoParametro, long codigoIfip) {
        this.parametroCompraIfipPK = new ParametroCompraIfipPK(codigoParametro, codigoIfip);
    }

    public ParametroCompraIfipPK getParametroCompraIfipPK() {
        return parametroCompraIfipPK;
    }

    public void setParametroCompraIfipPK(ParametroCompraIfipPK parametroCompraIfipPK) {
        this.parametroCompraIfipPK = parametroCompraIfipPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ParametroCompra getParametroCompra() {
        return parametroCompra;
    }

    public void setParametroCompra(ParametroCompra parametroCompra) {
        this.parametroCompra = parametroCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroCompraIfipPK != null ? parametroCompraIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroCompraIfip)) {
            return false;
        }
        ParametroCompraIfip other = (ParametroCompraIfip) object;
        if ((this.parametroCompraIfipPK == null && other.parametroCompraIfipPK != null) || (this.parametroCompraIfipPK != null && !this.parametroCompraIfipPK.equals(other.parametroCompraIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfip[ parametroCompraIfipPK=" + parametroCompraIfipPK + " ]";
    }
    
}
