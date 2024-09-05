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
@Table(name = "MKS_SEGURIDADES.MOTIVO_EXPIRA_CONTRASENA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoExpiraContrasena.findAll", query = "SELECT m FROM MotivoExpiraContrasena m"),
    @NamedQuery(name = "MotivoExpiraContrasena.findByCodigo", query = "SELECT m FROM MotivoExpiraContrasena m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MotivoExpiraContrasena.findByNombre", query = "SELECT m FROM MotivoExpiraContrasena m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MotivoExpiraContrasena.findByDescripcion", query = "SELECT m FROM MotivoExpiraContrasena m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MotivoExpiraContrasena.findByEliminado", query = "SELECT m FROM MotivoExpiraContrasena m WHERE m.eliminado = :eliminado")})
public class MotivoExpiraContrasena implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_MOTIVO_EXPIRA_CONTRASENA")
    @SequenceGenerator(name = "GSQ_MOTIVO_EXPIRA_CONTRASENA", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_MOTIVO_EXPIRA_CONTRASENA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMotivoExpira")
    private Collection<ExpiracionContrasena> expiracionContrasenaCollection;

    public MotivoExpiraContrasena() {
    }

    public MotivoExpiraContrasena(Long codigo) {
        this.codigo = codigo;
    }

    public MotivoExpiraContrasena(Long codigo, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<ExpiracionContrasena> getExpiracionContrasenaCollection() {
        return expiracionContrasenaCollection;
    }

    public void setExpiracionContrasenaCollection(Collection<ExpiracionContrasena> expiracionContrasenaCollection) {
        this.expiracionContrasenaCollection = expiracionContrasenaCollection;
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
        if (!(object instanceof MotivoExpiraContrasena)) {
            return false;
        }
        MotivoExpiraContrasena other = (MotivoExpiraContrasena) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.MotivoExpiraContrasena[ codigo=" + codigo + " ]";
    }
    
}
