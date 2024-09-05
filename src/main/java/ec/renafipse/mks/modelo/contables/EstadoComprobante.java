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
@Table(name = "MKS_CONTABLES.ESTADO_COMPROBANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoComprobante.findAll", query = "SELECT e FROM EstadoComprobante e"),
    @NamedQuery(name = "EstadoComprobante.findByCodigo", query = "SELECT e FROM EstadoComprobante e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EstadoComprobante.findByNombre", query = "SELECT e FROM EstadoComprobante e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoComprobante.findBySiglas", query = "SELECT e FROM EstadoComprobante e WHERE e.siglas = :siglas"),
    @NamedQuery(name = "EstadoComprobante.findByEliminado", query = "SELECT e FROM EstadoComprobante e WHERE e.eliminado = :eliminado")})
public class EstadoComprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado="EstadoComprobante.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ESTADO_COMPROBANTE")
    @SequenceGenerator(name = "GSQ_ESTADO_COMPROBANTE", schema = "MKS_CONTABLES",  allocationSize = 0, sequenceName = "SEQ_ESTADO_COMPROBANTE")        
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGLAS")
    private char siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEstado", fetch = FetchType.LAZY)
    private Collection<SeguimientoEstadoComprobante> seguimientoEstadoComprobanteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEstadoCom", fetch = FetchType.LAZY)
    private Collection<ProcesoContable> procesoContableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEstado", fetch = FetchType.LAZY)
    private Collection<Comprobante> comprobanteCollection;

    public EstadoComprobante() {
    }

    public EstadoComprobante(Long codigo) {
        this.codigo = codigo;
    }

    public EstadoComprobante(Long codigo, String nombre, char siglas, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
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

    public char getSiglas() {
        return siglas;
    }

    public void setSiglas(char siglas) {
        this.siglas = siglas;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<SeguimientoEstadoComprobante> getSeguimientoEstadoComprobanteCollection() {
        return seguimientoEstadoComprobanteCollection;
    }

    public void setSeguimientoEstadoComprobanteCollection(Collection<SeguimientoEstadoComprobante> seguimientoEstadoComprobanteCollection) {
        this.seguimientoEstadoComprobanteCollection = seguimientoEstadoComprobanteCollection;
    }

    @XmlTransient
    public Collection<ProcesoContable> getProcesoContableCollection() {
        return procesoContableCollection;
    }

    public void setProcesoContableCollection(Collection<ProcesoContable> procesoContableCollection) {
        this.procesoContableCollection = procesoContableCollection;
    }

    @XmlTransient
    public Collection<Comprobante> getComprobanteCollection() {
        return comprobanteCollection;
    }

    public void setComprobanteCollection(Collection<Comprobante> comprobanteCollection) {
        this.comprobanteCollection = comprobanteCollection;
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
        if (!(object instanceof EstadoComprobante)) {
            return false;
        }
        EstadoComprobante other = (EstadoComprobante) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.EstadoComprobante[ codigo=" + codigo + " ]";
    }
    
}
