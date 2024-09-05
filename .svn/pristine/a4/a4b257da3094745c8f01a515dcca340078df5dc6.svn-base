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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_REPORTES.REPORTE_GRUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteGrupo.findAll", query = "SELECT r FROM ReporteGrupo r"),
    @NamedQuery(name = "ReporteGrupo.findByCodigo", query = "SELECT r FROM ReporteGrupo r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "ReporteGrupo.findByNombre", query = "SELECT r FROM ReporteGrupo r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "ReporteGrupo.findByDescripcion", query = "SELECT r FROM ReporteGrupo r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "ReporteGrupo.findByEliminado", query = "SELECT r FROM ReporteGrupo r WHERE r.eliminado = :eliminado")})
public class ReporteGrupo implements Serializable {
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
    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoGrupo", fetch = FetchType.LAZY)
    private Collection<ReporteDetalle> reporteDetalleCollection;

    public ReporteGrupo() {
    }

    public ReporteGrupo(Long codigo) {
        this.codigo = codigo;
    }

    public ReporteGrupo(Long codigo, String nombre, String descripcion, char eliminado) {
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
    public Collection<ReporteDetalle> getReporteDetalleCollection() {
        return reporteDetalleCollection;
    }

    public void setReporteDetalleCollection(Collection<ReporteDetalle> reporteDetalleCollection) {
        this.reporteDetalleCollection = reporteDetalleCollection;
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
        if (!(object instanceof ReporteGrupo)) {
            return false;
        }
        ReporteGrupo other = (ReporteGrupo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.reportes.ReporteGrupo[ codigo=" + codigo + " ]";
    }
    
}
