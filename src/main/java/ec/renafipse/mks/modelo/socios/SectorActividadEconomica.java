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
@Table(name = "MKS_SOCIOS.SECTOR_ACTIVIDAD_ECONOMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SectorActividadEconomica.findAll", query = "SELECT s FROM SectorActividadEconomica s"),
    @NamedQuery(name = "SectorActividadEconomica.findByCodigo", query = "SELECT s FROM SectorActividadEconomica s WHERE s.codigo = :codigo"),    
    @NamedQuery(name = "SectorActividadEconomica.findByNombre", query = "SELECT s FROM SectorActividadEconomica s WHERE s.nombre = :nombre"),    
    @NamedQuery(name = "SectorActividadEconomica.findByElminado", query = "SELECT s FROM SectorActividadEconomica s WHERE s.eliminado = :eliminado ORDER BY s.nombre")})
public class SectorActividadEconomica implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByElminado = "SectorActividadEconomica.findByElminado";
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSector")
    private Collection<SubsectorActividadEconomica> subsectorActividadEconomicaCollection;

    public SectorActividadEconomica() {
    }

    public SectorActividadEconomica(Long codigo) {
        this.codigo = codigo;
    }

    public SectorActividadEconomica(Long codigo, String nombre, char elminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.eliminado = elminado;
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

    @XmlTransient
    public Collection<SubsectorActividadEconomica> getSubsectorActividadEconomicaCollection() {
        return subsectorActividadEconomicaCollection;
    }

    public void setSubsectorActividadEconomicaCollection(Collection<SubsectorActividadEconomica> subsectorActividadEconomicaCollection) {
        this.subsectorActividadEconomicaCollection = subsectorActividadEconomicaCollection;
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
        if (!(object instanceof SectorActividadEconomica)) {
            return false;
        }
        SectorActividadEconomica other = (SectorActividadEconomica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SectorActividadEconomica[ codigo=" + codigo + " ]";
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
