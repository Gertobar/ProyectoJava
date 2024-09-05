/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "MKS_CREDITOS.ESTADO_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCredito.findAll", query = "SELECT e FROM EstadoCredito e"),
    @NamedQuery(name = "EstadoCredito.findByCodigo", query = "SELECT e FROM EstadoCredito e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EstadoCredito.findByNombre", query = "SELECT e FROM EstadoCredito e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoCredito.findByDescripcion", query = "SELECT e FROM EstadoCredito e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EstadoCredito.findByEliminado", query = "SELECT e FROM EstadoCredito e WHERE e.eliminado = :eliminado")})
public class EstadoCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "EstadoCredito.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ESTADO_CREDITO")
    @SequenceGenerator(name = "GSQ_ESTADO_CREDITO", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_ESTADO_CREDITO")
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
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(mappedBy = "codigoEstadoDependiente", fetch = FetchType.LAZY)
    private Collection<EstadoCredito> estadoCreditoCollection;
    @JoinColumn(name = "CODIGO_ESTADO_DEPENDIENTE", referencedColumnName = "CODIGO")
    @ManyToOne
    private EstadoCredito codigoEstadoDependiente;

    public EstadoCredito() {
    }

    public EstadoCredito(Long codigo) {
        this.codigo = codigo;
    }

    public EstadoCredito(Long codigo, String nombre, String descripcion, char eliminado) {
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
    public Collection<EstadoCredito> getEstadoCreditoCollection() {
        return estadoCreditoCollection;
    }

    public void setEstadoCreditoCollection(Collection<EstadoCredito> estadoCreditoCollection) {
        this.estadoCreditoCollection = estadoCreditoCollection;
    }

    public EstadoCredito getCodigoEstadoDependiente() {
        return codigoEstadoDependiente;
    }

    public void setCodigoEstadoDependiente(EstadoCredito codigoEstadoDependiente) {
        this.codigoEstadoDependiente = codigoEstadoDependiente;
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
        if (!(object instanceof EstadoCredito)) {
            return false;
        }
        EstadoCredito other = (EstadoCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.EstadoCredito[ codigo=" + codigo + " ]";
    }
    
}
