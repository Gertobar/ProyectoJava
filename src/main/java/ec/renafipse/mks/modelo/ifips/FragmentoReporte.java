/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author santiago
 */
@Entity
@Table(name = "MKS_IFIPS.FRAGMENTO_REPORTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FragmentoReporte.findAll", query = "SELECT f FROM FragmentoReporte f"),
    @NamedQuery(name = "FragmentoReporte.findByCodigo", query = "SELECT f FROM FragmentoReporte f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "FragmentoReporte.findByNombre", query = "SELECT f FROM FragmentoReporte f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FragmentoReporte.findByDescripcion", query = "SELECT f FROM FragmentoReporte f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "FragmentoReporte.findByEliminado", query = "SELECT f FROM FragmentoReporte f WHERE f.eliminado = :eliminado")})
public class FragmentoReporte implements Serializable {
    
     public static final String findByEliminado="FragmentoReporte.findByEliminado";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fragmentoReporte")
    private Collection<ReporteFragmentoIfip> reporteFragmentoIfipCollection;
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "CODIGO")
//    private Long codigo;
    
//    
     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_FRAGMENTO_REPORTE")
    @SequenceGenerator(name = "GSQ_FRAGMENTO_REPORTE", schema = "MKS_IFIPS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_FRAGMENTO_REPORTE")
    @Column(name = "CODIGO")
    private Long codigo;
    //
    
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

    public FragmentoReporte() {
    }

    public FragmentoReporte(Long codigo) {
        this.codigo = codigo;
    }

    public FragmentoReporte(Long codigo, String nombre, String descripcion, char eliminado) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FragmentoReporte)) {
            return false;
        }
        FragmentoReporte other = (FragmentoReporte) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.FragmentoReporte[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<ReporteFragmentoIfip> getReporteFragmentoIfipCollection() {
        return reporteFragmentoIfipCollection;
    }

    public void setReporteFragmentoIfipCollection(Collection<ReporteFragmentoIfip> reporteFragmentoIfipCollection) {
        this.reporteFragmentoIfipCollection = reporteFragmentoIfipCollection;
    }
    
}
