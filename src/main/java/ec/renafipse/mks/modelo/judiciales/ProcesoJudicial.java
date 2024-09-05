/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.judiciales;

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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.PROCESO_JUDICIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoJudicial.findAll", query = "SELECT p FROM ProcesoJudicial p"),
    @NamedQuery(name = "ProcesoJudicial.findByCodigo", query = "SELECT p FROM ProcesoJudicial p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProcesoJudicial.findByDescripcion", query = "SELECT p FROM ProcesoJudicial p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProcesoJudicial.findByObservacion", query = "SELECT p FROM ProcesoJudicial p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "ProcesoJudicial.findByEliminado", query = "SELECT p FROM ProcesoJudicial p WHERE p.eliminado = :eliminado")})
public class ProcesoJudicial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PROCESO_JUDICIAL")
    @SequenceGenerator(name = "GSQ_PROCESO_JUDICIAL", schema = "MKS_JUDICIALES", allocationSize = 0, sequenceName = "SEQ_PROCESO_JUDICIAL")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoProcesoJudicial")
    private Collection<DetalleCostoJudicial> detalleCostoJudicialCollection;
    @OneToMany(mappedBy = "codigoProcesoJudicialDep")
    private Collection<ProcesoJudicialDependencia> procesoJudicialDependenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoProcesoJudicial")
    private Collection<ProcesoJudicialDependencia> procesoJudicialDependenciaCollection1;

    public ProcesoJudicial() {
    }

    public ProcesoJudicial(Long codigo) {
        this.codigo = codigo;
    }

    public ProcesoJudicial(Long codigo, String descripcion, String eliminado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<DetalleCostoJudicial> getDetalleCostoJudicialCollection() {
        return detalleCostoJudicialCollection;
    }

    public void setDetalleCostoJudicialCollection(Collection<DetalleCostoJudicial> detalleCostoJudicialCollection) {
        this.detalleCostoJudicialCollection = detalleCostoJudicialCollection;
    }

    @XmlTransient
    public Collection<ProcesoJudicialDependencia> getProcesoJudicialDependenciaCollection() {
        return procesoJudicialDependenciaCollection;
    }

    public void setProcesoJudicialDependenciaCollection(Collection<ProcesoJudicialDependencia> procesoJudicialDependenciaCollection) {
        this.procesoJudicialDependenciaCollection = procesoJudicialDependenciaCollection;
    }

    @XmlTransient
    public Collection<ProcesoJudicialDependencia> getProcesoJudicialDependenciaCollection1() {
        return procesoJudicialDependenciaCollection1;
    }

    public void setProcesoJudicialDependenciaCollection1(Collection<ProcesoJudicialDependencia> procesoJudicialDependenciaCollection1) {
        this.procesoJudicialDependenciaCollection1 = procesoJudicialDependenciaCollection1;
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
        if (!(object instanceof ProcesoJudicial)) {
            return false;
        }
        ProcesoJudicial other = (ProcesoJudicial) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.ProcesoJudicial[ codigo=" + codigo + " ]";
    }
    
}
