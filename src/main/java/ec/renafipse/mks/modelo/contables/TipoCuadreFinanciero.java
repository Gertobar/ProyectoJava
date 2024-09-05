/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

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
 * @author renafipse2
 */
@Entity
@Table(name = "MKS_CONTABLES.TIPO_CUADRE_FINANCIERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCuadreFinanciero.findAll", query = "SELECT t FROM TipoCuadreFinanciero t"),
    @NamedQuery(name = "TipoCuadreFinanciero.findByCodigo", query = "SELECT t FROM TipoCuadreFinanciero t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoCuadreFinanciero.findByNombre", query = "SELECT t FROM TipoCuadreFinanciero t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoCuadreFinanciero.findByDescripcion", query = "SELECT t FROM TipoCuadreFinanciero t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoCuadreFinanciero.findByEliminado", query = "SELECT t FROM TipoCuadreFinanciero t WHERE t.eliminado = :eliminado")})
public class TipoCuadreFinanciero implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoCuadre")
    private Collection<CuentasCuadreFinanciero> cuentasCuadreFinancieroCollection;

    public TipoCuadreFinanciero() {
    }

    public TipoCuadreFinanciero(Long codigo) {
        this.codigo = codigo;
    }

    public TipoCuadreFinanciero(Long codigo, String nombre, String descripcion, char eliminado) {
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<CuentasCuadreFinanciero> getCuentasCuadreFinancieroCollection() {
        return cuentasCuadreFinancieroCollection;
    }

    public void setCuentasCuadreFinancieroCollection(Collection<CuentasCuadreFinanciero> cuentasCuadreFinancieroCollection) {
        this.cuentasCuadreFinancieroCollection = cuentasCuadreFinancieroCollection;
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
        if (!(object instanceof TipoCuadreFinanciero)) {
            return false;
        }
        TipoCuadreFinanciero other = (TipoCuadreFinanciero) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.TipoCuadreFinanciero[ codigo=" + codigo + " ]";
    }
    
}
