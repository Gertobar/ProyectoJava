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
@Table(name = "MKS_CAJAS.INGRESO_EGRESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoEgreso.findAll", query = "SELECT i FROM IngresoEgreso i"),
    @NamedQuery(name = "IngresoEgreso.findByCodigo", query = "SELECT i FROM IngresoEgreso i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "IngresoEgreso.findByNombre", query = "SELECT i FROM IngresoEgreso i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IngresoEgreso.findBySiglas", query = "SELECT i FROM IngresoEgreso i WHERE i.siglas = :siglas"),
    @NamedQuery(name = "IngresoEgreso.findByDescripcion", query = "SELECT i FROM IngresoEgreso i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IngresoEgreso.findByAfectaCaja", query = "SELECT i FROM IngresoEgreso i WHERE i.afectaCaja = :afectaCaja"),
    @NamedQuery(name = "IngresoEgreso.findByContabiliza", query = "SELECT i FROM IngresoEgreso i WHERE i.contabiliza = :contabiliza"),
    @NamedQuery(name = "IngresoEgreso.findByEliminado", query = "SELECT i FROM IngresoEgreso i WHERE i.eliminado = :eliminado")
    
})
public class IngresoEgreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_INGRESO_EGRESO")
    @SequenceGenerator(name = "GSQ_INGRESO_EGRESO", schema = "MKS_CAJAS",  allocationSize = 0, sequenceName = "SEQ_INGRESO_EGRESO")
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
    @Size(min = 1, max = 10)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AFECTA_CAJA")
    private char afectaCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTABILIZA")
    private char contabiliza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_INGRESO")
    private char esIngreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingresoEgreso", fetch = FetchType.LAZY)
    private Collection<IngresoEgresoMoneda> ingresoEgresoMonedaCollection;

    public IngresoEgreso() {
    }

    public IngresoEgreso(Long codigo) {
        this.codigo = codigo;
    }

    public IngresoEgreso(Long codigo, String nombre, String siglas, String descripcion, char afectaCaja, char contabiliza, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.descripcion = descripcion;
        this.afectaCaja = afectaCaja;
        this.contabiliza = contabiliza;
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getAfectaCaja() {
        return afectaCaja;
    }

    public void setAfectaCaja(char afectaCaja) {
        this.afectaCaja = afectaCaja;
    }

    public char getContabiliza() {
        return contabiliza;
    }

    public void setContabiliza(char contabiliza) {
        this.contabiliza = contabiliza;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<IngresoEgresoMoneda> getIngresoEgresoMonedaCollection() {
        return ingresoEgresoMonedaCollection;
    }

    public void setIngresoEgresoMonedaCollection(Collection<IngresoEgresoMoneda> ingresoEgresoMonedaCollection) {
        this.ingresoEgresoMonedaCollection = ingresoEgresoMonedaCollection;
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
        if (!(object instanceof IngresoEgreso)) {
            return false;
        }
        IngresoEgreso other = (IngresoEgreso) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.IngresoEgreso[ codigo=" + codigo + " ]";
    }

    /**
     * @return the esIngreso
     */
    public char getEsIngreso() {
        return esIngreso;
    }

    /**
     * @param esIngreso the esIngreso to set
     */
    public void setEsIngreso(char esIngreso) {
        this.esIngreso = esIngreso;
    }
    
}
