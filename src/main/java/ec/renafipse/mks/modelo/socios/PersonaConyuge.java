/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

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
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_CONYUGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaConyuge.findAll", query = "SELECT p FROM PersonaConyuge p"),
    @NamedQuery(name = "PersonaConyuge.findByCodigoPersona", query = "SELECT p FROM PersonaConyuge p WHERE p.personaConyugePK.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "PersonaConyuge.findByCodigoPersonaConyuge", query = "SELECT p FROM PersonaConyuge p WHERE p.personaConyugePK.codigoPersonaConyuge = :codigoPersonaConyuge"),
    @NamedQuery(name = "PersonaConyuge.findByFirma", query = "SELECT p FROM PersonaConyuge p WHERE p.firma = :firma"),
    @NamedQuery(name = "PersonaConyuge.findByEliminado", query = "SELECT p FROM PersonaConyuge p WHERE p.eliminado = :eliminado"),
    @NamedQuery(name = "PersonaConyuge.findByCodigoPersonaElminado", query = "SELECT p FROM PersonaConyuge p WHERE p.personaConyugePK.codigoPersona = :codigoPersona AND p.eliminado = :eliminado"),
//personalizados
    @NamedQuery(name = "PersonaConyuge.findByCasadoCodigoPersonaConyuge", query = "SELECT p FROM PersonaConyuge p WHERE p.personaNatural.persona.nombreCompleto LIKE :nombreCompleto AND p.personaNatural.codigoEstadoCivil.codigo = :codigoEstado AND p.personaConyugePK.codigoPersonaConyuge = :codigoPersonaConyuge AND p.eliminado = :eliminado"),
    @NamedQuery(name = "PersonaConyuge.findByCodigoPersonaYcodigoConyuge", query = "SELECT p FROM PersonaConyuge p WHERE p.personaConyugePK.codigoPersona = :codigoPersona OR p.personaConyugePK.codigoPersonaConyuge = :codigoPersona OR p.personaConyugePK.codigoPersonaConyuge = :codigoPersonaConyuge OR p.personaConyugePK.codigoPersona = :codigoPersonaConyuge"),
    @NamedQuery(name = "PersonaConyuge.findByCodigoPersonaConyugeEliminado", query = "SELECT p FROM PersonaConyuge p WHERE p.personaConyugePK.codigoPersonaConyuge = :codigoPersonaConyuge AND p.eliminado = :eliminado"),
    @NamedQuery(name = "PersonaConyuge.findByCodigoPersonaFechaRegistro", query = "SELECT p FROM PersonaConyuge p WHERE p.personaConyugePK.codigoPersona = :codigoPersona AND p.eliminado = :eliminado ORDER BY p.fechaRegistro DESC"),
    @NamedQuery(name = "PersonaConyuge.findByCodigoPersonaYconyuge", query = "SELECT p FROM PersonaConyuge p WHERE p.personaConyugePK.codigoPersona = :codigoPersona AND p.personaConyugePK.codigoPersonaConyuge = :codigoPersonaConyuge")
       
})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
    name = "PersonaConyuge.InsertaConyuge",
    procedureName = "MKS_SERVICIOS.PKM_SERVICIO_PERSONA.P_INSERTA_CONYUGE",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_persona", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_persona_conyuge", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
),
    @NamedStoredProcedureQuery(
    name = "PersonaConyuge.DesvinculaConyuge",
    procedureName = "MKS_SOCIOS.PKM_PERSONA_CONYUGE.P_DESVINCULA_CONYUGE",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_persona", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_usuario", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
)})
public class PersonaConyuge implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersonaElminado = "PersonaConyuge.findByCodigoPersonaElminado";
    public static final String findByCodigoPersona = "PersonaConyuge.findByCodigoPersona";
    public static final String findByCodigoPersonaYcodigoConyuge = "PersonaConyuge.findByCodigoPersonaYcodigoConyuge";
    public static final String findByCasadoCodigoPersonaConyuge = "PersonaConyuge.findByCasadoCodigoPersonaConyuge";
    public static final String findByCodigoPersonaConyugeEliminado = "PersonaConyuge.findByCodigoPersonaConyugeEliminado";
    public static final String findByCodigoPersonaFechaRegistro = "PersonaConyuge.findByCodigoPersonaFechaRegistro";
    public static final String findByCodigoPersonaYconyuge = "PersonaConyuge.findByCodigoPersonaYconyuge";
    public static final String insertaConyuge = "PersonaConyuge.InsertaConyuge";
    public static final String desvinculaConyuge = "PersonaConyuge.DesvinculaConyuge";
    @EmbeddedId
    protected PersonaConyugePK personaConyugePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIRMA")
    private char firma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "CODIGO_PERSONA_CONYUGE", referencedColumnName = "CODIGO_PERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PersonaNatural personaNaturalConyuge;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO_PERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PersonaNatural personaNatural;

    public PersonaConyuge() {
    }

    public PersonaConyuge(PersonaConyugePK personaConyugePK) {
        this.personaConyugePK = personaConyugePK;
    }

    public PersonaConyuge(PersonaConyugePK personaConyugePK, char firma, char eliminado) {
        this.personaConyugePK = personaConyugePK;
        this.firma = firma;
        this.eliminado = eliminado;
    }

    public PersonaConyuge(long codigoPersona, long codigoPersonaConyuge) {
        this.personaConyugePK = new PersonaConyugePK(codigoPersona, codigoPersonaConyuge);
    }

    public PersonaConyugePK getPersonaConyugePK() {
        return personaConyugePK;
    }

    public void setPersonaConyugePK(PersonaConyugePK personaConyugePK) {
        this.personaConyugePK = personaConyugePK;
    }

    public char getFirma() {
        return firma;
    }

    public void setFirma(char firma) {
        this.firma = firma;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaConyugePK != null ? personaConyugePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaConyuge)) {
            return false;
        }
        PersonaConyuge other = (PersonaConyuge) object;
        if ((this.personaConyugePK == null && other.personaConyugePK != null) || (this.personaConyugePK != null && !this.personaConyugePK.equals(other.personaConyugePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaConyuge[ personaConyugePK=" + personaConyugePK + " ]";
    }

    /**
     * @return the personaNaturalConyuge
     */
    public PersonaNatural getPersonaNaturalConyuge() {
        return personaNaturalConyuge;
    }

    /**
     * @param personaNaturalConyuge the personaNaturalConyuge to set
     */
    public void setPersonaNaturalConyuge(PersonaNatural personaNaturalConyuge) {
        this.personaNaturalConyuge = personaNaturalConyuge;
    }

    /**
     * @return the registradoPor
     */
    public long getRegistradoPor() {
        return registradoPor;
    }

    /**
     * @param registradoPor the registradoPor to set
     */
    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
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
    
}
