/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_SOCIOS.SUBSECTOR_ACTIVIDAD_ECONOMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubsectorActividadEconomica.findAll", query = "SELECT s FROM SubsectorActividadEconomica s"),
    @NamedQuery(name = "SubsectorActividadEconomica.findByCodigo", query = "SELECT s FROM SubsectorActividadEconomica s WHERE s.codigo = :codigo"),    
    @NamedQuery(name = "SubsectorActividadEconomica.findByNombre", query = "SELECT s FROM SubsectorActividadEconomica s WHERE s.nombre = :nombre"),    
    @NamedQuery(name = "SubsectorActividadEconomica.findByElminado", query = "SELECT s FROM SubsectorActividadEconomica s WHERE s.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "SubsectorActividadEconomica.findByCodigoSectorElminado", query = "SELECT s FROM SubsectorActividadEconomica s JOIN s.codigoSector  c WHERE c.codigo = :codigoSector AND c.eliminado = :eliminado AND  s.eliminado = :eliminado ORDER BY s.nombre")
            })
public class SubsectorActividadEconomica implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String  findByCodigoSectorElminado = "SubsectorActividadEconomica.findByCodigoSectorElminado";
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_SECTOR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private SectorActividadEconomica codigoSector;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSubsector")
//    private Collection<ActividadEconomica> actividadEconomicaCollection;

    public SubsectorActividadEconomica() {
    }

    public SubsectorActividadEconomica(Long codigo) {
        this.codigo = codigo;
    }

    public SubsectorActividadEconomica(Long codigo, String nombre, char eliminado) {
        this.codigo = codigo;        
        this.nombre = nombre;        
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

     public SectorActividadEconomica getCodigoSector() {
        return codigoSector;
    }

    public void setCodigoSector(SectorActividadEconomica codigoSector) {
        this.codigoSector = codigoSector;
    }

//    @XmlTransient
//    public Collection<ActividadEconomica> getActividadEconomicaCollection() {
//        return actividadEconomicaCollection;
//    }
//
//    public void setActividadEconomicaCollection(Collection<ActividadEconomica> actividadEconomicaCollection) {
//        this.actividadEconomicaCollection = actividadEconomicaCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubsectorActividadEconomica)) {
            return false;
        }
        SubsectorActividadEconomica other = (SubsectorActividadEconomica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SubsectorActividadEconomica[ codigo=" + codigo + " ]";
    }

    /**
     * @return the eliminado
     */
    public char getEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }
    
}
