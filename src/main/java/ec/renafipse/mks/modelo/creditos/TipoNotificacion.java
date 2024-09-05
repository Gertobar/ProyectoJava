/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_NOTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNotificacion.findAll", query = "SELECT t FROM TipoNotificacion t"),
    @NamedQuery(name = "TipoNotificacion.findByCodigo", query = "SELECT t FROM TipoNotificacion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoNotificacion.findByNombre", query = "SELECT t FROM TipoNotificacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoNotificacion.findByDescripcion", query = "SELECT t FROM TipoNotificacion t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoNotificacion.findByTexto", query = "SELECT t FROM TipoNotificacion t WHERE t.texto = :texto"),
    @NamedQuery(name = "TipoNotificacion.findByEliminado", query = "SELECT t FROM TipoNotificacion t WHERE t.eliminado = :eliminado")})
public class TipoNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_NOTIFICACION")
    @SequenceGenerator(name = "GSQ_TIPO_NOTIFICACION", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_TIPO_NOTIFICACION")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "TEXTO")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoNotificacion")
    private Collection<TipoNotificacionIfip> tipoNotificacionIfipCollection;

    public TipoNotificacion() {
    }

    public TipoNotificacion(Long codigo) {
        this.codigo = codigo;
    }

    public TipoNotificacion(Long codigo, String nombre, String descripcion, String texto, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.texto = texto;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<TipoNotificacionIfip> getTipoNotificacionIfipCollection() {
        return tipoNotificacionIfipCollection;
    }

    public void setTipoNotificacionIfipCollection(Collection<TipoNotificacionIfip> tipoNotificacionIfipCollection) {
        this.tipoNotificacionIfipCollection = tipoNotificacionIfipCollection;
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
        if (!(object instanceof TipoNotificacion)) {
            return false;
        }
        TipoNotificacion other = (TipoNotificacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoNotificacion[ codigo=" + codigo + " ]";
    }
    
}
