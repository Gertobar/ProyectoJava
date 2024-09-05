/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.RESPUESTA_PREGUNTA_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaPreguntaUsuario.findAll", query = "SELECT r FROM RespuestaPreguntaUsuario r"),
    @NamedQuery(name = "RespuestaPreguntaUsuario.findByCodigoUsuario", query = "SELECT r FROM RespuestaPreguntaUsuario r WHERE r.respuestaPreguntaUsuarioPK.codigoUsuario = :codigoUsuario"),
    @NamedQuery(name = "RespuestaPreguntaUsuario.findByCodigoBancoPregunta", query = "SELECT r FROM RespuestaPreguntaUsuario r WHERE r.respuestaPreguntaUsuarioPK.codigoBancoPregunta = :codigoBancoPregunta"),
    @NamedQuery(name = "RespuestaPreguntaUsuario.findByRespuesta", query = "SELECT r FROM RespuestaPreguntaUsuario r WHERE r.respuesta = :respuesta")})
public class RespuestaPreguntaUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespuestaPreguntaUsuarioPK respuestaPreguntaUsuarioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RESPUESTA")
    private String respuesta;
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "CODIGO_BANCO_PREGUNTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BancoPreguntaSesion bancoPreguntaSesion;

    public RespuestaPreguntaUsuario() {
    }

    public RespuestaPreguntaUsuario(RespuestaPreguntaUsuarioPK respuestaPreguntaUsuarioPK) {
        this.respuestaPreguntaUsuarioPK = respuestaPreguntaUsuarioPK;
    }

    public RespuestaPreguntaUsuario(RespuestaPreguntaUsuarioPK respuestaPreguntaUsuarioPK, String respuesta) {
        this.respuestaPreguntaUsuarioPK = respuestaPreguntaUsuarioPK;
        this.respuesta = respuesta;
    }

    public RespuestaPreguntaUsuario(long codigoUsuario, long codigoBancoPregunta) {
        this.respuestaPreguntaUsuarioPK = new RespuestaPreguntaUsuarioPK(codigoUsuario, codigoBancoPregunta);
    }

    public RespuestaPreguntaUsuarioPK getRespuestaPreguntaUsuarioPK() {
        return respuestaPreguntaUsuarioPK;
    }

    public void setRespuestaPreguntaUsuarioPK(RespuestaPreguntaUsuarioPK respuestaPreguntaUsuarioPK) {
        this.respuestaPreguntaUsuarioPK = respuestaPreguntaUsuarioPK;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BancoPreguntaSesion getBancoPreguntaSesion() {
        return bancoPreguntaSesion;
    }

    public void setBancoPreguntaSesion(BancoPreguntaSesion bancoPreguntaSesion) {
        this.bancoPreguntaSesion = bancoPreguntaSesion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respuestaPreguntaUsuarioPK != null ? respuestaPreguntaUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPreguntaUsuario)) {
            return false;
        }
        RespuestaPreguntaUsuario other = (RespuestaPreguntaUsuario) object;
        if ((this.respuestaPreguntaUsuarioPK == null && other.respuestaPreguntaUsuarioPK != null) || (this.respuestaPreguntaUsuarioPK != null && !this.respuestaPreguntaUsuarioPK.equals(other.respuestaPreguntaUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RespuestaPreguntaUsuario[ respuestaPreguntaUsuarioPK=" + respuestaPreguntaUsuarioPK + " ]";
    }
    
}
