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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.ACTIVIDAD_ECONOMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadEconomica.findAll", query = "SELECT a FROM ActividadEconomica a"),
    @NamedQuery(name = "ActividadEconomica.findByCodigo", query = "SELECT a FROM ActividadEconomica a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "ActividadEconomica.findByCodigoOc", query = "SELECT a FROM ActividadEconomica a WHERE a.codigoOc = :codigoOc"),
    @NamedQuery(name = "ActividadEconomica.findByNombre", query = "SELECT a FROM ActividadEconomica a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "ActividadEconomica.findByEliminado", query = "SELECT a FROM ActividadEconomica a WHERE a.eliminado = :eliminado"),
    //-----------------------------------------------------------------------------
    //-- PERSONALIZADOS
    //@NamedQuery(name = "ActividadEconomica.findBySectorSubsectorNivelEliminado", query = "SELECT a FROM ActividadEconomica a JOIN a.codigoSubsector s JOIN s.codigoSector c WHERE a.codigoSubsector.codigo = :codigoSubsector AND a.codigoNivel.codigo = :codigoNivel AND a.eliminado = :eliminado AND s.eliminado = :eliminado AND c.eliminado = :eliminado ORDER BY a.nombre"),
    @NamedQuery(name = "ActividadEconomica.findByNivelEliminado", query = "SELECT a FROM ActividadEconomica a WHERE a.codigoNivel.codigo = :nivel AND a.catalogo = :catalogo AND a.eliminado = :eliminado"),
    @NamedQuery(name = "ActividadEconomica.findByNivelPadreEliminado", query = "SELECT a FROM ActividadEconomica a WHERE a.codigoNivel.codigo = :nivel AND a.eliminado = :eliminado AND a.codigoActEcoPad.codigo = :codigoPadre AND a.catalogo = :catalogo")
})
public class ActividadEconomica implements Serializable {
    private static final long serialVersionUID = 1L;
    //public static final String findBySectorSubsectorNivelEliminado = "ActividadEconomica.findBySectorSubsectorNivelEliminado";
    public static final String findByNivelEliminado = "ActividadEconomica.findByNivelEliminado";
    public static final String findByNivelPadreEliminado = "ActividadEconomica.findByNivelPadreEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_ACTIVIDAD_ECONOMICA")
    @SequenceGenerator(name = "GSQ_ACTIVIDAD_ECONOMICA", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_ACTIVIDAD_ECONOMICA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO_OC")
    private String codigoOc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private Character eliminado;
    @Column(name = "CATALOGO")
    private char catalogo;
    @JoinColumn(name = "CODIGO_NIVEL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Nivel codigoNivel;
    @OneToMany(mappedBy = "codigoActEcoPad", fetch = FetchType.LAZY)
    private Collection<ActividadEconomica> actividadEconomicaCollection;
    @JoinColumn(name = "CODIGO_ACT_ECO_PAD", referencedColumnName = "CODIGO")
    @ManyToOne
    private ActividadEconomica codigoActEcoPad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividadEconomica", fetch = FetchType.LAZY)
    private Collection<PersonaActividadEconomica> personaActividadEconomicaCollection;
//    @JoinColumn(name = "CODIGO_SUBSECTOR", referencedColumnName = "CODIGO")
//    @ManyToOne(optional = false)
//    private SubsectorActividadEconomica codigoSubsector;

    public ActividadEconomica() {
    }

    public ActividadEconomica(Long codigo) {
        this.codigo = codigo;
    }

    public ActividadEconomica(Long codigo, String codigoOc, String nombre, char eliminado) {
        this.codigo = codigo;
        this.codigoOc = codigoOc;
        this.nombre = nombre;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodigoOc() {
        return codigoOc;
    }

    public void setCodigoOc(String codigoOc) {
        this.codigoOc = codigoOc;
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

    public Nivel getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Nivel codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    @XmlTransient
    public Collection<ActividadEconomica> getActividadEconomicaCollection() {
        return actividadEconomicaCollection;
    }

    public void setActividadEconomicaCollection(Collection<ActividadEconomica> actividadEconomicaCollection) {
        this.actividadEconomicaCollection = actividadEconomicaCollection;
    }

    public ActividadEconomica getCodigoActEcoPad() {
        return codigoActEcoPad;
    }

    public void setCodigoActEcoPad(ActividadEconomica codigoActEcoPad) {
        this.codigoActEcoPad = codigoActEcoPad;
    }

    @XmlTransient
    public Collection<PersonaActividadEconomica> getPersonaActividadEconomicaCollection() {
        return personaActividadEconomicaCollection;
    }

    public void setPersonaActividadEconomicaCollection(Collection<PersonaActividadEconomica> personaActividadEconomicaCollection) {
        this.personaActividadEconomicaCollection = personaActividadEconomicaCollection;
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
        if (!(object instanceof ActividadEconomica)) {
            return false;
        }
        ActividadEconomica other = (ActividadEconomica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ActividadEconomica[ codigo=" + codigo + " ]";
    }

//    /**
//     * @return the codigoSubsector
//     */
//    public SubsectorActividadEconomica getCodigoSubsector() {
//        return codigoSubsector;
//    }
//
//    /**
//     * @param codigoSubsector the codigoSubsector to set
//     */
//    public void setCodigoSubsector(SubsectorActividadEconomica codigoSubsector) {
//        this.codigoSubsector = codigoSubsector;
//    }

    /**
     * @return the catalogo
     */
    public Character getCatalogo() {
        return catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(Character catalogo) {
        this.catalogo = catalogo;
    }
    
}
