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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.SEGMENTO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SegmentoIfip.findAll", query = "SELECT s FROM SegmentoIfip s"),
    @NamedQuery(name = "SegmentoIfip.findByCodigo", query = "SELECT s FROM SegmentoIfip s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "SegmentoIfip.findBySegmento", query = "SELECT s FROM SegmentoIfip s WHERE s.segmento = :segmento"),
    @NamedQuery(name = "SegmentoIfip.findByNombre", query = "SELECT s FROM SegmentoIfip s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SegmentoIfip.findByDescripcion", query = "SELECT s FROM SegmentoIfip s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SegmentoIfip.findByEliminado", query = "SELECT s FROM SegmentoIfip s WHERE s.eliminado = :eliminado")})
public class SegmentoIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEGMENTO")
    private short segmento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSegmentoIfip")
    private Collection<Ifip> ifipCollection;

    public SegmentoIfip() {
    }

    public SegmentoIfip(Long codigo) {
        this.codigo = codigo;
    }

    public SegmentoIfip(Long codigo, short segmento, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.segmento = segmento;
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

    public short getSegmento() {
        return segmento;
    }

    public void setSegmento(short segmento) {
        this.segmento = segmento;
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
    public Collection<Ifip> getIfipCollection() {
        return ifipCollection;
    }

    public void setIfipCollection(Collection<Ifip> ifipCollection) {
        this.ifipCollection = ifipCollection;
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
        if (!(object instanceof SegmentoIfip)) {
            return false;
        }
        SegmentoIfip other = (SegmentoIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.SegmentoIfip[ codigo=" + codigo + " ]";
    }
    
}
