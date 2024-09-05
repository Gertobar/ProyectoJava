/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
 * @author mvks
 */
@Entity
@Table(name = "PERSONA_RELACION_IFIP", catalog = "", schema = "MKS_SOCIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaRelacionIfip.findAll", query = "SELECT p FROM PersonaRelacionIfip p")
    , @NamedQuery(name = "PersonaRelacionIfip.findByCodigo", query = "SELECT p FROM PersonaRelacionIfip p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "PersonaRelacionIfip.findByCodigoPersona", query = "SELECT p FROM PersonaRelacionIfip p WHERE p.codigoPersona.codigo = :codigoPersona")
    , @NamedQuery(name = "PersonaRelacionIfip.findByFechaRegistro", query = "SELECT p FROM PersonaRelacionIfip p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "PersonaRelacionIfip.findByObservaciones", query = "SELECT p FROM PersonaRelacionIfip p WHERE p.observaciones = :observaciones")
    , @NamedQuery(name = "PersonaRelacionIfip.findByEliminado", query = "SELECT p FROM PersonaRelacionIfip p WHERE p.eliminado = :eliminado")
        //PERSONSALIZADOS
    , @NamedQuery(name = "PersonaRelacionIfip.findByCodigoPersonaEliminado", query = "SELECT p.codigoPersona FROM PersonaRelacionIfip p WHERE p.codigoPersona.codigo = :codigoPersona AND p.eliminado = :eliminado")
    , @NamedQuery(name = "PersonaRelacionIfip.findByCodigoPersonaP", query = "SELECT p FROM Persona p WHERE p.codigo = :codigoPersona")})

@NamedNativeQueries({
    @NamedNativeQuery(name = "PersonaRelacionIfip.findByVinculadoIfip", query = "SELECT CODIGO, FECHA_REGISTRO, OBSERVACIONES, CODIGO_CARGO, CODIGO_PERSONA, CODIGO_IFIP_AGENCIA FROM mks_socios.PERSONA_RELACION_IFIP PRI WHERE PRI.CODIGO_PERSONA IN ( SELECT PV.CODIGO_PERSONA FROM mks_socios.PERSONA_VINCULADO PV WHERE PV.CODIGO_PERSONA_VINCULADO = :codigoPersona AND PV.ELIMINADO = :eliminado UNION SELECT PV.CODIGO_PERSONA_VINCULADO FROM mks_socios.PERSONA_VINCULADO PV WHERE PV.CODIGO_PERSONA = :codigoPersona AND PV.ELIMINADO = :eliminado ) AND PRI.ELIMINADO = :eliminado UNION SELECT CODIGO, FECHA_REGISTRO, OBSERVACIONES, CODIGO_CARGO, CODIGO_PERSONA, CODIGO_IFIP_AGENCIA FROM mks_socios.PERSONA_RELACION_IFIP WHERE CODIGO_PERSONA = :codigoPersona AND ELIMINADO = :eliminado", resultClass = PersonaRelacionIfip.class)
})
public class PersonaRelacionIfip implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersona = "PersonaRelacionIfip.findByCodigoPersona";
    public static final String findByCodigoPersonaP = "PersonaRelacionIfip.findByCodigoPersonaP";
    public static final String findByCodigoPersonaEliminado = "PersonaRelacionIfip.findByCodigoPersonaEliminado";
    public static final String findByVinculadoIfip = "PersonaRelacionIfip.findByVinculadoIfip";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PERSONA_RELACION_IFIP")
    @SequenceGenerator(name = "GSQ_PERSONA_RELACION_IFIP", schema = "MKS_SOCIOS",  allocationSize = 0, sequenceName = "SEQ_PERSONA_RELACION_IFIP")
    @Column(name = "CODIGO")
    private Long codigo;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character eliminado;
    @JoinColumn(name = "CODIGO_CARGO", referencedColumnName = "CODIGO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cargo codigoCargo;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona codigoPersona;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private IfipAgencia codigoIfipAgencia;

    public PersonaRelacionIfip() {
    }

    public PersonaRelacionIfip(Long codigo) {
        this.codigo = codigo;
    }

    public PersonaRelacionIfip(Long codigo, String observaciones, Character eliminado) {
        this.codigo = codigo;
        this.observaciones = observaciones;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public IfipAgencia getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(IfipAgencia codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public Cargo getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Cargo codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public Persona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Persona codigoPersona) {
        this.codigoPersona = codigoPersona;
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
        if (!(object instanceof PersonaRelacionIfip)) {
            return false;
        }
        PersonaRelacionIfip other = (PersonaRelacionIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaRelacionIfip[ codigo=" + codigo + " ]";
    }
    
}
