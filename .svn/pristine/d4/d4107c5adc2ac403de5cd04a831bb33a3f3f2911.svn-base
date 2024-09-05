/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

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
 * @author FliaAstudillo
 */
@Entity
@Table(name = "MKS_IFIPS.PARAMETRO_SERVIDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroServidor.findAll", query = "SELECT p FROM ParametroServidor p"),
    @NamedQuery(name = "ParametroServidor.findByCodigo", query = "SELECT p FROM ParametroServidor p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroServidor.findByNombre", query = "SELECT p FROM ParametroServidor p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ParametroServidor.findByDescripcion", query = "SELECT p FROM ParametroServidor p WHERE p.descripcion = :descripcion")})
public class ParametroServidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroServidor")
    private Collection<ParametroServidorIfip> parametroServidorIfipCollection;

    public ParametroServidor() {
    }

    public ParametroServidor(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroServidor(Long codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    @XmlTransient
    public Collection<ParametroServidorIfip> getParametroServidorIfipCollection() {
        return parametroServidorIfipCollection;
    }

    public void setParametroServidorIfipCollection(Collection<ParametroServidorIfip> parametroServidorIfipCollection) {
        this.parametroServidorIfipCollection = parametroServidorIfipCollection;
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
        if (!(object instanceof ParametroServidor)) {
            return false;
        }
        ParametroServidor other = (ParametroServidor) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ifips.ParametroServidor[ codigo=" + codigo + " ]";
    }
    
}
