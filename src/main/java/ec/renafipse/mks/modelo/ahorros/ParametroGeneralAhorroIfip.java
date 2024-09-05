/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.PARAMETRO_GENERAL_AHORRO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroGeneralAhorroIfip.findAll", query = "SELECT p FROM ParametroGeneralAhorroIfip p"),
    @NamedQuery(name = "ParametroGeneralAhorroIfip.findByCodigoParametro", query = "SELECT p FROM ParametroGeneralAhorroIfip p WHERE p.parametroGeneralAhorroIfipPK.codigoParametro = :codigoParametro"),
    @NamedQuery(name = "ParametroGeneralAhorroIfip.findByCodigoIfip", query = "SELECT p FROM ParametroGeneralAhorroIfip p WHERE p.parametroGeneralAhorroIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ParametroGeneralAhorroIfip.findByValorTexto", query = "SELECT p FROM ParametroGeneralAhorroIfip p WHERE p.valorTexto = :valorTexto"),
    @NamedQuery(name = "ParametroGeneralAhorroIfip.findByValorNumerico", query = "SELECT p FROM ParametroGeneralAhorroIfip p WHERE p.valorNumerico = :valorNumerico"),
    @NamedQuery(name = "ParametroGeneralAhorroIfip.findByValorFecha", query = "SELECT p FROM ParametroGeneralAhorroIfip p WHERE p.valorFecha = :valorFecha"),
    @NamedQuery(name = "ParametroGeneralAhorroIfip.findByEliminado", query = "SELECT p FROM ParametroGeneralAhorroIfip p WHERE p.eliminado = :eliminado")})
public class ParametroGeneralAhorroIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParametroGeneralAhorroIfipPK parametroGeneralAhorroIfipPK;
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
    private ParametroGeneralAhorro parametroGeneralAhorro;

    public ParametroGeneralAhorroIfip() {
    }

    public ParametroGeneralAhorroIfip(ParametroGeneralAhorroIfipPK parametroGeneralAhorroIfipPK) {
        this.parametroGeneralAhorroIfipPK = parametroGeneralAhorroIfipPK;
    }

    public ParametroGeneralAhorroIfip(ParametroGeneralAhorroIfipPK parametroGeneralAhorroIfipPK, char eliminado) {
        this.parametroGeneralAhorroIfipPK = parametroGeneralAhorroIfipPK;
        this.eliminado = eliminado;
    }

    public ParametroGeneralAhorroIfip(long codigoParametro, long codigoIfip) {
        this.parametroGeneralAhorroIfipPK = new ParametroGeneralAhorroIfipPK(codigoParametro, codigoIfip);
    }

    public ParametroGeneralAhorroIfipPK getParametroGeneralAhorroIfipPK() {
        return parametroGeneralAhorroIfipPK;
    }

    public void setParametroGeneralAhorroIfipPK(ParametroGeneralAhorroIfipPK parametroGeneralAhorroIfipPK) {
        this.parametroGeneralAhorroIfipPK = parametroGeneralAhorroIfipPK;
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

    public ParametroGeneralAhorro getParametroGeneralAhorro() {
        return parametroGeneralAhorro;
    }

    public void setParametroGeneralAhorro(ParametroGeneralAhorro parametroGeneralAhorro) {
        this.parametroGeneralAhorro = parametroGeneralAhorro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroGeneralAhorroIfipPK != null ? parametroGeneralAhorroIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroGeneralAhorroIfip)) {
            return false;
        }
        ParametroGeneralAhorroIfip other = (ParametroGeneralAhorroIfip) object;
        if ((this.parametroGeneralAhorroIfipPK == null && other.parametroGeneralAhorroIfipPK != null) || (this.parametroGeneralAhorroIfipPK != null && !this.parametroGeneralAhorroIfipPK.equals(other.parametroGeneralAhorroIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ParametroGeneralAhorroIfip[ parametroGeneralAhorroIfipPK=" + parametroGeneralAhorroIfipPK + " ]";
    }
    
}
