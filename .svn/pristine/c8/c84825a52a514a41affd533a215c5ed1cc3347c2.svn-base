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
import javax.persistence.FetchType;
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
@Table(name = "MKS_SEGURIDADES.ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByCodigo", query = "SELECT r FROM Rol r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Rol.findByDescripcion", query = "SELECT r FROM Rol r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Rol.findByEliminado", query = "SELECT r FROM Rol r WHERE r.eliminado = :eliminado"),
    @NamedQuery(name = "Rol.findByRolEliminado", query = "SELECT r FROM Rol r WHERE r.codigo = :codigo AND r.eliminado = :eliminado")        
    })

public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado="Rol.findByEliminado";
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUEDE_ABRIR_CAJA")
    private char puedeAbrirCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUEDE_REPORTE_TODAS_AGENCIAS")
    private char puedeReporteTodasAgencias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
    private Collection<RolOpcionOpePorDef> rolOpcionOpePorDefCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
    private Collection<RolOpcionOperacion> rolOpcionOperacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRol", fetch = FetchType.LAZY)
    private Collection<UsuarioPuntoIfipCobroPago> usuarioPuntoIfipCobroPagoCollection;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRol",fetch = FetchType.LAZY)
    private Collection<UsuarioIfipAgencia> usuarioIfipAgenciaCollection;

    public Rol() {
    }

    public Rol(String codigo) {
        this.codigo = codigo;
    }

    public Rol(String codigo, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
    public Collection<UsuarioPuntoIfipCobroPago> getUsuarioPuntoIfipCobroPagoCollection() {
        return usuarioPuntoIfipCobroPagoCollection;
    }

    public void setUsuarioPuntoIfipCobroPagoCollection(Collection<UsuarioPuntoIfipCobroPago> usuarioPuntoIfipCobroPagoCollection) {
        this.usuarioPuntoIfipCobroPagoCollection = usuarioPuntoIfipCobroPagoCollection;
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
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.Rol[ codigo=" + codigo + " ]";
    }

    

    @XmlTransient
    public Collection<RolOpcionOpePorDef> getRolOpcionOpePorDefCollection() {
        return rolOpcionOpePorDefCollection;
    }

    public void setRolOpcionOpePorDefCollection(Collection<RolOpcionOpePorDef> rolOpcionOpePorDefCollection) {
        this.rolOpcionOpePorDefCollection = rolOpcionOpePorDefCollection;
    }

    @XmlTransient
    public Collection<RolOpcionOperacion> getRolOpcionOperacionCollection() {
        return rolOpcionOperacionCollection;
    }

    public void setRolOpcionOperacionCollection(Collection<RolOpcionOperacion> rolOpcionOperacionCollection) {
        this.rolOpcionOperacionCollection = rolOpcionOperacionCollection;
    }

    /**
     * @return the puedeAbrirCaja
     */
    public char getPuedeAbrirCaja() {
        return puedeAbrirCaja;
    }

    /**
     * @param puedeAbrirCaja the puedeAbrirCaja to set
     */
    public void setPuedeAbrirCaja(char puedeAbrirCaja) {
        this.puedeAbrirCaja = puedeAbrirCaja;
    }

    /**
     * @return the puedeReporteTodasAgencias
     */
    public char getPuedeReporteTodasAgencias() {
        return puedeReporteTodasAgencias;
    }

    /**
     * @param puedeReporteTodasAgencias the puedeReporteTodasAgencias to set
     */
    public void setPuedeReporteTodasAgencias(char puedeReporteTodasAgencias) {
        this.puedeReporteTodasAgencias = puedeReporteTodasAgencias;
    }

    
    
}
