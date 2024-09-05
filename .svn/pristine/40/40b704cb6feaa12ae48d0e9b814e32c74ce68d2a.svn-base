/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.tesoreria;

import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_TESORERIA.PARAMETRO_BANCO_CENTRAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroBancoCentral.findByCodigoIfip", query = "SELECT p FROM ParametroBancoCentral p WHERE p.codigoIfip.codigo = :codigoIfip ORDER BY p.codigo")
})
public class ParametroBancoCentral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PARAMETRO_BANCO_CENTRAL")
    @SequenceGenerator(name = "GSQ_PARAMETRO_BANCO_CENTRAL", schema = "MKS_TESORERIA",  allocationSize = 0, sequenceName = "SEQ_PARAMETRO_BANCO_CENTRAL")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 10)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoParametroBanCen")
    private Collection<ParametroBanCenUsuMod> parametroBanCenUsuModCollection;
    @JoinColumn(name = "CODIGO_TIPO_PAR_BAN_CEN", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoParametroBancoCentral codigoTipoParBanCen;

    public ParametroBancoCentral() {
    }

    public ParametroBancoCentral(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroBancoCentral(Long codigo, Ifip codigoIfip, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @XmlTransient
    public Collection<ParametroBanCenUsuMod> getParametroBanCenUsuModCollection() {
        return parametroBanCenUsuModCollection;
    }

    public void setParametroBanCenUsuModCollection(Collection<ParametroBanCenUsuMod> parametroBanCenUsuModCollection) {
        this.parametroBanCenUsuModCollection = parametroBanCenUsuModCollection;
    }

    public TipoParametroBancoCentral getCodigoTipoParBanCen() {
        return codigoTipoParBanCen;
    }

    public void setCodigoTipoParBanCen(TipoParametroBancoCentral codigoTipoParBanCen) {
        this.codigoTipoParBanCen = codigoTipoParBanCen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroBancoCentral)) {
            return false;
        }
        ParametroBancoCentral other = (ParametroBancoCentral) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.tesoreria.ParametroBancoCentral[ codigo=" + codigo + " ]";
    }
    
}
