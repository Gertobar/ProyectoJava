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
@Table(name = "MKS_SEGURIDADES.TIPO_OPERACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOperacion.findAll", query = "SELECT t FROM TipoOperacion t"),
    @NamedQuery(name = "TipoOperacion.findByCodigo", query = "SELECT t FROM TipoOperacion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoOperacion.findByNombre", query = "SELECT t FROM TipoOperacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoOperacion.findByEliminado", query = "SELECT t FROM TipoOperacion t WHERE t.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "TipoOperacion.findByTipoOperacionExistentesOpOp", query = "SELECT t FROM TipoOperacion t JOIN t.opcionOperacionCollection p WHERE p.codigoMenu.codigo = :codigoMenu") 
})
public class TipoOperacion implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoOperacion.findByEliminado";
    public static final String findByTipoOperacionExistentesOpOp = "TipoOperacion.findByTipoOperacionExistentesOpOp";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_OPERACION")
    @SequenceGenerator(name = "GSQ_TIPO_OPERACION", schema = "MKS_SEGURIDADES", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_TIPO_OPERACION")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoOperacion")
    private Collection<OpcionOperacion> opcionOperacionCollection;

    public TipoOperacion() {
    }

    public TipoOperacion(Long codigo) {
        this.codigo = codigo;
    }

    public TipoOperacion(Long codigo, String nombre, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<OpcionOperacion> getOperacionCollection() {
        return opcionOperacionCollection;
    }

    public void setOperacionCollection(Collection<OpcionOperacion> operacionCollection) {
        this.opcionOperacionCollection = operacionCollection;
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
        if (!(object instanceof TipoOperacion)) {
            return false;
        }
        TipoOperacion other = (TipoOperacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.TipoOperacion[ codigo=" + codigo + " ]";
    }

}
