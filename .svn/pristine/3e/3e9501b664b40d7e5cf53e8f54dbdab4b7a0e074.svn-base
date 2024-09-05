/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

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
 * @author pasante4
 */
@Entity
@Table(name = "MKS_DPFS.PARAMETRO_GENERAL_DPF_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroGeneralDpfIfip.findAll", query = "SELECT p FROM ParametroGeneralDpfIfip p"),
    @NamedQuery(name = "ParametroGeneralDpfIfip.findByCodigoParametro", query = "SELECT p FROM ParametroGeneralDpfIfip p WHERE p.parametroGeneralDpfIfipPK.codigoParametro = :codigoParametro"),
    @NamedQuery(name = "ParametroGeneralDpfIfip.findByCodigoIfip", query = "SELECT p FROM ParametroGeneralDpfIfip p WHERE p.parametroGeneralDpfIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ParametroGeneralDpfIfip.findByValorTexto", query = "SELECT p FROM ParametroGeneralDpfIfip p WHERE p.valorTexto = :valorTexto"),
    @NamedQuery(name = "ParametroGeneralDpfIfip.findByValorNumerico", query = "SELECT p FROM ParametroGeneralDpfIfip p WHERE p.valorNumerico = :valorNumerico"),
    @NamedQuery(name = "ParametroGeneralDpfIfip.findByValorFecha", query = "SELECT p FROM ParametroGeneralDpfIfip p WHERE p.valorFecha = :valorFecha"),
    @NamedQuery(name = "ParametroGeneralDpfIfip.findByEliminado", query = "SELECT p FROM ParametroGeneralDpfIfip p WHERE p.eliminado = :eliminado")})
public class ParametroGeneralDpfIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParametroGeneralDpfIfipPK parametroGeneralDpfIfipPK;
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
    private ParametroGeneralDpf parametroGeneralDpf;

    public ParametroGeneralDpfIfip() {
    }

    public ParametroGeneralDpfIfip(ParametroGeneralDpfIfipPK parametroGeneralDpfIfipPK) {
        this.parametroGeneralDpfIfipPK = parametroGeneralDpfIfipPK;
    }

    public ParametroGeneralDpfIfip(ParametroGeneralDpfIfipPK parametroGeneralDpfIfipPK, char eliminado) {
        this.parametroGeneralDpfIfipPK = parametroGeneralDpfIfipPK;
        this.eliminado = eliminado;
    }

    public ParametroGeneralDpfIfip(long codigoParametro, long codigoIfip) {
        this.parametroGeneralDpfIfipPK = new ParametroGeneralDpfIfipPK(codigoParametro, codigoIfip);
    }

    public ParametroGeneralDpfIfipPK getParametroGeneralDpfIfipPK() {
        return parametroGeneralDpfIfipPK;
    }

    public void setParametroGeneralDpfIfipPK(ParametroGeneralDpfIfipPK parametroGeneralDpfIfipPK) {
        this.parametroGeneralDpfIfipPK = parametroGeneralDpfIfipPK;
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

    public ParametroGeneralDpf getParametroGeneralDpf() {
        return parametroGeneralDpf;
    }

    public void setParametroGeneralDpf(ParametroGeneralDpf parametroGeneralDpf) {
        this.parametroGeneralDpf = parametroGeneralDpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroGeneralDpfIfipPK != null ? parametroGeneralDpfIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroGeneralDpfIfip)) {
            return false;
        }
        ParametroGeneralDpfIfip other = (ParametroGeneralDpfIfip) object;
        if ((this.parametroGeneralDpfIfipPK == null && other.parametroGeneralDpfIfipPK != null) || (this.parametroGeneralDpfIfipPK != null && !this.parametroGeneralDpfIfipPK.equals(other.parametroGeneralDpfIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.ParametroGeneralDpfIfip[ parametroGeneralDpfIfipPK=" + parametroGeneralDpfIfipPK + " ]";
    }
    
}
