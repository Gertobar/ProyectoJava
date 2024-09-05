/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.SUSTENTO_TRIBUTARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SustentoTributario.findAll", query = "SELECT s FROM SustentoTributario s"),
    @NamedQuery(name = "SustentoTributario.findByCodigo", query = "SELECT s FROM SustentoTributario s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SustentoTributario.findByNombre", query = "SELECT s FROM SustentoTributario s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SustentoTributario.findByCodigoOc", query = "SELECT s FROM SustentoTributario s WHERE s.codigoOc = :codigoOc"),
    @NamedQuery(name = "SustentoTributario.findByDescripcion", query = "SELECT s FROM SustentoTributario s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SustentoTributario.findByEliminado", query = "SELECT s FROM SustentoTributario s WHERE s.eliminado = :eliminado")})
public class SustentoTributario implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String  findByEliminado="SustentoTributario.findByEliminado";
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
    @Size(min = 1, max = 5)
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSustento", fetch = FetchType.LAZY)
    private Collection<Compra> compraCollection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_LIQUIDACION")
    private char esParaLiquidacion;

    public SustentoTributario() {
    }

    public SustentoTributario(Long codigo) {
        this.codigo = codigo;
    }

    public SustentoTributario(Long codigo, String nombre, String codigoOc, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigoOc = codigoOc;
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

    public String getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
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
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
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
        if (!(object instanceof SustentoTributario)) {
            return false;
        }
        SustentoTributario other = (SustentoTributario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.SustentoTributario[ codigo=" + codigo + " ]";
    }

    /**
     * @return the esParaLiquidacion
     */
    public char getEsParaLiquidacion() {
        return esParaLiquidacion;
    }

    /**
     * @param esParaLiquidacion the esParaLiquidacion to set
     */
    public void setEsParaLiquidacion(char esParaLiquidacion) {
        this.esParaLiquidacion = esParaLiquidacion;
    }
    
}
