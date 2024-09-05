/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_CAJAS.ESTADO_CHEQUE_DEPOSITADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoChequeDepositado.findAll", query = "SELECT e FROM EstadoChequeDepositado e"),
    @NamedQuery(name = "EstadoChequeDepositado.findByCodigo", query = "SELECT e FROM EstadoChequeDepositado e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EstadoChequeDepositado.findByNombre", query = "SELECT e FROM EstadoChequeDepositado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoChequeDepositado.findByDescripcion", query = "SELECT e FROM EstadoChequeDepositado e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EstadoChequeDepositado.findByEliminado", query = "SELECT e FROM EstadoChequeDepositado e WHERE e.eliminado = :eliminado")})
public class EstadoChequeDepositado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ESTADO_CHEQUE_DEP")
    @SequenceGenerator(name = "GSQ_ESTADO_CHEQUE_DEP", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_ESTADO_CHEQUE_DEP")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEstado", fetch = FetchType.LAZY)
    private Collection<ChequeDepositado> chequeDepositadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoUsuarioPosee", fetch = FetchType.LAZY)
    private Collection<SeguimientoChequeDep> seguimientoChequeDepCollection;

    public EstadoChequeDepositado() {
    }

    public EstadoChequeDepositado(Long codigo) {
        this.codigo = codigo;
    }

    public EstadoChequeDepositado(Long codigo, String nombre, String descripcion, char eliminado) {
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
    public Collection<ChequeDepositado> getChequeDepositadoCollection() {
        return chequeDepositadoCollection;
    }

    public void setChequeDepositadoCollection(Collection<ChequeDepositado> chequeDepositadoCollection) {
        this.chequeDepositadoCollection = chequeDepositadoCollection;
    }

    @XmlTransient
    public Collection<SeguimientoChequeDep> getSeguimientoChequeDepCollection() {
        return seguimientoChequeDepCollection;
    }

    public void setSeguimientoChequeDepCollection(Collection<SeguimientoChequeDep> seguimientoChequeDepCollection) {
        this.seguimientoChequeDepCollection = seguimientoChequeDepCollection;
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
        if (!(object instanceof EstadoChequeDepositado)) {
            return false;
        }
        EstadoChequeDepositado other = (EstadoChequeDepositado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.EstadoChequeDepositado[ codigo=" + codigo + " ]";
    }
    
}
