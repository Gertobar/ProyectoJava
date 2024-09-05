/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_NATURAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNatural.findAll", query = "SELECT p FROM PersonaNatural p"),
    @NamedQuery(name = "PersonaNatural.findByCodigoPersona", query = "SELECT p FROM PersonaNatural p WHERE p.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "PersonaNatural.findByCodigoUbiGeoNac", query = "SELECT p FROM PersonaNatural p WHERE p.codigoUbiGeoNac = :codigoUbiGeoNac"),
    @NamedQuery(name = "PersonaNatural.findByNombres", query = "SELECT p FROM PersonaNatural p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "PersonaNatural.findByPrimerApellido", query = "SELECT p FROM PersonaNatural p WHERE p.primerApellido = :primerApellido"),
    @NamedQuery(name = "PersonaNatural.findBySegundoApellido", query = "SELECT p FROM PersonaNatural p WHERE p.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "PersonaNatural.findByIngresos", query = "SELECT p FROM PersonaNatural p WHERE p.ingresos = :ingresos"),
    @NamedQuery(name = "PersonaNatural.findBySexo", query = "SELECT p FROM PersonaNatural p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "PersonaNatural.findByFechaNacimiento", query = "SELECT p FROM PersonaNatural p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "PersonaNatural.findByExoneradoSri", query = "SELECT p FROM PersonaNatural p WHERE p.exoneradoSri = :exoneradoSri"),
    @NamedQuery(name = "PersonaNatural.findByCargasFamiliares", query = "SELECT p FROM PersonaNatural p WHERE p.cargasFamiliares = :cargasFamiliares"),
    @NamedQuery(name = "PersonaNatural.findByFechaIngreso", query = "SELECT p FROM PersonaNatural p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "PersonaNatural.findByFechaActualizacion", query = "SELECT p FROM PersonaNatural p WHERE p.fechaActualizacion = :fechaActualizacion"),
//personalizados
    @NamedQuery(name = "PersonaNatural.findByNombreLike", query = "SELECT p FROM PersonaNatural p WHERE p.persona.nombreCompleto LIKE :nombreCompleto"),
    @NamedQuery(name = "PersonaNatural.findByidentificacion", query = "SELECT p FROM PersonaNatural p WHERE p.persona.identificacion = :identificacion"),
})
public class PersonaNatural implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersona = "PersonaNatural.findByCodigoPersona";
    public static final String findByNombreLike = "PersonaNatural.findByNombreLike";
    public static final String findByidentificacion = "PersonaNatural.findByidentificacion";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Long codigoPersona;    
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "INGRESOS")    
    private BigDecimal ingresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEXO")
    private char sexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXONERADO_SRI")
    private char exoneradoSri;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CARGAS_FAMILIARES")
    private int cargasFamiliares;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPersonaRep", fetch = FetchType.LAZY)
    private Collection<SocioMenorEdadRep> socioMenorEdadRepCollection;
    
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "CODIGO_UBI_GEO_NAC", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private UbicacionGeografica codigoUbiGeoNac;    
    @JoinColumn(name = "CODIGO_PROFESION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Profesion codigoProfesion;    
    @JoinColumn(name = "CODIGO_INSTRUCCION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Instruccion codigoInstruccion;
    @JoinColumn(name = "CODIGO_FUENTE_INGRESOS", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private FuenteIngresos codigoFuenteIngresos;
    @JoinColumn(name = "CODIGO_ESTADO_CIVIL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EstadoCivil codigoEstadoCivil;
    @JoinColumn(name = "CODIGO_UBI_GEO_NACI", referencedColumnName = "CODIGO")
    @ManyToOne(optional = true)
    private UbicacionGeografica codigoUbiGeoNaci;   

    public PersonaNatural() {
    }

    public PersonaNatural(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public PersonaNatural(Long codigoPersona, UbicacionGeografica codigoUbiGeoNac,UbicacionGeografica codigoUbiGeoNaci, String nombres, String primerApellido, String segundoApellido, BigDecimal ingresos, char sexo, Date fechaNacimiento, char exoneradoSri, int cargasFamiliares, Date fechaIngreso, Date fechaActualizacion) {
        this.codigoPersona = codigoPersona;
        this.codigoUbiGeoNac = codigoUbiGeoNac;
        this.codigoUbiGeoNaci = codigoUbiGeoNaci;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.ingresos = ingresos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.exoneradoSri = exoneradoSri;
        this.cargasFamiliares = cargasFamiliares;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public UbicacionGeografica getCodigoUbiGeoNac() {
        return codigoUbiGeoNac;
    }

    public void setCodigoUbiGeoNac(UbicacionGeografica codigoUbiGeoNac) {
        this.codigoUbiGeoNac = codigoUbiGeoNac;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public BigDecimal getIngresos() {
        return ingresos;
    }

    public void setIngresos(BigDecimal ingresos) {
        this.ingresos = ingresos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getExoneradoSri() {
        return exoneradoSri;
    }

    public void setExoneradoSri(char exoneradoSri) {
        this.exoneradoSri = exoneradoSri;
    }

    public int getCargasFamiliares() {
        return cargasFamiliares;
    }

    public void setCargasFamiliares(int cargasFamiliares) {
        this.cargasFamiliares = cargasFamiliares;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    @XmlTransient
    public Collection<SocioMenorEdadRep> getSocioMenorEdadRepCollection() {
        return socioMenorEdadRepCollection;
    }

    public void setSocioMenorEdadRepCollection(Collection<SocioMenorEdadRep> socioMenorEdadRepCollection) {
        this.socioMenorEdadRepCollection = socioMenorEdadRepCollection;
    }

    public Profesion getCodigoProfesion() {
        return codigoProfesion;
    }

    public void setCodigoProfesion(Profesion codigoProfesion) {
        this.codigoProfesion = codigoProfesion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Instruccion getCodigoInstruccion() {
        return codigoInstruccion;
    }

    public void setCodigoInstruccion(Instruccion codigoInstruccion) {
        this.codigoInstruccion = codigoInstruccion;
    }

    public FuenteIngresos getCodigoFuenteIngresos() {
        return codigoFuenteIngresos;
    }

    public void setCodigoFuenteIngresos(FuenteIngresos codigoFuenteIngresos) {
        this.codigoFuenteIngresos = codigoFuenteIngresos;
    }

    public EstadoCivil getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    public void setCodigoEstadoCivil(EstadoCivil codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
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
        if (!(object instanceof PersonaNatural)) {
            return false;
        }
        PersonaNatural other = (PersonaNatural) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaNatural[ codigoPersona=" + codigoPersona + " ]";
    }

    public UbicacionGeografica getCodigoUbiGeoNaci() {
        return codigoUbiGeoNaci;
    }

    public void setCodigoUbiGeoNaci(UbicacionGeografica codigoUbiGeoNaci) {
        this.codigoUbiGeoNaci = codigoUbiGeoNaci;
    }
    
}
