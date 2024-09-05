/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.PARAMETRO_GENERAL_SEG_IFI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroGeneralSegIfi.findAll", query = "SELECT p FROM ParametroGeneralSegIfi p"),
    @NamedQuery(name = "ParametroGeneralSegIfi.findByCodigoParametro", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.parametroGeneralSegIfiPK.codigoParametro = :codigoParametro"),
    @NamedQuery(name = "ParametroGeneralSegIfi.findByCodigoIfip", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.parametroGeneralSegIfiPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ParametroGeneralSegIfi.findByValorNumerico", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.valorNumerico = :valorNumerico"),
    @NamedQuery(name = "ParametroGeneralSegIfi.findByValorTexto", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.valorTexto = :valorTexto"),
    @NamedQuery(name = "ParametroGeneralSegIfi.findByValorFechaUno", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.valorFechaUno = :valorFechaUno"),
    @NamedQuery(name = "ParametroGeneralSegIfi.findByValorFechaDos", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.valorFechaDos = :valorFechaDos"),
    @NamedQuery(name = "ParametroGeneralSegIfi.findByEliminado", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "ParametroGeneralSegIfi.findByCodigoIfipCodigoParametro", query = "SELECT p FROM ParametroGeneralSegIfi p WHERE p.parametroGeneralSegIfiPK.codigoIfip = :codigoIfip AND p.parametroGeneralSegIfiPK.codigoParametro = :codigoParametro")
})
public class ParametroGeneralSegIfi implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoIfip="ParametroGeneralSegIfi.findByCodigoIfip";
    public static final String findByCodigoIfipCodigoParametro="ParametroGeneralSegIfi.findByCodigoIfipCodigoParametro";
    @EmbeddedId
    protected ParametroGeneralSegIfiPK parametroGeneralSegIfiPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_NUMERICO")
    private BigDecimal valorNumerico;
    @Size(max = 100)
    @Column(name = "VALOR_TEXTO")
    private String valorTexto;
    @Column(name = "VALOR_FECHA_UNO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valorFechaUno;
    @Column(name = "VALOR_FECHA_DOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valorFechaDos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PARAMETRO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ParametroGeneralSeguridad parametroGeneralSeguridad;

    public ParametroGeneralSegIfi() {
    }

    public ParametroGeneralSegIfi(ParametroGeneralSegIfiPK parametroGeneralSegIfiPK) {
        this.parametroGeneralSegIfiPK = parametroGeneralSegIfiPK;
    }

    public ParametroGeneralSegIfi(ParametroGeneralSegIfiPK parametroGeneralSegIfiPK, char eliminado) {
        this.parametroGeneralSegIfiPK = parametroGeneralSegIfiPK;
        this.eliminado = eliminado;
    }

    public ParametroGeneralSegIfi(long codigoParametro, long codigoIfip) {
        this.parametroGeneralSegIfiPK = new ParametroGeneralSegIfiPK(codigoParametro, codigoIfip);
    }

    public ParametroGeneralSegIfiPK getParametroGeneralSegIfiPK() {
        return parametroGeneralSegIfiPK;
    }

    public void setParametroGeneralSegIfiPK(ParametroGeneralSegIfiPK parametroGeneralSegIfiPK) {
        this.parametroGeneralSegIfiPK = parametroGeneralSegIfiPK;
    }

    public BigDecimal getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(BigDecimal valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public Date getValorFechaUno() {
        return valorFechaUno;
    }

    public void setValorFechaUno(Date valorFechaUno) {
        this.valorFechaUno = valorFechaUno;
    }

    public Date getValorFechaDos() {
        return valorFechaDos;
    }

    public void setValorFechaDos(Date valorFechaDos) {
        this.valorFechaDos = valorFechaDos;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public ParametroGeneralSeguridad getParametroGeneralSeguridad() {
        return parametroGeneralSeguridad;
    }

    public void setParametroGeneralSeguridad(ParametroGeneralSeguridad parametroGeneralSeguridad) {
        this.parametroGeneralSeguridad = parametroGeneralSeguridad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroGeneralSegIfiPK != null ? parametroGeneralSegIfiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroGeneralSegIfi)) {
            return false;
        }
        ParametroGeneralSegIfi other = (ParametroGeneralSegIfi) object;
        if ((this.parametroGeneralSegIfiPK == null && other.parametroGeneralSegIfiPK != null) || (this.parametroGeneralSegIfiPK != null && !this.parametroGeneralSegIfiPK.equals(other.parametroGeneralSegIfiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.ParametroGeneralSegIfi[ parametroGeneralSegIfiPK=" + parametroGeneralSegIfiPK + " ]";
    }
    
}
