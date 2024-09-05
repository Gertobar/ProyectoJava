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
@Table(name = "MKS_SEGURIDADES.USUARIO_ACCESO_SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioAccesoSistema.findAll", query = "SELECT u FROM UsuarioAccesoSistema u"),
    @NamedQuery(name = "UsuarioAccesoSistema.findByCodigo", query = "SELECT u FROM UsuarioAccesoSistema u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "UsuarioAccesoSistema.findByFechaAcceso", query = "SELECT u FROM UsuarioAccesoSistema u WHERE u.fechaAcceso = :fechaAcceso"),
    @NamedQuery(name = "UsuarioAccesoSistema.findByDireccionIp", query = "SELECT u FROM UsuarioAccesoSistema u WHERE u.direccionIp = :direccionIp")})
public class UsuarioAccesoSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_USUARIO_ACCESO_SISTEMA")
    @SequenceGenerator(name = "GSQ_USUARIO_ACCESO_SISTEMA", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_USUARIO_ACCESO_SISTEMA")
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DIRECCION_IP")
    private String direccionIp;
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @NotNull
    @Column(name = "CODIGO_SISTEMA")
    private Long codigoSistema;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO_USUARIO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO_SISTEMA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private UsuarioSistema usuarioSistema;

    public UsuarioAccesoSistema() {
    }

    public UsuarioAccesoSistema(Long codigo) {
        this.codigo = codigo;
    }

    public UsuarioAccesoSistema(Long codigo, Date fechaAcceso, String direccionIp) {
        this.codigo = codigo;
        this.fechaAcceso = fechaAcceso;
        this.direccionIp = direccionIp;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(Date fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
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
        if (!(object instanceof UsuarioAccesoSistema)) {
            return false;
        }
        UsuarioAccesoSistema other = (UsuarioAccesoSistema) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.UsuarioAccesoSistema[ codigo=" + codigo + " ]";
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
