/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SEGURIDADES.BANCO_PREGUNTA_SESION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BancoPreguntaSesion.findAll", query = "SELECT b FROM BancoPreguntaSesion b"),
    @NamedQuery(name = "BancoPreguntaSesion.findByCodigo", query = "SELECT b FROM BancoPreguntaSesion b WHERE b.codigo = :codigo"),
    @NamedQuery(name = "BancoPreguntaSesion.findByDescripcion", query = "SELECT b FROM BancoPreguntaSesion b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BancoPreguntaSesion.findByEliminado", query = "SELECT b FROM BancoPreguntaSesion b WHERE b.eliminado = :eliminado")})
public class BancoPreguntaSesion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_BANCO_PREGUNTA_SESION")
    @SequenceGenerator(name = "GSQ_BANCO_PREGUNTA_SESION", schema = "MKS_SEGURIDADES", allocationSize = 0, sequenceName = "SEQ_BANCO_PREGUNTA_SESION")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bancoPreguntaSesion")
    private Collection<RespuestaPreguntaUsuario> respuestaPreguntaUsuarioCollection;

    public BancoPreguntaSesion() {
    }

    public BancoPreguntaSesion(Long codigo) {
        this.codigo = codigo;
    }

    public BancoPreguntaSesion(Long codigo, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<RespuestaPreguntaUsuario> getRespuestaPreguntaUsuarioCollection() {
        return respuestaPreguntaUsuarioCollection;
    }

    public void setRespuestaPreguntaUsuarioCollection(Collection<RespuestaPreguntaUsuario> respuestaPreguntaUsuarioCollection) {
        this.respuestaPreguntaUsuarioCollection = respuestaPreguntaUsuarioCollection;
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
        if (!(object instanceof BancoPreguntaSesion)) {
            return false;
        }
        BancoPreguntaSesion other = (BancoPreguntaSesion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.BancoPreguntaSesion[ codigo=" + codigo + " ]";
    }
    
}
