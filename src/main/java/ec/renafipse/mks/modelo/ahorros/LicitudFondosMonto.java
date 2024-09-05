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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MKS_AHORROS.LICITUD_FONDOS_MONTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicitudFondosMonto.findAll", query = "SELECT l FROM LicitudFondosMonto l"),
    @NamedQuery(name = "LicitudFondosMonto.findByCodigo", query = "SELECT l FROM LicitudFondosMonto l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LicitudFondosMonto.findByDescripcion", query = "SELECT l FROM LicitudFondosMonto l WHERE l.descripcion = :descripcion"),
    @NamedQuery(name = "LicitudFondosMonto.findByValor", query = "SELECT l FROM LicitudFondosMonto l WHERE l.valor = :valor"),
    @NamedQuery(name = "LicitudFondosMonto.findByEliminado", query = "SELECT l FROM LicitudFondosMonto l WHERE l.eliminado = :eliminado"),
    @NamedQuery(name = "LicitudFondosMonto.findByFechaInicial", query = "SELECT l FROM LicitudFondosMonto l WHERE l.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "LicitudFondosMonto.findByFechaFinal", query = "SELECT l FROM LicitudFondosMonto l WHERE l.fechaFinal = :fechaFinal")})
public class LicitudFondosMonto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;

    public LicitudFondosMonto() {
    }

    public LicitudFondosMonto(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public LicitudFondosMonto(BigDecimal codigo, String descripcion, BigDecimal valor, char eliminado, Date fechaInicial, Date fechaFinal) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.eliminado = eliminado;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
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
        if (!(object instanceof LicitudFondosMonto)) {
            return false;
        }
        LicitudFondosMonto other = (LicitudFondosMonto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorrosl.LicitudFondosMonto[ codigo=" + codigo + " ]";
    }
    
}
