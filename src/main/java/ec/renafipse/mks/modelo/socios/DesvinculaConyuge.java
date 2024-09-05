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
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Mascoop
 */
@Entity
@Table(name = "DESVINCULA_CONYUGE", catalog = "", schema = "MKS_SOCIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DesvinculaConyuge.findAll", query = "SELECT d FROM DesvinculaConyuge d")
    , @NamedQuery(name = "DesvinculaConyuge.findByCodigo", query = "SELECT d FROM DesvinculaConyuge d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DesvinculaConyuge.findByCodigoUsuario", query = "SELECT d FROM DesvinculaConyuge d WHERE d.codigoUsuario = :codigoUsuario")
    , @NamedQuery(name = "DesvinculaConyuge.findByFechaSistema", query = "SELECT d FROM DesvinculaConyuge d WHERE d.fechaSistema = :fechaSistema")
    , @NamedQuery(name = "DesvinculaConyuge.findByMotivo", query = "SELECT d FROM DesvinculaConyuge d WHERE d.motivo = :motivo")
    , @NamedQuery(name = "DesvinculaConyuge.findByCodigoEstadoCivilFinal", query = "SELECT d FROM DesvinculaConyuge d WHERE d.codigoEstadoCivilFinal = :codigoEstadoCivilFinal")
    , @NamedQuery(name = "DesvinculaConyuge.findByCodigoEstadoCivil", query = "SELECT d FROM DesvinculaConyuge d WHERE d.codigoEstadoCivil = :codigoEstadoCivil")
    , @NamedQuery(name = "DesvinculaConyuge.findByCodigoPersonaConyuge", query = "SELECT d FROM DesvinculaConyuge d WHERE d.codigoPersonaConyuge = :codigoPersonaConyuge")
    , @NamedQuery(name = "DesvinculaConyuge.findByCodigoPersona", query = "SELECT d FROM DesvinculaConyuge d WHERE d.codigoPersona = :codigoPersona")})
public class DesvinculaConyuge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO", nullable = false)
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Size(max = 200)
    @Column(length = 200)
    private String motivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO_CIVIL_FINAL", nullable = false)
    private long codigoEstadoCivilFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO_CIVIL", nullable = false)
    private long codigoEstadoCivil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA_CONYUGE", nullable = false)
    private long codigoPersonaConyuge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA", nullable = false)
    private long codigoPersona;

    public DesvinculaConyuge() {
    }

    public DesvinculaConyuge(Long codigo) {
        this.codigo = codigo;
    }

    public DesvinculaConyuge(Long codigo, long codigoUsuario, Date fechaSistema, long codigoEstadoCivilFinal, long codigoEstadoCivil, long codigoPersonaConyuge, long codigoPersona) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.fechaSistema = fechaSistema;
        this.codigoEstadoCivilFinal = codigoEstadoCivilFinal;
        this.codigoEstadoCivil = codigoEstadoCivil;
        this.codigoPersonaConyuge = codigoPersonaConyuge;
        this.codigoPersona = codigoPersona;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public long getCodigoEstadoCivilFinal() {
        return codigoEstadoCivilFinal;
    }

    public void setCodigoEstadoCivilFinal(long codigoEstadoCivilFinal) {
        this.codigoEstadoCivilFinal = codigoEstadoCivilFinal;
    }

    public long getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    public void setCodigoEstadoCivil(long codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    public long getCodigoPersonaConyuge() {
        return codigoPersonaConyuge;
    }

    public void setCodigoPersonaConyuge(long codigoPersonaConyuge) {
        this.codigoPersonaConyuge = codigoPersonaConyuge;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
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
        if (!(object instanceof DesvinculaConyuge)) {
            return false;
        }
        DesvinculaConyuge other = (DesvinculaConyuge) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.DesvinculaConyuge[ codigo=" + codigo + " ]";
    }
    
}
