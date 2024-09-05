/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.TIPO_FRACCION_MONEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFraccionMoneda.findAll", query = "SELECT t FROM TipoFraccionMoneda t"),
    @NamedQuery(name = "TipoFraccionMoneda.findByCodigo", query = "SELECT t FROM TipoFraccionMoneda t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoFraccionMoneda.findByDenominacion", query = "SELECT t FROM TipoFraccionMoneda t WHERE t.denominacion = :denominacion"),
    @NamedQuery(name = "TipoFraccionMoneda.findByNombre", query = "SELECT t FROM TipoFraccionMoneda t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoFraccionMoneda.findByValor", query = "SELECT t FROM TipoFraccionMoneda t WHERE t.valor = :valor"),
    @NamedQuery(name = "TipoFraccionMoneda.findByEliminado", query = "SELECT t FROM TipoFraccionMoneda t WHERE t.eliminado = :eliminado")})
public class TipoFraccionMoneda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_FRACCION_MONEDA")
    @SequenceGenerator(name = "GSQ_TIPO_FRACCION_MONEDA", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_TIPO_FRACCION_MONEDA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DENOMINACION")
    private char denominacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoFraccionMoneda", fetch = FetchType.LAZY)
    private Collection<FraccionMoneda> fraccionMonedaCollection;

    public TipoFraccionMoneda() {
    }

    public TipoFraccionMoneda(Long codigo) {
        this.codigo = codigo;
    }

    public TipoFraccionMoneda(Long codigo, char denominacion, String nombre, BigDecimal valor, char eliminado) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.nombre = nombre;
        this.valor = valor;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public char getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(char denominacion) {
        this.denominacion = denominacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public Collection<FraccionMoneda> getFraccionMonedaCollection() {
        return fraccionMonedaCollection;
    }

    public void setFraccionMonedaCollection(Collection<FraccionMoneda> fraccionMonedaCollection) {
        this.fraccionMonedaCollection = fraccionMonedaCollection;
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
        if (!(object instanceof TipoFraccionMoneda)) {
            return false;
        }
        TipoFraccionMoneda other = (TipoFraccionMoneda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.TipoFraccionMoneda[ codigo=" + codigo + " ]";
    }
    
}
