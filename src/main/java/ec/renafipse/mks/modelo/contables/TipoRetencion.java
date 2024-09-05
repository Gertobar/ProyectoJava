/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
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
@Table(name = "MKS_CONTABLES.TIPO_RETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRetencion.findAll", query = "SELECT t FROM TipoRetencion t"),
    @NamedQuery(name = "TipoRetencion.findByCodigo", query = "SELECT t FROM TipoRetencion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoRetencion.findByNombre", query = "SELECT t FROM TipoRetencion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoRetencion.findByPorcentaje", query = "SELECT t FROM TipoRetencion t WHERE t.porcentaje = :porcentaje"),
    @NamedQuery(name = "TipoRetencion.findByEliminado", query = "SELECT t FROM TipoRetencion t WHERE t.eliminado = :eliminado ORDER BY t.porcentaje")})
public class TipoRetencion implements Serializable {
    private static final long serialVersionUID = 1L;
     public static final String findByAll = "TipoRetencion.findAll";
     public static final String findByEliminado = "TipoRetencion.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALCULA_SOBRE")
    private char calculaSobre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    

    public TipoRetencion() {
    }

    public TipoRetencion(Long codigo) {
        this.codigo = codigo;
    }

    public TipoRetencion(Long codigo, String nombre, BigDecimal porcentaje, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
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

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
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
        if (!(object instanceof TipoRetencion)) {
            return false;
        }
        TipoRetencion other = (TipoRetencion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.contables.TipoRetencion[ codigo=" + codigo + " ]";
    }

    /**
     * @return the calculaSobre
     */
    public char getCalculaSobre() {
        return calculaSobre;
    }

    /**
     * @param calculaSobre the calculaSobre to set
     */
    public void setCalculaSobre(char calculaSobre) {
        this.calculaSobre = calculaSobre;
    }

    /**
     * @return the codigoOc
     */
    public String getCodigoOc() {
        return codigoOc;
    }

    /**
     * @param codigoOc the codigoOc to set
     */
    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
    }
    
}
