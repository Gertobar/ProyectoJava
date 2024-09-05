/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos.lineacredito;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO_ESTADO_SOL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCreditoEstadoSol.findByCodigo", query = "SELECT l FROM LineaCreditoEstadoSol l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LineaCreditoEstadoSol.findCambioEstado", query = "SELECT l FROM LineaCreditoEstadoSol l WHERE l.codigo IN (7,8,9) AND l.eliminado = 'N'"),
    @NamedQuery(name = "LineaCreditoEstadoSol.findByCodigoYEliminado", query = "SELECT l FROM LineaCreditoEstadoSol l WHERE l.codigo = :codigo AND l.eliminado = :eliminado")
})
public class LineaCreditoEstadoSol implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(mappedBy = "codigoEstadoDependiente")
    private Collection<LineaCreditoEstadoSol> lineaCreditoEstadoSolCollection;
    @JoinColumn(name = "CODIGO_ESTADO_DEPENDIENTE", referencedColumnName = "CODIGO")
    @ManyToOne
    private LineaCreditoEstadoSol codigoEstadoDependiente;

    public LineaCreditoEstadoSol() {
    }

    public LineaCreditoEstadoSol(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoEstadoSol(Long codigo, String nombre, String descripcion, char eliminado) {
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
    public Collection<LineaCreditoEstadoSol> getLineaCreditoEstadoSolCollection() {
        return lineaCreditoEstadoSolCollection;
    }

    public void setLineaCreditoEstadoSolCollection(Collection<LineaCreditoEstadoSol> lineaCreditoEstadoSolCollection) {
        this.lineaCreditoEstadoSolCollection = lineaCreditoEstadoSolCollection;
    }

    public LineaCreditoEstadoSol getCodigoEstadoDependiente() {
        return codigoEstadoDependiente;
    }

    public void setCodigoEstadoDependiente(LineaCreditoEstadoSol codigoEstadoDependiente) {
        this.codigoEstadoDependiente = codigoEstadoDependiente;
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
        if (!(object instanceof LineaCreditoEstadoSol)) {
            return false;
        }
        LineaCreditoEstadoSol other = (LineaCreditoEstadoSol) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoEstadoSol[ codigo=" + codigo + " ]";
    }
    
}
