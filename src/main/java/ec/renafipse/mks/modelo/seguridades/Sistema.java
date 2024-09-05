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
@Table(name = "MKS_SEGURIDADES.SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sistema.findAll", query = "SELECT s FROM Sistema s"),
    @NamedQuery(name = "Sistema.findByCodigo", query = "SELECT s FROM Sistema s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Sistema.findByNombre", query = "SELECT s FROM Sistema s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sistema.findByUrlLogin", query = "SELECT s FROM Sistema s WHERE s.urlLogin = :urlLogin"),
    @NamedQuery(name = "Sistema.findByEliminado", query = "SELECT s FROM Sistema s WHERE s.eliminado = :eliminado")})

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "URL_LOGIN")
    private String urlLogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSistema")
    private Collection<Menu> menuCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sistema")
    private Collection<IfipSistema> ifipSistemaCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sistema")
    private Collection<UsuarioSistema> usuarioSistemaCollection;

    public Sistema() {
    }

    public Sistema(Long codigo) {
        this.codigo = codigo;
    }

    public Sistema(Long codigo, String nombre, String urlLogin, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.urlLogin = urlLogin;
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

    public String getUrlLogin() {
        return urlLogin;
    }

    public void setUrlLogin(String urlLogin) {
        this.urlLogin = urlLogin;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<IfipSistema> getIfipSistemaCollection() {
        return ifipSistemaCollection;
    }

    public void setIfipSistemaCollection(Collection<IfipSistema> ifipSistemaCollection) {
        this.ifipSistemaCollection = ifipSistemaCollection;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    @XmlTransient
    public Collection<UsuarioSistema> getUsuarioSistemaCollection() {
        return usuarioSistemaCollection;
    }

    public void setUsuarioSistemaCollection(Collection<UsuarioSistema> usuarioSistemaCollection) {
        this.usuarioSistemaCollection = usuarioSistemaCollection;
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
        if (!(object instanceof Sistema)) {
            return false;
        }
        Sistema other = (Sistema) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.Sistema[ codigo=" + codigo + " ]";
    }

    
    
}
