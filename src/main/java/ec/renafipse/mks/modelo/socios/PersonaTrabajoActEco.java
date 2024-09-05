/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.comunes.Empresa;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.comunes.TipoTelefono;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_TRABAJO_ACT_ECO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaTrabajoActEco.findAll", query = "SELECT p FROM PersonaTrabajoActEco p"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigo", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigoUbiGeoTra", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.codigoUbiGeoTra = :codigoUbiGeoTra"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigoEmpresa", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.codigoEmpresa = :codigoEmpresa"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigoPeriodicidad", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.codigoPeriodicidad = :codigoPeriodicidad"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByBarrio", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.barrio = :barrio"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByDireccion", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByReferencia", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.referencia = :referencia"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByTiempo", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.tiempo = :tiempo"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByFechaIngreso", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByFechaActualizacion", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigoTipoTelefono", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.codigoTipoTelefono = :codigoTipoTelefono"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByNumeroTelefonico", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.numeroTelefonico = :numeroTelefonico"),
    //PERSONALIZADOS
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigoPersonaActividadEconomicaEliminado", query = "SELECT p FROM PersonaTrabajoActEco p JOIN p.personaActividadEconomica r JOIN r.actividadEconomica a WHERE p.codigoPersona = :codigoPersona AND r.eliminado = :eliminado AND a.eliminado = :eliminado  ORDER BY p.personaActividadEconomica.actividadEconomica.nombre"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigoPersona", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.personaActividadEconomica.personaActividadEconomicaPK.codigoPersona = :codigoPersona ORDER BY p.personaActividadEconomica.actividadEconomica.nombre"),
    @NamedQuery(name = "PersonaTrabajoActEco.findByCodigoPersonaCodigoActividadCodigoEmpresa", query = "SELECT p FROM PersonaTrabajoActEco p WHERE p.codigoPersona = :codigoPersona AND p.codigoActividadEconomica = :codigoActividadEconomica AND p.codigoEmpresa = :codigoEmpresa")
})
public class PersonaTrabajoActEco implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersonaCodigoActividadCodigoEmpresa = "PersonaTrabajoActEco.findByCodigoPersonaCodigoActividadCodigoEmpresa";
    public static final String findByCodigoPersonaActividadEconomicaEliminado = "PersonaTrabajoActEco.findByCodigoPersonaActividadEconomicaEliminado";
    public static final String findByfindByCodigoPersona = "PersonaTrabajoActEco.findByCodigoPersona";
   @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PERSONA_TRABAJO_ACT_ECO")
    @SequenceGenerator(name = "GSQ_PERSONA_TRABAJO_ACT_ECO", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_PERSONA_TRABAJO_ACT_ECO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_UBI_GEO_TRA")
    private long codigoUbiGeoTra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_EMPRESA")
    private long codigoEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ACTIVIDAD_ECONOMICA")
    private long codigoActividadEconomica;
        @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CARGO")
    private long codigoCargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERIODICIDAD")
    private long codigoPeriodicidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BARRIO")
    private String barrio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO")
    private int tiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_TELEFONO")
    private long codigoTipoTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_RELACION")
    private long codigoTipoRelacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SECTOR")
    private long codigoSector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_TELEFONICO")
    private String numeroTelefonico;
     @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_RELACION", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private TipoRelacion tipoRelacion;
    @JoinColumn(name = "CODIGO_TIPO_TELEFONO", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private TipoTelefono tipoTelefono;
    @JoinColumn(name = "CODIGO_SECTOR", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private Sector sector;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ACTIVIDAD_ECONOMICA", referencedColumnName = "CODIGO_ACTIVIDAD_ECONOMICA", insertable = false, updatable =  false),
        @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO_PERSONA", insertable = false, updatable =  false)})
    @ManyToOne(optional = false)
    private PersonaActividadEconomica personaActividadEconomica;
    @JoinColumn(name = "CODIGO_CARGO", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    @JoinColumn(name = "CODIGO_EMPRESA", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private Empresa empresa;
    @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private Periodicidad periodicidad;
    @JoinColumn(name = "CODIGO_UBI_GEO_TRA", referencedColumnName = "CODIGO", insertable = false, updatable =  false)
    @ManyToOne(optional = false)
    private UbicacionGeografica ubicacionGeograficaTrabajo;

    public PersonaTrabajoActEco() {
    }

    public PersonaTrabajoActEco(Long codigo) {
        this.codigo = codigo;
    }

    public PersonaTrabajoActEco(Long codigo, long codigoUbiGeoTra, long codigoEmpresa, long codigoPeriodicidad, String barrio, String direccion, String referencia, int tiempo, Date fechaIngreso, Date fechaActualizacion, long codigoTipoTelefono, String numeroTelefonico) {
        this.codigo = codigo;
        this.codigoUbiGeoTra = codigoUbiGeoTra;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoPeriodicidad = codigoPeriodicidad;
        this.barrio = barrio;
        this.direccion = direccion;
        this.referencia = referencia;
        this.tiempo = tiempo;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
        this.codigoTipoTelefono = codigoTipoTelefono;
        this.numeroTelefonico = numeroTelefonico;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUbiGeoTra() {
        return codigoUbiGeoTra;
    }

    public void setCodigoUbiGeoTra(long codigoUbiGeoTra) {
        this.codigoUbiGeoTra = codigoUbiGeoTra;
    }

    public long getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(long codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public long getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(long codigoPeriodicidad) {
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

    public long getCodigoTipoTelefono() {
        return codigoTipoTelefono;
    }

    public void setCodigoTipoTelefono(long codigoTipoTelefono) {
        this.codigoTipoTelefono = codigoTipoTelefono;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

  


    public PersonaActividadEconomica getPersonaActividadEconomica() {
        return personaActividadEconomica;
    }

    public void setPersonaActividadEconomica(PersonaActividadEconomica personaActividadEconomica) {
        this.personaActividadEconomica = personaActividadEconomica;
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
        if (!(object instanceof PersonaTrabajoActEco)) {
            return false;
        }
        PersonaTrabajoActEco other = (PersonaTrabajoActEco) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.socios.PersonaTrabajoActEco[ codigo=" + codigo + " ]";
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the codigoCargo
     */
    public long getCodigoCargo() {
        return codigoCargo;
    }

    /**
     * @param codigoCargo the codigoCargo to set
     */
    public void setCodigoCargo(long codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    /**
     * @return the codigoTipoRelacion
     */
    public long getCodigoTipoRelacion() {
        return codigoTipoRelacion;
    }

    /**
     * @param codigoTipoRelacion the codigoTipoRelacion to set
     */
    public void setCodigoTipoRelacion(long codigoTipoRelacion) {
        this.codigoTipoRelacion = codigoTipoRelacion;
    }

    /**
     * @return the tipoRelacion
     */
    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
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

    /**
     * @return the fechaSalida
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the sector
     */
    public Sector getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(Sector sector) {
        this.sector = sector;
    }

    /**
     * @return the codigoSector
     */
    public long getCodigoSector() {
        return codigoSector;
    }

    /**
     * @param codigoSector the codigoSector to set
     */
    public void setCodigoSector(long codigoSector) {
        this.codigoSector = codigoSector;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the tipoTelefono
     */
    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    /**
     * @param tipoTelefono the tipoTelefono to set
     */
    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    /**
     * @return the ubicacionGeograficaTrabajo
     */
    public UbicacionGeografica getUbicacionGeograficaTrabajo() {
        return ubicacionGeograficaTrabajo;
    }

    /**
     * @param ubicacionGeograficaTrabajo the ubicacionGeograficaTrabajo to set
     */
    public void setUbicacionGeograficaTrabajo(UbicacionGeografica ubicacionGeograficaTrabajo) {
        this.ubicacionGeograficaTrabajo = ubicacionGeograficaTrabajo;
    }

    /**
     * @return the codigoPersona
     */
    public long getCodigoPersona() {
        return codigoPersona;
    }

    /**
     * @param codigoPersona the codigoPersona to set
     */
    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    /**
     * @return the codigoActividadEconomica
     */
    public long getCodigoActividadEconomica() {
        return codigoActividadEconomica;
    }

    /**
     * @param codigoActividadEconomica the codigoActividadEconomica to set
     */
    public void setCodigoActividadEconomica(long codigoActividadEconomica) {
        this.codigoActividadEconomica = codigoActividadEconomica;
    }
    
}
