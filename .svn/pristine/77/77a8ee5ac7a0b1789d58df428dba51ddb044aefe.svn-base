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
@Table(name = "MKS_SEGURIDADES.BLOQUEO_CUENTA_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BloqueoCuentaUsuario.findAll", query = "SELECT b FROM BloqueoCuentaUsuario b"),
    @NamedQuery(name = "BloqueoCuentaUsuario.findByCodigo", query = "SELECT b FROM BloqueoCuentaUsuario b WHERE b.codigo = :codigo"),
    @NamedQuery(name = "BloqueoCuentaUsuario.findByFechaBloqueo", query = "SELECT b FROM BloqueoCuentaUsuario b WHERE b.fechaBloqueo = :fechaBloqueo"),
    @NamedQuery(name = "BloqueoCuentaUsuario.findByVigente", query = "SELECT b FROM BloqueoCuentaUsuario b WHERE b.vigente = :vigente"),
//Personalizadas
    @NamedQuery(name = "BloqueoCuentaUsuario.findByBloqueoCuenUsuVig", query = "SELECT b FROM BloqueoCuentaUsuario b WHERE b.usuarioSistema= :usuarioSistema AND b.vigente = :vigente")    

})
public class BloqueoCuentaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByBloqueoCuenUsuVig = "BloqueoCuentaUsuario.findByBloqueoCuenUsuVig";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_BLOQUEO_CUENTA_USUARIO")
    @SequenceGenerator(name = "GSQ_BLOQUEO_CUENTA_USUARIO", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_BLOQUEO_CUENTA_USUARIO")
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
    @Column(name = "FECHA_BLOQUEO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBloqueo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private char vigente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO_USUARIO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_SISTEMA", referencedColumnName = "CODIGO_SISTEMA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private UsuarioSistema usuarioSistema;
    @JoinColumn(name = "BLOQUEADO_POR", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Usuario bloqueadoPor;
    @JoinColumn(name = "CODIGO_MOTIVO_BLOQUEO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MotivoBloqueoCuenta codigoMotivoBloqueo;

    public BloqueoCuentaUsuario() {
    }

    public BloqueoCuentaUsuario(Long codigo) {
        this.codigo = codigo;
    }

    public BloqueoCuentaUsuario(Long codigo, Date fechaBloqueo, char vigente) {
        this.codigo = codigo;
        this.fechaBloqueo = fechaBloqueo;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Date fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public Usuario getBloqueadoPor() {
        return bloqueadoPor;
    }

    public void setBloqueadoPor(Usuario bloqueadoPor) {
        this.bloqueadoPor = bloqueadoPor;
    }

    public MotivoBloqueoCuenta getCodigoMotivoBloqueo() {
        return codigoMotivoBloqueo;
    }

    public void setCodigoMotivoBloqueo(MotivoBloqueoCuenta codigoMotivoBloqueo) {
        this.codigoMotivoBloqueo = codigoMotivoBloqueo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCodigo() != null ? getCodigo().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloqueoCuentaUsuario)) {
            return false;
        }
        BloqueoCuentaUsuario other = (BloqueoCuentaUsuario) object;
        if ((this.getCodigo() == null && other.getCodigo() != null) || (this.getCodigo() != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.BloqueoCuentaUsuario[ codigo=" + getCodigo() + " ]";
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

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
