/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

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
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_IFIPS.PARAMETRO_SERVIDOR_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroServidorIfip.findAll", query = "SELECT p FROM ParametroServidorIfip p"),
    @NamedQuery(name = "ParametroServidorIfip.findByCodigoParametro", query = "SELECT p FROM ParametroServidorIfip p WHERE p.parametroServidorIfipPK.codigoParametro = :codigoParametro"),
    @NamedQuery(name = "ParametroServidorIfip.findByCodigoIfip", query = "SELECT p FROM ParametroServidorIfip p WHERE p.parametroServidorIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ParametroServidorIfip.findByValor", query = "SELECT p FROM ParametroServidorIfip p WHERE p.valor = :valor")})
public class ParametroServidorIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParametroServidorIfipPK parametroServidorIfipPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "CODIGO_PARAMETRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ParametroServidor parametroServidor;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;

    public ParametroServidorIfip() {
    }

    public ParametroServidorIfip(ParametroServidorIfipPK parametroServidorIfipPK) {
        this.parametroServidorIfipPK = parametroServidorIfipPK;
    }

    public ParametroServidorIfip(ParametroServidorIfipPK parametroServidorIfipPK, String valor) {
        this.parametroServidorIfipPK = parametroServidorIfipPK;
        this.valor = valor;
    }

    public ParametroServidorIfip(long codigoParametro, long codigoIfip) {
        this.parametroServidorIfipPK = new ParametroServidorIfipPK(codigoParametro, codigoIfip);
    }

    public ParametroServidorIfipPK getParametroServidorIfipPK() {
        return parametroServidorIfipPK;
    }

    public void setParametroServidorIfipPK(ParametroServidorIfipPK parametroServidorIfipPK) {
        this.parametroServidorIfipPK = parametroServidorIfipPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ParametroServidor getParametroServidor() {
        return parametroServidor;
    }

    public void setParametroServidor(ParametroServidor parametroServidor) {
        this.parametroServidor = parametroServidor;
    }

    public Ifip getIfip() {
        return ifip;
    }

    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroServidorIfipPK != null ? parametroServidorIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroServidorIfip)) {
            return false;
        }
        ParametroServidorIfip other = (ParametroServidorIfip) object;
        if ((this.parametroServidorIfipPK == null && other.parametroServidorIfipPK != null) || (this.parametroServidorIfipPK != null && !this.parametroServidorIfipPK.equals(other.parametroServidorIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ifips.ParametroServidorIfip[ parametroServidorIfipPK=" + parametroServidorIfipPK + " ]";
    }
    
}
