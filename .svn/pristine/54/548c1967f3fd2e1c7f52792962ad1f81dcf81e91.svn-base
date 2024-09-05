/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

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
@Table(name = "MKS_DPFS.TASA_INTERES_DPF_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TasaInteresDpfIfip.findAll", query = "SELECT t FROM TasaInteresDpfIfip t"),
    @NamedQuery(name = "TasaInteresDpfIfip.findByCodigo", query = "SELECT t FROM TasaInteresDpfIfip t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TasaInteresDpfIfip.findByCodigoIfip", query = "SELECT t FROM TasaInteresDpfIfip t WHERE t.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TasaInteresDpfIfip.findByValor", query = "SELECT t FROM TasaInteresDpfIfip t WHERE t.valor = :valor"),
    @NamedQuery(name = "TasaInteresDpfIfip.findByRegistradoPor", query = "SELECT t FROM TasaInteresDpfIfip t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TasaInteresDpfIfip.findByFechaRegistro", query = "SELECT t FROM TasaInteresDpfIfip t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TasaInteresDpfIfip.findByEliminado", query = "SELECT t FROM TasaInteresDpfIfip t WHERE t.eliminado = :eliminado")})
public class TasaInteresDpfIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoIfip="TasaInteresDpfIfip.findByCodigoIfip";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TASA_INTERES_DPF_IFIP")
    @SequenceGenerator(name = "GSQ_TASA_INTERES_DPF_IFIP", schema = "MKS_DPFS",  allocationSize = 0, sequenceName = "SEQ_TASA_INTERES_DPF_IFIP")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    private Collection<TasaInteresProDpfMon> tasaInteresProDpfMonCollection;
        @JoinColumn(name = "REGISTRADO_POR",referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario registradaPor;
        
         @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
        private Ifip codigoIfipDpf;
    public TasaInteresDpfIfip() {
    }

    public TasaInteresDpfIfip(Long codigo) {
        this.codigo = codigo;
    }

    public TasaInteresDpfIfip(Long codigo, long codigoIfip, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
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

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<TasaInteresProDpfMon> getTasaInteresProDpfMonCollection() {
        return tasaInteresProDpfMonCollection;
    }

    public void setTasaInteresProDpfMonCollection(Collection<TasaInteresProDpfMon> tasaInteresProDpfMonCollection) {
        this.tasaInteresProDpfMonCollection = tasaInteresProDpfMonCollection;
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
        if (!(object instanceof TasaInteresDpfIfip)) {
            return false;
        }
        TasaInteresDpfIfip other = (TasaInteresDpfIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.TasaInteresDpfIfip[ codigo=" + codigo + " ]";
    }

    /**
     * @return the registradaPor
     */
    public Usuario getRegistradaPor() {
        return registradaPor;
    }

    /**
     * @param registradaPor the registradaPor to set
     */
    public void setRegistradaPor(Usuario registradaPor) {
        this.registradaPor = registradaPor;
    }

    /**
     * @return the codigoIfipDpf
     */
    public Ifip getCodigoIfipDpf() {
        return codigoIfipDpf;
    }

    /**
     * @param codigoIfipDpf the codigoIfipDpf to set
     */
    public void setCodigoIfipDpf(Ifip codigoIfipDpf) {
        this.codigoIfipDpf = codigoIfipDpf;
    }
    
}
