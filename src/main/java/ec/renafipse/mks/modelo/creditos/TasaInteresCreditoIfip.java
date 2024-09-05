/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TASA_INTERES_CREDITO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TasaInteresCreditoIfip.findAll", query = "SELECT t FROM TasaInteresCreditoIfip t"),
    @NamedQuery(name = "TasaInteresCreditoIfip.findByCodigo", query = "SELECT t FROM TasaInteresCreditoIfip t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TasaInteresCreditoIfip.findByCodigoIfip", query = "SELECT t FROM TasaInteresCreditoIfip t WHERE t.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TasaInteresCreditoIfip.findByValor", query = "SELECT t FROM TasaInteresCreditoIfip t WHERE t.valor = :valor"),
    @NamedQuery(name = "TasaInteresCreditoIfip.findByRegistradoPor", query = "SELECT t FROM TasaInteresCreditoIfip t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TasaInteresCreditoIfip.findByFechaRegistro", query = "SELECT t FROM TasaInteresCreditoIfip t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TasaInteresCreditoIfip.findByEliminado", query = "SELECT t FROM TasaInteresCreditoIfip t WHERE t.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "TasaInteresCreditoIfip.findByCodigoIfipEliminado", query = "SELECT t FROM TasaInteresCreditoIfip t WHERE t.codigoIfip = :codigoIfip AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TasaInteresCreditoIfip.findByTasaIntProCreMonIfi", query = "SELECT DISTINCT i.codigoTasa FROM TasaInteresProCreMonIfi i JOIN i.productoCreditoMonedaIfip t WHERE t.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND t.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND t.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND i.eliminado = :eliminado")

})
public class TasaInteresCreditoIfip implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByValor = "TasaInteresCreditoIfip.findByValor";
    public static final String findByCodigoIfipEliminado = "TasaInteresCreditoIfip.findByCodigoIfipEliminado";
    public static final String findByTasaIntProCreMonIfi = "TasaInteresCreditoIfip.findByTasaIntProCreMonIfi";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TASA_INTERES_CREDITO_IFIP")
    @SequenceGenerator(name = "GSQ_TASA_INTERES_CREDITO_IFIP", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_TASA_INTERES_CREDITO_IFIP")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTasa", fetch = FetchType.LAZY)
    private Collection<TasaInteresProCreMonIfi> tasaInteresProCreMonIfiCollection;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;
    
     @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;
     
    public TasaInteresCreditoIfip() {
    }

    public TasaInteresCreditoIfip(Long codigo) {
        this.codigo = codigo;
    }

    public TasaInteresCreditoIfip(Long codigo, long codigoIfip, BigDecimal valor, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.valor = valor;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<TasaInteresProCreMonIfi> getTasaInteresProCreMonIfiCollection() {
        return tasaInteresProCreMonIfiCollection;
    }

    public void setTasaInteresProCreMonIfiCollection(Collection<TasaInteresProCreMonIfi> tasaInteresProCreMonIfiCollection) {
        this.tasaInteresProCreMonIfiCollection = tasaInteresProCreMonIfiCollection;
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
        if (!(object instanceof TasaInteresCreditoIfip)) {
            return false;
        }
        TasaInteresCreditoIfip other = (TasaInteresCreditoIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TasaInteresCreditoIfip[ codigo=" + codigo + " ]";
    }

    /**
     * @return the usuarioRegistradoPor
     */
    public Usuario getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Usuario usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }

    /**
     * @return the registradoPor
     */
    public long getRegistradoPor() {
        return registradoPor;
    }

    /**
     * @param registradoPor the registradoPor to set
     */
    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }

}
