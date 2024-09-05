/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ahorros;

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
 * @author vicastoc
 */
    @Entity
@Table(name = "MKS_AHORROS.TASA_INTERES_AHORRO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AhorroTasaInteresIfip.findAll", query = "SELECT a FROM AhorroTasaInteresIfip a"),
    @NamedQuery(name = "AhorroTasaInteresIfip.findByCodigo", query = "SELECT a FROM AhorroTasaInteresIfip a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "AhorroTasaInteresIfip.findByCodigoIfip", query = "SELECT a FROM AhorroTasaInteresIfip a WHERE a.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "AhorroTasaInteresIfip.findByRegistradaPor", query = "SELECT a FROM AhorroTasaInteresIfip a WHERE a.registradaPor = :registradaPor"),
    @NamedQuery(name = "AhorroTasaInteresIfip.findByValor", query = "SELECT a FROM AhorroTasaInteresIfip a WHERE a.valor = :valor"),
    @NamedQuery(name = "AhorroTasaInteresIfip.findByFechaRegistro", query = "SELECT a FROM AhorroTasaInteresIfip a WHERE a.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "AhorroTasaInteresIfip.findByEliminado", query = "SELECT a FROM AhorroTasaInteresIfip a WHERE a.eliminado = :eliminado")})
public class AhorroTasaInteresIfip implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCodigoIfip="AhorroTasaInteresIfip.findByCodigoIfip";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_AHORRO_TASA_INT_IFIP")
    @SequenceGenerator(name = "GSQ_AHORRO_TASA_INT_IFIP", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_AHORRO_TASA_INT_IFIP")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "CODIGO_IFIP")
//    private long codigoIfip;

    @JoinColumn(name = "REGISTRADA_POR",referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario registradaPor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTasaInteres", fetch = FetchType.LAZY)
    private Collection<TasaInteresProductoIfip> tasaInteresProductoIfipCollection;
   @Basic(optional = false)
  @NotNull
    @Column(name = "CODIGO_IFIP")
   private Long codigoIfipAhorro;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
        private Ifip codigoIfip;
     @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADA_POR")
    private Long regristradoPor;

    public AhorroTasaInteresIfip() {
    }

    public AhorroTasaInteresIfip(Long codigo) {
        this.codigo = codigo;
    }

    public AhorroTasaInteresIfip(Long codigo, Ifip codigoIfip, Usuario registradaPor, BigDecimal valor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
       this.codigoIfip=codigoIfip;
        this.registradaPor = registradaPor;
        this.valor = valor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

 

   

    public Usuario getRegistradaPor() {
        return registradaPor;
    }

    public void setRegistradaPor(Usuario registradaPor) {
        this.registradaPor = registradaPor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
//
//    /**
//     * @return the ifip
//     */
//    public Ifip getIfip() {
//        return ifip;
//    }
//
//    /**
//     * @param ifip the ifip to set
//     */
//    public void setIfip(Ifip ifip) {
//        this.ifip = ifip;
//    }

    @XmlTransient
    public Collection<TasaInteresProductoIfip> getTasaInteresProductoIfipCollection() {
        return tasaInteresProductoIfipCollection;
    }

    public void setTasaInteresProductoIfipCollection(Collection<TasaInteresProductoIfip> tasaInteresProductoIfipCollection) {
        this.tasaInteresProductoIfipCollection = tasaInteresProductoIfipCollection;
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
        if (!(object instanceof AhorroTasaInteresIfip)) {
            return false;
        }
        AhorroTasaInteresIfip other = (AhorroTasaInteresIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.AhorroTasaInteresIfip[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoIfip
     */
    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the codigoIfipAhorro
     */
    public Long getCodigoIfipAhorro() {
        return codigoIfipAhorro;
    }

    /**
     * @param codigoIfipAhorro the codigoIfipAhorro to set
     */
    public void setCodigoIfipAhorro(Long codigoIfipAhorro) {
        this.codigoIfipAhorro = codigoIfipAhorro;
    }

    /**
     * @return the regristradoPor
     */
    public Long getRegristradoPor() {
        return regristradoPor;
    }

    /**
     * @param regristradoPor the regristradoPor to set
     */
    public void setRegristradoPor(Long regristradoPor) {
        this.regristradoPor = regristradoPor;
    }


  

   

}
