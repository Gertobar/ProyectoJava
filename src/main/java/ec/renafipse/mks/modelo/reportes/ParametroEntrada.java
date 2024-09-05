/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.reportes;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_REPORTES.PARAMETRO_ENTRADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroEntrada.findAll", query = "SELECT p FROM ParametroEntrada p"),
    @NamedQuery(name = "ParametroEntrada.findByCodigo", query = "SELECT p FROM ParametroEntrada p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroEntrada.findByNombre", query = "SELECT p FROM ParametroEntrada p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ParametroEntrada.findByElimiando", query = "SELECT p FROM ParametroEntrada p WHERE p.elimiando = :elimiando")})
public class ParametroEntrada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMIANDO")
    private char elimiando;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoParametro")
    private Collection<ParametroEntradaReporte> parametroEntradaReporteCollection;

    public ParametroEntrada() {
    }

    public ParametroEntrada(String codigo) {
        this.codigo = codigo;
    }

    public ParametroEntrada(String codigo, String nombre, char elimiando) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.elimiando = elimiando;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getElimiando() {
        return elimiando;
    }

    public void setElimiando(char elimiando) {
        this.elimiando = elimiando;
    }

    @XmlTransient
    public Collection<ParametroEntradaReporte> getParametroEntradaReporteCollection() {
        return parametroEntradaReporteCollection;
    }

    public void setParametroEntradaReporteCollection(Collection<ParametroEntradaReporte> parametroEntradaReporteCollection) {
        this.parametroEntradaReporteCollection = parametroEntradaReporteCollection;
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
        if (!(object instanceof ParametroEntrada)) {
            return false;
        }
        ParametroEntrada other = (ParametroEntrada) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.reportes.ParametroEntrada[ codigo=" + codigo + " ]";
    }
    
}
