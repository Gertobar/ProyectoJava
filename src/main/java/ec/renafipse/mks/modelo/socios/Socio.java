/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.SOCIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Socio.findAll", query = "SELECT s FROM Socio s"),
    @NamedQuery(name = "Socio.findByCodigo", query = "SELECT s FROM Socio s WHERE s.socioPK.codigo = :codigo"),
    @NamedQuery(name = "Socio.findByCodigoIfip", query = "SELECT s FROM Socio s WHERE s.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Socio.findByCodigoIfipAgencia", query = "SELECT s FROM Socio s WHERE s.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "Socio.findByCodigoUsuarioCreacion", query = "SELECT s FROM Socio s WHERE s.codigoUsuarioCreacion = :codigoUsuarioCreacion"),
    @NamedQuery(name = "Socio.findByCodigoArchivo", query = "SELECT s FROM Socio s WHERE s.codigoArchivo = :codigoArchivo"),
    @NamedQuery(name = "Socio.findByEntregoRequesitios", query = "SELECT s FROM Socio s WHERE s.entregoRequesitios = :entregoRequesitios"),
    @NamedQuery(name = "Socio.findByObservaciones", query = "SELECT s FROM Socio s WHERE s.observaciones = :observaciones"),
    @NamedQuery(name = "Socio.findByFechaCreacion", query = "SELECT s FROM Socio s WHERE s.fechaCreacion = :fechaCreacion"),
    // Personalizados
    @NamedQuery(name = "Socio.findByIdentificacion", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.identificacion = :identificacion AND s.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Socio.findByNombres", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.nombreCompleto  LIKE :nombreCompleto AND s.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Socio.getCodigoSocio", query = "SELECT MAX(s.socioPK.codigo) FROM Socio s  WHERE s.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Socio.getCodigoArchivo", query = "SELECT MAX(s.codigoArchivo) FROM Socio s  WHERE s.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Socio.findByNombresIndicaActivo", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.nombreCompleto  LIKE :nombreCompleto AND s.socioPK.codigoIfip = :codigoIfip AND s.codigoEstadoSocio.indicaActivo = :indicaActivo"),
    @NamedQuery(name = "Socio.findByCodigoSocioIfip", query = "SELECT s FROM Socio s WHERE s.socioPK.codigo = :codigoSocio AND s.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Socio.findByIdPersona", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Socio.findByIdIfip", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.identificacion = :identificacion AND s.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Socio.findByIdIfipAgencia", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.identificacion = :identificacion AND s.socioPK.codigoIfip = :codigoIfip AND s.codigoIfipAgencia.codigo = :codigoIfipAgencia"),
    @NamedQuery(name = "Socio.findByIdIfipAgenciaIndicaActivo", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.identificacion = :identificacion AND s.socioPK.codigoIfip = :codigoIfip AND s.codigoIfipAgencia.codigo = :codigoIfipAgencia AND s.codigoEstadoSocio.indicaActivo = :indicaActivo"),
    @NamedQuery(name = "Socio.findByNombresIndicaActivoAgencia", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.nombreCompleto  LIKE :nombreCompleto AND s.socioPK.codigoIfip = :codigoIfip AND s.codigoEstadoSocio.indicaActivo = :indicaActivo AND s.codigoIfipAgencia.codigo = :codigoIfipAgencia"),
    @NamedQuery(name = "Socio.findByIdIndicaActivo", query = "SELECT s FROM Socio s  JOIN s.codigoPersona p WHERE p.identificacion LIKE :identificacion AND s.socioPK.codigoIfip = :codigoIfip AND s.codigoEstadoSocio.indicaActivo = :indicaActivo"),    
  })
public class Socio implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findAll = "Socio.findAll";
    public static final String findByIdentificacion = "Socio.findByIdentificacion";
    public static final String findByNombres = "Socio.findByNombres";
    public static final String getCodigoSocio = "Socio.getCodigoSocio";
    public static final String getCodigoArchivo = "Socio.getCodigoArchivo";
    public static final String findByNombresIndicaActivo = "Socio.findByNombresIndicaActivo";
    public static final String findByCodigoSocioIfip = "Socio.findByCodigoSocioIfip";
    public static final String findByIdPersona= "Socio.findByIdPersona";
    public static final String findByIdIfip = "Socio.findByIdIfip";
    public static final String findByIdIfipAgencia = "Socio.findByIdIfipAgencia";
    public static final String findByIdIfipAgenciaIndicaActivo = "Socio.findByIdIfipAgenciaIndicaActivo";
  
    @EmbeddedId
    protected SocioPK socioPK;
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "CODIGO_IFIP_AGENCIA")*/
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private IfipAgencia codigoIfipAgencia;
    /*@Basic(optional = false)
     @NotNull
     @Column(name = "CODIGO_USUARIO_CREACION")
     private long codigoUsuarioCreacion;*/
    @JoinColumn(name = "CODIGO_USUARIO_CREACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario codigoUsuarioCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ARCHIVO")
    private long codigoArchivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTREGO_REQUESITIOS")
    private char entregoRequesitios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    /* @OneToOne(cascade = CascadeType.ALL, mappedBy = "socio", fetch = FetchType.LAZY)
     private SocioSituacionPatrimonial socioSituacionPatrimonial;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "socio", fetch = FetchType.LAZY)
     private Collection<SocioMenorEdadRep> socioMenorEdadRepCollection;
     @OneToOne(cascade = CascadeType.ALL, mappedBy = "socio1", fetch = FetchType.LAZY)
     private SocioMenorEdadRep socioMenorEdadRep;
     /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "socio", fetch = FetchType.LAZY)
     private SocioFlujoCaja socioFlujoCaja;*/
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Persona codigoPersona;
    @JoinColumn(name = "CODIGO_MOTIVO_SOCIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MotivoSocio codigoMotivoSocio;
    @JoinColumn(name = "CODIGO_ESTADO_SOCIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EstadoSocio codigoEstadoSocio;
    @JoinColumn(name = "CODIGO_CONOCIMIENTO_IFIP", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ConocimientoIfip codigoConocimientoIfip;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRA_SITUACION_LABORAL")
    private char registraSituacionLaboral;

    public Socio() {
    }

    public Socio(SocioPK socioPK) {
        this.socioPK = socioPK;
    }

    public Socio(SocioPK socioPK, IfipAgencia codigoIfipAgencia, Usuario codigoUsuarioCreacion, long codigoArchivo, char entregoRequesitios, String observaciones, Date fechaCreacion) {
        this.socioPK = socioPK;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoUsuarioCreacion = codigoUsuarioCreacion;
        this.codigoArchivo = codigoArchivo;
        this.entregoRequesitios = entregoRequesitios;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
    }

    public Socio(long codigo, long codigoIfip) {
        this.socioPK = new SocioPK(codigo, codigoIfip);
    }

    public SocioPK getSocioPK() {
        return socioPK;
    }

    public void setSocioPK(SocioPK socioPK) {
        this.socioPK = socioPK;
    }

    public IfipAgencia getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(IfipAgencia codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Usuario getCodigoUsuarioCreacion() {
        return codigoUsuarioCreacion;
    }

    public void setCodigoUsuarioCreacion(Usuario codigoUsuarioCreacion) {
        this.codigoUsuarioCreacion = codigoUsuarioCreacion;
    }

    public long getCodigoArchivo() {
        return codigoArchivo;
    }

    public void setCodigoArchivo(long codigoArchivo) {
        this.codigoArchivo = codigoArchivo;
    }

    public char getEntregoRequesitios() {
        return entregoRequesitios;
    }

    public void setEntregoRequesitios(char entregoRequesitios) {
        this.entregoRequesitios = entregoRequesitios;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /*public SocioSituacionPatrimonial getSocioSituacionPatrimonial() {
     return socioSituacionPatrimonial;
     }

     public void setSocioSituacionPatrimonial(SocioSituacionPatrimonial socioSituacionPatrimonial) {
     this.socioSituacionPatrimonial = socioSituacionPatrimonial;
     }

     @XmlTransient
     public Collection<SocioMenorEdadRep> getSocioMenorEdadRepCollection() {
     return socioMenorEdadRepCollection;
     }

     public void setSocioMenorEdadRepCollection(Collection<SocioMenorEdadRep> socioMenorEdadRepCollection) {
     this.socioMenorEdadRepCollection = socioMenorEdadRepCollection;
     }

     public SocioMenorEdadRep getSocioMenorEdadRep() {
     return socioMenorEdadRep;
     }

     public void setSocioMenorEdadRep(SocioMenorEdadRep socioMenorEdadRep) {
     this.socioMenorEdadRep = socioMenorEdadRep;
     }

     /*
     public SocioFlujoCaja getSocioFlujoCaja() {
     return socioFlujoCaja;
     }

     public void setSocioFlujoCaja(SocioFlujoCaja socioFlujoCaja) {
     this.socioFlujoCaja = socioFlujoCaja;
     }*/
    public Persona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Persona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public MotivoSocio getCodigoMotivoSocio() {
        return codigoMotivoSocio;
    }

    public void setCodigoMotivoSocio(MotivoSocio codigoMotivoSocio) {
        this.codigoMotivoSocio = codigoMotivoSocio;
    }

    public EstadoSocio getCodigoEstadoSocio() {
        return codigoEstadoSocio;
    }

    public void setCodigoEstadoSocio(EstadoSocio codigoEstadoSocio) {
        this.codigoEstadoSocio = codigoEstadoSocio;
    }

    public ConocimientoIfip getCodigoConocimientoIfip() {
        return codigoConocimientoIfip;
    }

    public void setCodigoConocimientoIfip(ConocimientoIfip codigoConocimientoIfip) {
        this.codigoConocimientoIfip = codigoConocimientoIfip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socioPK != null ? socioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socio)) {
            return false;
        }
        Socio other = (Socio) object;
        if ((this.socioPK == null && other.socioPK != null) || (this.socioPK != null && !this.socioPK.equals(other.socioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.Socio[ socioPK=" + socioPK + " ]";
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * Devuelve N si no registra y S si ya tiene registrado la situacion laboral
     * @return
     */
    public char getRegistraSituacionLaboral() {
        return registraSituacionLaboral;
    }

    /**
     * Permite establecer la situacion laboral
     * @param registraSituacionLaboral
     */
    public void setRegistraSituacionLaboral(char registraSituacionLaboral) {
        this.registraSituacionLaboral = registraSituacionLaboral;
    }

}
