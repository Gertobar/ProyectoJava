/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PARAMETRO_GENERAL_CREDITO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findAll", query = "SELECT p FROM ParametroGeneralCreditoIfip p"),
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findByCodigoParametro", query = "SELECT p FROM ParametroGeneralCreditoIfip p WHERE p.parametroGeneralCreditoIfipPK.codigoParametro = :codigoParametro"),
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findByCodigoIfip", query = "SELECT p FROM ParametroGeneralCreditoIfip p WHERE p.parametroGeneralCreditoIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findByValorTexto", query = "SELECT p FROM ParametroGeneralCreditoIfip p WHERE p.valorTexto = :valorTexto"),
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findByValorNumerico", query = "SELECT p FROM ParametroGeneralCreditoIfip p WHERE p.valorNumerico = :valorNumerico"),
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findByValorFecha", query = "SELECT p FROM ParametroGeneralCreditoIfip p WHERE p.valorFecha = :valorFecha"),
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findByEliminado", query = "SELECT p FROM ParametroGeneralCreditoIfip p WHERE p.eliminado = :eliminado"),
//Personalizadas
    @NamedQuery(name = "ParametroGeneralCreditoIfip.findByParametroIfipEliminado", query = "SELECT p FROM ParametroGeneralCreditoIfip p WHERE p.parametroGeneralCreditoIfipPK.codigoParametro = :codigoParametro AND p.parametroGeneralCreditoIfipPK.codigoIfip = :codigoIfip AND p.eliminado = :eliminado")
})
public class ParametroGeneralCreditoIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByParametroIfipEliminado = "ParametroGeneralCreditoIfip.findByParametroIfipEliminado";
    @EmbeddedId
    protected ParametroGeneralCreditoIfipPK parametroGeneralCreditoIfipPK;
    @Size(max = 100)
    @Column(name = "VALOR_TEXTO")
    private String valorTexto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_NUMERICO")
    private BigDecimal valorNumerico;
    @Column(name = "VALOR_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valorFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PARAMETRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ParametroGeneralCredito parametroGeneralCredito;

    public ParametroGeneralCreditoIfip() {
    }

    public ParametroGeneralCreditoIfip(ParametroGeneralCreditoIfipPK parametroGeneralCreditoIfipPK) {
        this.parametroGeneralCreditoIfipPK = parametroGeneralCreditoIfipPK;
    }

    public ParametroGeneralCreditoIfip(ParametroGeneralCreditoIfipPK parametroGeneralCreditoIfipPK, char eliminado) {
        this.parametroGeneralCreditoIfipPK = parametroGeneralCreditoIfipPK;
        this.eliminado = eliminado;
    }

    public ParametroGeneralCreditoIfip(long codigoParametro, long codigoIfip) {
        this.parametroGeneralCreditoIfipPK = new ParametroGeneralCreditoIfipPK(codigoParametro, codigoIfip);
    }

    public ParametroGeneralCreditoIfipPK getParametroGeneralCreditoIfipPK() {
        return parametroGeneralCreditoIfipPK;
    }

    public void setParametroGeneralCreditoIfipPK(ParametroGeneralCreditoIfipPK parametroGeneralCreditoIfipPK) {
        this.parametroGeneralCreditoIfipPK = parametroGeneralCreditoIfipPK;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public BigDecimal getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(BigDecimal valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public Date getValorFecha() {
        return valorFecha;
    }

    public void setValorFecha(Date valorFecha) {
        this.valorFecha = valorFecha;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public ParametroGeneralCredito getParametroGeneralCredito() {
        return parametroGeneralCredito;
    }

    public void setParametroGeneralCredito(ParametroGeneralCredito parametroGeneralCredito) {
        this.parametroGeneralCredito = parametroGeneralCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroGeneralCreditoIfipPK != null ? parametroGeneralCreditoIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroGeneralCreditoIfip)) {
            return false;
        }
        ParametroGeneralCreditoIfip other = (ParametroGeneralCreditoIfip) object;
        if ((this.parametroGeneralCreditoIfipPK == null && other.parametroGeneralCreditoIfipPK != null) || (this.parametroGeneralCreditoIfipPK != null && !this.parametroGeneralCreditoIfipPK.equals(other.parametroGeneralCreditoIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ParametroGeneralCreditoIfip[ parametroGeneralCreditoIfipPK=" + parametroGeneralCreditoIfipPK + " ]";
    }
    
}
