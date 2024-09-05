/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

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
 * @author vicastoc
 */

@Entity
@Table(name = "MKS_SEGURIDADES.CONTRASENA_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContrasenaUsuario.findAll", query = "SELECT c FROM ContrasenaUsuario c"),
    @NamedQuery(name = "ContrasenaUsuario.findByCodigo", query = "SELECT c FROM ContrasenaUsuario c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ContrasenaUsuario.findByContrasena", query = "SELECT c FROM ContrasenaUsuario c WHERE c.contrasena = :contrasena"),
    @NamedQuery(name = "ContrasenaUsuario.findByMesesVigencia", query = "SELECT c FROM ContrasenaUsuario c WHERE c.mesesVigencia = :mesesVigencia"),
    @NamedQuery(name = "ContrasenaUsuario.findByDiasGracia", query = "SELECT c FROM ContrasenaUsuario c WHERE c.diasGracia = :diasGracia"),
    @NamedQuery(name = "ContrasenaUsuario.findByFecha", query = "SELECT c FROM ContrasenaUsuario c WHERE c.fecha = :fecha"),
// Personalizados
    @NamedQuery(name = "ContrasenaUsuario.findByContrasenaFecha", query = "SELECT c FROM ContrasenaUsuario c WHERE  c.codigoSistema = :codigoSistema AND c.contrasena = :contrasena AND c.fecha BETWEEN :fechaInicial AND :fechaFinal")
})
public class ContrasenaUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByContrasenaFecha = "ContrasenaUsuario.findByContrasenaFecha";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CONTRASENA_USUARIO")
    @SequenceGenerator(name = "GSQ_CONTRASENA_USUARIO", schema = "MKS_SEGURIDADES", allocationSize = 0, sequenceName = "SEQ_CONTRASENA_USUARIO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SISTEMA")
    private Long codigoSistema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESES_VIGENCIA")
    private short mesesVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_GRACIA")
    private short diasGracia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO_USUARIO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO_SISTEMA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private UsuarioSistema usuarioSistema;
    @JoinColumn(name = "MOTIVO_CAMBIO_USUARIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MotivoCambioContrasena motivoCambioUsuario;

    public ContrasenaUsuario() {
    }

    public ContrasenaUsuario(Long codigo) {
        this.codigo = codigo;
    }

    public ContrasenaUsuario(Long codigo, String contrasena, short mesesVigencia, short diasGracia, Date fecha) {
        this.codigo = codigo;
        this.contrasena = contrasena;
        this.mesesVigencia = mesesVigencia;
        this.diasGracia = diasGracia;
        this.fecha = fecha;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public short getMesesVigencia() {
        return mesesVigencia;
    }

    public void setMesesVigencia(short mesesVigencia) {
        this.mesesVigencia = mesesVigencia;
    }

    public short getDiasGracia() {
        return diasGracia;
    }

    public void setDiasGracia(short diasGracia) {
        this.diasGracia = diasGracia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public MotivoCambioContrasena getMotivoCambioUsuario() {
        return motivoCambioUsuario;
    }

    public void setMotivoCambioUsuario(MotivoCambioContrasena motivoCambioUsuario) {
        this.motivoCambioUsuario = motivoCambioUsuario;
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
        if (!(object instanceof ContrasenaUsuario)) {
            return false;
        }
        ContrasenaUsuario other = (ContrasenaUsuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " ec.renafipse.mks.modelo.seguridades.ContrasenaUsuario[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoUsuario
     */
    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * @param codigoUsuario the codigoUsuario to set
     */
    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * @return the codigoSistema
     */
    public Long getCodigoSistema() {
        return codigoSistema;
    }

    /**
     * @param codigoSistema the codigoSistema to set
     */
    public void setCodigoSistema(Long codigoSistema) {
        this.codigoSistema = codigoSistema;
    }
    
}
