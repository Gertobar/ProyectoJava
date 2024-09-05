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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.PROCESO_CONTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoContable.findAll", query = "SELECT p FROM ProcesoContable p"),
    @NamedQuery(name = "ProcesoContable.findByCodigo", query = "SELECT p FROM ProcesoContable p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProcesoContable.findByNombre", query = "SELECT p FROM ProcesoContable p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ProcesoContable.findByDescripcion", query = "SELECT p FROM ProcesoContable p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProcesoContable.findByEliminado", query = "SELECT p FROM ProcesoContable p WHERE p.eliminado = :eliminado"),
})
public class ProcesoContable implements Serializable {
    private static final long serialVersionUID = 1L;
  
    public static final String findByEliminado = "ProcesoContableConTraPro.findByEliminado";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PROCESO_CONTABLE")
    @SequenceGenerator(name = "GSQ_PROCESO_CONTABLE", schema = "MKS_CONTABLES",  allocationSize = 0, sequenceName = "SEQ_PROCESO_CONTABLE")        
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
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO_COM")
    private Long codigoEstadoCom;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesoContable", fetch = FetchType.LAZY)
    private Collection<ProcesoContableConTraPro> procesoContableConTraProCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesoContable", fetch = FetchType.LAZY)
    private Collection<ProcesoContableIngEgrMon> procesoContableIngEgrMonCollection;
    @JoinColumn(name = "CODIGO_ESTADO_COM", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EstadoComprobante estadoComprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesoContable", fetch = FetchType.LAZY)
    private Collection<ProcesoContableDetalle> procesoContableDetalleCollection;

    public ProcesoContable() {
    }

    public ProcesoContable(Long codigo) {
        this.codigo = codigo;
    }

    public ProcesoContable(Long codigo, String nombre, String descripcion, char eliminado) {
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
    public Collection<ProcesoContableConTraPro> getProcesoContableConTraProCollection() {
        return procesoContableConTraProCollection;
    }

    public void setProcesoContableConTraProCollection(Collection<ProcesoContableConTraPro> procesoContableConTraProCollection) {
        this.procesoContableConTraProCollection = procesoContableConTraProCollection;
    }

    @XmlTransient
    public Collection<ProcesoContableIngEgrMon> getProcesoContableIngEgrMonCollection() {
        return procesoContableIngEgrMonCollection;
    }

    public void setProcesoContableIngEgrMonCollection(Collection<ProcesoContableIngEgrMon> procesoContableIngEgrMonCollection) {
        this.procesoContableIngEgrMonCollection = procesoContableIngEgrMonCollection;
    }

   
    @XmlTransient
    public Collection<ProcesoContableDetalle> getProcesoContableDetalleCollection() {
        return procesoContableDetalleCollection;
    }

    public void setProcesoContableDetalleCollection(Collection<ProcesoContableDetalle> procesoContableDetalleCollection) {
        this.procesoContableDetalleCollection = procesoContableDetalleCollection;
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
        if (!(object instanceof ProcesoContable)) {
            return false;
        }
        ProcesoContable other = (ProcesoContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ProcesoContable[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoEstadoCom
     */
    public Long getCodigoEstadoCom() {
        return codigoEstadoCom;
    }

    /**
     * @param codigoEstadoCom the codigoEstadoCom to set
     */
    public void setCodigoEstadoCom(Long codigoEstadoCom) {
        this.codigoEstadoCom = codigoEstadoCom;
    }

    /**
     * @return the estadoComprobante
     */
    public EstadoComprobante getEstadoComprobante() {
        return estadoComprobante;
    }

    /**
     * @param estadoComprobante the estadoComprobante to set
     */
    public void setEstadoComprobante(EstadoComprobante estadoComprobante) {
        this.estadoComprobante = estadoComprobante;
    }
    
}
