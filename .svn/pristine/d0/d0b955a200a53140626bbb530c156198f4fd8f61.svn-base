/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

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
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_CAJAS.PARAMETRO_GENERAL_CAJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroGeneralCaja.findAll", query = "SELECT p FROM ParametroGeneralCaja p"),
    @NamedQuery(name = "ParametroGeneralCaja.findByCodigo", query = "SELECT p FROM ParametroGeneralCaja p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroGeneralCaja.findByNombre", query = "SELECT p FROM ParametroGeneralCaja p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ParametroGeneralCaja.findByDescripcion", query = "SELECT p FROM ParametroGeneralCaja p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ParametroGeneralCaja.findByValorTexto", query = "SELECT p FROM ParametroGeneralCaja p WHERE p.valorTexto = :valorTexto"),
    @NamedQuery(name = "ParametroGeneralCaja.findByValorNumerico", query = "SELECT p FROM ParametroGeneralCaja p WHERE p.valorNumerico = :valorNumerico"),
    @NamedQuery(name = "ParametroGeneralCaja.findByValorFecha", query = "SELECT p FROM ParametroGeneralCaja p WHERE p.valorFecha = :valorFecha"),
    @NamedQuery(name = "ParametroGeneralCaja.findByEliminado", query = "SELECT p FROM ParametroGeneralCaja p WHERE p.eliminado = :eliminado")})
public class ParametroGeneralCaja implements Serializable {
    public static final String findByCodigo = "ParametroGeneralCaja.findByCodigo";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
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

    public ParametroGeneralCaja() {
    }

    public ParametroGeneralCaja(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroGeneralCaja(Long codigo, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroGeneralCaja)) {
            return false;
        }
        ParametroGeneralCaja other = (ParametroGeneralCaja) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.ParametroGeneralCaja[ codigo=" + codigo + " ]";
    }
    
}
