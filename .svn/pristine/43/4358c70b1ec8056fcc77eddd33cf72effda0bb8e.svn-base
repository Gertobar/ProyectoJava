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
@Table(name = "MKS_SOCIOS.TIPO_RELACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRelacion.findAll", query = "SELECT t FROM TipoRelacion t"),
    @NamedQuery(name = "TipoRelacion.findByCodigo", query = "SELECT t FROM TipoRelacion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoRelacion.findByNombre", query = "SELECT t FROM TipoRelacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoRelacion.findByEliminado", query = "SELECT t FROM TipoRelacion t WHERE t.eliminado = :eliminado"),
    //PÉRSONALIZADOS
    @NamedQuery(name = "TipoRelacion.findByEsParaSolicitudSocioEliminado", query = "SELECT t FROM TipoRelacion t WHERE t.esParaSolicitudSocio = :esParaSolicitudSocio AND t.eliminado = :eliminado ORDER BY t.nombre"),
    @NamedQuery(name = "TipoRelacion.findByEsParaFirmantesEliminado", query = "SELECT t FROM TipoRelacion t WHERE t.esParaFirmantes = :esParaFirmantes AND t.eliminado = :eliminado ORDER BY t.nombre"),
    @NamedQuery(name = "TipoRelacion.findByEsParaSegurosEliminado", query = "SELECT t FROM TipoRelacion t WHERE t.esParaSeguros = :esParaSeguros AND t.eliminado = :eliminado ORDER BY t.nombre")
})
public class TipoRelacion implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEsParaSolicitudSocioEliminado = "TipoRelacion.findByEsParaSolicitudSocioEliminado";
    public static final String findByEsParaFirmantesEliminado = "TipoRelacion.findByEsParaFirmantesEliminado";
    public static final String findByEsParaSegurosEliminado = "TipoRelacion.findByEsParaSegurosEliminado";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_RELACION")
    @SequenceGenerator(name = "GSQ_TIPO_RELACION", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_TIPO_RELACION")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_SOLICITUD_SOCIO")
    private char esParaSolicitudSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_FIRMANTES")
    private char esParaFirmantes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PARA_SEGUROS")
    private char esParaSeguros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoRelacion", fetch = FetchType.LAZY)
    private Collection<PersonaTrabajoActEco> personaTrabajoActEcoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoRelacion", fetch = FetchType.LAZY)
    private Collection<SocioMenorEdadRep> socioMenorEdadRepCollection;

    public TipoRelacion() {
    }

    public TipoRelacion(Long codigo) {
        this.codigo = codigo;
    }

    public TipoRelacion(Long codigo, char eliminado) {
        this.codigo = codigo;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PersonaTrabajoActEco> getPersonaTrabajoActEcoCollection() {
        return personaTrabajoActEcoCollection;
    }

    public void setPersonaTrabajoActEcoCollection(Collection<PersonaTrabajoActEco> personaTrabajoActEcoCollection) {
        this.personaTrabajoActEcoCollection = personaTrabajoActEcoCollection;
    }

    @XmlTransient
    public Collection<SocioMenorEdadRep> getSocioMenorEdadRepCollection() {
        return socioMenorEdadRepCollection;
    }

    public void setSocioMenorEdadRepCollection(Collection<SocioMenorEdadRep> socioMenorEdadRepCollection) {
        this.socioMenorEdadRepCollection = socioMenorEdadRepCollection;
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
        if (!(object instanceof TipoRelacion)) {
            return false;
        }
        TipoRelacion other = (TipoRelacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.TipoRelacion[ codigo=" + codigo + " ]";
    }

    /**
     * @return the esParaSolicitudSocio
     */
    public char getEsParaSolicitudSocio() {
        return esParaSolicitudSocio;
    }

    /**
     * @param esParaSolicitudSocio the esParaSolicitudSocio to set
     */
    public void setEsParaSolicitudSocio(char esParaSolicitudSocio) {
        this.esParaSolicitudSocio = esParaSolicitudSocio;
    }

    /**
     * @return the esParaFirmantes
     */
    public char getEsParaFirmantes() {
        return esParaFirmantes;
    }

    /**
     * @param esParaFirmantes the esParaFirmantes to set
     */
    public void setEsParaFirmantes(char esParaFirmantes) {
        this.esParaFirmantes = esParaFirmantes;
    }

    /**
     * @return the esParaSeguros
     */
    public char getEsParaSeguros() {
        return esParaSeguros;
    }

    /**
     * @param esParaSeguros the esParaSeguros to set
     */
    public void setEsParaSeguros(char esParaSeguros) {
        this.esParaSeguros = esParaSeguros;
    }

}
