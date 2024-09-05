/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_RESIDENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaResidencia.findAll", query = "SELECT p FROM PersonaResidencia p"),
    @NamedQuery(name = "PersonaResidencia.findByCodigoPersona", query = "SELECT p FROM PersonaResidencia p WHERE p.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "PersonaResidencia.findByCodigoUbiGeoRes", query = "SELECT p FROM PersonaResidencia p WHERE p.codigoUbiGeoRes = :codigoUbiGeoRes"),
    @NamedQuery(name = "PersonaResidencia.findByCodigoPeriodicidad", query = "SELECT p FROM PersonaResidencia p WHERE p.codigoPeriodicidad = :codigoPeriodicidad"),
    @NamedQuery(name = "PersonaResidencia.findByBarrio", query = "SELECT p FROM PersonaResidencia p WHERE p.barrio = :barrio"),
    @NamedQuery(name = "PersonaResidencia.findByDireccion", query = "SELECT p FROM PersonaResidencia p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PersonaResidencia.findByReferencia", query = "SELECT p FROM PersonaResidencia p WHERE p.referencia = :referencia"),
    @NamedQuery(name = "PersonaResidencia.findByTiempo", query = "SELECT p FROM PersonaResidencia p WHERE p.tiempo = :tiempo")})
public class PersonaResidencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "BARRIO")
    private String barrio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO")
    private int tiempo;
    
    
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "CODIGO_TIPO_VIVIENDA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoVivienda codigoTipoVivienda;
    @JoinColumn(name = "CODIGO_SECTOR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Sector codigoSector;
    @JoinColumn(name = "CODIGO_UBI_GEO_RES", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private UbicacionGeografica codigoUbiGeoRes;
    @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Periodicidad codigoPeriodicidad;
    
    public PersonaResidencia() {
    }

    public PersonaResidencia(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public PersonaResidencia(Long codigoPersona, UbicacionGeografica codigoUbiGeoRes, Periodicidad codigoPeriodicidad, String barrio, String direccion, String referencia, int tiempo) {
        this.codigoPersona = codigoPersona;
        this.codigoUbiGeoRes = codigoUbiGeoRes;
        this.codigoPeriodicidad = codigoPeriodicidad;
        this.barrio = barrio;
        this.direccion = direccion;
        this.referencia = referencia;
        this.tiempo = tiempo;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public UbicacionGeografica getCodigoUbiGeoRes() {
        return codigoUbiGeoRes;
    }

    public void setCodigoUbiGeoRes(UbicacionGeografica codigoUbiGeoRes) {
        this.codigoUbiGeoRes = codigoUbiGeoRes;
    }

    public Periodicidad getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(Periodicidad codigoPeriodicidad) {
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public TipoVivienda getCodigoTipoVivienda() {
        return codigoTipoVivienda;
    }

    public void setCodigoTipoVivienda(TipoVivienda codigoTipoVivienda) {
        this.codigoTipoVivienda = codigoTipoVivienda;
    }

    public Sector getCodigoSector() {
        return codigoSector;
    }

    public void setCodigoSector(Sector codigoSector) {
        this.codigoSector = codigoSector;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPersona != null ? codigoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaResidencia)) {
            return false;
        }
        PersonaResidencia other = (PersonaResidencia) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaResidencia[ codigoPersona=" + codigoPersona + " ]";
    }
    
}
