/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.seguridades;

import ec.renafipse.mks.modelo.seguridades.UsuarioIfipAgencia;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MKS_SEGURIDADES.PERFIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findByCodigo", query = "SELECT p FROM Perfil p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Perfil.findByNombre", query = "SELECT p FROM Perfil p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Perfil.findByNumeroSesiones", query = "SELECT p FROM Perfil p WHERE p.numeroSesiones = :numeroSesiones"),
    @NamedQuery(name = "Perfil.findByTiempoInactividad", query = "SELECT p FROM Perfil p WHERE p.tiempoInactividad = :tiempoInactividad"),
    @NamedQuery(name = "Perfil.findByIntentosFallidos", query = "SELECT p FROM Perfil p WHERE p.intentosFallidos = :intentosFallidos"),
    @NamedQuery(name = "Perfil.findByEliminado", query = "SELECT p FROM Perfil p WHERE p.eliminado = :eliminado")})
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "Perfil.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_SESIONES")
    private short numeroSesiones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_INACTIVIDAD")
    private long tiempoInactividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTENTOS_FALLIDOS")
    private long intentosFallidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPerfil")
    private Collection<UsuarioIfipAgencia> usuarioIfipAgenciaCollection;

    public Perfil() {
    }

    public Perfil(Long codigo) {
        this.codigo = codigo;
    }

    public Perfil(Long codigo, String nombre, short numeroSesiones, long tiempoInactividad, long intentosFallidos, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroSesiones = numeroSesiones;
        this.tiempoInactividad = tiempoInactividad;
        this.intentosFallidos = intentosFallidos;
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

    public short getNumeroSesiones() {
        return numeroSesiones;
    }

    public void setNumeroSesiones(short numeroSesiones) {
        this.numeroSesiones = numeroSesiones;
    }

    public long getTiempoInactividad() {
        return tiempoInactividad;
    }

    public void setTiempoInactividad(long tiempoInactividad) {
        this.tiempoInactividad = tiempoInactividad;
    }

    public long getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(long intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<UsuarioIfipAgencia> getUsuarioIfipAgenciaCollection() {
        return usuarioIfipAgenciaCollection;
    }

    public void setUsuarioIfipAgenciaCollection(Collection<UsuarioIfipAgencia> usuarioIfipAgenciaCollection) {
        this.usuarioIfipAgenciaCollection = usuarioIfipAgenciaCollection;
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
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mksseguridades.control.Perfil[ codigo=" + codigo + " ]";
    }

}
