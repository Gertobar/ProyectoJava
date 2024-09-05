/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author scordero
 */
@Entity
@Table(name = "MKS_SEGUROS.VALOR_SEGURO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorSeguro.findAll", query = "SELECT v FROM ValorSeguro v"),
    @NamedQuery(name = "ValorSeguro.findByCodigo", query = "SELECT v FROM ValorSeguro v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "ValorSeguro.findByDescripcion", query = "SELECT v FROM ValorSeguro v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "ValorSeguro.findByValor", query = "SELECT v FROM ValorSeguro v WHERE v.valor = :valor"),
    @NamedQuery(name = "ValorSeguro.findByFechaInicio", query = "SELECT v FROM ValorSeguro v WHERE v.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ValorSeguro.findByFechaFin", query = "SELECT v FROM ValorSeguro v WHERE v.fechaFin = :fechaFin")})
public class ValorSeguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoValor")
    private List<ContratoSeguro> contratoSeguroList;

    public ValorSeguro() {
    }

    public ValorSeguro(Long codigo) {
        this.codigo = codigo;
    }

    public ValorSeguro(Long codigo, String descripcion, BigDecimal valor, Date fechaInicio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<ContratoSeguro> getContratoSeguroList() {
        return contratoSeguroList;
    }

    public void setContratoSeguroList(List<ContratoSeguro> contratoSeguroList) {
        this.contratoSeguroList = contratoSeguroList;
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
        if (!(object instanceof ValorSeguro)) {
            return false;
        }
        ValorSeguro other = (ValorSeguro) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguros.ValorSeguro[ codigo=" + codigo + " ]";
    }
    
}
